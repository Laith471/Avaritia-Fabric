package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.init.ModScreenHandlers;
import net.laith.avaritia.util.slots.ExtremeResultSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;

import java.util.Optional;

public class ExtremeCraftingTableScreenHandler extends ScreenHandler {
    public final CraftingInventory craftingInventory = new CraftingInventory(this, 9, 9);
    public final PlayerEntity player;
    private final CraftingResultInventory resultInventory = new CraftingResultInventory();
    private final Inventory blockEntityInventory;
    private final World world;
    public ExtremeRecipe cachedRecipe = null;

    public ExtremeCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(81));
    }

    public ExtremeCraftingTableScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        this(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, syncId, playerInventory, inventory);
    }

    protected ExtremeCraftingTableScreenHandler(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory beInventory) {
        super(type, syncId);
        this.player = playerInventory.player;
        this.world = playerInventory.player.getWorld();
        this.blockEntityInventory = beInventory;
        checkSize(beInventory, 81);

        // Load blockentity inventory into crafting grid
        for (int i = 0; i < 81; i++) {
            craftingInventory.setStack(i, blockEntityInventory.getStack(i));
        }

        // Crafting Result Slot
        this.addSlot(new ExtremeResultSlot(this, playerInventory.player, this.craftingInventory, this.resultInventory, 0, 210, 80));

        int h;
        int w;
        // Workbench inventory
        for (h = 0; h < 9; ++h) {
            for (w = 0; w < 9; ++w) {
                this.addSlot(new Slot(this.craftingInventory, w + h * 9, 12 + w * 18, 8 + h * 18));
            }
        }

        //The player inventory
        for (h = 0; h < 3; ++h) {
            for (w = 0; w < 9; ++w) {
                this.addSlot(new Slot(playerInventory, w + h * 9 + 9, 39 + w * 18, 174 + h * 18));
            }
        }
        //The player Hotbar
        for (w = 0; w < 9; ++w) {
            this.addSlot(new Slot(playerInventory, w, 39 + w * 18, 232));
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasStack()) {
            return itemStack;
        }
        ItemStack itemStack2 = slot.getStack();
        itemStack = itemStack2.copy();
        if (index == 0) {
            itemStack2.getItem().onCraft(itemStack2, world, player);
            if (!this.insertItem(itemStack2, 82, 118, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickTransfer(itemStack2, itemStack);
        } else if (index >= 82 && index < 118 ? !this.insertItem(itemStack2, 1, 82, false) && (index < 109 ? !this.insertItem(itemStack2, 109, 118, false) : !this.insertItem(itemStack2, 82, 109, false)) : !this.insertItem(itemStack2, 82, 118, false)) {
            return ItemStack.EMPTY;
        }
        if (itemStack2.isEmpty()) {
            slot.setStack(ItemStack.EMPTY);
        } else {
            slot.markDirty();
        }
        if (itemStack2.getCount() == itemStack.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTakeItem(player, itemStack2);
        if (index == 0) {
            player.dropItem(itemStack2, false);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.blockEntityInventory.canPlayerUse(player);
    }

    @Override
    public void onClosed(PlayerEntity player) {
        if (this.world.isClient) {
            return;
        }
        for (int i = 0; i < 81; i++) {
            if (!blockEntityInventory.getStack(i).isEmpty()) {
                blockEntityInventory.setStack(i, ItemStack.EMPTY);
            }
            blockEntityInventory.setStack(i, craftingInventory.removeStack(i));
        }
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack craftingResult;
        ExtremeRecipe currentRecipe = null;

        // Check cache
        if (cachedRecipe != null && !cachedRecipe.matches(craftingInventory, world)) {
            cachedRecipe = null;
        }

        // Find or set current recipe
        if (cachedRecipe == null) {
            Optional<? extends Recipe<CraftingInventory>> optional = world.getRecipeManager().getFirstMatch(ExtremeRecipe.Type.INSTANCE, craftingInventory, world);
            if (optional.isPresent()) {
                currentRecipe = (ExtremeRecipe) optional.get();
                cachedRecipe = currentRecipe;
            }
        } else {
            currentRecipe = cachedRecipe;
        }

        if (world.isClient) {
            return;
        }

        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) player;
        if (currentRecipe != null && resultInventory.shouldCraftRecipe(world, serverPlayerEntity, currentRecipe)) {
            craftingResult = currentRecipe.craft(craftingInventory, world.getRegistryManager());
        } else {
            craftingResult = ItemStack.EMPTY;
        }

        resultInventory.setStack(0, craftingResult);
        //setPreviousTrackedSlot(0, craftingResult);
        serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(syncId, nextRevision(), 0, craftingResult));

    }
}
