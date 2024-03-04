package net.laith.avaritia.compat.botania.block;

import net.laith.avaritia.compat.botania.Botania;
import net.laith.avaritia.compat.botania.blockentity.InfinityManaPoolBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.state.BotaniaStateProperties;
import vazkii.botania.common.block.BotaniaWaterloggedBlock;
import vazkii.botania.common.block.decor.BotaniaMushroomBlock;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.item.material.MysticalPetalItem;

import java.util.List;
import java.util.Optional;

import static vazkii.botania.api.state.BotaniaStateProperties.OPTIONAL_DYE_COLOR;

public class InfinityManaPoolBlock extends BotaniaWaterloggedBlock implements EntityBlock{
    private static final VoxelShape NORMAL_SHAPE;
    private static final VoxelShape NORMAL_SHAPE_INTERACT;
    static {
        NORMAL_SHAPE_INTERACT = box(0, 0, 0, 16, 8, 16);
        VoxelShape cutout = box(2, 2, 2, 14, 16, 14);

        NORMAL_SHAPE = Shapes.join(NORMAL_SHAPE_INTERACT, cutout, BooleanOp.ONLY_FIRST);
    }

    public enum Variant {
        INFINITY
    }

    public final Variant variant;

    public InfinityManaPoolBlock(Variant v, Properties builder) {
        super(builder);
        this.variant = v;
        registerDefaultState(defaultBlockState().setValue(OPTIONAL_DYE_COLOR, BotaniaStateProperties.OptionalDyeColor.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(OPTIONAL_DYE_COLOR);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
    }

    @NotNull
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext ctx) {
        return switch (this.variant) {
            case INFINITY -> NORMAL_SHAPE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext econtext
                && econtext.getEntity() instanceof ManaBurstEntity) {
            // Sometimes the pool's collision box is too thin for bursts shot straight up.
            return switch (this.variant) {
                case INFINITY -> NORMAL_SHAPE_INTERACT;
            };
        } else {
            return super.getCollisionShape(state, world, pos, context);
        }
    }

    @NotNull
    @Override
    public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
        return switch (this.variant) {
            case INFINITY -> NORMAL_SHAPE_INTERACT;
        };
    }

    @NotNull
    @Override
    public InteractionResult use(@NotNull BlockState state, Level world, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        BlockEntity be = world.getBlockEntity(pos);
        ItemStack stack = player.getItemInHand(hand);
        Optional<DyeColor> itemColor = Optional.empty();
        if (stack.getItem() instanceof MysticalPetalItem petalItem) {
            itemColor = Optional.of(petalItem.color);
        }
        if (Block.byItem(stack.getItem()) instanceof BotaniaMushroomBlock mushroomBlock) {
            itemColor = Optional.of(mushroomBlock.color);
        }
        if (itemColor.isPresent() && be instanceof InfinityManaPoolBlockEntity pool) {
            if (!itemColor.equals(pool.getColor())) {
                pool.setColor(itemColor);
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                return InteractionResult.sidedSuccess(world.isClientSide());
            }
        }
        if (stack.is(Items.CLAY_BALL) && be instanceof InfinityManaPoolBlockEntity pool && pool.getColor().isPresent()) {
            pool.setColor(Optional.empty());
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
            return InteractionResult.sidedSuccess(world.isClientSide());
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @NotNull
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new InfinityManaPoolBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, Botania.INFINITY_MANA_POOL_BLOCK_ENTITY, level.isClientSide ? InfinityManaPoolBlockEntity::clientTick : InfinityManaPoolBlockEntity::serverTick);
    }

    @Override
    public void entityInside(BlockState state, Level world, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity item) {
            InfinityManaPoolBlockEntity tile = (InfinityManaPoolBlockEntity) world.getBlockEntity(pos);
            tile.collideEntityItem(item);
        }
    }

    @NotNull
    @Override
    public RenderShape getRenderShape(BlockState state) {
            return RenderShape.MODEL;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
        InfinityManaPoolBlockEntity pool = (InfinityManaPoolBlockEntity) world.getBlockEntity(pos);
        return InfinityManaPoolBlockEntity.calculateComparatorLevel(pool.getCurrentMana(), pool.getMaxMana());
    }
}

