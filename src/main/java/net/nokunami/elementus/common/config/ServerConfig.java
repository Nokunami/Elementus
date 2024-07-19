package net.nokunami.elementus.common.config;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.compat.ironsspellbooks.MagicArmorMaterial;
import net.nokunami.elementus.common.registry.ModArmorMaterials;

import java.util.List;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ServerConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec SPEC;

    public static ArmorSetConfig STEEL_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_ARMOR_CONFIG;

    public static ISSArmorConfig ANTHEKTITE_WIZARD_ARMOR_CONFIG;
    public static ISSArmorConfig DIARKRITE_WIZARD_ARMOR_CONFIG;

    public static ArmorSetConfig DIARKRITE_IRON_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_GOLD_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_EMERALD_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_DIAMOND_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_IRON_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_GOLD_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_EMERALD_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_DIAMOND_ARMOR_CONFIG;

    public static ArmorSetConfig STEEL_SAMURAI_ARMOR_CONFIG;
    public static ArmorSetConfig STEEL_SAMURAI_LIGHT_ARMOR_CONFIG;
    public static ArmorSetConfig STEEL_SAMURAI_MASTER_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_SAMURAI_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_SAMURAI_LIGHT_ARMOR_CONFIG;
    public static ArmorSetConfig DIARKRITE_SAMURAI_MASTER_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_SAMURAI_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_SAMURAI_LIGHT_ARMOR_CONFIG;
    public static ArmorSetConfig ANTHEKTITE_SAMURAI_MASTER_ARMOR_CONFIG;

    static {
        BUILDER.push("Elementus Armor Attributes");
        BUILDER.comment("Changing armor values requires world restart");
        STEEL_ARMOR_CONFIG = defineConfig(BUILDER, "steel",
                List.of(3, 6, 8, 3),
                0.0F,
                0,
                0,
                0);

        DIARKRITE_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite",
                List.of(3, 6, 8, 3),
                4.0F,
                0.2,
                -0.035F,
                0);

        ANTHEKTITE_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite",
                List.of(3, 6, 8, 3),
                2.0F,
                0.05,
                0,
                0.1F);

        BUILDER.pop();

        BUILDER.push("Iron's Spellbooks Armor Attributes");
        DIARKRITE_WIZARD_ARMOR_CONFIG = defineConfig(BUILDER, "mage_diarkrite",
                List.of(3, 6, 8, 3),
                4.0F,
                0,
                -0.035F,
                0,
                135,
                -0.08,
                0.05,
                0,
                0,
                0);
        ANTHEKTITE_WIZARD_ARMOR_CONFIG = defineConfig(BUILDER, "mage_anthektite",
                List.of(3, 6, 8, 3),
                2.0F,
                0,
                0,
                0.1F,
                100,
                0.08,
                0.05,
                0,
                0,
                0);

        BUILDER.pop();
        BUILDER.push("Advanced Netherite Armor Attributes");
        DIARKRITE_IRON_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite_iron",
                List.of(4, 6, 8, 4),
                4.5F,
                0.2,
                -0.035F,
                0);
        DIARKRITE_GOLD_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite_gold",
                List.of(4, 7, 9, 4),
                4.5F,
                0.2,
                -0.035F,
                0);
        DIARKRITE_EMERALD_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite_emerald",
                List.of(4, 7, 9, 4),
                4.5F,
                0.2,
                -0.035F,
                0);
        DIARKRITE_DIAMOND_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite_diamond",
                List.of(5, 7, 9, 5),
                5.0F,
                0.2,
                -0.035F,
                0);

        ANTHEKTITE_IRON_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite_iron",
                List.of(4, 6, 8, 4),
                2.5F,
                0.05,
                0,
                0.1F);
        ANTHEKTITE_GOLD_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite_gold",
                List.of(4, 7, 9, 4),
                2.5F,
                0.05,
                0,
                0.1F);
        ANTHEKTITE_EMERALD_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite_emerald",
                List.of(4, 7, 9, 4),
                2.5F,
                0.05,
                0,
                0.1F);
        ANTHEKTITE_DIAMOND_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite_diamond",
                List.of(5, 7, 9, 5),
                3.0F,
                0.05,
                0,
                0.1F);

        BUILDER.pop();
        BUILDER.push("Epic Samurai Armor Attributes");
        STEEL_SAMURAI_ARMOR_CONFIG = defineConfig(BUILDER, "steel",
                List.of(2, 5, 6, 2),
                1,
                0,
                0,
                0);
        STEEL_SAMURAI_LIGHT_ARMOR_CONFIG = defineConfig(BUILDER, "steel",
                List.of(2, 5, 6, 2),
                1,
                0,
                0,
                0);
        STEEL_SAMURAI_MASTER_ARMOR_CONFIG = defineConfig(BUILDER, "steel",
                List.of(2, 5, 6, 2),
                1,
                0,
                0,
                0);

        DIARKRITE_SAMURAI_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite",
                List.of(3, 6, 8, 3),
                4,
                0.2,
                -0.035F,
                0);
        DIARKRITE_SAMURAI_LIGHT_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite",
                List.of(3, 6, 8, 3),
                4,
                0.2,
                -0.035F,
                0);
        DIARKRITE_SAMURAI_MASTER_ARMOR_CONFIG = defineConfig(BUILDER, "diarkrite",
                List.of(3, 6, 8, 3),
                4,
                0.2,
                -0.035F,
                0);

        ANTHEKTITE_SAMURAI_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite",
                List.of(3, 6, 8, 3),
                2,
                0.05,
                0,
                0.1F);
        ANTHEKTITE_SAMURAI_LIGHT_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite",
                List.of(3, 6, 8, 3),
                2,
                0.05,
                0,
                0.1F);
        ANTHEKTITE_SAMURAI_MASTER_ARMOR_CONFIG = defineConfig(BUILDER, "anthektite",
                List.of(3, 6, 8, 3),
                2,
                0.05,
                0,
                0.1F);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

    private static ArmorSetConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Integer> defenseValues, float toughness, double knockbackResistance, float speedBoost, float atkSpeedBoost) {
        builder.push(name);
        String localizedName = name.substring(0, 1).toUpperCase() + name.substring(1) + "'s ";
        var config = new ArmorSetConfig(
                builder.worldRestart().comment(localizedName + "(Integer) Armor Values, in the form of [boots, leggings, chestplate, helmet]. Default: " + defenseValues).defineList("armorValues", () -> defenseValues, (x) -> true),
                builder.worldRestart().comment(localizedName + "(Integer) Armor Toughness. Default: " + toughness).define("toughness", toughness),
                builder.worldRestart().comment(localizedName + "(Double) Knockback Resistance. Default: " + knockbackResistance).define("knockbackResistance", knockbackResistance),
                builder.worldRestart().comment(localizedName + "(Float) Armor Heaviness. Default: " + speedBoost).define("speedBoost", speedBoost),
                builder.worldRestart().comment(localizedName + "(Float) Armor Attack Speed Bonus. Default: " + atkSpeedBoost).define("atkSpeedBoost", atkSpeedBoost)
        );
        builder.pop();
        return config;
    }



    private static ISSArmorConfig defineConfig(ForgeConfigSpec.Builder builder, String name, List<Integer> defenseValues, float toughness, double knockbackResistance, float speedBoost, float atkSpeedBoost, double maxMana, double manaRegen, double spellPower, double spellResist, double castTime, double cooldown) {
        builder.push(name);
        String localizedName = name.substring(0, 1).toUpperCase() + name.substring(1) + "'s ";
        var config = new ISSArmorConfig(
                builder.worldRestart().comment(localizedName + "(Integer) Armor Values, in the form of [boots, leggings, chestplate, helmet]. Default: " + defenseValues).defineList("armorValues", () -> defenseValues, (x) -> true),
                builder.worldRestart().comment(localizedName + "(Integer) Armor Toughness. Default: " + toughness).define("toughness", toughness),
                builder.worldRestart().comment(localizedName + "(Double) Knockback Resistance. Default: " + knockbackResistance).define("knockbackResistance", knockbackResistance),
                builder.worldRestart().comment(localizedName + "(Float) Armor Heaviness. Default: " + speedBoost).define("speedBoost", speedBoost),
                builder.worldRestart().comment(localizedName + "(Float) Armor Attack Speed Bonus. Default: " + atkSpeedBoost).define("atkSpeedBoost", atkSpeedBoost),
                builder.worldRestart().comment(localizedName + "(Integer) Armor Max Mana. Default: " + maxMana).define("maxMana", maxMana),
                builder.worldRestart().comment(localizedName + "(Float) Armor Mana Regen. Default: " + manaRegen).define("manaRegen", manaRegen),
                builder.worldRestart().comment(localizedName + "(Float) Armor Spell Power. Default: " + spellPower).define("spellPower", spellPower),
                builder.worldRestart().comment(localizedName + "(Float) Armor Spell Resistance. Default: " + spellResist).define("spellResist", spellResist),
                builder.worldRestart().comment(localizedName + "(Float) Armor Cast Time Reduction. Default: " + castTime).define("castTime", castTime),
                builder.worldRestart().comment(localizedName + "(Float) Armor Cooldown Reduction. Default: " + cooldown).define("cooldown", cooldown)
        );
        builder.pop();
        return config;
    }

    public record ArmorSetConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ForgeConfigSpec.ConfigValue<? extends Float> toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.ConfigValue<? extends Float> speedBoost,
            ForgeConfigSpec.ConfigValue<? extends Float> atkSpeedBoost
    ) {
        public double getDefenseFor(EquipmentSlot slot) {
            if (defenseValues.get().size() != 4) {
                return defenseValues.getDefault().get(slot.getIndex());
            } else {
                return defenseValues.get().get(slot.getIndex());
            }
        }
    }

    public record ISSArmorConfig(
            ForgeConfigSpec.ConfigValue<List<? extends Integer>> defenseValues,
            ForgeConfigSpec.ConfigValue<? extends Float> toughness,
            ForgeConfigSpec.ConfigValue<? extends Double> knockbackResistance,
            ForgeConfigSpec.ConfigValue<? extends Float> speedBoost,
            ForgeConfigSpec.ConfigValue<? extends Float> atkSpeedBoost,
            ForgeConfigSpec.ConfigValue<? extends Double> maxMana,
            ForgeConfigSpec.ConfigValue<? extends Double> manaRegen,
            ForgeConfigSpec.ConfigValue<? extends Double> spellPower,
            ForgeConfigSpec.ConfigValue<? extends Double> spellResist,
            ForgeConfigSpec.ConfigValue<? extends Double> castTime,
            ForgeConfigSpec.ConfigValue<? extends Double> cooldown
    ) {
        public double getDefenseFor(EquipmentSlot slot) {
            if (defenseValues.get().size() != 4) {
                return defenseValues.getDefault().get(slot.getIndex());
            } else {
                return defenseValues.get().get(slot.getIndex());
            }
        }
    }

    @SubscribeEvent
    public static void onReload(ModConfigEvent.Reloading event) {
        for (ModArmorMaterials value : ModArmorMaterials.values()) {
            value.reload();
        }
        for (MagicArmorMaterial value1 : MagicArmorMaterial.values()) {
            value1.reload();
        }
    }
}
