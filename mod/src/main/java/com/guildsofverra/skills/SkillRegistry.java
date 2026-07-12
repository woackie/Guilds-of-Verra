package com.guildsofverra.skills;

import java.util.EnumMap;
import java.util.Map;

public final class SkillRegistry {

    private SkillRegistry() {
    }

    public static Map<SkillType, Skill> createDefaultSkills() {

        Map<SkillType, Skill> skills = new EnumMap<>(SkillType.class);

        for (SkillType type : SkillType.values()) {
            skills.put(type, new Skill(type));
        }

        return skills;
    }
}