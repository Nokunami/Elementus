package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.ability.PassiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;
import net.nokunami.elementus.common.registry.ERegistries;
import net.nokunami.elementus.common.registry.ESounds;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CatalystCore implements IClientCatalystExtension {
    public static final List<CatalystCore> CATALYST_CORE_LIST = new ArrayList<>();
    public static final Map<Item, CatalystCore> CORE_ITEM_MAP = new HashMap<>();
    public static final Map<ItemStack, CatalystCore> CORE_ITEMSTACK_MAP = new HashMap<>();
    private final Item item;
    public final ItemStack itemStack;
    private final CatalystCoreAttributes coreAttributes;
    private final ChatFormatting chatFormatting;
    private final Lazy<Multimap<Attribute, AttributeModifier>> armorAttributes;
    String id;

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting) {
        this(itemSupplier, formatting, new CatalystCoreAttributes.Builder().build());
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, CatalystCoreAttributes coreAttributes) {
        this(itemSupplier, formatting, CatalystArmorAttributes.baseAttributes(), coreAttributes);
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, ImmutableMultimap.Builder<Attribute, AttributeModifier> armorAttributes) {
        this(itemSupplier, formatting, armorAttributes, new CatalystCoreAttributes.Builder().build());
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, ImmutableMultimap.Builder<Attribute, AttributeModifier> catalystArmorAttributes, CatalystCoreAttributes catalystCoreAttributes) {
        item = itemSupplier.get().getItem();
        itemStack = itemSupplier.get();
        chatFormatting = formatting;
        armorAttributes = Lazy.of(catalystArmorAttributes::build);
        coreAttributes = catalystCoreAttributes;
        if (itemSupplier.get() != null)
            CATALYST_CORE_LIST.add(this);
        initClient();
    }

    private Object renderProperties;
    public Object getRenderPropertiesInternal() { return renderProperties; }

    private void initClient() {
        // Minecraft instance isn't available in datagen, so don't call initializeClient if in datagen
        if ((FMLEnvironment.dist == Dist.CLIENT) && !FMLLoader.getLaunchHandler().isData())
            initializeClient(properties -> renderProperties = properties);
    }

    public void initializeClient(Consumer<IClientCatalystExtension> consumer) {
        consumer.accept((IClientCatalystExtension) EClient.PROXY.getArmorCatalystRenderProperties());
    }

//    public String getPath() { return get().getPath(); }

    public String getId() { return id; }
    public String getOrCreateDescriptionId() { return id == null ? Util.makeDescriptionId("catalyst_core", getKey()) : id; }
    public String getDescriptionId() { return getOrCreateDescriptionId(); }
    public Component getDescription() { return Component.translatable(getDescriptionId()); }

    public ResourceLocation getKey() { return ERegistries.catalystCores().getKey(this); }
//    public String getWithNamespace() { return getKey().getNamespace() != null ? getKey().toString() : "test" + "." + getKey().getPath(); }
    public String getWithNamespace() { return getKey().toString(); }

    public ItemStack getCoreStack() { return new ItemStack(item); }
    public List<AbstractAbility> getAbilities() { return coreAttributes.getAllAbilities(); }
//    public List<PassiveAbility> getPassiveAbilities() { return coreAttributes.getPassiveAbilities(); }
//    public List<AbstractAbility> getActiveAbilities() { return coreAttributes.getActiveAbilities(); }

    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        Optional<ItemStack> core = getEquippedCore(stack);
        String base = EID + ":textures/models/catalyst/catalyst_chestplate.png";
        String coreTexture = "%s:textures/models/catalyst/catalyst_%s.png";
        return core.map(itemStack -> String.format(Locale.ROOT, coreTexture, getKey().getNamespace(), getKey().getPath())).orElse(base);
    }

    public static boolean filter(ItemStack stack) { return CORE_ITEM_MAP.containsKey(stack.getItem()); }

    public ChatFormatting tooltipColor() { return chatFormatting; }

    /**
     * <p>Tooltip Provider for Catalyst Core</p>
     * <p>Override this for custom tooltips</p>
     */
    public void tooltip(ItemStack stack, @NotNull List<Component> tooltip) {
        ChatFormatting tooltipColor = tooltipColor();
        tooltip.add(getDescription().copy().withStyle(tooltipColor));
        tooltip.add(Component.translatable(getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(CatalystItemUtil.getCoreInstance(stack)));
    }

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes(EquipmentSlot slot, ItemStack stack) { return armorAttributes; }

    public void onCoreEquip(ItemStack stack, LivingEntity entity) {
    }

    public void onCoreRemove(ItemStack stack, LivingEntity entity) {
    }

//    public void tick(ItemStack stack, Level level, LivingEntity entity, int slotId, boolean isSelected) {
//        var data = AbilityData.instance(entity);
//        for (AbstractAbility ability : getPassiveAbilities()) {
//            if (!data.isOnCooldown(ability))
//                ability.tick(entity, level);
//        }
//    }

//    public void onHurt(LivingEntity entity, Level level, LivingHurtEvent event) {
//        var data = AbilityData.instance(entity);
//        for (PassiveAbility ability : getPassiveAbilities()) {
//            if (!data.isOnCooldown(ability)) {
//                ability.onHurtEvent(event);
//                if (ability.applyCooldownOnHurt(entity))
//                    data.addCooldown(ability);
//            }
//        }
//    }
//
//    public void onDamage(LivingEntity entity, Level level, LivingDamageEvent event) {
//        var data = AbilityData.instance(entity);
//        for (PassiveAbility ability : getPassiveAbilities()) {
//            if (!data.isOnCooldown(ability)) {
//                ability.onDamageEvent(event);
//                if (ability.applyCooldownOnDamage(entity))
//                    data.addCooldown(ability);
//            }
//        }
//    }
//
//    public void onDeath(LivingEntity entity, Level level, LivingDeathEvent event) {
//        var data = AbilityData.instance(entity);
//        for (PassiveAbility ability : getPassiveAbilities()) {
//            if (!data.isOnCooldown(ability)) {
//                ability.onDeathEvent(event);
//                if (ability.applyCooldownOnDeath(entity))
//                    data.addCooldown(ability);
//            }
//        }
//    }

    public void equipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(ESounds.CATALYST_ARMOR_ACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public void unequipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(ESounds.CATALYST_ARMOR_DEACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
