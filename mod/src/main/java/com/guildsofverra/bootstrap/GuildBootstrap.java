package com.guildsofverra.bootstrap;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.skills.SkillManager;

public final class GuildBootstrap {

    private GuildBootstrap() {
    }

    public static void initialize() {
        GuildsOfVerra.LOGGER.info("====================================");
        GuildsOfVerra.LOGGER.info(" Guilds of Verra");
        GuildsOfVerra.LOGGER.info(" Version 0.0.2");
        GuildsOfVerra.LOGGER.info(" Bootstrapping...");
        GuildsOfVerra.LOGGER.info("====================================");

        SkillManager.initialize();
    }
}