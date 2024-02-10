package net.laith.avaritia;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;;
import net.laith.avaritia.client.model.GapingVoidModel;
import net.laith.avaritia.client.model.WingModel;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.client.screen.MatterClusterScreen;
import net.laith.avaritia.client.screen.NeutronCollectorScreen;
import net.laith.avaritia.client.screen.NeutroniumCompressorScreen;
import net.laith.avaritia.common.item.ItemSingularity;
import net.laith.avaritia.compat.ModCompatClient;
import net.laith.avaritia.init.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.GameRenderer;

public class AvaritiaModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModEvents.Client.register();
        MenuScreens.register(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, ExtremeCraftingTableScreen::new);
        MenuScreens.register(ModScreenHandlers.NEUTRON_COLLECTOR_SCREEN_HANDLER, NeutronCollectorScreen::new);
        MenuScreens.register(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, NeutroniumCompressorScreen::new);
        MenuScreens.register(ModScreenHandlers.MATTER_CLUSTER_SCREEN_HANDLER, MatterClusterScreen::new);
        EntityModelLayerRegistry.registerModelLayer(WingModel.LAYER_LOCATION, WingModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(GapingVoidModel.LAYER_LOCATION, GapingVoidModel::createBodyLayer);
        ModModelsPredicateProviders.registerProviders();
        ModEntityRenderers.registerRenderers();
        ModCompatClient.compatifyClient();
        for(ItemSingularity itemSingularity : ItemSingularity.getAll) {
            ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((ItemSingularity)stack.getItem()).getColor(tintIndex), itemSingularity);
        }
    }
}
