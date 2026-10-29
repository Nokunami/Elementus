package net.nokunami.elementus.common.item;

import net.minecraft.world.item.DyeableLeatherItem;
import net.nokunami.elementus.common.item.basic.EArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;

public class ModDyeableArmorItem extends EArmorItem implements DyeableLeatherItem {

    public ModDyeableArmorItem(EArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
    }
}