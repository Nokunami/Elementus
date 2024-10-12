package net.nokunami.elementus.datagen.generators;

import com.aetherteam.aether.AetherTags;
import com.ninni.twigs.TwigsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;
import umpaz.nethersdelight.common.tag.NDTags;
import vectorwing.farmersdelight.common.tag.ModTags;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP,
                               CompletableFuture<TagLookup<Block>> compFeaTagB, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, compFeaTagB, Elementus.MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.minecraftTags();
        this.forgeTags();
        this.elementusTags();
        this.modCompatibilityTags();
    }

    private void minecraftTags() {
        tag(ItemTags.SWORDS)
                .add(ModItems.STEEL_SWORD.get()).add(ModItems.ANTHEKTITE_SWORD.get()).add(ModItems.DIARKRITE_SWORD.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_sword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_sword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_sword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_sword"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_sword"));

        tag(ItemTags.SHOVELS)
                .add(ModItems.STEEL_SHOVEL.get()).add(ModItems.ANTHEKTITE_SHOVEL.get()).add(ModItems.DIARKRITE_SHOVEL.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_shovel"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_shovel"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_shovel"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_shovel"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_shovel"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_shovel"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_shovel"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_shovel"));

        tag(ItemTags.PICKAXES)
                .add(ModItems.STEEL_PICKAXE.get()).add(ModItems.ANTHEKTITE_PICKAXE.get()).add(ModItems.DIARKRITE_PICKAXE.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_pickaxe"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_pickaxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_pickaxe"));

        tag(ItemTags.AXES)
                .add(ModItems.STEEL_AXE.get()).add(ModItems.ANTHEKTITE_AXE.get()).add(ModItems.DIARKRITE_AXE.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_axe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_axe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_axe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_axe"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_axe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_axe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_axe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_axe"));

        tag(ItemTags.HOES)
                .add(ModItems.STEEL_HOE.get()).add(ModItems.ANTHEKTITE_HOE.get()).add(ModItems.DIARKRITE_HOE.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_hoe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_hoe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_hoe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_hoe"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_hoe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_hoe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_hoe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_hoe"));

        tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.STEEL_HELMET.get()).add(ModItems.STEEL_CHESTPLATE.get()).add(ModItems.STEEL_LEGGINGS.get()).add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.ANTHEKTITE_HELMET.get()).add(ModItems.ANTHEKTITE_CHESTPLATE.get()).add(ModItems.ANTHEKTITE_LEGGINGS.get()).add(ModItems.ANTHEKTITE_BOOTS.get())
                .add(ModItems.DIARKRITE_HELMET.get()).add(ModItems.DIARKRITE_CHESTPLATE.get()).add(ModItems.DIARKRITE_LEGGINGS.get()).add(ModItems.DIARKRITE_BOOTS.get())
                .addOptional(new ResourceLocation("elementus", "steel_gloves"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gloves"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gloves"))


                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_boots"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_boots"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_boots"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_boots"))


                .addOptional(new ResourceLocation("elementus", "anthektite_iron_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_iron_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_iron_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_iron_boots"))

                .addOptional(new ResourceLocation("elementus", "anthektite_gold_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_boots"))

                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_boots"))

                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_boots"));


        tag(ItemTags.TRIM_MATERIALS)
                .add(ModItems.STEEL_INGOT.get()).add(ModItems.DIARKRITE_INGOT.get()).add(ModItems.ANTHEKTITE_INGOT.get());

        tag(ItemTags.PLANKS)
                .add(ModItems.MOVCADIA_PLANKS.get());

        tag(ItemTags.STAIRS)
                .add(ModItems.MOVCADIA_STAIRS.get());
        tag(ItemTags.WOODEN_STAIRS)
                .add(ModItems.MOVCADIA_STAIRS.get());

        tag(ItemTags.SLABS)
                .add(ModItems.MOVCADIA_SLAB.get());
        tag(ItemTags.WOODEN_SLABS)
                .add(ModItems.MOVCADIA_SLAB.get());

        tag(ItemTags.FENCES)
                .add(ModItems.MOVCADIA_FENCE.get());
        tag(ItemTags.WOODEN_FENCES)
                .add(ModItems.MOVCADIA_FENCE.get());
        tag(Tags.Items.FENCES)
                .add(ModItems.MOVCADIA_FENCE.get());

        tag(ItemTags.DOORS)
                .add(ModItems.MOVCADIA_DOOR.get());
        tag(ItemTags.WOODEN_DOORS)
                .add(ModItems.MOVCADIA_DOOR.get());

        tag(ItemTags.BUTTONS)
                .add(ModItems.MOVCADIA_BUTTON.get());
        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.MOVCADIA_BUTTON.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.MOVCADIA_PRESSURE_PLATE.get());

        tag(ItemTags.TRAPDOORS)
                .add(ModItems.MOVCADIA_TRAPDOOR.get());
        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.MOVCADIA_TRAPDOOR.get());

        tag(ItemTags.NON_FLAMMABLE_WOOD)
                .add(ModItems.MOVCADIA_LOG.get()).add(ModItems.STRIPPED_MOVCADIA_LOG.get())
                .add(ModItems.MOVCADIA_WOOD.get()).add(ModItems.STRIPPED_MOVCADIA_WOOD.get())
                .add(ModItems.MOVCADIA_PLANKS.get()).add(ModItems.MOVCADIA_SLAB.get()).add(ModItems.MOVCADIA_STAIRS.get())
                .add(ModItems.MOVCADIA_FENCE.get()).add(ModItems.MOVCADIA_FENCE_GATE.get())
                .add(ModItems.MOVCADIA_DOOR.get()).add(ModItems.MOVCADIA_TRAPDOOR.get())
                .add(ModItems.MOVCADIA_PRESSURE_PLATE.get()).add(ModItems.MOVCADIA_BUTTON.get())
                .add(ModItems.MOVCADIA_SIGN.get()).add(ModItems.STURDY_MOVCADIA_SIGN.get())
                .add(ModItems.MOVCADIA_HANGING_SIGN.get())
                .addOptional(new ResourceLocation("elementus", "movcadia_cabinet"))
                .addOptional(new ResourceLocation("elementus", "movcadia_table"));

        tag(ItemTags.SIGNS)
                .add(ModItems.MOVCADIA_SIGN.get()).add(ModItems.STURDY_MOVCADIA_SIGN.get());

        tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.MOVCADIA_HANGING_SIGN.get());
    }

    private void forgeTags() {

        tag(Tags.Items.ARMORS_HELMETS).add(ModItems.STEEL_HELMET.get(),ModItems.DIARKRITE_HELMET.get(),ModItems.ANTHEKTITE_HELMET.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_helmet"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_helmet"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_helmet"));

        tag(Tags.Items.ARMORS_CHESTPLATES).add(ModItems.STEEL_CHESTPLATE.get(),ModItems.DIARKRITE_CHESTPLATE.get(),ModItems.ANTHEKTITE_CHESTPLATE.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_chestplate"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_chestplate"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_chestplate"));

        tag(Tags.Items.ARMORS_LEGGINGS).add(ModItems.STEEL_LEGGINGS.get(),ModItems.DIARKRITE_LEGGINGS.get(),ModItems.ANTHEKTITE_LEGGINGS.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_leggings"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_leggings"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_leggings"));

        tag(Tags.Items.ARMORS_BOOTS).add(ModItems.STEEL_BOOTS.get(),ModItems.DIARKRITE_BOOTS.get(),ModItems.ANTHEKTITE_BOOTS.get())
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_boots"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_boots"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_boots"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_boots"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_boots"))

                .addOptional(new ResourceLocation("elementus", "anthektite_iron_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_boots"));

        tag(Tags.Items.TOOLS_SHIELDS).add(ModItems.STEEL_SHIELD.get(),ModItems.DIARKRITE_SHIELD.get(),ModItems.ANTHEKTITE_SHIELD.get());

        tag(Tags.Items.INGOTS).addTags(Etags.Item.INGOTS_STEEL, Etags.Item.INGOTS_DIARKRITE, Etags.Item.INGOTS_ANTHEKTITE);
        tag(Tags.Items.NUGGETS).addTags(Etags.Item.NUGGETS_STEEL);
        tag(Tags.Items.ORES).addTags(Etags.Item.ORES_ATELIS);
        tag(Tags.Items.RAW_MATERIALS).addTags(Etags.Item.RAW_MATERIALS_STEEL);
        tag(Tags.Items.STORAGE_BLOCKS).addTags(Etags.Item.STORAGE_BLOCK_STEEL, Etags.Item.STORAGE_BLOCK_DIARKRITE, Etags.Item.STORAGE_BLOCK_ANTHEKTITE);

    }

    private void elementusTags() {
        tag(Etags.Item.STEEL_RECYCLABLE)
                .add(ModItems.STEEL_SWORD.get()).add(ModItems.STEEL_SHOVEL.get()).add(ModItems.STEEL_PICKAXE.get()).add(ModItems.STEEL_AXE.get()).add(ModItems.STEEL_HOE.get())
                .add(ModItems.STEEL_HELMET.get()).add(ModItems.STEEL_CHESTPLATE.get()).add(ModItems.STEEL_LEGGINGS.get()).add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.STEEL_SHIELD.get())
                .addOptional(new ResourceLocation("dixtas_armory", "steel_dagger"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_shortsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_stiletto"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_rapier"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_katana"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_katana_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_greatsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_longsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_twinblade"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_zweihander"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_battle_axe"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_battle_axe_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_glaive"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_spear"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_spear_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_halberd"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_pike"))

                .addOptional(new ResourceLocation("elementus", "steel_knife"))

                .addOptional(new ResourceLocation("elementus", "steel_machete"))

                .addOptional(new ResourceLocation("elementus", "steel_paxel"))

                .addOptional(new ResourceLocation("elementus", "steel_chakram"))
                .addOptional(new ResourceLocation("elementus", "steel_claymore"))
                .addOptional(new ResourceLocation("elementus", "steel_cutlass"))
                .addOptional(new ResourceLocation("elementus", "steel_glaive"))
                .addOptional(new ResourceLocation("elementus", "steel_greataxe"))
                .addOptional(new ResourceLocation("elementus", "steel_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "steel_halberd"))
                .addOptional(new ResourceLocation("elementus", "steel_katana"))
                .addOptional(new ResourceLocation("elementus", "steel_longsword"))
                .addOptional(new ResourceLocation("elementus", "steel_rapier"))
                .addOptional(new ResourceLocation("elementus", "steel_sai"))
                .addOptional(new ResourceLocation("elementus", "steel_scythe"))
                .addOptional(new ResourceLocation("elementus", "steel_spear"))
                .addOptional(new ResourceLocation("elementus", "steel_twinblade"))
                .addOptional(new ResourceLocation("elementus", "steel_warglaive"))

                .addOptional(new ResourceLocation("elementus", "steel_gloves"));


        tag(Etags.Item.MOVCADIA_LOGS)
                .add(ModItems.MOVCADIA_LOG.get()).add(ModItems.STRIPPED_MOVCADIA_LOG.get())
                .add(ModItems.MOVCADIA_WOOD.get()).add(ModItems.STRIPPED_MOVCADIA_WOOD.get());

        tag(Etags.Item.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(Etags.Item.INGOTS_DIARKRITE).add(ModItems.DIARKRITE_INGOT.get());
        tag(Etags.Item.INGOTS_ANTHEKTITE).add(ModItems.ANTHEKTITE_INGOT.get());
        tag(Etags.Item.NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());
        tag(Etags.Item.RAW_MATERIALS_STEEL).add(ModItems.CRUDE_STEEL.get());
        tag(Etags.Item.ORES_ATELIS).add(ModItems.ATELIS_SCRAP.get());

        tag(Etags.Item.STORAGE_BLOCK_STEEL).add(ModItems.STEEL_BLOCK.get());
        tag(Etags.Item.STORAGE_BLOCK_DIARKRITE).add(ModItems.DIARKRITE_BLOCK.get());
        tag(Etags.Item.STORAGE_BLOCK_ANTHEKTITE).add(ModItems.ANTHEKTITE_BLOCK.get());

        tag(Etags.Item.REPAIRS_STEEL_ARMOR).add(ModItems.STEEL_INGOT.get()).addTags(Etags.Item.INGOTS_STEEL);
        tag(Etags.Item.REPAIRS_DIARKRITE_ARMOR).add(ModItems.DIARKRITE_INGOT.get()).addTags(Etags.Item.INGOTS_DIARKRITE);
        tag(Etags.Item.REPAIRS_ANTHEKTITE_ARMOR).add(ModItems.ANTHEKTITE_INGOT.get()).addTags(Etags.Item.INGOTS_ANTHEKTITE);

        tag(Etags.Item.REPAIRS_DIARKRITE_IRON_ARMOR).addOptional(new ResourceLocation("elementus", "diarkrite_iron_ingot"));
        tag(Etags.Item.REPAIRS_DIARKRITE_GOLD_ARMOR).addOptional(new ResourceLocation("elementus", "diarkrite_gold_ingot"));
        tag(Etags.Item.REPAIRS_DIARKRITE_EMERALD_ARMOR).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_ingot"));
        tag(Etags.Item.REPAIRS_DIARKRITE_DIAMOND_ARMOR).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_ingot"));
        tag(Etags.Item.REPAIRS_ANTHEKTITE_IRON_ARMOR).addOptional(new ResourceLocation("elementus", "anthektite_iron_ingot"));
        tag(Etags.Item.REPAIRS_ANTHEKTITE_GOLD_ARMOR).addOptional(new ResourceLocation("elementus", "anthektite_gold_ingot"));
        tag(Etags.Item.REPAIRS_ANTHEKTITE_EMERALD_ARMOR).addOptional(new ResourceLocation("elementus", "anthektite_emerald_ingot"));
        tag(Etags.Item.REPAIRS_ANTHEKTITE_DIAMOND_ARMOR).addOptional(new ResourceLocation("elementus", "anthektite_diamond_ingot"));
    }

    private void modCompatibilityTags() {
        //Farmer's Delight
        tag(ModTags.KNIVES)
                .addOptional(new ResourceLocation("elementus", "steel_knife"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_knife"))
                .addOptional(new ResourceLocation("elementus", "anthektite_knife"));
        tag(Etags.Item.FD_KNIFE)
                .addOptional(new ResourceLocation("elementus", "steel_knife"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_knife"))
                .addOptional(new ResourceLocation("elementus", "anthektite_knife"));

        //Nether's Delight
        tag(NDTags.MACHETES)
                .addOptional(new ResourceLocation("elementus", "steel_machete"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_machete"))
                .addOptional(new ResourceLocation("elementus", "anthektite_machete"));

        //Iron's Spells 'n Spellbooks
        tag(Etags.Item.CURIOS_SPELLBOOK)
                .addOptional(new ResourceLocation("elementus", "steel_spell_book"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_spell_book"))
                .addOptional(new ResourceLocation("elementus", "anthektite_spell_book"));

        //Aether
        tag(AetherTags.Items.ACCESSORIES_GLOVES)
                .addOptional(new ResourceLocation("elementus", "steel_gloves"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gloves"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gloves"));

        //Simply Swords
        tag(Etags.Item.SIMPLY_SWORDS_SWORDS)
                .addOptional(new ResourceLocation("elementus", "steel_chakram"))
                .addOptional(new ResourceLocation("elementus", "steel_claymore"))
                .addOptional(new ResourceLocation("elementus", "steel_cutlass"))
                .addOptional(new ResourceLocation("elementus", "steel_glaive"))
                .addOptional(new ResourceLocation("elementus", "steel_greataxe"))
                .addOptional(new ResourceLocation("elementus", "steel_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "steel_halberd"))
                .addOptional(new ResourceLocation("elementus", "steel_katana"))
                .addOptional(new ResourceLocation("elementus", "steel_longsword"))
                .addOptional(new ResourceLocation("elementus", "steel_rapier"))
                .addOptional(new ResourceLocation("elementus", "steel_sai"))
                .addOptional(new ResourceLocation("elementus", "steel_scythe"))
                .addOptional(new ResourceLocation("elementus", "steel_spear"))
                .addOptional(new ResourceLocation("elementus", "steel_twinblade"))
                .addOptional(new ResourceLocation("elementus", "steel_warglaive"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_chakram"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_claymore"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_glaive"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_halberd"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_katana"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_longsword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_rapier"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_sai"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_scythe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_spear"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_warglaive"))

                .addOptional(new ResourceLocation("elementus", "anthektite_chakram"))
                .addOptional(new ResourceLocation("elementus", "anthektite_claymore"))
                .addOptional(new ResourceLocation("elementus", "anthektite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "anthektite_glaive"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "anthektite_halberd"))
                .addOptional(new ResourceLocation("elementus", "anthektite_katana"))
                .addOptional(new ResourceLocation("elementus", "anthektite_longsword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_rapier"))
                .addOptional(new ResourceLocation("elementus", "anthektite_sai"))
                .addOptional(new ResourceLocation("elementus", "anthektite_scythe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_spear"))
                .addOptional(new ResourceLocation("elementus", "anthektite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "anthektite_warglaive"));

        tag(Etags.Item.SIMPLY_SWORDS_STEEL)
                .addOptional(new ResourceLocation("elementus", "steel_chakram"))
                .addOptional(new ResourceLocation("elementus", "steel_claymore"))
                .addOptional(new ResourceLocation("elementus", "steel_cutlass"))
                .addOptional(new ResourceLocation("elementus", "steel_glaive"))
                .addOptional(new ResourceLocation("elementus", "steel_greataxe"))
                .addOptional(new ResourceLocation("elementus", "steel_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "steel_halberd"))
                .addOptional(new ResourceLocation("elementus", "steel_katana"))
                .addOptional(new ResourceLocation("elementus", "steel_longsword"))
                .addOptional(new ResourceLocation("elementus", "steel_rapier"))
                .addOptional(new ResourceLocation("elementus", "steel_sai"))
                .addOptional(new ResourceLocation("elementus", "steel_scythe"))
                .addOptional(new ResourceLocation("elementus", "steel_spear"))
                .addOptional(new ResourceLocation("elementus", "steel_twinblade"))
                .addOptional(new ResourceLocation("elementus", "steel_warglaive"));

        tag(Etags.Item.SIMPLY_SWORDS_DIARKRITE)
                .addOptional(new ResourceLocation("elementus", "diarkrite_chakram"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_claymore"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_glaive"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_halberd"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_katana"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_longsword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_rapier"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_sai"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_scythe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_spear"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_warglaive"));

        tag(Etags.Item.SIMPLY_SWORDS_ANTHEKTITE)
                .addOptional(new ResourceLocation("elementus", "anthektite_chakram"))
                .addOptional(new ResourceLocation("elementus", "anthektite_claymore"))
                .addOptional(new ResourceLocation("elementus", "anthektite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "anthektite_glaive"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "anthektite_halberd"))
                .addOptional(new ResourceLocation("elementus", "anthektite_katana"))
                .addOptional(new ResourceLocation("elementus", "anthektite_longsword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_rapier"))
                .addOptional(new ResourceLocation("elementus", "anthektite_sai"))
                .addOptional(new ResourceLocation("elementus", "anthektite_scythe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_spear"))
                .addOptional(new ResourceLocation("elementus", "anthektite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "anthektite_warglaive"));

        //Advanced Netherite
        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_IRON)
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_axe")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_hoe")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_pickaxe")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_shovel")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_iron_axe")).addOptional(new ResourceLocation("elementus", "anthektite_iron_hoe")).addOptional(new ResourceLocation("elementus", "anthektite_iron_pickaxe")).addOptional(new ResourceLocation("elementus", "anthektite_iron_shovel")).addOptional(new ResourceLocation("elementus", "anthektite_iron_sword"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_GOLD)
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_axe")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_hoe")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_pickaxe")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_shovel")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_axe")).addOptional(new ResourceLocation("elementus", "anthektite_gold_hoe")).addOptional(new ResourceLocation("elementus", "anthektite_gold_pickaxe")).addOptional(new ResourceLocation("elementus", "anthektite_gold_shovel")).addOptional(new ResourceLocation("elementus", "anthektite_gold_sword"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_EMERALD)
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_axe")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_hoe")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_pickaxe")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_shovel")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_axe")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_hoe")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_pickaxe")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_shovel")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_sword"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_TOOLS_NETHERITE_DIAMOND)
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_axe")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_hoe")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_pickaxe")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_shovel")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_sword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_axe")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_hoe")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_pickaxe")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_shovel")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_sword"));


        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_IRON)
                .addOptional(new ResourceLocation("elementus", "diarkrite_iron_helmet")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_chestplate")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_leggings")).addOptional(new ResourceLocation("elementus", "diarkrite_iron_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_iron_helmet")).addOptional(new ResourceLocation("elementus", "anthektite_iron_chestplate")).addOptional(new ResourceLocation("elementus", "anthektite_iron_leggings")).addOptional(new ResourceLocation("elementus", "anthektite_iron_boots"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_GOLD)
                .addOptional(new ResourceLocation("elementus", "diarkrite_gold_helmet")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_chestplate")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_leggings")).addOptional(new ResourceLocation("elementus", "diarkrite_gold_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gold_helmet")).addOptional(new ResourceLocation("elementus", "anthektite_gold_chestplate")).addOptional(new ResourceLocation("elementus", "anthektite_gold_leggings")).addOptional(new ResourceLocation("elementus", "anthektite_gold_boots"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_EMERALD)
                .addOptional(new ResourceLocation("elementus", "diarkrite_emerald_helmet")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_chestplate")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_leggings")).addOptional(new ResourceLocation("elementus", "diarkrite_emerald_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_emerald_helmet")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_chestplate")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_leggings")).addOptional(new ResourceLocation("elementus", "anthektite_emerald_boots"));

        tag(com.autovw.advancednetherite.core.util.ModTags.TIERS_ARMOR_NETHERITE_DIAMOND)
                .addOptional(new ResourceLocation("elementus", "diarkrite_diamond_helmet")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_chestplate")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_leggings")).addOptional(new ResourceLocation("elementus", "diarkrite_diamond_boots"))
                .addOptional(new ResourceLocation("elementus", "anthektite_diamond_helmet")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_chestplate")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_leggings")).addOptional(new ResourceLocation("elementus", "anthektite_diamond_boots"));

        //Twigs
        tag(TwigsTags.TABLES_ITEM).addOptional(new ResourceLocation("elementus", "movcadia_table"));


        //Alex's Caves
        tag(Etags.Item.AC_FERROMAGNETIC)
                .addTags(Etags.Item.INGOTS_STEEL, Etags.Item.INGOTS_DIARKRITE, Etags.Item.INGOTS_ANTHEKTITE,
                        Etags.Item.NUGGETS_STEEL, Etags.Item.RAW_MATERIALS_STEEL, Etags.Item.ORES_ATELIS,

                        Etags.Item.STORAGE_BLOCK_STEEL, Etags.Item.STORAGE_BLOCK_DIARKRITE, Etags.Item.STORAGE_BLOCK_ANTHEKTITE)
                .add(ModItems.STEEL_SCRAP.get())

                .add(ModItems.STEEL_SWORD.get()).add(ModItems.STEEL_SHOVEL.get()).add(ModItems.STEEL_PICKAXE.get()).add(ModItems.STEEL_AXE.get()).add(ModItems.STEEL_HOE.get())
                .add(ModItems.DIARKRITE_SWORD.get()).add(ModItems.DIARKRITE_SHOVEL.get()).add(ModItems.DIARKRITE_PICKAXE.get()).add(ModItems.DIARKRITE_AXE.get()).add(ModItems.DIARKRITE_HOE.get())
                .add(ModItems.ANTHEKTITE_SWORD.get()).add(ModItems.ANTHEKTITE_SHOVEL.get()).add(ModItems.ANTHEKTITE_PICKAXE.get()).add(ModItems.ANTHEKTITE_AXE.get()).add(ModItems.ANTHEKTITE_HOE.get())

                .add(ModItems.STEEL_HELMET.get()).add(ModItems.STEEL_CHESTPLATE.get()).add(ModItems.STEEL_LEGGINGS.get()).add(ModItems.STEEL_BOOTS.get())
                .add(ModItems.DIARKRITE_HELMET.get()).add(ModItems.DIARKRITE_CHESTPLATE.get()).add(ModItems.DIARKRITE_LEGGINGS.get()).add(ModItems.DIARKRITE_BOOTS.get())
                .add(ModItems.ANTHEKTITE_HELMET.get()).add(ModItems.ANTHEKTITE_CHESTPLATE.get()).add(ModItems.ANTHEKTITE_LEGGINGS.get()).add(ModItems.ANTHEKTITE_BOOTS.get())

                .add(ModItems.STEEL_SHIELD.get()).add(ModItems.DIARKRITE_SHIELD.get()).add(ModItems.ANTHEKTITE_SHIELD.get())
                .addOptional(new ResourceLocation("dixtas_armory", "steel_dagger"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_shortsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_stiletto"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_rapier"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_katana"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_katana_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_greatsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_longsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_twinblade"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_zweihander"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_battle_axe"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_battle_axe_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_glaive"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_spear"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_spear_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_halberd"))
                .addOptional(new ResourceLocation("dixtas_armory", "steel_pike"))

                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_dagger"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_shortsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_stiletto"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_rapier"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_katana"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_katana_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_greatsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_longsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_twinblade"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_zweihander"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_battle_axe"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_battle_axe_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_glaive"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_spear"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_spear_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_halberd"))
                .addOptional(new ResourceLocation("dixtas_armory", "diarkrite_pike"))

                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_dagger"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_shortsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_stiletto"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_rapier"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_katana"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_katana_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_greatsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_longsword"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_twinblade"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_zweihander"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_battle_axe"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_battle_axe_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_glaive"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_spear"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_spear_two_handed"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_halberd"))
                .addOptional(new ResourceLocation("dixtas_armory", "anthektite_pike"))

                .addOptional(new ResourceLocation("elementus", "steel_knife"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_knife"))
                .addOptional(new ResourceLocation("elementus", "anthektite_knife"))

                .addOptional(new ResourceLocation("elementus", "steel_spell_book"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_spell_book"))
                .addOptional(new ResourceLocation("elementus", "anthektite_spell_book"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_helmet"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_chestplate"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_leggings"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_mage_boots"))

                .addOptional(new ResourceLocation("elementus", "anthektite_mage_helmet"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_chestplate"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_leggings"))
                .addOptional(new ResourceLocation("elementus", "anthektite_mage_boots"))

                .addOptional(new ResourceLocation("elementus", "steel_machete"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_machete"))
                .addOptional(new ResourceLocation("elementus", "anthektite_machete"))

                .addOptional(new ResourceLocation("elementus", "steel_paxel"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_paxel"))
                .addOptional(new ResourceLocation("elementus", "anthektite_paxel"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_paxel_upgrade_kit"))
                .addOptional(new ResourceLocation("elementus", "anthektite_paxel_upgrade_kiy"))

                .addOptional(new ResourceLocation("elementus", "steel_chakram"))
                .addOptional(new ResourceLocation("elementus", "steel_claymore"))
                .addOptional(new ResourceLocation("elementus", "steel_cutlass"))
                .addOptional(new ResourceLocation("elementus", "steel_glaive"))
                .addOptional(new ResourceLocation("elementus", "steel_greataxe"))
                .addOptional(new ResourceLocation("elementus", "steel_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "steel_halberd"))
                .addOptional(new ResourceLocation("elementus", "steel_katana"))
                .addOptional(new ResourceLocation("elementus", "steel_longsword"))
                .addOptional(new ResourceLocation("elementus", "steel_rapier"))
                .addOptional(new ResourceLocation("elementus", "steel_sai"))
                .addOptional(new ResourceLocation("elementus", "steel_scythe"))
                .addOptional(new ResourceLocation("elementus", "steel_spear"))
                .addOptional(new ResourceLocation("elementus", "steel_twinblade"))
                .addOptional(new ResourceLocation("elementus", "steel_warglaive"))

                .addOptional(new ResourceLocation("elementus", "diarkrite_chakram"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_claymore"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_glaive"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_halberd"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_katana"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_longsword"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_rapier"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_sai"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_scythe"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_spear"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_warglaive"))

                .addOptional(new ResourceLocation("elementus", "anthektite_chakram"))
                .addOptional(new ResourceLocation("elementus", "anthektite_claymore"))
                .addOptional(new ResourceLocation("elementus", "anthektite_cutlass"))
                .addOptional(new ResourceLocation("elementus", "anthektite_glaive"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greataxe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_greatehammer"))
                .addOptional(new ResourceLocation("elementus", "anthektite_halberd"))
                .addOptional(new ResourceLocation("elementus", "anthektite_katana"))
                .addOptional(new ResourceLocation("elementus", "anthektite_longsword"))
                .addOptional(new ResourceLocation("elementus", "anthektite_rapier"))
                .addOptional(new ResourceLocation("elementus", "anthektite_sai"))
                .addOptional(new ResourceLocation("elementus", "anthektite_scythe"))
                .addOptional(new ResourceLocation("elementus", "anthektite_spear"))
                .addOptional(new ResourceLocation("elementus", "anthektite_twinblade"))
                .addOptional(new ResourceLocation("elementus", "anthektite_warglaive"))

                .addOptional(new ResourceLocation("elementus", "steel_gloves"))
                .addOptional(new ResourceLocation("elementus", "diarkrite_gloves"))
                .addOptional(new ResourceLocation("elementus", "anthektite_gloves"));
    }
}
