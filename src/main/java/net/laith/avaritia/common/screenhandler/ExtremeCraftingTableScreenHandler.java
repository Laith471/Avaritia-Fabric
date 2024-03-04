package net.laith.avaritia.common.screenhandler;

import net.laith.avaritia.common.recipe.ExtremeRecipe;
import net.laith.avaritia.init.ModScreenHandlers;
import net.laith.avaritia.util.slots.ExtremeResultSlot;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class ExtremeCraftingTableScreenHandler extends AbstractContainerMenu {
    public final TransientCraftingContainer craftingInventory = new TransientCraftingContainer(this, 9, 9);
    public final Player player;
    private final ResultContainer resultInventory = new ResultContainer();
    private final Container blockEntityInventory;
    private final Level level;
    public ExtremeRecipe cachedRecipe = null;

    public ExtremeCraftingTableScreenHandler(int syncId, Inventory playerInventory) {
        this(syncId, playerInventory, new SimpleContainer(81));
    }

    public ExtremeCraftingTableScreenHandler(int syncId, Inventory playerInventory, Container container) {
        this(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, syncId, playerInventory, container);
    }

    protected ExtremeCraftingTableScreenHandler(MenuType<?> type, int syncId, Inventory playerInventory, Container beInventory) {
        super(type, syncId);
        this.player = playerInventory.player;
        this.level = playerInventory.player.getCommandSenderWorld();
        this.blockEntityInventory = beInventory;
        checkContainerSize(beInventory, 81);

        // Load blockentity inventory into crafting grid
        for (int i = 0; i < 81; i++) {
            craftingInventory.setItem(i, blockEntityInventory.getItem(i));
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
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) {
            return itemStack;
        }
        ItemStack itemStack2 = slot.getItem();
        itemStack = itemStack2.copy();
        if (index == 0) {
            itemStack2.getItem().onCraftedBy(itemStack2, level, player);
            if (!this.moveItemStackTo(itemStack2, 82, 118, true)) {
                return ItemStack.EMPTY;
            }
            slot.onQuickCraft(itemStack2, itemStack);
        } else if (index >= 82 && index < 118 ? !this.moveItemStackTo(itemStack2, 1, 82, false) && (index < 109 ? !this.moveItemStackTo(itemStack2, 109, 118, false) : !this.moveItemStackTo(itemStack2, 82, 109, false)) : !this.moveItemStackTo(itemStack2, 82, 118, false)) {
            return ItemStack.EMPTY;
        }
        if (itemStack2.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        if (itemStack2.getCount() == itemStack.getCount()) {
            return ItemStack.EMPTY;
        }
        slot.onTake(player, itemStack2);
        if (index == 0) {
            player.drop(itemStack2, false);
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.blockEntityInventory.stillValid(player);
    }

    @Override
    public void removed(Player player) {
        if (this.level.isClientSide) {
            return;
        }
        for (int i = 0; i < 81; i++) {
            if (!blockEntityInventory.getItem(i).isEmpty()) {
                blockEntityInventory.setItem(i, ItemStack.EMPTY);
            }
            blockEntityInventory.setItem(i, craftingInventory.removeItemNoUpdate(i));
        }
    }

    @Override
    public void slotsChanged(Container container) {
        ItemStack craftingResult;
        ExtremeRecipe currentRecipe = null;

        // Check cache
        if (cachedRecipe != null && !cachedRecipe.matches(craftingInventory, level)) {
            cachedRecipe = null;
        }

        // Find or set current recipe
        if (cachedRecipe == null) {
            Optional<? extends Recipe<CraftingContainer>> optional = level.getRecipeManager().getRecipeFor(ExtremeRecipe.Type.INSTANCE, craftingInventory, level);
            if (optional.isPresent()) {
                currentRecipe = (ExtremeRecipe) optional.get();
                cachedRecipe = currentRecipe;
            }
        } else {
            currentRecipe = cachedRecipe;
        }

        if (level.isClientSide) {
            return;
        }

        ServerPlayer serverPlayerEntity = (ServerPlayer) player;
        if (currentRecipe != null && resultInventory.setRecipeUsed(level, serverPlayerEntity, currentRecipe)) {
            craftingResult = currentRecipe.assemble(craftingInventory, level.registryAccess());
        } else {
            craftingResult = ItemStack.EMPTY;
        }

        resultInventory.setItem(0, craftingResult);
        //setPreviousTrackedSlot(0, craftingResult);
        serverPlayerEntity.connection.send(new ClientboundContainerSetSlotPacket(containerId, incrementStateId(), 0, craftingResult));
    }
}
