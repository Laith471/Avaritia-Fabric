package net.laith.avaritia.init;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static ScreenHandlerType<ExtremeCraftingTableScreenHandler> EXTREME_CRAFTING_TABLE_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        EXTREME_CRAFTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple
                (new Identifier(AvaritiaMod.MOD_ID, "extreme_crafting_table_screen_handler"), (syncId, inventory) -> new ExtremeCraftingTableScreenHandler(syncId, inventory));
    }
}
