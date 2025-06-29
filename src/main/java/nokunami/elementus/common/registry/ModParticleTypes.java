package nokunami.elementus.common.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.Elementus;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(Registries.PARTICLE_TYPE, Elementus.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SONIC_BURST = PARTICLE.register("sonic_burst", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SONIC_BURST_EMITTER = PARTICLE.register("sonic_burst_emitter", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SACRIFICE_SONIC_BURST_EMITTER = PARTICLE.register("sacrifice_sonic_burst_emitter", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SACRIFICE_SONIC_BURST = PARTICLE.register("sacrifice_sonic_burst", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SACRIFICE_SONIC_BOOM = PARTICLE.register("sacrifice_sonic_boom", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARRY = PARTICLE.register("parry", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PARRY_RESONANCE = PARTICLE.register("parry_resonance", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SONIC_BOOM_START = PARTICLE.register("sonic_boom_start", () -> new SimpleParticleType(true));
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> SACRIFICE_SONIC_BOOM_START = PARTICLE.register("sacrifice_sonic_boom_start", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE.register(eventBus);
    }
}
