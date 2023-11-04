package net.laith.avaritia.compat;

import net.laith.avaritia.AvaritiaMod;
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
    }
}
