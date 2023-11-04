package net.laith.avaritia.compat;

import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.compat.tc.TC;
import net.laith.avaritia.util.helpers.BooleanHelper;

public class ModCompat {

    public static void compatify() {
        if (BooleanHelper.tc) {
            try {
                TC.init();
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia fell into smeltery");
            }
        }
    }
}
