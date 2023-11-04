package net.laith.avaritia.common.entity;

import net.laith.avaritia.init.ModEntities;
import net.laith.avaritia.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EndestPearlEntity extends ThrownItemEntity {
    private LivingEntity shooter;

    public EndestPearlEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }


    public EndestPearlEntity(World world, double x, double y, double z) {
        this(ModEntities.ENDEST_PEARL, world);
        setPos(x, y, z);
    }

    public EndestPearlEntity(World world, LivingEntity shooter) {
        this(ModEntities.ENDEST_PEARL, world);
        this.setShooter(shooter);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ENDEST_PEARL;
    }

    private ParticleEffect getParticle() {
        ItemStack itemstack = this.getItem();
        return itemstack.isEmpty() ? ParticleTypes.PORTAL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemstack);
    }

    public void setShooter(LivingEntity shooter) {
        this.shooter = shooter;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleoptions = this.getParticle();

            for (int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();

        if (entity != null) {
            entity.damage(this.getDamageSources().thrown(this, getOwner()), 0.0F);
        }

        if (!getEntityWorld().isClient) {
            GapingVoidEntity ent;
            if (shooter != null) {
                ent = new GapingVoidEntity(getEntityWorld(), shooter);

            } else ent = new GapingVoidEntity(getEntityWorld());

            Direction dir = entity.getHorizontalFacing();
            Vec3d offset = Vec3d.ZERO;
            if (dir != null) {
                offset = new Vec3d(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
            }
            if (shooter != null) {
                ent.setUser(shooter);
            }
            ent.refreshPositionAndAngles(entity.getX() + offset.x * 0.25, entity.getY() + offset.y * 0.25, entity.getZ() + offset.z * 0.25, entity.getYaw(), 0.0F);
            getEntityWorld().spawnEntity(ent);

            remove(RemovalReason.KILLED);
        }
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        BlockPos pos = blockHitResult.getBlockPos();

        if (!getEntityWorld().isClient) {

            GapingVoidEntity ent;
            if (shooter != null) {
                ent = new GapingVoidEntity(getEntityWorld(), shooter);

            } else ent = new GapingVoidEntity(getEntityWorld());
            Direction dir = blockHitResult.getSide();
            Vec3d offset = Vec3d.ZERO;
            if (dir != null) {
                offset = new Vec3d(dir.getOffsetX(), dir.getOffsetY(), dir.getOffsetZ());
            }
            if (shooter != null) {
                ent.setUser(shooter);
            }
            ent.refreshPositionAndAngles(pos.getX() + offset.x * 0.25, pos.getY() + offset.y * 0.25, pos.getZ() + offset.z * 0.25, getYaw(), 0.0F);
            getEntityWorld().spawnEntity(ent);

            remove(RemovalReason.KILLED);
        }
    }
}