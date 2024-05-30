package net.nokunami.elementus.compat.ironsspellbooks;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ISSArmorItem extends ImbuableChestplateArmorItem {
    public ISSArmorItem(MagicArmorMaterial material, Type type, Properties properties) {
        super(material, type, new Properties());
    }
}
