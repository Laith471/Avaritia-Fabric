package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.init.ModDamageTypes;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class InfinitySwordItem extends SwordItem {


    public InfinitySwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker.getWorld().isClient) {
            return true;
        }

        if(target instanceof PlayerEntity player) {
                if(BooleanHelper.isWearingTheFullArmor(player)) {
                    target.damage(attacker.getDamageSources().create(ModDamageTypes.INFINITY, attacker), 4.0f);
                }
            }

        else if (target instanceof EnderDragonEntity enderDragon) {
            if (!enderDragon.isInvulnerable()) {
                enderDragon.setHealth(0.0F);
                return true;
            }
        }

        else if(target.isAlive()) {
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

}

