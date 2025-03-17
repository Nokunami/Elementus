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

import static net.nokunami.elementus.Elementus.CONFIG_VERSION;

/**
 * credits: SkpC9 <a href="https://github.com/SkpC9/Simply-Steel/blob/main/src/main/java/com/trbz_/simplysteel/util/ConfigHandler.java">Link</a>
 * */
public class TierConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final TierConfig INSTANCE = new TierConfig();
    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Tiers
    public static int steelTierHarvestLevel;
    public static int steelTierDurability;
    public static float steelTierEfficiency;
    public static float steelTierDamage;
    public static int steelTierEnchantability;
    public static double steelWeaponSpeedModifier;

    public static int diarkriteTierHarvestLevel;
    public static int diarkriteTierDurability;
    public static float diarkriteTierEfficiency;
    public static float diarkriteTierDamage;
    public static int diarkriteTierEnchantability;
    public static double diarkriteWeaponSpeedModifier;
    public static float diarkriteAdditionalEfficiency;

    public static int anthektiteTierHarvestLevel;
    public static int anthektiteTierDurability;
    public static float anthektiteTierEfficiency;
    public static float anthektiteTierDamage;
    public static int anthektiteTierEnchantability;
    public static double anthektiteWeaponSpeedModifier;

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

    //Cracker's Witherstormmod
    public static int steelCMDTierHarvestLevel;
    public static int steelCMDTierDurability;
    public static float steelCMDTierDamage;
    public static float steelCMDTierEfficiency;
    public static int steelCMDTierEnchantability;

    public static int diarkriteCMDTierHarvestLevel;
    public static int diarkriteCMDTierDurability;
    public static float diarkriteCMDTierDamage;
    public static float diarkriteCMDTierEfficiency;
    public static int diarkriteCMDTierEnchantability;

    public static int anthektiteCMDTierHarvestLevel;
    public static int anthektiteCMDTierDurability;
    public static float anthektiteCMDTierDamage;
    public static float anthektiteCMDTierEfficiency;
    public static int anthektiteCMDTierEnchantability;

    //Banilla Claws
    public static int steelClawTierHarvestLevel;
    public static int steelClawTierDurability;
    public static float steelClawTierDamage;
    public static float steelClawTierEfficiency;
    public static int steelClawTierEnchantability;

    public static int diarkriteClawTierHarvestLevel;
    public static int diarkriteClawTierDurability;
    public static float diarkriteClawTierDamage;
    public static float diarkriteClawTierEfficiency;
    public static int diarkriteClawTierEnchantability;

    public static int anthektiteClawTierHarvestLevel;
    public static int anthektiteClawTierDurability;
    public static float anthektiteClawTierDamage;
    public static float anthektiteClawTierEfficiency;
    public static int anthektiteClawTierEnchantability;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Tier Config loaded");
    }

    private void setDefaults() {
        steelTierHarvestLevel = 3;
        steelTierDurability = 756;
        steelTierEfficiency = 7.0F;
        steelTierDamage = 3.0F;
        steelTierEnchantability = 15;
        steelWeaponSpeedModifier = -0.2;

        diarkriteTierHarvestLevel = 4;
        diarkriteTierDurability = 2546;
        diarkriteTierEfficiency = 7.0F;
        diarkriteTierDamage = 6.0F;
        diarkriteTierEnchantability = 15;
        diarkriteWeaponSpeedModifier = -0.3;
        diarkriteAdditionalEfficiency = 10F;

        anthektiteTierHarvestLevel = 4;
        anthektiteTierDurability = 1946;
        anthektiteTierEfficiency = 12.0F;
        anthektiteTierDamage = 3.0F;
        anthektiteTierEnchantability = 15;
        anthektiteWeaponSpeedModifier = 0.5;

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

        //Cracker's Witherstormmod
        steelCMDTierHarvestLevel = 5;
        steelCMDTierDurability = 0;
        steelCMDTierEfficiency = 12.0F;
        steelCMDTierDamage = 8.5F;
        steelCMDTierEnchantability = 20;

        diarkriteCMDTierHarvestLevel = 5;
        diarkriteCMDTierDurability = 0;
        diarkriteCMDTierEfficiency = 14.0F;
        diarkriteCMDTierDamage = 8.0F;
        diarkriteCMDTierEnchantability = 30;

        anthektiteCMDTierHarvestLevel = 5;
        anthektiteCMDTierDurability = 0;
        anthektiteCMDTierEfficiency = 32.0F;
        anthektiteCMDTierDamage = 2.0F;
        anthektiteCMDTierEnchantability = 30;

        //Banilla CLaws
        steelClawTierHarvestLevel = 0;
        steelClawTierDurability = 421;
        steelClawTierEfficiency = 7.0F;
        steelClawTierDamage = 2.0F;
        steelClawTierEnchantability = 1;

        diarkriteClawTierHarvestLevel = 0;
        diarkriteClawTierDurability = 1418;
        diarkriteClawTierEfficiency = 7.0F;
        diarkriteClawTierDamage = 5.0F;
        diarkriteClawTierEnchantability = 1;

        anthektiteClawTierHarvestLevel = 0;
        anthektiteClawTierDurability = 968;
        anthektiteClawTierEfficiency = 12.0F;
        anthektiteClawTierDamage = 2.0F;
        anthektiteClawTierEnchantability = 1;
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(Elementus.TIER_CONFIG_PATH)) {
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

                String errorPrefix = Elementus.TIER_CONFIG_PATH + ": line " + lineNumber + ": ";
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
                        case "steel_HarvestLevel": steelTierHarvestLevel = (int) value; break;
                        case "steel_Durability": steelTierDurability = (int) value; break;
                        case "steel_Efficiency": steelTierEfficiency = (float) value; break;
                        case "steel_Damage": steelTierDamage = (float) value; break;
                        case "steel_Enchantability": steelTierEnchantability = (int) value; break;
                        case "steel_WeaponSpeedModifier": steelWeaponSpeedModifier = value; break;

                        case "diarkrite_HarvestLevel": diarkriteTierHarvestLevel = (int) value; break;
                        case "diarkrite_Durability": diarkriteTierDurability = (int) value; break;
                        case "diarkrite_Efficiency": diarkriteTierEfficiency = (float) value; break;
                        case "diarkrite_Damage": diarkriteTierDamage = (float) value; break;
                        case "diarkrite_Enchantability": diarkriteTierEnchantability = (int) value; break;
                        case "diarkrite_WeaponSpeedModifier": diarkriteWeaponSpeedModifier = value; break;
                        case "diarkrite_AdditionalEfficiency": diarkriteAdditionalEfficiency = (float) value; break;

                        case "anthektite_HarvestLevel": anthektiteTierHarvestLevel = (int) value; break;
                        case "anthektite_Durability": anthektiteTierDurability = (int) value; break;
                        case "anthektite_Efficiency": anthektiteTierEfficiency = (float) value; break;
                        case "anthektite_Damage": anthektiteTierDamage = (float) value; break;
                        case "anthektite_Enchantability": anthektiteTierEnchantability = (int) value; break;
                        case "anthektite_WeaponSpeedModifier": anthektiteWeaponSpeedModifier = value; break;

                        //AdvancedNetherite Tiers Anthektite
                        case "diarkriteIron_HarvestLevel": diarkriteIronTierHarvestLevel = (int) value; break;
                        case "diarkriteIron_Durability": diarkriteIronTierDurability = (int) value; break;
                        case "diarkriteIron_Efficiency": diarkriteIronTierEfficiency = (float) value; break;
                        case "diarkriteIron_Damage": diarkriteIronTierDamage = (float) value; break;
                        case "diarkriteIron_Enchantability": diarkriteIronTierEnchantability = (int) value; break;

                        case "diarkriteGold_HarvestLevel": diarkriteGoldTierHarvestLevel = (int) value; break;
                        case "diarkriteGold_Durability": diarkriteGoldTierDurability = (int) value; break;
                        case "diarkriteGold_Efficiency": diarkriteGoldTierEfficiency = (float) value; break;
                        case "diarkriteGold_Damage": diarkriteGoldTierDamage = (float) value; break;
                        case "diarkriteGold_Enchantability": diarkriteGoldTierEnchantability = (int) value; break;

                        case "diarkriteEmerald_HarvestLevel": diarkriteEmeraldTierHarvestLevel = (int) value; break;
                        case "diarkriteEmerald_Durability": diarkriteEmeraldTierDurability = (int) value; break;
                        case "diarkriteEmerald_Efficiency": diarkriteEmeraldTierEfficiency = (float) value; break;
                        case "diarkriteEmerald_Damage": diarkriteEmeraldTierDamage = (float) value; break;
                        case "diarkriteEmerald_Enchantability": diarkriteEmeraldTierEnchantability = (int) value; break;

                        case "diarkriteDiamond_HarvestLevel": diarkriteDiamondTierHarvestLevel = (int) value; break;
                        case "diarkriteDiamond_Durability": diarkriteDiamondTierDurability = (int) value; break;
                        case "diarkriteDiamond_Efficiency": diarkriteDiamondTierEfficiency = (float) value; break;
                        case "diarkriteDiamond_Damage": diarkriteDiamondTierDamage = (float) value; break;
                        case "diarkriteDiamond_Enchantability": diarkriteDiamondTierEnchantability = (int) value; break;

                        case "anthektiteIron_HarvestLevel": anthektiteIronTierHarvestLevel = (int) value; break;
                        case "anthektiteIron_Durability": anthektiteIronTierDurability = (int) value; break;
                        case "anthektiteIron_Efficiency": anthektiteIronTierEfficiency = (float) value; break;
                        case "anthektiteIron_Damage": anthektiteIronTierDamage = (float) value; break;
                        case "anthektiteIron_Enchantability": anthektiteIronTierEnchantability = (int) value; break;

                        case "anthektiteGold_HarvestLevel": anthektiteGoldTierHarvestLevel = (int) value; break;
                        case "anthektiteGold_Durability": anthektiteGoldTierDurability = (int) value; break;
                        case "anthektiteGold_Efficiency": anthektiteGoldTierEfficiency = (float) value; break;
                        case "anthektiteGold_Damage": anthektiteGoldTierDamage = (float) value; break;
                        case "anthektiteGold_Enchantability": anthektiteGoldTierEnchantability = (int) value; break;

                        case "anthektiteEmerald_HarvestLevel": anthektiteEmeraldTierHarvestLevel = (int) value; break;
                        case "anthektiteEmerald_Durability": anthektiteEmeraldTierDurability = (int) value; break;
                        case "anthektiteEmerald_Efficiency": anthektiteEmeraldTierEfficiency = (float) value; break;
                        case "anthektiteEmerald_Damage": anthektiteEmeraldTierDamage = (float) value; break;
                        case "anthektiteEmerald_Enchantability": anthektiteEmeraldTierEnchantability = (int) value; break;

                        case "anthektiteDiamond_HarvestLevel": anthektiteDiamondTierHarvestLevel = (int) value; break;
                        case "anthektiteDiamond_Durability": anthektiteDiamondTierDurability = (int) value; break;
                        case "anthektiteDiamond_Efficiency": anthektiteDiamondTierEfficiency = (float) value; break;
                        case "anthektiteDiamond_Damage": anthektiteDiamondTierDamage = (float) value; break;
                        case "anthektiteDiamond_Enchantability": anthektiteDiamondTierEnchantability = (int) value; break;

                        //Cracker's Witherstormmod
                        case "steelCMD_HarvestLevel": steelCMDTierHarvestLevel = (int) value; break;
                        case "steelCMD_Durability": steelCMDTierDurability = (int) value; break;
                        case "steelCMD_Damage": steelCMDTierDamage = (float) value; break;
                        case "steelCMD_Efficiency": steelCMDTierEfficiency = (float) value; break;
                        case "steelCMD_Enchantability": steelCMDTierEnchantability = (int) value; break;

                        case "diarkriteCMD_HarvestLevel": diarkriteCMDTierHarvestLevel = (int) value; break;
                        case "diarkriteCMD_Durability": diarkriteCMDTierDurability = (int) value; break;
                        case "diarkriteCMD_Damage": diarkriteCMDTierDamage = (float) value; break;
                        case "diarkriteCMD_Efficiency": diarkriteCMDTierEfficiency = (float) value; break;
                        case "diarkriteCMD_Enchantability": diarkriteCMDTierEnchantability = (int) value; break;

                        case "anthektiteCMD_HarvestLevel": anthektiteCMDTierHarvestLevel = (int) value; break;
                        case "anthektiteCMD_Durability": anthektiteCMDTierDurability = (int) value; break;
                        case "anthektiteCMD_Damage": anthektiteCMDTierDamage = (float) value; break;
                        case "anthektiteCMD_Efficiency": anthektiteCMDTierEfficiency = (float) value; break;
                        case "anthektiteCMD_Enchantability": anthektiteCMDTierEnchantability = (int) value; break;

                        //Banilla Claws
                        case "steelClaw_HarvestLevel": steelClawTierHarvestLevel = (int) value; break;
                        case "steelClaw_Durability": steelClawTierDurability = (int) value; break;
                        case "steelClaw_Damage": steelClawTierDamage = (float) value; break;
                        case "steelClaw_Efficiency": steelClawTierEfficiency = (float) value; break;
                        case "steelClaw_Enchantability": steelClawTierEnchantability = (int) value; break;

                        case "diarkriteClaw_HarvestLevel": diarkriteClawTierHarvestLevel = (int) value; break;
                        case "diarkriteClaw_Durability": diarkriteClawTierDurability = (int) value; break;
                        case "diarkriteClaw_Damage": diarkriteClawTierDamage = (float) value; break;
                        case "diarkriteClaw_Efficiency": diarkriteClawTierEfficiency = (float) value; break;
                        case "diarkriteClaw_Enchantability": diarkriteClawTierEnchantability = (int) value; break;

                        case "anthektiteClaw_HarvestLevel": anthektiteClawTierHarvestLevel = (int) value; break;
                        case "anthektiteClaw_Durability": anthektiteClawTierDurability = (int) value; break;
                        case "anthektiteClaw_Damage": anthektiteClawTierDamage = (float) value; break;
                        case "anthektiteClaw_Efficiency": anthektiteClawTierEfficiency = (float) value; break;
                        case "anthektiteClaw_Enchantability": anthektiteClawTierEnchantability = (int) value; break;
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
            logger.info("Config version outdated, Updating config \"elementus_tier_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.TIER_CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Tier Config\n");
            writer.write("# Stat Examples\n");
            writer.write("# Format:\n");
            writer.write("# [Harvest Level, Durability, Damage, Efficiency, Enchantability, WeaponSpeedModifier]\n");
            writer.write("# [Wood      :0,   59,  2, 0, 15]\n");
            writer.write("# [Gold      :0,   32, 12, 0, 22]\n");
            writer.write("# [Stone     :1,  131,  4, 1,  5]\n");
            writer.write("# [Iron      :2,  250,  6, 2, 14]\n");
            writer.write("# [Diamond   :3, 1561,  8, 3, 10]\n");
            writer.write("# [Netherite :4, 2031,  9, 4, 15]\n");
            writer.write("\n");
            writer.write("[Steel]\n");
            writer.write("# [Default: " + steelTierHarvestLevel + "]\n");
            writer.write("  steel_HarvestLevel = " + steelTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelTierDurability + "]\n");
            writer.write("  steel_Durability = " + steelTierDurability + "\n");
            writer.write("# [Default: " + steelTierEfficiency + "]\n");
            writer.write("  steel_Efficiency = " + steelTierEfficiency + "\n");
            writer.write("# [Default: " + steelTierDamage + "]\n");
            writer.write("  steel_Damage = " + steelTierDamage + "\n");
            writer.write("# [Default: " + steelTierEnchantability + "]\n");
            writer.write("  steel_Enchantability = " + steelTierEnchantability + "\n");
            writer.write("# [Default: " + steelWeaponSpeedModifier + "]\n");
            writer.write("  steel_WeaponSpeedModifier = " + steelWeaponSpeedModifier + "\n");
            writer.write("[Diarkrite]\n");
            writer.write("# [Default: " + diarkriteTierHarvestLevel + "]\n");
            writer.write("  diarkrite_HarvestLevel = " + diarkriteTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteTierDurability + "]\n");
            writer.write("  diarkrite_Durability = " + diarkriteTierDurability + "\n");
            writer.write("# [Default: " + diarkriteTierEfficiency + "]\n");
            writer.write("  diarkrite_Efficiency = " + diarkriteTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteTierDamage + "]\n");
            writer.write("  diarkrite_Damage = " + diarkriteTierDamage + "\n");
            writer.write("# [Default: " + diarkriteTierEnchantability + "]\n");
            writer.write("  diarkrite_Enchantability = " + diarkriteTierEnchantability + "\n");
            writer.write("# [Default: " + diarkriteWeaponSpeedModifier + "]\n");
            writer.write("  diarkrite_WeaponSpeedModifier = " + diarkriteWeaponSpeedModifier + "\n");
            writer.write("# [Default: " + diarkriteAdditionalEfficiency + "]\n");
            writer.write("  diarkrite_AdditionalEfficiency = " + diarkriteAdditionalEfficiency + "\n");
            writer.write("[Anthektite]\n");
            writer.write("# [Default: " + anthektiteTierHarvestLevel + "]\n");
            writer.write("  anthektite_HarvestLevel = " + anthektiteTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteTierDurability + "]\n");
            writer.write("  anthektite_Durability = " + anthektiteTierDurability + "\n");
            writer.write("# [Default: " + anthektiteTierEfficiency + "]\n");
            writer.write("  anthektite_Efficiency = " + anthektiteTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteTierDamage + "]\n");
            writer.write("  anthektite_Damage = " + anthektiteTierDamage + "\n");
            writer.write("# [Default: " + anthektiteTierEnchantability + "]\n");
            writer.write("  anthektite_Enchantability = " + anthektiteTierEnchantability + "\n");
            writer.write("# [Default: " + anthektiteWeaponSpeedModifier + "]\n");
            writer.write("  anthektite_WeaponSpeedModifier = " + anthektiteWeaponSpeedModifier + "\n");
            writer.write("\n");
            writer.write("[AdvancedNetherite.DiarkriteIron]\n");
            writer.write("# [Default: " + diarkriteIronTierHarvestLevel + "]\n");
            writer.write("  diarkriteIron_HarvestLevel = " + diarkriteIronTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteIronTierDurability + "]\n");
            writer.write("  diarkriteIron_Durability = " + diarkriteIronTierDurability + "\n");
            writer.write("# [Default: " + diarkriteIronTierEfficiency + "]\n");
            writer.write("  diarkriteIron_Efficiency = " + diarkriteIronTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteIronTierDamage + "]\n");
            writer.write("  diarkriteIron_Damage = " + diarkriteIronTierDamage + "\n");
            writer.write("# [Default: " + diarkriteIronTierEnchantability + "]\n");
            writer.write("  diarkriteIron_Enchantability = " + diarkriteIronTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.DiarkriteGold]\n");
            writer.write("# [Default: " + diarkriteGoldTierHarvestLevel + "]\n");
            writer.write("  diarkriteGold_HarvestLevel = " + diarkriteGoldTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteGoldTierDurability + "]\n");
            writer.write("  diarkriteGold_Durability = " + diarkriteGoldTierDurability + "\n");
            writer.write("# [Default: " + diarkriteGoldTierEfficiency + "]\n");
            writer.write("  diarkriteGold_Efficiency = " + diarkriteGoldTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteGoldTierDamage + "]\n");
            writer.write("  diarkriteGold_Damage = " + diarkriteGoldTierDamage + "\n");
            writer.write("# [Default: " + diarkriteGoldTierEnchantability + "]\n");
            writer.write("  diarkriteGold_Enchantability = " + diarkriteGoldTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.DiarkriteEmerald]\n");
            writer.write("# [Default: " + diarkriteEmeraldTierHarvestLevel + "]\n");
            writer.write("  diarkriteEmerald_HarvestLevel = " + diarkriteEmeraldTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierDurability + "]\n");
            writer.write("  diarkriteEmerald_Durability = " + diarkriteEmeraldTierDurability + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierEfficiency + "]\n");
            writer.write("  diarkriteEmerald_Efficiency = " + diarkriteEmeraldTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierDamage + "]\n");
            writer.write("  diarkriteEmerald_Damage = " + diarkriteEmeraldTierDamage + "\n");
            writer.write("# [Default: " + diarkriteEmeraldTierEnchantability + "]\n");
            writer.write("  diarkriteEmerald_Enchantability = " + diarkriteEmeraldTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.DiarkriteDiamond]\n");
            writer.write("# [Default: " + diarkriteDiamondTierHarvestLevel + "]\n");
            writer.write("  diarkriteDiamond_HarvestLevel = " + diarkriteDiamondTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierDurability + "]\n");
            writer.write("  diarkriteDiamond_Durability = " + diarkriteDiamondTierDurability + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierEfficiency + "]\n");
            writer.write("  diarkriteDiamond_Efficiency = " + diarkriteDiamondTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierDamage + "]\n");
            writer.write("  diarkriteDiamond_Damage = " + diarkriteDiamondTierDamage + "\n");
            writer.write("# [Default: " + diarkriteDiamondTierEnchantability + "]\n");
            writer.write("  diarkriteDiamond_Enchantability = " + diarkriteDiamondTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[AdvancedNetherite.AnthektiteIron]\n");
            writer.write("# [Default: " + anthektiteIronTierHarvestLevel + "]\n");
            writer.write("  anthektiteIron_HarvestLevel = " + anthektiteIronTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteIronTierDurability + "]\n");
            writer.write("  anthektiteIron_Durability = " + anthektiteIronTierDurability + "\n");
            writer.write("# [Default: " + anthektiteIronTierEfficiency + "]\n");
            writer.write("  anthektiteIron_Efficiency = " + anthektiteIronTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteIronTierDamage + "]\n");
            writer.write("  anthektiteIron_Damage = " + anthektiteIronTierDamage + "\n");
            writer.write("# [Default: " + anthektiteIronTierEnchantability + "]\n");
            writer.write("  anthektiteIron_Enchantability = " + anthektiteIronTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.AnthektiteGold]\n");
            writer.write("# [Default: " + anthektiteGoldTierHarvestLevel + "]\n");
            writer.write("  anthektiteGold_HarvestLevel = " + anthektiteGoldTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteGoldTierDurability + "]\n");
            writer.write("  anthektiteGold_Durability = " + anthektiteGoldTierDurability + "\n");
            writer.write("# [Default: " + anthektiteGoldTierEfficiency + "]\n");
            writer.write("  anthektiteGold_Efficiency = " + anthektiteGoldTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteGoldTierDamage + "]\n");
            writer.write("  anthektiteGold_Damage = " + anthektiteGoldTierDamage + "\n");
            writer.write("# [Default: " + anthektiteGoldTierEnchantability + "]\n");
            writer.write("  anthektiteGold_Enchantability = " + anthektiteGoldTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.AnthektiteEmerald]\n");
            writer.write("# [Default: " + anthektiteEmeraldTierHarvestLevel + "]\n");
            writer.write("  anthektiteEmerald_HarvestLevel = " + anthektiteEmeraldTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierDurability + "]\n");
            writer.write("  anthektiteEmerald_Durability = " + anthektiteEmeraldTierDurability + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierEfficiency + "]\n");
            writer.write("  anthektiteEmerald_Efficiency = " + anthektiteEmeraldTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierDamage + "]\n");
            writer.write("  anthektiteEmerald_Damage = " + anthektiteEmeraldTierDamage + "\n");
            writer.write("# [Default: " + anthektiteEmeraldTierEnchantability + "]\n");
            writer.write("  anthektiteEmerald_Enchantability = " + anthektiteEmeraldTierEnchantability + "\n");
            writer.write("[AdvancedNetherite.AnthektiteDiamond]\n");
            writer.write("# [Default: " + anthektiteDiamondTierHarvestLevel + "]\n");
            writer.write("  anthektiteDiamond_HarvestLevel = " + anthektiteDiamondTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierDurability + "]\n");
            writer.write("  anthektiteDiamond_Durability = " + anthektiteDiamondTierDurability + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierEfficiency + "]\n");
            writer.write("  anthektiteDiamond_Efficiency = " + anthektiteDiamondTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierDamage + "]\n");
            writer.write("  anthektiteDiamond_Damage = " + anthektiteDiamondTierDamage + "\n");
            writer.write("# [Default: " + anthektiteDiamondTierEnchantability + "]\n");
            writer.write("  anthektiteDiamond_Enchantability = " + anthektiteDiamondTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[Witherstormmod.SteelCMD]\n");
            writer.write("# [Default: " + steelCMDTierHarvestLevel + "]\n");
            writer.write("  steelCMD_HarvestLevel = " + steelCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelCMDTierDurability + "]\n");
            writer.write("  steelCMD_Durability = " + steelCMDTierDurability + "\n");
            writer.write("# [Default: " + steelCMDTierEfficiency + "]\n");
            writer.write("  steelCMD_Efficiency = " + steelCMDTierEfficiency + "\n");
            writer.write("# [Default: " + steelCMDTierDamage + "]\n");
            writer.write("  steelCMD_Damage = " + steelCMDTierDamage + "\n");
            writer.write("# [Default: " + steelCMDTierEnchantability + "]\n");
            writer.write("  steelCMD_Enchantability = " + steelCMDTierEnchantability + "\n");
            writer.write("[Witherstormmod.DiarkriteCMD]\n");
            writer.write("# [Default: " + diarkriteCMDTierHarvestLevel + "]\n");
            writer.write("  diarkriteCMD_HarvestLevel = " + diarkriteCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteCMDTierDurability + "]\n");
            writer.write("  diarkriteCMD_Durability = " + diarkriteCMDTierDurability + "\n");
            writer.write("# [Default: " + diarkriteCMDTierEfficiency + "]\n");
            writer.write("  diarkriteCMD_Efficiency = " + diarkriteCMDTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteCMDTierDamage + "]\n");
            writer.write("  diarkriteCMD_Damage = " + diarkriteCMDTierDamage + "\n");
            writer.write("# [Default: " + diarkriteCMDTierEnchantability + "]\n");
            writer.write("  diarkriteCMD_Enchantability = " + diarkriteCMDTierEnchantability + "\n");
            writer.write("[Witherstormmod.AnthektiteCMD]\n");
            writer.write("# [Default: " + anthektiteCMDTierHarvestLevel + "]\n");
            writer.write("  anthektiteCMD_HarvestLevel = " + anthektiteCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteCMDTierDurability + "]\n");
            writer.write("  anthektiteCMD_Durability = " + anthektiteCMDTierDurability + "\n");
            writer.write("# [Default: " + anthektiteCMDTierEfficiency + "]\n");
            writer.write("  anthektiteCMD_Efficiency = " + anthektiteCMDTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteCMDTierDamage + "]\n");
            writer.write("  anthektiteCMD_Damage = " + anthektiteCMDTierDamage + "\n");
            writer.write("# [Default: " + anthektiteCMDTierEnchantability + "]\n");
            writer.write("  anthektiteCMD_Enchantability = " + anthektiteCMDTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[BanillaClaws.Steel]\n");
            writer.write("# [Default: " + steelClawTierHarvestLevel + "]\n");
            writer.write("  steelClaw_HarvestLevel = " + steelClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelClawTierDurability + "]\n");
            writer.write("  steelClaw_Durability = " + steelClawTierDurability + "\n");
            writer.write("# [Default: " + steelClawTierEfficiency + "]\n");
            writer.write("  steelClaw_Efficiency = " + steelClawTierEfficiency + "\n");
            writer.write("# [Default: " + steelClawTierDamage + "]\n");
            writer.write("  steelClaw_Damage = " + steelClawTierDamage + "\n");
            writer.write("# [Default: " + steelClawTierEnchantability + "]\n");
            writer.write("  steelClaw_Enchantability = " + steelClawTierEnchantability + "\n");
            writer.write("[BanillaClaws.DiarkriteClaw]\n");
            writer.write("# [Default: " + diarkriteClawTierHarvestLevel + "]\n");
            writer.write("  diarkriteClaw_HarvestLevel = " + diarkriteClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteClawTierDurability + "]\n");
            writer.write("  diarkriteClaw_Durability = " + diarkriteClawTierDurability + "\n");
            writer.write("# [Default: " + diarkriteClawTierEfficiency + "]\n");
            writer.write("  diarkriteClaw_Efficiency = " + diarkriteClawTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteClawTierDamage + "]\n");
            writer.write("  diarkriteClaw_Damage = " + diarkriteClawTierDamage + "\n");
            writer.write("# [Default: " + diarkriteClawTierEnchantability + "]\n");
            writer.write("  diarkriteClaw_Enchantability = " + diarkriteClawTierEnchantability + "\n");
            writer.write("[BanillaClaws.AnthektiteClaw]\n");
            writer.write("# [Default: " + anthektiteClawTierHarvestLevel + "]\n");
            writer.write("  anthektiteClaw_HarvestLevel = " + anthektiteClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteClawTierDurability + "]\n");
            writer.write("  anthektiteClaw_Durability = " + anthektiteClawTierDurability + "\n");
            writer.write("# [Default: " + anthektiteClawTierEfficiency + "]\n");
            writer.write("  anthektiteClaw_Efficiency = " + anthektiteClawTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteClawTierDamage + "]\n");
            writer.write("  anthektiteClaw_Damage = " + anthektiteClawTierDamage + "\n");
            writer.write("# [Default: " + anthektiteClawTierEnchantability + "]\n");
            writer.write("  anthektiteClaw_Enchantability = " + anthektiteClawTierEnchantability + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
