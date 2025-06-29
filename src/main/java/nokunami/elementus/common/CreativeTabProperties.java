package nokunami.elementus.common;

//import com.ninni.twigs.registry.TwigsCreativeModeTabs;
//import com.simibubi.create.AllCreativeModeTabs;
//import io.redspace.ironsspellbooks.registries.CreativeTabRegistry;
//import io.redspace.ironsspellbooks.registries.ItemRegistry;
//import net.dakotapride.vanilla_claws.registry.ItemsInit;
import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
//import net.minecraftforge.common.util.MutableHashedLinkedMap;
//import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.MutableHashedLinkedMap;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.registry.ModItems.*;
//import net.sweenus.simplyswords.SimplySwords;
//import net.sweenus.simplyswords.registry.ItemsRegistry;
//import nl.sniffiandros.sniffsweapons.reg.ItemReg;
//import nonamecrackers2.witherstormmod.common.init.WitherStormModItemTabs;
//import nonamecrackers2.witherstormmod.common.init.WitherStormModItems;
//import umpaz.nethersdelight.common.registry.NDCreativeTab;
//import umpaz.nethersdelight.common.registry.NDItems;
//import vectorwing.farmersdelight.common.registry.ModCreativeTabs;
//import vectorwing.farmersdelight.common.registry.ModItems;
//import xyz.amymialee.piercingpaxels.PiercingPaxels;

import java.util.function.Supplier;

import static nokunami.elementus.ModChecker.*;

public class CreativeTabProperties {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Elementus.MODID);

    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised

        ResourceKey<CreativeModeTab> tab = event.getTabKey();

        // SpawnEgg
        if (tab == CreativeModeTabs.SPAWN_EGGS) {
            event.insertAfter(new ItemStack(Items.SQUID_SPAWN_EGG), new ItemStack(ElementusItems.STEEL_GOLEM_SPAWN_EGG.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // Food
        if (tab == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.insertAfter(new ItemStack(Items.GLOW_BERRIES), new ItemStack(ElementusItems.MOVCADIA_BERRIES.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_BERRIES.asItem()), new ItemStack(ElementusItems.GLISTERING_MOVCADIA_BERRIES.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // Ingredients
        if (tab == CreativeModeTabs.INGREDIENTS) {
            event.insertAfter(new ItemStack(Items.ANCIENT_DEBRIS), new ItemStack(ElementusItems.REMNANT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.IRON_NUGGET), new ItemStack(ElementusItems.STEEL_NUGGET.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.IRON_INGOT), new ItemStack(ElementusItems.CRUDE_STEEL.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.CRUDE_STEEL.asItem()), new ItemStack(ElementusItems.STEEL_INGOT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_INGOT.asItem()), new ItemStack(ElementusItems.STEEL_SCRAP.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.NETHERITE_INGOT), new ItemStack(ElementusItems.ATELIS_SCRAP.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ATELIS_SCRAP.asItem()), new ItemStack(ElementusItems.DIARKRITE_INGOT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_INGOT.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_INGOT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.BLAZE_POWDER), new ItemStack(ElementusItems.MOVCADIA_ESSENCE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), new ItemStack(ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.asItem()), new ItemStack(ElementusItems.WEAPON_FRAGMENT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // Weapons
        if (tab == CreativeModeTabs.COMBAT) {
            // Swords
//            if  (sniffsWeapons) {
//                putBefore(entries, ItemReg.WOODEN_GREAT_SWORD), new ItemStack(ElementusItems.STEEL_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            } else {
                event.insertAfter(new ItemStack(Items.NETHERITE_SWORD), new ItemStack(ElementusItems.STEEL_SWORD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
            event.insertAfter(new ItemStack(ElementusItems.STEEL_SWORD.asItem()), new ItemStack(ElementusItems.DIARKRITE_SWORD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_SWORD.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_SWORD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            // Axes
//            if  (sniffsWeapons) {
//                putBefore(entries, ItemReg.WOODEN_GREAT_AXE), new ItemStack(ElementusItems.STEEL_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            } else {
                event.insertAfter(new ItemStack(Items.NETHERITE_AXE), new ItemStack(ElementusItems.STEEL_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
            event.insertAfter(new ItemStack(ElementusItems.STEEL_AXE.asItem()), new ItemStack(ElementusItems.DIARKRITE_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_AXE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            // Shields
            event.insertAfter(new ItemStack(Items.SHIELD), new ItemStack(ElementusItems.STEEL_SHIELD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_SHIELD.asItem()), new ItemStack(ElementusItems.DIARKRITE_SHIELD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_SHIELD.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_SHIELD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            // Bows
            event.insertAfter(new ItemStack(Items.BOW), new ItemStack(ElementusItems.STEEL_BOW.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_BOW.asItem()), new ItemStack(ElementusItems.DIARKRITE_BOW.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_BOW.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_BOW.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            // Special Weapon
            event.insertAfter(new ItemStack(Items.TRIDENT), new ItemStack(ElementusItems.DIARKRITE_CHARGE_BLADE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_CHARGE_BLADE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_CHARGE_BLADE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

//            if (sniffsWeapons) {
//                event.insertAfter(ItemReg.NETHERITE_GREAT_SWORD, SniffsWeaponsItems.STEEL_GREAT_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_GREAT_SWORD, SniffsWeaponsItems.DIARKRITE_GREAT_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_GREAT_SWORD, SniffsWeaponsItems.ANTHEKTITE_GREAT_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(ItemReg.NETHERITE_GREAT_AXE, SniffsWeaponsItems.STEEL_GREAT_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_GREAT_AXE, SniffsWeaponsItems.DIARKRITE_GREAT_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_GREAT_AXE, SniffsWeaponsItems.ANTHEKTITE_GREAT_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(ItemReg.NETHERITE_GREAT_PICKAXE, SniffsWeaponsItems.STEEL_GREAT_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_GREAT_PICKAXE, SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE, SniffsWeaponsItems.ANTHEKTITE_GREAT_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(ItemReg.NETHERITE_NAGINATA, SniffsWeaponsItems.STEEL_NAGINATA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_NAGINATA, SniffsWeaponsItems.DIARKRITE_NAGINATA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_NAGINATA, SniffsWeaponsItems.ANTHEKTITE_NAGINATA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//            if (vanillaClaws) {
//                event.insertAfter(ItemsInit.ZIRCON_CLAWS, BanillaClawsItems.STEEL_CLAWS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(BanillaClawsItems.STEEL_CLAWS, BanillaClawsItems.DIARKRITE_CLAWS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(BanillaClawsItems.DIARKRITE_CLAWS, BanillaClawsItems.ANTHEKTITE_CLAWS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
        }
        // Tools
        if (tab == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.insertAfter(new ItemStack(Items.NETHERITE_HOE), new ItemStack(ElementusItems.STEEL_SHOVEL.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_SHOVEL.asItem()), new ItemStack(ElementusItems.STEEL_PICKAXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_PICKAXE.asItem()), new ItemStack(ElementusItems.STEEL_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_AXE.asItem()), new ItemStack(ElementusItems.STEEL_HOE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.STEEL_HOE.asItem()), new ItemStack(ElementusItems.DIARKRITE_SHOVEL.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_SHOVEL.asItem()), new ItemStack(ElementusItems.DIARKRITE_PICKAXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_PICKAXE.asItem()), new ItemStack(ElementusItems.DIARKRITE_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_AXE.asItem()), new ItemStack(ElementusItems.DIARKRITE_HOE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_HOE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_SHOVEL.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_SHOVEL.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_PICKAXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_PICKAXE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_AXE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_AXE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_HOE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.BAMBOO_CHEST_RAFT), new ItemStack(ElementusItems.MOVCADIA_BOAT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_BOAT.asItem()), new ItemStack(ElementusItems.MOVCADIA_CHEST_BOAT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // Armor
        if (tab == CreativeModeTabs.COMBAT) {
//            if  (aether) {
//                event.insertAfter(com.aetherteam.aether.item.AetherItems.NETHERITE_GLOVES), new ItemStack(ElementusItems.STEEL_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            } else {
                event.insertAfter(new ItemStack(Items.NETHERITE_BOOTS), new ItemStack(ElementusItems.STEEL_HELMET.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
            event.insertAfter(new ItemStack(ElementusItems.STEEL_HELMET.asItem()), new ItemStack(ElementusItems.STEEL_CHESTPLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_CHESTPLATE.asItem()), new ItemStack(ElementusItems.STEEL_LEGGINGS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_LEGGINGS.asItem()), new ItemStack(ElementusItems.STEEL_BOOTS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.STEEL_BOOTS.asItem()), new ItemStack(ElementusItems.DIARKRITE_HELMET.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_HELMET.asItem()), new ItemStack(ElementusItems.DIARKRITE_CHESTPLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_CHESTPLATE.asItem()), new ItemStack(ElementusItems.DIARKRITE_LEGGINGS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_LEGGINGS.asItem()), new ItemStack(ElementusItems.DIARKRITE_BOOTS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_BOOTS.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_HELMET.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_HELMET.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_CHESTPLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_CHESTPLATE.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_LEGGINGS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_LEGGINGS.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_BOOTS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_BOOTS.asItem()), new ItemStack(ElementusItems.CATALYST_CHESTPLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

//            if (aether) {
//                event.insertAfter(new ItemStack(ElementusItems.STEEL_BOOTS, AetherItems.STEEL_GLOVES), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_BOOTS, AetherItems.DIARKRITE_GLOVES), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_BOOTS, AetherItems.ANTHEKTITE_GLOVES), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//
//            if (sniffsWeapons) {
//                event.insertAfter(new ItemStack(ElementusItems.STEEL_HELMET, SniffsWeaponsItems.STEEL_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_HELM, SniffsWeaponsItems.STEEL_HORNED_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_HORNED_HELM, SniffsWeaponsItems.STEEL_KABUTO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(new ItemStack(ElementusItems.STEEL_CHESTPLATE, SniffsWeaponsItems.STEEL_SURCOAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_SURCOAT, SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.PLATED_STEEL_CHESTPLATE, SniffsWeaponsItems.STEEL_DO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.STEEL_DO, SniffsWeaponsItems.CLOTHED_STEEL_CUIRASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_HELMET, SniffsWeaponsItems.DIARKRITE_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_HELM, SniffsWeaponsItems.DIARKRITE_HORNED_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_HORNED_HELM, SniffsWeaponsItems.DIARKRITE_KABUTO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_CHESTPLATE, SniffsWeaponsItems.DIARKRITE_SURCOAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_SURCOAT, SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.PLATED_DIARKRITE_CHESTPLATE, SniffsWeaponsItems.DIARKRITE_DO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.DIARKRITE_DO, SniffsWeaponsItems.CLOTHED_DIARKRITE_CUIRASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_HELMET, SniffsWeaponsItems.ANTHEKTITE_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.ANTHEKTITE_HELM, SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.ANTHEKTITE_HORNED_HELM, SniffsWeaponsItems.ANTHEKTITE_KABUTO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(new ItemStack(ElementusItems.ANTHEKTITE_CHESTPLATE, SniffsWeaponsItems.ANTHEKTITE_SURCOAT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.ANTHEKTITE_SURCOAT, SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.PLATED_ANTHEKTITE_CHESTPLATE, SniffsWeaponsItems.ANTHEKTITE_DO), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SniffsWeaponsItems.ANTHEKTITE_DO, SniffsWeaponsItems.CLOTHED_ANTHEKTITE_CUIRASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }

            event.insertAfter(new ItemStack(Items.DIAMOND_HORSE_ARMOR), new ItemStack(ElementusItems.REINFORCED_PLATING_GOLEM_UPGRADE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        // Blocks
        if (tab == CreativeModeTabs.BUILDING_BLOCKS) {
            event.insertAfter(new ItemStack(Items.NETHERITE_BLOCK), new ItemStack(ElementusItems.STEEL_BLOCK.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_BLOCK.asItem()), new ItemStack(ElementusItems.DIARKRITE_BLOCK.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.DIARKRITE_BLOCK.asItem()), new ItemStack(ElementusItems.ANTHEKTITE_BLOCK.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.STEEL_BLOCK.asItem()), new ItemStack(ElementusItems.STEEL_BARS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(ElementusItems.STEEL_BARS.asItem()), new ItemStack(ElementusItems.STEEL_TILES.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_TILES.asItem()), new ItemStack(ElementusItems.STEEL_TILE_STAIR.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STEEL_TILE_STAIR.asItem()), new ItemStack(ElementusItems.STEEL_TILE_SLAB.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);

            event.insertAfter(new ItemStack(Items.BAMBOO_BUTTON), new ItemStack(ElementusItems.MOVCADIA_LOG.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_LOG.asItem()), new ItemStack(ElementusItems.MOVCADIA_WOOD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_WOOD.asItem()), new ItemStack(ElementusItems.STRIPPED_MOVCADIA_LOG.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STRIPPED_MOVCADIA_LOG.asItem()), new ItemStack(ElementusItems.STRIPPED_MOVCADIA_WOOD.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.STRIPPED_MOVCADIA_WOOD.asItem()), new ItemStack(ElementusItems.MOVCADIA_PLANKS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_PLANKS.asItem()), new ItemStack(ElementusItems.MOVCADIA_STAIRS.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_STAIRS.asItem()), new ItemStack(ElementusItems.MOVCADIA_SLAB.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_SLAB.asItem()), new ItemStack(ElementusItems.MOVCADIA_FENCE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_FENCE.asItem()), new ItemStack(ElementusItems.MOVCADIA_FENCE_GATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_FENCE_GATE.asItem()), new ItemStack(ElementusItems.MOVCADIA_DOOR.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_DOOR.asItem()), new ItemStack(ElementusItems.MOVCADIA_TRAPDOOR.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_TRAPDOOR.asItem()), new ItemStack(ElementusItems.MOVCADIA_PRESSURE_PLATE.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_PRESSURE_PLATE.asItem()), new ItemStack(ElementusItems.MOVCADIA_BUTTON.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == CreativeModeTabs.NATURAL_BLOCKS) {
            event.insertAfter(new ItemStack(Items.CHERRY_LOG), new ItemStack(ElementusItems.MOVCADIA_LOG.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.FLOWERING_AZALEA_LEAVES), new ItemStack(ElementusItems.MOVCADIA_LEAVES.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_LEAVES.asItem()), new ItemStack(ElementusItems.FLOWERING_MOVCADIA_LEAVES.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.FLOWERING_AZALEA), new ItemStack(ElementusItems.MOVCADIA_SAPLING.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.ROOTED_DIRT), new ItemStack(ElementusItems.MOVCADIA_ROOTED_DIRT.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.insertAfter(new ItemStack(Items.CHEST), new ItemStack(ElementusItems.MOVCADIA_CHEST.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(Items.BAMBOO_HANGING_SIGN), new ItemStack(ElementusItems.MOVCADIA_SIGN.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_SIGN.asItem()), new ItemStack(ElementusItems.MOVCADIA_HANGING_SIGN.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(new ItemStack(ElementusItems.MOVCADIA_SIGN.asItem()), new ItemStack(ElementusItems.STURDY_MOVCADIA_SIGN.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
        if (tab == CreativeModeTabs.REDSTONE_BLOCKS) {
            event.insertAfter(new ItemStack(Items.CHEST), new ItemStack(ElementusItems.MOVCADIA_CHEST.asItem()), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }

        //Modded Tabs
//        if (farmersDelight) {
//            if (tab == ModCreativeTabs.TAB_FARMERS_DELIGHT.getKey()) {
//                event.insertAfter(ModItems.BAMBOO_CABINET, FarmersDelightItems.MOVCADIA_CABINET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(ModItems.GOLDEN_KNIFE, FarmersDelightItems.STEEL_KNIFE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(FarmersDelightItems.STEEL_KNIFE, FarmersDelightItems.DIARKRITE_KNIFE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(FarmersDelightItems.DIARKRITE_KNIFE, FarmersDelightItems.ANTHEKTITE_KNIFE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (nethersDelight) {
//            if (tab == NDCreativeTab.NETHERS_DELIGHT_TAB.getKey()) {
//                event.insertAfter(NDItems.NETHERITE_MACHETE, NethersDelightItems.STEEL_MACHETE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(NethersDelightItems.STEEL_MACHETE, NethersDelightItems.DIARKRITE_MACHETE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(NethersDelightItems.DIARKRITE_MACHETE, NethersDelightItems.ANTHEKTITE_MACHETE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (piercingPaxels) {
//            if (tab == PiercingPaxels.PIERCING_PAXELS_ITEM_GROUP.getKey()) {
//                event.insertAfter(PiercingPaxels.NETHERITE_PAXEL, PiercingPaxelsItems.STEEL_PAXEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(PiercingPaxelsItems.STEEL_PAXEL, PiercingPaxelsItems.DIARKRITE_PAXEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(PiercingPaxelsItems.DIARKRITE_PAXEL, PiercingPaxelsItems.ANTHEKTITE_PAXEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(PiercingPaxels.NETHERITE_UPGRADE_KIT, PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(PiercingPaxelsItems.DIARKRITE_UPGRADE_KIT, PiercingPaxelsItems.ANTHEKTITE_UPGRADE_KIT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (ironsSpellbooks) {
//            if (tab == CreativeTabRegistry.EQUIPMENT_TAB.getKey()) {
//                event.insertAfter(ItemRegistry.DRUIDIC_SPELL_BOOK, IronsSpellbooksItems.STEEL_SPELL_BOOK), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.STEEL_SPELL_BOOK, IronsSpellbooksItems.DIARKRITE_SPELL_BOOK), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.DIARKRITE_SPELL_BOOK, IronsSpellbooksItems.ANTHEKTITE_SPELL_BOOK), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(ItemRegistry.NETHERITE_MAGE_BOOTS, IronsSpellbooksItems.DIARKRITE_MAGE_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.DIARKRITE_MAGE_HELMET, IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.DIARKRITE_MAGE_CHESTPLATE, IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.DIARKRITE_MAGE_LEGGINGS, IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.DIARKRITE_MAGE_BOOTS, IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.ANTHEKTITE_MAGE_HELMET, IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.ANTHEKTITE_MAGE_CHESTPLATE, IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(IronsSpellbooksItems.ANTHEKTITE_MAGE_LEGGINGS, IronsSpellbooksItems.ANTHEKTITE_MAGE_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (simplySwords) {
//            if (tab == SimplySwords.SIMPLYSWORDS.getKey()) {
//                event.insertAfter(ItemsRegistry.RUNIC_HALBERD, SimplySwordsItems.STEEL_LONGSWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_LONGSWORD, SimplySwordsItems.STEEL_TWINBLADE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_TWINBLADE, SimplySwordsItems.STEEL_RAPIER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_RAPIER, SimplySwordsItems.STEEL_KATANA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_KATANA, SimplySwordsItems.STEEL_SAI), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_SAI, SimplySwordsItems.STEEL_SPEAR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_SPEAR, SimplySwordsItems.STEEL_GLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_GLAIVE, SimplySwordsItems.STEEL_CUTLASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_CUTLASS, SimplySwordsItems.STEEL_CLAYMORE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_CLAYMORE, SimplySwordsItems.STEEL_CHAKRAM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_CHAKRAM, SimplySwordsItems.STEEL_GREATAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_GREATAXE, SimplySwordsItems.STEEL_GREATHAMMER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_GREATHAMMER, SimplySwordsItems.STEEL_WARGLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_WARGLAIVE, SimplySwordsItems.STEEL_SCYTHE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_SCYTHE, SimplySwordsItems.STEEL_HALBERD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.STEEL_HALBERD, SimplySwordsItems.DIARKRITE_LONGSWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_LONGSWORD, SimplySwordsItems.DIARKRITE_TWINBLADE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_TWINBLADE, SimplySwordsItems.DIARKRITE_RAPIER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_RAPIER, SimplySwordsItems.DIARKRITE_KATANA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_KATANA, SimplySwordsItems.DIARKRITE_SAI), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_SAI, SimplySwordsItems.DIARKRITE_SPEAR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_SPEAR, SimplySwordsItems.DIARKRITE_GLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_GLAIVE, SimplySwordsItems.DIARKRITE_CUTLASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_CUTLASS, SimplySwordsItems.DIARKRITE_CLAYMORE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_CLAYMORE, SimplySwordsItems.DIARKRITE_CHAKRAM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_CHAKRAM, SimplySwordsItems.DIARKRITE_GREATAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_GREATAXE, SimplySwordsItems.DIARKRITE_GREATHAMMER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_GREATHAMMER, SimplySwordsItems.DIARKRITE_WARGLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_WARGLAIVE, SimplySwordsItems.DIARKRITE_SCYTHE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_SCYTHE, SimplySwordsItems.DIARKRITE_HALBERD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.DIARKRITE_HALBERD, SimplySwordsItems.ANTHEKTITE_LONGSWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_LONGSWORD, SimplySwordsItems.ANTHEKTITE_TWINBLADE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_TWINBLADE, SimplySwordsItems.ANTHEKTITE_RAPIER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_RAPIER, SimplySwordsItems.ANTHEKTITE_KATANA), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_KATANA, SimplySwordsItems.ANTHEKTITE_SAI), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_SAI, SimplySwordsItems.ANTHEKTITE_SPEAR), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_SPEAR, SimplySwordsItems.ANTHEKTITE_GLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_GLAIVE, SimplySwordsItems.ANTHEKTITE_CUTLASS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_CUTLASS, SimplySwordsItems.ANTHEKTITE_CLAYMORE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_CLAYMORE, SimplySwordsItems.ANTHEKTITE_CHAKRAM), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_CHAKRAM, SimplySwordsItems.ANTHEKTITE_GREATAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_GREATAXE, SimplySwordsItems.ANTHEKTITE_GREATHAMMER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_GREATHAMMER, SimplySwordsItems.ANTHEKTITE_WARGLAIVE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_WARGLAIVE, SimplySwordsItems.ANTHEKTITE_SCYTHE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(SimplySwordsItems.ANTHEKTITE_SCYTHE, SimplySwordsItems.ANTHEKTITE_HALBERD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (samuraiDynasty) {
//            if (tab == net.veroxuniverse.samurai_dynasty.registry.CreativeTabRegistry.TAB.getKey()) {
//                event.insertAfter(net.veroxuniverse.samurai_dynasty.registry.ItemsRegistry.GRAY_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.STEEL_SAMURAI_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_HELMET, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS, EpicSamuraiItems.STEEL_SAMURAI_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_BOOTS, EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_HELMET_MASTER, EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(EpicSamuraiItems.STEEL_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_HELMET_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//
//                event.insertAfter(EpicSamuraiItems.DIARKRITE_SAMURAI_BOOTS_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_LIGHT, EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_HELMET_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_CHESTPLATE_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(EpicSamuraiItems.ANTHEKTITE_SAMURAI_LEGGINGS_MASTER, EpicSamuraiItems.ANTHEKTITE_SAMURAI_BOOTS_MASTER), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (twigs) {
//            if (tab == TwigsCreativeModeTabs.TWIG.getKey()) {
//                event.insertAfter(com.ninni.twigs.registry.TwigsItems.BAMBOO_TABLE, TwigsItems.MOVCADIA_TABLE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }
//        if (witherStormMod) {
//            if (tab == WitherStormModItemTabs.CREATIVE_TAB.getKey()) {
//                event.insertAfter(WitherStormModItems.FORMIDI_BLADE, WitherstormModItems.STEEL_CMD_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.STEEL_CMD_SWORD, WitherstormModItems.STEEL_CMD_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.STEEL_CMD_PICKAXE, WitherstormModItems.STEEL_CMD_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.STEEL_CMD_AXE, WitherstormModItems.STEEL_CMD_SHOVEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.STEEL_CMD_SHOVEL, WitherstormModItems.STEEL_CMD_HOE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.STEEL_CMD_HOE, WitherstormModItems.DIARKRITE_CMD_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.DIARKRITE_CMD_SWORD, WitherstormModItems.DIARKRITE_CMD_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.DIARKRITE_CMD_PICKAXE, WitherstormModItems.DIARKRITE_CMD_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.DIARKRITE_CMD_AXE, WitherstormModItems.DIARKRITE_CMD_SHOVEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.DIARKRITE_CMD_SHOVEL, WitherstormModItems.DIARKRITE_CMD_HOE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.DIARKRITE_CMD_HOE, WitherstormModItems.ANTHEKTITE_CMD_SWORD), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.ANTHEKTITE_CMD_SWORD, WitherstormModItems.ANTHEKTITE_CMD_PICKAXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.ANTHEKTITE_CMD_PICKAXE, WitherstormModItems.ANTHEKTITE_CMD_AXE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.ANTHEKTITE_CMD_AXE, WitherstormModItems.ANTHEKTITE_CMD_SHOVEL), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//                event.insertAfter(WitherstormModItems.ANTHEKTITE_CMD_SHOVEL, WitherstormModItems.ANTHEKTITE_CMD_HOE), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            }
//        }

//        if (create && tab.equals(AllCreativeModeTabs.BASE_CREATIVE_TAB.getKey())) {
//            event.insertAfter((Item) AllItems.CRUSHED_ZINC), new ItemStack(ElementusItems.CRUSHED_REMNANT), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//            entries.put(ElementusItems.CRUSHED_REMNANT.get().getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//        }
    }


    //Credit: oreganised mod, GitHub: https://github.com/Xaidee/oreganised
//    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Item after, Supplier<? extends ItemLike> supplier) {
//        ItemLike key = supplier.get();
//        entries.putAfter(new ItemStack(after), new ItemStack(key)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//    }
//    private static void putAfter(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Supplier<? extends  ItemLike> after, Supplier<? extends ItemLike> supplier) {
//        ItemLike key1 = supplier.get();
//        ItemLike key2 = after.get();
//        entries.putAfter(new ItemStack(key2), new ItemStack(key1)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//    }
//
//    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, ItemLike after, Supplier<? extends ItemLike> supplier) {
//        ItemLike key = supplier.get();
//        entries.putBefore(new ItemStack(after), new ItemStack(key)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//    }
//    private static void putBefore(MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> entries, Supplier<? extends  ItemLike> after, Supplier<? extends ItemLike> supplier) {
//        ItemLike key1 = supplier.get();
//        ItemLike key2 = after.get();
//        entries.putBefore(new ItemStack(key2), new ItemStack(key1)), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
//    }


//    public static final DeferredRegister<CreativeModeTab> ELEMENTUS_MOD_INTEGRATION = CREATIVE_MODE_TABS.register("elementus_mod_integration",
//            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ElementusItems.DIARKRITE_INGOT.get()))
//                    .title(Component.translatable("creativetab.elementus_mod_integration"))
//                    .displayItems((parameters, output) -> {
//                        if (farmersDelight) FarmersDelightItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (piercingPaxels) PiercingPaxelsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (nethersDelight) NethersDelightItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (ironsSpellbooks) IronsSpellbooksItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (aether) AetherItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (simplySwords) SimplySwordsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (sniffsWeapons) SniffsWeaponsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (advancedNetherite) AdvancedNetheriteItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (samuraiDynasty) EpicSamuraiItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (twigs) TwigsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (witherStormMod) WitherstormModItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (vanillaClaws) BanillaClawsItems.ITEMS.getEntries().stream().map(RegistryObject::get).forEach(output::accept);
//                        if (create) output.accept(ElementusItems.CRUSHED_REMNANT.get());
//                    })
//                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
