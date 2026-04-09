package net.nokunami.elementus.common.catalystCore.core;

import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;

public class WayfinderCatalystCore extends CatalystCore {

    public WayfinderCatalystCore(CatalystCoreAttributes coreAttributes) {
        super(() -> new ItemStack(Items.COMPASS), ChatFormatting.LIGHT_PURPLE, coreAttributes);
    }

//    @Override
//    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
//        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s.png";
//        if (MobUtil.healthPercent(entity, 0.5F)) return String.format(coreTexture, EID, "ignitium_soul");
//
//        if (entity instanceof LivingEntity living) {
//            var core = CatalystItemUtil.getEquippedCore(living.getItemBySlot(EquipmentSlot.CHEST));
//            if (core.isPresent()) {
//                if (core.get().getItem() instanceof CompassItem) {
//                    CompoundTag tag = core.get().getTag();
//                    if (tag != null && tag.contains("LodestonePos") && tag.contains("LodestoneDimension")) {
//
//                    }
//                }
//            }
//        }
//        return String.format(coreTexture, EID, "ignitium");
//    }

    @Override
    public void equipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(SoundEvents.LODESTONE_COMPASS_LOCK, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    @Override
    public void unequipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(SoundEvents.LODESTONE_COMPASS_LOCK, 0.75F, 0.2F + entity.level().getRandom().nextFloat() * 0.3F);
    }
}