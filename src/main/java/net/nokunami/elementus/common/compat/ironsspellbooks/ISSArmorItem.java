package net.nokunami.elementus.common.compat.ironsspellbooks;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.Elementus;

import java.util.Locale;

public class ISSArmorItem extends ImbuableChestplateArmorItem {
    public ISSArmorItem(MagicArmorMaterial material, Type type, Properties properties) {
        super(material, type, new Properties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) Elementus.PROXY.getArmorRenderProperties());
    }

    public @org.jetbrains.annotations.Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/irons_spellbooks/%s_layer_" + (leggings | helmet ? "2.png" : "1.png"), domain, texture);
    }
}
