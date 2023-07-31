package net.laith.avaritia.compat.rei.display;

import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
public class ExtremeCraftingShapedDisplay extends ExtremeCraftingDisplay<ExtremeCraftingShapedRecipe> {

    public ExtremeCraftingShapedDisplay(ExtremeCraftingShapedRecipe recipe) {
        super(EntryIngredients.ofIngredients(recipe.getIngredients()), Collections.singletonList(EntryIngredients.of(recipe.getOutput(null))), Optional.of(recipe));
    }

    @Override
    public int getWidth() {
        return 9;
    }

    @Override
    public int getHeight() {
        return 9;
    }
}