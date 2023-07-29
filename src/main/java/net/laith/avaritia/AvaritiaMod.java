package net.laith.avaritia;

import net.fabricmc.api.ModInitializer;

import net.laith.avaritia.init.ModBlocks;
import net.laith.avaritia.init.ModBlockEntities;
import net.laith.avaritia.init.ModItemGroup;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.init.ModRecipes;
import net.laith.avaritia.init.ModScreenHandlers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AvaritiaMod implements ModInitializer {
	public static final String MOD_ID = "avaritia";
    public static final Logger LOGGER = LoggerFactory.getLogger("avaritia");

	@Override
	public void onInitialize() {
		ModItemGroup.registerGroupItem();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();
		ModRecipes.registerRecipes();

		LOGGER.info("Hello Fabric world!");
	}
}