package net.nokunami.elementus;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.resource.PathPackResources;
import net.nokunami.elementus.common.compat.farmersdelight.FDModBlocks;
import net.nokunami.elementus.common.compat.twigs.TWModBlocks;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
import net.nokunami.elementus.common.config.*;
import net.nokunami.elementus.common.config.simplyswords.ElementusSimplySwords;
import net.nokunami.elementus.common.config.simplyswords.GeneralWrapper;
import net.nokunami.elementus.common.registry.ModBlockEntityType;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.NDModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.worldgen.tree.ModTrunkPlacer;
import net.nokunami.elementus.datagen.loot.ANLootModifiers;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.common.CreativeTabProperties;
import net.nokunami.elementus.common.registry.ModEntities;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.datagen.loot.ModLootModifiers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

@Mod(Elementus.MODID)
@Mod.EventBusSubscriber(modid = "elementus")
public class Elementus {
    public static final String MODID = "elementus";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final Path MATERIAL_STATS_CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("elementus_material_stats_config.toml");
    public static final Path TOOLS_CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("elementus_tools_config.toml");
    public static final Path ARMOR_CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("elementus_armor_config.toml");
//    public static final Path CONFIG_PATH = FMLPaths.CONFIGDIR.get().resolve("elementus_config.toml");
//    public static final Path CONFIG_PATH_ADVANCED_NETHERITE = FMLPaths.CONFIGDIR.get().resolve("elementus_advanced_netherite_config.toml");
    public static ResourceLocation modLoc(String location) {
        return new ResourceLocation(Elementus.MODID, location);
    }

    public static ElementusSimplySwords weaponAttributes;

    public Elementus() {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TierConfig.reload();
        ToolsConfig.reload();
        ArmorConfig.reload();
//        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ServerConfig.SPEC, "elementus_armor_config.toml");

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntityType.register(modEventBus);
        ModEntities.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        ModTrunkPlacer.register(modEventBus);

        if (ModChecker.integrationTab()) {
            CreativeTabProperties.register(modEventBus);
        }

        if (ModChecker.farmersdelight()) {
            FDModItems.register(modEventBus);
            FDModBlocks.register(modEventBus);
        }
        if (ModChecker.piercingpaxels()) { PPModItems.register(modEventBus); }
        if (ModChecker.nethersdelight()) { NDModItems.register(modEventBus); }
        if (ModChecker.irons_spellbooks()) { ISSModItems.register(modEventBus); }
        if (ModChecker.aether()) { TAModItems.register(modEventBus); }
        if (ModChecker.simplyswords()) {
            SSModItems.register(modEventBus);
            AutoConfig.register(GeneralWrapper.class, PartitioningSerializer.wrap(JanksonConfigSerializer::new));
            weaponAttributes = AutoConfig.getConfigHolder(GeneralWrapper.class).getConfig().weaponAttributes;
        }
        if (ModChecker.sniffsweapons()) { SWModItems.register(modEventBus); }
        if (ModChecker.advancednetherite()) {
            ANModItems.register(modEventBus);
            ANModBlocks.register(modEventBus);
            ANLootModifiers.register(modEventBus);
        }
        if (ModChecker.epicsamurai()) { ESModItems.register(modEventBus); }
        if (ModChecker.twigs()) { TWModItems.register(modEventBus); TWModBlocks.register(modEventBus); }
//        if (ModChecker.refurbished_furniture()) {
//            RFModItems.register(modEventBus);
//            RFModBlocks.register(modEventBus);
//        }

        modEventBus.addListener(CreativeTabProperties::addCreative);
        modEventBus.addListener(this::addPackFinders);
    }

    public void addPackFinders(AddPackFindersEvent event) {
        LOGGER.debug("addPackFinders");

        try {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                addBuiltinPack(event, "elementus_legacy_textures", Component.literal("Elementus Legacy Textures"));
                if (ModChecker.simplyswords()) {
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
        Pack pack = Pack.readMetaAndCreate(id, displayName, false, (path) -> new PathPackResources(
                path, true, resourcePath), PackType.CLIENT_RESOURCES, Pack.Position.TOP, PackSource.BUILT_IN);
        event.addRepositorySource((packConsumer) -> {
            packConsumer.accept(pack);
        });
    }
}
