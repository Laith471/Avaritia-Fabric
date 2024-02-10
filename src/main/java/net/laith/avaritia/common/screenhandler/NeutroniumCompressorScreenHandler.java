package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.init.ModScreenHandlers;
import net.laith.avaritia.util.slots.OutputSlot;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class NeutroniumCompressorScreenHandler extends AbstractContainerMenu {
    private final Container inventory;
    ContainerData containerData;


    public NeutroniumCompressorScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(2), new SimpleContainerData(2));
    }


    public NeutroniumCompressorScreenHandler(int syncId, Inventory playerInventory, Container inventory, ContainerData containerData) {
        super(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, syncId);
        checkContainerSize(inventory, 2);
        this.inventory = inventory;
        inventory.startOpen(playerInventory.player);
        this.containerData = containerData;

        addDataSlots(containerData);

        this.addSlot(new OutputSlot(inventory, 1, 117, 35));
        this.addSlot(new Slot(inventory, 0, 39, 35));

        int m;
        int l;
        //The player inventory
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        //The player Hotbar
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    @Override
    public boolean stillValid(Player player) {
        return this.inventory.stillValid(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMoveStack(Player player, int invSlot) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(invSlot);

        if (slot.hasItem()) {
            var itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (invSlot == 0) {
                if (!this.moveItemStackTo(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(itemstack1, itemstack);

            } else if (invSlot >= 2 && invSlot < 38) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    if (invSlot < 29) {
                        if (!this.moveItemStackTo(itemstack1, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.moveItemStackTo(itemstack1, 10, 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.moveItemStackTo(itemstack1, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }


    public boolean isCrafting() {
        return containerData.get(0) > 0;
    }
    public int getProgress() {
        return containerData.get(0);
    }
    public int getCost() {
        return containerData.get(1);
    }

    public int getScaledProgress() {
        int progress = this.containerData.get(0);
        int costPrice = this.containerData.get(1);  // Max Progress
        int progressArrowSize = 16; // This is the width in pixels of your arrow

        return costPrice != 0 && progress != 0 ? progress * progressArrowSize / costPrice : 0;
    }
}