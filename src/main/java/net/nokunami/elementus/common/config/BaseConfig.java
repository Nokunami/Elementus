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
public class BaseConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final BaseConfig INSTANCE = new BaseConfig();

    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion("1.0");
    // values exposed to other classes
    //Tiers
    public static int steelTierHarvestLevel;
    public static int steelTierDurability;
    public static float steelTierEfficiency;
    public static float steelTierDamage;
    public static int steelTierEnchantability;
    public static double steelWeaponSpeedBoost;
    public static double steelWeaponHeaviness;

    public static int diarkriteTierHarvestLevel;
    public static int diarkriteTierDurability;
    public static float diarkriteTierEfficiency;
    public static float diarkriteTierDamage;
    public static int diarkriteTierEnchantability;
    public static double diarkriteWeaponSpeedBoost;
    public static double diarkriteWeaponHeaviness;

    public static int anthektiteTierHarvestLevel;
    public static int anthektiteTierDurability;
    public static float anthektiteTierEfficiency;
    public static float anthektiteTierDamage;
    public static int anthektiteTierEnchantability;
    public static double anthektiteWeaponSpeedBoost;
    public static double anthektiteWeaponHeaviness;

    //Tools
    public static int steelSwordDamage;
    public static float steelSwordAttackSpeed;
    public static float steelShovelDamage;
    public static float steelShovelAttackSpeed;
    public static int steelPickaxeDamage;
    public static float steelPickaxeAttackSpeed;
    public static float steelAxeDamage;
    public static float steelAxeAttackSpeed;
    public static int steelHoeDamage;
    public static float steelHoeAttackSpeed;
    public static int steelShieldDurability;

    public static int diarkriteSwordDamage;
    public static float diarkriteSwordAttackSpeed;
    public static float diarkriteShovelDamage;
    public static float diarkriteShovelAttackSpeed;
    public static int diarkritePickaxeDamage;
    public static float diarkritePickaxeAttackSpeed;
    public static float diarkriteAxeDamage;
    public static float diarkriteAxeAttackSpeed;
    public static int diarkriteHoeDamage;
    public static float diarkriteHoeAttackSpeed;
    public static int diarkriteShieldDurability;

    public static int anthektiteSwordDamage;
    public static float anthektiteSwordAttackSpeed;
    public static float anthektiteShovelDamage;
    public static float anthektiteShovelAttackSpeed;
    public static int anthektitePickaxeDamage;
    public static float anthektitePickaxeAttackSpeed;
    public static float anthektiteAxeDamage;
    public static float anthektiteAxeAttackSpeed;
    public static int anthektiteHoeDamage;
    public static float anthektiteHoeAttackSpeed;
    public static int anthektiteShieldDurability;

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
        steelTierHarvestLevel = 3;
        steelTierDurability = 756;
        steelTierEfficiency = 7.0F;
        steelTierDamage = 3.0F;
        steelTierEnchantability = 15;
        steelWeaponSpeedBoost = 0.0;
        steelWeaponHeaviness = 0.2;

        diarkriteTierHarvestLevel = 4;
        diarkriteTierDurability = 2546;
        diarkriteTierEfficiency = 9.0F;
        diarkriteTierDamage = 6.0F;
        diarkriteTierEnchantability = 15;
        diarkriteWeaponSpeedBoost = 0.0;
        diarkriteWeaponHeaviness = 0.3;

        anthektiteTierHarvestLevel = 4;
        anthektiteTierDurability = 1946;
        anthektiteTierEfficiency = 12.0F;
        anthektiteTierDamage = 3.0F;
        anthektiteTierEnchantability = 15;
        anthektiteWeaponSpeedBoost = 0.5;
        anthektiteWeaponHeaviness = 0.0;

        steelSwordDamage = 3;
        steelSwordAttackSpeed = -2.4F;
        steelShovelDamage = 1.5F;
        steelShovelAttackSpeed = -3.0F;
        steelPickaxeDamage = 1;
        steelPickaxeAttackSpeed = -2.8F;
        steelAxeDamage = 5F;
        steelAxeAttackSpeed = -3.1F;
        steelHoeDamage = -3;
        steelHoeAttackSpeed = 0.0F;
        steelShieldDurability = 457;

        diarkriteSwordDamage = 3;
        diarkriteSwordAttackSpeed = -2.4F;
        diarkriteShovelDamage = 1.5F;
        diarkriteShovelAttackSpeed = -3.0F;
        diarkritePickaxeDamage = 1;
        diarkritePickaxeAttackSpeed = -2.8F;
        diarkriteAxeDamage = 6F;
        diarkriteAxeAttackSpeed = -3.1F;
        diarkriteHoeDamage = -6;
        diarkriteHoeAttackSpeed = 0.0F;
        diarkriteShieldDurability = 843;

        anthektiteSwordDamage = 3;
        anthektiteSwordAttackSpeed = -2.4F;
        anthektiteShovelDamage = 1.5F;
        anthektiteShovelAttackSpeed = -3.0F;
        anthektitePickaxeDamage = 1;
        anthektitePickaxeAttackSpeed = -2.8F;
        anthektiteAxeDamage = 5F;
        anthektiteAxeAttackSpeed = -3.1F;
        anthektiteHoeDamage = -3;
        anthektiteHoeAttackSpeed = 0.0F;
        anthektiteShieldDurability = 598;


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

        //Iron's Spells 'n Spellbooks Armor Diarkrite
        diarkriteMageArmor_DurabilityForType = 38;
        diarkriteMageArmor_Helmet = 3;
        diarkriteMageArmor_Chestplate = 8;
        diarkriteMageArmor_Leggings = 6;
        diarkriteMageArmor_Boots = 3;
        diarkriteMageArmor_Enchantability = 18;
        diarkriteMageArmor_Toughness = 4F;
        diarkriteMageArmor_KnockbackResistance = 0.2F;
        diarkriteMageArmor_AttackSpeed = 0F;
        diarkriteMageArmor_MovementSpeed = -0.04F;
        diarkriteMageArmor_MaxMana = 135;
        diarkriteMageArmor_ManaRegen = -0.08F;
        diarkriteMageArmor_SpellPower = 0.05F;
        diarkriteMageArmor_SpellResist = 0.0;
        diarkriteMageArmor_CastTime = 0.0;
        diarkriteMageArmor_Cooldown = 0.0;

        anthektiteMageArmor_DurabilityForType = 35;
        anthektiteMageArmor_Helmet = 3;
        anthektiteMageArmor_Chestplate = 8;
        anthektiteMageArmor_Leggings = 6;
        anthektiteMageArmor_Boots = 3;
        anthektiteMageArmor_Enchantability = 15;
        anthektiteMageArmor_Toughness = 2F;
        anthektiteMageArmor_KnockbackResistance = 0.05F;
        anthektiteMageArmor_AttackSpeed = 0.1F;
        anthektiteMageArmor_MovementSpeed = 0F;
        anthektiteMageArmor_MaxMana = 100;
        anthektiteMageArmor_ManaRegen = 0.08F;
        anthektiteMageArmor_SpellPower = 0.0;
        anthektiteMageArmor_SpellResist = 0.0;
        anthektiteMageArmor_CastTime = 0.0;
        anthektiteMageArmor_Cooldown = 0.0;

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
        try (BufferedReader reader = Files.newBufferedReader(Elementus.CONFIG_PATH)) {
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

                String errorPrefix = Elementus.CONFIG_PATH + ": line " + lineNumber + ": ";
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
                        case "steelTier_HarvestLevel": steelTierHarvestLevel = (int) value; break;
                        case "steelTier_Durability": steelTierDurability = (int) value; break;
                        case "steelTier_Efficiency": steelTierEfficiency = (float) value; break;
                        case "steelTier_Damage": steelTierDamage = (float) value; break;
                        case "steelTier_Enchantability": steelTierEnchantability = (int) value; break;
                        case "steelTier_WeaponSpeedBoost": steelWeaponSpeedBoost = (float) value; break;
                        case "steelTier_WeaponHeaviness": steelWeaponHeaviness = (float) value; break;

                        case "diarkriteTier_HarvestLevel": diarkriteTierHarvestLevel = (int) value; break;
                        case "diarkriteTier_Durability": diarkriteTierDurability = (int) value; break;
                        case "diarkriteTier_Efficiency": diarkriteTierEfficiency = (float) value; break;
                        case "diarkriteTier_Damage": diarkriteTierDamage = (float) value; break;
                        case "diarkriteTier_Enchantability": diarkriteTierEnchantability = (int) value; break;
                        case "diarkriteTier_WeaponSpeedBoost": diarkriteWeaponSpeedBoost = (float) value; break;
                        case "diarkriteTier_WeaponHeaviness": diarkriteWeaponHeaviness = (float) value; break;

                        case "anthektiteTier_HarvestLevel": anthektiteTierHarvestLevel = (int) value; break;
                        case "anthektiteTier_Durability": anthektiteTierDurability = (int) value; break;
                        case "anthektiteTier_Efficiency": anthektiteTierEfficiency = (float) value; break;
                        case "anthektiteTier_Damage": anthektiteTierDamage = (float) value; break;
                        case "anthektiteTier_Enchantability": anthektiteTierEnchantability = (int) value; break;
                        case "anthektiteTier_WeaponSpeedBoost": anthektiteWeaponSpeedBoost = (float) value; break;
                        case "anthektiteTier_WeaponHeaviness": anthektiteWeaponHeaviness = (float) value; break;


                        case "steelSword_Damage": steelSwordDamage = (int) value; break;
                        case "steelSword_Speed": steelSwordAttackSpeed = (float) value; break;

                        case "steelShovel_Damage": steelShovelDamage = (float) value; break;
                        case "steelShovel_Speed": steelShovelAttackSpeed = (float) value; break;

                        case "steelPickaxe_Damage": steelPickaxeDamage = (int) value; break;
                        case "steelPickaxe_Speed": steelPickaxeAttackSpeed = (float) value; break;

                        case "steelAxe_Damage": steelAxeDamage = (float) value; break;
                        case "steelAxe_Speed": steelAxeAttackSpeed = (float) value; break;

                        case "steelHoe_Damage": steelHoeDamage = (int) value; break;
                        case "steelHoe_Speed": steelHoeAttackSpeed = (float) value; break;

                        case "steelShield_Durability": steelShieldDurability = (int) value; break;


                        case "diarkriteSword_Damage": diarkriteSwordDamage = (int) value; break;
                        case "diarkriteSword_Speed": diarkriteSwordAttackSpeed = (float) value; break;

                        case "diarkriteShovel_Damage": diarkriteShovelDamage = (float) value; break;
                        case "diarkriteShovel_Speed": diarkriteShovelAttackSpeed = (float) value; break;

                        case "diarkritePickaxe_Damage": diarkritePickaxeDamage = (int) value; break;
                        case "diarkritePickaxe_Speed": diarkritePickaxeAttackSpeed = (float) value; break;

                        case "diarkriteAxe_Damage": diarkriteAxeDamage = (float) value; break;
                        case "diarkriteAxe_Speed": diarkriteAxeAttackSpeed = (float) value; break;

                        case "diarkriteHoe_Damage": diarkriteHoeDamage = (int) value; break;
                        case "diarkriteHoe_Speed": diarkriteHoeAttackSpeed = (float) value; break;

                        case "diarkriteShield_Durability": diarkriteShieldDurability = (int) value; break;


                        case "anthektiteSword_Damage": anthektiteSwordDamage = (int) value; break;
                        case "anthektiteSword_Speed": anthektiteSwordAttackSpeed = (float) value; break;

                        case "anthektiteShovel_Damage": anthektiteShovelDamage = (float) value; break;
                        case "anthektiteShovel_Speed": anthektiteShovelAttackSpeed = (float) value; break;

                        case "anthektitePickaxe_Damage": anthektitePickaxeDamage = (int) value; break;
                        case "anthektitePickaxe_Speed": anthektitePickaxeAttackSpeed = (float) value; break;

                        case "anthektiteAxe_Damage": anthektiteAxeDamage = (float) value; break;
                        case "anthektiteAxe_Speed": anthektiteAxeAttackSpeed = (float) value; break;

                        case "anthektiteHoe_Damage": anthektiteHoeDamage = (int) value; break;
                        case "anthektiteHoe_Speed": anthektiteHoeAttackSpeed = (float) value; break;

                        case "anthektiteShield_Durability": anthektiteShieldDurability = (int) value; break;


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



//                        case "steel_golem_max_health":
//                            steel_golem_max_health = value;
//                            break;
//                        case "steel_golem_attack_damage":
//                            steel_golem_attack_damage = value;
//                            break;
//                        case "steel_golem_use_ingot_heal":
//                            steel_golem_use_ingot_heal = (float) value;
//                            break;
//                        case "steel_golem_movement_speed":
//                            steel_golem_movement_speed = value;
//                            break;
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
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config");
            writer.newLine();
            writer.newLine();
            writer.write("[ToolTiers]\n");
            writer.write("  [Stat_Examples]\n");
            writer.write("  # Format:\n");
            writer.write("  # [Harvest Level, Durability, Damage, Efficiency, Enchantability, WeaponSpeedBoost, WeaponsHeaviness]\n");
            writer.write("    # [Wood      :0,   59,  2, 0, 15]\n");
            writer.write("    # [Gold      :0,   32, 12, 0, 22]\n");
            writer.write("    # [Stone     :1,  131,  4, 1,  5]\n");
            writer.write("    # [Iron      :2,  250,  6, 2, 14]\n");
            writer.write("    # [Diamond   :3, 1561,  8, 3, 10]\n");
            writer.write("    # [Netherite :4, 2031,  9, 4, 15]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default Values: 3, 756, 3.0, 7.0, 15, 0.0, 0.2\n");
            writer.write("    steelTier_Harvestlevel = " + steelTierHarvestLevel + "\n");
            writer.write("    steelTier_Durability = " + steelTierDurability + "\n");
            writer.write("    steelTier_Damage = " + steelTierDamage + "\n");
            writer.write("    steelTier_Efficiency = " + steelTierEfficiency + "\n");
            writer.write("    steelTier_Enchantability = " + steelTierEnchantability + "\n");
            writer.write("    steelTier_WeaponSpeedBoost = " + steelWeaponSpeedBoost + "\n");
            writer.write("    steelTier_WeaponHeaviness = " + steelWeaponHeaviness + "\n");
            writer.newLine();
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default Values: 4, 2546, 6.0, 9.0, 15, 0.0, 0.3\n");
            writer.write("    diarkriteTierHarvest_level = " + diarkriteTierHarvestLevel + "\n");
            writer.write("    diarkriteTierDurability = " + diarkriteTierDurability + "\n");
            writer.write("    diarkriteTierDamage = " + diarkriteTierDamage + "\n");
            writer.write("    diarkriteTierEfficiency = " + diarkriteTierEfficiency + "\n");
            writer.write("    diarkriteTierEnchantability = " + diarkriteTierEnchantability + "\n");
            writer.write("    diarkriteTier_WeaponSpeedBoost = " + diarkriteWeaponSpeedBoost + "\n");
            writer.write("    diarkriteTier_WeaponHeaviness = " + diarkriteWeaponHeaviness + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    # Default Values: 4, 1946, 3.0, 12.0, 15, 0.5, 0.0\n");
            writer.write("    anthektiteTierHarvest_level = " + anthektiteTierHarvestLevel + "\n");
            writer.write("    anthektiteTierDurability = " + anthektiteTierDurability + "\n");
            writer.write("    anthektiteTierDamage = " + anthektiteTierDamage + "\n");
            writer.write("    anthektiteTierEfficiency = " + anthektiteTierEfficiency + "\n");
            writer.write("    anthektiteTierEnchantability = " + anthektiteTierEnchantability + "\n");
            writer.write("    anthektiteTier_WeaponSpeedBoost = " + anthektiteWeaponSpeedBoost + "\n");
            writer.write("    anthektiteTier_WeaponHeaviness = " + anthektiteWeaponHeaviness + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[ToolStats]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    steelSword_Damage = " + steelSwordDamage + "\n");
            writer.write("    steelSword_Speed = " + steelSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    steelShovel_Damage = " + steelShovelDamage + "\n");
            writer.write("    steelShovel_Speed = " + steelShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    steelPickaxe_Damage = " + steelPickaxeDamage + "\n");
            writer.write("    steelPickaxe_Speed = " + steelPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("    steelAxe_Damage = " + steelAxeDamage + "\n");
            writer.write("    steelAxe_Speed = " + steelAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("    steelHoe_Damage = " + steelHoeDamage + "\n");
            writer.write("    steelHoe_Speed = " + steelHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    steelShield_Durability = " + steelShieldDurability + "\n");
            writer.newLine();
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    diarkriteSword_Damage = " + diarkriteSwordDamage + "\n");
            writer.write("    diarkriteSword_Speed = " + diarkriteSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    diarkriteShovel_Damage = " + diarkriteShovelDamage + "\n");
            writer.write("    diarkriteShovel_Speed = " + diarkriteShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    diarkritePickaxe_Damage = " + diarkritePickaxeDamage + "\n");
            writer.write("    diarkritePickaxe_Speed = " + diarkritePickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 6), (Attack Speed: -3.1)\n");
            writer.write("    diarkriteAxe_Damage = " + diarkriteAxeDamage + "\n");
            writer.write("    diarkriteAxe_Speed = " + diarkriteAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("    diarkriteHoe_Damage = " + diarkriteHoeDamage + "\n");
            writer.write("    diarkriteHoe_Speed = " + diarkriteHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    diarkriteShield_Durability = " + diarkriteShieldDurability + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    anthektiteSword_Damage = " + anthektiteSwordDamage + "\n");
            writer.write("    anthektiteSword_Speed = " + anthektiteSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    anthektiteShovel_Damage = " + anthektiteShovelDamage + "\n");
            writer.write("    anthektiteShovel_Speed = " + anthektiteShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    anthektitePickaxe_Damage = " + anthektitePickaxeDamage + "\n");
            writer.write("    anthektitePickaxe_Speed = " + anthektitePickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("    anthektiteAxe_Damage = " + anthektiteAxeDamage + "\n");
            writer.write("    anthektiteAxe_Speed = " + anthektiteAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("    anthektiteHoe_Damage = " + anthektiteHoeDamage + "\n");
            writer.write("    anthektiteHoe_Speed = " + anthektiteHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    anthektiteShield_Durability = " + anthektiteShieldDurability + "\n");
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
