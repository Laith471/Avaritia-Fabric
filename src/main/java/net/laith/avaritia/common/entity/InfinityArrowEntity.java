package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModEntities;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;

public class InfinityArrowEntity extends AbstractArrow {
    public Random randy = new Random();

    public InfinityArrowEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.setBaseDamage(9999);
    }

    public InfinityArrowEntity(double x, double y, double z, Level level) {
        super(ModEntities.INFINITY_ARROW_ENTITY, x, y, z, level);
        this.setBaseDamage(9999);
    }

    public InfinityArrowEntity(LivingEntity owner, Level level) {
        super(ModEntities.INFINITY_ARROW_ENTITY, owner, level);
        this.setBaseDamage(9999);
    }

    @Override
    protected ItemStack getPickupItem() {
        return Items.ARROW.getDefaultInstance();
    }

    @Override
    protected void doPostHurtEffects (LivingEntity target) {
        super.doPostHurtEffects(target);

        if (!(target instanceof LivingEntity)) {
            return;
        } else {

            if (target instanceof EnderDragon enderDragon) {
                if (!enderDragon.isInvulnerable()) {
                    enderDragon.setHealth(0.0F);
                }
            }

            if(target.isAlive()) {
                for(int h = 0; h < 100; h++) {
                    target.setHealth(0);
                }
            }
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
            for (int i=0; i<35; i++) {
                double angle = randy.nextDouble() * 2 * Math.PI;
                double dist = randy.nextGaussian()*0.75;

                double x = Math.sin(angle) * dist + this.xo;
                double z = Math.cos(angle) * dist + this.zo;
                double y = this.yo + 25.0;

                double dangle = randy.nextDouble() * 2 * Math.PI;
                double ddist = randy.nextDouble()*0.35;
                double dx = Math.sin(dangle) * ddist;
                double dz = Math.cos(dangle) * ddist;

                InfinityArrowSubEntity arrow = new InfinityArrowSubEntity(x,y,z, this.getCommandSenderWorld());
                arrow.setDeltaMovement(dx, -(randy.nextDouble()* 1.85 + 0.15), dz);
                arrow.setCritArrow(true);

                this.getCommandSenderWorld().addFreshEntity(arrow);
        }
    }

    public static AbstractArrow createArrow(Level level, LivingEntity shooter) {
        return new InfinityArrowEntity(shooter, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGroundTime == 5) {
            this.kill();
        }
    }
}
