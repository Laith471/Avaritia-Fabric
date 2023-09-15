package net.laith.avaritia.client.render;

import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.client.model.WingModel;
import net.laith.avaritia.common.handler.ArmorHandler;
import net.laith.avaritia.init.ModItems;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class EyeRenderer<T extends PlayerEntity> extends EyesFeatureRenderer<T, EntityModel<T>> {
    private static final RenderLayer SKIN = RenderLayer.getEyes(new Identifier(AvaritiaMod.MOD_ID, "textures/models/armor/infinity_armor_eyes.png"));

    public EyeRenderer(FeatureRendererContext<T, EntityModel<T>> featureRendererContext) {super(featureRendererContext);}

    @Override
    public RenderLayer getEyesTexture() {
        return SKIN;
    }
}