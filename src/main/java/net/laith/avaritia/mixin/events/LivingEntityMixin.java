package net.laith.avaritia.mixin.events;

import net.laith.avaritia.util.events.JumpEvent;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.entity.LivingEntity.class)

public class LivingEntityMixin {

    @Inject(method = "jump()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(DDD)V", shift = At.Shift.AFTER))
    public void onJump(CallbackInfo ci) {
        JumpEvent.ON_NORMAL.invoker().OnNormal((LivingEntity)(Object)this);
        }


    @Inject(method = "jump()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V", shift = At.Shift.AFTER))
    public void onSprintJump(CallbackInfo ci) {
        JumpEvent.ON_SPRINT.invoker().OnSprint((LivingEntity)(Object)this);
    }
}
