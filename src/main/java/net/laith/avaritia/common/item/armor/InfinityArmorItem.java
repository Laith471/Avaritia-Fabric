package net.laith.avaritia.common.item.armor;

import com.google.common.collect.*;
import net.laith.avaritia.init.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.*;

public class InfinityArmorItem extends ArmorItem {

    public InfinityArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            PlayerEntity player = (PlayerEntity) entity;
            if (entity instanceof PlayerEntity && player.getEquippedStack(EquipmentSlot.HEAD).getItem() == ModItems.INFINITY_HELMET) {
                player.setAir(300);
                player.getHungerManager().add(20, 20.0F);
                StatusEffectInstance nightVisionEffect = player.getStatusEffect(StatusEffects.NIGHT_VISION);
                if (nightVisionEffect == null) {
                    nightVisionEffect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false);
                    player.addStatusEffect(nightVisionEffect);

                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false));
                }
            } if (player.getEquippedStack(EquipmentSlot.CHEST).getItem() == ModItems.INFINITY_CHESTPLATE) {
                List<StatusEffectInstance> effects = Lists.newArrayList(player.getStatusEffects());
                Iterator<StatusEffectInstance> iterator = Collections2.filter(effects, (effectInstance) -> effectInstance.getEffectType().getCategory() == StatusEffectCategory.HARMFUL).iterator();
                while (iterator.hasNext()) {
                    StatusEffectInstance effectInstance = iterator.next();
                    player.removeStatusEffect(effectInstance.getEffectType());
                }
            } if (player.getEquippedStack(EquipmentSlot.LEGS).getItem() == ModItems.INFINITY_LEGGINGS) {
                player.extinguish();
            }
        }
    }


    @Override
    public boolean isDamageable() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source) {
        return false;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

}