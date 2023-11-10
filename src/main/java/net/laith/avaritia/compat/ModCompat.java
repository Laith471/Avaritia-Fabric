package net.laith.avaritia.compat;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.recipe.ExtremeRecipeManager;
import net.laith.avaritia.compat.tc.TC;
import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import slimeknights.tconstruct.common.TinkerTabs;
import slimeknights.tconstruct.gadgets.TinkerGadgets;

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
                ItemGroupEvents.modifyEntriesEvent(TinkerTabs.TAB_GADGETS).register(entries -> entries.add(TC.INFINITY_SHURIKEN));
            } catch (Throwable e) {
                AvaritiaMod.LOGGER.info("Avaritia fell into smeltery");
            }
        }
    }
}
