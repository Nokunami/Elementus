package net.nokunami.elementus.common.catalystCore;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;
import java.util.function.Supplier;

import static net.nokunami.elementus.ModChecker.ironsSpellbooks;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystISSConfig.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.IronsAttributeRegistry.*;

public class CatalystArmorAttributes {
    public static UUID armorUUID = UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E");

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> baseAttributes(int armor, double toughness, double knockbackResist, double attackSpeed, double moveSpeed) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(armorUUID, "Armor modifier", armor, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(armorUUID, "Armor toughness", toughness, AttributeModifier.Operation.ADDITION));
        if (KnockbackResist > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorUUID, "Knockback resistance", knockbackResist, AttributeModifier.Operation.ADDITION));
        }
        if (AttackSpeed > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorUUID, "Attack Speed", attackSpeed, AttributeModifier.Operation.MULTIPLY_TOTAL));
        }
        if (MovementSpeed > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(armorUUID, "MovementSpeed", moveSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        return builder;
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> baseAttributes() {
        return baseAttributes(Armor, Toughness, KnockbackResist, AttackSpeed, MovementSpeed);
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> arcaneIngot() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", ISS_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", ISS_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", ISS_SpellPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", ISS_SpellResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> fireRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", fireRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", fireRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", fireRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", fireRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, FIRE_SPELL_POWER, "School SpellPower", fireRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, FIRE_MAFIC_RESIST, "School SpellResist", fireRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> iceRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", iceRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", iceRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", iceRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", iceRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, ICE_SPELL_POWER, "School SpellPower", iceRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, ICE_MAFIC_RESIST, "School SpellResist", iceRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> lightningRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", lightningRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", lightningRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", lightningRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", lightningRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, LIGHTNING_SPELL_POWER, "School SpellPower", lightningRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, LIGHTNING_MAFIC_RESIST, "School SpellResist", lightningRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> holyRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", holyRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", holyRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", holyRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", holyRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, HOLY_SPELL_POWER, "School SpellPower", holyRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, HOLY_MAFIC_RESIST, "School SpellResist", holyRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> enderRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", enderRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", enderRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", enderRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", enderRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, ENDER_SPELL_POWER, "School SpellPower", enderRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, ENDER_MAFIC_RESIST, "School SpellResist", enderRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> bloodRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", bloodRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", bloodRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", bloodRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", bloodRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, BLOOD_SPELL_POWER, "School SpellPower", bloodRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, BLOOD_MAFIC_RESIST, "School SpellResist", bloodRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> evocationRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", evocationRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", evocationRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", evocationRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", evocationRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, EVOCATION_SPELL_POWER, "School SpellPower", evocationRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, EVOCATION_MAFIC_RESIST, "School SpellResist", evocationRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> natureRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", natureRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", natureRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", natureRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", natureRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, NATURE_SPELL_POWER, "School SpellPower", natureRune_Power, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, NATURE_MAFIC_RESIST, "School SpellResist", natureRune_Resist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }

    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> arcaneRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", arcaneRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", arcaneRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", arcaneRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", arcaneRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> cooldownRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", cooldownRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", cooldownRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", cooldownRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", cooldownRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> protectionRune() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = baseAttributes();
        if (ironsSpellbooks) {
            toggleableAttribute(builder, MAX_MANA, "Max Mana", protectionRune_MaxMana, AttributeModifier.Operation.ADDITION);
            toggleableAttribute(builder, MANA_REGEN, "Mana Regen", protectionRune_ManaRegen, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_POWER, "Spell Power", protectionRune_SPower, AttributeModifier.Operation.MULTIPLY_TOTAL);
            toggleableAttribute(builder, SPELL_RESIST, "Spell Resist", protectionRune_SResist, AttributeModifier.Operation.MULTIPLY_TOTAL);
        }
        return builder;
    }

//    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, boolean condition, Supplier<Attribute> attribute, String name, double value, AttributeModifier.Operation operation) {
//        if (condition) builder.put(attribute.get(), new AttributeModifier(armorUUID, name, value, operation));
//        return builder;
//    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, boolean condition, Attribute attribute, String name, double value, AttributeModifier.Operation operation) {
        if (condition) builder.put(attribute, new AttributeModifier(armorUUID, name, value, operation));
        return builder;
    }

//    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, Attribute attribute, String name, double value, AttributeModifier.Operation operation) {
//        return toggleableAttribute(builder, value != 0, attribute, name, value, operation);
//    }
    public static ImmutableMultimap.Builder<Attribute, AttributeModifier> toggleableAttribute(ImmutableMultimap.Builder<Attribute, AttributeModifier> builder, Supplier<Attribute> attribute, String name, double value, AttributeModifier.Operation operation) {
        return toggleableAttribute(builder, value != 0, attribute.get(), name, value, operation);
    }
}
