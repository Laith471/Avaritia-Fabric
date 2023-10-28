package net.laith.avaritia;

import com.mojang.authlib.GameProfile;
import me.x150.renderer.objfile.ObjFile;
import net.fabricmc.api.ModInitializer;

import net.laith.avaritia.init.*;
import net.minecraft.sound.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

public class AvaritiaMod implements ModInitializer {
	public static final String MOD_ID = "avaritia";
	public static final GameProfile avaritiaFakePlayer = new GameProfile(UUID.fromString("32283731-bbef-487c-bb69-c7e32f84ed27"), "[Avaritia]");
	public static final Logger LOGGER = LoggerFactory.getLogger("avaritia");

    @Override
	public void onInitialize() {
		ModEvents.Server.register();
		ModItems.registerModItems();
		ModEntities.registerEntities();
		ModItemGroup.registerGroupItem();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerAllScreenHandlers();
		ModRecipes.registerRecipes();
		ModSounds.registerSounds();

		LOGGER.info("Hello Fabric world!");
	}
}