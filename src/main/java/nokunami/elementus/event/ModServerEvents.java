package nokunami.elementus.event;

import com.aetherteam.aether.item.combat.DartItem;
import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.message.MessageParticle;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.config.CatalystArmorConfig;
import nokunami.elementus.common.entity.living.SteelGolem;
import nokunami.elementus.common.item.CatalystArmorItem;
import nokunami.elementus.common.item.ChargeSwordItem;
import nokunami.elementus.common.item.DiarkriteChargeBlade;
import nokunami.elementus.common.registry.ModBlocks;
import nokunami.elementus.common.registry.ModEntityType;
import nokunami.elementus.common.registry.ModItems.ElementusItems;
import nokunami.elementus.common.registry.ModParticleTypes;
import nokunami.elementus.common.registry.ModSoundEvents;
import nonamecrackers2.witherstormmod.common.event.EntityConversionEvents;

import java.util.Objects;
import java.util.function.Predicate;

import static nokunami.elementus.ModChecker.cataclysm;
import static nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static nokunami.elementus.common.item.CatalystItemUtil.cursium;
import static nokunami.elementus.common.item.CatalystItemUtil.ignitium;
import static nokunami.elementus.common.item.DiarkriteChargeBlade.*;
import static nokunami.elementus.common.registry.ModEnchantments.RESONANCE;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModServerEvents {
    private static final int parryWindow = 6;

    @SubscribeEvent
    public void CatalystIgnitiumEffect(LivingDamageEvent event) {
        ItemStack stack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (!stack.isEmpty() && event.getSource() != null && attacker != null
                    && stack.getItem() == ElementusItems.CATALYST_CHESTPLATE.get()) {
                if (CatalystArmorItem.catalystActivator(stack).equals(ignitium)) {
                    if (attacker instanceof LivingEntity && attacker != event.getEntity()) {
                        if (event.getEntity().getRandom().nextFloat() < 0.5F) {
                            MobEffectInstance effectinstance1 = ((LivingEntity) attacker).getEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                            int i = 1;
                            if (effectinstance1 != null) {
                                i += effectinstance1.getAmplifier();
                                ((LivingEntity) attacker).removeEffectNoUpdate(ModEffect.EFFECTBLAZING_BRAND.get());
                            } else {
                                --i;
                            }

                            i = Mth.clamp(i, 0, 4);
                            MobEffectInstance effectinstance = new MobEffectInstance(ModEffect.EFFECTBLAZING_BRAND.get(), 100, i, false, false, true);
                            ((LivingEntity) attacker).addEffect(effectinstance);

                            if (!attacker.isOnFire()) {
                                attacker.setSecondsOnFire(5);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void CatalystCursiumEffect(LivingDamageEvent event) {
        ItemStack stack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (!stack.isEmpty() && event.getSource() != null && attacker != null) {
                if (stack.getItem() == ElementusItems.CATALYST_CHESTPLATE.get() && CatalystArmorItem.catalystActivator(stack).equals(cursium)) {
                    if (event.getEntity().hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
                        if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                            event.setCanceled(true);
                        }
                    }
                    if (!stack.isEmpty() && stack.getItem() == ElementusItems.CATALYST_CHESTPLATE.get()) {
                        if (event.getSource().is(DamageTypeTags.IS_PROJECTILE)) {
                            if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_ProjectileDodgeChance) {
                                event.setCanceled(true);
                            }
                        } else if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                            if (event.getEntity().getRandom().nextFloat() < CatalystArmorConfig.cursium_DodgeChance) {
                                event.setCanceled(true);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void CatalystCursiumReviveEffect(LivingDeathEvent event) {
        ItemStack stack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        DamageSource source = event.getSource();
        if (cataclysm) {
            if (!event.getEntity().level().isClientSide && !stack.isEmpty()
                    && stack.getItem() == ElementusItems.CATALYST_CHESTPLATE.get() && CatalystArmorItem.catalystActivator(stack).equals(cursium)) {
                if (!source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                    if(tryCursiumPlateRebirth(event.getEntity())){
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private static boolean tryCursiumPlateRebirth(LivingEntity living) {
        ItemStack chestplate = living.getItemBySlot(EquipmentSlot.CHEST);
        if (cataclysm) { // Copy-pasted from Cataclysm's ServerEventHandler
            if (chestplate.getItem() == ElementusItems.CATALYST_CHESTPLATE.get()
                    && CatalystArmorItem.catalystActivator(chestplate).equals(cursium)
                    && !living.hasEffect(ModEffect.EFFECTGHOST_SICKNESS.get())
                    && !living.hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
                living.setHealth(5.0F);
                living.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, CatalystArmorConfig.cursium_FireResistDuration, 0));
                living.addEffect(new MobEffectInstance(ModEffect.EFFECTGHOST_FORM.get(), CatalystArmorConfig.cursium_GhostFormDuration, 0));
                double d0 = living.getX();
                double d1 = living.getY() + 0.3F;
                double d2 = living.getZ();
                float size = 3.0F;
                for (ServerPlayer serverplayer : ((ServerLevel) living.level()).players()) {
                    if (serverplayer.distanceToSqr(Vec3.atCenterOf(living.blockPosition())) < 1024.0D) {
                        MessageParticle particlePacket = new MessageParticle();
                        for (float i = -size; i <= size; ++i) {
                            for (float j = -size; j <= size; ++j) {
                                for (float k = -size; k <= size; ++k) {
                                    double d3 = (double) j + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d4 = (double) i + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d5 = (double) k + (living.getRandom().nextDouble() - living.getRandom().nextDouble()) * 0.5D;
                                    double d6 = (double) Mth.sqrt((float) (d3 * d3 + d4 * d4 + d5 * d5)) / 0.5 + living.getRandom().nextGaussian() * 0.05D;
                                    particlePacket.queueParticle(ModParticle.CURSED_FLAME.get(),false, d0 , d1, d2, d3 / d6, d4 / d6, d5 / d6);
                                    if (i != -size && i != size && j != -size && j != size) {
                                        k += size * 2 - 1;
                                    }
                                }
                            }
                        }
                        Cataclysm.NETWORK_WRAPPER.send(PacketDistributor.PLAYER.with(() -> serverplayer), particlePacket);
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static final Predicate<BlockState> PUMPKINS_PREDICATE = (block) -> block != null && (block.is(Blocks.CARVED_PUMPKIN) || block.is(Blocks.JACK_O_LANTERN));
    private BlockPattern createSteelGolemFull() {
        return BlockPatternBuilder.start().aisle(
                        "~^~",
                        "###",
                        "~#~")
                .where('^', BlockInWorld.hasState(PUMPKINS_PREDICATE))
                .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(ModBlocks.ElementusBlocks.STEEL_BLOCK.get())))
                .where('~', (state) -> state.getState().isAir()).build();
    }

    @SubscribeEvent
    public void spawnSteelGolemEvent(BlockEvent.EntityPlaceEvent event) {
        Block block = event.getPlacedBlock().getBlock();
        if (block instanceof CarvedPumpkinBlock) {
            BlockPos blockpos = event.getPos();
            Level level = Objects.requireNonNull(event.getEntity()).level();
            BlockPattern.BlockPatternMatch blockpattern$blockpatternmatch = createSteelGolemFull().find(level, blockpos);
            if (blockpattern$blockpatternmatch != null) {
                // clearPatternBlocks in CarvedPumpkinBlock.java
                for (int j = 0; j < createSteelGolemFull().getWidth(); ++j) {
                    for (int k = 0; k < createSteelGolemFull().getHeight(); ++k) {
                        BlockInWorld blockinworld = blockpattern$blockpatternmatch.getBlock(j, k, 0);
                        level.setBlock(blockinworld.getPos(), Blocks.AIR.defaultBlockState(), 2);
                        level.levelEvent(2001, blockinworld.getPos(), Block.getId(blockinworld.getState()));
                    }
                }
                blockpos = blockpattern$blockpatternmatch.getBlock(1, 2, 0).getPos();
                SteelGolem steelgolem = ModEntityType.STEEL_GOLEM.get().create(level);
                assert steelgolem != null;
                steelgolem.setPlayerCreated(true);
                steelgolem.moveTo((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.05D, (double) blockpos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(steelgolem);

                for (ServerPlayer serverplayer1 : level.getEntitiesOfClass(ServerPlayer.class, steelgolem.getBoundingBox().inflate(5.0D))) {
                    CriteriaTriggers.SUMMONED_ENTITY.trigger(serverplayer1, steelgolem);
                }

                // updatePatternBlocks in CarvedPumpkinBlock.java
                for (int i1 = 0; i1 < createSteelGolemFull().getWidth(); ++i1) {
                    for (int j1 = 0; j1 < createSteelGolemFull().getHeight(); ++j1) {
                        BlockInWorld blockinworld = blockpattern$blockpatternmatch.getBlock(i1, j1, 0);
                        level.blockUpdated(blockinworld.getPos(), Blocks.AIR);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void LivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.getCommandSenderWorld();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);
            boolean emptyItem = stack.isEmpty();
            boolean currentCharge = getCharge(stack) < getMaxCharge(stack);
            boolean startResonance = getResonanceCharge(stack) > 0;
            if (!emptyItem && stack.getItem() instanceof DiarkriteChargeBlade && isEnchantedWith(stack, RESONANCE) && entity instanceof Player player
                    && currentCharge && startResonance && !player.isUsingItem() && level.getGameTime() % 100 == 0) {
                setCharge(stack, 1);
                setResonanceCharge(stack, -1);
                setResonanceTick(stack, 2);
                spawnParticlesOnEntity(level, entity.position(), ParticleTypes.GLOW, entity, UniformInt.of(2, 4));
            }
        }
    }

    private float damageAbsorption(ItemStack stack) {
        float i0 = Math.min(getCharge(stack), getMaxCharge(stack));
        float i1 = getMaxCharge(stack);
        return 1 - ((i0 / i1) * 0.5F);
    }

    @SubscribeEvent
    public void ShieldingParrying(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Vec3 position = eventEntity.position();
        Vec3 viewVec = eventEntity.getViewVector(1);
        Vec3 vec32 = damageSource.getSourcePosition();
        Entity directEntity = damageSource.getDirectEntity();
        Entity attacker = damageSource.getEntity();
        boolean usingItem = eventEntity.isUsingItem();
        Level level = eventEntity.level();
        float damage = event.getAmount();
        float randomFloat = 0.5F + (eventEntity.getRandom().nextFloat() - eventEntity.getRandom().nextFloat()) * 0.5F;

        boolean flag = false;

        if (directEntity instanceof AbstractArrow abstractarrow) {
            if (abstractarrow.getPierceLevel() > 0) {
                flag = true;
            }
        }

        if (vec32 != null) {
            Vec3 vec31 = vec32.vectorTo(position).normalize();
            vec31 = new Vec3(vec31.x, 0.0D, vec31.z);
            if (vec31.dot(viewVec) < 0.0D) {

                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack stack = eventEntity.getItemBySlot(slot);
                    boolean parry = eventEntity.getTicksUsingItem() <= parryWindow;

                    if (!stack.isEmpty() && stack.getItem() instanceof ChargeSwordItem && usingItem) {
                        float soundVol = 1;
                        SoundEvent soundEvent = isEnchantedWith(stack, RESONANCE) ? ModSoundEvents.DIARKRITE_CHARGE_BLADE_BLOCK_RESONANCE.get() : ModSoundEvents.DIARKRITE_CHARGE_BLADE_BLOCK.get();
                        float damageAmount = 0;
                        float knockback = 0;
                        if (((ChargeSwordItem)stack.getItem()).canParry() && parry) {
                            soundEvent = isEnchantedWith(stack, RESONANCE) ? ModSoundEvents.DIARKRITE_CHARGE_BLADE_PARRY_RESONANCE.get() : ModSoundEvents.DIARKRITE_CHARGE_BLADE_PARRY.get();
                            ParticleOptions parryParticle = isEnchantedWith(stack, RESONANCE) ? ModParticleTypes.PARRY_RESONANCE.get() : ModParticleTypes.PARRY.get();
                            parryParticle(level, eventEntity, parryParticle);
                            setCharge(stack, 1);
                            soundVol = 1.5F;
                            damageAmount = event.getAmount();
                            knockback = 2;
//                            if (directEntity instanceof Projectile projectile) {
//                                // this is horrible
//                                if (projectile instanceof AbstractArrow ) {
//                                    eventEntity.setArrowCount(eventEntity.getArrowCount() - 1);
//                                }
//                            }
//                            if (directEntity instanceof Bee) eventEntity.setStingerCount(eventEntity.getStingerCount() - 1);
                            removeStuckEntities(event);
                            event.setCanceled(true);
                        } else if (!damageSource.is(DamageTypeTags.BYPASSES_SHIELD) && !flag) {
                            event.setAmount(damage - (damage * damageAbsorption(stack)));
                            if (isEnchantedWith(stack, RESONANCE)) damageAmount = event.getAmount() * 0.25F;
                            knockback = 1;
                        } else if (stack.getItem() instanceof DiarkriteChargeBlade && damageSource.is(DamageTypes.SONIC_BOOM)) {
                            soundEvent = ModSoundEvents.DIARKRITE_CHARGE_BLADE_SONIC_RESONANCE.get();
                            event.setAmount(event.getAmount() * 0.8F);
                            setCharge(stack, (int) event.getAmount() / 2);
                        }
                        level.playSound(null, eventEntity, soundEvent, SoundSource.PLAYERS, soundVol, randomFloat);
                        setCharge(stack, 1);
                        setResonanceCharge(stack, 1);
                        applyRecoil(directEntity, eventEntity, knockback, 1);
                        if (directEntity instanceof LivingEntity living && parry) {
                            parry(level, (Player) eventEntity, living, damageAmount);
                        }
                        if (attacker instanceof LivingEntity living && ((isEnchantedWith(stack, RESONANCE) && parry) || isEnchantedWith(stack, RESONANCE))) {
                            parry(level, (Player) eventEntity, living, damageAmount);
                        }
                    }
                }
            }
        }
    }

    private void removeStuckEntities(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Entity directEntity = damageSource.getDirectEntity();

        if (directEntity instanceof Projectile projectile) {
            // this is horrible
            if (projectile instanceof AbstractArrow ) {
                eventEntity.setArrowCount(eventEntity.getArrowCount() - 1);
            }
        }
        if (directEntity instanceof Bee) eventEntity.setStingerCount(eventEntity.getStingerCount() - 1);
    }

//    public void catalystEffects(LivingEvent event) {
//        ItemStack chestplate = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
//        Predicate<ItemStack> coreItem = (e -> e.is(Items.NETHER_STAR));
//        if (chestplate.is(ElementusItems.CATALYST_CHESTPLATE.get())) {
//            if (CatalystArmorItem.getContents(chestplate).anyMatch(coreItem)) {
//                event.getEntity().addEffect()
//            }
//        }
//    }

    public void steelGolemConversion(LivingConversionEvent event) {

    }
}