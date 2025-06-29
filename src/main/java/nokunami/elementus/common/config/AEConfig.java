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
public class AEConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final AEConfig INSTANCE = new AEConfig();
    private static final Path CONFIG_PATH = Elementus.AE_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //The Aether
    public static double steelGloveDamage;
    public static double steelGloveMovementSpeed;
    public static double steelGloveAttackSpeed;
    public static double steelGloveArmorBonus;
    public static double steelGloveToughnessBonus;
    public static double diarkriteGloveDamage;
    public static double diarkriteGloveMovementSpeed;
    public static double diarkriteGloveAttackSpeed;
    public static double diarkriteGloveArmorBonus;
    public static double diarkriteGloveToughnessBonus;
    public static double anthektiteGloveDamage;
    public static double anthektiteGloveMovementSpeed;
    public static double anthektiteGloveAttackSpeed;
    public static double anthektiteGloveArmorBonus;
    public static double anthektiteGloveToughnessBonus;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Aether Config loaded");
    }

    private void setDefaults() {
        //The Aether
        steelGloveDamage = 0.6;
        steelGloveMovementSpeed = 0.0;
        steelGloveAttackSpeed = 0.0;
        steelGloveArmorBonus = 0.0;
        steelGloveToughnessBonus = 0.0;
        diarkriteGloveDamage = 1.25;
        diarkriteGloveMovementSpeed = -0.12;
        diarkriteGloveAttackSpeed = -0.05;
        diarkriteGloveArmorBonus = 0.0;
        diarkriteGloveToughnessBonus = 0.0;
        anthektiteGloveDamage = 0.75;
        anthektiteGloveMovementSpeed = 0.0;
        anthektiteGloveAttackSpeed = 0.16;
        anthektiteGloveArmorBonus = 0.0;
        anthektiteGloveToughnessBonus = 0.0;
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
                        //The Aether
                        case "steelGlove.Damage": steelGloveDamage = value; break;
                        case "steelGlove.MovementSpeed": steelGloveMovementSpeed = value; break;
                        case "steelGlove.AttackSpeed": steelGloveAttackSpeed = value; break;
                        case "steelGlove.ArmorBonus": steelGloveArmorBonus = value; break;
                        case "steelGlove.ToughnessBonus": steelGloveToughnessBonus = value; break;
                        case "diarkriteGlove.Damage": diarkriteGloveDamage = value; break;
                        case "diarkriteGlove.MovementSpeed": diarkriteGloveMovementSpeed = value; break;
                        case "diarkriteGlove.AttackSpeed": diarkriteGloveAttackSpeed = value; break;
                        case "diarkriteGlove.ArmorBonus": diarkriteGloveArmorBonus = value; break;
                        case "diarkriteGlove.ToughnessBonus": diarkriteGloveToughnessBonus = value; break;
                        case "anthektite.GloveDamage": anthektiteGloveDamage = value; break;
                        case "anthektite.GloveMovementSpeed": anthektiteGloveMovementSpeed = value; break;
                        case "anthektite.GloveAttackSpeed": anthektiteGloveAttackSpeed = value; break;
                        case "anthektite.GloveArmorBonus": anthektiteGloveArmorBonus = value; break;
                        case "anthektite.GloveToughnessBonus": anthektiteGloveToughnessBonus = value; break;
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
            logger.info("Config version outdated, Updating config \"aether_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Aether Item Stats Config\n");
            writer.write("\n");
            writer.write("[TheAether.Steel]\n");
            writer.write("# Default: " + steelGloveDamage + "\n");
            writer.write("  steelGlove.Damage = " + steelGloveDamage + "\n");
            writer.write("# Default: " + steelGloveMovementSpeed + "\n");
            writer.write("  steelGlove.MovementSpeed = " + steelGloveMovementSpeed + "\n");
            writer.write("# Default: " + steelGloveAttackSpeed + "\n");
            writer.write("  steelGlove.AttackSpeed = " + steelGloveAttackSpeed + "\n");
            writer.write("# Default: " + steelGloveArmorBonus + "\n");
            writer.write("  steelGlove.ArmorBonus = " + steelGloveArmorBonus + "\n");
            writer.write("# Default: " + steelGloveToughnessBonus + "\n");
            writer.write("  steelGlove.ToughnessBonus = " + steelGloveToughnessBonus + "\n");
            writer.write("\n");
            writer.write("[TheAether.Diarkrite]\n");
            writer.write("# Default: " + diarkriteGloveDamage + "\n");
            writer.write("  diarkriteGlove.Damage = " + diarkriteGloveDamage + "\n");
            writer.write("# Default: " + diarkriteGloveMovementSpeed + "\n");
            writer.write("  diarkriteGlove.MovementSpeed = " + diarkriteGloveMovementSpeed + "\n");
            writer.write("# Default: " + diarkriteGloveAttackSpeed + "\n");
            writer.write("  diarkriteGlove.AttackSpeed = " + diarkriteGloveAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGloveArmorBonus + "\n");
            writer.write("  diarkriteGlove.ArmorBonus = " + diarkriteGloveArmorBonus + "\n");
            writer.write("# Default: " + diarkriteGloveToughnessBonus + "\n");
            writer.write("  diarkriteGlove.ToughnessBonus = " + diarkriteGloveToughnessBonus + "\n");
            writer.write("\n");
            writer.write("[TheAether.Anthektite]\n");
            writer.write("# Default: " + anthektiteGloveDamage + "\n");
            writer.write("  anthektiteGlove.Damage = " + anthektiteGloveDamage + "\n");
            writer.write("# Default: " + anthektiteGloveMovementSpeed + "\n");
            writer.write("  anthektiteGlove.MovementSpeed = " + anthektiteGloveMovementSpeed + "\n");
            writer.write("# Default: " + anthektiteGloveAttackSpeed + "\n");
            writer.write("  anthektiteGlove.AttackSpeed = " + anthektiteGloveAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGloveArmorBonus + "\n");
            writer.write("  anthektiteGlove.ArmorBonus = " + anthektiteGloveArmorBonus + "\n");
            writer.write("# Default: " + anthektiteGloveToughnessBonus + "\n");
            writer.write("  anthektiteGlove.ToughnessBonus = " + anthektiteGloveToughnessBonus + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
