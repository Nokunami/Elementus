package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import nl.sniffiandros.sniffsweapons.client.model.SamuraiArmorModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.function.Consumer;

public class SamuraiArmorItem extends StylishArmorItem {

    public SamuraiArmorItem(ModArmorMaterials material, Type type, int color, Properties properties) {
        super(material, type, "samurai", color, new Properties());
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return new SamuraiArmorModel(SamuraiArmorModel.createBodyLayer().bakeRoot(), itemStack, equipmentSlot);
            }
        });
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        int idx = texture.indexOf(58);
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }

        return String.format(Locale.ROOT, "%s:textures/models/armor/sniffs_weapons/%s_%s.png", domain, "samurai", texture);
    }
}