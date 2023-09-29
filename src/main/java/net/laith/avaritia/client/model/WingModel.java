package net.laith.avaritia.client.model;

import com.google.common.collect.ImmutableList;
import net.laith.avaritia.AvaritiaMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class WingModel extends AnimalModel<PlayerEntity> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(AvaritiaMod.MOD_ID, "wing_model"), "main");
    private final ModelPart bone;

    public WingModel(ModelPart rootPart) {
        this.bone = rootPart.getChild("bone");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData ModelData = new ModelData();
        ModelPartData ModelPartData = ModelData.getRoot();

        ModelPartData bone = ModelPartData.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        bone.addChild("right_r1", ModelPartBuilder.create().uv(0, 8).cuboid(-8.0F,  -13.0F, 0.0F, 8.0F, 8.0F, 0.0F,
                new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 2.9671F, 0.0F));

        bone.addChild("left_r1", ModelPartBuilder.create().uv(0, 8).mirrored().cuboid(0.0F, -13.0F, 0.0F, 8.0F, 8.0F, 0.0F,
                new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -2.9671F, 0.0F));

        return TexturedModelData.of(ModelData, 16, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        bone.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }


    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.bone);
    }

    @Override
    public void setAngles(PlayerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
    }
}
