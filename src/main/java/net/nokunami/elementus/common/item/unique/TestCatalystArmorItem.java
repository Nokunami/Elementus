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
import net.nokunami.elementus.api.IAbilitySupplier;
import net.nokunami.elementus.client.extensions.ICatalystTrim;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.common.item.IScrollableItem;
import net.nokunami.elementus.common.item.basic.EArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;
import net.nokunami.elementus.common.tags.CatalystTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;
import static net.nokunami.elementus.common.registry.ERegistries.CatalystCoreHelper.CatalystCoreUtil;

public class TestCatalystArmorItem extends EArmorItem implements ICatalystTrim, IScrollableItem, IAbilitySupplier {

    public TestCatalystArmorItem(Properties properties) {
        this(EArmorMaterials.CATALYST, Type.CHESTPLATE, properties);
    }

    public TestCatalystArmorItem(EArmorMaterials armorMaterial, Type type, Properties properties) {
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
    public void initializeClient(@NotNull Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) EClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        var helper = CatalystCoreUtil(stack);
        if (helper.hasCore()) return helper.getCore().getBaseTexture(stack, entity, slot, type);
        return EID + ":textures/models/catalyst/catalyst_chestplate.png";
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot == type.getSlot() ? CatalystCoreUtil(stack).getAttributes(slot, stack) : super.getAttributeModifiers(slot, stack);
    }

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

//    @Override
//    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
//        if (!level.isClientSide && entity instanceof Player player) {
//            if (player.getItemBySlot(EquipmentSlot.CHEST).equals(stack))
//                CatalystCoreUtil(player).passiveTick(stack, level, slotId, isSelected);
//        }
//    }

    @Override public boolean canBeHurtBy(@NotNull DamageSource source) { return false; }

    @Override public int getEntityLifespan(ItemStack stack, Level level) { return Integer.MAX_VALUE; }
    @Override public boolean canBeDepleted() { return !EConfig.catalystConfig.baseCatalyst.durability.get().component2(); }
    @Override public boolean isRepairable(@NotNull ItemStack stack) { return EConfig.catalystConfig.baseCatalyst.durability.get().component2(); }

    @Override public boolean canElytraFly(ItemStack stack, LivingEntity entity) { return hasElytra(stack); }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (!EConfig.catalystConfig.baseCatalyst.durability.get().component2()) {
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
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return Mth.clamp(amount, 0, stack.getMaxDamage() - stack.getDamageValue() - 1);
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
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
                    removeEquipedElytra(chestplate).ifPresent((item) -> insertStack(chestplate, slot.safeInsert(item), player));
                } else if (getContentLWeight(chestplate) > 0) {
                    removeCoreL(chestplate).ifPresent((item) -> insertStack(chestplate, slot.safeInsert(item), player));
                } else {
                    takeStack(chestplate, player).ifPresent(core -> {
                        playUnequipSound(player, chestplate, core, true);
                        insertStack(chestplate, slot.safeInsert(core), player);
                    });
                }
            } else if (!slotStack.isEmpty() && canEquipCore(chestplate, slotStack) || canEquipElytra(chestplate, slotStack)) {
                insertStack(chestplate, slot.safeTake(slotStack.getCount(), 1, player), player);
                playEquipSound(player, chestplate, slotStack, true);
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
                    removeEquipedElytra(chestplate).ifPresent(access::set);
                } else if (getContentLWeight(chestplate) > 0) {
                    removeCoreL(chestplate).ifPresent(access::set);
                } else {
                    takeStack(chestplate, player).ifPresent(core -> {
                        playUnequipSound(player, chestplate, core, true);
                        access.set(core);
                    });
                }
            } else {
                int i = insertStack(chestplate, mouseStack, player);
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
            if (coreStack.is(CatalystTags.CATALYST_ELYTRA)) entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
            else CatalystCoreUtil(entity).getCore().equipSound(entity, chestStack, coreStack);
        }
    }
    private void playUnequipSound(Player entity, ItemStack chestStack, ItemStack coreStack, boolean playSound) {
        if (playSound) {
            if (coreStack.is(CatalystTags.CATALYST_ELYTRA)) entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
            else CatalystCoreUtil(chestStack).getCoreFromString(getCoreInstance(chestStack)).unequipSound(entity, chestStack, coreStack);
        }
    }

    @Override public void onItemScroll(ItemStack stack, int scrollAmount) {
        Selection.select(stack, Mth.clamp(scrollAmount, -1, 1), isTrimmed(stack) ? 2 : 1);
    }

    @Override
    public List<AbstractAbility> getAbilities(ItemStack stack) {
        return CatalystCoreUtil(stack).getAbilities();
    }
}