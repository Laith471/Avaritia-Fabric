package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapelessRecipe;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModRecipes {

    public static void registerRecipes() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(AvaritiaMod.MOD_ID, "shaped"),
                ExtremeCraftingShapedRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(AvaritiaMod.MOD_ID, "shapeless"),
                ExtremeCraftingShapelessRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "extreme_crafting"),
                ExtremeCraftingShapedRecipe.Type.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, new ResourceLocation(AvaritiaMod.MOD_ID, "compressor"),
                NeutroniumCompressorRecipe.Serializer.INSTANCE);
        Registry.register(BuiltInRegistries.RECIPE_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "neutronium_compressor"),
                NeutroniumCompressorRecipe.Type.INSTANCE);
    }
}
