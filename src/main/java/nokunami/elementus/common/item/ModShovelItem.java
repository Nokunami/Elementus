package nokunami.elementus.common.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.common.item.EItemUtil.cmdTier;

public class ModShovelItem extends ShovelItem {
    public ModShovelItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

//    @Override
//    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
//        if (cmdTier(this.getTier())) {
//            return Rarity.EPIC;
//        }
//        return super.getRarity(stack);
//    }

    @Override
    public boolean canBeHurtBy(ItemStack stack, DamageSource source) {
        if (cmdTier(this.getTier())) return false;
        return super.canBeHurtBy(stack, source);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        if (cmdTier(this.getTier())) {
            return this.getMaxStackSize(stack) == 1;
        }
        return super.isEnchantable(stack);
    }
}
