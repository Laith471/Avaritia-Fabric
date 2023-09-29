package net.laith.avaritia.compat.rei.menu;

import me.shedaniel.rei.api.common.transfer.info.MenuInfoContext;
import me.shedaniel.rei.api.common.transfer.info.simple.SimplePlayerInventoryMenuInfo;
import me.shedaniel.rei.api.common.transfer.info.stack.SlotAccessor;
import net.laith.avaritia.common.screenhandler.NeutroniumCompressorScreenHandler;
import net.laith.avaritia.compat.rei.display.NeutroniumCompressorDisplay;

import java.util.List;

public record NeutroniumCompressorMenu(NeutroniumCompressorDisplay display) implements SimplePlayerInventoryMenuInfo<NeutroniumCompressorScreenHandler, NeutroniumCompressorDisplay> {
    @Override
    public Iterable<SlotAccessor> getInputSlots(MenuInfoContext<NeutroniumCompressorScreenHandler, ?, NeutroniumCompressorDisplay> context) {
        return List.of

       (SlotAccessor.fromSlot(context.getMenu().getSlot(1)),
        SlotAccessor.fromSlot(context.getMenu().getSlot(0)));


    }

    @Override
    public NeutroniumCompressorDisplay getDisplay() {
       return this.display;
    }
}
