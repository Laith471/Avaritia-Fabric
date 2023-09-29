package net.laith.avaritia.compat.rei.display.extreme;

import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;

import java.util.Collections;
import java.util.Optional;
public class ExtremeCraftingShapedDisplay extends ExtremeCraftingDisplay<ExtremeCraftingShapedRecipe> {

    public ExtremeCraftingShapedDisplay(ExtremeCraftingShapedRecipe recipe) {
        super(
                EntryIngredients.ofIngredients(recipe.getIngredients()),
                Collections.singletonList(EntryIngredients.of(recipe.getOutput(BasicDisplay.registryAccess()))),
                Optional.of(recipe)
        );
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public int getWidth() {
        return 9;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public int getHeight() {
        return 9;
    }
}