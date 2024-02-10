package net.laith.avaritia.compat.rei.display.extreme;

import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapelessRecipe;

import java.util.Collections;
import java.util.Optional;

public class ExtremeCraftingShapelessDisplay extends ExtremeCraftingDisplay<ExtremeCraftingShapelessRecipe> {

    public ExtremeCraftingShapelessDisplay(ExtremeCraftingShapelessRecipe recipe) {
        super(
                EntryIngredients.ofIngredients(recipe.getIngredients()),
                Collections.singletonList(EntryIngredients.of(recipe.getResultItem(BasicDisplay.registryAccess()))),
                Optional.of(recipe)
        );
    }

    @Override
    public int getWidth() {
        return getInputEntries().size() > 4 ? 3 : 2;
    }

    @Override
    public int getHeight() {
        return getInputEntries().size() > 4 ? 3 : 2;
    }

    @Override
    public int getInputWidth() {
        return Math.min(getInputEntries().size(), 9);
    }

    @Override
    public int getInputWidth(int craftingWidth, int craftingHeight) {
        return craftingWidth * craftingHeight <= getInputEntries().size() ? craftingWidth : Math.min(getInputEntries().size(), 9);
    }

    @Override
    public int getInputHeight() {
        return (int) Math.ceil(getInputEntries().size() / (double) getInputWidth());
    }

    @Override
    public int getInputHeight(int craftingWidth, int craftingHeight) {
        return (int) Math.ceil(getInputEntries().size() / (double) getInputWidth(craftingWidth, craftingHeight));
    }

    @Override
    public boolean isShapeless() {
        return true;
    }
}
