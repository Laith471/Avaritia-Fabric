package net.laith.avaritia.util.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public interface TooltipEvent {

    Event<OnEquipmentSlot> ON_EQUIPMENT_SLOT = EventFactory.createArrayBacked(OnEquipmentSlot.class,
            (callbacks) -> (stack, list) -> {
                for (OnEquipmentSlot event : callbacks) {
                    event.OnEquipmentSlot(stack, list);
                }
            }
    );



    @FunctionalInterface
    public interface OnEquipmentSlot {

        void OnEquipmentSlot(ItemStack stack, List<Text> list);
    }
}
