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
import net.minecraftforge.common.util.Lazy;
import net.nokunami.elementus.common.config.ArmorConfig;
import net.nokunami.elementus.common.config.UniqueItemConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.nokunami.elementus.common.config.ArmorConfig.diarkriteArmor_AttackSpeed;
import static net.nokunami.elementus.common.config.UniqueItemConfig.*;

public class DiarkriteBootsItem extends ElementusArmorItem {
    private final Lazy<Multimap<Attribute, AttributeModifier>> silencedAttributes;

    public DiarkriteBootsItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
        silencedAttributes = Lazy.of(() -> createDefaultAttributeModifiers().build());
    }

    protected ImmutableMultimap.Builder<Attribute, AttributeModifier> createDefaultAttributeModifiers() {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(type);
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", this.getDefense(), AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", diarkriteBootsToughness, AttributeModifier.Operation.ADDITION));
        if (diarkriteBootsKnockbackResistance != 0) builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Knockback resistance", diarkriteBootsKnockbackResistance, AttributeModifier.Operation.ADDITION));
        if (diarkriteArmor_AttackSpeed != 0) builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Speed Modifier", diarkriteArmor_AttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        if (diarkriteBootsMovementSpeed != 0) builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Speed Modifier", diarkriteBootsMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        if (diarkriteBootsStepHeight != 0) builder.put(ForgeMod.STEP_HEIGHT.get(), new AttributeModifier(uuid, "Step Height Modifier", diarkriteBootsStepHeight, AttributeModifier.Operation.ADDITION));
        return builder;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == this.type.getSlot()) {
            if (getSculkSilencer(stack)) {
                return silencedAttributes.get();
            } else {
                return defaultModifiers.get();
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
