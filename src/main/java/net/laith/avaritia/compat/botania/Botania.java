package net.laith.avaritia.compat.botania;

import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.botania.flowerblock.AsgardandelionBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.FloatingSpecialFlowerBlock;
import vazkii.botania.common.block.mana.ManaSpreaderBlock;
import vazkii.botania.common.brew.BotaniaMobEffects;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.function.Supplier;

public class Botania {
    public static BlockEntityType<AsgardandelionBlockEntity> ASGARDANDELION_BLOCK_ENTITY;
    public static Block ASGARDANDELION;
    public static Block FLOATING_ASGARDANDELION;

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
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(AvaritiaMod.MOD_ID, name), new SpecialFlowerBlockItem(block, BotaniaItems.defaultBuilder()));
    }

    public static void registerBlockEntities() {
        ASGARDANDELION_BLOCK_ENTITY = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(AvaritiaMod.MOD_ID, "asgardandelion"),
                XplatAbstractions.INSTANCE.createBlockEntityType(AsgardandelionBlockEntity::new, ASGARDANDELION, FLOATING_ASGARDANDELION));
    }

    public static void init() {
        ASGARDANDELION = registerBlock("asgardandelion", createSpecialFlowerBlock(BotaniaMobEffects.clear, 1, FLOWER_PROPS, () -> Botania.ASGARDANDELION_BLOCK_ENTITY));
        FLOATING_ASGARDANDELION = registerBlock("floating_asgardandelion", new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> Botania.ASGARDANDELION_BLOCK_ENTITY));
        registerBlockEntities();
    }
}