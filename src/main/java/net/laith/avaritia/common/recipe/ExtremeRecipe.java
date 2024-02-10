package net.laith.avaritia.common.recipe;

import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public interface ExtremeRecipe extends Recipe<CraftingContainer> {
    @Override
    default RecipeType<?> getType() {
        return Type.INSTANCE;
    }


    class Type implements RecipeType<ExtremeRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "extreme_crafting";
    }
}
