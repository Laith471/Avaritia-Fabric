package net.laith.avaritia.compat;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.recipe.ExtremeRecipeManager;
import net.laith.avaritia.compat.botania.Botania;
import net.laith.avaritia.compat.tc.TC;
import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.common.TinkerTabs;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.common.block.BotaniaBlock;
import vazkii.botania.common.block.BotaniaFlowerBlocks;

public class ModCompat {
    public static void compatify() {
        if (BooleanHelper.tc) {
            try {
                TC.init();
                ExtremeRecipeManager.getInstance().addRecipe(new ItemStack(TC.INFINITY_SHURIKEN),
                        "CCC    CC",
                        "CNNC  CNC",
                        "CNNNCCNC ",
                        " CNNINC  ",
                        "  CIBIC  ",
                        "  CNINNC ",
                        " CNCCNNNC",
                        "CNC  CNNC",
                        "CC    CCC",
                        'C', new ItemStack(ModItems.CRYSTAL_MATRIX_INGOT),
                        'N', new ItemStack(ModItems.NEUTRONIUM_INGOT),
                        'I', new ItemStack(ModItems.INFINITY_INGOT),
                        'B', new ItemStack(ModBlocks.INFINITY_BLOCK));
                ItemGroupEvents.modifyEntriesEvent(TinkerTabs.TAB_GADGETS).register(entries -> entries.accept(TC.INFINITY_SHURIKEN));
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia fell into smeltery");
            }
        }
        if(BooleanHelper.botania) {
            try {
                Botania.init();
                ExtremeRecipeManager.getInstance().addRecipe(new ItemStack(Botania.ASGARDANDELION),
                        "   III   ",
                        "  IIIII  ",
                        "  IIXII  ",
                        "  IIIII  ",
                        "   III   ",
                        " nn N nn ",
                        "nnnnNnnnn",
                        " nn N nn ",
                        "    N    ",
                        'I', new ItemStack(ModItems.INFINITY_INGOT),
                        'X', new ItemStack(ModItems.INFINITY_CATALYST),
                        'N', new ItemStack(ModItems.NEUTRONIUM_INGOT),
                        'n', new ItemStack(ModItems.NEUTRONIUM_NUGGET));
                ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.addAfter(BotaniaFlowerBlocks.shulkMeNot, Botania.ASGARDANDELION));
                ItemGroupEvents.modifyEntriesEvent(BotaniaRegistries.BOTANIA_TAB_KEY).register(entries -> entries.addAfter(Botania.ASGARDANDELION, Botania.FLOATING_ASGARDANDELION));
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia doesn't see the beauty of the nature");
            }
        }
    }
}
