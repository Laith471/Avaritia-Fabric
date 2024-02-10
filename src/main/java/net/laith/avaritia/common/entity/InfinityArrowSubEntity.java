package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModEntities;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class InfinityArrowSubEntity extends AbstractArrow {

    public InfinityArrowSubEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
        this.setBaseDamage(9999);
    }

    public InfinityArrowSubEntity(double x, double y, double z, Level level) {
        super(ModEntities.INFINITY_ARROW_SUB_ENTITY, x, y, z, level);
        this.setBaseDamage(9999);
    }

    public InfinityArrowSubEntity(LivingEntity owner, Level level) {
        super(ModEntities.INFINITY_ARROW_SUB_ENTITY, owner, level);
        this.setBaseDamage(9999);
    }

    @Override
    protected ItemStack getPickupItem() {
        return Items.ARROW.getDefaultInstance();
    }

    @Override
    protected void doPostHurtEffects(LivingEntity target) {
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
    public void tick() {
        super.tick();
        if (this.inGroundTime == 20) {
            this.kill();
        }
        if (this.getCommandSenderWorld().isClientSide) {
            if(!this.inGround) {
                this.spawnParticles(2);
            }
        }
    }

    private void spawnParticles(int amount) {
        int i = 0;
        if (i != -1 && amount > 0) {
            double d = (double)(i >> 16 & 255) / 255.0;
            double e = (double)(i >> 8 & 255) / 255.0;
            double f = (double)(i >> 0 & 255) / 255.0;

            for(int j = 0; j < amount; ++j) {
                this.getCommandSenderWorld().addParticle(ParticleTypes.CRIT, this.getRandomX(0.5), this.getRandomY(), this.getRandomZ(0.5), d, e, f);
            }

        }
    }
}
