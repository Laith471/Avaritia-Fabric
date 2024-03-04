package net.laith.avaritia.util.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface IHaloRenderItem {

    @Environment(EnvType.CLIENT)
    boolean shouldDrawHalo(ItemStack stack);

    @Environment(EnvType.CLIENT)
    ResourceLocation getHaloTexture(ItemStack stack);

    @Environment(EnvType.CLIENT)
    int getHaloColour(ItemStack stack);

    @Environment(EnvType.CLIENT)
    int getHaloSize(ItemStack stack);

    @Environment(EnvType.CLIENT)
    boolean shouldDrawPulse(ItemStack stack);

    @Environment(EnvType.CLIENT)
    boolean isItNeutron(ItemStack stack);

}