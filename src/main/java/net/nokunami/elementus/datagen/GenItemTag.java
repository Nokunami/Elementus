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
import net.nokunami.elementus.registry.ItemsRegistry;
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
                .add(ItemsRegistry.STEEL_SWORD.get(),
                        ItemsRegistry.ANTHEKTITE_SWORD.get(),
                        ItemsRegistry.DIARKRITE_SWORD.get());

        this.tag(ItemTags.SHOVELS)
                .add(ItemsRegistry.STEEL_SHOVEL.get(),
                        ItemsRegistry.ANTHEKTITE_SHOVEL.get(),
                        ItemsRegistry.DIARKRITE_SHOVEL.get());

        this.tag(ItemTags.PICKAXES)
                .add(ItemsRegistry.STEEL_PICKAXE.get(),
                        ItemsRegistry.ANTHEKTITE_PICKAXE.get(),
                        ItemsRegistry.DIARKRITE_PICKAXE.get());

        this.tag(ItemTags.AXES)
                .add(ItemsRegistry.STEEL_AXE.get(),
                        ItemsRegistry.ANTHEKTITE_AXE.get(),
                        ItemsRegistry.DIARKRITE_AXE.get());

        this.tag(ItemTags.HOES)
                .add(ItemsRegistry.STEEL_HOE.get(),
                        ItemsRegistry.ANTHEKTITE_HOE.get(),
                        ItemsRegistry.DIARKRITE_HOE.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ItemsRegistry.STEEL_HELEMT.get(),
                        ItemsRegistry.STEEL_CHESTPLATE.get(),
                        ItemsRegistry.STEEL_LEGGINGS.get(),
                        ItemsRegistry.STEEL_BOOTS.get(),
                        ItemsRegistry.ANTHEKTITE_HELEMT.get(),
                        ItemsRegistry.ANTHEKTITE_CHESTPLATE.get(),
                        ItemsRegistry.ANTHEKTITE_LEGGINGS.get(),
                        ItemsRegistry.ANTHEKTITE_BOOTS.get(),
                        ItemsRegistry.DIARKRITE_HELEMT.get(),
                        ItemsRegistry.DIARKRITE_CHESTPLATE.get(),
                        ItemsRegistry.DIARKRITE_LEGGINGS.get(),
                        ItemsRegistry.DIARKRITE_BOOTS.get());

        if (ModList.get().isLoaded("farmersdelight")) {
            this.tag(ModTags.KNIVES)
                    .add(FDItemsRegistry.STEEL_KNIFE.get(),
                            FDItemsRegistry.ANTHEKTITE_KNIFE.get(),
                            FDItemsRegistry.DIARKRITE_KNIFE.get());

        }
    }
}
