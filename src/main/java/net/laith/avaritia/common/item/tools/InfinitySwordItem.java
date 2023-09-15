package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.util.TextUtil;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.item.TooltipData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class InfinitySwordItem extends SwordItem {


    public InfinitySwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity enderDragon) {
            if (!enderDragon.isInvulnerable()) {
                enderDragon.setHealth(0.0F);
                return true;
            }
        }

        if(target.isAlive()) {
            for(int h = 0; h < 100; h++) {
                target.setHealth(0);
            }
        }

        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        stack.addHideFlag(ItemStack.TooltipSection.MODIFIERS);
        tooltip.add(Text.of(" "));
        tooltip.add(Text.of(TextUtil.makeFabulous("+Infinite") + Formatting.GRAY + " Attack Damage"));
    }
}

