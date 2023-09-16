package net.laith.avaritia.util.helpers;

import net.laith.avaritia.init.ModArmorMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

public class BooleanHelper {

    public static boolean isWearingTheFullArmor(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);

        return headSlot.getItem() instanceof ArmorItem &&
                chestSlot.getItem() instanceof ArmorItem &&
                legsSlot.getItem() instanceof ArmorItem &&
                feetSlot.getItem() instanceof ArmorItem &&
                ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY &&
                ((ArmorItem) chestSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY &&
                ((ArmorItem) legsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY&&
                ((ArmorItem) feetSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    public static boolean isWearingHelmet(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        return headSlot.getItem() instanceof ArmorItem && ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    public static boolean isWearingChestplate(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        return headSlot.getItem() instanceof ArmorItem && ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    public static boolean isWearingLeggings(PlayerEntity player) {
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        return legsSlot.getItem() instanceof ArmorItem && ((ArmorItem) legsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    public static boolean isWearingBoots(PlayerEntity player) {
        ItemStack bootsSlot = player.getEquippedStack(EquipmentSlot.FEET);
        return bootsSlot.getItem() instanceof ArmorItem && ((ArmorItem) bootsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }
}
