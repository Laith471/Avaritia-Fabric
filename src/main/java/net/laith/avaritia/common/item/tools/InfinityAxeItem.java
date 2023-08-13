package net.laith.avaritia.common.item.tools;

import io.netty.util.internal.IntegerHolder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Stream;

public class InfinityAxeItem extends AxeItem {

    public InfinityAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if(isLeafOrLog(state)) {
            mineTree(pos, world, state, (PlayerEntity) miner);
        } else {

        }
        return super.postMine(stack, world, state, pos, miner);
    }

    public void mineTree(BlockPos pos, World world, BlockState state, PlayerEntity player) {
        if (isLeafOrLog(state)) {
            // Break the current block
            world.breakBlock(pos, true, player);

            // Break neighboring blocks recursively
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        BlockPos neighborPos = pos.add(xOffset, yOffset, zOffset);
                        BlockState neighborState = world.getBlockState(neighborPos);

                        if (isLeafOrLog(neighborState)) {
                            mineTree(neighborPos, world, neighborState, player);
                        }
                    }
                }
            }
        }
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        BlockPos targetPos = user.getBlockPos();

        if (user.isSneaking()) {
            user.swingHand(hand);

            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;

                for (int xOffset = -13; xOffset <= 12; xOffset++) {
                    for (int yOffset = 0; yOffset <= 25; yOffset++) {
                        for (int zOffset = -13; zOffset <= 12; zOffset++) {
                            BlockPos offsetPos = targetPos.add(xOffset, yOffset, zOffset);
                            BlockState offsetState = world.getBlockState(offsetPos);

                            if (isLeafOrLog(offsetState)) {
                                List<ItemStack> drops = Block.getDroppedStacks(offsetState, serverWorld, offsetPos, null, user, stack);
                                for (ItemStack drop : drops) {
                                    ItemEntity itemEntity = new ItemEntity(world, offsetPos.getX() + 0.5, offsetPos.getY() + 0.5, offsetPos.getZ() + 0.5, drop);
                                    serverWorld.addEntities(itemEntity.streamPassengersAndSelf());
                                }
                                world.setBlockState(offsetPos, Blocks.AIR.getDefaultState(), 3); // Clear the block
                            }
                        }
                    }
                }
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS,  stack);
    }

    public boolean isLeafOrLog(BlockState state) {

        if(state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES)) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}