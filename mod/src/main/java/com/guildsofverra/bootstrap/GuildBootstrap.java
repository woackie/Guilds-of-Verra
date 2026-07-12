package com.guildsofverra.bootstrap;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.config.ConfigManager;
import com.guildsofverra.events.BlockPlacementEvents;
import com.guildsofverra.events.MiningEvents;
import com.guildsofverra.events.PlayerEvents;
import com.guildsofverra.integration.PuffishSkillsIntegration;
import com.guildsofverra.player.PlayerManager;
import com.guildsofverra.skills.SkillManager;

public final class GuildBootstrap {

    private GuildBootstrap() {
    }

    public static void initialize() {
        GuildsOfVerra.LOGGER.info("====================================");
        GuildsOfVerra.LOGGER.info(" Guilds of Verra");
        GuildsOfVerra.LOGGER.info(" Version 0.0.6");
        GuildsOfVerra.LOGGER.info("====================================");

        ConfigManager.initialize();
        SkillManager.initialize();
        PlayerManager.initialize();
        PuffishSkillsIntegration.initialize();

        PlayerEvents.initialize();
        BlockPlacementEvents.initialize();
        MiningEvents.initialize();

        GuildsOfVerra.LOGGER.info("Bootstrap complete.");
    }
}
