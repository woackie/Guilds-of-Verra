package com.guildsofverra.events;

import com.guildsofverra.world.PlacedBlockTracker;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;

public final class BlockPlacementEvents {

    private BlockPlacementEvents() {
    }

    public static void initialize() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClientSide() || !(player instanceof ServerPlayer serverPlayer)) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);

            if (!(stack.getItem() instanceof BlockItem blockItem)) {
                return InteractionResult.PASS;
            }

            BlockPlaceContext context =
                    new BlockPlaceContext(player, hand, stack, hitResult);

            BlockPos placementPosition = context.getClickedPos().immutable();
            Block expectedBlock = blockItem.getBlock();
            MinecraftServer server = serverPlayer.getServer();

            if (server == null) {
                return InteractionResult.PASS;
            }

            /*
             * UseBlockCallback runs before vanilla finishes placing the block.
             * Queueing this check lets vanilla finish first, so we only mark a
             * position when the expected block was actually placed.
             */
            server.execute(() -> {
                BlockState placedState = world.getBlockState(placementPosition);

                if (placedState.is(expectedBlock)) {
                    PlacedBlockTracker.markPlaced(
                            world.dimension(),
                            placementPosition
                    );
                }
            });

            return InteractionResult.PASS;
        });
    }
}
