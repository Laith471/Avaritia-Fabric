package net.laith.avaritia.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IItemBlock extends BlockItem {
    public IItemBlock(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        stack.addHideFlag(ItemStack.TooltipSection.MODIFIERS);
        super.appendTooltip(stack, world, tooltip, context);
    }
}
