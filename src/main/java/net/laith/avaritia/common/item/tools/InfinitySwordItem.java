package net.laith.avaritia.common.item.tools;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class InfinitySwordItem extends SwordItem {


    public InfinitySwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity) {
            EnderDragonEntity enderDragon = (EnderDragonEntity) target;
            if (!enderDragon.isInvulnerable()) {
                enderDragon.setHealth(0.0F);
                return true;
            }
        }

        // For other entities, set their health to zero
        target.setHealth(0.0F);

        return true;
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
}

