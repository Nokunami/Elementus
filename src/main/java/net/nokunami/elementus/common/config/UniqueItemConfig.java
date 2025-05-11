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
public class UniqueItemConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final UniqueItemConfig INSTANCE = new UniqueItemConfig();
    private static final Path CONFIG_PATH = Elementus.UNIQUE_ITEM_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    public static int diarkriteChargeBladeDamage;
    public static double diarkriteChargeBladeAttackSpeed;
    public static double diarkriteChargeBladeAttackReach;
    public static double diarkriteChargeBladeSonicDamage;
    public static int diarkriteChargeBladeBaseCharge;
    public static double diarkriteChargeBladeSacrificeDamageBonus;
    public static double diarkriteChargeBladeSelfSacrificeDamage;
    public static int diarkriteChargeBladeChargePenalty;

    // Anthektite Charge Weapon
    public static int anthektiteChargeBladeDamage;
    public static double anthektiteChargeBladeAttackSpeed;
    public static double anthektiteChargeBladeAttackReach;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Item Config loaded");
    }

    private void setDefaults() {
        diarkriteChargeBladeDamage = 3;
        diarkriteChargeBladeAttackSpeed = -2.9;
        diarkriteChargeBladeAttackReach = 1;
        diarkriteChargeBladeSonicDamage = 15;
        diarkriteChargeBladeBaseCharge = 7;
        diarkriteChargeBladeSacrificeDamageBonus = 0.25;
        diarkriteChargeBladeSelfSacrificeDamage = 0.25;
        diarkriteChargeBladeChargePenalty = 14;

        anthektiteChargeBladeDamage = 3;
        anthektiteChargeBladeAttackSpeed = -2.9;
        anthektiteChargeBladeAttackReach = 1;
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
                        case "diarkriteChargeBlade.Damage": diarkriteChargeBladeDamage = (int) value; break;
                        case "diarkriteChargeBlade.AttackSpeed": diarkriteChargeBladeAttackSpeed = value; break;
                        case "diarkriteChargeBlade.AttackReach": diarkriteChargeBladeAttackReach = value; break;
                        case "diarkriteChargeBlade.SonicDamage": diarkriteChargeBladeSonicDamage = value; break;
                        case "diarkriteChargeBlade.BaseCharge": diarkriteChargeBladeBaseCharge = (int) value; break;
                        case "diarkriteChargeBlade.SacrificeDamageBonus": diarkriteChargeBladeSacrificeDamageBonus = value; break;
                        case "diarkriteChargeBlade.SelfSacrificeDamage": diarkriteChargeBladeSelfSacrificeDamage = value; break;
                        case "diarkriteChargeBlade.ChargePenalty": diarkriteChargeBladeChargePenalty = (int) value; break;

                        case "anthektiteChargeBlade.Damage": anthektiteChargeBladeDamage = (int) value; break;
                        case "anthektiteChargeBlade.AttackSpeed": anthektiteChargeBladeAttackSpeed = value; break;
                        case "anthektiteChargeBlade.AttackReach": anthektiteChargeBladeAttackReach = value; break;
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
            logger.info("Config version outdated, Updating config \"elementus_item_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Unique Item Stats Config\n");
            writer.write("\n");
            writer.write("[DiarkriteChargeBlade]\n");
            writer.write("# Default: " + diarkriteChargeBladeDamage + "\n");
            writer.write("  diarkriteChargeBlade.Damage = " + diarkriteChargeBladeDamage + "\n");
            writer.write("# Default: " + diarkriteChargeBladeAttackSpeed + "\n");
            writer.write("  diarkriteChargeBlade.AttackSpeed = " + diarkriteChargeBladeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteChargeBladeAttackReach + "\n");
            writer.write("  diarkriteChargeBlade.AttackReach = " + diarkriteChargeBladeAttackReach + "\n");
            writer.write("# Default: " + diarkriteChargeBladeSonicDamage + "\n");
            writer.write("  diarkriteChargeBlade.SonicDamage = " + diarkriteChargeBladeSonicDamage + "\n");
            writer.write("# Default: " + diarkriteChargeBladeBaseCharge + "\n");
            writer.write("  diarkriteChargeBlade.BaseCharge = " + diarkriteChargeBladeBaseCharge + "\n");
            writer.write("# Default: " + diarkriteChargeBladeSacrificeDamageBonus + "\n");
            writer.write("  diarkriteChargeBlade.SacrificeDamageBonus = " + diarkriteChargeBladeSacrificeDamageBonus + "\n");
            writer.write("# Default: " + diarkriteChargeBladeSelfSacrificeDamage + "\n");
            writer.write("  diarkriteChargeBlade.SelfSacrificeDamage = " + diarkriteChargeBladeSelfSacrificeDamage + "\n");
            writer.write("# Default: " + diarkriteChargeBladeChargePenalty + "\n");
            writer.write("  diarkriteChargeBlade.ChargePenalty = " + diarkriteChargeBladeChargePenalty + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteChargeBlade]\n");
            writer.write("# Default: " + anthektiteChargeBladeDamage + "\n");
            writer.write("  anthektiteChargeBlade.Damage = " + anthektiteChargeBladeDamage + "\n");
            writer.write("# Default: " + anthektiteChargeBladeAttackSpeed + "\n");
            writer.write("  anthektiteChargeBlade.AttackSpeed = " + anthektiteChargeBladeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteChargeBladeAttackReach + "\n");
            writer.write("  anthektiteChargeBlade.AttackReach = " + anthektiteChargeBladeAttackReach + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
