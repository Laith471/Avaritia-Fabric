package net.laith.avaritia.common.recipe;

import net.laith.avaritia.AvaritiaMod;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.*;

public class ExtremeRecipeManager {

    private static final ExtremeRecipeManager instance = new ExtremeRecipeManager();

    public static final List<ExtremeRecipe> recipes = new LinkedList<>();

    public static ExtremeRecipeManager getInstance() {
        return instance;

    }

    public void addRecipe(ItemStack result, Object... recipe) {
        StringBuilder str = new StringBuilder();
        int i = 0;
        int width = 0;
        int height = 0;

        if (recipe[i] instanceof String[]) {
            String[] astring = (String[]) recipe[i++];

            for (String s1 : astring) {
                ++height;
                width = s1.length();
                str.append(s1);
            }
        } else {
            while (recipe[i] instanceof String) {
                String s2 = (String) recipe[i++];
                ++height;
                width = s2.length();
                str.append(s2);
            }
        }

        Map<Character, Ingredient> hashmap = new HashMap<>();

        for (; i < recipe.length; i += 2) {
            Character character = (Character) recipe[i];
            Ingredient ingredient;

            if (recipe[i + 1] instanceof Item) {
                ingredient = Ingredient.of(new ItemStack((Item) recipe[i + 1]));
            } else if (recipe[i + 1] instanceof Block) {
                ingredient = Ingredient.of(new ItemStack((Block) recipe[i + 1]));
            } else if (recipe[i + 1] instanceof ItemStack) {
                ingredient = Ingredient.of((ItemStack) recipe[i + 1]);
            } else ingredient = Ingredient.EMPTY;

            hashmap.put(character, ingredient);
        }

        NonNullList<Ingredient> ingredients = NonNullList.create();

        char[] s = str.toString().toCharArray();

        for (int i1 = 0; i1 < width * height; ++i1) {
            char c0 = s[i1];
            for (Character character : hashmap.keySet()) {
                if (c0 == character) {
                    ingredients.add(hashmap.get(character));
                }
            }

            if (c0 == ' ')
                ingredients.add(Ingredient.EMPTY);

            ExtremeCraftingShapedRecipe shapedrecipes = new ExtremeCraftingShapedRecipe(new ResourceLocation(AvaritiaMod.MOD_ID, result.getItem().toString()), "", width, height, ingredients, result);
            recipes.add(shapedrecipes);
        }
    }
}
