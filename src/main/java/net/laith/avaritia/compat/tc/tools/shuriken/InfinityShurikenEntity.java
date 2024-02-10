package net.laith.avaritia.compat.tc.tools.shuriken;

import net.laith.avaritia.compat.tc.TC;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import slimeknights.tconstruct.gadgets.entity.shuriken.ShurikenEntityBase;

public class InfinityShurikenEntity extends ShurikenEntityBase {

    public InfinityShurikenEntity(EntityType<? extends InfinityShurikenEntity> type, Level worldIn) {
        super(type, worldIn);
    }

    public InfinityShurikenEntity(Level worldIn, LivingEntity throwerIn) {
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
    protected void onHitBlock(BlockHitResult result) {
        BlockState blockState = this.getCommandSenderWorld().getBlockState(result.getBlockPos());
        blockState.onProjectileHit(this.getCommandSenderWorld(), blockState, result, this);
        if(!getCommandSenderWorld().isClientSide) {
            getCommandSenderWorld().explode(this, this.getX(), this.getY(), this.getZ(), 1.0f, Level.ExplosionInteraction.BLOCK);
        }
    }
}
