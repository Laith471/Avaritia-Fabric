package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.init.ModTags;
import net.laith.avaritia.util.helpers.ToolHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfinityPickaxe extends MiningToolItem {

    public InfinityPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(attackDamage, attackSpeed, material, ModTags.Blocks.INFINITY_PICKAXE, settings);
    }


    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) {
            NbtCompound tags = stack.getOrCreateNbt();
            if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) < 10) {
                stack.addEnchantment(Enchantments.FORTUNE, 10);
            }
            tags.putBoolean("hammer", !tags.getBoolean("hammer"));
            user.setMainArm(Arm.RIGHT);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return super.use(world, user, hand);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getOrCreateNbt().getBoolean("hammer")) {
            if (!(target instanceof PlayerEntity)) {
                int i = 10;
                target.addVelocity(-MathHelper.sin(attacker.getYaw() * (float) Math.PI / 180.0F) * i * 0.5F, 2.0D, MathHelper.cos(attacker.getYaw() * (float) Math.PI / 180.0F) * i * 0.5F);
            }
        }
        return true;
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (stack.getOrCreateNbt().getBoolean("hammer")) {
            if (state.isIn(ModTags.Blocks.INFINITY_PICKAXE)) {
                ToolHelper.mineCube((PlayerEntity) miner, world, ModTags.Blocks.INFINITY_PICKAXE);
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (stack.getOrCreateNbt().getBoolean("hammer")) {
            return 5.0F;
        } if(!state.isIn(ModTags.Blocks.INFINITY_PICKAXE)) {
            return 275.0F;
        }
        return super.miningSpeed;
    }
}
