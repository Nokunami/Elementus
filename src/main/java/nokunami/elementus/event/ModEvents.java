package nokunami.elementus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.registry.ModEnchantments;
import nokunami.elementus.common.registry.ModItems.ElementusItems;

import java.util.List;

import static nokunami.elementus.event.VillagerTradeEnchantment.createForEnchantment;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 12),
                    createForEnchantment(ElementusItems.STEEL_LEGGINGS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(ElementusItems.STEEL_BOOTS.get(), 1, RandomSource.create(), false),
                    3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    createForEnchantment(ElementusItems.STEEL_HELMET.get(), 2, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ElementusItems.STEEL_AXE.get()),
                    3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    createForEnchantment(ElementusItems.STEEL_SWORD.get(), 1, RandomSource.create(), false),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ElementusItems.STEEL_HOE.get(), 1),
                    3, 10, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ElementusItems.STEEL_AXE.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ElementusItems.STEEL_SHOVEL.get(), 1),
                    3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 11),
                    new ItemStack(ElementusItems.STEEL_PICKAXE.get(), 1),
                    3, 30, 0.2F));
        }

        if (event.getType() == VillagerProfession.LIBRARIAN) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack arcaneSharpness = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.ARCANE_SHARPNESS.get(), 1));
            ItemStack resonance = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.RESONANCE.get(), 1));
            ItemStack condensedBurst = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(ModEnchantments.CONDENSED_BURST.get(), 1));

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
                new ItemStack(ElementusItems.MOVCADIA_LEAVES.get(), 5),
                6, 1, 0.25F)));

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(ElementusItems.MOVCADIA_BERRIES.get(), 4),
                4, 2, 0.5F)));

        rareTrades.add(((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 15),
                new ItemStack(ElementusItems.MOVCADIA_SAPLING.get(), 2),
                1, 5, 0.5F)));
    }
}
