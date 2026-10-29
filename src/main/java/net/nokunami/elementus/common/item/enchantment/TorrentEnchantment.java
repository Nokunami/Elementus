package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.nokunami.elementus.config.EnchantmentConfig;
import net.nokunami.elementus.common.item.unique.BladeOfSurgingWinds;
import net.nokunami.elementus.common.registry.EEnchantments;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.nokunami.elementus.config.EConfig.enchantmentConfig;

public class TorrentEnchantment extends Enchantment implements IChargeBladeEnchantment {
    EnchantmentConfig.baseEnchantConfig properties = enchantmentConfig.torrent.properties;

    public static TorrentEnchantment init() { return new TorrentEnchantment(Rarity.RARE, EEnchantments.CHARGE_BLADE, EquipmentSlot.MAINHAND); }

    public TorrentEnchantment(Rarity rarity, EnchantmentCategory category, EquipmentSlot... applicableSlots) {
        super(rarity, category, applicableSlots);
    }

    @Override public boolean isTreasureOnly() { return properties.isTreasure; }
    @Override public boolean isCurse() { return properties.isCurse; }
    @Override public boolean isTradeable() { return properties.isTradable; }
    @Override public boolean isDiscoverable() { return properties.isDiscoverable; }
    @Override public boolean isAllowedOnBooks() { return properties.isAllowedOnBooks; }

    @Override public boolean canEnchant(@NotNull ItemStack stack) { return stack.getItem() instanceof BladeOfSurgingWinds; }

    @Override public void getDescription(List<Component> tooltip, ItemStack stack) { }
}