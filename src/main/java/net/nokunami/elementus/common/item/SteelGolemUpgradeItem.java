package net.nokunami.elementus.common.item;

import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.entity.living.AstaliteGolem;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

public class SteelGolemUpgradeItem extends Item {
    public final GolemUpgradeProperties properties;
    private static final String TEX_FOLDER = "textures/entity/golem/steel_golem/";
    private final ResourceLocation texture;
    public String identifier;
    public static final DecimalFormat ATTRIBUTE_MODIFIER_FORMAT = Util.make(new DecimalFormat("#.##"), (p_41704_) -> {
        p_41704_.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT));
    });

    public SteelGolemUpgradeItem(String identifier, Item.Properties properties, GolemUpgradeProperties golemUpgradeProperties) {
        super(properties);
        this.texture = modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png");
        this.identifier = identifier;
        this.properties = golemUpgradeProperties;
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        return slot == EquipmentSlot.CHEST ? properties.getAttributes().get() : super.getAttributeModifiers(slot, stack);
//    }

    public Multimap<Attribute, AttributeModifier> getGolemAttributes(EquipmentSlot slot) {
        return properties.getAttributes().get();
    }

    public void onArmorTick(Level level, TamableGolem entity) {
        if (!entity.isChassisBroken()) {
            for (Pair<MobEffectInstance, Float> pair : properties.getEffects()) {
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    entity.addEffect(new MobEffectInstance(pair.getFirst()));
                }
            }
        }
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }

    public boolean isNotPushable() {
        return properties != null && properties.isNotPushable();
    }

    public boolean isFastAttack() {
        return properties != null && properties.isFastAttack();
    }

    public GolemUpgradeProperties getGolemUpgradeProperties() {
        return this.properties;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, components, isAdvanced);
        String armorId = "item.elementus.golem_upgrade." + identifier;
        components.add(Component.translatable(armorId).withStyle(ChatFormatting.GRAY));
        components.add(Component.translatable("item.elementus.golem_upgrade.upgrades").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        bonusDescription(components, "item.elementus.golem_upgrade.pushable", properties.isNotPushable());
        bonusDescription(components, "item.elementus.golem_upgrade.fast_attack", properties.isFastAttack());
        components.add(CommonComponents.space());

        attributeTooltip(stack, components);

//        components.add(Component.translatable("item.elementus.golem_upgrade.modifier_equip").withStyle(ChatFormatting.GRAY));
//        statDescription(components, "attribute.name.generic.armor", properties.getArmor());
//        statDescription(components, "attribute.name.generic.armor_toughness", properties.getToughness());
    }

    public void attributeTooltip(@NotNull ItemStack stack, List<Component> components) {
        for(EquipmentSlot equipmentslot : EquipmentSlot.values()) {
//            Multimap<Attribute, AttributeModifier> multimap = this.getAttributeModifiers(equipmentslot, this);
            Multimap<Attribute, AttributeModifier> multimap = getGolemAttributes(equipmentslot);
            if (!multimap.isEmpty()) {
                components.add(CommonComponents.EMPTY);
                components.add(Component.translatable("item.elementus.golem_upgrade.modifier_equip").withStyle(ChatFormatting.GRAY));

                for(Map.Entry<Attribute, AttributeModifier> entry : multimap.entries()) {
                    AttributeModifier attributemodifier = entry.getValue();
                    double d0 = attributemodifier.getAmount();
                    boolean flag = false;
//                    if (pPlayer != null) {
//                        if (attributemodifier.getId() == Item.BASE_ATTACK_DAMAGE_UUID) {
//                            d0 += pPlayer.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);
//                            d0 += EnchantmentHelper.getDamageBonus(this, MobType.UNDEFINED);
//                            flag = true;
//                        } else if (attributemodifier.getId() == Item.BASE_ATTACK_SPEED_UUID) {
//                            d0 += pPlayer.getAttributeBaseValue(Attributes.ATTACK_SPEED);
//                            flag = true;
//                        }
//                    }

                    double d1;
                    if (attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_BASE && attributemodifier.getOperation() != AttributeModifier.Operation.MULTIPLY_TOTAL) {
                        if (entry.getKey().equals(Attributes.KNOCKBACK_RESISTANCE)) {
                            d1 = d0 * 10.0D;
                        } else {
                            d1 = d0;
                        }
                    } else {
                        d1 = d0 * 100.0D;
                    }

                    /*if (flag) {
                        components.add(CommonComponents.space().append(Component.translatable("attribute.modifier.equals." + attributemodifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId()))).withStyle(ChatFormatting.DARK_GREEN));
                    } else*/ if (d0 > 0.0D) {
                        components.add(Component.translatable("attribute.modifier.plus." + attributemodifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId())).withStyle(ChatFormatting.BLUE));
                    } else if (d0 < 0.0D) {
                        d1 *= -1.0D;
                        components.add(Component.translatable("attribute.modifier.take." + attributemodifier.getOperation().toValue(), ATTRIBUTE_MODIFIER_FORMAT.format(d1), Component.translatable(entry.getKey().getDescriptionId())).withStyle(ChatFormatting.RED));
                    }
                }
            }
        }
    }

    public void statDescription(List<Component> components, String translationKey, double value) {
        String adjective = value > 0 ? "attribute.modifier.plus.0" : "attribute.modifier.take.0";
        ChatFormatting formatting = value > 0 ? ChatFormatting.BLUE : ChatFormatting.RED;
        if (value != 0) {
            components.add(Component.translatable(adjective, Math.round((value/10)*10), Component.translatable(translationKey)).withStyle(formatting));
        }
    }

    public void bonusDescription(List<Component> components, String translationKey, boolean value) {
        if (value) {
            components.add(CommonComponents.space().append(Component.translatable(translationKey)).withStyle(ChatFormatting.YELLOW));
        }
    }

    @Override
    public int getMaxStackSize(ItemStack stack) {
        return 1;
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "item." + MODID + ".golem_upgrade";
    }
}
