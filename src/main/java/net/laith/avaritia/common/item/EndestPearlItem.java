package net.laith.avaritia.common.item;

import net.laith.avaritia.common.entity.EndestPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class EndestPearlItem extends Item {
    public EndestPearlItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!user.isCreative()) {
            stack.decrement(1);
        }

        world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));

        if (!world.isClient) {
            EndestPearlEntity pearl = new EndestPearlEntity(world, user);
            pearl.setItem(stack);
            pearl.setPos(user.getX(), user.getEyeY() + 0.1, user.getZ());
            pearl.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(pearl);
            user.getItemCooldownManager().set(stack.getItem(), 30);
        }
        return TypedActionResult.success(stack, world.isClient);
    }

}
