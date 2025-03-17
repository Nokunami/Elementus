package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
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
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.ModClientEvents;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
import nonamecrackers2.witherstormmod.common.init.WitherStormModSoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.item.CatalystItemUtil.*;

public class CatalystArmorItem extends ArmorItem {
    protected final ModArmorMaterials material;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;


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

        this.defaultModifiers = builder.build();
    }

    @Override
    public @NotNull ModArmorMaterials getMaterial() {
        return this.material;
    }

    public @NotNull Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(@NotNull EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == this.type.getSlot() ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ModClientEvents.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return armorTexture(stack, entity, slot, type);
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        if (catalystActivator(stack).equals(netherStar)) {
            tooltip.add(Component.translatable("item.elementus.catalyst_nether_star_1.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
            tooltip.add(Component.translatable("item.elementus.catalyst_nether_star_2.desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(ignitium)) {
            if (cataclysm) {
                tooltip.add(Component.translatable("item.elementus.catalyst_iginitum_1.desc").withStyle(ChatFormatting.YELLOW));
                tooltip.add(Component.translatable("item.elementus.catalyst_iginitum_2.desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.cataclysm_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(arcane)) {
            if (ironsSpellbooks) {
                tooltip.add(Component.translatable("item.elementus.catalyst_iss.desc").withStyle(ChatFormatting.LIGHT_PURPLE));
                if (CatalystArmorConfig.ISS_MaxMana != 0) { tooltip.add(Component.translatable("item.elementus.catalyst_iss_max_mana.desc", String.valueOf(CatalystArmorConfig.ISS_MaxMana)).withStyle(ChatFormatting.AQUA));}
                if (CatalystArmorConfig.ISS_ManaRegen != 0) { tooltip.add(Component.translatable("item.elementus.catalyst_iss_mana_regen.desc", (int)(CatalystArmorConfig.ISS_ManaRegen * 100) + "%").withStyle(ChatFormatting.AQUA));}
                if (CatalystArmorConfig.ISS_SpellPower != 0) { tooltip.add(Component.translatable("item.elementus.catalyst_iss_spell_power.desc", (int)(CatalystArmorConfig.ISS_SpellPower * 100) + "%").withStyle(ChatFormatting.AQUA));}
                if (CatalystArmorConfig.ISS_SpellResist != 0) { tooltip.add(Component.translatable("item.elementus.catalyst_iss_spell_resist.desc", (int)(CatalystArmorConfig.ISS_SpellResist * 100) + "%").withStyle(ChatFormatting.AQUA));}
            } else tooltip.add(Component.translatable("item.elementus.iss_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(heartSea)) {
            tooltip.add(Component.translatable("item.elementus.catalyst_heart_of_the_sea_1.desc").withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.translatable("item.elementus.catalyst_heart_of_the_sea_2.desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(totem)) {
            tooltip.add(Component.translatable("item.elementus.catalyst_totem_1.desc").withStyle(ChatFormatting.GOLD));
            tooltip.add(Component.translatable("item.elementus.catalyst_totem_2.desc").withStyle(ChatFormatting.GRAY));
        }
        if (catalystActivator(stack).equals(cursium)) {
            if (cataclysm) {
                tooltip.add(Component.translatable("item.elementus.catalyst_cursium_1.desc").withStyle(ChatFormatting.DARK_AQUA));
                tooltip.add(Component.translatable("item.elementus.catalyst_cursium_2.desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.cataclysm_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (catalystActivator(stack).equals(witheredNetherStar)) {
            if (witherStormMod) {
                tooltip.add(Component.translatable("item.elementus.catalyst_withered_nether_star_1.desc").withStyle(ChatFormatting.DARK_PURPLE));
                tooltip.add(Component.translatable("item.elementus.catalyst_withered_nether_star_2.desc").withStyle(ChatFormatting.GRAY));
            } else tooltip.add(Component.translatable("item.elementus.witherstormod_not_installed.desc").withStyle(ChatFormatting.DARK_GRAY));
        }
        if (getElytraEquiped(stack).findAny().isPresent()) {
            tooltip.add(Component.translatable("item.elementus.catalyst_elytra_equiped").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
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
            effectRadius(player, stack, level);
        }
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }

    public static String catalystActivator(ItemStack stack) {
        if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == Items.NETHER_STAR))) {
            return netherStar;
        }
        if (cataclysm) {
            if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == com.github.L_Ender.cataclysm.init.ModItems.IGNITIUM_INGOT.get()))) {
                return ignitium;
            }
            if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == com.github.L_Ender.cataclysm.init.ModItems.CURSIUM_INGOT.get()))) {
                return cursium;
            }
        }
        if (ironsSpellbooks) {
            if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == ItemRegistry.ARCANE_INGOT.get()))) {
                return arcane;
            }
        }
        if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == Items.HEART_OF_THE_SEA))) {
            return heartSea;
        }
        if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == Items.TOTEM_OF_UNDYING))) {
            return totem;
        }
        if (witherStormMod) {
            if (getContents(stack).anyMatch((stack1 -> stack1.getItem() == WitherStormModItems.WITHERED_NETHER_STAR.get()))) {
                return witheredNetherStar;
            }
        }
        return "false";
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public int getEntityLifespan(ItemStack itemStack, Level level) {
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

    public static boolean isFlyEnabled(ItemStack pElytraStack) {
        return getElytraEquiped(pElytraStack).findAny().isPresent();
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
                        stack.hurtAndBreak(1, entity, e -> e.broadcastBreakEvent(net.minecraft.world.entity.EquipmentSlot.CHEST));
                    }
                }
                entity.gameEvent(net.minecraft.world.level.gameevent.GameEvent.ELYTRA_GLIDE);
            }
        }
        return true;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    // Insert Code

    //OnStackClicked
    public boolean overrideStackedOnOther(ItemStack stack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (stack.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack = slot.getItem();
            if (itemstack.isEmpty()) {
                if (getContentWeight(stack) > 0) {
                    playRemoveSound(player);
                    removeOne(stack).ifPresent((item) -> add(stack, slot.safeInsert(item)));
                } else if (checkElytraEquiped(stack) > 0) {
                    playElytraEquipSound(player);
                    removeEquipedElytra(stack).ifPresent((item) -> equipeElytra(stack, slot.safeInsert(item)));
                }
            } else if (itemstack.is(Etags.Items.CATALYST_ITEMS)) {
                int i = (1 - getContentWeight(stack) / getWeight());
                int j = add(stack, slot.safeTake(itemstack.getCount(), i, player));
                if (j > 0) {
                    playInsertSound(player, stack);
                }
            } else if (itemstack.is(Etags.Items.CATALYST_ELYTRA)) {
                int e = (1 - checkElytraEquiped(stack) / getElytaEquiped());
                int g = equipeElytra(stack, slot.safeTake(itemstack.getCount(), e, player));
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
                if (getContentWeight(coreStack) > 0  && checkElytraEquiped(coreStack) > 0 && Screen.hasAltDown()) {
                    removeEquipedElytra(coreStack).ifPresent((item) -> {
                        playElytraEquipSound(player);
                        access.set(item);
                    });
                } else if (checkElytraEquiped(coreStack) > 0 && getContentWeight(coreStack) > 0) {
                    removeOne(coreStack).ifPresent((item) -> {
                        playRemoveSound(player);
                        access.set(item);
                    });
                } else if (getContentWeight(coreStack) > 0) {
                    removeOne(coreStack).ifPresent((item) -> {
                        playRemoveSound(player);
                        access.set(item);
                    });
                } else {
                    removeEquipedElytra(coreStack).ifPresent((item) -> {
                        playElytraEquipSound(player);
                        access.set(item);
                    });
                }
            } else {
                int i = add(coreStack, mouseStack);
                int e = equipeElytra(coreStack, mouseStack);
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

    private static int add(ItemStack coreItemStack, ItemStack insertedStack) {
        if (!insertedStack.isEmpty() && insertedStack.getItem().canFitInsideContainerItems()) {
            if (insertedStack.is(Etags.Items.CATALYST_ITEMS)) {
                CompoundTag compoundtag = coreItemStack.getOrCreateTag();
                if (!compoundtag.contains("Items")) {
                    compoundtag.put("Items", new ListTag());
                }
                int i = getContentWeight(coreItemStack);
                int j = getWeight();
                int k = Math.min(insertedStack.getCount(), (1 - i) / j);
                if (k == 0) {
                    return 0;
                }
                else {
                    ListTag listtag = compoundtag.getList("Items", 10);
                    Optional<CompoundTag> optional = getMatchingItem(insertedStack, listtag);
                    if (optional.isPresent()) {
                        CompoundTag compoundtag1 = optional.get();
                        ItemStack itemstack = ItemStack.of(compoundtag1);
                        itemstack.grow(k);
                        itemstack.save(compoundtag1);
                        listtag.remove(compoundtag1);
                        listtag.add(0, compoundtag1);
                    } else {
                        ItemStack itemstack1 = insertedStack.copyWithCount(k);
                        CompoundTag compoundtag2 = new CompoundTag();
                        itemstack1.save(compoundtag2);
                        listtag.add(0, compoundtag2);
                    }
                    return k;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    private static int equipeElytra(ItemStack coreItemStack, ItemStack insertedStack) {
        if (!insertedStack.isEmpty() && insertedStack.getItem().canFitInsideContainerItems()) {
            if (insertedStack.is(net.minecraft.world.item.Items.ELYTRA)) {
                CompoundTag compoundtag = coreItemStack.getOrCreateTag();
                if (!compoundtag.contains("ElytraEquiped")) {
                    compoundtag.put("ElytraEquiped", new ListTag());
                }
                int i = checkElytraEquiped(coreItemStack);
                int j = getElytaEquiped();
                int k = Math.min(insertedStack.getCount(), (1 - i) / j);
                if (k == 0) {
                    return 0;
                }
                else {
                    ListTag listtag = compoundtag.getList("ElytraEquiped", 10);
                    Optional<CompoundTag> optional = getMatchingItem(insertedStack, listtag);
                    if (optional.isPresent()) {
                        CompoundTag compoundtag1 = optional.get();
                        ItemStack itemstack = ItemStack.of(compoundtag1);
                        itemstack.grow(k);
                        itemstack.save(compoundtag1);
                        listtag.remove(compoundtag1);
                        listtag.add(0, compoundtag1);
                    } else {
                        ItemStack itemstack1 = insertedStack.copyWithCount(k);
                        CompoundTag compoundtag2 = new CompoundTag();
                        itemstack1.save(compoundtag2);
                        listtag.add(0, compoundtag2);
                    }
                    return k;
                }
            } else {
                return 0;
            }
        }
        return 0;
    }

    private static Optional<CompoundTag> getMatchingItem(ItemStack pStack, ListTag pList) {
        return pStack.is(ModItems.ElementusItems.CATALYST_CHESTPLATE.get()) ? Optional.empty() : pList.stream()
                .filter(CompoundTag.class::isInstance).map(CompoundTag.class::cast).filter((p_186350_)
                        -> ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), pStack)).findFirst();
    }

    private static int getWeight() {
        return 1;
    }

    private static int getElytaEquiped() {
        return 1;
    }

    static int getContentWeight(ItemStack pStack) {
        return getContents(pStack).mapToInt((itemStack) -> getWeight() * itemStack.getCount()).sum();
    }

    public static int checkElytraEquiped(ItemStack pStack) {
        return getElytraEquiped(pStack).mapToInt((itemStack) -> getElytaEquiped() * itemStack.getCount()).sum();
    }

    private static Optional<ItemStack> removeOne(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getOrCreateTag();
        if (!compoundtag.contains("Items")) {
            return Optional.empty();
        } else {
            ListTag items = compoundtag.getList("Items", 10);
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                CompoundTag compoundtag1 = items.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                items.remove(0);
                if (items.isEmpty()) {
                    pStack.removeTagKey("Items");
                }
                return Optional.of(itemstack);
            }
        }
    }

    private static Optional<ItemStack> removeEquipedElytra(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getOrCreateTag();
        if (!compoundtag.contains("ElytraEquiped")) {
            return Optional.empty();
        } else {
            ListTag items = compoundtag.getList("ElytraEquiped", 10);
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                CompoundTag compoundtag1 = items.getCompound(0);
                ItemStack itemstack = ItemStack.of(compoundtag1);
                items.remove(0);
                if (items.isEmpty()) {
                    pStack.removeTagKey("ElytraEquiped");
                }
                return Optional.of(itemstack);
            }
        }
    }

    private static Stream<ItemStack> getContents(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("Items", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

    public static Stream<ItemStack> getElytraEquiped(ItemStack pStack) {
        CompoundTag compoundtag = pStack.getTag();
        if (compoundtag == null) {
            return Stream.empty();
        } else {
            ListTag listtag = compoundtag.getList("ElytraEquiped", 10);
            return listtag.stream().map(CompoundTag.class::cast).map(ItemStack::of);
        }
    }

//    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack pStack) {
//        NonNullList<ItemStack> nonnulllist = NonNullList.create();
//        getContents(pStack).forEach(nonnulllist::add);
//        return Optional.of(new BundleTooltip(nonnulllist, 0));
//    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        ItemUtils.onContainerDestroyed(itemEntity, getContents(itemEntity.getItem()));
    }

    private void playRemoveSound(Player entity) {
        entity.level().playSound(entity, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.CATALYST_ARMOR_DEACTIVATE.get(), SoundSource.PLAYERS, 1F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    private void playInsertSound(Player entity, ItemStack stack) {
        entity.level().playSound(entity, entity.getX(), entity.getY(), entity.getZ(), ModSoundEvents.CATALYST_ARMOR_ACTIVATE.get(), SoundSource.PLAYERS, 1F, 0.5F + entity.level().getRandom().nextFloat() * 0.4F);
        if (catalystActivator(stack).equals(witheredNetherStar) && witherStormMod) {
            entity.level().playSound(entity, entity.getX(), entity.getY(), entity.getZ(), WitherStormModSoundEvents.WITHER_STORM_REACTIVATES.get(), SoundSource.PLAYERS, 1F, 1F);
        }
    }

    private void playElytraEquipSound(Player entity) {
        entity.level().playSound(entity, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ARMOR_EQUIP_ELYTRA, SoundSource.PLAYERS, 1F, 1.0F);
    }
}