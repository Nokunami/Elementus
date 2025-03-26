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
public class ISSConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ISSConfig INSTANCE = new ISSConfig();
    private static final Path CONFIG_PATH = Elementus.ISS_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Iron's Spells 'n Spellbooks
    public static int steelSpellbookSlot;
    public static int steelSpellbookMana;
    public static double steelSpellbookCooldown;
    public static double steelSpellbookCastTime;
    public static double steelSpellbookSpellPower;
    public static int diarkriteSpellbookSlot;
    public static int diarkriteSpellbookMana;
    public static double diarkriteSpellbookCooldown;
    public static double diarkriteSpellbookCastTime;
    public static double diarkriteSpellbookSpellPower;
    public static int anthektiteSpellbookSlot;
    public static int anthektiteSpellbookMana;
    public static double anthektiteSpellbookCooldown;
    public static double anthektiteSpellbookCastTime;
    public static double anthektiteSpellbookSpellPower;

    //Iron's Spells 'n Spellbooks Armor
    public static int diarkriteMageArmor_DurabilityForType;
    public static int diarkriteMageArmor_Enchantability;
    public static int diarkriteMageArmor_Helmet;
    public static int diarkriteMageArmor_Chestplate;
    public static int diarkriteMageArmor_Leggings;
    public static int diarkriteMageArmor_Boots;
    public static double diarkriteMageArmor_Toughness;
    public static double diarkriteMageArmor_KnockbackResistance;
    public static double diarkriteMageArmor_AttackSpeed;
    public static double diarkriteMageArmor_MovementSpeed;
    public static int diarkriteMageArmor_MaxMana;
    public static double diarkriteMageArmor_ManaRegen;
    public static double diarkriteMageArmor_SpellPower;
    public static double diarkriteMageArmor_SpellResist;
    public static double diarkriteMageArmor_CastTime;
    public static double diarkriteMageArmor_Cooldown;

    public static int anthektiteMageArmor_DurabilityForType;
    public static int anthektiteMageArmor_Enchantability;
    public static int anthektiteMageArmor_Helmet;
    public static int anthektiteMageArmor_Chestplate;
    public static int anthektiteMageArmor_Leggings;
    public static int anthektiteMageArmor_Boots;
    public static double anthektiteMageArmor_Toughness;
    public static double anthektiteMageArmor_KnockbackResistance;
    public static double anthektiteMageArmor_AttackSpeed;
    public static double anthektiteMageArmor_MovementSpeed;
    public static int anthektiteMageArmor_MaxMana;
    public static double anthektiteMageArmor_ManaRegen;
    public static double anthektiteMageArmor_SpellPower;
    public static double anthektiteMageArmor_SpellResist;
    public static double anthektiteMageArmor_CastTime;
    public static double anthektiteMageArmor_Cooldown;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Iron's Spells 'n Spellbooks Config loaded");
    }

    private void setDefaults() {
        //Iron's Spells 'n Spellbooks
        steelSpellbookSlot = 8;
        steelSpellbookMana = 50;
        steelSpellbookCooldown = 0.0;
        steelSpellbookCastTime = 0.0;
        steelSpellbookSpellPower = 0.0;
        diarkriteSpellbookSlot = 12;
        diarkriteSpellbookMana = 200;
        diarkriteSpellbookCooldown = -0.15;
        diarkriteSpellbookCastTime = 0.0;
        diarkriteSpellbookSpellPower = 0.2;
        anthektiteSpellbookSlot = 12;
        anthektiteSpellbookMana = 200;
        anthektiteSpellbookCooldown = 0.25;
        anthektiteSpellbookCastTime = 0.1;
        anthektiteSpellbookSpellPower = -0.05;

        //Iron's Spells 'n Spellbooks Armor Diarkrite
        diarkriteMageArmor_DurabilityForType = 38;
        diarkriteMageArmor_Helmet = 3;
        diarkriteMageArmor_Chestplate = 8;
        diarkriteMageArmor_Leggings = 6;
        diarkriteMageArmor_Boots = 3;
        diarkriteMageArmor_Enchantability = 18;
        diarkriteMageArmor_Toughness = 4;
        diarkriteMageArmor_KnockbackResistance = 0.2;
        diarkriteMageArmor_AttackSpeed = 0;
        diarkriteMageArmor_MovementSpeed = -0.04;
        diarkriteMageArmor_MaxMana = 135;
        diarkriteMageArmor_ManaRegen = -0.12;
        diarkriteMageArmor_SpellPower = 0.10;
        diarkriteMageArmor_SpellResist = 0.0;
        diarkriteMageArmor_CastTime = 0.0;
        diarkriteMageArmor_Cooldown = 0.0;

        anthektiteMageArmor_DurabilityForType = 35;
        anthektiteMageArmor_Helmet = 3;
        anthektiteMageArmor_Chestplate = 8;
        anthektiteMageArmor_Leggings = 6;
        anthektiteMageArmor_Boots = 3;
        anthektiteMageArmor_Enchantability = 15;
        anthektiteMageArmor_Toughness = 2;
        anthektiteMageArmor_KnockbackResistance = 0.05;
        anthektiteMageArmor_AttackSpeed = 0.1;
        anthektiteMageArmor_MovementSpeed = 0;
        anthektiteMageArmor_MaxMana = 100;
        anthektiteMageArmor_ManaRegen = 0.08;
        anthektiteMageArmor_SpellPower = 0.0;
        anthektiteMageArmor_SpellResist = 0.0;
        anthektiteMageArmor_CastTime = 0.0;
        anthektiteMageArmor_Cooldown = 0.0;
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
                        //Iron's Spells 'n Spellbooks
                        case "steelSpellbook.Slot": steelSpellbookSlot = (int) value; break;
                        case "steelSpellbook.Mana": steelSpellbookMana = (int) value; break;
                        case "steelSpellbook.Cooldown": steelSpellbookCooldown = value; break;
                        case "steelSpellbook.CastTime": steelSpellbookCastTime = value; break;
                        case "steelSpellbook.SpellPower": steelSpellbookSpellPower = value; break;
                        case "diarkriteSpellbook.Slot": diarkriteSpellbookSlot = (int) value; break;
                        case "diarkriteSpellbook.Mana": diarkriteSpellbookMana = (int) value; break;
                        case "diarkriteSpellbook.Cooldown": diarkriteSpellbookCooldown = value; break;
                        case "diarkriteSpellbook.CastTime": diarkriteSpellbookCastTime = value; break;
                        case "diarkriteSpellbook.SpellPower": diarkriteSpellbookSpellPower = value; break;
                        case "anthektiteSpellbook.Slot": anthektiteSpellbookSlot = (int) value; break;
                        case "anthektiteSpellbook.Mana": anthektiteSpellbookMana = (int) value; break;
                        case "anthektiteSpellbook.Cooldown": anthektiteSpellbookCooldown = value; break;
                        case "anthektiteSpellbook.CastTime": anthektiteSpellbookCastTime = value; break;
                        case "anthektiteSpellbook.SpellPower": anthektiteSpellbookSpellPower = value; break;


                        case "diarkriteMageArmor.Durability": diarkriteMageArmor_DurabilityForType = (int) value; break;
                        case "diarkriteMageArmor.Helmet": diarkriteMageArmor_Helmet = (int) value; break;
                        case "diarkriteMageArmor.Chestplate": diarkriteMageArmor_Chestplate = (int) value; break;
                        case "diarkriteMageArmor.Leggings": diarkriteMageArmor_Leggings = (int) value; break;
                        case "diarkriteMageArmor.Boots": diarkriteMageArmor_Boots = (int) value; break;
                        case "diarkriteMageArmor.Enchantability": diarkriteMageArmor_Enchantability = (int) value; break;
                        case "diarkriteMageArmor.Toughness": diarkriteMageArmor_Toughness = value; break;
                        case "diarkriteMageArmor.KnockbackResistance": diarkriteMageArmor_KnockbackResistance = value; break;
                        case "diarkriteMageArmor.AttackSpeedBoost": diarkriteMageArmor_AttackSpeed = value; break;
                        case "diarkriteMageArmor.MovementSpeed": diarkriteMageArmor_MovementSpeed = value; break;
                        case "diarkriteMageArmor.MaxMana": diarkriteMageArmor_MaxMana = (int) value; break;
                        case "diarkriteMageArmor.ManaRegen": diarkriteMageArmor_ManaRegen = value; break;
                        case "diarkriteMageArmor.SpellPower": diarkriteMageArmor_SpellPower = value; break;
                        case "diarkriteMageArmor.SpellResist": diarkriteMageArmor_SpellResist = value; break;
                        case "diarkriteMageArmor.CastTime": diarkriteMageArmor_CastTime = value; break;
                        case "diarkriteMageArmor.Cooldown": diarkriteMageArmor_Cooldown = value; break;

                        case "anthektiteMageArmor.Durability": anthektiteMageArmor_DurabilityForType = (int) value; break;
                        case "anthektiteMageArmor.Helmet": anthektiteMageArmor_Helmet = (int) value; break;
                        case "anthektiteMageArmor.Chestplate": anthektiteMageArmor_Chestplate = (int) value; break;
                        case "anthektiteMageArmor.Leggings": anthektiteMageArmor_Leggings = (int) value; break;
                        case "anthektiteMageArmor.Boots": anthektiteMageArmor_Boots = (int) value; break;
                        case "anthektiteMageArmor.Enchantability": anthektiteMageArmor_Enchantability = (int) value; break;
                        case "anthektiteMageArmor.Toughness": anthektiteMageArmor_Toughness = value; break;
                        case "anthektiteMageArmor.KnockbackResistance": anthektiteMageArmor_KnockbackResistance = value; break;
                        case "anthektiteMageArmor.AttackSpeedBoost": anthektiteMageArmor_AttackSpeed = value; break;
                        case "anthektiteMageArmor.MovementSpeed": anthektiteMageArmor_MovementSpeed = value; break;
                        case "anthektiteMageArmor.MaxMana": anthektiteMageArmor_MaxMana = (int) value; break;
                        case "anthektiteMageArmor.ManaRegen": anthektiteMageArmor_ManaRegen = value; break;
                        case "anthektiteMageArmor.SpellPower": anthektiteMageArmor_SpellPower = value; break;
                        case "anthektiteMageArmor.SpellResist": anthektiteMageArmor_SpellResist = value; break;
                        case "anthektiteMageArmor.CastTime": anthektiteMageArmor_CastTime = value; break;
                        case "anthektiteMageArmor.Cooldown": anthektiteMageArmor_Cooldown = value; break;
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
            logger.info("Config version outdated, Updating config \"irons_spellbook_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Iron's Spells 'n Spellbooks Item Stats Config\n");
            writer.write("\n");
            writer.write("# Note: Spell slot config does not apply to existing spellbooks, only new ones\n");
            writer.write("[IronsSpellbooks.Steel]\n");
            writer.write("# Default: " + steelSpellbookSlot + "\n");
            writer.write("  steelSpellbook.Slot = " + steelSpellbookSlot + "\n");
            writer.write("# Default: " + steelSpellbookMana + "\n");
            writer.write("  steelSpellbook.Mana = " + steelSpellbookMana + "\n");
            writer.write("# Default: " + steelSpellbookCooldown + "\n");
            writer.write("  steelSpellbook.Cooldown = " + steelSpellbookCooldown + "\n");
            writer.write("# Default: " + steelSpellbookCastTime + "\n");
            writer.write("  steelSpellbook.CastTime = " + steelSpellbookCastTime + "\n");
            writer.write("# Default: " + steelSpellbookSpellPower + "\n");
            writer.write("  steelSpellbook.SpellPower = " + steelSpellbookSpellPower + "\n");
            writer.write("[IronsSpellbooks.Diarkrite]\n");
            writer.write("# Default: " + diarkriteSpellbookSlot + "\n");
            writer.write("  diarkriteSpellbook.Slot = " + diarkriteSpellbookSlot + "\n");
            writer.write("# Default: " + diarkriteSpellbookMana + "\n");
            writer.write("  diarkriteSpellbook.Mana = " + diarkriteSpellbookMana + "\n");
            writer.write("# Default: " + diarkriteSpellbookCooldown + "\n");
            writer.write("  diarkriteSpellbook.Cooldown = " + diarkriteSpellbookCooldown + "\n");
            writer.write("# Default: " + diarkriteSpellbookCastTime + "\n");
            writer.write("  diarkriteSpellbook.CastTime = " + diarkriteSpellbookCastTime + "\n");
            writer.write("# Default: " + diarkriteSpellbookSpellPower + "\n");
            writer.write("  diarkriteSpellbook.SpellPower = " + diarkriteSpellbookSpellPower + "\n");
            writer.write("[IronsSpellbooks.Anthektite]\n");
            writer.write("# Default: " + anthektiteSpellbookSlot + "\n");
            writer.write("  anthektiteSpellbook.Slot = " + anthektiteSpellbookSlot + "\n");
            writer.write("# Default: " + anthektiteSpellbookMana + "\n");
            writer.write("  anthektiteSpellbook.Mana = " + anthektiteSpellbookMana + "\n");
            writer.write("# Default: " + anthektiteSpellbookCooldown + "\n");
            writer.write("  anthektiteSpellbook.Cooldown = " + anthektiteSpellbookCooldown + "\n");
            writer.write("# Default: " + anthektiteSpellbookCastTime + "\n");
            writer.write("  anthektiteSpellbook.CastTime = " + anthektiteSpellbookCastTime + "\n");
            writer.write("# Default: " + anthektiteSpellbookSpellPower + "\n");
            writer.write("  anthektiteSpellbook.SpellPower = " + anthektiteSpellbookSpellPower + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[IronsSpellbooks.Diarkrite]\n");
            writer.write("# Default: " + diarkriteMageArmor_DurabilityForType +"\n");
            writer.write("  diarkriteMageArmor.Durability = " + diarkriteMageArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Enchantability +"\n");
            writer.write("  diarkriteMageArmor.Enchantability = " + diarkriteMageArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Helmet +"\n");
            writer.write("  diarkriteMageArmor.Helmet = " + diarkriteMageArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Chestplate +"\n");
            writer.write("  diarkriteMageArmor.Chestplate = " + diarkriteMageArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Leggings +"\n");
            writer.write("  diarkriteMageArmor.Leggings = " + diarkriteMageArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Boots +"\n");
            writer.write("  diarkriteMageArmor.Boots = " + diarkriteMageArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Toughness +"\n");
            writer.write("  diarkriteMageArmor.Toughness = " + diarkriteMageArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteMageArmor_KnockbackResistance +"\n");
            writer.write("  diarkriteMageArmor.KnockbackResistance = " + diarkriteMageArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteMageArmor_AttackSpeed +"\n");
            writer.write("  diarkriteMageArmor.AttackSpeedBoost = " + diarkriteMageArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteMageArmor_MovementSpeed +"\n");
            writer.write("  diarkriteMageArmor.MovementSpeed = " + diarkriteMageArmor_MovementSpeed + "\n");
            writer.write("# Default: " + diarkriteMageArmor_MaxMana +"\n");
            writer.write("  diarkriteMageArmor.MaxMana = " + diarkriteMageArmor_MaxMana + "\n");
            writer.write("# Default: " + diarkriteMageArmor_ManaRegen +"\n");
            writer.write("  diarkriteMageArmor.ManaRegen = " + diarkriteMageArmor_ManaRegen + "\n");
            writer.write("# Default: " + diarkriteMageArmor_SpellPower +"\n");
            writer.write("  diarkriteMageArmor.SpellPower = " + diarkriteMageArmor_SpellPower + "\n");
            writer.write("# Default: " + diarkriteMageArmor_SpellResist +"\n");
            writer.write("  diarkriteMageArmor.SpellResist = " + diarkriteMageArmor_SpellResist + "\n");
            writer.write("# Default: " + diarkriteMageArmor_CastTime +"\n");
            writer.write("  diarkriteMageArmor.CastTime = " + diarkriteMageArmor_CastTime + "\n");
            writer.write("# Default: " + diarkriteMageArmor_Cooldown +"\n");
            writer.write("  diarkriteMageArmor.Cooldown = " + diarkriteMageArmor_Cooldown + "\n");
            writer.write("\n");
            writer.write("[IronsSpellbooks.Anthektite]\n");
            writer.write("# Default: " + anthektiteMageArmor_DurabilityForType +"\n");
            writer.write("  anthektiteMageArmor.Durability = " + anthektiteMageArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Enchantability +"\n");
            writer.write("  anthektiteMageArmor.Enchantability = " + anthektiteMageArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Helmet +"\n");
            writer.write("  anthektiteMageArmor.Helmet = " + anthektiteMageArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Chestplate +"\n");
            writer.write("  anthektiteMageArmor.Chestplate = " + anthektiteMageArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Leggings +"\n");
            writer.write("  anthektiteMageArmor.Leggings = " + anthektiteMageArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Boots +"\n");
            writer.write("  anthektiteMageArmor.Boots = " + anthektiteMageArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Toughness +"\n");
            writer.write("  anthektiteMageArmor.Toughness = " + anthektiteMageArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteMageArmor_KnockbackResistance +"\n");
            writer.write("  anthektiteMageArmor.KnockbackResistance = " + anthektiteMageArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteMageArmor_AttackSpeed +"\n");
            writer.write("  anthektiteMageArmor.AttackSpeedBoost = " + anthektiteMageArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteMageArmor_MovementSpeed +"\n");
            writer.write("  anthektiteMageArmor.MovementSpeed = " + anthektiteMageArmor_MovementSpeed + "\n");
            writer.write("# Default: " + anthektiteMageArmor_MaxMana +"\n");
            writer.write("  anthektiteMageArmor.MaxMana = " + anthektiteMageArmor_MaxMana + "\n");
            writer.write("# Default: " + anthektiteMageArmor_ManaRegen +"\n");
            writer.write("  anthektiteMageArmor.ManaRegen = " + anthektiteMageArmor_ManaRegen + "\n");
            writer.write("# Default: " + anthektiteMageArmor_SpellPower +"\n");
            writer.write("  anthektiteMageArmor.SpellPower = " + anthektiteMageArmor_SpellPower + "\n");
            writer.write("# Default: " + anthektiteMageArmor_SpellResist +"\n");
            writer.write("  anthektiteMageArmor.SpellResist = " + anthektiteMageArmor_SpellResist + "\n");
            writer.write("# Default: " + anthektiteMageArmor_CastTime +"\n");
            writer.write("  anthektiteMageArmor.CastTime = " + anthektiteMageArmor_CastTime + "\n");
            writer.write("# Default: " + anthektiteMageArmor_Cooldown +"\n");
            writer.write("  anthektiteMageArmor.Cooldown = " + anthektiteMageArmor_Cooldown + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
