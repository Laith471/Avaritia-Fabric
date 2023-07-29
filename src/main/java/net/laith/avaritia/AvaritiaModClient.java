package net.laith.avaritia;

import net.fabricmc.api.ClientModInitializer;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.init.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AvaritiaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, ExtremeCraftingTableScreen::new);

    }
}
