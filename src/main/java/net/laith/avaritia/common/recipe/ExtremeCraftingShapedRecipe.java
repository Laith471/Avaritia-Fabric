package net.laith.avaritia.common.recipe;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.level.Level;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ExtremeCraftingShapedRecipe
        implements ExtremeRecipe {
    final int width;
    final int height;
    final NonNullList<Ingredient> input;
    final ItemStack output;
    private final ResourceLocation resourceLocation;
    final String group;

    public ExtremeCraftingShapedRecipe(ResourceLocation resourceLocation, String group, int width, int height, NonNullList<Ingredient> input, ItemStack output) {
        this.resourceLocation = resourceLocation;
        this.group = group;
        this.width = width;
        this.height = height;
        this.input = input;
        this.output = output;
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
    public ResourceLocation getId() {
        return this.resourceLocation;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.input;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width >= this.width && height >= this.height;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output;
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        for (int i = 0; i <= container.getWidth() - this.width; ++i) {
            for (int j = 0; j <= container.getHeight() - this.height; ++j) {
                if (this.matchesPattern(container, i, j, true)) {
                    return true;
                }
                if (!this.matchesPattern(container, i, j, false)) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer inventory, RegistryAccess registryAccess) {
        return this.getResultItem(registryAccess).copy();
    }

    private boolean matchesPattern(CraftingContainer container, int offsetX, int offsetY, boolean flipped) {
        for (int i = 0; i < container.getWidth(); ++i) {
            for (int j = 0; j < container.getHeight(); ++j) {
                int k = i - offsetX;
                int l = j - offsetY;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    ingredient = flipped ? this.input.get(this.width - k - 1 + l * this.width) : this.input.get(k + l * this.width);
                }
                if (ingredient.test(container.getItem(i + j * container.getWidth()))) continue;
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
    static NonNullList<Ingredient> dissolvePattern(String[] pattern, Map<String, Ingredient> symbols, int width, int height) {
        NonNullList<Ingredient> defaultedList = NonNullList.withSize(width * height, Ingredient.EMPTY);
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

    @Override
    public boolean isIncomplete() {
        NonNullList<Ingredient> nonNullList = this.getIngredients();
        return nonNullList.isEmpty() || nonNullList.stream().filter((ingredient) -> {
            return !ingredient.isEmpty();
        }).anyMatch((ingredient) -> {
            return ingredient.getItems().length == 0;
        });
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
            String string = GsonHelper.convertToString(json.get(i), "pattern[" + i + "]");
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
        int i = GsonHelper.getAsInt(json, "count", 1);
        if (i < 1) {
            throw new JsonSyntaxException("Invalid output count: " + i);
        }
        return new ItemStack(item, i);
    }

    public static Item getItem(JsonObject json) {
        String string = GsonHelper.getAsString(json, "item");
        Item item = (Item) BuiltInRegistries.ITEM.getOptional(new ResourceLocation(string)).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + string + "'"));
        if (item == Items.AIR) {
            throw new JsonSyntaxException("Invalid item: " + string);
        }
        return item;
    }

    public static class Serializer implements RecipeSerializer<ExtremeCraftingShapedRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("shaped");

        @Override

        public ExtremeCraftingShapedRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            String string = GsonHelper.getAsString(json, "group", "");
            Map<String, Ingredient> map = ExtremeCraftingShapedRecipe.readSymbols(GsonHelper.getAsJsonObject(json, "key"));
            String[] strings = (ExtremeCraftingShapedRecipe.getPattern(GsonHelper.getAsJsonArray(json, "pattern")));
            int i = strings[0].length();
            int j = strings.length;
            NonNullList<Ingredient> defaultedList = ExtremeCraftingShapedRecipe.dissolvePattern(strings, map, i, j);
            ItemStack itemStack = ExtremeCraftingShapedRecipe.outputFromJson(GsonHelper.getAsJsonObject(json, "result"));
            return new ExtremeCraftingShapedRecipe(recipeId, string, i, j, defaultedList, itemStack);
        }

        @Override
        public ExtremeCraftingShapedRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int i = buffer.readVarInt();
            int j = buffer.readVarInt();
            String string = buffer.readUtf();
            NonNullList<Ingredient> defaultedList = NonNullList.withSize(i * j, Ingredient.EMPTY);
            for (int k = 0; k < defaultedList.size(); ++k) {
                defaultedList.set(k, Ingredient.fromNetwork(buffer));
            }
            ItemStack itemStack = buffer.readItem();
            return new ExtremeCraftingShapedRecipe(recipeId, string, i, j, defaultedList, itemStack);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, ExtremeCraftingShapedRecipe shapedRecipe) {
            buffer.writeVarInt(shapedRecipe.width);
            buffer.writeVarInt(shapedRecipe.height);
            buffer.writeUtf(shapedRecipe.group);
            for (Ingredient ingredient : shapedRecipe.input) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeItem(shapedRecipe.output);
        }
    }
}
