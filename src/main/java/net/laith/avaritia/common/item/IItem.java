package net.laith.avaritia.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IItem extends Item {
    public IItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        stack.addHideFlag(ItemStack.TooltipSection.MODIFIERS);
        super.appendTooltip(stack, world, tooltip, context);
    }
}
