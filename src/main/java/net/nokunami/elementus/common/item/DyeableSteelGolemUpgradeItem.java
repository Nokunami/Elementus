package net.nokunami.elementus.common.item;

import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;

public class DyeableSteelGolemUpgradeItem extends SteelGolemUpgradeItem implements DyeableLeatherItem {

    public DyeableSteelGolemUpgradeItem(String pIdentifier, Item.Properties pProperties, GolemUpgradeProperties golemUpgradeProperties) {
        super(pIdentifier, pProperties, golemUpgradeProperties);
    }
}
