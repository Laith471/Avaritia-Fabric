package net.laith.avaritia.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.minecraft.network.chat.Component;

public class ExtremeCraftingCategory implements IRecipeCategory<ExtremeRecipe> {

    @Override
    public RecipeType<ExtremeRecipe> getRecipeType() {
        return null;
    }

    @Override
    public Component getTitle() {
        return Component.literal("");
    }


    @Override
    public IDrawable getBackground() {
        return null;
    }

    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ExtremeRecipe recipe, IFocusGroup focuses) {

    }
}
