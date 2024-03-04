package net.laith.avaritia.common.item;

import net.laith.avaritia.common.entity.EndestPearlEntity;
import net.laith.avaritia.init.ModTextures;
import net.laith.avaritia.util.render.IHaloRenderItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EndestPearlItem extends Item implements IHaloRenderItem {
    public EndestPearlItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (!player.isCreative()) {
            stack.shrink(1);
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));

        if (!level.isClientSide) {
            EndestPearlEntity pearl = new EndestPearlEntity(level, player);
            pearl.setItem(stack);
            pearl.setPos(player.getX(), player.getEyeY() + 0.1, player.getZ());
            pearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(pearl);
            player.getCooldowns().addCooldown(stack.getItem(), 30);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
    }

    @Override
    public boolean shouldDrawHalo(ItemStack stack) {
        return true;
    }

    @Override
    public ResourceLocation getHaloTexture(ItemStack stack) {
        return ModTextures.HALO;
    }

    @Override
    public int getHaloColour(ItemStack stack) {
        return 0xFF000000;
    }

    @Override
    public int getHaloSize(ItemStack stack) {
        return 4;
    }

    @Override
    public boolean shouldDrawPulse(ItemStack stack) {
        return true;
    }

    @Override
    public boolean isItNeutron(ItemStack stack) {
        return false;
    }
}
