package net.nokunami.elementus.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.MODID;

public class ModItemTagsData extends ItemTagsProvider {
    public ModItemTagsData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP,
                           CompletableFuture<TagLookup<Block>> compFeaTagB, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, compFeaTagB, MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
        this.modCompatibilityTags();
    }

    private void minecraftTags() {
        tag(ItemTags.SWORDS).add(EItems.STEEL_SWORD.get(), EItems.DIARKRITE_SWORD.get(), EItems.ANTHEKTITE_SWORD.get(),
                EItems.MOVCADIA_SWORD.get(),
                EItems.DIARKRITE_CHARGE_BLADE.get(), EItems.ANTHEKTITE_CHARGE_BLADE.get());

        tag(ItemTags.SHOVELS).add(EItems.STEEL_SHOVEL.get(), EItems.DIARKRITE_SHOVEL.get(), EItems.ANTHEKTITE_SHOVEL.get(), EItems.MOVCADIA_SHOVEL.get());

        tag(ItemTags.PICKAXES).add(EItems.STEEL_PICKAXE.get(), EItems.DIARKRITE_PICKAXE.get(), EItems.ANTHEKTITE_PICKAXE.get(), EItems.MOVCADIA_PICKAXE.get());

        tag(ItemTags.AXES).add(EItems.STEEL_AXE.get(), EItems.DIARKRITE_AXE.get(), EItems.ANTHEKTITE_AXE.get(), EItems.MOVCADIA_AXE.get());

        tag(ItemTags.HOES).add(EItems.STEEL_HOE.get(), EItems.DIARKRITE_HOE.get(), EItems.ANTHEKTITE_HOE.get(), EItems.MOVCADIA_HOE.get());

        tag(ItemTags.TRIMMABLE_ARMOR).add(EItems.STEEL_HELMET.get(), EItems.STEEL_CHESTPLATE.get(), EItems.STEEL_LEGGINGS.get(), EItems.STEEL_BOOTS.get(),
                EItems.ANTHEKTITE_HELMET.get(), EItems.ANTHEKTITE_CHESTPLATE.get(), EItems.ANTHEKTITE_LEGGINGS.get(), EItems.ANTHEKTITE_BOOTS.get(),
                EItems.DIARKRITE_HELMET.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.DIARKRITE_BOOTS.get(),
                EItems.TEST_CATALYST_CHESTPLATE.get());


        tag(ItemTags.TRIM_MATERIALS).add(EItems.STEEL_INGOT.get(), EItems.DIARKRITE_INGOT.get(), EItems.ANTHEKTITE_INGOT.get());

        tag(ItemTags.LOGS).addTag(Etags.Items.MOVCADIA_LOGS);

        tag(ItemTags.PLANKS).add(EItems.MOVCADIA_PLANKS.get());

        tag(ItemTags.STAIRS).add(EItems.MOVCADIA_STAIRS.get());
        tag(ItemTags.WOODEN_STAIRS).add(EItems.MOVCADIA_STAIRS.get());

        tag(ItemTags.SLABS).add(EItems.MOVCADIA_SLAB.get());
        tag(ItemTags.WOODEN_SLABS).add(EItems.MOVCADIA_SLAB.get());

        tag(ItemTags.FENCES).add(EItems.MOVCADIA_FENCE.get());
        tag(ItemTags.WOODEN_FENCES).add(EItems.MOVCADIA_FENCE.get());
        tag(Tags.Items.FENCES).add(EItems.MOVCADIA_FENCE.get());

        tag(ItemTags.DOORS).add(EItems.MOVCADIA_DOOR.get());
        tag(ItemTags.WOODEN_DOORS).add(EItems.MOVCADIA_DOOR.get());

        tag(ItemTags.BUTTONS).add(EItems.MOVCADIA_BUTTON.get());
        tag(ItemTags.WOODEN_BUTTONS).add(EItems.MOVCADIA_BUTTON.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(EItems.MOVCADIA_PRESSURE_PLATE.get());

        tag(ItemTags.TRAPDOORS).add(EItems.MOVCADIA_TRAPDOOR.get());
        tag(ItemTags.WOODEN_TRAPDOORS).add(EItems.MOVCADIA_TRAPDOOR.get());

        tag(ItemTags.NON_FLAMMABLE_WOOD).add(EItems.MOVCADIA_LOG.get(), EItems.STRIPPED_MOVCADIA_LOG.get(),
                EItems.MOVCADIA_WOOD.get(), EItems.STRIPPED_MOVCADIA_WOOD.get(), EItems.MOVCADIA_PLANKS.get(),
                EItems.MOVCADIA_SLAB.get(), EItems.MOVCADIA_STAIRS.get(), EItems.MOVCADIA_FENCE.get(),
                EItems.MOVCADIA_FENCE_GATE.get(), EItems.MOVCADIA_DOOR.get(), EItems.MOVCADIA_TRAPDOOR.get(),
                EItems.MOVCADIA_PRESSURE_PLATE.get(), EItems.MOVCADIA_BUTTON.get(), EItems.MOVCADIA_SIGN.get(),
                EItems.STURDY_MOVCADIA_SIGN.get(), EItems.MOVCADIA_HANGING_SIGN.get());

        tag(ItemTags.LEAVES).add(EItems.MOVCADIA_LEAVES.get(), EItems.FLOWERING_MOVCADIA_LEAVES.get());

        tag(ItemTags.SIGNS).add(EItems.MOVCADIA_SIGN.get(), EItems.STURDY_MOVCADIA_SIGN.get());

        tag(ItemTags.HANGING_SIGNS).add(EItems.MOVCADIA_HANGING_SIGN.get());

        tag(ItemTags.SAPLINGS).add(EItems.MOVCADIA_SAPLING.get());

        tag(ItemTags.FOX_FOOD).add(EItems.MOVCADIA_BERRIES.get());
    }
    private void forgeTags() {
        tag(Tags.Items.ARMORS_HELMETS).add(EItems.STEEL_HELMET.get(), EItems.DIARKRITE_HELMET.get(), EItems.ANTHEKTITE_HELMET.get());
        tag(Tags.Items.ARMORS_CHESTPLATES).add(EItems.STEEL_CHESTPLATE.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.ANTHEKTITE_CHESTPLATE.get(), EItems.TEST_CATALYST_CHESTPLATE.get());
        tag(Tags.Items.ARMORS_LEGGINGS).add(EItems.STEEL_LEGGINGS.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.ANTHEKTITE_LEGGINGS.get());
        tag(Tags.Items.ARMORS_BOOTS).add(EItems.STEEL_BOOTS.get(), EItems.DIARKRITE_BOOTS.get(), EItems.ANTHEKTITE_BOOTS.get());

        tag(Tags.Items.TOOLS_SHIELDS).add(EItems.STEEL_SHIELD.get(), EItems.DIARKRITE_SHIELD.get(), EItems.ANTHEKTITE_SHIELD.get());

        tag(Tags.Items.INGOTS).addTag(Etags.Items.INGOTS_STEEL).addTag(Etags.Items.INGOTS_DIARKRITE).addTag(Etags.Items.INGOTS_ANTHEKTITE);
        tag(Tags.Items.NUGGETS).addTag(Etags.Items.NUGGETS_STEEL);
        tag(Tags.Items.ORES).addTag(Etags.Items.ORES_ATELIS);
        tag(Tags.Items.RAW_MATERIALS).addTag(Etags.Items.RAW_MATERIALS_STEEL);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(Etags.Items.STORAGE_BLOCK_STEEL).addTag(Etags.Items.STORAGE_BLOCK_DIARKRITE).addTag(Etags.Items.STORAGE_BLOCK_ANTHEKTITE);
        tag(Etags.Items.BERRIES).add(EItems.MOVCADIA_BERRIES.get());

        tag(Tags.Items.CHESTS_WOODEN).add(EItems.MOVCADIA_CHEST.get());
    }
    private void elementusTags() {
        tag(Etags.Items.STEEL_RECYCLABLE)
                .add(EItems.STEEL_SWORD.get(), EItems.STEEL_SHOVEL.get(), EItems.STEEL_PICKAXE.get(), EItems.STEEL_AXE.get(), EItems.STEEL_HOE.get())
                .add(EItems.STEEL_HELMET.get(), EItems.STEEL_CHESTPLATE.get(), EItems.STEEL_LEGGINGS.get(), EItems.STEEL_BOOTS.get())
                .add(EItems.STEEL_SHIELD.get());


        tag(Etags.Items.MOVCADIA_LOGS)
                .add(EItems.MOVCADIA_LOG.get(), EItems.STRIPPED_MOVCADIA_LOG.get())
                .add(EItems.MOVCADIA_WOOD.get(), EItems.STRIPPED_MOVCADIA_WOOD.get());

        tag(Etags.Items.INGOTS_STEEL).add(EItems.STEEL_INGOT.get());
        tag(Etags.Items.INGOTS_DIARKRITE).add(EItems.DIARKRITE_INGOT.get());
        tag(Etags.Items.INGOTS_ANTHEKTITE).add(EItems.ANTHEKTITE_INGOT.get());
        tag(Etags.Items.NUGGETS_STEEL).add(EItems.STEEL_NUGGET.get());
        tag(Etags.Items.RAW_MATERIALS_STEEL).add(EItems.CRUDE_STEEL.get());
        tag(Etags.Items.ORES_ATELIS).add(EItems.ATELIS_SCRAP.get());

        tag(Etags.Items.STORAGE_BLOCK_STEEL).add(EItems.STEEL_BLOCK.get());
        tag(Etags.Items.STORAGE_BLOCK_DIARKRITE).add(EItems.DIARKRITE_BLOCK.get());
        tag(Etags.Items.STORAGE_BLOCK_ANTHEKTITE).add(EItems.ANTHEKTITE_BLOCK.get());

        tag(Etags.Items.REPAIRS_STEEL_EQUIPMENT).add(EItems.STEEL_INGOT.get());
        tag(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT).add(EItems.DIARKRITE_INGOT.get());
        tag(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT).add(EItems.ANTHEKTITE_INGOT.get());

        tag(Etags.Items.REPAIRS_CATALYST_ARMOR).addTag(Etags.Items.ORES_ATELIS);
        tag(Etags.Items.CATALYST_ITEMS)
                .add(Items.NETHER_STAR, Items.HEART_OF_THE_SEA, Items.TOTEM_OF_UNDYING)
                .addOptional(new ResourceLocation("cataclysm", "ignitium_ingot"))
                .addOptional(new ResourceLocation("irons_spellbooks", "arcane_ingot"))
                .addOptional(new ResourceLocation("cataclysm", "cursium_ingot"))
                .addOptional(new ResourceLocation("witherstormmod", "withered_nether_star"));
        tag(Etags.Items.CATALYST_ELYTRA)
                .add(net.minecraft.world.item.Items.ELYTRA);

        tag(Etags.Items.CORE_NETHER_STAR).add(Items.NETHER_STAR);
        tag(Etags.Items.CORE_HEART_OF_THE_SEA).add(Items.HEART_OF_THE_SEA);
        tag(Etags.Items.CORE_TOTEM_OF_UNDYING).add(Items.TOTEM_OF_UNDYING);

        tag(Etags.Items.STEEL_GOLEM_HEAL).add(EItems.STEEL_INGOT.get());
        tag(Etags.Items.STEEL_GOLEM_REPAIR_HALF).add(EItems.STEEL_SCRAP.get());
        tag(Etags.Items.STEEL_GOLEM_REPAIR_FULL).add(EItems.STEEL_BLOCK.get());
        tag(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION).addTag(ItemTags.LEAVES);
        tag(Etags.Items.STEEL_GOLEM_CARPET_DECORATION).addTag(ItemTags.WOOL_CARPETS);
        tag(Etags.Items.STEEL_GOLEM_MOSS).add(Items.MOSS_BLOCK, Items.MOSS_CARPET);
    }

    private void modCompatibilityTags() {
        //Alex's Caves
        tag(Etags.Items.AC_FERROMAGNETIC)
                .addTag(Etags.Items.INGOTS_STEEL).addTag(Etags.Items.INGOTS_DIARKRITE).addTag(Etags.Items.INGOTS_ANTHEKTITE)
                .addTag(Etags.Items.NUGGETS_STEEL).addTag(Etags.Items.RAW_MATERIALS_STEEL).addTag(Etags.Items.ORES_ATELIS)
                .addTag(Etags.Items.STORAGE_BLOCK_STEEL).addTag(Etags.Items.STORAGE_BLOCK_DIARKRITE).addTag(Etags.Items.STORAGE_BLOCK_ANTHEKTITE)
                .add(EItems.STEEL_SCRAP.get())

                .add(EItems.STEEL_SWORD.get(), EItems.STEEL_SHOVEL.get(), EItems.STEEL_PICKAXE.get(), EItems.STEEL_AXE.get(), EItems.STEEL_HOE.get())
                .add(EItems.DIARKRITE_SWORD.get(), EItems.DIARKRITE_SHOVEL.get(), EItems.DIARKRITE_PICKAXE.get(), EItems.DIARKRITE_AXE.get(), EItems.DIARKRITE_HOE.get())
                .add(EItems.ANTHEKTITE_SWORD.get(), EItems.ANTHEKTITE_SHOVEL.get(), EItems.ANTHEKTITE_PICKAXE.get(), EItems.ANTHEKTITE_AXE.get(), EItems.ANTHEKTITE_HOE.get())

                .add(EItems.STEEL_HELMET.get(), EItems.STEEL_CHESTPLATE.get(), EItems.STEEL_LEGGINGS.get(), EItems.STEEL_BOOTS.get())
                .add(EItems.DIARKRITE_HELMET.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.DIARKRITE_BOOTS.get())
                .add(EItems.ANTHEKTITE_HELMET.get(), EItems.ANTHEKTITE_CHESTPLATE.get(), EItems.ANTHEKTITE_LEGGINGS.get(), EItems.ANTHEKTITE_BOOTS.get())

                .add(EItems.STEEL_SHIELD.get(), EItems.DIARKRITE_SHIELD.get(), EItems.ANTHEKTITE_SHIELD.get());

        //Archery Expansion
        tag(Etags.Items.ANTI_POWER_BOW).add(EItems.STEEL_BOW.get(), EItems.DIARKRITE_BOW.get(), EItems.ANTHEKTITE_BOW.get());

        //Create
        tag(Etags.Items.CREATE_MOODED_STRIPPED_LOGS).add(EItems.STRIPPED_MOVCADIA_LOG.get(), EItems.STRIPPED_MOVCADIA_WOOD.get());
    }
}
