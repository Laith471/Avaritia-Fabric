package net.laith.avaritia.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.laith.avaritia.AvaritiaMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroup {
    public static ItemGroup AVARITIA;


    public static void registerGroupItem() {
        AVARITIA = FabricItemGroup.builder()
                .displayName(Text.literal("Avaritia"))
                .icon(() -> new ItemStack(ModItems.INFINITY_CATALYST))
                .entries(((displayContext, entries) -> {
                    entries.add(ModItems.INFINITY_CATALYST);
                    entries.add(ModItems.INFINITY_INGOT);
                    entries.add(ModItems.CRYSTAL_MATRIX_INGOT);
                    entries.add(ModItems.NEUTRONIUM_INGOT);
                    entries.add(ModItems.NEUTRONIUM_NUGGET);
                    entries.add(ModItems.PILE_OF_NEUTRONS);
                    entries.add(ModItems.RECORD_FRAGMENT);
                    entries.add(ModItems.DIAMOND_LATTICE);
                    entries.add(ModItems.ENDEST_PEARL);
                    entries.add(ModItems.COSMIC_MEATBALLS);
                    entries.add(ModItems.ULTIMATE_STEW);
                    //singularities
                    entries.add(ModItems.IRON_SINGULARITY);
                    entries.add(ModItems.GOLD_SINGULARITY);
                    entries.add(ModItems.LAPIS_SINGULARITY);
                    entries.add(ModItems.REDSTONE_SINGULARITY);
                    entries.add(ModItems.COPPER_SINGULARITY);
                    entries.add(ModItems.QUARTZ_SINGULARITY);
                    entries.add(ModItems.DIAMOND_SINGULARITY);
                    entries.add(ModItems.EMERALD_SINGULARITY);
                    entries.add(ModItems.NETHERITE_SINGULARITY);
                    entries.add(ModItems.AMETHYST_SINGULARITY);
                    entries.add(ModItems.LEAD_SINGULARITY);
                    entries.add(ModItems.NICKEL_SINGULARITY);
                    entries.add(ModItems.IRIDIUM_SINGULARITY);
                    entries.add(ModItems.FLUXED_SINGULARITY);
                    entries.add(ModItems.PLATINUM_SINGULARITY);
                    entries.add(ModItems.SILVER_SINGULARITY);
                    // Blocks
                    entries.add(ModBlocks.CRYSTAL_MATRIX_BLOCK);
                    entries.add(ModBlocks.INFINITY_BLOCK);
                    entries.add(ModBlocks.COMPRESSED_CRAFTING_TABLE);
                    entries.add(ModBlocks.NEUTRONIUM_BLOCK);
                    entries.add(ModBlocks.DOUBLE_COMPRESSED_CRAFTING_TABLE);
                    entries.add(ModBlocks.EXTREME_CRAFTING_TABLE);
                    entries.add(ModBlocks.NEUTRON_COLLECTOR);
                    entries.add(ModBlocks.NEUTRONIUM_COMPRESSOR);
                    //tools
                    entries.add(ModItems.SKULLFIRE_SWORD);
                    entries.add(ModItems.INFINITY_SWORD);
                    entries.add(ModItems.INFINITY_AXE);
                    entries.add(ModItems.INFINITY_PICKAXE);
                    entries.add(ModItems.INFINITY_SHOVEL);
                    entries.add(ModItems.INFINITY_BOW);
                    //armor
                    entries.add(ModItems.INFINITY_HELMET);
                    entries.add(ModItems.INFINITY_CHESTPLATE);
                    entries.add(ModItems.INFINITY_LEGGINGS);
                    entries.add(ModItems.INFINITY_BOOTS);
                }))
                .build();

        Registry.register(Registries.ITEM_GROUP, new Identifier(
                AvaritiaMod.MOD_ID, "infinity_catalyst"), AVARITIA);
    }
}