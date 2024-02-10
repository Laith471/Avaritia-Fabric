package net.laith.avaritia.util.blockentities;

import net.laith.avaritia.common.blockentity.NeutroniumCompressorBlockEntity;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class NeutroniumHelper {


    public static void shrink(SimpleContainer simpleContainer, NeutroniumCompressorBlockEntity entity) {
        List<ItemStack> matchingInputStacks = getMatchingInputStacks(simpleContainer, entity);
        //check if the matching isn't empty and if its return try its mean that getMatchingInputStacks methode has an item
        if (!matchingInputStacks.isEmpty()) {
            ItemStack inputItem = matchingInputStacks.get(0); // Get the first matching input item from the list
            simpleContainer.removeItemType(inputItem.getItem(), 1);
            entity.progress++;
        }
    }

    public static void craftItem(NeutroniumCompressorBlockEntity entity, Level level) {
        ItemStack outputItem = entity.recipe.getA(); // Get the output item from the recipe
        entity.inventory.set(1, new ItemStack(outputItem.getItem(), 1 + entity.inventory.get(1).getCount()));

        entity.progress = 0;
    }
    public static boolean checker(SimpleContainer simpleContainer, NeutroniumCompressorBlockEntity entity, Level level) {
        List<ItemStack> matchingInputStacks = getMatchingInputStacks(simpleContainer, entity);
        ItemStack outputItem = entity.recipe.getResultItem(level.registryAccess()); // Get the output item from the recipe

        return !matchingInputStacks.isEmpty() &&
                simpleContainer.getItem(0).getItem() == matchingInputStacks.get(0).getItem()
                && (simpleContainer.getItem(1).getItem() == outputItem.getItem() || simpleContainer.getItem(1).getItem() == ItemStack.EMPTY.getItem());
    }

    private static List<ItemStack> getMatchingInputStacks(SimpleContainer simpleContainer, NeutroniumCompressorBlockEntity entity) {
        List<ItemStack> matchingInputStacks = new ArrayList<>();
        //iterate through the ingredients
        for (ItemStack inputStack : entity.recipe.getInput().getItems()) {
            for (int i = 0; i < simpleContainer.getContainerSize(); i++) {
                //create an ItemStack that equals the ItemStack simple inventory in slot 0
                ItemStack inventoryStack = simpleContainer.getItem(i);
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
    public static void  setCachedValues(NeutroniumCompressorBlockEntity entity, Level level) {
        entity.cachedId = entity.recipe.getId();
        entity.cachedInput = entity.recipe.getInput();
        entity.cachedCost = entity.recipe.getCost();
        entity.cachedOutput = entity.recipe.getResultItem((level.registryAccess()));
    }
    public static ItemStack loadItemStack(CompoundTag tag) {
        return ItemStack.of(tag);
    }
    public static CompoundTag saveItemStack(ItemStack itemStack) {
        CompoundTag tag = new CompoundTag();
        itemStack.getTag();
        return tag;
    }
}
