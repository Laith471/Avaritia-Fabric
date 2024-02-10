package net.laith.avaritia.util.slots;

import net.laith.avaritia.common.screenhandler.ExtremeCraftingTableScreenHandler;
import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class ExtremeResultSlot extends ResultSlot {
    protected final ExtremeCraftingTableScreenHandler screenHandler;

     public ExtremeResultSlot(ExtremeCraftingTableScreenHandler screenHandler, Player player, CraftingContainer input, Container inventory, int index, int x, int y) {
            super(player, input, inventory, index, x, y);
            this.screenHandler = screenHandler;
        }

    @Override
    public void onTake(Player player, ItemStack stack) {
        this.checkTakeAchievements(stack);
        NonNullList<ItemStack> remainingStacks;

        if (screenHandler.cachedRecipe != null && screenHandler.cachedRecipe.matches(screenHandler.craftingInventory, player.getCommandSenderWorld())) {
            remainingStacks = screenHandler.cachedRecipe.getRemainingItems(screenHandler.craftingInventory);
        }
        else {
            remainingStacks = player.getCommandSenderWorld().getRecipeManager().getRemainingItemsFor(RecipeType.CRAFTING, screenHandler.craftingInventory, player.getCommandSenderWorld());
        }

        for (int i = 0; i < remainingStacks.size(); ++i) {
            ItemStack inventoryStack = screenHandler.craftingInventory.getItem(i);
            ItemStack remainingStack = remainingStacks.get(i);
            if (!inventoryStack.isEmpty()) {
                screenHandler.craftingInventory.removeItem(i, 1);
                inventoryStack = screenHandler.craftingInventory.getItem(i);
            }
            if (remainingStack.isEmpty()) continue;
            if (inventoryStack.isEmpty()) {
                screenHandler.craftingInventory.setItem(i, remainingStack);
                continue;
            }
            if (ItemStack.matches(inventoryStack, remainingStack) && ItemStack.isSameItem(inventoryStack, remainingStack)) {
                remainingStack.grow(inventoryStack.getCount());
                screenHandler.craftingInventory.setItem(i, remainingStack);
                continue;
            }
            if (screenHandler.player.getInventory().add(remainingStack)) continue;
            screenHandler.player.drop(remainingStack, false);
        }
    }
}