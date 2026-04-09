package net.nokunami.elementus.common.registry.tempCompat;

import com.github.L_Ender.cataclysm.entity.effect.Flame_Strike_Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.effect.CatalystFlameStrike;
import net.nokunami.elementus.common.entity.projectile.cataclysm.CatalystIgnisFireBall;

public class CompatEntityTypes {
    public static class CataclysmEntities {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Elementus.EID);

        public static final RegistryObject<EntityType<CatalystIgnisFireBall>> CATALYST_IGNIS_FIREBALL = ENTITY_TYPES.register("catalyst_ignis_fireball",
                () -> EntityType.Builder.<CatalystIgnisFireBall>of(CatalystIgnisFireBall::new, MobCategory.MISC)
                        .fireImmune()
                        .sized(0.5F, 0.5F)
                        .setUpdateInterval(1)
                        .setTrackingRange(20)
                        .setShouldReceiveVelocityUpdates(true).build("catalyst_ignis_fireball"));

        public static final RegistryObject<EntityType<CatalystFlameStrike>> CATALYST_FLAME_STRIKE = ENTITY_TYPES.register("catalyst_flame_strike",
                () -> EntityType.Builder.<CatalystFlameStrike>of(CatalystFlameStrike::new, MobCategory.MISC)
//                        .fireImmune()
//                        .sized(0.5F, 0.5F)
//                        .setUpdateInterval(1)
//                        .setTrackingRange(20)
//                        .setShouldReceiveVelocityUpdates(true).build("catalyst_ignis_fireball"));
        .sized(6.0F, 0.5F).fireImmune().clientTrackingRange(10).updateInterval(2).build("catalyst_flame_strike"));

        public static void register(IEventBus eventBus) {
            ENTITY_TYPES.register(eventBus);
        }
    }

    public static void register(IEventBus eventBus) {
        CataclysmEntities.register(eventBus);
    }
}
