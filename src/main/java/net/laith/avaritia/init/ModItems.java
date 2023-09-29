package net.laith.avaritia.init;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.item.*;
import net.laith.avaritia.common.item.armor.InfinityArmorItem;
import net.laith.avaritia.common.item.tools.*;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class ModItems {

    public static final Item INFINITY_CATALYST = registerItem("infinity_catalyst",
            new ItemResource(new FabricItemSettings(), "item.avaritia.infinity_catalyst.tooltip", false));

    public static final Item INFINITY_INGOT = registerItem("infinity_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.infinity_ingot.tooltip", false));

    public static final Item CRYSTAL_MATRIX_INGOT = registerItem("crystal_matrix_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.crystal_matrix_ingot.tooltip", true));

    public static final Item RECORD_FRAGMENT = registerItem("record_fragment",
            new ItemResource(new FabricItemSettings(), "item.avaritia.record_fragment.tooltip", true));

    public static final Item NEUTRONIUM_INGOT = registerItem("neutronium_ingot",
            new ItemResource(new FabricItemSettings(), "item.avaritia.neutronium_ingot.tooltip", true));

    public static final Item NEUTRONIUM_NUGGET = registerItem("neutronium_nugget",
            new ItemResource(new FabricItemSettings(), "item.avaritia.neutronium_nugget.tooltip", true));

    public static final Item PILE_OF_NEUTRONS = registerItem("pile_of_neutrons",
            new PileOfNeutrons(new FabricItemSettings()));

    public static final Item ULTIMATE_STEW = registerItem("ultimate_stew",
            new Item(new FabricItemSettings().food(ModFoodComponents.ULTIMATE_STEW)));

    public static final Item DIAMOND_LATTICE = registerItem("diamond_lattice",
            new Item(new FabricItemSettings()));

    public static final Item COSMIC_MEATBALLS = registerItem("cosmic_meatballs",
            new Item(new FabricItemSettings().food(ModFoodComponents.COSMIC_MEATBALLS)));

    public static final Item ENDEST_PEARL = registerItem("endest_pearl",
            new Item(new FabricItemSettings()));

    public static final Item IRON_SINGULARITY = registerItem("iron_singularity",
            new Item(new FabricItemSettings()));

    public static final Item GOLD_SINGULARITY = registerItem("gold_singularity",
            new Item(new FabricItemSettings()));

    public static final Item LAPIS_SINGULARITY = registerItem("lapis_singularity",
            new Item(new FabricItemSettings()));

    public static final Item REDSTONE_SINGULARITY = registerItem("redstone_singularity",
            new Item(new FabricItemSettings()));

    public static final Item COPPER_SINGULARITY = registerItem("copper_singularity",
            new Item(new FabricItemSettings()));

    public static final Item QUARTZ_SINGULARITY = registerItem("quartz_singularity",
            new Item(new FabricItemSettings()));

    public static final Item DIAMOND_SINGULARITY = registerItem("diamond_singularity",
            new Item(new FabricItemSettings()));

    public static final Item NETHERITE_SINGULARITY = registerItem("netherite_singularity",
            new Item(new FabricItemSettings()));

    public static final Item EMERALD_SINGULARITY = registerItem("emerald_singularity",
            new Item(new FabricItemSettings()));

    public static final Item AMETHYST_SINGULARITY = registerItem("amethyst_singularity",
            new Item(new FabricItemSettings()));

    public static final Item INFINITY_SWORD = registerItem("infinity_sword",
            new InfinitySwordItem(ModToolMaterials.INFINITY_SWORD, -1, -3.0F,new FabricItemSettings()));

    public static final Item INFINITY_AXE = registerItem("infinity_axe",
            new InfinityAxeItem(ModToolMaterials.INFINITY_AXE, -1, -3.0F,new FabricItemSettings()));

    public static final Item INFINITY_BOW = registerItem("infinity_bow",
            new InfinityBow(new FabricItemSettings().maxCount(1)));

    public static final Item INFINITY_PICKAXE = registerItem("infinity_pickaxe",
            new InfinityPickaxe(ModToolMaterials.INFINITY_PICKAXE, -1, -2.8F,new FabricItemSettings()));

    public static final Item INFINITY_SHOVEL = registerItem("infinity_shovel",
            new InfinityShovelItem(ModToolMaterials.INFINITY_SHOVEL, -1, -3.0F,new FabricItemSettings()));

    public static final Item SKULLFIRE_SWORD = registerItem("skullfire_sword",
            new SkullFireSwordItem(ToolMaterials.DIAMOND, 3, -2.4F,new FabricItemSettings()));

    public static final Item INFINITY_HELMET = registerItem("infinity_helmet",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.HELMET, new FabricItemSettings()));

    public static final Item INFINITY_CHESTPLATE = registerItem("infinity_chestplate",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    public static final Item INFINITY_LEGGINGS = registerItem("infinity_leggings",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.LEGGINGS,new FabricItemSettings()));

    public static final Item INFINITY_BOOTS = registerItem("infinity_boots",
            new InfinityArmorItem(ModArmorMaterials.INFINITY, ArmorItem.Type.BOOTS,new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,
                new Identifier(AvaritiaMod.MOD_ID, name), item);
    }
    public static void registerModItems() {
        AvaritiaMod.LOGGER.info("Registering Mod Items for " + AvaritiaMod.MOD_ID);
    }
}

