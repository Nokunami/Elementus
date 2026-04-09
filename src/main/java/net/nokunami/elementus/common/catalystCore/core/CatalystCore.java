package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.EClient;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CatalystCoreAttributes;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;
import net.nokunami.elementus.common.registry.tempCompat.CompatCoreRegistry;
import net.nokunami.elementus.common.registry.ESounds;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;
import static net.nokunami.elementus.common.registry.CustomRegistries.*;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

public class CatalystCore implements IClientCatalystExtension {
    public static final List<CatalystCore> CATALYST_CORE_LIST = new ArrayList<>();
    public static final Map<Item, CatalystCore> CORE_ITEM_MAP = new HashMap<>();
    public static final Map<ItemStack, CatalystCore> CORE_ITEMSTACK_MAP = new HashMap<>();
    private final Item item;
    public final ItemStack itemStack;
    private final CatalystCoreAttributes coreAttributes;
    private final ChatFormatting chatFormatting;
//    private final Lazy<Multimap<Attribute, AttributeModifier>> armorAttributes;
    String id;

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting) {
        this(itemSupplier, formatting, new CatalystCoreAttributes.Builder().build());
    }

//    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, CatalystCoreAttributes coreAttributes) {
//        this(itemSupplier, formatting, CatalystArmorAttributes.baseAttributes(), coreAttributes);
//    }

//    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting, ImmutableMultimap.Builder<Attribute, AttributeModifier> armorAttributes) {
//        this(itemSupplier, formatting, armorAttributes, new CatalystCoreAttributes.Builder().build());
//    }

    public CatalystCore(Supplier<ItemStack> itemSupplier, ChatFormatting formatting/*, ImmutableMultimap.Builder<Attribute, AttributeModifier> armorAttributes*/, CatalystCoreAttributes coreAttributes) {
        item = itemSupplier.get().getItem();
        itemStack = itemSupplier.get();
        chatFormatting = formatting;
//        this.armorAttributes = Lazy.of(armorAttributes::build);
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
        if ((FMLEnvironment.dist == Dist.CLIENT) && !FMLLoader.getLaunchHandler().isData())
            initializeClient(properties -> renderProperties = properties);
    }

    public void initializeClient(Consumer<IClientCatalystExtension> consumer) {
        consumer.accept((IClientCatalystExtension) EClient.PROXY.getArmorCatalystRenderProperties());
    }

//    public String getId() {
//        var i = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_RL).getKey(this);
//        return i != null ? i.getPath() : "missing";
//    }
//    public String getId() { return id; }
//    public ResourceLocation get() { return RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getKey(this); }
//    public String getNamespace() { return get().getNamespace(); }
//    public String getPath() { return get().getPath(); }
//    public String getOrCreateTranslation() { return Util.makeDescriptionId("catalyst_core", get()); }
//    public String getDescriptionId() { return getOrCreateTranslation(); }
//    public Component getDescription() { return Component.translatable(getDescriptionId()); }

    public String getId() { return id; }
    public String getOrCreateDescriptionId() {
        return id == null ? Util.makeDescriptionId("catalyst_core", get()) : id;
    }
    public String getDescriptionId() { return getOrCreateDescriptionId(); }
    public Component getDescription() { return Component.translatable(getDescriptionId()); }

    public ResourceLocation get() { return RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getKey(this); }
    public String getWithNamespace() { return get().getNamespace() != null ? get().toString() : "test" + "." + get().getPath(); }

    public ItemStack getCoreStack() { return new ItemStack(item); }
    public List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility() { return coreAttributes.getPassiveAbility(); }

    public String getBaseTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        Optional<ItemStack> core = getEquippedCore(stack);
        String base = EID + ":textures/models/armor/catalyst/catalyst_chestplate.png";
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s.png";
//        return core.map(itemStack -> String.format(Locale.ROOT, coreTexture, EID, CustomRegistries.getCatalystId(itemStack))).orElse(base);
        return core.map(itemStack -> String.format(Locale.ROOT, coreTexture, EID, get().getPath())).orElse(base);
    }

    public static boolean filter(ItemStack stack) { return CORE_ITEM_MAP.containsKey(stack.getItem()); }

    public ChatFormatting tooltipColor() { return chatFormatting; }

    /**
     * <p>Tooltip Provider for Catalyst Core</p>
     * <p>Override this for custom tooltips</p>
     */
    public void tooltip(ItemStack stack, @NotNull List<Component> tooltip) {
        CatalystCore core = CatalystCoreUtil(stack).getCore();
        ChatFormatting tooltipColor = tooltipColor();
//        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".title").withStyle(tooltipColor));
//        tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc").withStyle(ChatFormatting.GRAY));
//        tooltip.add(Component.translatable("catalyst_core.elementus." + getId() + ".title").withStyle(tooltipColor));
//        tooltip.add(Component.translatable("catalyst_core.elementus." + getId() + ".desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(getDescription().copy().withStyle(tooltipColor));
        tooltip.add(Component.translatable(getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(CatalystItemUtil.getCoreInstance(stack)));
        if (cataclysm)
            if (core.equals(CompatCoreRegistry.CataclysmCores.IGNITIUM.get()) || core.equals(CompatCoreRegistry.CataclysmCores.IGNITIUM.get())) {
//                tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc_1").withStyle(ChatFormatting.GRAY));
//                tooltip.add(Component.translatable("catalyst_core.elementus." + CustomRegistries.getCatalystId(stack) + ".desc_2").withStyle(ChatFormatting.GRAY));
//                tooltip.add(Component.translatable("catalyst_core.elementus." + getId() + ".desc_1").withStyle(ChatFormatting.GRAY));
//                tooltip.add(Component.translatable("catalyst_core.elementus." + getId() + ".desc_2").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable(getDescriptionId() + ".desc_1").withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable(getDescriptionId() + ".desc_2").withStyle(ChatFormatting.GRAY));
            }
    }

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes(EquipmentSlot slot, ItemStack stack) { return CatalystArmorAttributes.baseAttributes()::build; }

    public void onCoreRemove(ItemStack stack) { }

    public void tick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        for(Pair<PassiveCatalystAbility, Float> pair : coreAttributes.getPassiveAbility())
            if (!level.isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond())
                pair.getFirst().tick(level, entity);
//        for(AbstractActiveAbility ability : coreAttributes.getActiveAbilities()) {
//            if (ability.isType(AbilityType.PASSIVE)) ability.tick(entity, level);
//        }
        for(AbilityHolder ability : coreAttributes.getActiveAbilities().stream().filter(a -> a.getAbility().get().isType(AbilityType.PASSIVE)).toList()) {
//            AbstractActiveAbility ability1 = ability.getAbility().get();
//            if (ability1.isType(AbilityType.PASSIVE)) ability1.tick(entity, level);
            ability.getAbility().get().tick(entity, level);
        }
    }

    public void postHurt(Entity entity, Level level, LivingHurtEvent event) {
        for(Pair<PassiveCatalystAbility, Float> pair : getPassiveAbility()) {
            if (!entity.level().isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                pair.getFirst().postHurtEvent(event);
            }
        }
    }

    public void postDamage(Entity entity, Level level, LivingDamageEvent event) {
        for(Pair<PassiveCatalystAbility, Float> pair : getPassiveAbility()) {
            if (!entity.level().isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                pair.getFirst().postDamageEvent(event);
            }
        }
    }

    public void postDeath(Entity entity, Level level, LivingDeathEvent event) {
        for(Pair<PassiveCatalystAbility, Float> pair : getPassiveAbility()) {
            if (!entity.level().isClientSide && pair.getFirst() != null && level.random.nextFloat() < pair.getSecond()) {
                pair.getFirst().postDeathEvent(event);
            }
        }
    }

    public AbstractActiveAbility getActiveAbility(int slot) { return coreAttributes.getActiveAbilities().get(slot).getAbility().get(); }
    public List<AbilityHolder> getActiveAbilityList() { return coreAttributes.getActiveAbilities(); }

    public void equipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(ESounds.CATALYST_ARMOR_ACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }

    public void unequipSound(Player entity, ItemStack chestStack, ItemStack coreStack) {
        entity.playSound(ESounds.CATALYST_ARMOR_DEACTIVATE.get(), 0.75F, 0.6F + entity.level().getRandom().nextFloat() * 0.4F);
    }
}
