package net.laith.avaritia.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.item.*;
import net.laith.avaritia.common.item.armor.InfinityArmorItem;
import net.laith.avaritia.common.item.tools.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;

public class ModItems {

    public static final Item INFINITY_CATALYST = registerItem("infinity_catalyst",
            new ItemResource(new FabricItemSettings(), "item.avaritia.infinity_catalyst.tooltip", false, 5));

    public static final Item INFINITY_INGOT = registerItem("infinity_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.infinity_ingot.tooltip", false, 6));

    public static final Item CRYSTAL_MATRIX_INGOT = registerItem("crystal_matrix_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.crystal_matrix_ingot.tooltip", true, 0));

    public static final Item RECORD_FRAGMENT = registerItem("record_fragment",
            new ItemResource(new FabricItemSettings(), "item.avaritia.record_fragment.tooltip", true, 0));

    public static final Item NEUTRONIUM_INGOT = registerItem("neutronium_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.neutronium_ingot.tooltip", true,4));

    public static final Item NEUTRONIUM_NUGGET = registerItem("neutronium_nugget",
            new ItemResource(new FabricItemSettings(), "item.avaritia.neutronium_nugget.tooltip", true,3));

    public static final Item PILE_OF_NEUTRONS = registerItem("pile_of_neutrons",
            new PileOfNeutrons(new FabricItemSettings()));

    public static final Item ULTIMATE_STEW = registerItem("ultimate_stew",
            new Item(new FabricItemSettings().food(ModFoodComponents.ULTIMATE_STEW)));

    public static final Item DIAMOND_LATTICE = registerItem("diamond_lattice",
            new Item(new FabricItemSettings()));

    public static final Item COSMIC_MEATBALLS = registerItem("cosmic_meatballs",
            new Item(new FabricItemSettings().food(ModFoodComponents.COSMIC_MEATBALLS)));

    public static final Item ENDEST_PEARL = registerItem("endest_pearl",
            new EndestPearlItem(new FabricItemSettings()));

    public static final Item LEAD_SINGULARITY = registerItem("lead_singularity",
            new ItemSingularity(0x3E3D4E, 0x444072, new FabricItemSettings()));

    public static final Item PLATINUM_SINGULARITY = registerItem("platinum_singularity",
            new ItemSingularity(0x5A82E2, 0x00BFFF, new FabricItemSettings()));

    public static final Item IRON_SINGULARITY = registerItem("iron_singularity",
            new ItemSingularity(0x7F7F7F, 0xE6E7E8, new FabricItemSettings()));

    public static final Item GOLD_SINGULARITY = registerItem("gold_singularity",
            new ItemSingularity(0xDBA213, 0xE8EF23, new FabricItemSettings()));

    public static final Item LAPIS_SINGULARITY = registerItem("lapis_singularity",
            new ItemSingularity(0x224BAF, 0x5A82E2, new FabricItemSettings()));

    public static final Item REDSTONE_SINGULARITY = registerItem("redstone_singularity",
            new ItemSingularity(0x900000, 0xDF0000, new FabricItemSettings()));

    public static final Item COPPER_SINGULARITY = registerItem("copper_singularity",
            new ItemSingularity(0x89511A, 0xE47200, new FabricItemSettings()));

    public static final Item QUARTZ_SINGULARITY = registerItem("quartz_singularity",
            new ItemSingularity(0x94867D, 0xFFFFFF, new FabricItemSettings()));

    public static final Item DIAMOND_SINGULARITY = registerItem("diamond_singularity",
            new ItemSingularity(0x8FCDC9, 0x45ACA5, new FabricItemSettings()));

    public static final Item NETHERITE_SINGULARITY = registerItem("netherite_singularity",
            new ItemSingularity(0x31292A, 0x654740, new FabricItemSettings()));

    public static final Item NICKEL_SINGULARITY = registerItem("nickel_singularity",
            new ItemSingularity(0xC4C698, 0xDEE187, new FabricItemSettings()));

    public static final Item SILVER_SINGULARITY = registerItem("silver_singularity",
            new ItemSingularity(0xD5D5D5, 0xC0C0C0, new FabricItemSettings()));

    public static final Item EMERALD_SINGULARITY = registerItem("emerald_singularity",
            new ItemSingularity(0x8CD170, 0x5CBE34, new FabricItemSettings()));

    public static final Item FLUXED_SINGULARITY = registerItem("fluxed_singularity",
            new ItemSingularity(0xFFFC95, 0xD62306, new FabricItemSettings()));

    public static final Item AMETHYST_SINGULARITY = registerItem("amethyst_singularity",
            new ItemSingularity(0x54398A, 0xB38EF3, new FabricItemSettings()));

    public static final Item IRIDIUM_SINGULARITY = registerItem("iridium_singularity",
            new ItemSingularity(0xE6E6FA, 0xE6E6FA, new FabricItemSettings()));

    public static final Item INFINITY_SWORD = registerItem("infinity_sword",
            new InfinitySwordItem(ModToolMaterials.INFINITY_SWORD, -1, -3.0F, new FabricItemSettings()));

    public static final Item INFINITY_AXE = registerItem("infinity_axe",
            new InfinityAxeItem(ModToolMaterials.INFINITY_AXE, -1, -3.0F, new FabricItemSettings()));

    public static final Item INFINITY_BOW = registerItem("infinity_bow",
            new InfinityBow(new FabricItemSettings().maxCount(1)));

    public static final Item INFINITY_PICKAXE = registerItem("infinity_pickaxe",
            new InfinityPickaxe(ModToolMaterials.INFINITY_PICKAXE, -1, -2.8F, new FabricItemSettings()));

    public static final Item INFINITY_SHOVEL = registerItem("infinity_shovel",
            new InfinityShovelItem(ModToolMaterials.INFINITY_SHOVEL, -1, -3.0F, new FabricItemSettings()));

    public static final Item SKULLFIRE_SWORD = registerItem("skullfire_sword",
            new SkullFireSwordItem(Tiers.DIAMOND, 3, -2.4F, new FabricItemSettings()));

    public static final Item INFINITY_HELMET = registerItem("infinity_helmet",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.HELMET, new FabricItemSettings()));

    public static final Item INFINITY_CHESTPLATE = registerItem("infinity_chestplate",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    public static final Item INFINITY_LEGGINGS = registerItem("infinity_leggings",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));

    public static final Item INFINITY_BOOTS = registerItem("infinity_boots",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM,
                new ResourceLocation(AvaritiaMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        AvaritiaMod.LOGGER.info("Registering Mod Items for " + AvaritiaMod.MOD_ID);
    }
}