package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> INFINITY_AXE = TagKey.create(Registries.BLOCK, new ResourceLocation(AvaritiaMod.MOD_ID, "mineable/infinity_axe"));
        public static final TagKey<Block> INFINITY_PICKAXE = TagKey.create(Registries.BLOCK, new ResourceLocation(AvaritiaMod.MOD_ID, "mineable/infinity_pickaxe"));
    }

    public static class Items {
        public static final TagKey<Item> AMETHYST_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "amethyst_blocks"));
        public static final TagKey<Item> EMERALD_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "emerald_blocks"));
        public static final TagKey<Item> IRON_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "iron_blocks"));
        public static final TagKey<Item> GOLD_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "gold_blocks"));
        public static final TagKey<Item> QUARTZ_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "quartz_blocks"));
        public static final TagKey<Item> HOPPERS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "hoppers"));
        public static final TagKey<Item> BONES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "bones"));
        public static final TagKey<Item> NETHERITE_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "netherite_blocks"));
        public static final TagKey<Item> LAPIS_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "lapis_blocks"));
        public static final TagKey<Item> REDSTONE_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "redstone_blocks"));
        public static final TagKey<Item> DIAMOND_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "diamond_blocks"));
        public static final TagKey<Item> CRAFTING_TABLES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "crafting_tables"));
        public static final TagKey<Item> COPPER_BLOCKS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "copper_blocks"));
        public static final TagKey<Item> NETHER_STARS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "nether_stars"));
        public static final TagKey<Item> DIAMONDS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "diamonds"));
        public static final TagKey<Item> END_STONES = TagKey.create(Registries.ITEM, new ResourceLocation("c", "end_stones"));
        public static final TagKey<Item> ENDER_PEARLS = TagKey.create(Registries.ITEM, new ResourceLocation("c", "ender_pearls"));
    }
}
