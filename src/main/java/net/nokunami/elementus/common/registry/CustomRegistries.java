package net.nokunami.elementus.common.registry;

import com.google.common.collect.Multimap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.common.catalystCore.AbstractActiveAbility;
import net.nokunami.elementus.common.catalystCore.PassiveCatalystAbility;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;

import java.util.List;
import java.util.Map;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CustomRegistries {
    public static final ResourceLocation CATALYST_CORE_RL = modLoc("catalyst_core");
    public static final ResourceKey<Registry<CatalystCore>> CATALYST_CORE_KEY = ResourceKey.createRegistryKey(CATALYST_CORE_RL);

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void customRegistry(NewRegistryEvent event) {
        event.create(new RegistryBuilder<CatalystCore>().setName(CATALYST_CORE_RL));
    }

    public static final CatalystCore fallBackCore = new CatalystCore(() -> new ItemStack(Items.BARRIER), ChatFormatting.DARK_PURPLE);

    public static CatalystCore getCatalystCore(ItemStack stack) {
        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
        if (stream1.isPresent()) {
            return stream1.get().getValue();
        }
        return fallBackCore;
    }

    public static String getCatalystId(ItemStack stack) {
        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
        return stream1.map(entry -> entry.getKey().location().getPath()).orElse("missing");
    }

    public static CatalystCore getCoreInstance(String string) {
        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getId().equals(string)).findAny();
        return stream1.isPresent() ? stream1.get().getValue() : fallBackCore;
    }

    public static List<Pair<PassiveCatalystAbility, Float>> getPassiveAbility(ItemStack stack) {
        return getCatalystCore(stack).getPassiveAbility();
    }

    public static AbstractActiveAbility getCatalystAbility1(ItemStack stack, int slot) {
        return getCatalystCore(stack).getActiveAbility(slot);
    }

    public static Multimap<Attribute, AttributeModifier> getCatalystAttribute(ItemStack stack) {
        return getCatalystCore(stack).getAttributes().get();
    }
}
