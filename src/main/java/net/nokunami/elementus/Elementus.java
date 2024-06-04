package net.nokunami.elementus;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.AddPackFindersEvent;
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
import net.nokunami.elementus.client.ClientProxy;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.NDModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.config.ConfigWrapper;
import net.nokunami.elementus.common.config.ServerConfig;
import net.nokunami.elementus.common.config.WeaponAttributesConfig;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.common.CreativeTabProperties;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.datagen.loot.ModLootModifiers;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

@Mod(Elementus.MODID)
@Mod.EventBusSubscriber(modid = "elementus")
public class Elementus {
    public static final String MODID = "elementus";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static WeaponAttributesConfig weaponAttributesConfig;
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(
            () -> ClientProxy::new, () -> CommonProxy::new);

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(Elementus.MODID, location);
    }

    public Elementus() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "elementus_armor_config.toml");

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        if (ModList.get().isLoaded("farmersdelight")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("piercingpaxels")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("nethersdelight")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("irons_spellbooks")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("aether")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("simplyswords")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("sniffsweapons")) {
            CreativeTabProperties.register(modEventBus);
        }
        else if (ModList.get().isLoaded("advancednetherite")) {
            CreativeTabProperties.register(modEventBus);
        }

        if (ModList.get().isLoaded("farmersdelight")) {
            FDModItems.register(modEventBus);
        }
        if (ModList.get().isLoaded("piercingpaxels")) {
            PPModItems.register(modEventBus);
        }
        if (ModList.get().isLoaded("nethersdelight")) {
            NDModItems.register(modEventBus);
        }
        if (ModList.get().isLoaded("irons_spellbooks")) {
            ISSModItems.register(modEventBus);
        }
        if (ModList.get().isLoaded("aether")) {
            AEItemsRegistry.register(modEventBus);
        }
        if (ModList.get().isLoaded("simplyswords")) {
            SSModItems.register(modEventBus);
            AutoConfig.register(ConfigWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
            weaponAttributesConfig = AutoConfig
                    .getConfigHolder(ConfigWrapper.class)
                    .getConfig().weapon_attributes;
        }
        if (ModList.get().isLoaded("sniffsweapons")) {
            SWModItems.register(modEventBus);
        }
        if (ModList.get().isLoaded("advancednetherite")) {
            ANModItems.register(modEventBus);
            ANModBlocks.register(modEventBus);
        }
        if (ModList.get().isLoaded("epicsamurai")) {
            ESModItems.register(modEventBus);
        }

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::setupClient);
        modEventBus.addListener(this::setupEntityModelLayers);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
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
