package net.laith.avaritia.client.render.entity;

import com.mojang.blaze3d.systems.RenderSystem;
import me.x150.renderer.objfile.ObjFile;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.client.model.GapingVoidModel;
import net.laith.avaritia.common.entity.EntityGapingVoid;
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
public class EntityGapingVoidRenderer extends EntityRenderer<EntityGapingVoid> {

    private final Identifier fill = new Identifier("avaritia:textures/entity/voidhalo.png");
    ObjFile objFile;

    {
        try {
            objFile = new ObjFile("hemisphere.obj", ObjFile.ResourceProvider.ofPath(Path.of("/Dev/Minecraft/Fabric/1.20.1/Avaritia/src/main/resources/assets/avaritia/modelobj/")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private final EntityModel<Entity> gapingVoid;

    public EntityGapingVoidRenderer(EntityRendererFactory.Context context) {
        super(context);
        gapingVoid = new GapingVoidModel<>(context.getPart(GapingVoidModel.LAYER_LOCATION));
    }

    @Override
    public Identifier getTexture(EntityGapingVoid entity) {
        return fill;
    }

    @Override
    public void render(EntityGapingVoid entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        /*VertexConsumer builder = vertexConsumers.getBuffer(this.gapingVoid.getLayer(this.getTexture(entity)));
        float scale = (float) EntityGapingVoid.getVoidScale(entity.getAge() + tickDelta);
        matrices.scale(scale, scale, scale);
        matrices.translate(0, -scale * 0.1d, 0);
        this.gapingVoid.render(matrices, builder, light, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
         */
        RenderSystem.enableDepthTest();
        RenderSystem.depthFunc(GL11.GL_ALWAYS);
        float cache = RenderSystem.getShaderFogStart();
        RenderSystem.setShaderFogStart(Float.MAX_VALUE);
        matrices.push();
        float scale = (float) EntityGapingVoid.getVoidScale(entity.getAge() + tickDelta) * 1.25F;
        Matrix4f matrix4f = new Matrix4f().scale(scale, scale, scale);
        Vec3d pos = entity.getPos();
        matrices.translate(0.5, -scale * 0.1d + 2.25, 0.75);
        objFile.draw(matrices, matrix4f, pos);;
        matrices.pop();
        RenderSystem.setShaderFogStart(cache);
        RenderSystem.clearDepth(GL11.GL_ALWAYS);
    }
}