package net.laith.avaritia;

import net.fabricmc.api.ClientModInitializer;
import net.laith.avaritia.client.screen.ExtremeCraftingTableScreen;
import net.laith.avaritia.client.screen.NeutronCollectorScreen;
import net.laith.avaritia.client.screen.NeutroniumCompressorScreen;
import net.laith.avaritia.common.item.tools.InfinityPickaxe;
import net.laith.avaritia.init.ModItems;
import net.laith.avaritia.init.ModScreenHandlers;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class AvaritiaModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.EXTREME_CRAFTING_TABLE_SCREEN_HANDLER, ExtremeCraftingTableScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRON_COLLECTOR_SCREEN_HANDLER, NeutronCollectorScreen::new);
        HandledScreens.register(ModScreenHandlers.NEUTRONIUM_COMPRESSOR_SCREEN_HANDLER, NeutroniumCompressorScreen::new);

        ModelPredicateProviderRegistry.register(ModItems.INFINITY_PICKAXE, new Identifier("hammer"), (stack, world, entity, seed) -> stack.hasNbt() && stack.getNbt().getBoolean("hammer") ? 1 : 0);
        ModelPredicateProviderRegistry.register(ModItems.INFINITY_SHOVEL, new Identifier("destroyer"), (stack, world, entity, seed) -> stack.hasNbt() && stack.getNbt().getBoolean("destroyer") ? 1 : 0);
    }
}
