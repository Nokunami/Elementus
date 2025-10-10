package net.nokunami.elementus.common.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.item.unique.AnthektiteChargeBlade;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class RushProjectileEntity extends Projectile {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(RushProjectileEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<BlockPos> BLOCK_POS = SynchedEntityData.defineId(RushProjectileEntity.class, EntityDataSerializers.BLOCK_POS);
    protected static final EntityDataAccessor<Float> DISCARD_DISTANCE = SynchedEntityData.defineId(RushProjectileEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Boolean> FRIENDLY_FIRE = SynchedEntityData.defineId(RushProjectileEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<ItemStack> WEAPON = SynchedEntityData.defineId(RushProjectileEntity.class, EntityDataSerializers.ITEM_STACK);
    private float damage;
    private int totalLifespan;
    private int delay;
    private final int totalDelay;
    public Predicate<? super Entity> REMOVE_ENTITIES_PREDICATE = (e -> MobUtil.allied(this.getTrueOwner(), e, this.getFriendlyFire()) || e.equals(this.getTrueOwner()));
    public Predicate<? super LivingEntity> REMOVE_ENTITIES_PREDICATE2 = (e) -> MobUtil.allied(this.getTrueOwner(), e, getFriendlyFire());
    private final Set<Entity> alreadyHitEntities;

    public RushProjectileEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 7.5F;
        this.totalLifespan = 120;
        this.delay = 0;
        this.totalDelay = 1;
        this.alreadyHitEntities = new HashSet<>();
    }

    protected RushProjectileEntity(EntityType<? extends Projectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected RushProjectileEntity(EntityType<? extends Projectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public RushProjectileEntity(Level pLevel, LivingEntity pShooter) {
        this(ModEntityType.RUSH_PROJECTILE.get(), pShooter, pLevel);
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getTotalLifespan() {
        return totalLifespan;
    }

    public void setTotalLifespan(int totalLifespan) {
        this.totalLifespan = totalLifespan;
    }

    protected void defineSynchedData() {
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
        this.entityData.define(BLOCK_POS, BlockPos.ZERO);
        this.entityData.define(DISCARD_DISTANCE, 10F);
        this.entityData.define(FRIENDLY_FIRE, false);
        this.entityData.define(WEAPON, ItemStack.EMPTY);
    }

    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        UUID uuid;
        if (compound.hasUUID("Owner")) {
            uuid = compound.getUUID("Owner");
        } else {
            String s = compound.getString("Owner");
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(Objects.requireNonNull(this.getServer()), s);
        }

        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
            } catch (Throwable ignored) {
            }
        }

        if (compound.contains("Damage")) {
            this.setDamage(compound.getInt("Damage"));
        }
        if (compound.contains("TotalLife")) {
            this.setTotalLifespan(compound.getInt("TotalLife"));
        }
        int x = compound.getInt("PosX");
        int y = compound.getInt("PosY");
        int z = compound.getInt("PosZ");
        this.setBlockPos(new BlockPos(x, y, z));
        this.setDiscardDistance(compound.getFloat("DiscardDistance"));
        this.setFriendlyFire(compound.getBoolean("FriendlyFire"));
        CompoundTag stack = compound.getCompound("ItemStack");
        if (!stack.isEmpty()) {
            ItemStack itemStack = ItemStack.of(stack);
            if (itemStack.isEmpty()) Elementus.LOGGER.warn("Unable to load ItemStack from: {}", stack);
            this.setItemStack(itemStack);
        }
    }

    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getOwnerId() != null) {
            compound.putUUID("Owner", this.getOwnerId());
        }
        compound.putFloat("Damage", this.getDamage());
        compound.putInt("TotalLife", this.getTotalLifespan());
        compound.putInt("PosX", getBlockPos().getX());
        compound.putInt("PosY", getBlockPos().getY());
        compound.putInt("PosZ", getBlockPos().getZ());
        compound.putFloat("DiscardDistance", this.getDiscardDistance());
        compound.putBoolean("FriendlyFire", this.getFriendlyFire());
        if (!this.getItemStack().isEmpty()) {
            compound.put("ItemStack", this.getItemStack().save(new CompoundTag()));
        }
    }

    public LivingEntity getTrueOwner() {
        try {
            UUID uuid = this.getOwnerId();
            return uuid == null ? null : getLivingEntityByUuiD(uuid);
        } catch (IllegalArgumentException illegalargumentexception) {
            return null;
        }
    }

    @Nullable
    public UUID getOwnerId() {
        return this.entityData.get(OWNER_UNIQUE_ID).orElse(null);
    }

    public void setOwnerId(@Nullable UUID uuid) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(uuid));
    }

    public BlockPos getBlockPos() {
        return this.entityData.get(BLOCK_POS);
    }

    public void setBlockPos(BlockPos blockPos) {
        this.entityData.set(BLOCK_POS, blockPos);
    }

    public float getDiscardDistance() {
        return this.entityData.get(DISCARD_DISTANCE);
    }

    public void setDiscardDistance(float v) {
        this.entityData.set(DISCARD_DISTANCE, v);
    }

    public boolean getFriendlyFire() {
        return this.entityData.get(FRIENDLY_FIRE);
    }

    public void setFriendlyFire(boolean b) {
        this.entityData.set(FRIENDLY_FIRE, b);
    }

    public ItemStack getItemStack() {
        return this.entityData.get(WEAPON);
    }

    public void setItemStack(ItemStack b) {
        this.entityData.set(WEAPON, b);
    }

    public void tick() {
        if (!this.getItemStack().isEmpty()) {
            this.setFriendlyFire(AnthektiteChargeBlade.getFriendlyFire(this.getItemStack()));
        }

        Entity owner = this.getOwner();

        if (this.tickCount > getTotalLifespan())
            this.discard();

//        if (this.level().isClientSide && owner != null) {
//        }
        if (this.delay < totalDelay){
            ++this.delay;
        } else {
            if (this.getTrueOwner() != null && !this.getTrueOwner().onGround())
                this.level().addParticle(ModParticleTypes.RUSH_TRAIL.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            this.delay = 0;
        }


        if (this.getTrueOwner() != null) {
            this.setPos(getTrueOwner().getPosition(tickCount).add(0, getTrueOwner().getBbHeight()*0.25, 0));
            Set<Entity> targets = new HashSet<>(this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(1.5F, 0.5F, 1.5F), (e) -> MobUtil.allied(this.getTrueOwner(), e, getFriendlyFire())));
            targets.remove(this.getTrueOwner());
            float damage1 = this.getDamage();
            if (!targets.isEmpty()){
                for (Entity entity: targets){
                    if (entity instanceof LivingEntity living && entity != this.getTrueOwner()) {
                        damage1 += EnchantmentHelper.getDamageBonus(this.getItemStack(), (living).getMobType());
                        //temp (might make a new slash entity just for this)
                        swordDanceSlash(this.getTrueOwner(), this.getTrueOwner().getUsedItemHand(), damage1, entity);
                        if (living.getLastAttacker() == this.getTrueOwner())
                            this.discard();
                    }
                }
            }
        }
    }

    public void swordDanceSlash(LivingEntity player, InteractionHand hand, float damage, Entity target) {
        Level level = player.level();
        if (/*!MobUtil.allied(player, target, this.getFriendlyFire()) && */!alreadyHitEntities.contains(target)) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_STRONG, SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            boolean mirrored = false;
            if (player.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                if (player.getMainArm() == HumanoidArm.RIGHT) mirrored = true;
            } else {
                if (player.getMainArm() == HumanoidArm.LEFT) mirrored = true;
            }
            SwordDanceSlashEntity slash = new SwordDanceSlashEntity(level, player);
            slash.setOwnerId(player.getUUID());
            slash.setDamage(damage);
            slash.setItemStack(player.getItemInHand(hand));
            slash.setMirrored(mirrored);
            Vec3 hitLocation = player.position().add(0.0F, player.getBbHeight() * 0.3F, 0.0F).add(player.getForward().multiply(1.65F, 0.35F, 1.65F));

            slash.moveTo(hitLocation);
            slash.setYRot(player.getYRot());
            level.addFreshEntity(slash);
            alreadyHitEntities.add(target);
        }
    }

    public boolean isOnFire() {
        return false;
    }

    public boolean hurt(@NotNull DamageSource pSource, float pAmount) {
        return false;
    }

    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public static LivingEntity getLivingEntityByUuiD(UUID uuid) {
        return getLivingEntityByUuiD(ServerLifecycleHooks.getCurrentServer(), uuid);
    }

    public static LivingEntity getLivingEntityByUuiD(MinecraftServer server, UUID uuid){
        if (uuid != null && server != null) {
            for (ServerLevel world : server.getAllLevels()) {
                Entity entity = world.getEntity(uuid);
                if (entity instanceof LivingEntity){
                    return (LivingEntity) entity;
                }
            }
        }
        return null;
    }
}