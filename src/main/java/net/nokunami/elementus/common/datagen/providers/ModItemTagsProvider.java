package net.nokunami.elementus.common.datagen.providers;

import com.autovw.advancednetherite.common.item.AdvancedPickaxeItem;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.common.compat.farmersdelight.FDModItems;
import net.nokunami.elementus.Elementus;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP,
                               CompletableFuture<TagLookup<Block>> compFeaTagB, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, compFeaTagB, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

//        this.tag(ItemTags.SWORDS).add(
//                        ModItems.STEEL_SWORD.get(),
//                        ModItems.ANTHEKTITE_SWORD.get(),
//                        ModItems.DIARKRITE_SWORD.get()
//                );

//        this.tag(ItemTags.SHOVELS).add(
//                        ModItems.STEEL_SHOVEL.get(),
//                        ModItems.ANTHEKTITE_SHOVEL.get(),
//                        ModItems.DIARKRITE_SHOVEL.get()
//                );

//        this.tag(ItemTags.PICKAXES).add(
//                        ModItems.STEEL_PICKAXE.get(),
//                        ModItems.ANTHEKTITE_PICKAXE.get(),
//                        ModItems.DIARKRITE_PICKAXE.get()
//                );

//        this.tag(ItemTags.AXES).add(
//                        ModItems.STEEL_AXE.get(),
//                        ModItems.ANTHEKTITE_AXE.get(),
//                        ModItems.DIARKRITE_AXE.get()
//                );

//        this.tag(ItemTags.HOES).add(
//                        ModItems.STEEL_HOE.get(),
//                        ModItems.ANTHEKTITE_HOE.get(),
//                        ModItems.DIARKRITE_HOE.get()
//                );
//
//        this.tag(ItemTags.TRIMMABLE_ARMOR).add(
//                        ModItems.STEEL_HELMET.get(),
//                        ModItems.STEEL_CHESTPLATE.get(),
//                        ModItems.STEEL_LEGGINGS.get(),
//                        ModItems.STEEL_BOOTS.get(),
//                        ModItems.ANTHEKTITE_HELMET.get(),
//                        ModItems.ANTHEKTITE_CHESTPLATE.get(),
//                        ModItems.ANTHEKTITE_LEGGINGS.get(),
//                        ModItems.ANTHEKTITE_BOOTS.get(),
//                        ModItems.DIARKRITE_HELMET.get(),
//                        ModItems.DIARKRITE_CHESTPLATE.get(),
//                        ModItems.DIARKRITE_LEGGINGS.get(),
//                        ModItems.DIARKRITE_BOOTS.get()
//                        AEItemsRegistry.STEEL_GLOVES.get(),
//                        AEItemsRegistry.DIARKRITE_GLOVES.get(),
//                        AEItemsRegistry.ANTHEKTITE_GLOVES.get()
//                );

        if (ModList.get().isLoaded("farmersdelight")) {
            tag(ModTags.KNIVES).add(
                            FDModItems.STEEL_KNIFE.get(),
                            FDModItems.ANTHEKTITE_KNIFE.get(),
                            FDModItems.DIARKRITE_KNIFE.get()
                    );
        }

        if (ModList.get().isLoaded("advancednetherite")) {
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_IRON).add(
                    ANModItems.DIARKRITE_IRON_AXE.get(),
                    ANModItems.DIARKRITE_IRON_HOE.get(),
                    ANModItems.DIARKRITE_IRON_PICKAXE.get(),
                    ANModItems.DIARKRITE_IRON_SHOVEL.get(),
                    ANModItems.DIARKRITE_IRON_SWORD.get(),

                    ANModItems.ANTHEKTITE_IRON_AXE.get(),
                    ANModItems.ANTHEKTITE_IRON_HOE.get(),
                    ANModItems.ANTHEKTITE_IRON_PICKAXE.get(),
                    ANModItems.ANTHEKTITE_IRON_SHOVEL.get(),
                    ANModItems.ANTHEKTITE_IRON_SWORD.get()
                    );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_GOLD).add(
                    ANModItems.DIARKRITE_GOLD_AXE.get(),
                    ANModItems.DIARKRITE_GOLD_HOE.get(),
                    ANModItems.DIARKRITE_GOLD_PICKAXE.get(),
                    ANModItems.DIARKRITE_GOLD_SHOVEL.get(),
                    ANModItems.DIARKRITE_GOLD_SWORD.get(),

                    ANModItems.ANTHEKTITE_GOLD_AXE.get(),
                    ANModItems.ANTHEKTITE_GOLD_HOE.get(),
                    ANModItems.ANTHEKTITE_GOLD_PICKAXE.get(),
                    ANModItems.ANTHEKTITE_GOLD_SHOVEL.get(),
                    ANModItems.ANTHEKTITE_GOLD_SWORD.get()
                    );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_EMERALD).add(
                    ANModItems.DIARKRITE_EMERALD_AXE.get(),
                    ANModItems.DIARKRITE_EMERALD_HOE.get(),
                    ANModItems.DIARKRITE_EMERALD_PICKAXE.get(),
                    ANModItems.DIARKRITE_EMERALD_SHOVEL.get(),
                    ANModItems.DIARKRITE_EMERALD_SWORD.get(),

                    ANModItems.ANTHEKTITE_EMERALD_AXE.get(),
                    ANModItems.ANTHEKTITE_EMERALD_HOE.get(),
                    ANModItems.ANTHEKTITE_EMERALD_PICKAXE.get(),
                    ANModItems.ANTHEKTITE_EMERALD_SHOVEL.get(),
                    ANModItems.ANTHEKTITE_EMERALD_SWORD.get()
                    );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_DIAMOND).add(
                    ANModItems.DIARKRITE_DIAMOND_AXE.get(),
                    ANModItems.DIARKRITE_DIAMOND_HOE.get(),
                    ANModItems.DIARKRITE_DIAMOND_PICKAXE.get(),
                    ANModItems.DIARKRITE_DIAMOND_SHOVEL.get(),
                    ANModItems.DIARKRITE_DIAMOND_SWORD.get(),

                    ANModItems.ANTHEKTITE_DIAMOND_AXE.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_HOE.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_PICKAXE.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_SHOVEL.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_SWORD.get()
                    );

            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_IRON).add(
                    ANModItems.DIARKRITE_IRON_HELMET.get(),
                    ANModItems.DIARKRITE_IRON_CHESTPLATE.get(),
                    ANModItems.DIARKRITE_IRON_LEGGINGS.get(),
                    ANModItems.DIARKRITE_IRON_BOOTS.get(),

                    ANModItems.ANTHEKTITE_IRON_HELMET.get(),
                    ANModItems.ANTHEKTITE_IRON_CHESTPLATE.get(),
                    ANModItems.ANTHEKTITE_IRON_LEGGINGS.get(),
                    ANModItems.ANTHEKTITE_IRON_BOOTS.get()
            );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_GOLD).add(
                    ANModItems.DIARKRITE_GOLD_HELMET.get(),
                    ANModItems.DIARKRITE_GOLD_CHESTPLATE.get(),
                    ANModItems.DIARKRITE_GOLD_LEGGINGS.get(),
                    ANModItems.DIARKRITE_GOLD_BOOTS.get(),

                    ANModItems.ANTHEKTITE_GOLD_HELMET.get(),
                    ANModItems.ANTHEKTITE_GOLD_CHESTPLATE.get(),
                    ANModItems.ANTHEKTITE_GOLD_LEGGINGS.get(),
                    ANModItems.ANTHEKTITE_GOLD_BOOTS.get()
            );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_EMERALD).add(
                    ANModItems.DIARKRITE_EMERALD_HELMET.get(),
                    ANModItems.DIARKRITE_EMERALD_CHESTPLATE.get(),
                    ANModItems.DIARKRITE_EMERALD_LEGGINGS.get(),
                    ANModItems.DIARKRITE_EMERALD_BOOTS.get(),

                    ANModItems.ANTHEKTITE_EMERALD_HELMET.get(),
                    ANModItems.ANTHEKTITE_EMERALD_CHESTPLATE.get(),
                    ANModItems.ANTHEKTITE_EMERALD_LEGGINGS.get(),
                    ANModItems.ANTHEKTITE_EMERALD_BOOTS.get()
            );
            tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_DIAMOND).add(
                    ANModItems.DIARKRITE_DIAMOND_HELMET.get(),
                    ANModItems.DIARKRITE_DIAMOND_CHESTPLATE.get(),
                    ANModItems.DIARKRITE_DIAMOND_LEGGINGS.get(),
                    ANModItems.DIARKRITE_DIAMOND_BOOTS.get(),

                    ANModItems.ANTHEKTITE_DIAMOND_HELMET.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_CHESTPLATE.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_LEGGINGS.get(),
                    ANModItems.ANTHEKTITE_DIAMOND_BOOTS.get()
            );
        }
    }
}
