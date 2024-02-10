package net.laith.avaritia.compat.tc;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.tc.tools.shuriken.InfinityShurikenEntity;
import net.laith.avaritia.compat.tc.tools.shuriken.InfinityShurikenItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;

public class TC {
    public static EntityType<InfinityShurikenEntity> INFINITY_SHURIKEN_ENTITY;
    public static Item INFINITY_SHURIKEN;

    private static void registerTinkerConstructEntities() {
        INFINITY_SHURIKEN_ENTITY = Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_shuriken"),
                FabricEntityTypeBuilder.<InfinityShurikenEntity>create(MobCategory.MISC, InfinityShurikenEntity::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .forceTrackedVelocityUpdates(true)
                        .build()
        );
    }

    private static void registerTinkerConstructItems() {
         INFINITY_SHURIKEN = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_shuriken"), new InfinityShurikenItem(new FabricItemSettings().maxCount(16), InfinityShurikenEntity::new));
    }

    public static void init() {
        registerTinkerConstructItems();
        registerTinkerConstructEntities();
    }
}
