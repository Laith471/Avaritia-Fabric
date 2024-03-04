package net.laith.avaritia.mixin;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.laith.avaritia.util.render.AnimationTimer;
import net.laith.avaritia.util.render.IHaloRenderItem;
import net.laith.avaritia.util.render.Timer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Random;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {


    @Unique
    private Random random = new Random();

    @Unique
    private Timer timer = new Timer(new float[]{0, 0.125f, 0.25f, 0.375f, 0.5f, 0.625f, 0.75f, 0.875f}, 100);

    @Shadow public abstract void render(ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model);

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void onRender(ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model, CallbackInfo ci, boolean bl, boolean bl2, RenderType renderType, VertexConsumer vertexConsumer) {
        if (itemStack.getItem() instanceof IHaloRenderItem hri && displayContext == ItemDisplayContext.GUI) {
            int haloColour = hri.getHaloColour(itemStack);

            float spread = (float)hri.getHaloSize(itemStack)/ 16;
            float min = 0 - spread;
            float max = 1 + spread;

            RenderSystem.enableBlend();
            RenderSystem.disableDepthTest();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

            if (hri.shouldDrawHalo(itemStack)) {
                poseStack.pushPose();
                float ca = (float) (haloColour >> 24 & 255) / 255.0F;
                float cr = (float) (haloColour >> 16 & 255) / 255.0F;
                float cg = (float) (haloColour >> 8 & 255) / 255.0F;
                float cb = (float) (haloColour & 255) / 255.0F;
                Matrix4f positionMatrix = poseStack.last().pose();
                positionMatrix.translate(0, 0, 0);
                Tesselator tessellator = Tesselator.getInstance();
                BufferBuilder buffer1 = tessellator.getBuilder();

                buffer1.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
                if(hri.isItNeutron(itemStack)) {
                    buffer1.vertex(positionMatrix, max, max, 0).uv(0f, 0f + ((AnimationTimer)minecraft).timer()).endVertex();  // Top-left corner
                    buffer1.vertex(positionMatrix, min, max, 0).uv(1f, 0f + ((AnimationTimer)minecraft).timer()).endVertex(); // Top-right corner
                    buffer1.vertex(positionMatrix, min, min, 0).uv(1f, 0.125f + ((AnimationTimer)minecraft).timer()).endVertex(); // Bottom-right corner
                    buffer1.vertex(positionMatrix, max, min, 0).uv(0f, 0.125f + ((AnimationTimer)minecraft).timer()).endVertex(); // Bottom-left corner
                }
                else {
                    buffer1.vertex(positionMatrix, max, max, 0).uv(0f, 0f).endVertex();
                    buffer1.vertex(positionMatrix, min, max, 0).uv(0f, 1f).endVertex();
                    buffer1.vertex(positionMatrix, min, min, 0).uv(1f, 1f).endVertex();
                    buffer1.vertex(positionMatrix, max, min, 0).uv(1f, 0f).endVertex();
                }

                RenderSystem.setShaderColor(cr, cg, cb, ca);
                RenderSystem.setShader(GameRenderer::getPositionTexShader);
                RenderSystem.setShaderTexture(0, hri.getHaloTexture(itemStack));

                tessellator.end();
                poseStack.popPose();
                if (hri.shouldDrawPulse(itemStack)) {
                    double scale = random.nextDouble() * 0.15 + 0.95;
                    double trans = (1.0 - scale) / 2.0;
                    poseStack.translate(trans, trans, 0.0);
                    poseStack.scale((float) scale, (float) scale, 1.0001F);

                }

            }

            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
        }
    }
}