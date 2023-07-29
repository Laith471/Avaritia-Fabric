package net.laith.avaritia.common.recipe;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public interface ExtremeRecipe extends Recipe<CraftingInventory> {
    @Override
    default RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    class Type implements RecipeType<ExtremeRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "extreme_crafting";
    }
}
