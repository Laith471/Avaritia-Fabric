package net.laith.avaritia.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoRegistry;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleMenuInfoProvider;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.laith.avaritia.compat.rei.display.ExtremeCraftingDisplay;
import net.laith.avaritia.compat.rei.menu.ExtremeMenuInfo;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.Recipe;

public class ServerREIPlugin implements REIServerPlugin {

    @Override
    public void registerMenuInfo(MenuInfoRegistry registry) {
        registry.register(EXTREME_CRAFTING, ExtremeCraftingTableScreenHandler.class, SimpleMenuInfoProvider.of(ExtremeMenuInfo::new));

    }

    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(EXTREME_CRAFTING, ExtremeCraftingDisplay.serializer());
    }
    public static final CategoryIdentifier<ExtremeCraftingDisplay<?>> EXTREME_CRAFTING = CategoryIdentifier.of(AvaritiaMod.MOD_ID, "extreme_display");

}
