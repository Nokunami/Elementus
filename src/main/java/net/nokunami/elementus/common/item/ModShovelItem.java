package net.nokunami.elementus.common.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.*;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.common.item.ElementusItemUtil.cmdTier;

public class ModShovelItem extends ShovelItem {
    public ModShovelItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
        if (cmdTier(this.getTier())) {
            return Rarity.EPIC;
        }
        return super.getRarity(stack);
    }

    @Override
    public boolean canBeHurtBy(@NotNull DamageSource pDamageSource) {
        if (cmdTier(this.getTier())) {
            return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
        }
        return super.canBeHurtBy(pDamageSource);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        if (cmdTier(this.getTier())) {
            return this.getMaxStackSize(stack) == 1;
        }
        return super.isEnchantable(stack);
    }
}
