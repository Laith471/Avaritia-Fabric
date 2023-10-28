package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import java.util.Random;

public class InfinityArrowEntity extends PersistentProjectileEntity {
    public Random randy = new Random();

    public InfinityArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.setDamage(9999);
    }

    public InfinityArrowEntity(double x, double y, double z, World world) {
        super(ModEntities.INFINITY_ARROW_ENTITY, x, y, z, world);
        this.setDamage(9999);
    }

    public InfinityArrowEntity(LivingEntity owner, World world) {
        super(ModEntities.INFINITY_ARROW_ENTITY, owner, world);
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
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
            for (int i=0; i<35; i++) {
                double angle = randy.nextDouble() * 2 * Math.PI;
                double dist = randy.nextGaussian()*0.75;

                double x = Math.sin(angle) * dist + this.prevX;
                double z = Math.cos(angle) * dist + this.prevZ;
                double y = this.prevY + 25.0;

                double dangle = randy.nextDouble() * 2 * Math.PI;
                double ddist = randy.nextDouble()*0.35;
                double dx = Math.sin(dangle) * ddist;
                double dz = Math.cos(dangle) * ddist;

                InfinityArrowSubEntity arrow = new InfinityArrowSubEntity(x,y,z, this.getWorld());
                arrow.setVelocity(dx, -(randy.nextDouble()* 1.85 + 0.15), dz);
                arrow.setCritical(true);

                this.getWorld().spawnEntity(arrow);
        }
    }

    public static PersistentProjectileEntity createArrow(World world, LivingEntity shooter) {
        return new InfinityArrowEntity(shooter, world);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.inGroundTime == 5) {
            this.kill();
        }
    }
}
