package net.laith.avaritia.compat;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.laith.avaritia.AvaritiaMod;
import net.laith.avaritia.common.blockentity.ExtremeCraftingTableBlockEntity;
import net.laith.avaritia.common.blockentity.MatterClusterBlockEntity;
import net.laith.avaritia.common.recipe.ExtremeRecipeManager;
import net.laith.avaritia.compat.botania.Botania;
import net.laith.avaritia.compat.botania.flowerblock.AsgardandelionBlockEntity;
import net.laith.avaritia.compat.tc.TC;
import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import slimeknights.tconstruct.common.TinkerTabs;

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
        if(FabricLoader.getInstance().isModLoaded("botania")) {
            Botania.init();
        }
    }
}
