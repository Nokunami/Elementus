package net.nokunami.elementus.common.catalystCore.core;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;

public class WayfinderCatalystCore extends CatalystCore {

    public WayfinderCatalystCore(CatalystCoreAttributes coreAttributes) {
        super(() -> new ItemStack(Items.COMPASS), ChatFormatting.LIGHT_PURPLE, CatalystArmorAttributes.wayfinder(), coreAttributes);
    }

    @Override
    public void equipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(SoundEvents.LODESTONE_COMPASS_LOCK, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void unequipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(SoundEvents.LODESTONE_COMPASS_LOCK, 0.75F, 0.2F + entity.level().getRandom().nextFloat() * 0.3F);
    }
}