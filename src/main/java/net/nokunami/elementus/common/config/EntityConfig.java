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
    public static double steelGolem_MaxHealth;
    public static double steelGolem_MovementSpeed;
    public static double steelGolem_AngryMovementSpeed;
    public static double steelGolem_KnockbackResist;
    public static double steelGolem_AttackDamage;
    public static double steelGolem_Armor;
    public static double steelGolem_Toughness;
    public static int steelGolem_FollowRange;
    public static int steelGolem_RepairAmount;

    public static double diarkriteGolem_MaxHealth;
    public static double diarkriteGolem_MovementSpeed;
    public static double diarkriteGolem_AngryMovementSpeed;
    public static double diarkriteGolem_KnockbackResist;
    public static double diarkriteGolem_AttackDamage;
    public static double diarkriteGolem_Armor;
    public static double diarkriteGolem_Toughness;
    public static int diarkriteGolem_FollowRange;
    public static int diarkriteGolem_RepairAmount;

    public static double anthektiteGolem_MaxHealth;
    public static double anthektiteGolem_MovementSpeed;
    public static double anthektiteGolem_AngryMovementSpeed;
    public static double anthektiteGolem_KnockbackResist;
    public static double anthektiteGolem_AttackDamage;
    public static double anthektiteGolem_Armor;
    public static double anthektiteGolem_Toughness;
    public static int anthektiteGolem_FollowRange;
    public static int anthektiteGolem_RepairAmount;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Entity Config loaded");
    }

    private void setDefaults() {
        steelGolem_MaxHealth = 150;
        steelGolem_MovementSpeed = 0.25;
        steelGolem_AngryMovementSpeed = 0.3;
        steelGolem_KnockbackResist = 1;
        steelGolem_AttackDamage = 15;
        steelGolem_Armor = 10;
        steelGolem_Toughness = 0;
        steelGolem_FollowRange = 32;
        steelGolem_RepairAmount = 5;

        diarkriteGolem_MaxHealth = 250;
        diarkriteGolem_MovementSpeed = 0.2;
        diarkriteGolem_AngryMovementSpeed = 0.35;
        diarkriteGolem_KnockbackResist = 2;
        diarkriteGolem_AttackDamage = 40;
        diarkriteGolem_Armor = 20;
        diarkriteGolem_Toughness = 12;
        diarkriteGolem_FollowRange = 32;
        diarkriteGolem_RepairAmount = 5;

        anthektiteGolem_MaxHealth = 200;
        anthektiteGolem_MovementSpeed = 0.3;
        anthektiteGolem_AngryMovementSpeed = 0.4;
        anthektiteGolem_KnockbackResist = 0.5;
        anthektiteGolem_AttackDamage = 10;
        anthektiteGolem_Armor = 10;
        anthektiteGolem_Toughness = 0;
        anthektiteGolem_FollowRange = 32;
        anthektiteGolem_RepairAmount = 5;
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
                        case "Steel.MaxHealth": steelGolem_MaxHealth = value; break;
                        case "Steel.MovementSpeed": steelGolem_MovementSpeed = value; break;
                        case "Steel.AngryMovementSpeed": steelGolem_AngryMovementSpeed = value; break;
                        case "Steel.KnockbackResist": steelGolem_KnockbackResist = value; break;
                        case "Steel.AttackDamage": steelGolem_AttackDamage = value; break;
                        case "Steel.Armor": steelGolem_Armor = value; break;
                        case "Steel.Toughness": steelGolem_Toughness = value; break;
                        case "Steel.FollowRange": steelGolem_FollowRange = (int) value; break;
                        case "Steel.RepairAmount": steelGolem_RepairAmount = (int) value; break;

                        case "Diarkrite.MaxHealth": diarkriteGolem_MaxHealth = value; break;
                        case "Diarkrite.MovementSpeed": diarkriteGolem_MovementSpeed = value; break;
                        case "Diarkrite.AngryMovementSpeed": diarkriteGolem_AngryMovementSpeed = value; break;
                        case "Diarkrite.KnockbackResist": diarkriteGolem_KnockbackResist = value; break;
                        case "Diarkrite.AttackDamage": diarkriteGolem_AttackDamage = value; break;
                        case "Diarkrite.Armor": diarkriteGolem_Armor = value; break;
                        case "Diarkrite.Toughness": diarkriteGolem_Toughness = value; break;
                        case "Diarkrite.FollowRange": diarkriteGolem_FollowRange = (int) value; break;
                        case "Diarkrite.RepairAmount": diarkriteGolem_RepairAmount = (int) value; break;

                        case "Anthektite.MaxHealth": anthektiteGolem_MaxHealth = value; break;
                        case "Anthektite.MovementSpeed": anthektiteGolem_MovementSpeed = value; break;
                        case "Anthektite.AngryMovementSpeed": anthektiteGolem_AngryMovementSpeed = value; break;
                        case "Anthektite.KnockbackResist": anthektiteGolem_KnockbackResist = value; break;
                        case "Anthektite.AttackDamage": anthektiteGolem_AttackDamage = value; break;
                        case "Anthektite.Armor": anthektiteGolem_Armor = value; break;
                        case "Anthektite.Toughness": anthektiteGolem_Toughness = value; break;
                        case "Anthektite.FollowRange": anthektiteGolem_FollowRange = (int) value; break;
                        case "Anthektite.RepairAmount": anthektiteGolem_RepairAmount = (int) value; break;

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
            writer.write("# Default: " + steelGolem_MaxHealth +"\n");
            writer.write("  Steel.MaxHealth = " + steelGolem_MaxHealth + "\n");
            writer.write("# Default: " + steelGolem_MovementSpeed + "\n");
            writer.write("  Steel.MovementSpeed = " + steelGolem_MovementSpeed + "\n");
            writer.write("# Default: " + steelGolem_AngryMovementSpeed + "\n");
            writer.write("  Steel.AngryMovementSpeed = " + steelGolem_AngryMovementSpeed + "\n");
            writer.write("# Default: " + steelGolem_KnockbackResist + "\n");
            writer.write("  Steel.KnockbackResist = " + steelGolem_KnockbackResist + "\n");
            writer.write("# Default: " + steelGolem_AttackDamage + "\n");
            writer.write("  Steel.AttackDamage = " + steelGolem_AttackDamage + "\n");
            writer.write("# Default: " + steelGolem_Armor + "\n");
            writer.write("  Steel.Armor = " + steelGolem_Armor + "\n");
            writer.write("# Default: " + steelGolem_Toughness + "\n");
            writer.write("  Steel.Toughness = " + steelGolem_Toughness + "\n");
            writer.write("# Default: " + steelGolem_FollowRange + "\n");
            writer.write("  Steel.FollowRange = " + steelGolem_FollowRange + "\n");
            writer.write("# Default: " + steelGolem_RepairAmount + " (How many ingots does it take to fully heal)\n");
            writer.write("  Steel.RepairAmount = " + steelGolem_RepairAmount + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[DiarkriteGolem]\n");
            writer.write("# Default: " + diarkriteGolem_MaxHealth +"\n");
            writer.write("  Diarkrite.MaxHealth = " + diarkriteGolem_MaxHealth + "\n");
            writer.write("# Default: " + diarkriteGolem_MovementSpeed + "\n");
            writer.write("  Diarkrite.MovementSpeed = " + diarkriteGolem_MovementSpeed + "\n");
            writer.write("# Default: " + diarkriteGolem_AngryMovementSpeed + "\n");
            writer.write("  Diarkrite.AngryMovementSpeed = " + diarkriteGolem_AngryMovementSpeed + "\n");
            writer.write("# Default: " + diarkriteGolem_KnockbackResist + "\n");
            writer.write("  Diarkrite.KnockbackResist = " + diarkriteGolem_KnockbackResist + "\n");
            writer.write("# Default: " + diarkriteGolem_AttackDamage + "\n");
            writer.write("  Diarkrite.AttackDamage = " + diarkriteGolem_AttackDamage + "\n");
            writer.write("# Default: " + diarkriteGolem_Armor + "\n");
            writer.write("  Diarkrite.Armor = " + diarkriteGolem_Armor + "\n");
            writer.write("# Default: " + diarkriteGolem_Toughness + "\n");
            writer.write("  Diarkrite.Toughness = " + diarkriteGolem_Toughness + "\n");
            writer.write("# Default: " + diarkriteGolem_FollowRange + "\n");
            writer.write("  Diarkrite.FollowRange = " + diarkriteGolem_FollowRange + "\n");
            writer.write("# Default: " + diarkriteGolem_RepairAmount + " (How many ingots does it take to fully heal)\n");
            writer.write("  Diarkrite.RepairAmount = " + diarkriteGolem_RepairAmount + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteGolem]\n");
            writer.write("# Default: " + anthektiteGolem_MaxHealth +"\n");
            writer.write("  Anthektite.MaxHealth = " + anthektiteGolem_MaxHealth + "\n");
            writer.write("# Default: " + anthektiteGolem_MovementSpeed + "\n");
            writer.write("  Anthektite.MovementSpeed = " + anthektiteGolem_MovementSpeed + "\n");
            writer.write("# Default: " + anthektiteGolem_AngryMovementSpeed + "\n");
            writer.write("  Anthektite.AngryMovementSpeed = " + anthektiteGolem_AngryMovementSpeed + "\n");
            writer.write("# Default: " + anthektiteGolem_KnockbackResist + "\n");
            writer.write("  Anthektite.KnockbackResist = " + anthektiteGolem_KnockbackResist + "\n");
            writer.write("# Default: " + anthektiteGolem_AttackDamage + "\n");
            writer.write("  Anthektite.AttackDamage = " + anthektiteGolem_AttackDamage + "\n");
            writer.write("# Default: " + anthektiteGolem_Armor + "\n");
            writer.write("  Anthektite.Armor = " + anthektiteGolem_Armor + "\n");
            writer.write("# Default: " + anthektiteGolem_Toughness + "\n");
            writer.write("  Anthektite.Toughness = " + anthektiteGolem_Toughness + "\n");
            writer.write("# Default: " + anthektiteGolem_FollowRange + "\n");
            writer.write("  Anthektite.FollowRange = " + anthektiteGolem_FollowRange + "\n");
            writer.write("# Default: " + anthektiteGolem_RepairAmount + " (How many ingots does it take to fully heal)\n");
            writer.write("  Anthektite.RepairAmount = " + anthektiteGolem_RepairAmount + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
