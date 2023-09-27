package net.laith.avaritia.mixin;

import net.laith.avaritia.util.event.JumpCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.entity.LivingEntity.class)

public class LivingEntityMixin {

    @Inject(method = "jump()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V", shift = At.Shift.AFTER), cancellable = true)
    public void onJump(CallbackInfo ci) {
        ActionResult result = JumpCallback.ON_NORMAL.invoker().interact((LivingEntity)(Object) this);
        if(result == ActionResult.FAIL) {
            ci.cancel();
        }
    }

    @Inject(method = "jump()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", shift = At.Shift.AFTER), cancellable = true)
    public void onSprintJump(CallbackInfo ci) {
        ActionResult result = JumpCallback.ON_SPRINT.invoker().interact((LivingEntity)(Object) this);
        if(result == ActionResult.FAIL) {
            ci.cancel();
        }
    }
}
