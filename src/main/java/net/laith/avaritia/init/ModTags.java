package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> INFINITY_AXE = TagKey.of(RegistryKeys.BLOCK, new Identifier(AvaritiaMod.MOD_ID, "mineable/infinity_axe"));
        public static final TagKey<Block> INFINITY_PICKAXE = TagKey.of(RegistryKeys.BLOCK, new Identifier(AvaritiaMod.MOD_ID, "mineable/infinity_pickaxe"));
    }

    public static class Items {
        public static final TagKey<Item> AMETHYST_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "amethyst_blocks"));
        public static final TagKey<Item> EMERALD_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "emerald_blocks"));
        public static final TagKey<Item> IRON_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "iron_blocks"));
        public static final TagKey<Item> GOLD_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "gold_blocks"));
        public static final TagKey<Item> QUARTZ_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "quartz_blocks"));
        public static final TagKey<Item> HOPPERS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "hoppers"));
        public static final TagKey<Item> BONES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "bones"));
        public static final TagKey<Item> NETHERITE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "netherite_blocks"));
        public static final TagKey<Item> LAPIS_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "lapis_blocks"));
        public static final TagKey<Item> REDSTONE_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "redstone_blocks"));
        public static final TagKey<Item> DIAMOND_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "diamond_blocks"));
        public static final TagKey<Item> CRAFTING_TABLES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "crafting_tables"));
        public static final TagKey<Item> COPPER_BLOCKS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "copper_blocks"));
        public static final TagKey<Item> NETHER_STARS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "nether_stars"));
        public static final TagKey<Item> DIAMONDS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "diamonds"));
        public static final TagKey<Item> END_STONES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "end_stones"));
        public static final TagKey<Item> ENDER_PEARLS = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "ender_pearls"));
    }
}
