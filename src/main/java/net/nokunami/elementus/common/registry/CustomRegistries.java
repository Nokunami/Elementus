package net.nokunami.elementus.common.registry;

import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.CatalystArmorAttributes;
import net.nokunami.elementus.common.catalystCore.ability.AbilityHolder;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.item.unique.CatalystItemUtil;

import java.util.List;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomRegistries {
    public static final ResourceLocation CATALYST_CORE_RL = modLoc("catalyst_core");
    public static final ResourceKey<Registry<CatalystCore>> CATALYST_CORE_KEY = ResourceKey.createRegistryKey(CATALYST_CORE_RL);
    public static final ResourceLocation ABILITY_RL = modLoc("catalyst_ability");
    public static final ResourceKey<Registry<AbstractActiveAbility>> ABILITY_KEY = ResourceKey.createRegistryKey(ABILITY_RL);

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void customRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<CatalystCore>().setName(CATALYST_CORE_RL));
        event.create(new RegistryBuilder<AbstractActiveAbility>().setName(ABILITY_RL));
    }

    public static final CatalystCore fallBackCore = new CatalystCore(() -> new ItemStack(Items.BARRIER), ChatFormatting.DARK_PURPLE);
    public static final AbstractActiveAbility fallbackAbility = new AbstractActiveAbility(new AbstractActiveAbility.AbilityProperties(AbilityType.PASSIVE)) {
        @Override public boolean castAbility(LivingEntity entity, Level level, CAbility ca) { return false; }
    };

    public static CatalystCore getCatalystCore(ItemStack stack) {
        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
        if (stream1.isPresent()) {
            return stream1.get().getValue();
        }
        return fallBackCore;
    }

    public static AbstractActiveAbility getAbility(String s) {
        var id = RegistryManager.ACTIVE.getRegistry(ABILITY_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().get().toString().equals(s)).findAny();
        if (stream1.isPresent()) {
            return stream1.get().getValue();
        }
        return fallbackAbility;
    }

//    public static String getCatalystId(ItemStack stack) {
//        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
//        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
//        return stream1.map(entry -> entry.getKey().location().getPath()).orElse("missing");
//    }

//    public static CatalystCore getCoreInstance(String string) {
//        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
//        var stream1 = id.stream().filter(c -> c.getValue().getId().equals(string)).findAny();
//        return stream1.isPresent() ? stream1.get().getValue() : fallBackCore;
//    }

//    public static List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility(ItemStack stack) {
//        return getCatalystCore(stack).getPassiveAbility();
//    }

//    public static AbstractActiveAbility getActiveAbility(ItemStack stack, int slot) {
//        return getCatalystCore(stack).getActiveAbility(slot);
//    }

//    public static List<AbstractActiveAbility> getActiveAbilityList(ItemStack stack) {
//        return getCatalystCore(stack).getActiveAbilityList();
//    }
//    public static List<AbilityHolder> getActiveAbilityList(ItemStack stack) {
//        return getCatalystCore(stack).getActiveAbilityList();
//    }

//    public static Multimap<Attribute, AttributeModifier> getCatalystAttribute(ItemStack stack) {
//        return getCatalystCore(stack).getAttributes().get();
//    }

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
            var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
//            var stream1 = id.stream().filter(c -> c.getValue().getDescriptionId().equals(s)).findAny();
            var stream1 = id.stream().filter(c -> c.getKey().location().toString().equals(s)).findAny();
            if (stream1.isPresent()) {
                return stream1.get().getValue();
            }
            return fallBackCore;}

        public String getCoreInstance() { return CatalystItemUtil.getCoreInstance(getChestplate()); }
        public String getCoreId() { return getCore().getDescriptionId(); }

        public void tooltip(List<Component> tooltip) { if (hasCore()) getCore().tooltip(chestStack, tooltip); }

        public Multimap<Attribute, AttributeModifier> getAttributes(EquipmentSlot slot, ItemStack stack) {
            return hasCore() ? getCore().getAttributes(slot, stack).get() : CatalystArmorAttributes.baseAttributes().build();
        }

        public List<AbilityHolder> getAbilities() { return getCore().getActiveAbilityList(); }
        public AbstractActiveAbility getAbility(int i) { return getCore().getActiveAbility(i); }
        public List<Pair<PassiveCatalystAbility, Float>> getPassiveAbilities() { return getCore().getPassiveAbility(); }

        public void passiveTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
            getCore().tick(stack, level, entity, slotId, isSelected);
        }
        public void postHurt(LivingHurtEvent event) { getCore().postHurt(entity, entity.level(), event); }
        public void postDamage(LivingDamageEvent event) { getCore().postDamage(entity, entity.level(), event); }
        public void postDeath(LivingDeathEvent event) { getCore().postDeath(entity, entity.level(), event); }
    }
}
