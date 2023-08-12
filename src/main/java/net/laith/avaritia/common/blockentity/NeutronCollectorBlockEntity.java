package net.laith.avaritia.common.blockentity;

import net.laith.avaritia.common.screenhandler.NeutronCollectorScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NeutronCollectorBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
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
            entity.propertyDelegate.set(0, entity.propertyDelegate.get(0)+1);
            if(entity.progress == TIMER) {
                craftItem(entity);
                entity.resetProgress();
                markDirty(world, blockPos, state);
            } else {
                markDirty(world, blockPos, state);
            }
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
}
