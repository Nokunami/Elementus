package net.nokunami.elementus.common.entity.projectile;

import com.bobmowzie.mowziesmobs.client.particle.util.RibbonComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class TrailEntity extends Entity {

    public TrailEntity(EntityType<? extends TrailEntity> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag tag) {

    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag tag) {

    }
}
