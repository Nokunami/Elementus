package nokunami.elementus;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.block.ComposterBlock;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.AddPackFindersEvent;
//import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.DistExecutor;
//import net.minecraftforge.fml.ModList;
//import net.minecraftforge.fml.ModLoadingContext;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.fml.loading.FMLPaths;
//import net.minecraftforge.resource.PathPackResources;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import nokunami.elementus.client.ClientProxy;
import nokunami.elementus.common.CreativeTabProperties;
import nokunami.elementus.common.config.*;
import nokunami.elementus.common.network.ModNetwork;
import nokunami.elementus.common.registry.*;
import nokunami.elementus.common.worldgen.tree.ModTrunkPlacer;
import nokunami.elementus.datagen.loot.ModLootModifiers;
import nokunami.elementus.event.ModClientEvents;
import nokunami.elementus.event.ModServerEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static net.neoforged.fml.config.ModConfig.Type.CLIENT;
import static net.neoforged.fml.config.ModConfig.Type.COMMON;
import static nokunami.elementus.ModChecker.*;

@net.neoforged.fml.common.Mod(Elementus.MODID)
public class Elementus {
    public static final String MODID = "elementus";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String CONFIG_VERSION = "1.4";
    public static final Path TIER_CONFIG_PATH = configPath("config/elementus", "tier_config.toml");
    public static final Path ITEM_CONFIG_PATH = configPath("config/elementus", "item_config.toml");
    public static final Path UNIQUE_ITEM_CONFIG_PATH = configPath("config/elementus", "unique_item_config.toml");
    public static final Path ARMOR_CONFIG_PATH = configPath("config/elementus", "armor_config.toml");
    public static final Path CATALYST_CONFIG_PATH = configPath("config/elementus", "catalyst_armor_config.toml");
    public static final Path ENTITY_CONFIG = configPath("config/elementus", "entity_config.toml");
    public static final Path ISS_CONFIG_PATH = configPath("config/elementus/compat", "irons_spellbook_config.toml");
    public static final Path AE_CONFIG_PATH = configPath("config/elementus/compat", "aether_config.toml");
    public static final Path SS_CONFIG_PATH = configPath("config/elementus/compat", "simply_sword_config.toml");
    public static final Path SW_CONFIG_PATH = configPath("config/elementus/compat", "sniff_weapon_config.toml");
    public static final Path AN_CONFIG_PATH = configPath("config/elementus/compat", "advanced_netherite_config.toml");
    public static final Path SD_CONFIG_PATH = configPath("config/elementus/compat", "samurai_dynasty_config.toml");
    public static final Path WS_CONFIG_PATH = configPath("config/elementus/compat", "witherstorm_config.toml");
//    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    static Path configPath(String path, String configPath) {
        return FMLPaths.getOrCreateGameRelativePath(Path.of(path)).resolve(configPath);
    }

    public static ResourceLocation modLoc(String location) {
        return ResourceLocation.fromNamespaceAndPath(Elementus.MODID, location);
    }

    public Elementus(IEventBus modEventBus, ModContainer modContainer) {
//        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TierConfig.reload();
        ItemConfig.reload();
        UniqueItemConfig.reload();
        ArmorConfig.reload();
        CatalystArmorConfig.reload();
        EntityConfig.reload();
        if (ironsSpellbooks) ISSConfig.reload();
        if (aether) AEConfig.reload();
        if (simplySwords) SSConfig.reload();
        if (sniffsWeapons) SWConfig.reload();
        if (advancedNetherite) ANConfig.reload();
        if (samuraiDynasty) SDConfig.reload();
        if (witherStormMod) WSConfig.reload();


        modContainer.registerConfig(CLIENT, ModConfig.CLIENT_SPEC, "elementus/client.toml");
        modContainer.registerConfig(COMMON, ModConfig.COMMON_SPEC, "elementus/common.toml");

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntityType.register(modEventBus);
        ModEntityType.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModSoundEvents.register(modEventBus);
        ModMobEffects.register(modEventBus);
        ModEnchantments.register(modEventBus);
        ModTrunkPlacer.register(modEventBus);
        ModParticleTypes.register(modEventBus);

        NeoForge.EVENT_BUS.register(new ModServerEvents());

        if (ModChecker.integrationTab()) CreativeTabProperties.register(modEventBus);

        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::commonSetup);
        ModNetwork.setup();
        modEventBus.addListener(ModClientEvents::itemDecorations);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(ModItems.ElementusItems.MOVCADIA_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.ElementusItems.MOVCADIA_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.ElementusItems.MOVCADIA_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(ModItems.ElementusItems.FLOWERING_MOVCADIA_LEAVES.get(), 0.3F);
    }

    public void addPackFinders(AddPackFindersEvent event) {
        LOGGER.debug("addPackFinders");

        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                addBuiltinPack(event, "elementus_legacy_textures", Component.literal("Elementus Legacy Textures"));
                if (samuraiDynasty) {
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
        Path resourcePath = ModList.get().getModFileById(MODID).getFile().findResource(filename);
        Pack pack = Pack.readMetaAndCreate(
                new PackLocationInfo(id, displayName, PackSource.BUILT_IN, Optional.empty()),
                BuiltInPackSource.fromName((path) -> new PathPackResources(path, resourcePath)),
                PackType.CLIENT_RESOURCES, new PackSelectionConfig(false, Pack.Position.TOP, false));
        event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
    }
}
