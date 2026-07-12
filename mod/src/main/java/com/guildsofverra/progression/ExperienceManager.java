package com.guildsofverra.progression;

import com.guildsofverra.GuildsOfVerra;
import com.guildsofverra.player.PlayerManager;
import com.guildsofverra.player.PlayerProfile;
import com.guildsofverra.skills.Skill;
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

        PlayerProfile profile =
                PlayerManager.getProfile(player.getUUID());

        Skill skill = profile.getSkill(skillType);

        if (skill == null) {
            GuildsOfVerra.LOGGER.error(
                    "Player profile {} has no {} skill.",
                    player.getUUID(),
                    skillType
            );
            return;
        }

        skill.addExperience(amount);

        player.displayClientMessage(
                Component.literal(
                        skillType.getDisplayName()
                                + " +"
                                + amount
                                + " XP"
                ),
                true
        );

        GuildsOfVerra.LOGGER.debug(
                "{} gained {} {} XP (total: {}).",
                player.getGameProfile().getName(),
                amount,
                skillType.getDisplayName(),
                skill.getExperience()
        );
    }
}
