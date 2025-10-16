package net.nokunami.elementus.event;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.capability.CatalystExhaustion;
import net.nokunami.elementus.common.capability.CatalystExhaustionProvider;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ModEvents {
//
//    @SubscribeEvent
//    public static void addCustomTrades(VillagerTradesEvent event) {
//        if (event.getType() == VillagerProfession.ARMORER) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 12),
//                    createForEnchantment(ModItems.STEEL_LEGGINGS.get(), 1, RandomSource.create(), false),
//                    3, 15, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,7),
//                    createForEnchantment(ModItems.STEEL_BOOTS.get(), 1, RandomSource.create(), false),
//                    3, 15, 0.2F));
//
//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,7),
//                    createForEnchantment(ModItems.STEEL_HELMET.get(), 2, RandomSource.create(), false),
//                    3, 30, 0.2F));
//        }
//
//        if (event.getType() == VillagerProfession.WEAPONSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 10),
//                    new ItemStack(ModItems.STEEL_AXE.get()),
//                    3, 15, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 7),
//                    createForEnchantment(ModItems.STEEL_SWORD.get(), 1, RandomSource.create(), false),
//                    3, 30, 0.2F));
//        }
//
//        if (event.getType() == VillagerProfession.TOOLSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//
//            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 4),
//                    new ItemStack(ModItems.STEEL_HOE.get(), 1),
//                    3, 10, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 10),
//                    new ItemStack(ModItems.STEEL_AXE.get(), 1),
//                    3, 15, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 4),
//                    new ItemStack(ModItems.STEEL_SHOVEL.get(), 1),
//                    3, 15, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 11),
//                    new ItemStack(ModItems.STEEL_PICKAXE.get(), 1),
//                    3, 30, 0.2F));
//        }
//
//        if (event.getType() == VillagerProfession.LIBRARIAN) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
//            ItemStack arcaneSharpness = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ARCANE_SHARPNESS.get(), 1));
//            ItemStack resonance = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.RESONANCE.get(), 1));
//            ItemStack condensedBurst = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.CONDENSED_BURST.get(), 1));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 24), new ItemStack(Items.BOOK, 1),
//                    condensedBurst,
//                    3, 10, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 36), new ItemStack(Items.BOOK, 1),
//                    resonance,
//                    2, 20, 0.8F));
//
//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 56), new ItemStack(Items.BOOK, 1),
//                    arcaneSharpness,
//                    3, 30, 0.95F));
//        }
//    }
//
//    @SubscribeEvent
//    public static void addWanderingTraderTrades(WandererTradesEvent event) {
//        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
//        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();
//
//        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
//                new ItemStack(Items.EMERALD, 4),
//                new ItemStack(ModItems.MOVCADIA_LEAVES.get(), 5),
//                6, 1, 0.25F)));
//
//        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
//                new ItemStack(Items.EMERALD, 6),
//                new ItemStack(ModItems.MOVCADIA_BERRIES.get(), 4),
//                4, 2, 0.5F)));
//
//        rareTrades.add(((pTrader, pRandom) -> new MerchantOffer(
//                new ItemStack(Items.EMERALD, 15),
//                new ItemStack(ModItems.MOVCADIA_SAPLING.get(), 2),
//                1, 5, 0.5F)));
//    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
//            event.getOriginal().getCapability(TestArmorCapProvider.TEST_ARMOR_CAP).ifPresent(oldCap ->
//                    event.getOriginal().getCapability(TestArmorCapProvider.TEST_ARMOR_CAP).ifPresent(newCap -> newCap.copy(oldCap)));
            event.getOriginal().getCapability(CatalystExhaustionProvider.CAP).ifPresent(oldCap ->
                    event.getOriginal().getCapability(CatalystExhaustionProvider.CAP).ifPresent(newCap -> newCap.copyExhaustion(oldCap)));
        }
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
//        event.register(TestArmorCap.class);
        event.register(CatalystExhaustion.class);
    }
}
