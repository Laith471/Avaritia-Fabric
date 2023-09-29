package net.laith.avaritia.mixin;

import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {

    @Inject(method = "isPlayerStaring", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getInventory()Lnet/minecraft/entity/player/PlayerInventory;"), cancellable = true)
    public void wearingHelmet(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        if(BooleanHelper.isWearingHelmet(player)) {
            cir.setReturnValue(false);
        }
    }
}
