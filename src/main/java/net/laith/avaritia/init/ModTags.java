package net.laith.avaritia.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Item> END_STONES = TagKey.of(RegistryKeys.ITEM, new Identifier("c", "end_stones"));
    }
}
