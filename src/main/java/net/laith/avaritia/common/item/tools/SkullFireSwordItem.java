package net.laith.avaritia.common.item.tools;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkullFireSwordItem extends SwordItem {

    public SkullFireSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(Tiers.DIAMOND, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof Skeleton || target instanceof WitherSkeleton) {

            if (target.isDeadOrDying()) {
                target.spawnAtLocation(Items.WITHER_SKELETON_SKULL);
            }
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable("item.avaritia.skullfire_sword.tooltip"));
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced);
    }
}
