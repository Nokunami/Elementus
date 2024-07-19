package net.nokunami.elementus.common.config;

import net.nokunami.elementus.Elementus;
import org.apache.logging.log4j.Logger;
import org.apache.maven.artifact.versioning.ComparableVersion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.Locale;
import java.util.Scanner;

/**
 * credits: SkpC9 <a href="https://github.com/SkpC9/Simply-Steel/blob/main/src/main/java/com/trbz_/simplysteel/util/ConfigHandler.java">Link</a>
 * */
public class ToolsConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final ToolsConfig INSTANCE = new ToolsConfig();
    //    public static final String VERSION = "${mod_version}";
    public static final ComparableVersion VERSION = new ComparableVersion("1.0");
    // values exposed to other classes
    //Tools
    public static int steelSwordDamage;
    public static float steelSwordAttackSpeed;
    public static float steelShovelDamage;
    public static float steelShovelAttackSpeed;
    public static int steelPickaxeDamage;
    public static float steelPickaxeAttackSpeed;
    public static float steelAxeDamage;
    public static float steelAxeAttackSpeed;
    public static int steelHoeDamage;
    public static float steelHoeAttackSpeed;
    public static int steelShieldDurability;

    public static int diarkriteSwordDamage;
    public static float diarkriteSwordAttackSpeed;
    public static float diarkriteShovelDamage;
    public static float diarkriteShovelAttackSpeed;
    public static int diarkritePickaxeDamage;
    public static float diarkritePickaxeAttackSpeed;
    public static float diarkriteAxeDamage;
    public static float diarkriteAxeAttackSpeed;
    public static int diarkriteHoeDamage;
    public static float diarkriteHoeAttackSpeed;
    public static int diarkriteShieldDurability;

    public static int anthektiteSwordDamage;
    public static float anthektiteSwordAttackSpeed;
    public static float anthektiteShovelDamage;
    public static float anthektiteShovelAttackSpeed;
    public static int anthektitePickaxeDamage;
    public static float anthektitePickaxeAttackSpeed;
    public static float anthektiteAxeDamage;
    public static float anthektiteAxeAttackSpeed;
    public static int anthektiteHoeDamage;
    public static float anthektiteHoeAttackSpeed;
    public static int anthektiteShieldDurability;

    //AdvancedNetherite Tools Diarkrite
    public static int diarkriteIronSwordDamage;
    public static float diarkriteIronSwordAttackSpeed;
    public static float diarkriteIronShovelDamage;
    public static float diarkriteIronShovelAttackSpeed;
    public static int diarkriteIronPickaxeDamage;
    public static float diarkriteIronPickaxeAttackSpeed;
    public static float diarkriteIronAxeDamage;
    public static float diarkriteIronAxeAttackSpeed;
    public static int diarkriteIronHoeDamage;
    public static float diarkriteIronHoeAttackSpeed;

    public static int diarkriteGoldSwordDamage;
    public static float diarkriteGoldSwordAttackSpeed;
    public static float diarkriteGoldShovelDamage;
    public static float diarkriteGoldShovelAttackSpeed;
    public static int diarkriteGoldPickaxeDamage;
    public static float diarkriteGoldPickaxeAttackSpeed;
    public static float diarkriteGoldAxeDamage;
    public static float diarkriteGoldAxeAttackSpeed;
    public static int diarkriteGoldHoeDamage;
    public static float diarkriteGoldHoeAttackSpeed;

    public static int diarkriteEmeraldSwordDamage;
    public static float diarkriteEmeraldSwordAttackSpeed;
    public static float diarkriteEmeraldShovelDamage;
    public static float diarkriteEmeraldShovelAttackSpeed;
    public static int diarkriteEmeraldPickaxeDamage;
    public static float diarkriteEmeraldPickaxeAttackSpeed;
    public static float diarkriteEmeraldAxeDamage;
    public static float diarkriteEmeraldAxeAttackSpeed;
    public static int diarkriteEmeraldHoeDamage;
    public static float diarkriteEmeraldHoeAttackSpeed;

    public static int diarkriteDiamondSwordDamage;
    public static float diarkriteDiamondSwordAttackSpeed;
    public static float diarkriteDiamondShovelDamage;
    public static float diarkriteDiamondShovelAttackSpeed;
    public static int diarkriteDiamondPickaxeDamage;
    public static float diarkriteDiamondPickaxeAttackSpeed;
    public static float diarkriteDiamondAxeDamage;
    public static float diarkriteDiamondAxeAttackSpeed;
    public static int diarkriteDiamondHoeDamage;
    public static float diarkriteDiamondHoeAttackSpeed;

    //AdvancedNetherite Tools Anthektite
    public static int anthektiteIronSwordDamage;
    public static float anthektiteIronSwordAttackSpeed;
    public static float anthektiteIronShovelDamage;
    public static float anthektiteIronShovelAttackSpeed;
    public static int anthektiteIronPickaxeDamage;
    public static float anthektiteIronPickaxeAttackSpeed;
    public static float anthektiteIronAxeDamage;
    public static float anthektiteIronAxeAttackSpeed;
    public static int anthektiteIronHoeDamage;
    public static float anthektiteIronHoeAttackSpeed;

    public static int anthektiteGoldSwordDamage;
    public static float anthektiteGoldSwordAttackSpeed;
    public static float anthektiteGoldShovelDamage;
    public static float anthektiteGoldShovelAttackSpeed;
    public static int anthektiteGoldPickaxeDamage;
    public static float anthektiteGoldPickaxeAttackSpeed;
    public static float anthektiteGoldAxeDamage;
    public static float anthektiteGoldAxeAttackSpeed;
    public static int anthektiteGoldHoeDamage;
    public static float anthektiteGoldHoeAttackSpeed;

    public static int anthektiteEmeraldSwordDamage;
    public static float anthektiteEmeraldSwordAttackSpeed;
    public static float anthektiteEmeraldShovelDamage;
    public static float anthektiteEmeraldShovelAttackSpeed;
    public static int anthektiteEmeraldPickaxeDamage;
    public static float anthektiteEmeraldPickaxeAttackSpeed;
    public static float anthektiteEmeraldAxeDamage;
    public static float anthektiteEmeraldAxeAttackSpeed;
    public static int anthektiteEmeraldHoeDamage;
    public static float anthektiteEmeraldHoeAttackSpeed;

    public static int anthektiteDiamondSwordDamage;
    public static float anthektiteDiamondSwordAttackSpeed;
    public static float anthektiteDiamondShovelDamage;
    public static float anthektiteDiamondShovelAttackSpeed;
    public static int anthektiteDiamondPickaxeDamage;
    public static float anthektiteDiamondPickaxeAttackSpeed;
    public static float anthektiteDiamondAxeDamage;
    public static float anthektiteDiamondAxeAttackSpeed;
    public static int anthektiteDiamondHoeDamage;
    public static float anthektiteDiamondHoeAttackSpeed;


    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Configuration has been loaded");
    }

    private void setDefaults() {
        steelSwordDamage = 3;
        steelSwordAttackSpeed = -2.4F;
        steelShovelDamage = 1.5F;
        steelShovelAttackSpeed = -3.0F;
        steelPickaxeDamage = 1;
        steelPickaxeAttackSpeed = -2.8F;
        steelAxeDamage = 5F;
        steelAxeAttackSpeed = -3.1F;
        steelHoeDamage = -3;
        steelHoeAttackSpeed = 0.0F;
        steelShieldDurability = 457;

        diarkriteSwordDamage = 3;
        diarkriteSwordAttackSpeed = -2.4F;
        diarkriteShovelDamage = 1.5F;
        diarkriteShovelAttackSpeed = -3.0F;
        diarkritePickaxeDamage = 1;
        diarkritePickaxeAttackSpeed = -2.8F;
        diarkriteAxeDamage = 6F;
        diarkriteAxeAttackSpeed = -3.1F;
        diarkriteHoeDamage = -6;
        diarkriteHoeAttackSpeed = 0.0F;
        diarkriteShieldDurability = 843;

        anthektiteSwordDamage = 3;
        anthektiteSwordAttackSpeed = -2.4F;
        anthektiteShovelDamage = 1.5F;
        anthektiteShovelAttackSpeed = -3.0F;
        anthektitePickaxeDamage = 1;
        anthektitePickaxeAttackSpeed = -2.8F;
        anthektiteAxeDamage = 5F;
        anthektiteAxeAttackSpeed = -3.1F;
        anthektiteHoeDamage = -3;
        anthektiteHoeAttackSpeed = 0.0F;
        anthektiteShieldDurability = 598;

        //AdvancedNetherite Tools Diarkrite
        diarkriteIronSwordDamage = 3;
        diarkriteIronSwordAttackSpeed = -2.4F;
        diarkriteIronShovelDamage = 1.5F;
        diarkriteIronShovelAttackSpeed = -3.0F;
        diarkriteIronPickaxeDamage = 1;
        diarkriteIronPickaxeAttackSpeed = -2.8F;
        diarkriteIronAxeDamage = 6F;
        diarkriteIronAxeAttackSpeed = -3.1F;
        diarkriteIronHoeDamage = -3;
        diarkriteIronHoeAttackSpeed = -3.0F;

        diarkriteGoldSwordDamage = 4;
        diarkriteGoldSwordAttackSpeed = -2.4F;
        diarkriteGoldShovelDamage = 1.5F;
        diarkriteGoldShovelAttackSpeed = -3.0F;
        diarkriteGoldPickaxeDamage = 1;
        diarkriteGoldPickaxeAttackSpeed = -2.8F;
        diarkriteGoldAxeDamage = 7F;
        diarkriteGoldAxeAttackSpeed = -3.1F;
        diarkriteGoldHoeDamage = -3;
        diarkriteGoldHoeAttackSpeed = -3.0F;

        diarkriteEmeraldSwordDamage = 4;
        diarkriteEmeraldSwordAttackSpeed = -2.4F;
        diarkriteEmeraldShovelDamage = 1.5F;
        diarkriteEmeraldShovelAttackSpeed = -3.0F;
        diarkriteEmeraldPickaxeDamage = 1;
        diarkriteEmeraldPickaxeAttackSpeed = -2.8F;
        diarkriteEmeraldAxeDamage = 7F;
        diarkriteEmeraldAxeAttackSpeed = -3.1F;
        diarkriteEmeraldHoeDamage = -4;
        diarkriteEmeraldHoeAttackSpeed = -3.0F;

        diarkriteDiamondSwordDamage = 5;
        diarkriteDiamondSwordAttackSpeed = -2.4F;
        diarkriteDiamondShovelDamage = 1.5F;
        diarkriteDiamondShovelAttackSpeed = -3.0F;
        diarkriteDiamondPickaxeDamage = 1;
        diarkriteDiamondPickaxeAttackSpeed = -2.8F;
        diarkriteDiamondAxeDamage = 8F;
        diarkriteDiamondAxeAttackSpeed = -3.1F;
        diarkriteDiamondHoeDamage = -4;
        diarkriteDiamondHoeAttackSpeed = -3.0F;

        //AdvancedNetherite Tools Anthektite
        anthektiteIronSwordDamage = 3;
        anthektiteIronSwordAttackSpeed = -2.4F;
        anthektiteIronShovelDamage = 1.5F;
        anthektiteIronShovelAttackSpeed = -3.0F;
        anthektiteIronPickaxeDamage = 1;
        anthektiteIronPickaxeAttackSpeed = -2.8F;
        anthektiteIronAxeDamage = 6F;
        anthektiteIronAxeAttackSpeed = -3.1F;
        anthektiteIronHoeDamage = 1;
        anthektiteIronHoeAttackSpeed = -3.0F;

        anthektiteGoldSwordDamage = 4;
        anthektiteGoldSwordAttackSpeed = -2.4F;
        anthektiteGoldShovelDamage = 1.5F;
        anthektiteGoldShovelAttackSpeed = -3.0F;
        anthektiteGoldPickaxeDamage = 1;
        anthektiteGoldPickaxeAttackSpeed = -2.8F;
        anthektiteGoldAxeDamage = 7F;
        anthektiteGoldAxeAttackSpeed = -3.1F;
        anthektiteGoldHoeDamage = 1;
        anthektiteGoldHoeAttackSpeed = -3.0F;

        anthektiteEmeraldSwordDamage = 4;
        anthektiteEmeraldSwordAttackSpeed = -2.4F;
        anthektiteEmeraldShovelDamage = 1.5F;
        anthektiteEmeraldShovelAttackSpeed = -3.0F;
        anthektiteEmeraldPickaxeDamage = 1;
        anthektiteEmeraldPickaxeAttackSpeed = -2.8F;
        anthektiteEmeraldAxeDamage = 7F;
        anthektiteEmeraldAxeAttackSpeed = -3.1F;
        anthektiteEmeraldHoeDamage = 1;
        anthektiteEmeraldHoeAttackSpeed = -3.0F;

        anthektiteDiamondSwordDamage = 5;
        anthektiteDiamondSwordAttackSpeed = -2.4F;
        anthektiteDiamondShovelDamage = 1.5F;
        anthektiteDiamondShovelAttackSpeed = -3.0F;
        anthektiteDiamondPickaxeDamage = 1;
        anthektiteDiamondPickaxeAttackSpeed = -2.8F;
        anthektiteDiamondAxeDamage = 8F;
        anthektiteDiamondAxeAttackSpeed = -3.1F;
        anthektiteDiamondHoeDamage = 1;
        anthektiteDiamondHoeAttackSpeed = -3.0F;
    }

    private void load() {
//        String version = "0";
        ComparableVersion version = new ComparableVersion("0");
        try (BufferedReader reader = Files.newBufferedReader(Elementus.TOOLS_CONFIG_PATH)) {
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

                String errorPrefix = Elementus.TOOLS_CONFIG_PATH + ": line " + lineNumber + ": ";
                try (Scanner s = new Scanner(line)) {
                    s.useLocale(Locale.US);
                    s.useDelimiter("\\s*=\\s*");

                    if (!s.hasNext()) {
                        logger.warn(errorPrefix + "parameter name is missing");
                        continue;
                    }
                    String key = s.next().trim();
                    // use string version
                    if (key.equals("version")) {
                        if (!s.hasNext()) {
                            logger.warn(errorPrefix + "version number is missing");
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

                        case "steelSword_Damage": steelSwordDamage = (int) value; break;
                        case "steelSword_Speed": steelSwordAttackSpeed = (float) value; break;

                        case "steelShovel_Damage": steelShovelDamage = (float) value; break;
                        case "steelShovel_Speed": steelShovelAttackSpeed = (float) value; break;

                        case "steelPickaxe_Damage": steelPickaxeDamage = (int) value; break;
                        case "steelPickaxe_Speed": steelPickaxeAttackSpeed = (float) value; break;

                        case "steelAxe_Damage": steelAxeDamage = (float) value; break;
                        case "steelAxe_Speed": steelAxeAttackSpeed = (float) value; break;

                        case "steelHoe_Damage": steelHoeDamage = (int) value; break;
                        case "steelHoe_Speed": steelHoeAttackSpeed = (float) value; break;

                        case "steelShield_Durability": steelShieldDurability = (int) value; break;


                        case "diarkriteSword_Damage": diarkriteSwordDamage = (int) value; break;
                        case "diarkriteSword_Speed": diarkriteSwordAttackSpeed = (float) value; break;

                        case "diarkriteShovel_Damage": diarkriteShovelDamage = (float) value; break;
                        case "diarkriteShovel_Speed": diarkriteShovelAttackSpeed = (float) value; break;

                        case "diarkritePickaxe_Damage": diarkritePickaxeDamage = (int) value; break;
                        case "diarkritePickaxe_Speed": diarkritePickaxeAttackSpeed = (float) value; break;

                        case "diarkriteAxe_Damage": diarkriteAxeDamage = (float) value; break;
                        case "diarkriteAxe_Speed": diarkriteAxeAttackSpeed = (float) value; break;

                        case "diarkriteHoe_Damage": diarkriteHoeDamage = (int) value; break;
                        case "diarkriteHoe_Speed": diarkriteHoeAttackSpeed = (float) value; break;

                        case "diarkriteShield_Durability": diarkriteShieldDurability = (int) value; break;


                        case "anthektiteSword_Damage": anthektiteSwordDamage = (int) value; break;
                        case "anthektiteSword_Speed": anthektiteSwordAttackSpeed = (float) value; break;

                        case "anthektiteShovel_Damage": anthektiteShovelDamage = (float) value; break;
                        case "anthektiteShovel_Speed": anthektiteShovelAttackSpeed = (float) value; break;

                        case "anthektitePickaxe_Damage": anthektitePickaxeDamage = (int) value; break;
                        case "anthektitePickaxe_Speed": anthektitePickaxeAttackSpeed = (float) value; break;

                        case "anthektiteAxe_Damage": anthektiteAxeDamage = (float) value; break;
                        case "anthektiteAxe_Speed": anthektiteAxeAttackSpeed = (float) value; break;

                        case "anthektiteHoe_Damage": anthektiteHoeDamage = (int) value; break;
                        case "anthektiteHoe_Speed": anthektiteHoeAttackSpeed = (float) value; break;

                        case "anthektiteShield_Durability": anthektiteShieldDurability = (int) value; break;


                        case "diarkriteIronSword_Damage": diarkriteIronSwordDamage = (int) value; break;
                        case "diarkriteIronSword_Speed": diarkriteIronSwordAttackSpeed = (float) value; break;
                        case "diarkriteIronShovel_Damage": diarkriteIronShovelDamage = (float) value; break;
                        case "diarkriteIronShovel_Speed": diarkriteIronShovelAttackSpeed = (float) value; break;
                        case "diarkriteIronPickaxe_Damage": diarkriteIronPickaxeDamage = (int) value; break;
                        case "diarkriteIronPickaxe_Speed": diarkriteIronPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteIronAxe_Damage": diarkriteIronAxeDamage = (float) value; break;
                        case "diarkriteIronAxe_Speed": diarkriteIronAxeAttackSpeed = (float) value; break;
                        case "diarkriteIronHoe_Damage": diarkriteIronHoeDamage = (int) value; break;
                        case "diarkriteIronHoe_Speed": diarkriteIronHoeAttackSpeed = (float) value; break;

                        case "diarkriteGoldSword_Damage": diarkriteGoldSwordDamage = (int) value; break;
                        case "diarkriteGoldSword_Speed": diarkriteGoldSwordAttackSpeed = (float) value; break;
                        case "diarkriteGoldShovel_Damage": diarkriteGoldShovelDamage = (float) value; break;
                        case "diarkriteGoldShovel_Speed": diarkriteGoldShovelAttackSpeed = (float) value; break;
                        case "diarkriteGoldPickaxe_Damage": diarkriteGoldPickaxeDamage = (int) value; break;
                        case "diarkriteGoldPickaxe_Speed": diarkriteGoldPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteGoldAxe_Damage": diarkriteGoldAxeDamage = (float) value; break;
                        case "diarkriteGoldAxe_Speed": diarkriteGoldAxeAttackSpeed = (float) value; break;
                        case "diarkriteGoldHoe_Damage": diarkriteGoldHoeDamage = (int) value; break;
                        case "diarkriteGoldHoe_Speed": diarkriteGoldHoeAttackSpeed = (float) value; break;

                        case "diarkriteEmeraldSword_Damage": diarkriteEmeraldSwordDamage = (int) value; break;
                        case "diarkriteEmeraldSword_Speed": diarkriteEmeraldSwordAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldShovel_Damage": diarkriteEmeraldShovelDamage = (float) value; break;
                        case "diarkriteEmeraldShovel_Speed": diarkriteEmeraldShovelAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldPickaxe_Damage": diarkriteEmeraldPickaxeDamage = (int) value; break;
                        case "diarkriteEmeraldPickaxe_Speed": diarkriteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldAxe_Damage": diarkriteEmeraldAxeDamage = (float) value; break;
                        case "diarkriteEmeraldAxe_Speed": diarkriteEmeraldAxeAttackSpeed = (float) value; break;
                        case "diarkriteEmeraldHoe_Damage": diarkriteEmeraldHoeDamage = (int) value; break;
                        case "diarkriteEmeraldHoe_Speed": diarkriteEmeraldHoeAttackSpeed = (float) value; break;

                        case "diarkriteDiamondSword_Damage": diarkriteDiamondSwordDamage = (int) value; break;
                        case "diarkriteDiamondSword_Speed": diarkriteDiamondSwordAttackSpeed = (float) value; break;
                        case "diarkriteDiamondShovel_Damage": diarkriteDiamondShovelDamage = (float) value; break;
                        case "diarkriteDiamondShovel_Speed": diarkriteDiamondShovelAttackSpeed = (float) value; break;
                        case "diarkriteDiamondPickaxe_Damage": diarkriteDiamondPickaxeDamage = (int) value; break;
                        case "diarkriteDiamondPickaxe_Speed": diarkriteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteDiamondAxe_Damage": diarkriteDiamondAxeDamage = (float) value; break;
                        case "diarkriteDiamondAxe_Speed": diarkriteDiamondAxeAttackSpeed = (float) value; break;
                        case "diarkriteDiamondHoe_Damage": diarkriteDiamondHoeDamage = (int) value; break;
                        case "diarkriteDiamondHoe_Speed": diarkriteDiamondHoeAttackSpeed = (float) value; break;


                        case "anthektiteIronSword_Damage": anthektiteIronSwordDamage = (int) value; break;
                        case "anthektiteIronSword_Speed": anthektiteIronSwordAttackSpeed = (float) value; break;
                        case "anthektiteIronShovel_Damage": anthektiteIronShovelDamage = (float) value; break;
                        case "anthektiteIronShovel_Speed": anthektiteIronShovelAttackSpeed = (float) value; break;
                        case "anthektiteIronPickaxe_Damage": anthektiteIronPickaxeDamage = (int) value; break;
                        case "anthektiteIronPickaxe_Speed": anthektiteIronPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteIronAxe_Damage": anthektiteIronAxeDamage = (float) value; break;
                        case "anthektiteIronAxe_Speed": anthektiteIronAxeAttackSpeed = (float) value; break;
                        case "anthektiteIronHoe_Damage": anthektiteIronHoeDamage = (int) value; break;
                        case "anthektiteIronHoe_Speed": anthektiteIronHoeAttackSpeed = (float) value; break;

                        case "anthektiteGoldSword_Damage": anthektiteGoldSwordDamage = (int) value; break;
                        case "anthektiteGoldSword_Speed": anthektiteGoldSwordAttackSpeed = (float) value; break;
                        case "anthektiteGoldShovel_Damage": anthektiteGoldShovelDamage = (float) value; break;
                        case "anthektiteGoldShovel_Speed": anthektiteGoldShovelAttackSpeed = (float) value; break;
                        case "anthektiteGoldPickaxe_Damage": anthektiteGoldPickaxeDamage = (int) value; break;
                        case "anthektiteGoldPickaxe_Speed": anthektiteGoldPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteGoldAxe_Damage": anthektiteGoldAxeDamage = (float) value; break;
                        case "anthektiteGoldAxe_Speed": anthektiteGoldAxeAttackSpeed = (float) value; break;
                        case "anthektiteGoldHoe_Damage": anthektiteGoldHoeDamage = (int) value; break;
                        case "anthektiteGoldHoe_Speed": anthektiteGoldHoeAttackSpeed = (float) value; break;

                        case "anthektiteEmeraldSword_Damage": anthektiteEmeraldSwordDamage = (int) value; break;
                        case "anthektiteEmeraldSword_Speed": anthektiteEmeraldSwordAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldShovel_Damage": anthektiteEmeraldShovelDamage = (float) value; break;
                        case "anthektiteEmeraldShovel_Speed": anthektiteEmeraldShovelAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldPickaxe_Damage": anthektiteEmeraldPickaxeDamage = (int) value; break;
                        case "anthektiteEmeraldPickaxe_Speed": anthektiteEmeraldPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldAxe_Damage": anthektiteEmeraldAxeDamage = (float) value; break;
                        case "anthektiteEmeraldAxe_Speed": anthektiteEmeraldAxeAttackSpeed = (float) value; break;
                        case "anthektiteEmeraldHoe_Damage": anthektiteEmeraldHoeDamage = (int) value; break;
                        case "anthektiteEmeraldHoe_Speed": anthektiteEmeraldHoeAttackSpeed = (float) value; break;

                        case "anthektiteDiamondSword_Damage": anthektiteDiamondSwordDamage = (int) value; break;
                        case "anthektiteDiamondSword_Speed": anthektiteDiamondSwordAttackSpeed = (float) value; break;
                        case "anthektiteDiamondShovel_Damage": anthektiteDiamondShovelDamage = (float) value; break;
                        case "anthektiteDiamondShovel_Speed": anthektiteDiamondShovelAttackSpeed = (float) value; break;
                        case "anthektiteDiamondPickaxe_Damage": anthektiteDiamondPickaxeDamage = (int) value; break;
                        case "anthektiteDiamondPickaxe_Speed": anthektiteDiamondPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteDiamondAxe_Damage": anthektiteDiamondAxeDamage = (float) value; break;
                        case "anthektiteDiamondAxe_Speed": anthektiteDiamondAxeAttackSpeed = (float) value; break;
                        case "anthektiteDiamondHoe_Damage": anthektiteDiamondHoeDamage = (int) value; break;
                        case "anthektiteDiamondHoe_Speed": anthektiteDiamondHoeAttackSpeed = (float) value; break;
                        default:
                            logger.warn(errorPrefix + "unrecognized parameter name: " + key);
                    }
                }
            }
        } catch (NoSuchFileException e) {
            save();
            logger.info("Configuration file not found, default created");

        } catch (IOException e) {
            logger.warn("Could not read configuration file: ", e);
        }
        // may save twice, but not big deal
        if (version.compareTo(VERSION) < 0) {
            logger.info("Configuration file belongs to older version, updating");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(Elementus.TOOLS_CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config");
            writer.newLine();
            writer.newLine();
            writer.write("[ToolStats]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    steelSword_Damage = " + steelSwordDamage + "\n");
            writer.write("    steelSword_Speed = " + steelSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    steelShovel_Damage = " + steelShovelDamage + "\n");
            writer.write("    steelShovel_Speed = " + steelShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    steelPickaxe_Damage = " + steelPickaxeDamage + "\n");
            writer.write("    steelPickaxe_Speed = " + steelPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("    steelAxe_Damage = " + steelAxeDamage + "\n");
            writer.write("    steelAxe_Speed = " + steelAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("    steelHoe_Damage = " + steelHoeDamage + "\n");
            writer.write("    steelHoe_Speed = " + steelHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    steelShield_Durability = " + steelShieldDurability + "\n");
            writer.newLine();
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    diarkriteSword_Damage = " + diarkriteSwordDamage + "\n");
            writer.write("    diarkriteSword_Speed = " + diarkriteSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    diarkriteShovel_Damage = " + diarkriteShovelDamage + "\n");
            writer.write("    diarkriteShovel_Speed = " + diarkriteShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    diarkritePickaxe_Damage = " + diarkritePickaxeDamage + "\n");
            writer.write("    diarkritePickaxe_Speed = " + diarkritePickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 6), (Attack Speed: -3.1)\n");
            writer.write("    diarkriteAxe_Damage = " + diarkriteAxeDamage + "\n");
            writer.write("    diarkriteAxe_Speed = " + diarkriteAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("    diarkriteHoe_Damage = " + diarkriteHoeDamage + "\n");
            writer.write("    diarkriteHoe_Speed = " + diarkriteHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    diarkriteShield_Durability = " + diarkriteShieldDurability + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("    anthektiteSword_Damage = " + anthektiteSwordDamage + "\n");
            writer.write("    anthektiteSword_Speed = " + anthektiteSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("    anthektiteShovel_Damage = " + anthektiteShovelDamage + "\n");
            writer.write("    anthektiteShovel_Speed = " + anthektiteShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("    anthektitePickaxe_Damage = " + anthektitePickaxeDamage + "\n");
            writer.write("    anthektitePickaxe_Speed = " + anthektitePickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("    anthektiteAxe_Damage = " + anthektiteAxeDamage + "\n");
            writer.write("    anthektiteAxe_Speed = " + anthektiteAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("    anthektiteHoe_Damage = " + anthektiteHoeDamage + "\n");
            writer.write("    anthektiteHoe_Speed = " + anthektiteHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    # Default:457\n");
            writer.write("    anthektiteShield_Durability = " + anthektiteShieldDurability + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[AdvancedNetherite]\n");
            writer.write("  [ToolStats]\n");
            writer.write("    [Diarkrite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteIronSword_Damage = " + diarkriteIronSwordDamage + "\n");
            writer.write("        diarkriteIronSword_Speed = " + diarkriteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteIronShovel_Damage = " + diarkriteIronShovelDamage + "\n");
            writer.write("        diarkriteIronShovel_Speed = " + diarkriteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteIronPickaxe_Damage = " + diarkriteIronPickaxeDamage + "\n");
            writer.write("        diarkriteIronPickaxe_Speed = " + diarkriteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 6), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteIronAxe_Damage = " + diarkriteIronAxeDamage + "\n");
            writer.write("        diarkriteIronAxe_Speed = " + diarkriteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteIronHoe_Damage = " + diarkriteIronHoeDamage + "\n");
            writer.write("        diarkriteIronHoe_Speed = " + diarkriteIronHoeAttackSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteGoldSword_Damage = " + diarkriteGoldSwordDamage + "\n");
            writer.write("        diarkriteGoldSword_Speed = " + diarkriteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteGoldShovel_Damage = " + diarkriteGoldShovelDamage + "\n");
            writer.write("        diarkriteGoldShovel_Speed = " + diarkriteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteGoldPickaxe_Damage = " + diarkriteGoldPickaxeDamage + "\n");
            writer.write("        diarkriteGoldPickaxe_Speed = " + diarkriteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteGoldAxe_Damage = " + diarkriteGoldAxeDamage + "\n");
            writer.write("        diarkriteGoldAxe_Speed = " + diarkriteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteGoldHoe_Damage = " + diarkriteGoldHoeDamage + "\n");
            writer.write("        diarkriteGoldHoe_Speed = " + diarkriteGoldHoeAttackSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteEmeraldSword_Damage = " + diarkriteEmeraldSwordDamage + "\n");
            writer.write("        diarkriteEmeraldSword_Speed = " + diarkriteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteEmeraldShovel_Damage = " + diarkriteEmeraldShovelDamage + "\n");
            writer.write("        diarkriteEmeraldShovel_Speed = " + diarkriteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteEmeraldPickaxe_Damage = " + diarkriteEmeraldPickaxeDamage + "\n");
            writer.write("        diarkriteEmeraldPickaxe_Speed = " + diarkriteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteEmeraldAxe_Damage = " + diarkriteEmeraldAxeDamage + "\n");
            writer.write("        diarkriteEmeraldAxe_Speed = " + diarkriteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteEmeraldHoe_Damage = " + diarkriteEmeraldHoeDamage + "\n");
            writer.write("        diarkriteEmeraldHoe_Speed = " + diarkriteEmeraldHoeAttackSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("        diarkriteDiamondSword_Damage = " + diarkriteDiamondSwordDamage + "\n");
            writer.write("        diarkriteDiamondSword_Speed = " + diarkriteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        diarkriteDiamondShovel_Damage = " + diarkriteDiamondShovelDamage + "\n");
            writer.write("        diarkriteDiamondShovel_Speed = " + diarkriteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        diarkriteDiamondPickaxe_Damage = " + diarkriteDiamondPickaxeDamage + "\n");
            writer.write("        diarkriteDiamondPickaxe_Speed = " + diarkriteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 8), (Attack Speed: -3.1)\n");
            writer.write("        diarkriteDiamondAxe_Damage = " + diarkriteDiamondAxeDamage + "\n");
            writer.write("        diarkriteDiamondAxe_Speed = " + diarkriteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("        diarkriteDiamondHoe_Damage = " + diarkriteDiamondHoeDamage + "\n");
            writer.write("        diarkriteDiamondHoe_Speed = " + diarkriteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("    [Anthektite]\n");
            writer.write("      [Iron]\n");
            writer.write("        # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteIronSword_Damage = " + anthektiteIronSwordDamage + "\n");
            writer.write("        anthektiteIronSword_Speed = " + anthektiteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteIronShovel_Damage = " + anthektiteIronShovelDamage + "\n");
            writer.write("        anthektiteIronShovel_Speed = " + anthektiteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteIronPickaxe_Damage = " + anthektiteIronPickaxeDamage + "\n");
            writer.write("        anthektiteIronPickaxe_Speed = " + anthektiteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteIronAxe_Damage = " + anthektiteIronAxeDamage + "\n");
            writer.write("        anthektiteIronAxe_Speed = " + anthektiteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteIronHoe_Damage = " + anthektiteIronHoeDamage + "\n");
            writer.write("        anthektiteIronHoe_Speed = " + anthektiteIronHoeAttackSpeed + "\n");
            writer.write("      [Gold]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteGoldSword_Damage = " + anthektiteGoldSwordDamage + "\n");
            writer.write("        anthektiteGoldSword_Speed = " + anthektiteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteGoldShovel_Damage = " + anthektiteGoldShovelDamage + "\n");
            writer.write("        anthektiteGoldShovel_Speed = " + anthektiteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteGoldPickaxe_Damage = " + anthektiteGoldPickaxeDamage + "\n");
            writer.write("        anthektiteGoldPickaxe_Speed = " + anthektiteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteGoldAxe_Damage = " + anthektiteGoldAxeDamage + "\n");
            writer.write("        anthektiteGoldAxe_Speed = " + anthektiteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteGoldHoe_Damage = " + anthektiteGoldHoeDamage + "\n");
            writer.write("        anthektiteGoldHoe_Speed = " + anthektiteGoldHoeAttackSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("        # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteEmeraldSword_Damage = " + anthektiteEmeraldSwordDamage + "\n");
            writer.write("        anthektiteEmeraldSword_Speed = " + anthektiteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteEmeraldShovel_Damage = " + anthektiteEmeraldShovelDamage + "\n");
            writer.write("        anthektiteEmeraldShovel_Speed = " + anthektiteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteEmeraldPickaxe_Damage = " + anthektiteEmeraldPickaxeDamage + "\n");
            writer.write("        anthektiteEmeraldPickaxe_Speed = " + anthektiteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteEmeraldAxe_Damage = " + anthektiteEmeraldAxeDamage + "\n");
            writer.write("        anthektiteEmeraldAxe_Speed = " + anthektiteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteEmeraldHoe_Damage = " + anthektiteEmeraldHoeDamage + "\n");
            writer.write("        anthektiteEmeraldHoe_Speed = " + anthektiteEmeraldHoeAttackSpeed + "\n");
            writer.write("      [Diamond]\n");
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("        anthektiteDiamondSword_Damage = " + anthektiteDiamondSwordDamage + "\n");
            writer.write("        anthektiteDiamondSword_Speed = " + anthektiteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("        anthektiteDiamondShovel_Damage = " + anthektiteDiamondShovelDamage + "\n");
            writer.write("        anthektiteDiamondShovel_Speed = " + anthektiteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("        anthektiteDiamondPickaxe_Damage = " + anthektiteDiamondPickaxeDamage + "\n");
            writer.write("        anthektiteDiamondPickaxe_Speed = " + anthektiteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("        anthektiteDiamondAxe_Damage = " + anthektiteDiamondAxeDamage + "\n");
            writer.write("        anthektiteDiamondAxe_Speed = " + anthektiteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("        # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("        anthektiteDiamondHoe_Damage = " + anthektiteDiamondHoeDamage + "\n");
            writer.write("        anthektiteDiamondHoe_Speed = " + anthektiteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("\n");

        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
