package net.laith.avaritia.compat.botania.blockentityrenderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaPoolBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.PoolOverlayProvider;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.helper.VecHelper;

import java.util.Objects;

public class InfinityManaPoolBlockEntityRenderer implements BlockEntityRenderer<InfinityManaPoolBlockEntity> {

    // Overrides for when we call this renderer from a cart
    private final TextureAtlasSprite waterSprite;
    private final BlockRenderDispatcher blockRenderDispatcher;

    public InfinityManaPoolBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.blockRenderDispatcher = ctx.getBlockRenderDispatcher();
        this.waterSprite = Objects.requireNonNull(
                Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                        .apply(new ResourceLocation(AvaritiaMod.MOD_ID, "textures/block/mana_water"))
        );
    }

    @Override
    public void render(@Nullable InfinityManaPoolBlockEntity pool, float f, PoseStack ms, @NotNull MultiBufferSource buffers, int light, int overlay) {
        ms.pushPose();

        int insideUVStart = 2;
        int insideUVEnd = 16 - insideUVStart;
        float poolBottom = insideUVStart / 16F + 0.001F;
        float poolTop = (7) / 16F;

        if (pool != null) {
            Block below = Objects.requireNonNull(pool.getLevel()).getBlockState(pool.getBlockPos().below()).getBlock();
            if (below instanceof PoolOverlayProvider overlayProvider) {
                var overlaySpriteId = overlayProvider.getIcon(pool.getLevel(), pool.getBlockPos());
                var overlayIcon = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(overlaySpriteId);
                ms.pushPose();

                float alpha = (float) ((Math.sin((ClientTickHandler.ticksInGame + f) / 20.0) + 1) * 0.3 + 0.2);

                ms.translate(0, poolBottom, 0);
                ms.mulPose(VecHelper.rotateX(90F));

                VertexConsumer buffer = buffers.getBuffer(RenderHelper.ICON_OVERLAY);
                RenderHelper.renderIconCropped(
                        ms, buffer,
                        insideUVStart, insideUVStart, insideUVEnd, insideUVEnd,
                        overlayIcon, 0xFFFFFF, alpha, light
                );

                ms.popPose();
            }
        }

        int mana = pool.getCurrentMana();
        int maxMana = pool == null ? -1 : pool.getMaxMana();
        if (maxMana == -1) {
            maxMana = InfinityManaPoolBlockEntity.MAX_MANA;
        }

        float manaLevel = (float) mana / (float) maxMana;
        if (manaLevel > 0) {
            ms.pushPose();
            ms.translate(0, Mth.clampedMap(manaLevel, 0, 1, poolBottom, poolTop), 0);
            ms.mulPose(VecHelper.rotateX(90F));

            VertexConsumer buffer = buffers.getBuffer(RenderHelper.MANA_POOL_WATER);
            RenderHelper.renderIconCropped(
                    ms, buffer,
                    insideUVStart, insideUVStart, insideUVEnd, insideUVEnd,
                    this.waterSprite, 0xFFFFFF, 1, light);

            ms.popPose();
        }
        ms.popPose();
    }
}

