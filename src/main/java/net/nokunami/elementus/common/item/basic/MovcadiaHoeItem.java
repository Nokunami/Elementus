package net.nokunami.elementus.common.item.basic;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.item.IMovcadiaTool;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.nokunami.elementus.common.item.EItemUtil.setMovcadiaEssence;

public class MovcadiaHoeItem extends EHoeItem implements IMovcadiaTool {

    public MovcadiaHoeItem(Tier tier, int attackDamage, float attackSpeed, Properties properties) {
        super(tier, attackDamage, attackSpeed, properties);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        drain(stack);
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack other, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        return IMovcadiaTool.super.overrideOtherStackedOnMe(stack, other, slot, action, player, access);
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        return getMovcadiaDestroySpeed(stack, state, super.getDestroySpeed(stack, state));
    }
}
