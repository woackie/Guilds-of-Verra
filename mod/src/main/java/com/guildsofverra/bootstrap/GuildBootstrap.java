package com.guildsofverra.bootstrap;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.events.PlayerEvents;
import com.guildsofverra.player.PlayerManager;
import com.guildsofverra.skills.SkillManager;

public final class GuildBootstrap {

    private GuildBootstrap() {
    }

    public static void initialize() {

        GuildsOfVerra.LOGGER.info("====================================");
        GuildsOfVerra.LOGGER.info(" Guilds of Verra");
        GuildsOfVerra.LOGGER.info(" Version 0.0.4");
        GuildsOfVerra.LOGGER.info("====================================");

        SkillManager.initialize();
        PlayerManager.initialize();
        PlayerEvents.initialize();

        GuildsOfVerra.LOGGER.info("Bootstrap complete.");
    }
}