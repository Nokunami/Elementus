package net.nokunami.elementus.common.registry;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.catalystCore.CoreArmorAttributes;
import net.nokunami.elementus.common.catalystCore.ability.*;

import java.util.function.Supplier;

public class CatalystAbilities {

    public static final CatalystAbility NETHER_STAR = new NetherStarCatalystAbility();
    public static final CatalystAbility HEART_OF_THE_SEA = new HeartOfTheSeaCatalystAbility();

    public static final CatalystAbility TOTEM_OF_UNDYING = new TotemOfUndyingCatalystAbility();

    public static final Supplier<CatalystAbility> IGNITIUM = IgnitiumCatalystAbility::new;
    public static final Supplier<CatalystAbility> CURSIUM = CursiumCatalystAbility::new;
    public static final Supplier<CatalystAbility> ESSENCE_OF_STORM = EssenceOfStormCatalystAbility::new;

    public static final Supplier<CatalystAbility> ARCANE_INGOT = issCatalystAbility(CoreArmorAttributes::arcaneIngot);
    public static final Supplier<CatalystAbility> FIRE_RUNE = issCatalystAbility(CoreArmorAttributes::fireRune);
    public static final Supplier<CatalystAbility> ICE_RUNE = issCatalystAbility(CoreArmorAttributes::iceRune);
    public static final Supplier<CatalystAbility> LIGHTNING_RUNE = issCatalystAbility(CoreArmorAttributes::lightningRune);
    public static final Supplier<CatalystAbility> HOLY_RUNE = issCatalystAbility(CoreArmorAttributes::holyRune);
    public static final Supplier<CatalystAbility> ENDER_RUNE = issCatalystAbility(CoreArmorAttributes::enderRune);
    public static final Supplier<CatalystAbility> BLOOD_RUNE = issCatalystAbility(CoreArmorAttributes::bloodRune);
    public static final Supplier<CatalystAbility> EVOCATION_RUNE = issCatalystAbility(CoreArmorAttributes::evocationRune);
    public static final Supplier<CatalystAbility> NATURE_RUNE = issCatalystAbility(CoreArmorAttributes::natureRune);
    public static final Supplier<CatalystAbility> ARCANE_RUNE = issCatalystAbility(CoreArmorAttributes::arcaneRune);
    public static final Supplier<CatalystAbility> COOLDOWN_RUNE = issCatalystAbility(CoreArmorAttributes::cooldownRune);
    public static final Supplier<CatalystAbility> PROTECTION_RUNE = issCatalystAbility(CoreArmorAttributes::protectionRune);


    public static final Supplier<CatalystAbility> WITHERED_NETHER_STAR = WitheredNetherStarCatalystAbility::new;

    public static Supplier<CatalystAbility> issCatalystAbility(Supplier<ImmutableMultimap.Builder<Attribute, AttributeModifier>> attributes) {
        return () -> new CatalystAbility(new Abilities.Builder()
                .attributes(attributes).build());
    }
}
