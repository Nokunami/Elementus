package net.nokunami.elementus.common.block.grower;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.nokunami.elementus.common.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MovcadiaTreeGrower extends AbstractMegaTreeGrower {
    public float tallProbability;

    public MovcadiaTreeGrower(float tallProbability) {
        this.tallProbability = tallProbability;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return pRandom.nextFloat() < this.tallProbability ? ModConfiguredFeatures.MOVCADIA_TREE : ModConfiguredFeatures.MOVCADIA_TALL_TREE;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(@NotNull RandomSource pRandom) {
        return ModConfiguredFeatures.MOVCADIA_MEGA_TREE;
    }
}
