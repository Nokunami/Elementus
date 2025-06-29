package nokunami.elementus.common.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.DamageEnchantment;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import nokunami.elementus.common.config.ModConfig;
import nl.sniffiandros.sniffsweapons.item.GreatPickaxeItem;
import nl.sniffiandros.sniffsweapons.item.NaginataItem;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.ModChecker.sniffsWeapons;

public class ArcaneSharpnessEnchantment extends Enchantment {

    public ArcaneSharpnessEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public float getDamageBonus(int level, MobType mobType, ItemStack enchantedItem) {
        return (float) (enchantedItem.getEnchantmentValue()* ModConfig.COMMON.arcaneSharpnessPercent.get());
    }

    public boolean isTreasureOnly() {
        return ModConfig.COMMON.arcaneSharpnessTreasure.get();
    }

    public boolean checkCompatibility(@NotNull Enchantment pEnch) {
        if (ModConfig.COMMON.arcaneSharpnessIncompatibility.get()) {
            return !(pEnch instanceof DamageEnchantment);
        } return false;
    }

    public boolean canEnchant(@NotNull ItemStack stack) {
        Item item = stack.getItem();
        if (sniffsWeapons) return item instanceof AxeItem || super.canEnchant(stack)|| item instanceof GreatPickaxeItem || item instanceof NaginataItem;
        return item instanceof AxeItem || super.canEnchant(stack);
    }
}