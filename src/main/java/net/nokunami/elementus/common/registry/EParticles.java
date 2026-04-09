package net.nokunami.elementus.common.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class EParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Elementus.EID);

    public static final RegistryObject<SimpleParticleType> SONIC_BURST = PARTICLE.register("sonic_burst", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BURST_EMITTER = PARTICLE.register("sonic_burst_emitter", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BURST_SACRIFICE = PARTICLE.register("sonic_burst_sacrifice", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BURST_SACRIFICE_EMITTER = PARTICLE.register("sonic_burst_sacrifice_emitter", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BOOM_SACRIFICE = PARTICLE.register("sonic_boom_sacrifice", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PARRY = PARTICLE.register("parry", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PARRY_RESONANCE = PARTICLE.register("parry_resonance", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> INITIAL_BURST = PARTICLE.register("initial_burst", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> INITIAL_BURST_SACRIFICE = PARTICLE.register("initial_burst_sacrifice", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SLASH_IMPACT = PARTICLE.register("anthektite_slash_impact", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SLASH_HIT = PARTICLE.register("slash_hit", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SLASH_CLASH = PARTICLE.register("anthektite_slash_clash", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SLASH_TRAIL = PARTICLE.register("slash_trail", () -> new SimpleParticleType(true));
//    public static final RegistryObject<SimpleParticleType> SLASH_AFTER_EFFECT = PARTICLE.register("slash_after_effect", () -> new SimpleParticleType(true));
public static final RegistryObject<SimpleParticleType> RUSH_TRAIL = PARTICLE.register("rush_trail", () -> new SimpleParticleType(true));
public static final RegistryObject<SimpleParticleType> SACRIFICE_SCULK_SOUL = PARTICLE.register("sacrifice_sculk_soul", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE.register(eventBus);
    }
}
