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
    public static final ComparableVersion VERSION = new ComparableVersion("1.1");
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

    //Farmer's Delight
    public static float steelKnifeDamage;
    public static float steelKnifeAttackSpeed;
    public static float diarkriteKnifeDamage;
    public static float diarkriteKnifeAttackSpeed;
    public static float anthektiteKnifeDamage;
    public static float anthektiteKnifeAttackSpeed;

    //Piercing Paxels
    public static float steelPaxelDamage;
    public static float steelPaxelAttackSpeed;
    public static float diarkritePaxelDamage;
    public static float diarkritePaxelAttackSpeed;
    public static float anthektitePaxelDamage;
    public static float anthektitePaxelAttackSpeed;

    //Nether's Delight
    public static int steelMacheteDamage;
    public static float steelMacheteAttackSpeed;
    public static int diarkriteMacheteDamage;
    public static float diarkriteMacheteAttackSpeed;
    public static int anthektiteMacheteDamage;
    public static float anthektiteMacheteAttackSpeed;

    //Iron's Spells 'n Spellbooks
    public static int steelSpellbookSlot;
    public static int steelSpellbookMana;
    public static float steelSpellbookCooldown;
    public static float steelSpellbookCastTime;
    public static float steelSpellbookSpellPower;
    public static int diarkriteSpellbookSlot;
    public static int diarkriteSpellbookMana;
    public static float diarkriteSpellbookCooldown;
    public static float diarkriteSpellbookCastTime;
    public static float diarkriteSpellbookSpellPower;
    public static int anthektiteSpellbookSlot;
    public static int anthektiteSpellbookMana;
    public static float anthektiteSpellbookCooldown;
    public static float anthektiteSpellbookCastTime;
    public static float anthektiteSpellbookSpellPower;

    //The Aether
    public static float steelGloveDamage;
    public static float steelGloveMovementSpeed;
    public static float steelGloveAttackSpeed;
    public static float steelGloveArmorBonus;
    public static float steelGloveToughnessBonus;
    public static float diarkriteGloveDamage;
    public static float diarkriteGloveMovementSpeed;
    public static float diarkriteGloveAttackSpeed;
    public static float diarkriteGloveArmorBonus;
    public static float diarkriteGloveToughnessBonus;
    public static float anthektiteGloveDamage;
    public static float anthektiteGloveMovementSpeed;
    public static float anthektiteGloveAttackSpeed;
    public static float anthektiteGloveArmorBonus;
    public static float anthektiteGloveToughnessBonus;

    //Sniff's Weapons
    public static int steelGreatSwordDamage;
    public static float steelGreatSwordAttackSpeed;
    public static int steelNaginataDamage;
    public static float steelNaginataAttackSpeed;
    public static int steelGreatAxeDamage;
    public static float steelGreatAxeAttackSpeed;
    public static int steelGreatPickaxeDamage;
    public static float steelGreatPickaxeAttackSpeed;
    public static int diarkriteGreatSwordDamage;
    public static float diarkriteGreatSwordAttackSpeed;
    public static int diarkriteNaginataDamage;
    public static float diarkriteNaginataAttackSpeed;
    public static int diarkriteGreatAxeDamage;
    public static float diarkriteGreatAxeAttackSpeed;
    public static int diarkriteGreatPickaxeDamage;
    public static float diarkriteGreatPickaxeAttackSpeed;
    public static int anthektiteGreatSwordDamage;
    public static float anthektiteGreatSwordAttackSpeed;
    public static int anthektiteNaginataDamage;
    public static float anthektiteNaginataAttackSpeed;
    public static int anthektiteGreatAxeDamage;
    public static float anthektiteGreatAxeAttackSpeed;
    public static int anthektiteGreatPickaxeDamage;
    public static float anthektiteGreatPickaxeAttackSpeed;

    //AdvancedNetherite Diarkrite
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

        //Farmer's Delight
        steelKnifeDamage = 0.5F;
        steelKnifeAttackSpeed = -2.0F;
        diarkriteKnifeDamage = 0.5F;
        diarkriteKnifeAttackSpeed = -2.0F;
        anthektiteKnifeDamage = 0.5F;
        anthektiteKnifeAttackSpeed = -2.0F;

        //Piercing Paxels
        steelPaxelDamage = 5.0F;
        steelPaxelAttackSpeed = -2.8F;
        diarkritePaxelDamage = 5.0F;
        diarkritePaxelAttackSpeed = -2.8F;
        anthektitePaxelDamage = 5.0F;
        anthektitePaxelAttackSpeed = -2.8F;

        //Nether's Delight
        steelMacheteDamage = 2;
        steelMacheteAttackSpeed = -2.6F;
        diarkriteMacheteDamage = 2;
        diarkriteMacheteAttackSpeed = -2.6F;
        anthektiteMacheteDamage = 2;
        anthektiteMacheteAttackSpeed = -2.6F;

        //Iron's Spells 'n Spellbooks
        steelSpellbookSlot = 8;
        steelSpellbookMana = 50;
        steelSpellbookCooldown = 0.0F;
        steelSpellbookCastTime = 0.0F;
        steelSpellbookSpellPower = 0.0F;
        diarkriteSpellbookSlot = 12;
        diarkriteSpellbookMana = 200;
        diarkriteSpellbookCooldown = -0.15F;
        diarkriteSpellbookCastTime = 0.0F;
        diarkriteSpellbookSpellPower = 0.2F;
        anthektiteSpellbookSlot = 12;
        anthektiteSpellbookMana = 200;
        anthektiteSpellbookCooldown = 0.25F;
        anthektiteSpellbookCastTime = 0.1F;
        anthektiteSpellbookSpellPower = -0.05F;

        //The Aether
        steelGloveDamage = 0.6F;
        steelGloveMovementSpeed = 0.0F;
        steelGloveAttackSpeed = 0.0F;
        steelGloveArmorBonus = 0.0F;
        steelGloveToughnessBonus = 0.0F;
        diarkriteGloveDamage = 1.25F;
        diarkriteGloveMovementSpeed = -0.12F;
        diarkriteGloveAttackSpeed = -0.05F;
        diarkriteGloveArmorBonus = 0.0F;
        diarkriteGloveToughnessBonus = 0.0F;
        anthektiteGloveDamage = 0.75F;
        anthektiteGloveMovementSpeed = 0.0F;
        anthektiteGloveAttackSpeed = 0.16F;
        anthektiteGloveArmorBonus = 0.0F;
        anthektiteGloveToughnessBonus = 0.0F;

        //Sniff's Weapons
        steelGreatSwordDamage = 5;
        steelGreatSwordAttackSpeed = -2.9F;
        steelNaginataDamage = 4;
        steelNaginataAttackSpeed = -3.0F;
        steelGreatAxeDamage = 7;
        steelGreatAxeAttackSpeed = -3.2F;
        steelGreatPickaxeDamage = 3;
        steelGreatPickaxeAttackSpeed = -3.05F;
        diarkriteGreatSwordDamage = 5;
        diarkriteGreatSwordAttackSpeed = -2.9F;
        diarkriteNaginataDamage = 4;
        diarkriteNaginataAttackSpeed = -3.0F;
        diarkriteGreatAxeDamage = 7;
        diarkriteGreatAxeAttackSpeed = -3.2F;
        diarkriteGreatPickaxeDamage = 3;
        diarkriteGreatPickaxeAttackSpeed = -3.05F;
        anthektiteGreatSwordDamage = 5;
        anthektiteGreatSwordAttackSpeed = -2.9F;
        anthektiteNaginataDamage = 4;
        anthektiteNaginataAttackSpeed = -3.0F;
        anthektiteGreatAxeDamage = 7;
        anthektiteGreatAxeAttackSpeed = -3.2F;
        anthektiteGreatPickaxeDamage = 3;
        anthektiteGreatPickaxeAttackSpeed = -3.05F;

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
        anthektiteIronHoeDamage = 0;
        anthektiteIronHoeAttackSpeed = -3.0F;

        anthektiteGoldSwordDamage = 4;
        anthektiteGoldSwordAttackSpeed = -2.4F;
        anthektiteGoldShovelDamage = 1.5F;
        anthektiteGoldShovelAttackSpeed = -3.0F;
        anthektiteGoldPickaxeDamage = 1;
        anthektiteGoldPickaxeAttackSpeed = -2.8F;
        anthektiteGoldAxeDamage = 7F;
        anthektiteGoldAxeAttackSpeed = -3.1F;
        anthektiteGoldHoeDamage = 0;
        anthektiteGoldHoeAttackSpeed = -3.0F;

        anthektiteEmeraldSwordDamage = 4;
        anthektiteEmeraldSwordAttackSpeed = -2.4F;
        anthektiteEmeraldShovelDamage = 1.5F;
        anthektiteEmeraldShovelAttackSpeed = -3.0F;
        anthektiteEmeraldPickaxeDamage = 0;
        anthektiteEmeraldPickaxeAttackSpeed = -2.8F;
        anthektiteEmeraldAxeDamage = 7F;
        anthektiteEmeraldAxeAttackSpeed = -3.1F;
        anthektiteEmeraldHoeDamage = -1;
        anthektiteEmeraldHoeAttackSpeed = -3.0F;

        anthektiteDiamondSwordDamage = 5;
        anthektiteDiamondSwordAttackSpeed = -2.4F;
        anthektiteDiamondShovelDamage = 1.5F;
        anthektiteDiamondShovelAttackSpeed = -3.0F;
        anthektiteDiamondPickaxeDamage = 0;
        anthektiteDiamondPickaxeAttackSpeed = -2.8F;
        anthektiteDiamondAxeDamage = 8F;
        anthektiteDiamondAxeAttackSpeed = -3.1F;
        anthektiteDiamondHoeDamage = -1;
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

                        //Farmer's Delight
                        case "steelKnife_Damage": steelKnifeDamage = (float) value; break;
                        case "steelKnife_Speed": steelKnifeAttackSpeed = (float) value; break;
                        case "diarkriteKnife_Damage": diarkriteKnifeDamage = (float) value; break;
                        case "diarkriteKnife_Speed": diarkriteKnifeAttackSpeed = (float) value; break;
                        case "anthektiteKnife_Damage": anthektiteKnifeDamage = (float) value; break;
                        case "anthektiteKnife_Speed": anthektiteKnifeAttackSpeed = (float) value; break;

                        //Piercing Paxels
                        case "steelPaxel_Damage": steelPaxelDamage = (float) value; break;
                        case "steelPaxel_Speed": steelPaxelAttackSpeed = (float) value; break;
                        case "diarkritePaxel_Damage": diarkritePaxelDamage = (float) value; break;
                        case "diarkritePaxel_Speed": diarkritePaxelAttackSpeed = (float) value; break;
                        case "anthektitePaxel_Damage": anthektitePaxelDamage = (float) value; break;
                        case "anthektitePaxel_Speed": anthektitePaxelAttackSpeed = (float) value; break;

                        //Nether's Delight
                        case "steelMachete_Damage": steelMacheteDamage = (int) value; break;
                        case "steelMachete_Speed": steelMacheteAttackSpeed = (float) value; break;
                        case "diarkriteMachete_Damage": diarkriteMacheteDamage = (int) value; break;
                        case "diarkriteMachete_Speed": diarkriteMacheteAttackSpeed = (float) value; break;
                        case "anthektiteMachete_Damage": anthektiteMacheteDamage = (int) value; break;
                        case "anthektiteMachete_Speed": anthektiteMacheteAttackSpeed = (float) value; break;

                        //Iron's Spells 'n Spellbooks
                        case "steelSpellbookSlot": steelSpellbookSlot = (int) value; break;
                        case "steelSpellbookMana": steelSpellbookMana = (int) value; break;
                        case "steelSpellbookCooldown": steelSpellbookCooldown = (float) value; break;
                        case "steelSpellbookCastTime": steelSpellbookCastTime = (float) value; break;
                        case "steelSpellbookSpellPower": steelSpellbookSpellPower = (float) value; break;
                        case "diarkriteSpellbookSlot": diarkriteSpellbookSlot = (int) value; break;
                        case "diarkriteSpellbookMana": diarkriteSpellbookMana = (int) value; break;
                        case "diarkriteSpellbookCooldown": diarkriteSpellbookCooldown = (float) value; break;
                        case "diarkriteSpellbookCastTime": diarkriteSpellbookCastTime = (float) value; break;
                        case "diarkriteSpellbookSpellPower": diarkriteSpellbookSpellPower = (float) value; break;
                        case "anthektiteSpellbookSlot": anthektiteSpellbookSlot = (int) value; break;
                        case "anthektiteSpellbookMana": anthektiteSpellbookMana = (int) value; break;
                        case "anthektiteSpellbookCooldown": anthektiteSpellbookCooldown = (float) value; break;
                        case "anthektiteSpellbookCastTime": anthektiteSpellbookCastTime = (float) value; break;
                        case "anthektiteSpellbookSpellPower": anthektiteSpellbookSpellPower = (float) value; break;

                        //The Aether
                        case "steelGloveDamage": steelGloveDamage = (float) value; break;
                        case "steelGloveMovementSpeed": steelGloveMovementSpeed = (float) value; break;
                        case "steelGloveAttackSpeed": steelGloveAttackSpeed = (float) value; break;
                        case "steelGloveArmorBonus": steelGloveArmorBonus = (float) value; break;
                        case "steelGloveToughnessBonus": steelGloveToughnessBonus = (float) value; break;
                        case "diarkriteGloveDamage": diarkriteGloveDamage = (float) value; break;
                        case "diarkriteGloveMovementSpeed": diarkriteGloveMovementSpeed = (float) value; break;
                        case "diarkriteGloveAttackSpeed": diarkriteGloveAttackSpeed = (float) value; break;
                        case "diarkriteGloveArmorBonus": diarkriteGloveArmorBonus = (float) value; break;
                        case "diarkriteGloveToughnessBonus": diarkriteGloveToughnessBonus = (float) value; break;
                        case "anthektiteGloveDamage": anthektiteGloveDamage = (float) value; break;
                        case "anthektiteGloveMovementSpeed": anthektiteGloveMovementSpeed = (float) value; break;
                        case "anthektiteGloveAttackSpeed": anthektiteGloveAttackSpeed = (float) value; break;
                        case "anthektiteGloveArmorBonus": anthektiteGloveArmorBonus = (float) value; break;
                        case "anthektiteGloveToughnessBonus": anthektiteGloveToughnessBonus = (float) value; break;

                        //Sniff's Weapons
                        case "steelGreatSword_Damage": steelGreatSwordDamage = (int) value; break;
                        case "steelGreatSword_Speed": steelGreatSwordAttackSpeed = (float) value; break;
                        case "steelNaginata_Damage": steelNaginataDamage = (int) value; break;
                        case "steelNaginata_Speed": steelNaginataAttackSpeed = (float) value; break;
                        case "steelGreatAxe_Damage": steelGreatAxeDamage = (int) value; break;
                        case "steelGreatAxe_Speed": steelGreatAxeAttackSpeed = (float) value; break;
                        case "steelGreatPickaxe_Damage": steelGreatPickaxeDamage = (int) value; break;
                        case "steelGreatPickaxe_Speed": steelGreatPickaxeAttackSpeed = (float) value; break;
                        case "diarkriteGreatSword_Damage": diarkriteGreatSwordDamage = (int) value; break;
                        case "diarkriteGreatSword_Speed": diarkriteGreatSwordAttackSpeed = (float) value; break;
                        case "diarkriteNaginata_Damage": diarkriteNaginataDamage = (int) value; break;
                        case "diarkriteNaginata_Speed": diarkriteNaginataAttackSpeed = (float) value; break;
                        case "diarkriteGreatAxe_Damage": diarkriteGreatAxeDamage = (int) value; break;
                        case "diarkriteGreatAxe_Speed": diarkriteGreatAxeAttackSpeed = (float) value; break;
                        case "diarkriteGreatPickaxe_Damage": diarkriteGreatPickaxeDamage = (int) value; break;
                        case "diarkriteGreatPickaxe_Speed": diarkriteGreatPickaxeAttackSpeed = (float) value; break;
                        case "anthektiteGreatSword_Damage": anthektiteGreatSwordDamage = (int) value; break;
                        case "anthektiteGreatSword_Speed": anthektiteGreatSwordAttackSpeed = (float) value; break;
                        case "anthektiteNaginata_Damage": anthektiteNaginataDamage = (int) value; break;
                        case "anthektiteNaginata_Speed": anthektiteNaginataAttackSpeed = (float) value; break;
                        case "anthektiteGreatAxe_Damage": anthektiteGreatAxeDamage = (int) value; break;
                        case "anthektiteGreatAxe_Speed": anthektiteGreatAxeAttackSpeed = (float) value; break;
                        case "anthektiteGreatPickaxe_Damage": anthektiteGreatPickaxeDamage = (int) value; break;
                        case "anthektiteGreatPickaxe_Speed": anthektiteGreatPickaxeAttackSpeed = (float) value; break;

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
            writer.write("[FarmersDelight]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Attack Damage: 0.5), (Attack Speed: -2.0)\n");
            writer.write("    steelKnife_Damage = " + steelKnifeDamage + "\n");
            writer.write("    steelKnife_Speed = " + steelKnifeAttackSpeed + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Attack Damage: 0.5), (Attack Speed: -2.0)\n");
            writer.write("    diarkriteKnife_Damage = " + diarkriteKnifeDamage + "\n");
            writer.write("    diarkriteKnife_Speed = " + diarkriteKnifeAttackSpeed + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Attack Damage: 0.5), (Attack Speed: -2.0)\n");
            writer.write("    anthektiteKnife_Damage = " + anthektiteKnifeDamage + "\n");
            writer.write("    anthektiteKnife_Speed = " + anthektiteKnifeAttackSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[PiercingPaxels]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Attack Damage: 5.0), (Attack Speed: -2.8)\n");
            writer.write("    steelPaxel_Damage = " + steelPaxelDamage + "\n");
            writer.write("    steelPaxel_Speed = " + steelPaxelAttackSpeed + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Attack Damage: 5.0), (Attack Speed: -2.8)\n");
            writer.write("    diarkritePaxel_Damage = " + diarkritePaxelDamage + "\n");
            writer.write("    diarkritePaxel_Speed = " + diarkritePaxelAttackSpeed + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Attack Damage: 5.0), (Attack Speed: -2.8)\n");
            writer.write("    anthektitePaxel_Damage = " + anthektitePaxelDamage + "\n");
            writer.write("    anthektitePaxel_Speed = " + anthektitePaxelAttackSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[NethersDelight]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Attack Damage: 2), (Attack Speed: -2.6)\n");
            writer.write("    steelMachete_Damage = " + steelMacheteDamage + "\n");
            writer.write("    steelMachete_Speed = " + steelMacheteAttackSpeed + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Attack Damage: 2), (Attack Speed: -2.6)\n");
            writer.write("    diarkriteMachete_Damage = " + diarkriteMacheteDamage + "\n");
            writer.write("    diarkriteMachete_Speed = " + diarkriteMacheteAttackSpeed + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Attack Damage: 2), (Attack Speed: -2.6)\n");
            writer.write("    anthektiteMachete_Damage = " + anthektiteMacheteDamage + "\n");
            writer.write("    anthektiteMachete_Speed = " + anthektiteMacheteAttackSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[IronsSpellbooks]\n");
            writer.write("# Note: Spell slot config does not apply to existing spellbooks, only new ones\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Spell Slots: 8), (Mana: 50), (Cooldown: 0.0), (CastTime: 0.0), (Spell Power: 0.0)\n");
            writer.write("    steelSpellbookSlot = " + steelSpellbookSlot + "\n");
            writer.write("    steelSpellbookMana = " + steelSpellbookMana + "\n");
            writer.write("    steelSpellbookCooldown = " + steelSpellbookCooldown + "\n");
            writer.write("    steelSpellbookCastTime = " + steelSpellbookCastTime + "\n");
            writer.write("    steelSpellbookSpellPower = " + steelSpellbookSpellPower + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Spell Slots: 12), (Mana: 200), (Cooldown: -0.15), (CastTime: 0.0), (Spell Power: 0.2)\n");
            writer.write("    diarkriteSpellbookSlot = " + diarkriteSpellbookSlot + "\n");
            writer.write("    diarkriteSpellbookMana = " + diarkriteSpellbookMana + "\n");
            writer.write("    diarkriteSpellbookCooldown = " + diarkriteSpellbookCooldown + "\n");
            writer.write("    diarkriteSpellbookCastTime = " + diarkriteSpellbookCastTime + "\n");
            writer.write("    diarkriteSpellbookSpellPower = " + diarkriteSpellbookSpellPower + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Spell Slots: 12), (Mana: 200), (Cooldown: 0.25), (CastTime: 0.1), (Spell Power: 0.05)\n");
            writer.write("    anthektiteSpellbookSlot = " + anthektiteSpellbookSlot + "\n");
            writer.write("    anthektiteSpellbookMana = " + anthektiteSpellbookMana + "\n");
            writer.write("    anthektiteSpellbookCooldown = " + anthektiteSpellbookCooldown + "\n");
            writer.write("    anthektiteSpellbookCastTime = " + anthektiteSpellbookCastTime + "\n");
            writer.write("    anthektiteSpellbookSpellPower = " + anthektiteSpellbookSpellPower + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[TheAether]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Default: (Damage: 0.6), (MovementSpeed: 0), (AttackSpeed: 0), (ArmorBonus: 0), (ToughnessBonus: 0)\n");
            writer.write("    steelGloveDamage = " + steelGloveDamage + "\n");
            writer.write("    steelGloveMovementSpeed = " + steelGloveMovementSpeed + "\n");
            writer.write("    steelGloveAttackSpeed = " + steelGloveAttackSpeed + "\n");
            writer.write("    steelGloveArmorBonus = " + steelGloveArmorBonus + "\n");
            writer.write("    steelGloveToughnessBonus = " + steelGloveToughnessBonus + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Default: (Damage: 1.25), (MovementSpeed: -0.12), (AttackSpeed: -0.05), (ArmorBonus: 0), (ToughnessBonus: 0)\n");
            writer.write("    diarkriteGloveDamage = " + diarkriteGloveDamage + "\n");
            writer.write("    diarkriteGloveMovementSpeed = " + diarkriteGloveMovementSpeed + "\n");
            writer.write("    diarkriteGloveAttackSpeed = " + diarkriteGloveAttackSpeed + "\n");
            writer.write("    diarkriteGloveArmorBonus = " + diarkriteGloveArmorBonus + "\n");
            writer.write("    diarkriteGloveToughnessBonus = " + diarkriteGloveToughnessBonus + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Default: (Damage: 0.75), (MovementSpeed: 0), (AttackSpeed: 0.16), (ArmorBonus: 0), (ToughnessBonus: 0)\n");
            writer.write("    anthektiteGloveDamage = " + anthektiteGloveDamage + "\n");
            writer.write("    anthektiteGloveMovementSpeed = " + anthektiteGloveMovementSpeed + "\n");
            writer.write("    anthektiteGloveAttackSpeed = " + anthektiteGloveAttackSpeed + "\n");
            writer.write("    anthektiteGloveArmorBonus = " + anthektiteGloveArmorBonus + "\n");
            writer.write("    anthektiteGloveToughnessBonus = " + anthektiteGloveToughnessBonus + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[SniffsWeapons]\n");
            writer.write("  [Steel]\n");
            writer.write("    # Great Sword   | Default: (Attack Damage: 5), (Attack Speed: -2.9)\n");
            writer.write("    steelGreatSword_Damage = " + steelGreatSwordDamage + "\n");
            writer.write("    steelGreatSword_Speed = " + steelGreatSwordAttackSpeed + "\n");
            writer.write("    # Naginata      | Default: (Attack Damage: 4), (Attack Speed: -3.0)\n");
            writer.write("    steelNaginata_Damage = " + steelNaginataDamage + "\n");
            writer.write("    steelNaginata_Speed = " + steelNaginataAttackSpeed + "\n");
            writer.write("    # Great Axe     | Default: (Attack Damage: 7), (Attack Speed: -3.2)\n");
            writer.write("    steelGreatAxe_Damage = " + steelGreatAxeDamage + "\n");
            writer.write("    steelGreatAxe_Speed = " + steelGreatAxeAttackSpeed + "\n");
            writer.write("    # Great Pickaxe | Default: (Attack Damage: 3), (Attack Speed: -3.05)\n");
            writer.write("    steelGreatPickaxe_Damage = " + steelGreatPickaxeDamage + "\n");
            writer.write("    steelGreatPickaxe_Speed = " + steelGreatPickaxeAttackSpeed + "\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    # Great Sword   | Default: (Attack Damage: 5), (Attack Speed: -2.9)\n");
            writer.write("    diarkriteGreatSword_Damage = " + diarkriteGreatSwordDamage + "\n");
            writer.write("    diarkriteGreatSword_Speed = " + diarkriteGreatSwordAttackSpeed + "\n");
            writer.write("    # Naginata      | Default: (Attack Damage: 4), (Attack Speed: -3.0)\n");
            writer.write("    diarkriteNaginata_Damage = " + diarkriteNaginataDamage + "\n");
            writer.write("    diarkriteNaginata_Speed = " + diarkriteNaginataAttackSpeed + "\n");
            writer.write("    # Great Axe     | Default: (Attack Damage: 7), (Attack Speed: -3.2)\n");
            writer.write("    diarkriteGreatAxe_Damage = " + diarkriteGreatAxeDamage + "\n");
            writer.write("    diarkriteGreatAxe_Speed = " + diarkriteGreatAxeAttackSpeed + "\n");
            writer.write("    # Great Pickaxe | Default: (Attack Damage: 3), (Attack Speed: -3.05)\n");
            writer.write("    diarkriteGreatPickaxe_Damage = " + diarkriteGreatPickaxeDamage + "\n");
            writer.write("    diarkriteGreatPickaxe_Speed = " + diarkriteGreatPickaxeAttackSpeed + "\n");
            writer.write("  [Anthektite]\n");
            writer.write("    # Great Sword   | Default: (Attack Damage: 5), (Attack Speed: -2.9)\n");
            writer.write("    anthektiteGreatSword_Damage = " + anthektiteGreatSwordDamage + "\n");
            writer.write("    anthektiteGreatSword_Speed = " + anthektiteGreatSwordAttackSpeed + "\n");
            writer.write("    # Naginata      | Default: (Attack Damage: 4), (Attack Speed: -3.0)\n");
            writer.write("    anthektiteNaginata_Damage = " + anthektiteNaginataDamage + "\n");
            writer.write("    anthektiteNaginata_Speed = " + anthektiteNaginataAttackSpeed + "\n");
            writer.write("    # Great Axe     | Default: (Attack Damage: 7), (Attack Speed: -3.2)\n");
            writer.write("    anthektiteGreatAxe_Damage = " + anthektiteGreatAxeDamage + "\n");
            writer.write("    anthektiteGreatAxe_Speed = " + anthektiteGreatAxeAttackSpeed + "\n");
            writer.write("    # Great Pickaxe | Default: (Attack Damage: 3), (Attack Speed: -3.05)\n");
            writer.write("    anthektiteGreatPickaxe_Damage = " + anthektiteGreatPickaxeDamage + "\n");
            writer.write("    anthektiteGreatPickaxe_Speed = " + anthektiteGreatPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.newLine();
            writer.write("[AdvancedNetherite]\n");
            writer.write("  [Diarkrite]\n");
            writer.write("    [Iron]\n");
            writer.write("      # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("      diarkriteIronSword_Damage = " + diarkriteIronSwordDamage + "\n");
            writer.write("      diarkriteIronSword_Speed = " + diarkriteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      diarkriteIronShovel_Damage = " + diarkriteIronShovelDamage + "\n");
            writer.write("      diarkriteIronShovel_Speed = " + diarkriteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      diarkriteIronPickaxe_Damage = " + diarkriteIronPickaxeDamage + "\n");
            writer.write("      diarkriteIronPickaxe_Speed = " + diarkriteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 6), (Attack Speed: -3.1)\n");
            writer.write("      diarkriteIronAxe_Damage = " + diarkriteIronAxeDamage + "\n");
            writer.write("      diarkriteIronAxe_Speed = " + diarkriteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("      diarkriteIronHoe_Damage = " + diarkriteIronHoeDamage + "\n");
            writer.write("      diarkriteIronHoe_Speed = " + diarkriteIronHoeAttackSpeed + "\n");
            writer.write("    [Gold]\n");
            writer.write("      # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("      diarkriteGoldSword_Damage = " + diarkriteGoldSwordDamage + "\n");
            writer.write("      diarkriteGoldSword_Speed = " + diarkriteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      diarkriteGoldShovel_Damage = " + diarkriteGoldShovelDamage + "\n");
            writer.write("      diarkriteGoldShovel_Speed = " + diarkriteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      diarkriteGoldPickaxe_Damage = " + diarkriteGoldPickaxeDamage + "\n");
            writer.write("      diarkriteGoldPickaxe_Speed = " + diarkriteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("      diarkriteGoldAxe_Damage = " + diarkriteGoldAxeDamage + "\n");
            writer.write("      diarkriteGoldAxe_Speed = " + diarkriteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -3), (Attack Speed: 0.0)\n");
            writer.write("      diarkriteGoldHoe_Damage = " + diarkriteGoldHoeDamage + "\n");
            writer.write("      diarkriteGoldHoe_Speed = " + diarkriteGoldHoeAttackSpeed + "\n");
            writer.write("    [Emerald]\n");
            writer.write("      # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("      diarkriteEmeraldSword_Damage = " + diarkriteEmeraldSwordDamage + "\n");
            writer.write("      diarkriteEmeraldSword_Speed = " + diarkriteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      diarkriteEmeraldShovel_Damage = " + diarkriteEmeraldShovelDamage + "\n");
            writer.write("      diarkriteEmeraldShovel_Speed = " + diarkriteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      diarkriteEmeraldPickaxe_Damage = " + diarkriteEmeraldPickaxeDamage + "\n");
            writer.write("      diarkriteEmeraldPickaxe_Speed = " + diarkriteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 7), (Attack Speed: -3.1)\n");
            writer.write("      diarkriteEmeraldAxe_Damage = " + diarkriteEmeraldAxeDamage + "\n");
            writer.write("      diarkriteEmeraldAxe_Speed = " + diarkriteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("      diarkriteEmeraldHoe_Damage = " + diarkriteEmeraldHoeDamage + "\n");
            writer.write("      diarkriteEmeraldHoe_Speed = " + diarkriteEmeraldHoeAttackSpeed + "\n");
            writer.write("    [Diamond]\n");
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("      diarkriteDiamondSword_Damage = " + diarkriteDiamondSwordDamage + "\n");
            writer.write("      diarkriteDiamondSword_Speed = " + diarkriteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      diarkriteDiamondShovel_Damage = " + diarkriteDiamondShovelDamage + "\n");
            writer.write("      diarkriteDiamondShovel_Speed = " + diarkriteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      diarkriteDiamondPickaxe_Damage = " + diarkriteDiamondPickaxeDamage + "\n");
            writer.write("      diarkriteDiamondPickaxe_Speed = " + diarkriteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 8), (Attack Speed: -3.1)\n");
            writer.write("      diarkriteDiamondAxe_Damage = " + diarkriteDiamondAxeDamage + "\n");
            writer.write("      diarkriteDiamondAxe_Speed = " + diarkriteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -4), (Attack Speed: 0.0)\n");
            writer.write("      diarkriteDiamondHoe_Damage = " + diarkriteDiamondHoeDamage + "\n");
            writer.write("      diarkriteDiamondHoe_Speed = " + diarkriteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("  [Anthektite]\n");
            writer.write("    [Iron]\n");
            writer.write("      # Default: (Attack Damage: 3), (Attack Speed: -2.4)\n");
            writer.write("      anthektiteIronSword_Damage = " + anthektiteIronSwordDamage + "\n");
            writer.write("      anthektiteIronSword_Speed = " + anthektiteIronSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      anthektiteIronShovel_Damage = " + anthektiteIronShovelDamage + "\n");
            writer.write("      anthektiteIronShovel_Speed = " + anthektiteIronShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      anthektiteIronPickaxe_Damage = " + anthektiteIronPickaxeDamage + "\n");
            writer.write("      anthektiteIronPickaxe_Speed = " + anthektiteIronPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("      anthektiteIronAxe_Damage = " + anthektiteIronAxeDamage + "\n");
            writer.write("      anthektiteIronAxe_Speed = " + anthektiteIronAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("      anthektiteIronHoe_Damage = " + anthektiteIronHoeDamage + "\n");
            writer.write("      anthektiteIronHoe_Speed = " + anthektiteIronHoeAttackSpeed + "\n");
            writer.write("    [Gold]\n");
            writer.write("      # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("      anthektiteGoldSword_Damage = " + anthektiteGoldSwordDamage + "\n");
            writer.write("      anthektiteGoldSword_Speed = " + anthektiteGoldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      anthektiteGoldShovel_Damage = " + anthektiteGoldShovelDamage + "\n");
            writer.write("      anthektiteGoldShovel_Speed = " + anthektiteGoldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      anthektiteGoldPickaxe_Damage = " + anthektiteGoldPickaxeDamage + "\n");
            writer.write("      anthektiteGoldPickaxe_Speed = " + anthektiteGoldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("      anthektiteGoldAxe_Damage = " + anthektiteGoldAxeDamage + "\n");
            writer.write("      anthektiteGoldAxe_Speed = " + anthektiteGoldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 0), (Attack Speed: 0.0)\n");
            writer.write("      anthektiteGoldHoe_Damage = " + anthektiteGoldHoeDamage + "\n");
            writer.write("      anthektiteGoldHoe_Speed = " + anthektiteGoldHoeAttackSpeed + "\n");
            writer.write("      [Emerald]\n");
            writer.write("      # Default: (Attack Damage: 4), (Attack Speed: -2.4)\n");
            writer.write("      anthektiteEmeraldSword_Damage = " + anthektiteEmeraldSwordDamage + "\n");
            writer.write("      anthektiteEmeraldSword_Speed = " + anthektiteEmeraldSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      anthektiteEmeraldShovel_Damage = " + anthektiteEmeraldShovelDamage + "\n");
            writer.write("      anthektiteEmeraldShovel_Speed = " + anthektiteEmeraldShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      anthektiteEmeraldPickaxe_Damage = " + anthektiteEmeraldPickaxeDamage + "\n");
            writer.write("      anthektiteEmeraldPickaxe_Speed = " + anthektiteEmeraldPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("      anthektiteEmeraldAxe_Damage = " + anthektiteEmeraldAxeDamage + "\n");
            writer.write("      anthektiteEmeraldAxe_Speed = " + anthektiteEmeraldAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("      anthektiteEmeraldHoe_Damage = " + anthektiteEmeraldHoeDamage + "\n");
            writer.write("      anthektiteEmeraldHoe_Speed = " + anthektiteEmeraldHoeAttackSpeed + "\n");
            writer.write("    [Diamond]\n");
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -2.4)\n");
            writer.write("      anthektiteDiamondSword_Damage = " + anthektiteDiamondSwordDamage + "\n");
            writer.write("      anthektiteDiamondSword_Speed = " + anthektiteDiamondSwordAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1.5), (Attack Speed: -3.0)\n");
            writer.write("      anthektiteDiamondShovel_Damage = " + anthektiteDiamondShovelDamage + "\n");
            writer.write("      anthektiteDiamondShovel_Speed = " + anthektiteDiamondShovelAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 1), (Attack Speed: -2.8)\n");
            writer.write("      anthektiteDiamondPickaxe_Damage = " + anthektiteDiamondPickaxeDamage + "\n");
            writer.write("      anthektiteDiamondPickaxe_Speed = " + anthektiteDiamondPickaxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: 5), (Attack Speed: -3.1)\n");
            writer.write("      anthektiteDiamondAxe_Damage = " + anthektiteDiamondAxeDamage + "\n");
            writer.write("      anthektiteDiamondAxe_Speed = " + anthektiteDiamondAxeAttackSpeed + "\n");
            writer.newLine();
            writer.write("      # Default: (Attack Damage: -1), (Attack Speed: 0.0)\n");
            writer.write("      anthektiteDiamondHoe_Damage = " + anthektiteDiamondHoeDamage + "\n");
            writer.write("      anthektiteDiamondHoe_Speed = " + anthektiteDiamondHoeAttackSpeed + "\n");
            writer.newLine();
            writer.write("\n");

        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
