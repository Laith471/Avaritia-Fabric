package net.laith.avaritia.common.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExtremeCraftingShapedRecipe
        implements ExtremeRecipe {
    final int width;
    final int height;
    final DefaultedList<Ingredient> input;
    final ItemStack output;
    private final Identifier id;
    final String group;

    public ExtremeCraftingShapedRecipe(Identifier id, String group, int width, int height, DefaultedList<Ingredient> input, ItemStack output) {
        this.id = id;
        this.group = group;
        this.width = width;
        this.height = height;
        this.input = input;
        this.output = output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public String getGroup() {
        return this.group;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        return this.input;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output;
    }

    @Override
    public boolean matches(CraftingInventory craftingInventory, World world) {
        for (int i = 0; i <= craftingInventory.getWidth() - this.width; ++i) {
            for (int j = 0; j <= craftingInventory.getHeight() - this.height; ++j) {
                if (this.matchesPattern(craftingInventory, i, j, true)) {
                    return true;
                }
                if (!this.matchesPattern(craftingInventory, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory, DynamicRegistryManager registryManager) {
        return this.getOutput(registryManager).copy();
    }

    private boolean matchesPattern(CraftingInventory inv, int offsetX, int offsetY, boolean flipped) {
        for (int i = 0; i < inv.getWidth(); ++i) {
            for (int j = 0; j < inv.getHeight(); ++j) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    ingredient = flipped ? this.input.get(this.width - k - 1 + l * this.width) : this.input.get(k + l * this.width);
                }
                if (ingredient.test(inv.getStack(i + j * inv.getWidth()))) continue;
                return false;
            }
        }
        return true;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Compiles a pattern and series of symbols into a list of ingredients (the matrix) suitable for matching
     * against a crafting grid.
     */
    static DefaultedList<Ingredient> createPatternMatrix(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(width * height, Ingredient.EMPTY);
        HashSet<String> set = Sets.newHashSet(symbols.keySet());
        set.remove(" ");
        for (int i = 0; i < pattern.length; ++i) {
            for (int j = 0; j < pattern[i].length(); ++j) {
                String string = pattern[i].substring(j, j + 1);
                Ingredient ingredient = symbols.get(string);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + string + "' but it's not defined in the key");
                }
                set.remove(string);
                defaultedList.set(j + width * i, ingredient);
            }
        }
        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        }
        return defaultedList;
    }

    /**
     * Removes empty space from around the recipe pattern.
     *
     * <p>Turns patterns such as:</p>
     * <pre>
     * {@code
     * "   o"
     * "   a"
     * "    "
     * }
     * </pre>
     * Into:
     * <pre>
     * {@code
     * "o"
     * "a"
     * }
     * </pre>
     *
     * @return a new recipe pattern with all leading and trailing empty rows/columns removed
     */
    @VisibleForTesting
    static String[] removePadding(String ... pattern) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;
        for (int m = 0; m < pattern.length; ++m) {
            String string = pattern[m];
            i = Math.min(i, ExtremeCraftingShapedRecipe.findFirstSymbol(string));
            int n = ExtremeCraftingShapedRecipe.findLastSymbol(string);
            j = Math.max(j, n);
            if (n < 0) {
                if (k == m) {
                    ++k;
                }
                ++l;
                continue;
            }
            l = 0;
        }
        if (pattern.length == l) {
            return new String[0];
        }
        String[] strings = new String[pattern.length - l - k];
        for (int o = 0; o < strings.length; ++o) {
            strings[o] = pattern[o + k].substring(i, j + 1);
        }
        return strings;
    }

    @Override
    public boolean isEmpty() {
        DefaultedList<Ingredient> defaultedList = this.getIngredients();
        return defaultedList.isEmpty() || defaultedList.stream().filter(ingredient -> !ingredient.isEmpty()).anyMatch(ingredient -> ingredient.getMatchingStacks().length == 0);
    }

    private static int findFirstSymbol(String line) {
        int i;
        for (i = 0; i < line.length() && line.charAt(i) == ' '; ++i) {
        }
        return i;
    }

    private static int findLastSymbol(String pattern) {
        int i;
        for (i = pattern.length() - 1; i >= 0 && pattern.charAt(i) == ' '; --i) {
        }
        return i;
    }

    static String[] getPattern(JsonArray json) {
        String[] strings = new String[json.size()];
        if (strings.length > 9) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, 9 is maximum");
        }
        if (strings.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        }
        for (int i = 0; i < strings.length; ++i) {
            String string = JsonHelper.asString(json.get(i), "pattern[" + i + "]");
            if (string.length() > 9) {
                throw new JsonSyntaxException("Invalid pattern: too many columns, 9 is maximum");
            }
            if (i > 0 && strings[0].length() != string.length()) {
                throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
            }
            strings[i] = string;
        }
        return strings;
    }

    /**
     * Reads the pattern symbols.
     *
     * @return a mapping from a symbol to the ingredient it represents
     */
    static Map<String, Ingredient> readSymbols(JsonObject json) {
        HashMap<String, Ingredient> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + entry.getKey() + "' is an invalid symbol (must be 1 character only).");
            }
            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }
            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue(), false));
        }
        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    public static ItemStack outputFromJson(JsonObject json) {
        Item item = ExtremeCraftingShapedRecipe.getItem(json);
        if (json.has("data")) {
            throw new JsonParseException("Disallowed data tag found");
        }
        int i = JsonHelper.getInt(json, "count", 1);
        if (i < 1) {
            throw new JsonSyntaxException("Invalid output count: " + i);
        }
        return new ItemStack(item, i);
    }

    public static Item getItem(JsonObject json) {
        String string = JsonHelper.getString(json, "item");
        Item item = Registries.ITEM.getOrEmpty(new Identifier(string)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + string + "'"));
        if (item == Items.AIR) {
            throw new JsonSyntaxException("Invalid item: " + string);
        }
        return item;
    }

    public static class Serializer implements RecipeSerializer<ExtremeCraftingShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID = new Identifier("shaped");

        @Override

        public ExtremeCraftingShapedRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            Map<String, Ingredient> map = ExtremeCraftingShapedRecipe.readSymbols(JsonHelper.getObject(jsonObject, "key"));
            String[] strings = ExtremeCraftingShapedRecipe.removePadding(ExtremeCraftingShapedRecipe.getPattern(JsonHelper.getArray(jsonObject, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            DefaultedList<Ingredient> defaultedList = ExtremeCraftingShapedRecipe.createPatternMatrix(strings, map, i, j);
            ItemStack itemStack = ExtremeCraftingShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
            return new ExtremeCraftingShapedRecipe(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public ExtremeCraftingShapedRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            int i = packetByteBuf.readVarInt();
            int j = packetByteBuf.readVarInt();
            String string = packetByteBuf.readString();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i * j, Ingredient.EMPTY);
            for (int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromPacket(packetByteBuf));
            }
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ExtremeCraftingShapedRecipe(identifier, string, i, j, defaultedList, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, ExtremeCraftingShapedRecipe shapedRecipe) {
            packetByteBuf.writeVarInt(shapedRecipe.width);
            packetByteBuf.writeVarInt(shapedRecipe.height);
            packetByteBuf.writeString(shapedRecipe.group);
            for (Ingredient ingredient : shapedRecipe.input) {
                ingredient.write(packetByteBuf);
            }
            packetByteBuf.writeItemStack(shapedRecipe.output);
        }
    }
}
