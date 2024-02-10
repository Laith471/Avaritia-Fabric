package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.common.block.MatterClusterBlock;
import net.laith.avaritia.init.ModScreenHandlers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;

public class MatterClusterScreenHandler extends AbstractContainerMenu {
    private final Container inventory;


    public MatterClusterScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(45));
    }


    public MatterClusterScreenHandler(int syncId, Inventory playerInventory, Container inventory) {
        super(ModScreenHandlers.MATTER_CLUSTER_SCREEN_HANDLER, syncId);
        checkContainerSize(inventory, 45);
        this.inventory = inventory;
        inventory.startOpen(playerInventory.player);


        int m;
        int l;


        for (m = 0; m < 5; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + m * 9, 12 + l * 18, 18 + m * 18));
            }
        }
        //The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 12 + l * 18, 122 + m * 18));
            }
        }
        //The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 12 + m * 18, 180));
        }

    }

    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.inventory.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.inventory.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player player) {
        BlockPos pos = inventory instanceof BlockEntity ? ((BlockEntity) inventory).getBlockPos() : null;
        if (pos != null && player.getCommandSenderWorld().getBlockState(pos).getBlock() instanceof MatterClusterBlock) {
            player.getCommandSenderWorld().setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }
    }
}