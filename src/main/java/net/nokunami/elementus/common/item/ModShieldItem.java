package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.nokunami.elementus.common.registry.ModItems.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class ModShieldItem extends ShieldItem {
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
//    private int diarkriteCooldown;
//    private int anthektiteCooldown;

    public ModShieldItem(Item.Properties properties, float armor, float toughness) {
        super(properties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("e3aab972-f751-411f-9e8f-919244b4862a"), "Weapon modifier", armor, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("f971eb6a-5225-4cfe-bf06-36ad543a5102"), "Weapon modifier", toughness, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSE_ITEM_BEHAVIOR);
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(@NotNull ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return super.canDisableShield(stack, shield, entity, attacker);
    }

//    @Override
//    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
//        super.inventoryTick(itemStack, level, entity, slotId, isSelected);
//        --this.diarkriteCooldown;
//        --this.anthektiteCooldown;
//        if (entity instanceof Player player) {
//            if (player.getCooldowns().isOnCooldown(ElementusItems.DIARKRITE_SHIELD.get()) && diarkriteCooldown < 1) {
//                player.getCooldowns().addCooldown(ElementusItems.DIARKRITE_SHIELD.get(), 150);
//                this.diarkriteCooldown = 150;
//            }
//            if (player.getCooldowns().isOnCooldown(ElementusItems.ANTHEKTITE_SHIELD.get()) && anthektiteCooldown < 1) {
//                player.getCooldowns().addCooldown(ElementusItems.ANTHEKTITE_SHIELD.get(), 50);
//                this.anthektiteCooldown = 50;
//            }
//        }
//    }

    @Override
    public int getDefaultTooltipHideFlags(@NotNull ItemStack stack) {
        return super.getDefaultTooltipHideFlags(stack);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        return (pEquipmentSlot == EquipmentSlot.MAINHAND || pEquipmentSlot == EquipmentSlot.OFFHAND) ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }
}
