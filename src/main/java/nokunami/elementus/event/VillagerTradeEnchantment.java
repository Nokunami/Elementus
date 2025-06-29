package nokunami.elementus.event;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class VillagerTradeEnchantment {

    public static ListTag getEnchantments(ItemStack pEnchantedBookStack) {
        CompoundTag compoundtag = pEnchantedBookStack.getTag();
        return compoundtag != null ? compoundtag.getList("Enchantments", 10) : new ListTag();
    }

    public static void addEnchantment(ItemStack pStack, EnchantmentInstance pInstance) {
        ListTag listtag = getEnchantments(pStack);
        boolean flag = true;
        ResourceLocation resourcelocation = EnchantmentHelper.getEnchantmentId(pInstance.enchantment);

        for(int i = 0; i < listtag.size(); ++i) {
            CompoundTag compoundtag = listtag.getCompound(i);
            ResourceLocation resourcelocation1 = EnchantmentHelper.getEnchantmentId(compoundtag);
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                if (EnchantmentHelper.getEnchantmentLevel(compoundtag) < pInstance.level) {
                    EnchantmentHelper.setEnchantmentLevel(compoundtag, pInstance.level);
                }

                flag = false;
                break;
            }
        }

        if (flag) {
            listtag.add(EnchantmentHelper.storeEnchantment(resourcelocation, pInstance.level));
        }

        pStack.getOrCreateTag().put("Enchantments", listtag);
    }

    public static ItemStack createForEnchantment(Item item, Enchantment enchantment, int level) {
        ItemStack itemstack = new ItemStack(item);
        addEnchantment(itemstack, new EnchantmentInstance(enchantment, level));
        return itemstack;
    }

    public static ItemStack createForEnchantment(Item item, int level, RandomSource randomSource, boolean b) {
        ItemStack itemstack = new ItemStack(item);
        EnchantmentHelper.enchantItem(randomSource, itemstack, level, b);
        return itemstack;
    }
}
