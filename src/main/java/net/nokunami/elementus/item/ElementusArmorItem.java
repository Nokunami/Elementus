package net.nokunami.elementus.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.client.ArmorModelLayered;
import net.nokunami.elementus.item.ArmorTiers;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ElementusArmorItem extends ArmorItem {
    private static final String TEXTURE = Elementus.MODID + ":textures/armor/armor_template.png";

    protected final ArmorItem.Type type;
    private final int defense;
    protected final float knockbackResistance;
    protected final ArmorMaterial material;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public ElementusArmorItem(ArmorTiers material, ArmorItem.Type type, Item.Properties properties) {
        super(material, type, properties);
        this.material = material;
        this.type = type;
        this.defense = material.getDefenseForType(type);
        float toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        DispenserBlock.registerBehavior(this, DISPENSE_ITEM_BEHAVIOR);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double) this.getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double) this.getToughness(), AttributeModifier.Operation.ADDITION));
        if (this.knockbackResistance > 0) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)this.knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        for (Map.Entry<Attribute, AttributeModifier> modifierEntry : material.getAdditionalAttributes().entrySet()) {
            AttributeModifier atr = modifierEntry.getValue();
            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
            builder.put(modifierEntry.getKey(), atr);
        }

        this.defaultModifiers = builder.build();
    }

    public ArmorItem.Type getType() {
        return this.type;
    }

    public int getEnchantmentValue() {
        return this.material.getEnchantmentValue();
    }

    public ArmorMaterial getMaterial() {
        return this.material;
    }

    public boolean isValidRepairItem(ItemStack p_40392_, ItemStack p_40393_) {
        return this.material.getRepairIngredient().test(p_40393_) || super.isValidRepairItem(p_40392_, p_40393_);
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot p_40390_) {
        return p_40390_ == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(p_40390_);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return TEXTURE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                return new ArmorModelLayered<>(ArmorModelLayered.createBodyLayer().bakeRoot(), armorSlot);
            }
        });
    }
}