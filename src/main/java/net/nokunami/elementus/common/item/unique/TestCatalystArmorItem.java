package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.client.extensions.ICatalystTrim;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.item.IScrollableItem;
import net.nokunami.elementus.common.item.basic.ElementusArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;
import static net.nokunami.elementus.common.registry.EArmorMaterials.EnumArmorMaterials.HEALTH_FUNCTION_FOR_TYPE;

public class TestCatalystArmorItem extends ElementusArmorItem implements ICatalystTrim, IScrollableItem {

    public TestCatalystArmorItem(EArmorMaterials.EnumArmorMaterials armorMaterial, Type type, Properties properties) {
        super(armorMaterial, type, properties);
        initClient();
    }

    private Object renderTrimProperties;
    public Object getRenderTrimPropertiesInternal() { return renderTrimProperties; }

    private void initClient() {
        // Minecraft instance isn't available in datagen, so don't call initializeClient if in datagen
        if (FMLEnvironment.dist == Dist.CLIENT && !FMLLoader.getLaunchHandler().isData())
            initializeClientTrim(properties -> renderTrimProperties = properties);
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClientTrim(Consumer<ICatalystTrim> consumer) {
        consumer.accept((ICatalystTrim) EClient.PROXY.getArmorCatalystTrimRenderProperties());
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) EClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        var helper = CatalystCoreUtil(stack);
        if (helper.hasCore()) return helper.getCore().getBaseTexture(stack, entity, slot, type);
        return EID + ":textures/models/armor/catalyst/catalyst_chestplate.png";
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == type.getSlot() ? CatalystCoreUtil(stack).getAttributes(slot, stack) : super.getAttributeModifiers(slot, stack);
    }

    @Override public int getDefense() { return EConfig.COMMON.CATALYST_ARMOR.armor.get(); }
    @Override public float getToughness() { return EConfig.COMMON.CATALYST_ARMOR.attributesConfig.toughness.get().floatValue(); }
    public float getKnockbackResistance() { return EConfig.COMMON.CATALYST_ARMOR.attributesConfig.knockback.get().floatValue(); }
    public float getAttackSpeed() { return EConfig.COMMON.CATALYST_ARMOR.attributesConfig.attackSpeed.get().floatValue(); }
    public float getMovementSpeed() { return EConfig.COMMON.CATALYST_ARMOR.attributesConfig.movementSpeed.get().floatValue(); }

    @Override public int getMaxDamage(ItemStack stack) { return HEALTH_FUNCTION_FOR_TYPE.get(getType()) * EConfig.COMMON.CATALYST_ARMOR.getDurability(); }
    @Override public int getEnchantmentValue(ItemStack stack) { return EConfig.COMMON.CATALYST_ARMOR.getEnchantability(); }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        CatalystCoreUtil(stack).tooltip(tooltip);
        if (Screen.hasShiftDown()) {
            getEquippedElytra(stack).ifPresent(core -> tooltip.add(Component.translatable("item.elementus.catalyst_chestplate.elytra_equipped").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE)));
            if (getTrimVisibility(stack) > 1) {
                tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.trim_diabled", "Trim hidden.").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
            } else if (getTrimVisibility(stack) == 2) {
                tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.trim_enabled", "Trim visible.").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
            }
        } else if (hasElytra(stack) || isTrimmed(stack)) {
            tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.show_more", "Press shift for more.").withStyle(ChatFormatting.DARK_GRAY));
        }

        if (getContentsL(stack).findAny().isPresent()) tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.core_equipped_legacy", "Catalyst Core NBT tag has change, please remove core!").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.UNDERLINE));
        if (getElytraEquiped(stack).findAny().isPresent()) tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.elytra_equipped_legacy", "Elytra NBT tag has change, please remove elytra!").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.UNDERLINE));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.CHEST).equals(stack))
                CatalystCoreUtil(player).passiveTick(stack, level, entity, slotId, isSelected);
        }
    }

    @Override public boolean canBeHurtBy(@NotNull DamageSource source) { return false; }

    @Override public int getEntityLifespan(ItemStack stack, Level level) { return Integer.MAX_VALUE; }
    @Override public boolean canBeDepleted() { return !EConfig.COMMON.CATALYST_ARMOR.hasDurability.get(); }
    @Override public boolean isRepairable(@NotNull ItemStack stack) { return EConfig.COMMON.CATALYST_ARMOR.hasDurability.get(); }

    @Override public boolean canElytraFly(ItemStack stack, LivingEntity entity) { return hasElytra(stack); }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (!EConfig.COMMON.CATALYST_ARMOR.hasDurability.get()) {
                    if (nextFlightTick % 20 == 0) {
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
        return !EConfig.COMMON.CATALYST_ARMOR.hasDurability.get() ? super.canApplyAtEnchantingTable(stack, enchantment) : enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override public boolean isEnchantable(@NotNull ItemStack stack) { return this.getMaxStackSize(stack) == 1; }

    // Insert Code

    //StackOnMouseClicked (IS on Mouse)
    public boolean overrideStackedOnOther(ItemStack chestplate, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (chestplate.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack slotStack = slot.getItem();
            if (slotStack.isEmpty()) {
                if (checkElytraEquiped(chestplate) > 0) {
                    removeEquipedElytra(chestplate).ifPresent((item) -> {
                        insertStack(chestplate, slot.safeInsert(item));
                    });
                } else if (getContentLWeight(chestplate) > 0) {
                    removeCoreL(chestplate).ifPresent((item) -> {
                        insertStack(chestplate, slot.safeInsert(item));
                    });
                } else {
                    takeStack(chestplate).ifPresent((coreItem) -> {
                        playUnequipSound(player, chestplate, coreItem, true);
                        insertStack(chestplate, slot.safeInsert(coreItem));
                    });
                }
            } else if (!slotStack.isEmpty() && canEquipCore(chestplate, slotStack) || canEquipElytra(chestplate, slotStack)) {
                playEquipSound(player, chestplate, slotStack, true);
                insertStack(chestplate, slot.safeTake(slotStack.getCount(), 1, player));
            }

            return true;
        }
    }
    //StackOnSlotClicked (Not on Mouse)
    public boolean overrideOtherStackedOnMe(ItemStack chestplate, @NotNull ItemStack mouseStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (chestplate.getCount() != 1) return false;
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (mouseStack.isEmpty()) {
                if (Selection.get(chestplate) > 1) {
                    int i = getTrimVisibility(chestplate);
                    setTrimVisibility(chestplate, i > 1 ? 1 : 2);
                } else if (checkElytraEquiped(chestplate) > 0) {
                    removeEquipedElytra(chestplate).ifPresent((item) -> {
                        access.set(item);
                    });
                } else if (getContentLWeight(chestplate) > 0) {
                    removeCoreL(chestplate).ifPresent((item) -> {
                        access.set(item);
                    });
                } else {
                    takeStack(chestplate).ifPresent((core) -> {
                        playUnequipSound(player, chestplate, core, true);
                        access.set(core);
                    });
                }
            } else {
                int i = insertStack(chestplate, mouseStack);
                playEquipSound(player, chestplate, mouseStack, i > 0);
                mouseStack.shrink(i);
            }
            return true;
        } else return false;
    }

    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        NonNullList<ItemStack> core = NonNullList.create();
        NonNullList<ItemStack> elytra = NonNullList.create();
        core.addAll(getEquippedCore(stack).stream().toList());
        elytra.addAll(getEquippedElytra(stack).stream().toList());
        return Optional.of(new CatalystTooltip(core, elytra, CatalystItemUtil.Selection.get(stack), CatalystItemUtil.getTrimVisibility(stack), stack));
    }

    @Override public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        ItemUtils.onContainerDestroyed(itemEntity, getCatalystContents(itemEntity.getItem()));
    }

    private void playEquipSound(Player entity, ItemStack chestStack, ItemStack coreStack, boolean playSound) {
        if (playSound) {
            if (coreStack.is(EItemTags.CATALYST_ELYTRA)) entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
            else CatalystCoreUtil(entity).getCore().equipSound(entity, chestStack, coreStack);
        }
    }
    private void playUnequipSound(Player entity, ItemStack chestStack, ItemStack coreStack, boolean playSound) {
        if (playSound) {
            if (coreStack.is(EItemTags.CATALYST_ELYTRA)) entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
            else CatalystCoreUtil(chestStack).getCoreFromString(getCoreInstance(chestStack)).unequipSound(entity, chestStack, coreStack);
        }
    }

    @Override public void onItemScroll(ItemStack stack, int scrollAmount) {
        Selection.select(stack, Mth.clamp(scrollAmount, -1, 1));
    }
}