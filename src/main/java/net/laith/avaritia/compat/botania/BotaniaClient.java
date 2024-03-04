package net.laith.avaritia.compat.botania;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaPoolBlockEntity;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaSpreaderBlockEntity;
import net.laith.avaritia.compat.botania.blockentityrenderer.InfinityManaPoolBlockEntityRenderer;
import net.laith.avaritia.compat.botania.blockentityrenderer.InfinityManaSpreaderBlockEntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import vazkii.botania.api.BotaniaFabricClientCapabilities;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;

public class BotaniaClient {

    public static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(Botania.ASGARDANDELION_BLOCK_ENTITY, SpecialFlowerBlockEntityRenderer::new);
        BlockEntityRenderers.register(Botania.INFINITY_MANA_SPREADER_BLOCK_ENTITY, InfinityManaSpreaderBlockEntityRenderer::new);
        BlockEntityRenderers.register(Botania.INFINITY_MANA_POOL_BLOCK_ENTITY, InfinityManaPoolBlockEntityRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(Botania.ASGARDANDELION, RenderType.cutoutMipped());
    }

    public static void registerCapabilities() {
        BotaniaFabricClientCapabilities.WAND_HUD.registerForBlockEntities((blockEntity, context) ->
                new InfinityManaSpreaderBlockEntity.WandHud((InfinityManaSpreaderBlockEntity) blockEntity), Botania.INFINITY_MANA_SPREADER_BLOCK_ENTITY);

        BotaniaFabricClientCapabilities.WAND_HUD.registerForBlockEntities((blockEntity, context) ->
                new InfinityManaPoolBlockEntity.WandHud((InfinityManaPoolBlockEntity) blockEntity), Botania.INFINITY_MANA_POOL_BLOCK_ENTITY);

    }
    public static void init() {
        registerBlockEntityRenderers();
        registerCapabilities();
    }
}
