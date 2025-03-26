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
public class WSConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final WSConfig INSTANCE = new WSConfig();
    private static final Path CONFIG_PATH = Elementus.WS_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Cracker's Witherstormmod Tools
    public static int steelCMDSwordDamage;
    public static double steelCMDSwordAttackSpeed;
    public static double steelCMDShovelDamage;
    public static double steelCMDShovelAttackSpeed;
    public static int steelCMDPickaxeDamage;
    public static double steelCMDPickaxeAttackSpeed;
    public static int steelCMDAxeDamage;
    public static double steelCMDAxeAttackSpeed;
    public static int steelCMDHoeDamage;
    public static double steelCMDHoeAttackSpeed;

    public static int diarkriteCMDSwordDamage;
    public static double diarkriteCMDSwordAttackSpeed;
    public static double diarkriteCMDShovelDamage;
    public static double diarkriteCMDShovelAttackSpeed;
    public static int diarkriteCMDPickaxeDamage;
    public static double diarkriteCMDPickaxeAttackSpeed;
    public static int diarkriteCMDAxeDamage;
    public static double diarkriteCMDAxeAttackSpeed;
    public static int diarkriteCMDHoeDamage;
    public static double diarkriteCMDHoeAttackSpeed;

    public static int anthektiteCMDSwordDamage;
    public static double anthektiteCMDSwordAttackSpeed;
    public static double anthektiteCMDShovelDamage;
    public static double anthektiteCMDShovelAttackSpeed;
    public static int anthektiteCMDPickaxeDamage;
    public static double anthektiteCMDPickaxeAttackSpeed;
    public static int anthektiteCMDAxeDamage;
    public static double anthektiteCMDAxeAttackSpeed;
    public static int anthektiteCMDHoeDamage;
    public static double anthektiteCMDHoeAttackSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Witherstorm Config loaded");
    }

    private void setDefaults() {
        //Cracker's Witherstormmod Tools
        steelCMDSwordDamage = 3;
        steelCMDSwordAttackSpeed = -2.8;
        steelCMDShovelDamage = 1.5;
        steelCMDShovelAttackSpeed = -3.2;
        steelCMDPickaxeDamage = 1;
        steelCMDPickaxeAttackSpeed = -3.1;
        steelCMDAxeDamage = 5;
        steelCMDAxeAttackSpeed = -3.3;
        steelCMDHoeDamage = -3;
        steelCMDHoeAttackSpeed = 0.0;

        diarkriteCMDSwordDamage = 3;
        diarkriteCMDSwordAttackSpeed = -2.4;
        diarkriteCMDShovelDamage = 1.5;
        diarkriteCMDShovelAttackSpeed = -3.0;
        diarkriteCMDPickaxeDamage = 1;
        diarkriteCMDPickaxeAttackSpeed = -2.8;
        diarkriteCMDAxeDamage = 6;
        diarkriteCMDAxeAttackSpeed = -3.1;
        diarkriteCMDHoeDamage = -6;
        diarkriteCMDHoeAttackSpeed = 0.0;

        anthektiteCMDSwordDamage = 3;
        anthektiteCMDSwordAttackSpeed = -1.5;
        anthektiteCMDShovelDamage = 2.5;
        anthektiteCMDShovelAttackSpeed = -2.4;
        anthektiteCMDPickaxeDamage = 2;
        anthektiteCMDPickaxeAttackSpeed = -2.2;
        anthektiteCMDAxeDamage = 7;
        anthektiteCMDAxeAttackSpeed = -1.6;
        anthektiteCMDHoeDamage = 3;
        anthektiteCMDHoeAttackSpeed = 0.0;
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
                        //Cracker's Witherstormmod
                        case "steelCMDSword.Damage": steelCMDSwordDamage = (int) value; break;
                        case "steelCMDSword.Speed": steelCMDSwordAttackSpeed = value; break;
                        case "steelCMDShovel.Damage": steelCMDShovelDamage = value; break;
                        case "steelCMDShovel.Speed": steelCMDShovelAttackSpeed = value; break;
                        case "steelCMDPickaxe.Damage": steelCMDPickaxeDamage = (int) value; break;
                        case "steelCMDPickaxe.Speed": steelCMDPickaxeAttackSpeed = value; break;
                        case "steelCMDAxe.Damage": steelCMDAxeDamage = (int) value; break;
                        case "steelCMDAxe.Speed": steelCMDAxeAttackSpeed = value; break;
                        case "steelCMDHoe.Damage": steelCMDHoeDamage = (int) value; break;
                        case "steelCMDHoe.Speed": steelCMDHoeAttackSpeed = value; break;

                        case "diarkriteCMDSword.Damage": diarkriteCMDSwordDamage = (int) value; break;
                        case "diarkriteCMDSword.Speed": diarkriteCMDSwordAttackSpeed = value; break;
                        case "diarkriteCMDShovel.Damage": diarkriteCMDShovelDamage = value; break;
                        case "diarkriteCMDShovel.Speed": diarkriteCMDShovelAttackSpeed = value; break;
                        case "diarkriteCMDPickaxe.Damage": diarkriteCMDPickaxeDamage = (int) value; break;
                        case "diarkriteCMDPickaxe.Speed": diarkriteCMDPickaxeAttackSpeed = value; break;
                        case "diarkriteCMDAxe.Damage": diarkriteCMDAxeDamage = (int) value; break;
                        case "diarkriteCMDAxe.Speed": diarkriteCMDAxeAttackSpeed = value; break;
                        case "diarkriteCMDHoe.Damage": diarkriteCMDHoeDamage = (int) value; break;
                        case "diarkriteCMDHoe.Speed": diarkriteCMDHoeAttackSpeed = value; break;

                        case "anthektiteCMDSword.Damage": anthektiteCMDSwordDamage = (int) value; break;
                        case "anthektiteCMDSword.Speed": anthektiteCMDSwordAttackSpeed = value; break;
                        case "anthektiteCMDShovel.Damage": anthektiteCMDShovelDamage = value; break;
                        case "anthektiteCMDShovel.Speed": anthektiteCMDShovelAttackSpeed = value; break;
                        case "anthektiteCMDPickaxe.Damage": anthektiteCMDPickaxeDamage = (int) value; break;
                        case "anthektiteCMDPickaxe.Speed": anthektiteCMDPickaxeAttackSpeed = value; break;
                        case "anthektiteCMDAxe.Damage": anthektiteCMDAxeDamage = (int) value; break;
                        case "anthektiteCMDAxe.Speed": anthektiteCMDAxeAttackSpeed = value; break;
                        case "anthektiteCMDHoe.Damage": anthektiteCMDHoeDamage = (int) value; break;
                        case "anthektiteCMDHoe.Speed": anthektiteCMDHoeAttackSpeed = value; break;
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
            logger.info("Config version outdated, Updating config \"witherstorm_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Witherstorm Item Stats Config\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[Witherstormmod.Steel]\n");
            writer.write("# Default: " + steelCMDSwordDamage + "\n");
            writer.write("  steelCMDSword.Damage = " + steelCMDSwordDamage + "\n");
            writer.write("# Default: " + steelCMDSwordAttackSpeed + "\n");
            writer.write("  steelCMDSword.Speed = " + steelCMDSwordAttackSpeed + "\n");
            writer.write("# Default: " + steelCMDShovelDamage + "\n");
            writer.write("  steelCMDShovel.Damage = " + steelCMDShovelDamage + "\n");
            writer.write("# Default: " + steelCMDShovelAttackSpeed + "\n");
            writer.write("  steelCMDShovel.Speed = " + steelCMDShovelAttackSpeed + "\n");
            writer.write("# Default: " + steelCMDPickaxeDamage + "\n");
            writer.write("  steelCMDPickaxe.Damage = " + steelCMDPickaxeDamage + "\n");
            writer.write("# Default: " + steelCMDPickaxeAttackSpeed + "\n");
            writer.write("  steelCMDPickaxe.Speed = " + steelCMDPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + steelCMDAxeDamage + "\n");
            writer.write("  steelCMDAxe.Damage = " + steelCMDAxeDamage + "\n");
            writer.write("# Default: " + steelCMDAxeAttackSpeed + "\n");
            writer.write("  steelCMDAxe.Speed = " + steelCMDAxeAttackSpeed + "\n");
            writer.write("# Default: " + steelCMDHoeDamage + "\n");
            writer.write("  steelCMDHoe.Damage = " + steelCMDHoeDamage + "\n");
            writer.write("# Default: " + steelCMDHoeAttackSpeed + "\n");
            writer.write("  steelCMDHoe.Speed = " + steelCMDHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[Witherstormmod.Diarkrite]\n");
            writer.write("# Default: " + diarkriteCMDSwordDamage + "\n");
            writer.write("  diarkriteCMDSword.Damage = " + diarkriteCMDSwordDamage + "\n");
            writer.write("# Default: " + diarkriteCMDSwordAttackSpeed + "\n");
            writer.write("  diarkriteCMDSword.Speed = " + diarkriteCMDSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteCMDShovelDamage + "\n");
            writer.write("  diarkriteCMDShovel.Damage = " + diarkriteCMDShovelDamage + "\n");
            writer.write("# Default: " + diarkriteCMDShovelAttackSpeed + "\n");
            writer.write("  diarkriteCMDShovel.Speed = " + diarkriteCMDShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteCMDPickaxeDamage + "\n");
            writer.write("  diarkriteCMDPickaxe.Damage = " + diarkriteCMDPickaxeDamage + "\n");
            writer.write("# Default: " + diarkriteCMDPickaxeAttackSpeed + "\n");
            writer.write("  diarkriteCMDPickaxe.Speed = " + diarkriteCMDPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteCMDAxeDamage + "\n");
            writer.write("  diarkriteCMDAxe.Damage = " + diarkriteCMDAxeDamage + "\n");
            writer.write("# Default: " + diarkriteCMDAxeAttackSpeed + "\n");
            writer.write("  diarkriteCMDAxe.Speed = " + diarkriteCMDAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteCMDHoeDamage + "\n");
            writer.write("  diarkriteCMDHoe.Damage = " + diarkriteCMDHoeDamage + "\n");
            writer.write("# Default: " + diarkriteCMDHoeAttackSpeed + "\n");
            writer.write("  diarkriteCMDHoe.Speed = " + diarkriteCMDHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[Witherstormmod.Anthektite]\n");
            writer.write("# Default: " + anthektiteCMDSwordDamage + "\n");
            writer.write("  anthektiteCMDSword.Damage = " + anthektiteCMDSwordDamage + "\n");
            writer.write("# Default: " + anthektiteCMDSwordAttackSpeed + "\n");
            writer.write("  anthektiteCMDSword.Speed = " + anthektiteCMDSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteCMDShovelDamage + "\n");
            writer.write("  anthektiteCMDShovel.Damage = " + anthektiteCMDShovelDamage + "\n");
            writer.write("# Default: " + anthektiteCMDShovelAttackSpeed + "\n");
            writer.write("  anthektiteCMDShovel.Speed = " + anthektiteCMDShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteCMDPickaxeDamage + "\n");
            writer.write("  anthektiteCMDPickaxe.Damage = " + anthektiteCMDPickaxeDamage + "\n");
            writer.write("# Default: " + anthektiteCMDPickaxeAttackSpeed + "\n");
            writer.write("  anthektiteCMDPickaxe.Speed = " + anthektiteCMDPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteCMDAxeDamage + "\n");
            writer.write("  anthektiteCMDAxe.Damage = " + anthektiteCMDAxeDamage + "\n");
            writer.write("# Default: " + anthektiteCMDAxeAttackSpeed + "\n");
            writer.write("  anthektiteCMDAxe.Speed = " + anthektiteCMDAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteCMDHoeDamage + "\n");
            writer.write("  anthektiteCMDHoe.Damage = " + anthektiteCMDHoeDamage + "\n");
            writer.write("# Default: " + anthektiteCMDHoeAttackSpeed + "\n");
            writer.write("  anthektiteCMDHoe.Speed = " + anthektiteCMDHoeAttackSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
