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
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.resource.PathPackResources;
import net.nokunami.elementus.client.ClientProxy;
import net.nokunami.elementus.common.CreativeTabProperties;
import net.nokunami.elementus.common.config.*;
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

import static net.nokunami.elementus.ModChecker.*;

@Mod(Elementus.MODID)
@Mod.EventBusSubscriber(modid = "elementus")
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
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    static Path configPath(String path, String configPath) {
        return FMLPaths.getOrCreateGameRelativePath(Path.of(path)).resolve(configPath);
    }

    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(Elementus.MODID, location);
    }

    public Elementus() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

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
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.CLIENT, ModConfig.CLIENT_SPEC, "elementus/client.toml");
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ModConfig.COMMON_SPEC, "elementus/common.toml");

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

        MinecraftForge.EVENT_BUS.register(new ServerEvents());

        if (ModChecker.integrationTab()) CreativeTabProperties.register(modEventBus);

        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::commonSetup);
        ModNetwork.setup();
        modEventBus.addListener(ClientEvents::itemDecorations);
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
        Path resourcePath = ModList.get().getModFileById("elementus").getFile().findResource(filename);
        Pack pack = Pack.readMetaAndCreate(id, displayName, false, (path) -> new PathPackResources(
                path, true, resourcePath), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
        event.addRepositorySource((packConsumer) -> packConsumer.accept(pack));
    }
}
