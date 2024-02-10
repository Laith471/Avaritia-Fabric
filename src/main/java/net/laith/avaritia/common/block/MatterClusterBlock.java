package net.laith.avaritia.common.block;

import net.laith.avaritia.common.blockentity.ExtremeCraftingTableBlockEntity;
import net.laith.avaritia.common.blockentity.MatterClusterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MatterClusterBlock extends BaseEntityBlock {

    public MatterClusterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MatterClusterBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {

            MenuProvider menuProvider = state.getMenuProvider(level, pos);

            if (menuProvider != null) {

                player.openMenu(menuProvider);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
