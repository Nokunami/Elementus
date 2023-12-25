package net.nokunami.elementus.item.armor;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.item.ArmorTiers;
import net.nokunami.elementus.item.ElementusArmorItem;

import javax.annotation.Nullable;

public class SteelArmorItem extends ElementusArmorItem {
    public SteelArmorItem(ArmorTiers tiers, Type pType, Properties pProperties) {
        super(tiers, pType, pProperties);
    }

    private static final String TEXTURE = Elementus.MODID + ":textures/armor/steel_armor_layer.png";

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TEXTURE;
    }
}