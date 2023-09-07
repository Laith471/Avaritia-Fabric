package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ModModelsPredicateProviders {

    public static void registerProviders() {
        ModelPredicateProviderRegistry.register(ModItems.INFINITY_PICKAXE, new Identifier("hammer"), (stack, world, entity, seed) -> stack.hasNbt() && stack.getNbt().getBoolean("hammer") ? 1 : 0);
        ModelPredicateProviderRegistry.register(ModItems.INFINITY_SHOVEL, new Identifier("destroyer"), (stack, world, entity, seed) -> stack.hasNbt() && stack.getNbt().getBoolean("destroyer") ? 1 : 0);

        ModelPredicateProviderRegistry.register(ModItems.INFINITY_BOW, new Identifier(AvaritiaMod.MOD_ID, "pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
        });

        ModelPredicateProviderRegistry.register(ModItems.INFINITY_BOW, new Identifier(AvaritiaMod.MOD_ID, "pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
        });
    }

}