package net.nokunami.elementus.common.catalystCore.core;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLLoader;
import net.nokunami.elementus.ElementusClient;
import net.nokunami.elementus.api.IClientCatalystExtension;
import net.nokunami.elementus.common.catalystCore.CoreArmorAttributes;
import net.nokunami.elementus.common.catalystCore.CoreAttributes;
import net.nokunami.elementus.common.catalystCore.ability.CatalystAbility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CatalystCore implements IClientCatalystExtension {
    public static final List<CatalystCore> CATALYST_CORE_LIST = new ArrayList<>();
    public static final Map<Item, CatalystCore> CORE_ITEM_MAP = new HashMap<>();
    public final String id;
    private final Item item;
    private final CoreAttributes coreAttributes;

    public CatalystCore(String id, Item item, CoreAttributes attributes) {
        this(id, () -> new ItemStack(item), attributes);
    }

    public CatalystCore(String id, Supplier<ItemStack> itemSupplier, CoreAttributes attributes) {
        this.id = id;
        item = itemSupplier.get().getItem();
        coreAttributes = attributes;
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
        return id != null ? id : "missing";
    }
    public ItemStack getCoreStack() {
        return new ItemStack(item);
    }
    public CatalystAbility getAbility() {
        return coreAttributes.getAbility();
    }
//    public String getTexture() {
//        return getAbility().getBaseTexture();
//    }
    public CatalystAbility getEmissiveTexture() {
        return coreAttributes.getAbility();
    }

    public static boolean filter(ItemStack stack) {
        return CORE_ITEM_MAP.containsKey(stack.getItem());
    }

    public static CatalystCore getInstance(ItemStack stack) {
        return CORE_ITEM_MAP.get(stack.getItem());
    }

    public Lazy<Multimap<Attribute, AttributeModifier>> getAttributes() {
        return coreAttributes.getAbility().getAttributes() != null ? coreAttributes.getAbility().getAttributes() : Lazy.of(CoreArmorAttributes.baseAttributes()::build);
    }

    public void tick(Entity entity, Level level) {
        CatalystAbility ability = coreAttributes.getAbility();
        if (ability != null)
            ability.tick(level, entity);
    }

    public void postDeathEffects(Entity entity, Level level) {
        CatalystAbility ability = coreAttributes.getAbility();
        if (ability != null)
            ability.postDeathEffect(level, entity);
    }

    public void postDamageEvent(LivingDamageEvent event) {
        coreAttributes.getAbility().postDamageEvent(event);
    }

    public void postDeathEvent(LivingDeathEvent event) {
        coreAttributes.getAbility().postDeathEvent(event);
    }

    public ChatFormatting tooltipColor() {
        return coreAttributes.getTooltipColor();
    }
    public int descNum() {
        return coreAttributes.getDescriptionNumber();
    }

}
