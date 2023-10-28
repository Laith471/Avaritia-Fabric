package net.laith.avaritia.common.entity;

import com.google.common.base.Predicate;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.init.ModDamageTypes;
import net.laith.avaritia.init.ModEntities;
import net.laith.avaritia.init.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class EntityGapingVoid extends Entity {

    public static final TrackedData<Integer> AGE_PARAMETER = DataTracker.registerData(EntityGapingVoid.class, TrackedDataHandlerRegistry.INTEGER);
    public static final int maxLifetime = 186;
    public static double collapse = .95;
    public static double suckRange = 20.0;
    private FakePlayer fakePlayer;
    private LivingEntity user;
    public static final Predicate<Entity> SUCK_PREDICATE = input -> {
        if (input instanceof PlayerEntity p) {
            return !p.isCreative() || !p.isFallFlying();
        }

        return true;
    };
    public static final Predicate<Entity> OMNOM_PREDICATE = input -> {
        if (!(input instanceof LivingEntity)) {
            return false;
        }

        if (input instanceof PlayerEntity p) {
            return !p.isCreative();
        } else return false;
    };

    public EntityGapingVoid(EntityType<?> type, World world) {
        super(type, world);
        this.ignoreCameraFrustum = true;
        this.shouldRender(100);
        if (getEntityWorld() instanceof ServerWorld) {
            fakePlayer = FakePlayer.get((ServerWorld) getEntityWorld(), AvaritiaMod.avaritiaFakePlayer);
        }
    }

    public EntityGapingVoid(World world) {
        this(ModEntities.GAPING_VOID, world);
    }

    public EntityGapingVoid(World world, LivingEntity shooter) {
        this(ModEntities.GAPING_VOID, world);
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
        return this.dataTracker.get(AGE_PARAMETER);
    }

    private void setAge(int age) {
        this.dataTracker.set(AGE_PARAMETER, age);
    }

    @Override
    protected void initDataTracker() {
        this.dataTracker.startTracking(AGE_PARAMETER, 0);
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        setAge(nbt.getInt("age"));
        if (getEntityWorld() instanceof ServerWorld) {
            fakePlayer = FakePlayer.get((ServerWorld) getEntityWorld(), AvaritiaMod.avaritiaFakePlayer);
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putInt("age", getAge());
    }

    @Override
    public void tick() {
        double posX = this.getX();
        double posY = this.getY();
        double posZ = this.getZ();
        BlockPos position = this.getBlockPos();
        int age = getAge();

        if (age >= maxLifetime && !getEntityWorld().isClient) {
            getEntityWorld().createExplosion(this, posX, posY, posZ, 6.0f, World.ExplosionSourceType.BLOCK);
            int range = 4;
            Box axisAlignedBB = new Box(position.add(-range, -range, -range), position.add(range, range, range));
            List<Entity> nommed = getEntityWorld().getEntitiesByClass(Entity.class, axisAlignedBB, OMNOM_PREDICATE);
            nommed.stream()
                    .filter(entity -> entity != this)
                    .forEach(entity -> {
                        if (entity instanceof EnderDragonEntity dragon) {
                            dragon.damagePart(dragon.head, ModDamageTypes.of(user.getEntityWorld(), ModDamageTypes.INFINITY), 4.0F);
                            dragon.setHealth(0);
                        } else if (entity instanceof WitherEntity wither) {
                            wither.setInvulTimer(0);
                            wither.damage(ModDamageTypes.of(user.getEntityWorld(), ModDamageTypes.INFINITY), 4.0F);
                        } else entity.damage(ModDamageTypes.of(user.getEntityWorld(), ModDamageTypes.INFINITY), 4.0F);
                    });
            remove(RemovalReason.KILLED);
        } else {
            if (age == 0) {
                getEntityWorld().playSound(fakePlayer, getX(), getY(), getZ(), ModSounds.GAPING_VOID, SoundCategory.HOSTILE, 8.0F, 1.0F);
            }
            setAge(age + 1);
        }

        double particlespeed = 4.5;
        double size = getVoidScale(age) * 0.5 - 0.2;
        for (int i = 0; i < 50; i++) {
            this.getEntityWorld().addParticle(ParticleTypes.PORTAL, position.getX(), position.getY(), position.getZ(), this.random.nextGaussian() * 3,
                    this.random.nextGaussian() * 3, this.random.nextGaussian() * 3);
        }

        if (getEntityWorld().isClient) {
            return;
        }
        if (fakePlayer == null) {
            remove(RemovalReason.KILLED);
            return;
        }

        // *slurping noises*

        int range = (int) (size * suckRange);
        Box axisAlignedBB = new Box(position.add(-range, -range, -range), position.add(range, range, range));

        List<Entity> sucked = getEntityWorld().getEntitiesByClass(Entity.class, axisAlignedBB, SUCK_PREDICATE);

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
                    Vec3d motion = suckee.getVelocity();
                    double motionX = motion.x + (dx / len) * strength * power;
                    double motionY = motion.y + (dy / len) * strength * power;
                    double motionZ = motion.z + (dz / len) * strength * power;
                    suckee.setVelocity(motionX, motionY, motionZ);

                }
            }
        }

        // om nom nom
        int nomrange = (int) (radius * 0.95);
        Box alignedBB = new Box(position.add(-nomrange, -nomrange, -nomrange), position.add(nomrange, nomrange, nomrange));
        List<Entity> nommed = getEntityWorld().getEntitiesByClass(Entity.class, alignedBB, OMNOM_PREDICATE);

        for (Entity nommee : nommed) {
            if (nommee != this) {
                Vec3d nomedPos = nommee.getRotationVector();
                Vec3d diff = this.getRotationVector().subtract(nomedPos);

                double len = diff.length();

                if (len <= nomrange) {
                    if (nommee instanceof EnderDragonEntity dragon) {
                        dragon.damagePart(dragon.head, this.getDamageSources().outOfWorld(), 5.0f);
                    }
                    nommee.damage(this.getDamageSources().outOfWorld(), 5.0f);
                }
            }
        }

        // every half second, SMASH STUFF
        if (age % 10 == 0) {
            Vec3d posFloor = this.getPos();
            int bx = (int) Math.floor(posX);
            int by = (int) Math.floor(posY);
            int bz = (int) Math.floor(posZ);

            int blockrange = Math.round(nomrange);
            int lx, ly, lz;

            for (int y = -blockrange; y <= blockrange; y++) {
                for (int z = -blockrange; z <= blockrange; z++) {
                    for (int x = -blockrange; x <= blockrange; x++) {
                        lx = bx + x;
                        ly = by + y;
                        lz = bz + z;
                        Vec3d pos2 = new Vec3d(x, y, z);
                        Vec3d rPos = posFloor.add(pos2);
                        BlockPos blockPos = BlockPos.ofFloored(rPos.x, rPos.y, rPos.z);

                        if (ly < 0 || ly > 255) {
                            double dist = Math.sqrt(x * x + y * y + z * z);
                            if (dist <= nomrange && !this.getEntityWorld().getBlockState(blockPos).isAir()) {
                                Block b = this.getEntityWorld().getBlockState(blockPos).getBlock();
                                BlockState blockState = this.getEntityWorld().getBlockState(blockPos);
                                float resist = b.getBlastResistance();
                                if (resist <= 10.0) {
                                    this.getEntityWorld().setBlockState(blockPos, Blocks.AIR.getDefaultState());
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

    @Override
    public boolean isFireImmune() {return true;}


}
