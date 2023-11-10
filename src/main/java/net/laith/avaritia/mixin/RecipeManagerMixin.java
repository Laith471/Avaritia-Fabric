package net.laith.avaritia.mixin;

import com.google.common.collect.ImmutableMap;
import net.laith.avaritia.common.recipe.ExtremeCraftingShapedRecipe;
import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.common.recipe.ExtremeRecipeManager;
import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.recipe.RecipeType;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;builder()Lcom/google/common/collect/ImmutableMap$Builder;", shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILHARD)
    private void addShadowRecipe(Map map, ResourceManager arg1, Profiler arg2, CallbackInfo ci, Map<RecipeType, ImmutableMap.Builder> map2, ImmutableMap.Builder<Identifier, Recipe<?>> builder) {
        for(int i = 0; i < ExtremeRecipeManager.recipes.size(); i++) {
            ((ImmutableMap.Builder)map2.computeIfAbsent(ExtremeRecipeManager.recipes.get(i).getType(), recipeType -> ImmutableMap.builder())).put(ExtremeRecipeManager.recipes.get(i).getId(), ExtremeRecipeManager.recipes.get(i));
            builder.put(ExtremeRecipeManager.recipes.get(i).getId(), ExtremeRecipeManager.recipes.get(i));
            if(ExtremeRecipeManager.recipes.get(i).getId().equals(ExtremeRecipeManager.recipes.get(i + 1).getId()))
                break;
        }
    }
}
