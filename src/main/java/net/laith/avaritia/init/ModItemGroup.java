package net.laith.avaritia.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.laith.avaritia.AvaritiaMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModItemGroup {
    public static CreativeModeTab AVARITIA;


    public static void registerGroupItem() {
        AVARITIA = FabricItemGroup.builder()
                .title(Component.literal("Avaritia"))
                .icon(() -> new ItemStack(ModItems.INFINITY_CATALYST))
                .displayItems(((displayContext, entries) -> {
                    entries.accept(ModItems.INFINITY_CATALYST);
                    entries.accept(ModItems.INFINITY_INGOT);
                    entries.accept(ModItems.CRYSTAL_MATRIX_INGOT);
                    entries.accept(ModItems.NEUTRONIUM_INGOT);
                    entries.accept(ModItems.NEUTRONIUM_NUGGET);
                    entries.accept(ModItems.PILE_OF_NEUTRONS);
                    entries.accept(ModItems.RECORD_FRAGMENT);
                    entries.accept(ModItems.DIAMOND_LATTICE);
                    entries.accept(ModItems.ENDEST_PEARL);
                    entries.accept(ModItems.COSMIC_MEATBALLS);
                    entries.accept(ModItems.ULTIMATE_STEW);
                    //singularities
                    entries.accept(ModItems.IRON_SINGULARITY);
                    entries.accept(ModItems.GOLD_SINGULARITY);
                    entries.accept(ModItems.LAPIS_SINGULARITY);
                    entries.accept(ModItems.REDSTONE_SINGULARITY);
                    entries.accept(ModItems.COPPER_SINGULARITY);
                    entries.accept(ModItems.QUARTZ_SINGULARITY);
                    entries.accept(ModItems.DIAMOND_SINGULARITY);
                    entries.accept(ModItems.EMERALD_SINGULARITY);
                    entries.accept(ModItems.NETHERITE_SINGULARITY);
                    entries.accept(ModItems.AMETHYST_SINGULARITY);
                    entries.accept(ModItems.LEAD_SINGULARITY);
                    entries.accept(ModItems.NICKEL_SINGULARITY);
                    entries.accept(ModItems.IRIDIUM_SINGULARITY);
                    entries.accept(ModItems.FLUXED_SINGULARITY);
                    entries.accept(ModItems.PLATINUM_SINGULARITY);
                    entries.accept(ModItems.SILVER_SINGULARITY);
                    // Blocks
                    entries.accept(ModBlocks.CRYSTAL_MATRIX_BLOCK);
                    entries.accept(ModBlocks.INFINITY_BLOCK);
                    entries.accept(ModBlocks.COMPRESSED_CRAFTING_TABLE);
                    entries.accept(ModBlocks.NEUTRONIUM_BLOCK);
                    entries.accept(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE);
                    entries.accept(ModBlocks.EXTREME_CRAFTING_TABLE);
                    entries.accept(ModBlocks.NEUTRON_COLLECTOR);
                    entries.accept(ModBlocks.NEUTRONIUM_COMPRESSOR);
                    //tools
                    entries.accept(ModItems.SKULLFIRE_SWORD);
                    entries.accept(ModItems.INFINITY_SWORD);
                    entries.accept(ModItems.INFINITY_AXE);
                    entries.accept(ModItems.INFINITY_PICKAXE);
                    entries.accept(ModItems.INFINITY_SHOVEL);
                    entries.accept(ModItems.INFINITY_BOW);
                    //armor
                    entries.accept(ModItems.INFINITY_HELMET);
                    entries.accept(ModItems.INFINITY_CHESTPLATE);
                    entries.accept(ModItems.INFINITY_LEGGINGS);
                    entries.accept(ModItems.INFINITY_BOOTS);
                }))
                .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(
                AvaritiaMod.MOD_ID, "infinity_catalyst"), AVARITIA);
    }
}