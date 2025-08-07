package net.nokunami.elementus.common.compat.sniffsweapons;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.DyeableArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;

import static net.nokunami.elementus.Elementus.MODID;

public class SniffsWeaponsArmorItem extends DyeableArmorItem {
    private final int color;
    private final String texture_type;

    protected final ModArmorMaterials material;

    public SniffsWeaponsArmorItem(ModArmorMaterials material, Type type, String texture_type, int color, Properties properties) {
        super(material, type, properties);
        this.material = material;
        this.color = color;
        this.texture_type = texture_type;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ElementusClient.PROXY.getArmorRenderProperties());
    }

    public int getColor(ItemStack itemStack) {
        CompoundTag compoundTag = itemStack.getTagElement("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : this.color;
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        boolean is_overlay = Objects.equals(type, "overlay");
        String texture = item.getMaterial().getName();
        String domain = MODID;
        int idx = texture.indexOf(99);
        if (idx != -1) {
            domain = texture.substring(0, idx);
            texture = texture.substring(idx + 1);
        }

        if (this.texture_type.equals("clothed")) {
            String s1 = String.format(Locale.ROOT, "%s:textures/models/armor/sniffs_weapons/clothed_%s_cuirass_layer.png", domain, texture);
            String s2 = String.format(Locale.ROOT, "%s:textures/models/armor/clothed_cuirass_color.png", "sniffsweapons");
            return is_overlay ? s1 : s2;
        } return String.format(Locale.ROOT, "%s:textures/models/armor/sniffs_weapons/%s_%s.png", domain, this.texture_type, texture);
    }
}
