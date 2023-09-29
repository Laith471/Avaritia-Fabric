package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.util.helpers.ToolHelper;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfinityShovelItem extends ShovelItem {

    public InfinityShovelItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
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
            tags.putBoolean("destroyer", !tags.getBoolean("destroyer"));
            user.setMainArm(Arm.RIGHT);
            return new TypedActionResult<>(ActionResult.SUCCESS, stack);
        }
        return super.use(world, user, hand);
    }


    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (stack.getOrCreateNbt().getBoolean("destroyer")) {
            if (state.isIn(BlockTags.SHOVEL_MINEABLE)) {
                ToolHelper.mineCube((PlayerEntity) miner, world, BlockTags.SHOVEL_MINEABLE);
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }

}
