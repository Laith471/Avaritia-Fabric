package net.laith.avaritia.common.item;

import net.laith.avaritia.init.ModTextures;
import net.laith.avaritia.util.render.IHaloRenderItem;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PileOfNeutrons extends Item implements IHaloRenderItem {
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

    @Override
    public boolean shouldDrawHalo(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation getHaloTexture(ItemStack stack) {
                return ModTextures.HALO_NOISE_MMM;
    }

    @Override
    public int getHaloColour(ItemStack stack) {
        return 0x33FFFFFF;
    }

    @Override
    public int getHaloSize(ItemStack stack) {
        return 8;
    }

    @Override
    public boolean shouldDrawPulse(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isItNeutron(ItemStack stack) {
        return true;
    }
}
