package net.nokunami.elementus.common.config;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Tier;
import net.minecraftforge.common.ForgeConfigSpec;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.config.configSets.armor.*;
import net.nokunami.elementus.common.config.configSets.item.*;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import net.nokunami.elementus.common.registry.ETier;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class CommonConfig {
//    public final ForgeConfigSpec.BooleanValue catalystArmorDurability;
//    public final ForgeConfigSpec.BooleanValue catalystTotemAbility;
    public final ForgeConfigSpec.BooleanValue arcaneSharpnessTreasure;
    public final ForgeConfigSpec.DoubleValue arcaneSharpnessPercent;
    public final ForgeConfigSpec.BooleanValue arcaneSharpnessIncompatibility;
    public final ToolSetConfig ASTALITE;
    public final DiarkriteToolSetConfig DIARKRITE;
    public final ToolSetConfig ANTHEKTITE;
    public final ToolSetConfig MOVCADIA;

    public final AstaliteArmorMaterialConfig ASTALITE_ARMOR;
    public final DiarkriteArmorMaterialConfig DIARKRITE_ARMOR;
    public final AnthektiteArmorMaterialConfig ANTHEKTITE_ARMOR;

    public final ChargeBladeConfig BLADE_OF_RESONANCE;
    public final ChargeBladeConfig BLADE_OF_SURGING_WINDS;
    public final CatalystArmorMaterialConfig CATALYST_ARMOR;

    public CommonConfig(ForgeConfigSpec.Builder builder) {
//        diarkriteEfficiency = builder.translation(PREFIX + "diarkriteEfficiency.desc")
//                .define("diarkriteEfficiency", true);
//        builder.push("Common");
//        catalystArmorDurability = builder.translation(PREFIX + "catalystArmorDurability")
//                .define("catalystArmorDurability", true);
//        catalystTotemAbility = builder.translation(PREFIX + "catalystTotemAbility")
//                .define("catalystTotemAbility", true);
//        builder.pop();
        builder.push("Enchantments");
        arcaneSharpnessTreasure = builder.translation(PREFIX + "arcaneSharpnessTreasure")
                .define("arcaneSharpnessTreasure", true);
        arcaneSharpnessPercent = builder.translation(PREFIX + "arcaneSharpnessPercent")
                .defineInRange("arcaneSharpnessPercent", 0.3, 0, Double.MAX_VALUE);
        arcaneSharpnessIncompatibility = builder.translation(PREFIX + "arcaneSharpnessIncompatibility")
                .define("arcaneSharpnessIncompatibility", true);
        builder.pop();
        builder.push("ToolConfig");
        ASTALITE = new ToolSetConfig(builder, true, "astalite", new AstaliteTierConfig(builder), 3, -2.4, 1.5, -3, 1, -2.8, 5, -3.1, -3, 0, -0.2);
        builder.push("diarkrite");
        DIARKRITE = new DiarkriteToolSetConfig(builder, new DiarkriteTierConfig(builder));
        builder.pop();
        ANTHEKTITE = new ToolSetConfig(builder, true, "anthektite", new AnthektiteTierConfig(builder), 3, -2.4, 1.5, -3, 1, -2.8, 5, -3.1, -3, 0, 0.5);
        MOVCADIA = new ToolSetConfig(builder, true, "movcadia", new MovcadiaTierConfig(builder), 3, -2.4, 1.5, -3, 1, -2.8, 7, -3.1, -1, 1, 0);
        builder.pop();
        builder.push("Unique Items");
        BLADE_OF_RESONANCE = new ChargeBladeConfig(builder, "blade_of_resonance", 3, -2.7, 1);
        BLADE_OF_SURGING_WINDS = new ChargeBladeConfig(builder, "blade_of_surging_winds", 3, -1.9, 1);
        CATALYST_ARMOR = new CatalystArmorMaterialConfig(builder);
        builder.pop();
        builder.push("Armor");
        ASTALITE_ARMOR = new AstaliteArmorMaterialConfig(builder);
        DIARKRITE_ARMOR = new DiarkriteArmorMaterialConfig(builder);
        ANTHEKTITE_ARMOR = new AnthektiteArmorMaterialConfig(builder);
        builder.pop();
    }

    public static void reload(CommonConfig config) {
        try {
            config.ASTALITE.tier.reload(config.ASTALITE);
            config.DIARKRITE.tier.reload(config.DIARKRITE);
            config.ANTHEKTITE.tier.reload(config.ANTHEKTITE);
            config.MOVCADIA.tier.reload(config.MOVCADIA);

            config.ASTALITE_ARMOR.reload(config.ASTALITE_ARMOR);
            config.DIARKRITE_ARMOR.reload(config.DIARKRITE_ARMOR);
            config.ANTHEKTITE_ARMOR.reload(config.ANTHEKTITE_ARMOR);
//            config.CATALYST_ARMOR.reload(config.CATALYST_ARMOR);
        } catch (Exception e) {
            Elementus.LOGGER.warn("failed to load common config");
            e.printStackTrace();
        }
    }

    public static ToolSetConfig getToolConfig(Tier tier) {
        var config = EConfig.COMMON;
        return tier.equals(ETier.EnumTiers.DIARKRITE) ? config.DIARKRITE
                : tier.equals(ETier.EnumTiers.ANTHEKTITE) ? config.ANTHEKTITE
                : tier.equals(ETier.EnumTiers.MOVCADIA) ? config.MOVCADIA
                : config.ASTALITE;
    }
    public static ArmorMaterialConfig getArmorConfig(ArmorMaterial material) {
        var config = EConfig.COMMON;
        return material.equals(EArmorMaterials.EnumArmorMaterials.DIARKRITE) ? config.DIARKRITE_ARMOR
                : material.equals(EArmorMaterials.EnumArmorMaterials.ANTHEKTITE) ? config.ANTHEKTITE_ARMOR
//                : material.equals(EArmorMaterials.EnumArmorMaterials.CATALYST) ? config.CATALYST_ARMOR
                : config.ASTALITE_ARMOR;
    }
//    public static ArmorMaterialConfig getArmorConfigForType(ArmorItem.Type type) {
//        return getArmorConfig()
//    }

    public static class ChargeBladeConfig extends ToolConfig {
        public final ForgeConfigSpec.DoubleValue reach;

        public ChargeBladeConfig(final ForgeConfigSpec.Builder builder, String id, double defaultDamage, double defaultSpeed, double entityReach) {
            super(builder, id, defaultDamage, defaultSpeed);
            reach = builder.translation(PREFIX + id + ".entity_reach").defineInRange(id + ".entity_reach", entityReach, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        }
    }
}
