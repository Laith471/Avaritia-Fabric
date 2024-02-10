package net.laith.avaritia.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemResource extends Item {
    public String translatable;
    public boolean isDamageable;

    public ItemResource(Properties properties, String translatable, boolean isDamageable) {
        super(properties);
        this.translatable = translatable;
        this.isDamageable = isDamageable;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable(translatable));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        if(!isDamageable) {
            return false;
        } else {
            return super.canBeHurtBy(source);
        }
    }
}
