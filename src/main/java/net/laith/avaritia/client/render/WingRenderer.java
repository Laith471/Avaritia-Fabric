package net.laith.avaritia.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.client.model.WingModel;
import net.laith.avaritia.init.ModItems;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.joml.Matrix3f;

@Environment(value=EnvType.CLIENT)
public class WingRenderer<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier BASE = new Identifier(AvaritiaMod.MOD_ID, "textures/models/armor/infinity_armor_wings.png");

    public WingRenderer(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,LivingEntity livingEntity, float f, float g, float h, float j, float k, float l) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        ItemStack itemStack = player.getEquippedStack(EquipmentSlot.CHEST);
        if (!itemStack.isOf(ModItems.INFINITY_CHESTPLATE)) {
            return;
        }
        var model = new WingModel(WingModel.createBodyLayer().createModel());
        if (!player.isInvisible() && player.getAbilities().flying) {
            VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucentCull(BASE));
            matrixStack.push();
            matrixStack.translate(0, -2.8, 0.325);
            matrixStack.scale(3F, 3F, 3F);
            model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
        }
    }
}

