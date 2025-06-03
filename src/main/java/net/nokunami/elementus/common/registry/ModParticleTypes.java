package net.nokunami.elementus.common.registry;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class ModParticleTypes {
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Elementus.MODID);

    public static final RegistryObject<SimpleParticleType> SONIC_BURST = PARTICLE.register("sonic_burst", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BURST_EMITTER = PARTICLE.register("sonic_burst_emitter", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SACRIFICE_SONIC_BOOM = PARTICLE.register("sacrifice_sonic_boom", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PARRY = PARTICLE.register("parry", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> PARRY_RESONANCE = PARTICLE.register("parry_resonance", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> SONIC_BOOM_BURST_START = PARTICLE.register("sonic_boom_burst_start", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE.register(eventBus);
    }
}
