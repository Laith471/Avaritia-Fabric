package net.laith.avaritia.common.entity;

import com.google.common.base.Predicate;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.init.ModDamageTypes;
import net.laith.avaritia.init.ModEntities;
import net.laith.avaritia.init.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.*;

public class GapingVoidEntity extends Entity {

    public static final EntityDataAccessor<Integer> AGE_PARAMETER = SynchedEntityData.defineId(GapingVoidEntity.class, EntityDataSerializers.INT);
    public static final int maxLifetime = 186;
    public static double collapse = .95;
    public static double suckRange = 20.0;
    private FakePlayer fakePlayer;
    private LivingEntity user;
    public static final Predicate<Entity> SUCK_PREDICATE = input -> {
        if (input instanceof Player p) {
            return !p.isCreative() || !p.isFallFlying();
        }

        return true;
    };

    public static final Predicate<Entity> OMNOM_PREDICATE = input -> {
        if (!(input instanceof LivingEntity)) {
            return false;
        }

        if (input instanceof Player) {
            Player p = (Player) input;
            return !p.isCreative();
        }
        return true;
    };

    public GapingVoidEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noCulling = true;
        this.shouldRenderAtSqrDistance(100);
        if (getCommandSenderWorld() instanceof ServerLevel) {
            fakePlayer = FakePlayer.get((ServerLevel) getCommandSenderWorld(), AvaritiaMod.avaritiaFakePlayer);
        }
    }

    public GapingVoidEntity(Level level) {
        this(ModEntities.GAPING_VOID, level);
    }

    public GapingVoidEntity(Level level, LivingEntity shooter) {
        this(ModEntities.GAPING_VOID, level);
        this.setUser(shooter);
    }

    public void setUser(LivingEntity user) {
        this.user = user;
    }

    public static double getVoidScale(double age) {
        double life = age / (double) maxLifetime;

        double curve;
        if (life < collapse) {
            curve = 0.005 + ease(1 - ((collapse - life) / collapse)) * 0.995;
        } else {
            curve = ease(1 - ((life - collapse) / (1 - collapse)));
        }
        return 10.0 * curve;
    }



    private static double ease(double in) {
        double t = in - 1;
        return Math.sqrt(1 - t * t);
    }

    public int getAge() {
        return this.entityData.get(AGE_PARAMETER);
    }

    private void setAge(int age) {
        this.entityData.set(AGE_PARAMETER, age);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(AGE_PARAMETER, 0);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        setAge(tag.getInt("age"));
        if (getCommandSenderWorld() instanceof ServerLevel) {
            fakePlayer = FakePlayer.get((ServerLevel) getCommandSenderWorld(), AvaritiaMod.avaritiaFakePlayer);
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        nbt.putInt("age", getAge());
    }

    @Override
    public void tick() {
        super.tick();
        double posX = this.getX();
        double posY = this.getY();
        double posZ = this.getZ();
        BlockPos position = this.blockPosition();
        int age = getAge();

        if (age >= maxLifetime && !getCommandSenderWorld().isClientSide) {
            getCommandSenderWorld().explode(this, posX, posY, posZ, 6.0f, Level.ExplosionInteraction.BLOCK);
            int range = 4;
            AABB axisAlignedBB = new AABB(position.offset(-range, -range, -range), position.offset(range, range, range));
            List<Entity> nommed = getCommandSenderWorld().getEntitiesOfClass(Entity.class, axisAlignedBB, OMNOM_PREDICATE);
            nommed.stream()
                    .filter(entity -> entity != this)
                    .forEach(entity -> {
                        if (entity instanceof EnderDragon dragon) {
                            dragon.hurt(dragon.head, ModDamageTypes.of(user.getCommandSenderWorld(), ModDamageTypes.INFINITY), 1000.0f);
                            dragon.setHealth(0);
                        } else if (entity instanceof WitherBoss wither) {
                            wither.setInvulnerableTicks(0);
                            wither.hurt(ModDamageTypes.of(user.getCommandSenderWorld(), ModDamageTypes.INFINITY), 1000.0f);
                        } else entity.hurt(ModDamageTypes.of(user.getCommandSenderWorld(), ModDamageTypes.INFINITY), 1000.0f);
                    });

            remove(RemovalReason.KILLED);
        }
        else {
            if (age == 0) {
                getCommandSenderWorld().playSound(fakePlayer, getX(), getY(), getZ(), ModSounds.GAPING_VOID, SoundSource.HOSTILE, 8.0F, 1.0F);
            }
            setAge(age + 1);
        }

        double size = getVoidScale(age) * 0.5 - 0.2;
        for (int i = 0; i < 50; i++) {
            this.getCommandSenderWorld().addParticle(ParticleTypes.PORTAL, position.getX(), position.getY(), position.getZ(), this.random.nextGaussian() * 3,
                    this.random.nextGaussian() * 3, this.random.nextGaussian() * 3);
        }

        if (getCommandSenderWorld().isClientSide) {
            return;
        }
        if (fakePlayer == null) {
            remove(RemovalReason.KILLED);
            return;
        }

        // *slurping noises*

        int range = (int) (size * suckRange);
        AABB axisAlignedBB = new AABB(position.offset(-range, -range, -range), position.offset(range, range, range));

        List<Entity> sucked = getCommandSenderWorld().getEntitiesOfClass(Entity.class, axisAlignedBB, SUCK_PREDICATE);

        double radius = getVoidScale(age) * 0.5;

        for (Entity suckee : sucked) {
            if (suckee != this) {
                double dx = posX - suckee.getX();
                double dy = posY - suckee.getY();
                double dz = posZ - suckee.getZ();

                double lensquared = dx * dx + dy * dy + dz * dz;
                double len = Math.sqrt(lensquared);
                double lenn = len / suckRange;

                if (len <= suckRange) {
                    double strength = (1 - lenn) * (1 - lenn);
                    double power = 0.075 * radius;
                    Vec3 motion = suckee.getDeltaMovement();
                    double motionX = motion.x + (dx / len) * strength * power;
                    double motionY = motion.y + (dy / len) * strength * power;
                    double motionZ = motion.z + (dz / len) * strength * power;
                    suckee.setDeltaMovement(motionX, motionY, motionZ);

                }
            }
        }

        // om nom nom
        int nomrange = (int) (radius * 0.95);
        AABB alignedBB = new AABB(position.offset(-nomrange, -nomrange, -nomrange), position.offset(nomrange, nomrange, nomrange));
        List<Entity> nommed = getCommandSenderWorld().getEntitiesOfClass(Entity.class, alignedBB, OMNOM_PREDICATE);

        for (Entity nommee : nommed) {
            if (nommee != this) {
                Vec3 nomedPos = nommee.getLookAngle();
                Vec3 diff = this.getLookAngle().subtract(nomedPos);

                double len = diff.length();

                if (len <= nomrange) {
                    if (nommee instanceof EnderDragon dragon) {
                        dragon.hurt(dragon.head, this.damageSources().fellOutOfWorld(), 5.0f);
                    }
                    nommee.hurt(this.damageSources().fellOutOfWorld(), 5.0f);
                }
            }
        }

        // every half second, SMASH STUFF
        if (age % 10 == 0) {
            Vec3 posFloor = new Vec3(Math.floor(this.getX()), Math.floor(this.getY()), Math.floor(this.getZ()));
            int blockrange = Math.round(nomrange);

            for (int y = -blockrange; y <= blockrange; y++) {
                for (int z = -blockrange; z <= blockrange; z++) {
                    for (int x = -blockrange; x <= blockrange; x++) {
                        Vec3 pos2 = new Vec3(x, y, z);
                        Vec3 rPos = posFloor.add(pos2);
                        BlockPos blockPos = BlockPos.containing(rPos.x, rPos.y, rPos.z);

                        if(!this.getCommandSenderWorld().isOutsideBuildHeight(blockPos)) {
                            double dist = Math.sqrt(x * x + y * y + z * z);
                            BlockState state = this.getCommandSenderWorld().getBlockState(blockPos);
                            if (dist <= nomrange && !this.getCommandSenderWorld().getBlockState(blockPos).isAir()) {
                                Explosion explosion = new Explosion(this.getCommandSenderWorld(), this, this.getX(), this.getY(), this.getZ(), 10.0F, List.of(blockPos));
                                Block b = this.getCommandSenderWorld().getBlockState(blockPos).getBlock();
                                float resist = b.getExplosionResistance();
                                if (resist <= 10.0) {
                                    if(state.getBlock().dropFromExplosion(explosion)) {
                                        BlockEntity be = state.hasBlockEntity() ? this.getCommandSenderWorld().getBlockEntity(blockPos) : null;
                                        LootParams.Builder lootContextParameters = new LootParams.Builder((ServerLevel)this.getCommandSenderWorld()).withParameter(LootContextParams.THIS_ENTITY, this)
                                                .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(blockPos))
                                                .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                                                .withParameter(LootContextParams.BLOCK_ENTITY, be)
                                                .withParameter(LootContextParams.THIS_ENTITY, this)
                                                .withParameter(LootContextParams.EXPLOSION_RADIUS, 10.0F);

                                        Iterator<ItemStack> var = state.getDrops(lootContextParameters).iterator();

                                        while(var.hasNext()) {
                                            ItemStack drop = var.next();
                                            double xVelocity = this.getCommandSenderWorld().random.nextFloat() * 0.7 + (1.0D - 0.7) * 0.5D;
                                            double yVelocity = this.getCommandSenderWorld().random.nextFloat() * 0.7 + (1.0D - 0.7) * 0.5D;
                                            double zVelocity = this.getCommandSenderWorld().random.nextFloat() * 0.7 + (1.0D - 0.7) * 0.5D;
                                            ItemEntity entityItem = new ItemEntity(this.getCommandSenderWorld(), position().x() + xVelocity, position().y() + yVelocity, position().z() + zVelocity, drop);
                                            entityItem.setPickUpDelay(10);
                                            this.getCommandSenderWorld().addFreshEntity(entityItem);
                                        }
                                    }
                                    this.getCommandSenderWorld().setBlock(blockPos, Blocks.AIR.defaultBlockState(), 3);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    @Override
    public boolean fireImmune() {
        return true;
    }
}
