package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.Lazy;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.registry.CompatRegistryObjectGetter;

import java.util.function.Supplier;

public class ISSCatalystCore extends CatalystCore{

    public ISSCatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting) {
        super(itemSupplier, formatting);
    }

    @Override
    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.CHEST) {
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.FIRE_RUNE.get()))
                return CatalystArmorAttributes.fireRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.ICE_RUNE.get()))
                return CatalystArmorAttributes.iceRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.ENDER_RUNE.get()))
                return CatalystArmorAttributes.enderRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.LIGHTNING_RUNE.get()))
                return CatalystArmorAttributes.lightningRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.HOLY_RUNE.get()))
                return CatalystArmorAttributes.holyRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.BLOOD_RUNE.get()))
                return CatalystArmorAttributes.bloodRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.EVOCATION_RUNE.get()))
                return CatalystArmorAttributes.evocationRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.NATURE_RUNE.get()))
                return CatalystArmorAttributes.natureRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.MANA_RUNE.get()))
                return CatalystArmorAttributes.arcaneRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.RECOVERY_RUNE.get()))
                return CatalystArmorAttributes.recoveryRune()::build;
            if (getCoreStack().is(CompatRegistryObjectGetter.IronsItemRegistry.PROTECTION_RUNE.get()))
                return CatalystArmorAttributes.protectionRune()::build;
        }
        return super.getAttributes(slot, stack);
    }
}