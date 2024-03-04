package net.laith.avaritia.init;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class ModModelsPredicateProviders {

    public static void registerProviders() {
        ItemProperties.register(ModItems.INFINITY_PICKAXE, new ResourceLocation("hammer"), (stack, world, entity, seed) -> stack.hasTag() && stack.getTag().getBoolean("hammer") ? 1 : 0);
        ItemProperties.register(ModItems.INFINITY_SHOVEL, new ResourceLocation("destroyer"), (stack, world, entity, seed) -> stack.hasTag() && stack.getTag().getBoolean("destroyer") ? 1 : 0);

        ItemProperties.register(ModItems.INFINITY_BOW, new ResourceLocation(AvaritiaMod.MOD_ID, "pull"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.getUseItem() != itemStack ? 0.0F : (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F;
        });

        ItemProperties.register(ModItems.INFINITY_BOW, new ResourceLocation(AvaritiaMod.MOD_ID, "pulling"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            return livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
        ResourceLocation poolFullId = new ResourceLocation(AvaritiaMod.MOD_ID, "full");
        ClampedItemPropertyFunction poolFull = (stack, world, entity, seed) -> {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            boolean renderFull = stack.hasTag() && stack.getTag().getBoolean("RenderFull");
            return renderFull ? 1F : 0F;
        };
    }

}