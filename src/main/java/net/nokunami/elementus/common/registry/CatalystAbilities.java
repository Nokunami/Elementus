package net.nokunami.elementus.common.registry;

import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.passiveAbility.*;

import java.util.function.Supplier;

public class CatalystAbilities {

    public static final PassiveCatalystAbility NETHER_STAR = new NetherStarCatalystAbility();
    public static final PassiveCatalystAbility HEART_OF_THE_SEA = new HeartOfTheSeaCatalystAbility();
    public static final PassiveCatalystAbility TOTEM_OF_UNDYING = new TotemOfUndyingCatalystAbility();

    public static final Supplier<PassiveCatalystAbility> IGNITIUM = IgnitiumCatalystAbility::new;
    public static final Supplier<PassiveCatalystAbility> CURSIUM = CursiumCatalystAbility::new;
    public static final Supplier<PassiveCatalystAbility> ESSENCE_OF_STORM = EssenceOfStormCatalystAbility::new;

    public static final Supplier<PassiveCatalystAbility> WITHERED_BEACON_POWER = WitheredNetherStarCatalystAbility::new;
}
