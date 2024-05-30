package net.nokunami.elementus.compat.simplyswords.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "weapon_attributes")
public class WeaponAttributesConfig implements ConfigData {
    @ConfigEntry.Gui.PrefixText
    public float steel_damageModifier = 3.0f;
    public float diarkrite_damageModifier = 3.0f;
    public float anthektite_damageModifier = 3.0f;

    public float darkrite_atkSpeedAdd = 0.0F;
    public float darkrite_atkSpeedNeg = 0.3F;
    public float anthektite_atkSpeedAdd = 0.4f;
    public float anthektite_atkSpeedNeg = 0.0f;
}
