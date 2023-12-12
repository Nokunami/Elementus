package net.nokunami.elementus.core;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class EArttibutes {
    public static final DeferredRegister<Attribute>
            ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, Elementus.MODID);
    public static final RegistryObject<Attribute>
            MOVEMENT_SPEED = ATTRIBUTES
            .register("movement_speed",
                    () -> new RangedAttribute("attribute.name.generic.movement_speed",
                            0.0D, 0.0D, 1.0D));
    public static final RegistryObject<Attribute>
            ARMOR = ATTRIBUTES
            .register("explosive_damage_reduction",
                    () -> new RangedAttribute("attribute.name.generic.armor",
                            0.0D, 0.0D, 1.0D));
    public static final RegistryObject<Attribute>
            ARMOR_TOUGHNESS = ATTRIBUTES
            .register("explosive_damage_reduction",
                    () -> new RangedAttribute("attribute.name.generic.armor_toughness",
                            0.0D, 0.0D, 1.0D));
    public static final RegistryObject<Attribute>
            KNOCKBACK_RESISTANCE = ATTRIBUTES
            .register("explosive_damage_reduction",
                    () -> new RangedAttribute("attribute.name.generic.knockback_resistance",
                            0.0D, 0.0D, 1.0D));
    public static final RegistryObject<Attribute>
            ATTACK_DAMGE = ATTRIBUTES
            .register("explosive_damage_reduction",
                    () -> new RangedAttribute("attribute.name.generic.bonus_attack_damage",
                            0.0D, 0.0D, 1.0D));
}
