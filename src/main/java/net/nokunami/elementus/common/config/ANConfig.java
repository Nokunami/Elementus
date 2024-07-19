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
public class ANConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ANConfig INSTANCE = new ANConfig();
    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion("1.0");
    // values exposed to other classes
    //AdvancedNetherite Tiers Diarkrite
    public static int diarkriteIronTierHarvestLevel;
    public static int diarkriteIronTierDurability;
    public static float diarkriteIronTierEfficiency;
    public static float diarkriteIronTierDamage;
    public static int diarkriteIronTierEnchantability;

    public static int diarkriteGoldTierHarvestLevel;
    public static int diarkriteGoldTierDurability;
    public static float diarkriteGoldTierEfficiency;
    public static float diarkriteGoldTierDamage;
    public static int diarkriteGoldTierEnchantability;

    public static int diarkriteEmeraldTierHarvestLevel;
    public static int diarkriteEmeraldTierDurability;
    public static float diarkriteEmeraldTierEfficiency;
    public static float diarkriteEmeraldTierDamage;
    public static int diarkriteEmeraldTierEnchantability;

    public static int diarkriteDiamondTierHarvestLevel;
    public static int diarkriteDiamondTierDurability;
    public static float diarkriteDiamondTierEfficiency;
    public static float diarkriteDiamondTierDamage;
    public static int diarkriteDiamondTierEnchantability;

    //AdvancedNetherite Tiers Anthektite
    public static int anthektiteIronTierHarvestLevel;
    public static int anthektiteIronTierDurability;
    public static float anthektiteIronTierEfficiency;
    public static float anthektiteIronTierDamage;
    public static int anthektiteIronTierEnchantability;

    public static int anthektiteGoldTierHarvestLevel;
    public static int anthektiteGoldTierDurability;
    public static float anthektiteGoldTierEfficiency;
    public static float anthektiteGoldTierDamage;
    public static int anthektiteGoldTierEnchantability;

    public static int anthektiteEmeraldTierHarvestLevel;
    public static int anthektiteEmeraldTierDurability;
    public static float anthektiteEmeraldTierEfficiency;
    public static float anthektiteEmeraldTierDamage;
    public static int anthektiteEmeraldTierEnchantability;

    public static int anthektiteDiamondTierHarvestLevel;
    public static int anthektiteDiamondTierDurability;
    public static float anthektiteDiamondTierEfficiency;
    public static float anthektiteDiamondTierDamage;
    public static int anthektiteDiamondTierEnchantability;

    //AdvancedNetherite Tools Diarkrite
    public static int diarkriteIronSwordDamage;
    public static float diarkriteIronSwordAttackSpeed;
    public static float diarkriteIronShovelDamage;
    public static float diarkriteIronShovelAttackSpeed;
    public static int diarkriteIronPickaxeDamage;
    public static float diarkriteIronPickaxeAttackSpeed;
    public static float diarkriteIronAxeDamage;
    public static float diarkriteIronAxeAttackSpeed;
    public static int diarkriteIronHoeDamage;
    public static float diarkriteIronHoeAttackSpeed;

    public static int diarkriteGoldSwordDamage;
    public static float diarkriteGoldSwordAttackSpeed;
    public static float diarkriteGoldShovelDamage;
    public static float diarkriteGoldShovelAttackSpeed;
    public static int diarkriteGoldPickaxeDamage;
    public static float diarkriteGoldPickaxeAttackSpeed;
    public static float diarkriteGoldAxeDamage;
    public static float diarkriteGoldAxeAttackSpeed;
    public static int diarkriteGoldHoeDamage;
    public static float diarkriteGoldHoeAttackSpeed;

    public static int diarkriteEmeraldSwordDamage;
    public static float diarkriteEmeraldSwordAttackSpeed;
    public static float diarkriteEmeraldShovelDamage;
    public static float diarkriteEmeraldShovelAttackSpeed;
    public static int diarkriteEmeraldPickaxeDamage;
    public static float diarkriteEmeraldPickaxeAttackSpeed;
    public static float diarkriteEmeraldAxeDamage;
    public static float diarkriteEmeraldAxeAttackSpeed;
    public static int diarkriteEmeraldHoeDamage;
    public static float diarkriteEmeraldHoeAttackSpeed;

    public static int diarkriteDiamondSwordDamage;
    public static float diarkriteDiamondSwordAttackSpeed;
    public static float diarkriteDiamondShovelDamage;
    public static float diarkriteDiamondShovelAttackSpeed;
    public static int diarkriteDiamondPickaxeDamage;
    public static float diarkriteDiamondPickaxeAttackSpeed;
    public static float diarkriteDiamondAxeDamage;
    public static float diarkriteDiamondAxeAttackSpeed;
    public static int diarkriteDiamondHoeDamage;
    public static float diarkriteDiamondHoeAttackSpeed;

    //AdvancedNetherite Tools Anthektite
    public static int anthektiteIronSwordDamage;
    public static float anthektiteIronSwordAttackSpeed;
    public static float anthektiteIronShovelDamage;
    public static float anthektiteIronShovelAttackSpeed;
    public static int anthektiteIronPickaxeDamage;
    public static float anthektiteIronPickaxeAttackSpeed;
    public static float anthektiteIronAxeDamage;
    public static float anthektiteIronAxeAttackSpeed;
    public static int anthektiteIronHoeDamage;
    public static float anthektiteIronHoeAttackSpeed;

    public static int anthektiteGoldSwordDamage;
    public static float anthektiteGoldSwordAttackSpeed;
    public static float anthektiteGoldShovelDamage;
    public static float anthektiteGoldShovelAttackSpeed;
    public static int anthektiteGoldPickaxeDamage;
    public static float anthektiteGoldPickaxeAttackSpeed;
    public static float anthektiteGoldAxeDamage;
    public static float anthektiteGoldAxeAttackSpeed;
    public static int anthektiteGoldHoeDamage;
    public static float anthektiteGoldHoeAttackSpeed;

    public static int anthektiteEmeraldSwordDamage;
    public static float anthektiteEmeraldSwordAttackSpeed;
    public static float anthektiteEmeraldShovelDamage;
    public static float anthektiteEmeraldShovelAttackSpeed;
    public static int anthektiteEmeraldPickaxeDamage;
    public static float anthektiteEmeraldPickaxeAttackSpeed;
    public static float anthektiteEmeraldAxeDamage;
    public static float anthektiteEmeraldAxeAttackSpeed;
    public static int anthektiteEmeraldHoeDamage;
    public static float anthektiteEmeraldHoeAttackSpeed;

    public static int anthektiteDiamondSwordDamage;
    public static float anthektiteDiamondSwordAttackSpeed;
    public static float anthektiteDiamondShovelDamage;
    public static float anthektiteDiamondShovelAttackSpeed;
    public static int anthektiteDiamondPickaxeDamage;
    public static float anthektiteDiamondPickaxeAttackSpeed;
    public static float anthektiteDiamondAxeDamage;
    public static float anthektiteDiamondAxeAttackSpeed;
    public static int anthektiteDiamondHoeDamage;
    public static float anthektiteDiamondHoeAttackSpeed;

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


    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Configuration has been loaded");
    }

    private void setDefaults() {
        //AdvancedNetherite Tiers Diarkrite
        diarkriteIronTierHarvestLevel = 4;
        diarkriteIronTierDurability = 2608;
        diarkriteIronTierEfficiency = 12.0F;
        diarkriteIronTierDamage = 6.0F;
        diarkriteIronTierEnchantability = 15;

        diarkriteGoldTierHarvestLevel = 4;
        diarkriteGoldTierDurability = 2899;
        diarkriteGoldTierEfficiency = 20.0F;
        diarkriteGoldTierDamage = 6.0F;
        diarkriteGoldTierEnchantability = 25;

        diarkriteEmeraldTierHarvestLevel = 4;
        diarkriteEmeraldTierDurability = 3323;
        diarkriteEmeraldTierEfficiency = 29.0F;
        diarkriteEmeraldTierDamage = 7.0F;
        diarkriteEmeraldTierEnchantability = 20;

        diarkriteDiamondTierHarvestLevel = 4;
        diarkriteDiamondTierDurability = 3879;
        diarkriteDiamondTierEfficiency = 39.0F;
        diarkriteDiamondTierDamage = 7.0F;
        diarkriteDiamondTierEnchantability = 15;

        //AdvancedNetherite Tiers Anthektite
        anthektiteIronTierHarvestLevel = 4;
        anthektiteIronTierDurability = 1946;
        anthektiteIronTierEfficiency = 15.0F;
        anthektiteIronTierDamage = 3.0F;
        anthektiteIronTierEnchantability = 15;

        anthektiteGoldTierHarvestLevel = 4;
        anthektiteGoldTierDurability = 1946;
        anthektiteGoldTierEfficiency = 23.0F;
        anthektiteGoldTierDamage = 3.0F;
        anthektiteGoldTierEnchantability = 15;

        anthektiteEmeraldTierHarvestLevel = 4;
        anthektiteEmeraldTierDurability = 1946;
        anthektiteEmeraldTierEfficiency = 32.0F;
        anthektiteEmeraldTierDamage = 4.0F;
        anthektiteEmeraldTierEnchantability = 15;

        anthektiteDiamondTierHarvestLevel = 4;
        anthektiteDiamondTierDurability = 1946;
        anthektiteDiamondTierEfficiency = 42.0F;
        anthektiteDiamondTierDamage = 4.0F;
        anthektiteDiamondTierEnchantability = 15;

        //AdvancedNetherite Tools Diarkrite
        diarkriteIronSwordDamage = 3;
        diarkriteIronSwordAttackSpeed = -2.4F;
        diarkriteIronShovelDamage = 1.5F;
        diarkriteIronShovelAttackSpeed = -3.0F;
        diarkriteIronPickaxeDamage = 1;
        diarkriteIronPickaxeAttackSpeed = -2.8F;
        diarkriteIronAxeDamage = 6F;
        diarkriteIronAxeAttackSpeed = -3.1F;
        diarkriteIronHoeDamage = -3;
        diarkriteIronHoeAttackSpeed = -3.0F;

        diarkriteGoldSwordDamage = 4;
        diarkriteGoldSwordAttackSpeed = -2.4F;
        diarkriteGoldShovelDamage = 1.5F;
        diarkriteGoldShovelAttackSpeed = -3.0F;
        diarkriteGoldPickaxeDamage = 1;
        diarkriteGoldPickaxeAttackSpeed = -2.8F;
        diarkriteGoldAxeDamage = 7F;
        diarkriteGoldAxeAttackSpeed = -3.1F;
        diarkriteGoldHoeDamage = -3;
        diarkriteGoldHoeAttackSpeed = -3.0F;

        diarkriteEmeraldSwordDamage = 4;
        diarkriteEmeraldSwordAttackSpeed = -2.4F;
        diarkriteEmeraldShovelDamage = 1.5F;
        diarkriteEmeraldShovelAttackSpeed = -3.0F;
        diarkriteEmeraldPickaxeDamage = 1;
        diarkriteEmeraldPickaxeAttackSpeed = -2.8F;
        diarkriteEmeraldAxeDamage = 7F;
        diarkriteEmeraldAxeAttackSpeed = -3.1F;
        diarkriteEmeraldHoeDamage = -4;
        diarkriteEmeraldHoeAttackSpeed = -3.0F;

        diarkriteDiamondSwordDamage = 5;
        diarkriteDiamondSwordAttackSpeed = -2.4F;
        diarkriteDiamondShovelDamage = 1.5F;
        diarkriteDiamondShovelAttackSpeed = -3.0F;
        diarkriteDiamondPickaxeDamage = 1;
        diarkriteDiamondPickaxeAttackSpeed = -2.8F;
        diarkriteDiamondAxeDamage = 8F;
        diarkriteDiamondAxeAttackSpeed = -3.1F;
        diarkriteDiamondHoeDamage = -4;
        diarkriteDiamondHoeAttackSpeed = -3.0F;

        //AdvancedNetherite Tools Anthektite
        anthektiteIronSwordDamage = 3;
        anthektiteIronSwordAttackSpeed = -2.4F;
        anthektiteIronShovelDamage = 1.5F;
        anthektiteIronShovelAttackSpeed = -3.0F;
        anthektiteIronPickaxeDamage = 1;
        anthektiteIronPickaxeAttackSpeed = -2.8F;
        anthektiteIronAxeDamage = 6F;
        anthektiteIronAxeAttackSpeed = -3.1F;
        anthektiteIronHoeDamage = 1;
        anthektiteIronHoeAttackSpeed = -3.0F;

        anthektiteGoldSwordDamage = 4;
        anthektiteGoldSwordAttackSpeed = -2.4F;
        anthektiteGoldShovelDamage = 1.5F;
        anthektiteGoldShovelAttackSpeed = -3.0F;
        anthektiteGoldPickaxeDamage = 1;
        anthektiteGoldPickaxeAttackSpeed = -2.8F;
        anthektiteGoldAxeDamage = 7F;
        anthektiteGoldAxeAttackSpeed = -3.1F;
        anthektiteGoldHoeDamage = 1;
        anthektiteGoldHoeAttackSpeed = -3.0F;

        anthektiteEmeraldSwordDamage = 4;
        anthektiteEmeraldSwordAttackSpeed = -2.4F;
        anthektiteEmeraldShovelDamage = 1.5F;
        anthektiteEmeraldShovelAttackSpeed = -3.0F;
        anthektiteEmeraldPickaxeDamage = 1;
        anthektiteEmeraldPickaxeAttackSpeed = -2.8F;
        anthektiteEmeraldAxeDamage = 7F;
        anthektiteEmeraldAxeAttackSpeed = -3.1F;
        anthektiteEmeraldHoeDamage = 1;
        anthektiteEmeraldHoeAttackSpeed = -3.0F;

        anthektiteDiamondSwordDamage = 5;
        anthektiteDiamondSwordAttackSpeed = -2.4F;
        anthektiteDiamondShovelDamage = 1.5F;
        anthektiteDiamondShovelAttackSpeed = -3.0F;
        anthektiteDiamondPickaxeDamage = 1;
        anthektiteDiamondPickaxeAttackSpeed = -2.8F;
        anthektiteDiamondAxeDamage = 8F;
        anthektiteDiamondAxeAttackSpeed = -3.1F;
        anthektiteDiamondHoeDamage = 1;
        anthektiteDiamondHoeAttackSpeed = -3.0F;

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


//        steel_golem_max_health = 150;
//        steel_golem_attack_damage = 15;
//        steel_golem_use_ingot_heal = 37.5F;
//        steel_golem_movement_speed = 0.25;
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(Elementus.CONFIG_PATH_ADVANCED_NETHERITE)) {
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

                String errorPrefix = Elementus.CONFIG_PATH_ADVANCED_NETHERITE + ": line " + lineNumber + ": ";
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
                        case "diarkriteIronTier_HarvestLevel": diarkriteIronTierHarvestLevel = (int) value; break;
                        case "diarkriteIronTier_Durability": diarkriteIronTierDurability = (int) value; break;
                        case "diarkriteIronTier_Efficiency": diarkriteIronTierEfficiency = (float) value; break;
                        case "diarkriteIronTier_Damage": diarkriteIronTierDamage = (float) value; break;
                        case "diarkriteIronTier_Enchantability": diarkriteIronTierEnchantability = (int) value; break;

                        case "diarkriteGoldTier_HarvestLevel": diarkriteGoldTierHarvestLevel = (int) value; break;
                        case "diarkriteGoldTier_Durability": diarkriteGoldTierDurability = (int) value; break;
                        case "diarkriteGoldTier_Efficiency": diarkriteGoldTierEfficiency = (float) value; break;
                        case "diarkriteGoldTier_Damage": diarkriteGoldTierDamage = (float) value; break;
                        case "diarkriteGoldTier_Enchantability": diarkriteGoldTierEnchantability = (int) value; break;

                        case "diarkriteEmeraldTier_HarvestLevel": diarkriteEmeraldTierHarvestLevel = (int) value; break;
                        case "diarkriteEmeraldTier_Durability": diarkriteEmeraldTierDurability = (int) value; break;
                        case "diarkriteEmeraldTier_Efficiency": diarkriteEmeraldTierEfficiency = (float) value; break;
                        case "diarkriteEmeraldTier_Damage": diarkriteEmeraldTierDamage = (float) value; break;
                        case "diarkriteEmeraldTier_Enchantability": diarkriteEmeraldTierEnchantability = (int) value; break;

                        case "diarkriteDiamondTier_HarvestLevel": diarkriteDiamondTierHarvestLevel = (int) value; break;
                        case "diarkriteDiamondTier_Durability": diarkriteDiamondTierDurability = (int) value; break;
                        case "diarkriteDiamondTier_Efficiency": diarkriteDiamondTierEfficiency = (float) value; break;
                        case "diarkriteDiamondTier_Damage": diarkriteDiamondTierDamage = (float) value; break;
                        case "diarkriteDiamondTier_Enchantability": diarkriteDiamondTierEnchantability = (int) value; break;

                        case "anthektiteIronTier_HarvestLevel": anthektiteIronTierHarvestLevel = (int) value; break;
                        case "anthektiteIronTier_Durability": anthektiteIronTierDurability = (int) value; break;
                        case "anthektiteIronTier_Efficiency": anthektiteIronTierEfficiency = (float) value; break;
                        case "anthektiteIronTier_Damage": anthektiteIronTierDamage = (float) value; break;
                        case "anthektiteIronTier_Enchantability": anthektiteIronTierEnchantability = (int) value; break;

                        case "anthektiteGoldTier_HarvestLevel": anthektiteGoldTierHarvestLevel = (int) value; break;
                        case "anthektiteGoldTier_Durability": anthektiteGoldTierDurability = (int) value; break;
                        case "anthektiteGoldTier_Efficiency": anthektiteGoldTierEfficiency = (float) value; break;
                        case "anthektiteGoldTier_Damage": anthektiteGoldTierDamage = (float) value; break;
                        case "anthektiteGoldTier_Enchantability": anthektiteGoldTierEnchantability = (int) value; break;

                        case "anthektiteEmeraldTier_HarvestLevel": anthektiteEmeraldTierHarvestLevel = (int) value; break;
                        case "anthektiteEmeraldTier_Durability": anthektiteEmeraldTierDurability = (int) value; break;
                        case "anthektiteEmeraldTier_Efficiency": anthektiteEmeraldTierEfficiency = (float) value; break;
                        case "anthektiteEmeraldTier_Damage": anthektiteEmeraldTierDamage = (float) value; break;
                        case "anthektiteEmeraldTier_Enchantability": anthektiteEmeraldTierEnchantability = (int) value; break;

                        case "anthektiteDiamondTier_HarvestLevel": anthektiteDiamondTierHarvestLevel = (int) value; break;
                        case "anthektiteDiamondTier_Durability": anthektiteDiamondTierDurability = (int) value; break;
                        case "anthektiteDiamondTier_Efficiency": anthektiteDiamondTierEfficiency = (float) value; break;
                        case "anthektiteDiamondTier_Damage": anthektiteDiamondTierDamage = (float) value; break;
                        case "anthektiteDiamondTier_Enchantability": anthektiteDiamondTierEnchantability = (int) value; break;


                        case "diarkriteIronSword_Damage": diarkriteIronSwordDamage = (int) value; break;
                        case "diarkriteIronSword_Speed": diarkriteIronSwordAttackSpeed = (float) value; break;
                        case "diarkriteIronShovel_Damage": diarkriteIronShovelDamage = (float) value; break;
                        case "diarkriteIronShovel_Speed": diarkriteIronShovelAttackSpeed = (float) value; break;
                        case "diarkriteIronPickaxe_Damage": diarkriteIronPickaxeDamage = (int) value; break;
                        case "diarkriteIronPickaxe_Speed": diarkriteIronPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteIronAxe_Damage": diarkriteIronAxeDamage = (float) value; break;
                        case "diarkriteIronAxe_Speed": diarkriteIronAxeAttackSpeed = (float) value; break;
                        case "diarkriteIronHoe_Damage": diarkriteIronHoeDamage = (int) value; break;
                        case "diarkriteIronHoe_Speed": diarkriteIronHoeAttackSpeed = (float) value; break;

                        case "diarkriteGoldSword_Damage": diarkriteGoldSwordDamage = (int) value; break;
                        case "diarkriteGoldSword_Speed": diarkriteGoldSwordAttackSpeed = (float) value; break;
                        case "diarkriteGoldShovel_Damage": diarkriteGoldShovelDamage = (float) value; break;
                        case "diarkriteGoldShovel_Speed": diarkriteGoldShovelAttackSpeed = (float) value; break;
                        case "diarkriteGoldPickaxe_Damage": diarkriteGoldPickaxeDamage = (int) value; break;
                        case "diarkriteGoldPickaxe_Speed": diarkriteGoldPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteGoldAxe_Damage": diarkriteGoldAxeDamage = (float) value; break;
                        case "diarkriteGoldAxe_Speed": diarkriteGoldAxeAttackSpeed = (float) value; break;
                        case "diarkriteGoldHoe_Damage": diarkriteGoldHoeDamage = (int) value; break;
                        case "diarkriteGoldHoe_Speed": diarkriteGoldHoeAttackSpeed = (float) value; break;

                        case "diarkriteEmeraldSword_Damage": diarkriteEmeraldSwordDamage = (int) value; break;
                        case "diarkriteEmeraldSword_Speed": diarkriteEmeraldSwordAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldShovel_Damage": diarkriteEmeraldShovelDamage = (float) value; break;
                        case "diarkriteEmeraldShovel_Speed": diarkriteEmeraldShovelAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldPickaxe_Damage": diarkriteEmeraldPickaxeDamage = (int) value; break;
                        case "diarkriteEmeraldPickaxe_Speed": diarkriteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldAxe_Damage": diarkriteEmeraldAxeDamage = (float) value; break;
                        case "diarkriteEmeraldAxe_Speed": diarkriteEmeraldAxeAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldHoe_Damage": diarkriteEmeraldHoeDamage = (int) value; break;
                        case "diarkriteEmeraldHoe_Speed": diarkriteEmeraldHoeAttackSpeed = (float) value; break;

                        case "diarkriteDiamondSword_Damage": diarkriteDiamondSwordDamage = (int) value; break;
                        case "diarkriteDiamondSword_Speed": diarkriteDiamondSwordAttackSpeed = (float) value; break;
                        case "diarkriteDiamondShovel_Damage": diarkriteDiamondShovelDamage = (float) value; break;
                        case "diarkriteDiamondShovel_Speed": diarkriteDiamondShovelAttackSpeed = (float) value; break;
                        case "diarkriteDiamondPickaxe_Damage": diarkriteDiamondPickaxeDamage = (int) value; break;
                        case "diarkriteDiamondPickaxe_Speed": diarkriteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteDiamondAxe_Damage": diarkriteDiamondAxeDamage = (float) value; break;
                        case "diarkriteDiamondAxe_Speed": diarkriteDiamondAxeAttackSpeed = (float) value; break;
                        case "diarkriteDiamondHoe_Damage": diarkriteDiamondHoeDamage = (int) value; break;
                        case "diarkriteDiamondHoe_Speed": diarkriteDiamondHoeAttackSpeed = (float) value; break;


                        case "anthektiteIronSword_Damage": anthektiteIronSwordDamage = (int) value; break;
                        case "anthektiteIronSword_Speed": anthektiteIronSwordAttackSpeed = (float) value; break;
                        case "anthektiteIronShovel_Damage": anthektiteIronShovelDamage = (float) value; break;
                        case "anthektiteIronShovel_Speed": anthektiteIronShovelAttackSpeed = (float) value; break;
                        case "anthektiteIronPickaxe_Damage": anthektiteIronPickaxeDamage = (int) value; break;
                        case "anthektiteIronPickaxe_Speed": anthektiteIronPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteIronAxe_Damage": anthektiteIronAxeDamage = (float) value; break;
                        case "anthektiteIronAxe_Speed": anthektiteIronAxeAttackSpeed = (float) value; break;
                        case "anthektiteIronHoe_Damage": anthektiteIronHoeDamage = (int) value; break;
                        case "anthektiteIronHoe_Speed": anthektiteIronHoeAttackSpeed = (float) value; break;

                        case "anthektiteGoldSword_Damage": anthektiteGoldSwordDamage = (int) value; break;
                        case "anthektiteGoldSword_Speed": anthektiteGoldSwordAttackSpeed = (float) value; break;
                        case "anthektiteGoldShovel_Damage": anthektiteGoldShovelDamage = (float) value; break;
                        case "anthektiteGoldShovel_Speed": anthektiteGoldShovelAttackSpeed = (float) value; break;
                        case "anthektiteGoldPickaxe_Damage": anthektiteGoldPickaxeDamage = (int) value; break;
                        case "anthektiteGoldPickaxe_Speed": anthektiteGoldPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteGoldAxe_Damage": anthektiteGoldAxeDamage = (float) value; break;
                        case "anthektiteGoldAxe_Speed": anthektiteGoldAxeAttackSpeed = (float) value; break;
                        case "anthektiteGoldHoe_Damage": anthektiteGoldHoeDamage = (int) value; break;
                        case "anthektiteGoldHoe_Speed": anthektiteGoldHoeAttackSpeed = (float) value; break;

                        case "anthektiteEmeraldSword_Damage": anthektiteEmeraldSwordDamage = (int) value; break;
                        case "anthektiteEmeraldSword_Speed": anthektiteEmeraldSwordAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldShovel_Damage": anthektiteEmeraldShovelDamage = (float) value; break;
                        case "anthektiteEmeraldShovel_Speed": anthektiteEmeraldShovelAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldPickaxe_Damage": anthektiteEmeraldPickaxeDamage = (int) value; break;
                        case "anthektiteEmeraldPickaxe_Speed": anthektiteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldAxe_Damage": anthektiteEmeraldAxeDamage = (float) value; break;
                        case "anthektiteEmeraldAxe_Speed": anthektiteEmeraldAxeAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldHoe_Damage": anthektiteEmeraldHoeDamage = (int) value; break;
                        case "anthektiteEmeraldHoe_Speed": anthektiteEmeraldHoeAttackSpeed = (float) value; break;

                        case "anthektiteDiamondSword_Damage": anthektiteDiamondSwordDamage = (int) value; break;
                        case "anthektiteDiamondSword_Speed": anthektiteDiamondSwordAttackSpeed = (float) value; break;
                        case "anthektiteDiamondShovel_Damage": anthektiteDiamondShovelDamage = (float) value; break;
                        case "anthektiteDiamondShovel_Speed": anthektiteDiamondShovelAttackSpeed = (float) value; break;
                        case "anthektiteDiamondPickaxe_Damage": anthektiteDiamondPickaxeDamage = (int) value; break;
                        case "anthektiteDiamondPickaxe_Speed": anthektiteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteDiamondAxe_Damage": anthektiteDiamondAxeDamage = (float) value; break;
                        case "anthektiteDiamondAxe_Speed": anthektiteDiamondAxeAttackSpeed = (float) value; break;
                        case "anthektiteDiamondHoe_Damage": anthektiteDiamondHoeDamage = (int) value; break;
                        case "anthektiteDiamondHoe_Speed": anthektiteDiamondHoeAttackSpeed = (float) value; break;


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
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.CONFIG_PATH_ADVANCED_NETHERITE)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config");
            writer.newLine();
            writer.newLine();
            writer.write("[AdvancedNetherite]\n");
            writer.write("  [ToolTiers]\n");
            writer.write("    [Diarkrite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default Values: 4, 2608, 6.0, 12.0, 15\n");
            writer.write("        diarkriteIronTier_HarvestLevel = " + diarkriteIronTierHarvestLevel + "\n");
            writer.write("        diarkriteIronTier_Durability = " + diarkriteIronTierDurability + "\n");
            writer.write("        diarkriteIronTier_Damage = " + diarkriteIronTierDamage + "\n");
            writer.write("        diarkriteIronTier_Efficiency = " + diarkriteIronTierEfficiency + "\n");
            writer.write("        diarkriteIronTier_Enchantability = " + diarkriteIronTierEnchantability + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default Values: 4, 2899, 6.0, 20.0, 25\n");
            writer.write("        diarkriteGoldTier_HarvestLevel = " + diarkriteGoldTierHarvestLevel + "\n");
            writer.write("        diarkriteGoldTier_Durability = " + diarkriteGoldTierDurability + "\n");
            writer.write("        diarkriteGoldTier_Damage = " + diarkriteGoldTierDamage + "\n");
            writer.write("        diarkriteGoldTier_Efficiency = " + diarkriteGoldTierEfficiency + "\n");
            writer.write("        diarkriteGoldTier_Enchantability = " + diarkriteGoldTierEnchantability + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default Values: 4, 3323, 7.0, 29.0, 20\n");
            writer.write("        diarkriteEmeraldTier_HarvestLevel = " + diarkriteEmeraldTierHarvestLevel + "\n");
            writer.write("        diarkriteEmeraldTier-Durability = " + diarkriteEmeraldTierDurability + "\n");
            writer.write("        diarkriteEmeraldTier_Damage = " + diarkriteEmeraldTierDamage + "\n");
            writer.write("        diarkriteEmeraldTier_Efficiency = " + diarkriteEmeraldTierEfficiency + "\n");
            writer.write("        diarkriteEmeraldTier_Enchantability = " + diarkriteEmeraldTierEnchantability + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default Values: 4, 3876, 7.0, 39.0, 15\n");
            writer.write("        diarkriteDiamondTier_HarvestLevel = " + diarkriteDiamondTierHarvestLevel + "\n");
            writer.write("        diarkriteDiamondTier_Durability = " + diarkriteDiamondTierDurability + "\n");
            writer.write("        diarkriteDiamondTier_Damage = " + diarkriteDiamondTierDamage + "\n");
            writer.write("        diarkriteDiamondTier_Efficiency = " + diarkriteDiamondTierEfficiency + "\n");
            writer.write("        diarkriteDiamondTier_Enchantability = " + diarkriteDiamondTierEnchantability + "\n");
            writer.newLine();
            writer.write("    [Anthektite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default Values: 4, 1994, 3.0, 15.0, 15\n");
            writer.write("        anthektiteIronTier_HarvestLevel = " + anthektiteIronTierHarvestLevel + "\n");
            writer.write("        anthektiteIronTier_Durability = " + anthektiteIronTierDurability + "\n");
            writer.write("        anthektiteIronTier_Damage = " + anthektiteIronTierDamage + "\n");
            writer.write("        anthektiteIronTier_Efficiency = " + anthektiteIronTierEfficiency + "\n");
            writer.write("        anthektiteIronTier_Enchantability = " + anthektiteIronTierEnchantability + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default Values: 4, 2216, 3.0, 23.0, 25\n");
            writer.write("        anthektiteGoldTier_HarvestLevel = " + anthektiteGoldTierHarvestLevel + "\n");
            writer.write("        anthektiteGoldTier_Durability = " + anthektiteGoldTierDurability + "\n");
            writer.write("        anthektiteGoldTier_Damage = " + anthektiteGoldTierDamage + "\n");
            writer.write("        anthektiteGoldTier_Efficiency = " + anthektiteGoldTierEfficiency + "\n");
            writer.write("        anthektiteGoldTier_Enchantability = " + anthektiteGoldTierEnchantability + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default Values: 4, 2540, 4.0, 32.0, 20\n");
            writer.write("        anthektiteEmeraldTier_HarvestLevel = " + anthektiteEmeraldTierHarvestLevel + "\n");
            writer.write("        anthektiteEmeraldTier_Durability = " + anthektiteEmeraldTierDurability + "\n");
            writer.write("        anthektiteEmeraldTier_Damage = " + anthektiteEmeraldTierDamage + "\n");
            writer.write("        anthektiteEmeraldTier_Efficiency = " + anthektiteEmeraldTierEfficiency + "\n");
            writer.write("        anthektiteEmeraldTier_Enchantability = " + anthektiteEmeraldTierEnchantability + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default Values: 4, 2962, 4.0, 42.0, 15\n");
            writer.write("        anthektiteDiamondTier_HarvestLevel = " + anthektiteDiamondTierHarvestLevel + "\n");
            writer.write("        anthektiteDiamondTier_Durability = " + anthektiteDiamondTierDurability + "\n");
            writer.write("        anthektiteDiamondTier_Damage = " + anthektiteDiamondTierDamage + "\n");
            writer.write("        anthektiteDiamondTier_Efficiency = " + anthektiteDiamondTierEfficiency + "\n");
            writer.write("        anthektiteDiamondTier_Enchantability = " + anthektiteDiamondTierEnchantability + "\n");
            writer.newLine();
            writer.write("  [ToolStats]\n");
            writer.write("    [Diarkrite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteIronSword_Damage = " + diarkriteIronSwordDamage + "\n");
            writer.write("        diarkriteIronSword_Speed = " + diarkriteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteIronShovel_Damage = " + diarkriteIronShovelDamage + "\n");
            writer.write("        diarkriteIronShovel_Speed = " + diarkriteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteIronPickaxe_Damage = " + diarkriteIronPickaxeDamage + "\n");
            writer.write("        diarkriteIronPickaxe_Speed = " + diarkriteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 6), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteIronAxe_Damage = " + diarkriteIronAxeDamage + "\n");
            writer.write("        diarkriteIronAxe_Speed = " + diarkriteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteIronHoe_Damage = " + diarkriteIronHoeDamage + "\n");
            writer.write("        diarkriteIronHoe_Speed = " + diarkriteIronHoeAttackSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteGoldSword_Damage = " + diarkriteGoldSwordDamage + "\n");
            writer.write("        diarkriteGoldSword_Speed = " + diarkriteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteGoldShovel_Damage = " + diarkriteGoldShovelDamage + "\n");
            writer.write("        diarkriteGoldShovel_Speed = " + diarkriteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteGoldPickaxe_Damage = " + diarkriteGoldPickaxeDamage + "\n");
            writer.write("        diarkriteGoldPickaxe_Speed = " + diarkriteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteGoldAxe_Damage = " + diarkriteGoldAxeDamage + "\n");
            writer.write("        diarkriteGoldAxe_Speed = " + diarkriteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteGoldHoe_Damage = " + diarkriteGoldHoeDamage + "\n");
            writer.write("        diarkriteGoldHoe_Speed = " + diarkriteGoldHoeAttackSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteEmeraldSword_Damage = " + diarkriteEmeraldSwordDamage + "\n");
            writer.write("        diarkriteEmeraldSword_Speed = " + diarkriteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteEmeraldShovel_Damage = " + diarkriteEmeraldShovelDamage + "\n");
            writer.write("        diarkriteEmeraldShovel_Speed = " + diarkriteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteEmeraldPickaxe_Damage = " + diarkriteEmeraldPickaxeDamage + "\n");
            writer.write("        diarkriteEmeraldPickaxe_Speed = " + diarkriteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteEmeraldAxe_Damage = " + diarkriteEmeraldAxeDamage + "\n");
            writer.write("        diarkriteEmeraldAxe_Speed = " + diarkriteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteEmeraldHoe_Damage = " + diarkriteEmeraldHoeDamage + "\n");
            writer.write("        diarkriteEmeraldHoe_Speed = " + diarkriteEmeraldHoeAttackSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteDiamondSword_Damage = " + diarkriteDiamondSwordDamage + "\n");
            writer.write("        diarkriteDiamondSword_Speed = " + diarkriteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteDiamondShovel_Damage = " + diarkriteDiamondShovelDamage + "\n");
            writer.write("        diarkriteDiamondShovel_Speed = " + diarkriteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteDiamondPickaxe_Damage = " + diarkriteDiamondPickaxeDamage + "\n");
            writer.write("        diarkriteDiamondPickaxe_Speed = " + diarkriteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 8), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteDiamondAxe_Damage = " + diarkriteDiamondAxeDamage + "\n");
            writer.write("        diarkriteDiamondAxe_Speed = " + diarkriteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteDiamondHoe_Damage = " + diarkriteDiamondHoeDamage + "\n");
            writer.write("        diarkriteDiamondHoe_Speed = " + diarkriteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    [Anthektite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteIronSword_Damage = " + anthektiteIronSwordDamage + "\n");
            writer.write("        anthektiteIronSword_Speed = " + anthektiteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteIronShovel_Damage = " + anthektiteIronShovelDamage + "\n");
            writer.write("        anthektiteIronShovel_Speed = " + anthektiteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteIronPickaxe_Damage = " + anthektiteIronPickaxeDamage + "\n");
            writer.write("        anthektiteIronPickaxe_Speed = " + anthektiteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteIronAxe_Damage = " + anthektiteIronAxeDamage + "\n");
            writer.write("        anthektiteIronAxe_Speed = " + anthektiteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteIronHoe_Damage = " + anthektiteIronHoeDamage + "\n");
            writer.write("        anthektiteIronHoe_Speed = " + anthektiteIronHoeAttackSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteGoldSword_Damage = " + anthektiteGoldSwordDamage + "\n");
            writer.write("        anthektiteGoldSword_Speed = " + anthektiteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteGoldShovel_Damage = " + anthektiteGoldShovelDamage + "\n");
            writer.write("        anthektiteGoldShovel_Speed = " + anthektiteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteGoldPickaxe_Damage = " + anthektiteGoldPickaxeDamage + "\n");
            writer.write("        anthektiteGoldPickaxe_Speed = " + anthektiteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteGoldAxe_Damage = " + anthektiteGoldAxeDamage + "\n");
            writer.write("        anthektiteGoldAxe_Speed = " + anthektiteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteGoldHoe_Damage = " + anthektiteGoldHoeDamage + "\n");
            writer.write("        anthektiteGoldHoe_Speed = " + anthektiteGoldHoeAttackSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteEmeraldSword_Damage = " + anthektiteEmeraldSwordDamage + "\n");
            writer.write("        anthektiteEmeraldSword_Speed = " + anthektiteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteEmeraldShovel_Damage = " + anthektiteEmeraldShovelDamage + "\n");
            writer.write("        anthektiteEmeraldShovel_Speed = " + anthektiteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteEmeraldPickaxe_Damage = " + anthektiteEmeraldPickaxeDamage + "\n");
            writer.write("        anthektiteEmeraldPickaxe_Speed = " + anthektiteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteEmeraldAxe_Damage = " + anthektiteEmeraldAxeDamage + "\n");
            writer.write("        anthektiteEmeraldAxe_Speed = " + anthektiteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteEmeraldHoe_Damage = " + anthektiteEmeraldHoeDamage + "\n");
            writer.write("        anthektiteEmeraldHoe_Speed = " + anthektiteEmeraldHoeAttackSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteDiamondSword_Damage = " + anthektiteDiamondSwordDamage + "\n");
            writer.write("        anthektiteDiamondSword_Speed = " + anthektiteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteDiamondShovel_Damage = " + anthektiteDiamondShovelDamage + "\n");
            writer.write("        anthektiteDiamondShovel_Speed = " + anthektiteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteDiamondPickaxe_Damage = " + anthektiteDiamondPickaxeDamage + "\n");
            writer.write("        anthektiteDiamondPickaxe_Speed = " + anthektiteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteDiamondAxe_Damage = " + anthektiteDiamondAxeDamage + "\n");
            writer.write("        anthektiteDiamondAxe_Speed = " + anthektiteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteDiamondHoe_Damage = " + anthektiteDiamondHoeDamage + "\n");
            writer.write("        anthektiteDiamondHoe_Speed = " + anthektiteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
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

        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
