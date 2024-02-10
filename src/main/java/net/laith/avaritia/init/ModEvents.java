package net.laith.avaritia.init;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.laith.avaritia.client.render.EyeRenderer;
import net.laith.avaritia.client.render.WingRenderer;
import net.laith.avaritia.common.handler.InfinityHandler;
import net.laith.avaritia.util.TextUtil;
import net.laith.avaritia.util.events.JumpEvent;
import net.laith.avaritia.util.helpers.BooleanHelper;
import net.laith.avaritia.util.helpers.ToolHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class ModEvents {

    public static class Server {
        public static void register() {
            ServerTickEvents.START_WORLD_TICK.register(world -> {
                for(var player: world.players()) {
                    InfinityHandler.serverAbilities(player);
                }
            });

            ServerLivingEntityEvents.ALLOW_DAMAGE.register(((entity, source, amount) -> {
                if(entity instanceof Player player) {
                    return !BooleanHelper.isWearingTheFullArmor(player);
                }
                return true;
            }));

            AttackEntityCallback.EVENT.register((attacker, world, hand, entity, hitResult) -> {
                if(attacker.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.INFINITY_SWORD)) {
                    if (!entity.getCommandSenderWorld().isClientSide && entity instanceof Player victim) {
                        if (victim.isCreative() && !victim.isDeadOrDying() && victim.getHealth() > 0 && !BooleanHelper.isWearingTheFullArmor(victim)) {
                            victim.getCombatTracker().recordDamage(attacker.damageSources().source(ModDamageTypes.INFINITY, attacker, victim), victim.getHealth());
                            victim.setHealth(0);
                            victim.die(attacker.damageSources().source(ModDamageTypes.INFINITY, attacker, victim));
                            return InteractionResult.SUCCESS;
                        }
                    }
                }
                return InteractionResult.PASS;
            });

            PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
               ItemStack stack = player.getItemBySlot(EquipmentSlot.MAINHAND);
               if(stack.is(ModItems.INFINITY_PICKAXE)) {
                   if (stack.getOrCreateTag().getBoolean("hammer")) {
                       if (state.is(ModTags.Blocks.INFINITY_PICKAXE)) {
                           ToolHelper.mineCube(player, world, ModTags.Blocks.INFINITY_PICKAXE);
                       }
                   }
               }
                   else if (stack.getOrCreateTag().getBoolean("destroyer")) {
                       if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
                           ToolHelper.mineCube(player, world, BlockTags.MINEABLE_WITH_SHOVEL);
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
                for (var player: world.players()) {
                    InfinityHandler.clientAbilities(player);
                }
            });


            LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
                if (entityRenderer instanceof PlayerRenderer playerRenderer) {
                    registrationHelper.register(new WingRenderer<>(playerRenderer));
                    registrationHelper.register(new EyeRenderer(playerRenderer));
                }
            });

            JumpEvent.ON_NORMAL.register(entity -> {
                if (entity.getCommandSenderWorld().isClientSide && entity instanceof Player player) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        player.addDeltaMovement(new Vec3(0, 0.5, 0));
                    }
                }
            });

            JumpEvent.ON_SPRINT.register(entity -> {
                if (entity.getCommandSenderWorld().isClientSide && entity instanceof Player player) {
                    if (BooleanHelper.isWearingBoots(player)) {
                        player.addDeltaMovement(new Vec3(0, 0.08, 0));
                    }
                }
            });

            ItemTooltipCallback.EVENT.register((stack, context, list) -> {
                if (stack.getItem() == ModItems.INFINITY_SWORD) {
                        int s = 0;
                        if (stack.isEnchanted()) {
                            List a = stack.getEnchantmentTags().stream().toList();
                            s = a.size();
                        }
                        list.set(3 + s, Component.nullToEmpty(TextUtil.makeFabulous("+Infinite") + ChatFormatting.DARK_GREEN + " Attack Damage"));
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