package net.laith.avaritia.common.blockentity;

import net.laith.avaritia.common.block.NeutronCollectorBlock;
import net.laith.avaritia.common.block.NeutroniumCompressorBlock;
import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.inventory.ImplementedInventory;
import net.laith.avaritia.util.inventory.ImplementedSidedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class NeutronCollectorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedSidedInventory {
    public static final int TIMER = 7111;
    private final DefaultedList<ItemStack> inventory;
    private int progress = 0;

    protected final PropertyDelegate propertyDelegate;

    public NeutronCollectorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.NEUTRON_COLLECTOR_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return progress;
            }

            @Override
            public void set(int index, int value) {
                progress = value;

            }

            @Override
            public int size() {
                return 1;
            }
        };
    }


    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }


    public static void tick(World world, BlockPos blockPos, BlockState state, NeutronCollectorBlockEntity entity) {
        if (world.isClient()) {
            return;
        }
        if(entity.inventory.get(0).getCount() < 64) {
            world.setBlockState(blockPos, state.with(NeutronCollectorBlock.ACTIVE, true));
            entity.propertyDelegate.set(0, entity.propertyDelegate.get(0)+1);
            if(entity.progress == TIMER) {
                craftItem(entity);
                entity.resetProgress();
                markDirty(world, blockPos, state);
            } else {
                markDirty(world, blockPos, state);
            }
        } else {
            world.setBlockState(blockPos, state.with(NeutronCollectorBlock.ACTIVE, false));
        }
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new NeutronCollectorScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.progress = nbt.getInt("progress");
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("progress", this.progress);
        Inventories.writeNbt(nbt, this.inventory);
    }

    private static void craftItem(NeutronCollectorBlockEntity entity) {
        entity.inventory.set(0, new ItemStack(ModItems.PILE_OF_NEUTRONS, 1 + entity.inventory.get(0).getCount()));
        entity.resetProgress();
    }

    public void resetProgress() {
        this.progress = 0;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction side) {
        return false;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, @Nullable Direction side) {
        Direction localDir = this.getWorld().getBlockState(this.pos).get(NeutroniumCompressorBlock.FACING);


        if(side == Direction.UP || side == Direction.DOWN) {
            return slot == 0;
        }

        return switch (localDir) {
            default ->
                    side.getOpposite() == Direction.NORTH && slot == 0 ||
                            side.getOpposite() == Direction.EAST && slot == 0 ||
                            side.getOpposite() == Direction.WEST && slot == 0 ||
                            side.getOpposite() == Direction.SOUTH && slot == 0;
            case EAST ->
                    side.rotateYClockwise() == Direction.NORTH && slot == 0 ||
                            side.rotateYClockwise() == Direction.EAST && slot == 0 ||
                            side.rotateYClockwise() == Direction.WEST && slot == 0 ||
                            side.rotateYClockwise() == Direction.SOUTH && slot == 0;
            case SOUTH ->
                    side == Direction.NORTH && slot == 0 ||
                            side == Direction.EAST && slot == 0 ||
                            side == Direction.WEST && slot == 0 ||
                            side == Direction.SOUTH && slot == 0;
            case WEST ->
                    side.rotateYCounterclockwise() == Direction.NORTH && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.EAST && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.WEST && slot == 0 ||
                            side.rotateYCounterclockwise() == Direction.SOUTH && slot == 0;
        };
    }
}
