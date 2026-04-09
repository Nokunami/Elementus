package net.nokunami.elementus.common.catalystCore.ability.activeAbility;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CompassItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.entity.MobUtil;

import java.util.List;

import static net.nokunami.elementus.common.entity.MobUtil.teleportEntity;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class LodehomeAbility extends AbstractActiveAbility{

    public LodehomeAbility() {
        super(new AbilityProperties(AbilityType.TRIGGERED, 6000)
                .setCastDuration(60));
    }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        var core = CatalystCoreUtil(entity);
        if (!level.isClientSide) {
            if (core.hasCore() && core.getCoreStack().getItem() instanceof CompassItem) {
                CompoundTag tag = core.getCoreStack().getTag();
                if (tag != null && tag.contains("LodestonePos") && tag.contains("LodestoneDimension")) {
                    var le = level.dimension().location().toString();
                    var t = tag.getString("LodestoneDimension");

                    ResourceKey<Level> resourcekey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(t));
                    ServerLevel serverlevel = ((ServerLevel) level).getServer().getLevel(resourcekey);

                    if (entity.canChangeDimensions()) {
                        var bp = NbtUtils.readBlockPos(tag.getCompound("LodestonePos"));
                        var tp = bp.above().getCenter();
                        if (serverlevel != null) {
                            if (!le.equals(t)) entity.changeDimension(serverlevel);
                            List<LivingEntity> livingEntities = level.getEntitiesOfClass(LivingEntity.class, new AABB(entity.blockPosition()).inflate(1));
                            for (LivingEntity living : livingEntities) {
                                if (living.isAlliedTo(entity) && !MobUtil.isTargetingFriendly(living)) {
                                    living.resetFallDistance();
                                    teleportEntity(living, tp);
                                }
                            }
                            teleportEntity(entity, tp);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

//    @Override
//    public void onCastStop(LivingEntity entity, Level level) {
////        var core = CatalystItemUtil.getEquippedCore(entity.getItemBySlot(EquipmentSlot.CHEST));
//        var core = CatalystCoreUtil(entity);
//        if (!level.isClientSide) {
//            CAbility ca = CAbility.instance(entity);
//            if (ca.getCastTick() > 60) {
//                if (core.hasCore() && core.getCoreStack().getItem() instanceof CompassItem) {
//                    CompoundTag tag = core.getCoreStack().getTag();
//                    if (tag != null && tag.contains("LodestonePos") && tag.contains("LodestoneDimension")) {
//                        var le = level.dimension().location().toString();
//                        var t = tag.getString("LodestoneDimension");
//
//                        ResourceKey<Level> resourcekey = ResourceKey.create(Registries.DIMENSION, new ResourceLocation(t));
//                        ServerLevel serverlevel = ((ServerLevel) level).getServer().getLevel(resourcekey);
//
//                        if (entity.canChangeDimensions()) {
//                            var bp = NbtUtils.readBlockPos(tag.getCompound("LodestonePos"));
//                            var tp = bp.above().getCenter();
//                            if (serverlevel != null) {
//                                if (!le.equals(t)) entity.changeDimension(serverlevel);
//                                entity.resetFallDistance();
//                                entity.teleportTo(tp.x, tp.y, tp.z);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }

//    @Override public ResourceLocation abilityIcon() { return icon("lodehome_icon"); }
}
