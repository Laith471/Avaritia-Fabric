package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.common.block.MatterClusterBlock;
import net.laith.avaritia.init.ModScreenHandlers;
import net.laith.avaritia.util.slots.OutputSlot;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class MatterClusterScreenHandler extends ScreenHandler {
    private final Inventory inventory;


    public MatterClusterScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(45));
    }


    public MatterClusterScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(ModScreenHandlers.MATTER_CLUSTER_SCREEN_HANDLER, syncId);
        checkSize(inventory, 45);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);


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
    public ItemStack quickMove(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onClosed(PlayerEntity player) {
        BlockPos pos = inventory instanceof BlockEntity ? ((BlockEntity) inventory).getPos() : null;
        if (pos != null && player.getEntityWorld().getBlockState(pos).getBlock() instanceof MatterClusterBlock) {
            player.getEntityWorld().setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }
}