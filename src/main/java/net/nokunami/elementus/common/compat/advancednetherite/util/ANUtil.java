package net.nokunami.elementus.common.compat.advancednetherite.util;

import com.autovw.advancednetherite.config.ConfigHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModTiers;

public class ANUtil {

    public static int getDurabilityBarColor(int originalColor, ItemStack stack) {
        int newColor = originalColor;
        if ( ConfigHelper.get().getClient().matchingDurabilityBars()) {
            Item var4 = stack.getItem();
            if (var4 instanceof DiggerItem) {
                DiggerItem item = (DiggerItem)var4;
                Tier tier = item.getTier();
                if (tier == ModTiers.DIARKRITE_IRON) {
                    newColor = ChatFormatting.GRAY.getColor();
                }

                if (tier == ModTiers.DIARKRITE_GOLD) {
                    newColor = ChatFormatting.GOLD.getColor();
                }

                if (tier == ModTiers.DIARKRITE_EMERALD) {
                    newColor = ChatFormatting.DARK_GREEN.getColor();
                }

                if (tier == ModTiers.DIARKRITE_DIAMOND) {
                    newColor = ChatFormatting.AQUA.getColor();
                }


                if (tier == ModTiers.ANTHEKTITE_IRON) {
                    newColor = ChatFormatting.GRAY.getColor();
                }

                if (tier == ModTiers.ANTHEKTITE_GOLD) {
                    newColor = ChatFormatting.GOLD.getColor();
                }

                if (tier == ModTiers.ANTHEKTITE_EMERALD) {
                    newColor = ChatFormatting.DARK_GREEN.getColor();
                }

                if (tier == ModTiers.ANTHEKTITE_DIAMOND) {
                    newColor = ChatFormatting.AQUA.getColor();
                }
            }

            var4 = stack.getItem();
            if (var4 instanceof ArmorItem) {
                ArmorItem item = (ArmorItem)var4;
                ArmorMaterial material = item.getMaterial();
                if (material == ModArmorMaterials.DIARKRITE_IRON) {
                    newColor = ChatFormatting.GRAY.getColor();
                }

                if (material == ModArmorMaterials.DIARKRITE_GOLD) {
                    newColor = ChatFormatting.GOLD.getColor();
                }

                if (material == ModArmorMaterials.DIARKRITE_EMERALD) {
                    newColor = ChatFormatting.DARK_GREEN.getColor();
                }

                if (material == ModArmorMaterials.DIARKRITE_DIAMOND) {
                    newColor = ChatFormatting.AQUA.getColor();
                }


                if (material == ModArmorMaterials.ANTHEKTITE_IRON) {
                    newColor = ChatFormatting.GRAY.getColor();
                }

                if (material == ModArmorMaterials.ANTHEKTITE_GOLD) {
                    newColor = ChatFormatting.GOLD.getColor();
                }

                if (material == ModArmorMaterials.ANTHEKTITE_EMERALD) {
                    newColor = ChatFormatting.DARK_GREEN.getColor();
                }

                if (material == ModArmorMaterials.ANTHEKTITE_DIAMOND) {
                    newColor = ChatFormatting.AQUA.getColor();
                }
            }
        }

        return newColor;
    }

    public static float getDestroySpeed(float originalSpeed, ItemStack stack, BlockState state) {
        float newSpeed = originalSpeed;
        Item var5 = stack.getItem();
        if (var5 instanceof DiggerItem diggerItem) {
            Tier tier = diggerItem.getTier();
            if (diggerItem.isCorrectToolForDrops(state)) {
                if (tier == ModTiers.DIARKRITE_IRON) {
                    newSpeed *= (float) ConfigHelper.get().getServer().getToolProperties().getIronBreakingSpeedMultiplier();
                }

                if (tier == ModTiers.DIARKRITE_GOLD) {
                    newSpeed *= (float) ConfigHelper.get().getServer().getToolProperties().getGoldBreakingSpeedMultiplier();
                }

                if (tier == ModTiers.DIARKRITE_EMERALD) {
                    newSpeed *= (float) ConfigHelper.get().getServer().getToolProperties().getEmeraldBreakingSpeedMultiplier();
                }

                if (tier == ModTiers.DIARKRITE_DIAMOND) {
                    newSpeed *= (float) ConfigHelper.get().getServer().getToolProperties().getDiamondBreakingSpeedMultiplier();
                }


                if (tier == ModTiers.ANTHEKTITE_IRON) {
                    newSpeed *= (float) (ConfigHelper.get().getServer().getToolProperties().getIronBreakingSpeedMultiplier() * 1.25);
                }

                if (tier == ModTiers.ANTHEKTITE_GOLD) {
                    newSpeed *= (float) (ConfigHelper.get().getServer().getToolProperties().getGoldBreakingSpeedMultiplier() * 1.25);
                }

                if (tier == ModTiers.ANTHEKTITE_EMERALD) {
                    newSpeed *= (float) (ConfigHelper.get().getServer().getToolProperties().getEmeraldBreakingSpeedMultiplier() * 1.25);
                }

                if (tier == ModTiers.ANTHEKTITE_DIAMOND) {
                    newSpeed *= (float) (ConfigHelper.get().getServer().getToolProperties().getDiamondBreakingSpeedMultiplier() * 1.25);
                }
            }
        }

        return newSpeed;
    }
}
