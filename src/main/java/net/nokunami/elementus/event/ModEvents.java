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
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;

import java.util.List;

@Mod.EventBusSubscriber(modid = Elementus.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            EnchantedBookItem.createForEnchantment(new EnchantmentInstance(Enchantments.ALL_DAMAGE_PROTECTION,1));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,12),
                    new ItemStack(ElementusItems.STEEL_LEGGINGS.get())
                    ,3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    new ItemStack(ElementusItems.STEEL_BOOTS.get())
                    ,3, 15, 0.2F));

            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD,7),
                    new ItemStack(ElementusItems.STEEL_HELMET.get()),
                    3, 30, 0.2F));

//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,30),
//                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
//                            1),3, 50, 0.2F));
        }

        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ElementusItems.STEEL_AXE.get(),
                            1), 3, 15, 0.2F));

            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 7),
                    new ItemStack(ElementusItems.STEEL_SWORD.get(),
                            1), 3, 30, 0.2F));

//            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,30),
//                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
//                            1),3, 50, 0.2F));
        }

        if (event.getType() == VillagerProfession.TOOLSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ElementusItems.STEEL_HOE.get(),
                            1), 3, 10, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 10),
                    new ItemStack(ElementusItems.STEEL_AXE.get(),
                            1), 3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 4),
                    new ItemStack(ElementusItems.STEEL_SHOVEL.get(),
                            1), 3, 15, 0.2F));

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 11),
                    new ItemStack(ElementusItems.STEEL_PICKAXE.get(),
                            1), 3, 30, 0.2F));

//            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
//                    new ItemStack(Items.EMERALD,30),
//                    new ItemStack(ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get(),
//                            1),3, 50, 0.2F));
        }
    }

}
