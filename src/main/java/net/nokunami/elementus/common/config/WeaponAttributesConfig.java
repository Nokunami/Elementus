package net.nokunami.elementus.common.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "weapon_attributes")
public class WeaponAttributesConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    @Comment("Steel Damage Modifier is now using SimplySwords's Config")
    public float diarkrite_damageModifier = 3.0F;
    public float anthektite_damageModifier = 3.0F;

    public float simply_sword_darkrite_atkSpeedAdd = 0.0F;
    public float simply_sword_darkrite_atkSpeedNeg = 0.3F;
    public float simply_sword_anthektite_atkSpeedAdd = 0.5F;
    public float simply_sword_anthektite_atkSpeedNeg = 0.0F;
}
