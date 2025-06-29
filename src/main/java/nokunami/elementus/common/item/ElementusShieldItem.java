package nokunami.elementus.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import nokunami.elementus.common.registry.ModItems.*;
import nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ElementusShieldItem extends ShieldItem {
//    public final int armorModifer;
//    public final float speedModifer;
    private final ModTiers tier;
//    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ElementusShieldItem(/*int armorModifier, float speedModifier, */Properties pProperties, ModTiers modTiers) {
        super(pProperties);
        this.tier = modTiers;
//        this.armorModifer = armorModifier;
//        this.speedModifer = speedModifier;
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("aeafec78-f87e-415b-b321-c750d360488d"), "Tool modifier", this.armorModifer, AttributeModifier.Operation.ADDITION));
//        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("aeafec78-f87e-415b-b321-c750d360488d"), "Tool modifier", this.speedModifer, AttributeModifier.Operation.MULTIPLY_BASE));
//        this.defaultModifiers = builder.build();
    }

//    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
//        return pEquipmentSlot == EquipmentSlot.OFFHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
//    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltip, @NotNull TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }

    public Tier getTier() {
        return this.tier;
    }

    public boolean isValidRepairItem(@NotNull ItemStack pToRepair, @NotNull ItemStack pRepair) {
        if (tier == ModTiers.STEEL) {
            return pRepair.is(ElementusItems.STEEL_INGOT.get());
        } else if (tier == ModTiers.DIARKRITE) {
            return pRepair.is(ElementusItems.DIARKRITE_INGOT.get());
        } else if (tier == ModTiers.ANTHEKTITE) {
            return pRepair.is(ElementusItems.ANTHEKTITE_INGOT.get());
        }
        return this.tier.getRepairIngredient().test(pRepair) || super.isValidRepairItem(pToRepair, pRepair);
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }
}
