package net.laith.avaritia.common.item;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PileOfNeutrons extends Item {
    public PileOfNeutrons(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("item.avaritia.pile_of_neutrons_source.tooltip"));
        } else {
            tooltip.add(Text.translatable("item.avaritia.pile_of_neutrons_see_source.tooltip"));
        }
        tooltip.add(Text.translatable("item.avaritia.pile_of_neutrons.tooltip"));
    }
}
