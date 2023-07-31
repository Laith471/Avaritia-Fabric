package net.laith.avaritia.compat.rei.menu;

import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.transfer.info.simple.SimpleGridMenuInfo;
import net.laith.avaritia.compat.rei.display.ExtremeCraftingDisplay;
import net.minecraft.screen.ScreenHandler;

public record ExtremeMenuInfo(ExtremeCraftingDisplay display) implements SimpleGridMenuInfo {
    @Override
    public int getCraftingResultSlotIndex(ScreenHandler menu) {
        return 0;
    }

    @Override
    public int getCraftingWidth(ScreenHandler menu) {
        return 9;
    }

    @Override
    public int getCraftingHeight(ScreenHandler menu) {
        return 9;
    }

    @Override
    public Display getDisplay() {
        return this.display ;
    }
}
