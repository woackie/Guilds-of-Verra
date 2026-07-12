package com.guildsofverra.player;

import com.guildsofverra.GuildsOfVerra;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerManager {

    private static final Map<UUID, PlayerProfile> PLAYERS = new HashMap<>();

    private PlayerManager() {
    }

    public static void initialize() {
        GuildsOfVerra.LOGGER.info("Player Manager initialized.");
    }

    public static PlayerProfile getProfile(UUID uuid) {

        return PLAYERS.computeIfAbsent(uuid, id -> {

            GuildsOfVerra.LOGGER.info("Creating new player profile.");

            return new PlayerProfile(id);

        });

    }

}