package net.laith.avaritia.mixin;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonElement;
import net.laith.avaritia.common.recipe.ExtremeRecipeManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
            at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableMap;builder()Lcom/google/common/collect/ImmutableMap$Builder;", shift = At.Shift.BY, by = 2), locals = LocalCapture.CAPTURE_FAILHARD)
    private void addShadowRecipe(Map<ResourceLocation, JsonElement> object, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci, Map<RecipeType, ImmutableMap.Builder> map2, ImmutableMap.Builder builder) {
        for(int i = 0; i < ExtremeRecipeManager.recipes.size(); i++) {
            ((ImmutableMap.Builder)map2.computeIfAbsent(ExtremeRecipeManager.recipes.get(i).getType(), recipeType -> ImmutableMap.builder())).put(ExtremeRecipeManager.recipes.get(i).getId(), ExtremeRecipeManager.recipes.get(i));
            builder.put(ExtremeRecipeManager.recipes.get(i).getId(), ExtremeRecipeManager.recipes.get(i));
            if(ExtremeRecipeManager.recipes.get(i).getId().equals(ExtremeRecipeManager.recipes.get(i + 1).getId()))
                break;
        }
    }
}
