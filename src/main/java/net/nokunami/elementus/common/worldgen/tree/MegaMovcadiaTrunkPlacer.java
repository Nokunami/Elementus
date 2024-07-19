package net.nokunami.elementus.common.worldgen.tree;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.nokunami.elementus.common.registry.ModBlocks;

import java.util.List;
import java.util.function.BiConsumer;

public class MegaMovcadiaTrunkPlacer extends TrunkPlacer {
    public static final Codec<MegaMovcadiaTrunkPlacer> CODEC = RecordCodecBuilder.create(megaMovcadiaTrunkPlacerInstance ->
            trunkPlacerParts(megaMovcadiaTrunkPlacerInstance).apply(megaMovcadiaTrunkPlacerInstance, MegaMovcadiaTrunkPlacer::new));

    private static final BlockStateProvider rootProvider = BlockStateProvider.simple(ModBlocks.MOVCADIA_ROOTS.get());

    public MegaMovcadiaTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacer.MEGA_MOVCADIA_TRUNK_PLACER.get();
    }

    private static boolean isDirt(LevelSimulatedReader pLevel, BlockPos pPos) {
        return pLevel.isStateAtPosition(pPos, (blockState) -> Feature
                .isDirt(blockState) && !blockState.is(Blocks.GRASS_BLOCK) && !blockState.is(Blocks.MYCELIUM));
    }

    protected static void setBlockAt(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, BlockPos pPos, TreeConfiguration pConfig) {
        if (!(((LevelReader) pLevel).getBlockState(pPos).onTreeGrow((LevelReader) pLevel, pBlockSetter, pRandom, pPos, pConfig)) && (pConfig.forceDirt || !isDirt(pLevel, pPos))) {
            pBlockSetter.accept(pPos, rootProvider.getState(pRandom, pPos));
        }
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        BlockPos blockpos = pPos.below();
        setBlockAt(pLevel, pBlockSetter, pRandom, pPos.below(), pConfig);
        setBlockAt(pLevel, pBlockSetter, pRandom, pPos.below().east(), pConfig);
        setBlockAt(pLevel, pBlockSetter, pRandom, pPos.below().south(), pConfig);
        setBlockAt(pLevel, pBlockSetter, pRandom, pPos.below().south().east(), pConfig);
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
