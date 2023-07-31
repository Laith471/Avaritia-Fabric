package net.laith.avaritia.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.Iterator;

public class ExtremeCraftingShapelessRecipe implements ExtremeRecipe {
    private final Identifier id;
    final String group;
    final ItemStack output;
    final DefaultedList<Ingredient> input;

    public ExtremeCraftingShapelessRecipe(Identifier id, String group, ItemStack output, DefaultedList<Ingredient> input) {
        this.id = id;
        this.group = group;
        this.output = output;
        this.input = input;
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return ExtremeCraftingShapelessRecipe.Serializer.INSTANCE;
    }

    public String getGroup() {
        return this.group;
    }


    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return this.output;
    }

    public DefaultedList<Ingredient> getIngredients() {
        return this.input;
    }


    public boolean matches(CraftingInventory craftingInventory, World world) {
        RecipeMatcher recipeMatcher = new RecipeMatcher();
        int i = 0;

        for(int j = 0; j < craftingInventory.size(); ++j) {
            ItemStack itemStack = craftingInventory.getStack(j);
            if (!itemStack.isEmpty()) {
                ++i;
                recipeMatcher.addInput(itemStack, 1);
            }
        }

        return i == this.input.size() && recipeMatcher.match(this, (IntList)null);
    }

    public ItemStack craft(CraftingInventory craftingInventory, DynamicRegistryManager dynamicRegistryManager) {
        return this.output.copy();
    }

    public boolean fits(int width, int height) {
        return width * height >= this.input.size();
    }

    public static class Serializer implements RecipeSerializer<ExtremeCraftingShapelessRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final Identifier ID = new Identifier("shapeless");

        public ExtremeCraftingShapelessRecipe read(Identifier identifier, JsonObject jsonObject) {
            String string = JsonHelper.getString(jsonObject, "group", "");
            DefaultedList<Ingredient> defaultedList = getIngredients(JsonHelper.getArray(jsonObject, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (defaultedList.size() > 81) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ExtremeCraftingShapedRecipe.outputFromJson(JsonHelper.getObject(jsonObject, "result"));
                return new ExtremeCraftingShapelessRecipe(identifier, string, itemStack, defaultedList);
            }
        }

        private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
            DefaultedList<Ingredient> defaultedList = DefaultedList.of();

            for(int i = 0; i < json.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(json.get(i), false);
                if (!ingredient.isEmpty()) {
                    defaultedList.add(ingredient);
                }
            }

            return defaultedList;
        }

        public ExtremeCraftingShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            String string = packetByteBuf.readString();
            int i = packetByteBuf.readVarInt();
            DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);

            for(int j = 0; j < defaultedList.size(); ++j) {
                defaultedList.set(j, Ingredient.fromPacket(packetByteBuf));
            }

            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ExtremeCraftingShapelessRecipe(identifier, string, itemStack, defaultedList);
        }

        public void write(PacketByteBuf packetByteBuf, ExtremeCraftingShapelessRecipe shapelessRecipe) {
            packetByteBuf.writeString(shapelessRecipe.group);
            packetByteBuf.writeVarInt(shapelessRecipe.input.size());
            Iterator var3 = shapelessRecipe.input.iterator();

            while(var3.hasNext()) {
                Ingredient ingredient = (Ingredient)var3.next();
                ingredient.write(packetByteBuf);
            }

            packetByteBuf.writeItemStack(shapelessRecipe.output);
        }
    }
}
