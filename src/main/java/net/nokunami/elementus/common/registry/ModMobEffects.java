package net.nokunami.elementus.common.registry;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.effect.*;

import static net.nokunami.elementus.ModChecker.ironsSpellbooks;

public class ModMobEffects {

    public static class ElementusEffects {
        public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Elementus.MODID);

        public static final RegistryObject<MobEffect> TOTEM_COOLDOWN = EFFECTS.register("totem_cooldown", CatalystTotemExhuastEffect::new);
        public static final RegistryObject<MobEffect> BEACON_POWER = EFFECTS.register("beacon_power", NetherStarEffect::new);
        public static final RegistryObject<MobEffect> WITHERED_BEACON_POWER = EFFECTS.register("withered_beacon_power", WitheredBeaconPower::new);
        public static final RegistryObject<MobEffect> ANTHEKTITE_SWORD_DANCE = EFFECTS.register("sword_dance", SwordDanceEffect::new);

        public static void register(IEventBus eventBus) {
            EFFECTS.register(eventBus);
        }
    }

    public static class ISSEffects {
        public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Elementus.MODID);

        public static final RegistryObject<MobEffect> ADD_ISS_MANA = EFFECTS.register("additional_iss_mana", AdditionalIssManaEffect::new);

        public static void register(IEventBus eventBus) {
            EFFECTS.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        ElementusEffects.register(eventBus);
        if (ironsSpellbooks) {
            ISSEffects.register(eventBus);
        }
    }
}
