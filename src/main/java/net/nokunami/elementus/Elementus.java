package net.nokunami.elementus;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.resource.PathPackResources;
import net.nokunami.elementus.common.CreativeTabProperties;
import net.nokunami.elementus.config.EConfig;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.registry.*;
import net.nokunami.elementus.common.worldgen.tree.ETrunkPlacer;
import net.nokunami.elementus.datagen.loot.ELootModifiers;
import net.nokunami.elementus.event.ServerEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.catalystCore.ability.AbstractAbility.ABILITIES;
import static net.nokunami.elementus.common.catalystCore.ability.AbstractAbility.ABILITIES_MAP;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.*;

@Mod(EID)
@Mod.EventBusSubscriber(modid = EID)
public class Elementus {
    public static final String EID = "elementus";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String CONFIG_VERSION = "1.4";
    public static final Path TIER_CONFIG_PATH = configPath("tier_config.toml");
    public static final Path ITEM_CONFIG_PATH = configPath("item_config.toml");
    public static final Path UNIQUE_ITEM_CONFIG_PATH = configPath("unique_item_config.toml");
    public static final Path ARMOR_CONFIG_PATH = configPath("armor_config.toml");
    public static final Path CATALYST_CONFIG_PATH = configPath("catalyst_armor_config.toml");
    public static final Path ENTITY_CONFIG = configPath("entity_config.toml");

    public static final Path CATALYST_ISS_CONFIG_PATH = configPath("config/elementus/compat", "catalyst_core_iss_config.toml");

    static Path configPath(String configPath) { return configPath("config/elementus", configPath); }

    static Path configPath(String path, String configPath) {
        return FMLPaths.getOrCreateGameRelativePath(Path.of(path)).resolve(configPath);
    }

    public static ResourceLocation modLoc(String location) { return modLoc(EID, location); }
    public static ResourceLocation modLoc(String id, String location) { return new ResourceLocation(id, location); }

    public Elementus(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

//        CatalystArmorConfig.reload();

        EConfig.init();

        EItems.register(modEventBus);
        EBlocks.register(modEventBus);
        ESounds.register(modEventBus);
        EParticles.register(modEventBus);
        EMobEffects.register(modEventBus);
        EEntityTypes.register(modEventBus);
        ETrunkPlacer.register(modEventBus);
        EEnchantments.register(modEventBus);
        ELootModifiers.register(modEventBus);
        EBlockEntityType.register(modEventBus);
        CatalystAbilities.register(modEventBus);
        CatalystCores.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(new ServerEvents());
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, ECapabilities::attachEntityCapability);

        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
        modEventBus.addListener(this::commonSetup);
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_BERRIES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.MOVCADIA_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(EItems.FLOWERING_MOVCADIA_LEAVES.get(), 0.3F);
        CATALYST_CORE_LIST.forEach(core -> CORE_ITEM_MAP.put(core.getCoreStack().getItem(), core));
        CATALYST_CORE_LIST.forEach(core -> CORE_ITEMSTACK_MAP.put(core.itemStack, core));
        ABILITIES.forEach(ability -> ABILITIES_MAP.put(ability.getId(), ability));
        event.enqueueWork(ENet::setup);
        EGameRules.init();
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
