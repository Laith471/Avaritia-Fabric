package net.laith.avaritia.common.handler;


import dev.architectury.event.EventResult;
import dev.architectury.event.events.common.EntityEvent;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.laith.avaritia.init.ModArmorMaterials;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

public class ArmorHandler implements ServerTickEvents.StartTick, ServerLivingEntityEvents.AllowDamage {

    public void onServerTick(ServerPlayerEntity player) {
        // Check if the player is wearing the specific armor set

        if (isWearingChestplate(player)) {
            // Enable flying
            player.getAbilities().allowFlying = true;
            player.sendAbilitiesUpdate();
        } else if(!player.isCreative() && !player.isSpectator()){
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            player.sendAbilitiesUpdate();
        }
/*
        if (isWearingBoots(player)) {
            boolean flying = player.getAbilities().flying;
            boolean swimming = player.isSwimming();
            boolean sneaking = player.isSneaking();



                if (flying || swimming || sneaking) {
                    float speedMultiplier = 0.15f; // Adjust this value as needed
                    speedMultiplier *= flying ? 1.1f : 1.0f;
                    speedMultiplier *= swimming ? 1.2f : 1.0f;
                    speedMultiplier *= sneaking ? 0.1f : 1.0f;

                    Vec3d motion = player.getVelocity();

                    float moveForward = player.forwardSpeed;
                    float moveStrafing = player.sidewaysSpeed;


                    // Modify the player's velocity based on movement inputs and speed multiplier
                    if (moveForward > 0f) {
                        motion = motion.add(0, motion.y, speedMultiplier);
                    } else if (moveForward < 0f) {
                        motion = motion.add(0, motion.y, -speedMultiplier * 0.3f);
                    }

                    if (moveStrafing != 0f) {
                        motion = motion.add(speedMultiplier * Math.signum(moveStrafing), motion.y, 0);
                    }

                    player.setVelocity(motion);
                }
            }
        }

*/
    }

    private boolean isWearingTheFullArmor(PlayerEntity player) {
        // Implement your logic to check if the player is wearing the specific armor set
        // For example, you can check the player's equipped armor items
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetSlot = player.getEquippedStack(EquipmentSlot.FEET);

        // Example: Check if the player is wearing a full set of diamond armor
        return headSlot.getItem() instanceof ArmorItem &&
                chestSlot.getItem() instanceof ArmorItem &&
                legsSlot.getItem() instanceof ArmorItem &&
                feetSlot.getItem() instanceof ArmorItem &&
                ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY &&
                ((ArmorItem) chestSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY &&
                ((ArmorItem) legsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY&&
                ((ArmorItem) feetSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

   private boolean isWearingHelmet(PlayerEntity player) {
       ItemStack headSlot = player.getEquippedStack(EquipmentSlot.HEAD);
       return headSlot.getItem() instanceof ArmorItem && ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
   }

    private boolean isWearingChestplate(PlayerEntity player) {
        ItemStack headSlot = player.getEquippedStack(EquipmentSlot.CHEST);
        return headSlot.getItem() instanceof ArmorItem && ((ArmorItem) headSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    private boolean isWearingLeggings(PlayerEntity player) {
        ItemStack legsSlot = player.getEquippedStack(EquipmentSlot.LEGS);
        return legsSlot.getItem() instanceof ArmorItem && ((ArmorItem) legsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }

    private boolean isWearingBoots(PlayerEntity player) {
        ItemStack bootsSlot = player.getEquippedStack(EquipmentSlot.FEET);
        return bootsSlot.getItem() instanceof ArmorItem && ((ArmorItem) bootsSlot.getItem()).getMaterial() == ModArmorMaterials.INFINITY;
    }


    @Override
    public void onStartTick(MinecraftServer server) {
        for (ServerPlayerEntity player: server.getPlayerManager().getPlayerList()) {
            onServerTick(player);
        }
    }

    @Override
    public boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        if(entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if(isWearingTheFullArmor(player)) {
                return false;
            }
        }
            return true;
    }
}

