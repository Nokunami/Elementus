package nokunami.elementus.common.entity.vehicle;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
//import net.minecraftforge.fluids.FluidType;
import net.neoforged.neoforge.fluids.FluidType;
import nokunami.elementus.common.registry.ModBlocks.*;
import nokunami.elementus.common.registry.ModEntityType;
import nokunami.elementus.common.registry.ModItems.*;
import org.jetbrains.annotations.NotNull;

import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity.class, EntityDataSerializers.INT);

    public ModBoatEntity(EntityType<? extends ModBoatEntity> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModBoatEntity(Level level, double pX, double pY, double pZ) {
        this(ModEntityType.MOD_BOAT.get(), level);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    @Override
    public @NotNull Item getDropItem() {
        return switch (getModVariant()) {
            case MOVCADIA -> ElementusItems.MOVCADIA_BOAT.get();
        };
    }

    public void setVariant(Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public Type getModVariant() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ID_TYPE, Type.MOVCADIA.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(Type.byName(pCompound.getString("Type")));
        }
    }

    public enum Type implements StringRepresentable {
        MOVCADIA(ElementusBlocks.MOVCADIA_PLANKS, "movcadia");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<ModBoatEntity.Type> CODEC = StringRepresentable.fromEnum(ModBoatEntity.Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public @NotNull String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a boat type by its enum ordinal
         */
        public static ModBoatEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static ModBoatEntity.Type byName(String pName) {
            return CODEC.byName(pName, MOVCADIA);
        }
    }

    @Override
    public boolean canBoatInFluid(FluidState state) {
        Boat boat = this;
        return state.supportsBoating(boat) || (state.getFluidType().equals(Fluids.LAVA.getFluidType()) && isFireproofBoat(getModVariant()));
    }

    @Override
    public boolean canBoatInFluid(FluidType type) {
        Boat boat = this;
        return type.supportsBoating(boat) || (type.equals(Fluids.LAVA.getFluidType()) && isFireproofBoat(getModVariant()));
    }

    public static boolean isFireproofBoat(Type boatType) {
        return ((FireBlock) Blocks.FIRE).getBurnOdds(boatType.getPlanks().defaultBlockState()) == 0;
    }

    public boolean fireImmune() {
        return isFireproofBoat(getModVariant()) || super.fireImmune();
    }
}