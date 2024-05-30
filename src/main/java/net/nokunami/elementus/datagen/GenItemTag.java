package net.nokunami.elementus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.compat.theaether.AEItemsRegistry;
import net.nokunami.elementus.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class GenItemTag extends ItemTagsProvider {
    public GenItemTag(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP,
                      CompletableFuture<TagLookup<Block>> compFeaTagB, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, compFeaTagB, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ItemTags.SWORDS)
                .add(
                        ModItems.STEEL_SWORD.get(),
                        ModItems.ANTHEKTITE_SWORD.get(),
                        ModItems.DIARKRITE_SWORD.get()
                );

        this.tag(ItemTags.SHOVELS)
                .add(
                        ModItems.STEEL_SHOVEL.get(),
                        ModItems.ANTHEKTITE_SHOVEL.get(),
                        ModItems.DIARKRITE_SHOVEL.get()
                );

        this.tag(ItemTags.PICKAXES)
                .add(
                        ModItems.STEEL_PICKAXE.get(),
                        ModItems.ANTHEKTITE_PICKAXE.get(),
                        ModItems.DIARKRITE_PICKAXE.get()
                );

        this.tag(ItemTags.AXES)
                .add(
                        ModItems.STEEL_AXE.get(),
                        ModItems.ANTHEKTITE_AXE.get(),
                        ModItems.DIARKRITE_AXE.get()
                );

        this.tag(ItemTags.HOES)
                .add(
                        ModItems.STEEL_HOE.get(),
                        ModItems.ANTHEKTITE_HOE.get(),
                        ModItems.DIARKRITE_HOE.get()
                );

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(
                        ModItems.STEEL_HELMET.get(),
                        ModItems.STEEL_CHESTPLATE.get(),
                        ModItems.STEEL_LEGGINGS.get(),
                        ModItems.STEEL_BOOTS.get(),
                        ModItems.ANTHEKTITE_HELEMT.get(),
                        ModItems.ANTHEKTITE_CHESTPLATE.get(),
                        ModItems.ANTHEKTITE_LEGGINGS.get(),
                        ModItems.ANTHEKTITE_BOOTS.get(),
                        ModItems.DIARKRITE_HELEMT.get(),
                        ModItems.DIARKRITE_CHESTPLATE.get(),
                        ModItems.DIARKRITE_LEGGINGS.get(),
                        ModItems.DIARKRITE_BOOTS.get()
//                        AEItemsRegistry.STEEL_GLOVES.get(),
//                        AEItemsRegistry.DIARKRITE_GLOVES.get(),
//                        AEItemsRegistry.ANTHEKTITE_GLOVES.get()
                );

        if (ModList.get().isLoaded("farmersdelight")) {
            this.tag(ModTags.KNIVES)
                    .add(
                            FDItemsRegistry.STEEL_KNIFE.get(),
                            FDItemsRegistry.ANTHEKTITE_KNIFE.get(),
                            FDItemsRegistry.DIARKRITE_KNIFE.get()
                    );

        }
    }
}
