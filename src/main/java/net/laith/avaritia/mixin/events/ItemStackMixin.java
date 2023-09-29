package net.laith.avaritia.mixin.events;

import net.laith.avaritia.util.events.TooltipEvent;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)

public class ItemStackMixin {

    @Inject(method = "getTooltip", at = @At("TAIL"))
    public void onJump(@Nullable PlayerEntity player, TooltipContext context, CallbackInfoReturnable<List<Text>> cir) {
        TooltipEvent.ON_EQUIPMENT_SLOT.invoker().OnEquipmentSlot((ItemStack)(Object)this, cir.getReturnValue());
        }
}
