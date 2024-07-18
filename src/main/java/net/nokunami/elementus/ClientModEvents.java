package net.nokunami.elementus;

import com.aetherteam.aether.client.renderer.accessory.GlovesRenderer;
import io.redspace.ironsspellbooks.item.SpellBook;
import io.redspace.ironsspellbooks.render.SpellBookCurioRenderer;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.nokunami.elementus.compat.ironsspellbooks.ISSItemsRegistry;
import net.nokunami.elementus.compat.sniffsweapons.SniffsWeaponsRegistry;
import net.nokunami.elementus.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.item.ElementusShield;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

import static net.nokunami.elementus.Elementus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {
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

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        if (ModList.get().isLoaded("sniffsweapons")) {
            event.register((stack, layer) -> {return layer > 0 ? -1 : ((DyeableLeatherItem)stack.getItem()).getColor(stack);},
                    new ItemLike[]{
                            (ItemLike) SniffsWeaponsRegistry.STEEL_SURCOAT.get(),
                            (ItemLike)SniffsWeaponsRegistry.DIARKRITE_SURCOAT.get(),
                            (ItemLike)SniffsWeaponsRegistry.ANTHEKTITE_SURCOAT.get(),
                            (ItemLike)SniffsWeaponsRegistry.STEEL_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.DIARKRITE_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.ANTHEKTITE_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.STEEL_HORNED_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.DIARKRITE_HORNED_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.ANTHEKTITE_HORNED_HELM.get(),
                            (ItemLike)SniffsWeaponsRegistry.PLATED_STEEL_CHESTPLATE.get(),
                            (ItemLike)SniffsWeaponsRegistry.PLATED_DIARKRITE_CHESTPLATE.get(),
                            (ItemLike)SniffsWeaponsRegistry.PLATED_ANTHEKTITE_CHESTPLATE.get()
            });
        }
    }
}
