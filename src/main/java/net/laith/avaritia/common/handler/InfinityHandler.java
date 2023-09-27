package net.laith.avaritia.common.handler;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class InfinityHandler {

    public static class Server implements ServerTickEvents.StartTick, ServerLivingEntityEvents.AllowDamage{
        public void onServerTick(ServerPlayerEntity player) {
            // Check if the player is wearing the specific armor set
            boolean toggle = false;
            if (BooleanHelper.isWearingChestplate(player)) {
                player.getAbilities().allowFlying = true;
                player.sendAbilitiesUpdate();
                toggle = true;

            } else if (!player.isCreative() && !player.isSpectator() && toggle) {
                player.getAbilities().allowFlying = false;
                player.getAbilities().flying = false;
                toggle = false;
                player.sendAbilitiesUpdate();
            }
        }

        @Override
        public void onStartTick(MinecraftServer server) {
            for (ServerPlayerEntity player: server.getPlayerManager().getPlayerList()) {
                onServerTick(player);
            }
        }

        @Override
        public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
            if(entity instanceof PlayerEntity player) {
                if(BooleanHelper.isWearingTheFullArmor(player)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class Client implements ClientTickEvents.StartTick {

        @Override
        public void onStartTick(MinecraftClient client) {
                ClientPlayerEntity player = client.player;
                if (player != null) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        boolean flying = player.getAbilities().flying;
                        boolean swimming = player.isSwimming();
                        if (player.isOnGround() || flying || swimming) {
                            boolean sneaking = player.isSneaking();

                            float speed = 0.15f * (flying ? 1.1f : 1.0f)
                                    * (swimming ? 1.2f : 1.0f)
                                    * (sneaking ? 0.1f : 1.0f);

                            if (player.forwardSpeed > 0f) {
                                player.updateVelocity(speed, new Vec3d(0, 0, 1));
                            } else if (player.forwardSpeed < 0f) {
                                player.updateVelocity(-speed * 0.3f, new Vec3d(0, 0, 1));
                            }

                            if (player.sidewaysSpeed != 0f) {
                                player.updateVelocity(speed * 0.5f * Math.signum(player.sidewaysSpeed), new Vec3d(1, 0, 0));
                            }
                        }
                    }
                }
        }
    }
}

