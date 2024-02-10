package net.laith.avaritia.init;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.blockentity.ExtremeCraftingTableBlockEntity;
import net.laith.avaritia.common.blockentity.MatterClusterBlockEntity;
import net.laith.avaritia.common.blockentity.NeutronCollectorBlockEntity;
import net.laith.avaritia.common.blockentity.NeutroniumCompressorBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntities {
    public static BlockEntityType<ExtremeCraftingTableBlockEntity> EXTREME_CRAFTING_TABLE_BLOCK_ENTITY;
    public static BlockEntityType<NeutronCollectorBlockEntity> NEUTRON_COLLECTOR_BLOCK_ENTITY;
    public static BlockEntityType<NeutroniumCompressorBlockEntity> NEUTRONIUM_COMPRESSOR_BLOCK_ENTITY;
    public static BlockEntityType<MatterClusterBlockEntity> MATTER_CLUSTER_BLOCK_ENTITY;

    public static void registerBlockEntities() {
        EXTREME_CRAFTING_TABLE_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "extreme_crafting_table"),
                FabricBlockEntityTypeBuilder.create(ExtremeCraftingTableBlockEntity::new, ModBlocks.EXTREME_CRAFTING_TABLE).build(null));

    NEUTRON_COLLECTOR_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "neutron_collector"),
                FabricBlockEntityTypeBuilder.create(NeutronCollectorBlockEntity::new, ModBlocks.NEUTRON_COLLECTOR).build(null));

    NEUTRONIUM_COMPRESSOR_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "neutronium_compressor"),
            FabricBlockEntityTypeBuilder.create(NeutroniumCompressorBlockEntity::new, ModBlocks.NEUTRONIUM_COMPRESSOR).build(null));

        MATTER_CLUSTER_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "matter_cluster"),
                FabricBlockEntityTypeBuilder.create(MatterClusterBlockEntity::new, ModBlocks.MATTER_CLUSTER).build(null));
    }
}
