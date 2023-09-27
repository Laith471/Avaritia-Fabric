package net.laith.avaritia.util.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface JumpCallback {
    Event<JumpCallback> ON_NORMAL = EventFactory.createArrayBacked(JumpCallback.class,
            (listeners) -> (entity) -> {
                for (JumpCallback listener : listeners) {
                    ActionResult result = listener.interact(entity);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    Event<JumpCallback> ON_SPRINT = EventFactory.createArrayBacked(JumpCallback.class,
            (listeners) -> (entity) -> {
                for (JumpCallback listener : listeners) {
                    ActionResult result = listener.interact(entity);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult interact(LivingEntity entity);
}
