package com.guildsofverra.progression;

import com.guildsofverra.integration.PuffishSkillsIntegration;
import com.guildsofverra.skills.SkillType;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

public final class ExperienceManager {

    private ExperienceManager() {
    }

    public static void addExperience(
            ServerPlayer player,
            SkillType skillType,
            int amount
    ) {
        if (amount <= 0) {
            return;
        }

        boolean awarded = PuffishSkillsIntegration.addExperience(
                player,
                skillType,
                amount
        );

        if (!awarded) {
            player.displayClientMessage(
                    Component.literal(
                            skillType.getDisplayName()
                                    + " category is not loaded"
                    ),
                    true
            );
            return;
        }

        player.displayClientMessage(
                Component.literal(
                        skillType.getDisplayName()
                                + " +"
                                + amount
                                + " XP"
                ),
                true
        );
    }

    public static int getLevel(
            ServerPlayer player,
            SkillType skillType
    ) {
        return PuffishSkillsIntegration.getLevel(player, skillType);
    }

    public static int getTotalExperience(
            ServerPlayer player,
            SkillType skillType
    ) {
        return PuffishSkillsIntegration.getTotalExperience(
                player,
                skillType
        );
    }
}
