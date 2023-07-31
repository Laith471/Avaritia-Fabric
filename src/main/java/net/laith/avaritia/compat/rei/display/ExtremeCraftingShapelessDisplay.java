package net.laith.avaritia.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapelessRecipe;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.compat.rei.ServerREIPlugin;
import net.minecraft.recipe.ShapelessRecipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExtremeCraftingShapelessDisplay extends ExtremeCraftingDisplay<ExtremeCraftingShapelessRecipe> {

    public ExtremeCraftingShapelessDisplay(ExtremeCraftingShapelessRecipe recipe) {
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

    @Override
    public boolean isShapeless() {
        return true;
    }
}
