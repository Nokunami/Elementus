package net.nokunami.elementus;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nokunami.elementus.client.ClientProxy;
import net.nokunami.elementus.client.ModAtlases;
import net.nokunami.elementus.client.gui.screens.inventory.tooltip.ClientCatalystTooltip;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.particle.*;
import net.nokunami.elementus.client.render.CatalystElytraLayer;
import net.nokunami.elementus.client.render.DiarkriteEmissiveLayer;
import net.nokunami.elementus.client.render.entity.projectile.SonicRushParticleEntityRenderer;
import net.nokunami.elementus.client.render.vehicle.ModBoatRenderer;
import net.nokunami.elementus.client.render.vehicle.ModChestRenderer;
import net.nokunami.elementus.client.render.entity.steelGolem.SteelGolemRenderer;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.client.render.entity.projectile.AnthektiteSlashRenderer;
import net.nokunami.elementus.common.config.ModConfig;
import net.nokunami.elementus.common.item.ItemPredicateRegister;
import net.nokunami.elementus.common.registry.ModBlockEntityType;
import net.nokunami.elementus.common.registry.ModBlockSetType;
import net.nokunami.elementus.common.registry.ModEntityType;
import net.nokunami.elementus.common.registry.ModItems.AetherItems;
import net.nokunami.elementus.common.registry.ModItems.IronsSpellbooksItems;
import net.nokunami.elementus.common.registry.ModItems.SniffsWeaponsItems;
import net.nokunami.elementus.common.registry.ModParticleTypes;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import static net.nokunami.elementus.ModChecker.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ElementusClient {
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(
            () -> ClientProxy::new, () -> CommonProxy::new);

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ItemPredicateRegister.registerItemPredicate();
        event.enqueueWork(ModAtlases::registerSheets);
        Sheets.addWoodType(ModBlockSetType.MOVCADIA_WOOD_TYPE);
        Sheets.addWoodType(ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE);

        PROXY.clientInit();

        EntityRenderers.register(ModEntityType.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntityType.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        EntityRenderers.register(ModEntityType.STEEL_GOLEM.get(), SteelGolemRenderer::new);
        EntityRenderers.register(ModEntityType.ANTHEKTITE_SLASH.get(), AnthektiteSlashRenderer::new);
        EntityRenderers.register(ModEntityType.SONIC_RUSH.get(), SonicRushParticleEntityRenderer::new);


        if (ModConfig.CLIENT.lavaRendererType.get()) {
            ItemBlockRenderTypes.setRenderLayer(Fluids.LAVA, RenderType.translucent());
        }

        if (ironsSpellbooks) {
            IronsSpellbooksItems.getISSCompatItems().stream().filter((item) -> item.get() instanceof SpellBook)
                    .forEach((item) -> CuriosRendererRegistry.register(item.get(), SpellBookCurioRenderer::new));
        }
        if (aether) {
            CuriosRendererRegistry.register(AetherItems.STEEL_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(AetherItems.ANTHEKTITE_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(AetherItems.DIARKRITE_GLOVES.get(), GlovesRenderer::new);
        }
    }

    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CatalystTooltip.class ,ClientCatalystTooltip::new);
    }

    @SubscribeEvent
    public static void setupEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModelLayers.register(event);
    }

    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_CHEST.get(), ModChestRenderer::new);
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_SIGN.get(), SignRenderer::new);
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerElytraRenderers(EntityRenderersEvent event) {
        /// Credits to Netherite Elytra : link https://github.com/DrunkBlood/Netherite-Elytra
        if(event instanceof EntityRenderersEvent.AddLayers addLayersEvent){
            EntityModelSet entityModels = addLayersEvent.getEntityModels();
            addLayersEvent.getSkins().forEach(s -> {
                LivingEntityRenderer<? extends Player, ? extends EntityModel<? extends Player>> livingEntityRenderer = addLayersEvent.getSkin(s);
                if(livingEntityRenderer instanceof PlayerRenderer playerRenderer){
                    playerRenderer.addLayer(new CatalystElytraLayer<>(playerRenderer, entityModels));
                    playerRenderer.addLayer(new DiarkriteEmissiveLayer<>(playerRenderer, entityModels));
//                    playerRenderer.addLayer(new CatalystElytraExtraLayer<>(playerRenderer, entityModels));
                }
            });
            LivingEntityRenderer<ArmorStand, ? extends EntityModel<ArmorStand>> livingEntityRenderer = addLayersEvent.getRenderer(EntityType.ARMOR_STAND);
            if(livingEntityRenderer instanceof ArmorStandRenderer armorStandRenderer){
                armorStandRenderer.addLayer(new CatalystElytraLayer<>(armorStandRenderer, entityModels));
                armorStandRenderer.addLayer(new DiarkriteEmissiveLayer<>(armorStandRenderer, entityModels));
//                armorStandRenderer.addLayer(new CatalystElytraExtraLayer<>(armorStandRenderer,entityModels));
            }
        }
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        if (sniffsWeapons) {
            event.register((stack, layer) -> layer > 0 ? -1 : ((DyeableLeatherItem)stack.getItem()).getColor(stack),
                    SniffsWeaponsItems.STEEL_SURCOAT.get(),
                    SniffsWeaponsItems.DIARKRITE_SURCOAT.get(),
                    SniffsWeaponsItems.ANTHEKTITE_SURCOAT.get(),
                    SniffsWeaponsItems.STEEL_HELM.get(),
                    SniffsWeaponsItems.DIARKRITE_HELM.get(),
                    SniffsWeaponsItems.ANTHEKTITE_HELM.get(),
                    SniffsWeaponsItems.STEEL_HORNED_HELM.get(),
                    SniffsWeaponsItems.DIARKRITE_HORNED_HELM.get(),
                    SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM.get(),
                    SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE.get(),
                    SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE.get(),
                    SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE.get(),
                    SniffsWeaponsItems.STEEL_KABUTO.get(),
                    SniffsWeaponsItems.DIARKRITE_KABUTO.get(),
                    SniffsWeaponsItems.ANTHEKTITE_KABUTO.get(),
                    SniffsWeaponsItems.STEEL_DO.get(),
                    SniffsWeaponsItems.DIARKRITE_DO.get(),
                    SniffsWeaponsItems.ANTHEKTITE_DO.get(),
                    SniffsWeaponsItems.CLOTHED_STEEL_CUIRASS.get(),
                    SniffsWeaponsItems.CLOTHED_DIARKRITE_CUIRASS.get(),
                    SniffsWeaponsItems.CLOTHED_ANTHEKTITE_CUIRASS.get());
        }
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.SONIC_BURST.get(), SonicBurstParticle.Provider::new);
        event.registerSpecial(ModParticleTypes.SONIC_BURST_EMITTER.get(), new SonicBurstEmitterParticle.Provider());
        event.registerSpecial(ModParticleTypes.SACRIFICE_SONIC_BURST_EMITTER.get(), new SacrificeSonicBoomEmitterParticle.Provider());
        event.registerSpriteSet(ModParticleTypes.SACRIFICE_SONIC_BOOM.get(), SacrificeSonicBoomParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PARRY.get(), ParryParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PARRY_RESONANCE.get(), ParryParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.SONIC_BOOM_BURST_START.get(), SonicBoomBurstStartParticle.Provider::new);
    }
}
