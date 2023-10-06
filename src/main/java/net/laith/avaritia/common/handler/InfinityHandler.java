package net.laith.avaritia.common.handler;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class InfinityHandler {

       private static final AbilitySource FLIGHT = Pal.getAbilitySource(AvaritiaMod.MOD_ID, "flight");

        public static void serverAbilities(PlayerEntity player) {
            if (BooleanHelper.isWearingChestplate(player)) {
                FLIGHT.grantTo(player, VanillaAbilities.ALLOW_FLYING);
            } else {
                FLIGHT.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
            }
        }


        public static void clientAbilities(PlayerEntity player) {
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

