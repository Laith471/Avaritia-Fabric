package net.laith.avaritia.common.item.armor;

import com.google.common.collect.*;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.TextUtil;
import net.minecraft.client.item.TooltipContext;
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
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.management.Attribute;
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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(stack.getItem() == ModItems.INFINITY_BOOTS) {
            TextUtil.tooltipReplacer(stack, tooltip, "feet", TextUtil.makeSANIC("+SANIC%"), "Speed", 6);
        }
        if(stack.getItem() == ModItems.INFINITY_LEGGINGS) {
            TextUtil.tooltipReplacer(stack, tooltip, "legs", TextUtil.makeDemonic("+DEMONIC"), "Protection", 12);
        }
        if(stack.getItem() == ModItems.INFINITY_CHESTPLATE) {
            TextUtil.tooltipReplacer(stack, tooltip, "chest", TextUtil.makeAngelic("+ANGEL"), "Wings", 16);
        }
        if(stack.getItem() == ModItems.INFINITY_HELMET) {
            TextUtil.tooltipReplacer(stack, tooltip, "head", TextUtil.makeFairiec("+DRACONIC"), "Eyes", 6);
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}