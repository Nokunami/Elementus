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
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.tags.EItemTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.EID;

public class EItemTagsData extends ItemTagsProvider {
    public EItemTagsData(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP,
                         CompletableFuture<TagLookup<Block>> compFeaTagB, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, compFeaTagB, EID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
        this.modCompatibilityTags();
    }

    private void minecraftTags() {
        tag(ItemTags.SWORDS).add(EItems.ASTALITE_SWORD.get(), EItems.DIARKRITE_SWORD.get(), EItems.ANTHEKTITE_SWORD.get(),
                EItems.MOVCADIA_SWORD.get(),
                EItems.DIARKRITE_CHARGE_BLADE.get(), EItems.ANTHEKTITE_CHARGE_BLADE.get());

        tag(ItemTags.SHOVELS).add(EItems.ASTALITE_SHOVEL.get(), EItems.DIARKRITE_SHOVEL.get(), EItems.ANTHEKTITE_SHOVEL.get(), EItems.MOVCADIA_SHOVEL.get());

        tag(ItemTags.PICKAXES).add(EItems.ASTALITE_PICKAXE.get(), EItems.DIARKRITE_PICKAXE.get(), EItems.ANTHEKTITE_PICKAXE.get(), EItems.MOVCADIA_PICKAXE.get());

        tag(ItemTags.AXES).add(EItems.ASTALITE_AXE.get(), EItems.DIARKRITE_AXE.get(), EItems.ANTHEKTITE_AXE.get(), EItems.MOVCADIA_AXE.get());

        tag(ItemTags.HOES).add(EItems.ASTALITE_HOE.get(), EItems.DIARKRITE_HOE.get(), EItems.ANTHEKTITE_HOE.get(), EItems.MOVCADIA_HOE.get());

        tag(ItemTags.TRIMMABLE_ARMOR).add(EItems.ASTALITE_HELMET.get(), EItems.ASTALITE_CHESTPLATE.get(), EItems.ASTALITE_LEGGINGS.get(), EItems.ASTALITE_BOOTS.get(),
                EItems.ANTHEKTITE_HELMET.get(), EItems.ANTHEKTITE_CHESTPLATE.get(), EItems.ANTHEKTITE_LEGGINGS.get(), EItems.ANTHEKTITE_BOOTS.get(),
                EItems.DIARKRITE_HELMET.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.DIARKRITE_BOOTS.get(),
                EItems.CATALYST_CHESTPLATE.get()/*, EItems.TEST_CATALYST_CHESTPLATE.get()*/);


        tag(ItemTags.TRIM_MATERIALS).add(EItems.ASTALITE_INGOT.get(), EItems.DIARKRITE_INGOT.get(), EItems.ANTHEKTITE_INGOT.get());

        tag(ItemTags.LOGS).addTag(EItemTags.MOVCADIA_LOGS);

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
        tag(Tags.Items.ARMORS_HELMETS).add(EItems.ASTALITE_HELMET.get(), EItems.DIARKRITE_HELMET.get(), EItems.ANTHEKTITE_HELMET.get());
        tag(Tags.Items.ARMORS_CHESTPLATES).add(EItems.ASTALITE_CHESTPLATE.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.ANTHEKTITE_CHESTPLATE.get()/*, EItems.TEST_CATALYST_CHESTPLATE.get()*/);
        tag(Tags.Items.ARMORS_LEGGINGS).add(EItems.ASTALITE_LEGGINGS.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.ANTHEKTITE_LEGGINGS.get());
        tag(Tags.Items.ARMORS_BOOTS).add(EItems.ASTALITE_BOOTS.get(), EItems.DIARKRITE_BOOTS.get(), EItems.ANTHEKTITE_BOOTS.get());

        tag(Tags.Items.TOOLS_SHIELDS).add(EItems.ASTALITE_SHIELD.get(), EItems.DIARKRITE_SHIELD.get(), EItems.ANTHEKTITE_SHIELD.get());

        tag(Tags.Items.INGOTS).addTag(EItemTags.INGOTS_STEEL).addTag(EItemTags.INGOTS_DIARKRITE).addTag(EItemTags.INGOTS_ANTHEKTITE);
        tag(Tags.Items.NUGGETS).addTag(EItemTags.NUGGETS_STEEL);
        tag(Tags.Items.ORES).addTag(EItemTags.ORES_ATELIS);
        tag(Tags.Items.RAW_MATERIALS).addTag(EItemTags.RAW_MATERIALS_STEEL);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(EItemTags.STORAGE_BLOCK_STEEL).addTag(EItemTags.STORAGE_BLOCK_DIARKRITE).addTag(EItemTags.STORAGE_BLOCK_ANTHEKTITE);
        tag(EItemTags.BERRIES).add(EItems.MOVCADIA_BERRIES.get());

        tag(Tags.Items.CHESTS_WOODEN).add(EItems.MOVCADIA_CHEST.get());
    }
    private void elementusTags() {
        tag(EItemTags.STEEL_RECYCLABLE)
                .add(EItems.ASTALITE_SWORD.get(), EItems.ASTALITE_SHOVEL.get(), EItems.ASTALITE_PICKAXE.get(), EItems.ASTALITE_AXE.get(), EItems.ASTALITE_HOE.get())
                .add(EItems.ASTALITE_HELMET.get(), EItems.ASTALITE_CHESTPLATE.get(), EItems.ASTALITE_LEGGINGS.get(), EItems.ASTALITE_BOOTS.get())
                .add(EItems.ASTALITE_SHIELD.get());


        tag(EItemTags.MOVCADIA_LOGS)
                .add(EItems.MOVCADIA_LOG.get(), EItems.STRIPPED_MOVCADIA_LOG.get())
                .add(EItems.MOVCADIA_WOOD.get(), EItems.STRIPPED_MOVCADIA_WOOD.get());

        tag(EItemTags.INGOTS_STEEL).add(EItems.ASTALITE_INGOT.get());
        tag(EItemTags.INGOTS_DIARKRITE).add(EItems.DIARKRITE_INGOT.get());
        tag(EItemTags.INGOTS_ANTHEKTITE).add(EItems.ANTHEKTITE_INGOT.get());
        tag(EItemTags.NUGGETS_STEEL).add(EItems.ASTALITE_NUGGET.get());
        tag(EItemTags.RAW_MATERIALS_STEEL).add(EItems.CRUDE_STEEL.get());
        tag(EItemTags.ORES_ATELIS).add(EItems.ATELIS_SCRAP.get());

        tag(EItemTags.STORAGE_BLOCK_STEEL).add(EItems.STEEL_BLOCK.get());
        tag(EItemTags.STORAGE_BLOCK_DIARKRITE).add(EItems.DIARKRITE_BLOCK.get());
        tag(EItemTags.STORAGE_BLOCK_ANTHEKTITE).add(EItems.ANTHEKTITE_BLOCK.get());

        tag(EItemTags.REPAIRS_ASTALITE_EQUIPMENT).add(EItems.ASTALITE_INGOT.get());
        tag(EItemTags.REPAIRS_DIARKRITE_EQUIPMENT).add(EItems.DIARKRITE_INGOT.get());
        tag(EItemTags.REPAIRS_ANTHEKTITE_EQUIPMENT).add(EItems.ANTHEKTITE_INGOT.get());

        tag(EItemTags.REPAIRS_CATALYST_ARMOR).addTag(EItemTags.ORES_ATELIS);
        tag(EItemTags.CATALYST_ITEMS)
                .add(Items.NETHER_STAR, Items.HEART_OF_THE_SEA, Items.TOTEM_OF_UNDYING)
                .addOptional(new ResourceLocation("cataclysm", "ignitium_ingot"))
                .addOptional(new ResourceLocation("irons_spellbooks", "arcane_ingot"))
                .addOptional(new ResourceLocation("cataclysm", "cursium_ingot"))
                .addOptional(new ResourceLocation("witherstormmod", "withered_nether_star"));
        tag(EItemTags.CATALYST_ELYTRA)
                .add(net.minecraft.world.item.Items.ELYTRA);

        tag(EItemTags.CORE_NETHER_STAR).add(Items.NETHER_STAR);
        tag(EItemTags.CORE_HEART_OF_THE_SEA).add(Items.HEART_OF_THE_SEA);
        tag(EItemTags.CORE_TOTEM_OF_UNDYING).add(Items.TOTEM_OF_UNDYING);

        tag(EItemTags.STEEL_GOLEM_HEAL).add(EItems.ASTALITE_INGOT.get());
        tag(EItemTags.STEEL_GOLEM_REPAIR_HALF).add(EItems.ASTALITE_SCRAP.get());
        tag(EItemTags.STEEL_GOLEM_REPAIR_FULL).add(EItems.STEEL_BLOCK.get());
        tag(EItemTags.STEEL_GOLEM_LEAVES_DECORATION).addTag(ItemTags.LEAVES);
        tag(EItemTags.STEEL_GOLEM_CARPET_DECORATION).addTag(ItemTags.WOOL_CARPETS);
        tag(EItemTags.STEEL_GOLEM_MOSS).add(Items.MOSS_BLOCK, Items.MOSS_CARPET);
    }

    private void modCompatibilityTags() {
        //Alex's Caves
        tag(EItemTags.AC_FERROMAGNETIC)
                .addTag(EItemTags.INGOTS_STEEL).addTag(EItemTags.INGOTS_DIARKRITE).addTag(EItemTags.INGOTS_ANTHEKTITE)
                .addTag(EItemTags.NUGGETS_STEEL).addTag(EItemTags.RAW_MATERIALS_STEEL).addTag(EItemTags.ORES_ATELIS)
                .addTag(EItemTags.STORAGE_BLOCK_STEEL).addTag(EItemTags.STORAGE_BLOCK_DIARKRITE).addTag(EItemTags.STORAGE_BLOCK_ANTHEKTITE)
                .add(EItems.ASTALITE_SCRAP.get())

                .add(EItems.ASTALITE_SWORD.get(), EItems.ASTALITE_SHOVEL.get(), EItems.ASTALITE_PICKAXE.get(), EItems.ASTALITE_AXE.get(), EItems.ASTALITE_HOE.get())
                .add(EItems.DIARKRITE_SWORD.get(), EItems.DIARKRITE_SHOVEL.get(), EItems.DIARKRITE_PICKAXE.get(), EItems.DIARKRITE_AXE.get(), EItems.DIARKRITE_HOE.get())
                .add(EItems.ANTHEKTITE_SWORD.get(), EItems.ANTHEKTITE_SHOVEL.get(), EItems.ANTHEKTITE_PICKAXE.get(), EItems.ANTHEKTITE_AXE.get(), EItems.ANTHEKTITE_HOE.get())

                .add(EItems.ASTALITE_HELMET.get(), EItems.ASTALITE_CHESTPLATE.get(), EItems.ASTALITE_LEGGINGS.get(), EItems.ASTALITE_BOOTS.get())
                .add(EItems.DIARKRITE_HELMET.get(), EItems.DIARKRITE_CHESTPLATE.get(), EItems.DIARKRITE_LEGGINGS.get(), EItems.DIARKRITE_BOOTS.get())
                .add(EItems.ANTHEKTITE_HELMET.get(), EItems.ANTHEKTITE_CHESTPLATE.get(), EItems.ANTHEKTITE_LEGGINGS.get(), EItems.ANTHEKTITE_BOOTS.get())

                .add(EItems.ASTALITE_SHIELD.get(), EItems.DIARKRITE_SHIELD.get(), EItems.ANTHEKTITE_SHIELD.get());

        //Archery Expansion
        tag(EItemTags.ANTI_POWER_BOW).add(EItems.ASTALITE_BOW.get(), EItems.DIARKRITE_BOW.get(), EItems.ANTHEKTITE_BOW.get());

        //Create
        tag(EItemTags.CREATE_MOODED_STRIPPED_LOGS).add(EItems.STRIPPED_MOVCADIA_LOG.get(), EItems.STRIPPED_MOVCADIA_WOOD.get());
    }
}
