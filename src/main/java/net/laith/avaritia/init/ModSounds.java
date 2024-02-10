package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent GAPING_VOID = registerSoundEvent("gaping_void");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation id = new ResourceLocation(AvaritiaMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        AvaritiaMod.LOGGER.info("Registering Sounds for " + AvaritiaMod.MOD_ID);
    }
}