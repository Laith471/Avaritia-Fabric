package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Item> END_STONES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "end_stones"));
        public static final TagKey<Block> INFINITY_AXE = TagKey.of(RegistryKeys.BLOCK, new Identifier(AvaritiaMod.MOD_ID, "mineable/infinity_axe"));
        public static final TagKey<Block> INFINITY_PICKAXE = TagKey.of(RegistryKeys.BLOCK, new Identifier(AvaritiaMod.MOD_ID, "mineable/infinity_pickaxe"));
    }
}
