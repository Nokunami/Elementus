package net.nokunami.elementus.mixin;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ThornsEnchantment;
import net.nokunami.elementus.common.registry.ETiers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static net.minecraft.world.item.enchantment.ThornsEnchantment.shouldHit;

@Mixin(ThornsEnchantment.class)
public class ThornsEnchantmentMixin {

    @Inject(method = "doPostHurt", at = @At("HEAD"), cancellable = true)
    private void E$doPostHurt(LivingEntity user, Entity attacker, int level, CallbackInfo ci) {
        RandomSource randomsource = user.getRandom();
        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Enchantments.THORNS, user);
        if (shouldHit(level, randomsource)) {
            if (attacker != null) attacker.hurt(user.damageSources().thorns(user), E$getDamage(level, randomsource));

            if (entry != null && !E$getDiarkriteItems(entry.getValue())) entry.getValue().hurtAndBreak(2, user, entity -> entity.broadcastBreakEvent(entry.getKey()));
        }
    }

    @Unique
    private static boolean E$getDiarkriteItems(ItemStack stack) {
        return stack.getItem() instanceof TieredItem tieredItem && tieredItem.getTier().equals(ETiers.DIARKRITE);
    }

    @Unique
    private static int E$getDamage(int pLevel, RandomSource pRandom) {
        return pLevel > 10 ? pLevel - 10 : 1 + pRandom.nextInt(4);
    }
}