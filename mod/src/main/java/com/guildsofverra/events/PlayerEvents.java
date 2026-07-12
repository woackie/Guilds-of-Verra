package com.guildsofverra.events;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.player.PlayerManager;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

public final class PlayerEvents {

    private PlayerEvents() {
    }

    public static void initialize() {

        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {

            var player = handler.player;

            PlayerManager.getProfile(player.getUUID());

            GuildsOfVerra.LOGGER.info(
                    "Loaded profile for {}",
                    player.getGameProfile().getName()
            );

        });

    }
}