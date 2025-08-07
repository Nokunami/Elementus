package net.nokunami.elementus.common.item.basic;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.item.EItemUtil;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Consumer;

import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.item.EItemUtil.setMovcadiaEssence;

public class ModSwordItem extends SwordItem {
    public ModSwordItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (this.getTier().equals(ModTiers.MOVCADIA) && getMovcadiaEssence(stack) < 1 && otherStack.getItem() == ModItems.ElementusItems.MOVCADIA_ESSENCE.get() && action.equals(ClickAction.SECONDARY)) {
            EItemUtil.movcadiaClickAction(stack, otherStack, player);
            return true;
        }
        return super.overrideOtherStackedOnMe(stack, otherStack, slot, action, player, access);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (getMovcadiaEssence(stack) > 0) setMovcadiaEssence(stack, getMovcadiaEssence(stack) - 1);
        return super.damageItem(stack, amount, entity, onBroken);
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

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        EItemUtil.tooltip(stack, tooltip, getTier());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        if (ModConfig.COMMON.diarkriteEfficiency.get()) {
            float originalSpeed = super.getDestroySpeed(stack, state);
            return EItemUtil.toolMiningSpeed(originalSpeed, stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }
}
