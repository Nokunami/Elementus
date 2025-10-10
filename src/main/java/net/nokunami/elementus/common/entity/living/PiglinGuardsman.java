package net.nokunami.elementus.common.entity.living;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.monster.piglin.PiglinArmPose;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class PiglinGuardsman extends AbstractPiglin {
    protected static final ImmutableList<SensorType<? extends Sensor<? super PiglinBrute>>> SENSOR_TYPES = ImmutableList.of(
            SensorType.NEAREST_LIVING_ENTITIES,
            SensorType.NEAREST_PLAYERS,
            SensorType.NEAREST_ITEMS,
            SensorType.HURT_BY,
            SensorType.PIGLIN_BRUTE_SPECIFIC_SENSOR);
    protected static final ImmutableList<MemoryModuleType<?>> MEMORY_TYPES = ImmutableList.of(
            MemoryModuleType.LOOK_TARGET,
            MemoryModuleType.DOORS_TO_CLOSE,
            MemoryModuleType.NEAREST_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
            MemoryModuleType.NEAREST_VISIBLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER,
            MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS,
            MemoryModuleType.NEARBY_ADULT_PIGLINS,
            MemoryModuleType.HURT_BY,
            MemoryModuleType.HURT_BY_ENTITY,
            MemoryModuleType.WALK_TARGET,
            MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
            MemoryModuleType.ATTACK_TARGET,
            MemoryModuleType.ATTACK_COOLING_DOWN,
            MemoryModuleType.INTERACTION_TARGET,
            MemoryModuleType.PATH,
            MemoryModuleType.ANGRY_AT,
            MemoryModuleType.NEAREST_VISIBLE_NEMESIS,
            MemoryModuleType.HOME);

    public PiglinGuardsman(EntityType<? extends AbstractPiglin> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 20;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.325F)
                .add(Attributes.ATTACK_DAMAGE, 5.0D);
    }

    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
    }

    protected Brain.Provider<PiglinBrute> brainProvider() {
        return Brain.provider(MEMORY_TYPES, SENSOR_TYPES);
    }

//    protected Brain<?> makeBrain(Dynamic<?> pDynamic) {
//        return PiglinBruteAi.makeBrain(this, this.brainProvider().makeBrain(pDynamic));
//    }

    public Brain<PiglinBrute> getBrain() {
        return (Brain<PiglinBrute>)super.getBrain();
    }

    @Override
    protected boolean canHunt() {
        return false;
    }

    @Override
    public PiglinArmPose getArmPose() {
        return this.isAggressive() && this.isHoldingMeleeWeapon() ? PiglinArmPose.ATTACKING_WITH_MELEE_WEAPON : PiglinArmPose.DEFAULT;
    }

    public boolean hurt(DamageSource pSource, float pAmount) {
        boolean flag = super.hurt(pSource, pAmount);
        if (this.level().isClientSide) {
            return false;
        } else {
            if (flag && pSource.getEntity() instanceof LivingEntity) {
//                PiglinBruteAi.wasHurtBy(this, (LivingEntity)pSource.getEntity());
            }

            return flag;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PIGLIN_BRUTE_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PIGLIN_BRUTE_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PIGLIN_BRUTE_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.PIGLIN_BRUTE_STEP, 0.15F, 1.0F);
    }

    protected void playAngrySound() {
        this.playSound(SoundEvents.PIGLIN_BRUTE_ANGRY, 1.0F, this.getVoicePitch());
    }

    @Override
    protected void playConvertedSound() {
        this.playSound(SoundEvents.PIGLIN_BRUTE_CONVERTED_TO_ZOMBIFIED, 1.0F, this.getVoicePitch());
    }
}
