package com.guildsofverra.skills;

public enum SkillType {

    FIGHTING("Fighting"),
    MINING("Mining"),
    ADVENTURING("Adventuring"),
    FARMING("Farming"),
    SURVIVAL("Survival");

    private final String displayName;

    SkillType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}