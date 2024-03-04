package net.laith.avaritia.common.item;

import net.laith.avaritia.init.ModTextures;
import net.laith.avaritia.util.render.IHaloRenderItem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemResource extends Item implements IHaloRenderItem {
    public String translatable;
    public int i;
    public boolean isDamageable;

    public ItemResource(Properties properties, String translatable, boolean isDamageable, int i) {
        super(properties);
        this.i = i;
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

    @Override
    public boolean shouldDrawHalo(ItemStack stack) {
        return i > 2;
    }

    @Override
    public ResourceLocation getHaloTexture(ItemStack stack) {
        if(i == 3 || i == 4) {
                return ModTextures.HALO_NOISE_MMM;
        }
        return ModTextures.HALO;
    }

    @Override
    public int getHaloColour(ItemStack stack) {
        if (i == 3) {
            return 0x4DFFFFFF;
        }
        if (i == 4) {
            return 0x99FFFFFF;
        }
        return 0xFF000000;
    }

    @Override
    public int getHaloSize(ItemStack stack) {
        if(i == 5 || i == 6) {
            return 10;
        }
        return 8;
    }

    @Override
    public boolean shouldDrawPulse(ItemStack stack) {
        return i == 5 || i == 6;
    }

    @Override
    public boolean isItNeutron(ItemStack stack) {
        return i == 3 || i == 4;
    }
}
