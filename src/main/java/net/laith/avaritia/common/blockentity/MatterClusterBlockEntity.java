package net.laith.avaritia.common.blockentity;

import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.laith.avaritia.common.screenhandler.MatterClusterScreenHandler;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.util.inventory.ImplementedInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MatterClusterBlockEntity  extends BlockEntity implements MenuProvider, ImplementedInventory {
    private final NonNullList<ItemStack> inventory;

    public MatterClusterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MATTER_CLUSTER_BLOCK_ENTITY, pos, state);
        this.inventory = NonNullList.withSize(45, ItemStack.EMPTY);

    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new MatterClusterScreenHandler(i, inventory, this);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        ContainerHelper.loadAllItems(tag, inventory);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        ContainerHelper.saveAllItems(tag, inventory);
    }
}

