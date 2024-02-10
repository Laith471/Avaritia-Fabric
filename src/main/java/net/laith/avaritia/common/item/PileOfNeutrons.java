package net.laith.avaritia.common.item;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PileOfNeutrons extends Item {
    public PileOfNeutrons(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("item.avaritia.pile_of_neutrons_source.tooltip"));
        } else {
            tooltipComponents.add(Component.translatable("item.avaritia.pile_of_neutrons_see_source.tooltip"));
        }
        tooltipComponents.add(Component.translatable("item.avaritia.pile_of_neutrons.tooltip"));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
