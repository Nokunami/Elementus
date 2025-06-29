package nokunami.elementus.common.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import nokunami.elementus.Elementus;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_REMNANT_ORE = registerKey("add_remnant_ore");
    public static final ResourceKey<BiomeModifier> ADD_REMNANT_ORE_BURIED = registerKey("add_remnant_ore_buried");

    public static final ResourceKey<BiomeModifier> ADD_MOVCADIA_TREE = registerKey("add_movcadia_tree");
    public static final ResourceKey<BiomeModifier> ADD_MOVCADIA_TALL_TREE = registerKey("add_movcadia_tall_tree");
    public static final ResourceKey<BiomeModifier> ADD_MOVCADIA_MEGA_TREE = registerKey("add_movcadia_mega_tree");

    public static final ResourceKey<BiomeModifier> ADD_ROOTED_MOVCADIA_TREE = registerKey("add_rooted_movcadia_tree");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_REMNANT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.REMNANT_ORE_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
        context.register(ADD_REMNANT_ORE_BURIED, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.REMNANT_ORE_BURIED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_ROOTED_MOVCADIA_TREE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.HAS_ANCIENT_CITY),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ROOTED_MOVCADIA_TREE_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Elementus.MODID, name));
    }
}
