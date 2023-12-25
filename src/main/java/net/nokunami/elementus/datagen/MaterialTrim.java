package net.nokunami.elementus.datagen;

import com.google.common.collect.ImmutableMap;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.registry.ItemsRegistry;

import java.util.Map;

public class MaterialTrim {

    public static final Map<String, Float> TRIM_MATERIALS = new ImmutableMap.Builder<String, Float>()
            .put("quartz", 0.1F)
            .put("iron", 0.2F)
            .put("netherite", 0.3F)
            .put("redstone", 0.4F)
            .put("copper", 0.5F)
            .put("gold", 0.6F)
            .put("emerald", 0.7F)
            .put("diamond", 0.8F)
            .put("lapis", 0.9F)
            .put("amethyst", 1.0F)
            .put("steel", 0.22F)
            .put("anthektite", 0.25F)
            .put("diarkrite", 0.24F)
            .build();

    public static final ResourceKey<TrimMaterial> STEEL = registerKey("steel");
    public static final ResourceKey<TrimMaterial> ANTHEKTITE = registerKey("anthektite");
    public static final ResourceKey<TrimMaterial> DIARKRITE = registerKey("diarkrite");

    private static ResourceKey<TrimMaterial> registerKey(String name) {
        return ResourceKey.create(Registries.TRIM_MATERIAL, Elementus.modLoc(name));
    }

    public static void bootstrap(BootstapContext<TrimMaterial> context) {
        register(context, STEEL, ItemsRegistry.STEEL_INGOT.getHolder().get(), Style.EMPTY.withColor(12238276));
        register(context, ANTHEKTITE, ItemsRegistry.ANTHEKTITE_INGOT.getHolder().get(), Style.EMPTY.withColor(6052206));
        register(context, DIARKRITE, ItemsRegistry.DIARKRITE_INGOT.getHolder().get(), Style.EMPTY.withColor(7110813));
    }

    private static void register(BootstapContext<TrimMaterial> context, ResourceKey<TrimMaterial> trimKey, Holder<Item> trimItem, Style color) {
        float itemModelIndex = TRIM_MATERIALS.get(trimKey.location().getPath());
        TrimMaterial material = new TrimMaterial(trimKey.location().getPath(), trimItem, itemModelIndex, Map.of(), Component.translatable(Util.makeDescriptionId("trim_material", trimKey.location())).withStyle(color));
        context.register(trimKey, material);
    }
}
