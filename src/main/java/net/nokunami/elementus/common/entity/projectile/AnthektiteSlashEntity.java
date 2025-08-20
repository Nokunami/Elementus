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
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.item.unique.AnthektiteChargeBlade;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModMobEffects;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public class AnthektiteSlashEntity extends Projectile {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<Boolean> CHARGEABLE = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<BlockPos> BLOCK_POS = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.BLOCK_POS);
    protected static final EntityDataAccessor<Float> DISCARD_DISTANCE = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Boolean> FRIENDLY_FIRE = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<ItemStack> WEAPON = SynchedEntityData.defineId(AnthektiteSlashEntity.class, EntityDataSerializers.ITEM_STACK);
    private float damage;
    private int delay;
    public int pTimer;
    public int tickD;
//    public Predicate<LivingEntity> REMOVE_PREDICATE = (e) ->
//        !(e instanceof OwnableEntity) && (e.isAlliedTo(this.getTrueOwner()) && getFriendlyFire() || !e.isAlliedTo(this.getTrueOwner())) ||
//        (e instanceof OwnableEntity ownable && ((ownable.getOwner() != null && (ownable.getOwner().is(this.getTrueOwner()) ||
//                ownable.getOwner().isAlliedTo(this.getTrueOwner())) && getFriendlyFire()) || ownable.getOwner() == null));

    public Predicate<LivingEntity> TEST = (e -> MobUtil.allied(this.getTrueOwner(), e, this.getFriendlyFire()));
    public Predicate<? super Entity> REMOVE_ENTITIES_PREDICATE = (e -> MobUtil.allied(this.getTrueOwner(), e, this.getFriendlyFire()) || e.equals(this.getTrueOwner()));
    private final Set<Entity> alreadyHitEntities = new HashSet<>();

    public AnthektiteSlashEntity(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 7.5F;
    }

    protected AnthektiteSlashEntity(EntityType<? extends Projectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected AnthektiteSlashEntity(EntityType<? extends Projectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public AnthektiteSlashEntity(Level pLevel, LivingEntity pShooter) {
        this(ModEntityType.ANTHEKTITE_SLASH.get(), pShooter, pLevel);
    }

    public void launchSlash(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, pVelocity, pInaccuracy);
        Vec3 vec3 = pShooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, 0.0D, vec3.z));
        this.setXRot(pX);
        this.setYRot(pY);
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    protected void defineSynchedData() {
        this.entityData.define(OWNER_UNIQUE_ID, Optional.empty());
        this.entityData.define(CHARGEABLE, false);
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
            uuid = OldUsersConverter.convertMobOwnerIfNecessary(this.getServer(), s);
        }
        if (uuid != null) {
            try {
                this.setOwnerId(uuid);
            } catch (Throwable ignored) {
            }
        }
        if (compound.contains("Damage")) {
            this.setDamage(compound.getFloat("Damage"));
        }
        this.setChargeable(compound.getBoolean("Chargeable"));
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
        compound.putBoolean("Chargeable", this.getChargeable());
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

    public void setOwnerId(@Nullable UUID p_184754_1_) {
        this.entityData.set(OWNER_UNIQUE_ID, Optional.ofNullable(p_184754_1_));
    }

    public boolean getChargeable() {
        return this.entityData.get(CHARGEABLE);
    }

    public void setChargeable(boolean blockPos) {
        this.entityData.set(CHARGEABLE, blockPos);
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
        if (!this.getItemStack().isEmpty())
            this.setFriendlyFire(AnthektiteChargeBlade.getFriendlyFire(this.getItemStack()));

        Vec3 vec3 = this.getDeltaMovement();


        if (this.getTrueOwner() != null && getBlockPos() != null) {
            double blockPos = this.distanceToSqr(this.getBlockPos().getCenter());
            double discardDistance = Mth.square(this.getDiscardDistance());
            if (blockPos >= discardDistance)
                ++tickD;

            if (tickD == 1)
                this.level().addParticle(ModParticleTypes.SLASH_IMPACT.get(), this.getX() + vec3.x, this.getY() + vec3.y, this.getZ() + vec3.z, -vec3.x, -vec3.y, -vec3.z);
            else if (tickD >= 1)
                this.discard();

            if (blockPos > Mth.square(1))
                ++pTimer;

            if (blockPos > Mth.square(2)) {
                if (this.delay < 0){
                    ++delay;
                } else {
                    double discardDistance1 = Mth.square(this.getDiscardDistance() - 2);
                    if (this.level().isClientSide() && blockPos <= discardDistance1) {
                        this.level().addParticle(ModParticleTypes.SLASH_TRAIL.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                    }
                    delay = 0;
                }
            }

            Set<Entity> targets = new HashSet<>(this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.5F), (e) -> MobUtil.allied(this.getTrueOwner(), e, getFriendlyFire())));

//            targets.removeIf(REMOVE_ENTITIES_PREDICATE);
            float damage1 = this.getDamage();
            if (!targets.isEmpty()){
                for (Entity entity: targets){
                    if (entity instanceof AnthektiteSlashEntity slash && (slash.getTrueOwner() == null || !slash.getTrueOwner().isAlliedTo(this.getTrueOwner()))) {
                        this.level().addParticle(ModParticleTypes.SLASH_CLASH.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                        this.discard();
                    } else if (entity instanceof LivingEntity living && entity != this.getTrueOwner()) {
                        damage1 += EnchantmentHelper.getDamageBonus(this.getItemStack(), (living).getMobType());
                        hurtMob(living, entity.damageSources().playerAttack((Player) getTrueOwner()), damage1);
                    }
                }
            }
        }

        AABB aabb = this.getBoundingBox().inflate(0.2D);
        for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.is(BlockTags.MINEABLE_WITH_HOE) && !blockstate.is(Etags.Blocks.ANTHEKTITE_SLASH_BLACKLIST)) {
                ItemStack itemStack = this.getItemStack();
                if (this.getItemStack() == null || this.getItemStack().isEmpty()){
                    itemStack = new ItemStack(ModItems.ANTHEKTITE_CHARGE_BLADE.get());
                }
                breakBlock(this.level(), blockpos, itemStack, this);
            }
        }

        setPos(position().add(getDeltaMovement()));
        Vec3 vec34 = this.getDeltaMovement();
//        this.setDeltaMovement(vec34.x + (vec34.x * 0.125), vec34.y + (vec34.y * 0.125), vec34.z + (vec34.z * 0.125));
        this.setDeltaMovement(vec34.x * 1.125, vec34.y * 1.125, vec34.z * 1.125);

        super.tick();
    }

    private void hurtMob(LivingEntity entity, DamageSource source, float damage) {
        if (!alreadyHitEntities.contains(entity)) {
            this.alreadyHitEntities.add(entity);
            this.level().addParticle(ModParticleTypes.SLASH_IMPACT.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            entity.hurt(source, damage);
        }
        if (this.getChargeable()) {
            if (!entity.hasEffect(ModMobEffects.ElementusEffects.ANTHEKTITE_SWORD_DANCE.get()))
                AnthektiteChargeBlade.setCharge(this.getItemStack(), 1);
            this.setChargeable(false);
        }
    }

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public boolean isNoGravity() {
        return true;
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

    public static LivingEntity getLivingEntityByUuiD(Level level, UUID uuid) {
        return getLivingEntityByUuiD(level.getServer(), uuid);
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

    public static boolean breakBlock(Level level, BlockPos blockPos, ItemStack itemStack, @Nullable Entity entity) {
        BlockState blockstate = level.getBlockState(blockPos);
        if (blockstate.isAir()) {
            return false;
        } else {
            FluidState fluidstate = level.getFluidState(blockPos);
            if (!(blockstate.getBlock() instanceof BaseFireBlock)) {
                level.levelEvent(2001, blockPos, Block.getId(blockstate));
            }

            BlockEntity blockentity = blockstate.hasBlockEntity() ? level.getBlockEntity(blockPos) : null;
            Block.dropResources(blockstate, level, blockPos, blockentity, entity, itemStack);

            boolean flag = level.setBlock(blockPos, fluidstate.createLegacyBlock(), 3, 512);
            if (flag) {
                level.gameEvent(GameEvent.BLOCK_DESTROY, blockPos, GameEvent.Context.of(entity, blockstate));
            }

            return flag;
        }
    }

    public static boolean areAllies(@Nullable Entity entity, @Nullable Entity entity1){
        if (entity != null && entity1 != null) {
            return entity.isAlliedTo(entity1) || entity1.isAlliedTo(entity) || entity == entity1;
        } else {
            return false;
        }
    }
}
