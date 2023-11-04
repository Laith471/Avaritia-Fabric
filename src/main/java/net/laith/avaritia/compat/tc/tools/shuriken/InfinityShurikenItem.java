package net.laith.avaritia.compat.tc.tools.shuriken;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.gadgets.entity.shuriken.ShurikenEntityBase;
import slimeknights.tconstruct.gadgets.item.ShurikenItem;

import java.util.function.BiFunction;

public class InfinityShurikenItem extends ShurikenItem {
    private final BiFunction<World, PlayerEntity, ShurikenEntityBase> entity;

    public InfinityShurikenItem(Settings properties, BiFunction<World, PlayerEntity, ShurikenEntityBase> entity) {
        super(properties, entity);
        this.entity = entity;
    }

    @Override
    public TypedActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        level.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), Sounds.SHURIKEN_THROW.getSound(), SoundCategory.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
        player.getItemCooldownManager().set(stack.getItem(), 4);
        if (!level.isClient()) {
            ShurikenEntityBase entity = (ShurikenEntityBase)this.entity.apply(level, player);
            entity.setItem(stack);
            entity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            level.spawnEntity(entity);
        }

        return TypedActionResult.success(stack, level.isClient());
    }
}
