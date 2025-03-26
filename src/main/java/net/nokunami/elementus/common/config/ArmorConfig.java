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
public class ArmorConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ArmorConfig INSTANCE = new ArmorConfig();
    private static final Path CONFIG_PATH = Elementus.ARMOR_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Armor
    public static int steelArmor_DurabilityForType;
    public static int steelArmor_Enchantability;
    public static int steelArmor_Helmet;
    public static int steelArmor_Chestplate;
    public static int steelArmor_Leggings;
    public static int steelArmor_Boots;
    public static double steelArmor_Toughness;
    public static double steelArmor_KnockbackResistance;
    public static double steelArmor_AttackSpeed;
    public static double steelArmor_MovementSpeed;

    public static int diarkriteArmor_DurabilityForType;
    public static int diarkriteArmor_Enchantability;
    public static int diarkriteArmor_Helmet;
    public static int diarkriteArmor_Chestplate;
    public static int diarkriteArmor_Leggings;
    public static int diarkriteArmor_Boots;
    public static double diarkriteArmor_Toughness;
    public static double diarkriteArmor_KnockbackResistance;
    public static double diarkriteArmor_AttackSpeed;
    public static double diarkriteArmor_MovementSpeed;

    public static int anthektiteArmor_DurabilityForType;
    public static int anthektiteArmor_Enchantability;
    public static int anthektiteArmor_Helmet;
    public static int anthektiteArmor_Chestplate;
    public static int anthektiteArmor_Leggings;
    public static int anthektiteArmor_Boots;
    public static double anthektiteArmor_Toughness;
    public static double anthektiteArmor_KnockbackResistance;
    public static double anthektiteArmor_AttackSpeed;
    public static double anthektiteArmor_MovementSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Armor Config loaded");
    }

    private void setDefaults() {
        steelArmor_DurabilityForType = 24;
        steelArmor_Helmet = 3;
        steelArmor_Chestplate = 8;
        steelArmor_Leggings = 6;
        steelArmor_Boots = 3;
        steelArmor_Enchantability = 10;
        steelArmor_Toughness = 0;
        steelArmor_KnockbackResistance = 0;
        steelArmor_AttackSpeed = 0;
        steelArmor_MovementSpeed = 0;

        diarkriteArmor_DurabilityForType = 38;
        diarkriteArmor_Helmet = 3;
        diarkriteArmor_Chestplate = 8;
        diarkriteArmor_Leggings = 6;
        diarkriteArmor_Boots = 3;
        diarkriteArmor_Enchantability = 18;
        diarkriteArmor_Toughness = 4;
        diarkriteArmor_KnockbackResistance = 0.2;
        diarkriteArmor_AttackSpeed = 0;
        diarkriteArmor_MovementSpeed = -0.04;

        anthektiteArmor_DurabilityForType = 35;
        anthektiteArmor_Helmet = 3;
        anthektiteArmor_Chestplate = 8;
        anthektiteArmor_Leggings = 6;
        anthektiteArmor_Boots = 3;
        anthektiteArmor_Enchantability = 15;
        anthektiteArmor_Toughness = 2;
        anthektiteArmor_KnockbackResistance = 0.05;
        anthektiteArmor_AttackSpeed = 0.1;
        anthektiteArmor_MovementSpeed = 0;
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
                        case "steelArmor.Durability": steelArmor_DurabilityForType = (int) value; break;
                        case "steelArmor.Helmet": steelArmor_Helmet = (int) value; break;
                        case "steelArmor.Chestplate": steelArmor_Chestplate = (int) value; break;
                        case "steelArmor.Leggings": steelArmor_Leggings = (int) value; break;
                        case "steelArmor.Boots": steelArmor_Boots = (int) value; break;
                        case "steelArmor.Enchantability": steelArmor_Enchantability = (int) value; break;
                        case "steelArmor.Toughness": steelArmor_Toughness = value; break;
                        case "steelArmor.KnockbackResistance": steelArmor_KnockbackResistance = value; break;
                        case "steelArmor.AttackSpeedBoost": steelArmor_AttackSpeed = value; break;
                        case "steelArmor.MovementSpeed": steelArmor_MovementSpeed = value; break;

                        case "diarkriteArmor.Durability": diarkriteArmor_DurabilityForType = (int) value; break;
                        case "diarkriteArmor.Helmet": diarkriteArmor_Helmet = (int) value; break;
                        case "diarkriteArmor.Chestplate": diarkriteArmor_Chestplate = (int) value; break;
                        case "diarkriteArmor.Leggings": diarkriteArmor_Leggings = (int) value; break;
                        case "diarkriteArmor.Boots": diarkriteArmor_Boots = (int) value; break;
                        case "diarkriteArmor.Enchantability": diarkriteArmor_Enchantability = (int) value; break;
                        case "diarkriteArmor.Toughness": diarkriteArmor_Toughness = value; break;
                        case "diarkriteArmor.KnockbackResistance": diarkriteArmor_KnockbackResistance = value; break;
                        case "diarkriteArmor.AttackSpeedBoost": diarkriteArmor_AttackSpeed = value; break;
                        case "diarkriteArmor.MovementSpeed": diarkriteArmor_MovementSpeed = value; break;

                        case "anthektiteArmor.Durability": anthektiteArmor_DurabilityForType = (int) value; break;
                        case "anthektiteArmor.Helmet": anthektiteArmor_Helmet = (int) value; break;
                        case "anthektiteArmor.Chestplate": anthektiteArmor_Chestplate = (int) value; break;
                        case "anthektiteArmor.Leggings": anthektiteArmor_Leggings = (int) value; break;
                        case "anthektiteArmor.Boots": anthektiteArmor_Boots = (int) value; break;
                        case "anthektiteArmor.Enchantability": anthektiteArmor_Enchantability = (int) value; break;
                        case "anthektiteArmor.Toughness": anthektiteArmor_Toughness = value; break;
                        case "anthektiteArmor.KnockbackResistance": anthektiteArmor_KnockbackResistance = value; break;
                        case "anthektiteArmor.AttackSpeedBoost": anthektiteArmor_AttackSpeed = value; break;
                        case "anthektiteArmor.MovementSpeed": anthektiteArmor_MovementSpeed = value; break;
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
            logger.info("Config version outdated, Updating config \"elementus_armor_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Armor Config\n");
            writer.write("# Vanilla Stats for comparison\n");
            writer.write("# Format:\n");
            writer.write("# [Material, Durability, [Helmet, Chestplate, Leggings, Boots], Enchantability, Toughness, KnockbackResistance]\n");
            writer.write("# [Leather   :5,  [1,3,2,1], 15, 0, 0  ]\n");
            writer.write("# [Chain     :15, [2,5,4,1], 25, 0, 0  ]\n");
            writer.write("# [Gold      :7,  [2,5,3,1], 12, 0, 0  ]\n");
            writer.write("# [Iron      :15, [2,6,5,2], 9,  0, 0  ]\n");
            writer.write("# [Diamond   :33, [3,8,6,3], 10, 2, 0  ]\n");
            writer.write("# [Netherite :37, [3,8,6,3], 15, 3, 0.1]\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[Steel]\n");
            writer.write("# Default: " + steelArmor_DurabilityForType +"\n");
            writer.write("  steelArmor.Durability = " + steelArmor_DurabilityForType + "\n");
            writer.write("# Default: " + steelArmor_Enchantability +"\n");
            writer.write("  steelArmor.Enchantability = " + steelArmor_Enchantability + "\n");
            writer.write("# Default: " + steelArmor_Helmet +"\n");
            writer.write("  steelArmor.Helmet = " + steelArmor_Helmet + "\n");
            writer.write("# Default: " + steelArmor_Chestplate +"\n");
            writer.write("  steelArmor.Chestplate = " + steelArmor_Chestplate + "\n");
            writer.write("# Default: " + steelArmor_Leggings +"\n");
            writer.write("  steelArmor.Leggings = " + steelArmor_Leggings + "\n");
            writer.write("# Default: " + steelArmor_Boots +"\n");
            writer.write("  steelArmor.Boots = " + steelArmor_Boots + "\n");
            writer.write("# Default: " + steelArmor_Toughness +"\n");
            writer.write("  steelArmor.Toughness = " + steelArmor_Toughness + "\n");
            writer.write("# Default: " + steelArmor_KnockbackResistance +"\n");
            writer.write("  steelArmor.KnockbackResistance = " + steelArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + steelArmor_AttackSpeed +"\n");
            writer.write("  steelArmor.AttackSpeedBoost = " + steelArmor_AttackSpeed + "\n");
            writer.write("# Default: " + steelArmor_MovementSpeed +"\n");
            writer.write("  steelArmor.MovementSpeed = " + steelArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[Diarkrite]\n");
            writer.write("# Default: " + diarkriteArmor_DurabilityForType +"\n");
            writer.write("  diarkriteArmor.Durability = " + diarkriteArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteArmor_Enchantability +"\n");
            writer.write("  diarkriteArmor.Enchantability = " + diarkriteArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteArmor_Helmet +"\n");
            writer.write("  diarkriteArmor.Helmet = " + diarkriteArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteArmor_Chestplate +"\n");
            writer.write("  diarkriteArmor.Chestplate = " + diarkriteArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteArmor_Leggings +"\n");
            writer.write("  diarkriteArmor.Leggings = " + diarkriteArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteArmor_Boots +"\n");
            writer.write("  diarkriteArmor.Boots = " + diarkriteArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteArmor_Toughness +"\n");
            writer.write("  diarkriteArmor.Toughness = " + diarkriteArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteArmor_KnockbackResistance +"\n");
            writer.write("  diarkriteArmor.KnockbackResistance = " + diarkriteArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteArmor_AttackSpeed +"\n");
            writer.write("  diarkriteArmor.AttackSpeedBoost = " + diarkriteArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteArmor_MovementSpeed +"\n");
            writer.write("  diarkriteArmor.MovementSpeed = " + diarkriteArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[Anthektite]\n");
            writer.write("# Default: " + anthektiteArmor_DurabilityForType +"\n");
            writer.write("  anthektiteArmor.Durability = " + anthektiteArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteArmor_Enchantability +"\n");
            writer.write("  anthektiteArmor.Enchantability = " + anthektiteArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteArmor_Helmet +"\n");
            writer.write("  anthektiteArmor.Helmet = " + anthektiteArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteArmor_Chestplate +"\n");
            writer.write("  anthektiteArmor.Chestplate = " + anthektiteArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteArmor_Leggings +"\n");
            writer.write("  anthektiteArmor.Leggings = " + anthektiteArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteArmor_Boots +"\n");
            writer.write("  anthektiteArmor.Boots = " + anthektiteArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteArmor_Toughness +"\n");
            writer.write("  anthektiteArmor.Toughness = " + anthektiteArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteArmor_KnockbackResistance +"\n");
            writer.write("  anthektiteArmor.KnockbackResistance = " + anthektiteArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteArmor_AttackSpeed +"\n");
            writer.write("  anthektiteArmor.AttackSpeedBoost = " + anthektiteArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteArmor_MovementSpeed +"\n");
            writer.write("  anthektiteArmor.MovementSpeed = " + anthektiteArmor_MovementSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
