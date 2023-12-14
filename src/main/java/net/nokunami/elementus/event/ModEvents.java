package net.nokunami.elementus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.item.ItemsRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent tradesEvent) {
        if (tradesEvent.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();
            EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION,1));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,12),
                    new ItemStack(ItemsRegistry.STEEL_LEGGINGS.get())
                    ,3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    new ItemStack(ItemsRegistry.STEEL_BOOTS.get())
                    ,3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    new ItemStack(ItemsRegistry.STEEL_HELEMT.get()),
                    3, 30, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,14),
                    new ItemStack(ItemsRegistry.STEEL_CHESTPLATE.get()),
                    3, 30, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,30),
                    new ItemStack(ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
                            1),3, 50, 0.2F));
        }

        if (tradesEvent.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ItemsRegistry.STEEL_AXE.get(),
                            1), 3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(ItemsRegistry.STEEL_SWORD.get(),
                            1), 3, 30, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,30),
                    new ItemStack(ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
                            1),3, 50, 0.2F));
        }

        if (tradesEvent.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ItemsRegistry.STEEL_HOE.get(),
                            1), 3, 10, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ItemsRegistry.STEEL_AXE.get(),
                            1), 3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ItemsRegistry.STEEL_SHOVEL.get(),
                            1), 3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 11),
                    new ItemStack(ItemsRegistry.STEEL_PICKAXE.get(),
                            1), 3, 30, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,30),
                    new ItemStack(ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
                            1),3, 50, 0.2F));
        }
    }


}
