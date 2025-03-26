package net.nokunami.elementus.common.registry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.enchantment.ArcaneSharpnessEnchantment;
import net.nokunami.elementus.common.item.enchantment.ResonanceEnchantment;
import net.nokunami.elementus.common.item.enchantment.SacrificeCurseEnchantment;
import net.nokunami.elementus.common.item.enchantment.CondensedBurstEnchantment;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Elementus.MODID);

    public static final RegistryObject<Enchantment> ARCANE_SHARPNESS = ENCHANTMENT.register("arcane_sharpness",
            () -> new ArcaneSharpnessEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SACRIFICE_CURSE = ENCHANTMENT.register("sacrifice_curse",
            () -> new SacrificeCurseEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> RESONANCE = ENCHANTMENT.register("resonance",
            () -> new ResonanceEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> CONDENSED_BURST = ENCHANTMENT.register("condensed_burst",
            () -> new CondensedBurstEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENT.register(eventBus);
    }
}
