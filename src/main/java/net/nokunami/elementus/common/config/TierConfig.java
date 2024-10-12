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
public class TierConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final TierConfig INSTANCE = new TierConfig();
    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion("1.1");
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
        steelWeaponSpeedModifier = -0.2;

        diarkriteTierHarvestLevel = 4;
        diarkriteTierDurability = 2546;
        diarkriteTierEfficiency = 9.0F;
        diarkriteTierDamage = 6.0F;
        diarkriteTierEnchantability = 15;
        diarkriteWeaponSpeedModifier = -0.3;

        anthektiteTierHarvestLevel = 4;
        anthektiteTierDurability = 1946;
        anthektiteTierEfficiency = 12.0F;
        anthektiteTierDamage = 3.0F;
        anthektiteTierEnchantability = 15;
        anthektiteWeaponSpeedModifier = 0.5;

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
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(Elementus.MATERIAL_STATS_CONFIG_PATH)) {
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

                String errorPrefix = Elementus.MATERIAL_STATS_CONFIG_PATH + ": line " + lineNumber + ": ";
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
                        case "steelTier_WeaponSpeedModifier": steelWeaponSpeedModifier = (float) value; break;

                        case "diarkriteTier_HarvestLevel": diarkriteTierHarvestLevel = (int) value; break;
                        case "diarkriteTier_Durability": diarkriteTierDurability = (int) value; break;
                        case "diarkriteTier_Efficiency": diarkriteTierEfficiency = (float) value; break;
                        case "diarkriteTier_Damage": diarkriteTierDamage = (float) value; break;
                        case "diarkriteTier_Enchantability": diarkriteTierEnchantability = (int) value; break;
                        case "diarkriteTier_WeaponSpeedModifier": diarkriteWeaponSpeedModifier = (float) value; break;

                        case "anthektiteTier_HarvestLevel": anthektiteTierHarvestLevel = (int) value; break;
                        case "anthektiteTier_Durability": anthektiteTierDurability = (int) value; break;
                        case "anthektiteTier_Efficiency": anthektiteTierEfficiency = (float) value; break;
                        case "anthektiteTier_Damage": anthektiteTierDamage = (float) value; break;
                        case "anthektiteTier_Enchantability": anthektiteTierEnchantability = (int) value; break;
                        case "anthektiteTier_WeaponSpeedModifier": anthektiteWeaponSpeedModifier = (float) value; break;


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
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.MATERIAL_STATS_CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config");
            writer.newLine();
            writer.newLine();
            writer.write("[ToolTiers]\n");
            writer.write("  [Stat_Examples]\n");
            writer.write("  # Format:\n");
            writer.write("  # [Harvest Level, Durability, Damage, Efficiency, Enchantability, WeaponSpeedModifier]\n");
            writer.write("    # [Wood      :0,   59,  2, 0, 15]\n");
            writer.write("    # [Gold      :0,   32, 12, 0, 22]\n");
            writer.write("    # [Stone     :1,  131,  4, 1,  5]\n");
            writer.write("    # [Iron      :2,  250,  6, 2, 14]\n");
            writer.write("    # [Diamond   :3, 1561,  8, 3, 10]\n");
            writer.write("    # [Netherite :4, 2031,  9, 4, 15]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default Values: 3, 756, 3.0, 7.0, 15, -0.2\n");
            writer.write("    steelTier_Harvestlevel = " + steelTierHarvestLevel + "\n");
            writer.write("    steelTier_Durability = " + steelTierDurability + "\n");
            writer.write("    steelTier_Damage = " + steelTierDamage + "\n");
            writer.write("    steelTier_Efficiency = " + steelTierEfficiency + "\n");
            writer.write("    steelTier_Enchantability = " + steelTierEnchantability + "\n");
            writer.write("    steelTier_WeaponSpeedModifier = " + steelWeaponSpeedModifier + "\n");
            writer.newLine();
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default Values: 4, 2546, 6.0, 9.0, 15, -0.3\n");
            writer.write("    diarkriteTierHarvest_level = " + diarkriteTierHarvestLevel + "\n");
            writer.write("    diarkriteTierDurability = " + diarkriteTierDurability + "\n");
            writer.write("    diarkriteTierDamage = " + diarkriteTierDamage + "\n");
            writer.write("    diarkriteTierEfficiency = " + diarkriteTierEfficiency + "\n");
            writer.write("    diarkriteTierEnchantability = " + diarkriteTierEnchantability + "\n");
            writer.write("    diarkriteTier_WeaponSpeedModifier = " + diarkriteWeaponSpeedModifier + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    # Default Values: 4, 1946, 3.0, 12.0, 15, 0.5\n");
            writer.write("    anthektiteTierHarvest_level = " + anthektiteTierHarvestLevel + "\n");
            writer.write("    anthektiteTierDurability = " + anthektiteTierDurability + "\n");
            writer.write("    anthektiteTierDamage = " + anthektiteTierDamage + "\n");
            writer.write("    anthektiteTierEfficiency = " + anthektiteTierEfficiency + "\n");
            writer.write("    anthektiteTierEnchantability = " + anthektiteTierEnchantability + "\n");
            writer.write("    anthektiteTier_WeaponSpeedModifier = " + anthektiteWeaponSpeedModifier + "\n");
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
            writer.write("\n");

        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
