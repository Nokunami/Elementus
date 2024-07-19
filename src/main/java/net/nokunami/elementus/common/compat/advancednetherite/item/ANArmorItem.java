package net.nokunami.elementus.common.compat.advancednetherite.item;

import com.autovw.advancednetherite.api.annotation.Internal;
import com.autovw.advancednetherite.common.item.AdvancedArmorItem;
import com.autovw.advancednetherite.config.ConfigHelper;
import com.autovw.advancednetherite.core.util.ModTooltips;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ModClientEvents;
import net.nokunami.elementus.common.compat.advancednetherite.util.ANUtil;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class ANArmorItem extends AdvancedArmorItem {
    protected final ModArmorMaterials material;

    public ANArmorItem(ModArmorMaterials material, Type armorType, Properties properties) {
        super(material, armorType, properties);
        this.material = material;
    }

    @Override
    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        if (pEquipmentSlot == this.type.getSlot()) {
            return this.material.getSlotToAttributeMap().get(pEquipmentSlot);
        } else {
            return ImmutableMultimap.of();
        }
    }

    public boolean pacifiesEndermen() {
        if (this.material == ModArmorMaterials.DIARKRITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_DIAMOND) {
            return ConfigHelper.get().getCommon().getArmor().isDiamondEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldEndermanPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldEndermanPassiveArmor();
        } else {
            return this.material == ModArmorMaterials.ANTHEKTITE_DIAMOND ? ConfigHelper.get().getCommon().getArmor().isDiamondEndermanPassiveArmor() : false;
        }
    }

    public boolean pacifiesPiglins() {
        if (this.material == ModArmorMaterials.DIARKRITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_DIAMOND) {
            return ConfigHelper.get().getCommon().getArmor().isDiamondPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldPiglinPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldPiglinPassiveArmor();
        } else {
            return this.material == ModArmorMaterials.ANTHEKTITE_DIAMOND ? ConfigHelper.get().getCommon().getArmor().isDiamondPiglinPassiveArmor() : false;
        }
    }

    public boolean pacifiesPhantoms() {
        if (this.material == ModArmorMaterials.DIARKRITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.DIARKRITE_DIAMOND) {
            return ConfigHelper.get().getCommon().getArmor().isDiamondPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_IRON) {
            return ConfigHelper.get().getCommon().getArmor().isIronPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_GOLD) {
            return ConfigHelper.get().getCommon().getArmor().isGoldPhantomPassiveArmor();
        } else if (this.material == ModArmorMaterials.ANTHEKTITE_EMERALD) {
            return ConfigHelper.get().getCommon().getArmor().isEmeraldPhantomPassiveArmor();
        } else {
            return this.material == ModArmorMaterials.ANTHEKTITE_DIAMOND ? ConfigHelper.get().getCommon().getArmor().isDiamondPhantomPassiveArmor() : false;
        }

    }

    public boolean isFireResistant() {
        return true;
    }

    public void addTooltips(ItemStack stack, Level level, List<Component> tooltips, TooltipFlag flag) {
    }

    public ChatFormatting customDurabilityBarColor(ItemStack stack) {
        return null;
    }

    @Internal
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (ConfigHelper.get().getClient().showTooltips()) {
            if (Screen.hasShiftDown()) {
                if (this.pacifiesEndermen()) {
                    tooltip.add(ModTooltips.ENDERMAN_PASSIVE_TOOLTIP);
                }

                if (this.pacifiesPiglins()) {
                    tooltip.add(ModTooltips.PIGLIN_PASSIVE_TOOLTIP);
                }

                if (this.pacifiesPhantoms()) {
                    tooltip.add(ModTooltips.PHANTOM_PASSIVE_TOOLTIP);
                }
            } else if (this.pacifiesEndermen() || this.pacifiesPiglins() || this.pacifiesPhantoms()) {
                tooltip.add(ModTooltips.SHIFT_KEY_TOOLTIP);
            }

            this.addTooltips(stack, world, tooltip, flag);
        }

    }

    @Internal
    public int getBarColor(ItemStack stack) {
        int originalColor = super.getBarColor(stack);
        return this.customDurabilityBarColor(stack)
                != null && ConfigHelper.get().getClient().matchingDurabilityBars()
                ? (Integer)Objects.requireNonNull(
                this.customDurabilityBarColor(stack).getColor()) : ANUtil.getDurabilityBarColor(originalColor, stack);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ModClientEvents.PROXY.getArmorRenderProperties());
    }


    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean chestplate = slot == EquipmentSlot.CHEST;
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/advancednetherite/%s_layer_" + (helmet ? "1.png" : chestplate ? "2.png" : leggings ? "3.png" : "4.png"), domain, texture);
    }
}
