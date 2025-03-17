package net.nokunami.elementus.datagen;

import net.minecraft.Util;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModItems.*;

import java.util.Map;

public class ModTrimMaterials {

    public static final ResourceKey<TrimMaterial> STEEL = registerKey("steel");
    public static final ResourceKey<TrimMaterial> ANTHEKTITE = registerKey("anthektite");
    public static final ResourceKey<TrimMaterial> DIARKRITE = registerKey("diarkrite");

    private static ResourceKey<TrimMaterial> registerKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Elementus.modLoc(name));
    }

    public static void bootstrap(BootstapContext<TrimMaterial> context) {
        register(context, STEEL, ElementusItems.STEEL_INGOT.get(), Style.EMPTY.withColor(13816789), 0.2F);
        register(context, DIARKRITE, ElementusItems.DIARKRITE_INGOT.get(), Style.EMPTY.withColor(6842750), 0.3F);
        register(context, ANTHEKTITE, ElementusItems.ANTHEKTITE_INGOT.get(), Style.EMPTY.withColor(9018025), 0.2F);
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, float itemModelIndex) {
        register(context, materialKey, ingredient, style, itemModelIndex, Map.of());
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> materialKey, Item ingredient, Style style, float itemModelIndex, Map<ArmorMaterials, String> overrideArmorMaterials) {
        TrimMaterial trimMaterial = TrimMaterial.create(materialKey.location().getPath(), ingredient, itemModelIndex, Component.translatable(Util.makeDescriptionId("trim_material", materialKey.location())).withStyle(style), overrideArmorMaterials);
        context.register(materialKey, trimMaterial);
    }
}
