package net.laith.avaritia.common.item.armor;

import com.google.common.collect.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
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
import net.minecraft.util.Util;
import net.minecraft.world.World;

import javax.management.Attribute;
import java.util.*;

public class InfinityArmorItem extends ArmorItem {
    Multimap<EntityAttribute, EntityAttributeModifier> map;
    public static final EntityAttributeModifier SPEED_MODIFIER = new EntityAttributeModifier(
            "Walk Speed Modifier",
            0.2, // Change this value to adjust the walk speed
            EntityAttributeModifier.Operation.MULTIPLY_TOTAL
    );


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
            } else if (slot == EquipmentSlot.CHEST.getEntitySlotId()) {
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

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        final Multimap<EntityAttribute, EntityAttributeModifier> multimap = ArrayListMultimap.create(super.getAttributeModifiers(slot));

        if (slot == EquipmentSlot.FEET) {
            multimap.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier("V1 SPEED", 0.5, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        }
        return multimap;
    }
}