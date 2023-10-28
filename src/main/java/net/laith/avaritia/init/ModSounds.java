package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final SoundEvent GAPING_VOID = registerSoundEvent("gaping_void");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(AvaritiaMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        AvaritiaMod.LOGGER.info("Registering Sounds for " + AvaritiaMod.MOD_ID);
    }
}