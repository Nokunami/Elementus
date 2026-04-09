package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.Lazy;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.entity.MobUtil;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.Catalysm.IGNITIUM_INGOT;

public class IgnitiumCatalystCore extends CatalystCore {

    public IgnitiumCatalystCore(CatalystCoreAttributes coreAttributes) {
        super(() -> new ItemStack(IGNITIUM_INGOT.get()), ChatFormatting.GOLD, coreAttributes);
    }

    @Override
    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s.png";
        if (MobUtil.healthPercent(entity, 0.5F)) return String.format(coreTexture, EID, "ignitium_soul");
        return String.format(coreTexture, EID, "ignitium");
    }

    @Override
    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes(EquipmentSlot slot, ItemStack stack) {
        return Lazy.of(CatalystArmorAttributes.ignitium(stack)::build);
    }

    @Override
    public void onCoreRemove(ItemStack stack) {
        if (stack.getOrCreateTag().contains("soul_fired")) stack.getOrCreateTag().remove("soul_fired");
        super.onCoreRemove(stack);
    }

    @Override
    public void tick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        setSoulFired(stack, MobUtil.healthPercent(entity, 0.5F));
        super.tick(stack, level, entity, slotId, isSelected);
    }

    public static boolean isSoulFired(ItemStack stack) { return stack.getOrCreateTag().getBoolean("soul_fired"); }
    public static void setSoulFired(ItemStack stack, boolean b) { stack.getOrCreateTag().putBoolean("soul_fired", b); }

    //    @Override
//    public void equipSound(Player entity, ItemStack stack) {
////        MobUtil.playEntitySound(entity, SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
//        entity.playSound(SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
//    }
}