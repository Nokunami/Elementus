package net.nokunami.elementus.common.entity.boss;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.ai.goal.fragmentedWither.AlternateTargetingGoal;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class FragmentedWither extends Monster implements PowerableMob, RangedAttackMob {
    private static final EntityDataAccessor<Integer> DATA_TARGET_A = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_B = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DATA_TARGET_C = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final List<EntityDataAccessor<Integer>> DATA_TARGETS = ImmutableList.of(DATA_TARGET_A, DATA_TARGET_B, DATA_TARGET_C);
    private static final EntityDataAccessor<Integer> INVULNERABILITY = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BOSS_PHASE = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SOUL_CHECK = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Float> DEFAULT_MAX_HEALTH = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> ANGER = SynchedEntityData.defineId(FragmentedWither.class, EntityDataSerializers.INT);
    private static final int INVULNERABLE_TICKS = 220;
    private final float[] xRotHeads = new float[2];
    private final float[] yRotHeads = new float[2];
    private final float[] xRotOHeads = new float[2];
    private final float[] yRotOHeads = new float[2];
    private final int[] nextHeadUpdate = new int[2];
    private final int[] idleHeadUpdates = new int[2];
    private int destroyBlocksTick;
    private final ServerBossEvent bossEvent = (ServerBossEvent)(new ServerBossEvent(getDisplayName(), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS)).setDarkenScreen(true);
    private static final Predicate<LivingEntity> LIVING_ENTITY_SELECTOR = (entity) -> entity.getMobType() != MobType.UNDEAD && entity.attackable();
    public static final TargetingConditions TARGETING_CONDITIONS = TargetingConditions.forCombat().range(20.0D).selector(LIVING_ENTITY_SELECTOR);

    public FragmentedWither(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        moveControl = new FlyingMoveControl(this, 10, false);
        setHealth(getMaxHealth());
        xpReward = 50;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 400)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.FLYING_SPEED, 0.6)
                .add(Attributes.FOLLOW_RANGE, 40)
                .add(Attributes.ARMOR, 10)
                .add(Attributes.ARMOR_TOUGHNESS, 6)
                ;
    }

    @Override
    protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    protected void registerGoals() {
        goalSelector.addGoal(0, new DoNothingGoal());
        goalSelector.addGoal(5, new WaterAvoidingRandomFlyingGoal(this, 1));
        goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8));
        goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));

//        if (level().getDifficulty() != Difficulty.PEACEFUL) {
//            goalSelector.addGoal(2, new RangedAttackGoal(this, 1, 40, 20));
//            targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 0, false, false, LIVING_ENTITY_SELECTOR));
//        }
        targetSelector.addGoal(3, new AlternateTargetingGoal(this));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(DATA_TARGET_A, 0);
        entityData.define(DATA_TARGET_B, 0);
        entityData.define(DATA_TARGET_C, 0);
        entityData.define(INVULNERABILITY, 0);
        entityData.define(BOSS_PHASE, 0);
        entityData.define(SOUL_CHECK, false);
        entityData.define(DEFAULT_MAX_HEALTH, 400F);
        entityData.define(ANGER, 0);
    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Invul", getInvulnerableTicks());
        tag.putInt("BossPhase", getBossPhase());
        tag.putBoolean("SoulCheck", getSoulCheck());
        tag.putFloat("DefaultMaxHealth", getDefaultMaxHealth());
        tag.putInt("AngerLevel", getAngerLevel());
    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setInvulnerableTicks(tag.getInt("Invul"));
        setBossPhase(tag.getInt("BossPhase"));
        setSoulCheck(tag.getBoolean("SoulCheck"));
        setDefaultMaxHealth(tag.getFloat("DefaultMaxHealth"));
        setAngerLevel(tag.getInt("AngerLevel"));
        if (hasCustomName()) bossEvent.setName(getDisplayName());
    }

    public int getInvulnerableTicks() { return entityData.get(INVULNERABILITY); }
    public void setInvulnerableTicks(int invulnerableTicks) { entityData.set(INVULNERABILITY, invulnerableTicks); }

    public int getBossPhase() { return entityData.get(BOSS_PHASE); }
    public void setBossPhase(int phase) { entityData.set(BOSS_PHASE, phase); }

    public boolean getSoulCheck() { return entityData.get(SOUL_CHECK); }
    public void setSoulCheck(boolean b) { entityData.set(SOUL_CHECK, b); }

    public float getDefaultMaxHealth() { return entityData.get(DEFAULT_MAX_HEALTH); }
    public void setDefaultMaxHealth(float f) { entityData.set(DEFAULT_MAX_HEALTH, f); }

    public int getAngerLevel() { return entityData.get(ANGER); }
    public void setAngerLevel(int i) { entityData.set(ANGER, i); }

    public void setCustomName(@Nullable Component name) {
        super.setCustomName(name);
        bossEvent.setName(getDisplayName());
    }

    protected SoundEvent getAmbientSound() { return SoundEvents.WITHER_AMBIENT; }
    protected @NotNull SoundEvent getHurtSound(@NotNull DamageSource source) { return SoundEvents.WITHER_HURT; }
    protected @NotNull SoundEvent getDeathSound() { return SoundEvents.WITHER_DEATH; }

    public void aiStep() {
        soulCheck();

        Vec3 vec3 = getDeltaMovement().multiply(1, 0.6, 1);
        if (!level().isClientSide && getAlternativeTarget(0) > 0) {
            Entity entity = level().getEntity(getAlternativeTarget(0));
            if (entity != null) {
                double d0 = vec3.y;
                if (getY() < entity.getY() || !isPowered() && getY() < entity.getY() + 5) {
                    d0 = Math.max(0, d0);
                    d0 += 0.3 - d0 * 0.6;
                }

                vec3 = new Vec3(vec3.x, d0, vec3.z);
                Vec3 vec31 = new Vec3(entity.getX() - getX(), 0, entity.getZ() - getZ());
                if (vec31.horizontalDistanceSqr() > 9) {
                    Vec3 vec32 = vec31.normalize();
                    vec3 = vec3.add(vec32.x * 0.3 - vec3.x * 0.6, 0, vec32.z * 0.3 - vec3.z * 0.6);
                }
            }
        }

        setDeltaMovement(vec3);
        if (vec3.horizontalDistanceSqr() > 0.05) setYRot((float) Mth.atan2(vec3.z, vec3.x) * (180 / (float) Math.PI) - 90);

        super.aiStep();

        for(int i = 0; i < 2; ++i) {
            yRotOHeads[i] = yRotHeads[i];
            xRotOHeads[i] = xRotHeads[i];
        }

        for(int j = 0; j < 2; ++j) {
            int k = getAlternativeTarget(j + 1);
            Entity entity1 = null;
            if (k > 0) {
                entity1 = level().getEntity(k);
            }

            if (entity1 != null) {
                double d9 = getHeadX(j + 1);
                double d1 = getHeadY(j + 1);
                double d3 = getHeadZ(j + 1);
                double d4 = entity1.getX() - d9;
                double d5 = entity1.getEyeY() - d1;
                double d6 = entity1.getZ() - d3;
                double d7 = Math.sqrt(d4 * d4 + d6 * d6);
//                float f = (float) (Mth.atan2(d6, d4) * (double) (180 / (float) Math.PI)) - 90;
//                float f1 = (float) (-(Mth.atan2(d5, d7) * (double) (180 / (float) Math.PI)));
                float f = (float) (Mth.atan2(d6, d4) * Mth.HALF_PI) - 90;
                float f1 = (float) (-(Mth.atan2(d5, d7) * Mth.HALF_PI));
                xRotHeads[j] = rotlerp(xRotHeads[j], f1, 40);
                yRotHeads[j] = rotlerp(yRotHeads[j], f, 10);
            } else yRotHeads[j] = rotlerp(yRotHeads[j], yBodyRot, 10);
        }

        boolean flag = isPowered();

        for(int l = 0; l < 3; ++l) {
            double d8 = getHeadX(l);
            double d10 = getHeadY(l);
            double d2 = getHeadZ(l);
            level().addParticle(ParticleTypes.SMOKE, d8 + random.nextGaussian() * 0.3, d10 + random.nextGaussian() * 0.3, d2 + random.nextGaussian() * 0.3, 0, 0, 0);
            if (flag && level().random.nextInt(4) == 0) {
                level().addParticle(ParticleTypes.ENTITY_EFFECT, d8 + random.nextGaussian() * 0.3, d10 + random.nextGaussian() * 0.3, d2 + random.nextGaussian() * 0.3, 0.7, 0.7, 0.5);
            }
        }

        if (getInvulnerableTicks() > 0) {
            for(int i1 = 0; i1 < 3; ++i1) {
                level().addParticle(ParticleTypes.ENTITY_EFFECT, getX() + random.nextGaussian(), getY() + (random.nextFloat() * 3.3), getZ() + random.nextGaussian(), 0.7, 0.7, 0.9);
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        if (getInvulnerableTicks() > 0) {
            int k1 = getInvulnerableTicks() - 1;
            bossEvent.setProgress(1 - (float) k1 / INVULNERABLE_TICKS);
            if (k1 <= 0) {
                level().explode(this, getX(), getEyeY(), getZ(), 7, false, Level.ExplosionInteraction.MOB);
                if (!isSilent()) {
                    level().globalLevelEvent(1023, blockPosition(), 0);
                }
            }

            setInvulnerableTicks(k1);
            if (tickCount % 10 == 0) heal(10);

        } else {
            super.customServerAiStep();

            if (destroyBlocksTick > 0) {
                --destroyBlocksTick;
                if (destroyBlocksTick == 0 && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(level(), this)) {
                    int j1 = Mth.floor(getY());
                    int i2 = Mth.floor(getX());
                    int j2 = Mth.floor(getZ());
                    boolean flag = false;

                    for(int j = -1; j <= 1; ++j) {
                        for(int k2 = -1; k2 <= 1; ++k2) {
                            for(int k = 0; k <= 3; ++k) {
                                int l2 = i2 + j;
                                int l = j1 + k;
                                int i1 = j2 + k2;
                                BlockPos blockpos = new BlockPos(l2, l, i1);
                                BlockState blockstate = level().getBlockState(blockpos);
                                if (blockstate.canEntityDestroy(level(), blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, blockstate)) {
                                    flag = level().destroyBlock(blockpos, true, this) || flag;
                                }
                            }
                        }
                    }

                    if (flag) level().levelEvent(null, 1022, blockPosition(), 0);
                }
            }

            if (tickCount % 20 == 0) heal(1);
            bossEvent.setProgress(getHealth() / getMaxHealth());
        }
    }

//    @Deprecated public static boolean canDestroy(BlockState state) { return !state.isAir() && !state.is(BlockTags.WITHER_IMMUNE); }

    public void makeInvulnerable() {
        setInvulnerableTicks(INVULNERABLE_TICKS);
        bossEvent.setProgress(0);
        setHealth(getMaxHealth() / 3);
    }

    public void soulCheck() {
        if (getMaxHealth() < getDefaultMaxHealth()) {
            setSoulCheck(true);
            AttributeInstance attribute = getAttribute(Attributes.MAX_HEALTH);
            if (attribute != null)  attribute.setBaseValue(getDefaultMaxHealth());
        }
    }

    @Override public void makeStuckInBlock(@NotNull BlockState state, @NotNull Vec3 motionMultiplier) { }

    @Override
    public void startSeenByPlayer(@NotNull ServerPlayer player) {
        super.startSeenByPlayer(player);
        bossEvent.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(@NotNull ServerPlayer player) {
        super.stopSeenByPlayer(player);
        bossEvent.removePlayer(player);
    }

    private double getHeadX(int pHead) {
        if (pHead <= 0) {
            return getX();
        } else {
            float f = (yBodyRot + (float)(180 * (pHead - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.cos(f);
            return getX() + (double)f1 * 1.3D;
        }
    }

    private double getHeadY(int pHead) {
        return pHead <= 0 ? getY() + 3.0D : getY() + 2.2D;
    }

    private double getHeadZ(int pHead) {
        if (pHead <= 0) {
            return getZ();
        } else {
            float f = (yBodyRot + (float)(180 * (pHead - 1))) * ((float)Math.PI / 180F);
            float f1 = Mth.sin(f);
            return getZ() + (double)f1 * 1.3D;
        }
    }

    private float rotlerp(float pAngle, float p_31444_, float p_31445_) {
        float f = Mth.wrapDegrees(p_31444_ - pAngle);
        if (f > p_31445_) {
            f = p_31445_;
        }

        if (f < -p_31445_) {
            f = -p_31445_;
        }

        return pAngle + f;
    }

    public void performRangedAttack(int head, LivingEntity target) {
        performRangedAttack(head, target.getX(), target.getY() + (double) target.getEyeHeight() * 0.5D, target.getZ(), head == 0 && random.nextFloat() < 0.001F);
    }

    public void performRangedAttack(int head, double pX, double pY, double pZ, boolean isDangerous) {
        if (!isSilent()) level().levelEvent(null, 1024, blockPosition(), 0);

        double d0 = getHeadX(head);
        double d1 = getHeadY(head);
        double d2 = getHeadZ(head);
        double d3 = pX - d0;
        double d4 = pY - d1;
        double d5 = pZ - d2;
        WitherSkull witherskull = new WitherSkull(level(), this, d3, d4, d5);
        witherskull.setOwner(this);
        if (isDangerous) witherskull.setDangerous(true);

        witherskull.setPosRaw(d0, d1, d2);
        level().addFreshEntity(witherskull);
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {
        performRangedAttack(0, target);
    }

    public boolean hurt(@NotNull DamageSource source, float amount) {
        if (isInvulnerableTo(source)) {
            return false;
        } else if (!source.is(DamageTypeTags.WITHER_IMMUNE_TO) && !(source.getEntity() instanceof WitherBoss)) {
            if (getInvulnerableTicks() > 0 && !source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                return false;
            } else {
                if (isPowered()) {
                    Entity entity = source.getDirectEntity();
                    if (entity instanceof AbstractArrow) return false;
                }

                Entity entity1 = source.getEntity();
                if (!(entity1 instanceof Player) && entity1 instanceof LivingEntity living && living.getMobType() == getMobType()) {
                    return false;
                } else {
                    if (destroyBlocksTick <= 0) destroyBlocksTick = 20;
                    for(int i = 0; i < idleHeadUpdates.length; ++i) idleHeadUpdates[i] += 3;
                    setAngerLevel(getAngerLevel() + 1);
                    return super.hurt(source, amount);
                }
            }
        } else return false;
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull DamageSource source, int looting, boolean recentlyHit) {
        super.dropCustomDeathLoot(source, looting, recentlyHit);
        ItemEntity itementity = spawnAtLocation(Items.NETHER_STAR);
        if (itementity != null) itementity.setExtendedLifetime();
    }

    public void checkDespawn() {
        if (level().getDifficulty() == Difficulty.PEACEFUL) discard();
        else noActionTime = 0;
    }

    public boolean addEffect(@NotNull MobEffectInstance effectInstance, @Nullable Entity entity) {
        return false;
    }

    public float getHeadYRot(int pHead, float partialTick) {
        return Mth.lerp(partialTick, yRotOHeads[pHead], yRotHeads[pHead]);
    }

    public float getHeadXRot(int pHead, float partialTick) {
        return Mth.lerp(partialTick, xRotOHeads[pHead], xRotHeads[pHead]);
    }

    public void setNextHeadUpdate(int i, int i1) { nextHeadUpdate[i] = i1; }
    public int getNextHeadUpdate(int i) { return nextHeadUpdate[i]; }

    public void setIdleHeadUpdates(int i, int i1) { idleHeadUpdates[i] = i1; }
    public int getIdleHeadUpdates(int i) { return idleHeadUpdates[i]; }

    /**
     * Returns the target entity ID if present, or -1 if not
     * @param head The target offset, should be from 0-2
     */
    public int getAlternativeTarget(int head) {
        return entityData.get(DATA_TARGETS.get(head));
    }

    /**
     * Updates the target entity ID
     */
    public void setAlternativeTarget(int targetOffset, int pNewId) {
        entityData.set(DATA_TARGETS.get(targetOffset), pNewId);
    }

    @Override public boolean isPowered() { return getHealth() <= getMaxHealth() / 2; }
    @Override public @NotNull MobType getMobType() { return MobType.UNDEAD; }
    @Override protected boolean canRide(@NotNull Entity vehicle) { return false; }
    public boolean canChangeDimensions() { return false; }

    public boolean canBeAffected(MobEffectInstance effectInstance) {
        return effectInstance.getEffect() != MobEffects.WITHER && super.canBeAffected(effectInstance);
    }

    class DoNothingGoal extends Goal {
        public DoNothingGoal() { setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK)); }
        public boolean canUse() { return FragmentedWither.this.getInvulnerableTicks() > 0; }
    }
}