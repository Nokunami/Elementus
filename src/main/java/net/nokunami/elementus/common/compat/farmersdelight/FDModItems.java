package net.nokunami.elementus.common.compat.farmersdelight;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;
import vectorwing.farmersdelight.common.item.KnifeItem;

import static net.nokunami.elementus.Elementus.MODID;

public class FDModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
    protected static float anthektiteSpeed = ModItems.anthektiteSpeed;

    public static final RegistryObject<Item> STEEL_KNIFE = ITEMS.register("steel_knife",
            () -> new KnifeItem(ModTiers.STEEL, 0.5F, -2.0F, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_KNIFE = ITEMS.register("diarkrite_knife",
            () -> new KnifeItem(ModTiers.DIARKRITE, 0.5F, -(2.0F + diarkriteSpeed), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_KNIFE = ITEMS.register("anthektite_knife",
            () -> new KnifeItem(ModTiers.ANTHEKTITE, 0.5F, -(2.0F - anthektiteSpeed), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> MOVCADIA_CABINET = ITEMS.register("movcadia_cabinet",
            () -> new BlockItem(FDModBlocks.MOVCADIA_CABINET.get(), new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
