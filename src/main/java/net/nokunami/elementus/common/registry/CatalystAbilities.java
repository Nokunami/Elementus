package net.nokunami.elementus.common.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.LodehomeAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter.GroupTeleportAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter.TeleportAbility;
import net.nokunami.elementus.common.catalystCore.ability.passiveAbility.*;
import net.nokunami.elementus.common.registry.tempCompat.CompatAbilities;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.registry.CustomRegistries.ABILITY_RL;

public class CatalystAbilities {
    public static final DeferredRegister<AbstractActiveAbility> ABILITY = DeferredRegister.create(ABILITY_RL, EID);

    public static final PassiveCatalystAbility NETHER_STAR = new NetherStarCatalystAbility();

//    public static final AbstractActiveAbility BEACON_EFFECT = new BeaconPowerRangeEffectAbility();

    public static final PassiveCatalystAbility HEART_OF_THE_SEA = new HeartOfTheSeaCatalystAbility();
    public static final PassiveCatalystAbility TOTEM_OF_UNDYING = new TotemOfUndyingCatalystAbility();

    public static final Supplier<PassiveCatalystAbility> IGNITIUM = IgnitiumCatalystAbility::new;
    public static final Supplier<PassiveCatalystAbility> CURSIUM = CursiumCatalystAbility::new;

    public static final Supplier<PassiveCatalystAbility> WITHERED_BEACON_POWER = WitheredNetherStarCatalystAbility::new;

    public static final Supplier<PassiveCatalystAbility> TOTEM_OF_FREEZING = TotemOfFreezingCatalystAbility::new;
    public static final Supplier<PassiveCatalystAbility> TOTEM_OF_ILLUSION = TotemOfIllusionsCatalystAbility::new;

    public static final RegistryObject<AbstractActiveAbility> TELEPORT = registerAbility("teleport", new TeleportAbility());
    public static final RegistryObject<AbstractActiveAbility> GROUP_TELEPORT = registerAbility("group_teleport", new GroupTeleportAbility());

    public static final RegistryObject<AbstractActiveAbility> LODEHOME = registerAbility("lodehome", new LodehomeAbility());

    public static RegistryObject<AbstractActiveAbility> registerAbility(String id, AbstractActiveAbility ability) {
        return ABILITY.register(id, () -> ability);
    }

    public static void register(IEventBus eventBus) {
        ABILITY.register(eventBus);
        CompatAbilities.register(eventBus);
    }
}
