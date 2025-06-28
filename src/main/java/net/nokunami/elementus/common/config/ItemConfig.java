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
public class ItemConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ItemConfig INSTANCE = new ItemConfig();
    private static final Path CONFIG_PATH = Elementus.ITEM_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Tools
    public static int steelSwordDamage;
    public static double steelSwordAttackSpeed;
    public static double steelShovelDamage;
    public static double steelShovelAttackSpeed;
    public static int steelPickaxeDamage;
    public static double steelPickaxeAttackSpeed;
    public static double steelAxeDamage;
    public static double steelAxeAttackSpeed;
    public static int steelHoeDamage;
    public static double steelHoeAttackSpeed;
    public static int steelShieldDurability;
    public static int steelBowDurability;

    public static int diarkriteSwordDamage;
    public static double diarkriteSwordAttackSpeed;
    public static double diarkriteShovelDamage;
    public static double diarkriteShovelAttackSpeed;
    public static int diarkritePickaxeDamage;
    public static double diarkritePickaxeAttackSpeed;
    public static double diarkriteAxeDamage;
    public static double diarkriteAxeAttackSpeed;
    public static int diarkriteHoeDamage;
    public static double diarkriteHoeAttackSpeed;
    public static int diarkriteShieldDurability;
    public static int diarkriteBowDurability;

    public static int anthektiteSwordDamage;
    public static double anthektiteSwordAttackSpeed;
    public static double anthektiteShovelDamage;
    public static double anthektiteShovelAttackSpeed;
    public static int anthektitePickaxeDamage;
    public static double anthektitePickaxeAttackSpeed;
    public static double anthektiteAxeDamage;
    public static double anthektiteAxeAttackSpeed;
    public static int anthektiteHoeDamage;
    public static double anthektiteHoeAttackSpeed;
    public static int anthektiteShieldDurability;
    public static int anthektiteBowDurability;

    //Farmer's Delight
    public static double steelKnifeDamage;
    public static double steelKnifeAttackSpeed;
    public static double diarkriteKnifeDamage;
    public static double diarkriteKnifeAttackSpeed;
    public static double anthektiteKnifeDamage;
    public static double anthektiteKnifeAttackSpeed;

    //Piercing Paxels
    public static double steelPaxelDamage;
    public static double steelPaxelAttackSpeed;
    public static double diarkritePaxelDamage;
    public static double diarkritePaxelAttackSpeed;
    public static double anthektitePaxelDamage;
    public static double anthektitePaxelAttackSpeed;

    //Nether's Delight
    public static int steelMacheteDamage;
    public static double steelMacheteAttackSpeed;
    public static int diarkriteMacheteDamage;
    public static double diarkriteMacheteAttackSpeed;
    public static int anthektiteMacheteDamage;
    public static double anthektiteMacheteAttackSpeed;

    //Banilla Claws
    public static int steelClawDamage;
    public static double steelClawAttackSpeed;
    public static int diarkriteClawDamage;
    public static double diarkriteClawAttackSpeed;
    public static int anthektiteClawDamage;
    public static double anthektiteClawAttackSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Item Config loaded");
    }

    private void setDefaults() {
        steelSwordDamage = 3;
        steelSwordAttackSpeed = -2.4;
        steelShovelDamage = 1.5;
        steelShovelAttackSpeed = -3.0;
        steelPickaxeDamage = 1;
        steelPickaxeAttackSpeed = -2.8;
        steelAxeDamage = 5;
        steelAxeAttackSpeed = -3.1;
        steelHoeDamage = -3;
        steelHoeAttackSpeed = 0.0;
        steelShieldDurability = 457;
        steelBowDurability = 457;

        diarkriteSwordDamage = 3;
        diarkriteSwordAttackSpeed = -2.4;
        diarkriteShovelDamage = 1.5;
        diarkriteShovelAttackSpeed = -3.0;
        diarkritePickaxeDamage = 1;
        diarkritePickaxeAttackSpeed = -2.8;
        diarkriteAxeDamage = 6;
        diarkriteAxeAttackSpeed = -3.1;
        diarkriteHoeDamage = -6;
        diarkriteHoeAttackSpeed = 0.0;
        diarkriteShieldDurability = 843;
        diarkriteBowDurability = 843;

        anthektiteSwordDamage = 3;
        anthektiteSwordAttackSpeed = -2.4;
        anthektiteShovelDamage = 1.5;
        anthektiteShovelAttackSpeed = -3.0;
        anthektitePickaxeDamage = 1;
        anthektitePickaxeAttackSpeed = -2.8;
        anthektiteAxeDamage = 5;
        anthektiteAxeAttackSpeed = -3.1;
        anthektiteHoeDamage = -3;
        anthektiteHoeAttackSpeed = 0.0;
        anthektiteShieldDurability = 598;
        anthektiteBowDurability = 598;

        //Farmer's Delight
        steelKnifeDamage = 0.5;
        steelKnifeAttackSpeed = -2.0;
        diarkriteKnifeDamage = 0.5;
        diarkriteKnifeAttackSpeed = -2.0;
        anthektiteKnifeDamage = 0.5;
        anthektiteKnifeAttackSpeed = -2.0;

        //Piercing Paxels
        steelPaxelDamage = 5.0;
        steelPaxelAttackSpeed = -2.8;
        diarkritePaxelDamage = 5.0;
        diarkritePaxelAttackSpeed = -2.8;
        anthektitePaxelDamage = 5.0;
        anthektitePaxelAttackSpeed = -2.8;

        //Nether's Delight
        steelMacheteDamage = 2;
        steelMacheteAttackSpeed = -2.6;
        diarkriteMacheteDamage = 2;
        diarkriteMacheteAttackSpeed = -2.6;
        anthektiteMacheteDamage = 2;
        anthektiteMacheteAttackSpeed = -2.6;

        //Banilla Claws
        steelClawDamage = 4;
        steelClawAttackSpeed = 2.0;
        diarkriteClawDamage = 4;
        diarkriteClawAttackSpeed = 2.0;
        anthektiteClawDamage = 4;
        anthektiteClawAttackSpeed = 2.0;
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
                        case "steelSword.Damage": steelSwordDamage = (int) value; break;
                        case "steelSword.Speed": steelSwordAttackSpeed = value; break;
                        case "steelShovel.Damage": steelShovelDamage = value; break;
                        case "steelShovel.Speed": steelShovelAttackSpeed = value; break;
                        case "steelPickaxe.Damage": steelPickaxeDamage = (int) value; break;
                        case "steelPickaxe.Speed": steelPickaxeAttackSpeed = value; break;
                        case "steelAxe.Damage": steelAxeDamage = value; break;
                        case "steelAxe.Speed": steelAxeAttackSpeed = value; break;
                        case "steelHoe.Damage": steelHoeDamage = (int) value; break;
                        case "steelHoe.Speed": steelHoeAttackSpeed = value; break;
                        case "steelShield.Durability": steelShieldDurability = (int) value; break;
                        case "steelBow.Durability": steelBowDurability = (int) value; break;

                        case "diarkriteSword.Damage": diarkriteSwordDamage = (int) value; break;
                        case "diarkriteSword.Speed": diarkriteSwordAttackSpeed = value; break;
                        case "diarkriteShovel.Damage": diarkriteShovelDamage = value; break;
                        case "diarkriteShovel.Speed": diarkriteShovelAttackSpeed = value; break;
                        case "diarkritePickaxe.Damage": diarkritePickaxeDamage = (int) value; break;
                        case "diarkritePickaxe.Speed": diarkritePickaxeAttackSpeed = value; break;
                        case "diarkriteAxe.Damage": diarkriteAxeDamage = value; break;
                        case "diarkriteAxe.Speed": diarkriteAxeAttackSpeed = value; break;
                        case "diarkriteHoe.Damage": diarkriteHoeDamage = (int) value; break;
                        case "diarkriteHoe.Speed": diarkriteHoeAttackSpeed = value; break;
                        case "diarkriteShield.Durability": diarkriteShieldDurability = (int) value; break;
                        case "diarkriteBow.Durability": diarkriteBowDurability = (int) value; break;

                        case "anthektiteSword.Damage": anthektiteSwordDamage = (int) value; break;
                        case "anthektiteSword.Speed": anthektiteSwordAttackSpeed = value; break;
                        case "anthektiteShovel.Damage": anthektiteShovelDamage = value; break;
                        case "anthektiteShovel.Speed": anthektiteShovelAttackSpeed = value; break;
                        case "anthektitePickaxe.Damage": anthektitePickaxeDamage = (int) value; break;
                        case "anthektitePickaxe.Speed": anthektitePickaxeAttackSpeed = value; break;
                        case "anthektiteAxe.Damage": anthektiteAxeDamage = value; break;
                        case "anthektiteAxe.Speed": anthektiteAxeAttackSpeed = value; break;
                        case "anthektiteHoe.Damage": anthektiteHoeDamage = (int) value; break;
                        case "anthektiteHoe.Speed": anthektiteHoeAttackSpeed = value; break;
                        case "anthektiteShield.Durability": anthektiteShieldDurability = (int) value; break;
                        case "anthektiteBow.Durability": anthektiteBowDurability = (int) value; break;

                        //Farmer's Delight
                        case "steelKnife.Damage": steelKnifeDamage = value; break;
                        case "steelKnife.Speed": steelKnifeAttackSpeed = value; break;
                        case "diarkriteKnife.Damage": diarkriteKnifeDamage = value; break;
                        case "diarkriteKnife.Speed": diarkriteKnifeAttackSpeed = value; break;
                        case "anthektiteKnife.Damage": anthektiteKnifeDamage = value; break;
                        case "anthektiteKnife.Speed": anthektiteKnifeAttackSpeed = value; break;

                        //Piercing Paxels
                        case "steelPaxel.Damage": steelPaxelDamage = value; break;
                        case "steelPaxel.Speed": steelPaxelAttackSpeed = value; break;
                        case "diarkritePaxel.Damage": diarkritePaxelDamage = value; break;
                        case "diarkritePaxel.Speed": diarkritePaxelAttackSpeed = value; break;
                        case "anthektitePaxel.Damage": anthektitePaxelDamage = value; break;
                        case "anthektitePaxel.Speed": anthektitePaxelAttackSpeed = value; break;

                        //Nether's Delight
                        case "steelMachete.Damage": steelMacheteDamage = (int) value; break;
                        case "steelMachete.Speed": steelMacheteAttackSpeed = value; break;
                        case "diarkriteMachete.Damage": diarkriteMacheteDamage = (int) value; break;
                        case "diarkriteMachete.Speed": diarkriteMacheteAttackSpeed = value; break;
                        case "anthektiteMachete.Damage": anthektiteMacheteDamage = (int) value; break;
                        case "anthektiteMachete.Speed": anthektiteMacheteAttackSpeed = value; break;

                        //Banilla Claws
                        case "steelClaw.Damage": steelClawDamage = (int) value; break;
                        case "steelClaw.AttackSpeed": steelClawAttackSpeed = value; break;
                        case "diarkriteClaw.Damage": diarkriteClawDamage = (int) value; break;
                        case "diarkriteClaw.AttackSpeed": diarkriteClawAttackSpeed = value; break;
                        case "anthektiteClaw.Damage": anthektiteClawDamage = (int) value; break;
                        case "anthektiteClaw.AttackSpeed": anthektiteClawAttackSpeed = value; break;
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
            writer.write("# Item Stats Config\n");
            writer.write("\n");
            writer.write("[Steel]\n");
            writer.write("# Default: " + steelSwordDamage +"\n");
            writer.write("  steelSword.Damage = " + steelSwordDamage + "\n");
            writer.write("# Default: " + steelSwordAttackSpeed +"\n");
            writer.write("  steelSword.Speed = " + steelSwordAttackSpeed + "\n");
            writer.write("# Default: " + steelShovelDamage +"\n");
            writer.write("  steelShovel.Damage = " + steelShovelDamage + "\n");
            writer.write("# Default: " + steelShovelAttackSpeed +"\n");
            writer.write("  steelShovel.Speed = " + steelShovelAttackSpeed + "\n");
            writer.write("# Default: " + steelPickaxeDamage +"\n");
            writer.write("  steelPickaxe.Damage = " + steelPickaxeDamage + "\n");
            writer.write("# Default: " + steelPickaxeAttackSpeed +"\n");
            writer.write("  steelPickaxe.Speed = " + steelPickaxeAttackSpeed + "\n");
            writer.write("# Default: " + steelAxeDamage +"\n");
            writer.write("  steelAxe.Damage = " + steelAxeDamage + "\n");
            writer.write("# Default: " + steelAxeAttackSpeed +"\n");
            writer.write("  steelAxe.Speed = " + steelAxeAttackSpeed + "\n");
            writer.write("# Default: " + steelHoeDamage +"\n");
            writer.write("  steelHoe.Damage = " + steelHoeDamage + "\n");
            writer.write("# Default: " + steelHoeAttackSpeed +"\n");
            writer.write("  steelHoe.Speed = " + steelHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("# Default: " + steelShieldDurability +"\n");
            writer.write("  steelShield.Durability = " + steelShieldDurability + "\n");
            writer.write("# Default: " + steelBowDurability +"\n");
            writer.write("  steelBow.Durability = " + steelBowDurability + "\n");
            writer.write("\n");
            writer.write("[Diarkrite]\n");
            writer.write("# Default: " + diarkriteSwordDamage + "\n");
            writer.write("  diarkriteSword.Damage = " + diarkriteSwordDamage + "\n");
            writer.write("# Default: " + diarkriteSwordAttackSpeed + "\n");
            writer.write("  diarkriteSword.Speed = " + diarkriteSwordAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteShovelDamage + "\n");
            writer.write("  diarkriteShovel.Damage = " + diarkriteShovelDamage + "\n");
            writer.write("# Default: " + diarkriteShovelAttackSpeed + "\n");
            writer.write("  diarkriteShovel.Speed = " + diarkriteShovelAttackSpeed + "\n");
            writer.write("# Default: " + diarkritePickaxeDamage + "\n");
            writer.write("  diarkritePickaxe.Damage = " + diarkritePickaxeDamage + "\n");
            writer.write("# Default: " + diarkritePickaxeAttackSpeed + "\n");
            writer.write("  diarkritePickaxe.Speed = " + diarkritePickaxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteAxeDamage + "\n");
            writer.write("  diarkriteAxe.Damage = " + diarkriteAxeDamage + "\n");
            writer.write("# Default: " + diarkriteAxeAttackSpeed + "\n");
            writer.write("  diarkriteAxe.Speed = " + diarkriteAxeAttackSpeed + "\n");
            writer.write("# Default: " + diarkriteHoeDamage + "\n");
            writer.write("  diarkriteHoe.Damage = " + diarkriteHoeDamage + "\n");
            writer.write("# Default: " + diarkriteHoeAttackSpeed + "\n");
            writer.write("  diarkriteHoe.Speed = " + diarkriteHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("# Default: " + diarkriteShieldDurability + "\n");
            writer.write("  diarkriteShield.Durability = " + diarkriteShieldDurability + "\n");
            writer.write("# Default: " + diarkriteBowDurability + "\n");
            writer.write("  diarkriteBow.Durability = " + diarkriteBowDurability + "\n");
            writer.write("\n");
            writer.write("[Anthektite]\n");
            writer.write("# Default: " + anthektiteSwordDamage + "\n");
            writer.write("  anthektiteSword.Damage = " + anthektiteSwordDamage + "\n");
            writer.write("# Default: " + anthektiteSwordAttackSpeed + "\n");
            writer.write("  anthektiteSword.Speed = " + anthektiteSwordAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteShovelDamage + "\n");
            writer.write("  anthektiteShovel.Damage = " + anthektiteShovelDamage + "\n");
            writer.write("# Default: " + anthektiteShovelAttackSpeed + "\n");
            writer.write("  anthektiteShovel.Speed = " + anthektiteShovelAttackSpeed + "\n");
            writer.write("# Default: " + anthektitePickaxeDamage + "\n");
            writer.write("  anthektitePickaxe.Damage = " + anthektitePickaxeDamage + "\n");
            writer.write("# Default: " + anthektitePickaxeAttackSpeed + "\n");
            writer.write("  anthektitePickaxe.Speed = " + anthektitePickaxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteAxeDamage + "\n");
            writer.write("  anthektiteAxe.Damage = " + anthektiteAxeDamage + "\n");
            writer.write("# Default: " + anthektiteAxeAttackSpeed + "\n");
            writer.write("  anthektiteAxe.Speed = " + anthektiteAxeAttackSpeed + "\n");
            writer.write("# Default: " + anthektiteHoeDamage + "\n");
            writer.write("  anthektiteHoe.Damage = " + anthektiteHoeDamage + "\n");
            writer.write("# Default: " + anthektiteHoeAttackSpeed + "\n");
            writer.write("  anthektiteHoe.Speed = " + anthektiteHoeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("# Default: " + anthektiteShieldDurability + "\n");
            writer.write("  anthektiteShield.Durability = " + anthektiteShieldDurability + "\n");
            writer.write("# Default: " + anthektiteBowDurability + "\n");
            writer.write("  anthektiteBow.Durability = " + anthektiteBowDurability + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[FarmersDelight.Steel]\n");
            writer.write("# Default: " + steelKnifeDamage + "\n");
            writer.write("  steelKnife.Damage = " + steelKnifeDamage + "\n");
            writer.write("# Default: " + steelKnifeAttackSpeed + "\n");
            writer.write("  steelKnife.Speed = " + steelKnifeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[FarmersDelight.Diarkrite]\n");
            writer.write("# Default: " + diarkriteKnifeDamage + "\n");
            writer.write("  diarkriteKnife.Damage = " + diarkriteKnifeDamage + "\n");
            writer.write("# Default: " + diarkriteKnifeAttackSpeed + "\n");
            writer.write("  diarkriteKnife.Speed = " + diarkriteKnifeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[FarmersDelight.Anthektite]\n");
            writer.write("# Default: " + anthektiteKnifeDamage + "\n");
            writer.write("  anthektiteKnife.Damage = " + anthektiteKnifeDamage + "\n");
            writer.write("# Default: " + anthektiteKnifeAttackSpeed + "\n");
            writer.write("  anthektiteKnife.Speed = " + anthektiteKnifeAttackSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[PiercingPaxels.Steel]\n");
            writer.write("# Default: " + steelPaxelDamage + "\n");
            writer.write("  steelPaxel.Damage = " + steelPaxelDamage + "\n");
            writer.write("# Default: " + steelPaxelAttackSpeed + "\n");
            writer.write("  steelPaxel.Speed = " + steelPaxelAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[PiercingPaxels.Diarkrite]\n");
            writer.write("# Default: " + diarkritePaxelDamage + "\n");
            writer.write("  diarkritePaxel.Damage = " + diarkritePaxelDamage + "\n");
            writer.write("# Default: " + diarkritePaxelAttackSpeed + "\n");
            writer.write("  diarkritePaxel.Speed = " + diarkritePaxelAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[PiercingPaxels.Anthektite]\n");
            writer.write("# Default: " + anthektitePaxelDamage + "\n");
            writer.write("  anthektitePaxel.Damage = " + anthektitePaxelDamage + "\n");
            writer.write("# Default: " + anthektitePaxelAttackSpeed + "\n");
            writer.write("  anthektitePaxel.Speed = " + anthektitePaxelAttackSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[NethersDelight.Steel]\n");
            writer.write("# Default: " + steelMacheteDamage + "\n");
            writer.write("  steelMachete.Damage = " + steelMacheteDamage + "\n");
            writer.write("# Default: " + steelMacheteAttackSpeed + "\n");
            writer.write("  steelMachete.Speed = " + steelMacheteAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[NethersDelight.Diarkrite]\n");
            writer.write("# Default: " + diarkriteMacheteDamage + "\n");
            writer.write("  diarkriteMachete.Damage = " + diarkriteMacheteDamage + "\n");
            writer.write("# Default: " + diarkriteMacheteAttackSpeed + "\n");
            writer.write("  diarkriteMachete.Speed = " + diarkriteMacheteAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[NethersDelight.Anthektite]\n");
            writer.write("# Default: " + anthektiteMacheteDamage + "\n");
            writer.write("  anthektiteMachete.Damage = " + anthektiteMacheteDamage + "\n");
            writer.write("# Default: " + anthektiteMacheteAttackSpeed + "\n");
            writer.write("  anthektiteMachete.Speed = " + anthektiteMacheteAttackSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[BanillaClaws.Steel]\n");
            writer.write("# Default: " + steelClawDamage + "\n");
            writer.write("  steelClaw.Damage = " + steelClawDamage + "\n");
            writer.write("# Default: " + steelClawAttackSpeed + "\n");
            writer.write("  steelClaw.AttackSpeed = " + steelClawAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[BanillaClaws.Diarkrite]\n");
            writer.write("# Default: " + diarkriteClawDamage + "\n");
            writer.write("  diarkriteClaw.Damage = " + diarkriteClawDamage + "\n");
            writer.write("# Default: " + diarkriteClawAttackSpeed + "\n");
            writer.write("  diarkriteClaw.AttackSpeed = " + diarkriteClawAttackSpeed + "\n");
            writer.write("\n");
            writer.write("[BanillaClaws.Anthektite]\n");
            writer.write("# Default: " + anthektiteClawDamage + "\n");
            writer.write("  anthektiteClaw.Damage = " + anthektiteClawDamage + "\n");
            writer.write("# Default: " + anthektiteClawAttackSpeed + "\n");
            writer.write("  anthektiteClaw.AttackSpeed = " + anthektiteClawAttackSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
