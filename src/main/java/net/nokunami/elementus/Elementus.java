package net.nokunami.elementus;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.resource.PathPackResources;
import net.nokunami.elementus.common.CreativeTabProperties;
import net.nokunami.elementus.common.config.*;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystISSConfig;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.*;
import net.nokunami.elementus.common.worldgen.tree.ModTrunkPlacer;
import net.nokunami.elementus.datagen.loot.ModLootModifiers;
import net.nokunami.elementus.event.ClientEvents;
import net.nokunami.elementus.event.ServerEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.*;

@Mod(MODID)
@Mod.EventBusSubscriber(modid = MODID)
public class Elementus {
    public static final String MODID = "elementus";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String CONFIG_VERSION = "1.4";
    public static final Path TIER_CONFIG_PATH = configPath("tier_config.toml");
    public static final Path ITEM_CONFIG_PATH = configPath("item_config.toml");
    public static final Path UNIQUE_ITEM_CONFIG_PATH = configPath("unique_item_config.toml");
    public static final Path ARMOR_CONFIG_PATH = configPath("armor_config.toml");
    public static final Path CATALYST_CONFIG_PATH = configPath("catalyst_armor_config.toml");
    public static final Path ENTITY_CONFIG = configPath("entity_config.toml");

    public static final Path CATALYST_ISS_CONFIG_PATH = configPath("config/elementus/compat", "catalyst_core_iss_config.toml");

    static Path configPath(String configPath) {
        return configPath("config/elementus", configPath);
    }

    static Path configPath(String path, String configPath) {
        return FMLPaths.getOrCreateGameRelativePath(Path.of(path)).resolve(configPath);
    }

    public static ResourceLocation modLoc(String location) {
        return modLoc(MODID, location);
    }

    public static ResourceLocation modLoc(String id, String location) {
        return new ResourceLocation(id, location);
    }

    public Elementus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TierConfig.reload();
        ItemConfig.reload();
        UniqueItemConfig.reload();
        ArmorConfig.reload();
        CatalystArmorConfig.reload();
        EntityConfig.reload();

        CatalystISSConfig.reload();

        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ModConfig.CLIENT_SPEC, "elementus/client.toml");
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC, "elementus/common.toml");

        EItems.register(modEventBus);
        EBlocks.register(modEventBus);
        ModBlockEntityType.register(modEventBus);
        ModEntityType.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ESoundEvents.register(modEventBus);
        EMobEffects.register(modEventBus);
        EEnchantments.register(modEventBus);
        ModTrunkPlacer.register(modEventBus);
        ModParticleTypes.register(modEventBus);
        CatalystCoreRegistry.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new ServerEvents());

        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::commonSetup);
        ModNetwork.setup();

        modEventBus.addListener(ClientEvents::itemDecorations);
        modEventBus.addListener(ClientEvents::onRegisterKeybinds);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.FLOWERING_MOVCADIA_LEAVES.get(), 0.3F);
        CATALYST_CORE_LIST.forEach(core -> CORE_ITEM_MAP.put(core.getCoreStack().getItem(), core));
        CATALYST_CORE_LIST.forEach(core -> CORE_ITEMSTACK_MAP.put(core.itemStack, core));
    }

    public void addPackFinders(AddPackFindersEvent event) {
        LOGGER.debug("addPackFinders");
        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES)
                addBuiltinPack(event, "elementus_legacy_textures", Component.literal("Elementus Legacy Textures"));
        } catch (IOException var3) {
            LOGGER.error("Failed to load a builtin resource pack! If you see this message, please report it to https://github.com/Nokunami/Elementus/issues");
        }
    }

    private static void addBuiltinPack(AddPackFindersEvent event, String filename, Component displayName) throws IOException {
        filename = "resourcepacks/" + filename;
        String id = "builtin/" + filename;
        Path resourcePath = ModList.get().getModFileById("elementus").getFile().findResource(filename);
        Pack pack = Pack.readMetaAndCreate(id, displayName, false, (path) -> new PathPackResources(
                path, true, resourcePath), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
        event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
    }
}
