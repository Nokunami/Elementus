package net.nokunami.elementus.event;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import net.nokunami.elementus.common.registry.EBlocks;
import net.nokunami.elementus.common.registry.EEntityTypes;

import java.util.function.Predicate;

public class GolemSpawnUtils {
    public static final Predicate<BlockState> PUMPKINS_PREDICATE = (block) -> block != null && (block.is(Blocks.CARVED_PUMPKIN) || block.is(Blocks.JACK_O_LANTERN));

    public static BlockPattern createAstaliteGolemCarrier() {
        return BlockPatternBuilder.start().aisle(
                        "~^~",
                        "###",
                        "###")
                .where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE))
                .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(EBlocks.ASTALITE_BLOCK.get())))
                .where('~', (state) -> state.getState().isAir()).build();
    }

    public static BlockPattern createAstaliteGolemLongarm() {
        return BlockPatternBuilder.start().aisle(
                        "~^~",
                        "###",
                        "~#~")
                .where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE))
                .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(EBlocks.ASTALITE_BLOCK.get())))
                .where('~', (state) -> state.getState().isAir()).build();
    }

    public static void createAstaliteCarrier(Entity blockPlacer, Level level, BlockPos pos) {
        BlockPattern carrierPattern = createAstaliteGolemCarrier();
        BlockPattern.BlockPatternMatch carrierPatternMatch = carrierPattern.find(level, pos);
        if (carrierPatternMatch != null) {
            // clearPatternBlocks in CarvedPumpkinBlock.java
            for (int j = 0; j < carrierPattern.getWidth(); ++j) {
                for (int k = 0; k < carrierPattern.getHeight(); ++k) {
                    BlockInWorld blockinworld = carrierPatternMatch.getBlock(j, k, 0);
                    level.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                    level.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
                }
            }
            pos = carrierPatternMatch.getBlock(1, 2, 0).getPos();
            AstaliteGolem golem = EEntityTypes.ASTALITE_GOLEM_CARRIER.get().create(level);
            assert golem != null;
            golem.setPlayerCreated(true);
            if (blockPlacer instanceof Player player) golem.tame(player);
            golem.moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.05D, (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
            level.addFreshEntity(golem);

            for (ServerPlayer serverplayer1 : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer1, golem);
            }
            // updatePatternBlocks in CarvedPumpkinBlock.java
            for (int i1 = 0; i1 < carrierPattern.getWidth(); ++i1) {
                for (int j1 = 0; j1 < carrierPattern.getHeight(); ++j1) {
                    BlockInWorld blockinworld = carrierPatternMatch.getBlock(i1, j1, 0);
                    level.blockUpdated(blockinworld.getPos(), Blocks.AIR);
                }
            }
        }
    }

    public static void createAstaliteLongarm(Entity blockPlacer, Level level, BlockPos pos) {
        BlockPattern carrierPattern = createAstaliteGolemLongarm();
        BlockPattern.BlockPatternMatch carrierPatternMatch = carrierPattern.find(level, pos);
        if (carrierPatternMatch != null) {
            // clearPatternBlocks in CarvedPumpkinBlock.java
            for (int j = 0; j < carrierPattern.getWidth(); ++j) {
                for (int k = 0; k < carrierPattern.getHeight(); ++k) {
                    BlockInWorld blockinworld = carrierPatternMatch.getBlock(j, k, 0);
                    level.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                    level.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
                }
            }
            pos = carrierPatternMatch.getBlock(1, 2, 0).getPos();
            AstaliteGolemLongarm golem = EEntityTypes.ASTALITE_GOLEM_LONGARM.get().create(level);
            assert golem != null;
            golem.setPlayerCreated(true);
            if (blockPlacer instanceof Player player) golem.tame(player);
            golem.moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.05D, (double) pos.getZ() + 0.5D, 0.0F, 0.0F);
            level.addFreshEntity(golem);

            for (ServerPlayer serverplayer1 : level.getEntitiesOfClass(ServerPlayer.class, golem.getBoundingBox().inflate(5.0D))) {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer1, golem);
            }
            // updatePatternBlocks in CarvedPumpkinBlock.java
            for (int i1 = 0; i1 < carrierPattern.getWidth(); ++i1) {
                for (int j1 = 0; j1 < carrierPattern.getHeight(); ++j1) {
                    BlockInWorld blockinworld = carrierPatternMatch.getBlock(i1, j1, 0);
                    level.blockUpdated(blockinworld.getPos(), Blocks.AIR);
                }
            }
        }
    }
}