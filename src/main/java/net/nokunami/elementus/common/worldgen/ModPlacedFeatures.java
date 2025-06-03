package net.nokunami.elementus.common.worldgen;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RootSystemConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModBlocks.ElementusBlocks;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> REMNANT_ORE_KEY = registerKey("remnant_ore_placed");
    public static final ResourceKey<PlacedFeature> REMNANT_ORE_BURIED_KEY = registerKey("remnant_ore_buried_placed");

    public static final ResourceKey<PlacedFeature> MOVCADIA_TREE_KEY = registerKey("movcadia_tree_placed");
    public static final ResourceKey<PlacedFeature> MOVCADIA_TALL_TREE_KEY = registerKey("movcadia_tall_tree_placed");
    public static final ResourceKey<PlacedFeature> MOVCADIA_MEGA_TREE_KEY = registerKey("movcadia_mega_tree_placed");
    public static final ResourceKey<PlacedFeature> ROOTED_MOVCADIA_TREE_KEY = registerKey("rooted_movcadia_tree_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, REMNANT_ORE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_REMNANT_KEY),
                ModOrePlacement.commonOrePlacement(7,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(80))));
        register(context, REMNANT_ORE_BURIED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_REMNANT_BURIED_KEY),
                ModOrePlacement.commonOrePlacement(4,
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.aboveBottom(80))));

        register(context, MOVCADIA_TREE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOVCADIA_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1f, 3),
                        ElementusBlocks.MOVCADIA_SAPLING.get()));

        register(context, MOVCADIA_TALL_TREE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOVCADIA_TALL_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2),
                        ElementusBlocks.MOVCADIA_SAPLING.get()));

        register(context, MOVCADIA_MEGA_TREE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MOVCADIA_MEGA_TREE),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1f, 2),
                        ElementusBlocks.MOVCADIA_SAPLING.get()));

        register(context, ROOTED_MOVCADIA_TREE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ROOTED_MOVCADIA),
                CountPlacement.of(UniformInt.of(1, 2)),
                InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(256)),
                EnvironmentScanPlacement.scanningFor(Direction.UP, BlockPredicate.solid(), BlockPredicate.ONLY_IN_AIR_PREDICATE, 32),
                RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BiomeFilter.biome());
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Elementus.MODID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    public static void register(BootstapContext<PlacedFeature> pContext, ResourceKey<PlacedFeature> pKey, Holder<ConfiguredFeature<?, ?>> pConfiguredFeatures, PlacementModifier... pPlacements) {
        register(pContext, pKey, pConfiguredFeatures, List.of(pPlacements));
    }
}
