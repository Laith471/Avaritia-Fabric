package net.laith.avaritia.compat;

import net.fabricmc.loader.api.FabricLoader;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.botania.Botania;
import net.laith.avaritia.compat.botania.BotaniaClient;
import net.laith.avaritia.compat.tc.TCClient;
import net.laith.avaritia.util.helpers.BooleanHelper;

public class ModCompatClient {

    public static void compatifyClient() {
        if (BooleanHelper.tc) {
            try {
                TCClient.init();
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia client fell into smeltery");
            }

        }
        /*
        if(BooleanHelper.botania) {
            try {
                BotaniaClient.init();
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia doesn't see the beauty of the nature");
            }
        }
         */
    }
}
