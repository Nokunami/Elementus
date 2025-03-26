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
public class SSConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final SSConfig INSTANCE = new SSConfig();
    private static final Path CONFIG_PATH = Elementus.SS_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    // SimplySwords
    public static int steelDamage;
    public static int diarkriteDamage;
    public static int anthektiteDamage;

    public static int longswordDamageModifier;
    public static int twinbladeDamageModifier;
    public static int rapierDamageModifier;
    public static int katanaDamageModifier;
    public static int saiDamageModifier;
    public static int spearDamageModifier;
    public static int glaiveDamageModifier;
    public static int warglaiveDamageModifier;
    public static int cutlassDamageModifier;
    public static int claymoreDamageModifier;
    public static int greataxeDamageModifier;
    public static int greathammerDamageModifier;
    public static int chakramDamageModifier;
    public static int scytheDamageModifier;
    public static int halberdDamageModifier;

    public static double longswordAttackSpeed;
    public static double twinbladeAttackSpeed;
    public static double rapierAttackSpeed;
    public static double katanaAttackSpeed;
    public static double saiAttackSpeed;
    public static double spearAttackSpeed;
    public static double glaiveAttackSpeed;
    public static double warglaiveAttackSpeed;
    public static double cutlassAttackSpeed;
    public static double claymoreAttackSpeed;
    public static double greataxeAttackSpeed;
    public static double greathammerAttackSpeed;
    public static double chakramAttackSpeed;
    public static double scytheAttackSpeed;
    public static double halberdAttackSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Simply Swords Config loaded");
    }

    private void setDefaults() {
        // SimplySwords
        steelDamage = 3;
        diarkriteDamage = 3;
        anthektiteDamage = 3;

        longswordDamageModifier = 0;
        twinbladeDamageModifier = 0;
        rapierDamageModifier = -1;
        katanaDamageModifier = 0;
        saiDamageModifier = -3;
        spearDamageModifier = 0;
        glaiveDamageModifier = 0;
        warglaiveDamageModifier = 0;
        cutlassDamageModifier = 0;
        claymoreDamageModifier = 2;
        greataxeDamageModifier = 3;
        greathammerDamageModifier = 4;
        chakramDamageModifier = -1;
        scytheDamageModifier = 1;
        halberdDamageModifier = 3;

        longswordAttackSpeed = -2.4;
        twinbladeAttackSpeed = -2.0;
        rapierAttackSpeed = -1.8;
        katanaAttackSpeed = -2.0;
        saiAttackSpeed = -1.5;
        spearAttackSpeed = -2.7;
        glaiveAttackSpeed = -2.6;
        warglaiveAttackSpeed = -2.2;
        cutlassAttackSpeed = -2.0;
        claymoreAttackSpeed = -2.8;
        greataxeAttackSpeed = -3.1;
        greathammerAttackSpeed = -3.2;
        chakramAttackSpeed = -3.0;
        scytheAttackSpeed = -2.7;
        halberdAttackSpeed = -2.8;
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
                        // SimplySwords
                        case "steel.BaseDamage": steelDamage = (int) value; break;
                        case "diarkrite.BaseDamage": diarkriteDamage = (int) value; break;
                        case "anthektite.BaseDamage": anthektiteDamage = (int) value; break;

                        case "longsword.damageModifier": longswordDamageModifier = (int) value; break;
                        case "twinblade.damageModifier": twinbladeDamageModifier = (int) value; break;
                        case "rapier.damageModifier": rapierDamageModifier = (int) value; break;
                        case "katana.damageModifier": katanaDamageModifier = (int) value; break;
                        case "sai.damageModifier": saiDamageModifier = (int) value; break;
                        case "spear.damageModifier": spearDamageModifier = (int) value; break;
                        case "glaive.damageModifier": glaiveDamageModifier = (int) value; break;
                        case "warglaive.damageModifier": warglaiveDamageModifier = (int) value; break;
                        case "cutlass.damageModifier": cutlassDamageModifier = (int) value; break;
                        case "claymore.damageModifier": claymoreDamageModifier = (int) value; break;
                        case "greataxe.damageModifier": greataxeDamageModifier = (int) value; break;
                        case "greathammer.damageModifier": greathammerDamageModifier = (int) value; break;
                        case "chakram.damageModifier": chakramDamageModifier = (int) value; break;
                        case "scythe.damageModifier": scytheDamageModifier = (int) value; break;
                        case "halberd.damageModifier": halberdDamageModifier = (int) value; break;

                        case "longsword.attackSpeed": longswordAttackSpeed = value; break;
                        case "twinblade.attackSpeed": twinbladeAttackSpeed = value; break;
                        case "rapier.attackSpeed": rapierAttackSpeed = value; break;
                        case "katana.attackSpeed": katanaAttackSpeed = value; break;
                        case "sai.attackSpeed": saiAttackSpeed = value; break;
                        case "spear.attackSpeed": spearAttackSpeed = value; break;
                        case "glaive.attackSpeed": glaiveAttackSpeed = value; break;
                        case "warglaive.attackSpeed": warglaiveAttackSpeed = value; break;
                        case "cutlass.attackSpeed": cutlassAttackSpeed = value; break;
                        case "claymore.attackSpeed": claymoreAttackSpeed = value; break;
                        case "greataxe.attackSpeed": greataxeAttackSpeed = value; break;
                        case "greathammer.attackSpeed": greathammerAttackSpeed = value; break;
                        case "chakram.attackSpeed": chakramAttackSpeed = value; break;
                        case "scythe.attackSpeed": scytheAttackSpeed = value; break;
                        case "halberd.attackSpeed": halberdAttackSpeed = value; break;
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
            logger.info("Config version outdated, Updating config \"simply_sword_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Simply Swords Item Stats Config\n");
            writer.write("\n");
            writer.write("[SimplySwords.BaseDamage]\n");
            writer.write("# Default: " + steelDamage + "\n");
            writer.write("  steel.BaseDamage = " + steelDamage + "\n");
            writer.write("# Default: " + diarkriteDamage + "\n");
            writer.write("  diarkrite.BaseDamage = " + diarkriteDamage + "\n");
            writer.write("# Default: " + anthektiteDamage + "\n");
            writer.write("  anthektite.BaseDamage = " + anthektiteDamage + "\n");
            writer.write("\n");
            writer.write("[SimplySwords.DamageModifier]\n");
            writer.write("# Default: " + longswordDamageModifier + "\n");
            writer.write("  longsword.damageModifier = " + longswordDamageModifier + "\n");
            writer.write("# Default: " + twinbladeDamageModifier + "\n");
            writer.write("  twinblade.damageModifier = " + twinbladeDamageModifier + "\n");
            writer.write("# Default: " + rapierDamageModifier + "\n");
            writer.write("  rapier.damageModifier = " + rapierDamageModifier + "\n");
            writer.write("# Default: " + katanaDamageModifier + "\n");
            writer.write("  katana.damageModifier = " + katanaDamageModifier + "\n");
            writer.write("# Default: " + saiDamageModifier + "\n");
            writer.write("  sai.damageModifier = " + saiDamageModifier + "\n");
            writer.write("# Default: " + spearDamageModifier + "\n");
            writer.write("  spear.damageModifier = " + spearDamageModifier + "\n");
            writer.write("# Default: " + glaiveDamageModifier + "\n");
            writer.write("  glaive.damageModifier = " + glaiveDamageModifier + "\n");
            writer.write("# Default: " + warglaiveDamageModifier + "\n");
            writer.write("  warglaive.damageModifier = " + warglaiveDamageModifier + "\n");
            writer.write("# Default: " + cutlassDamageModifier + "\n");
            writer.write("  cutlass.damageModifier = " + cutlassDamageModifier + "\n");
            writer.write("# Default: " + claymoreDamageModifier + "\n");
            writer.write("  claymore.damageModifier = " + claymoreDamageModifier + "\n");
            writer.write("# Default: " + greataxeDamageModifier + "\n");
            writer.write("  greataxe.damageModifier = " + greataxeDamageModifier + "\n");
            writer.write("# Default: " + greathammerDamageModifier + "\n");
            writer.write("  greathammer.damageModifier = " + greathammerDamageModifier + "\n");
            writer.write("# Default: " + chakramDamageModifier + "\n");
            writer.write("  chakram.damageModifier = " + chakramDamageModifier + "\n");
            writer.write("# Default: " + scytheDamageModifier + "\n");
            writer.write("  scythe.damageModifier = " + scytheDamageModifier + "\n");
            writer.write("# Default: " + halberdDamageModifier + "\n");
            writer.write("  halberd.damageModifier = " + halberdDamageModifier + "\n");
            writer.write("\n");
            writer.write("[SimplySwords.AttackSpeedModifier]\n");
            writer.write("# Default: " + longswordAttackSpeed + "\n");
            writer.write("  longsword.attackSpeed = " + longswordAttackSpeed + "\n");
            writer.write("# Default: " + twinbladeAttackSpeed + "\n");
            writer.write("  twinblade.attackSpeed = " + twinbladeAttackSpeed + "\n");
            writer.write("# Default: " + rapierAttackSpeed + "\n");
            writer.write("  rapier.attackSpeed = " + rapierAttackSpeed + "\n");
            writer.write("# Default: " + katanaAttackSpeed + "\n");
            writer.write("  katana.attackSpeed = " + katanaAttackSpeed + "\n");
            writer.write("# Default: " + saiAttackSpeed + "\n");
            writer.write("  sai.attackSpeed = " + saiAttackSpeed + "\n");
            writer.write("# Default: " + spearAttackSpeed + "\n");
            writer.write("  spear.attackSpeed = " + spearAttackSpeed + "\n");
            writer.write("# Default: " + glaiveAttackSpeed + "\n");
            writer.write("  glaive.attackSpeed = " + glaiveAttackSpeed + "\n");
            writer.write("# Default: " + warglaiveAttackSpeed + "\n");
            writer.write("  warglaive.attackSpeed = " + warglaiveAttackSpeed + "\n");
            writer.write("# Default: " + cutlassAttackSpeed + "\n");
            writer.write("  cutlass.attackSpeed = " + cutlassAttackSpeed + "\n");
            writer.write("# Default: " + claymoreAttackSpeed + "\n");
            writer.write("  claymore.attackSpeed = " + claymoreAttackSpeed + "\n");
            writer.write("# Default: " + greataxeAttackSpeed + "\n");
            writer.write("  greataxe.attackSpeed = " + greataxeAttackSpeed + "\n");
            writer.write("# Default: " + greathammerAttackSpeed + "\n");
            writer.write("  greathammer.attackSpeed = " + greathammerAttackSpeed + "\n");
            writer.write("# Default: " + chakramAttackSpeed + "\n");
            writer.write("  chakram.attackSpeed = " + chakramAttackSpeed + "\n");
            writer.write("# Default: " + scytheAttackSpeed + "\n");
            writer.write("  scythe.attackSpeed = " + scytheAttackSpeed + "\n");
            writer.write("# Default: " + halberdAttackSpeed + "\n");
            writer.write("  halberd.attackSpeed = " + halberdAttackSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
