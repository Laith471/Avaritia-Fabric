package net.laith.avaritia.common.blockentity;

import net.laith.avaritia.common.screenhandler.MatterClusterScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.util.inventory.ImplementedInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class MatterClusterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {
    public final DefaultedList<ItemStack> inventory;


    public MatterClusterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MATTER_CLUSTER_BLOCK_ENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(45, ItemStack.EMPTY);

    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }


    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new MatterClusterScreenHandler(syncId, inv, this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);

    }
}

