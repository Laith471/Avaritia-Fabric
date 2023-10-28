package net.laith.avaritia.client.model;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class GapingVoidModel<T extends Entity> extends EntityModel<T> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier(AvaritiaMod.MOD_ID, "gaping_void"), "main");
    private final ModelPart gaping_void;

    public GapingVoidModel(ModelPart root) {
        this.gaping_void = root.getChild("gaping_void");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData ModelPartData = meshdefinition.getRoot();

        ModelPartData gaping_void = ModelPartData.addChild("gaping_void", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 0.0F));

        ModelPartData bone = gaping_void.addChild("bone", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData bone2 = bone.addChild("bone2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r1 = bone2.addChild("cube1_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r2 = bone2.addChild("cube1_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r3 = bone2.addChild("cube1_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r4 = bone2.addChild("cube1_r4", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone3 = bone.addChild("bone3", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r5 = bone3.addChild("cube1_r5", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r6 = bone3.addChild("cube1_r6", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r7 = bone3.addChild("cube1_r7", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r8 = bone3.addChild("cube1_r8", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone5 = bone.addChild("bone5", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r9 = bone5.addChild("cube1_r9", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r10 = bone5.addChild("cube1_r10", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r11 = bone5.addChild("cube1_r11", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r12 = bone5.addChild("cube1_r12", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone7 = bone.addChild("bone7", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r13 = bone7.addChild("cube1_r13", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r14 = bone7.addChild("cube1_r14", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r15 = bone7.addChild("cube1_r15", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r16 = bone7.addChild("cube1_r16", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone4 = gaping_void.addChild("bone4", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData bone6 = bone4.addChild("bone6", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r17 = bone6.addChild("cube1_r17", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r18 = bone6.addChild("cube1_r18", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r19 = bone6.addChild("cube1_r19", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r20 = bone6.addChild("cube1_r20", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone8 = bone4.addChild("bone8", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r21 = bone8.addChild("cube1_r21", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r22 = bone8.addChild("cube1_r22", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r23 = bone8.addChild("cube1_r23", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r24 = bone8.addChild("cube1_r24", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone9 = bone4.addChild("bone9", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r25 = bone9.addChild("cube1_r25", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r26 = bone9.addChild("cube1_r26", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r27 = bone9.addChild("cube1_r27", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r28 = bone9.addChild("cube1_r28", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone10 = bone4.addChild("bone10", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r29 = bone10.addChild("cube1_r29", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r30 = bone10.addChild("cube1_r30", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r31 = bone10.addChild("cube1_r31", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r32 = bone10.addChild("cube1_r32", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone21 = gaping_void.addChild("bone21", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData bone22 = bone21.addChild("bone22", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r33 = bone22.addChild("cube1_r33", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r34 = bone22.addChild("cube1_r34", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r35 = bone22.addChild("cube1_r35", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r36 = bone22.addChild("cube1_r36", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone23 = bone21.addChild("bone23", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r37 = bone23.addChild("cube1_r37", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r38 = bone23.addChild("cube1_r38", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r39 = bone23.addChild("cube1_r39", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r40 = bone23.addChild("cube1_r40", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone24 = bone21.addChild("bone24", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r41 = bone24.addChild("cube1_r41", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r42 = bone24.addChild("cube1_r42", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r43 = bone24.addChild("cube1_r43", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r44 = bone24.addChild("cube1_r44", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone25 = bone21.addChild("bone25", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r45 = bone25.addChild("cube1_r45", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r46 = bone25.addChild("cube1_r46", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r47 = bone25.addChild("cube1_r47", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r48 = bone25.addChild("cube1_r48", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone26 = gaping_void.addChild("bone26", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData bone27 = bone26.addChild("bone27", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r49 = bone27.addChild("cube1_r49", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r50 = bone27.addChild("cube1_r50", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r51 = bone27.addChild("cube1_r51", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r52 = bone27.addChild("cube1_r52", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone28 = bone26.addChild("bone28", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r53 = bone28.addChild("cube1_r53", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r54 = bone28.addChild("cube1_r54", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r55 = bone28.addChild("cube1_r55", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r56 = bone28.addChild("cube1_r56", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone29 = bone26.addChild("bone29", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r57 = bone29.addChild("cube1_r57", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r58 = bone29.addChild("cube1_r58", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r59 = bone29.addChild("cube1_r59", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r60 = bone29.addChild("cube1_r60", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone30 = bone26.addChild("bone30", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r61 = bone30.addChild("cube1_r61", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r62 = bone30.addChild("cube1_r62", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r63 = bone30.addChild("cube1_r63", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r64 = bone30.addChild("cube1_r64", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone31 = gaping_void.addChild("bone31", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone32 = bone31.addChild("bone32", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r65 = bone32.addChild("cube1_r65", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r66 = bone32.addChild("cube1_r66", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r67 = bone32.addChild("cube1_r67", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r68 = bone32.addChild("cube1_r68", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone33 = bone31.addChild("bone33", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r69 = bone33.addChild("cube1_r69", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r70 = bone33.addChild("cube1_r70", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r71 = bone33.addChild("cube1_r71", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r72 = bone33.addChild("cube1_r72", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone34 = bone31.addChild("bone34", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r73 = bone34.addChild("cube1_r73", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r74 = bone34.addChild("cube1_r74", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r75 = bone34.addChild("cube1_r75", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r76 = bone34.addChild("cube1_r76", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone35 = bone31.addChild("bone35", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r77 = bone35.addChild("cube1_r77", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r78 = bone35.addChild("cube1_r78", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r79 = bone35.addChild("cube1_r79", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r80 = bone35.addChild("cube1_r80", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone36 = gaping_void.addChild("bone36", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

        ModelPartData bone37 = bone36.addChild("bone37", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r81 = bone37.addChild("cube1_r81", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r82 = bone37.addChild("cube1_r82", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r83 = bone37.addChild("cube1_r83", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r84 = bone37.addChild("cube1_r84", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone38 = bone36.addChild("bone38", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r85 = bone38.addChild("cube1_r85", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r86 = bone38.addChild("cube1_r86", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r87 = bone38.addChild("cube1_r87", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r88 = bone38.addChild("cube1_r88", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone39 = bone36.addChild("bone39", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r89 = bone39.addChild("cube1_r89", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r90 = bone39.addChild("cube1_r90", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r91 = bone39.addChild("cube1_r91", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r92 = bone39.addChild("cube1_r92", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone40 = bone36.addChild("bone40", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r93 = bone40.addChild("cube1_r93", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r94 = bone40.addChild("cube1_r94", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r95 = bone40.addChild("cube1_r95", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r96 = bone40.addChild("cube1_r96", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone11 = gaping_void.addChild("bone11", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

        ModelPartData bone12 = bone11.addChild("bone12", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r97 = bone12.addChild("cube1_r97", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r98 = bone12.addChild("cube1_r98", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r99 = bone12.addChild("cube1_r99", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r100 = bone12.addChild("cube1_r100", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone13 = bone11.addChild("bone13", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r101 = bone13.addChild("cube1_r101", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r102 = bone13.addChild("cube1_r102", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r103 = bone13.addChild("cube1_r103", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r104 = bone13.addChild("cube1_r104", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone14 = bone11.addChild("bone14", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r105 = bone14.addChild("cube1_r105", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r106 = bone14.addChild("cube1_r106", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r107 = bone14.addChild("cube1_r107", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r108 = bone14.addChild("cube1_r108", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone15 = bone11.addChild("bone15", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r109 = bone15.addChild("cube1_r109", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r110 = bone15.addChild("cube1_r110", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r111 = bone15.addChild("cube1_r111", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r112 = bone15.addChild("cube1_r112", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone16 = gaping_void.addChild("bone16", ModelPartBuilder.create(), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.1781F, 0.0F));

        ModelPartData bone17 = bone16.addChild("bone17", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -2.0F, 7.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r113 = bone17.addChild("cube1_r113", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r114 = bone17.addChild("cube1_r114", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r115 = bone17.addChild("cube1_r115", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r116 = bone17.addChild("cube1_r116", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone18 = bone16.addChild("bone18", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        ModelPartData cube1_r117 = bone18.addChild("cube1_r117", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r118 = bone18.addChild("cube1_r118", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r119 = bone18.addChild("cube1_r119", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r120 = bone18.addChild("cube1_r120", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone19 = bone16.addChild("bone19", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

        ModelPartData cube1_r121 = bone19.addChild("cube1_r121", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r122 = bone19.addChild("cube1_r122", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r123 = bone19.addChild("cube1_r123", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r124 = bone19.addChild("cube1_r124", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData bone20 = bone16.addChild("bone20", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, -2.0F, -1.5708F, 0.0F, -1.5708F));

        ModelPartData cube1_r125 = bone20.addChild("cube1_r125", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

        ModelPartData cube1_r126 = bone20.addChild("cube1_r126", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

        ModelPartData cube1_r127 = bone20.addChild("cube1_r127", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData cube1_r128 = bone20.addChild("cube1_r128", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 8.0F, 4.0F, 4.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        return TexturedModelData.of(meshdefinition, 16, 16);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        gaping_void.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}