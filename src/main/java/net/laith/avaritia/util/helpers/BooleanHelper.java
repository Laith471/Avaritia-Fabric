package net.laith.avaritia.util.helpers;

import net.laith.avaritia.init.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public class BooleanHelper {

    public static boolean isWearingTheFullArmor(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);

        return  headSlot.getItem() == ModItems.INFINITY_HELMET &&
                chestSlot.getItem() == ModItems.INFINITY_CHESTPLATE &&
                legsSlot.getItem() == ModItems.INFINITY_LEGGINGS &&
                feetSlot.getItem() == ModItems.INFINITY_BOOTS;

    }

    public static boolean isWearingHelmet(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        return headSlot.getItem() == ModItems.INFINITY_HELMET;
    }

    public static boolean isWearingChestplate(PlayerEntity player) {
        ItemStack chestSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        return chestSlot.getItem() == ModItems.INFINITY_CHESTPLATE;
    }

    public static boolean isWearingLeggings(PlayerEntity player) {
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        return legsSlot.getItem() == ModItems.INFINITY_LEGGINGS;
    }

    public static boolean isWearingBoots(PlayerEntity player) {
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);
        return feetSlot.getItem() == ModItems.INFINITY_BOOTS;
    }
}
