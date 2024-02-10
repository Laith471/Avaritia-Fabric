package net.laith.avaritia.init;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.function.Supplier;

public enum ModToolMaterials implements Tier {

    INFINITY_SWORD(32, 0, 8.0F, 12.0F, 0, () -> {
        return Ingredient.of(new ItemLike[]{ModItems.INFINITY_INGOT});
    }),

    INFINITY_AXE(32, 0, 9999.0F, 29.0F, 0, () -> {
        return Ingredient.of(new ItemLike[]{ModItems.INFINITY_INGOT});
    }),

    INFINITY_PICKAXE(32, 0, 9999.0F, 15.0F, 0, () -> {
        return Ingredient.of(new ItemLike[]{ModItems.INFINITY_INGOT});
    }),

    INFINITY_SHOVEL(32, 0, 9999F, 16.0F, 0, () -> {
        return Ingredient.of(new ItemLike[]{ModItems.INFINITY_INGOT});
    });



    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new LazyLoadedValue<>(repairIngredient);
    }

    @Override
    public int getUses() {
        return this.itemDurability;
    }

    @Override
    public float getSpeed() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamage;
    }

    @Override
    public int getLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
