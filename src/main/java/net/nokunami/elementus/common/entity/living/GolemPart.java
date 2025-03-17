package net.nokunami.elementus.common.entity.living;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.entity.PartEntity;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class GolemPart extends PartEntity<SteelGolem> {
    public final SteelGolem parentMob;
    public final String name;
    private final EntityDimensions size;

    public GolemPart(SteelGolem parent, String pName, float pWidth, float pHeight) {
        super(parent);
        this.size = EntityDimensions.scalable(pWidth, pHeight);
        this.refreshDimensions();
        this.parentMob = parent;
        this.name = pName;
    }

    @Override
    protected void defineSynchedData() {
    }

    @Override
    protected void readAdditionalSaveData(@NotNull CompoundTag pCompound) {
    }

    @Override
    protected void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
    }

    /**
     * Returns {@code true} if other Entities should be prevented from moving through this Entity.
     */
    public boolean isPickable() {
        if (parentMob.isVehicle() || parentMob.getChassisState()) {
            return false;
        } else return parentMob.isSaddled();
    }

    @Nullable
    public ItemStack getPickResult() {
        return this.parentMob.getPickResult();
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean hurt(@NotNull DamageSource source, float pAmount) {
        return !this.isInvulnerableTo(source) && this.parentMob.hurt(source, pAmount);
    }

    /**
     * Returns {@code true} if Entity argument is equal to this Entity
     */
    public boolean is(@NotNull Entity pEntity) {
        return this == pEntity || this.parentMob == pEntity;
    }

    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        throw new UnsupportedOperationException();
    }

    public @NotNull EntityDimensions getDimensions(@NotNull Pose pPose) {
        return this.size;
    }

    public boolean shouldBeSaved() {
        return false;
    }

    @Override
    public @NotNull InteractionResult interact(@NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        boolean shearsItem = itemStack.is(Items.SHEARS);
        if (!parentMob.getChassisState()) {
            if (parentMob.isSaddled() && shearsItem) {
                parentMob.dropSaddle(parentMob.getSaddle(), player);
                parentMob.setSaddle(ItemStack.EMPTY);
                parentMob.getNavigation().stop();
                return InteractionResult.SUCCESS;
            }
            if (parentMob.isSaddled() && !player.isSecondaryUseActive()) {
                parentMob.doPlayerRide(player);
            } else if (parentMob.isSaddled() && player.isSecondaryUseActive()) {
                parentMob.sitOrder();
                return InteractionResult.SUCCESS;
            }
        }
        return super.interact(player, hand);
    }

    @Override
    public boolean canBeHitByProjectile() {
        return !parentMob.isVehicle();
    }
}
