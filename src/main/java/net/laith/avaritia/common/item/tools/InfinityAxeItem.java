package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.init.ModTags;
import net.laith.avaritia.util.ToolHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class InfinityAxeItem extends MiningToolItem {

    public InfinityAxeItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.INFINITY_AXE, settings);
    }

    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if(isLeafOrLog(state)) {
            mineTree(pos, world, state, (PlayerEntity) miner);
        } else {

        }
        return super.postMine(stack, world, state, pos, miner);
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

                            if (offsetState.isIn(ModTags.Blocks.INFINITY_AXE)) {
                                ToolHelper.dropItems(offsetPos, serverWorld, world, user, stack, offsetState);
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
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }
    @Override
    public boolean damage(DamageSource source) {
        return false;
    }
}