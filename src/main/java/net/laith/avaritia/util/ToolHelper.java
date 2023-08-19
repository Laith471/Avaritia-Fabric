package net.laith.avaritia.util;

import net.laith.avaritia.common.blockentity.MatterClusterBlockEntity;
import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class ToolHelper {

    public static void setItemsInMatterCluster(BlockPos offsetPos, ServerWorld serverWorld, List<ItemStack> drops, PlayerEntity player, boolean inUseMethod) {
        BlockState matterClusterState = ModBlocks.MATTER_CLUSTER.getDefaultState();

        // Set the Matter Cluster block in the world
        serverWorld.setBlockState(player.getBlockPos(), matterClusterState);

        // Get the newly placed Matter Cluster block entity
        BlockEntity blockEntity = serverWorld.getBlockEntity(player.getBlockPos());
        if (blockEntity instanceof MatterClusterBlockEntity) {
            MatterClusterBlockEntity matterCluster = (MatterClusterBlockEntity) blockEntity;

            for (ItemStack item : drops) {
                for (int slot = 0; slot < matterCluster.size(); slot++) {
                    ItemStack currentStack = matterCluster.getStack(slot);
                    if (currentStack.isEmpty()) {
                        matterCluster.setStack(slot, item.copy());
                        break;
                    } else if (ItemStack.areItemsEqual(currentStack, item) && ItemStack.areItemsEqual(currentStack, item)) {
                        int space = currentStack.getMaxCount() - currentStack.getCount();
                        if (space > 0) {
                            int transfer = Math.min(space, item.getCount());
                            currentStack.increment(transfer);
                            item.decrement(transfer);
                            if (item.isEmpty()) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (inUseMethod == true) {
            serverWorld.setBlockState(offsetPos, Blocks.AIR.getDefaultState(), 3); // Clear the block
        }
    }

    public static void mineCube(PlayerEntity player, World world, TagKey<Block> tag) {

        Direction facingDirection = player.getHorizontalFacing();

        boolean facingNorth = facingDirection == Direction.NORTH;
        boolean facingEast = facingDirection == Direction.EAST;
        boolean facingSouth = facingDirection == Direction.SOUTH;
        boolean facingWest = facingDirection == Direction.WEST;

        int cubeWidth = 16;
        int cubeHeight = 9;
        int heightRearranger = 0;
        int widthRearranger = 0;
        int xRearranger = 0;
        int zRearranger = 0;

        BlockPos playerPos = player.getBlockPos();
        Direction lookDirection = player.getHorizontalFacing();
        float pitch = player.getPitch();

        // Calculate the starting position of the cube to be mined
        int startX = playerPos.getX() + (lookDirection.getOffsetX() * 4) - (cubeWidth / 2);
        int startY = playerPos.getY() + 0;
        int startZ = playerPos.getZ() + (lookDirection.getOffsetZ() * 4) - (cubeWidth / 2);


        // Calculate the cube's dimensions based on the pitch angle
        if (pitch > 60) {
            heightRearranger = cubeHeight;
            widthRearranger = (cubeWidth / 2);
            if (facingNorth) {
                zRearranger = 12;
            } else if (facingEast) {
                zRearranger = 8;
                xRearranger = -3;
            } else if (facingSouth) {
                zRearranger = 4;
            } else if (facingWest) {
                zRearranger = 8;
                xRearranger = 4;
            }
        } else if (pitch < -60) {
            if (facingNorth) {
                zRearranger = 4;
            } else if (facingEast) {
                xRearranger = -3;
            } else if (facingSouth) {
                zRearranger = -3;
            } else if (facingWest) {
                xRearranger = 4;
            }
        }


        for (int xOffset = 0; xOffset < cubeWidth; xOffset++) {
            for (int yOffset = 0; yOffset < cubeHeight; yOffset++) {
                for (int zOffset = 0; zOffset < cubeWidth; zOffset++) {
                    BlockPos targetPos = new BlockPos((startX + xOffset) + xRearranger, (startY + yOffset) - heightRearranger, (startZ + zOffset) - widthRearranger + zRearranger);
                    BlockState targetState = world.getBlockState(targetPos);

                    if (targetState.isIn(tag)) {
                        world.breakBlock(targetPos, false, player);
                        List<ItemStack> drops = Block.getDroppedStacks(targetState, (ServerWorld) world, targetPos, null, player, null);
                        setItemsInMatterCluster(targetPos, (ServerWorld) world, drops, player,  false);
                    }
                }
            }
        }
    }
    public static boolean isLeafOrLog(BlockState state) {

        if(state.isIn(BlockTags.LOGS) || state.isIn(BlockTags.LEAVES)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void mineTree(BlockPos pos, World world, BlockState state, PlayerEntity player) {
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
}
