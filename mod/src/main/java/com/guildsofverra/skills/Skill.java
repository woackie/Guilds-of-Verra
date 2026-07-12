package com.guildsofverra.skills;

public class Skill {

    private final SkillType type;

    private int level;

    private int experience;

    public Skill(SkillType type) {
        this.type = type;
        this.level = 1;
        this.experience = 0;
    }

    public SkillType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getExperience() {
        return experience;
    }

    public void addExperience(int amount) {
        experience += amount;
    }
}