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
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;

import java.util.List;

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
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (!entity.getWorld().isClient && entity instanceof PlayerEntity victim) {
            if (victim.isCreative() && !victim.isDead() && victim.getHealth() > 0 && !BooleanHelper.isWearingTheFullArmor(victim)) {
                victim.getDamageTracker().onDamage(user.getDamageSources().create(ModDamageTypes.INFINITY, user, victim), victim.getHealth());
                victim.setHealth(0);
                victim.onDeath(user.getDamageSources().create(ModDamageTypes.INFINITY, user, victim));
                return ActionResult.PASS;
            }
        }
        return ActionResult.FAIL;
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

