package net.nokunami.elementus.common.item;

import net.minecraft.world.item.DyeableLeatherItem;
import net.nokunami.elementus.common.registry.ModArmorMaterials;

public class ModDyeableArmorItem extends ElementusArmorItem implements DyeableLeatherItem {
    public ModDyeableArmorItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
    }
}
