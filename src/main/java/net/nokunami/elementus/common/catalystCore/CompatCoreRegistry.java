package net.nokunami.elementus.common.catalystCore;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.registry.CatalystAbilities;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.CatalysmItems.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.IronsItemRegistry.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CompatCoreRegistry {
    public static class CataclysmCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<CatalystCore> IGNITIUM = CORE.register("ignitium", () -> new CatalystCore("ignitium", IGNITIUM_INGOT.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.GOLD)
                .descriptionNumber(2)
                .ability(CatalystAbilities.IGNITIUM)
                .build()));

        public static final RegistryObject<CatalystCore> CURSIUM = CORE.register("cursium", () -> new CatalystCore("cursium", CURSIUM_INGOT.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.DARK_AQUA)
                .ability(CatalystAbilities.CURSIUM)
                .build()));

        public static final RegistryObject<CatalystCore> ESSENCE_OF_STORM = CORE.register("essence_of_storm", () -> new CatalystCore("essence_of_storm", ESSENCE_OF_THE_STORM.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.AQUA)
                .ability(CatalystAbilities.ESSENCE_OF_STORM)
                .build()));

        public static void register(IEventBus eventBus) {
            if (cataclysm)
                CORE.register(eventBus);
        }
    }
    public static class IronsSpellbooksCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<CatalystCore> ARCANE_INGOT_CORE = CORE.register("arcane_ingot", () -> new CatalystCore("arcane_ingot", ARCANE_INGOT.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.AQUA)
                .ability(CatalystAbilities.ARCANE_INGOT)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_FIRE = CORE.register("fire_irons_spellbooks", () -> new CatalystCore("fire_irons_spellbooks", FIRE_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.GOLD)
                .ability(CatalystAbilities.FIRE_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_ICE = CORE.register("ice_irons_spellbooks", () -> new CatalystCore("ice_irons_spellbooks", ICE_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.AQUA)
                .ability(CatalystAbilities.ICE_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_ENDER = CORE.register("ender_irons_spellbooks", () -> new CatalystCore("ender_irons_spellbooks", ENDER_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.LIGHT_PURPLE)
                .ability(CatalystAbilities.ENDER_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_LIGHTNING = CORE.register("lightning_irons_spellbooks", () -> new CatalystCore("lightning_irons_spellbooks", LIGHTNING_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.AQUA)
                .ability(CatalystAbilities.LIGHTNING_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_HOLY = CORE.register("holy_irons_spellbooks", () -> new CatalystCore("holy_irons_spellbooks", HOLY_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.YELLOW)
                .ability(CatalystAbilities.HOLY_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_BLOOD = CORE.register("blood_irons_spellbooks", () -> new CatalystCore("blood_irons_spellbooks", BLOOD_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.DARK_RED)
                .ability(CatalystAbilities.BLOOD_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_EVOCATION = CORE.register("evocation_irons_spellbooks", () -> new CatalystCore("evocation_irons_spellbooks", EVOCATION_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.DARK_GREEN)
                .ability(CatalystAbilities.EVOCATION_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_NATURE = CORE.register("nature_irons_spellbooks", () -> new CatalystCore("nature_irons_spellbooks", NATURE_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.GREEN)
                .ability(CatalystAbilities.NATURE_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_ARCANE = CORE.register("arcane_irons_spellbooks", () -> new CatalystCore("arcane_irons_spellbooks", MANA_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.AQUA)
                .ability(CatalystAbilities.ARCANE_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_COOLDOWN = CORE.register("cooldown_irons_spellbooks", () -> new CatalystCore("cooldown_irons_spellbooks", COOLDOWN_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.GRAY)
                .ability(CatalystAbilities.COOLDOWN_RUNE)
                .build()));
        public static final RegistryObject<CatalystCore> ISS_PROTECTION = CORE.register("protection_irons_spellbooks", () -> new CatalystCore("protection_irons_spellbooks", PROTECTION_RUNE.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.GRAY)
                .ability(CatalystAbilities.PROTECTION_RUNE)
                .build()));


        public static void register(IEventBus eventBus) {
            if (ironsSpellbooks)
                CORE.register(eventBus);
        }
    }
    public static class WitherstormModCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, MODID);

        public static final RegistryObject<Item> WITHERED_NETHER_STAR = RegistryObject.create(modLoc(witherStormModID, "withered_nether_star"), ForgeRegistries.ITEMS);

        public static final RegistryObject<CatalystCore> WITHERED_BEACON_POWER = CORE.register("withered_beacon_power", () -> new CatalystCore("withered_beacon_power", WITHERED_NETHER_STAR.get(), new CoreAttributes.Builder()
                .tooltipColor(ChatFormatting.DARK_PURPLE)
                .ability(CatalystAbilities.WITHERED_NETHER_STAR)
                .build()));

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
