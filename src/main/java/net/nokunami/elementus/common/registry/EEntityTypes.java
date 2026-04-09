package net.nokunami.elementus.common.registry;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.CatalystTotemMarker;
import net.nokunami.elementus.common.entity.boss.FragmentedWither;
import net.nokunami.elementus.common.entity.living.AstaliteGolemCarrier;
import net.nokunami.elementus.common.entity.living.AstaliteGolemLongarm;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.entity.projectile.*;
import net.nokunami.elementus.common.entity.vehicle.ModBoatEntity;
import net.nokunami.elementus.common.entity.vehicle.ModChestBoatEntity;
import net.nokunami.elementus.common.registry.tempCompat.CompatEntityTypes;

public class EEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Elementus.EID);

    public static final RegistryObject<EntityType<ModBoatEntity>> MOVCADIA_BOAT = ENTITY_TYPES.register("movcadia_boat",
            () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("movcadia_boat"));
    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOVCADIA_CHEST_BOAT = ENTITY_TYPES.register("movcadia_chest_boat",
            () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).build("movcadia_chest_boat"));

    public static final RegistryObject<EntityType<SteelGolem>> OLD_STEEL_GOLEM = ENTITY_TYPES.register("steel_golem",
            () -> EntityType.Builder.of(SteelGolem::new, MobCategory.CREATURE).sized(SteelGolem.rawBbWidth, SteelGolem.rawBbHeight).build("steel_golem"));

    public static final RegistryObject<EntityType<AstaliteGolemCarrier>> ASTALITE_GOLEM_CARRIER = ENTITY_TYPES.register("astalite_golem_carrier",
            () -> EntityType.Builder.of(AstaliteGolemCarrier::new, MobCategory.CREATURE).sized(AstaliteGolemCarrier.rawBbWidth, AstaliteGolemCarrier.rawBbHeight).build("astalite_golem_carrier"));

    public static final RegistryObject<EntityType<AstaliteGolemLongarm>> ASTALITE_GOLEM_LONGARM = ENTITY_TYPES.register("astalite_golem_longarm",
            () -> EntityType.Builder.of(AstaliteGolemLongarm::new, MobCategory.CREATURE).sized(AstaliteGolemLongarm.rawBbWidth, AstaliteGolemLongarm.rawBbHeight).build("astalite_golem_longarm"));

    public static final RegistryObject<EntityType<AnthektiteSlashEntity>> ANTHEKTITE_SLASH = ENTITY_TYPES.register("anthektite_slash",
            () -> EntityType.Builder.<AnthektiteSlashEntity>of(AnthektiteSlashEntity::new, MobCategory.MISC).sized(0.75F, 0.5F).build("anthektite_slash"));

    public static final RegistryObject<EntityType<RushProjectileEntity>> RUSH_PROJECTILE = ENTITY_TYPES.register("rush_projectile",
            () -> EntityType.Builder.<RushProjectileEntity>of(RushProjectileEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build("rush_projectile"));

    public static final RegistryObject<EntityType<SwordDanceSlashEntity>> SWORD_DANCE_SLASH = ENTITY_TYPES.register("sword_dance_slash",
            () -> EntityType.Builder.<SwordDanceSlashEntity>of(SwordDanceSlashEntity::new, MobCategory.MISC).sized(SwordDanceSlashEntity.bbWidth, SwordDanceSlashEntity.bbHeight).build("sword_dance_slash"));

    public static final RegistryObject<EntityType<PulseBurstEntity>> PULSE_BURST = ENTITY_TYPES.register("pulse_burst",
            () -> EntityType.Builder.<PulseBurstEntity>of(PulseBurstEntity::new, MobCategory.MISC).sized(1.0F, 1.0F).build("pulse_burst"));

    public static final RegistryObject<EntityType<WrathTridentEntity>> WRATH_TRIDENT = ENTITY_TYPES.register("wrath_of_the_sea",
            () -> EntityType.Builder.<WrathTridentEntity>of(WrathTridentEntity::new, MobCategory.MISC).sized(WrathTridentEntity.bbWidth, WrathTridentEntity.bbHeight).build("wrath_of_the_sea"));

    public static final RegistryObject<EntityType<FragmentedWither>> FRAGMENTED_WITHER = ENTITY_TYPES.register("fragmented_wither",
            () -> EntityType.Builder.of(FragmentedWither::new, MobCategory.MONSTER).fireImmune().sized(0.9F, 3.5F).clientTrackingRange(12).build("fragmented_wither"));

    public static final RegistryObject<EntityType<CatalystTotemMarker>> CATALYST_TOTEM_MARKER = ENTITY_TYPES.register("catalyst_totem_marker",
            () -> EntityType.Builder.of(CatalystTotemMarker::new, MobCategory.MISC).fireImmune().sized(0.3F, 0.3F).build("catalyst_totem_marker"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
        CompatEntityTypes.register(eventBus);
    }
}
