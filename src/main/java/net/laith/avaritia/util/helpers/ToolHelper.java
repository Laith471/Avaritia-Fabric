package net.laith.avaritia.util.helpers;

import net.laith.avaritia.common.blockentity.MatterClusterBlockEntity;
import net.laith.avaritia.init.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ToolHelper {

    public static void setItemsInMatterCluster(BlockPos offsetPos, ServerLevel serverLevel, List<ItemStack> drops, Player player, boolean inUseMethod) {
        BlockState matterClusterState = ModBlocks.MATTER_CLUSTER.defaultBlockState();

        // Set the Matter Cluster block in the world
        serverLevel.setBlockAndUpdate(player.blockPosition(), matterClusterState);

        // Get the newly placed Matter Cluster block entity
        BlockEntity blockEntity = serverLevel.getBlockEntity(player.blockPosition());
        if (blockEntity instanceof MatterClusterBlockEntity) {
            MatterClusterBlockEntity matterCluster = (MatterClusterBlockEntity) blockEntity;

            for (ItemStack item : drops) {
                for (int slot = 0; slot < matterCluster.getContainerSize(); slot++) {
                    ItemStack currentStack = matterCluster.getItem(slot);
                    if (currentStack.isEmpty()) {
                        matterCluster.setItem(slot, item.copy());
                        break;
                    } else if (ItemStack.isSameItem(currentStack, item) && ItemStack.isSameItem(currentStack, item)) {
                        int space = currentStack.getMaxStackSize() - currentStack.getCount();
                        if (space > 0) {
                            int transfer = Math.min(space, item.getCount());
                            currentStack.grow(transfer);
                            item.shrink(transfer);
                            if (item.isEmpty()) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (inUseMethod) {
            serverLevel.setBlock(offsetPos, Blocks.AIR.defaultBlockState(), 3); // Clear the block
        }
    }

    public static void mineCube(Player player, Level level, TagKey<Block> tag) {

        Direction facingDirection = player.getDirection();

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

        BlockPos playerPos = player.blockPosition();
        Direction lookDirection = player.getDirection();
        float pitch = player.getXRot();

        // Calculate the starting position of the cube to be mined
        int startX = playerPos.getX() + (lookDirection.getStepX() * 4) - (cubeWidth / 2);
        int startY = playerPos.getY() + 0;
        int startZ = playerPos.getZ() + (lookDirection.getStepZ() * 4) - (cubeWidth / 2);


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
                    BlockState targetState = level.getBlockState(targetPos);

                    if (targetState.is(tag)) {
                        level.destroyBlock(targetPos, false, player);
                        List<ItemStack> drops = Block.getDrops(targetState, (ServerLevel) level, targetPos, null, player, null);
                        setItemsInMatterCluster(targetPos, (ServerLevel) level, drops, player,  false);
                    }
                }
            }
        }
    }
    public static boolean isLeafOrLog(BlockState state) {

        if(state.is(BlockTags.LOGS) || state.is(BlockTags.LEAVES)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void mineTree(BlockPos pos, Level world, BlockState state, Player player) {
        if (isLeafOrLog(state)) {
            // Break the current block
            world.destroyBlock(pos, true, player);

            // Break neighboring blocks recursively
            for (int xOffset = -1; xOffset <= 1; xOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        BlockPos neighborPos = pos.offset(xOffset, yOffset, zOffset);
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
