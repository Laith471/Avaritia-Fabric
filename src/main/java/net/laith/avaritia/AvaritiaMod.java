package net.laith.avaritia;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.common.handler.ArmorHandler;
import net.laith.avaritia.init.*;
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

		ServerTickEvents.START_SERVER_TICK.register(new ArmorHandler());
		ServerLivingEntityEvents.ALLOW_DAMAGE.register(new ArmorHandler());

		LOGGER.info("Hello Fabric world!");
	}
}