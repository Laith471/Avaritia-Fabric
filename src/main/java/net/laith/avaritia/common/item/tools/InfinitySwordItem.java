package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.init.ModDamageTypes;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class InfinitySwordItem extends SwordItem {
    public InfinitySwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(attacker.getCommandSenderWorld().isClientSide) {
            return true;
        }

        if(target instanceof Player player) {
            if(BooleanHelper.isWearingTheFullArmor(player)) {
                target.hurt(attacker.damageSources().source(ModDamageTypes.INFINITY, attacker), 4.0f);
            }
        }

        else if (target instanceof EnderDragon enderDragon) {
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
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
    }

}

