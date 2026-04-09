package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.item.EItemUtil.setMovcadiaEssence;

public interface IMovcadiaTool {
    UUID DAMAGE_UUID = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    UUID SPEED_UUID = UUID.fromString("FA233E1C-4180-4865-B01B-BCCE9785ACA3");

    default Multimap<Attribute, AttributeModifier> movcadiaAttributesMap(ItemStack stack, float attackDamage, float attackSpeed) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        attackDamage *= Math.min(1, (1 - (((float) stack.getDamageValue() / 2) / ((float) stack.getMaxDamage()))));
        attackSpeed *= ((((float) stack.getMaxDamage() + stack.getDamageValue()) / ((float) stack.getMaxDamage())));
        if (getMovcadiaEssence(stack) > 0) attackSpeed /= 1.25F;

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(DAMAGE_UUID, "Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        return builder.build();
    }

    default boolean clickOnStack(ItemStack stack, ItemStack otherStack, Slot slot, ClickAction action, Player player, SlotAccess access) {
        if (getMovcadiaEssence(stack) < 1 && otherStack.getItem() == EItems.MOVCADIA_ESSENCE.get() && action.equals(ClickAction.SECONDARY)) {
            EItemUtil.movcadiaClickAction(stack, otherStack, player);
            return true;
        }
        return false;
    }

    default void drain(ItemStack stack) {
        if (getMovcadiaEssence(stack) > 0) setMovcadiaEssence(stack, getMovcadiaEssence(stack) - 1);
    }

    default float getMovcadiaDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state, float defaultSpeed) {
        return EItemUtil.toolMiningSpeed(defaultSpeed, stack, state);
    }
}
