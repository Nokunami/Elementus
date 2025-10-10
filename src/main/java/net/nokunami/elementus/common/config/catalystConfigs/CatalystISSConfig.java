package net.nokunami.elementus.common.config.catalystConfigs;

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
public class CatalystISSConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final CatalystISSConfig INSTANCE = new CatalystISSConfig();
    private static final Path CONFIG_PATH = Elementus.CATALYST_ISS_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Armor
    public static int ISS_MaxMana;
    public static double ISS_ManaRegen;
    public static double ISS_SpellPower;
    public static double ISS_SpellResist;
    
    public static double fireRune_MaxMana;
    public static double fireRune_ManaRegen;
    public static double fireRune_SPower;
    public static double fireRune_SResist;
    public static double fireRune_Power;
    public static double fireRune_Resist;
    
    public static double iceRune_MaxMana;
    public static double iceRune_ManaRegen;
    public static double iceRune_SPower;
    public static double iceRune_SResist;
    public static double iceRune_Power;
    public static double iceRune_Resist;
    
    public static double lightningRune_MaxMana;
    public static double lightningRune_ManaRegen;
    public static double lightningRune_SPower;
    public static double lightningRune_SResist;
    public static double lightningRune_Power;
    public static double lightningRune_Resist;
    
    public static double holyRune_MaxMana;
    public static double holyRune_ManaRegen;
    public static double holyRune_SPower;
    public static double holyRune_SResist;
    public static double holyRune_Power;
    public static double holyRune_Resist;
    
    public static double enderRune_MaxMana;
    public static double enderRune_ManaRegen;
    public static double enderRune_SPower;
    public static double enderRune_SResist;
    public static double enderRune_Power;
    public static double enderRune_Resist;
    
    public static double bloodRune_MaxMana;
    public static double bloodRune_ManaRegen;
    public static double bloodRune_SPower;
    public static double bloodRune_SResist;
    public static double bloodRune_Power;
    public static double bloodRune_Resist;
    
    public static double evocationRune_MaxMana;
    public static double evocationRune_ManaRegen;
    public static double evocationRune_SPower;
    public static double evocationRune_SResist;
    public static double evocationRune_Power;
    public static double evocationRune_Resist;
    
    public static double natureRune_MaxMana;
    public static double natureRune_ManaRegen;
    public static double natureRune_SPower;
    public static double natureRune_SResist;
    public static double natureRune_Power;
    public static double natureRune_Resist;
    
    public static double arcaneRune_MaxMana;
    public static double arcaneRune_ManaRegen;
    public static double arcaneRune_SPower;
    public static double arcaneRune_SResist;
    
    public static double cooldownRune_MaxMana;
    public static double cooldownRune_ManaRegen;
    public static double cooldownRune_SPower;
    public static double cooldownRune_SResist;
    public static double cooldownRune_Cooldown;
    
    public static double protectionRune_MaxMana;
    public static double protectionRune_ManaRegen;
    public static double protectionRune_SPower;
    public static double protectionRune_SResist;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Catalyst Core (Iron's Spells 'n Spellbooks) Config loaded");
    }

    private void setDefaults() {
        ISS_MaxMana = 300;
        ISS_ManaRegen = 0.0;
        ISS_SpellPower = 0.0;
        ISS_SpellResist = 0.0;

        fireRune_MaxMana = 150;
        fireRune_ManaRegen = 0.0;
        fireRune_SPower = 0.1;
        fireRune_SResist = 0.0;
        fireRune_Power = 0.2;
        fireRune_Resist = 0.1;

        iceRune_MaxMana = 150;
        iceRune_ManaRegen = 0.0;
        iceRune_SPower = 0.1;
        iceRune_SResist = 0.0;
        iceRune_Power = 0.2;
        iceRune_Resist = 0.1;

        lightningRune_MaxMana = 150;
        lightningRune_ManaRegen = 0.0;
        lightningRune_SPower = 0.1;
        lightningRune_SResist = 0.0;
        lightningRune_Power = 0.2;
        lightningRune_Resist = 0.1;

        holyRune_MaxMana = 150;
        holyRune_ManaRegen = 0.0;
        holyRune_SPower = 0.1;
        holyRune_SResist = 0.0;
        holyRune_Power = 0.2;
        holyRune_Resist = 0.1;

        enderRune_MaxMana = 150;
        enderRune_ManaRegen = 0.0;
        enderRune_SPower = 0.1;
        enderRune_SResist = 0.0;
        enderRune_Power = 0.2;
        enderRune_Resist = 0.1;

        bloodRune_MaxMana = 150;
        bloodRune_ManaRegen = 0.0;
        bloodRune_SPower = 0.1;
        bloodRune_SResist = 0.0;
        bloodRune_Power = 0.2;
        bloodRune_Resist = 0.1;

        evocationRune_MaxMana = 150;
        evocationRune_ManaRegen = 0.0;
        evocationRune_SPower = 0.1;
        evocationRune_SResist = 0.0;
        evocationRune_Power = 0.2;
        evocationRune_Resist = 0.1;

        natureRune_MaxMana = 150;
        natureRune_ManaRegen = 0.0;
        natureRune_SPower = 0.1;
        natureRune_SResist = 0.0;
        natureRune_Power = 0.2;
        natureRune_Resist = 0.1;

        arcaneRune_MaxMana = 350;
        arcaneRune_ManaRegen = 0.0;
        arcaneRune_SPower = 0.0;
        arcaneRune_SResist = 0.0;

        cooldownRune_MaxMana = 25;
        cooldownRune_ManaRegen = 0.1;
        cooldownRune_SPower = 0.0;
        cooldownRune_SResist = 0.0;
        cooldownRune_Cooldown = 0.3;

        protectionRune_MaxMana = 50;
        protectionRune_ManaRegen = 0.0;
        protectionRune_SPower = 0.0;
        protectionRune_SResist = 0.5;
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
                        case "ISS.MaxMana": ISS_MaxMana = (int) value; break;
                        case "ISS.ManaRegen": ISS_ManaRegen = value; break;
                        case "ISS.SpellPower": ISS_SpellPower = value; break;
                        case "ISS.SpellResist": ISS_SpellResist = value; break;

                        case "FireRune.MaxMana": fireRune_MaxMana = (int) value; break;
                        case "FireRune.ManaRegen": fireRune_ManaRegen = value; break;
                        case "FireRune.SpellPower": fireRune_SPower = value; break;
                        case "FireRune.SpellResist": fireRune_SResist = value; break;
                        case "FireRune.Power": fireRune_Power = value; break;
                        case "FireRune.Resist": fireRune_Resist = value; break;

                        case "IceRune.MaxMana": iceRune_MaxMana = (int) value; break;
                        case "IceRune.ManaRegen": iceRune_ManaRegen = value; break;
                        case "IceRune.SpellPower": iceRune_SPower = value; break;
                        case "IceRune.SpellResist": iceRune_SResist = value; break;
                        case "IceRune.Power": iceRune_Power = value; break;
                        case "IceRune.Resist": iceRune_Resist = value; break;

                        case "LightningRune.MaxMana": lightningRune_MaxMana = (int) value; break;
                        case "LightningRune.ManaRegen": lightningRune_ManaRegen = value; break;
                        case "LightningRune.SpellPower": lightningRune_SPower = value; break;
                        case "LightningRune.SpellResist": lightningRune_SResist = value; break;
                        case "LightningRune.Power": lightningRune_Power = value; break;
                        case "LightningRune.Resist": lightningRune_Resist = value; break;

                        case "HolyRune.MaxMana": holyRune_MaxMana = (int) value; break;
                        case "HolyRune.ManaRegen": holyRune_ManaRegen = value; break;
                        case "HolyRune.SpellPower": holyRune_SPower = value; break;
                        case "HolyRune.SpellResist": holyRune_SResist = value; break;
                        case "HolyRune.Power": holyRune_Power = value; break;
                        case "HolyRune.Resist": holyRune_Resist = value; break;

                        case "EnderRune.MaxMana": enderRune_MaxMana = (int) value; break;
                        case "EnderRune.ManaRegen": enderRune_ManaRegen = value; break;
                        case "EnderRune.SpellPower": enderRune_SPower = value; break;
                        case "EnderRune.SpellResist": enderRune_SResist = value; break;
                        case "EnderRune.Power": enderRune_Power = value; break;
                        case "EnderRune.Resist": enderRune_Resist = value; break;

                        case "BloodRune.MaxMana": bloodRune_MaxMana = (int) value; break;
                        case "BloodRune.ManaRegen": bloodRune_ManaRegen = value; break;
                        case "BloodRune.SpellPower": bloodRune_SPower = value; break;
                        case "BloodRune.SpellResist": bloodRune_SResist = value; break;
                        case "BloodRune.Power": bloodRune_Power = value; break;
                        case "BloodRune.Resist": bloodRune_Resist = value; break;

                        case "EvocationRune.MaxMana": evocationRune_MaxMana = (int) value; break;
                        case "EvocationRune.ManaRegen": evocationRune_ManaRegen = value; break;
                        case "EvocationRune.SpellPower": evocationRune_SPower = value; break;
                        case "EvocationRune.SpellResist": evocationRune_SResist = value; break;
                        case "EvocationRune.Power": evocationRune_Power = value; break;
                        case "EvocationRune.Resist": evocationRune_Resist = value; break;

                        case "NatureRune.MaxMana": natureRune_MaxMana = (int) value; break;
                        case "NatureRune.ManaRegen": natureRune_ManaRegen = value; break;
                        case "NatureRune.SpellPower": natureRune_SPower = value; break;
                        case "NatureRune.SpellResist": natureRune_SResist = value; break;
                        case "NatureRune.Power": natureRune_Power = value; break;
                        case "NatureRune.Resist": natureRune_Resist = value; break;

                        case "ArcaneRune.MaxMana": arcaneRune_MaxMana = (int) value; break;
                        case "ArcaneRune.ManaRegen": arcaneRune_ManaRegen = value; break;
                        case "ArcaneRune.SpellPower": arcaneRune_SPower = value; break;
                        case "ArcaneRune.SpellResist": arcaneRune_SResist = value; break;

                        case "CooldownRune.MaxMana": cooldownRune_MaxMana = (int) value; break;
                        case "CooldownRune.ManaRegen": cooldownRune_ManaRegen = value; break;
                        case "CooldownRune.SpellPower": cooldownRune_SPower = value; break;
                        case "CooldownRune.SpellResist": cooldownRune_SResist = value; break;

                        case "ProtectionRune.MaxMana": protectionRune_MaxMana = (int) value; break;
                        case "ProtectionRune.ManaRegen": protectionRune_ManaRegen = value; break;
                        case "ProtectionRune.SpellPower": protectionRune_SPower = value; break;
                        case "ProtectionRune.SpellResist": protectionRune_SResist = value; break;

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
            writer.write("# Catalyst Core Config for Iron's Spells 'n Spellbooks\n");
            writer.write("\n");
            writer.write("# Format: \n");
            writer.write("# (default value) config_name: config_value \n");
            writer.write("# (150) IronsSpellbooks.IceRune: 150\n");
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
            writer.write("\n");
            writer.write("[IronsSpellbooks.FireRune]\n");
            writer.write("# Default: " + fireRune_MaxMana + "\n" + "  FireRune.MaxMana = " + fireRune_MaxMana + "\n");
            writer.write("# Default: " + fireRune_ManaRegen + "\n" + "  FireRune.ManaRegen = " + fireRune_ManaRegen + "\n");
            writer.write("# Default: " + fireRune_SPower + "\n" + "  FireRune.SpellPower = " + fireRune_SPower + "\n");
            writer.write("# Default: " + fireRune_SResist + "\n" + "  FireRune.SpellResist = " + fireRune_SResist + "\n");
            writer.write("# Default: " + fireRune_Power + "\n" + "  FireRune.Power = " + fireRune_Power + "\n");
            writer.write("# Default: " + fireRune_Resist + "\n" + "  FireRune.Resist = " + fireRune_Resist + "\n");
            writer.write("[IronsSpellbooks.IceRune]\n");
            writer.write("# Default: " + iceRune_MaxMana + "\n" + "  IceRune.MaxMana = " + iceRune_MaxMana + "\n");
            writer.write("# Default: " + iceRune_ManaRegen + "\n" + "  IceRune.ManaRegen = " + iceRune_ManaRegen + "\n");
            writer.write("# Default: " + iceRune_SPower + "\n" + "  IceRune.SpellPower = " + iceRune_SPower + "\n");
            writer.write("# Default: " + iceRune_SResist + "\n" + "  IceRune.SpellResist = " + iceRune_SResist + "\n");
            writer.write("# Default: " + iceRune_Power + "\n" + "  IceRune.Power = " + iceRune_Power + "\n");
            writer.write("# Default: " + iceRune_Resist + "\n" + "  IceRune.Resist = " + iceRune_Resist + "\n");
            writer.write("[IronsSpellbooks.LightningRune]\n");
            writer.write("# Default: " + lightningRune_MaxMana + "\n" + "  LightningRune.MaxMana = " + lightningRune_MaxMana + "\n");
            writer.write("# Default: " + lightningRune_ManaRegen + "\n" + "  LightningRune.ManaRegen = " + lightningRune_ManaRegen + "\n");
            writer.write("# Default: " + lightningRune_SPower + "\n" + "  LightningRune.SpellPower = " + lightningRune_SPower + "\n");
            writer.write("# Default: " + lightningRune_SResist + "\n" + "  LightningRune.SpellResist = " + lightningRune_SResist + "\n");
            writer.write("# Default: " + lightningRune_Power + "\n" + "  LightningRune.Power = " + lightningRune_Power + "\n");
            writer.write("# Default: " + lightningRune_Resist + "\n" + "  LightningRune.Resist = " + lightningRune_Resist + "\n");
            writer.write("[IronsSpellbooks.HolyRune]\n");
            writer.write("# Default: " + holyRune_MaxMana + "\n" + "  HolyRune.MaxMana = " + holyRune_MaxMana + "\n");
            writer.write("# Default: " + holyRune_ManaRegen + "\n" + "  HolyRune.ManaRegen = " + holyRune_ManaRegen + "\n");
            writer.write("# Default: " + holyRune_SPower + "\n" + "  HolyRune.SpellPower = " + holyRune_SPower + "\n");
            writer.write("# Default: " + holyRune_SResist + "\n" + "  HolyRune.SpellResist = " + holyRune_SResist + "\n");
            writer.write("# Default: " + holyRune_Power + "\n" + "  HolyRune.Power = " + holyRune_Power + "\n");
            writer.write("# Default: " + holyRune_Resist + "\n" + "  HolyRune.Resist = " + holyRune_Resist + "\n");
            writer.write("[IronsSpellbooks.EnderRune]\n");
            writer.write("# Default: " + enderRune_MaxMana + "\n" + "  EnderRune.MaxMana = " + enderRune_MaxMana + "\n");
            writer.write("# Default: " + enderRune_ManaRegen + "\n" + "  EnderRune.ManaRegen = " + enderRune_ManaRegen + "\n");
            writer.write("# Default: " + enderRune_SPower + "\n" + "  EnderRune.SpellPower = " + enderRune_SPower + "\n");
            writer.write("# Default: " + enderRune_SResist + "\n" + "  EnderRune.SpellResist = " + enderRune_SResist + "\n");
            writer.write("# Default: " + enderRune_Power + "\n" + "  EnderRune.Power = " + enderRune_Power + "\n");
            writer.write("# Default: " + enderRune_Resist + "\n" + "  EnderRune.Resist = " + enderRune_Resist + "\n");
            writer.write("[IronsSpellbooks.BloodRune]\n");
            writer.write("# Default: " + bloodRune_MaxMana + "\n" + "  BloodRune.MaxMana = " + bloodRune_MaxMana + "\n");
            writer.write("# Default: " + bloodRune_ManaRegen + "\n" + "  BloodRune.ManaRegen = " + bloodRune_ManaRegen + "\n");
            writer.write("# Default: " + bloodRune_SPower + "\n" + "  BloodRune.SpellPower = " + bloodRune_SPower + "\n");
            writer.write("# Default: " + bloodRune_SResist + "\n" + "  BloodRune.SpellResist = " + bloodRune_SResist + "\n");
            writer.write("# Default: " + bloodRune_Power + "\n" + "  BloodRune.Power = " + bloodRune_Power + "\n");
            writer.write("# Default: " + bloodRune_Resist + "\n" + "  BloodRune.Resist = " + bloodRune_Resist + "\n");
            writer.write("[IronsSpellbooks.EvocationRune]\n");
            writer.write("# Default: " + evocationRune_MaxMana + "\n" + "  EvocationRune.MaxMana = " + evocationRune_MaxMana + "\n");
            writer.write("# Default: " + evocationRune_ManaRegen + "\n" + "  EvocationRune.ManaRegen = " + evocationRune_ManaRegen + "\n");
            writer.write("# Default: " + evocationRune_SPower + "\n" + "  EvocationRune.SpellPower = " + evocationRune_SPower + "\n");
            writer.write("# Default: " + evocationRune_SResist + "\n" + "  EvocationRune.SpellResist = " + evocationRune_SResist + "\n");
            writer.write("# Default: " + evocationRune_Power + "\n" + "  EvocationRune.Power = " + evocationRune_Power + "\n");
            writer.write("# Default: " + evocationRune_Resist + "\n" + "  EvocationRune.Resist = " + evocationRune_Resist + "\n");
            writer.write("[IronsSpellbooks.NatureRune]\n");
            writer.write("# Default: " + natureRune_MaxMana + "\n" + "  NatureRune.MaxMana = " + natureRune_MaxMana + "\n");
            writer.write("# Default: " + natureRune_ManaRegen + "\n" + "  NatureRune.ManaRegen = " + natureRune_ManaRegen + "\n");
            writer.write("# Default: " + natureRune_SPower + "\n" + "  NatureRune.SpellPower = " + natureRune_SPower + "\n");
            writer.write("# Default: " + natureRune_SResist + "\n" + "  NatureRune.SpellResist = " + natureRune_SResist + "\n");
            writer.write("# Default: " + natureRune_Power + "\n" + "  NatureRune.Power = " + natureRune_Power + "\n");
            writer.write("# Default: " + natureRune_Resist + "\n" + "  NatureRune.Resist = " + natureRune_Resist + "\n");
            writer.write("[IronsSpellbooks.ArcaneRune]\n");
            writer.write("# Default: " + arcaneRune_MaxMana + "\n" + "  ArcaneRune.MaxMana = " + arcaneRune_MaxMana + "\n");
            writer.write("# Default: " + arcaneRune_ManaRegen + "\n" + "  ArcaneRune.ManaRegen = " + arcaneRune_ManaRegen + "\n");
            writer.write("# Default: " + arcaneRune_SPower + "\n" + "  ArcaneRune.SpellPower = " + arcaneRune_SPower + "\n");
            writer.write("# Default: " + arcaneRune_SResist + "\n" + "  ArcaneRune.SpellResist = " + arcaneRune_SResist + "\n");
            writer.write("[IronsSpellbooks.CooldownRune]\n");
            writer.write("# Default: " + cooldownRune_MaxMana + "\n" + "  CooldownRune.MaxMana = " + cooldownRune_MaxMana + "\n");
            writer.write("# Default: " + cooldownRune_ManaRegen + "\n" + "  CooldownRune.ManaRegen = " + cooldownRune_ManaRegen + "\n");
            writer.write("# Default: " + cooldownRune_SPower + "\n" + "  CooldownRune.SpellPower = " + cooldownRune_SPower + "\n");
            writer.write("# Default: " + cooldownRune_SResist + "\n" + "  CooldownRune.SpellResist = " + cooldownRune_SResist + "\n");
            writer.write("[IronsSpellbooks.ProtectionRune]\n");
            writer.write("# Default: " + protectionRune_MaxMana + "\n" + "  ProtectionRune.MaxMana = " + protectionRune_MaxMana + "\n");
            writer.write("# Default: " + protectionRune_ManaRegen + "\n" + "  ProtectionRune.ManaRegen = " + protectionRune_ManaRegen + "\n");
            writer.write("# Default: " + protectionRune_SPower + "\n" + "  ProtectionRune.SpellPower = " + protectionRune_SPower + "\n");
            writer.write("# Default: " + protectionRune_SResist + "\n" + "  ProtectionRune.SpellResist = " + protectionRune_SResist + "\n");
            writer.write("\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
