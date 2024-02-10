package net.laith.avaritia.common.handler;

import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class InfinityHandler {

       private static final AbilitySource FLIGHT = Pal.getAbilitySource(AvaritiaMod.MOD_ID, "flight");

        public static void serverAbilities(Player player) {
            if (BooleanHelper.isWearingChestplate(player)) {
                FLIGHT.grantTo(player, VanillaAbilities.ALLOW_FLYING);
            } else {
                FLIGHT.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
            }
        }


        public static void clientAbilities(Player player) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        boolean flying = player.getAbilities().flying;
                        boolean swimming = player.isSwimming();
                        if (player.onGround() || flying || swimming) {
                            boolean sneaking = player.isShiftKeyDown();

                            float speed = 0.15f * (flying ? 1.1f : 1.0f)
                                    * (swimming ? 1.2f : 1.0f)
                                    * (sneaking ? 0.1f : 1.0f);

                            if (player.zza > 0f) {
                                player.moveRelative(speed, new Vec3(0, 0, 1));
                            } else if (player.zza < 0f) {
                                player.moveRelative(-speed * 0.3f, new Vec3(0, 0, 1));
                            }

                            if (player.xxa != 0f) {
                                player.moveRelative(speed * 0.5f * Math.signum(player.xxa), new Vec3(1, 0, 0));
                            }
                        }
                    }
        }
}

