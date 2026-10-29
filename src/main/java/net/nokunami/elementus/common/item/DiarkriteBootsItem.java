package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeMod;
import net.nokunami.elementus.common.item.basic.EArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.nokunami.elementus.common.item.AttributeUtil.toggleableAttribute;

public class DiarkriteBootsItem extends EArmorItem {

    public DiarkriteBootsItem(EArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
    }

    public ImmutableMultimap.Builder<Attribute, AttributeModifier> attributes() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", 8, AttributeModifier.Operation.ADDITION));
        toggleableAttribute(builder, Attributes.KNOCKBACK_RESISTANCE, "Armor knockback resistance", 0.5, AttributeModifier.Operation.ADDITION);
        toggleableAttribute(builder, Attributes.ATTACK_SPEED, "Armor attack speed", 0, AttributeModifier.Operation.ADDITION);
        toggleableAttribute(builder, Attributes.MOVEMENT_SPEED, "Armor movement speed", -0.5, AttributeModifier.Operation.ADDITION);
        toggleableAttribute(builder, ForgeMod.STEP_HEIGHT_ADDITION, "Armor step height", 1, AttributeModifier.Operation.ADDITION);
        return builder;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == this.type.getSlot()) {
            if (getSculkSilencer(stack)) {
                return attributes().build();
            } else {
                return super.getAttributeModifiers(slot, stack);
            }
        }
        return ImmutableMultimap.of();
    }

    @Override
    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, @NotNull ItemStack otherStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (otherStack.isEmpty() && action == ClickAction.SECONDARY) {
            setSculkSilencer(stack, !getSculkSilencer(stack));
            player.playSound(SoundEvents.ENDER_EYE_DEATH);
            return true;
        }
        return super.overrideOtherStackedOnMe(stack, otherStack, slot, action, player, access);
    }

    public static boolean getSculkSilencer(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("SculkSilencer");
    }

    public static void setSculkSilencer(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("SculkSilencer", b);
    }

    public static boolean SculkWalkerActivation(LivingEntity entity) {
        ItemStack itemStack = entity.getItemBySlot(EquipmentSlot.FEET);
        return itemStack.getItem() instanceof DiarkriteBootsItem && getSculkSilencer(itemStack);
    }
}
