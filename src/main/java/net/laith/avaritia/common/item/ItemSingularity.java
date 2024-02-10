package net.laith.avaritia.common.item;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemSingularity extends Item {
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

}
