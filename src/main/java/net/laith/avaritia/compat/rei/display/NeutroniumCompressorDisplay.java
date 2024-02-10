package net.laith.avaritia.compat.rei.display;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.registry.RecipeManagerContext;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.laith.avaritia.compat.rei.ServerREIPlugin;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NeutroniumCompressorDisplay extends BasicDisplay {

    private int cost;

    public NeutroniumCompressorDisplay(NeutroniumCompressorRecipe recipe) {
        this(Collections.singletonList(EntryIngredients.ofIngredient(recipe.getInput())), Collections.singletonList(EntryIngredients.of(recipe.getResultItem(null))), recipe, recipe.getCost());

    }

    public NeutroniumCompressorDisplay(List<EntryIngredient> input, List<EntryIngredient> output, CompoundTag tag) {
        this(input, output, (NeutroniumCompressorRecipe) RecipeManagerContext.getInstance().byId(tag, "location"),
                tag.getInt("cost"));
    }

    public NeutroniumCompressorDisplay(List<EntryIngredient> input, List<EntryIngredient> output, NeutroniumCompressorRecipe recipe, int cost) {
        super(input, output, Optional.ofNullable(recipe).map(Recipe::getId));
        this.cost = cost;
    }

     Serializer<NeutroniumCompressorDisplay> serializer() {
         return Serializer.ofRecipeLess(NeutroniumCompressorDisplay::new, (display, tag) -> {
             tag.putInt("cost", display.getCost());
         });
    }


    @Override
    public List<EntryIngredient> getInputEntries() {
        return this.inputs;
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return this.outputs;
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return ServerREIPlugin.NEUTRONIUM_COMPRESSOR;
    }
    public int getCost() {
        return cost;
    }

}
