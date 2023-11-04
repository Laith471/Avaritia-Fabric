package net.laith.avaritia.client.render.entity;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.objfile.ObjFile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.client.model.GapingVoidModel;
import net.laith.avaritia.common.entity.GapingVoidEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.nio.file.Path;

@Environment(EnvType.CLIENT)
public class GapingVoidEntityRenderer extends EntityRenderer<GapingVoidEntity> {

    private final Identifier fill = new Identifier("avaritia:textures/entity/voidtemp.png");

    private final EntityModel<Entity> gapingVoid;

    public GapingVoidEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        gapingVoid = new GapingVoidModel<>(context.getPart(GapingVoidModel.LAYER_LOCATION));
    }

    @Override
    public Identifier getTexture(GapingVoidEntity entity) {
        return fill;
    }

    @Override
    public void render(GapingVoidEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        VertexConsumer builder = vertexConsumers.getBuffer(this.gapingVoid.getLayer(this.getTexture(entity)));
        float scale = (float) GapingVoidEntity.getVoidScale(entity.getAge() + tickDelta);
        matrices.scale(scale, scale, scale);
        matrices.translate(0, -scale * 0.1d, 0);
        this.gapingVoid.render(matrices, builder, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);

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
        matrices.pop();
    }
}