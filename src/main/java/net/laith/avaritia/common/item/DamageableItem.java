package net.laith.avaritia.common.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DamageableItem extends Item {
    public String translatable;

    public DamageableItem(Settings settings, String translatable) {
        super(settings);
        this.translatable = translatable;
    }

        @Override
        public void appendTooltip(ItemStack stack, @Nullable World world, List< Text > tooltip, TooltipContext context) {
            super.appendTooltip(stack, world, tooltip, context);
            tooltip.add(Text.translatable(translatable));
        }
}
