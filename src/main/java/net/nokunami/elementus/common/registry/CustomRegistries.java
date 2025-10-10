package net.nokunami.elementus.common.registry;

import com.google.common.collect.Multimap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.NewRegistryEvent;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import net.nokunami.elementus.common.catalystCore.Abilities;
import net.nokunami.elementus.common.catalystCore.CoreAttributes;
import net.nokunami.elementus.common.catalystCore.ability.CatalystAbility;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;

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

    public static CatalystCore getCatalystCore(ItemStack stack) {
        var id = RegistryManager.ACTIVE.getRegistry(CATALYST_CORE_KEY).getEntries();
        var stream1 = id.stream().filter(c -> c.getValue().getCoreStack().is(stack.getItem())).findAny();
        if (stream1.isPresent()) {
            return stream1.get().getValue();
        }
        return new CatalystCore("fallback", Items.BARRIER, new CoreAttributes.Builder().ability(new CatalystAbility(new Abilities.Builder().build())).build());
    }

    public static String getCatalystId(ItemStack stack) {
        return getCatalystCore(stack).getId();
    }

    public static CatalystAbility getCatalystAbility(ItemStack stack) {
        return getCatalystCore(stack).getAbility();
    }

    public static Multimap<Attribute, AttributeModifier> getCatalystAttribute(ItemStack stack) {
        return getCatalystCore(stack).getAbility().getAttributes().get();
    }
}
