package net.nokunami.elementus.common.compat.ironsspellbooks;

import io.redspace.ironsspellbooks.api.spells.IPresetSpellContainer;
import io.redspace.ironsspellbooks.api.spells.ISpellContainer;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class ImbuableChestplateArmorItem extends ElementusWizardArmorItem implements IPresetSpellContainer {
    public ImbuableChestplateArmorItem(MagicArmorMaterial1 material, Type type, Properties properties) {
        super(material, type, new Properties());
    }


    public void initializeSpellContainer(ItemStack itemStack) {
        if (itemStack != null) {
            Item var3 = itemStack.getItem();
            if (var3 instanceof ArmorItem) {
                ArmorItem armorItem = (ArmorItem)var3;
                if (armorItem.getType() == Type.CHESTPLATE && !ISpellContainer.isSpellContainer(itemStack)) {
                    ISpellContainer spellContainer = ISpellContainer.create(1, true, true);
                    spellContainer.save(itemStack);
                }
            }

        }
    }
}
