package net.laith.avaritia.common.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class NeutroniumCompressorRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation resourceLocation;
    private final Ingredient input;
    private final int cost;
    private final ItemStack output;
    private int progress;


    public NeutroniumCompressorRecipe(ResourceLocation resourceLocation, Ingredient input, int cost, ItemStack output) {
      this.resourceLocation = resourceLocation;
      this.input = input;
      this.cost = cost;
      this.output = output;
        this.progress = 0;


    }

    @Override
    public boolean matches(SimpleContainer inventory, Level level) {
        if(level.isClientSide) {
            return false;
        }
        ItemStack input = inventory.getItem(0);

        return this.input.test(input);
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess registryAccess) {
        return null;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess RegistryAccess) {
        return output.copy();
    }

    public ItemStack getA () {
        return output;
    }


    public int getCost() {
        return cost;
    }


    @Override
    public ResourceLocation getId() {
        return resourceLocation;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<NeutroniumCompressorRecipe> {
        private Type() {}
        public static final Type INSTANCE = new Type();
        public static final String ID = "neutronium_compressor";
    }
    public static class Serializer implements RecipeSerializer<NeutroniumCompressorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation("compressor");

        @Override
        public NeutroniumCompressorRecipe fromJson(ResourceLocation resourceLocation, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));
            int cost = json.get("cost").getAsInt();
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));

            return new NeutroniumCompressorRecipe(resourceLocation, input, cost, output);
        }

        @Override
        public NeutroniumCompressorRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buffer) {
            Ingredient input = Ingredient.fromNetwork(buffer);
            int cost = buffer.readInt();
            ItemStack output = buffer.readItem();

            return new NeutroniumCompressorRecipe(resourceLocation, input, cost, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, NeutroniumCompressorRecipe recipe) {
            recipe.input.toNetwork(buffer);
            buffer.writeInt(recipe.cost);
            buffer.writeItem(recipe.output);

        }
    }
}
