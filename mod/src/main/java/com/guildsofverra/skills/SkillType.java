package com.guildsofverra.skills;

import com.guildsofverra.GuildsOfVerra;
import net.minecraft.resources.ResourceLocation;

public enum SkillType {

    FIGHTING("Fighting", "fighting"),
    MINING("Mining", "mining"),
    ADVENTURING("Adventuring", "adventuring"),
    FARMING("Farming", "farming"),
    SURVIVAL("Survival", "survival");

    private final String displayName;
    private final ResourceLocation categoryId;

    SkillType(String displayName, String categoryPath) {
        this.displayName = displayName;
        this.categoryId = GuildsOfVerra.id(categoryPath);
    }

    public String getDisplayName() {
        return displayName;
    }

    public ResourceLocation getCategoryId() {
        return categoryId;
    }
}
