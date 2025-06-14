package net.nokunami.elementus.common.item;

import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

public class SteelGolemUpgradeItem extends Item {
    public final GolemUpgradeProperties properties;
    private static final String TEX_FOLDER = "textures/entity/golem/steel_golem/";
    private final ResourceLocation texture;
    public String identifier;

    public void onArmorTick(ItemStack stack, Level level, SteelGolem entity) {
        if (!entity.isChassisBroken()) {
            for (Pair<MobEffectInstance, Float> pair : properties.getEffects()) {
                if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                    entity.addEffect(new MobEffectInstance(pair.getFirst()));
                }
            }
        }
    }

    public SteelGolemUpgradeItem(String identifier, Item.Properties properties, GolemUpgradeProperties golemUpgradeProperties) {
        super(properties);
        this.texture = modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png");
        this.identifier = identifier;
        this.properties = golemUpgradeProperties;
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
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        String armorId = "item." + MODID + ".golem_upgrade." + identifier;
        components.add(Component.translatable(armorId).withStyle(ChatFormatting.GRAY));
        components.add(Component.translatable("item." + MODID + ".golem_upgrade.upgrades").withStyle(ChatFormatting.BOLD).withStyle(ChatFormatting.GOLD));
        bonusDescription(components, "item." + MODID + ".golem_upgrade.pushable", properties.isNotPushable());
        bonusDescription(components, "item." + MODID + ".golem_upgrade.fast_attack", properties.isFastAttack());
        components.add(CommonComponents.space());
        components.add(Component.translatable("item." + MODID + ".golem_upgrade.modifier_equip").withStyle(ChatFormatting.GRAY));
        statDescription(components, "attribute.name.generic.armor", properties.getArmor());
        statDescription(components, "attribute.name.generic.armor_toughness", properties.getToughness());
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
