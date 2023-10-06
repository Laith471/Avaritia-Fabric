package net.laith.avaritia.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.laith.avaritia.client.render.EyeRenderer;
import net.laith.avaritia.client.render.WingRenderer;
import net.laith.avaritia.common.handler.InfinityHandler;
import net.laith.avaritia.util.TextUtil;
import net.laith.avaritia.util.events.JumpEvent;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.laith.avaritia.util.helpers.ToolHelper;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class ModEvents {

    public static class Server {
        public static void register() {
            ServerTickEvents.START_WORLD_TICK.register(world -> {
                for(var player: world.getPlayers()) {
                    InfinityHandler.serverAbilities(player);
                }
            });

            ServerLivingEntityEvents.ALLOW_DAMAGE.register(((entity, source, amount) -> {
                if(entity instanceof PlayerEntity player) {
                    return !BooleanHelper.isWearingTheFullArmor(player);
                }
                return true;
            }));

            PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
               ItemStack stack = player.getEquippedStack(EquipmentSlot.MAINHAND);
               if(stack.isOf(ModItems.INFINITY_PICKAXE)) {
                   if (stack.getOrCreateNbt().getBoolean("hammer")) {
                       if (state.isIn(ModTags.Blocks.INFINITY_PICKAXE)) {
                           ToolHelper.mineCube(player, world, ModTags.Blocks.INFINITY_PICKAXE);
                       }
                   }
               }
                   else if (stack.getOrCreateNbt().getBoolean("destroyer")) {
                       if (state.isIn(BlockTags.SHOVEL_MINEABLE)) {
                           ToolHelper.mineCube(player, world, BlockTags.SHOVEL_MINEABLE);
                       }
                   }
               return true;
            });
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Client {
        public static void register() {

           ClientTickEvents.START_WORLD_TICK.register(world -> {
                for (var player: world.getPlayers()) {
                    InfinityHandler.clientAbilities(player);
                }
            });


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

            ItemTooltipCallback.EVENT.register((stack, context, list) -> {
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