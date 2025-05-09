package net.nokunami.elementus.common.worldgen.tree;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.BiConsumer;

public class MegaMovcadiaTrunkPlacer extends TrunkPlacer {
    public static final Codec<MegaMovcadiaTrunkPlacer> CODEC = RecordCodecBuilder.create(megaMovcadiaTrunkPlacerInstance ->
            trunkPlacerParts(megaMovcadiaTrunkPlacerInstance).apply(megaMovcadiaTrunkPlacerInstance, MegaMovcadiaTrunkPlacer::new));

    private static final BlockStateProvider rootedDirt = BlockStateProvider.simple(ModBlocks.ElementusBlocks.MOVCADIA_ROOTED_DIRT.get());
    private static final BlockStateProvider rootedStone = BlockStateProvider.simple(ModBlocks.ElementusBlocks.MOVCADIA_ROOTED_STONE.get());
    private static final BlockStateProvider rootedDeepslate = BlockStateProvider.simple(ModBlocks.ElementusBlocks.MOVCADIA_ROOTED_DEEPSLATE.get());

    public MegaMovcadiaTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected @NotNull TrunkPlacerType<?> type() {
        return ModTrunkPlacer.MEGA_MOVCADIA_TRUNK_PLACER.get();
    }

    private static boolean isDirt(LevelSimulatedReader pLevel, BlockPos pPos) {
//        return pLevel.isStateAtPosition(pPos, (blockState) -> Feature
//                .isDirt(blockState) && !blockState.is(Blocks.GRASS_BLOCK) && !blockState.is(Blocks.MYCELIUM));
        return pLevel.isStateAtPosition(pPos, (blockState) -> blockState.is(Etags.Blocks.MOVCADIA_ROOTED_DIRT));
    }

    private static boolean isStone(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (blockState) -> blockState.is(Etags.Blocks.MOVCADIA_ROOTED_STONE));
    }

    private static boolean isDeepslate(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (blockState) -> blockState.is(Etags.Blocks.MOVCADIA_ROOTED_DEEPSLATE));
    }

    protected static void setBlock(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos pos, TreeConfiguration config) {
        if ((((LevelReader)level).getBlockState(pos).onTreeGrow((LevelReader)level, blockSetter, random, pos, config))) {
//            if (!isDirt(level, pos) && isStone(level, pos) && isDeepslate(level, pos)) {
//                blockSetter.accept(pos, rootedDirt.getState(random, pos));
//            }
//            if (!isStone(level, pos) && isDirt(level, pos) && isDeepslate(level, pos)) {
//                blockSetter.accept(pos, rootedStone.getState(random, pos));
//            }
//            if (!isDeepslate(level, pos) && isStone(level, pos) && isDirt(level, pos)) {
//                blockSetter.accept(pos, rootedDeepslate.getState(random, pos));
//            }
            if (isDirt(level, pos)) {
                blockSetter.accept(pos, rootedDirt.getState(random, pos));
            } else if (isStone(level, pos)) {
                blockSetter.accept(pos, rootedStone.getState(random, pos));
            } else if (isDeepslate(level, pos)) {
                blockSetter.accept(pos, rootedDeepslate.getState(random, pos));
            }
        }
    }

    @Override
    public @NotNull List<FoliagePlacer.FoliageAttachment> placeTrunk(@NotNull LevelSimulatedReader pLevel, @NotNull BiConsumer<BlockPos, BlockState> pBlockSetter, @NotNull RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, @NotNull TreeConfiguration pConfig) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos blockpos = pPos.below();
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below().east(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below().south(), pConfig);
        setDirtAt(pLevel, pBlockSetter, pRandom, pPos.below().south().east(), pConfig);
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        int i = pFreeTreeHeight - pRandom.nextInt(4);
        int j = 2 - pRandom.nextInt(3);
        int k = blockpos.getX();
        int l = blockpos.getY();
        int i1 = blockpos.getZ();
        int j1 = k;
        int k1 = i1;
        int l1 = l + pFreeTreeHeight - 1;

        for (int mainTrunk = 0; mainTrunk < pFreeTreeHeight; ++mainTrunk) {
            if (mainTrunk > i && j > 0) {
                j1 += direction.getStepX();
                k1 += direction.getStepZ();
                --j;
            }

            int j2 = l + mainTrunk;
            BlockPos blockpos1 = new BlockPos(j1, j2, k1);
            if (TreeFeature.isAirOrLeaves(pLevel, blockpos1)) {
                this.placeLog(pLevel, pBlockSetter, pRandom, blockpos1, pConfig);
                this.placeLog(pLevel, pBlockSetter, pRandom, blockpos1.east(), pConfig);
                this.placeLog(pLevel, pBlockSetter, pRandom, blockpos1.south(), pConfig);
                this.placeLog(pLevel, pBlockSetter, pRandom, blockpos1.south().east(), pConfig);
            }
        }

        list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(j1, l1, k1), 0, false));

        for(int l2 = -1; l2 <= 2; ++l2) {
            for(int i3 = -1; i3 <= 2; ++i3) {
                if ((l2 < 0 || l2 > 1 || i3 < 0 || i3 > 1) && pRandom.nextInt(1) <= 0) {
                    int j3 = pRandom.nextInt(8) + 3;

                    for(int k2 = 0; k2 < j3; ++k2) {
                        this.placeLog(pLevel, pBlockSetter, pRandom, new BlockPos(k + l2, l1 - k2 - 1, i1 + i3), pConfig);
                    }

                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(j1 + l2, l1, k1 + 1), 0, false));
                }
            }
        }

        for(int l2 = -2; l2 <= 3; ++l2) {
            for(int i3 = -2; i3 <= 3; ++i3) {
                if ((l2 < 0 || l2 > 1 || i3 < 0 || i3 > 1) && pRandom.nextInt(2) <= 0) {
                    int j3 = pRandom.nextInt(2) + 3;

                    for(int k2 = 0; k2 < j3; ++k2) {
                        this.placeLog(pLevel, pBlockSetter, pRandom, new BlockPos(k + l2, l1 - k2 - 1, i1 + i3), pConfig);
                    }

//                    list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(j1 + l2, l1, k1 + 1), 0, false));
                }
            }
        }

        return list;
    }
}
