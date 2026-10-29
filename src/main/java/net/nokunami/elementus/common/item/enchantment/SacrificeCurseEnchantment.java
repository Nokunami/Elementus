package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.config.EnchantmentConfig;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.item.unique.BladeOfResonance;
import net.nokunami.elementus.common.item.unique.ChargeBladeAttributes;
import net.nokunami.elementus.common.registry.EDamageTypes;
import net.nokunami.elementus.common.registry.EEnchantments;
import net.nokunami.elementus.common.registry.EMobEffects;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.config.EConfig.enchantmentConfig;

public class SacrificeCurseEnchantment extends Enchantment implements IChargeBladeEnchantment {
    double bonusDamage = enchantmentConfig.sacrifice.sacrificeDamageBonus;
    double selfDamage = enchantmentConfig.sacrifice.selfSacrificeDamage;
    EnchantmentConfig.sacrifice config = enchantmentConfig.sacrifice;
    private final EnchantmentConfig.baseEnchantConfig properties = enchantmentConfig.sacrifice.properties;

    public static SacrificeCurseEnchantment init() {
        return new SacrificeCurseEnchantment(Enchantment.Rarity.VERY_RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND);
    }

    public SacrificeCurseEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override public boolean isTreasureOnly() { return properties.isTreasure; }
    @Override public boolean isCurse() { return properties.isCurse; }
    @Override public boolean isTradeable() { return properties.isTradable; }
    @Override public boolean isDiscoverable() { return properties.isDiscoverable; }
    @Override public boolean isAllowedOnBooks() { return properties.isAllowedOnBooks; }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return super.canApplyAtEnchantingTable(stack);
    }


    @Override
    protected boolean checkCompatibility(@NotNull Enchantment ench) {
        return !(ench instanceof ChargeStackingEnchantment) && super.checkCompatibility(ench);
    }

    @Override public boolean canEnchant(@NotNull ItemStack stack) { return stack.getItem() instanceof BladeOfResonance; }

    @Override
    public void getDescription(List<Component> tooltip, ItemStack stack) {
//        boolean hasBonus = !(diarkriteChargeBladeSacrificeDamageBonus <= 0);
//        boolean hasPenalty = !(diarkriteChargeBladeSelfSacrificeDamage <= 0);
        boolean hasBonus = !(bonusDamage <= 0);
        boolean hasPenalty = !(selfDamage <= 0);
        if (hasBonus) tooltip.add(bonusComponent().copy());
        if (hasPenalty) tooltip.add(penaltyComponent().copy());
    }

    Component bonusComponent() {
        return Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".bonus_desc",
                        Math.round((bonusDamage + 1) * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.YELLOW));
    }
    Component penaltyComponent() {
        return Component.literal("| ").withStyle(ChatFormatting.GRAY)
                .append(Component.translatable(getDescriptionId() + ".penalty_desc",
                        Math.round(selfDamage * 100),
                        Component.translatable(getDescriptionId()))
                        .withStyle(ChatFormatting.RED));
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return super.getDamageBonus(level, mobType, enchantedItem);
    }

    @Override
    public float bonusChargeBladeDamage() {
        return (float) bonusDamage;
    }

    @Override
    public void postUseEvent(LivingEntity entity) {
        MobUtil.hurt(entity, EDamageTypes.SACRIFICIAL, entity, entity.getMaxHealth() * (float) selfDamage, 0);
        entity.addEffect(new MobEffectInstance(EMobEffects.SACRIFICE_PENALTY.get(), 40));
    }

    @Override
    public ChargeBladeAttributes attributes() {
        return new ChargeBladeAttributes()
                .overrideDamage(config.sacrificeBaseDamage)
                .chargeDamageMultiplier(config.sacrificeDamageBonus)
                .color("00FF5555");
    }
}