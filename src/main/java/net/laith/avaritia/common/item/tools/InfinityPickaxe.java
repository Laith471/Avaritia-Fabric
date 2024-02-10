package net.laith.avaritia.common.item.tools;

import net.laith.avaritia.init.ModTags;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfinityPickaxe extends DiggerItem {

    public InfinityPickaxe(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(attackDamage, attackSpeed, tier, ModTags.Blocks.INFINITY_PICKAXE, properties);
    }


    @Override
    public boolean canBeDepleted() {
        return false;
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (player.isShiftKeyDown()) {
            CompoundTag tag = stack.getOrCreateTag();
            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack) < 10) {
                stack.enchant(Enchantments.BLOCK_FORTUNE, 10);
            }
            tag.putBoolean("hammer", !tag.getBoolean("hammer"));
            player.setMainArm(HumanoidArm.RIGHT);
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
        }
        return super.use(level, player, usedHand);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (stack.getOrCreateTag().getBoolean("hammer")) {
            if (!(target instanceof Player)) {
                int i = 10;
                target.setDeltaMovement(-Mth.sin(attacker.getYRot() * (float) Math.PI / 180.0F) * i * 0.5F, 2.0D, Mth.cos(attacker.getYRot() * (float) Math.PI / 180.0F) * i * 0.5F);
            }
        }
        return true;
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (stack.getOrCreateTag().getBoolean("hammer")) {
            return 5.0F;
        } if(!state.is(ModTags.Blocks.INFINITY_PICKAXE)) {
            return 275.0F;
        }
        return super.speed;
    }
}
