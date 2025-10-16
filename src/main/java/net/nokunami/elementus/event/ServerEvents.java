package net.nokunami.elementus.event;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModParticle;
import com.github.L_Ender.cataclysm.message.MessageParticle;
import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
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
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.capability.CatalystExhaustionProvider;
import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.item.unique.CatalystArmorItem;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.item.unique.DiarkriteChargeBlade;
import net.nokunami.elementus.common.item.unique.TestCatalystArmorItem;
import net.nokunami.elementus.common.network.CatalystExhaustionSyncPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.*;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.entity.MobUtil.blockVec;
import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;
import static net.nokunami.elementus.common.item.unique.DiarkriteChargeBlade.*;
import static net.nokunami.elementus.common.registry.EEnchantments.RESONANCE;
import static net.nokunami.elementus.common.registry.ESoundEvents.*;
import static net.nokunami.elementus.common.registry.ModParticleTypes.PARRY;
import static net.nokunami.elementus.common.registry.ModParticleTypes.PARRY_RESONANCE;
import static net.nokunami.elementus.event.VillagerTradeEnchantment.createForEnchantment;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ServerEvents {
    private static final int parryWindow = 6;

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    createForEnchantment(EItems.STEEL_LEGGINGS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(EItems.STEEL_BOOTS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(EItems.STEEL_HELMET.get(), 2, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(EItems.STEEL_AXE.get()),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    createForEnchantment(EItems.STEEL_SWORD.get(), 1, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(EItems.STEEL_HOE.get(), 1),
                    3, 10, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(EItems.STEEL_AXE.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(EItems.STEEL_SHOVEL.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 11),
                    new ItemStack(EItems.STEEL_PICKAXE.get(), 1),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack arcaneSharpness = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EEnchantments.ARCANE_SHARPNESS.get(), 1));
            ItemStack resonance = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EEnchantments.RESONANCE.get(), 1));
            ItemStack condensedBurst = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(EEnchantments.CONDENSED_BURST.get(), 1));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 24), new ItemStack(Items.BOOK, 1),
                    condensedBurst,
                    3, 10, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 36), new ItemStack(Items.BOOK, 1),
                    resonance,
                    2, 20, 0.8F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 56), new ItemStack(Items.BOOK, 1),
                    arcaneSharpness,
                    3, 30, 0.95F));
        }
    }

    @SubscribeEvent
    public static void addWanderingTraderTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 4),
                new ItemStack(EItems.MOVCADIA_LEAVES.get(), 5),
                6, 1, 0.25F)));

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(EItems.MOVCADIA_BERRIES.get(), 4),
                4, 2, 0.5F)));

        rareTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 15),
                new ItemStack(EItems.MOVCADIA_SAPLING.get(), 2),
                1, 5, 0.5F)));
    }

    @SubscribeEvent
    public void CatalystArmorPostDamageEvent(LivingDamageEvent event) {
        ItemStack itemStack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        var core = getEquippedCore(itemStack);
        Level level = event.getEntity().level();
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof TestCatalystArmorItem && core.isPresent()) {
            for(Pair<PassiveCatalystAbility, Float> pair : CustomRegistries.getCatalystCore(core.get()).getPassiveAbility()) {
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    pair.getFirst().postDamageEvent(event);
                }
            }
        }
    }

    @SubscribeEvent
    public void CatalystArmorPostDeathEvent(LivingDeathEvent event) {
        ItemStack itemStack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        var core = getEquippedCore(itemStack);
        Level level = event.getEntity().level();
        if (!itemStack.isEmpty() && itemStack.getItem() instanceof TestCatalystArmorItem && core.isPresent()) {
            for(Pair<PassiveCatalystAbility, Float> pair : CustomRegistries.getCatalystCore(core.get()).getPassiveAbility()) {
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    pair.getFirst().postDeathEvent(event);
                }
            }
        }
    }

    @SubscribeEvent
    public void CatalystIgnitiumEffect(LivingDamageEvent event) {
        ItemStack stack = event.getEntity().getItemBySlot(EquipmentSlot.CHEST);
        Entity attacker = event.getSource().getEntity();
        if (cataclysm) {
            if (!stack.isEmpty() && event.getSource() != null && attacker != null
                    && stack.getItem() == EItems.CATALYST_CHESTPLATE.get()) {
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
                if (stack.getItem() == EItems.CATALYST_CHESTPLATE.get() && CatalystArmorItem.catalystActivator(stack).equals(cursium)) {
                    if (event.getEntity().hasEffect(ModEffect.EFFECTGHOST_FORM.get())) {
                        if (!event.getSource().is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
                            event.setCanceled(true);
                        }
                    }
                    if (!stack.isEmpty() && stack.getItem() == EItems.CATALYST_CHESTPLATE.get()) {
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
                    && stack.getItem() == EItems.CATALYST_CHESTPLATE.get() && CatalystArmorItem.catalystActivator(stack).equals(cursium)) {
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
            if (chestplate.getItem() == EItems.CATALYST_CHESTPLATE.get()
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
                .where('#', BlockInWorld.hasState(BlockStatePredicate.forBlock(EBlocks.STEEL_BLOCK.get())))
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
                AstaliteGolem steelgolem = ModEntityType.STEEL_GOLEM.get().create(level);
                assert steelgolem != null;
                steelgolem.setPlayerCreated(true);
                if (event.getEntity() != null && event.getEntity() instanceof Player player) {
                    steelgolem.tame(player);
                }
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
    public void ParryEvent(LivingHurtEvent event) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Vec3 position = eventEntity.position();
        Vec3 viewVec = eventEntity.getViewVector(1);
        Vec3 vec32 = damageSource.getSourcePosition();
        Entity directAttacker = damageSource.getDirectEntity();
        Entity attacker = damageSource.getEntity();
        boolean usingItem = eventEntity.isUsingItem();
        Level level = eventEntity.level();
        float damage = event.getAmount();
        float randomFloat = 0.5F + (eventEntity.getRandom().nextFloat() - eventEntity.getRandom().nextFloat()) * 0.5F;

        boolean flag = directAttacker instanceof AbstractArrow abstractarrow && abstractarrow.getPierceLevel() > 0;

        if (blockVec(vec32, position, viewVec))
            for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = eventEntity.getItemBySlot(slot);
            boolean parry = eventEntity.getTicksUsingItem() <= parryWindow;

            if (!stack.isEmpty() && stack.getItem() instanceof ChargeBladeItem && usingItem) {
                float soundVol = 1;
                SoundEvent soundEvent = isEnchantedWith(stack, RESONANCE) ? DIARKRITE_CHARGE_BLADE_BLOCK_RESONANCE.get() : CHARGE_BLADE_BLOCK.get();
                float damageAmount = 0;
                float knockback = 0;
                if (((ChargeBladeItem)stack.getItem()).canParry() && parry) {
                    soundEvent = isEnchantedWith(stack, RESONANCE) ? DIARKRITE_CHARGE_BLADE_PARRY_RESONANCE.get() : CHARGE_BLADE_PARRY.get();
                    ParticleOptions parryParticle = isEnchantedWith(stack, RESONANCE) ? PARRY_RESONANCE.get() : PARRY.get();
                    parryParticle(level, eventEntity, parryParticle);
                    setCharge(stack, 1);
                    soundVol = 1.5F;
                    damageAmount = event.getAmount();
                    knockback = 2;
                    removeStuckEntities(event);
                    event.setCanceled(true);
                } else if (!damageSource.is(DamageTypeTags.BYPASSES_SHIELD) && !flag) {
                    event.setAmount(damage - (damage * damageAbsorption(stack)));
                    knockback = 1;
                } else if (stack.getItem() instanceof DiarkriteChargeBlade && damageSource.is(DamageTypes.SONIC_BOOM)) {
                    soundEvent = DIARKRITE_CHARGE_BLADE_SONIC_RESONANCE.get();
                    event.setAmount(event.getAmount() * 0.8F);
                    setCharge(stack, (int) event.getAmount() / 2);
                }
                level.playSound(null, eventEntity, soundEvent, SoundSource.PLAYERS, soundVol, randomFloat);
                setCharge(stack, 1);
                setResonanceCharge(stack, 1);
                applyRecoil(directAttacker, eventEntity, knockback, true);

                if ((directAttacker instanceof LivingEntity living && parry)) {
                    parry(level, (Player) eventEntity, living, damageAmount);
                }
                if (attacker instanceof LivingEntity living && ((isEnchantedWith(stack, RESONANCE) && parry))) {
                    parry(level, (Player) eventEntity, living, damageAmount);
                }
            }
        }
    }

    private void blockAction(LivingHurtEvent event, boolean cancelEvent, float damage, ItemStack stack, double chargeAmount) {
        event.setCanceled(cancelEvent);
        event.setAmount(damage);
        setCharge(stack, (int) chargeAmount);
    }

    private void blockAction(LivingHurtEvent event, float damage, ItemStack stack, double chargeAmount) {
        blockAction(event, false, damage, stack, chargeAmount);
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

    @SubscribeEvent
    public void DeflectProjectile(ProjectileImpactEvent event) {
        Projectile projectile = event.getProjectile();
        Vec3 projectileDelta = event.getProjectile().getDeltaMovement();

        if (event.getRayTraceResult() instanceof EntityHitResult result) {
            Elementus.LOGGER.debug("testHit1");
            if (result.getEntity() instanceof LivingEntity entity) {
                Level level = entity.level();
                float randomFloat = 0.5F + (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.5F;
                ItemStack stack = entity.getUseItem();
                Vec3 position = entity.position();
                Vec3 viewVec = entity.getViewVector(1);
                Vec3 vec32 = projectile.position();

                Vec3 vec31 = vec32.vectorTo(position).normalize();
                vec31 = new Vec3(vec31.x, 0.0D, vec31.z);

                if (vec31.dot(viewVec) < 0.0D) {
                    if (entity.getUseItem().getItem() instanceof ChargeBladeItem bladeItem && entity.isUsingItem() && entity.getTicksUsingItem() <= parryWindow && bladeItem.canParry() && !isEnchantedWith(stack, RESONANCE)) {
                        Elementus.LOGGER.debug("testHit2 this deflects \"i hope\"");
//                        event.setCanceled(true);
                        event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
                        parryParticleAlt(level, entity, PARRY.get());
                        level.playSound(null, entity, ESoundEvents.CHARGE_BLADE_PARRY.get(), SoundSource.PLAYERS, 1, randomFloat);
                        projectile.setDeltaMovement(-projectileDelta.x/2, -projectileDelta.y/2, -projectileDelta.z/2);
                    }
                }
            }
        }
    }

    public void steelGolemConversion(LivingConversionEvent event) {
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(CatalystExhaustionProvider.CAP).ifPresent(exhaustion -> {
                if(exhaustion.getExhaustion() > 0 && event.player.tickCount % 20 == 0) { // Once Every 10 Seconds on Avg
                    exhaustion.subExhaustion(1);
                    ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) event.player), new CatalystExhaustionSyncPacket(exhaustion.getExhaustion()));
                }
            });
        }
    }
}