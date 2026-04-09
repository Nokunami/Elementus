package net.nokunami.elementus.common.item.basic;

import com.google.common.collect.ImmutableMultimap;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.item.IMovcadiaTool;
import net.nokunami.elementus.common.item.ISecondaryBar;
import net.nokunami.elementus.common.registry.ETier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import static net.nokunami.elementus.common.item.EItemUtil.getEssenceBarWidth;
import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;

public class MovcadiaAxeItem extends EAxeItem implements IMovcadiaTool, ISecondaryBar {

    public MovcadiaAxeItem() {
        super(ETier.EnumTiers.MOVCADIA, new Properties().fireResistant());
    }

    @Override
    public ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes(ItemStack stack) {
        double attackDamage = getAttackDamage();
        double attackSpeed = getAttackSpeed();

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        attackDamage *= Math.min(1, (1 - (((float) stack.getDamageValue() / 2) / (float) stack.getMaxDamage())));
        attackSpeed *= Math.max(1, ((float) (stack.getMaxDamage() + stack.getDamageValue() * 0.25) / (float) stack.getMaxDamage()));
        if (getMovcadiaEssence(stack) > 0) attackSpeed /= 1.25F;

        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(Item.BASE_ATTACK_DAMAGE_UUID, "Tool modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(Item.BASE_ATTACK_SPEED_UUID, "Tool modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        return builder;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        drain(stack);
        return super.damageItem(stack, amount, entity, onBroken);
    }

    @Override
    public boolean clickOnStack(@NotNull ItemStack stack, @NotNull ItemStack other, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        return IMovcadiaTool.super.clickOnStack(stack, other, slot, action, player, access);
    }

    @Override public boolean isSecondBarVisible(ItemStack stack) { return getMovcadiaEssence(stack) > 0; }
    @Override public int getSecondBarWidth(ItemStack stack) { return getEssenceBarWidth(stack); }
    @Override public int getSecondBarColor(ItemStack stack) { return 12054986; }
}
