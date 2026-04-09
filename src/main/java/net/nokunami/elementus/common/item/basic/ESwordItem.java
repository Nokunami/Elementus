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
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.config.configSets.item.ToolSetConfig;
import net.nokunami.elementus.common.item.EItemUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.nokunami.elementus.common.config.CommonConfig.getToolConfig;

public class ESwordItem extends SwordItem implements IConfiguredItem {

    public ESwordItem(Tier tier, Properties properties) { super(tier, 0, 0, properties); }

//    @Override
//    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
//        if (pTarget.hasEffect(MobEffects.MOVEMENT_SLOWDOWN)) {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1, false, false));
//        } else {
//            pTarget.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0, false, false));
//        }
//        return super.hurtEnemy(pStack, pTarget, pAttacker);
//    }

//    @Override
//    public @NotNull Tier getTier() {
//        return ETier.EnumTiers.getConfiguredTier(getToolConfig(super.getTier()).tier, super.getTier());
//    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == EquipmentSlot.MAINHAND ? attributes(stack).build() : super.getAttributeModifiers(slot, stack);
    }

    @Override public ToolSetConfig config() { return getToolConfig(getTier()); }

    @Override
    public ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes(ItemStack stack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDamage(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", getAttackSpeed(), AttributeModifier.Operation.ADDITION));
        return builder;
    }

    @Override public int getMaxDamage(ItemStack stack) { return config().tier.durability.get(); }
    @Override public double getAttackSpeed() { return config().sword.attackSpeed.get() + config().materialSpeed.get(); }
    @Override public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return EItemUtil.toolMiningSpeed(super.getDestroySpeed(stack, state), stack, state);
    }
    @Override public float getDamage() { return config().sword.attackDamage.get().floatValue() + config().tier.damage.get().floatValue(); }
    @Override public int getEnchantmentValue(ItemStack stack) { return config().tier.enchantability.get(); }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        EItemUtil.tooltip(stack, tooltip, getTier());
    }

    @Override
    public boolean isCorrectToolForDrops(ItemStack stack, BlockState state) {
        return super.isCorrectToolForDrops(stack, state);
    }
}
