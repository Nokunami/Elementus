package net.nokunami.elementus.common.compat.farmersdelight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.config.ItemConfig;
import net.nokunami.elementus.common.registry.ModTiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.registry.ModItems.*;

public class FarmersDelightItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife", () -> new KnifeItem(ModTiers.STEEL,
            (float) ItemConfig.steelKnifeDamage, (float) ItemConfig.steelKnifeAttackSpeed + steelSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_KNIFE = ITEMS.register("diarkrite_knife", () -> new KnifeItem(ModTiers.DIARKRITE,
            (float) ItemConfig.diarkriteKnifeDamage, (float) ItemConfig.diarkriteKnifeAttackSpeed + diarkriteSpeed, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_KNIFE = ITEMS.register("anthektite_knife", () -> new KnifeItem(ModTiers.ANTHEKTITE,
            (float) ItemConfig.anthektiteKnifeDamage, (float) ItemConfig.anthektiteKnifeAttackSpeed - anthektiteSpeed, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_CABINET = ITEMS.register("movcadia_cabinet",
            () -> new BlockItem(FarmersDelightBlocks.MOVCADIA_CABINET.get(), new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
