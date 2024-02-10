package net.laith.avaritia.client.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.client.model.WingModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;


@Environment(EnvType.CLIENT)
public class WingRenderer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final ResourceLocation BASE = new ResourceLocation(AvaritiaMod.MOD_ID, "textures/models/armor/infinity_armor_wings_base.png");
    private static final ResourceLocation MMM = new ResourceLocation(AvaritiaMod.MOD_ID, "textures/models/armor/infinity_armor_wings_mmm.png");


    public WingRenderer(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferIn, int pPackedLight, @NotNull LivingEntity livingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        Player player = (Player) livingEntity;
        VertexConsumer vertexConsumer;
        ItemStack itemStack = player.getItemBySlot(EquipmentSlot.CHEST);
        if (!itemStack.is(ModItems.INFINITY_CHESTPLATE)) {
            return;
        }
        var model = new WingModel(WingModel.createBodyLayer().bakeRoot());
        if (!player.isInvisible() && player.getAbilities().flying) {
            if(FabricLoader.getInstance().isModLoaded("moremcmeta")) {
                vertexConsumer = bufferIn.getBuffer(RenderType.armorCutoutNoCull(MMM));
            } else
                vertexConsumer = bufferIn.getBuffer(RenderType.armorCutoutNoCull(BASE));
            poseStack.pushPose();
            poseStack.translate(0, -2.8, 0.325);
            poseStack.scale(3F, 3F, 3F);
            model.renderToBuffer(poseStack, vertexConsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            poseStack.popPose();
        }
    }
}
