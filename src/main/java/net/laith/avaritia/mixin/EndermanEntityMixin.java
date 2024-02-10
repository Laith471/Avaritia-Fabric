package net.laith.avaritia.mixin;

import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderMan.class)
public class EndermanEntityMixin {

    @Inject(method = "isLookingAtMe", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;getInventory()Lnet/minecraft/world/entity/player/Inventory;"), cancellable = true)
    public void wearingHelmet(Player player, CallbackInfoReturnable<Boolean> cir) {
        if(BooleanHelper.isWearingHelmet(player)) {
            cir.setReturnValue(false);
        }
    }
}
