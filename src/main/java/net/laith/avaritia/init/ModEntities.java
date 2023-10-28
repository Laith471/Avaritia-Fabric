package net.laith.avaritia.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.entity.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static EntityType<InfinityArrowEntity> INFINITY_ARROW_ENTITY;
    public static EntityType<InfinityArrowSubEntity> INFINITY_ARROW_SUB_ENTITY;
    public static EntityType<EntityGapingVoid> GAPING_VOID;
    public static EntityType<EndestPearlEntity> ENDEST_PEARL;


    public static void registerEntities() {

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

        ENDEST_PEARL = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(AvaritiaMod.MOD_ID, "endest_pearl"),
                FabricEntityTypeBuilder.<EndestPearlEntity>create(SpawnGroup.MISC, EndestPearlEntity::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .build()
        );

        GAPING_VOID = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(AvaritiaMod.MOD_ID, "gaping_void"),
                FabricEntityTypeBuilder.<EntityGapingVoid>create(SpawnGroup.MISC, EntityGapingVoid::new)
                        .build()
        );
    }
}
