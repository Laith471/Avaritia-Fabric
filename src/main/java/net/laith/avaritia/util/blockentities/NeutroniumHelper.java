package net.laith.avaritia.util.blockentities;

import net.laith.avaritia.common.blockentity.NeutroniumCompressorBlockEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class NeutroniumHelper {


    public static void shrink(SimpleInventory simpleInventory, NeutroniumCompressorBlockEntity entity) {
        List<ItemStack> matchingInputStacks = getMatchingInputStacks(simpleInventory, entity);
        //check if the matching isn't empty and if its return try its mean that getMatchingInputStacks methode has an item
        if (!matchingInputStacks.isEmpty()) {
            ItemStack inputItem = matchingInputStacks.get(0); // Get the first matching input item from the list
            simpleInventory.removeItem(inputItem.getItem(), 1);
            entity.progress++;
        }
    }

    public static void craftItem(NeutroniumCompressorBlockEntity entity, World world) {
        ItemStack outputItem = entity.recipe.getA(); // Get the output item from the recipe
        entity.inventory.set(1, new ItemStack(outputItem.getItem(), 1 + entity.inventory.get(1).getCount()));

        entity.progress = 0;
    }
    public static boolean checker(SimpleInventory simpleInventory, NeutroniumCompressorBlockEntity entity, World world) {
        List<ItemStack> matchingInputStacks = getMatchingInputStacks(simpleInventory, entity);
        ItemStack outputItem = entity.recipe.getOutput(world.getRegistryManager()); // Get the output item from the recipe

        return !matchingInputStacks.isEmpty() &&
                simpleInventory.getStack(0).getItem() == matchingInputStacks.get(0).getItem()
                && (simpleInventory.getStack(1).getItem() == outputItem.getItem() || simpleInventory.getStack(1).getItem() == ItemStack.EMPTY.getItem());
    }

    private static List<ItemStack> getMatchingInputStacks(SimpleInventory simpleInventory, NeutroniumCompressorBlockEntity entity) {
        List<ItemStack> matchingInputStacks = new ArrayList<>();
        //iterate through the ingredients
        for (ItemStack inputStack : entity.recipe.getInput().getMatchingStacks()) {
            for (int i = 0; i < simpleInventory.size(); i++) {
                //create an ItemStack that equals the ItemStack simple inventory in slot 0
                ItemStack inventoryStack = simpleInventory.getStack(i);
                //if the inventoryStack isn't empty and inputStack equals the inventoryStack
                if (!inventoryStack.isEmpty() && inputStack.getItem() == inventoryStack.getItem()) {
                    //if it's true then we add inventorStack to the list
                    matchingInputStacks.add(inventoryStack);
                    break; // Only add the first matching stack to the list
                }
            }
        }
        return matchingInputStacks;
        //finally we return a List of ItemStack, and we will only get the first index
    }
    public static void  setCachedValues(NeutroniumCompressorBlockEntity entity, World world) {
        entity.cachedId = entity.recipe.getId();
        entity.cachedInput = entity.recipe.getInput();
        entity.cachedCost = entity.recipe.getCost();
        entity.cachedOutput = entity.recipe.getOutput(world.getRegistryManager());
    }
    public static ItemStack loadItemStack(NbtCompound tag) {
        return ItemStack.fromNbt(tag);
    }
    public static NbtCompound saveItemStack(ItemStack itemStack) {
        NbtCompound tag = new NbtCompound();
        itemStack.getNbt();
        return tag;
    }
}
