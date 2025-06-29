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
public class SWConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final SWConfig INSTANCE = new SWConfig();
    private static final Path CONFIG_PATH = Elementus.SW_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Sniff's Weapons
    public static int steelGreatSwordDamage;
    public static double steelGreatSwordAttackSpeed;
    public static int steelGreatAxeDamage;
    public static double steelGreatAxeAttackSpeed;
    public static int steelGreatPickaxeDamage;
    public static double steelGreatPickaxeAttackSpeed;
    public static int steelNaginataDamage;
    public static double steelNaginataAttackSpeed;
    public static int diarkriteGreatSwordDamage;
    public static double diarkriteGreatSwordAttackSpeed;
    public static int diarkriteGreatAxeDamage;
    public static double diarkriteGreatAxeAttackSpeed;
    public static int diarkriteGreatPickaxeDamage;
    public static double diarkriteGreatPickaxeAttackSpeed;
    public static int diarkriteNaginataDamage;
    public static double diarkriteNaginataAttackSpeed;
    public static int anthektiteGreatSwordDamage;
    public static double anthektiteGreatSwordAttackSpeed;
    public static int anthektiteGreatAxeDamage;
    public static double anthektiteGreatAxeAttackSpeed;
    public static int anthektiteGreatPickaxeDamage;
    public static double anthektiteGreatPickaxeAttackSpeed;
    public static int anthektiteNaginataDamage;
    public static double anthektiteNaginataAttackSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Sniff's Weapons Config loaded");
    }

    private void setDefaults() {
        //Sniff's Weapons
        steelGreatSwordDamage = 5;
        steelGreatSwordAttackSpeed = -2.9;
        steelNaginataDamage = 4;
        steelNaginataAttackSpeed = -3.0;
        steelGreatAxeDamage = 7;
        steelGreatAxeAttackSpeed = -3.2;
        steelGreatPickaxeDamage = 3;
        steelGreatPickaxeAttackSpeed = -3.05;
        diarkriteGreatSwordDamage = 5;
        diarkriteGreatSwordAttackSpeed = -2.9;
        diarkriteNaginataDamage = 4;
        diarkriteNaginataAttackSpeed = -3.0;
        diarkriteGreatAxeDamage = 7;
        diarkriteGreatAxeAttackSpeed = -3.2;
        diarkriteGreatPickaxeDamage = 3;
        diarkriteGreatPickaxeAttackSpeed = -3.05;
        anthektiteGreatSwordDamage = 5;
        anthektiteGreatSwordAttackSpeed = -2.9;
        anthektiteNaginataDamage = 4;
        anthektiteNaginataAttackSpeed = -3.0;
        anthektiteGreatAxeDamage = 7;
        anthektiteGreatAxeAttackSpeed = -3.2;
        anthektiteGreatPickaxeDamage = 3;
        anthektiteGreatPickaxeAttackSpeed = -3.05;
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
                        //Sniff's Weapons
                        case "steelGreatSword.Damage": steelGreatSwordDamage = (int) value; break;
                        case "steelGreatSword.Speed": steelGreatSwordAttackSpeed = value; break;
                        case "steelNaginata.Damage": steelNaginataDamage = (int) value; break;
                        case "steelNaginata.Speed": steelNaginataAttackSpeed = value; break;
                        case "steelGreatAxe.Damage": steelGreatAxeDamage = (int) value; break;
                        case "steelGreatAxe.Speed": steelGreatAxeAttackSpeed = value; break;
                        case "steelGreatPickaxe.Damage": steelGreatPickaxeDamage = (int) value; break;
                        case "steelGreatPickaxe.Speed": steelGreatPickaxeAttackSpeed = value; break;
                        case "diarkriteGreatSword.Damage": diarkriteGreatSwordDamage = (int) value; break;
                        case "diarkriteGreatSword.Speed": diarkriteGreatSwordAttackSpeed = value; break;
                        case "diarkriteNaginata.Damage": diarkriteNaginataDamage = (int) value; break;
                        case "diarkriteNaginata.Speed": diarkriteNaginataAttackSpeed = value; break;
                        case "diarkriteGreatAxe.Damage": diarkriteGreatAxeDamage = (int) value; break;
                        case "diarkriteGreatAxe.Speed": diarkriteGreatAxeAttackSpeed = value; break;
                        case "diarkriteGreatPickaxe.Damage": diarkriteGreatPickaxeDamage = (int) value; break;
                        case "diarkriteGreatPickaxe.Speed": diarkriteGreatPickaxeAttackSpeed = value; break;
                        case "anthektiteGreatSword.Damage": anthektiteGreatSwordDamage = (int) value; break;
                        case "anthektiteGreatSword.Speed": anthektiteGreatSwordAttackSpeed = value; break;
                        case "anthektiteNaginata.Damage": anthektiteNaginataDamage = (int) value; break;
                        case "anthektiteNaginata.Speed": anthektiteNaginataAttackSpeed = value; break;
                        case "anthektiteGreatAxe.Damage": anthektiteGreatAxeDamage = (int) value; break;
                        case "anthektiteGreatAxe.Speed": anthektiteGreatAxeAttackSpeed = value; break;
                        case "anthektiteGreatPickaxe.Damage": anthektiteGreatPickaxeDamage = (int) value; break;
                        case "anthektiteGreatPickaxe.Speed": anthektiteGreatPickaxeAttackSpeed = value; break;
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
            logger.info("Config version outdated, Updating config \"sniff_weapon_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Sniff's Weapons Stats Config\n");
            writer.write("\n");
            writer.write("[SniffsWeapons.Steel]\n");
            writer.write("# Default: " + steelGreatSwordDamage + "\n");
            writer.write("  steelGreatSword.Damage = " + steelGreatSwordDamage + "\n");
            writer.write("# Default: " + steelGreatSwordAttackSpeed + "\n");
            writer.write("  steelGreatSword.Speed = " + steelGreatSwordAttackSpeed + "\n");
            writer.write("# Default: " + steelNaginataDamage + "\n");
            writer.write("  steelNaginata.Damage = " + steelNaginataDamage + "\n");
            writer.write("# Default: " + steelNaginataAttackSpeed + "\n");
            writer.write("  steelNaginata.Speed = " + steelNaginataAttackSpeed + "\n");
            writer.write("# Default: " + steelGreatAxeDamage + "\n");
            writer.write("  steelGreatAxe.Damage = " + steelGreatAxeDamage + "\n");
            writer.write("# Default: " + steelGreatAxeAttackSpeed + "\n");
            writer.write("  steelGreatAxe.Speed = " + steelGreatAxeAttackSpeed + "\n");
            writer.write("# Default: " + steelGreatPickaxeDamage + "\n");
            writer.write("  steelGreatPickaxe.Damage = " + steelGreatPickaxeDamage + "\n");
            writer.write("# Default: " + steelGreatPickaxeAttackSpeed + "\n");
            writer.write("  steelGreatPickaxe.Speed = " + steelGreatPickaxeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[SniffsWeapons.Diarkrite]\n");
            writer.write("# Default: " + diarkriteGreatSwordDamage + "\n");
            writer.write("  diarkriteGreatSword.Damage = " + diarkriteGreatSwordDamage + "\n");
            writer.write("# Default: " + diarkriteGreatSwordAttackSpeed + "\n");
            writer.write("  diarkriteGreatSword.Speed = " + diarkriteGreatSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteNaginataDamage + "\n");
            writer.write("  diarkriteNaginata.Damage = " + diarkriteNaginataDamage + "\n");
            writer.write("# Default: " + diarkriteNaginataAttackSpeed + "\n");
            writer.write("  diarkriteNaginata.Speed = " + diarkriteNaginataAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGreatAxeDamage + "\n");
            writer.write("  diarkriteGreatAxe.Damage = " + diarkriteGreatAxeDamage + "\n");
            writer.write("# Default: " + diarkriteGreatAxeAttackSpeed + "\n");
            writer.write("  diarkriteGreatAxe.Speed = " + diarkriteGreatAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteGreatPickaxeDamage + "\n");
            writer.write("  diarkriteGreatPickaxe.Damage = " + diarkriteGreatPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteGreatPickaxeAttackSpeed + "\n");
            writer.write("  diarkriteGreatPickaxe.Speed = " + diarkriteGreatPickaxeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[SniffsWeapons.Anthektite]\n");
            writer.write("# Default: " + anthektiteGreatSwordDamage + "\n");
            writer.write("  anthektiteGreatSword.Damage = " + anthektiteGreatSwordDamage + "\n");
            writer.write("# Default: " + anthektiteGreatSwordAttackSpeed + "\n");
            writer.write("  anthektiteGreatSword.Speed = " + anthektiteGreatSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteNaginataDamage + "\n");
            writer.write("  anthektiteNaginata.Damage = " + anthektiteNaginataDamage + "\n");
            writer.write("# Default: " + anthektiteNaginataAttackSpeed + "\n");
            writer.write("  anthektiteNaginata.Speed = " + anthektiteNaginataAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGreatAxeDamage + "\n");
            writer.write("  anthektiteGreatAxe.Damage = " + anthektiteGreatAxeDamage + "\n");
            writer.write("# Default: " + anthektiteGreatAxeAttackSpeed + "\n");
            writer.write("  anthektiteGreatAxe.Speed = " + anthektiteGreatAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteGreatPickaxeDamage + "\n");
            writer.write("  anthektiteGreatPickaxe.Damage = " + anthektiteGreatPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteGreatPickaxeAttackSpeed + "\n");
            writer.write("  anthektiteGreatPickaxe.Speed = " + anthektiteGreatPickaxeAttackSpeed + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
