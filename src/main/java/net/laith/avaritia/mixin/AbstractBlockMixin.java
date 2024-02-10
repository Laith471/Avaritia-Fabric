package net.laith.avaritia.mixin;

import net.laith.avaritia.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockBehaviour.class)
public class AbstractBlockMixin {

	/**
	 * @author Nokko, Edited by Xanthian 2022
	 **/

	final float effectiveHardness = 1.0F;

	@Inject(at = @At(value = "JUMP", opcode = Opcodes.IFNE, shift = At.Shift.AFTER),
			method = "getDestroyProgress",
			cancellable = true,
			locals = LocalCapture.CAPTURE_FAILSOFT
	)
	public void allowBedrockBreaking(BlockState state, Player player, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
		ItemStack stack = player.getItemInHand(InteractionHand.MAIN_HAND);

		if (state.getBlock() == Blocks.BEDROCK && (stack.getItem() == ModItems.INFINITY_PICKAXE)) {
			cir.setReturnValue(player.getDestroySpeed(state) / effectiveHardness);
		}
		if (state.getBlock() == Blocks.END_PORTAL_FRAME && (stack.getItem() == ModItems.INFINITY_PICKAXE)) {
			cir.setReturnValue(player.getDestroySpeed(state) / effectiveHardness);
		}
	}
}