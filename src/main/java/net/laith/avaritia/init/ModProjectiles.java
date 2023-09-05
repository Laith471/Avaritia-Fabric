package net.laith.avaritia.init;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.entity.InfinityArrowEntity;
import net.laith.avaritia.common.entity.InfinityArrowEntityRenderer;
import net.laith.avaritia.common.entity.InfinityArrowSubEntity;
import net.laith.avaritia.common.entity.InfinityArrowSubEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModProjectiles {
    public static EntityType<InfinityArrowEntity> INFINITY_ARROW_ENTITY;
    public static EntityType<InfinityArrowSubEntity> INFINITY_ARROW_SUB_ENTITY;

    public static void registerProjectiles() {

        INFINITY_ARROW_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(AvaritiaMod.MOD_ID, "infinity_arrow"),
                FabricEntityTypeBuilder.<InfinityArrowEntity>create(SpawnGroup.MISC, InfinityArrowEntity::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                        .trackRangeBlocks(64).trackedUpdateRate(20)
                        .build()
        );

        INFINITY_ARROW_SUB_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(AvaritiaMod.MOD_ID, "infinity_sub_arrow"),
                FabricEntityTypeBuilder.<InfinityArrowSubEntity>create(SpawnGroup.MISC, InfinityArrowSubEntity::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                        .trackRangeBlocks(64).trackedUpdateRate(20)
                        .build()
        );
    }

    public static void registerRenderers() {
        EntityRendererRegistry.register(ModProjectiles.INFINITY_ARROW_ENTITY,
                InfinityArrowEntityRenderer::new);

        EntityRendererRegistry.register(ModProjectiles.INFINITY_ARROW_SUB_ENTITY,
                InfinityArrowSubEntityRenderer::new);
    }
}
