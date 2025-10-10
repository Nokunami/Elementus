package net.nokunami.elementus.common.catalystCore.ability;

import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.catalystCore.CoreArmorAttributes;
import net.nokunami.elementus.common.registry.CustomRegistries;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CatalystAbility {
    public final Abilities ability;

    public CatalystAbility(Abilities builder) {
        ability = builder;
    }

    public String getBaseTexture(ItemStack stack) {
        Optional<ItemStack> core = getEquippedCore(stack);
        String base = MODID + ":textures/models/armor/catalyst/catalyst_chestplate.png";
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s_armor.png";
        return core.map(itemStack -> String.format(Locale.ROOT, coreTexture, MODID, CustomRegistries.getCatalystId(itemStack))).orElse(base);
    }

    public String getEmissiveTexture(ItemStack stack) {
//        return ResourceLocation.isValidPath();
        return "null";
    }

    public void tooltip(ItemStack stack, List<Component> tooltip) {
        ChatFormatting tooltipColor = CustomRegistries.getCatalystCore(stack).tooltipColor();
        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".title").withStyle(tooltipColor != null ? tooltipColor : ChatFormatting.WHITE));
        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
    }

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes() {
        return ability.getAttributes() != null ? ability.getAttributes() : Lazy.of(CoreArmorAttributes.baseAttributes()::build);
    }

    public void tick(Level level, Entity entity) {
        tickEffect(level, entity);
    }

    public void tickEffect(Level level, Entity entity) {
        for (Pair<Supplier<MobEffectInstance>, Float> pair : ability.getEffects()) {
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                if (entity instanceof LivingEntity living) living.addEffect(new MobEffectInstance(pair.getFirst().get()));
            }
        }
    }

    /// Doesn't do anything on its own, it's just here to give a second set of effects
    public void tickAltEffect(Level level, Entity entity) {
        for (Pair<Supplier<MobEffectInstance>, Float> pair : ability.getAltEffects()) {
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                if (entity instanceof LivingEntity living) living.addEffect(new MobEffectInstance(pair.getFirst().get()));
            }
        }
    }

    public void postDeathEffect(Level level, Entity entity) {
        for (Pair<Supplier<MobEffectInstance>, Float> pair : ability.getDeathEffects()) {
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                if (entity instanceof LivingEntity living) living.addEffect(new MobEffectInstance(pair.getFirst().get()));
            }
        }
    }

    public void postDamageEvent(LivingDamageEvent event) {
    }

    public void postDeathEvent(LivingDeathEvent event) {
        Entity entity = event.getEntity();
        Level level = entity.level();
        postDeathEffect(level, entity);
    }

}