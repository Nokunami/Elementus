package net.nokunami.elementus.common.registry;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.LodehomeAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.heartOfTheSea.ConduitCleanseAbility;
import net.nokunami.elementus.common.catalystCore.ability.passiveAbility.beaconPower.BeaconPowerPassiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter.GroupTeleportAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.endShifter.TeleportAbility;
import net.nokunami.elementus.common.catalystCore.ability.passiveAbility.heartOfTheSea.ConduitPassiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.passiveAbility.totemOfUndying.TotemOfUndyingPassiveAbility;

import static net.nokunami.elementus.Elementus.EID;

public class CatalystAbilities {
    public static final DeferredRegister<AbstractAbility> ABILITY = DeferredRegister.create(ERegistries.ABILITY, EID);

    public static final RegistryObject<AbstractAbility> BEACON_POWER = registerAbility("beacon_power", new BeaconPowerPassiveAbility());
    public static final RegistryObject<AbstractAbility> CONDUIT_POWER = registerAbility("conduit_power", new ConduitPassiveAbility());
    public static final RegistryObject<AbstractAbility> CONDUIT_CLEANSE = registerAbility("conduit_cleanse", new ConduitCleanseAbility());
    public static final RegistryObject<AbstractAbility> GILDED_REVIVAL = registerAbility("gilded_revival", new TotemOfUndyingPassiveAbility());

    public static final RegistryObject<AbstractAbility> TELEPORT = registerAbility("teleport", new TeleportAbility());
    public static final RegistryObject<AbstractAbility> GROUP_TELEPORT = registerAbility("group_teleport", new GroupTeleportAbility());

    public static final RegistryObject<AbstractAbility> LODEHOME = registerAbility("lodehome", new LodehomeAbility());

    public static RegistryObject<AbstractAbility> registerAbility(String id, AbstractAbility ability) {
        return ABILITY.register(id, () -> ability);
    }

    public static void register(IEventBus eventBus) { ABILITY.register(eventBus); }
}
