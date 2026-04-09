package net.nokunami.elementus.common.config.configSets.armor;

import net.minecraft.Util;
import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.nokunami.elementus.common.config.configSets.item.ToolSetConfig;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ArmorMaterialConfig {
    public final ArmorSetConfig armorSetConfig;
    public final ArmorAttributesConfig attributesConfig;

    public ArmorMaterialConfig(final ForgeConfigSpec.Builder builder, String path, String id, int defaultHelmet, int defaultChsetplate, int defaultLeggings, int defaultBoots, int defaultDurability, int defaultEnchantability, double defaultToughness, double defaultKnockback, double defaultAttackSpeed, double defaultMovementSpeed) {
        armorSetConfig = new ArmorSetConfig(builder, path, id, defaultHelmet, defaultChsetplate, defaultLeggings, defaultBoots);
        attributesConfig = new ArmorAttributesConfig(builder, path, id, defaultDurability, defaultEnchantability, defaultToughness, defaultKnockback, defaultAttackSpeed, defaultMovementSpeed);
    }

    public void reload(ArmorMaterialConfig config) {
    }

    public EnumMap<ArmorItem.Type, Integer> getArmor() { return null; }
    public int getDurability(){ return 0; }
    public int getEnchantability(){ return 0; }
    public double getToughness(){ return 0; }
    public double getKnockback(){ return 0; }
    public double getAttackSpeed(){ return 0; }
    public double getMovementSpeed(){ return 0; }
}