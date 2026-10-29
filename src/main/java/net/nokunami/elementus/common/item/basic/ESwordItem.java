package net.nokunami.elementus.common.item.basic;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.api.util.EItemUtil;
import net.nokunami.elementus.common.item.IETier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ESwordItem extends SwordItem {
    private final IETier eTier;
    private final double attackDamage;
    private final double attackSpeed;

    public ESwordItem(IETier tier, double damage, double speed, Properties properties) {
        super(tier, 0, 0, properties);
        eTier = tier;
        attackDamage = damage + tier.getAttackDamageBonus();
        attackSpeed = speed;
    }

    @Override public @NotNull IETier getTier() { return eTier; }

    //    @Override
//    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
//        if (pTarget.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false));
//        } else {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0, false, false));
//        }
//        return super.hurtEnemy(pStack, pTarget, pAttacker);
//    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? attributes(stack).build() : super.getAttributeModifiers(slot, stack);
    }

    public ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes(ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
        return builder;
    }

    @Override public float getDamage() { return (float) attackDamage; }
    public double getAttackSpeed() { return attackSpeed; }

    @Override public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return EItemUtil.toolMiningSpeed(super.getDestroySpeed(stack, state), stack, state);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        EItemUtil.tooltip(stack, tooltip, getTier());
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return super.isCorrectToolForDrops(stack, state);
    }
}