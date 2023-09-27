package net.laith.avaritia;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.laith.avaritia.client.model.WingModel;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.client.screen.MatterClusterScreen;
import net.laith.avaritia.client.screen.NeutronCollectorScreen;
import net.laith.avaritia.client.screen.NeutroniumCompressorScreen;
import net.laith.avaritia.init.*;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AvaritiaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, ExtremeCraftingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRON_COLLECTOR_SCREEN_HANDLER, NeutronCollectorScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, NeutroniumCompressorScreen::new);
        HandledScreens.register(ModScreenHandlers.MATTER_CLUSTER_SCREEN_HANDLER, MatterClusterScreen::new);
        EntityModelLayerRegistry.registerModelLayer(WingModel.LAYER_LOCATION, WingModel::createBodyLayer);
        ModModelsPredicateProviders.registerProviders();
        ModProjectiles.registerRenderers();
        ModEvents.Client.registerEvents();
    }
}
