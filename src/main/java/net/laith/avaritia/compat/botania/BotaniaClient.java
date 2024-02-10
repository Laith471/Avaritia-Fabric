package net.laith.avaritia.compat.botania;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

public class BotaniaClient {

    public static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(Botania.ASGARDANDELION_BLOCK_ENTITY, SpecialFlowerBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(Botania.ASGARDANDELION, RenderType.cutoutMipped());
    }

    public static void init() {
        registerBlockEntityRenderers();
    }
}
