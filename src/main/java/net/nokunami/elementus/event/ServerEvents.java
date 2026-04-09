package net.nokunami.elementus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.capability.cAbility.CAbilityCap;
import net.nokunami.elementus.common.item.unique.ChargeBladeItem;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.registry.EEnchantments;
import net.nokunami.elementus.common.registry.EItems;

import java.util.List;
import java.util.Objects;

import static net.nokunami.elementus.common.entity.ModParticleUtil.spawnParticlesOnEntity;
import static net.nokunami.elementus.common.item.EItemUtil.enchantedWith;
import static net.nokunami.elementus.common.item.unique.BladeOfResonance.setCharge;
import static net.nokunami.elementus.common.item.unique.ChargeBladeItem.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;
import static net.nokunami.elementus.common.registry.EEnchantments.RESONANCE;
import static net.nokunami.elementus.event.VillagerTradeEnchantment.createForEnchantment;

@Mod.EventBusSubscriber(modid = Elementus.EID)
public class ServerEvents {
    public static final int parryWindow = 6;

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    createForEnchantment(EItems.ASTALITE_LEGGINGS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(EItems.ASTALITE_BOOTS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(EItems.ASTALITE_HELMET.get(), 2, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(EItems.ASTALITE_AXE.get()),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    createForEnchantment(EItems.ASTALITE_SWORD.get(), 1, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(EItems.ASTALITE_HOE.get(), 1),
                    3, 10, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(EItems.ASTALITE_AXE.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(EItems.ASTALITE_SHOVEL.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 11),
                    new ItemStack(EItems.ASTALITE_PICKAXE.get(), 1),
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
    public void CatalystArmorPostHurtEvent(LivingHurtEvent event) {
        CatalystCoreUtil(event.getEntity()).postHurt(event);
    }

    @SubscribeEvent
    public void CatalystArmorPostDamageEvent(LivingDamageEvent event) {
        CatalystCoreUtil(event.getEntity()).postDamage(event);
    }

    @SubscribeEvent
    public void CatalystArmorPostDeathEvent(LivingDeathEvent event) {
        CatalystCoreUtil(event.getEntity()).postDeath(event);
    }

    @SubscribeEvent
    public void spawnSteelGolemEvent(BlockEvent.EntityPlaceEvent event) {
        Block block = event.getPlacedBlock().getBlock();
        Entity blockPlacer = event.getEntity();
        if (block instanceof CarvedPumpkinBlock) {
            BlockPos blockpos = event.getPos();
            Level level = Objects.requireNonNull(event.getEntity()).level();
            GolemSpawnUtils.createAstaliteCarrier(blockPlacer, level, blockpos);
            GolemSpawnUtils.createAstaliteLongarm(blockPlacer, level, blockpos);
        }
    }

    @SubscribeEvent
    public void LivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = entity.getCommandSenderWorld();
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);

            boolean currentCharge = getCharge(stack) < getMaxCharge(stack);
            boolean startResonance = getResonanceCharge(stack) > 0;
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof ChargeBladeItem && enchantedWith(stack, RESONANCE)
//                    && entity instanceof Player player
                    && currentCharge && startResonance) {
                    if (!entity.isUsingItem() && level.getGameTime() % 100 == 0) {
                        setCharge(stack, 1);
                        setResonanceCharge(stack, -1);
                        setResonanceTick(stack, 2);
                        spawnParticlesOnEntity(level, entity.position(), ParticleTypes.GLOW, entity, UniformInt.of(2, 4));
                    }
                }
            }
        }
        CAbility.onLivingTick(event);
    }

    @SubscribeEvent
    public void ParryEvent(LivingHurtEvent event) {
        LivingEntity eventEntity = event.getEntity();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = eventEntity.getItemBySlot(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof ChargeBladeItem bladeItem) {
                bladeItem.blockDirectEvent(event, stack);
            }
        }
    }

//    @SubscribeEvent
//    public void DeflectProjectile(ProjectileImpactEvent event) {
//        Projectile projectile = event.getProjectile();
//        Vec3 projectileDelta = event.getProjectile().getDeltaMovement();
//
//        if (event.getRayTraceResult() instanceof EntityHitResult result) {
//            Elementus.LOGGER.debug("testHit1");
//            if (result.getEntity() instanceof LivingEntity entity) {
//
//                if (entity.getUseItem().getItem() instanceof ChargeBladeItem bladeItem) {
//                    bladeItem.blockProjectileEvent(event, entity.getUseItem(), entity, projectile, projectileDelta);
//                }
//            }
//        }
//    }

    @SubscribeEvent
    public void disableShieldEvent(ShieldBlockEvent event) {

    }

    @SubscribeEvent
    public void playerCloneEvent(PlayerEvent.Clone event) {
//        var player = event.getEntity();
//        var ca = CAbility.instance(player);
        if(event.isWasDeath()) {
//            event.getOriginal().getCapability(CAbilityCap.CAP).ifPresent(
//                    oldStore -> event.getOriginal().getCapability(CAbilityCap.CAP).ifPresent(
//                            newStore -> {
//                                newStore.load(oldStore);
//                                if (event.getEntity() instanceof ServerPlayer serverPlayer)
//                                    ENetwork.sendTo(serverPlayer, new SyncAbilitySelectionS2CPacket(newStore.getSelection()));
//                            }
//                    ));
            var caOld = CAbility.instance(event.getOriginal());
            var caNew = CAbility.instance(event.getEntity());
            caNew.load(caOld);
            if (event.getEntity() instanceof ServerPlayer player) {
                ENetwork.sendTo(player, new SyncAbilitySelectionS2CPacket(caNew.getSelection()));
            }
        }
    }
}