package net.laith.avaritia.compat.tc.tools.shuriken;

import net.laith.avaritia.compat.tc.TC;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;
import slimeknights.tconstruct.gadgets.entity.shuriken.ShurikenEntityBase;

public class InfinityShurikenEntity extends ShurikenEntityBase {

    public InfinityShurikenEntity(EntityType<? extends InfinityShurikenEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public InfinityShurikenEntity(World worldIn, LivingEntity throwerIn) {
        super(TC.INFINITY_SHURIKEN_ENTITY, throwerIn, worldIn);
    }

    @Override
    public float getDamage() {
        return Float.MAX_VALUE;
    }

    @Override
    public float getKnockback() {
        return 10;
    }

    @Override
    protected Item getDefaultItem() {
        return TC.INFINITY_SHURIKEN;
    }

    @Override
    protected void onBlockHit(BlockHitResult result) {
        BlockState blockState = this.getWorld().getBlockState(result.getBlockPos());
        blockState.onProjectileHit(this.getWorld(), blockState, result, this);
        if(!getEntityWorld().isClient) {
            getEntityWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 1.0f, World.ExplosionSourceType.BLOCK);
        }
    }
}
