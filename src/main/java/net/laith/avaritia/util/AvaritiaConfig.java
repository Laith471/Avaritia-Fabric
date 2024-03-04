package net.laith.avaritia.util;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.laith.avaritia.AvaritiaMod;

@Config(name = AvaritiaMod.MOD_ID)
public class AvaritiaConfig implements ConfigData {
    public int process = 7111;
    public boolean tc = true;
    public boolean botania = true;

    @ConfigEntry.Gui.Excluded
    private transient static boolean registered = false;

    public static synchronized AvaritiaConfig getConfig() {
        if (!registered) {
            AutoConfig.register(AvaritiaConfig.class, Toml4jConfigSerializer::new);
            registered = true;
        }

        return AutoConfig.getConfigHolder(AvaritiaConfig.class).getConfig();
    }
}
