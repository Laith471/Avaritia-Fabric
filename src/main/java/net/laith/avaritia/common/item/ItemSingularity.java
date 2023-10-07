package net.laith.avaritia.common.item;

import net.minecraft.item.Item;

import javax.swing.plaf.SplitPaneUI;
import java.util.ArrayList;
import java.util.List;

public class ItemSingularity extends Item {
    public static final List<ItemSingularity> getAll = new ArrayList<>();

    private final int primaryColor;
    private final int secondaryColor;

    public ItemSingularity(int primaryColor, int secondaryColor, Settings settings) {
        super(settings);
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        getAll.add(this);
    }

    public int getColor(int tintIndex) {
        return tintIndex == 0 ? this.primaryColor : this.secondaryColor;
    }

}
