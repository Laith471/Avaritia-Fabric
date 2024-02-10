package net.laith.avaritia.mixin;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Blocks.class)
public class BlocksMixinBedrock {

    /**
     * @author LlamaLad7
     **/

    @Redirect(
            method = "<clinit>",
            slice = @Slice(from = @At(value = "CONSTANT", args = "stringValue=bedrock")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;noLootTable()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;", ordinal = 0)
    )
    private static BlockBehaviour.Properties bedrockDropsItself(BlockBehaviour.Properties instance) {
        return instance;
    }
}