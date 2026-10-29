package net.nokunami.elementus.common.item.enchantment;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.item.unique.ChargeBladeAttributes;

import java.util.List;

public interface IChargeBladeEnchantment {

    void getDescription(List<Component> tooltip, ItemStack stack);

    default float bonusChargeBladeDamage() { return 0; }

    default void postUseEvent(LivingEntity entity) { }

    default ChargeBladeAttributes attributes() {
        return new ChargeBladeAttributes();
    }
}
