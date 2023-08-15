package net.laith.avaritia.init;

import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {

    INFINITY_SWORD(32, 0, 8.0F, 200.0F, 15, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.INFINITY_INGOT});
    }),

    INFINITY_AXE(32, 0, 9999.0F, 200.0F, 15, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.INFINITY_INGOT});
    }),

    INFINITY_SHOVEL(32, 0, 9999F, 200.0F, 0, () -> {
        return Ingredient.ofItems(new ItemConvertible[]{ModItems.INFINITY_INGOT});
    });



    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
