package net.laith.avaritia.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.common.entity.InfinityArrowSubEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class InfinityArrowSubEntityRenderer extends ArrowRenderer<InfinityArrowSubEntity> {
    public static final ResourceLocation TEXTURE = new ResourceLocation("avaritia:textures/entity/infinity_arrow.png");

    public InfinityArrowSubEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(InfinityArrowSubEntity entity) {
        return TEXTURE;
    }
}