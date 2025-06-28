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
public class CatalystArmorConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final CatalystArmorConfig INSTANCE = new CatalystArmorConfig();
    private static final Path CONFIG_PATH = Elementus.CATALYST_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Armor
    public static int Durability;
    public static int Enchantability;
    public static int Armor;
    public static double Toughness;
    public static double KnockbackResist;
    public static double AttackSpeed;
    public static double MovementSpeed;


    public static int NSCooldown;
    public static int NSDuration;
    public static int NSBaseAmp;
    public static int NSBoostedAmp;
    public static double NSSpeed;
    public static int NSHaste;
    public static double NSResistance;
    public static double NSResistAmountPerLevel;
    public static int NSJumpBoost;
    public static double NSAttack;

    public static int ignitium_HasteAmp;
    public static int ignitium_HasteDuration;
    public static int ignitium_StrengthAmp;
    public static int ignitium_StrengthDuration;
    public static int ignitium_ResistanceAmp;
    public static int ignitium_ResistanceDuration;

    public static int totem_Cooldown;
    public static int totem_RegenAmp;
    public static int totem_RegenDuration;
    public static int totem_AbsorbAmp;
    public static int totem_AbsorbDuration;
    public static int totem_FireResAmp;
    public static int totem_FireResDuration;

    public static int ISS_MaxMana;
    public static double ISS_ManaRegen;
    public static double ISS_SpellPower;
    public static double ISS_SpellResist;

    public static int cursium_GhostFormDuration;
    public static int cursium_FireResistDuration;
    public static double cursium_DodgeChance;
    public static double cursium_ProjectileDodgeChance;

    public static int WNSBaseAmp;
    public static int WNSBoostedAmp;
    public static double WNSRegenPenalty;
    public static double WNSSpeed;
    public static int WNSHaste;
    public static double WNSResistance;
    public static double WNSResistAmountPerLevel;
    public static int WNSJumpBoost;
    public static double WNSAttack;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Catalyst Armor Config loaded");
    }

    private void setDefaults() {
        Durability = 40;
        Enchantability = 20;
        Armor = 8;
        Toughness = 4;
        KnockbackResist = 0.1;
        AttackSpeed = 0.0;
        MovementSpeed = 0.0;

        NSCooldown = 350;
        NSDuration = 160;
        NSBaseAmp = 0;
        NSBoostedAmp = 1;
        NSSpeed = 1;
        NSHaste = 1;
        NSResistance = 10;
        NSResistAmountPerLevel = 10;
        NSJumpBoost = 1;
        NSAttack = 3;

        ignitium_HasteAmp = 1;
        ignitium_HasteDuration = 1;
        ignitium_StrengthAmp = 1;
        ignitium_StrengthDuration = 1;
        ignitium_ResistanceAmp = 1;
        ignitium_ResistanceDuration = 1;

        totem_Cooldown = 2400;
        totem_RegenAmp = 1;
        totem_RegenDuration = 900;
        totem_AbsorbAmp = 1;
        totem_AbsorbDuration = 100;
        totem_FireResAmp = 0;
        totem_FireResDuration = 800;

        ISS_MaxMana = 200;
        ISS_ManaRegen = 0.0;
        ISS_SpellPower = 0.0;
        ISS_SpellResist = 0.0;

        cursium_GhostFormDuration = 100;
        cursium_FireResistDuration = 200;
        cursium_DodgeChance = 0.08;
        cursium_ProjectileDodgeChance = 0.15;

        WNSBaseAmp = 0;
        WNSBoostedAmp = 1;
        WNSRegenPenalty = 0.5;
        WNSSpeed = 1;
        WNSHaste = 1;
        WNSResistance = 10;
        WNSResistAmountPerLevel = 10;
        WNSJumpBoost = 1;
        WNSAttack = 3;
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
                        case "Durability": Durability = (int) value; break;
                        case "ArmorProtection": Armor = (int) value; break;
                        case "Enchantability": Enchantability = (int) value; break;
                        case "ArmorToughness": Toughness = value; break;
                        case "KnockbackResistance": KnockbackResist = value; break;
                        case "AttackSpeedBoost": AttackSpeed = value; break;
                        case "MovementSpeed": MovementSpeed = value; break;

                        case "NS.Cooldown": NSCooldown = (int) value; break;
                        case "NS.BoostedDuration": NSDuration = (int) value; break;
                        case "NS.Amp": NSBaseAmp = (int) value; break;
                        case "NS.BoostedAmp": NSBoostedAmp = (int) value; break;
                        case "NS.Speed": NSSpeed = (int) value; break;
                        case "NS.Haste": NSHaste = (int) value; break;
                        case "NS.Resistance": NSResistance = value; break;
                        case "NS.ResistancePerLv": NSResistAmountPerLevel = value; break;
                        case "NS.JumpBoost": NSJumpBoost = (int) value; break;
                        case "NS.Base_Strength": NSAttack = value; break;

                        case "ignitium.HasteAmp": ignitium_HasteAmp = (int) value; break;
                        case "ignitium.HasteDuration": ignitium_HasteDuration = (int) value; break;
                        case "ignitium.StrengthAmp": ignitium_StrengthAmp = (int) value; break;
                        case "ignitium.StrengthDuration": ignitium_StrengthDuration = (int) value; break;
                        case "ignitium.ResistanceAmp": ignitium_ResistanceAmp = (int) value; break;
                        case "ignitium.ResistanceDuration": ignitium_ResistanceDuration = (int) value; break;

                        case "totem.Cooldown": totem_Cooldown = (int) value; break;
                        case "totem.RegenAmp": totem_RegenAmp = (int) value; break;
                        case "totem.RegenDuration": totem_RegenDuration = (int) value; break;
                        case "totem.AbsorbAmp": totem_AbsorbAmp = (int) value; break;
                        case "totem.AbsorbDuration": totem_AbsorbDuration = (int) value; break;
                        case "totem.FireResAmp": totem_FireResAmp = (int) value; break;
                        case "totem.FireResDuration": totem_FireResDuration = (int) value; break;

                        case "ISS.MaxMana": ISS_MaxMana = (int) value; break;
                        case "ISS.ManaRegen": ISS_ManaRegen = value; break;
                        case "ISS.SpellPower": ISS_SpellPower = value; break;
                        case "ISS.SpellResist": ISS_SpellResist = value; break;

                        case "cursium.GhostFormDuration": cursium_GhostFormDuration = (int) value; break;
                        case "cursium.FireResistDuration": cursium_FireResistDuration = (int) value; break;
                        case "cursium.DodgeChance": cursium_DodgeChance = value; break;
                        case "cursium.ProjectileDodgeChance": cursium_ProjectileDodgeChance = value; break;

                        case "WNS.Amp": WNSBaseAmp = (int) value; break;
                        case "WNS.BoostedAmp": WNSBoostedAmp = (int) value; break;
                        case "WNS.Speed": WNSSpeed = (int) value; break;
                        case "WNS.Haste": WNSHaste = (int) value; break;
                        case "WNS.Resistance": WNSResistance = value; break;
                        case "WNS.ResistancePerLv": WNSResistAmountPerLevel = value; break;
                        case "WNS.JumpBoost": WNSJumpBoost = (int) value; break;
                        case "WNS.Base_Strength": WNSAttack = value; break;

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
            logger.info("Config version outdated, Updating config \"catalyst_armor_config\"!");
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
            writer.write("[Catalyst]\n");
            writer.write("# Default: " + Durability +"\n");
            writer.write("  Durability = " + Durability + "\n");
            writer.write("# Default: " + Enchantability + "\n");
            writer.write("  Enchantability = " + Enchantability + "\n");
            writer.write("# Default: " + Armor + "\n");
            writer.write("  ArmorProtection = " + Armor + "\n");
            writer.write("# Default: " + Toughness + "\n");
            writer.write("  ArmorToughness = " + Toughness + "\n");
            writer.write("# Default: " + KnockbackResist + "\n");
            writer.write("  KnockbackResistance = " + KnockbackResist + "\n");
            writer.write("# Default: " + AttackSpeed + "\n");
            writer.write("  AttackSpeedBoost = " + AttackSpeed + "\n");
            writer.write("# Default: " + MovementSpeed + "\n");
            writer.write("  MovementSpeed = " + MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[NetherStar]\n");
            writer.write("# Cooldown: 350 (20 = 1 second)\n");
            writer.write("# Default: " + NSCooldown + "\n");
            writer.write("  NS.Cooldown = " + NSCooldown + "\n");
            writer.write("# Default: " + NSDuration + "\n");
            writer.write("  NS.BoostedDuration = " + NSDuration + "\n");
            writer.write("# Default: " + NSBaseAmp + "\n");
            writer.write("  NS.Regen = " + NSBaseAmp + "\n");
            writer.write("# Default: " + NSBoostedAmp + "\n");
            writer.write("  NS.BoostedAmp = " + NSBoostedAmp + "\n");
            writer.write("# Default: " + NSHaste + "\n");
            writer.write("  NS.Haste = " + NSHaste + "\n");
            writer.write("# Default: " + NSSpeed + "\n");
            writer.write("  NS.Speed = " + NSSpeed + "\n");
            writer.write("# Default: " + NSResistance + "\n");
            writer.write("  NS.Resistance = " + NSResistance + "\n");
            writer.write("# Default: " + NSResistAmountPerLevel + "\n");
            writer.write("  NS.ResistancePerLv = " + NSResistAmountPerLevel + "\n");
            writer.write("# Default: " + NSJumpBoost + "\n");
            writer.write("  NS.JumpBoost = " + NSJumpBoost + "\n");
            writer.write("# Default: " + NSAttack + "\n");
            writer.write("  NS.Strength = " + NSAttack + "\n");
            writer.write("\n");
            writer.write("[Cataclysm.Ignitium]\n");
            writer.write("# Default: " + ignitium_HasteAmp + "\n");
            writer.write("  ignitium.HasteAmp = " + ignitium_HasteAmp + "\n");
            writer.write("# Default: " + ignitium_HasteDuration + "\n");
            writer.write("  ignitium.HasteDuration = " + ignitium_HasteDuration + "\n");
            writer.write("# Default: " + ignitium_StrengthAmp + "\n");
            writer.write("  ignitium.StrengthAmp = " + ignitium_StrengthAmp + "\n");
            writer.write("# Default: " + ignitium_StrengthDuration + "\n");
            writer.write("  ignitium.StrengthDuration = " + ignitium_StrengthDuration + "\n");
            writer.write("# Default: " + ignitium_ResistanceAmp + "\n");
            writer.write("  ignitium.ResistanceAmp = " + ignitium_StrengthAmp + "\n");
            writer.write("# Default: " + ignitium_ResistanceDuration + "\n");
            writer.write("  ignitium.ResistanceDuration = " + ignitium_ResistanceDuration + "\n");
            writer.write("\n");
            writer.write("[Totem]\n");
            writer.write("# Default: " + totem_Cooldown + "(20 = 1 second)\n");
            writer.write("  totem.Cooldown = " + totem_Cooldown + "\n");
            writer.write("# Default: " + totem_RegenAmp + "\n");
            writer.write("  totem.RegenAmp = " + totem_RegenAmp + "\n");
            writer.write("# Default: " + totem_RegenDuration + "\n");
            writer.write("  totem.RegenDuration = " + totem_RegenDuration + "\n");
            writer.write("# Default: " + totem_AbsorbAmp + "\n");
            writer.write("  totem.AbsorbAmp = " + totem_AbsorbAmp + "\n");
            writer.write("# Default: " + totem_AbsorbDuration + "\n");
            writer.write("  totem.AbsorbDuration = " + totem_AbsorbDuration + "\n");
            writer.write("# Default: " + totem_FireResAmp + "\n");
            writer.write("  totem.FireResAmp = " + totem_FireResAmp + "\n");
            writer.write("# Default: " + totem_FireResDuration + "\n");
            writer.write("  totem.FireResDuration = " + totem_FireResDuration + "\n");
            writer.write("\n");
            writer.write("[IronsSpellbooks.Arcane]\n");
            writer.write("# Default: " + ISS_MaxMana + "\n");
            writer.write("  ISS.MaxMana = " + ISS_MaxMana + "\n");
            writer.write("# Default: " + ISS_ManaRegen + "\n");
            writer.write("  ISS.ManaRegen = " + ISS_ManaRegen + "\n");
            writer.write("# Default: " + ISS_SpellPower + "\n");
            writer.write("  ISS.SpellPower = " + ISS_SpellPower + "\n");
            writer.write("# Default: " + ISS_SpellResist + "\n");
            writer.write("  ISS.SpellResist = " + ISS_SpellResist + "\n");
            writer.write("\n");
            writer.write("[Cataclysm.Cursium]\n");
            writer.write("# Default: " + cursium_GhostFormDuration + "\n");
            writer.write("  cursium.GhostFormDuration = " + cursium_GhostFormDuration + "\n");
            writer.write("# Default: " + cursium_FireResistDuration + "\n");
            writer.write("  cursium.FireResistDuration = " + cursium_FireResistDuration + "\n");
            writer.write("# Default: " + cursium_DodgeChance + "\n");
            writer.write("  cursium.DodgeChance = " + cursium_DodgeChance + "\n");
            writer.write("# Default: " + cursium_ProjectileDodgeChance + "\n");
            writer.write("  cursium.ProjectileDodgeChance = " + cursium_ProjectileDodgeChance + "\n");
            writer.write("\n");
            writer.write("[WitherStorm.WitheredNetherStar]\n");
            writer.write("# Cooldown for Nether Star's ability\n");
            writer.write("# Default: " + WNSBaseAmp + "\n");
            writer.write("  WNS.Amp = " + WNSBaseAmp + "\n");
            writer.write("# Default: " + WNSBoostedAmp + "\n");
            writer.write("  WNS.BoostedAmp = " + WNSBoostedAmp + "\n");
            writer.write("# Default: " + WNSRegenPenalty + "\n");
            writer.write("  WNS.RegenPenalty = " + WNSRegenPenalty + "\n");
            writer.write("# Default: " + WNSHaste + "\n");
            writer.write("  WNS.Haste = " + WNSHaste + "\n");
            writer.write("# Default: " + WNSSpeed + "\n");
            writer.write("  WNS.Speed = " + WNSSpeed + "\n");
            writer.write("# Default: " + WNSResistance + "\n");
            writer.write("  WNS.Resistance = " + WNSResistance + "\n");
            writer.write("# Default: " + WNSResistAmountPerLevel + "\n");
            writer.write("  WNS.ResistancePerLv = " + WNSResistAmountPerLevel + "\n");
            writer.write("# Default: " + WNSJumpBoost + "\n");
            writer.write("  WNS.JumpBoost = " + WNSJumpBoost + "\n");
            writer.write("# Default: " + WNSAttack + "\n");
            writer.write("  WNS.Strength = " + WNSAttack + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
