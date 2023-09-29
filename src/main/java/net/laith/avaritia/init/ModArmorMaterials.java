package net.laith.avaritia.init;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {

        INFINITY("infinity", 0, (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (map) -> {
            map.put(ArmorItem.Type.BOOTS, 6);
            map.put(ArmorItem.Type.LEGGINGS, 12);
            map.put(ArmorItem.Type.CHESTPLATE, 16);
            map.put(ArmorItem.Type.HELMET, 6);
        }), 1000, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 100.0F, () -> {
            return Ingredient.ofItems(new ItemConvertible[]{ModItems.INFINITY_INGOT});
        });

        private final String name;
        private final int durabilityMultiplier;
        private final EnumMap<ArmorItem.Type, Integer> protectionAmounts;
        private final int enchantability;
        private final SoundEvent equipSound;
        private final float toughness;
        private final float knockbackResistance;
        private final Lazy<Ingredient> repairIngredientSupplier;

        private ModArmorMaterials(String name, int durabilityMultiplier, EnumMap protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier repairIngredientSupplier) {
            this.name = name;
            this.durabilityMultiplier = durabilityMultiplier;
            this.protectionAmounts = protectionAmounts;
            this.enchantability = enchantability;
            this.equipSound = equipSound;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
            this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
        }

        public int getDurability(ArmorItem.Type type) {return this.durabilityMultiplier;}

        public int getProtection(ArmorItem.Type type) {
            return (Integer)this.protectionAmounts.get(type);
        }

        public int getEnchantability() {
            return this.enchantability;
        }

        public SoundEvent getEquipSound() {
            return this.equipSound;
        }

        public Ingredient getRepairIngredient() {
            return (Ingredient)this.repairIngredientSupplier.get();
        }

        public String getName() {
            return this.name;
        }

        public float getToughness() {
            return this.toughness;
        }

        public float getKnockbackResistance() {
            return this.knockbackResistance;
        }

        public String asString() {
            return this.name;
        }
}
