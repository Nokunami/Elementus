package net.nokunami.elementus.common.entity.projectile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.entity.*;
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
import net.nokunami.elementus.common.item.AnthektiteChargeBlade;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Predicate;

public class AnthektiteSlash extends Projectile {
    protected static final EntityDataAccessor<Optional<UUID>> OWNER_UNIQUE_ID = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.OPTIONAL_UUID);
    protected static final EntityDataAccessor<Boolean> CHARGEABLE = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<BlockPos> BLOCK_POS = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.BLOCK_POS);
    protected static final EntityDataAccessor<Float> DISCARD_DISTANCE = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.FLOAT);
    protected static final EntityDataAccessor<Integer> DISCARD_TIME = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> FRIENDLY_FIRE = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.BOOLEAN);
    protected static final EntityDataAccessor<ItemStack> WEAPON = SynchedEntityData.defineId(AnthektiteSlash.class, EntityDataSerializers.ITEM_STACK);
    private ItemStack weapon = new ItemStack(ModItems.ElementusItems.ANTHEKTITE_CHARGE_BLADE.get());
    private float damage;
    private int delay;
    private final int totalDelay;
    public int pTimer;
    public Predicate<LivingEntity> REMOVE_PREDICATE = (e) ->
        !(e instanceof OwnableEntity) && (e.isAlliedTo(this.getTrueOwner()) && getFriendlyFire() || !e.isAlliedTo(this.getTrueOwner())) ||
        (e instanceof OwnableEntity ownable && ((ownable.getOwner() != null && (ownable.getOwner().is(this.getTrueOwner()) ||
                ownable.getOwner().isAlliedTo(this.getTrueOwner())) && getFriendlyFire()) || ownable.getOwner() == null));

    public AnthektiteSlash(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
        this.damage = 7.5F;
        this.delay = 0;
        this.totalDelay = 1;
    }

    protected AnthektiteSlash(EntityType<? extends Projectile> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected AnthektiteSlash(EntityType<? extends Projectile> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
    }

    public AnthektiteSlash(Level pLevel, LivingEntity pShooter) {
        this(ModEntityType.ANTHEKTITE_SLASH.get(), pShooter, pLevel);
        this.weapon = pShooter.getMainHandItem();
    }

    public void launchSlash(Entity pShooter, float pX, float pY, float pZ, float pVelocity, float pInaccuracy) {
        float f = -Mth.sin(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        float f1 = -Mth.sin((pX + pZ) * ((float)Math.PI / 180F));
        float f2 = Mth.cos(pY * ((float)Math.PI / 180F)) * Mth.cos(pX * ((float)Math.PI / 180F));
        this.shoot(f, f1, f2, pVelocity, pInaccuracy);
        Vec3 vec3 = pShooter.getDeltaMovement();
        this.setDeltaMovement(this.getDeltaMovement().add(vec3.x, 0.0D, vec3.z));
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
        this.entityData.define(DISCARD_TIME, 0);
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
        this.setDiscardTime(compound.getInt("DiscardTime"));
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
        compound.putInt("DiscardTime", this.getDiscardTime());
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

    public int getDiscardTime() {
        return this.entityData.get(DISCARD_TIME);
    }

    public void setDiscardTime(int b) {
        this.entityData.set(DISCARD_TIME, b);
    }

    public void tick() {
        this.setFriendlyFire(AnthektiteChargeBlade.getFriendlyFire(this.weapon));

        double discardDistance = Mth.square(this.getDiscardDistance());
        Set<Entity> targets = new HashSet<>();

        if (this.getTrueOwner() != null && getBlockPos() != null) {
            double blockPos = this.distanceToSqr(this.getBlockPos().getCenter());
            if (blockPos >= discardDistance) {
                this.setDiscardTime(this.getDiscardTime() + 1);
                if (this.getDiscardTime() < 0) {
                    this.getTrueOwner().sendSystemMessage(Component.literal("out of range"));
                }
            }

            if (this.getDiscardTime() == 1) {
                this.level().addParticle(ModParticleTypes.SONIC_BOOM_START.get(), this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            } else if (this.getDiscardTime() > 1) {
                this.discard();
            }


            for (Entity entity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.5F), REMOVE_PREDICATE)) {
                if (this.getTrueOwner() != null) {
                    targets.add(entity);
//                this.getTrueOwner().sendSystemMessage(Component.literal(String.valueOf(entity.getType())));
                }
            }

            if (this.distanceToSqr(this.getBlockPos().getCenter()) < Mth.square(2)) {
                if (pTimer < 0) ++pTimer;
            }
        }

        Vec3 vec3 = this.getDeltaMovement();
        double d0 = this.getX() + vec3.x;
        double d1 = this.getY() + vec3.y;
        double d2 = this.getZ() + vec3.z;
//        if (this.level().isClientSide || (owner == null || !owner.isRemoved()) && this.level().hasChunkAt(this.blockPosition())) {
//            if (this.delay < totalDelay){
//                delay += 1;
//            } else {
//                this.level().addParticle(ParticleTypes.SWEEP_ATTACK, d0, d1, d2, 0.0D, 0.0D, 0.0D);
//                delay = 0;
//            }
//        }
        if (pTimer > 2) {
            if (this.delay < totalDelay){
                delay += 1;
            } else {
                if (this.level().isClientSide())
                    this.level().addParticle(ParticleTypes.SWEEP_ATTACK, d0, d1, d2, 0.0D, 0.0D, 0.0D);
                delay = 0;
            }
        }


        targets.remove(this.getTrueOwner());
        float damage1 = this.getDamage();
        if (!targets.isEmpty()){
            for (Entity entity: targets){
                if (this.getTrueOwner() != null) {
                    if (entity.isAlliedTo(this.getTrueOwner())) {
                        targets.remove(entity);
                        this.getTrueOwner().sendSystemMessage(Component.literal("Removed: " + entity.getType()));
                    }
                    if (entity instanceof LivingEntity living && entity != this.getTrueOwner() /*&& !areAllies(entity, this.getTrueOwner())*/) {
                        damage1 += EnchantmentHelper.getDamageBonus(this.weapon, (living).getMobType());
                        hurtMob(entity, entity.damageSources().playerAttack((Player) getTrueOwner()), damage1);
                    }
                }
            }
        }

        boolean breakBlock = true;
        if (breakBlock) {
            AABB aabb = this.getBoundingBox().inflate(0.2D);

            for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
                BlockState blockstate = this.level().getBlockState(blockpos);
                if (blockstate.is(BlockTags.MINEABLE_WITH_HOE)) {
                    ItemStack itemStack = this.weapon;
                    if (this.weapon == null || this.weapon.isEmpty()){
                        itemStack = new ItemStack(ModItems.ElementusItems.ANTHEKTITE_CHARGE_BLADE.get());
                    }
                    breakBlock(this.level(), blockpos, itemStack, this);
                }
            }
        }

        travel();

        super.tick();
    }

    // Code form Iron's Spellsbooks https://github.com/iron431/irons-spells-n-spellbooks/blob/1.21/src/main/java/io/redspace/ironsspellbooks/entity/spells/AbstractMagicProjectile.java#L29
    public void travel() {
        setPos(position().add(getDeltaMovement()));
        Vec3 motion = this.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        this.setXRot(Mth.wrapDegrees(xRot));
        this.setYRot(Mth.wrapDegrees(yRot));
        Vec3 vec34 = this.getDeltaMovement();
//        this.setDeltaMovement(vec34.x + 0.05D, vec34.y + 0.05D, vec34.z + 0.05D);
        this.setDeltaMovement(vec34.x + (vec34.x * 0.125), vec34.y + (vec34.y * 0.125), vec34.z + (vec34.z * 0.125));
    }

    private void hurtMob(Entity entity, DamageSource source, float damage) {
        if (this.getChargeable()/* && entity.isAlliedTo(this.getTrueOwner())*/) {
            AnthektiteChargeBlade.setCharge(this.weapon, 1);
            this.setChargeable(false);
        }
        this.getTrueOwner().sendSystemMessage(Component.literal( "chargeable: " + this.getChargeable()));
        entity.hurt(source, damage);
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
