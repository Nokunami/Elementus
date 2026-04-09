package net.nokunami.elementus;

import com.github.L_Ender.cataclysm.client.render.entity.Flame_Strike_Renderer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.particle.SoulParticle;
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
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nokunami.elementus.client.ClientProxy;
import net.nokunami.elementus.client.ModAtlases;
import net.nokunami.elementus.client.extensions.ICatalystTrim;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.client.gui.screens.inventory.tooltip.ClientCatalystTooltip;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.particle.*;
import net.nokunami.elementus.client.render.NoRenderRenderer;
import net.nokunami.elementus.client.render.entity.armor.CatalystElytraLayer;
import net.nokunami.elementus.client.render.entity.astaliteGolem.AstaliteGolemCarrierRenderer;
import net.nokunami.elementus.client.render.entity.astaliteGolem.longarm.AstaliteGolemLongarmRenderer;
import net.nokunami.elementus.client.render.entity.fragmentedWither.FragmentedWitherRenderer;
import net.nokunami.elementus.client.render.entity.projectile.*;
import net.nokunami.elementus.client.render.entity.projectile.cataclysm.CatalystIgnisFireballRenderer;
import net.nokunami.elementus.client.render.entity.steelGolem.SteelGolemRenderer;
import net.nokunami.elementus.client.render.item.inventory.CatalystTooltip;
import net.nokunami.elementus.client.render.vehicle.ModBoatRenderer;
import net.nokunami.elementus.client.render.vehicle.ModChestRenderer;
import net.nokunami.elementus.common.config.EConfig;
import net.nokunami.elementus.common.item.EItemPredicate;
import net.nokunami.elementus.common.registry.*;
import net.nokunami.elementus.common.registry.tempCompat.CompatEntityTypes;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.cataclysm;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EClient {
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EItemPredicate.registerItemPredicate();
        event.enqueueWork(ModAtlases::registerSheets);
        Sheets.addWoodType(ModBlockSetType.MOVCADIA_WOOD_TYPE);
        Sheets.addWoodType(ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE);

        PROXY.clientInit();

        EntityRenderers.register(EEntityTypes.MOVCADIA_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(EEntityTypes.MOVCADIA_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));
        EntityRenderers.register(EEntityTypes.OLD_STEEL_GOLEM.get(), SteelGolemRenderer::new);
        EntityRenderers.register(EEntityTypes.ASTALITE_GOLEM_CARRIER.get(), AstaliteGolemCarrierRenderer::new);
        EntityRenderers.register(EEntityTypes.ASTALITE_GOLEM_LONGARM.get(), AstaliteGolemLongarmRenderer::new);
        EntityRenderers.register(EEntityTypes.ANTHEKTITE_SLASH.get(), AnthektiteSlashRenderer::new);
        EntityRenderers.register(EEntityTypes.RUSH_PROJECTILE.get(), RushProjectileEntityRenderer::new);
        EntityRenderers.register(EEntityTypes.SWORD_DANCE_SLASH.get(), SwordDanceSlashRenderer::new);
        EntityRenderers.register(EEntityTypes.PULSE_BURST.get(), PulseBurstEntityRenderer::new);
        EntityRenderers.register(EEntityTypes.WRATH_TRIDENT.get(), TestTridentRenderer::new);

        EntityRenderers.register(EEntityTypes.FRAGMENTED_WITHER.get(), FragmentedWitherRenderer::new);

        EntityRenderers.register(EEntityTypes.CATALYST_TOTEM_MARKER.get(), NoRenderRenderer::new);

        if (cataclysm) {
            EntityRenderers.register(CompatEntityTypes.CataclysmEntities.CATALYST_IGNIS_FIREBALL.get(), CatalystIgnisFireballRenderer::new);
            EntityRenderers.register(CompatEntityTypes.CataclysmEntities.CATALYST_FLAME_STRIKE.get(), Flame_Strike_Renderer::new);
        }

        if (EConfig.CLIENT.lavaRendererType.get()) ItemBlockRenderTypes.setRenderLayer(Fluids.LAVA, RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerTooltip(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(CatalystTooltip.class ,ClientCatalystTooltip::new);
    }

    @SubscribeEvent
    public static void setupEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        EModelLayers.register(event);
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
                }
            });
            LivingEntityRenderer<ArmorStand, ? extends EntityModel<ArmorStand>> livingEntityRenderer = addLayersEvent.getRenderer(EntityType.ARMOR_STAND);
            if(livingEntityRenderer instanceof ArmorStandRenderer armorStandRenderer){
                armorStandRenderer.addLayer(new CatalystElytraLayer<>(armorStandRenderer, entityModels));
            }
        }
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(EParticles.PARRY.get(), ParryParticle.Provider::new);
        event.registerSpriteSet(EParticles.PARRY_RESONANCE.get(), ParryParticle.Provider::new);
        event.registerSpriteSet(EParticles.SONIC_BURST.get(), SonicBurstParticle.Provider::new);
        event.registerSpriteSet(EParticles.INITIAL_BURST.get(), InitialBurstParticle.Provider::new);
        event.registerSpriteSet(EParticles.SONIC_BOOM_SACRIFICE.get(), SonicBoomParticle.Provider::new);
        event.registerSpriteSet(EParticles.SONIC_BURST_SACRIFICE.get(), SonicBurstParticle.Provider::new);
        event.registerSpriteSet(EParticles.INITIAL_BURST_SACRIFICE.get(), InitialBurstParticle.Provider::new);
        event.registerSpriteSet(EParticles.SLASH_IMPACT.get(), AnthektiteSlashImpactParticle.Provider::new);
        event.registerSpriteSet(EParticles.SLASH_HIT.get(), AnthektiteSlashImpactParticle.Provider::new);
        event.registerSpriteSet(EParticles.SLASH_CLASH.get(), AnthektiteSlashImpactParticle.Provider::new);
        event.registerSpriteSet(EParticles.SLASH_TRAIL.get(), SlashTrailParticle.Provider::new);
        event.registerSpriteSet(EParticles.RUSH_TRAIL.get(), RushTrailParticle.Provider::new);
        event.registerSpriteSet(EParticles.SACRIFICE_SCULK_SOUL.get(), SoulParticle.EmissiveProvider::new);
//        event.registerSpriteSet(ModParticleTypes.SLASH_AFTER_EFFECT.get(), new SlashAfterEffectsParticle.Provider());
        event.registerSpecial(EParticles.SONIC_BURST_EMITTER.get(), new BurstEmitterParticle.normalProvider());
        event.registerSpecial(EParticles.SONIC_BURST_SACRIFICE_EMITTER.get(), new BurstEmitterParticle.sacrificeProvider());
    }

    public static Model getArmorModel(LivingEntity entityLiving, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> _default) {
        return IClientCatalystExtension.ofCore(stack).getGenericArmorModel(entityLiving, stack, slot, _default);
    }

    @SuppressWarnings("unchecked")
    public static <T extends LivingEntity> void copyModelProperties(HumanoidModel<T> original, HumanoidModel<?> replacement) {
        // this function does not make use of the <T> generic, so the unchecked cast should be safe
        original.copyPropertiesTo((HumanoidModel<T>)replacement);
        replacement.head.visible = original.head.visible;
        replacement.hat.visible = original.hat.visible;
        replacement.body.visible = original.body.visible;
        replacement.rightArm.visible = original.rightArm.visible;
        replacement.leftArm.visible = original.leftArm.visible;
        replacement.rightLeg.visible = original.rightLeg.visible;
        replacement.leftLeg.visible = original.leftLeg.visible;
    }

    public static Model getTrimArmorModel(LivingEntity entityLiving, ItemStack core, EquipmentSlot slot, HumanoidModel<?> _default) {
        return ICatalystTrim.of(core).getGenericArmorModel(entityLiving, core, slot, _default);
    }

    @SuppressWarnings("unchecked")
    public static <T extends LivingEntity> void copyTrimModelProperties(HumanoidModel<T> original, HumanoidModel<?> replacement) {
        // this function does not make use of the <T> generic, so the unchecked cast should be safe
        original.copyPropertiesTo((HumanoidModel<T>)replacement);
        replacement.head.visible = original.head.visible;
        replacement.hat.visible = original.hat.visible;
        replacement.body.visible = original.body.visible;
        replacement.rightArm.visible = original.rightArm.visible;
        replacement.leftArm.visible = original.leftArm.visible;
        replacement.rightLeg.visible = original.rightLeg.visible;
        replacement.leftLeg.visible = original.leftLeg.visible;
    }
}
