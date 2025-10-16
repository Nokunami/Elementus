package net.nokunami.elementus.common.catalystCore.core;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.CatalystAbilities;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.CompatRegistryObjectGetter.CatalysmItems.IGNITIUM_INGOT;

public class IgnitiumCatalystCore extends CatalystCore {

    public IgnitiumCatalystCore() {
        super(() -> new ItemStack(IGNITIUM_INGOT.get()), ChatFormatting.GOLD, CatalystArmorAttributes.baseAttributes(), new CatalystCoreAttributes.Builder()
                .passiveAbility(CatalystAbilities.IGNITIUM).build());
    }

    @Override
    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s.png";
        if (MobUtil.healthPercent(entity, 0.5F)) return String.format(coreTexture, MODID, "ignitium_soul");
        return String.format(coreTexture, MODID, "ignitium");
    }

    @Override
    public void equipSound(Entity entity, ItemStack stack) {
        MobUtil.playEntitySound(entity, SoundEvents.ALLAY_AMBIENT_WITH_ITEM, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}