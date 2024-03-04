package net.laith.avaritia.common.item;

import net.laith.avaritia.init.ModTextures;
import net.laith.avaritia.util.render.IHaloRenderItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ItemSingularity extends Item implements IHaloRenderItem {
    public static final List<ItemSingularity> getAll = new ArrayList<>();

    private final int primaryColor;
    private final int secondaryColor;

    public ItemSingularity(int primaryColor, int secondaryColor, Properties properties) {
        super(properties);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        getAll.add(this);
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

    @Override
    public boolean shouldDrawHalo(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation getHaloTexture(ItemStack stack) {
        return ModTextures.HALO;
    }

    @Override
    public int getHaloColour(ItemStack stack) {
        return 0xFF000000;
    }

    @Override
    public int getHaloSize(ItemStack stack) {
        return 4;
    }

    @Override
    public boolean shouldDrawPulse(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isItNeutron(ItemStack stack) {
        return false;
    }
}
