package net.nokunami.elementus.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.nokunami.elementus.Elementus;

import java.util.function.Supplier;

public enum ArmorTiers implements ArmorMaterial {
    STEEL("steel", 24, new int[]{ 2, 6, 7, 2 }, 10,
            SoundEvents.ARMOR_EQUIP_IRON, 1.0f, 0.025f, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get())),
    ANTHEKTITE("anthektite", 34, new int[]{ 3, 6, 8, 3 }, 15,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 2.25f, 0.125f, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get())),
    DIARKRITE("diarkrite", 40, new int[]{ 3, 6, 8, 3 }, 18,
            SoundEvents.ARMOR_EQUIP_NETHERITE, 4.0f, 0.175f, () -> Ingredient.of(ItemsRegistry.STEEL_INGOT.get()));

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantmentValue;
    private final SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = { 11, 16, 16, 13 };

    ArmorTiers(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantmentValue, SoundEvent equipSound,
                      float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantmentValue = enchantmentValue;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return BASE_DURABILITY[pType.ordinal()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionAmounts[pType.ordinal()];
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @Override
    public String getName() {
        return Elementus.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
