package com.guildsofverra.integration;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.skills.SkillType;
import net.minecraft.server.level.ServerPlayer;
import net.puffish.skillsmod.api.Category;
import net.puffish.skillsmod.api.Experience;
import net.puffish.skillsmod.api.SkillsAPI;

import java.util.EnumSet;
import java.util.Optional;
import java.util.Set;

public final class PuffishSkillsIntegration {

    private static final Set<SkillType> REPORTED_MISSING_CATEGORIES =
            EnumSet.noneOf(SkillType.class);

    private PuffishSkillsIntegration() {
    }

    public static void initialize() {
        GuildsOfVerra.LOGGER.info("Pufferfish's Skills integration initialized.");
    }

    public static boolean addExperience(
            ServerPlayer player,
            SkillType skillType,
            int amount
    ) {
        if (amount <= 0) {
            return false;
        }

        Optional<Category> category =
                SkillsAPI.getCategory(skillType.getCategoryId());

        if (category.isEmpty()) {
            reportMissingCategory(skillType);
            return false;
        }

        Optional<Experience> experience = category.get().getExperience();

        if (experience.isEmpty()) {
            GuildsOfVerra.LOGGER.warn(
                    "Puffish category {} has no experience configuration.",
                    skillType.getCategoryId()
            );
            return false;
        }

        experience.get().addTotal(player, amount);
        return true;
    }

    public static int getLevel(
            ServerPlayer player,
            SkillType skillType
    ) {
        return SkillsAPI.getCategory(skillType.getCategoryId())
                .flatMap(Category::getExperience)
                .map(experience -> experience.getLevel(player))
                .orElse(0);
    }

    public static int getTotalExperience(
            ServerPlayer player,
            SkillType skillType
    ) {
        return SkillsAPI.getCategory(skillType.getCategoryId())
                .flatMap(Category::getExperience)
                .map(experience -> experience.getTotal(player))
                .orElse(0);
    }

    private static void reportMissingCategory(SkillType skillType) {
        if (REPORTED_MISSING_CATEGORIES.add(skillType)) {
            GuildsOfVerra.LOGGER.warn(
                    "Puffish category {} is not loaded. "
                            + "Install the Guilds of Verra skills datapack "
                            + "or change the category mapping.",
                    skillType.getCategoryId()
            );
        }
    }
}
