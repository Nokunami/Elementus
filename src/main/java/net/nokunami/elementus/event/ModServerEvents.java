package net.nokunami.elementus.event;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.message.MessageParticle;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
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
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.item.CatalystArmorItem;
import net.nokunami.elementus.common.item.DiarkriteChargeBlade;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.registry.ModEnchantments;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;

import java.util.Objects;
import java.util.function.Predicate;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.item.CatalystItemUtil.cursium;
import static net.nokunami.elementus.common.item.CatalystItemUtil.ignitium;
import static net.nokunami.elementus.common.item.DiarkriteChargeBlade.*;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModServerEvents {

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
            boolean startResonance = getCharge(stack) > 2;
            int resonanceLevel = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.RESONANCE.get(), stack);
            if (!emptyItem && stack.getItem() instanceof DiarkriteChargeBlade && resonanceLevel > 0 && entity instanceof Player player
                    && currentCharge && startResonance && !player.isUsingItem() && level.getGameTime() % 100 == 0) {
                addCharge(stack, 1);
            }
        }
    }

    @SubscribeEvent
    public void Elementus$Shielding(LivingDamageEvent event) {
        DamageSource damageSource = event.getSource();
        Vec3 position = event.getEntity().position();
        Vec3 viewVec = event.getEntity().getViewVector(1);
        Entity directEntity = damageSource.getDirectEntity();
        boolean usingItem = event.getEntity().isUsingItem();
        LivingEntity entity = event.getEntity();
        boolean flag = false;

        if (directEntity instanceof AbstractArrow abstractarrow) {
            if (abstractarrow.getPierceLevel() > 0) {
                flag = true;
            }
        }

        Vec3 vec32 = damageSource.getSourcePosition();
        if (vec32 != null) {
            Vec3 vec31 = vec32.vectorTo(position).normalize();
            vec31 = new Vec3(vec31.x, 0.0D, vec31.z);
            if (vec31.dot(viewVec) < 0.0D) {

                for (EquipmentSlot slot : EquipmentSlot.values()) {
                    ItemStack stack = entity.getItemBySlot(slot);
                    if (!stack.isEmpty() && stack.getItem() instanceof DiarkriteChargeBlade) {
                        Level level = entity.level();
                        if (!damageSource.is(DamageTypeTags.BYPASSES_SHIELD) && usingItem && !flag) {
                            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, SoundSource.PLAYERS, 1.5F, 1.5F);
                            event.setAmount(event.getAmount() / 2);
                            addCharge(stack, 1);
                        } else if (damageSource.is(DamageTypes.SONIC_BOOM)) {
                            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ZOMBIE_ATTACK_IRON_DOOR, SoundSource.PLAYERS, 1.5F, 1.5F);
                            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1.5F, 0F);
                            level.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BELL_BLOCK, SoundSource.PLAYERS, 1.5F, 0F);
                            event.setAmount(event.getAmount() * 0.8F);
                            addCharge(stack, (int) event.getAmount() / 2);
                        }
                    }

                }
            }
        }
    }
}