package net.laith.avaritia.compat.rei.display.extreme;

import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ExtremeCraftingCustomShapedDisplay extends ExtremeCraftingDisplay<Recipe<?>> {
    private int width;
    private int height;

    public ExtremeCraftingCustomShapedDisplay(@Nullable Recipe<?> possibleRecipe, List<EntryIngredient> input, List<EntryIngredient> output, int width, int height) {
        this(null, possibleRecipe, input, output, width, height);
    }

    public ExtremeCraftingCustomShapedDisplay(@Nullable ResourceLocation location, @Nullable Recipe<?> possibleRecipe, List<EntryIngredient> input, List<EntryIngredient> output, int width, int height) {
        super(input, output, Optional.ofNullable(location == null && possibleRecipe != null ? possibleRecipe.getId() : location), Optional.ofNullable(possibleRecipe));
        this.width = width;
        this.height = height;
    }

    public static ExtremeCraftingCustomShapedDisplay simple(List<EntryIngredient> input, List<EntryIngredient> output, int width, int height, Optional<ResourceLocation> location) {
        Recipe<?> optionalRecipe = location.flatMap(resourceLocation -> RecipeManagerContext.getInstance().getRecipeManager().byKey(resourceLocation))
                .orElse(null);
        return new ExtremeCraftingCustomShapedDisplay(location.orElse(null), optionalRecipe, input, output, width, height);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
