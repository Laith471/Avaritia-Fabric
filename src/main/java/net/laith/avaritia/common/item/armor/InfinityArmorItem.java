package net.laith.avaritia.common.item.armor;

import com.google.common.collect.*;
import net.laith.avaritia.init.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


import java.util.*;

public class InfinityArmorItem extends ArmorItem {

    public InfinityArmorItem(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide) {
            Player player = (Player) entity;
            if (entity instanceof Player && player.getItemBySlot(EquipmentSlot.HEAD).getItem() == ModItems.INFINITY_HELMET) {
                player.setAirSupply(300);
                player.getFoodData().eat(20, 20.0F);
                MobEffectInstance nightVisionEffect = player.getEffect(MobEffects.NIGHT_VISION);
                if (nightVisionEffect == null) {
                    nightVisionEffect = new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false);
                    player.addEffect(nightVisionEffect);

                } else {
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, false, false));
                }
            } if (player.getItemBySlot(EquipmentSlot.CHEST).getItem() == ModItems.INFINITY_CHESTPLATE) {
                List<MobEffectInstance> effects = Lists.newArrayList(player.getActiveEffects());
                Iterator<MobEffectInstance> iterator = Collections2.filter(effects, (effectInstance) -> effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).iterator();
                while (iterator.hasNext()) {
                    MobEffectInstance effectInstance = iterator.next();
                    player.removeEffect(effectInstance.getEffect());
                }
            } if (player.getItemBySlot(EquipmentSlot.LEGS).getItem() == ModItems.INFINITY_LEGGINGS) {
                player.clearFire();
            }
        }
    }

    @Override
    public boolean canBeDepleted() {
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource source) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}