package com.guildsofverra.requirements;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResultHolder;

public final class RequirementEvents {

    private RequirementEvents() {
    }

    public static void initialize() {

        UseItemCallback.EVENT.register((player, world, hand) -> {

            if (!(player instanceof ServerPlayer serverPlayer)) {
                return InteractionResultHolder.pass(player.getItemInHand(hand));
            }

            if (!RequirementManager.canUse(serverPlayer, player.getItemInHand(hand).getItem())) {

                serverPlayer.displayClientMessage(
                        Component.literal("§cRequires Mining Level 20"),
                        true
                );

                return InteractionResultHolder.fail(player.getItemInHand(hand));
            }

            return InteractionResultHolder.pass(player.getItemInHand(hand));
        });
    }
}