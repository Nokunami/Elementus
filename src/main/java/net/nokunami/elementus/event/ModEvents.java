package net.nokunami.elementus.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.registry.ModItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ModEvents {

//    @SubscribeEvent
//    public static void addCustomTrades(VillagerTradesEvent tradesEvent) {
//        if (tradesEvent.getType() == VillagerProfession.ARMORER) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();
//            EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION,1));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,12),
//                    new ItemStack(ModItems.STEEL_LEGGINGS.get())
//                    ,3, 15, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,7),
//                    new ItemStack(ModItems.STEEL_BOOTS.get())
//                    ,3, 15, 0.2F));
//
//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,7),
//                    new ItemStack(ModItems.STEEL_HELEMT.get()),
//                    3, 30, 0.2F));
//
//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,14),
//                    new ItemStack(ModItems.STEEL_CHESTPLATE.get()),
//                    3, 30, 0.2F));
//
//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,30),
//                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
//                            1),3, 50, 0.2F));
//        }
//
//        if (tradesEvent.getType() == VillagerProfession.WEAPONSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 10),
//                    new ItemStack(ModItems.STEEL_AXE.get(),
//                            1), 3, 15, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 7),
//                    new ItemStack(ModItems.STEEL_SWORD.get(),
//                            1), 3, 30, 0.2F));
//
//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,30),
//                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
//                            1),3, 50, 0.2F));
//        }
//
//        if (tradesEvent.getType() == VillagerProfession.TOOLSMITH) {
//            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = tradesEvent.getTrades();
//
//            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 4),
//                    new ItemStack(ModItems.STEEL_HOE.get(),
//                            1), 3, 10, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 10),
//                    new ItemStack(ModItems.STEEL_AXE.get(),
//                            1), 3, 15, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 4),
//                    new ItemStack(ModItems.STEEL_SHOVEL.get(),
//                            1), 3, 15, 0.2F));
//
//            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD, 11),
//                    new ItemStack(ModItems.STEEL_PICKAXE.get(),
//                            1), 3, 30, 0.2F));
//
////            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
////                    new ItemStack(Items.EMERALD,30),
////                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
////                            1),3, 50, 0.2F));
//        }
//    }

//    static class EnchantedItemForEmeralds implements VillagerTrades.ItemListing {
//        private final ItemStack itemStack;
//        private final int baseEmeraldCost;
//        private final int maxUses;
//        private final int villagerXp;
//        private final float priceMultiplier;
//
//        public EnchantedItemForEmeralds(ItemStack pItem, int pBaseEmeraldCost, int pMaxUses, int pVillagerXp) {
//            this(pItem.getItem(), pBaseEmeraldCost, pMaxUses, pVillagerXp, 0.05F);
//        }
//
//        public EnchantedItemForEmeralds(Item pItem, int pBaseEmeraldCost, int pMaxUses, int pVillagerXp, float pPriceMultiplier) {
//            this.itemStack = new ItemStack(pItem);
//            this.baseEmeraldCost = pBaseEmeraldCost;
//            this.maxUses = pMaxUses;
//            this.villagerXp = pVillagerXp;
//            this.priceMultiplier = pPriceMultiplier;
//        }
//
//        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
//            int i = 5 + pRandom.nextInt(15);
//            ItemStack itemstack = EnchantmentHelper.enchantItem(pRandom, new ItemStack(this.itemStack.getItem()), i, false);
//            int j = Math.min(this.baseEmeraldCost + i, 64);
//            ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
//            return new MerchantOffer(itemstack1, itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
//        }
//    }




}
