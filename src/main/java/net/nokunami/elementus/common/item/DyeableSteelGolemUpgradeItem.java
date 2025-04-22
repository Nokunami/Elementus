package net.nokunami.elementus.common.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;

public class DyeableSteelGolemUpgradeItem extends SteelGolemUpgradeItem implements DyeableLeatherItem {
    /**
     *
     * @param protection the given protection level of the {@code HorseArmorItem}
     * @param pIdentifier the texture path identifier for the {@code DyeableHorseArmorItem}, {@link
     * net.minecraft.world.item.HorseArmorItem}
     * @param pProperties the item properties
     */
    public DyeableSteelGolemUpgradeItem(int protection, int toughness, String pIdentifier, Item.Properties pProperties) {
        super(protection, toughness, pIdentifier, pProperties);
    }
    /**
     *
     * @param protection the given protection level of the {@code HorseArmorItem}
     * @param pIdentifier the texture path identifier for the {@code DyeableHorseArmorItem}, {@link
     * net.minecraft.world.item.HorseArmorItem}
     * @param pProperties the item properties
     */
    public DyeableSteelGolemUpgradeItem(int protection, int toughness, boolean pushable, ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(protection, toughness, pushable, pIdentifier, pProperties);
    }

    public DyeableSteelGolemUpgradeItem(int protection, int toughness, ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(protection, toughness, false, pIdentifier, pProperties);
    }


    public DyeableSteelGolemUpgradeItem(int protection, boolean pushable, ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(protection, 0, pushable, pIdentifier, pProperties);
    }

    public DyeableSteelGolemUpgradeItem(int protection, ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(protection, 0, false, pIdentifier, pProperties);
    }


    public DyeableSteelGolemUpgradeItem(boolean pushable, ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(0, 0, pushable, pIdentifier, pProperties);
    }

    public DyeableSteelGolemUpgradeItem(ResourceLocation pIdentifier, Item.Properties pProperties) {
        super(0, 0, false, pIdentifier, pProperties);
    }
}
