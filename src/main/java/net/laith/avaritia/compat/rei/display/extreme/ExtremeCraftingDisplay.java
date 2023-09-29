package net.laith.avaritia.compat.rei.display.extreme;

import it.unimi.dsi.fastutil.ints.IntIntImmutablePair;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.SimpleGridMenuDisplay;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.entry.InputIngredient;
import me.shedaniel.rei.api.common.entry.type.VanillaEntryTypes;
import me.shedaniel.rei.api.common.transfer.info.MenuInfo;
import me.shedaniel.rei.api.common.transfer.info.MenuSerializationContext;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleGridMenuInfo;
import me.shedaniel.rei.api.common.util.CollectionUtils;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.plugin.common.displays.crafting.*;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapelessRecipe;
import net.laith.avaritia.compat.rei.ServerREIPlugin;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class ExtremeCraftingDisplay<C extends Recipe<?>> extends BasicDisplay implements SimpleGridMenuDisplay {
    protected Optional<C> recipe;

    public ExtremeCraftingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<C> recipe) {
        this(inputs, outputs, recipe.map(Recipe::getId), recipe);
    }

    public ExtremeCraftingDisplay(List<EntryIngredient> inputs, List<EntryIngredient> outputs, Optional<Identifier> location, Optional<C> recipe) {
        super(inputs, outputs, location);
        this.recipe = recipe;
    }

    private static final List<CraftingRecipeSizeProvider<?>> SIZE_PROVIDER = new ArrayList<>();

    public static <R extends Recipe<?>> void registerSizeProvider(CraftingRecipeSizeProvider<R> sizeProvider) {
        SIZE_PROVIDER.add(0, sizeProvider);
    }

    @Nullable
    public static ExtremeCraftingDisplay<?> of(Recipe<?> recipe) {
        if (recipe instanceof ExtremeCraftingShapelessRecipe) {
            return new ExtremeCraftingShapelessDisplay((ExtremeCraftingShapelessRecipe) recipe);
        } else if (recipe instanceof ExtremeCraftingShapedRecipe) {
            return new ExtremeCraftingShapedDisplay((ExtremeCraftingShapedRecipe) recipe);
        } else if (!recipe.isIgnoredInRecipeBook()) {
            DefaultedList<Ingredient> ingredients = recipe.getIngredients();
            for (CraftingRecipeSizeProvider<?> pair : SIZE_PROVIDER) {
                CraftingRecipeSizeProvider.Size size = ((CraftingRecipeSizeProvider<Recipe<?>>) pair).getSize(recipe);

                if (size != null) {
                    return new ExtremeCraftingCustomShapedDisplay(recipe, EntryIngredients.ofIngredients(recipe.getIngredients()),
                            Collections.singletonList(EntryIngredients.of(recipe.getOutput(BasicDisplay.registryAccess()))),
                            size.getWidth(), size.getHeight());
                }
            }

            return new ExtremeCustomDisplay(recipe, EntryIngredients.ofIngredients(recipe.getIngredients()),
                    Collections.singletonList(EntryIngredients.of(recipe.getOutput(BasicDisplay.registryAccess()))));
        }

        return null;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ServerREIPlugin.EXTREME_CRAFTING;
    }

    public Optional<C> getOptionalRecipe() {
        return recipe;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return getOptionalRecipe().map(Recipe::getId);
    }

    public <T extends ScreenHandler> List<List<ItemStack>> getOrganisedInputEntries(SimpleGridMenuInfo<T, ExtremeCraftingDisplay<?>> menuInfo, T container) {
        return CollectionUtils.map(getOrganisedInputEntries(menuInfo.getCraftingWidth(container), menuInfo.getCraftingHeight(container)), ingredient ->
                CollectionUtils.<EntryStack<?>, ItemStack>filterAndMap(ingredient, stack -> stack.getType() == VanillaEntryTypes.ITEM,
                        EntryStack::castValue));
    }

    public <T extends ScreenHandler> List<EntryIngredient> getOrganisedInputEntries(int menuWidth, int menuHeight) {
        List<EntryIngredient> list = new ArrayList<>(menuWidth * menuHeight);
        for (int i = 0; i < menuWidth * menuHeight; i++) {
            list.add(EntryIngredient.empty());
        }
        for (int i = 0; i < getInputEntries().size(); i++) {
            list.set(getSlotWithSize(this, i, menuWidth), getInputEntries().get(i));
        }
        return list;
    }

    public boolean isShapeless() {
        return false;
    }

    public static int getSlotWithSize(ExtremeCraftingDisplay display, int index, int craftingGridWidth) {
        return getSlotWithSize(display.getInputWidth(craftingGridWidth, 9), index, craftingGridWidth);
    }

    public static int getSlotWithSize(int recipeWidth, int index, int craftingGridWidth) {
        int x = index % recipeWidth;
        int y = (index - x) / recipeWidth;
        return craftingGridWidth * y + x;
    }

    public static BasicDisplay.Serializer<ExtremeCraftingDisplay<?>> serializer() {
        return BasicDisplay.Serializer.<ExtremeCraftingDisplay<?>>of((input, output, location, tag) -> {
            if (tag.contains("ExtremeRecipeType")) {
                String type = tag.getString("ExtremeRecipeType");
                return switch (type) {
                    case "Shapeless" -> ExtremeCraftingCustomShapelessDisplay.simple(input, output, location);
                    case "Shaped" -> ExtremeCraftingCustomShapedDisplay.simple(input, output, tag.getInt("RecipeWidth"), tag.getInt("RecipeHeight"), location);
                    default -> throw new IllegalArgumentException("Unknown recipe type: " + type);
                };
            } else {
                return ExtremeCustomDisplay.simple(input, output, location);
            }
        }, (display, tag) -> {
            tag.putString("ExtremeRecipeType", display.isShapeless() ? "Shapeless" : "Shaped");
            if (!display.isShapeless()) {
                tag.putInt("RecipeWidth", display.getInputWidth(9, 9));
                tag.putInt("RecipeHeight", display.getInputHeight(9, 9));
            }
        });
    }

    @Override
    public List<InputIngredient<EntryStack<?>>> getInputIngredients(MenuSerializationContext<?, ?, ?> context, MenuInfo<?, ?> info, boolean fill) {
        int craftingWidth = 9, craftingHeight = 9;

        if (info instanceof SimpleGridMenuInfo && fill) {
            craftingWidth = ((SimpleGridMenuInfo<ScreenHandler, ?>) info).getCraftingWidth(context.getMenu());
            craftingHeight = ((SimpleGridMenuInfo<ScreenHandler, ?>) info).getCraftingHeight(context.getMenu());
        }

        return getInputIngredients(craftingWidth, craftingHeight);
    }

    @Override
    public List<InputIngredient<EntryStack<?>>> getInputIngredients(@Nullable ScreenHandler menu, @Nullable PlayerEntity player) {
        return getInputIngredients(9, 9);
    }

    public List<InputIngredient<EntryStack<?>>> getInputIngredients(int craftingWidth, int craftingHeight) {
        int inputWidth = getInputWidth(craftingWidth, craftingHeight);
        int inputHeight = getInputHeight(craftingWidth, craftingHeight);

        Map<IntIntPair, InputIngredient<EntryStack<?>>> grid = new HashMap<>();

        List<EntryIngredient> inputEntries = getInputEntries();
        for (int i = 0; i < inputEntries.size(); i++) {
            EntryIngredient stacks = inputEntries.get(i);
            if (stacks.isEmpty()) {
                continue;
            }
            int index = getSlotWithSize(inputWidth, i, craftingWidth);
            int x = i % inputWidth;
            int y = i / inputWidth;
            grid.put(new IntIntImmutablePair(x, y), InputIngredient.of(index, 9 * y + x, stacks));
        }

        List<InputIngredient<EntryStack<?>>> list = new ArrayList<>(craftingWidth * craftingHeight);
        for (int i = 0, n = craftingWidth * craftingHeight; i < n; i++) {
            list.add(InputIngredient.empty(i));
        }

        for (int x = 0; x < craftingWidth; x++) {
            for (int y = 0; y < craftingHeight; y++) {
                InputIngredient<EntryStack<?>> ingredient = grid.get(new IntIntImmutablePair(x, y));
                if (ingredient != null) {
                    int index = craftingWidth * y + x;
                    list.set(index, ingredient);
                }
            }
        }

        return list;
    }
}
