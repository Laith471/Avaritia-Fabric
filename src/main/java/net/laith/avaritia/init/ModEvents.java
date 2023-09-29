package net.laith.avaritia.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.client.render.EyeRenderer;
import net.laith.avaritia.client.render.WingRenderer;
import net.laith.avaritia.common.handler.InfinityHandler;
import net.laith.avaritia.util.TextUtil;
import net.laith.avaritia.util.events.JumpEvent;
import net.laith.avaritia.util.events.TooltipEvent;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ModEvents {

    public static class Server {
        public static void register() {
            ServerTickEvents.START_SERVER_TICK.register(new InfinityHandler.Server());

            ServerLivingEntityEvents.ALLOW_DAMAGE.register(new InfinityHandler.Server());

        }
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void register() {

            ClientTickEvents.START_CLIENT_TICK.register(new InfinityHandler.Client());

            LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
                if (entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                    registrationHelper.register(new WingRenderer<>(playerEntityRenderer));
                    registrationHelper.register(new EyeRenderer(playerEntityRenderer));
                }
            });

            JumpEvent.ON_NORMAL.register(entity -> {
                if (entity.getWorld().isClient && entity instanceof PlayerEntity player) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        player.addVelocity(0, 0.5, 0);
                    }
                }
            });

            JumpEvent.ON_SPRINT.register(entity -> {
                if (entity.getWorld().isClient && entity instanceof PlayerEntity player) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        player.addVelocity(0, 0.08, 0);
                    }
                }
            });

            TooltipEvent.ON_EQUIPMENT_SLOT.register((stack, list) -> {
                if (stack.getItem() == ModItems.INFINITY_SWORD) {
                        int s = 0;
                        if (stack.hasEnchantments()) {
                            List a = stack.getEnchantments().stream().toList();
                            s = a.size();
                        }
                        list.set(3 + s, Text.of(TextUtil.makeFabulous("+Infinite") + Formatting.DARK_GREEN + " Attack Damage"));
                        return;
                }

                TextUtil.armorTooltip(stack, ModItems.INFINITY_HELMET, TextUtil.makeFairiec("+DRACONIC"), "Eyes", list);
                TextUtil.armorTooltip(stack, ModItems.INFINITY_CHESTPLATE, TextUtil.makeAngelic("+ANGEL"), "Wings", list);
                TextUtil.armorTooltip(stack, ModItems.INFINITY_LEGGINGS, TextUtil.makeDemonic("+DEMONIC"), "Protection", list);
                TextUtil.armorTooltip(stack, ModItems.INFINITY_BOOTS, TextUtil.makeSANIC("+SANIC"), "Speed", list);
            });
        }
    }
}