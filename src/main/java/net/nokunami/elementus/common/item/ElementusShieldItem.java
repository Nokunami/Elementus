package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.ModItems.*;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

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
            return pRepair.is(Etags.Items.INGOTS_STEEL);
        } else if (tier == ModTiers.DIARKRITE) {
            return pRepair.is(Etags.Items.INGOTS_DIARKRITE);
        } else if (tier == ModTiers.ANTHEKTITE) {
            return pRepair.is(Etags.Items.INGOTS_ANTHEKTITE);
        }
        return this.tier.getRepairIngredient().test(pRepair) || super.isValidRepairItem(pToRepair, pRepair);
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_IRON;
    }

    public static void setDiarkriteShieldCooldown(Player playerEntity, LivingEntity livingEntity){
        float disableChance = 0.25F + (float) EnchantmentHelper.getBlockEfficiency(livingEntity) * 0.05F;
        if (livingEntity.getRandom().nextFloat() < disableChance) {
            playerEntity.getCooldowns().addCooldown(ElementusItems.DIARKRITE_SHIELD.get(), 150);
            livingEntity.level().broadcastEntityEvent(playerEntity, (byte)30);
        }
    }

    public static void setAnthektiteShieldCooldown(Player playerEntity, LivingEntity livingEntity){
        float disableChance = 0.25F + (float) EnchantmentHelper.getBlockEfficiency(livingEntity) * 0.05F;
        if (livingEntity.getRandom().nextFloat() < disableChance) {
            playerEntity.getCooldowns().addCooldown(ElementusItems.ANTHEKTITE_SHIELD.get(), 50);
            livingEntity.level().broadcastEntityEvent(playerEntity, (byte)30);
        }
    }
}
