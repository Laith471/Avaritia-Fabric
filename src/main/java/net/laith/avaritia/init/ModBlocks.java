package net.laith.avaritia.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.block.ExtremeCraftingTableBlock;
import net.laith.avaritia.common.block.NeutronCollectorBlock;
import net.laith.avaritia.common.block.NeutroniumCompressorBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block CRYSTAL_MATRIX_BLOCK = registerBlock("crystal_matrix_block", new Block(FabricBlockSettings.of().strength(50.0F, 2000.0F).requiresTool()));

    public static final Block NEUTRONIUM_BLOCK = registerBlock("neutronium_block", new Block(FabricBlockSettings.of().strength(50.0F, 2000.0F).requiresTool()));

    public static final Block INFINITY_BLOCK = registerBlock("infinity_block", new Block(FabricBlockSettings.of().strength(50.0F, 2000.0F).requiresTool()));

    public static final Block COMPRESSED_CRAFTING_TABLE = registerBlock("compressed_crafting_table", new Block(FabricBlockSettings.of().hardness(4.0f).requiresTool()));

    public static final Block DOUBLE_COMPRESSED_CRAFTING_TABLE = registerBlock("double_compressed_crafting_table", new Block(FabricBlockSettings.of().hardness(20.0F).requiresTool()));

    public static final Block EXTREME_CRAFTING_TABLE = registerBlock("extreme_crafting_table", new ExtremeCraftingTableBlock(FabricBlockSettings.of().strength(20.0F, 2000.0F).requiresTool()));

    public static final Block NEUTRON_COLLECTOR = registerBlock("neutron_collector", new NeutronCollectorBlock(FabricBlockSettings.of().hardness(20.0F).requiresTool()));

    public static final Block NEUTRONIUM_COMPRESSOR = registerBlock("neutronium_compressor", new NeutroniumCompressorBlock(FabricBlockSettings.of().hardness(20.0F).requiresTool()));

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(AvaritiaMod.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(AvaritiaMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        AvaritiaMod.LOGGER.info("Registering Mod Blocks for " + AvaritiaMod.MOD_ID);
    }
}