package net.nokunami.elementus.common;

import com.aetherteam.aether.item.AetherItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.util.MutableHashedLinkedMap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.epicsamurai.ESModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.common.compat.farmersdelight.NDModItems;
import net.nokunami.elementus.common.compat.ironsspellbooks.ISSModItems;
import net.nokunami.elementus.common.compat.piercingpaxels.PPModItems;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.compat.simplyswords.SSModItems;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.common.compat.twigs.TWModItems;
import net.nokunami.elementus.common.registry.ModItems;
import nl.sniffiandros.sniffsweapons.reg.ItemReg;

import java.util.function.Supplier;

public class CreativeTabProperties {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Elementus.MODID);

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();
        MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries = event.getEntries();
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            putAfter(entries, Items.ANCIENT_DEBRIS, ModItems.REMNANT);

            putAfter(entries, Items.IRON_NUGGET, ModItems.STEEL_NUGGET);
            putAfter(entries, Items.IRON_INGOT, ModItems.CRUDE_STEEL);
            putAfter(entries, ModItems.CRUDE_STEEL.get(), ModItems.STEEL_INGOT);
            putAfter(entries, ModItems.STEEL_INGOT.get(), ModItems.STEEL_SCRAP);

            putAfter(entries, Items.NETHERITE_INGOT, ModItems.ATELIS_SCRAP);
            putAfter(entries, ModItems.ATELIS_SCRAP.get(), ModItems.DIARKRITE_INGOT);
            putAfter(entries, ModItems.DIARKRITE_INGOT.get(), ModItems.ANTHEKTITE_INGOT);

            putAfter(entries, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE);
            putAfter(entries, Items.WHEAT, ModItems.MOVCADIA_BARK);
        }

        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
            if  (ModChecker.sniffsweapons()) {
                putBefore(entries, ItemReg.WOODEN_GREAT_SWORD.get(), ModItems.STEEL_SWORD);
            } else {
                putAfter(entries, Items.NETHERITE_SWORD, ModItems.STEEL_SWORD);
            }
            putAfter(entries, ModItems.STEEL_SWORD.get(), ModItems.DIARKRITE_SWORD);
            putAfter(entries, ModItems.DIARKRITE_SWORD.get(), ModItems.ANTHEKTITE_SWORD);

            // Axes
            if  (ModChecker.sniffsweapons()) {
                putBefore(entries, ItemReg.WOODEN_GREAT_AXE.get(), ModItems.STEEL_AXE);
            } else {
                putAfter(entries, Items.NETHERITE_AXE, ModItems.STEEL_AXE);
            }
            putAfter(entries, ModItems.STEEL_AXE.get(), ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE.get(), ModItems.ANTHEKTITE_AXE);

            // Shields
            putAfter(entries, Items.SHIELD, ModItems.STEEL_SHIELD);
            putAfter(entries, ModItems.STEEL_SHIELD.get(), ModItems.DIARKRITE_SHIELD);
            putAfter(entries, ModItems.DIARKRITE_SHIELD.get(), ModItems.ANTHEKTITE_SHIELD);

            if (ModChecker.sniffsweapons()) {
                putAfter(entries, ItemReg.NETHERITE_GREAT_SWORD.get(), SWModItems.STEEL_GREAT_SWORD);
                putAfter(entries, SWModItems.STEEL_GREAT_SWORD.get(), SWModItems.DIARKRITE_GREAT_SWORD);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_SWORD.get(), SWModItems.ANTHEKTITE_GREAT_SWORD);

                putAfter(entries, ItemReg.NETHERITE_GREAT_AXE.get(), SWModItems.STEEL_GREAT_AXE);
                putAfter(entries, SWModItems.STEEL_GREAT_AXE.get(), SWModItems.DIARKRITE_GREAT_AXE);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_AXE.get(), SWModItems.ANTHEKTITE_GREAT_AXE);

                putAfter(entries, ItemReg.NETHERITE_GREAT_PICKAXE.get(), SWModItems.STEEL_GREAT_PICKAXE);
                putAfter(entries, SWModItems.STEEL_GREAT_PICKAXE.get(), SWModItems.DIARKRITE_GREAT_PICKAXE);
                putAfter(entries, SWModItems.DIARKRITE_GREAT_PICKAXE.get(), SWModItems.ANTHEKTITE_GREAT_PICKAXE);

                putAfter(entries, ItemReg.NETHERITE_NAGINATA.get(), SWModItems.STEEL_NAGINATA);
                putAfter(entries, SWModItems.STEEL_NAGINATA.get(), SWModItems.DIARKRITE_NAGINATA);
                putAfter(entries, SWModItems.DIARKRITE_NAGINATA.get(), SWModItems.ANTHEKTITE_NAGINATA);
            }
        }

        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            putAfter(entries, Items.NETHERITE_HOE, ModItems.STEEL_SHOVEL);
            putAfter(entries, ModItems.STEEL_SHOVEL.get(), ModItems.STEEL_PICKAXE);
            putAfter(entries, ModItems.STEEL_PICKAXE.get(), ModItems.STEEL_AXE);
            putAfter(entries, ModItems.STEEL_AXE.get(), ModItems.STEEL_HOE);

            putAfter(entries, ModItems.STEEL_HOE.get(), ModItems.DIARKRITE_SHOVEL);
            putAfter(entries, ModItems.DIARKRITE_SHOVEL.get(), ModItems.DIARKRITE_PICKAXE);
            putAfter(entries, ModItems.DIARKRITE_PICKAXE.get(), ModItems.DIARKRITE_AXE);
            putAfter(entries, ModItems.DIARKRITE_AXE.get(), ModItems.DIARKRITE_HOE);

            putAfter(entries, ModItems.DIARKRITE_HOE.get(), ModItems.ANTHEKTITE_SHOVEL);
            putAfter(entries, ModItems.ANTHEKTITE_SHOVEL.get(), ModItems.ANTHEKTITE_PICKAXE);
            putAfter(entries, ModItems.ANTHEKTITE_PICKAXE.get(), ModItems.ANTHEKTITE_AXE);
            putAfter(entries, ModItems.ANTHEKTITE_AXE.get(), ModItems.ANTHEKTITE_HOE);

            putAfter(entries, Items.BAMBOO_CHEST_RAFT, ModItems.MOVCADIA_BOAT);
            putAfter(entries, ModItems.MOVCADIA_BOAT.get(), ModItems.MOVCADIA_CHEST_BOAT);
        }

        // Armor
        if (tab == CreativeModeTabs.COMBAT) {
            if  (ModChecker.aether()) {
                putAfter(entries, AetherItems.NETHERITE_GLOVES.get(), ModItems.STEEL_HELMET);
            } else {
                putAfter(entries, Items.NETHERITE_BOOTS, ModItems.STEEL_HELMET);
            }
            putAfter(entries, ModItems.STEEL_HELMET.get(), ModItems.STEEL_CHESTPLATE);
            putAfter(entries, ModItems.STEEL_CHESTPLATE.get(), ModItems.STEEL_LEGGINGS);
            putAfter(entries, ModItems.STEEL_LEGGINGS.get(), ModItems.STEEL_BOOTS);

            putAfter(entries, ModItems.STEEL_BOOTS.get(), ModItems.DIARKRITE_HELMET);
            putAfter(entries, ModItems.DIARKRITE_HELMET.get(), ModItems.DIARKRITE_CHESTPLATE);
            putAfter(entries, ModItems.DIARKRITE_CHESTPLATE.get(), ModItems.DIARKRITE_LEGGINGS);
            putAfter(entries, ModItems.DIARKRITE_LEGGINGS.get(), ModItems.DIARKRITE_BOOTS);

            putAfter(entries, ModItems.DIARKRITE_BOOTS.get(), ModItems.ANTHEKTITE_HELMET);
            putAfter(entries, ModItems.ANTHEKTITE_HELMET.get(), ModItems.ANTHEKTITE_CHESTPLATE);
            putAfter(entries, ModItems.ANTHEKTITE_CHESTPLATE.get(), ModItems.ANTHEKTITE_LEGGINGS);
            putAfter(entries, ModItems.ANTHEKTITE_LEGGINGS.get(), ModItems.ANTHEKTITE_BOOTS);

            if (ModChecker.aether()) {
                putAfter(entries, ModItems.STEEL_BOOTS.get(), AEItemsRegistry.STEEL_GLOVES);
                putAfter(entries, ModItems.DIARKRITE_BOOTS.get(), AEItemsRegistry.DIARKRITE_GLOVES);
                putAfter(entries, ModItems.ANTHEKTITE_BOOTS.get(), AEItemsRegistry.ANTHEKTITE_GLOVES);
            }

            if (ModChecker.sniffsweapons()) {
                putAfter(entries, ModItems.STEEL_HELMET.get(), SWModItems.STEEL_HELM);
                putAfter(entries, SWModItems.STEEL_HELM.get(), SWModItems.STEEL_HORNED_HELM);
                putAfter(entries, SWModItems.STEEL_HORNED_HELM.get(), SWModItems.STEEL_KABUTO);
                putAfter(entries, ModItems.STEEL_CHESTPLATE.get(), SWModItems.STEEL_SURCOAT);
                putAfter(entries, SWModItems.STEEL_SURCOAT.get(), SWModItems.PLATED_STEEL_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_STEEL_CHESTPLATE.get(), SWModItems.STEEL_DO);

                putAfter(entries, ModItems.DIARKRITE_HELMET.get(), SWModItems.DIARKRITE_HELM);
                putAfter(entries, SWModItems.DIARKRITE_HELM.get(), SWModItems.DIARKRITE_HORNED_HELM);
                putAfter(entries, SWModItems.DIARKRITE_HORNED_HELM.get(), SWModItems.DIARKRITE_KABUTO);
                putAfter(entries, ModItems.DIARKRITE_CHESTPLATE.get(), SWModItems.DIARKRITE_SURCOAT);
                putAfter(entries, SWModItems.DIARKRITE_SURCOAT.get(), SWModItems.PLATED_DIARKRITE_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_DIARKRITE_CHESTPLATE.get(), SWModItems.DIARKRITE_DO);

                putAfter(entries, ModItems.ANTHEKTITE_HELMET.get(), SWModItems.ANTHEKTITE_HELM);
                putAfter(entries, SWModItems.ANTHEKTITE_HELM.get(), SWModItems.ANTHEKTITE_HORNED_HELM);
                putAfter(entries, SWModItems.ANTHEKTITE_HORNED_HELM.get(), SWModItems.ANTHEKTITE_KABUTO);
                putAfter(entries, ModItems.ANTHEKTITE_CHESTPLATE.get(), SWModItems.ANTHEKTITE_SURCOAT);
                putAfter(entries, SWModItems.ANTHEKTITE_SURCOAT.get(), SWModItems.PLATED_ANTHEKTITE_CHESTPLATE);
                putAfter(entries, SWModItems.PLATED_ANTHEKTITE_CHESTPLATE.get(), SWModItems.ANTHEKTITE_DO);
            }
        }

        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            putAfter(entries, Items.NETHERITE_BLOCK, ModItems.STEEL_BLOCK);
            putAfter(entries, ModItems.STEEL_BLOCK.get(), ModItems.DIARKRITE_BLOCK);
            putAfter(entries, ModItems.DIARKRITE_BLOCK.get(), ModItems.ANTHEKTITE_BLOCK);

            putAfter(entries, ModItems.STEEL_BLOCK.get(), ModItems.STEEL_BARS);

            putAfter(entries, Items.BAMBOO_BUTTON, ModItems.MOVCADIA_LOG);
            putAfter(entries, ModItems.MOVCADIA_LOG.get(), ModItems.MOVCADIA_WOOD);
            putAfter(entries, ModItems.MOVCADIA_WOOD.get(), ModItems.STRIPPED_MOVCADIA_LOG);
            putAfter(entries, ModItems.STRIPPED_MOVCADIA_LOG.get(), ModItems.STRIPPED_MOVCADIA_WOOD);
            putAfter(entries, ModItems.STRIPPED_MOVCADIA_WOOD.get(), ModItems.MOVCADIA_PLANKS);
            putAfter(entries, ModItems.MOVCADIA_PLANKS.get(), ModItems.MOVCADIA_STAIRS);
            putAfter(entries, ModItems.MOVCADIA_STAIRS.get(), ModItems.MOVCADIA_SLAB);
            putAfter(entries, ModItems.MOVCADIA_SLAB.get(), ModItems.MOVCADIA_FENCE);
            putAfter(entries, ModItems.MOVCADIA_FENCE.get(), ModItems.MOVCADIA_FENCE_GATE);
            putAfter(entries, ModItems.MOVCADIA_FENCE_GATE.get(), ModItems.MOVCADIA_DOOR);
            putAfter(entries, ModItems.MOVCADIA_DOOR.get(), ModItems.MOVCADIA_TRAPDOOR);
            putAfter(entries, ModItems.MOVCADIA_TRAPDOOR.get(), ModItems.MOVCADIA_PRESSURE_PLATE);
            putAfter(entries, ModItems.MOVCADIA_PRESSURE_PLATE.get(), ModItems.MOVCADIA_BUTTON);
        }

        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            putAfter(entries, Items.ROOTED_DIRT, ModItems.MOVCADIA_ROOTS);
            putAfter(entries, Items.CHERRY_LOG, ModItems.MOVCADIA_LOG);
            putAfter(entries, Items.FLOWERING_AZALEA_LEAVES, ModItems.MOVCADIA_LEAVES);
            putAfter(entries, Items.FLOWERING_AZALEA, ModItems.MOVCADIA_SAPLING);
        }

        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            putAfter(entries, Items.CHEST, ModItems.MOVCADIA_CHEST);
            putAfter(entries, Items.BAMBOO_HANGING_SIGN, ModItems.MOVCADIA_SIGN);
            putAfter(entries, ModItems.MOVCADIA_SIGN.get(), ModItems.MOVCADIA_HANGING_SIGN);
            putAfter(entries, ModItems.MOVCADIA_SIGN.get(), ModItems.STURDY_MOVCADIA_SIGN);
        }

        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            putAfter(entries, Items.CHEST, ModItems.MOVCADIA_CHEST);
        }
    }


    //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised
    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Item after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putAfter(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
        ItemLike key = supplier.get();
        entries.putBefore(new ItemStack(after), new ItemStack(key), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }


    public static final RegistryObject<CreativeModeTab> ELEMENTUS_MOD_INTEGRATION = CREATIVE_MODE_TABS.register("elementus_mod_integration",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIARKRITE_INGOT.get()))
                    .title(Component.translatable("creativetab.elementus_mod_integration"))
                    .displayItems((pParameters, pOutput) -> {

                        if (ModChecker.farmersdelight()) {
                            pOutput.accept(FDModItems.STEEL_KNIFE.get());
                            pOutput.accept(FDModItems.DIARKRITE_KNIFE.get());
                            pOutput.accept(FDModItems.ANTHEKTITE_KNIFE.get());
                            pOutput.accept(FDModItems.MOVCADIA_CABINET.get());
                        }

                        if (ModChecker.piercingpaxels()) {
                            pOutput.accept(PPModItems.STEEL_PAXEL.get());
                            pOutput.accept(PPModItems.DIARKRITE_PAXEL.get());
                            pOutput.accept(PPModItems.ANTHEKTITE_PAXEL.get());
                            pOutput.accept(PPModItems.DIARKRITE_UPGRADE_KIT.get());
                            pOutput.accept(PPModItems.ANTHEKTITE_UPGRADE_KIT.get());
                        }

                        if (ModChecker.nethersdelight()) {
                            pOutput.accept(NDModItems.STEEL_MACHETE.get());
                            pOutput.accept(NDModItems.DIARKRITE_MACHETE.get());
                            pOutput.accept(NDModItems.ANTHEKTITE_MACHETE.get());
                        }

                        if (ModChecker.irons_spellbooks()) {
                            pOutput.accept(ISSModItems.STEEL_SPELL_BOOK.get());
                            pOutput.accept(ISSModItems.DIARKRITE_SPELL_BOOK.get());
                            pOutput.accept(ISSModItems.ANTHEKTITE_SPELL_BOOK.get());
                            pOutput.accept(ISSModItems.DIARKRITE_MAGE_HELMET.get());
                            pOutput.accept(ISSModItems.DIARKRITE_MAGE_CHESTPLATE.get());
                            pOutput.accept(ISSModItems.DIARKRITE_MAGE_LEGGINGS.get());
                            pOutput.accept(ISSModItems.DIARKRITE_MAGE_BOOTS.get());
                            pOutput.accept(ISSModItems.ANTHEKTITE_MAGE_HELMET.get());
                            pOutput.accept(ISSModItems.ANTHEKTITE_MAGE_CHESTPLATE.get());
                            pOutput.accept(ISSModItems.ANTHEKTITE_MAGE_LEGGINGS.get());
                            pOutput.accept(ISSModItems.ANTHEKTITE_MAGE_BOOTS.get());
                        }

                        if (ModChecker.aether()) {
                            pOutput.accept(AEItemsRegistry.STEEL_GLOVES.get());
                            pOutput.accept(AEItemsRegistry.DIARKRITE_GLOVES.get());
                            pOutput.accept(AEItemsRegistry.ANTHEKTITE_GLOVES.get());
                        }

                        if (ModChecker.simplyswords()) {
                            pOutput.accept(SSModItems.STEEL_LONGSWORD.get());
                            pOutput.accept(SSModItems.STEEL_TWINBLADE.get());
                            pOutput.accept(SSModItems.STEEL_RAPIER.get());
                            pOutput.accept(SSModItems.STEEL_SAI.get());
                            pOutput.accept(SSModItems.STEEL_SPEAR.get());
                            pOutput.accept(SSModItems.STEEL_KATANA.get());
                            pOutput.accept(SSModItems.STEEL_GLAIVE.get());
                            pOutput.accept(SSModItems.STEEL_WARGLAIVE.get());
                            pOutput.accept(SSModItems.STEEL_CUTLASS.get());
                            pOutput.accept(SSModItems.STEEL_CLAYMORE.get());
                            pOutput.accept(SSModItems.STEEL_GREATAXE.get());
                            pOutput.accept(SSModItems.STEEL_GREATHAMMER.get());
                            pOutput.accept(SSModItems.STEEL_CHAKRAM.get());
                            pOutput.accept(SSModItems.STEEL_SCYTHE.get());
                            pOutput.accept(SSModItems.STEEL_HALBERD.get());

                            pOutput.accept(SSModItems.DIARKRITE_LONGSWORD.get());
                            pOutput.accept(SSModItems.DIARKRITE_TWINBLADE.get());
                            pOutput.accept(SSModItems.DIARKRITE_RAPIER.get());
                            pOutput.accept(SSModItems.DIARKRITE_SAI.get());
                            pOutput.accept(SSModItems.DIARKRITE_SPEAR.get());
                            pOutput.accept(SSModItems.DIARKRITE_KATANA.get());
                            pOutput.accept(SSModItems.DIARKRITE_GLAIVE.get());
                            pOutput.accept(SSModItems.DIARKRITE_WARGLAIVE.get());
                            pOutput.accept(SSModItems.DIARKRITE_CUTLASS.get());
                            pOutput.accept(SSModItems.DIARKRITE_CLAYMORE.get());
                            pOutput.accept(SSModItems.DIARKRITE_GREATAXE.get());
                            pOutput.accept(SSModItems.DIARKRITE_GREATHAMMER.get());
                            pOutput.accept(SSModItems.DIARKRITE_CHAKRAM.get());
                            pOutput.accept(SSModItems.DIARKRITE_SCYTHE.get());
                            pOutput.accept(SSModItems.DIARKRITE_HALBERD.get());

                            pOutput.accept(SSModItems.ANTHEKTITE_LONGSWORD.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_TWINBLADE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_RAPIER.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_SAI.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_SPEAR.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_KATANA.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_GLAIVE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_WARGLAIVE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_CUTLASS.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_CLAYMORE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_GREATAXE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_GREATHAMMER.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_CHAKRAM.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_SCYTHE.get());
                            pOutput.accept(SSModItems.ANTHEKTITE_HALBERD.get());
                        }

                        if (ModChecker.sniffsweapons()) {
                            pOutput.accept(SWModItems.STEEL_GREAT_SWORD.get());
                            pOutput.accept(SWModItems.STEEL_GREAT_AXE.get());
                            pOutput.accept(SWModItems.STEEL_GREAT_PICKAXE.get());
                            pOutput.accept(SWModItems.STEEL_HELM.get());
                            pOutput.accept(SWModItems.STEEL_HORNED_HELM.get());
                            pOutput.accept(SWModItems.STEEL_KABUTO.get());
                            pOutput.accept(SWModItems.STEEL_SURCOAT.get());
                            pOutput.accept(SWModItems.PLATED_STEEL_CHESTPLATE.get());
                            pOutput.accept(SWModItems.STEEL_DO.get());

                            pOutput.accept(SWModItems.DIARKRITE_GREAT_SWORD.get());
                            pOutput.accept(SWModItems.DIARKRITE_GREAT_AXE.get());
                            pOutput.accept(SWModItems.DIARKRITE_GREAT_PICKAXE.get());
                            pOutput.accept(SWModItems.DIARKRITE_HELM.get());
                            pOutput.accept(SWModItems.DIARKRITE_HORNED_HELM.get());
                            pOutput.accept(SWModItems.DIARKRITE_KABUTO.get());
                            pOutput.accept(SWModItems.DIARKRITE_SURCOAT.get());
                            pOutput.accept(SWModItems.PLATED_DIARKRITE_CHESTPLATE.get());
                            pOutput.accept(SWModItems.DIARKRITE_DO.get());

                            pOutput.accept(SWModItems.ANTHEKTITE_GREAT_SWORD.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_GREAT_AXE.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_GREAT_PICKAXE.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_HELM.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_HORNED_HELM.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_KABUTO.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_SURCOAT.get());
                            pOutput.accept(SWModItems.PLATED_ANTHEKTITE_CHESTPLATE.get());
                            pOutput.accept(SWModItems.ANTHEKTITE_DO.get());
                        }

                        if (ModChecker.advancednetherite()) {
                            pOutput.accept(ANModItems.DIARKRITE_IRON.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND.get());


                            pOutput.accept(ANModItems.DIARKRITE_IRON_AXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_AXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_AXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_AXE.get());

                            pOutput.accept(ANModItems.DIARKRITE_IRON_HOE.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_HOE.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_HOE.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_HOE.get());

                            pOutput.accept(ANModItems.DIARKRITE_IRON_PICKAXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_PICKAXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_PICKAXE.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_PICKAXE.get());

                            pOutput.accept(ANModItems.DIARKRITE_IRON_SHOVEL.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_SHOVEL.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_SHOVEL.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_SHOVEL.get());

                            pOutput.accept(ANModItems.DIARKRITE_IRON_SWORD.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_SWORD.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_SWORD.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_SWORD.get());


                            pOutput.accept(ANModItems.DIARKRITE_IRON_HELMET.get());
                            pOutput.accept(ANModItems.DIARKRITE_IRON_CHESTPLATE.get());
                            pOutput.accept(ANModItems.DIARKRITE_IRON_LEGGINGS.get());
                            pOutput.accept(ANModItems.DIARKRITE_IRON_BOOTS.get());

                            pOutput.accept(ANModItems.DIARKRITE_GOLD_HELMET.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_CHESTPLATE.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_LEGGINGS.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_BOOTS.get());

                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_HELMET.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_CHESTPLATE.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_LEGGINGS.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_BOOTS.get());

                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_HELMET.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_CHESTPLATE.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_LEGGINGS.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_BOOTS.get());


                            pOutput.accept(ANModItems.DIARKRITE_IRON_BLOCK.get());
                            pOutput.accept(ANModItems.DIARKRITE_GOLD_BLOCK.get());
                            pOutput.accept(ANModItems.DIARKRITE_EMERALD_BLOCK.get());
                            pOutput.accept(ANModItems.DIARKRITE_DIAMOND_BLOCK.get());



                            pOutput.accept(ANModItems.ANTHEKTITE_IRON.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND.get());


                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_AXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_AXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_AXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_AXE.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_HOE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_HOE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_HOE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_HOE.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_PICKAXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_PICKAXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_PICKAXE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_PICKAXE.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_SHOVEL.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_SHOVEL.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_SHOVEL.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_SHOVEL.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_SWORD.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_SWORD.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_SWORD.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_SWORD.get());


                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_HELMET.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_CHESTPLATE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_LEGGINGS.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_BOOTS.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_HELMET.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_CHESTPLATE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_LEGGINGS.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_BOOTS.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_HELMET.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_LEGGINGS.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_BOOTS.get());

                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_HELMET.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_BOOTS.get());


                            pOutput.accept(ANModItems.ANTHEKTITE_IRON_BLOCK.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_GOLD_BLOCK.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_EMERALD_BLOCK.get());
                            pOutput.accept(ANModItems.ANTHEKTITE_DIAMOND_BLOCK.get());
                        }

                        if (ModChecker.epicsamurai()) {
                            pOutput.accept(ESModItems.STEEL_SAMURAI_HELMET.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_CHESTPLATE.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_LEGGINGS.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_BOOTS.get());

                            pOutput.accept(ESModItems.STEEL_SAMURAI_HELMET_LIGHT.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_CHESTPLATE_LIGHT.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_LEGGINGS_LIGHT.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_BOOTS_LIGHT.get());

                            pOutput.accept(ESModItems.STEEL_SAMURAI_HELMET_MASTER.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_CHESTPLATE_MASTER.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_LEGGINGS_MASTER.get());
                            pOutput.accept(ESModItems.STEEL_SAMURAI_BOOTS_MASTER.get());


                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_HELMET.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_LEGGINGS.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_BOOTS.get());

                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_HELMET_LIGHT.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_BOOTS_LIGHT.get());

                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_HELMET_MASTER.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER.get());
                            pOutput.accept(ESModItems.DIARKRITE_SAMURAI_BOOTS_MASTER.get());


                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_HELMET.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_BOOTS.get());

                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT.get());

                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_HELMET_MASTER.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER.get());
                            pOutput.accept(ESModItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER.get());
                        }

                        if (ModChecker.twigs()) {
                            pOutput.accept(TWModItems.MOVCADIA_TABLE.get());
                        }

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
