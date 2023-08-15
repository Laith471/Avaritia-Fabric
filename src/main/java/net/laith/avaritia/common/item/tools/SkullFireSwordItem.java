package net.laith.avaritia.common.item.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.*;

public class SkullFireSwordItem extends SwordItem {

    public SkullFireSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(ToolMaterials.DIAMOND, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target instanceof SkeletonEntity || target instanceof WitherSkeletonEntity) {

            if(target.isDead()) {
                target.dropItem(Items.WITHER_SKELETON_SKULL);
            }
        }
        return super.postHit(stack, target, attacker);
    }
}
