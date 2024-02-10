package net.laith.avaritia.compat.tc.tools.shuriken;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.gadgets.entity.shuriken.ShurikenEntityBase;
import slimeknights.tconstruct.gadgets.item.ShurikenItem;

import java.util.function.BiFunction;

public class InfinityShurikenItem extends ShurikenItem {
    private final BiFunction<Level, Player, ShurikenEntityBase> entity;

    public InfinityShurikenItem(Properties properties, BiFunction<Level, Player, ShurikenEntityBase> entity) {
        super(properties, entity);
        this.entity = entity;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), Sounds.SHURIKEN_THROW.getSound(), SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getCooldowns().addCooldown(stack.getItem(), 4);
        if (!level.isClientSide) {
            ShurikenEntityBase entity = (ShurikenEntityBase)this.entity.apply(level, player);
            entity.setItem(stack);
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(entity);
        }

        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}
