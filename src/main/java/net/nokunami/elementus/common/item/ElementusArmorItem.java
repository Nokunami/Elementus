package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ModClientEvents;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;


public class ElementusArmorItem extends ArmorItem {
    protected final ModArmorMaterials material;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ElementusArmorItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, new Properties());
        this.material = material;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", this.getToughness(), AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Knockback resistance", this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        for (Map.Entry<Attribute, AttributeModifier> modifierEntry : material.getAdditionalAttributes().entrySet()) {
            AttributeModifier atr = modifierEntry.getValue();
            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
            builder.put(modifierEntry.getKey(), atr);
        }

        this.defaultModifiers = builder.build();
    }

    @Override
    public @NotNull ModArmorMaterials getMaterial() {
        return this.material;
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

//    private int repairPlayerItems(Player pPlayer, int pRepairAmount) {
//        Map.Entry<EquipmentSlot, ItemStack> entry = EnchantmentHelper.getRandomItemWith(Enchantments.MENDING, pPlayer, ItemStack::isDamaged);
//        if (entry != null) {
//            ItemStack itemstack = entry.getValue();
//            int i = Math.min((int) (this.value * itemstack.getXpRepairRatio()), itemstack.getDamageValue());
//            itemstack.setDamageValue(itemstack.getDamageValue() - i);
//            int j = pRepairAmount - this.durabilityToXp(i);
//            return j > 0 ? this.repairPlayerItems(pPlayer, j) : 0;
//        } else {
//            return pRepairAmount;
//        }
//    }
//
//    private int durabilityToXp(int pDurability) {
//        return pDurability / 2;
//    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(java.util.function.Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ModClientEvents.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean chestplate = slot == EquipmentSlot.CHEST;
        boolean leggings = slot == EquipmentSlot.LEGS;

        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (helmet ? "1.png" : chestplate ? "2.png" : leggings ? "3.png" : "4.png"), domain, texture);
    }
}