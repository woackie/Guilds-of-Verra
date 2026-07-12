package com.guildsofverra.skills;

import com.guildsofverra.GuildsOfVerra;

public final class SkillManager {

    private SkillManager() {
    }

    public static void initialize() {

        GuildsOfVerra.LOGGER.info("Registering skills...");

        for (SkillType type : SkillType.values()) {

            GuildsOfVerra.LOGGER.info("- {}", type.getDisplayName());

        }

    }

}