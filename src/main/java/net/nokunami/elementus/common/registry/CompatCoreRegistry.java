package net.nokunami.elementus.common.registry;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.catalystCore.core.IgnitiumCatalystCore;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.CatalysmItems.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.IronsItemRegistry.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CompatCoreRegistry {
    public static class CataclysmCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<CatalystCore> IGNITIUM = CORE.register("ignitium", IgnitiumCatalystCore::new);
        public static final RegistryObject<CatalystCore> CURSIUM = registerCore("cursium", CURSIUM_INGOT, ChatFormatting.DARK_AQUA, new CatalystCoreAttributes.Builder()
                .passiveAbility(CatalystAbilities.CURSIUM));
        public static final RegistryObject<CatalystCore> ESSENCE_OF_STORM = registerCore("essence_of_storm", ESSENCE_OF_THE_STORM, ChatFormatting.AQUA, new CatalystCoreAttributes.Builder()
                .passiveAbility(CatalystAbilities.ESSENCE_OF_STORM));

        public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
            return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting, builder.build()));
        }

        public static void register(IEventBus eventBus) {
            if (cataclysm)
                CORE.register(eventBus);
        }
    }
    public static class IronsSpellbooksCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<CatalystCore> ARCANE_INGOT_CORE = registerCore("arcane_ingot", ARCANE_INGOT, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_FIRE = registerCore("fire_irons_spellbooks", FIRE_RUNE, ChatFormatting.GOLD, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_ICE = registerCore("ice_irons_spellbooks", ICE_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_ENDER = registerCore("ender_irons_spellbooks", ENDER_RUNE, ChatFormatting.LIGHT_PURPLE, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_LIGHTNING = registerCore("lightning_irons_spellbooks", LIGHTNING_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_HOLY = registerCore("holy_irons_spellbooks", HOLY_RUNE, ChatFormatting.YELLOW, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_BLOOD = registerCore("blood_irons_spellbooks", BLOOD_RUNE, ChatFormatting.DARK_RED, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_EVOCATION = registerCore("evocation_irons_spellbooks", EVOCATION_RUNE, ChatFormatting.DARK_GREEN, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_NATURE = registerCore("nature_irons_spellbooks", NATURE_RUNE, ChatFormatting.GREEN, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_ARCANE = registerCore("arcane_irons_spellbooks", MANA_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_COOLDOWN = registerCore("cooldown_irons_spellbooks", COOLDOWN_RUNE, ChatFormatting.GRAY, CatalystArmorAttributes::arcaneIngot);
        public static final RegistryObject<CatalystCore> ISS_PROTECTION = registerCore("protection_irons_spellbooks", PROTECTION_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneIngot);

    public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, Supplier<ImmutableMultimap.Builder<Attribute, AttributeModifier>> armorAttributes) {
        return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting, armorAttributes.get()));
    }

        public static void register(IEventBus eventBus) {
            if (ironsSpellbooks)
                CORE.register(eventBus);
        }
    }
    public static class WitherstormModCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<Item> WITHERED_NETHER_STAR = RegistryObject.create(modLoc(witherStormModID, "withered_nether_star"), ForgeRegistries.ITEMS);

        public static final RegistryObject<CatalystCore> WITHERED_BEACON_POWER = registerCore("withered_beacon_power", WITHERED_NETHER_STAR, ChatFormatting.DARK_PURPLE, new CatalystCoreAttributes.Builder()
                .passiveAbility(CatalystAbilities.WITHERED_BEACON_POWER));

        public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
            return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting, builder.build()));
        }

        public static void register(IEventBus eventBus) {
            if (witherStormMod)
                CORE.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        CataclysmCores.register(eventBus);
        IronsSpellbooksCores.register(eventBus);
        WitherstormModCores.register(eventBus);
    }
}
