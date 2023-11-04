package net.laith.avaritia.compat.tc;

import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import slimeknights.tconstruct.gadgets.client.RenderShuriken;

public class TCClient {

    private static void registerTinkerConstructEntityRenderers() {
        EntityRendererRegistry.register(TC.INFINITY_SHURIKEN_ENTITY,
                RenderShuriken::new);
    }

    public static void init() {
        registerTinkerConstructEntityRenderers();
    }
}
