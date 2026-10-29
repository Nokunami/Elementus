package net.nokunami.elementus.common.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public interface IShieldedArmor {

    boolean isShielded(LivingEntity entity, DamageSource source, ItemStack stack, boolean pierced);
}
