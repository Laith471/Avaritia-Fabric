package net.laith.avaritia.compat.rei;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REIServerPlugin;
import me.shedaniel.rei.api.common.transfer.info.MenuInfoRegistry;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleMenuInfoProvider;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.laith.avaritia.compat.rei.display.extreme.ExtremeCraftingDisplay;
import net.laith.avaritia.compat.rei.display.NeutroniumCompressorDisplay;
import net.laith.avaritia.compat.rei.menu.ExtremeMenuInfo;
import net.laith.avaritia.compat.rei.menu.NeutroniumCompressorMenu;

public class ServerREIPlugin implements REIServerPlugin {

    @Override
    public void registerMenuInfo(MenuInfoRegistry registry) {
        registry.register(EXTREME_CRAFTING, ExtremeCraftingTableScreenHandler.class, SimpleMenuInfoProvider.of(ExtremeMenuInfo::new));
        registry.register(NEUTRONIUM_COMPRESSOR, NeutroniumCompressorScreenHandler.class, SimpleMenuInfoProvider.of(NeutroniumCompressorMenu::new));

    }

    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(EXTREME_CRAFTING, ExtremeCraftingDisplay.serializer());
        registry.register(NEUTRONIUM_COMPRESSOR, NeutroniumCompressorDisplay.Serializer.ofRecipeLess(NeutroniumCompressorDisplay::new));
    }
    public static final CategoryIdentifier<ExtremeCraftingDisplay<?>> EXTREME_CRAFTING = CategoryIdentifier.of(AvaritiaMod.MOD_ID, "extreme_display");
    public static final CategoryIdentifier<NeutroniumCompressorDisplay> NEUTRONIUM_COMPRESSOR = CategoryIdentifier.of(AvaritiaMod.MOD_ID, "neutronium_compressor");

}
