package net.laith.avaritia.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.entity.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static EntityType<InfinityArrowEntity> INFINITY_ARROW_ENTITY;
    public static EntityType<InfinityArrowSubEntity> INFINITY_ARROW_SUB_ENTITY;
    public static EntityType<GapingVoidEntity> GAPING_VOID;
    public static EntityType<EndestPearlEntity> ENDEST_PEARL;


    public static void registerEntities() {

        INFINITY_ARROW_ENTITY = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_arrow"),
                FabricEntityTypeBuilder.<InfinityArrowEntity>create(MobCategory.MISC, InfinityArrowEntity::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                        .trackRangeBlocks(64).trackedUpdateRate(20)
                        .build()
        );

        INFINITY_ARROW_SUB_ENTITY = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_sub_arrow"),
                FabricEntityTypeBuilder.<InfinityArrowSubEntity>create(MobCategory.MISC, InfinityArrowSubEntity::new)
                        .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                        .trackRangeBlocks(64).trackedUpdateRate(20)
                        .build()
        );

        ENDEST_PEARL = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(AvaritiaMod.MOD_ID, "endest_pearl"),
                FabricEntityTypeBuilder.<EndestPearlEntity>create(MobCategory.MISC, EndestPearlEntity::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .build()
        );



        GAPING_VOID = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(AvaritiaMod.MOD_ID, "gaping_void"),
                FabricEntityTypeBuilder.<GapingVoidEntity>create(MobCategory.MISC, GapingVoidEntity::new)
                        .build()
        );
    }
}
