package net.laith.avaritia.init;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.laith.avaritia.common.screenhandler.MatterClusterScreenHandler;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

public class ModScreenHandlers {
    public static MenuType<ExtremeCraftingTableScreenHandler> EXTREME_CRAFTING_TABLE_SCREEN_HANDLER;
    public static MenuType<NeutronCollectorScreenHandler> NEUTRON_COLLECTOR_SCREEN_HANDLER;
    public static MenuType<NeutroniumCompressorScreenHandler> NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER;
    public static MenuType<MatterClusterScreenHandler> MATTER_CLUSTER_SCREEN_HANDLER;

    public static void registerAllScreenHandlers() {
        EXTREME_CRAFTING_TABLE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple
                (new ResourceLocation(AvaritiaMod.MOD_ID, "extreme_crafting_table_screen_handler"), (syncId, inventory) -> new ExtremeCraftingTableScreenHandler(syncId, inventory));

        NEUTRON_COLLECTOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple
                (new ResourceLocation(AvaritiaMod.MOD_ID, "neutron_collector_screen_handler"), (syncId, inventory) -> new NeutronCollectorScreenHandler(syncId, inventory));

        NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple
                (new ResourceLocation(AvaritiaMod.MOD_ID, "neutronium_compressor_screen_handler"), (syncId, inventory) -> new NeutroniumCompressorScreenHandler(syncId, inventory));


        MATTER_CLUSTER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple
                (new ResourceLocation(AvaritiaMod.MOD_ID, "matter_cluster_screen_handler"), (syncId, inventory) -> new MatterClusterScreenHandler(syncId, inventory));
    }
}
