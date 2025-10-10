package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.item.ArmorItem;
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
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.api.ICatalystTrim;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.catalystCore.CoreArmorAttributes;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ESoundEvents;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;

public class TestCatalystArmorItem extends ArmorItem implements ICatalystTrim {
    public final ModArmorMaterials material;
    public static Multimap<Attribute, AttributeModifier> defaultModifiers;



    public TestCatalystArmorItem(ModArmorMaterials material, Type type, Properties properties) {
        super(material, type, properties);
        this.material = material;
        defaultModifiers = CoreArmorAttributes.baseAttributes().build();
        initClient();
    }

    private Object renderTrimProperties;

    public Object getRenderTrimPropertiesInternal() {
        return renderTrimProperties;
    }

    private void initClient() {
        // Minecraft instance isn't available in datagen, so don't call initializeClient if in datagen
        if (FMLEnvironment.dist == Dist.CLIENT && !FMLLoader.getLaunchHandler().isData()) {
            initializeClientTrim(properties -> this.renderTrimProperties = properties);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClientTrim(Consumer<ICatalystTrim> consumer) {
        consumer.accept((ICatalystTrim) ElementusClient.PROXY.getArmorCatalystTrimRenderProperties());
    }

    @Override
    public @NotNull ModArmorMaterials getMaterial() {
        return this.material;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Optional<ItemStack> core = getEquippedCore(stack);
        return slot == type.getSlot() ? core.isPresent() ? CustomRegistries.getCatalystAttribute(core.get()) : defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept((IClientItemExtensions) ElementusClient.PROXY.getArmorRenderProperties());
    }

    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if (getEquippedCore(stack).isPresent()) return CustomRegistries.getCatalystAbility(getEquippedCore(stack).get()).getBaseTexture(stack);
        return MODID + ":textures/models/armor/catalyst/catalyst_chestplate.png";
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        if (getEquippedCore(stack).isPresent()) {
            CustomRegistries.getCatalystAbility(getEquippedCore(stack).get()).tooltip(getEquippedCore(stack).get(), tooltip);
        }
        if (getEquippedElytra(stack).isPresent()) {
            tooltip.add(Component.translatable("item.elementus.catalyst_chestplate.elytra_equipped").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.UNDERLINE));
        }
        if (getContentsL(stack).findAny().isPresent()) {
            tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.core_equipped_legacy", "Catalyst Core NBT tag has change, please remove core!").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.UNDERLINE));
        }
        if (getElytraEquiped(stack).findAny().isPresent()) {
            tooltip.add(Component.translatableWithFallback("item.elementus.catalyst_chestplate.elytra_equipped_legacy", "Elytra NBT tag has change, please remove elytra!").withStyle(ChatFormatting.DARK_RED).withStyle(ChatFormatting.UNDERLINE));
        }
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if (!level.isClientSide && entity instanceof Player player) {
            if (player.getItemBySlot(EquipmentSlot.CHEST).equals(stack)) {
//                getEquippedCore(stack).ifPresent(core -> CatalystCore.getInstance(core).tick(entity, level));
                getEquippedCore(stack).ifPresent(core -> CustomRegistries.getCatalystAbility(core).tick(level, entity));
            }
        }
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

    @Override
    public boolean canElytraFly(ItemStack stack, LivingEntity entity) {
        return getEquippedElytra(stack).isPresent();
    }

    @Override
    public boolean elytraFlightTick(ItemStack stack, LivingEntity entity, int flightTicks) {
        if (!entity.level().isClientSide) {
            int nextFlightTick = flightTicks + 1;
            if (nextFlightTick % 10 == 0) {
                if (!ModConfig.COMMON.catalystArmorDurability.get()) {
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
        return !ModConfig.COMMON.catalystArmorDurability.get() ? super.canApplyAtEnchantingTable(stack, enchantment) : enchantment != Enchantments.MENDING && super.canApplyAtEnchantingTable(stack, enchantment);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack pStack) {
        return this.getMaxStackSize(pStack) == 1;
    }

    // Insert Code

    //StackOnSlotClicked (Not on Mouse)
    public boolean overrideStackedOnOther(ItemStack coreStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player) {
        if (coreStack.getCount() != 1 || action != ClickAction.SECONDARY) {
            return false;
        } else {
            ItemStack itemstack = slot.getItem();
            if (itemstack.isEmpty()) {
                if (checkElytraEquiped(coreStack) > 0) {
                    removeEquipedElytra(coreStack).ifPresent((item) -> {
                        playUnequipSound(player, coreStack);
                        equipElytraL(coreStack, slot.safeInsert(item));
                    });
                } else if (getContentLWeight(coreStack) > 0) {
                    removeCoreL(coreStack).ifPresent((item) -> {
                        playUnequipSound(player, coreStack);
                        insertCoreL(coreStack, slot.safeInsert(item));
                    });
                } else {
                    takeStack(coreStack).ifPresent((coreItem) -> {
                        playUnequipSound(player, coreStack);
                        insertStack(coreStack, slot.safeInsert(coreItem));
                    });
                }
            } else if (itemstack.getItem().canFitInsideContainerItems()) {
                int i = (2 - getContentAmount(coreStack)) / getWeight(itemstack);
                int inserted = insertStack(coreStack, slot.safeTake(itemstack.getCount(), i, player));
                if (inserted > 0) {
                    playEquipSound(player, coreStack);
                }
            }

            return true;
        }
    }
    //StackOnMouseClicked (IS on Mouse)
    public boolean overrideOtherStackedOnMe(ItemStack coreStack, @NotNull ItemStack mouseStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
        if (coreStack.getCount() != 1) return false;
        if (action == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (mouseStack.isEmpty()) {
                if (checkElytraEquiped(coreStack) > 0) {
                    removeEquipedElytra(coreStack).ifPresent((item) -> {
                        this.playUnequipSound(player, coreStack);
                        access.set(item);
                    });
                } else if (getContentLWeight(coreStack) > 0) {
                    removeCoreL(coreStack).ifPresent((item) -> {
                        this.playUnequipSound(player, coreStack);
                        access.set(item);
                    });
                } else {
                    takeStack(coreStack).ifPresent((core) -> {
                        this.playUnequipSound(player, coreStack);
                        access.set(core);
                    });
                }
            } else {
                int i = insertStack(coreStack, mouseStack);
                if (i > 0) {
                    this.playEquipSound(player, coreStack);
                    mouseStack.shrink(i);
                }
            }

            return true;
        } else return false;
    }

    public @NotNull Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
        NonNullList<ItemStack> core = NonNullList.create();
        NonNullList<ItemStack> elytra = NonNullList.create();
        core.addAll(getEquippedCore(stack).stream().toList());
        elytra.addAll(getEquippedElytra(stack).stream().toList());
        return Optional.of(new CatalystTooltip(core, elytra));
    }

    @Override
    public void onDestroyed(ItemEntity itemEntity, DamageSource damageSource) {
        ItemUtils.onContainerDestroyed(itemEntity, getCatalystContents(itemEntity.getItem()));
    }

    private void playEquipSound(Player entity, ItemStack stack) {
        if (stack.is(Etags.Items.CATALYST_ELYTRA))
            entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
        else entity.playSound(ESoundEvents.CATALYST_ARMOR_ACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
    private void playUnequipSound(Player entity, ItemStack stack) {
        if (stack.is(Etags.Items.CATALYST_ELYTRA))
            entity.playSound(SoundEvents.ARMOR_EQUIP_ELYTRA, 0.75F, 1);
        else entity.playSound(ESoundEvents.CATALYST_ARMOR_DEACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}