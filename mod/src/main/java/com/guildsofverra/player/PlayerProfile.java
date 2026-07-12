package com.guildsofverra.player;

import com.guildsofverra.skills.Skill;
import com.guildsofverra.skills.SkillRegistry;
import com.guildsofverra.skills.SkillType;

import java.util.Map;
import java.util.UUID;

public class PlayerProfile {

    private final UUID uuid;
    private final Map<SkillType, Skill> skills;

    public PlayerProfile(UUID uuid) {
        this.uuid = uuid;
        this.skills = SkillRegistry.createDefaultSkills();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Skill getSkill(SkillType type) {
        return skills.get(type);
    }

    public Map<SkillType, Skill> getSkills() {
        return skills;
    }
}