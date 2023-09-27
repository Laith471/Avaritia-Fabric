package net.laith.avaritia.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.client.render.EyeRenderer;
import net.laith.avaritia.client.render.WingRenderer;
import net.laith.avaritia.common.handler.InfinityHandler;
import net.laith.avaritia.util.event.JumpCallback;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class ModEvents {

    public static class Server {
        public static void registerServer() {
            ServerTickEvents.START_SERVER_TICK.register(new InfinityHandler.Server());

            ServerLivingEntityEvents.ALLOW_DAMAGE.register(new InfinityHandler.Server());

        }
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void registerEvents() {

            ClientTickEvents.START_CLIENT_TICK.register(new InfinityHandler.Client());

            LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
                if(entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                    registrationHelper.register(new WingRenderer<>(playerEntityRenderer));
                    registrationHelper.register(new EyeRenderer(playerEntityRenderer));
                }
            });

            JumpCallback.ON_NORMAL.register(entity -> {
                if(entity.getWorld().isClient && entity instanceof PlayerEntity player) {
                    if(BooleanHelper.isWearingBoots(player)) {
                        player.addVelocity(0, 0.5,0);
                    }
                }
                return ActionResult.PASS;
            });

            JumpCallback.ON_SPRINT.register(entity -> {
                if(entity.getWorld().isClient && entity instanceof PlayerEntity player) {
                    if(BooleanHelper.isWearingBoots(player)) {
                        player.addVelocity(0, 0.08,0);
                    }
                }
                return ActionResult.PASS;
            });
        }
    }
}