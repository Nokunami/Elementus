package nokunami.elementus.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.item.enchantment.*;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENT = DeferredRegister.create(Registries.ENCHANTMENT, Elementus.MODID);

    public static final RegistryObject<Enchantment> ARCANE_SHARPNESS = ENCHANTMENT.register("arcane_sharpness",
            () -> new ArcaneSharpnessEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> SACRIFICE_CURSE = ENCHANTMENT.register("sacrifice_curse",
            () -> new SacrificeCurseEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> RESONANCE = ENCHANTMENT.register("resonance",
            () -> new ResonanceEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> CONDENSED_BURST = ENCHANTMENT.register("condensed_burst",
            () -> new CondensedBurstEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> RUSH = ENCHANTMENT.register("rush",
            () -> new RushEnchantment(Enchantment.Rarity.RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static final RegistryObject<Enchantment> MULTI_CHARGE = ENCHANTMENT.register("multi_charge",
            () -> new MultiChargeEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENT.register(eventBus);
    }
}
