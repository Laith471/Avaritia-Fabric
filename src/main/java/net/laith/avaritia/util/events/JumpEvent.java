package net.laith.avaritia.util.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;

public interface JumpEvent {

    Event<OnNormal> ON_NORMAL = EventFactory.createArrayBacked(OnNormal.class,
            (callbacks) -> (entity) -> {
                for (OnNormal event : callbacks) {
                    event.OnNormal(entity);
                }
            }
    );

    Event<OnSprint> ON_SPRINT = EventFactory.createArrayBacked(OnSprint.class,
            (callbacks) -> (entity) -> {
                for (OnSprint event : callbacks) {
                    event.OnSprint(entity);
                }
            }
    );

    @FunctionalInterface
    public interface OnNormal {

        void OnNormal(LivingEntity entity);
    }

    @FunctionalInterface
    public interface OnSprint {

        void OnSprint(LivingEntity entity);
    }
}
