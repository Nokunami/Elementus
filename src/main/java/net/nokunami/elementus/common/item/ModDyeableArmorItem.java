package net.nokunami.elementus.common.item;

import net.minecraft.world.item.DyeableLeatherItem;
import net.nokunami.elementus.common.item.basic.ElementusArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;

public class ModDyeableArmorItem extends ElementusArmorItem implements DyeableLeatherItem {
    public ModDyeableArmorItem(EArmorMaterials.EnumArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
    }
}
