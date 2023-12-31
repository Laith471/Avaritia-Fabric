package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.init.ModScreenHandlers;
import net.laith.avaritia.util.slots.OutputSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class NeutroniumCompressorScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    PropertyDelegate propertyDelegate;


    public NeutroniumCompressorScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(2), new ArrayPropertyDelegate(2));
    }


    public NeutroniumCompressorScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;

        addProperties(propertyDelegate);

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
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + Player Inv Slot
    @Override
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        var itemstack = ItemStack.EMPTY;
        var slot = this.slots.get(invSlot);

        if (slot.hasStack()) {
            var itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (invSlot == 0) {
                if (!this.insertItem(itemstack1, 2, 38, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickTransfer(itemstack1, itemstack);

            } else if (invSlot >= 2 && invSlot < 38) {
                if (!this.insertItem(itemstack1, 1, 2, false)) {
                    if (invSlot < 29) {
                        if (!this.insertItem(itemstack1, 29, 38, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(itemstack1, 10, 29, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(itemstack1, 2, 38, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.getCount() == 0) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, itemstack1);
        }

        return itemstack;
    }


    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }
    public int getProgress() {
        return propertyDelegate.get(0);
    }
    public int getCost() {
        return propertyDelegate.get(1);
    }

    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int costPrice = this.propertyDelegate.get(1);  // Max Progress
        int progressArrowSize = 16; // This is the width in pixels of your arrow

        return costPrice != 0 && progress != 0 ? progress * progressArrowSize / costPrice : 0;
    }
}