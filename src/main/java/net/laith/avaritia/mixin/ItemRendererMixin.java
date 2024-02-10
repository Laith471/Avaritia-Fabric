package net.laith.avaritia.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.laith.avaritia.util.helpers.StackHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {

    @Unique
    private Random random = new Random();

    @Inject(method = "render", at = @At( value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderModelLists(Lnet/minecraft/client/resources/model/BakedModel;Lnet/minecraft/world/item/ItemStack;IILcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;)V"))
    public void renderItem(ItemStack itemStack, ItemDisplayContext displayContext, boolean leftHand, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, BakedModel model, CallbackInfo ci) {
        if(displayContext == ItemDisplayContext.GUI && StackHelper.isPulse(itemStack)) {
            double scale = random.nextDouble() * 0.15 + 0.95;
            double trans = (1.0 - scale) / 2.0;
            poseStack.translate(trans, trans, 0.0);
            poseStack.scale((float)scale, (float)scale, 1.0001F);
        }
    }
}