package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.common.catalystCore.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.CompatCoreRegistry;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.ESoundEvents;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;
import static net.nokunami.elementus.common.registry.CustomRegistries.CATALYST_CORE_RL;

public class CatalystCore implements IClientCatalystExtension {
    public static final List<CatalystCore> CATALYST_CORE_LIST = new ArrayList<>();
    public static final Map<Item, CatalystCore> CORE_ITEM_MAP = new HashMap<>();
    public static final Map<ItemStack, CatalystCore> CORE_ITEMSTACK_MAP = new HashMap<>();
    private final Item item;
    public final ItemStack itemStack;
    private final CatalystCoreAttributes coreAttributes;
    private final ChatFormatting chatFormatting;
    private final Lazy<Multimap<Attribute, AttributeModifier>> armorAttributes;

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting) {
        this(itemSupplier, formatting, CatalystArmorAttributes.baseAttributes(), new CatalystCoreAttributes.Builder().build());
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, CatalystCoreAttributes coreAttributes) {
        this(itemSupplier, formatting, CatalystArmorAttributes.baseAttributes(), coreAttributes);
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, ImmutableMultimap.Builder<Attribute, AttributeModifier> armorAttributes) {
        this(itemSupplier, formatting, armorAttributes, new CatalystCoreAttributes.Builder().build());
    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, ImmutableMultimap.Builder<Attribute, AttributeModifier> armorAttributes, CatalystCoreAttributes coreAttributes) {
        item = itemSupplier.get().getItem();
        itemStack = itemSupplier.get();
        chatFormatting = formatting;
        this.armorAttributes = Lazy.of(armorAttributes::build);
        this.coreAttributes = coreAttributes;
        if (itemSupplier.get() != null)
            CATALYST_CORE_LIST.add(this);
        initClient();
    }

    private Object renderProperties;

    public Object getRenderPropertiesInternal() {
        return renderProperties;
    }

    private void initClient() {
        // Minecraft instance isn't available in datagen, so don't call initializeClient if in datagen
        if (FMLEnvironment.dist == Dist.CLIENT && !FMLLoader.getLaunchHandler().isData()) {
            initializeClient(properties -> this.renderProperties = properties);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientCatalystExtension> consumer) {
        consumer.accept((IClientCatalystExtension) ElementusClient.PROXY.getArmorCatalystRenderProperties());
    }

    public String getId() {
        var i = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_RL).getKey(this);
        return i != null ? i.getPath() : "missing";
    }

    public ItemStack getCoreStack() {
        return new ItemStack(item);
    }
    public List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility() {
        return coreAttributes.getPassiveAbility();
    }

    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        Optional<ItemStack> core = getEquippedCore(stack);
        String base = MODID + ":textures/models/armor/catalyst/catalyst_chestplate.png";
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s.png";
        return core.map(itemStack -> String.format(Locale.ROOT, coreTexture, MODID, CustomRegistries.getCatalystId(itemStack))).orElse(base);
    }

    public static boolean filter(ItemStack stack) {
        return CORE_ITEM_MAP.containsKey(stack.getItem());
    }

    public static CatalystCore getInstance(ItemStack stack) {
        return CORE_ITEM_MAP.get(stack.getItem());
    }

    public ChatFormatting tooltipColor() {
        return chatFormatting;
    }

    /**
     * Tooltip Provider for Catalyst Core
     * <p>
     * Override this for custom tooltips
     */
    public void tooltip(ItemStack stack, @NotNull List<Component> tooltip) {
        CatalystCore core = CustomRegistries.getCatalystCore(stack);
        ChatFormatting tooltipColor = tooltipColor();
        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".title").withStyle(tooltipColor));
        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
        if (cataclysm)
            if (core.equals(CompatCoreRegistry.CataclysmCores.IGNITIUM.get()) || core.equals(CompatCoreRegistry.CataclysmCores.IGNITIUM.get())) {
                tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc_1").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc_2").withStyle(ChatFormatting.GRAY));
            }
    }

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes() {
        return armorAttributes;
    }

    public void tick(Entity entity, Level level) {
        for(Pair<PassiveCatalystAbility, Float> pair : coreAttributes.getPassiveAbility()) {
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                pair.getFirst().tick(level, entity);
            }
        }
    }

    public AbstractActiveAbility getActiveAbility(int slot) {
        return coreAttributes.getCatalystAbilities().get(slot);
    }

    public List<AbstractActiveAbility> getActiveAbilityList() {
        return coreAttributes.getCatalystAbilities();
    }

    public void equipSound(Entity entity, ItemStack stack) {
        MobUtil.playEntitySound(entity, ESoundEvents.CATALYST_ARMOR_ACTIVATE, 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
