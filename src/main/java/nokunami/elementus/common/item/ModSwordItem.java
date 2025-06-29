package nokunami.elementus.common.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.jetbrains.annotations.NotNull;

import static nokunami.elementus.common.item.EItemUtil.*;

public class ModSwordItem extends SwordItem {
    public ModSwordItem(Tier tier, Properties properties) {
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
    public boolean canBeHurtBy(@NotNull ItemStack stack, @NotNull DamageSource source) {
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

//    @Override
//    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
//        if (pTarget.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false));
//        } else {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0, false, false));
//        }
//        return super.hurtEnemy(pStack, pTarget, pAttacker);
//    }
}
