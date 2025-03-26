package net.nokunami.elementus.common.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RootSystemConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.RandomSpreadFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;
import net.nokunami.elementus.common.worldgen.tree.MegaMovcadiaTrunkPlacer;
import net.nokunami.elementus.common.worldgen.tree.MovcadiaTrunkPlacer;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_REMNANT_KEY = registerKey("remnant_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_REMNANT_BURIED_KEY = registerKey("remnant_ore_buried");

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_TREE = registerKey("movcadia");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_TALL_TREE = registerKey("movcadia_tall");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOVCADIA_MEGA_TREE = registerKey("movcadia_mega");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROOTED_MOVCADIA = registerKey("rooted_movcadia");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<Block> blockHolderGetter = context.lookup(Registries.BLOCK);
        HolderGetter<ConfiguredFeature<?, ?>> treeHoldergetter = context.lookup(Registries.CONFIGURED_FEATURE);
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endReplaceables = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldRemnantOres = List.of(OreConfiguration.target(stoneReplaceable,
                        ElementusBlocks.REMNANT.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ElementusBlocks.REMNANT.get().defaultBlockState()));

        register(context, OVERWORLD_REMNANT_KEY, Feature.ORE, new OreConfiguration(overworldRemnantOres, 4, 0.5F));
        register(context, OVERWORLD_REMNANT_BURIED_KEY, Feature.ORE, new OreConfiguration(overworldRemnantOres, 8, 1.0F));


        register(context, MOVCADIA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ElementusBlocks.MOVCADIA_LOG.get()),
                new MovcadiaTrunkPlacer(5, 2, 1),

                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(ElementusBlocks.MOVCADIA_LEAVES.get().defaultBlockState(), 4)
                        .add(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get().defaultBlockState(), 1)),
                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(3), 90),

//                Optional.of(new MovcadiaRootPlacer(UniformInt.of(3, 7), BlockStateProvider.simple(Blocks.MANGROVE_ROOTS),
//                        Optional.of(new AboveRootPlacement(BlockStateProvider.simple(Blocks.MOSS_CARPET), 0.5F)),
//                        new MovcadiaRootPlacement(blockHolderGetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH),
//                                HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS),
//                                BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS)))),

                new TwoLayersFeatureSize(1, 0, 3)).build());

        register(context, MOVCADIA_TALL_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ElementusBlocks.MOVCADIA_LOG.get()),
                new MovcadiaTrunkPlacer(8, 2, 1),

                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(ElementusBlocks.MOVCADIA_LEAVES.get().defaultBlockState(), 4)
                        .add(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get().defaultBlockState(), 1)),
                new RandomSpreadFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(4), 150),

                new TwoLayersFeatureSize(1, 0, 4)).build());

        FeatureUtils.register(context, MOVCADIA_MEGA_TREE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ElementusBlocks.MOVCADIA_LOG.get()),
                new MegaMovcadiaTrunkPlacer(14, 2, 1),

                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(ElementusBlocks.MOVCADIA_LEAVES.get().defaultBlockState(), 5)
                        .add(ElementusBlocks.FLOWERING_MOVCADIA_LEAVES.get().defaultBlockState(), 1)),
                new RandomSpreadFoliagePlacer(ConstantInt.of(8), ConstantInt.of(0), ConstantInt.of(5), 225),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        FeatureUtils.register(context, ROOTED_MOVCADIA, Feature.ROOT_SYSTEM, new RootSystemConfiguration(
                PlacementUtils.inlinePlaced(treeHoldergetter.getOrThrow(MOVCADIA_MEGA_TREE)), 3, 3, Etags.Blocks.MOVCADIA_GROWS_ON,
                BlockStateProvider.simple(Blocks.ROOTED_DIRT), 20, 200, 3, 2,
                BlockStateProvider.simple(Blocks.HANGING_ROOTS), 20, 2,
                BlockPredicate.allOf(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(
                        List.of(Blocks.AIR, Blocks.CAVE_AIR, Blocks.VOID_AIR)), BlockPredicate.matchesTag(Etags.Blocks.MOVCADIA_GROWS_ON)),
                        BlockPredicate.matchesTag(Direction.DOWN.getNormal(), Etags.Blocks.MOVCADIA_GROWS_ON))));

//        FeatureUtils.register(context, TALL_MANGROVE, Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(
//                BlockStateProvider.simple(Blocks.MANGROVE_LOG),
//                new UpwardsBranchingTrunkPlacer(4, 1, 9, UniformInt.of(1, 6), 0.5F, UniformInt.of(0, 1), holdergetter.getOrThrow(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)),
//
//                BlockStateProvider.simple(Blocks.MANGROVE_LEAVES),
//                new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70),
//
//                Optional.of(new MangroveRootPlacer(UniformInt.of(3, 7), BlockStateProvider.simple(Blocks.MANGROVE_ROOTS),
//                        Optional.of(new AboveRootPlacement(BlockStateProvider.simple(Blocks.MOSS_CARPET), 0.5F)),
//                        new MangroveRootPlacement(holdergetter.getOrThrow(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, Blocks.MUDDY_MANGROVE_ROOTS), BlockStateProvider.simple(Blocks.MUDDY_MANGROVE_ROOTS), 8, 15, 0.2F))),
//                new TwoLayersFeatureSize(3, 0, 2))).decorators(List.of(new LeaveVineDecorator(0.125F), new AttachedToLeavesDecorator(0.14F, 1, 0,
//                new RandomizedIntStateProvider(BlockStateProvider.simple(Blocks.MANGROVE_PROPAGULE.defaultBlockState().setValue(MangrovePropaguleBlock.HANGING, Boolean.valueOf(true))), MangrovePropaguleBlock.AGE, UniformInt.of(0, 4)), 2, List.of(Direction.DOWN)))).ignoreVines().build());
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Elementus.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
