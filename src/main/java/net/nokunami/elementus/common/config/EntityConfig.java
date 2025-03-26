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
public class EntityConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final EntityConfig INSTANCE = new EntityConfig();
    private static final Path CONFIG_PATH = Elementus.ENTITY_CONFIG;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Armor
    public static double MaxHealth;
    public static double MovementSpeed;
    public static double KnockbackResist;
    public static double AttackDamage;
    public static double Armor;
    public static double Toughness;
    public static int RepairAmount;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Entity Config loaded");
    }

    private void setDefaults() {
        MaxHealth = 150;
        MovementSpeed = 0.25;
        KnockbackResist = 1;
        AttackDamage = 15;
        Armor = 10;
        Toughness = 0;
        RepairAmount = 5;
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
                        case "Steel.MaxHealth": MaxHealth = value; break;
                        case "Steel.MovementSpeed": MovementSpeed = value; break;
                        case "Steel.KnockbackResist": KnockbackResist = value; break;
                        case "Steel.AttackDamage": AttackDamage = value; break;
                        case "Steel.Armor": Armor = value; break;
                        case "Steel.Toughness": Toughness = value; break;
                        case "Steel.RepairAmount": RepairAmount = (int) value; break;

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
            logger.info("Config version outdated, Updating config \"elementus_entity_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Entity Config\n");
            writer.write("\n");
            writer.write("[SteelGolem]\n");
            writer.write("# Default: " + MaxHealth +"\n");
            writer.write("  Steel.MaxHealth = " + MaxHealth + "\n");
            writer.write("# Default: " + MovementSpeed + "\n");
            writer.write("  Steel.MovementSpeed = " + MovementSpeed + "\n");
            writer.write("# Default: " + KnockbackResist + "\n");
            writer.write("  Steel.KnockbackResist = " + KnockbackResist + "\n");
            writer.write("# Default: " + AttackDamage + "\n");
            writer.write("  Steel.AttackDamage = " + AttackDamage + "\n");
            writer.write("# Default: " + Armor + "\n");
            writer.write("  Steel.Armor = " + Armor + "\n");
            writer.write("# Default: " + Toughness + "\n");
            writer.write("  Steel.Toughness = " + Toughness + "\n");
            writer.write("# Default: " + RepairAmount + " (How many ingots does it take to fully heal)\n");
            writer.write("  Steel.RepairAmount = " + RepairAmount + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
