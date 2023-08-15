package net.laith.avaritia.init;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {

    public static final FoodComponent ULTIMATE_STEW = new FoodComponent.Builder().saturationModifier(20.0f)
            .hunger(20).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 6000, 1), 1).build();

    public static final FoodComponent COSMIC_MEATBALLS = new FoodComponent.Builder().saturationModifier(20.0f)
            .hunger(20).statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 6000, 1), 1).build();
}
