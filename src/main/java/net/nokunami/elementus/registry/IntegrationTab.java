package net.nokunami.elementus.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.compat.farmersdelight.NDItemsRegistry;
import net.nokunami.elementus.compat.ironsspellbooks.ISSItemsRegistry;
import net.nokunami.elementus.compat.piercingpaxels.PPItemsRegistry;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.compat.simplyswords.SSItemsRegistry;
import net.nokunami.elementus.compat.sniffsweapons.SniffsWeaponsRegistry;
import net.nokunami.elementus.compat.theaether.AEItemsRegistry;

public class IntegrationTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Elementus.MODID);

    public static final RegistryObject<CreativeModeTab> ELEMENTUS_MOD_INTEGRATION = CREATIVE_MODE_TABS.register("elementus_mod_integration",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIARKRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.elementus_mod_integration"))
                    .displayItems((pParameters, pOutput) -> {

                        if (ModList.get().isLoaded("farmersdelight")) {
                            pOutput.accept(FDItemsRegistry.STEEL_KNIFE.get());
                            pOutput.accept(FDItemsRegistry.DIARKRITE_KNIFE.get());
                            pOutput.accept(FDItemsRegistry.ANTHEKTITE_KNIFE.get());
                        }

                        if (ModList.get().isLoaded("piercingpaxels")) {
                            pOutput.accept(PPItemsRegistry.STEEL_PAXEL.get());
                            pOutput.accept(PPItemsRegistry.DIARKRITE_PAXEL.get());
                            pOutput.accept(PPItemsRegistry.ANTHEKTITE_PAXEL.get());
                            pOutput.accept(PPItemsRegistry.DIARKRITE_UPGRADE_KIT.get());
                            pOutput.accept(PPItemsRegistry.ANTHEKTITE_UPGRADE_KIT.get());
                        }

                        if (ModList.get().isLoaded("nethersdelight")) {
                            pOutput.accept(NDItemsRegistry.STEEL_MACHETE.get());
                            pOutput.accept(NDItemsRegistry.DIARKRITE_MACHETE.get());
                            pOutput.accept(NDItemsRegistry.ANTHEKTITE_MACHETE.get());
                        }

                        if (ModList.get().isLoaded("irons_spellbooks")) {
                            pOutput.accept(ISSItemsRegistry.STEEL_SPELL_BOOK.get());
                            pOutput.accept(ISSItemsRegistry.DIARKRITE_SPELL_BOOK.get());
                            pOutput.accept(ISSItemsRegistry.ANTHEKTITE_SPELL_BOOK.get());
                            pOutput.accept(ISSItemsRegistry.DIARKRITE_MAGE_HELMET.get());
                            pOutput.accept(ISSItemsRegistry.DIARKRITE_MAGE_CHESTPLATE.get());
                            pOutput.accept(ISSItemsRegistry.DIARKRITE_MAGE_LEGGINGS.get());
                            pOutput.accept(ISSItemsRegistry.DIARKRITE_MAGE_BOOTS.get());
                            pOutput.accept(ISSItemsRegistry.ANTHEKTITE_MAGE_HELMET.get());
                            pOutput.accept(ISSItemsRegistry.ANTHEKTITE_MAGE_CHESTPLATE.get());
                            pOutput.accept(ISSItemsRegistry.ANTHEKTITE_MAGE_LEGGINGS.get());
                            pOutput.accept(ISSItemsRegistry.ANTHEKTITE_MAGE_BOOTS.get());
                        }

                        if (ModList.get().isLoaded("aether")) {
                            pOutput.accept(AEItemsRegistry.STEEL_GLOVES.get());
                            pOutput.accept(AEItemsRegistry.DIARKRITE_GLOVES.get());
                            pOutput.accept(AEItemsRegistry.ANTHEKTITE_GLOVES.get());
                        }

                        if (ModList.get().isLoaded("simplyswords")) {
                            pOutput.accept(SSItemsRegistry.STEEL_CHARKRAM.get());
                            pOutput.accept(SSItemsRegistry.STEEL_CLAYMORE.get());
                            pOutput.accept(SSItemsRegistry.STEEL_CUTLASS.get());
                            pOutput.accept(SSItemsRegistry.STEEL_GLAIVE.get());
                            pOutput.accept(SSItemsRegistry.STEEL_GREATAXE.get());
                            pOutput.accept(SSItemsRegistry.STEEL_GREATHAMMER.get());
                            pOutput.accept(SSItemsRegistry.STEEL_HALBERD.get());
                            pOutput.accept(SSItemsRegistry.STEEL_KATANA.get());
                            pOutput.accept(SSItemsRegistry.STEEL_LONGSWORD.get());
                            pOutput.accept(SSItemsRegistry.STEEL_RAPIER.get());
                            pOutput.accept(SSItemsRegistry.STEEL_SAI.get());
                            pOutput.accept(SSItemsRegistry.STEEL_SCYTHE.get());
                            pOutput.accept(SSItemsRegistry.STEEL_SPEAR.get());
                            pOutput.accept(SSItemsRegistry.STEEL_TWINBLADE.get());
                            pOutput.accept(SSItemsRegistry.STEEL_WARGLAIVE.get());

                            pOutput.accept(SSItemsRegistry.DIARKRITE_CHARKRAM.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_CLAYMORE.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_CUTLASS.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_GLAIVE.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_GREATAXE.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_GREATHAMMER.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_HALBERD.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_KATANA.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_LONGSWORD.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_RAPIER.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_SAI.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_SCYTHE.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_SPEAR.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_TWINBLADE.get());
                            pOutput.accept(SSItemsRegistry.DIARKRITE_WARGLAIVE.get());

                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_CHARKRAM.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_CLAYMORE.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_CUTLASS.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_GLAIVE.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_GREATAXE.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_GREATHAMMER.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_HALBERD.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_KATANA.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_LONGSWORD.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_RAPIER.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_SAI.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_SCYTHE.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_SPEAR.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_TWINBLADE.get());
                            pOutput.accept(SSItemsRegistry.ANTHEKTITE_WARGLAIVE.get());
                        }

                        if (ModList.get().isLoaded("sniffsweapons")) {
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_GREAT_SWORD.get());
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_GREAT_AXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_GREAT_PICKAXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_SURCOAT.get());
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_HELM.get());
                            pOutput.accept(SniffsWeaponsRegistry.PLATED_STEEL_CHESTPLATE.get());
                            pOutput.accept(SniffsWeaponsRegistry.STEEL_HORNED_HELM.get());

                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_GREAT_SWORD.get());
                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_GREAT_AXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_GREAT_PICKAXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_SURCOAT.get());
                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_HELM.get());
                            pOutput.accept(SniffsWeaponsRegistry.PLATED_DIARKRITE_CHESTPLATE.get());
                            pOutput.accept(SniffsWeaponsRegistry.DIARKRITE_HORNED_HELM.get());

                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_GREAT_SWORD.get());
                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_GREAT_AXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_GREAT_PICKAXE.get());
                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_SURCOAT.get());
                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_HELM.get());
                            pOutput.accept(SniffsWeaponsRegistry.PLATED_ANTHEKTITE_CHESTPLATE.get());
                            pOutput.accept(SniffsWeaponsRegistry.ANTHEKTITE_HORNED_HELM.get());
                        }

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
