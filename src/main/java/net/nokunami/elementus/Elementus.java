package net.nokunami.elementus;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import com.mojang.logging.LogUtils;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.resource.PathPackResources;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.compat.farmersdelight.NDItemsRegistry;
import net.nokunami.elementus.compat.ironsspellbooks.ISSItemsRegistry;
import net.nokunami.elementus.compat.piercingpaxels.PPItemsRegistry;
import net.nokunami.elementus.compat.simplyswords.SSItemsRegistry;
import net.nokunami.elementus.compat.simplyswords.config.ConfigWrapper;
import net.nokunami.elementus.compat.simplyswords.config.WeaponAttributesConfig;
import net.nokunami.elementus.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.item.ElementusShield;
import net.nokunami.elementus.registry.ModBlocks;
import net.nokunami.elementus.compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.registry.IntegrationTab;
import net.nokunami.elementus.registry.ModItems;
import net.nokunami.elementus.datagen.loot.ModLootModifiers;
import org.slf4j.Logger;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

@Mod(Elementus.MODID)
public class Elementus {
    public static final String MODID = "elementus";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static WeaponAttributesConfig weaponAttributesConfig;
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(Elementus.MODID, location);
    }

    public Elementus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        if (ModList.get().isLoaded("farmersdelight")) {
        IntegrationTab.register(modEventBus);
        } else if (ModList.get().isLoaded("piercingpaxels")) {
            IntegrationTab.register(modEventBus);
        } else if (ModList.get().isLoaded("nethersdelight")) {
            IntegrationTab.register(modEventBus);
        } else if (ModList.get().isLoaded("irons_spellbooks")) {
            IntegrationTab.register(modEventBus);
        } else if (ModList.get().isLoaded("aether")) {
            IntegrationTab.register(modEventBus);
        } else if (ModList.get().isLoaded("simplyswords")) {
            IntegrationTab.register(modEventBus);
        }

        if (ModList.get().isLoaded("farmersdelight")) {
            FDItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("piercingpaxels")) {
            PPItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("nethersdelight")) {
            NDItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("irons_spellbooks")) {
            ISSItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("aether")) {
            AEItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("simplyswords")) {
            SSItemsRegistry.register(modEventBus);

            AutoConfig.register(ConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
            weaponAttributesConfig = AutoConfig.getConfigHolder(ConfigWrapper.class).getConfig().weapon_attributes;
        }

        PROXY.init();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupEntityModelLayers);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "elementus_armor_config.toml");
    }


    public void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.ANCIENT_DEBRIS, ModBlocks.REMNANT);

            putAfter(entries, Items.IRON_NUGGET, ModItems.STEEL_NUGGET);
            putAfter(entries, Items.IRON_INGOT, ModItems.CRUDE_STEEL);
            putAfter(entries, ModItems.CRUDE_STEEL.get(), ModItems.STEEL_INGOT);
            putAfter(entries, ModItems.STEEL_INGOT.get(), ModItems.STEEL_SCRAP);

            putAfter(entries, Items.NETHERITE_INGOT, ModItems.ATELIS_SCRAP);
            putAfter(entries, ModItems.ATELIS_SCRAP.get(), ModItems.DIARKRITE_INGOT);
            putAfter(entries, ModItems.ATELIS_SCRAP.get(), ModItems.ANTHEKTITE_SCRAP);
            putAfter(entries, ModItems.DIARKRITE_INGOT.get(), ModItems.ANTHEKTITE_INGOT);

            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.ECHOING_MIRROR);
            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.DIARKRITE_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, ModItems.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get(), ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE);
        }

        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
            putAfter(entries, Items.NETHERITE_SWORD, ModItems.STEEL_SWORD);
            putAfter(entries, ModItems.STEEL_SWORD.get(), ModItems.DIARKRITE_SWORD);
            putAfter(entries, ModItems.DIARKRITE_SWORD.get(), ModItems.ANTHEKTITE_SWORD);

            // Axes
            putAfter(entries, Items.NETHERITE_AXE, ModItems.STEEL_AXE);
            putAfter(entries, ModItems.STEEL_AXE.get(), ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE.get(), ModItems.ANTHEKTITE_AXE);

            // Shields
            putAfter(entries, Items.SHIELD, ModItems.STEEL_SHIELD);
            putAfter(entries, ModItems.STEEL_SHIELD.get(), ModItems.DIARKRITE_SHIELD);
            putAfter(entries, ModItems.DIARKRITE_SHIELD.get(), ModItems.ANTHEKTITE_SHIELD);        }

        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.NETHERITE_HOE, ModItems.STEEL_SHOVEL);
            putAfter(entries, ModItems.STEEL_SHOVEL.get(), ModItems.STEEL_PICKAXE);
            putAfter(entries, ModItems.STEEL_PICKAXE.get(), ModItems.STEEL_AXE);
            putAfter(entries, ModItems.STEEL_AXE.get(), ModItems.STEEL_HOE);

            putAfter(entries, ModItems.STEEL_HOE.get(), ModItems.DIARKRITE_SHOVEL);
            putAfter(entries, ModItems.DIARKRITE_SHOVEL.get(), ModItems.DIARKRITE_PICKAXE);
            putAfter(entries, ModItems.DIARKRITE_PICKAXE.get(), ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE.get(), ModItems.DIARKRITE_HOE);

            putAfter(entries, ModItems.DIARKRITE_HOE.get(), ModItems.ANTHEKTITE_SHOVEL);
            putAfter(entries, ModItems.ANTHEKTITE_SHOVEL.get(), ModItems.ANTHEKTITE_PICKAXE);
            putAfter(entries, ModItems.ANTHEKTITE_PICKAXE.get(), ModItems.ANTHEKTITE_AXE);
            putAfter(entries, ModItems.ANTHEKTITE_AXE.get(), ModItems.ANTHEKTITE_HOE);
        }

        // Armor
        if (tab == CreativeModeTabs.COMBAT) {
            putAfter(entries, Items.NETHERITE_BOOTS, ModItems.STEEL_HELMET);
            putAfter(entries, ModItems.STEEL_HELMET.get(), ModItems.STEEL_CHESTPLATE);
            putAfter(entries, ModItems.STEEL_CHESTPLATE.get(), ModItems.STEEL_LEGGINGS);
            putAfter(entries, ModItems.STEEL_LEGGINGS.get(), ModItems.STEEL_BOOTS);

            putAfter(entries, ModItems.STEEL_BOOTS.get(), ModItems.DIARKRITE_HELEMT);
            putAfter(entries, ModItems.DIARKRITE_HELEMT.get(), ModItems.DIARKRITE_CHESTPLATE);
            putAfter(entries, ModItems.DIARKRITE_CHESTPLATE.get(), ModItems.DIARKRITE_LEGGINGS);
            putAfter(entries, ModItems.DIARKRITE_LEGGINGS.get(), ModItems.DIARKRITE_BOOTS);

            putAfter(entries, ModItems.DIARKRITE_BOOTS.get(), ModItems.ANTHEKTITE_HELEMT);
            putAfter(entries, ModItems.ANTHEKTITE_HELEMT.get(), ModItems.ANTHEKTITE_CHESTPLATE);
            putAfter(entries, ModItems.ANTHEKTITE_CHESTPLATE.get(), ModItems.ANTHEKTITE_LEGGINGS);
            putAfter(entries, ModItems.ANTHEKTITE_LEGGINGS.get(), ModItems.ANTHEKTITE_BOOTS);

            if (ModList.get().isLoaded("aether")) {
                putAfter(entries, ModItems.STEEL_BOOTS.get(), AEItemsRegistry.STEEL_GLOVES);
                putAfter(entries, ModItems.DIARKRITE_BOOTS.get(), AEItemsRegistry.DIARKRITE_GLOVES);
                putAfter(entries, ModItems.ANTHEKTITE_BOOTS.get(), AEItemsRegistry.ANTHEKTITE_GLOVES);
            }
        }

        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Items.NETHERITE_BLOCK, ModBlocks.STEEL_BLOCK);
            putAfter(entries, Item.byBlock(ModBlocks.STEEL_BLOCK.get()), ModBlocks.DIARKRITE_BLOCK);
            putAfter(entries, Item.byBlock(ModBlocks.DIARKRITE_BLOCK.get()), ModBlocks.ANTHEKTITE_BLOCK);
        }
    }


    //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised
    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Item after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putBefore(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    private void setupClient(FMLClientSetupEvent event) {
        PROXY.clientInit();
    }

    private void setupEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModelLayers.register(event);
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ElementusShield.addShieldItemProperties();

            if (ModList.get().isLoaded("irons_spellbooks")) {
                ISSItemsRegistry.getISSCompatItems().stream().filter((item) -> {
                    return item.get() instanceof SpellBook;
                }).forEach((item) -> {
                    CuriosRendererRegistry.register((Item) item.get(), SpellBookCurioRenderer::new);
                });
            }
            if (ModList.get().isLoaded("aether")) {
                CuriosRendererRegistry.register(AEItemsRegistry.STEEL_GLOVES.get(), GlovesRenderer::new);
                CuriosRendererRegistry.register(AEItemsRegistry.ANTHEKTITE_GLOVES.get(), GlovesRenderer::new);
                CuriosRendererRegistry.register(AEItemsRegistry.DIARKRITE_GLOVES.get(), GlovesRenderer::new);
            }
        }
    }

    public void addPackFinders(AddPackFindersEvent event) {
        LOGGER.debug("addPackFinders");

        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                addBuiltinPack(event, "elementus_legacy_textures", Component.literal("Elementus Legacy Textures"));
                if (ModList.get().isLoaded("simplyswords")) {
                    addBuiltinPack(event, "simply_swords_default_style", Component.literal("Elementus Simply Swords Defualt Style"));
                }
            }
        } catch (IOException var3) {
            LOGGER.error("Failed to load a builtin resource pack! If you see this message, please report it to https://github.com/Nokunami/Elementus/issues");
        }
    }

    private static void addBuiltinPack(AddPackFindersEvent event, String filename, Component displayName) throws IOException {
        filename = "resourcepacks/" + filename;
        String id = "builtin/" + filename;
        Path resourcePath = ModList.get().getModFileById("elementus").getFile().findResource(new String[]{filename});
        Pack pack = Pack.readMetaAndCreate(id, displayName, false, (path) -> {
            return new PathPackResources(path, true, resourcePath);
        }, PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
        event.addRepositorySource((packConsumer) -> {
            packConsumer.accept(pack);
        });
    }
}
