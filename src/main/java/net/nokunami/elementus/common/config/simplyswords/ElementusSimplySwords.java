package net.nokunami.elementus.common.config.simplyswords;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "weapon_attributes")
public class ElementusSimplySwords implements ConfigData {

    public double steel_damageModifier = 3.0;
    public double diarkrite_damageModifier = 3.0;
    public double anthektite_damageModifier = 3.0;

//    @Comment("Speed multiplayer for Diarkrite")
//    public double darkrite_atkSpeedAdd = 0.0;
//    public double darkrite_atkSpeedNeg = 0.3;
//
//    @Comment("Speed multiplayer for Anthektite")
//    public double anthektite_atkSpeedAdd = 0.5;
//    public double anthektite_atkSpeedNeg = 0.0;
    
    @Comment("Positive Values for Weapons")
    public float longsword_positiveDamageModifier = 0.0F;
    public float twinblade_positiveDamageModifier = 0.0F;
    public float rapier_positiveDamageModifier = 0.0F;
    public float katana_positiveDamageModifier = 0.0F;
    public float sai_positiveDamageModifier = 0.0F;
    public float spear_positiveDamageModifier = 0.0F;
    public float glaive_positiveDamageModifier = 0.0F;
    public float warglaive_positiveDamageModifier = 0.0F;
    public float cutlass_positiveDamageModifier = 0.0F;
    public float claymore_positiveDamageModifier = 2.0F;
    public float greataxe_positiveDamageModifier = 3.0F;
    public float greathammer_positiveDamageModifier = 4.0F;
    public float chakram_positiveDamageModifier = 0.0F;
    public float scythe_positiveDamageModifier = 1.0F;
    public float halberd_positiveDamageModifier = 3.0F;
    
    @Comment("Negative Values for Weapons")
    public float longsword_negativeDamageModifier = 0.0F;
    public float twinblade_negativeDamageModifier = 0.0F;
    public float rapier_negativeDamageModifier = 1.0F;
    public float katana_negativeDamageModifier = 0.0F;
    public float sai_negativeDamageModifier = 3.0F;
    public float spear_negativeDamageModifier = 0.0F;
    public float glaive_negativeDamageModifier = 0.0F;
    public float warglaive_negativeDamageModifier = 0.0F;
    public float cutlass_negativeDamageModifier = 0.0F;
    public float claymore_negativeDamageModifier = 0.0F;
    public float greataxe_negativeDamageModifier = 0.0F;
    public float greathammer_negativeDamageModifier = 0.0F;
    public float chakram_negativeDamageModifier = 1.0F;
    public float scythe_negativeDamageModifier = 0.0F;
    public float halberd_negativeDamageModifier = 0.0F;

    @Comment("AttackSpeed Values for Weapons")
    public float longsword_attackSpeed = -2.4F;
    public float twinblade_attackSpeed = -2.0F;
    public float rapier_attackSpeed = -1.8F;
    public float katana_attackSpeed = -2.0F;
    public float sai_attackSpeed = -1.5F;
    public float spear_attackSpeed = -2.7F;
    public float glaive_attackSpeed = -2.6F;
    public float warglaive_attackSpeed = -2.2F;
    public float cutlass_attackSpeed = -2.0F;
    public float claymore_attackSpeed = -2.8F;
    public float greataxe_attackSpeed = -3.1F;
    public float greathammer_attackSpeed = -3.2F;
    public float chakram_attackSpeed = -3.0F;
    public float scythe_attackSpeed = -2.7F;
    public float halberd_attackSpeed = -2.8F;
}
