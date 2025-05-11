package net.nokunami.elementus.common.item;

import com.github.L_Ender.cataclysm.init.ModItems;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.item.CatalystItemUtil.*;

public class CatalystArmorItem extends ArmorItem {
    public final ModArmorMaterials material;
    public static Multimap<Attribute, AttributeModifier> defaultModifiers;
    private static final String core = "Items";
    private static final String elytra = "ElytraEquipped";
    private static final String elytraL = "ElytraEquiped";
    public static int attributeCheck = 0;


    public CatalystArmorItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
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

        defaultModifiers = builder.build();
    }

    @Override
    public @NotNull ModArmorMaterials getMaterial() {
        return this.material;
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ElementusClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture(stack, entity);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        if (catalystActivator(stack).equals(netherStar)) {
            tooltip.add(Component.translatable(getDescriptionId() + ".nether_star_title_desc").withStyle(ChatFormatting.LIGHT_PURPLE));
            tooltip.add(Component.translatable(getDescriptionId() + ".nether_star_desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(ignitium)) {
            if (cataclysm) {
                tooltip.add(Component.translatable(getDescriptionId() + ".iginitum_title_desc").withStyle(ChatFormatting.YELLOW));
                tooltip.add(Component.translatable(getDescriptionId() + ".iginitum_desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.cataclysm_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(arcane)) {
            if (ironsSpellbooks) {
                tooltip.add(Component.translatable(getDescriptionId() + ".iss_title").withStyle(ChatFormatting.LIGHT_PURPLE));
                if (CatalystArmorConfig.ISS_MaxMana != 0) tooltip.add(Component.translatable(getDescriptionId() + ".iss_max_mana_desc", String.valueOf(CatalystArmorConfig.ISS_MaxMana)).withStyle(ChatFormatting.AQUA));
                if (CatalystArmorConfig.ISS_ManaRegen != 0) tooltip.add(Component.translatable(getDescriptionId() + ".iss_mana_regen_desc", (int)(CatalystArmorConfig.ISS_ManaRegen * 100) + "%").withStyle(ChatFormatting.AQUA));
                if (CatalystArmorConfig.ISS_SpellPower != 0) tooltip.add(Component.translatable(getDescriptionId() + ".iss_spell_power_desc", (int)(CatalystArmorConfig.ISS_SpellPower * 100) + "%").withStyle(ChatFormatting.AQUA));
                if (CatalystArmorConfig.ISS_SpellResist != 0) tooltip.add(Component.translatable(getDescriptionId() + ".iss_spell_resist_desc", (int)(CatalystArmorConfig.ISS_SpellResist * 100) + "%").withStyle(ChatFormatting.AQUA));
            } else tooltip.add(Component.translatable("item.elementus.iss_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(heartSea)) {
            tooltip.add(Component.translatable(getDescriptionId() + ".heart_of_the_sea_title_desc").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.translatable(getDescriptionId() + ".heart_of_the_sea_desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(totem)) {
            tooltip.add(Component.translatable(getDescriptionId() + ".totem_title_desc").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable(getDescriptionId() + ".totem_desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(cursium)) {
            if (cataclysm) {
                tooltip.add(Component.translatable(getDescriptionId() + ".cursium_title_desc").withStyle(ChatFormatting.DARK_AQUA));
                tooltip.add(Component.translatable(getDescriptionId() + ".cursium_desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.cataclysm_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(witheredNetherStar)) {
            if (witherStormMod) {
                tooltip.add(Component.translatable(getDescriptionId() + ".withered_nether_star_title_desc").withStyle(ChatFormatting.DARK_PURPLE));
                tooltip.add(Component.translatable(getDescriptionId() + ".withered_nether_star_desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.witherstormod_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (getElytraEquipped(stack).findAny().isPresent()) {
            tooltip.add(Component.translatable(getDescriptionId() + ".elytra_equipped").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
        }
        if (getElytraEquiped(stack).findAny().isPresent()) {
            tooltip.add(Component.translatable(getDescriptionId() + ".elytra_equipped_legacy").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Override
    public void onArmorTick(ItemStack stack, Level world, Player player) {
        netherStar(stack, player);
        ignitium(stack, player);
        arcane(stack ,player);
        heartSea(stack ,player);
        witheredNetherStar(stack ,player);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            int type = switch (catalystActivator(stack)) {
                case netherStar -> 1;
                case heartSea -> 2;
                case witheredNetherStar -> 3;
                default -> 0;
            };
            effectRadius(player, stack, level, type);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public static void attribute(int i) {
        attributeCheck = i;
    }

    public static String catalystActivator(ItemStack stack) {
        if (getContents(stack).anyMatch((c -> c.getItem() == Items.NETHER_STAR)))
            return netherStar;
        if (cataclysm) {
            if (getContents(stack).anyMatch((c -> c.getItem() == ModItems.IGNITIUM_INGOT.get())))
                return ignitium;
            if (getContents(stack).anyMatch((c -> c.getItem() == ModItems.CURSIUM_INGOT.get())))
                return cursium;
        }
        if (ironsSpellbooks)
            if (getContents(stack).anyMatch((c -> c.getItem() == ItemRegistry.ARCANE_INGOT.get())))
                return arcane;
        if (getContents(stack).anyMatch((c -> c.getItem() == Items.HEART_OF_THE_SEA)))
            return heartSea;
        if (getContents(stack).anyMatch((c -> c.getItem() == Items.TOTEM_OF_UNDYING)))
            return totem;
        if (witherStormMod)
            if (getContents(stack).anyMatch((c -> c.getItem() == WitherStormModItems.WITHERED_NETHER_STAR.get())))
                return witheredNetherStar;
        return "false";
    }

    @Override
    public boolean canBeHurtBy(@NotNull DamageSource source) {
        return false;
    }

    @Override
    public int getEntityLifespan(ItemStack stack, Level level) {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean canBeDepleted() {
        return !ModConfig.COMMON.catalystArmorDurability.get();
    }

    @Override
    public boolean isRepairable(@NotNull ItemStack stack) {
        return ModConfig.COMMON.catalystArmorDurability.get();
    }

    public static boolean isFlyEnabled(ItemStack stack) {
        return getElytraEquipped(stack).findAny().isPresent();
    }

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return isFlyEnabled(stack);
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (nextFlightTick % 20 == 0) {
                    if (!ModConfig.COMMON.catalystArmorDurability.get()) {
                        stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(EquipmentSlot.CHEST));
                    }
                }
                entity.gameEvent(GameEvent.ELYTRA_GLIDE);
            }
        }
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return !ModConfig.COMMON.catalystArmorDurability.get() ? super.canApplyAtEnchantingTable(stack, enchantment) : enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    // Insert Code

    //OnStackClicked
    public boolean overrideStackedOnOther(ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (stack.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack = slot.getItem();
            if (itemstack.isEmpty()) {
                if (checkElytraEquiped(stack) > 0) {
                    playElytraEquipSound(player);
                    removeEquipedElytra(stack).ifPresent((item) -> equipElytraL(stack, slot.safeInsert(item)));
                } else if (checkElytraEquipped(stack) > 0) {
                    playElytraEquipSound(player);
                    removeEquippedElytra(stack).ifPresent((item) -> equipElytra(stack, slot.safeInsert(item)));
                } else if (getContentWeight(stack) > 0) {
                    playRemoveSound(player);
                    removeCore(stack).ifPresent((item) -> insertCore(stack, slot.safeInsert(item)));
                }
            } else if (itemstack.is(Etags.Items.CATALYST_ITEMS)) {
                int i = (1 - getContentWeight(stack));
                int j = insertCore(stack, slot.safeTake(itemstack.getCount(), i, player));
                if (j > 0) {
                    playInsertSound(player, stack);
                }
            } else if (itemstack.is(Etags.Items.CATALYST_ELYTRA)) {
                int e = (1 - checkElytraEquipped(stack));
                int g = equipElytra(stack, slot.safeTake(itemstack.getCount(), e, player));
                if (g > 0) {
                    playElytraEquipSound(player);
                }
            }
            return true;
        }
    }
    //OnClicked
    public boolean overrideOtherStackedOnMe(ItemStack coreStack, @NotNull ItemStack mouseStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (coreStack.getCount() != 1) return false;
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (mouseStack.isEmpty()) {
                if (checkElytraEquiped(coreStack) > 0) {
                    removeEquipedElytra(coreStack).ifPresent((item) -> {
                        playElytraEquipSound(player);
                        access.set(item);
                    });
                } else if (checkElytraEquipped(coreStack) > 0) {
                    removeEquippedElytra(coreStack).ifPresent((item) -> {
                        playElytraEquipSound(player);
                        access.set(item);
                    });
                } else if (getContentWeight(coreStack) > 0) {
                    removeCore(coreStack).ifPresent((item) -> {
                        playRemoveSound(player);
                        access.set(item);
                    });
                }
            } else {
                int i = insertCore(coreStack, mouseStack);
                int e = equipElytra(coreStack, mouseStack);
                if (i > 0) {
                    playInsertSound(player, coreStack);
                    mouseStack.shrink(i);
                }
                if (e > 0) {
                    playElytraEquipSound(player);
                    mouseStack.shrink(e);
                }
            }
            return true;
        } else {
            return false;
        }
    }

    private static int insertCore(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && insertStack.is(Etags.Items.CATALYST_ITEMS)) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains(core))
                tag.put(core, new ListTag());
            int k = Math.min(insertStack.getCount(), (1 - getContentWeight(coreStack)));
            if (k == 0) {
                return 0;
            }
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(k).save(newTag);
                tag.getList(core, 10).add(0, newTag);
//                addCUstomAttribute(ModArmorMaterials.CATALYST, Type.CHESTPLATE, coreStack);
                return k;
            }
        } else {
            return 0;
        }
    }

    private static int equipElytra(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && insertStack.is(Etags.Items.CATALYST_ELYTRA)) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains(elytra)) {
                tag.put(elytra, new ListTag());
            }
            int k = Math.min(insertStack.getCount(), (1 - checkElytraEquipped(coreStack)));
            if (k == 0) {
                return 0;
            }
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(k).save(newTag);
                tag.getList(elytra, 10).add(0, newTag);
                return k;
            }
        } else {
            return 0;
        }
    }

    private static int equipElytraL(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && insertStack.is(Etags.Items.CATALYST_ELYTRA)) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains(elytraL)) {
                tag.put(elytraL, new ListTag());
            }
            int k = Math.min(insertStack.getCount(), (1 - checkElytraEquipped(coreStack)));
            if (k == 0) {
                return 0;
            }
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(k).save(newTag);
                tag.getList(elytraL, 10).add(0, newTag);
                return k;
            }
        } else {
            return 0;
        }
    }

    static int getContentWeight(ItemStack pStack) {
        return getContents(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static int checkElytraEquipped(ItemStack pStack) {
        return getElytraEquipped(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static int checkElytraEquiped(ItemStack pStack) {
        return getElytraEquiped(pStack).mapToInt(ItemStack::getCount).sum();
    }

    private static Optional<ItemStack> removeCore(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList(core, 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains(core)) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey(core);
                }
//                addCUstomAttribute(ModArmorMaterials.CATALYST, Type.CHESTPLATE, stack);
                return Optional.of(itemstack);
            }
        }
    }

    private static Optional<ItemStack> removeEquippedElytra(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList(elytra, 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains(elytra)) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey(elytra);
                }
                return Optional.of(itemstack);
            }
        }
    }

    private static Optional<ItemStack> removeEquipedElytra(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList(elytraL, 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains(elytraL)) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey(elytraL);
                }
                return Optional.of(itemstack);
            }
        }
    }

    private static Stream<ItemStack> getContents(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList(core, 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static Stream<ItemStack> getElytraEquipped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList(elytra, 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static Stream<ItemStack> getElytraEquiped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList(elytraL, 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        NonNullList<ItemStack> core = NonNullList.create();
        NonNullList<ItemStack> elytra = NonNullList.create();
        getContents(stack).forEach(core::add);
        getElytraEquipped(stack).forEach(elytra::add);
        return Optional.of(new CatalystTooltip(core, elytra));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        ItemUtils.onContainerDestroyed(itemEntity, getContents(itemEntity.getItem()));
    }

    private void playRemoveSound(Player entity) {
        entity.playSound(ModSoundEvents.CATALYST_ARMOR_DEACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Player entity, ItemStack stack) {
        entity.playSound(ModSoundEvents.CATALYST_ARMOR_ACTIVATE.get(), 0.75F, 0.5F + entity.level().getRandom().nextFloat() * 0.4F);
        if (catalystActivator(stack).equals(witheredNetherStar) && witherStormMod) {
            entity.playSound(WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), 0.8F, 1F);
        }
    }

    private void playElytraEquipSound(Player entity) {
        entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
    }
}