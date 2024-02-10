package net.laith.avaritia.util.helpers;

import net.laith.avaritia.init.ModItems;
import net.minecraft.world.item.ItemStack;

public class StackHelper {

    public static boolean isPulse(ItemStack stack) {
        return stack.is(ModItems.INFINITY_CATALYST) ||
         stack.is(ModItems.INFINITY_INGOT);
    }
}
