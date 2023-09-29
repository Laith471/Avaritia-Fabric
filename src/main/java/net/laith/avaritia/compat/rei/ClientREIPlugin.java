package net.laith.avaritia.compat.rei;

import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.common.recipe.NeutroniumCompressorRecipe;
import net.laith.avaritia.compat.rei.category.ExtremeCategory;
import net.laith.avaritia.compat.rei.category.IButtonArea;
import net.laith.avaritia.compat.rei.category.NeutroniumButtonArea;
import net.laith.avaritia.compat.rei.category.NeutroniumCompressorCategory;
import net.laith.avaritia.compat.rei.display.extreme.ExtremeCraftingDisplay;
import net.laith.avaritia.compat.rei.display.NeutroniumCompressorDisplay;
import net.laith.avaritia.init.ModBlocks;


public class ClientREIPlugin implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new ExtremeCategory());
        registry.add(new NeutroniumCompressorCategory());
        registry.setPlusButtonArea(ServerREIPlugin.NEUTRONIUM_COMPRESSOR, NeutroniumButtonArea.defaultArea());
        registry.setPlusButtonArea(ServerREIPlugin.EXTREME_CRAFTING, IButtonArea.defaultArea());
        registry.addWorkstations(ServerREIPlugin.EXTREME_CRAFTING, EntryStacks.of(ModBlocks.EXTREME_CRAFTING_TABLE));
        registry.addWorkstations(ServerREIPlugin.NEUTRONIUM_COMPRESSOR, EntryStacks.of(ModBlocks.NEUTRONIUM_COMPRESSOR));
    }

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerFiller(ExtremeRecipe.class, ExtremeCraftingDisplay::of);
        registry.registerFiller(NeutroniumCompressorRecipe.class, NeutroniumCompressorDisplay::new);
    }
}
