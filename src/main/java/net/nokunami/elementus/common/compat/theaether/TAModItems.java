package net.nokunami.elementus.common.compat.theaether;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.ToolsConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModTiers;

import java.util.UUID;

import static net.nokunami.elementus.Elementus.MODID;

public class TAModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<GlovesItem> STEEL_GLOVES = ITEMS.register("steel_gloves", () -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.steelGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.steelGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.steelGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.steelGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        return new AttributeGlovesItem(ModArmorMaterials.STEEL, ToolsConfig.steelGloveDamage, "steel_gloves", () -> SoundEvents.ARMOR_EQUIP_IRON,
                new Item.Properties().defaultDurability(ModTiers.STEEL.getUses()), builder.build());
    });

    public static final RegistryObject<GlovesItem> DIARKRITE_GLOVES = ITEMS.register("diarkrite_gloves", () -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.diarkriteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.diarkriteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.diarkriteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.diarkriteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, ToolsConfig.diarkriteGloveDamage, "diarkrite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
                new Item.Properties().defaultDurability(ModTiers.DIARKRITE.getUses()).fireResistant(), builder.build());
    });

    public static final RegistryObject<GlovesItem> ANTHEKTITE_GLOVES = ITEMS.register("anthektite_gloves", () -> {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.anthektiteGloveMovementSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.anthektiteGloveAttackSpeed, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.anthektiteGloveArmorBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Weapon modifier", ToolsConfig.anthektiteGloveToughnessBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        return new AttributeGlovesItem(ModArmorMaterials.DIARKRITE, ToolsConfig.anthektiteGloveDamage, "anthektite_gloves", () -> SoundEvents.ARMOR_EQUIP_NETHERITE,
            new Item.Properties().defaultDurability(ModTiers.ANTHEKTITE.getUses()).fireResistant(), builder.build());
    });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
