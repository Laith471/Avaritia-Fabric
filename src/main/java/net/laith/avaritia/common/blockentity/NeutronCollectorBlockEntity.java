package net.laith.avaritia.common.blockentity;

import net.laith.avaritia.common.block.NeutronCollectorBlock;
import net.laith.avaritia.common.block.NeutroniumCompressorBlock;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.AvaritiaConfig;
import net.laith.avaritia.util.inventory.ImplementedSidedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class NeutronCollectorBlockEntity extends BlockEntity implements MenuProvider, ImplementedSidedInventory {
    public static final int TIMER = AvaritiaConfig.getConfig().process;
    private final NonNullList<ItemStack> inventory;
    private int progress = 0;

    protected final ContainerData containerData;

    public NeutronCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEUTRON_COLLECTOR_BLOCK_ENTITY, pos, state);
        this.inventory = NonNullList.withSize(1, ItemStack.EMPTY);
        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return progress;
            }

            @Override
            public void set(int index, int value) {
                progress = value;

            }

            @Override
            public int getCount() {
                return 1;
            }
        };
    }


    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState state, NeutronCollectorBlockEntity entity) {
        if (level.isClientSide) {
            return;
        }
        if(entity.inventory.get(0).getCount() < 64) {
            level.setBlockAndUpdate(blockPos, state.setValue(NeutronCollectorBlock.ACTIVE, true));
            entity.containerData.set(0, entity.containerData.get(0)+1);
            if(entity.progress == TIMER) {
                craftItem(entity);
                entity.resetProgress();
                setChanged(level, blockPos, state);
            } else {
                setChanged(level, blockPos, state);
            }
        } else {
            level.setBlockAndUpdate(blockPos, state.setValue(NeutronCollectorBlock.ACTIVE, false));
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new NeutronCollectorScreenHandler(i, inventory, this, this.containerData);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.progress = tag.getInt("progress");
        ContainerHelper.loadAllItems(tag, this.inventory);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if(this.inventory.get(0).getCount() < 64) {
            tag.putInt("progress", this.progress);
        }
        ContainerHelper.saveAllItems(tag, this.inventory);
    }

    private static void craftItem(NeutronCollectorBlockEntity entity) {
        entity.inventory.set(0, new ItemStack(ModItems.PILE_OF_NEUTRONS, 1 + entity.inventory.get(0).getCount()));
        entity.resetProgress();
    }

    public void resetProgress() {
        this.progress = 0;
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        Direction localDir = this.getLevel().getBlockState(this.getBlockPos()).getValue(NeutroniumCompressorBlock.FACING);


        if(direction == Direction.UP || direction == Direction.DOWN) {
            return index == 0;
        }

        return switch (localDir) {
            default ->
                    direction.getOpposite() == Direction.NORTH && index == 0 ||
                            direction.getOpposite() == Direction.EAST && index == 0 ||
                            direction.getOpposite() == Direction.WEST && index == 0 ||
                            direction.getOpposite() == Direction.SOUTH && index == 0;
            case EAST ->
                    direction.getClockWise() == Direction.NORTH && index == 0 ||
                            direction.getClockWise() == Direction.EAST && index == 0 ||
                            direction.getClockWise() == Direction.WEST && index == 0 ||
                            direction.getClockWise() == Direction.SOUTH && index == 0;
            case SOUTH ->
                    direction == Direction.NORTH && index == 0 ||
                            direction == Direction.EAST && index == 0 ||
                            direction == Direction.WEST && index == 0 ||
                            direction == Direction.SOUTH && index == 0;
            case WEST ->
                    direction.getCounterClockWise() == Direction.NORTH && index == 0 ||
                            direction.getCounterClockWise() == Direction.EAST && index == 0 ||
                            direction.getCounterClockWise() == Direction.WEST && index == 0 ||
                            direction.getCounterClockWise() == Direction.SOUTH && index == 0;
        };
    }
}
