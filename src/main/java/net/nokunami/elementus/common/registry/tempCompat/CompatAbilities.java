package net.nokunami.elementus.common.registry.tempCompat;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.essenceOfTheStorm.LightningSpearAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.essenceOfTheStorm.WaveAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.ignitium.IgnisFinalAttackAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.ignitium.IgnitiumFireBallAbility;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.voidGuardian.VoidRunesAbility;
import net.nokunami.elementus.common.catalystCore.ability.passiveAbility.EssenceOfStormCatalystAbility;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.registry.CustomRegistries.ABILITY_RL;

public class CompatAbilities {
    public static class CataclysmAbilities {
        public static final DeferredRegister<AbstractActiveAbility> ABILITY = DeferredRegister.create(ABILITY_RL, EID);

        public static final Supplier<PassiveCatalystAbility> ESSENCE_OF_STORM = EssenceOfStormCatalystAbility::new;

        public static final RegistryObject<AbstractActiveAbility> INFERNO_BALLS = registerAbility("ingis_balls_test", new IgnitiumFireBallAbility());
        public static final RegistryObject<AbstractActiveAbility> IGNIS_FLAME_STRIKE = registerAbility("ignis_flame_strike", new IgnisFinalAttackAbility());

        public static final RegistryObject<AbstractActiveAbility> VOID_RUNES = registerAbility("void_runes", new VoidRunesAbility());

        public static final RegistryObject<AbstractActiveAbility> WAVE = registerAbility("wave", new WaveAbility());
        public static final RegistryObject<AbstractActiveAbility> LIGHTNING_SPEAR = registerAbility("lightning_spear", new LightningSpearAbility());

        public static RegistryObject<AbstractActiveAbility> registerAbility(String id, AbstractActiveAbility ability) {
            return ABILITY.register(id, () -> ability);
        }

        public static void register(IEventBus eventBus) {
            if (cataclysm)
                ABILITY.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        CataclysmAbilities.register(eventBus);
    }
}
