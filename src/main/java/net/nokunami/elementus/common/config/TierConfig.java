package net.nokunami.elementus.common.config;

import net.nokunami.elementus.Elementus;
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

import static net.nokunami.elementus.Elementus.CONFIG_VERSION;

/**
 * credits: SkpC9 <a href="https://github.com/SkpC9/Simply-Steel/blob/main/src/main/java/com/trbz_/simplysteel/util/ConfigHandler.java">Link</a>
 * */
public class TierConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final TierConfig INSTANCE = new TierConfig();
    private static final Path CONFIG_PATH = Elementus.TIER_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Tiers
    public static int steelTierHarvestLevel;
    public static int steelTierDurability;
    public static double steelTierEfficiency;
    public static double steelTierDamage;
    public static int steelTierEnchantability;
    public static double steelWeaponSpeedModifier;

    public static int diarkriteTierHarvestLevel;
    public static int diarkriteTierDurability;
    public static double diarkriteTierEfficiency;
    public static double diarkriteTierDamage;
    public static int diarkriteTierEnchantability;
    public static double diarkriteWeaponSpeedModifier;
    public static double diarkriteAdditionalEfficiency;

    public static int anthektiteTierHarvestLevel;
    public static int anthektiteTierDurability;
    public static double anthektiteTierEfficiency;
    public static double anthektiteTierDamage;
    public static int anthektiteTierEnchantability;
    public static double anthektiteWeaponSpeedModifier;

    //Cracker's Witherstormmod
    public static int steelCMDTierHarvestLevel;
    public static int steelCMDTierDurability;
    public static double steelCMDTierDamage;
    public static double steelCMDTierEfficiency;
    public static int steelCMDTierEnchantability;

    public static int diarkriteCMDTierHarvestLevel;
    public static int diarkriteCMDTierDurability;
    public static double diarkriteCMDTierDamage;
    public static double diarkriteCMDTierEfficiency;
    public static int diarkriteCMDTierEnchantability;

    public static int anthektiteCMDTierHarvestLevel;
    public static int anthektiteCMDTierDurability;
    public static double anthektiteCMDTierDamage;
    public static double anthektiteCMDTierEfficiency;
    public static int anthektiteCMDTierEnchantability;

    //Banilla Claws
    public static int steelClawTierHarvestLevel;
    public static int steelClawTierDurability;
    public static double steelClawTierDamage;
    public static double steelClawTierEfficiency;
    public static int steelClawTierEnchantability;

    public static int diarkriteClawTierHarvestLevel;
    public static int diarkriteClawTierDurability;
    public static double diarkriteClawTierDamage;
    public static double diarkriteClawTierEfficiency;
    public static int diarkriteClawTierEnchantability;

    public static int anthektiteClawTierHarvestLevel;
    public static int anthektiteClawTierDurability;
    public static double anthektiteClawTierDamage;
    public static double anthektiteClawTierEfficiency;
    public static int anthektiteClawTierEnchantability;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Tier Config loaded");
    }

    private void setDefaults() {
        steelTierHarvestLevel = 3;
        steelTierDurability = 756;
        steelTierEfficiency = 7.0;
        steelTierDamage = 3.0;
        steelTierEnchantability = 15;
        steelWeaponSpeedModifier = -0.2;

        diarkriteTierHarvestLevel = 4;
        diarkriteTierDurability = 2546;
        diarkriteTierEfficiency = 7.0;
        diarkriteTierDamage = 6.0;
        diarkriteTierEnchantability = 15;
        diarkriteWeaponSpeedModifier = -0.3;
        diarkriteAdditionalEfficiency = 10;

        anthektiteTierHarvestLevel = 4;
        anthektiteTierDurability = 1946;
        anthektiteTierEfficiency = 12.0;
        anthektiteTierDamage = 3.0;
        anthektiteTierEnchantability = 15;
        anthektiteWeaponSpeedModifier = 0.5;

        //Cracker's Witherstormmod
        steelCMDTierHarvestLevel = 5;
        steelCMDTierDurability = 0;
        steelCMDTierEfficiency = 12.0;
        steelCMDTierDamage = 8.5;
        steelCMDTierEnchantability = 20;

        diarkriteCMDTierHarvestLevel = 5;
        diarkriteCMDTierDurability = 0;
        diarkriteCMDTierEfficiency = 14.0;
        diarkriteCMDTierDamage = 8.0;
        diarkriteCMDTierEnchantability = 30;

        anthektiteCMDTierHarvestLevel = 5;
        anthektiteCMDTierDurability = 0;
        anthektiteCMDTierEfficiency = 32.0;
        anthektiteCMDTierDamage = 2.0;
        anthektiteCMDTierEnchantability = 30;

        //Banilla CLaws
        steelClawTierHarvestLevel = 0;
        steelClawTierDurability = 421;
        steelClawTierEfficiency = 7.0;
        steelClawTierDamage = 2.0;
        steelClawTierEnchantability = 1;

        diarkriteClawTierHarvestLevel = 0;
        diarkriteClawTierDurability = 1418;
        diarkriteClawTierEfficiency = 7.0;
        diarkriteClawTierDamage = 5.0;
        diarkriteClawTierEnchantability = 1;

        anthektiteClawTierHarvestLevel = 0;
        anthektiteClawTierDurability = 968;
        anthektiteClawTierEfficiency = 12.0;
        anthektiteClawTierDamage = 2.0;
        anthektiteClawTierEnchantability = 1;
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
                        case "steel.HarvestLevel": steelTierHarvestLevel = (int) value; break;
                        case "steel.Durability": steelTierDurability = (int) value; break;
                        case "steel.Efficiency": steelTierEfficiency = value; break;
                        case "steel.Damage": steelTierDamage = value; break;
                        case "steel.Enchantability": steelTierEnchantability = (int) value; break;
                        case "steel.WeaponSpeedModifier": steelWeaponSpeedModifier = value; break;

                        case "diarkrite.HarvestLevel": diarkriteTierHarvestLevel = (int) value; break;
                        case "diarkrite.Durability": diarkriteTierDurability = (int) value; break;
                        case "diarkrite.Efficiency": diarkriteTierEfficiency = value; break;
                        case "diarkrite.Damage": diarkriteTierDamage = value; break;
                        case "diarkrite.Enchantability": diarkriteTierEnchantability = (int) value; break;
                        case "diarkrite.WeaponSpeedModifier": diarkriteWeaponSpeedModifier = value; break;
                        case "diarkrite.AdditionalEfficiency": diarkriteAdditionalEfficiency = value; break;

                        case "anthektite.HarvestLevel": anthektiteTierHarvestLevel = (int) value; break;
                        case "anthektite.Durability": anthektiteTierDurability = (int) value; break;
                        case "anthektite.Efficiency": anthektiteTierEfficiency = value; break;
                        case "anthektite.Damage": anthektiteTierDamage = value; break;
                        case "anthektite.Enchantability": anthektiteTierEnchantability = (int) value; break;
                        case "anthektite.WeaponSpeedModifier": anthektiteWeaponSpeedModifier = value; break;

                        //Cracker's Witherstormmod
                        case "steelCMD.HarvestLevel": steelCMDTierHarvestLevel = (int) value; break;
                        case "steelCMD.Durability": steelCMDTierDurability = (int) value; break;
                        case "steelCMD.Damage": steelCMDTierDamage = value; break;
                        case "steelCMD.Efficiency": steelCMDTierEfficiency = value; break;
                        case "steelCMD.Enchantability": steelCMDTierEnchantability = (int) value; break;

                        case "diarkriteCMD.HarvestLevel": diarkriteCMDTierHarvestLevel = (int) value; break;
                        case "diarkriteCMD.Durability": diarkriteCMDTierDurability = (int) value; break;
                        case "diarkriteCMD.Damage": diarkriteCMDTierDamage = value; break;
                        case "diarkriteCMD.Efficiency": diarkriteCMDTierEfficiency = value; break;
                        case "diarkriteCMD.Enchantability": diarkriteCMDTierEnchantability = (int) value; break;

                        case "anthektiteCMD.HarvestLevel": anthektiteCMDTierHarvestLevel = (int) value; break;
                        case "anthektiteCMD.Durability": anthektiteCMDTierDurability = (int) value; break;
                        case "anthektiteCMD.Damage": anthektiteCMDTierDamage = value; break;
                        case "anthektiteCMD.Efficiency": anthektiteCMDTierEfficiency = value; break;
                        case "anthektiteCMD.Enchantability": anthektiteCMDTierEnchantability = (int) value; break;

                        //Banilla Claws
                        case "steelClaw.HarvestLevel": steelClawTierHarvestLevel = (int) value; break;
                        case "steelClaw.Durability": steelClawTierDurability = (int) value; break;
                        case "steelClaw.Damage": steelClawTierDamage = value; break;
                        case "steelClaw.Efficiency": steelClawTierEfficiency = value; break;
                        case "steelClaw.Enchantability": steelClawTierEnchantability = (int) value; break;

                        case "diarkriteClaw.HarvestLevel": diarkriteClawTierHarvestLevel = (int) value; break;
                        case "diarkriteClaw.Durability": diarkriteClawTierDurability = (int) value; break;
                        case "diarkriteClaw.Damage": diarkriteClawTierDamage = value; break;
                        case "diarkriteClaw.Efficiency": diarkriteClawTierEfficiency = value; break;
                        case "diarkriteClaw.Enchantability": diarkriteClawTierEnchantability = (int) value; break;

                        case "anthektiteClaw.HarvestLevel": anthektiteClawTierHarvestLevel = (int) value; break;
                        case "anthektiteClaw.Durability": anthektiteClawTierDurability = (int) value; break;
                        case "anthektiteClaw.Damage": anthektiteClawTierDamage = value; break;
                        case "anthektiteClaw.Efficiency": anthektiteClawTierEfficiency = value; break;
                        case "anthektiteClaw.Enchantability": anthektiteClawTierEnchantability = (int) value; break;
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
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Tier Config\n");
            writer.write("# Stats Examples\n");
            writer.write("# [Material |Harvest Level |Durability |Damage |Efficiency |Enchantability]\n");
            writer.write("# [Wood     |0             |59         |2      |0          |15            ]\n");
            writer.write("# [Gold     |0             |32         |12     |0          |22            ]\n");
            writer.write("# [Stone    |1             |131        |4      |1          |5             ]\n");
            writer.write("# [Iron     |2             |250        |6      |2          |14            ]\n");
            writer.write("# [Diamond  |3             |1561       |8      |3          |10            ]\n");
            writer.write("# [Netherite|4             |2031       |9      |4          |15            ]\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[Steel]\n");
            writer.write("# [Default: " + steelTierHarvestLevel + "]\n");
            writer.write("  steel.HarvestLevel = " + steelTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelTierDurability + "]\n");
            writer.write("  steel.Durability = " + steelTierDurability + "\n");
            writer.write("# [Default: " + steelTierEfficiency + "]\n");
            writer.write("  steel.Efficiency = " + steelTierEfficiency + "\n");
            writer.write("# [Default: " + steelTierDamage + "]\n");
            writer.write("  steel.Damage = " + steelTierDamage + "\n");
            writer.write("# [Default: " + steelTierEnchantability + "]\n");
            writer.write("  steel.Enchantability = " + steelTierEnchantability + "\n");
            writer.write("# [Default: " + steelWeaponSpeedModifier + "]\n");
            writer.write("  steel.WeaponSpeedModifier = " + steelWeaponSpeedModifier + "\n");
            writer.write("\n");
            writer.write("[Diarkrite]\n");
            writer.write("# [Default: " + diarkriteTierHarvestLevel + "]\n");
            writer.write("  diarkrite.HarvestLevel = " + diarkriteTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteTierDurability + "]\n");
            writer.write("  diarkrite.Durability = " + diarkriteTierDurability + "\n");
            writer.write("# [Default: " + diarkriteTierEfficiency + "]\n");
            writer.write("  diarkrite.Efficiency = " + diarkriteTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteTierDamage + "]\n");
            writer.write("  diarkrite.Damage = " + diarkriteTierDamage + "\n");
            writer.write("# [Default: " + diarkriteTierEnchantability + "]\n");
            writer.write("  diarkrite.Enchantability = " + diarkriteTierEnchantability + "\n");
            writer.write("# [Default: " + diarkriteWeaponSpeedModifier + "]\n");
            writer.write("  diarkrite.WeaponSpeedModifier = " + diarkriteWeaponSpeedModifier + "\n");
            writer.write("# [Default: " + diarkriteAdditionalEfficiency + "]\n");
            writer.write("  diarkrite.AdditionalEfficiency = " + diarkriteAdditionalEfficiency + "\n");
            writer.write("\n");
            writer.write("[Anthektite]\n");
            writer.write("# [Default: " + anthektiteTierHarvestLevel + "]\n");
            writer.write("  anthektite.HarvestLevel = " + anthektiteTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteTierDurability + "]\n");
            writer.write("  anthektite.Durability = " + anthektiteTierDurability + "\n");
            writer.write("# [Default: " + anthektiteTierEfficiency + "]\n");
            writer.write("  anthektite.Efficiency = " + anthektiteTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteTierDamage + "]\n");
            writer.write("  anthektite.Damage = " + anthektiteTierDamage + "\n");
            writer.write("# [Default: " + anthektiteTierEnchantability + "]\n");
            writer.write("  anthektite.Enchantability = " + anthektiteTierEnchantability + "\n");
            writer.write("# [Default: " + anthektiteWeaponSpeedModifier + "]\n");
            writer.write("  anthektite.WeaponSpeedModifier = " + anthektiteWeaponSpeedModifier + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[Witherstormmod.SteelCMD]\n");
            writer.write("# [Default: " + steelCMDTierHarvestLevel + "]\n");
            writer.write("  steelCMD.HarvestLevel = " + steelCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelCMDTierDurability + "]\n");
            writer.write("  steelCMD.Durability = " + steelCMDTierDurability + "\n");
            writer.write("# [Default: " + steelCMDTierEfficiency + "]\n");
            writer.write("  steelCMD.Efficiency = " + steelCMDTierEfficiency + "\n");
            writer.write("# [Default: " + steelCMDTierDamage + "]\n");
            writer.write("  steelCMD.Damage = " + steelCMDTierDamage + "\n");
            writer.write("# [Default: " + steelCMDTierEnchantability + "]\n");
            writer.write("  steelCMD.Enchantability = " + steelCMDTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[Witherstormmod.DiarkriteCMD]\n");
            writer.write("# [Default: " + diarkriteCMDTierHarvestLevel + "]\n");
            writer.write("  diarkriteCMD.HarvestLevel = " + diarkriteCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteCMDTierDurability + "]\n");
            writer.write("  diarkriteCMD.Durability = " + diarkriteCMDTierDurability + "\n");
            writer.write("# [Default: " + diarkriteCMDTierEfficiency + "]\n");
            writer.write("  diarkriteCMD.Efficiency = " + diarkriteCMDTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteCMDTierDamage + "]\n");
            writer.write("  diarkriteCMD.Damage = " + diarkriteCMDTierDamage + "\n");
            writer.write("# [Default: " + diarkriteCMDTierEnchantability + "]\n");
            writer.write("  diarkriteCMD.Enchantability = " + diarkriteCMDTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[Witherstormmod.AnthektiteCMD]\n");
            writer.write("# [Default: " + anthektiteCMDTierHarvestLevel + "]\n");
            writer.write("  anthektiteCMD.HarvestLevel = " + anthektiteCMDTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteCMDTierDurability + "]\n");
            writer.write("  anthektiteCMD.Durability = " + anthektiteCMDTierDurability + "\n");
            writer.write("# [Default: " + anthektiteCMDTierEfficiency + "]\n");
            writer.write("  anthektiteCMD.Efficiency = " + anthektiteCMDTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteCMDTierDamage + "]\n");
            writer.write("  anthektiteCMD.Damage = " + anthektiteCMDTierDamage + "\n");
            writer.write("# [Default: " + anthektiteCMDTierEnchantability + "]\n");
            writer.write("  anthektiteCMD.Enchantability = " + anthektiteCMDTierEnchantability + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[BanillaClaws.Steel]\n");
            writer.write("# [Default: " + steelClawTierHarvestLevel + "]\n");
            writer.write("  steelClaw.HarvestLevel = " + steelClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + steelClawTierDurability + "]\n");
            writer.write("  steelClaw.Durability = " + steelClawTierDurability + "\n");
            writer.write("# [Default: " + steelClawTierEfficiency + "]\n");
            writer.write("  steelClaw.Efficiency = " + steelClawTierEfficiency + "\n");
            writer.write("# [Default: " + steelClawTierDamage + "]\n");
            writer.write("  steelClaw.Damage = " + steelClawTierDamage + "\n");
            writer.write("# [Default: " + steelClawTierEnchantability + "]\n");
            writer.write("  steelClaw.Enchantability = " + steelClawTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[BanillaClaws.DiarkriteClaw]\n");
            writer.write("# [Default: " + diarkriteClawTierHarvestLevel + "]\n");
            writer.write("  diarkriteClaw.HarvestLevel = " + diarkriteClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + diarkriteClawTierDurability + "]\n");
            writer.write("  diarkriteClaw.Durability = " + diarkriteClawTierDurability + "\n");
            writer.write("# [Default: " + diarkriteClawTierEfficiency + "]\n");
            writer.write("  diarkriteClaw.Efficiency = " + diarkriteClawTierEfficiency + "\n");
            writer.write("# [Default: " + diarkriteClawTierDamage + "]\n");
            writer.write("  diarkriteClaw.Damage = " + diarkriteClawTierDamage + "\n");
            writer.write("# [Default: " + diarkriteClawTierEnchantability + "]\n");
            writer.write("  diarkriteClaw.Enchantability = " + diarkriteClawTierEnchantability + "\n");
            writer.write("\n");
            writer.write("[BanillaClaws.AnthektiteClaw]\n");
            writer.write("# [Default: " + anthektiteClawTierHarvestLevel + "]\n");
            writer.write("  anthektiteClaw.HarvestLevel = " + anthektiteClawTierHarvestLevel + "\n");
            writer.write("# [Default: " + anthektiteClawTierDurability + "]\n");
            writer.write("  anthektiteClaw.Durability = " + anthektiteClawTierDurability + "\n");
            writer.write("# [Default: " + anthektiteClawTierEfficiency + "]\n");
            writer.write("  anthektiteClaw.Efficiency = " + anthektiteClawTierEfficiency + "\n");
            writer.write("# [Default: " + anthektiteClawTierDamage + "]\n");
            writer.write("  anthektiteClaw.Damage = " + anthektiteClawTierDamage + "\n");
            writer.write("# [Default: " + anthektiteClawTierEnchantability + "]\n");
            writer.write("  anthektiteClaw.Enchantability = " + anthektiteClawTierEnchantability + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
