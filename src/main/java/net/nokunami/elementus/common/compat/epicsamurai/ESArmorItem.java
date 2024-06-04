package net.nokunami.elementus.common.compat.epicsamurai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.item.ElementusArmorItem;
import net.nokunami.elementus.common.registry.ModArmorMaterials;

import java.util.Locale;

public class ESArmorItem extends ElementusArmorItem {
    public ESArmorItem(ModArmorMaterials material, Type type, Properties properties) {
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
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/epic_samurai/%s_layer_" + (leggings ? "2.png" : "1.png"), domain, texture);
    }
}
