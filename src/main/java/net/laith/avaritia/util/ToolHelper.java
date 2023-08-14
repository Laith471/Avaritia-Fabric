package net.laith.avaritia.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class ToolHelper {

    public static void dropItems(BlockPos offsetPos, ServerWorld serverWorld, World world, PlayerEntity user, ItemStack stack, BlockState offsetState) {
        List<ItemStack> drops = Block.getDroppedStacks(offsetState, serverWorld, offsetPos, null, user, stack);
        for (ItemStack drop : drops) {
            ItemEntity itemEntity = new ItemEntity(world, offsetPos.getX() + 0.5, offsetPos.getY() + 0.5, offsetPos.getZ() + 0.5, drop);
            serverWorld.addEntities(itemEntity.streamPassengersAndSelf());
        }
        world.setBlockState(offsetPos, Blocks.AIR.getDefaultState(), 3); // Clear the block
    }

    public static void mineCube(PlayerEntity player, World world) {
        // Get the player's position and look direction
        Direction facingDirection = player.getHorizontalFacing();

        // Check if the player is facing north
        boolean facingNorth = facingDirection == Direction.NORTH;

        // Check if the player is facing east
        boolean facingEast = facingDirection == Direction.EAST;

        // Check if the player is facing south
        boolean facingSouth = facingDirection == Direction.SOUTH;

        // Check if the player is facing west
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
        }

        for (int xOffset = 0; xOffset < cubeWidth; xOffset++) {
            for (int yOffset = 0; yOffset < cubeHeight; yOffset++) {
                for (int zOffset = 0; zOffset < cubeWidth; zOffset++) {
                    BlockPos targetPos = new BlockPos((startX + xOffset) + xRearranger, (startY + yOffset) - heightRearranger, (startZ + zOffset) - widthRearranger + zRearranger);
                    BlockState targetState = world.getBlockState(targetPos);

                    if (targetState.isIn(BlockTags.PICKAXE_MINEABLE)) {
                        world.breakBlock(targetPos, true, player);
                    }
                }
            }
        }
    }
}

