package net.laith.avaritia.util.helpers;

import net.fabricmc.loader.api.FabricLoader;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.AvaritiaConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class BooleanHelper {

    public static boolean isWearingTheFullArmor(Player player) {
        ItemStack headSlot = player.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chestSlot = player.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legsSlot = player.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feetSlot = player.getItemBySlot(EquipmentSlot.FEET);

        return  headSlot.getItem() == ModItems.INFINITY_HELMET &&
                chestSlot.getItem() == ModItems.INFINITY_CHESTPLATE &&
                legsSlot.getItem() == ModItems.INFINITY_LEGGINGS &&
                feetSlot.getItem() == ModItems.INFINITY_BOOTS;

    }

    public static boolean isWearingHelmet(Player player) {
        ItemStack headSlot = player.getItemBySlot(EquipmentSlot.HEAD);
        return headSlot.getItem() == ModItems.INFINITY_HELMET;
    }

    public static boolean isWearingChestplate(Player player) {
        ItemStack chestSlot = player.getItemBySlot(EquipmentSlot.CHEST);
        return chestSlot.getItem() == ModItems.INFINITY_CHESTPLATE;
    }

    public static boolean isWearingLeggings(Player player) {
        ItemStack legsSlot = player.getItemBySlot(EquipmentSlot.LEGS);
        return legsSlot.getItem() == ModItems.INFINITY_LEGGINGS;
    }

    public static boolean isWearingBoots(Player player) {
        ItemStack feetSlot = player.getItemBySlot(EquipmentSlot.FEET);
        return feetSlot.getItem() == ModItems.INFINITY_BOOTS;
    }

    public static boolean tc = false;
    public static boolean botania = false;

    public static void census(){
        tc = FabricLoader.getInstance().isModLoaded("tconstruct") && AvaritiaConfig.getConfig().tc;
        botania = FabricLoader.getInstance().isModLoaded("botania") && AvaritiaConfig.getConfig().botania;
    }
}
