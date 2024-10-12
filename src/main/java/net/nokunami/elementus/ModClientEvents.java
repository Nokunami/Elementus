package net.nokunami.elementus;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nokunami.elementus.client.ClientProxy;
import net.nokunami.elementus.client.ModAtlases;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.render.ModBoatRenderer;
import net.nokunami.elementus.client.render.ModChestRenderer;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.TAModItems;
import net.nokunami.elementus.common.item.ElementusShield;
import net.nokunami.elementus.common.registry.ModBlockEntityType;
import net.nokunami.elementus.common.registry.ModBlockSetType;
import net.nokunami.elementus.common.registry.ModEntities;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Elementus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    public static CommonProxy PROXY = DistExecutor.safeRunForDist(
            () -> ClientProxy::new, () -> CommonProxy::new);

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ElementusShield.addShieldItemProperties();
        event.enqueueWork(ModAtlases::registerSheets);
        Sheets.addWoodType(ModBlockSetType.MOVCADIA_WOOD_TYPE);
        Sheets.addWoodType(ModBlockSetType.STURDY_MOVCADIA_WOOD_TYPE);

        PROXY.clientInit();

        EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

        if (!ModChecker.fireproofboats()) {
            ItemBlockRenderTypes.setRenderLayer(Fluids.LAVA, RenderType.translucent());
        }

        if (ModChecker.irons_spellbooks()) {
            ISSModItems.getISSCompatItems().stream().filter((item) -> item.get() instanceof SpellBook).forEach((item) -> {
                CuriosRendererRegistry.register((Item) item.get(), SpellBookCurioRenderer::new);
            });
        }
        if (ModChecker.aether()) {
            CuriosRendererRegistry.register(TAModItems.STEEL_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TAModItems.ANTHEKTITE_GLOVES.get(), GlovesRenderer::new);
            CuriosRendererRegistry.register(TAModItems.DIARKRITE_GLOVES.get(), GlovesRenderer::new);
        }
    }

    @SubscribeEvent
    public static void setupEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModModelLayers.register(event);

        event.registerLayerDefinition(ModModelLayers.MOVCADIA_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.MOVCADIA_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers renderers) {
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_CHEST.get(), ModChestRenderer::new);
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_SIGN.get(), SignRenderer::new);
        renderers.registerBlockEntityRenderer(ModBlockEntityType.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        if (ModChecker.sniffsweapons()) {
            event.register((stack, layer) -> {return layer > 0 ? -1 : ((DyeableLeatherItem)stack.getItem()).getColor(stack);},
                    new ItemLike[]{
                            SWModItems.STEEL_SURCOAT.get(),
                            SWModItems.DIARKRITE_SURCOAT.get(),
                            SWModItems.ANTHEKTITE_SURCOAT.get(),
                            SWModItems.STEEL_HELM.get(),
                            SWModItems.DIARKRITE_HELM.get(),
                            SWModItems.ANTHEKTITE_HELM.get(),
                            SWModItems.STEEL_HORNED_HELM.get(),
                            SWModItems.DIARKRITE_HORNED_HELM.get(),
                            SWModItems.ANTHEKTITE_HORNED_HELM.get(),
                            SWModItems.PLATED_STEEL_CHESTPLATE.get(),
                            SWModItems.PLATED_DIARKRITE_CHESTPLATE.get(),
                            SWModItems.PLATED_ANTHEKTITE_CHESTPLATE.get(),
                            SWModItems.STEEL_KABUTO.get(),
                            SWModItems.DIARKRITE_KABUTO.get(),
                            SWModItems.ANTHEKTITE_KABUTO.get(),
                            SWModItems.STEEL_DO.get(),
                            SWModItems.DIARKRITE_DO.get(),
                            SWModItems.ANTHEKTITE_DO.get()
            });
        }
    }
}
