package net.laith.avaritia.compat.tc;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.tc.tools.shuriken.InfinityShurikenEntity;
import net.laith.avaritia.compat.tc.tools.shuriken.InfinityShurikenItem;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TC {
    public static EntityType<InfinityShurikenEntity> INFINITY_SHURIKEN_ENTITY;
    public static Item INFINITY_SHURIKEN;

    private static void registerTinkerConstructEntities() {
        INFINITY_SHURIKEN_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                new Identifier(AvaritiaMod.MOD_ID, "infinity_shuriken"),
                FabricEntityTypeBuilder.<InfinityShurikenEntity>create(SpawnGroup.MISC, InfinityShurikenEntity::new)
                        .dimensions(EntityDimensions.fixed(0.25F, 0.25F))
                        .trackRangeBlocks(4).trackedUpdateRate(10)
                        .forceTrackedVelocityUpdates(true)
                        .build()
        );
    }

    private static void registerTinkerConstructItems() {
         INFINITY_SHURIKEN = Registry.register(Registries.ITEM, new Identifier(AvaritiaMod.MOD_ID, "infinity_shuriken"), new InfinityShurikenItem(new FabricItemSettings().maxCount(16), InfinityShurikenEntity::new));
    }

    public static void init() {
        registerTinkerConstructItems();
        registerTinkerConstructEntities();
    }
}
