package net.laith.avaritia.common.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

import java.util.Arrays;

public class NeutroniumCompressorRecipe implements Recipe<SimpleInventory> {
    private final Identifier id;
    private final Ingredient input;
    private final int cost;
    private final ItemStack output;
    private int progress;


    public NeutroniumCompressorRecipe(Identifier id, Ingredient input, int cost, ItemStack output) {
      this.id = id;
      this.input = input;
      this.cost = cost;
      this.output = output;
        this.progress = 0;


    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if(world.isClient) {
            return false;
        }
        ItemStack input = inventory.getStack(0);

        return this.input.test(input);
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public ItemStack getOutput(DynamicRegistryManager registryManager) {
        return output.copy();
    }

    public ItemStack getA () {
        return output;
    }


    public int getCost() {
        return cost;
    }


    @Override
    public Identifier getId() {
        return id;
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
        public static final Identifier ID = new Identifier("compressor");

        @Override
        public NeutroniumCompressorRecipe read(Identifier id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("input"));
            int cost = json.get("cost").getAsInt();
            ItemStack output = ShapedRecipe.outputFromJson(json.getAsJsonObject("output"));

            return new NeutroniumCompressorRecipe(id, input, cost, output);
        }

        @Override
        public NeutroniumCompressorRecipe read(Identifier id, PacketByteBuf buf) {
            Ingredient input = Ingredient.fromPacket(buf);
            int cost = buf.readInt();
            ItemStack output = buf.readItemStack();

            return new NeutroniumCompressorRecipe(id, input, cost, output);
        }

        @Override
        public void write(PacketByteBuf buf, NeutroniumCompressorRecipe recipe) {
            recipe.input.write(buf);
            buf.writeInt(recipe.cost);
            buf.writeItemStack(recipe.output);

        }
    }
}
