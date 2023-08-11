package net.laith.avaritia.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.ButtonArea;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapelessRecipe;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.laith.avaritia.compat.rei.category.ExtremeCategory;
import net.laith.avaritia.compat.rei.category.IButtonArea;
import net.laith.avaritia.compat.rei.category.NeutroniumCompressorCategory;
import net.laith.avaritia.compat.rei.display.ExtremeCraftingShapedDisplay;
import net.laith.avaritia.compat.rei.display.ExtremeCraftingShapelessDisplay;
import net.laith.avaritia.compat.rei.display.NeutroniumCompressorDisplay;
import net.laith.avaritia.init.ModBlocks;


public class ClientREIPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ExtremeCategory());
        registry.add(new NeutroniumCompressorCategory());
        registry.setPlusButtonArea(ServerREIPlugin.EXTREME_CRAFTING, IButtonArea.defaultArea());
        registry.addWorkstations(ServerREIPlugin.EXTREME_CRAFTING, EntryStacks.of(ModBlocks.EXTREME_CRAFTING_TABLE));
        registry.addWorkstations(ServerREIPlugin.NEUTRONIUM_COMPRESSOR, EntryStacks.of(ModBlocks.NEUTRONIUM_COMPRESSOR));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(ExtremeCraftingShapelessRecipe.class, ExtremeCraftingShapelessDisplay::new);
        registry.registerFiller(ExtremeCraftingShapedRecipe.class, ExtremeCraftingShapedDisplay::new);
        registry.registerFiller(NeutroniumCompressorRecipe.class, NeutroniumCompressorDisplay::new);
    }
}
