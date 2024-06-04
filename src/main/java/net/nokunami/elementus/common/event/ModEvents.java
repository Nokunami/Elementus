package net.nokunami.elementus.common.event;

import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;

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


//    @SuppressWarnings("unused")
//    @SubscribeEvent
//    public static void onEnderManAngerEvent(final EnderManAngerEvent event)
//    {
//        Player player = event.getPlayer(); // Gets the player (target)
//        EnderMan enderMan = event.getEntity(); // Gets the enderman
//
//        // return early if player is in creative mode
//        if (player.isCreative())
//            return;
//
//        for (ItemStack stack : player.getArmorSlots())
//        {
//            Item item = stack.getItem();
//            if ((item instanceof ANArmorItem && ((ANArmorItem) item).pacifiesEndermen()) || (item instanceof ANIAdvancedHooks && ((IAdvancedHooks) item).pacifyEndermen(stack)))
//            {
//                event.setCanceled(true); // Cancels the enderman anger if conditions are met
//            }
//        }
//    }
//
//    @SuppressWarnings("unused")
//    @SubscribeEvent
//    public static void onLivingChangeTargetEvent(final LivingChangeTargetEvent event)
//    {
//        LivingEntity target = event.getOriginalTarget(); // Gets the target (player)
//        LivingEntity attacker = event.getEntity(); // Gets the attacker
//
//        if (target == null)
//            return;
//
//        // return early if the attacker was angered by the target (player)
//        if (attacker.getLastHurtByMob() == target)
//            return;
//
//        if (attacker instanceof Phantom phantom)
//        {
//            for (ItemStack stack : target.getArmorSlots())
//            {
//                Item item = stack.getItem();
//                if ((item instanceof ANArmorItem && ((ANArmorItem) item).pacifiesPhantoms()) || (item instanceof IAdvancedHooks && ((IAdvancedHooks) item).pacifyPhantoms(stack)))
//                {
//                    event.setNewTarget(null); // Set target to null to allow the attacker to pick a new target
//                }
//            }
//        }
//
//        if (attacker instanceof Piglin piglin)
//        {
//            for (ItemStack stack : target.getArmorSlots())
//            {
//                Item item = stack.getItem();
//                if ((item instanceof ANArmorItem && ((ANArmorItem) item).pacifiesPiglins()) || (item instanceof IAdvancedHooks && ((IAdvancedHooks) item).pacifyPiglins(stack)))
//                {
//                    event.setCanceled(true); // TODO figure out how to reliably use event
//                }
//            }
//        }
//    }
}
