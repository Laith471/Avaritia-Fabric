package net.laith.avaritia;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.laith.avaritia.client.model.WingModel;
import net.laith.avaritia.client.render.EyeRenderer;
import net.laith.avaritia.client.render.WingRenderer;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.client.screen.MatterClusterScreen;
import net.laith.avaritia.client.screen.NeutronCollectorScreen;
import net.laith.avaritia.client.screen.NeutroniumCompressorScreen;
import net.laith.avaritia.init.ModModelsPredicateProviders;
import net.laith.avaritia.init.ModProjectiles;
import net.laith.avaritia.init.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.PlayerEntityRenderer;

public class AvaritiaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, ExtremeCraftingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRON_COLLECTOR_SCREEN_HANDLER, NeutronCollectorScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, NeutroniumCompressorScreen::new);
        HandledScreens.register(ModScreenHandlers.MATTER_CLUSTER_SCREEN_HANDLER, MatterClusterScreen::new);
        ModProjectiles.registerRenderers();
        ModModelsPredicateProviders.registerProviders();
        EntityModelLayerRegistry.registerModelLayer(WingModel.LAYER_LOCATION, WingModel::createBodyLayer);
        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                registrationHelper.register(new WingRenderer<>(playerEntityRenderer));
                registrationHelper.register(new EyeRenderer(playerEntityRenderer));
            }
        });
    }
}
