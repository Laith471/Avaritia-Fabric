package net.laith.avaritia.mixin.events;

import net.laith.avaritia.util.events.JumpEvent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)

public class LivingEntityMixin {

    @Inject(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(DDD)V", shift = At.Shift.AFTER))
    public void onJump(CallbackInfo ci) {
        JumpEvent.ON_NORMAL.invoker().OnNormal((LivingEntity)(Object)this);
        }


    @Inject(method = "jumpFromGround", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V", shift = At.Shift.AFTER))
    public void onSprintJump(CallbackInfo ci) {
        JumpEvent.ON_SPRINT.invoker().OnSprint((LivingEntity)(Object)this);
    }
}
