package net.laith.avaritia.init;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.laith.avaritia.client.render.entity.GapingVoidEntityRenderer;
import net.laith.avaritia.client.render.entity.InfinityArrowEntityRenderer;
import net.laith.avaritia.client.render.entity.InfinityArrowSubEntityRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

public class ModEntityRenderers {

    public static void registerRenderers() {
         EntityRendererRegistry.register(ModEntities.GAPING_VOID,
              GapingVoidEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.ENDEST_PEARL, (context) ->
                new ThrownItemRenderer<>(context));

        EntityRendererRegistry.register(ModEntities.INFINITY_ARROW_ENTITY,
                InfinityArrowEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.INFINITY_ARROW_SUB_ENTITY,
                InfinityArrowSubEntityRenderer::new);
    }
}
