package net.laith.avaritia.common.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;;

import java.util.Iterator;

public class ExtremeCraftingShapelessRecipe implements ExtremeRecipe {
    private final ResourceLocation resourceLocation;
    final String group;
    final ItemStack output;
    final NonNullList<Ingredient> input;

    public ExtremeCraftingShapelessRecipe(ResourceLocation resourceLocation, String group, ItemStack output, NonNullList<Ingredient> input) {
        this.resourceLocation = resourceLocation;
        this.group = group;
        this.output = output;
        this.input = input;
    }

    public ResourceLocation getId() {
        return this.resourceLocation;
    }

    public RecipeSerializer<?> getSerializer() {
        return ExtremeCraftingShapelessRecipe.Serializer.INSTANCE;
    }

    public String getGroup() {
        return this.group;
    }


    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.output;
    }

    public NonNullList<Ingredient> getIngredients() {
        return this.input;
    }

    private static NonNullList<Ingredient> getIngredients(JsonArray json) {
        NonNullList<Ingredient> nonNullList = NonNullList.create();

        for(int i = 0; i < json.size(); ++i) {
            Ingredient ingredient = Ingredient.fromJson(json.get(i), false);
            if (!ingredient.isEmpty()) {
                nonNullList.add(ingredient);
            }
        }

        return nonNullList;
    }


    public boolean matches(CraftingContainer container, Level level) {
        StackedContents stackedContents = new StackedContents();
        int i = 0;

        for(int j = 0; j < container.getContainerSize(); ++j) {
            ItemStack itemStack = container.getItem(j);
            if (!itemStack.isEmpty()) {
                ++i;
                stackedContents.accountStack(itemStack, 1);
            }
        }

        return i == this.input.size() && stackedContents.canCraft(this, (IntList)null);
    }

    public ItemStack assemble(CraftingContainer container, RegistryAccess registryAccess) {
        return this.output.copy();
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= this.input.size();
    }

    public static class Serializer implements RecipeSerializer<ExtremeCraftingShapelessRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("shapeless");

        public ExtremeCraftingShapelessRecipe fromJson(ResourceLocation identifier, JsonObject jsonObject) {
            String string = GsonHelper.getAsString(jsonObject, "group", "");
            NonNullList<Ingredient> defaultedList = getIngredients(GsonHelper.getAsJsonArray(jsonObject, "ingredients"));
            if (defaultedList.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (defaultedList.size() > 81) {
                throw new JsonParseException("Too many ingredients for shapeless recipe");
            } else {
                ItemStack itemStack = ExtremeCraftingShapedRecipe.outputFromJson(GsonHelper.getAsJsonObject(jsonObject, "result"));
                return new ExtremeCraftingShapelessRecipe(identifier, string, itemStack, defaultedList);
            }
        }

        public ExtremeCraftingShapelessRecipe fromNetwork(ResourceLocation identifier, FriendlyByteBuf buffer) {
            String string = buffer.readUtf();
            int i = buffer.readVarInt();
            NonNullList<Ingredient> nonNullList = NonNullList.withSize(i, Ingredient.EMPTY);

            for(int j = 0; j < nonNullList.size(); ++j) {
                nonNullList.set(j, Ingredient.fromNetwork(buffer));
            }

            ItemStack itemStack = buffer.readItem();
            return new ExtremeCraftingShapelessRecipe(identifier, string, itemStack, nonNullList);
        }

        public void toNetwork(FriendlyByteBuf buffer, ExtremeCraftingShapelessRecipe shapelessRecipe) {
            buffer.writeUtf(shapelessRecipe.group);
            buffer.writeVarInt(shapelessRecipe.input.size());
            Iterator var3 = shapelessRecipe.input.iterator();

            while(var3.hasNext()) {
                Ingredient ingredient = (Ingredient)var3.next();
                ingredient.toNetwork(buffer);
            }

            buffer.writeItem(shapelessRecipe.output);
        }
    }
}
