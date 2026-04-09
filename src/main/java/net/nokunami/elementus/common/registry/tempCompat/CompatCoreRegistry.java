package net.nokunami.elementus.common.registry.tempCompat;

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
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.catalystCore.core.ISSCatalystCore;
import net.nokunami.elementus.common.catalystCore.core.IgnitiumCatalystCore;
import net.nokunami.elementus.common.registry.CatalystAbilities;
import net.nokunami.elementus.common.registry.CompatRegistryObjectGetter;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.Catalysm.CURSIUM_INGOT;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.IronsItemRegistry.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CompatCoreRegistry {
    public static class CataclysmCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, EID);

//        public static final RegistryObject<CatalystCore> IGNITIUM = CORE.register("ignitium", IgnitiumCatalystCore::new);
        public static final RegistryObject<CatalystCore> IGNITIUM = CORE.register("ignitium", () -> new IgnitiumCatalystCore(new CatalystCoreAttributes.Builder()
        .passiveAbility(CatalystAbilities.IGNITIUM)
//        .activeAbility(new AbilityHolder(CompatAbilities.CataclysmAbilities.INFERNO_BALLS))
        .activeAbility(new AbilityHolder(CompatAbilities.CataclysmAbilities.IGNIS_FLAME_STRIKE))
        .build()));
        public static final RegistryObject<CatalystCore> CURSIUM_WARRIOR = registerCore("cursium_warrior", CURSIUM_INGOT, ChatFormatting.DARK_AQUA, new CatalystCoreAttributes.Builder()
                .passiveAbility(CatalystAbilities.CURSIUM));
        public static final RegistryObject<CatalystCore> ESSENCE_OF_THE_STORM = registerCore("essence_of_the_storm", CompatRegistryObjectGetter.Catalysm.ESSENCE_OF_THE_STORM, ChatFormatting.AQUA, new CatalystCoreAttributes.Builder()
                .passiveAbility(CompatAbilities.CataclysmAbilities.ESSENCE_OF_STORM)
                .activeAbility(new AbilityHolder(CompatAbilities.CataclysmAbilities.WAVE))
                .activeAbility(new AbilityHolder(CompatAbilities.CataclysmAbilities.LIGHTNING_SPEAR))
        );
        public static final RegistryObject<CatalystCore> VOID_GUARDIAN = registerCore("void_guardian", CompatRegistryObjectGetter.Catalysm.VOID_CORE, ChatFormatting.DARK_PURPLE, new CatalystCoreAttributes.Builder()
                .activeAbility(new AbilityHolder(CompatAbilities.CataclysmAbilities.VOID_RUNES))
        );

        public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
            return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting, builder.build()));
        }

        public static void register(IEventBus eventBus) {
            if (cataclysm)
                CORE.register(eventBus);
        }
    }
    public static class IronsSpellbooksCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, EID);

//        public static final RegistryObject<CatalystCore> ISS_FIRE = registerCore("fire_irons_spellbooks", FIRE_RUNE, ChatFormatting.GOLD, CatalystArmorAttributes::fireRune);
//        public static final RegistryObject<CatalystCore> ISS_ICE = registerCore("ice_irons_spellbooks", ICE_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::iceRune);
//        public static final RegistryObject<CatalystCore> ISS_ENDER = registerCore("ender_irons_spellbooks", ENDER_RUNE, ChatFormatting.LIGHT_PURPLE, CatalystArmorAttributes::enderRune);
//        public static final RegistryObject<CatalystCore> ISS_LIGHTNING = registerCore("lightning_irons_spellbooks", LIGHTNING_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::lightningRune);
//        public static final RegistryObject<CatalystCore> ISS_HOLY = registerCore("holy_irons_spellbooks", HOLY_RUNE, ChatFormatting.YELLOW, CatalystArmorAttributes::holyRune);
//        public static final RegistryObject<CatalystCore> ISS_BLOOD = registerCore("blood_irons_spellbooks", BLOOD_RUNE, ChatFormatting.DARK_RED, CatalystArmorAttributes::bloodRune);
//        public static final RegistryObject<CatalystCore> ISS_EVOCATION = registerCore("evocation_irons_spellbooks", EVOCATION_RUNE, ChatFormatting.DARK_GREEN, CatalystArmorAttributes::evocationRune);
//        public static final RegistryObject<CatalystCore> ISS_NATURE = registerCore("nature_irons_spellbooks", NATURE_RUNE, ChatFormatting.GREEN, CatalystArmorAttributes::natureRune);
//        public static final RegistryObject<CatalystCore> ISS_ARCANE = registerCore("arcane_irons_spellbooks", MANA_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::arcaneRune);
//        public static final RegistryObject<CatalystCore> ISS_COOLDOWN = registerCore("recovery_irons_spellbooks", RECOVERY_RUNE, ChatFormatting.GRAY, CatalystArmorAttributes::recoveryRune);
//        public static final RegistryObject<CatalystCore> ISS_PROTECTION = registerCore("protection_irons_spellbooks", PROTECTION_RUNE, ChatFormatting.AQUA, CatalystArmorAttributes::protectionRune);

        public static final RegistryObject<CatalystCore> ISS_FIRE = registerCore("fire_irons_spellbooks", FIRE_RUNE, ChatFormatting.GOLD);
        public static final RegistryObject<CatalystCore> ISS_ICE = registerCore("ice_irons_spellbooks", ICE_RUNE, ChatFormatting.AQUA);
        public static final RegistryObject<CatalystCore> ISS_ENDER = registerCore("ender_irons_spellbooks", ENDER_RUNE, ChatFormatting.LIGHT_PURPLE);
        public static final RegistryObject<CatalystCore> ISS_LIGHTNING = registerCore("lightning_irons_spellbooks", LIGHTNING_RUNE, ChatFormatting.AQUA);
        public static final RegistryObject<CatalystCore> ISS_HOLY = registerCore("holy_irons_spellbooks", HOLY_RUNE, ChatFormatting.YELLOW);
        public static final RegistryObject<CatalystCore> ISS_BLOOD = registerCore("blood_irons_spellbooks", BLOOD_RUNE, ChatFormatting.DARK_RED);
        public static final RegistryObject<CatalystCore> ISS_EVOCATION = registerCore("evocation_irons_spellbooks", EVOCATION_RUNE, ChatFormatting.DARK_GREEN);
        public static final RegistryObject<CatalystCore> ISS_NATURE = registerCore("nature_irons_spellbooks", NATURE_RUNE, ChatFormatting.GREEN);
        public static final RegistryObject<CatalystCore> ISS_ARCANE = registerCore("arcane_irons_spellbooks", MANA_RUNE, ChatFormatting.AQUA);
        public static final RegistryObject<CatalystCore> ISS_COOLDOWN = registerCore("recovery_irons_spellbooks", RECOVERY_RUNE, ChatFormatting.GRAY);
        public static final RegistryObject<CatalystCore> ISS_PROTECTION = registerCore("protection_irons_spellbooks", PROTECTION_RUNE, ChatFormatting.AQUA);

    public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, Supplier<ImmutableMultimap.Builder<Attribute, AttributeModifier>> armorAttributes) {
        return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting));
    }
    public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting) {
        return CORE.register(id, () -> new ISSCatalystCore(() -> new ItemStack(item.get()), chatFormatting));
    }

        public static void register(IEventBus eventBus) {
            if (ironsSpellbooks)
                CORE.register(eventBus);
        }
    }
    public static class WitherstormModCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, EID);

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
    public static class FriendsAndFoesCores {
        public static final DeferredRegister<CatalystCore> CORE = DeferredRegister.create(CATALYST_CORE_RL, EID);

        public static final RegistryObject<Item> TOTEM_OF_FREEZING = RegistryObject.create(modLoc(friendsandfoesID, "totem_of_freezing"), ForgeRegistries.ITEMS);
        public static final RegistryObject<Item> TOTEM_OF_ILLUSION = RegistryObject.create(modLoc(friendsandfoesID, "totem_of_illusion"), ForgeRegistries.ITEMS);

        public static final RegistryObject<CatalystCore> FREEZE = registerCore("totem_of_freezing", TOTEM_OF_FREEZING, ChatFormatting.AQUA,
                new CatalystCoreAttributes.Builder().passiveAbility(CatalystAbilities.TOTEM_OF_FREEZING));

        public static final RegistryObject<CatalystCore> ILLUSION = registerCore("totem_of_illusion", TOTEM_OF_ILLUSION, ChatFormatting.WHITE,
                new CatalystCoreAttributes.Builder().passiveAbility(CatalystAbilities.TOTEM_OF_ILLUSION));

        public static RegistryObject<CatalystCore> registerCore(String id, Supplier<Item> item, ChatFormatting chatFormatting, CatalystCoreAttributes.Builder builder) {
            return CORE.register(id, () -> new CatalystCore(() -> new ItemStack(item.get()), chatFormatting, builder.build()));
        }

        public static void register(IEventBus eventBus) {
            if (friendsandfoes)
                CORE.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        CataclysmCores.register(eventBus);
        IronsSpellbooksCores.register(eventBus);
        WitherstormModCores.register(eventBus);
        FriendsAndFoesCores.register(eventBus);
    }
}
