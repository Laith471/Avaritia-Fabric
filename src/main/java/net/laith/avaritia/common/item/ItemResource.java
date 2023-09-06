package net.laith.avaritia.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemResource extends Item {
    public String translatable;
    public boolean isDamageable;

    public ItemResource(Settings settings, String translatable, boolean isDamageable) {
        super(settings);
        this.translatable = translatable;
        this.isDamageable = isDamageable;
    }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List< Text > tooltip, TooltipContext context) {
            super.appendTooltip(stack, world, tooltip, context);
            tooltip.add(Text.translatable(translatable));
        }

    @Override
    public boolean damage(DamageSource source) {
        if(!isDamageable) {
            return false;
        } else {
            return super.damage(source);
        }
    }
}
