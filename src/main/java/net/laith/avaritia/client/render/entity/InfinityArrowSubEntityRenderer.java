package net.laith.avaritia.client.render.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.laith.avaritia.common.entity.InfinityArrowSubEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class InfinityArrowSubEntityRenderer extends ProjectileEntityRenderer<InfinityArrowSubEntity> {
    public static final Identifier TEXTURE = new Identifier("avaritia:textures/entity/infinity_arrow.png");

    public InfinityArrowSubEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(InfinityArrowSubEntity entity) {
        return TEXTURE;
    }
}