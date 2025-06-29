package nokunami.elementus.common.config;

import nokunami.elementus.Elementus;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Scanner;

import static nokunami.elementus.Elementus.CONFIG_VERSION;

/**
 * credits: SkpC9 <a href="https://github.com/SkpC9/Simply-Steel/blob/main/src/main/java/com/trbz_/simplysteel/util/ConfigHandler.java">Link</a>
 * */
public class ANConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ANConfig INSTANCE = new ANConfig();
    private static final Path CONFIG_PATH = Elementus.AN_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
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

    //AdvancedNetherite Diarkrite
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

        logger.info("Advanced Netherite Config loaded");
    }

    private void setDefaults() {

        //AdvancedNetherite Tiers Diarkrite
        diarkriteIronTierHarvestLevel = 4;
        diarkriteIronTierDurability = 2608;
        diarkriteIronTierEfficiency = 10.0F;
        diarkriteIronTierDamage = 6.0F;
        diarkriteIronTierEnchantability = 15;

        diarkriteGoldTierHarvestLevel = 4;
        diarkriteGoldTierDurability = 2899;
        diarkriteGoldTierEfficiency = 18.0F;
        diarkriteGoldTierDamage = 6.0F;
        diarkriteGoldTierEnchantability = 25;

        diarkriteEmeraldTierHarvestLevel = 4;
        diarkriteEmeraldTierDurability = 3323;
        diarkriteEmeraldTierEfficiency = 27.0F;
        diarkriteEmeraldTierDamage = 7.0F;
        diarkriteEmeraldTierEnchantability = 20;

        diarkriteDiamondTierHarvestLevel = 4;
        diarkriteDiamondTierDurability = 3879;
        diarkriteDiamondTierEfficiency = 37.0F;
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
        anthektiteIronHoeDamage = 0;
        anthektiteIronHoeAttackSpeed = -3.0F;

        anthektiteGoldSwordDamage = 4;
        anthektiteGoldSwordAttackSpeed = -2.4F;
        anthektiteGoldShovelDamage = 1.5F;
        anthektiteGoldShovelAttackSpeed = -3.0F;
        anthektiteGoldPickaxeDamage = 1;
        anthektiteGoldPickaxeAttackSpeed = -2.8F;
        anthektiteGoldAxeDamage = 7F;
        anthektiteGoldAxeAttackSpeed = -3.1F;
        anthektiteGoldHoeDamage = 0;
        anthektiteGoldHoeAttackSpeed = -3.0F;

        anthektiteEmeraldSwordDamage = 4;
        anthektiteEmeraldSwordAttackSpeed = -2.4F;
        anthektiteEmeraldShovelDamage = 1.5F;
        anthektiteEmeraldShovelAttackSpeed = -3.0F;
        anthektiteEmeraldPickaxeDamage = 0;
        anthektiteEmeraldPickaxeAttackSpeed = -2.8F;
        anthektiteEmeraldAxeDamage = 7F;
        anthektiteEmeraldAxeAttackSpeed = -3.1F;
        anthektiteEmeraldHoeDamage = -1;
        anthektiteEmeraldHoeAttackSpeed = -3.0F;

        anthektiteDiamondSwordDamage = 5;
        anthektiteDiamondSwordAttackSpeed = -2.4F;
        anthektiteDiamondShovelDamage = 1.5F;
        anthektiteDiamondShovelAttackSpeed = -3.0F;
        anthektiteDiamondPickaxeDamage = 0;
        anthektiteDiamondPickaxeAttackSpeed = -2.8F;
        anthektiteDiamondAxeDamage = 8F;
        anthektiteDiamondAxeAttackSpeed = -3.1F;
        anthektiteDiamondHoeDamage = -1;
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
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(CONFIG_PATH)) {
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

                String errorPrefix = CONFIG_PATH + ": line " + lineNumber + ": ";
                try (Scanner s = new Scanner(line)) {
                    s.useLocale(Locale.US);
                    s.useDelimiter("\\s*=\\s*");

                    if (!s.hasNext()) {
                        logger.warn(errorPrefix + "missing parameter name");
                        continue;
                    }
                    String key = s.next().trim();
                    // use string version
                    if (key.equals("version")) {
                        if (!s.hasNext()) {
                            logger.warn(errorPrefix + "missing version number");
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
                        //AdvancedNetherite Tiers Anthektite
                        case "dIronTier.HarvestLevel": diarkriteIronTierHarvestLevel = (int) value; break;
                        case "dIronTier.Durability": diarkriteIronTierDurability = (int) value; break;
                        case "dIronTier.Efficiency": diarkriteIronTierEfficiency = (float) value; break;
                        case "dIronTier.Damage": diarkriteIronTierDamage = (float) value; break;
                        case "dIronTier.Enchantability": diarkriteIronTierEnchantability = (int) value; break;

                        case "dGoldTier.HarvestLevel": diarkriteGoldTierHarvestLevel = (int) value; break;
                        case "dGoldTier.Durability": diarkriteGoldTierDurability = (int) value; break;
                        case "dGoldTier.Efficiency": diarkriteGoldTierEfficiency = (float) value; break;
                        case "dGoldTier.Damage": diarkriteGoldTierDamage = (float) value; break;
                        case "dGoldTier.Enchantability": diarkriteGoldTierEnchantability = (int) value; break;

                        case "dEmeraldTier.HarvestLevel": diarkriteEmeraldTierHarvestLevel = (int) value; break;
                        case "dEmeraldTier.Durability": diarkriteEmeraldTierDurability = (int) value; break;
                        case "dEmeraldTier.Efficiency": diarkriteEmeraldTierEfficiency = (float) value; break;
                        case "dEmeraldTier.Damage": diarkriteEmeraldTierDamage = (float) value; break;
                        case "dEmeraldTier.Enchantability": diarkriteEmeraldTierEnchantability = (int) value; break;

                        case "dDiamondTier.HarvestLevel": diarkriteDiamondTierHarvestLevel = (int) value; break;
                        case "dDiamondTier.Durability": diarkriteDiamondTierDurability = (int) value; break;
                        case "dDiamondTier.Efficiency": diarkriteDiamondTierEfficiency = (float) value; break;
                        case "dDiamondTier.Damage": diarkriteDiamondTierDamage = (float) value; break;
                        case "dDiamondTier.Enchantability": diarkriteDiamondTierEnchantability = (int) value; break;

                        case "aIronTier.HarvestLevel": anthektiteIronTierHarvestLevel = (int) value; break;
                        case "aIronTier.Durability": anthektiteIronTierDurability = (int) value; break;
                        case "aIronTier.Efficiency": anthektiteIronTierEfficiency = (float) value; break;
                        case "aIronTier.Damage": anthektiteIronTierDamage = (float) value; break;
                        case "aIronTier.Enchantability": anthektiteIronTierEnchantability = (int) value; break;

                        case "aGoldTier.HarvestLevel": anthektiteGoldTierHarvestLevel = (int) value; break;
                        case "aGoldTier.Durability": anthektiteGoldTierDurability = (int) value; break;
                        case "aGoldTier.Efficiency": anthektiteGoldTierEfficiency = (float) value; break;
                        case "aGoldTier.Damage": anthektiteGoldTierDamage = (float) value; break;
                        case "aGoldTier.Enchantability": anthektiteGoldTierEnchantability = (int) value; break;

                        case "aEmeraldTier.HarvestLevel": anthektiteEmeraldTierHarvestLevel = (int) value; break;
                        case "aEmeraldTier.Durability": anthektiteEmeraldTierDurability = (int) value; break;
                        case "aEmeraldTier.Efficiency": anthektiteEmeraldTierEfficiency = (float) value; break;
                        case "aEmeraldTier.Damage": anthektiteEmeraldTierDamage = (float) value; break;
                        case "aEmeraldTier.Enchantability": anthektiteEmeraldTierEnchantability = (int) value; break;

                        case "aDiamondTier.HarvestLevel": anthektiteDiamondTierHarvestLevel = (int) value; break;
                        case "aDiamondTier.Durability": anthektiteDiamondTierDurability = (int) value; break;
                        case "aDiamondTier.Efficiency": anthektiteDiamondTierEfficiency = (float) value; break;
                        case "aDiamondTier.Damage": anthektiteDiamondTierDamage = (float) value; break;
                        case "aDiamondTier.Enchantability": anthektiteDiamondTierEnchantability = (int) value; break;


                        case "dIronSword.Damage": diarkriteIronSwordDamage = (int) value; break;
                        case "dIronSword.Speed": diarkriteIronSwordAttackSpeed = (float) value; break;
                        case "dIronShovel.Damage": diarkriteIronShovelDamage = (float) value; break;
                        case "dIronShovel.Speed": diarkriteIronShovelAttackSpeed = (float) value; break;
                        case "ddIronPickaxe.Damage": diarkriteIronPickaxeDamage = (int) value; break;
                        case "ddIronPickaxe.Speed": diarkriteIronPickaxeAttackSpeed = (float) value; break;
                        case "dIronAxe.Damage": diarkriteIronAxeDamage = (float) value; break;
                        case "dIronAxe.Speed": diarkriteIronAxeAttackSpeed = (float) value; break;
                        case "dIronHoe.Damage": diarkriteIronHoeDamage = (int) value; break;
                        case "dIronHoe.Speed": diarkriteIronHoeAttackSpeed = (float) value; break;

                        case "dGoldSword.Damage": diarkriteGoldSwordDamage = (int) value; break;
                        case "dGoldSword.Speed": diarkriteGoldSwordAttackSpeed = (float) value; break;
                        case "dGoldShovel.Damage": diarkriteGoldShovelDamage = (float) value; break;
                        case "dGoldShovel.Speed": diarkriteGoldShovelAttackSpeed = (float) value; break;
                        case "dGoldPickaxe.Damage": diarkriteGoldPickaxeDamage = (int) value; break;
                        case "dGoldPickaxe.Speed": diarkriteGoldPickaxeAttackSpeed = (float) value; break;
                        case "dGoldAxe.Damage": diarkriteGoldAxeDamage = (float) value; break;
                        case "dGoldAxe.Speed": diarkriteGoldAxeAttackSpeed = (float) value; break;
                        case "dGoldHoe.Damage": diarkriteGoldHoeDamage = (int) value; break;
                        case "dGoldHoe.Speed": diarkriteGoldHoeAttackSpeed = (float) value; break;

                        case "dEmeraldSword.Damage": diarkriteEmeraldSwordDamage = (int) value; break;
                        case "dEmeraldSword.Speed": diarkriteEmeraldSwordAttackSpeed = (float) value; break;
                        case "dEmeraldShovel.Damage": diarkriteEmeraldShovelDamage = (float) value; break;
                        case "dEmeraldShovel.Speed": diarkriteEmeraldShovelAttackSpeed = (float) value; break;
                        case "dEmeraldPickaxe.Damage": diarkriteEmeraldPickaxeDamage = (int) value; break;
                        case "dEmeraldPickaxe.Speed": diarkriteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "dEmeraldAxe.Damage": diarkriteEmeraldAxeDamage = (float) value; break;
                        case "dEmeraldAxe.Speed": diarkriteEmeraldAxeAttackSpeed = (float) value; break;
                        case "dEmeraldHoe.Damage": diarkriteEmeraldHoeDamage = (int) value; break;
                        case "dEmeraldHoe.Speed": diarkriteEmeraldHoeAttackSpeed = (float) value; break;

                        case "dDiamondSword.Damage": diarkriteDiamondSwordDamage = (int) value; break;
                        case "dDiamondSword.Speed": diarkriteDiamondSwordAttackSpeed = (float) value; break;
                        case "dDiamondShovel.Damage": diarkriteDiamondShovelDamage = (float) value; break;
                        case "dDiamondShovel.Speed": diarkriteDiamondShovelAttackSpeed = (float) value; break;
                        case "dDiamondPickaxe.Damage": diarkriteDiamondPickaxeDamage = (int) value; break;
                        case "dDiamondPickaxe.Speed": diarkriteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "dDiamondAxe.Damage": diarkriteDiamondAxeDamage = (float) value; break;
                        case "dDiamondAxe.Speed": diarkriteDiamondAxeAttackSpeed = (float) value; break;
                        case "dDiamondHoe.Damage": diarkriteDiamondHoeDamage = (int) value; break;
                        case "dDiamondHoe.Speed": diarkriteDiamondHoeAttackSpeed = (float) value; break;


                        case "aIronSword.Damage": anthektiteIronSwordDamage = (int) value; break;
                        case "aIronSword.Speed": anthektiteIronSwordAttackSpeed = (float) value; break;
                        case "aIronShovel.Damage": anthektiteIronShovelDamage = (float) value; break;
                        case "aIronShovel.Speed": anthektiteIronShovelAttackSpeed = (float) value; break;
                        case "aIronPickaxe.Damage": anthektiteIronPickaxeDamage = (int) value; break;
                        case "aIronPickaxe.Speed": anthektiteIronPickaxeAttackSpeed = (float) value; break;
                        case "aIronAxe.Damage": anthektiteIronAxeDamage = (float) value; break;
                        case "aIronAxe.Speed": anthektiteIronAxeAttackSpeed = (float) value; break;
                        case "aIronHoe.Damage": anthektiteIronHoeDamage = (int) value; break;
                        case "aIronHoe.Speed": anthektiteIronHoeAttackSpeed = (float) value; break;

                        case "aGoldSword.Damage": anthektiteGoldSwordDamage = (int) value; break;
                        case "aGoldSword.Speed": anthektiteGoldSwordAttackSpeed = (float) value; break;
                        case "aGoldShovel.Damage": anthektiteGoldShovelDamage = (float) value; break;
                        case "aGoldShovel.Speed": anthektiteGoldShovelAttackSpeed = (float) value; break;
                        case "aGoldPickaxe.Damage": anthektiteGoldPickaxeDamage = (int) value; break;
                        case "aGoldPickaxe.Speed": anthektiteGoldPickaxeAttackSpeed = (float) value; break;
                        case "aGoldAxe.Damage": anthektiteGoldAxeDamage = (float) value; break;
                        case "aGoldAxe.Speed": anthektiteGoldAxeAttackSpeed = (float) value; break;
                        case "aGoldHoe.Damage": anthektiteGoldHoeDamage = (int) value; break;
                        case "aGoldHoe.Speed": anthektiteGoldHoeAttackSpeed = (float) value; break;

                        case "aEmeraldSword.Damage": anthektiteEmeraldSwordDamage = (int) value; break;
                        case "aEmeraldSword.Speed": anthektiteEmeraldSwordAttackSpeed = (float) value; break;
                        case "aEmeraldShovel.Damage": anthektiteEmeraldShovelDamage = (float) value; break;
                        case "aEmeraldShovel.Speed": anthektiteEmeraldShovelAttackSpeed = (float) value; break;
                        case "aEmeraldPickaxe.Damage": anthektiteEmeraldPickaxeDamage = (int) value; break;
                        case "aEmeraldPickaxe.Speed": anthektiteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "aEmeraldAxe.Damage": anthektiteEmeraldAxeDamage = (float) value; break;
                        case "aEmeraldAxe.Speed": anthektiteEmeraldAxeAttackSpeed = (float) value; break;
                        case "aEmeraldHoe.Damage": anthektiteEmeraldHoeDamage = (int) value; break;
                        case "aEmeraldHoe.Speed": anthektiteEmeraldHoeAttackSpeed = (float) value; break;

                        case "aDiamondSword.Damage": anthektiteDiamondSwordDamage = (int) value; break;
                        case "aDiamondSword.Speed": anthektiteDiamondSwordAttackSpeed = (float) value; break;
                        case "aDiamondShovel.Damage": anthektiteDiamondShovelDamage = (float) value; break;
                        case "aDiamondShovel.Speed": anthektiteDiamondShovelAttackSpeed = (float) value; break;
                        case "aDiamondPickaxe.Damage": anthektiteDiamondPickaxeDamage = (int) value; break;
                        case "aDiamondPickaxe.Speed": anthektiteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "aDiamondAxe.Damage": anthektiteDiamondAxeDamage = (float) value; break;
                        case "aDiamondAxe.Speed": anthektiteDiamondAxeAttackSpeed = (float) value; break;
                        case "aDiamondHoe.Damage": anthektiteDiamondHoeDamage = (int) value; break;
                        case "aDiamondHoe.Speed": anthektiteDiamondHoeAttackSpeed = (float) value; break;


                        case "dIronArmor.Durability": diarkriteIronArmor_DurabilityForType = (int) value; break;
                        case "dIronArmor.Helmet": diarkriteIronArmor_Helmet = (int) value; break;
                        case "dIronArmor.Chestplate": diarkriteIronArmor_Chestplate = (int) value; break;
                        case "dIronArmor.Leggings": diarkriteIronArmor_Leggings = (int) value; break;
                        case "dIronArmor.Boots": diarkriteIronArmor_Boots = (int) value; break;
                        case "dIronArmor.Enchantability": diarkriteIronArmor_Enchantability = (int) value; break;
                        case "dIronArmor.Toughness": diarkriteIronArmor_Toughness = (float) value; break;
                        case "dIronArmor.KnockbackResistance": diarkriteIronArmor_KnockbackResistance = (float) value; break;
                        case "dIronArmor.AttackSpeedBoost": diarkriteIronArmor_AttackSpeed = (float) value; break;
                        case "dIronArmor.MovementSpeed": diarkriteIronArmor_MovementSpeed = (float) value; break;

                        case "dGoldArmor.Durability": diarkriteGoldArmor_DurabilityForType = (int) value; break;
                        case "dGoldArmor.Helmet": diarkriteGoldArmor_Helmet = (int) value; break;
                        case "dGoldArmor.Chestplate": diarkriteGoldArmor_Chestplate = (int) value; break;
                        case "dGoldArmor.Leggings": diarkriteGoldArmor_Leggings = (int) value; break;
                        case "dGoldArmor.Boots": diarkriteGoldArmor_Boots = (int) value; break;
                        case "dGoldArmor.Enchantability": diarkriteGoldArmor_Enchantability = (int) value; break;
                        case "dGoldArmor.Toughness": diarkriteGoldArmor_Toughness = (float) value; break;
                        case "dGoldArmor.KnockbackResistance": diarkriteGoldArmor_KnockbackResistance = (float) value; break;
                        case "dGoldArmor.AttackSpeedBoost": diarkriteGoldArmor_AttackSpeed = (float) value; break;
                        case "dGoldArmor.MovementSpeed": diarkriteGoldArmor_MovementSpeed = (float) value; break;

                        case "dEmeraldArmor.Durability": diarkriteEmeraldArmor_DurabilityForType = (int) value; break;
                        case "dEmeraldArmor.Helmet": diarkriteEmeraldArmor_Helmet = (int) value; break;
                        case "dEmeraldArmor.Chestplate": diarkriteEmeraldArmor_Chestplate = (int) value; break;
                        case "dEmeraldArmor.Leggings": diarkriteEmeraldArmor_Leggings = (int) value; break;
                        case "dEmeraldArmor.Boots": diarkriteEmeraldArmor_Boots = (int) value; break;
                        case "dEmeraldArmor.Enchantability": diarkriteEmeraldArmor_Enchantability = (int) value; break;
                        case "dEmeraldArmor.Toughness": diarkriteEmeraldArmor_Toughness = (float) value; break;
                        case "dEmeraldArmor.KnockbackResistance": diarkriteEmeraldArmor_KnockbackResistance = (float) value; break;
                        case "dEmeraldArmor.AttackSpeedBoost": diarkriteEmeraldArmor_AttackSpeed = (float) value; break;
                        case "dEmeraldArmor.MovementSpeed": diarkriteEmeraldArmor_MovementSpeed = (float) value; break;

                        case "dDiamondArmor.Durability": diarkriteDiamondArmor_DurabilityForType = (int) value; break;
                        case "dDiamondArmor.Helmet": diarkriteDiamondArmor_Helmet = (int) value; break;
                        case "dDiamondArmor.Chestplate": diarkriteDiamondArmor_Chestplate = (int) value; break;
                        case "dDiamondArmor.Leggings": diarkriteDiamondArmor_Leggings = (int) value; break;
                        case "dDiamondArmor.Boots": diarkriteDiamondArmor_Boots = (int) value; break;
                        case "dDiamondArmor.Enchantability": diarkriteDiamondArmor_Enchantability = (int) value; break;
                        case "dDiamondArmor.Toughness": diarkriteDiamondArmor_Toughness = (float) value; break;
                        case "dDiamondArmor.KnockbackResistance": diarkriteDiamondArmor_KnockbackResistance = (float) value; break;
                        case "dDiamondArmor.AttackSpeedBoost": diarkriteDiamondArmor_AttackSpeed = (float) value; break;
                        case "dDiamondArmor.MovementSpeed": diarkriteDiamondArmor_MovementSpeed = (float) value; break;


                        case "aIronArmor.Durability": anthektiteIronArmor_DurabilityForType = (int) value; break;
                        case "aIronArmor.Helmet": anthektiteIronArmor_Helmet = (int) value; break;
                        case "aIronArmor.Chestplate": anthektiteIronArmor_Chestplate = (int) value; break;
                        case "aIronArmor.Leggings": anthektiteIronArmor_Leggings = (int) value; break;
                        case "aIronArmor.Boots": anthektiteIronArmor_Boots = (int) value; break;
                        case "aIronArmor.Enchantability": anthektiteIronArmor_Enchantability = (int) value; break;
                        case "aIronArmor.Toughness": anthektiteIronArmor_Toughness = (float) value; break;
                        case "aIronArmor.KnockbackResistance": anthektiteIronArmor_KnockbackResistance = (float) value; break;
                        case "aIronArmor.AttackSpeedBoost": anthektiteIronArmor_AttackSpeed = (float) value; break;
                        case "aIronArmor.MovementSpeed": anthektiteIronArmor_MovementSpeed = (float) value; break;

                        case "aGoldArmor.Durability": anthektiteGoldArmor_DurabilityForType = (int) value; break;
                        case "aGoldArmor.Helmet": anthektiteGoldArmor_Helmet = (int) value; break;
                        case "aGoldArmor.Chestplate": anthektiteGoldArmor_Chestplate = (int) value; break;
                        case "aGoldArmor.Leggings": anthektiteGoldArmor_Leggings = (int) value; break;
                        case "aGoldArmor.Boots": anthektiteGoldArmor_Boots = (int) value; break;
                        case "aGoldArmor.Enchantability": anthektiteGoldArmor_Enchantability = (int) value; break;
                        case "aGoldArmor.Toughness": anthektiteGoldArmor_Toughness = (float) value; break;
                        case "aGoldArmor.KnockbackResistance": anthektiteGoldArmor_KnockbackResistance = (float) value; break;
                        case "aGoldArmor.AttackSpeedBoost": anthektiteGoldArmor_AttackSpeed = (float) value; break;
                        case "aGoldArmor.MovementSpeed": anthektiteGoldArmor_MovementSpeed = (float) value; break;

                        case "aEmeraldArmor.Durability": anthektiteEmeraldArmor_DurabilityForType = (int) value; break;
                        case "aEmeraldArmor.Helmet": anthektiteEmeraldArmor_Helmet = (int) value; break;
                        case "aEmeraldArmor.Chestplate": anthektiteEmeraldArmor_Chestplate = (int) value; break;
                        case "aEmeraldArmor.Leggings": anthektiteEmeraldArmor_Leggings = (int) value; break;
                        case "aEmeraldArmor.Boots": anthektiteEmeraldArmor_Boots = (int) value; break;
                        case "aEmeraldArmor.Enchantability": anthektiteEmeraldArmor_Enchantability = (int) value; break;
                        case "aEmeraldArmor.Toughness": anthektiteEmeraldArmor_Toughness = (float) value; break;
                        case "aEmeraldArmor.KnockbackResistance": anthektiteEmeraldArmor_KnockbackResistance = (float) value; break;
                        case "aEmeraldArmor.AttackSpeedBoost": anthektiteEmeraldArmor_AttackSpeed = (float) value; break;
                        case "aEmeraldArmor.MovementSpeed": anthektiteEmeraldArmor_MovementSpeed = (float) value; break;

                        case "aDiamondArmor.Durability": anthektiteDiamondArmor_DurabilityForType = (int) value; break;
                        case "aDiamondArmor.Helmet": anthektiteDiamondArmor_Helmet = (int) value; break;
                        case "aDiamondArmor.Chestplate": anthektiteDiamondArmor_Chestplate = (int) value; break;
                        case "aDiamondArmor.Leggings": anthektiteDiamondArmor_Leggings = (int) value; break;
                        case "aDiamondArmor.Boots": anthektiteDiamondArmor_Boots = (int) value; break;
                        case "aDiamondArmor.Enchantability": anthektiteDiamondArmor_Enchantability = (int) value; break;
                        case "aDiamondArmor.Toughness": anthektiteDiamondArmor_Toughness = (float) value; break;
                        case "aDiamondArmor.KnockbackResistance": anthektiteDiamondArmor_KnockbackResistance = (float) value; break;
                        case "aDiamondArmor.AttackSpeedBoost": anthektiteDiamondArmor_AttackSpeed = (float) value; break;
                        case "aDiamondArmor.MovementSpeed": anthektiteDiamondArmor_MovementSpeed = (float) value; break;
                        default:
                            logger.warn(errorPrefix + "unrecognized parameter name: " + key);
                    }
                }
            }
        } catch (NoSuchFileException e) {
            save();
            logger.info("Config file not found, generating default");

        } catch (IOException e) {
            logger.warn("Could not read config file: ", e);
        }
        // may save twice, but not big deal
        if (version.compareTo(VERSION) < 0) {
            logger.info("Config version outdated, Updating config \"advanced_netherite_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Advanced Netherite Item Stat Config\n");
            writer.write("\n");
            writer.write("[DiarkriteIron.Tier]\n");
            writer.write("# [Default: " + diarkriteIronTierHarvestLevel + "]\n");
            writer.write("  dIronTier.HarvestLevel = " + diarkriteIronTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteIronTierDurability + "]\n");
            writer.write("  dIronTier.Durability = " + diarkriteIronTierDurability + "\n");
            writer.write("# [Default: " + diarkriteIronTierEfficiency + "]\n");
            writer.write("  dIronTier.Efficiency = " + diarkriteIronTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteIronTierDamage + "]\n");
            writer.write("  dIronTier.Damage = " + diarkriteIronTierDamage + "\n");
            writer.write("# [Default: " + diarkriteIronTierEnchantability + "]\n");
            writer.write("  dIronTier.Enchantability = " + diarkriteIronTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[DiarkriteGold.Tier]\n");
            writer.write("# [Default: " + diarkriteGoldTierHarvestLevel + "]\n");
            writer.write("  dGoldTier.HarvestLevel = " + diarkriteGoldTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteGoldTierDurability + "]\n");
            writer.write("  dGoldTier.Durability = " + diarkriteGoldTierDurability + "\n");
            writer.write("# [Default: " + diarkriteGoldTierEfficiency + "]\n");
            writer.write("  dGoldTier.Efficiency = " + diarkriteGoldTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteGoldTierDamage + "]\n");
            writer.write("  dGoldTier.Damage = " + diarkriteGoldTierDamage + "\n");
            writer.write("# [Default: " + diarkriteGoldTierEnchantability + "]\n");
            writer.write("  dGoldTier.Enchantability = " + diarkriteGoldTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[DiarkriteEmerald.Tier]\n");
            writer.write("# [Default: " + diarkriteEmeraldTierHarvestLevel + "]\n");
            writer.write("  dEmeraldTier.HarvestLevel = " + diarkriteEmeraldTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierDurability + "]\n");
            writer.write("  dEmeraldTier.Durability = " + diarkriteEmeraldTierDurability + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierEfficiency + "]\n");
            writer.write("  dEmeraldTier.Efficiency = " + diarkriteEmeraldTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierDamage + "]\n");
            writer.write("  dEmeraldTier.Damage = " + diarkriteEmeraldTierDamage + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierEnchantability + "]\n");
            writer.write("  dEmeraldTier.Enchantability = " + diarkriteEmeraldTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[DiarkriteDiamond.Tier]\n");
            writer.write("# [Default: " + diarkriteDiamondTierHarvestLevel + "]\n");
            writer.write("  dDiamondTier.HarvestLevel = " + diarkriteDiamondTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierDurability + "]\n");
            writer.write("  dDiamondTier.Durability = " + diarkriteDiamondTierDurability + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierEfficiency + "]\n");
            writer.write("  dDiamondTier.Efficiency = " + diarkriteDiamondTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierDamage + "]\n");
            writer.write("  dDiamondTier.Damage = " + diarkriteDiamondTierDamage + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierEnchantability + "]\n");
            writer.write("  dDiamondTier.Enchantability = " + diarkriteDiamondTierEnchantability + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteIron.Tier]\n");
            writer.write("# [Default: " + anthektiteIronTierHarvestLevel + "]\n");
            writer.write("  aIronTier.HarvestLevel = " + anthektiteIronTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteIronTierDurability + "]\n");
            writer.write("  aIronTier.Durability = " + anthektiteIronTierDurability + "\n");
            writer.write("# [Default: " + anthektiteIronTierEfficiency + "]\n");
            writer.write("  aIronTier.Efficiency = " + anthektiteIronTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteIronTierDamage + "]\n");
            writer.write("  aIronTier.Damage = " + anthektiteIronTierDamage + "\n");
            writer.write("# [Default: " + anthektiteIronTierEnchantability + "]\n");
            writer.write("  aIronTier.Enchantability = " + anthektiteIronTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[AnthektiteGold.Tier]\n");
            writer.write("# [Default: " + anthektiteGoldTierHarvestLevel + "]\n");
            writer.write("  aGoldTier.HarvestLevel = " + anthektiteGoldTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteGoldTierDurability + "]\n");
            writer.write("  aGoldTier.Durability = " + anthektiteGoldTierDurability + "\n");
            writer.write("# [Default: " + anthektiteGoldTierEfficiency + "]\n");
            writer.write("  aGoldTier.Efficiency = " + anthektiteGoldTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteGoldTierDamage + "]\n");
            writer.write("  aGoldTier.Damage = " + anthektiteGoldTierDamage + "\n");
            writer.write("# [Default: " + anthektiteGoldTierEnchantability + "]\n");
            writer.write("  aGoldTier.Enchantability = " + anthektiteGoldTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[AnthektiteEmerald.Tier]\n");
            writer.write("# [Default: " + anthektiteEmeraldTierHarvestLevel + "]\n");
            writer.write("  aEmeraldTier.HarvestLevel = " + anthektiteEmeraldTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierDurability + "]\n");
            writer.write("  aEmeraldTier.Durability = " + anthektiteEmeraldTierDurability + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierEfficiency + "]\n");
            writer.write("  aEmeraldTier.Efficiency = " + anthektiteEmeraldTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierDamage + "]\n");
            writer.write("  aEmeraldTier.Damage = " + anthektiteEmeraldTierDamage + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierEnchantability + "]\n");
            writer.write("  aEmeraldTier.Enchantability = " + anthektiteEmeraldTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[AnthektiteDiamond.Tier]\n");
            writer.write("# [Default: " + anthektiteDiamondTierHarvestLevel + "]\n");
            writer.write("  aDiamondTier.HarvestLevel = " + anthektiteDiamondTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierDurability + "]\n");
            writer.write("  aDiamondTier.Durability = " + anthektiteDiamondTierDurability + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierEfficiency + "]\n");
            writer.write("  aDiamondTier.Efficiency = " + anthektiteDiamondTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierDamage + "]\n");
            writer.write("  aDiamondTier.Damage = " + anthektiteDiamondTierDamage + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierEnchantability + "]\n");
            writer.write("  aDiamondTier.Enchantability = " + anthektiteDiamondTierEnchantability + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[DiarkriteIron.Tool]\n");
            writer.write("# Default: " + diarkriteIronSwordDamage +"\n");
            writer.write("  dIronSword.Damage = " + diarkriteIronSwordDamage + "\n");
            writer.write("# Default: " + diarkriteIronSwordAttackSpeed +"\n");
            writer.write("  dIronSword.Speed = " + diarkriteIronSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteIronShovelDamage +"\n");
            writer.write("  dIronShovel.Damage = " + diarkriteIronShovelDamage + "\n");
            writer.write("# Default: " + diarkriteIronShovelAttackSpeed +"\n");
            writer.write("  dIronShovel.Speed = " + diarkriteIronShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteIronPickaxeDamage +"\n");
            writer.write("  ddIronPickaxe.Damage = " + diarkriteIronPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteIronPickaxeAttackSpeed +"\n");
            writer.write("  ddIronPickaxe.Speed = " + diarkriteIronPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteIronAxeDamage +"\n");
            writer.write("  dIronAxe.Damage = " + diarkriteIronAxeDamage + "\n");
            writer.write("# Default: " + diarkriteIronAxeAttackSpeed +"\n");
            writer.write("  dIronAxe.Speed = " + diarkriteIronAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteIronHoeDamage +"\n");
            writer.write("  dIronHoe.Damage = " + diarkriteIronHoeDamage + "\n");
            writer.write("# Default: " + diarkriteIronHoeAttackSpeed +"\n");
            writer.write("  dIronHoe.Speed = " + diarkriteIronHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteGold.Tool]\n");
            writer.write("# Default: " + diarkriteGoldSwordDamage +"\n");
            writer.write("  dGoldSword.Damage = " + diarkriteGoldSwordDamage + "\n");
            writer.write("# Default: " + diarkriteGoldSwordAttackSpeed +"\n");
            writer.write("  dGoldSword.Speed = " + diarkriteGoldSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGoldShovelDamage +"\n");
            writer.write("  dGoldShovel.Damage = " + diarkriteGoldShovelDamage + "\n");
            writer.write("# Default: " + diarkriteGoldShovelAttackSpeed +"\n");
            writer.write("  dGoldShovel.Speed = " + diarkriteGoldShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGoldPickaxeDamage +"\n");
            writer.write("  dGoldPickaxe.Damage = " + diarkriteGoldPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteGoldPickaxeAttackSpeed +"\n");
            writer.write("  dGoldPickaxe.Speed = " + diarkriteGoldPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGoldAxeDamage +"\n");
            writer.write("  dGoldAxe.Damage = " + diarkriteGoldAxeDamage + "\n");
            writer.write("# Default: " + diarkriteGoldAxeAttackSpeed +"\n");
            writer.write("  dGoldAxe.Speed = " + diarkriteGoldAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGoldHoeDamage +"\n");
            writer.write("  dGoldHoe.Damage = " + diarkriteGoldHoeDamage + "\n");
            writer.write("# Default: " + diarkriteGoldHoeAttackSpeed +"\n");
            writer.write("  dGoldHoe.Speed = " + diarkriteGoldHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteEmerald.Tool]\n");
            writer.write("# Default: " + diarkriteEmeraldSwordDamage +"\n");
            writer.write("  dEmeraldSword.Damage = " + diarkriteEmeraldSwordDamage + "\n");
            writer.write("# Default: " + diarkriteEmeraldSwordAttackSpeed +"\n");
            writer.write("  dEmeraldSword.Speed = " + diarkriteEmeraldSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteEmeraldShovelDamage +"\n");
            writer.write("  dEmeraldShovel.Damage = " + diarkriteEmeraldShovelDamage + "\n");
            writer.write("# Default: " + diarkriteEmeraldShovelAttackSpeed +"\n");
            writer.write("  dEmeraldShovel.Speed = " + diarkriteEmeraldShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteEmeraldPickaxeDamage +"\n");
            writer.write("  dEmeraldPickaxe.Damage = " + diarkriteEmeraldPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteEmeraldPickaxeAttackSpeed +"\n");
            writer.write("  dEmeraldPickaxe.Speed = " + diarkriteEmeraldPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteEmeraldAxeDamage +"\n");
            writer.write("  dEmeraldAxe.Damage = " + diarkriteEmeraldAxeDamage + "\n");
            writer.write("# Default: " + diarkriteEmeraldAxeAttackSpeed +"\n");
            writer.write("  dEmeraldAxe.Speed = " + diarkriteEmeraldAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteEmeraldHoeDamage +"\n");
            writer.write("  dEmeraldHoe.Damage = " + diarkriteEmeraldHoeDamage + "\n");
            writer.write("# Default: " + diarkriteEmeraldHoeAttackSpeed +"\n");
            writer.write("  dEmeraldHoe.Speed = " + diarkriteEmeraldHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteDiamond.Tool]\n");
            writer.write("# Default: " + diarkriteDiamondSwordDamage +"\n");
            writer.write("  dDiamondSword.Damage = " + diarkriteDiamondSwordDamage + "\n");
            writer.write("# Default: " + diarkriteDiamondSwordAttackSpeed +"\n");
            writer.write("  dDiamondSword.Speed = " + diarkriteDiamondSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteDiamondShovelDamage +"\n");
            writer.write("  dDiamondShovel.Damage = " + diarkriteDiamondShovelDamage + "\n");
            writer.write("# Default: " + diarkriteDiamondShovelAttackSpeed +"\n");
            writer.write("  dDiamondShovel.Speed = " + diarkriteDiamondShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteDiamondPickaxeDamage +"\n");
            writer.write("  dDiamondPickaxe.Damage = " + diarkriteDiamondPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteDiamondPickaxeAttackSpeed +"\n");
            writer.write("  dDiamondPickaxe.Speed = " + diarkriteDiamondPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteDiamondAxeDamage +"\n");
            writer.write("  dDiamondAxe.Damage = " + diarkriteDiamondAxeDamage + "\n");
            writer.write("# Default: " + diarkriteDiamondAxeAttackSpeed +"\n");
            writer.write("  dDiamondAxe.Speed = " + diarkriteDiamondAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteDiamondHoeDamage +"\n");
            writer.write("  dDiamondHoe.Damage = " + diarkriteDiamondHoeDamage + "\n");
            writer.write("# Default: " + diarkriteDiamondHoeAttackSpeed +"\n");
            writer.write("  dDiamondHoe.Speed = " + diarkriteDiamondHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteIron.Tool]\n");
            writer.write("# Default: " + anthektiteIronSwordDamage +"\n");
            writer.write("  aIronSword.Damage = " + anthektiteIronSwordDamage + "\n");
            writer.write("# Default: " + anthektiteIronSwordAttackSpeed +"\n");
            writer.write("  aIronSword.Speed = " + anthektiteIronSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteIronShovelDamage +"\n");
            writer.write("  aIronShovel.Damage = " + anthektiteIronShovelDamage + "\n");
            writer.write("# Default: " + anthektiteIronShovelAttackSpeed +"\n");
            writer.write("  aIronShovel.Speed = " + anthektiteIronShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteIronPickaxeDamage +"\n");
            writer.write("  aIronPickaxe.Damage = " + anthektiteIronPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteIronPickaxeAttackSpeed +"\n");
            writer.write("  aIronPickaxe.Speed = " + anthektiteIronPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteIronAxeDamage +"\n");
            writer.write("  aIronAxe.Damage = " + anthektiteIronAxeDamage + "\n");
            writer.write("# Default: " + anthektiteIronAxeAttackSpeed +"\n");
            writer.write("  aIronAxe.Speed = " + anthektiteIronAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteIronHoeDamage +"\n");
            writer.write("  aIronHoe.Damage = " + anthektiteIronHoeDamage + "\n");
            writer.write("# Default: " + anthektiteIronHoeAttackSpeed +"\n");
            writer.write("  aIronHoe.Speed = " + anthektiteIronHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteGold.Tool]\n");
            writer.write("# Default: " + anthektiteGoldSwordDamage +"\n");
            writer.write("  aGoldSword.Damage = " + anthektiteGoldSwordDamage + "\n");
            writer.write("# Default: " + anthektiteGoldSwordAttackSpeed +"\n");
            writer.write("  aGoldSword.Speed = " + anthektiteGoldSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGoldShovelDamage +"\n");
            writer.write("  aGoldShovel.Damage = " + anthektiteGoldShovelDamage + "\n");
            writer.write("# Default: " + anthektiteGoldShovelAttackSpeed +"\n");
            writer.write("  aGoldShovel.Speed = " + anthektiteGoldShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGoldPickaxeDamage +"\n");
            writer.write("  aGoldPickaxe.Damage = " + anthektiteGoldPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteGoldPickaxeAttackSpeed +"\n");
            writer.write("  aGoldPickaxe.Speed = " + anthektiteGoldPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGoldAxeDamage +"\n");
            writer.write("  aGoldAxe.Damage = " + anthektiteGoldAxeDamage + "\n");
            writer.write("# Default: " + anthektiteGoldAxeAttackSpeed +"\n");
            writer.write("  aGoldAxe.Speed = " + anthektiteGoldAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGoldHoeDamage +"\n");
            writer.write("  aGoldHoe.Damage = " + anthektiteGoldHoeDamage + "\n");
            writer.write("# Default: " + anthektiteGoldHoeAttackSpeed +"\n");
            writer.write("  aGoldHoe.Speed = " + anthektiteGoldHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteEmerald.Tool]\n");
            writer.write("# Default: " + anthektiteEmeraldSwordDamage +"\n");
            writer.write("  aEmeraldSword.Damage = " + anthektiteEmeraldSwordDamage + "\n");
            writer.write("# Default: " + anthektiteEmeraldSwordAttackSpeed +"\n");
            writer.write("  aEmeraldSword.Speed = " + anthektiteEmeraldSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteEmeraldShovelDamage +"\n");
            writer.write("  aEmeraldShovel.Damage = " + anthektiteEmeraldShovelDamage + "\n");
            writer.write("# Default: " + anthektiteEmeraldShovelAttackSpeed +"\n");
            writer.write("  aEmeraldShovel.Speed = " + anthektiteEmeraldShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteEmeraldPickaxeDamage +"\n");
            writer.write("  aEmeraldPickaxe.Damage = " + anthektiteEmeraldPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteEmeraldPickaxeAttackSpeed +"\n");
            writer.write("  aEmeraldPickaxe.Speed = " + anthektiteEmeraldPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteEmeraldAxeDamage +"\n");
            writer.write("  aEmeraldAxe.Damage = " + anthektiteEmeraldAxeDamage + "\n");
            writer.write("# Default: " + anthektiteEmeraldAxeAttackSpeed +"\n");
            writer.write("  aEmeraldAxe.Speed = " + anthektiteEmeraldAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteEmeraldHoeDamage +"\n");
            writer.write("  aEmeraldHoe.Damage = " + anthektiteEmeraldHoeDamage + "\n");
            writer.write("# Default: " + anthektiteEmeraldHoeAttackSpeed +"\n");
            writer.write("  aEmeraldHoe.Speed = " + anthektiteEmeraldHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteDiamond.Tool]\n");
            writer.write("# Default: " + anthektiteDiamondSwordDamage +"\n");
            writer.write("  aDiamondSword.Damage = " + anthektiteDiamondSwordDamage + "\n");
            writer.write("# Default: " + anthektiteDiamondSwordAttackSpeed +"\n");
            writer.write("  aDiamondSword.Speed = " + anthektiteDiamondSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteDiamondShovelDamage +"\n");
            writer.write("  aDiamondShovel.Damage = " + anthektiteDiamondShovelDamage + "\n");
            writer.write("# Default: " + anthektiteDiamondShovelAttackSpeed +"\n");
            writer.write("  aDiamondShovel.Speed = " + anthektiteDiamondShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteDiamondPickaxeDamage +"\n");
            writer.write("  aDiamondPickaxe.Damage = " + anthektiteDiamondPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteDiamondPickaxeAttackSpeed +"\n");
            writer.write("  aDiamondPickaxe.Speed = " + anthektiteDiamondPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteDiamondAxeDamage +"\n");
            writer.write("  aDiamondAxe.Damage = " + anthektiteDiamondAxeDamage + "\n");
            writer.write("# Default: " + anthektiteDiamondAxeAttackSpeed +"\n");
            writer.write("  aDiamondAxe.Speed = " + anthektiteDiamondAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteDiamondHoeDamage +"\n");
            writer.write("  aDiamondHoe.Damage = " + anthektiteDiamondHoeDamage + "\n");
            writer.write("# Default: " + anthektiteDiamondHoeAttackSpeed +"\n");
            writer.write("  aDiamondHoe.Speed = " + anthektiteDiamondHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[DiarkriteIron.Armor]\n");
            writer.write("# Default: " + diarkriteIronArmor_DurabilityForType +"\n");
            writer.write("  dIronArmor.Durability = " + diarkriteIronArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Enchantability +"\n");
            writer.write("  dIronArmor.Enchantability = " + diarkriteIronArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Helmet +"\n");
            writer.write("  dIronArmor.Helmet = " + diarkriteIronArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Chestplate +"\n");
            writer.write("  dIronArmor.Chestplate = " + diarkriteIronArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Leggings +"\n");
            writer.write("  dIronArmor.Leggings = " + diarkriteIronArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Boots +"\n");
            writer.write("  dIronArmor.Boots = " + diarkriteIronArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteIronArmor_Toughness +"\n");
            writer.write("  dIronArmor.Toughness = " + diarkriteIronArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteIronArmor_KnockbackResistance +"\n");
            writer.write("  dIronArmor.KnockbackResistance = " + diarkriteIronArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteIronArmor_AttackSpeed +"\n");
            writer.write("  dIronArmor.AttackSpeedBoost = " + diarkriteIronArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteIronArmor_MovementSpeed +"\n");
            writer.write("  dIronArmor.MovementSpeed = " + diarkriteIronArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteGold.Armor]\n");
            writer.write("# Default: " + diarkriteGoldArmor_DurabilityForType +"\n");
            writer.write("  dGoldArmor.Durability = " + diarkriteGoldArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Enchantability +"\n");
            writer.write("  dGoldArmor.Enchantability = " + diarkriteGoldArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Helmet +"\n");
            writer.write("  dGoldArmor.Helmet = " + diarkriteGoldArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Chestplate +"\n");
            writer.write("  dGoldArmor.Chestplate = " + diarkriteGoldArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Leggings +"\n");
            writer.write("  dGoldArmor.Leggings = " + diarkriteGoldArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Boots +"\n");
            writer.write("  dGoldArmor.Boots = " + diarkriteGoldArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_Toughness +"\n");
            writer.write("  dGoldArmor.Toughness = " + diarkriteGoldArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_KnockbackResistance +"\n");
            writer.write("  dGoldArmor.KnockbackResistance = " + diarkriteGoldArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_AttackSpeed +"\n");
            writer.write("  dGoldArmor.AttackSpeedBoost = " + diarkriteGoldArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGoldArmor_MovementSpeed +"\n");
            writer.write("  dGoldArmor.MovementSpeed = " + diarkriteGoldArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteEmerald.Armor]\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_DurabilityForType +"\n");
            writer.write("  dEmeraldArmor.Durability = " + diarkriteEmeraldArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Enchantability +"\n");
            writer.write("  dEmeraldArmor.Enchantability = " + diarkriteEmeraldArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Helmet +"\n");
            writer.write("  dEmeraldArmor.Helmet = " + diarkriteEmeraldArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Chestplate +"\n");
            writer.write("  dEmeraldArmor.Chestplate = " + diarkriteEmeraldArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Leggings +"\n");
            writer.write("  dEmeraldArmor.Leggings = " + diarkriteEmeraldArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Boots +"\n");
            writer.write("  dEmeraldArmor.Boots = " + diarkriteEmeraldArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_Toughness +"\n");
            writer.write("  dEmeraldArmor.Toughness = " + diarkriteEmeraldArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_KnockbackResistance +"\n");
            writer.write("  dEmeraldArmor.KnockbackResistance = " + diarkriteEmeraldArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_AttackSpeed +"\n");
            writer.write("  dEmeraldArmor.AttackSpeedBoost = " + diarkriteEmeraldArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteEmeraldArmor_MovementSpeed +"\n");
            writer.write("  dEmeraldArmor.MovementSpeed = " + diarkriteEmeraldArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteDiamond.Armor]\n");
            writer.write("# Default: " + diarkriteDiamondArmor_DurabilityForType +"\n");
            writer.write("  dDiamondArmor.Durability = " + diarkriteDiamondArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Enchantability +"\n");
            writer.write("  dDiamondArmor.Enchantability = " + diarkriteDiamondArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Helmet +"\n");
            writer.write("  dDiamondArmor.Helmet = " + diarkriteDiamondArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Chestplate +"\n");
            writer.write("  dDiamondArmor.Chestplate = " + diarkriteDiamondArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Leggings +"\n");
            writer.write("  dDiamondArmor.Leggings = " + diarkriteDiamondArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Boots +"\n");
            writer.write("  dDiamondArmor.Boots = " + diarkriteDiamondArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_Toughness +"\n");
            writer.write("  dDiamondArmor.Toughness = " + diarkriteDiamondArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_KnockbackResistance +"\n");
            writer.write("  dDiamondArmor.KnockbackResistance = " + diarkriteDiamondArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_AttackSpeed +"\n");
            writer.write("  dDiamondArmor.AttackSpeedBoost = " + diarkriteDiamondArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteDiamondArmor_MovementSpeed +"\n");
            writer.write("  dDiamondArmor.MovementSpeed = " + diarkriteDiamondArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteIron.Armor]\n");
            writer.write("# Default: " + anthektiteIronArmor_DurabilityForType +"\n");
            writer.write("  aIronArmor.Durability = " + anthektiteIronArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Enchantability +"\n");
            writer.write("  aIronArmor.Enchantability = " + anthektiteIronArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Helmet +"\n");
            writer.write("  aIronArmor.Helmet = " + anthektiteIronArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Chestplate +"\n");
            writer.write("  aIronArmor.Chestplate = " + anthektiteIronArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Leggings +"\n");
            writer.write("  aIronArmor.Leggings = " + anthektiteIronArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Boots +"\n");
            writer.write("  aIronArmor.Boots = " + anthektiteIronArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteIronArmor_Toughness +"\n");
            writer.write("  aIronArmor.Toughness = " + anthektiteIronArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteIronArmor_KnockbackResistance +"\n");
            writer.write("  aIronArmor.KnockbackResistance = " + anthektiteIronArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteIronArmor_AttackSpeed +"\n");
            writer.write("  aIronArmor.AttackSpeedBoost = " + anthektiteIronArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteIronArmor_MovementSpeed +"\n");
            writer.write("  aIronArmor.MovementSpeed = " + anthektiteIronArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteGold.Armor]\n");
            writer.write("# Default: " + anthektiteGoldArmor_DurabilityForType +"\n");
            writer.write("  aGoldArmor.Durability = " + anthektiteGoldArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Enchantability +"\n");
            writer.write("  aGoldArmor.Enchantability = " + anthektiteGoldArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Helmet +"\n");
            writer.write("  aGoldArmor.Helmet = " + anthektiteGoldArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Chestplate +"\n");
            writer.write("  aGoldArmor.Chestplate = " + anthektiteGoldArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Leggings +"\n");
            writer.write("  aGoldArmor.Leggings = " + anthektiteGoldArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Boots +"\n");
            writer.write("  aGoldArmor.Boots = " + anthektiteGoldArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_Toughness +"\n");
            writer.write("  aGoldArmor.Toughness = " + anthektiteGoldArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_KnockbackResistance +"\n");
            writer.write("  aGoldArmor.KnockbackResistance = " + anthektiteGoldArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_AttackSpeed +"\n");
            writer.write("  aGoldArmor.AttackSpeedBoost = " + anthektiteGoldArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGoldArmor_MovementSpeed +"\n");
            writer.write("  aGoldArmor.MovementSpeed = " + anthektiteGoldArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteEmerald.Armor]\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_DurabilityForType +"\n");
            writer.write("  aEmeraldArmor.Durability = " + anthektiteEmeraldArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Enchantability +"\n");
            writer.write("  aEmeraldArmor.Enchantability = " + anthektiteEmeraldArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Helmet +"\n");
            writer.write("  aEmeraldArmor.Helmet = " + anthektiteEmeraldArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Chestplate +"\n");
            writer.write("  aEmeraldArmor.Chestplate = " + anthektiteEmeraldArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Leggings +"\n");
            writer.write("  aEmeraldArmor.Leggings = " + anthektiteEmeraldArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Boots +"\n");
            writer.write("  aEmeraldArmor.Boots = " + anthektiteEmeraldArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_Toughness +"\n");
            writer.write("  aEmeraldArmor.Toughness = " + anthektiteEmeraldArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_KnockbackResistance +"\n");
            writer.write("  aEmeraldArmor.KnockbackResistance = " + anthektiteEmeraldArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_AttackSpeed +"\n");
            writer.write("  aEmeraldArmor.AttackSpeedBoost = " + anthektiteEmeraldArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteEmeraldArmor_MovementSpeed +"\n");
            writer.write("  aEmeraldArmor.MovementSpeed = " + anthektiteEmeraldArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteDiamond.Armor]\n");
            writer.write("# Default: " + anthektiteDiamondArmor_DurabilityForType +"\n");
            writer.write("  aDiamondArmor.Durability = " + anthektiteDiamondArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Enchantability +"\n");
            writer.write("  aDiamondArmor.Enchantability = " + anthektiteDiamondArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Helmet +"\n");
            writer.write("  aDiamondArmor.Helmet = " + anthektiteDiamondArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Chestplate +"\n");
            writer.write("  aDiamondArmor.Chestplate = " + anthektiteDiamondArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Leggings +"\n");
            writer.write("  aDiamondArmor.Leggings = " + anthektiteDiamondArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Boots +"\n");
            writer.write("  aDiamondArmor.Boots = " + anthektiteDiamondArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_Toughness +"\n");
            writer.write("  aDiamondArmor.Toughness = " + anthektiteDiamondArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_KnockbackResistance +"\n");
            writer.write("  aDiamondArmor.KnockbackResistance = " + anthektiteDiamondArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_AttackSpeed +"\n");
            writer.write("  aDiamondArmor.AttackSpeedBoost = " + anthektiteDiamondArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteDiamondArmor_MovementSpeed +"\n");
            writer.write("  aDiamondArmor.MovementSpeed = " + anthektiteDiamondArmor_MovementSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
