package net.laith.avaritia.common.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class InfinityArrowEntityRenderer extends ProjectileEntityRenderer<InfinityArrowEntity> {
    public static final Identifier TEXTURE = new Identifier("avaritia:textures/entity/infinity_arrow.png");

    public InfinityArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(InfinityArrowEntity entity) {
        return TEXTURE;
    }
}