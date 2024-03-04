package net.laith.avaritia.compat.botania;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.botania.block.InfinityManaPoolBlock;
import net.laith.avaritia.compat.botania.block.InfinityManaSpreaderBlock;
import net.laith.avaritia.compat.botania.block.SpecialFlowerBlockItem;
import net.laith.avaritia.compat.botania.blockentity.AsgardandelionBlockEntity;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaPoolBlockEntity;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaSpreaderBlockEntity;
import net.laith.avaritia.init.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.brew.BotaniaMobEffects;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.Supplier;

public class Botania {
    public static BlockEntityType<AsgardandelionBlockEntity> ASGARDANDELION_BLOCK_ENTITY;
    public static BlockEntityType<InfinityManaSpreaderBlockEntity> INFINITY_MANA_SPREADER_BLOCK_ENTITY;
    public static BlockEntityType<InfinityManaPoolBlockEntity> INFINITY_MANA_POOL_BLOCK_ENTITY;
    public static Block ASGARDANDELION;
    public static Block FLOATING_ASGARDANDELION;
    public static Block INFINITY_SPREADER;
    public static Block INFINITY_MANA_POOL;
    private static final BlockBehaviour.StateArgumentPredicate<EntityType<?>> NO_SPAWN = (state, world, pos, et) -> false;

    private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.copy(Blocks.POPPY);
    private static final BlockBehaviour.Properties FLOATING_PROPS = BotaniaBlocks.FLOATING_PROPS;


    private static FlowerBlock createSpecialFlowerBlock(
            MobEffect effect, int effectDuration,
            BlockBehaviour.Properties props,
            Supplier<BlockEntityType<? extends SpecialFlowerBlockEntity>> beType) {
        return XplatAbstractions.INSTANCE.createSpecialFlowerBlock(
                effect, effectDuration, props, beType
        );
    }

    public static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(AvaritiaMod.MOD_ID, name), block);
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AvaritiaMod.MOD_ID, name), new SpecialFlowerBlockItem(block, new FabricItemSettings()));
    }

    public static void registerBlockEntities() {
        ASGARDANDELION_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "asgardandelion_block_entity"),
                XplatAbstractions.INSTANCE.createBlockEntityType(AsgardandelionBlockEntity::new, ASGARDANDELION, FLOATING_ASGARDANDELION));
        INFINITY_MANA_SPREADER_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_spreader_block_entity"),
                FabricBlockEntityTypeBuilder.create(InfinityManaSpreaderBlockEntity::new, INFINITY_SPREADER).build(null));
        INFINITY_MANA_POOL_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "infinity_mana_pool_block_entity"),
                FabricBlockEntityTypeBuilder.create(InfinityManaPoolBlockEntity::new, INFINITY_MANA_POOL).build());
    }

    public static void init() {
        ASGARDANDELION = registerBlock("asgardandelion", createSpecialFlowerBlock(BotaniaMobEffects.clear, 1, FLOWER_PROPS, () -> Botania.ASGARDANDELION_BLOCK_ENTITY));
        FLOATING_ASGARDANDELION = registerBlock("floating_asgardandelion", new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> Botania.ASGARDANDELION_BLOCK_ENTITY));
        INFINITY_SPREADER = registerBlock("infinity_spreader", new InfinityManaSpreaderBlock(InfinityManaSpreaderBlock.Variant.INFINITY, BlockBehaviour.Properties.copy(ModBlocks.INFINITY_BLOCK).isValidSpawn(NO_SPAWN)));
        INFINITY_MANA_POOL = registerBlock("infinity_mana_pool", new InfinityManaPoolBlock(InfinityManaPoolBlock.Variant.INFINITY, BlockBehaviour.Properties.copy(ModBlocks.INFINITY_BLOCK)));
        registerBlockEntities();
    }
}