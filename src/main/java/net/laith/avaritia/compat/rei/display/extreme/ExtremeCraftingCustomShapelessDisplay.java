package net.laith.avaritia.compat.rei.display.extreme;

import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import net.minecraft.recipe.Recipe;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ExtremeCraftingCustomShapelessDisplay extends ExtremeCraftingDisplay<Recipe<?>> {
public ExtremeCraftingCustomShapelessDisplay(@Nullable Recipe<?> possibleRecipe, List<EntryIngredient> input, List<EntryIngredient> output) {
        this(null, possibleRecipe, input, output);
        }

public ExtremeCraftingCustomShapelessDisplay(@Nullable Identifier location, @Nullable Recipe<?> possibleRecipe, List<EntryIngredient> input, List<EntryIngredient> output) {
        super(input, output, Optional.ofNullable(location == null && possibleRecipe != null ? possibleRecipe.getId() : location), Optional.ofNullable(possibleRecipe));
        }

public static ExtremeCraftingCustomShapelessDisplay simple(List<EntryIngredient> input, List<EntryIngredient> output, Optional<Identifier> location) {
        Recipe<?> optionalRecipe = location.flatMap(resourceLocation -> RecipeManagerContext.getInstance().getRecipeManager().get(resourceLocation))
        .orElse(null);
        return new ExtremeCraftingCustomShapelessDisplay(location.orElse(null), optionalRecipe, input, output);
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
