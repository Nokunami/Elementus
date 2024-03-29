package net.nokunami.elementus;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nokunami.elementus.compat.farmersdelight.PiercingPaxelsItemsRegistry;
import net.nokunami.elementus.registry.BlocksRegistry;
import net.nokunami.elementus.compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.registry.IntegrationTab;
import net.nokunami.elementus.registry.ItemsRegistry;
import net.nokunami.elementus.datagen.loot.ModLootModifiers;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Elementus.MODID)
public class Elementus
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "elementus";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(Elementus.MODID, location);
    }

    public Elementus()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ItemsRegistry.register(modEventBus);
        BlocksRegistry.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        IntegrationTab.register(modEventBus);

        if (ModList.get().isLoaded("farmersdelight")) {
            FDItemsRegistry.register(modEventBus);
        }

        if (ModList.get().isLoaded("piercingpaxels")) {
            PiercingPaxelsItemsRegistry.register(modEventBus);
        }

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.IRON_NUGGET.getDefaultInstance(),
                    ItemsRegistry.STEEL_NUGGET.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.IRON_INGOT.getDefaultInstance(),
                    ItemsRegistry.CRUDE_STEEL.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(ItemsRegistry.CRUDE_STEEL.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_INGOT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_INGOT.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_SCRAP.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.NETHERITE_INGOT.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_SCRAP.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_SCRAP.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_INGOT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_INGOT.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_INGOT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }



        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.IRON_SWORD.getDefaultInstance(),
                    ItemsRegistry.STEEL_SWORD.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.DIAMOND_SWORD.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_SWORD.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.NETHERITE_SWORD.getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_SWORD.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.IRON_AXE.getDefaultInstance(),
                    ItemsRegistry.STEEL_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.DIAMOND_AXE.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.NETHERITE_AXE.getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }



        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(Items.IRON_HOE.getDefaultInstance(),
                    ItemsRegistry.STEEL_SHOVEL.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_SHOVEL.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_PICKAXE.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_AXE.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_HOE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(Items.DIAMOND_HOE.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_SHOVEL.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_SHOVEL.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_PICKAXE.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_AXE.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_HOE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(Items.NETHERITE_HOE.getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_SHOVEL.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_SHOVEL.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_PICKAXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_PICKAXE.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_AXE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_AXE.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_HOE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }




        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.IRON_BOOTS.getDefaultInstance(),
                    ItemsRegistry.STEEL_HELEMT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_HELEMT.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_CHESTPLATE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_CHESTPLATE.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_LEGGINGS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.STEEL_LEGGINGS.get().getDefaultInstance(),
                    ItemsRegistry.STEEL_BOOTS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.DIAMOND_BOOTS.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_HELEMT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_HELEMT.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_CHESTPLATE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_CHESTPLATE.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_LEGGINGS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_LEGGINGS.get().getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_BOOTS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(Items.NETHERITE_BOOTS.getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_HELEMT.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_HELEMT.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_CHESTPLATE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_CHESTPLATE.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_LEGGINGS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.getEntries().putAfter(ItemsRegistry.DIARKRITE_LEGGINGS.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_BOOTS.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }



        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultInstance(),
                    ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.getEntries().putAfter(ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(),
                    ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get().getDefaultInstance(),
                    CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }



        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BlocksRegistry.STEEL_BLOCK.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BlocksRegistry.ANTHEKTITE_BLOCK.get());
        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(BlocksRegistry.DIARKRITE_BLOCK.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(BlocksRegistry.REMNANT.get());
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
