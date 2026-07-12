package com.guildsofverra.requirements;

import com.guildsofverra.integration.PuffishSkillsIntegration;
import com.guildsofverra.skills.SkillType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

public final class RequirementManager {

    private RequirementManager() {
    }

    public static boolean canUse(ServerPlayer player, Item item) {

        // Diamond Pickaxe requires Mining 20
        if (item.toString().contains("diamond_pickaxe")) {
            return PuffishSkillsIntegration.getLevel(player, SkillType.MINING) >= 20;
        }

        return true;
    }
}