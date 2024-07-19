package net.nokunami.elementus.common.worldgen;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.worldgen.tree.MegaMovcadiaTrunkPlacer;
import net.nokunami.elementus.common.worldgen.tree.MovcadiaTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_REMNANT_KEY = registerKey("remnant_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_REMNANT_BURIED_KEY = registerKey("remnant_ore_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_TREE = registerKey("movcadia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_TALL_TREE = registerKey("movcadia_tall");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_MEGA_TREE = registerKey("movcadia_mega");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldRemnantOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ModBlocks.REMNANT.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.REMNANT.get().defaultBlockState()));

        register(context, OVERWORLD_REMNANT_KEY, Feature.ORE, new OreConfiguration(overworldRemnantOres, 4, 0.5F));
        register(context, OVERWORLD_REMNANT_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldRemnantOres, 8, 1.0F));


        register(context, MOVCADIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MOVCADIA_LOG.get()),
                new MovcadiaTrunkPlacer(5, 2, 1),

                BlockStateProvider.simple(ModBlocks.MOVCADIA_LEAVES.get()),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, MOVCADIA_TALL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MOVCADIA_LOG.get()),
                new MovcadiaTrunkPlacer(8, 2, 1),

                BlockStateProvider.simple(ModBlocks.MOVCADIA_LEAVES.get()),
                new RandomSpreadFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(3), 75),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        FeatureUtils.register(context, MOVCADIA_MEGA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.MOVCADIA_LOG.get()),
                new MegaMovcadiaTrunkPlacer(14, 2, 1),

                BlockStateProvider.simple(ModBlocks.MOVCADIA_LEAVES.get()),
                new RandomSpreadFoliagePlacer(ConstantInt.of(8), ConstantInt.of(0), ConstantInt.of(4), 120),

                new TwoLayersFeatureSize(1, 0, 2)).build());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Elementus.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
