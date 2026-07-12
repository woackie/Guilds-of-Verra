package com.guildsofverra.world;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class PlacedBlockTracker {

    private static final Set<TrackedPosition> PLAYER_PLACED_BLOCKS =
            new HashSet<>();

    private PlacedBlockTracker() {
    }

    public static void markPlaced(
            ResourceKey<Level> dimension,
            BlockPos position
    ) {
        PLAYER_PLACED_BLOCKS.add(
                new TrackedPosition(dimension, position.immutable())
        );
    }

    public static boolean consume(
            ResourceKey<Level> dimension,
            BlockPos position
    ) {
        return PLAYER_PLACED_BLOCKS.remove(
                new TrackedPosition(dimension, position.immutable())
        );
    }

    private record TrackedPosition(
            ResourceKey<Level> dimension,
            BlockPos position
    ) {
        private TrackedPosition {
            Objects.requireNonNull(dimension, "dimension");
            Objects.requireNonNull(position, "position");
        }
    }
}
