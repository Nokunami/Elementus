package net.nokunami.elementus.common.item.basic;

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
import net.minecraftforge.common.util.Lazy;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.common.config.configSets.armor.ArmorMaterialConfig;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;
import java.util.UUID;
import java.util.function.Consumer;

import static net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes.toggleableAttribute;
import static net.nokunami.elementus.common.config.CommonConfig.getArmorConfig;
import static net.nokunami.elementus.common.registry.EArmorMaterials.EnumArmorMaterials.HEALTH_FUNCTION_FOR_TYPE;


public class ElementusArmorItem extends ArmorItem {
//    protected final EArmorMaterials.EnumArmorMaterials material;
//    protected final Lazy<Multimap<Attribute, AttributeModifier>> defaultModifiers;

    public ElementusArmorItem(EArmorMaterials.EnumArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
//        this.material = material;
//        defaultModifiers = Lazy.of(createDefaultAttributeModifiers()::build);
    }

//    @Override public @NotNull EArmorMaterials.EnumArmorMaterials getMaterial() { return material; }

    // Attribute code from biomancy
    protected ImmutableMultimap.Builder<Attribute, AttributeModifier> createDefaultAttributeModifiers() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", getToughness(), AttributeModifier.Operation.ADDITION));
        toggleableAttribute(builder, Attributes.KNOCKBACK_RESISTANCE, "Armor knockback resistance", getKnockbackResistance(), AttributeModifier.Operation.ADDITION);
        toggleableAttribute(builder, Attributes.ATTACK_SPEED, "Armor attack speed", getAttackSpeed(), AttributeModifier.Operation.MULTIPLY_BASE);
        toggleableAttribute(builder, Attributes.MOVEMENT_SPEED, "Armor movement speed", getMovementSpeed(), AttributeModifier.Operation.MULTIPLY_BASE);
        return builder;
    }

//    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
//        return pEquipmentSlot == this.type.getSlot() ? defaultModifiers.get() : super.getDefaultAttributeModifiers(pEquipmentSlot);
//    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == getEquipmentSlot() ? createDefaultAttributeModifiers().build() : super.getAttributeModifiers(slot, stack);
    }

    public ArmorMaterialConfig config () {
        return getArmorConfig(getMaterial());
    }

    @Override public int getDefense() { return config().getArmor().get(getType()); }
    @Override public float getToughness() { return config().attributesConfig.toughness.get().floatValue(); }
    public float getKnockbackResistance() { return config().attributesConfig.knockback.get().floatValue(); }
    public float getAttackSpeed() { return config().attributesConfig.attackSpeed.get().floatValue(); }
    public float getMovementSpeed() { return config().attributesConfig.movementSpeed.get().floatValue(); }

    @Override public int getMaxDamage(ItemStack stack) { return HEALTH_FUNCTION_FOR_TYPE.get(getType()) * config().getDurability(); }
    @Override public int getEnchantmentValue(ItemStack stack) { return config().getEnchantability(); }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) EClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        ArmorItem item = (ArmorItem)stack.getItem();
        String texture = item.getMaterial().getName();
        String domain = "elementus";
        boolean helmet = slot == EquipmentSlot.HEAD;
        boolean leggings = slot == EquipmentSlot.LEGS;
        return String.format(Locale.ROOT, "%s:textures/models/armor/%s_layer_" + (helmet | leggings ? "2.png" : "1.png"), domain, texture);
    }
}