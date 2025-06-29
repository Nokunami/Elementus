package nokunami.elementus.common.compat.epicsamurai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import nokunami.elementus.ElementusClient;
import nokunami.elementus.common.item.ElementusArmorItem;
import nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Locale;

public class ESArmorItem extends ElementusArmorItem {
    public ESArmorItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, new Properties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ElementusClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/epic_samurai/%s_layer_" + (leggings ? "2.png" : "1.png"), domain, texture);
    }
}
