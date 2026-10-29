package net.nokunami.elementus.common.registry;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.enchantment.*;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;

public class EEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Elementus.EID);

    public static EnchantmentCategory CHARGE_BLADE = EnchantmentCategory.create("CHARGE_BLADE", i -> i instanceof ChargeBladeItem);

    public static final RegistryObject<Enchantment> ARCANE_SHARPNESS = ENCHANTMENT.register("arcane_sharpness", ArcaneSharpnessEnchantment::init);
    public static final RegistryObject<Enchantment> SACRIFICE_CURSE = ENCHANTMENT.register("sacrifice_curse", SacrificeCurseEnchantment::init);
    public static final RegistryObject<Enchantment> RESONANCE = ENCHANTMENT.register("resonance", ResonanceEnchantment::init);
    public static final RegistryObject<Enchantment> CONDENSED_BURST = ENCHANTMENT.register("condensed_burst", CondensedBurstEnchantment::init);
    public static final RegistryObject<Enchantment> RUSH = ENCHANTMENT.register("rush", RushEnchantment::init);
    public static final RegistryObject<Enchantment> CHARGE_STACKING = ENCHANTMENT.register("charge_stacking", ChargeStackingEnchantment::init);
    public static final RegistryObject<Enchantment> PULSE_BURST = ENCHANTMENT.register("pulse_burst", PulseBurstEnchantment::init);
    public static final RegistryObject<Enchantment> CLEAVE = ENCHANTMENT.register("cleave", CleaveCurseEnchantment::init);
    public static final RegistryObject<Enchantment> TORRENT = ENCHANTMENT.register("torrent", TorrentEnchantment::init);

    public static void register(IEventBus eventBus) { ENCHANTMENT.register(eventBus); }
}