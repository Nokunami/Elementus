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
public class SDConfig {
    private static final Logger logger = Elementus.LOGGER;
    public static final SDConfig INSTANCE = new SDConfig();
    private static final Path CONFIG_PATH = Elementus.SD_CONFIG_PATH;
    public static final ComparableVersion VERSION = new ComparableVersion(CONFIG_VERSION);
    // values exposed to other classes
    //Samurai Dynasty Steel
    public static int steelSamuraiArmor_DurabilityForType;
    public static int steelSamuraiArmor_Enchantability;
    public static int steelSamuraiArmor_Helmet;
    public static int steelSamuraiArmor_Chestplate;
    public static int steelSamuraiArmor_Leggings;
    public static int steelSamuraiArmor_Boots;
    public static double steelSamuraiArmor_Toughness;
    public static double steelSamuraiArmor_KnockbackResistance;
    public static double steelSamuraiArmor_AttackSpeed;
    public static double steelSamuraiArmor_MovementSpeed;

    public static int steelSamuraiLightArmor_DurabilityForType;
    public static int steelSamuraiLightArmor_Enchantability;
    public static int steelSamuraiLightArmor_Helmet;
    public static int steelSamuraiLightArmor_Chestplate;
    public static int steelSamuraiLightArmor_Leggings;
    public static int steelSamuraiLightArmor_Boots;
    public static double steelSamuraiLightArmor_Toughness;
    public static double steelSamuraiLightArmor_KnockbackResistance;
    public static double steelSamuraiLightArmor_AttackSpeed;
    public static double steelSamuraiLightArmor_MovementSpeed;

    public static int steelSamuraiMasterArmor_DurabilityForType;
    public static int steelSamuraiMasterArmor_Enchantability;
    public static int steelSamuraiMasterArmor_Helmet;
    public static int steelSamuraiMasterArmor_Chestplate;
    public static int steelSamuraiMasterArmor_Leggings;
    public static int steelSamuraiMasterArmor_Boots;
    public static double steelSamuraiMasterArmor_Toughness;
    public static double steelSamuraiMasterArmor_KnockbackResistance;
    public static double steelSamuraiMasterArmor_AttackSpeed;
    public static double steelSamuraiMasterArmor_MovementSpeed;

    //Samurai Dynasty Diarkrite
    public static int diarkriteSamuraiArmor_DurabilityForType;
    public static int diarkriteSamuraiArmor_Enchantability;
    public static int diarkriteSamuraiArmor_Helmet;
    public static int diarkriteSamuraiArmor_Chestplate;
    public static int diarkriteSamuraiArmor_Leggings;
    public static int diarkriteSamuraiArmor_Boots;
    public static double diarkriteSamuraiArmor_Toughness;
    public static double diarkriteSamuraiArmor_KnockbackResistance;
    public static double diarkriteSamuraiArmor_AttackSpeed;
    public static double diarkriteSamuraiArmor_MovementSpeed;

    public static int diarkriteSamuraiLightArmor_DurabilityForType;
    public static int diarkriteSamuraiLightArmor_Enchantability;
    public static int diarkriteSamuraiLightArmor_Helmet;
    public static int diarkriteSamuraiLightArmor_Chestplate;
    public static int diarkriteSamuraiLightArmor_Leggings;
    public static int diarkriteSamuraiLightArmor_Boots;
    public static double diarkriteSamuraiLightArmor_Toughness;
    public static double diarkriteSamuraiLightArmor_KnockbackResistance;
    public static double diarkriteSamuraiLightArmor_AttackSpeed;
    public static double diarkriteSamuraiLightArmor_MovementSpeed;

    public static int diarkriteSamuraiMasterArmor_DurabilityForType;
    public static int diarkriteSamuraiMasterArmor_Enchantability;
    public static int diarkriteSamuraiMasterArmor_Helmet;
    public static int diarkriteSamuraiMasterArmor_Chestplate;
    public static int diarkriteSamuraiMasterArmor_Leggings;
    public static int diarkriteSamuraiMasterArmor_Boots;
    public static double diarkriteSamuraiMasterArmor_Toughness;
    public static double diarkriteSamuraiMasterArmor_KnockbackResistance;
    public static double diarkriteSamuraiMasterArmor_AttackSpeed;
    public static double diarkriteSamuraiMasterArmor_MovementSpeed;

    //Samurai Dynasty Anthektite
    public static int anthektiteSamuraiArmor_DurabilityForType;
    public static int anthektiteSamuraiArmor_Enchantability;
    public static int anthektiteSamuraiArmor_Helmet;
    public static int anthektiteSamuraiArmor_Chestplate;
    public static int anthektiteSamuraiArmor_Leggings;
    public static int anthektiteSamuraiArmor_Boots;
    public static double anthektiteSamuraiArmor_Toughness;
    public static double anthektiteSamuraiArmor_KnockbackResistance;
    public static double anthektiteSamuraiArmor_AttackSpeed;
    public static double anthektiteSamuraiArmor_MovementSpeed;

    public static int anthektiteSamuraiLightArmor_DurabilityForType;
    public static int anthektiteSamuraiLightArmor_Enchantability;
    public static int anthektiteSamuraiLightArmor_Helmet;
    public static int anthektiteSamuraiLightArmor_Chestplate;
    public static int anthektiteSamuraiLightArmor_Leggings;
    public static int anthektiteSamuraiLightArmor_Boots;
    public static double anthektiteSamuraiLightArmor_Toughness;
    public static double anthektiteSamuraiLightArmor_KnockbackResistance;
    public static double anthektiteSamuraiLightArmor_AttackSpeed;
    public static double anthektiteSamuraiLightArmor_MovementSpeed;

    public static int anthektiteSamuraiMasterArmor_DurabilityForType;
    public static int anthektiteSamuraiMasterArmor_Enchantability;
    public static int anthektiteSamuraiMasterArmor_Helmet;
    public static int anthektiteSamuraiMasterArmor_Chestplate;
    public static int anthektiteSamuraiMasterArmor_Leggings;
    public static int anthektiteSamuraiMasterArmor_Boots;
    public static double anthektiteSamuraiMasterArmor_Toughness;
    public static double anthektiteSamuraiMasterArmor_KnockbackResistance;
    public static double anthektiteSamuraiMasterArmor_AttackSpeed;
    public static double anthektiteSamuraiMasterArmor_MovementSpeed;

    public static void reload() {
        INSTANCE.setDefaults();
        INSTANCE.load();

        logger.info("Samurai Dynasty Config loaded");
    }

    private void setDefaults() {
        //EpicSamurai Armor Steel
        steelSamuraiArmor_DurabilityForType = 24;
        steelSamuraiArmor_Helmet = 3;
        steelSamuraiArmor_Chestplate = 8;
        steelSamuraiArmor_Leggings = 6;
        steelSamuraiArmor_Boots = 3;
        steelSamuraiArmor_Enchantability = 10;
        steelSamuraiArmor_Toughness = 0;
        steelSamuraiArmor_KnockbackResistance = 0;
        steelSamuraiArmor_AttackSpeed = 0;
        steelSamuraiArmor_MovementSpeed = 0;
        steelSamuraiLightArmor_DurabilityForType = 24;
        steelSamuraiLightArmor_Helmet = 3;
        steelSamuraiLightArmor_Chestplate = 8;
        steelSamuraiLightArmor_Leggings = 6;
        steelSamuraiLightArmor_Boots = 3;
        steelSamuraiLightArmor_Enchantability = 10;
        steelSamuraiLightArmor_Toughness = 0;
        steelSamuraiLightArmor_KnockbackResistance = 0;
        steelSamuraiLightArmor_AttackSpeed = 0;
        steelSamuraiLightArmor_MovementSpeed = 0;
        steelSamuraiMasterArmor_DurabilityForType = 24;
        steelSamuraiMasterArmor_Helmet = 3;
        steelSamuraiMasterArmor_Chestplate = 8;
        steelSamuraiMasterArmor_Leggings = 6;
        steelSamuraiMasterArmor_Boots = 3;
        steelSamuraiMasterArmor_Enchantability = 10;
        steelSamuraiMasterArmor_Toughness = 0;
        steelSamuraiMasterArmor_KnockbackResistance = 0;
        steelSamuraiMasterArmor_AttackSpeed = 0;
        steelSamuraiMasterArmor_MovementSpeed = 0;

        //EpicSamurai Armor Diarkrite
        diarkriteSamuraiArmor_DurabilityForType = 38;
        diarkriteSamuraiArmor_Helmet = 3;
        diarkriteSamuraiArmor_Chestplate = 8;
        diarkriteSamuraiArmor_Leggings = 6;
        diarkriteSamuraiArmor_Boots = 3;
        diarkriteSamuraiArmor_Enchantability = 18;
        diarkriteSamuraiArmor_Toughness = 4;
        diarkriteSamuraiArmor_KnockbackResistance = 0.2;
        diarkriteSamuraiArmor_AttackSpeed = 0;
        diarkriteSamuraiArmor_MovementSpeed = 0.04;
        diarkriteSamuraiLightArmor_DurabilityForType = 38;
        diarkriteSamuraiLightArmor_Helmet = 3;
        diarkriteSamuraiLightArmor_Chestplate = 8;
        diarkriteSamuraiLightArmor_Leggings = 6;
        diarkriteSamuraiLightArmor_Boots = 3;
        diarkriteSamuraiLightArmor_Enchantability = 18;
        diarkriteSamuraiLightArmor_Toughness = 4;
        diarkriteSamuraiLightArmor_KnockbackResistance = 0.2;
        diarkriteSamuraiLightArmor_AttackSpeed = 0;
        diarkriteSamuraiLightArmor_MovementSpeed = 0.04;
        diarkriteSamuraiMasterArmor_DurabilityForType = 38;
        diarkriteSamuraiMasterArmor_Helmet = 3;
        diarkriteSamuraiMasterArmor_Chestplate = 8;
        diarkriteSamuraiMasterArmor_Leggings = 6;
        diarkriteSamuraiMasterArmor_Boots = 3;
        diarkriteSamuraiMasterArmor_Enchantability = 18;
        diarkriteSamuraiMasterArmor_Toughness = 4;
        diarkriteSamuraiMasterArmor_KnockbackResistance = 0.2;
        diarkriteSamuraiMasterArmor_AttackSpeed = 0;
        diarkriteSamuraiMasterArmor_MovementSpeed = 0.04;

        //EpicSamurai Armor Anthektite
        anthektiteSamuraiArmor_DurabilityForType = 35;
        anthektiteSamuraiArmor_Helmet = 3;
        anthektiteSamuraiArmor_Chestplate = 8;
        anthektiteSamuraiArmor_Leggings = 6;
        anthektiteSamuraiArmor_Boots = 3;
        anthektiteSamuraiArmor_Enchantability = 15;
        anthektiteSamuraiArmor_Toughness = 2;
        anthektiteSamuraiArmor_KnockbackResistance = 0.05;
        anthektiteSamuraiArmor_AttackSpeed = 0.1;
        anthektiteSamuraiArmor_MovementSpeed = 0;
        anthektiteSamuraiLightArmor_DurabilityForType = 35;
        anthektiteSamuraiLightArmor_Helmet = 3;
        anthektiteSamuraiLightArmor_Chestplate = 8;
        anthektiteSamuraiLightArmor_Leggings = 6;
        anthektiteSamuraiLightArmor_Boots = 3;
        anthektiteSamuraiLightArmor_Enchantability = 15;
        anthektiteSamuraiLightArmor_Toughness = 2;
        anthektiteSamuraiLightArmor_KnockbackResistance = 0.05;
        anthektiteSamuraiLightArmor_AttackSpeed = 0.1;
        anthektiteSamuraiLightArmor_MovementSpeed = 0;
        anthektiteSamuraiMasterArmor_DurabilityForType = 35;
        anthektiteSamuraiMasterArmor_Helmet = 3;
        anthektiteSamuraiMasterArmor_Chestplate = 8;
        anthektiteSamuraiMasterArmor_Leggings = 6;
        anthektiteSamuraiMasterArmor_Boots = 3;
        anthektiteSamuraiMasterArmor_Enchantability = 15;
        anthektiteSamuraiMasterArmor_Toughness = 2;
        anthektiteSamuraiMasterArmor_KnockbackResistance = 0.05;
        anthektiteSamuraiMasterArmor_AttackSpeed = 0.1;
        anthektiteSamuraiMasterArmor_MovementSpeed = 0;
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
                        case "steelArmor.Durability": steelSamuraiArmor_DurabilityForType = (int) value; break;
                        case "steelArmor.Helmet": steelSamuraiArmor_Helmet = (int) value; break;
                        case "steelArmor.Chestplate": steelSamuraiArmor_Chestplate = (int) value; break;
                        case "steelArmor.Leggings": steelSamuraiArmor_Leggings = (int) value; break;
                        case "steelArmor.Boots": steelSamuraiArmor_Boots = (int) value; break;
                        case "steelArmor.Enchantability": steelSamuraiArmor_Enchantability = (int) value; break;
                        case "steelArmor.Toughness": steelSamuraiArmor_Toughness = value; break;
                        case "steelArmor.KnockbackResistance": steelSamuraiArmor_KnockbackResistance = value; break;
                        case "steelArmor.AttackSpeedBoost": steelSamuraiArmor_AttackSpeed = value; break;
                        case "steelArmor.MovementSpeed": steelSamuraiArmor_MovementSpeed = value; break;

                        case "steelLightArmor.Durability": steelSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "steelLightArmor.Helmet": steelSamuraiLightArmor_Helmet = (int) value; break;
                        case "steelLightArmor.Chestplate": steelSamuraiLightArmor_Chestplate = (int) value; break;
                        case "steelLightArmor.Leggings": steelSamuraiLightArmor_Leggings = (int) value; break;
                        case "steelLightArmor.Boots": steelSamuraiLightArmor_Boots = (int) value; break;
                        case "steelLightArmor.Enchantability": steelSamuraiLightArmor_Enchantability = (int) value; break;
                        case "steelLightArmor.Toughness": steelSamuraiLightArmor_Toughness = value; break;
                        case "steelLightArmor.KnockbackResistance": steelSamuraiLightArmor_KnockbackResistance = value; break;
                        case "steelLightArmor.AttackSpeedBoost": steelSamuraiLightArmor_AttackSpeed = value; break;
                        case "steelLightArmor.MovementSpeed": steelSamuraiLightArmor_MovementSpeed = value; break;

                        case "steelMasterArmor.Durability": steelSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "steelMasterArmor.Helmet": steelSamuraiMasterArmor_Helmet = (int) value; break;
                        case "steelMasterArmor.Chestplate": steelSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "steelMasterArmor.Leggings": steelSamuraiMasterArmor_Leggings = (int) value; break;
                        case "steelMasterArmor.Boots": steelSamuraiMasterArmor_Boots = (int) value; break;
                        case "steelMasterArmor.Enchantability": steelSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "steelMasterArmor.Toughness": steelSamuraiMasterArmor_Toughness = value; break;
                        case "steelMasterArmor.KnockbackResistance": steelSamuraiMasterArmor_KnockbackResistance = value; break;
                        case "steelMasterArmor.AttackSpeedBoost": steelSamuraiMasterArmor_AttackSpeed = value; break;
                        case "steelMasterArmor.MovementSpeed": steelSamuraiMasterArmor_MovementSpeed = value; break;


                        case "diarkriteArmor.Durability": diarkriteSamuraiArmor_DurabilityForType = (int) value; break;
                        case "diarkriteArmor.Helmet": diarkriteSamuraiArmor_Helmet = (int) value; break;
                        case "diarkriteArmor.Chestplate": diarkriteSamuraiArmor_Chestplate = (int) value; break;
                        case "diarkriteArmor.Leggings": diarkriteSamuraiArmor_Leggings = (int) value; break;
                        case "diarkriteArmor.Boots": diarkriteSamuraiArmor_Boots = (int) value; break;
                        case "diarkriteArmor.Enchantability": diarkriteSamuraiArmor_Enchantability = (int) value; break;
                        case "diarkriteArmor.Toughness": diarkriteSamuraiArmor_Toughness = value; break;
                        case "diarkriteArmor.KnockbackResistance": diarkriteSamuraiArmor_KnockbackResistance = value; break;
                        case "diarkriteArmor.AttackSpeedBoost": diarkriteSamuraiArmor_AttackSpeed = value; break;
                        case "diarkriteArmor.MovementSpeed": diarkriteSamuraiArmor_MovementSpeed = value; break;

                        case "diarkriteLightArmor.Durability": diarkriteSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "diarkriteLightArmor.Helmet": diarkriteSamuraiLightArmor_Helmet = (int) value; break;
                        case "diarkriteLightArmor.Chestplate": diarkriteSamuraiLightArmor_Chestplate = (int) value; break;
                        case "diarkriteLightArmor.Leggings": diarkriteSamuraiLightArmor_Leggings = (int) value; break;
                        case "diarkriteLightArmor.Boots": diarkriteSamuraiLightArmor_Boots = (int) value; break;
                        case "diarkriteLightArmor.Enchantability": diarkriteSamuraiLightArmor_Enchantability = (int) value; break;
                        case "diarkriteLightArmor.Toughness": diarkriteSamuraiLightArmor_Toughness = value; break;
                        case "diarkriteLightArmor.KnockbackResistance": diarkriteSamuraiLightArmor_KnockbackResistance = value; break;
                        case "diarkriteLightArmor.AttackSpeedBoost": diarkriteSamuraiLightArmor_AttackSpeed = value; break;
                        case "diarkriteLightArmor.MovementSpeed": diarkriteSamuraiLightArmor_MovementSpeed = value; break;

                        case "diarkriteMasterArmor.Durability": diarkriteSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "diarkriteMasterArmor.Helmet": diarkriteSamuraiMasterArmor_Helmet = (int) value; break;
                        case "diarkriteMasterArmor.Chestplate": diarkriteSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "diarkriteMasterArmor.Leggings": diarkriteSamuraiMasterArmor_Leggings = (int) value; break;
                        case "diarkriteMasterArmor.Boots": diarkriteSamuraiMasterArmor_Boots = (int) value; break;
                        case "diarkriteMasterArmor.Enchantability": diarkriteSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "diarkriteMasterArmor.Toughness": diarkriteSamuraiMasterArmor_Toughness = value; break;
                        case "diarkriteMasterArmor.KnockbackResistance": diarkriteSamuraiMasterArmor_KnockbackResistance = value; break;
                        case "diarkriteMasterArmor.AttackSpeedBoost": diarkriteSamuraiMasterArmor_AttackSpeed = value; break;
                        case "diarkriteMasterArmor.MovementSpeed": diarkriteSamuraiMasterArmor_MovementSpeed = value; break;


                        case "anthektiteArmor.Durability": anthektiteSamuraiArmor_DurabilityForType = (int) value; break;
                        case "anthektiteArmor.Helmet": anthektiteSamuraiArmor_Helmet = (int) value; break;
                        case "anthektiteArmor.Chestplate": anthektiteSamuraiArmor_Chestplate = (int) value; break;
                        case "anthektiteArmor.Leggings": anthektiteSamuraiArmor_Leggings = (int) value; break;
                        case "anthektiteArmor.Boots": anthektiteSamuraiArmor_Boots = (int) value; break;
                        case "anthektiteArmor.Enchantability": anthektiteSamuraiArmor_Enchantability = (int) value; break;
                        case "anthektiteArmor.Toughness": anthektiteSamuraiArmor_Toughness = value; break;
                        case "anthektiteArmor.KnockbackResistance": anthektiteSamuraiArmor_KnockbackResistance = value; break;
                        case "anthektiteArmor.AttackSpeedBoost": anthektiteSamuraiArmor_AttackSpeed = value; break;
                        case "anthektiteArmor.MovementSpeed": anthektiteSamuraiArmor_MovementSpeed = value; break;

                        case "anthektiteLightArmor.Durability": anthektiteSamuraiLightArmor_DurabilityForType = (int) value; break;
                        case "anthektiteLightArmor.Helmet": anthektiteSamuraiLightArmor_Helmet = (int) value; break;
                        case "anthektiteLightArmor.Chestplate": anthektiteSamuraiLightArmor_Chestplate = (int) value; break;
                        case "anthektiteLightArmor.Leggings": anthektiteSamuraiLightArmor_Leggings = (int) value; break;
                        case "anthektiteLightArmor.Boots": anthektiteSamuraiLightArmor_Boots = (int) value; break;
                        case "anthektiteLightArmor.Enchantability": anthektiteSamuraiLightArmor_Enchantability = (int) value; break;
                        case "anthektiteLightArmor.Toughness": anthektiteSamuraiLightArmor_Toughness = value; break;
                        case "anthektiteLightArmor.KnockbackResistance": anthektiteSamuraiLightArmor_KnockbackResistance = value; break;
                        case "anthektiteLightArmor.AttackSpeedBoost": anthektiteSamuraiLightArmor_AttackSpeed = value; break;
                        case "anthektiteLightArmor.MovementSpeed": anthektiteSamuraiLightArmor_MovementSpeed = value; break;

                        case "anthektiteMasterArmor.Durability": anthektiteSamuraiMasterArmor_DurabilityForType = (int) value; break;
                        case "anthektiteMasterArmor.Helmet": anthektiteSamuraiMasterArmor_Helmet = (int) value; break;
                        case "anthektiteMasterArmor.Chestplate": anthektiteSamuraiMasterArmor_Chestplate = (int) value; break;
                        case "anthektiteMasterArmor.Leggings": anthektiteSamuraiMasterArmor_Leggings = (int) value; break;
                        case "anthektiteMasterArmor.Boots": anthektiteSamuraiMasterArmor_Boots = (int) value; break;
                        case "anthektiteMasterArmor.Enchantability": anthektiteSamuraiMasterArmor_Enchantability = (int) value; break;
                        case "anthektiteMasterArmor.Toughness": anthektiteSamuraiMasterArmor_Toughness = value; break;
                        case "anthektiteMasterArmor.KnockbackResistance": anthektiteSamuraiMasterArmor_KnockbackResistance = value; break;
                        case "anthektiteMasterArmor.AttackSpeedBoost": anthektiteSamuraiMasterArmor_AttackSpeed = value; break;
                        case "anthektiteMasterArmor.MovementSpeed": anthektiteSamuraiMasterArmor_MovementSpeed = value; break;
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
            logger.info("Config version outdated, Updating config \"samurai_dynasty_config\"!");
            save();
        }
    }

    private void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_PATH)) {
            writer.write("version = " + VERSION + "\n");
            writer.write("# Note: Restart minecraft to apply changes in config\n");
            writer.write("# Samurai Dynasty Item Stats Config\n");
            writer.write("# Stat Examples\n");
            writer.write("# [Material |Harvest Level |Durability |Damage |Efficiency |Enchantability]\n");
            writer.write("# [Wood     |0             |59         |2      |0          |15            ]\n");
            writer.write("# [Gold     |0             |32         |12     |0          |22            ]\n");
            writer.write("# [Stone    |1             |131        |4      |1          |5             ]\n");
            writer.write("# [Iron     |2             |250        |6      |2          |14            ]\n");
            writer.write("# [Diamond  |3             |1561       |8      |3          |10            ]\n");
            writer.write("# [Netherite|4             |2031       |9      |4          |15            ]\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[SteelSamurai]");
            writer.write("# Default: " + steelSamuraiArmor_DurabilityForType + "\n");
            writer.write("  steelArmor.Durability = " + steelSamuraiArmor_DurabilityForType + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Enchantability + "\n");
            writer.write("  steelArmor.Enchantability = " + steelSamuraiArmor_Enchantability + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Helmet + "\n");
            writer.write("  steelArmor.Helmet = " + steelSamuraiArmor_Helmet + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Chestplate + "\n");
            writer.write("  steelArmor.Chestplate = " + steelSamuraiArmor_Chestplate + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Leggings + "\n");
            writer.write("  steelArmor.Leggings = " + steelSamuraiArmor_Leggings + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Boots + "\n");
            writer.write("  steelArmor.Boots = " + steelSamuraiArmor_Boots + "\n");
            writer.write("# Default: " + steelSamuraiArmor_Toughness + "\n");
            writer.write("  steelArmor.Toughness = " + steelSamuraiArmor_Toughness + "\n");
            writer.write("# Default: " + steelSamuraiArmor_KnockbackResistance + "\n");
            writer.write("  steelArmor.KnockbackResistance = " + steelSamuraiArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + steelSamuraiArmor_AttackSpeed + "\n");
            writer.write("  steelArmor.AttackSpeedBoost = " + steelSamuraiArmor_AttackSpeed + "\n");
            writer.write("# Default: " + steelSamuraiArmor_MovementSpeed + "\n");
            writer.write("  steelArmor.MovementSpeed = " + steelSamuraiArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[SteelSamuraiLight]");
            writer.write("# Default: " + steelSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("  steelLightArmor.Durability = " + steelSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Enchantability + "\n");
            writer.write("  steelLightArmor.Enchantability = " + steelSamuraiLightArmor_Enchantability + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Helmet + "\n");
            writer.write("  steelLightArmor.Helmet = " + steelSamuraiLightArmor_Helmet + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Chestplate + "\n");
            writer.write("  steelLightArmor.Chestplate = " + steelSamuraiLightArmor_Chestplate + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Leggings + "\n");
            writer.write("  steelLightArmor.Leggings = " + steelSamuraiLightArmor_Leggings + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Boots + "\n");
            writer.write("  steelLightArmor.Boots = " + steelSamuraiLightArmor_Boots + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_Toughness + "\n");
            writer.write("  steelLightArmor.Toughness = " + steelSamuraiLightArmor_Toughness + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("  steelLightArmor.KnockbackResistance = " + steelSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("  steelLightArmor.AttackSpeedBoost = " + steelSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("# Default: " + steelSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("  steelLightArmor.MovementSpeed = " + steelSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[SteelSamuraiMaster]");
            writer.write("# Default: " + steelSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("  steelMasterArmor.Durability = " + steelSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Enchantability + "\n");
            writer.write("  steelMasterArmor.Enchantability = " + steelSamuraiMasterArmor_Enchantability + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Helmet + "\n");
            writer.write("  steelMasterArmor.Helmet = " + steelSamuraiMasterArmor_Helmet + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Chestplate + "\n");
            writer.write("  steelMasterArmor.Chestplate = " + steelSamuraiMasterArmor_Chestplate + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Leggings + "\n");
            writer.write("  steelMasterArmor.Leggings = " + steelSamuraiMasterArmor_Leggings + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Boots + "\n");
            writer.write("  steelMasterArmor.Boots = " + steelSamuraiMasterArmor_Boots + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_Toughness + "\n");
            writer.write("  steelMasterArmor.Toughness = " + steelSamuraiMasterArmor_Toughness + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("  steelMasterArmor.KnockbackResistance = " + steelSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("  steelMasterArmor.AttackSpeedBoost = " + steelSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("# Default: " + steelSamuraiMasterArmor_MovementSpeed + "\n");
            writer.write("  steelMasterArmor.MovementSpeed = " + steelSamuraiMasterArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[DiarkriteSamurai]");
            writer.write("# Default: " + diarkriteSamuraiArmor_DurabilityForType + "\n");
            writer.write("  diarkriteArmor.Durability = " + diarkriteSamuraiArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Enchantability + "\n");
            writer.write("  diarkriteArmor.Enchantability = " + diarkriteSamuraiArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Helmet + "\n");
            writer.write("  diarkriteArmor.Helmet = " + diarkriteSamuraiArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Chestplate + "\n");
            writer.write("  diarkriteArmor.Chestplate = " + diarkriteSamuraiArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Leggings + "\n");
            writer.write("  diarkriteArmor.Leggings = " + diarkriteSamuraiArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Boots + "\n");
            writer.write("  diarkriteArmor.Boots = " + diarkriteSamuraiArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_Toughness + "\n");
            writer.write("  diarkriteArmor.Toughness = " + diarkriteSamuraiArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("  diarkriteArmor.KnockbackResistance = " + diarkriteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_AttackSpeed + "\n");
            writer.write("  diarkriteArmor.AttackSpeedBoost = " + diarkriteSamuraiArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteSamuraiArmor_MovementSpeed + "\n");
            writer.write("  diarkriteArmor.MovementSpeed = " + diarkriteSamuraiArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteSamuraiLight]");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("  diarkriteLightArmor.Durability = " + diarkriteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Enchantability + "\n");
            writer.write("  diarkriteLightArmor.Enchantability = " + diarkriteSamuraiLightArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Helmet + "\n");
            writer.write("  diarkriteLightArmor.Helmet = " + diarkriteSamuraiLightArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Chestplate + "\n");
            writer.write("  diarkriteLightArmor.Chestplate = " + diarkriteSamuraiLightArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Leggings + "\n");
            writer.write("  diarkriteLightArmor.Leggings = " + diarkriteSamuraiLightArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Boots + "\n");
            writer.write("  diarkriteLightArmor.Boots = " + diarkriteSamuraiLightArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_Toughness + "\n");
            writer.write("  diarkriteLightArmor.Toughness = " + diarkriteSamuraiLightArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("  diarkriteLightArmor.KnockbackResistance = " + diarkriteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("  diarkriteLightArmor.AttackSpeedBoost = " + diarkriteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("  diarkriteLightArmor.MovementSpeed = " + diarkriteSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[DiarkriteSamuraiMaster]");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("  diarkriteMasterArmor.Durability = " + diarkriteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Enchantability + "\n");
            writer.write("  diarkriteMasterArmor.Enchantability = " + diarkriteSamuraiMasterArmor_Enchantability + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Helmet + "\n");
            writer.write("  diarkriteMasterArmor.Helmet = " + diarkriteSamuraiMasterArmor_Helmet + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("  diarkriteMasterArmor.Chestplate = " + diarkriteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Leggings + "\n");
            writer.write("  diarkriteMasterArmor.Leggings = " + diarkriteSamuraiMasterArmor_Leggings + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Boots + "\n");
            writer.write("  diarkriteMasterArmor.Boots = " + diarkriteSamuraiMasterArmor_Boots + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_Toughness + "\n");
            writer.write("  diarkriteMasterArmor.Toughness = " + diarkriteSamuraiMasterArmor_Toughness + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("  diarkriteMasterArmor.KnockbackResistance = " + diarkriteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("  diarkriteMasterArmor.AttackSpeedBoost = " + diarkriteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("# Default: " + diarkriteSamuraiMasterArmor_MovementSpeed + "\n");
            writer.write("  diarkriteMasterArmor.MovementSpeed = " + diarkriteSamuraiMasterArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("\n");
            writer.write("[AnthektiteSamurai]");
            writer.write("# Default: " + anthektiteSamuraiArmor_DurabilityForType + "\n");
            writer.write("  anthektiteArmor.Durability = " + anthektiteSamuraiArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Enchantability + "\n");
            writer.write("  anthektiteArmor.Enchantability = " + anthektiteSamuraiArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Helmet + "\n");
            writer.write("  anthektiteArmor.Helmet = " + anthektiteSamuraiArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Chestplate + "\n");
            writer.write("  anthektiteArmor.Chestplate = " + anthektiteSamuraiArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Leggings + "\n");
            writer.write("  anthektiteArmor.Leggings = " + anthektiteSamuraiArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Boots + "\n");
            writer.write("  anthektiteArmor.Boots = " + anthektiteSamuraiArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_Toughness + "\n");
            writer.write("  anthektiteArmor.Toughness = " + anthektiteSamuraiArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("  anthektiteArmor.KnockbackResistance = " + anthektiteSamuraiArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_AttackSpeed + "\n");
            writer.write("  anthektiteArmor.AttackSpeedBoost = " + anthektiteSamuraiArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteSamuraiArmor_MovementSpeed + "\n");
            writer.write("  anthektiteArmor.MovementSpeed = " + anthektiteSamuraiArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteSamuraiLight]");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("  anthektiteLightArmor.Durability = " + anthektiteSamuraiLightArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Enchantability + "\n");
            writer.write("  anthektiteLightArmor.Enchantability = " + anthektiteSamuraiLightArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Helmet + "\n");
            writer.write("  anthektiteLightArmor.Helmet = " + anthektiteSamuraiLightArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Chestplate + "\n");
            writer.write("  anthektiteLightArmor.Chestplate = " + anthektiteSamuraiLightArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Leggings + "\n");
            writer.write("  anthektiteLightArmor.Leggings = " + anthektiteSamuraiLightArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Boots + "\n");
            writer.write("  anthektiteLightArmor.Boots = " + anthektiteSamuraiLightArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_Toughness + "\n");
            writer.write("  anthektiteLightArmor.Toughness = " + anthektiteSamuraiLightArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("  anthektiteLightArmor.KnockbackResistance = " + anthektiteSamuraiLightArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("  anthektiteLightArmor.AttackSpeedBoost = " + anthektiteSamuraiLightArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("  anthektiteLightArmor.MovementSpeed = " + anthektiteSamuraiLightArmor_MovementSpeed + "\n");
            writer.write("\n");
            writer.write("[AnthektiteSamuraiMaster]");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("  anthektiteMasterArmor.Durability = " + anthektiteSamuraiMasterArmor_DurabilityForType + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Enchantability + "\n");
            writer.write("  anthektiteMasterArmor.Enchantability = " + anthektiteSamuraiMasterArmor_Enchantability + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Helmet + "\n");
            writer.write("  anthektiteMasterArmor.Helmet = " + anthektiteSamuraiMasterArmor_Helmet + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("  anthektiteMasterArmor.Chestplate = " + anthektiteSamuraiMasterArmor_Chestplate + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Leggings + "\n");
            writer.write("  anthektiteMasterArmor.Leggings = " + anthektiteSamuraiMasterArmor_Leggings + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Boots + "\n");
            writer.write("  anthektiteMasterArmor.Boots = " + anthektiteSamuraiMasterArmor_Boots + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_Toughness + "\n");
            writer.write("  anthektiteMasterArmor.Toughness = " + anthektiteSamuraiMasterArmor_Toughness + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("  anthektiteMasterArmor.KnockbackResistance = " + anthektiteSamuraiMasterArmor_KnockbackResistance + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("  anthektiteMasterArmor.AttackSpeedBoost = " + anthektiteSamuraiMasterArmor_AttackSpeed + "\n");
            writer.write("# Default: " + anthektiteSamuraiMasterArmor_MovementSpeed + "\n");
            writer.write("  anthektiteMasterArmor.MovementSpeed = " + anthektiteSamuraiMasterArmor_MovementSpeed + "\n");
        } catch (IOException e) {
            logger.warn("Could not save configuration file: ", e);
        }
    }
}
