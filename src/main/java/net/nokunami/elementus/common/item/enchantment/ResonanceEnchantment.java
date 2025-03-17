package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ResonanceEnchantment extends Enchantment {
    public ResonanceEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public boolean canEnchant(@NotNull ItemStack pStack) {
        return pStack.is(ModItems.ElementusItems.DIARKRITE_CHARGE_BLADE.get());
    }
}