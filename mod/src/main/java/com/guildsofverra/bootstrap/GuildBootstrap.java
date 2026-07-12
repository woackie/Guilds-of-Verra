package com.guildsofverra.bootstrap;

import com.guildsofverra.GuildsOfVerra;

public final class GuildBootstrap {

    private GuildBootstrap() {
    }

    public static void initialize() {
        GuildsOfVerra.LOGGER.info("====================================");
        GuildsOfVerra.LOGGER.info(" Guilds of Verra");
        GuildsOfVerra.LOGGER.info(" Version 0.0.1");
        GuildsOfVerra.LOGGER.info(" Bootstrapping...");
        GuildsOfVerra.LOGGER.info("====================================");
    }
}