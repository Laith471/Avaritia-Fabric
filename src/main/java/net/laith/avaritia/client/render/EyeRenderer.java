package net.laith.avaritia.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class EyeRenderer<T extends Player> extends EyesLayer<T, EntityModel<T>> {
    private static final RenderType SKIN = RenderType.eyes(new ResourceLocation(AvaritiaMod.MOD_ID, "textures/models/armor/infinity_armor_eyes.png"));

    public EyeRenderer(RenderLayerParent<T, EntityModel<T>> featureRendererContext) {super(featureRendererContext);}

    @Override
    public RenderType renderType() {
        return SKIN;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T livingEntity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        if (BooleanHelper.isWearingHelmet(livingEntity)) {
            super.render(poseStack, buffer, packedLight, livingEntity, limbSwing, limbSwingAmount, partialTick, ageInTicks, netHeadYaw, headPitch);
        }
    }
}
