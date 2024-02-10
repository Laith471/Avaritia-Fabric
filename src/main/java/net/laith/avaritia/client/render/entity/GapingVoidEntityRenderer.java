package net.laith.avaritia.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.client.model.GapingVoidModel;
import net.laith.avaritia.common.entity.GapingVoidEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

@Environment(EnvType.CLIENT)
public class GapingVoidEntityRenderer extends EntityRenderer<GapingVoidEntity> {

    private final ResourceLocation fill = new ResourceLocation("avaritia:textures/entity/voidtemp.png");

    private final EntityModel<Entity> gapingVoid;

    public GapingVoidEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        gapingVoid = new GapingVoidModel<>(context.bakeLayer(GapingVoidModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(GapingVoidEntity entity) {
        return fill;
    }

    @Override
    public void render(GapingVoidEntity entity, float yaw, float tickDelta, PoseStack matrices, MultiBufferSource vertexConsumers, int light) {
        matrices.pushPose();
        VertexConsumer builder = vertexConsumers.getBuffer(this.gapingVoid.renderType(this.getTextureLocation(entity)));
        float scale = (float) GapingVoidEntity.getVoidScale(entity.getAge() + tickDelta);
        matrices.scale(scale, scale, scale);
        matrices.translate(0, -scale * 0.1d, 0);
        this.gapingVoid.renderToBuffer(matrices, builder, light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);

      /*  float scale = (float) GapingVoidEntity.getVoidScale(entity.getAge() + tickDelta) * 1.25F;
        float cache = RenderSystem.getShaderFogStart();

        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(GL11.GL_ALWAYS);
        RenderSystem.setShaderFogStart(Float.MAX_VALUE);

        Matrix4f matrix4f = new Matrix4f().scale(scale, scale, scale);
        Vec3d pos = entity.getPos();
        matrices.translate(0.5, -scale * 0.1d + 2.25, 0.75);
        objFile.draw(matrices, matrix4f, pos);

        RenderSystem.setShaderFogStart(cache);
        RenderSystem.clearDepth(GL11.GL_ALWAYS);
        RenderSystem.disableDepthTest();

       */
        matrices.popPose();
    }
}