package net.nokunami.elementus.common.registry;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.ability.AbstractAbility;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ERegistries {
    public static final ResourceLocation CATALYST_CORE = modLoc("catalyst_core");
    public static final ResourceLocation ABILITY = modLoc("catalyst_ability");

    @SubscribeEvent
    public static void customRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<CatalystCore>().setName(CATALYST_CORE));
        event.create(new RegistryBuilder<AbstractAbility>().setName(ABILITY));
    }

    public static final CatalystCore fallBackCore = new CatalystCore(() -> new ItemStack(Items.BARRIER), ChatFormatting.DARK_PURPLE);

    public static IForgeRegistry<CatalystCore> catalystCores() { return RegistryManager.ACTIVE.getRegistry(Keys.CATALYST_CORE); }
    public static IForgeRegistry<AbstractAbility> abilities() { return RegistryManager.ACTIVE.getRegistry(Keys.ABILITY); }

    public static CatalystCore getCatalystCore(ItemStack stack) {
        var id = catalystCores().getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
        if (stream1.isPresent()) {
            return stream1.get().getValue();
        }
        return fallBackCore;
    }

    @Nullable
    public static AbstractAbility getAbility(String s) {
        var id = abilities().getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().get().toString().equals(s)).findAny();
        return stream1.map(Map.Entry::getValue).orElse(null);
    }

    public static class CatalystCoreHelper {
        LivingEntity entity;
        ItemStack chestStack;

        public static CatalystCoreHelper CatalystCoreUtil(LivingEntity entity) { return new CatalystCoreHelper(entity); }
        public static CatalystCoreHelper CatalystCoreUtil(ItemStack stack) { return new CatalystCoreHelper(stack); }

        public CatalystCoreHelper(LivingEntity livingEntity) {
            entity = livingEntity;
            chestStack = entity.getItemBySlot(EquipmentSlot.CHEST);
        }
        public CatalystCoreHelper(ItemStack chest) { chestStack = chest; }

        public boolean hasCore() { return !getCoreStack().isEmpty(); }

        public ItemStack getChestplate() { return entity != null ? entity.getItemBySlot(EquipmentSlot.CHEST) : chestStack; }
        public ItemStack getCoreStack() { return CatalystItemUtil.getEquippedCore(getChestplate()).orElse(ItemStack.EMPTY); }

        public CatalystCore getCore() { return getCatalystCore(getCoreStack()); }
        public CatalystCore getCoreFromString(String s) {
            var id = catalystCores().getEntries();
//            var stream1 = id.stream().filter(c -> c.getValue().getDescriptionId().equals(s)).findAny();
            var stream1 = id.stream().filter(c -> c.getKey().location().toString().equals(s)).findAny();
            if (stream1.isPresent()) {
                return stream1.get().getValue();
            }
            return fallBackCore;
        }

        public String getCoreInstance() { return CatalystItemUtil.getCoreInstance(getChestplate()); }
        public String getCoreId() { return getCore().getDescriptionId(); }

        public void tooltip(List<Component> tooltip) { if (hasCore()) getCore().tooltip(chestStack, tooltip); }

        public Multimap<Attribute, AttributeModifier> getAttributes(EquipmentSlot slot, ItemStack stack) {
            return hasCore() ? getCore().getAttributes(slot, stack).get() : CatalystArmorAttributes.baseAttributes().build();
        }

//        public List<PassiveAbility> getPassiveAbilities() { return getCore().getPassiveAbilities(); }
//        public List<AbstractAbility> getActiveAbilities() { return getCore().getActiveAbilities(); }
        public List<AbstractAbility> getAbilities() { return getCore().getAbilities(); }

//        public void hurtEvent(LivingHurtEvent event) { getCore().onHurt(entity, entity.level(), event); }
//        public void damageEvent(LivingDamageEvent event) { getCore().onDamage(entity, entity.level(), event); }
//        public void deathEvent(LivingDeathEvent event) { getCore().onDeath(entity, entity.level(), event); }

        public void onCoreEquip(LivingEntity entity) {
            if (entity instanceof Player player)
                getCore().onCoreEquip(getCoreStack(), player);
        }
        public void onCoreRemove(LivingEntity entity) {
            if (entity instanceof Player player)
                getCore().onCoreRemove(getCoreStack(), player);
        }
    }

    public static class Keys {
        public static final ResourceKey<Registry<CatalystCore>> CATALYST_CORE = ResourceKey.createRegistryKey(ERegistries.CATALYST_CORE);
        public static final ResourceKey<Registry<AbstractAbility>> ABILITY = ResourceKey.createRegistryKey(ERegistries.ABILITY);
    }
}
