package com.guildsofverra.events;

import com.guildsofverra.config.ConfigManager;
import com.guildsofverra.progression.ExperienceManager;
import com.guildsofverra.skills.SkillType;
import com.guildsofverra.world.PlacedBlockTracker;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public final class MiningEvents {

    private MiningEvents() {
    }

    public static void initialize() {
        PlayerBlockBreakEvents.AFTER.register(
                (world, player, position, blockState, blockEntity) -> {

                    if (!(player instanceof ServerPlayer serverPlayer)) {
                        return;
                    }

                    boolean playerPlaced = PlacedBlockTracker.consume(
                            world.dimension(),
                            position
                    );

                    if (playerPlaced) {
                        return;
                    }

                    ResourceLocation blockId =
                            BuiltInRegistries.BLOCK.getKey(blockState.getBlock());

                    int experience =
                            ConfigManager.getMiningExperience(blockId);

                    if (experience <= 0) {
                        return;
                    }

                    ExperienceManager.addExperience(
                            serverPlayer,
                            SkillType.MINING,
                            experience
                    );
                }
        );
    }
}