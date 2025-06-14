package net.nokunami.elementus.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.entity.projectile.*;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.entity.vehicle.ModChestBoatEntity;

public class ModEntityType {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Elementus.MODID);

    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT = ENTITY_TYPES.register("movcadia_boat",
            () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("movcadia_boat"));
    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT = ENTITY_TYPES.register("movcadia_chest_boat",
            () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("movcadia_chest_boat"));

    public static final RegistryObject<EntityType<SteelGolem>> STEEL_GOLEM = ENTITY_TYPES.register("steel_golem",
            () -> EntityType.Builder.of(SteelGolem::new, MobCategory.CREATURE).sized(SteelGolem.rawBbWidth, SteelGolem.rawBbHeight).build("steel_golem"));

    public static final RegistryObject<EntityType<AnthektiteSlash>> ANTHEKTITE_SLASH = ENTITY_TYPES.register("anthektite_slash",
            () -> EntityType.Builder.<AnthektiteSlash>of(AnthektiteSlash::new, MobCategory.MISC).sized(0.75F, 0.5F).build("anthektite_slash"));

    public static final RegistryObject<EntityType<SonicRustParticleEntity>> SONIC_RUSH = ENTITY_TYPES.register("sonic_rush",
            () -> EntityType.Builder.<SonicRustParticleEntity>of(SonicRustParticleEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build("sonic_rush"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
