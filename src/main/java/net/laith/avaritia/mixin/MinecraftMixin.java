package net.laith.avaritia.mixin;

import net.laith.avaritia.util.render.AnimationTimer;
import net.laith.avaritia.util.render.Timer;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin implements AnimationTimer {

    @Unique
    private Timer timer = new Timer(new float[]{0, 0.125f, 0.25f, 0.375f, 0.5f, 0.625f, 0.75f, 0.875f}, 0);

    @Inject(method = "tick", at = @At("TAIL"))
    public void animationTick(CallbackInfo ci) {
        timer.animationTimer();
    }

    @Override
    public float timer() {
        return timer.getX();
    }
}
