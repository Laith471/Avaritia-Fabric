package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModEntities;
import net.laith.avaritia.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EndestPearlEntity extends ThrowableItemProjectile {
    private LivingEntity shooter;

    public EndestPearlEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level level) {
        super(entityType, level);
    }


    public EndestPearlEntity(Level level, double x, double y, double z) {
        this(ModEntities.ENDEST_PEARL, level);
        setPos(x, y, z);
    }

    public EndestPearlEntity(Level level, LivingEntity shooter) {
        this(ModEntities.ENDEST_PEARL, level);
        this.setShooter(shooter);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ENDEST_PEARL;
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItem();
        return itemstack.isEmpty() ? ParticleTypes.PORTAL : new ItemParticleOption(ParticleTypes.ITEM, itemstack);
    }

    public void setShooter(LivingEntity shooter) {
        this.shooter = shooter;
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for (int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();

        if (entity != null) {
            entity.hurt(this.damageSources().thrown(this, getOwner()), 0.0F);
        }

        if (!getCommandSenderWorld().isClientSide) {
            GapingVoidEntity ent;
            if (shooter != null) {
                ent = new GapingVoidEntity(getCommandSenderWorld(), shooter);

            } else ent = new GapingVoidEntity(getCommandSenderWorld());

            Direction dir = entity.getDirection();
            Vec3 offset = Vec3.ZERO;
            if (dir != null) {
                offset = new Vec3(dir.getStepX(), dir.getStepY(), dir.getStepZ());
            }
            if (shooter != null) {
                ent.setUser(shooter);
            }
            ent.moveTo(entity.getX() + offset.x * 0.25, entity.getY() + offset.y * 0.25, entity.getZ() + offset.z * 0.25, entity.getYRot(), 0.0F);
            getCommandSenderWorld().addFreshEntity(ent);

            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockPos pos = blockHitResult.getBlockPos();

        if (!getCommandSenderWorld().isClientSide) {

            GapingVoidEntity ent;
            if (shooter != null) {
                ent = new GapingVoidEntity(getCommandSenderWorld(), shooter);

            } else ent = new GapingVoidEntity(getCommandSenderWorld());
            Direction dir = blockHitResult.getDirection();
            Vec3 offset = Vec3.ZERO;
            if (dir != null) {
                offset = new Vec3(dir.getStepX(), dir.getStepY(), dir.getStepZ());
            }
            if (shooter != null) {
                ent.setUser(shooter);
            }
            ent.moveTo(pos.getX() + offset.x * 0.25, pos.getY() + offset.y * 0.25, pos.getZ() + offset.z * 0.25, getYRot(), 0.0F);
            getCommandSenderWorld().addFreshEntity(ent);

            remove(RemovalReason.KILLED);
        }
    }
}