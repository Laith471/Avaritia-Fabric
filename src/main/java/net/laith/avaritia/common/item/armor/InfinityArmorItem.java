package net.laith.avaritia.common.item.armor;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

public class InfinityArmorItem extends ArmorItem {


    public InfinityArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            PlayerEntity player = (PlayerEntity) entity;
            if (entity instanceof PlayerEntity && slot == EquipmentSlot.HEAD.getEntitySlotId()) {
                player.setAir(300);
                player.getHungerManager().add(20, 20.0F);
                StatusEffectInstance nightVisionEffect = player.getStatusEffect(StatusEffects.NIGHT_VISION);
                if (nightVisionEffect == null) {
                    nightVisionEffect = new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false);
                    player.addStatusEffect(nightVisionEffect);
                } else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 300, 0, false, false));
                }
            }    else if (slot == EquipmentSlot.CHEST.getEntitySlotId()) {
                    List<StatusEffectInstance> effects = Lists.newArrayList(player.getStatusEffects());
                    Iterator<StatusEffectInstance> iterator = Collections2.filter(effects, (effectInstance) -> effectInstance.getEffectType().getCategory() == StatusEffectCategory.HARMFUL).iterator();
                    while (iterator.hasNext()) {
                        StatusEffectInstance effectInstance = iterator.next();
                        player.removeStatusEffect(effectInstance.getEffectType());
                    }
                } else if (slot == EquipmentSlot.LEGS.getEntitySlotId() && player.isOnFire()) {
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
        return true;
    }
}
