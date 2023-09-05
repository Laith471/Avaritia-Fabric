package net.laith.avaritia.util.helpers;

import net.laith.avaritia.init.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class StackHelper {

    public static boolean isPulse(ItemStack stack) {
        return stack.isOf(ModItems.INFINITY_CATALYST) ||
         stack.isOf(ModItems.INFINITY_INGOT);
    }
}
