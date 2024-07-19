package net.nokunami.elementus.common.config;

import net.nokunami.elementus.Elementus;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Locale;
import java.util.Scanner;

/**
 * credits: SkpC9 <a href="https://github.com/SkpC9/Simply-Steel/blob/main/src/main/java/com/trbz_/simplysteel/util/ConfigHandler.java">Link</a>
 * */
public class ArmorConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ArmorConfig INSTANCE = new ArmorConfig();
    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion("1.0");
    // values exposed to other classes
    //Armor
    public static int steelArmor_DurabilityForType;
    public static int steelArmor_Enchantability;
    public static int steelArmor_Helmet;
    public static int steelArmor_Chestplate;
    public static int steelArmor_Leggings;
    public static int steelArmor_Boots;
    public static float steelArmor_Toughness;
    public static float steelArmor_KnockbackResistance;
    public static float steelArmor_AttackSpeed;
    public static float steelArmor_MovementSpeed;

    public static int diarkriteArmor_DurabilityForType;
    public static int diarkriteArmor_Enchantability;
    public static int diarkriteArmor_Helmet;
    public static int diarkriteArmor_Chestplate;
    public static int diarkriteArmor_Leggings;
    public static int diarkriteArmor_Boots;
    public static float diarkriteArmor_Toughness;
    public static float diarkriteArmor_KnockbackResistance;
    public static float diarkriteArmor_AttackSpeed;
    public static float diarkriteArmor_MovementSpeed;

    public static int anthektiteArmor_DurabilityForType;
    public static int anthektiteArmor_Enchantability;
    public static int anthektiteArmor_Helmet;
    public static int anthektiteArmor_Chestplate;
    public static int anthektiteArmor_Leggings;
    public static int anthektiteArmor_Boots;
    public static float anthektiteArmor_Toughness;
    public static float anthektiteArmor_KnockbackResistance;
    public static float anthektiteArmor_AttackSpeed;
    public static float anthektiteArmor_MovementSpeed;

    //Iron's Spells 'n Spellbooks Armor
    public static int diarkriteMageArmor_DurabilityForType;
    public static int diarkriteMageArmor_Enchantability;
    public static int diarkriteMageArmor_Helmet;
    public static int diarkriteMageArmor_Chestplate;
    public static int diarkriteMageArmor_Leggings;
    public static int diarkriteMageArmor_Boots;
    public static float diarkriteMageArmor_Toughness;
    public static float diarkriteMageArmor_KnockbackResistance;
    public static float diarkriteMageArmor_AttackSpeed;
    public static float diarkriteMageArmor_MovementSpeed;
    public static int diarkriteMageArmor_MaxMana;
    public static double diarkriteMageArmor_ManaRegen;
    public static double diarkriteMageArmor_SpellPower;
    public static double diarkriteMageArmor_SpellResist;
    public static double diarkriteMageArmor_CastTime;
    public static double diarkriteMageArmor_Cooldown;

    public static int anthektiteMageArmor_DurabilityForType;
    public static int anthektiteMageArmor_Enchantability;
    public static int anthektiteMageArmor_Helmet;
    public static int anthektiteMageArmor_Chestplate;
    public static int anthektiteMageArmor_Leggings;
    public static int anthektiteMageArmor_Boots;
    public static float anthektiteMageArmor_Toughness;
    public static float anthektiteMageArmor_KnockbackResistance;
    public static float anthektiteMageArmor_AttackSpeed;
    public static float anthektiteMageArmor_MovementSpeed;
    public static int anthektiteMageArmor_MaxMana;
    public static double anthektiteMageArmor_ManaRegen;
    public static double anthektiteMageArmor_SpellPower;
    public static double anthektiteMageArmor_SpellResist;
    public static double anthektiteMageArmor_CastTime;
    public static double anthektiteMageArmor_Cooldown;


    //AdvancedNetherite Armor Diarkrite
    public static int diarkriteIronArmor_DurabilityForType;
    public static int diarkriteIronArmor_Enchantability;
    public static int diarkriteIronArmor_Helmet;
    public static int diarkriteIronArmor_Chestplate;
    public static int diarkriteIronArmor_Leggings;
    public static int diarkriteIronArmor_Boots;
    public static float diarkriteIronArmor_Toughness;
    public static float diarkriteIronArmor_KnockbackResistance;
    public static float diarkriteIronArmor_AttackSpeed;
    public static float diarkriteIronArmor_MovementSpeed;

    public static int diarkriteGoldArmor_DurabilityForType;
    public static int diarkriteGoldArmor_Enchantability;
    public static int diarkriteGoldArmor_Helmet;
    public static int diarkriteGoldArmor_Chestplate;
    public static int diarkriteGoldArmor_Leggings;
    public static int diarkriteGoldArmor_Boots;
    public static float diarkriteGoldArmor_Toughness;
    public static float diarkriteGoldArmor_KnockbackResistance;
    public static float diarkriteGoldArmor_AttackSpeed;
    public static float diarkriteGoldArmor_MovementSpeed;

    public static int diarkriteEmeraldArmor_DurabilityForType;
    public static int diarkriteEmeraldArmor_Enchantability;
    public static int diarkriteEmeraldArmor_Helmet;
    public static int diarkriteEmeraldArmor_Chestplate;
    public static int diarkriteEmeraldArmor_Leggings;
    public static int diarkriteEmeraldArmor_Boots;
    public static float diarkriteEmeraldArmor_Toughness;
    public static float diarkriteEmeraldArmor_KnockbackResistance;
    public static float diarkriteEmeraldArmor_AttackSpeed;
    public static float diarkriteEmeraldArmor_MovementSpeed;

    public static int diarkriteDiamondArmor_DurabilityForType;
    public static int diarkriteDiamondArmor_Enchantability;
    public static int diarkriteDiamondArmor_Helmet;
    public static int diarkriteDiamondArmor_Chestplate;
    public static int diarkriteDiamondArmor_Leggings;
    public static int diarkriteDiamondArmor_Boots;
    public static float diarkriteDiamondArmor_Toughness;
    public static float diarkriteDiamondArmor_KnockbackResistance;
    public static float diarkriteDiamondArmor_AttackSpeed;
    public static float diarkriteDiamondArmor_MovementSpeed;

    //AdvancedNetherite Armor Anthektite
    public static int anthektiteIronArmor_DurabilityForType;
    public static int anthektiteIronArmor_Enchantability;
    public static int anthektiteIronArmor_Helmet;
    public static int anthektiteIronArmor_Chestplate;
    public static int anthektiteIronArmor_Leggings;
    public static int anthektiteIronArmor_Boots;
    public static float anthektiteIronArmor_Toughness;
    public static float anthektiteIronArmor_KnockbackResistance;
    public static float anthektiteIronArmor_AttackSpeed;
    public static float anthektiteIronArmor_MovementSpeed;

    public static int anthektiteGoldArmor_DurabilityForType;
    public static int anthektiteGoldArmor_Enchantability;
    public static int anthektiteGoldArmor_Helmet;
    public static int anthektiteGoldArmor_Chestplate;
    public static int anthektiteGoldArmor_Leggings;
    public static int anthektiteGoldArmor_Boots;
    public static float anthektiteGoldArmor_Toughness;
    public static float anthektiteGoldArmor_KnockbackResistance;
    public static float anthektiteGoldArmor_AttackSpeed;
    public static float anthektiteGoldArmor_MovementSpeed;

    public static int anthektiteEmeraldArmor_DurabilityForType;
    public static int anthektiteEmeraldArmor_Enchantability;
    public static int anthektiteEmeraldArmor_Helmet;
    public static int anthektiteEmeraldArmor_Chestplate;
    public static int anthektiteEmeraldArmor_Leggings;
    public static int anthektiteEmeraldArmor_Boots;
    public static float anthektiteEmeraldArmor_Toughness;
    public static float anthektiteEmeraldArmor_KnockbackResistance;
    public static float anthektiteEmeraldArmor_AttackSpeed;
    public static float anthektiteEmeraldArmor_MovementSpeed;

    public static int anthektiteDiamondArmor_DurabilityForType;
    public static int anthektiteDiamondArmor_Enchantability;
    public static int anthektiteDiamondArmor_Helmet;
    public static int anthektiteDiamondArmor_Chestplate;
    public static int anthektiteDiamondArmor_Leggings;
    public static int anthektiteDiamondArmor_Boots;
    public static float anthektiteDiamondArmor_Toughness;
    public static float anthektiteDiamondArmor_KnockbackResistance;
    public static float anthektiteDiamondArmor_AttackSpeed;
    public static float anthektiteDiamondArmor_MovementSpeed;

    //Samurai Dynasty Steel
    public static int steelSamuraiArmor_DurabilityForType;
    public static int steelSamuraiArmor_Enchantability;
    public static int steelSamuraiArmor_Helmet;
    public static int steelSamuraiArmor_Chestplate;
    public static int steelSamuraiArmor_Leggings;
    public static int steelSamuraiArmor_Boots;
    public static float steelSamuraiArmor_Toughness;
    public static float steelSamuraiArmor_KnockbackResistance;
    public static float steelSamuraiArmor_AttackSpeed;
    public static float steelSamuraiArmor_MovementSpeed;

    public static int steelSamuraiLightArmor_DurabilityForType;
    public static int steelSamuraiLightArmor_Enchantability;
    public static int steelSamuraiLightArmor_Helmet;
    public static int steelSamuraiLightArmor_Chestplate;
    public static int steelSamuraiLightArmor_Leggings;
    public static int steelSamuraiLightArmor_Boots;
    public static float steelSamuraiLightArmor_Toughness;
    public static float steelSamuraiLightArmor_KnockbackResistance;
    public static float steelSamuraiLightArmor_AttackSpeed;
    public static float steelSamuraiLightArmor_MovementSpeed;

    public static int steelSamuraiMasterArmor_DurabilityForType;
    public static int steelSamuraiMasterArmor_Enchantability;
    public static int steelSamuraiMasterArmor_Helmet;
    public static int steelSamuraiMasterArmor_Chestplate;
    public static int steelSamuraiMasterArmor_Leggings;
    public static int steelSamuraiMasterArmor_Boots;
    public static float steelSamuraiMasterArmor_Toughness;
    public static float steelSamuraiMasterArmor_KnockbackResistance;
    public static float steelSamuraiMasterArmor_AttackSpeed;
    public static float steelSamuraiMasterArmor_MovementSpeed;

    //Samurai Dynasty Diarkrite
    public static int diarkriteSamuraiArmor_DurabilityForType;
    public static int diarkriteSamuraiArmor_Enchantability;
    public static int diarkriteSamuraiArmor_Helmet;
    public static int diarkriteSamuraiArmor_Chestplate;
    public static int diarkriteSamuraiArmor_Leggings;
    public static int diarkriteSamuraiArmor_Boots;
    public static float diarkriteSamuraiArmor_Toughness;
    public static float diarkriteSamuraiArmor_KnockbackResistance;
    public static float diarkriteSamuraiArmor_AttackSpeed;
    public static float diarkriteSamuraiArmor_MovementSpeed;

    public static int diarkriteSamuraiLightArmor_DurabilityForType;
    public static int diarkriteSamuraiLightArmor_Enchantability;
    public static int diarkriteSamuraiLightArmor_Helmet;
    public static int diarkriteSamuraiLightArmor_Chestplate;
    public static int diarkriteSamuraiLightArmor_Leggings;
    public static int diarkriteSamuraiLightArmor_Boots;
    public static float diarkriteSamuraiLightArmor_Toughness;
    public static float diarkriteSamuraiLightArmor_KnockbackResistance;
    public static float diarkriteSamuraiLightArmor_AttackSpeed;
    public static float diarkriteSamuraiLightArmor_MovementSpeed;

    public static int diarkriteSamuraiMasterArmor_DurabilityForType;
    public static int diarkriteSamuraiMasterArmor_Enchantability;
    public static int diarkriteSamuraiMasterArmor_Helmet;
    public static int diarkriteSamuraiMasterArmor_Chestplate;
    public static int diarkriteSamuraiMasterArmor_Leggings;
    public static int diarkriteSamuraiMasterArmor_Boots;
    public static float diarkriteSamuraiMasterArmor_Toughness;
    public static float diarkriteSamuraiMasterArmor_KnockbackResistance;
    public static float diarkriteSamuraiMasterArmor_AttackSpeed;
    public static float diarkriteSamuraiMasterArmor_MovementSpeed;

    //Samurai Dynasty Anthektite
    public static int anthektiteSamuraiArmor_DurabilityForType;
    public static int anthektiteSamuraiArmor_Enchantability;
    public static int anthektiteSamuraiArmor_Helmet;
    public static int anthektiteSamuraiArmor_Chestplate;
    public static int anthektiteSamuraiArmor_Leggings;
    public static int anthektiteSamuraiArmor_Boots;
    public static float anthektiteSamuraiArmor_Toughness;
    public static float anthektiteSamuraiArmor_KnockbackResistance;
    public static float anthektiteSamuraiArmor_AttackSpeed;
    public static float anthektiteSamuraiArmor_MovementSpeed;

    public static int anthektiteSamuraiLightArmor_DurabilityForType;
    public static int anthektiteSamuraiLightArmor_Enchantability;
    public static int anthektiteSamuraiLightArmor_Helmet;
    public static int anthektiteSamuraiLightArmor_Chestplate;
    public static int anthektiteSamuraiLightArmor_Leggings;
    public static int anthektiteSamuraiLightArmor_Boots;
    public static float anthektiteSamuraiLightArmor_Toughness;
    public static float anthektiteSamuraiLightArmor_KnockbackResistance;
    public static float anthektiteSamuraiLightArmor_AttackSpeed;
    public static float anthektiteSamuraiLightArmor_MovementSpeed;

    public static int anthektiteSamuraiMasterArmor_DurabilityForType;
    public static int anthektiteSamuraiMasterArmor_Enchantability;
    public static int anthektiteSamuraiMasterArmor_Helmet;
    public static int anthektiteSamuraiMasterArmor_Chestplate;
    public static int anthektiteSamuraiMasterArmor_Leggings;
    public static int anthektiteSamuraiMasterArmor_Boots;
    public static float anthektiteSamuraiMasterArmor_Toughness;
    public static float anthektiteSamuraiMasterArmor_KnockbackResistance;
    public static float anthektiteSamuraiMasterArmor_AttackSpeed;
    public static float anthektiteSamuraiMasterArmor_MovementSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Configuration has been loaded");
    }

    private void setDefaults() {
        steelArmor_DurabilityForType = 24;
        steelArmor_Helmet = 3;
        steelArmor_Chestplate = 8;
        steelArmor_Leggings = 6;
        steelArmor_Boots = 3;
        steelArmor_Enchantability = 10;
        steelArmor_Toughness = 0F;
        steelArmor_KnockbackResistance = 0F;
        steelArmor_AttackSpeed = 0F;
        steelArmor_MovementSpeed = 0F;

        diarkriteArmor_DurabilityForType = 38;
        diarkriteArmor_Helmet = 3;
        diarkriteArmor_Chestplate = 8;
        diarkriteArmor_Leggings = 6;
        diarkriteArmor_Boots = 3;
        diarkriteArmor_Enchantability = 18;
        diarkriteArmor_Toughness = 4F;
        diarkriteArmor_KnockbackResistance = 0.2F;
        diarkriteArmor_AttackSpeed = 0F;
        diarkriteArmor_MovementSpeed = -0.04F;

        anthektiteArmor_DurabilityForType = 35;
        anthektiteArmor_Helmet = 3;
        anthektiteArmor_Chestplate = 8;
        anthektiteArmor_Leggings = 6;
        anthektiteArmor_Boots = 3;
        anthektiteArmor_Enchantability = 15;
        anthektiteArmor_Toughness = 2F;
        anthektiteArmor_KnockbackResistance = 0.05F;
        anthektiteArmor_AttackSpeed = 0.1F;
        anthektiteArmor_MovementSpeed = 0F;

        //AdvancedNetherite Armor Diarkrite
        diarkriteIronArmor_DurabilityForType = 40;
        diarkriteIronArmor_Helmet = 4;
        diarkriteIronArmor_Chestplate = 8;
        diarkriteIronArmor_Leggings = 6;
        diarkriteIronArmor_Boots = 4;
        diarkriteIronArmor_Enchantability = 18;
        diarkriteIronArmor_Toughness = 4.5F;
        diarkriteIronArmor_KnockbackResistance = 0.2F;
        diarkriteIronArmor_AttackSpeed = 0F;
        diarkriteIronArmor_MovementSpeed = -0.04F;

        diarkriteGoldArmor_DurabilityForType = 42;
        diarkriteGoldArmor_Helmet = 4;
        diarkriteGoldArmor_Chestplate = 9;
        diarkriteGoldArmor_Leggings = 7;
        diarkriteGoldArmor_Boots = 4;
        diarkriteGoldArmor_Enchantability = 28;
        diarkriteGoldArmor_Toughness = 4.5F;
        diarkriteGoldArmor_KnockbackResistance = 0.2F;
        diarkriteGoldArmor_AttackSpeed = 0F;
        diarkriteGoldArmor_MovementSpeed = -0.04F;

        diarkriteEmeraldArmor_DurabilityForType = 44;
        diarkriteEmeraldArmor_Helmet = 4;
        diarkriteEmeraldArmor_Chestplate = 9;
        diarkriteEmeraldArmor_Leggings = 7;
        diarkriteEmeraldArmor_Boots = 4;
        diarkriteEmeraldArmor_Enchantability = 23;
        diarkriteEmeraldArmor_Toughness = 4.5F;
        diarkriteEmeraldArmor_KnockbackResistance = 0.2F;
        diarkriteEmeraldArmor_AttackSpeed = 0F;
        diarkriteEmeraldArmor_MovementSpeed = -0.04F;

        diarkriteDiamondArmor_DurabilityForType = 48;
        diarkriteDiamondArmor_Helmet = 5;
        diarkriteDiamondArmor_Chestplate = 9;
        diarkriteDiamondArmor_Leggings = 7;
        diarkriteDiamondArmor_Boots = 5;
        diarkriteDiamondArmor_Enchantability = 18;
        diarkriteDiamondArmor_Toughness = 5F;
        diarkriteDiamondArmor_KnockbackResistance = 0.2F;
        diarkriteDiamondArmor_AttackSpeed = 0F;
        diarkriteDiamondArmor_MovementSpeed = -0.04F;

        //AdvancedNetherite Armor Anthektite
        anthektiteIronArmor_DurabilityForType = 37;
        anthektiteIronArmor_Helmet = 4;
        anthektiteIronArmor_Chestplate = 8;
        anthektiteIronArmor_Leggings = 6;
        anthektiteIronArmor_Boots = 4;
        anthektiteIronArmor_Enchantability = 15;
        anthektiteIronArmor_Toughness = 2.5F;
        anthektiteIronArmor_KnockbackResistance = 0.05F;
        anthektiteIronArmor_AttackSpeed = 0.1F;
        anthektiteIronArmor_MovementSpeed = 0F;

        anthektiteGoldArmor_DurabilityForType = 39;
        anthektiteGoldArmor_Helmet = 4;
        anthektiteGoldArmor_Chestplate = 9;
        anthektiteGoldArmor_Leggings = 7;
        anthektiteGoldArmor_Boots = 4;
        anthektiteGoldArmor_Enchantability = 25;
        anthektiteGoldArmor_Toughness = 2.5F;
        anthektiteGoldArmor_KnockbackResistance = 0.05F;
        anthektiteGoldArmor_AttackSpeed = 0.1F;
        anthektiteGoldArmor_MovementSpeed = 0F;

        anthektiteEmeraldArmor_DurabilityForType = 41;
        anthektiteEmeraldArmor_Helmet = 4;
        anthektiteEmeraldArmor_Chestplate = 9;
        anthektiteEmeraldArmor_Leggings = 7;
        anthektiteEmeraldArmor_Boots = 4;
        anthektiteEmeraldArmor_Enchantability = 20;
        anthektiteEmeraldArmor_Toughness = 2.5F;
        anthektiteEmeraldArmor_KnockbackResistance = 0.05F;
        anthektiteEmeraldArmor_AttackSpeed = 0.1F;
        anthektiteEmeraldArmor_MovementSpeed = 0F;

        anthektiteDiamondArmor_DurabilityForType = 45;
        anthektiteDiamondArmor_Helmet = 5;
        anthektiteDiamondArmor_Chestplate = 9;
        anthektiteDiamondArmor_Leggings = 7;
        anthektiteDiamondArmor_Boots = 5;
        anthektiteDiamondArmor_Enchantability = 15;
        anthektiteDiamondArmor_Toughness = 3F;
        anthektiteDiamondArmor_KnockbackResistance = 0.05F;
        anthektiteDiamondArmor_AttackSpeed = 0.1F;
        anthektiteDiamondArmor_MovementSpeed = 0F;

        //EpicSamurai Armor Steel
        steelSamuraiArmor_DurabilityForType = 24;
        steelSamuraiArmor_Helmet = 3;
        steelSamuraiArmor_Chestplate = 8;
        steelSamuraiArmor_Leggings = 6;
        steelSamuraiArmor_Boots = 3;
        steelSamuraiArmor_Enchantability = 10;
        steelSamuraiArmor_Toughness = 0F;
        steelSamuraiArmor_KnockbackResistance = 0F;
        steelSamuraiArmor_AttackSpeed = 0F;
        steelSamuraiArmor_MovementSpeed = 0F;
        steelSamuraiLightArmor_DurabilityForType = 24;
        steelSamuraiLightArmor_Helmet = 3;
        steelSamuraiLightArmor_Chestplate = 8;
        steelSamuraiLightArmor_Leggings = 6;
        steelSamuraiLightArmor_Boots = 3;
        steelSamuraiLightArmor_Enchantability = 10;
        steelSamuraiLightArmor_Toughness = 0F;
        steelSamuraiLightArmor_KnockbackResistance = 0F;
        steelSamuraiLightArmor_AttackSpeed = 0F;
        steelSamuraiLightArmor_MovementSpeed = 0F;
        steelSamuraiMasterArmor_DurabilityForType = 24;
        steelSamuraiMasterArmor_Helmet = 3;
        steelSamuraiMasterArmor_Chestplate = 8;
        steelSamuraiMasterArmor_Leggings = 6;
        steelSamuraiMasterArmor_Boots = 3;
        steelSamuraiMasterArmor_Enchantability = 10;
        steelSamuraiMasterArmor_Toughness = 0F;
        steelSamuraiMasterArmor_KnockbackResistance = 0F;
        steelSamuraiMasterArmor_AttackSpeed = 0F;
        steelSamuraiMasterArmor_MovementSpeed = 0F;

        //EpicSamurai Armor Diarkrite
        diarkriteSamuraiArmor_DurabilityForType = 38;
        diarkriteSamuraiArmor_Helmet = 3;
        diarkriteSamuraiArmor_Chestplate = 8;
        diarkriteSamuraiArmor_Leggings = 6;
        diarkriteSamuraiArmor_Boots = 3;
        diarkriteSamuraiArmor_Enchantability = 18;
        diarkriteSamuraiArmor_Toughness = 4F;
        diarkriteSamuraiArmor_KnockbackResistance = 0.2F;
        diarkriteSamuraiArmor_AttackSpeed = 0F;
        diarkriteSamuraiArmor_MovementSpeed = 0.04F;
        diarkriteSamuraiLightArmor_DurabilityForType = 38;
        diarkriteSamuraiLightArmor_Helmet = 3;
        diarkriteSamuraiLightArmor_Chestplate = 8;
        diarkriteSamuraiLightArmor_Leggings = 6;
        diarkriteSamuraiLightArmor_Boots = 3;
        diarkriteSamuraiLightArmor_Enchantability = 18;
        diarkriteSamuraiLightArmor_Toughness = 4F;
        diarkriteSamuraiLightArmor_KnockbackResistance = 0.2F;
        diarkriteSamuraiLightArmor_AttackSpeed = 0F;
        diarkriteSamuraiLightArmor_MovementSpeed = 0.04F;
        diarkriteSamuraiMasterArmor_DurabilityForType = 38;
        diarkriteSamuraiMasterArmor_Helmet = 3;
        diarkriteSamuraiMasterArmor_Chestplate = 8;
        diarkriteSamuraiMasterArmor_Leggings = 6;
        diarkriteSamuraiMasterArmor_Boots = 3;
        diarkriteSamuraiMasterArmor_Enchantability = 18;
        diarkriteSamuraiMasterArmor_Toughness = 4F;
        diarkriteSamuraiMasterArmor_KnockbackResistance = 0.2F;
        diarkriteSamuraiMasterArmor_AttackSpeed = 0F;
        diarkriteSamuraiMasterArmor_MovementSpeed = 0.04F;

        //EpicSamurai Armor Anthektite
        anthektiteSamuraiArmor_DurabilityForType = 35;
        anthektiteSamuraiArmor_Helmet = 3;
        anthektiteSamuraiArmor_Chestplate = 8;
        anthektiteSamuraiArmor_Leggings = 6;
        anthektiteSamuraiArmor_Boots = 3;
        anthektiteSamuraiArmor_Enchantability = 15;
        anthektiteSamuraiArmor_Toughness = 2F;
        anthektiteSamuraiArmor_KnockbackResistance = 0.05F;
        anthektiteSamuraiArmor_AttackSpeed = 0.1F;
        anthektiteSamuraiArmor_MovementSpeed = 0F;
        anthektiteSamuraiLightArmor_DurabilityForType = 35;
        anthektiteSamuraiLightArmor_Helmet = 3;
        anthektiteSamuraiLightArmor_Chestplate = 8;
        anthektiteSamuraiLightArmor_Leggings = 6;
        anthektiteSamuraiLightArmor_Boots = 3;
        anthektiteSamuraiLightArmor_Enchantability = 15;
        anthektiteSamuraiLightArmor_Toughness = 2F;
        anthektiteSamuraiLightArmor_KnockbackResistance = 0.05F;
        anthektiteSamuraiLightArmor_AttackSpeed = 0.1F;
        anthektiteSamuraiLightArmor_MovementSpeed = 0F;
        anthektiteSamuraiMasterArmor_DurabilityForType = 35;
        anthektiteSamuraiMasterArmor_Helmet = 3;
        anthektiteSamuraiMasterArmor_Chestplate = 8;
        anthektiteSamuraiMasterArmor_Leggings = 6;
        anthektiteSamuraiMasterArmor_Boots = 3;
        anthektiteSamuraiMasterArmor_Enchantability = 15;
        anthektiteSamuraiMasterArmor_Toughness = 2F;
        anthektiteSamuraiMasterArmor_KnockbackResistance = 0.05F;
        anthektiteSamuraiMasterArmor_AttackSpeed = 0.1F;
        anthektiteSamuraiMasterArmor_MovementSpeed = 0F;
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(Elementus.ARMOR_CONFIG_PATH)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;

                // to ignore comments
                int commentStart = line.indexOf('#');
                if (commentStart != -1) line = line.substring(0, commentStart);
                commentStart = line.indexOf('[');
                if (commentStart != -1) line = line.substring(0, commentStart);

                line.trim();
                if (line.isEmpty()) continue;

                String errorPrefix = Elementus.ARMOR_CONFIG_PATH + ": line " + lineNumber + ": ";
                try (Scanner s = new Scanner(line)) {
                    s.useLocale(Locale.US);
                    s.useDelimiter("\\s*=\\s*");

                    if (!s.hasNext()) {
                        logger.warn(errorPrefix + "parameter name is missing");
                        continue;
                    }
                    String key = s.next().trim();
                    // use string version
                    if (key.equals("version")) {
                        if (!s.hasNext()) {
                            logger.warn(errorPrefix + "version number is missing");
                            continue;
                        }
                        version.parseVersion(s.next().trim());
                        continue;
                    }

                    if (!s.hasNextDouble()) {
                        logger.warn(errorPrefix + "value is missing/wrong/not a number");
                        continue;
                    }
                    double value = s.nextDouble();

                    switch (key) {
                        case "steelArmor_Durability": steelArmor_DurabilityForType = (int) value; break;
                        case "steelArmor_Helmet": steelArmor_Helmet = (int) value; break;
                        case "steelArmor_Chestplate": steelArmor_Chestplate = (int) value; break;
                        case "steelArmor_Leggings": steelArmor_Leggings = (int) value; break;
                        case "steelArmor_Boots": steelArmor_Boots = (int) value; break;
                        case "steelArmor_Enchantability": steelArmor_Enchantability = (int) value; break;
                        case "steelArmor_Toughness": steelArmor_Toughness = (float) value; break;
                        case "steelArmor_KnockbackResistance": steelArmor_KnockbackResistance = (float) value; break;
                        case "steelArmor_AttackSpeedBoost": steelArmor_AttackSpeed = (float) value; break;
                        case "steelArmor_MovementSpeed": steelArmor_MovementSpeed = (float) value; break;

                        case "diarkriteArmor_Durability": diarkriteArmor_DurabilityForType = (int) value; break;
                        case "diarkriteArmor_Helmet": diarkriteArmor_Helmet = (int) value; break;
                        case "diarkriteArmor_Chestplate": diarkriteArmor_Chestplate = (int) value; break;
                        case "diarkriteArmor_Leggings": diarkriteArmor_Leggings = (int) value; break;
                        case "diarkriteArmor_Boots": diarkriteArmor_Boots = (int) value; break;
                        case "diarkriteArmor_Enchantability": diarkriteArmor_Enchantability = (int) value; break;
                        case "diarkriteArmor_Toughness": diarkriteArmor_Toughness = (float) value; break;
                        case "diarkriteArmor_KnockbackResistance": diarkriteArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteArmor_AttackSpeedBoost": diarkriteArmor_AttackSpeed = (float) value; break;
                        case "diarkriteArmor_MovementSpeed": diarkriteArmor_MovementSpeed = (float) value; break;

                        case "anthektiteArmor_Durability": anthektiteArmor_DurabilityForType = (int) value; break;
                        case "anthektiteArmor_Helmet": anthektiteArmor_Helmet = (int) value; break;
                        case "anthektiteArmor_Chestplate": anthektiteArmor_Chestplate = (int) value; break;
                        case "anthektiteArmor_Leggings": anthektiteArmor_Leggings = (int) value; break;
                        case "anthektiteArmor_Boots": anthektiteArmor_Boots = (int) value; break;
                        case "anthektiteArmor_Enchantability": anthektiteArmor_Enchantability = (int) value; break;
                        case "anthektiteArmor_Toughness": anthektiteArmor_Toughness = (float) value; break;
                        case "anthektiteArmor_KnockbackResistance": anthektiteArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteArmor_AttackSpeedBoost": anthektiteArmor_AttackSpeed = (float) value; break;
                        case "anthektiteArmor_MovementSpeed": anthektiteArmor_MovementSpeed = (float) value; break;


                        case "diarkriteIronArmor_Durability": diarkriteIronArmor_DurabilityForType = (int) value; break;
                        case "diarkriteIronArmor_Helmet": diarkriteIronArmor_Helmet = (int) value; break;
                        case "diarkriteIronArmor_Chestplate": diarkriteIronArmor_Chestplate = (int) value; break;
                        case "diarkriteIronArmor_Leggings": diarkriteIronArmor_Leggings = (int) value; break;
                        case "diarkriteIronArmor_Boots": diarkriteIronArmor_Boots = (int) value; break;
                        case "diarkriteIronArmor_Enchantability": diarkriteIronArmor_Enchantability = (int) value; break;
                        case "diarkriteIronArmor_Toughness": diarkriteIronArmor_Toughness = (float) value; break;
                        case "diarkriteIronArmor_KnockbackResistance": diarkriteIronArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteIronArmor_AttackSpeedBoost": diarkriteIronArmor_AttackSpeed = (float) value; break;
                        case "diarkriteIronArmor_MovementSpeed": diarkriteIronArmor_MovementSpeed = (float) value; break;

                        case "diarkriteGoldArmor_Durability": diarkriteGoldArmor_DurabilityForType = (int) value; break;
                        case "diarkriteGoldArmor_Helmet": diarkriteGoldArmor_Helmet = (int) value; break;
                        case "diarkriteGoldArmor_Chestplate": diarkriteGoldArmor_Chestplate = (int) value; break;
                        case "diarkriteGoldArmor_Leggings": diarkriteGoldArmor_Leggings = (int) value; break;
                        case "diarkriteGoldArmor_Boots": diarkriteGoldArmor_Boots = (int) value; break;
                        case "diarkriteGoldArmor_Enchantability": diarkriteGoldArmor_Enchantability = (int) value; break;
                        case "diarkriteGoldArmor_Toughness": diarkriteGoldArmor_Toughness = (float) value; break;
                        case "diarkriteGoldArmor_KnockbackResistance": diarkriteGoldArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteGoldArmor_AttackSpeedBoost": diarkriteGoldArmor_AttackSpeed = (float) value; break;
                        case "diarkriteGoldArmor_MovementSpeed": diarkriteGoldArmor_MovementSpeed = (float) value; break;

                        case "diarkriteEmeraldArmor_Durability": diarkriteEmeraldArmor_DurabilityForType = (int) value; break;
                        case "diarkriteEmeraldArmor_Helmet": diarkriteEmeraldArmor_Helmet = (int) value; break;
                        case "diarkriteEmeraldArmor_Chestplate": diarkriteEmeraldArmor_Chestplate = (int) value; break;
                        case "diarkriteEmeraldArmor_Leggings": diarkriteEmeraldArmor_Leggings = (int) value; break;
                        case "diarkriteEmeraldArmor_Boots": diarkriteEmeraldArmor_Boots = (int) value; break;
                        case "diarkriteEmeraldArmor_Enchantability": diarkriteEmeraldArmor_Enchantability = (int) value; break;
                        case "diarkriteEmeraldArmor_Toughness": diarkriteEmeraldArmor_Toughness = (float) value; break;
                        case "diarkriteEmeraldArmor_KnockbackResistance": diarkriteEmeraldArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteEmeraldArmor_AttackSpeedBoost": diarkriteEmeraldArmor_AttackSpeed = (float) value; break;
                        case "diarkriteEmeraldArmor_MovementSpeed": diarkriteEmeraldArmor_MovementSpeed = (float) value; break;

                        case "diarkriteDiamondArmor_Durability": diarkriteDiamondArmor_DurabilityForType = (int) value; break;
                        case "diarkriteDiamondArmor_Helmet": diarkriteDiamondArmor_Helmet = (int) value; break;
                        case "diarkriteDiamondArmor_Chestplate": diarkriteDiamondArmor_Chestplate = (int) value; break;
                        case "diarkriteDiamondArmor_Leggings": diarkriteDiamondArmor_Leggings = (int) value; break;
                        case "diarkriteDiamondArmor_Boots": diarkriteDiamondArmor_Boots = (int) value; break;
                        case "diarkriteDiamondArmor_Enchantability": diarkriteDiamondArmor_Enchantability = (int) value; break;
                        case "diarkriteDiamondArmor_Toughness": diarkriteDiamondArmor_Toughness = (float) value; break;
                        case "diarkriteDiamondArmor_KnockbackResistance": diarkriteDiamondArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteDiamondArmor_AttackSpeedBoost": diarkriteDiamondArmor_AttackSpeed = (float) value; break;
                        case "diarkriteDiamondArmor_MovementSpeed": diarkriteDiamondArmor_MovementSpeed = (float) value; break;


                        case "anthektiteIronArmor_Durability": anthektiteIronArmor_DurabilityForType = (int) value; break;
                        case "anthektiteIronArmor_Helmet": anthektiteIronArmor_Helmet = (int) value; break;
                        case "anthektiteIronArmor_Chestplate": anthektiteIronArmor_Chestplate = (int) value; break;
                        case "anthektiteIronArmor_Leggings": anthektiteIronArmor_Leggings = (int) value; break;
                        case "anthektiteIronArmor_Boots": anthektiteIronArmor_Boots = (int) value; break;
                        case "anthektiteIronArmor_Enchantability": anthektiteIronArmor_Enchantability = (int) value; break;
                        case "anthektiteIronArmor_Toughness": anthektiteIronArmor_Toughness = (float) value; break;
                        case "anthektiteIronArmor_KnockbackResistance": anthektiteIronArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteIronArmor_AttackSpeedBoost": anthektiteIronArmor_AttackSpeed = (float) value; break;
                        case "anthektiteIronArmor_MovementSpeed": anthektiteIronArmor_MovementSpeed = (float) value; break;

                        case "anthektiteGoldArmor_Durability": anthektiteGoldArmor_DurabilityForType = (int) value; break;
                        case "anthektiteGoldArmor_Helmet": anthektiteGoldArmor_Helmet = (int) value; break;
                        case "anthektiteGoldArmor_Chestplate": anthektiteGoldArmor_Chestplate = (int) value; break;
                        case "anthektiteGoldArmor_Leggings": anthektiteGoldArmor_Leggings = (int) value; break;
                        case "anthektiteGoldArmor_Boots": anthektiteGoldArmor_Boots = (int) value; break;
                        case "anthektiteGoldArmor_Enchantability": anthektiteGoldArmor_Enchantability = (int) value; break;
                        case "anthektiteGoldArmor_Toughness": anthektiteGoldArmor_Toughness = (float) value; break;
                        case "anthektiteGoldArmor_KnockbackResistance": anthektiteGoldArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteGoldArmor_AttackSpeedBoost": anthektiteGoldArmor_AttackSpeed = (float) value; break;
                        case "anthektiteGoldArmor_MovementSpeed": anthektiteGoldArmor_MovementSpeed = (float) value; break;

                        case "anthektiteEmeraldArmor_Durability": anthektiteEmeraldArmor_DurabilityForType = (int) value; break;
                        case "anthektiteEmeraldArmor_Helmet": anthektiteEmeraldArmor_Helmet = (int) value; break;
                        case "anthektiteEmeraldArmor_Chestplate": anthektiteEmeraldArmor_Chestplate = (int) value; break;
                        case "anthektiteEmeraldArmor_Leggings": anthektiteEmeraldArmor_Leggings = (int) value; break;
                        case "anthektiteEmeraldArmor_Boots": anthektiteEmeraldArmor_Boots = (int) value; break;
                        case "anthektiteEmeraldArmor_Enchantability": anthektiteEmeraldArmor_Enchantability = (int) value; break;
                        case "anthektiteEmeraldArmor_Toughness": anthektiteEmeraldArmor_Toughness = (float) value; break;
                        case "anthektiteEmeraldArmor_KnockbackResistance": anthektiteEmeraldArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteEmeraldArmor_AttackSpeedBoost": anthektiteEmeraldArmor_AttackSpeed = (float) value; break;
                        case "anthektiteEmeraldArmor_MovementSpeed": anthektiteEmeraldArmor_MovementSpeed = (float) value; break;

                        case "anthektiteDiamondArmor_Durability": anthektiteDiamondArmor_DurabilityForType = (int) value; break;
                        case "anthektiteDiamondArmor_Helmet": anthektiteDiamondArmor_Helmet = (int) value; break;
                        case "anthektiteDiamondArmor_Chestplate": anthektiteDiamondArmor_Chestplate = (int) value; break;
                        case "anthektiteDiamondArmor_Leggings": anthektiteDiamondArmor_Leggings = (int) value; break;
                        case "anthektiteDiamondArmor_Boots": anthektiteDiamondArmor_Boots = (int) value; break;
                        case "anthektiteDiamondArmor_Enchantability": anthektiteDiamondArmor_Enchantability = (int) value; break;
                        case "anthektiteDiamondArmor_Toughness": anthektiteDiamondArmor_Toughness = (float) value; break;
                        case "anthektiteDiamondArmor_KnockbackResistance": anthektiteDiamondArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteDiamondArmor_AttackSpeedBoost": anthektiteDiamondArmor_AttackSpeed = (float) value; break;
                        case "anthektiteDiamondArmor_MovementSpeed": anthektiteDiamondArmor_MovementSpeed = (float) value; break;


                        case "steelSamuraiArmor_Durability": steelSamuraiArmor_DurabilityForType = (int) value; break;
                        case "steelSamuraiArmor_Helmet": steelSamuraiArmor_Helmet = (int) value; break;
                        case "steelSamuraiArmor_Chestplate": steelSamuraiArmor_Chestplate = (int) value; break;
                        case "steelSamuraiArmor_Leggings": steelSamuraiArmor_Leggings = (int) value; break;
                        case "steelSamuraiArmor_Boots": steelSamuraiArmor_Boots = (int) value; break;
                        case "steelSamuraiArmor_Enchantability": steelSamuraiArmor_Enchantability = (int) value; break;
                        case "steelSamuraiArmor_Toughness": steelSamuraiArmor_Toughness = (float) value; break;
                        case "steelSamuraiArmor_KnockbackResistance": steelSamuraiArmor_KnockbackResistance = (float) value; break;
                        case "steelSamuraiArmor_AttackSpeedBoost": steelSamuraiArmor_AttackSpeed = (float) value; break;
                        case "steelSamuraiArmor_MovementSpeed": steelSamuraiArmor_MovementSpeed = (float) value; break;

                        case "steelSamuraiLightArmor_Durability": steelSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "steelSamuraiLightArmor_Helmet": steelSamuraiLightArmor_Helmet = (int) value; break;
                        case "steelSamuraiLightArmor_Chestplate": steelSamuraiLightArmor_Chestplate = (int) value; break;
                        case "steelSamuraiLightArmor_Leggings": steelSamuraiLightArmor_Leggings = (int) value; break;
                        case "steelSamuraiLightArmor_Boots": steelSamuraiLightArmor_Boots = (int) value; break;
                        case "steelSamuraiLightArmor_Enchantability": steelSamuraiLightArmor_Enchantability = (int) value; break;
                        case "steelSamuraiLightArmor_Toughness": steelSamuraiLightArmor_Toughness = (float) value; break;
                        case "steelSamuraiLightArmor_KnockbackResistance": steelSamuraiLightArmor_KnockbackResistance = (float) value; break;
                        case "steelSamuraiLightArmor_AttackSpeedBoost": steelSamuraiLightArmor_AttackSpeed = (float) value; break;
                        case "steelSamuraiLightArmor_MovementSpeed": steelSamuraiLightArmor_MovementSpeed = (float) value; break;

                        case "steelSamuraiMasterArmor_Durability": steelSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "steelSamuraiMasterArmor_Helmet": steelSamuraiMasterArmor_Helmet = (int) value; break;
                        case "steelSamuraiMasterArmor_Chestplate": steelSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "steelSamuraiMasterArmor_Leggings": steelSamuraiMasterArmor_Leggings = (int) value; break;
                        case "steelSamuraiMasterArmor_Boots": steelSamuraiMasterArmor_Boots = (int) value; break;
                        case "steelSamuraiMasterArmor_Enchantability": steelSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "steelSamuraiMasterArmor_Toughness": steelSamuraiMasterArmor_Toughness = (float) value; break;
                        case "steelSamuraiMasterArmor_KnockbackResistance": steelSamuraiMasterArmor_KnockbackResistance = (float) value; break;
                        case "steelSamuraiMasterArmor_AttackSpeedBoost": steelSamuraiMasterArmor_AttackSpeed = (float) value; break;
                        case "steelSamuraiMasterArmor_MovementSpeed": steelSamuraiMasterArmor_MovementSpeed = (float) value; break;


                        case "diarkriteSamuraiArmor_Durability": diarkriteSamuraiArmor_DurabilityForType = (int) value; break;
                        case "diarkriteSamuraiArmor_Helmet": diarkriteSamuraiArmor_Helmet = (int) value; break;
                        case "diarkriteSamuraiArmor_Chestplate": diarkriteSamuraiArmor_Chestplate = (int) value; break;
                        case "diarkriteSamuraiArmor_Leggings": diarkriteSamuraiArmor_Leggings = (int) value; break;
                        case "diarkriteSamuraiArmor_Boots": diarkriteSamuraiArmor_Boots = (int) value; break;
                        case "diarkriteSamuraiArmor_Enchantability": diarkriteSamuraiArmor_Enchantability = (int) value; break;
                        case "diarkriteSamuraiArmor_Toughness": diarkriteSamuraiArmor_Toughness = (float) value; break;
                        case "diarkriteSamuraiArmor_KnockbackResistance": diarkriteSamuraiArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteSamuraiArmor_AttackSpeedBoost": diarkriteSamuraiArmor_AttackSpeed = (float) value; break;
                        case "diarkriteSamuraiArmor_MovementSpeed": diarkriteSamuraiArmor_MovementSpeed = (float) value; break;

                        case "diarkriteSamuraiLightArmor_Durability": diarkriteSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Helmet": diarkriteSamuraiLightArmor_Helmet = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Chestplate": diarkriteSamuraiLightArmor_Chestplate = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Leggings": diarkriteSamuraiLightArmor_Leggings = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Boots": diarkriteSamuraiLightArmor_Boots = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Enchantability": diarkriteSamuraiLightArmor_Enchantability = (int) value; break;
                        case "diarkriteSamuraiLightArmor_Toughness": diarkriteSamuraiLightArmor_Toughness = (float) value; break;
                        case "diarkriteSamuraiLightArmor_KnockbackResistance": diarkriteSamuraiLightArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteSamuraiLightArmor_AttackSpeedBoost": diarkriteSamuraiLightArmor_AttackSpeed = (float) value; break;
                        case "diarkriteSamuraiLightArmor_MovementSpeed": diarkriteSamuraiLightArmor_MovementSpeed = (float) value; break;

                        case "diarkriteSamuraiMasterArmor_Durability": diarkriteSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Helmet": diarkriteSamuraiMasterArmor_Helmet = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Chestplate": diarkriteSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Leggings": diarkriteSamuraiMasterArmor_Leggings = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Boots": diarkriteSamuraiMasterArmor_Boots = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Enchantability": diarkriteSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "diarkriteSamuraiMasterArmor_Toughness": diarkriteSamuraiMasterArmor_Toughness = (float) value; break;
                        case "diarkriteSamuraiMasterArmor_KnockbackResistance": diarkriteSamuraiMasterArmor_KnockbackResistance = (float) value; break;
                        case "diarkriteSamuraiMasterArmor_AttackSpeedBoost": diarkriteSamuraiMasterArmor_AttackSpeed = (float) value; break;
                        case "diarkriteSamuraiMasterArmor_MovementSpeed": diarkriteSamuraiMasterArmor_MovementSpeed = (float) value; break;


                        case "anthektiteSamuraiArmor_Durability": anthektiteSamuraiArmor_DurabilityForType = (int) value; break;
                        case "anthektiteSamuraiArmor_Helmet": anthektiteSamuraiArmor_Helmet = (int) value; break;
                        case "anthektiteSamuraiArmor_Chestplate": anthektiteSamuraiArmor_Chestplate = (int) value; break;
                        case "anthektiteSamuraiArmor_Leggings": anthektiteSamuraiArmor_Leggings = (int) value; break;
                        case "anthektiteSamuraiArmor_Boots": anthektiteSamuraiArmor_Boots = (int) value; break;
                        case "anthektiteSamuraiArmor_Enchantability": anthektiteSamuraiArmor_Enchantability = (int) value; break;
                        case "anthektiteSamuraiArmor_Toughness": anthektiteSamuraiArmor_Toughness = (float) value; break;
                        case "anthektiteSamuraiArmor_KnockbackResistance": anthektiteSamuraiArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteSamuraiArmor_AttackSpeedBoost": anthektiteSamuraiArmor_AttackSpeed = (float) value; break;
                        case "anthektiteSamuraiArmor_MovementSpeed": anthektiteSamuraiArmor_MovementSpeed = (float) value; break;

                        case "anthektiteSamuraiLightArmor_Durability": anthektiteSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Helmet": anthektiteSamuraiLightArmor_Helmet = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Chestplate": anthektiteSamuraiLightArmor_Chestplate = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Leggings": anthektiteSamuraiLightArmor_Leggings = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Boots": anthektiteSamuraiLightArmor_Boots = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Enchantability": anthektiteSamuraiLightArmor_Enchantability = (int) value; break;
                        case "anthektiteSamuraiLightArmor_Toughness": anthektiteSamuraiLightArmor_Toughness = (float) value; break;
                        case "anthektiteSamuraiLightArmor_KnockbackResistance": anthektiteSamuraiLightArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteSamuraiLightArmor_AttackSpeedBoost": anthektiteSamuraiLightArmor_AttackSpeed = (float) value; break;
                        case "anthektiteSamuraiLightArmor_MovementSpeed": anthektiteSamuraiLightArmor_MovementSpeed = (float) value; break;

                        case "anthektiteSamuraiMasterArmor_Durability": anthektiteSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Helmet": anthektiteSamuraiMasterArmor_Helmet = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Chestplate": anthektiteSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Leggings": anthektiteSamuraiMasterArmor_Leggings = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Boots": anthektiteSamuraiMasterArmor_Boots = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Enchantability": anthektiteSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "anthektiteSamuraiMasterArmor_Toughness": anthektiteSamuraiMasterArmor_Toughness = (float) value; break;
                        case "anthektiteSamuraiMasterArmor_KnockbackResistance": anthektiteSamuraiMasterArmor_KnockbackResistance = (float) value; break;
                        case "anthektiteSamuraiMasterArmor_AttackSpeedBoost": anthektiteSamuraiMasterArmor_AttackSpeed = (float) value; break;
                        case "anthektiteSamuraiMasterArmor_MovementSpeed": anthektiteSamuraiMasterArmor_MovementSpeed = (float) value; break;
                        default:
                            logger.warn(errorPrefix + "unrecognized parameter name: " + key);
                    }
                }
            }
        } catch (NoSuchFileException e) {
            save();
            logger.info("Configuration file not found, default created");

        } catch (IOException e) {
            logger.warn("Could not read configuration file: ", e);
        }
        // may save twice, but not big deal
        if (version.compareTo(VERSION) < 0) {
            logger.info("Configuration file belongs to older version, updating");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.ARMOR_CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config");
            writer.newLine();
            writer.newLine();
            writer.write("[ArmorStats]\n");
            writer.write("  # Vanilla Armor Stats for comparison\n");
            writer.write("  # Format:\n");
            writer.write("  # [Material, Durability, [Helmet, Chestplate, Leggings, Boots], Enchantability, Toughness, KnockbackResistance]\n");
            writer.write("    # [Leather   :5,  [1,3,2,1], 15, 0, 0  ]\n");
            writer.write("    # [Chain     :15, [2,5,4,1], 25, 0, 0  ]\n");
            writer.write("    # [Gold      :7,  [2,5,3,1], 12, 0, 0  ]\n");
            writer.write("    # [Iron      :15, [2,6,5,2], 9,  0, 0  ]\n");
            writer.write("    # [Diamond   :33, [3,8,6,3], 10, 2, 0  ]\n");
            writer.write("    # [Netherite :37, [3,8,6,3], 15, 3, 0.1]\n");
            writer.newLine();
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Durability: 24), (Enchantability: 10)\n");
            writer.write("    steelArmor_Durability = " + steelArmor_DurabilityForType + "\n");
            writer.write("    steelArmor_Enchantability = " + steelArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("    # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("    steelArmor_Helmet = " + steelArmor_Helmet + "\n");
            writer.write("    steelArmor_Chestplate = " + steelArmor_Chestplate + "\n");
            writer.write("    steelArmor_Leggings = " + steelArmor_Leggings + "\n");
            writer.write("    steelArmor_Boots = " + steelArmor_Boots + "\n");
            writer.newLine();
            writer.write("    # Default: (Toughness: 0), (KnockbackResistance: 0), (AttackSpeedBoost: 0), (MovementSpeedBoost: 0)\n");
            writer.write("    steelArmor_Toughness = " + steelArmor_Toughness + "\n");
            writer.write("    steelArmor_KnockbackResistance = " + steelArmor_KnockbackResistance + "\n");
            writer.write("    steelArmor_AttackSpeedBoost = " + steelArmor_AttackSpeed + "\n");
            writer.write("    steelArmor_MovementSpeed = " + steelArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("    diarkriteArmor_Durability = " + diarkriteArmor_DurabilityForType + "\n");
            writer.write("    diarkriteArmor_Enchantability = " + diarkriteArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("    # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("    diarkriteArmor_Helmet = " + diarkriteArmor_Helmet + "\n");
            writer.write("    diarkriteArmor_Chestplate = " + diarkriteArmor_Chestplate + "\n");
            writer.write("    diarkriteArmor_Leggings = " + diarkriteArmor_Leggings + "\n");
            writer.write("    diarkriteArmor_Boots = " + diarkriteArmor_Boots + "\n");
            writer.newLine();
            writer.write("    # Default: (Toughness: 4), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("    diarkriteArmor_Toughness = " + diarkriteArmor_Toughness + "\n");
            writer.write("    diarkriteArmor_KnockbackResistance = " + diarkriteArmor_KnockbackResistance + "\n");
            writer.write("    diarkriteArmor_AttackSpeedBoost = " + diarkriteArmor_AttackSpeed + "\n");
            writer.write("    diarkriteArmor_MovementSpeed = " + diarkriteArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("    anthektiteArmor_Durability = " + anthektiteArmor_DurabilityForType + "\n");
            writer.write("    anthektiteArmor_Enchantability = " + anthektiteArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("    # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("    anthektiteArmor_Helmet = " + anthektiteArmor_Helmet + "\n");
            writer.write("    anthektiteArmor_Chestplate = " + anthektiteArmor_Chestplate + "\n");
            writer.write("    anthektiteArmor_Leggings = " + anthektiteArmor_Leggings + "\n");
            writer.write("    anthektiteArmor_Boots = " + anthektiteArmor_Boots + "\n");
            writer.newLine();
            writer.write("    # Default: (Toughness: 2), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("    anthektiteArmor_Toughness = " + anthektiteArmor_Toughness + "\n");
            writer.write("    anthektiteArmor_KnockbackResistance = " + anthektiteArmor_KnockbackResistance + "\n");
            writer.write("    anthektiteArmor_AttackSpeedBoost = " + anthektiteArmor_AttackSpeed + "\n");
            writer.write("    anthektiteArmor_MovementSpeed = " + anthektiteArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[AdvancedNetherite]\n");
            writer.write("  [ArmorStats]\n");
            writer.write("    [Diarkrite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("        diarkriteIronArmor_Durability = " + diarkriteIronArmor_DurabilityForType + "\n");
            writer.write("        diarkriteIronArmor_Enchantability = " + diarkriteIronArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 8), (Leggings: 6), (Boots: 4)\n");
            writer.write("        diarkriteIronArmor_Helmet = " + diarkriteIronArmor_Helmet + "\n");
            writer.write("        diarkriteIronArmor_Chestplate = " + diarkriteIronArmor_Chestplate + "\n");
            writer.write("        diarkriteIronArmor_Leggings = " + diarkriteIronArmor_Leggings + "\n");
            writer.write("        diarkriteIronArmor_Boots = " + diarkriteIronArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 4.5), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("        diarkriteIronArmor_Toughness = " + diarkriteIronArmor_Toughness + "\n");
            writer.write("        diarkriteIronArmor_KnockbackResistance = " + diarkriteIronArmor_KnockbackResistance + "\n");
            writer.write("        diarkriteIronArmor_AttackSpeedBoost = " + diarkriteIronArmor_AttackSpeed + "\n");
            writer.write("        diarkriteIronArmor_MovementSpeed = " + diarkriteIronArmor_MovementSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("        diarkriteGoldArmor_Durability = " + diarkriteGoldArmor_DurabilityForType + "\n");
            writer.write("        diarkriteGoldArmor_Enchantability = " + diarkriteGoldArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 9), (Leggings: 7), (Boots: 4)\n");
            writer.write("        diarkriteGoldArmor_Helmet = " + diarkriteGoldArmor_Helmet + "\n");
            writer.write("        diarkriteGoldArmor_Chestplate = " + diarkriteGoldArmor_Chestplate + "\n");
            writer.write("        diarkriteGoldArmor_Leggings = " + diarkriteGoldArmor_Leggings + "\n");
            writer.write("        diarkriteGoldArmor_Boots = " + diarkriteGoldArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 4.5), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("        diarkriteGoldArmor_Toughness = " + diarkriteGoldArmor_Toughness + "\n");
            writer.write("        diarkriteGoldArmor_KnockbackResistance = " + diarkriteGoldArmor_KnockbackResistance + "\n");
            writer.write("        diarkriteGoldArmor_AttackSpeedBoost = " + diarkriteGoldArmor_AttackSpeed + "\n");
            writer.write("        diarkriteGoldArmor_MovementSpeed = " + diarkriteGoldArmor_MovementSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("        diarkriteEmeraldArmor_Durability = " + diarkriteEmeraldArmor_DurabilityForType + "\n");
            writer.write("        diarkriteEmeraldArmor_Enchantability = " + diarkriteEmeraldArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 9), (Leggings: 7), (Boots: 4)\n");
            writer.write("        diarkriteEmeraldArmor_Helmet = " + diarkriteEmeraldArmor_Helmet + "\n");
            writer.write("        diarkriteEmeraldArmor_Chestplate = " + diarkriteEmeraldArmor_Chestplate + "\n");
            writer.write("        diarkriteEmeraldArmor_Leggings = " + diarkriteEmeraldArmor_Leggings + "\n");
            writer.write("        diarkriteEmeraldArmor_Boots = " + diarkriteEmeraldArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 4.5), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("        diarkriteEmeraldArmor_Toughness = " + diarkriteEmeraldArmor_Toughness + "\n");
            writer.write("        diarkriteEmeraldArmor_KnockbackResistance = " + diarkriteEmeraldArmor_KnockbackResistance + "\n");
            writer.write("        diarkriteEmeraldArmor_AttackSpeedBoost = " + diarkriteEmeraldArmor_AttackSpeed + "\n");
            writer.write("        diarkriteEmeraldArmor_MovementSpeed = " + diarkriteEmeraldArmor_MovementSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("        diarkriteDiamondArmor_Durability = " + diarkriteDiamondArmor_DurabilityForType + "\n");
            writer.write("        diarkriteDiamondArmor_Enchantability = " + diarkriteDiamondArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 5), (Chestplate: 9), (Leggings: 7), (Boots: 5)\n");
            writer.write("        diarkriteDiamondArmor_Helmet = " + diarkriteDiamondArmor_Helmet + "\n");
            writer.write("        diarkriteDiamondArmor_Chestplate = " + diarkriteDiamondArmor_Chestplate + "\n");
            writer.write("        diarkriteDiamondArmor_Leggings = " + diarkriteDiamondArmor_Leggings + "\n");
            writer.write("        diarkriteDiamondArmor_Boots = " + diarkriteDiamondArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 5), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("        diarkriteDiamondArmor_Toughness = " + diarkriteDiamondArmor_Toughness + "\n");
            writer.write("        diarkriteDiamondArmor_KnockbackResistance = " + diarkriteDiamondArmor_KnockbackResistance + "\n");
            writer.write("        diarkriteDiamondArmor_AttackSpeedBoost = " + diarkriteDiamondArmor_AttackSpeed + "\n");
            writer.write("        diarkriteDiamondArmor_MovementSpeed = " + diarkriteDiamondArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [Anthektite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("        anthektiteIronArmor_Durability = " + anthektiteIronArmor_DurabilityForType + "\n");
            writer.write("        anthektiteIronArmor_Enchantability = " + anthektiteIronArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 8), (Leggings: 6), (Boots: 4)\n");
            writer.write("        anthektiteIronArmor_Helmet = " + anthektiteIronArmor_Helmet + "\n");
            writer.write("        anthektiteIronArmor_Chestplate = " + anthektiteIronArmor_Chestplate + "\n");
            writer.write("        anthektiteIronArmor_Leggings = " + anthektiteIronArmor_Leggings + "\n");
            writer.write("        anthektiteIronArmor_Boots = " + anthektiteIronArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 2.5), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("        anthektiteIronArmor_Toughness = " + anthektiteIronArmor_Toughness + "\n");
            writer.write("        anthektiteIronArmor_KnockbackResistance = " + anthektiteIronArmor_KnockbackResistance + "\n");
            writer.write("        anthektiteIronArmor_AttackSpeedBoost = " + anthektiteIronArmor_AttackSpeed + "\n");
            writer.write("        anthektiteIronArmor_MovementSpeed = " + anthektiteIronArmor_MovementSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("        anthektiteGoldArmor_Durability = " + anthektiteGoldArmor_DurabilityForType + "\n");
            writer.write("        anthektiteGoldArmor_Enchantability = " + anthektiteGoldArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 9), (Leggings: 7), (Boots: 4)\n");
            writer.write("        anthektiteGoldArmor_Helmet = " + anthektiteGoldArmor_Helmet + "\n");
            writer.write("        anthektiteGoldArmor_Chestplate = " + anthektiteGoldArmor_Chestplate + "\n");
            writer.write("        anthektiteGoldArmor_Leggings = " + anthektiteGoldArmor_Leggings + "\n");
            writer.write("        anthektiteGoldArmor_Boots = " + anthektiteGoldArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 2.5), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("        anthektiteGoldArmor_Toughness = " + anthektiteGoldArmor_Toughness + "\n");
            writer.write("        anthektiteGoldArmor_KnockbackResistance = " + anthektiteGoldArmor_KnockbackResistance + "\n");
            writer.write("        anthektiteGoldArmor_AttackSpeedBoost = " + anthektiteGoldArmor_AttackSpeed + "\n");
            writer.write("        anthektiteGoldArmor_MovementSpeed = " + anthektiteGoldArmor_MovementSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("        anthektiteEmeraldArmor_Durability = " + anthektiteEmeraldArmor_DurabilityForType + "\n");
            writer.write("        anthektiteEmeraldArmor_Enchantability = " + anthektiteEmeraldArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 4), (Chestplate: 9), (Leggings: 7), (Boots: 4)\n");
            writer.write("        anthektiteEmeraldArmor_Helmet = " + anthektiteEmeraldArmor_Helmet + "\n");
            writer.write("        anthektiteEmeraldArmor_Chestplate = " + anthektiteEmeraldArmor_Chestplate + "\n");
            writer.write("        anthektiteEmeraldArmor_Leggings = " + anthektiteEmeraldArmor_Leggings + "\n");
            writer.write("        anthektiteEmeraldArmor_Boots = " + anthektiteEmeraldArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 2.5), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("        anthektiteEmeraldArmor_Toughness = " + anthektiteEmeraldArmor_Toughness + "\n");
            writer.write("        anthektiteEmeraldArmor_KnockbackResistance = " + anthektiteEmeraldArmor_KnockbackResistance + "\n");
            writer.write("        anthektiteEmeraldArmor_AttackSpeedBoost = " + anthektiteEmeraldArmor_AttackSpeed + "\n");
            writer.write("        anthektiteEmeraldArmor_MovementSpeed = " + anthektiteEmeraldArmor_MovementSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("        anthektiteDiamondArmor_Durability = " + anthektiteDiamondArmor_DurabilityForType + "\n");
            writer.write("        anthektiteDiamondArmor_Enchantability = " + anthektiteDiamondArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("        # Default: (Helmet: 5), (Chestplate: 9), (Leggings: 7), (Boots: 5)\n");
            writer.write("        anthektiteDiamondArmor_Helmet = " + anthektiteDiamondArmor_Helmet + "\n");
            writer.write("        anthektiteDiamondArmor_Chestplate = " + anthektiteDiamondArmor_Chestplate + "\n");
            writer.write("        anthektiteDiamondArmor_Leggings = " + anthektiteDiamondArmor_Leggings + "\n");
            writer.write("        anthektiteDiamondArmor_Boots = " + anthektiteDiamondArmor_Boots + "\n");
            writer.newLine();
            writer.write("        # Default: (Toughness: 3), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("        anthektiteDiamondArmor_Toughness = " + anthektiteDiamondArmor_Toughness + "\n");
            writer.write("        anthektiteDiamondArmor_KnockbackResistance = " + anthektiteDiamondArmor_KnockbackResistance + "\n");
            writer.write("        anthektiteDiamondArmor_AttackSpeedBoost = " + anthektiteDiamondArmor_AttackSpeed + "\n");
            writer.write("        anthektiteDiamondArmor_MovementSpeed = " + anthektiteDiamondArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[EpicSamurai]");
            writer.write("  [Steel]");
            writer.write("    [Samurai]");
            writer.write("      # Default: (Durability: 24), (Enchantability: 10)\n");
            writer.write("      steelSamuraiArmor_Durability = " + steelSamuraiArmor_DurabilityForType + "\n");
            writer.write("      steelSamuraiArmor_Enchantability = " + steelSamuraiArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      steelSamuraiArmor_Helmet = " + steelSamuraiArmor_Helmet + "\n");
            writer.write("      steelSamuraiArmor_Chestplate = " + steelSamuraiArmor_Chestplate + "\n");
            writer.write("      steelSamuraiArmor_Leggings = " + steelSamuraiArmor_Leggings + "\n");
            writer.write("      steelSamuraiArmor_Boots = " + steelSamuraiArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 0), (KnockbackResistance: 0), (AttackSpeedBoost: 0), (MovementSpeedBoost: 0)\n");
            writer.write("      steelSamuraiArmor_Toughness = " + steelSamuraiArmor_Toughness + "\n");
            writer.write("      steelSamuraiArmor_KnockbackResistance = " + steelSamuraiArmor_KnockbackResistance + "\n");
            writer.write("      steelSamuraiArmor_AttackSpeedBoost = " + steelSamuraiArmor_AttackSpeed + "\n");
            writer.write("      steelSamuraiArmor_MovementSpeed = " + steelSamuraiArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiLight]");
            writer.write("      # Default: (Durability: 24), (Enchantability: 10)\n");
            writer.write("      steelSamuraiLightArmor_Durability = " + steelSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("      steelSamuraiLightArmor_Enchantability = " + steelSamuraiLightArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      steelSamuraiLightArmor_Helmet = " + steelSamuraiLightArmor_Helmet + "\n");
            writer.write("      steelSamuraiLightArmor_Chestplate = " + steelSamuraiLightArmor_Chestplate + "\n");
            writer.write("      steelSamuraiLightArmor_Leggings = " + steelSamuraiLightArmor_Leggings + "\n");
            writer.write("      steelSamuraiLightArmor_Boots = " + steelSamuraiLightArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 0), (KnockbackResistance: 0), (AttackSpeedBoost: 0), (MovementSpeedBoost: 0)\n");
            writer.write("      steelSamuraiLightArmor_Toughness = " + steelSamuraiLightArmor_Toughness + "\n");
            writer.write("      steelSamuraiLightArmor_KnockbackResistance = " + steelSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("      steelSamuraiLightArmor_AttackSpeedBoost = " + steelSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("      steelSamuraiLightArmor_MovementSpeed = " + steelSamuraiLightArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiMaster]");
            writer.write("      # Default: (Durability: 24), (Enchantability: 10)\n");
            writer.write("      steelSamuraiMasterArmor_Durability = " + steelSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("      steelSamuraiMasterArmor_Enchantability = " + steelSamuraiMasterArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      steelSamuraiMasterArmor_Helmet = " + steelSamuraiMasterArmor_Helmet + "\n");
            writer.write("      steelSamuraiMasterArmor_Chestplate = " + steelSamuraiMasterArmor_Chestplate + "\n");
            writer.write("      steelSamuraiMasterArmor_Leggings = " + steelSamuraiMasterArmor_Leggings + "\n");
            writer.write("      steelSamuraiMasterArmor_Boots = " + steelSamuraiMasterArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 0), (KnockbackResistance: 0), (AttackSpeedBoost: 0), (MovementSpeedBoost: 0)\n");
            writer.write("      steelSamuraiMasterArmor_Toughness = " + steelSamuraiMasterArmor_Toughness + "\n");
            writer.write("      steelSamuraiMasterArmor_KnockbackResistance = " + steelSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("      steelSamuraiMasterArmor_AttackSpeedBoost = " + steelSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("      steelSamuraiMasterArmor_MovementSpeed = " + steelSamuraiMasterArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("  [Diarkrite]");
            writer.write("    [Samurai]");
            writer.write("      # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("      diarkriteSamuraiArmor_Durability = " + diarkriteSamuraiArmor_DurabilityForType + "\n");
            writer.write("      diarkriteSamuraiArmor_Enchantability = " + diarkriteSamuraiArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      diarkriteSamuraiArmor_Helmet = " + diarkriteSamuraiArmor_Helmet + "\n");
            writer.write("      diarkriteSamuraiArmor_Chestplate = " + diarkriteSamuraiArmor_Chestplate + "\n");
            writer.write("      diarkriteSamuraiArmor_Leggings = " + diarkriteSamuraiArmor_Leggings + "\n");
            writer.write("      diarkriteSamuraiArmor_Boots = " + diarkriteSamuraiArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 4), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("      diarkriteSamuraiArmor_Toughness = " + diarkriteSamuraiArmor_Toughness + "\n");
            writer.write("      diarkriteSamuraiArmor_KnockbackResistance = " + diarkriteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("      diarkriteSamuraiArmor_AttackSpeedBoost = " + diarkriteSamuraiArmor_AttackSpeed + "\n");
            writer.write("      diarkriteSamuraiArmor_MovementSpeed = " + diarkriteSamuraiArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiLight]");
            writer.write("      # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("      diarkriteSamuraiLightArmor_Durability = " + diarkriteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("      diarkriteSamuraiLightArmor_Enchantability = " + diarkriteSamuraiLightArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      diarkriteSamuraiLightArmor_Helmet = " + diarkriteSamuraiLightArmor_Helmet + "\n");
            writer.write("      diarkriteSamuraiLightArmor_Chestplate = " + diarkriteSamuraiLightArmor_Chestplate + "\n");
            writer.write("      diarkriteSamuraiLightArmor_Leggings = " + diarkriteSamuraiLightArmor_Leggings + "\n");
            writer.write("      diarkriteSamuraiLightArmor_Boots = " + diarkriteSamuraiLightArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 4), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("      diarkriteSamuraiLightArmor_Toughness = " + diarkriteSamuraiLightArmor_Toughness + "\n");
            writer.write("      diarkriteSamuraiLightArmor_KnockbackResistance = " + diarkriteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("      diarkriteSamuraiLightArmor_AttackSpeedBoost = " + diarkriteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("      diarkriteSamuraiLightArmor_MovementSpeed = " + diarkriteSamuraiLightArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiMaster]");
            writer.write("      # Default: (Durability: 38), (Enchantability: 18)\n");
            writer.write("      diarkriteSamuraiMasterArmor_Durability = " + diarkriteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_Enchantability = " + diarkriteSamuraiMasterArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      diarkriteSamuraiMasterArmor_Helmet = " + diarkriteSamuraiMasterArmor_Helmet + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_Chestplate = " + diarkriteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_Leggings = " + diarkriteSamuraiMasterArmor_Leggings + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_Boots = " + diarkriteSamuraiMasterArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 4), (KnockbackResistance: 0.2), (AttackSpeedBoost: 0), (MovementSpeedBoost: -0.04)\n");
            writer.write("      diarkriteSamuraiMasterArmor_Toughness = " + diarkriteSamuraiMasterArmor_Toughness + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_KnockbackResistance = " + diarkriteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_AttackSpeedBoost = " + diarkriteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("      diarkriteSamuraiMasterArmor_MovementSpeed = " + diarkriteSamuraiMasterArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("  [Anthektite]");
            writer.write("    [Samurai]");
            writer.write("      # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("      anthektiteSamuraiArmor_Durability = " + anthektiteSamuraiArmor_DurabilityForType + "\n");
            writer.write("      anthektiteSamuraiArmor_Enchantability = " + anthektiteSamuraiArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      anthektiteSamuraiArmor_Helmet = " + anthektiteSamuraiArmor_Helmet + "\n");
            writer.write("      anthektiteSamuraiArmor_Chestplate = " + anthektiteSamuraiArmor_Chestplate + "\n");
            writer.write("      anthektiteSamuraiArmor_Leggings = " + anthektiteSamuraiArmor_Leggings + "\n");
            writer.write("      anthektiteSamuraiArmor_Boots = " + anthektiteSamuraiArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 2), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("      anthektiteSamuraiArmor_Toughness = " + anthektiteSamuraiArmor_Toughness + "\n");
            writer.write("      anthektiteSamuraiArmor_KnockbackResistance = " + anthektiteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("      anthektiteSamuraiArmor_AttackSpeedBoost = " + anthektiteSamuraiArmor_AttackSpeed + "\n");
            writer.write("      anthektiteSamuraiArmor_MovementSpeed = " + anthektiteSamuraiArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiLight]");
            writer.write("      # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("      anthektiteSamuraiLightArmor_Durability = " + anthektiteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("      anthektiteSamuraiLightArmor_Enchantability = " + anthektiteSamuraiLightArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      anthektiteSamuraiLightArmor_Helmet = " + anthektiteSamuraiLightArmor_Helmet + "\n");
            writer.write("      anthektiteSamuraiLightArmor_Chestplate = " + anthektiteSamuraiLightArmor_Chestplate + "\n");
            writer.write("      anthektiteSamuraiLightArmor_Leggings = " + anthektiteSamuraiLightArmor_Leggings + "\n");
            writer.write("      anthektiteSamuraiLightArmor_Boots = " + anthektiteSamuraiLightArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 2), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("      anthektiteSamuraiLightArmor_Toughness = " + anthektiteSamuraiLightArmor_Toughness + "\n");
            writer.write("      anthektiteSamuraiLightArmor_KnockbackResistance = " + anthektiteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("      anthektiteSamuraiLightArmor_AttackSpeedBoost = " + anthektiteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("      anthektiteSamuraiLightArmor_MovementSpeed = " + anthektiteSamuraiLightArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("    [SamuraiMaster]");
            writer.write("      # Default: (Durability: 35), (Enchantability: 15)\n");
            writer.write("      anthektiteSamuraiMasterArmor_Durability = " + anthektiteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_Enchantability = " + anthektiteSamuraiMasterArmor_Enchantability + "\n");
            writer.newLine();
            writer.write("      # Default: (Helmet: 3), (Chestplate: 8), (Leggings: 6), (Boots: 3)\n");
            writer.write("      anthektiteSamuraiMasterArmor_Helmet = " + anthektiteSamuraiMasterArmor_Helmet + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_Chestplate = " + anthektiteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_Leggings = " + anthektiteSamuraiMasterArmor_Leggings + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_Boots = " + anthektiteSamuraiMasterArmor_Boots + "\n");
            writer.newLine();
            writer.write("      # Default: (Toughness: 2), (KnockbackResistance: 0.05), (AttackSpeedBoost: 0.1), (MovementSpeedBoost: 0)\n");
            writer.write("      anthektiteSamuraiMasterArmor_Toughness = " + anthektiteSamuraiMasterArmor_Toughness + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_KnockbackResistance = " + anthektiteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_AttackSpeedBoost = " + anthektiteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("      anthektiteSamuraiMasterArmor_MovementSpeed = " + anthektiteSamuraiMasterArmor_MovementSpeed + "\n");
            writer.newLine();
            writer.write("\n");

        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
