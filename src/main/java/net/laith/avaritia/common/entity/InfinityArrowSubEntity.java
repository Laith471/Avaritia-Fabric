package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModProjectiles;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;

public class InfinityArrowSubEntity extends PersistentProjectileEntity {

    public InfinityArrowSubEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setDamage(9999);
    }

    public InfinityArrowSubEntity(double x, double y, double z, World world) {
        super(ModProjectiles.INFINITY_ARROW_SUB_ENTITY, x, y, z, world);
        this.setDamage(9999);
    }

    public InfinityArrowSubEntity(LivingEntity owner, World world) {
        super(ModProjectiles.INFINITY_ARROW_SUB_ENTITY, owner, world);
        this.setDamage(9999);
    }

    @Override
    protected ItemStack asItemStack() {
        return Items.ARROW.getDefaultStack();
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);

        if (!(target instanceof LivingEntity)) {
            return;
        } else {

            if (target instanceof EnderDragonEntity enderDragon) {
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
        if (this.getWorld().isClient) {
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
                this.getWorld().addParticle(ParticleTypes.CRIT, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
            }

        }
    }
}
