package net.laith.avaritia.compat.rei.menu;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleGridMenuInfo;
import net.laith.avaritia.compat.rei.display.extreme.ExtremeCraftingDisplay;
import net.minecraft.world.inventory.AbstractContainerMenu;

public record ExtremeMenuInfo(ExtremeCraftingDisplay<?> display) implements SimpleGridMenuInfo {
    @Override
    public int getCraftingResultSlotIndex(AbstractContainerMenu menu) {
        return 0;
    }

    @Override
    public int getCraftingWidth(AbstractContainerMenu menu) {
        return 9;
    }

    @Override
    public int getCraftingHeight(AbstractContainerMenu menu) {
        return 9;
    }

    @Override
    public Display getDisplay() {
        return this.display ;
    }
}
