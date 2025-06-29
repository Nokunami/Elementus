package nokunami.elementus.common.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.effect.*;

import static nokunami.elementus.ModChecker.ironsSpellbooks;

public class ModMobEffects {

    public static class ElementusEffects {
        public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, Elementus.MODID);

        public static final Holder<MobEffect> TOTEM_COOLDOWN = register("totem_cooldown", new CatalystTotemExhuastEffect());
        public static final Holder<MobEffect> BEACON_POWER = register("beacon_power", new NetherStarEffect());
        public static final Holder<MobEffect> WITHERED_BEACON_POWER = register("withered_beacon_power", new WitheredBeaconPower());
        public static final Holder<MobEffect> ANTHEKTITE_SWORD_DANCE = register("sword_dance", new SwordDanceEffect());

        public static void register(IEventBus eventBus) {
            EFFECTS.register(eventBus);
        }

        private static Holder<MobEffect> register(String name, MobEffect effect) {
            return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, ResourceLocation.withDefaultNamespace(name), effect);
        }
    }

//    public static class ISSEffects {
//        public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Elementus.MODID);
//
//        public static final RegistryObject<MobEffect> ADD_ISS_MANA = EFFECTS.register("additional_iss_mana", AdditionalIssManaEffect::new);
//
//        public static void register(IEventBus eventBus) {
//            EFFECTS.register(eventBus);
//        }
//    }

    public static void register(IEventBus eventBus) {
        ElementusEffects.register(eventBus);
//        if (ironsSpellbooks) {
//            ISSEffects.register(eventBus);
//        }
    }
}
