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
import net.nokunami.elementus.common.registry.ModItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

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
        tag(ItemTags.SWORDS).add(ModItems.STEEL_SWORD.get(), ModItems.DIARKRITE_SWORD.get(), ModItems.ANTHEKTITE_SWORD.get(),
                        ModItems.MOVCADIA_SWORD.get(),
                        ModItems.DIARKRITE_CHARGE_BLADE.get(), ModItems.ANTHEKTITE_CHARGE_BLADE.get())
                .addOptional(modLoc("diarkrite_iron_sword"))
                .addOptional(modLoc("diarkrite_gold_sword"))
                .addOptional(modLoc("diarkrite_emerald_sword"))
                .addOptional(modLoc("diarkrite_diamond_sword"))

                .addOptional(modLoc("anthektite_iron_sword"))
                .addOptional(modLoc("anthektite_gold_sword"))
                .addOptional(modLoc("anthektite_emerald_sword"))
                .addOptional(modLoc("anthektite_diamond_sword"));

        tag(ItemTags.SHOVELS).add(ModItems.STEEL_SHOVEL.get(), ModItems.DIARKRITE_SHOVEL.get(), ModItems.ANTHEKTITE_SHOVEL.get(),
                        ModItems.MOVCADIA_SHOVEL.get())
                .addOptional(modLoc("diarkrite_iron_shovel"))
                .addOptional(modLoc("diarkrite_gold_shovel"))
                .addOptional(modLoc("diarkrite_emerald_shovel"))
                .addOptional(modLoc("diarkrite_diamond_shovel"))

                .addOptional(modLoc("anthektite_iron_shovel"))
                .addOptional(modLoc("anthektite_gold_shovel"))
                .addOptional(modLoc("anthektite_emerald_shovel"))
                .addOptional(modLoc("anthektite_diamond_shovel"));

        tag(ItemTags.PICKAXES).add(ModItems.STEEL_PICKAXE.get(), ModItems.DIARKRITE_PICKAXE.get(), ModItems.ANTHEKTITE_PICKAXE.get(),
                        ModItems.MOVCADIA_PICKAXE.get())
                .addOptional(modLoc("diarkrite_iron_pickaxe"))
                .addOptional(modLoc("diarkrite_gold_pickaxe"))
                .addOptional(modLoc("diarkrite_emerald_pickaxe"))
                .addOptional(modLoc("diarkrite_diamond_pickaxe"))

                .addOptional(modLoc("anthektite_iron_pickaxe"))
                .addOptional(modLoc("anthektite_gold_pickaxe"))
                .addOptional(modLoc("anthektite_emerald_pickaxe"))
                .addOptional(modLoc("anthektite_diamond_pickaxe"));

        tag(ItemTags.AXES).add(ModItems.STEEL_AXE.get(), ModItems.DIARKRITE_AXE.get(), ModItems.ANTHEKTITE_AXE.get(),
                        ModItems.MOVCADIA_AXE.get())
                .addOptional(modLoc("diarkrite_iron_axe"))
                .addOptional(modLoc("diarkrite_gold_axe"))
                .addOptional(modLoc("diarkrite_emerald_axe"))
                .addOptional(modLoc("diarkrite_diamond_axe"))

                .addOptional(modLoc("anthektite_iron_axe"))
                .addOptional(modLoc("anthektite_gold_axe"))
                .addOptional(modLoc("anthektite_emerald_axe"))
                .addOptional(modLoc("anthektite_diamond_axe"));

        tag(ItemTags.HOES).add(ModItems.STEEL_HOE.get(), ModItems.DIARKRITE_HOE.get(), ModItems.ANTHEKTITE_HOE.get(),
                        ModItems.MOVCADIA_HOE.get())
                .addOptional(modLoc("diarkrite_iron_hoe"))
                .addOptional(modLoc("diarkrite_gold_hoe"))
                .addOptional(modLoc("diarkrite_emerald_hoe"))
                .addOptional(modLoc("diarkrite_diamond_hoe"))

                .addOptional(modLoc("anthektite_iron_hoe"))
                .addOptional(modLoc("anthektite_gold_hoe"))
                .addOptional(modLoc("anthektite_emerald_hoe"))
                .addOptional(modLoc("anthektite_diamond_hoe"));

        tag(ItemTags.TRIMMABLE_ARMOR).add(ModItems.STEEL_HELMET.get(), ModItems.STEEL_CHESTPLATE.get(), ModItems.STEEL_LEGGINGS.get(), ModItems.STEEL_BOOTS.get(),
                        ModItems.ANTHEKTITE_HELMET.get(), ModItems.ANTHEKTITE_CHESTPLATE.get(), ModItems.ANTHEKTITE_LEGGINGS.get(), ModItems.ANTHEKTITE_BOOTS.get(),
                        ModItems.DIARKRITE_HELMET.get(), ModItems.DIARKRITE_CHESTPLATE.get(), ModItems.DIARKRITE_LEGGINGS.get(), ModItems.DIARKRITE_BOOTS.get())
                .addOptional(modLoc("steel_gloves"))
                .addOptional(modLoc("diarkrite_gloves"))
                .addOptional(modLoc("anthektite_gloves"))


                .addOptional(modLoc("diarkrite_iron_helmet"))
                .addOptional(modLoc("diarkrite_iron_chestplate"))
                .addOptional(modLoc("diarkrite_iron_leggings"))
                .addOptional(modLoc("diarkrite_iron_boots"))

                .addOptional(modLoc("diarkrite_gold_helmet"))
                .addOptional(modLoc("diarkrite_gold_chestplate"))
                .addOptional(modLoc("diarkrite_gold_leggings"))
                .addOptional(modLoc("diarkrite_gold_boots"))

                .addOptional(modLoc("diarkrite_emerald_helmet"))
                .addOptional(modLoc("diarkrite_emerald_chestplate"))
                .addOptional(modLoc("diarkrite_emerald_leggings"))
                .addOptional(modLoc("diarkrite_emerald_boots"))

                .addOptional(modLoc("diarkrite_diamond_helmet"))
                .addOptional(modLoc("diarkrite_diamond_chestplate"))
                .addOptional(modLoc("diarkrite_diamond_leggings"))
                .addOptional(modLoc("diarkrite_diamond_boots"))


                .addOptional(modLoc("anthektite_iron_helmet"))
                .addOptional(modLoc("anthektite_iron_chestplate"))
                .addOptional(modLoc("anthektite_iron_leggings"))
                .addOptional(modLoc("anthektite_iron_boots"))

                .addOptional(modLoc("anthektite_gold_helmet"))
                .addOptional(modLoc("anthektite_gold_chestplate"))
                .addOptional(modLoc("anthektite_gold_leggings"))
                .addOptional(modLoc("anthektite_gold_boots"))

                .addOptional(modLoc("anthektite_emerald_helmet"))
                .addOptional(modLoc("anthektite_emerald_chestplate"))
                .addOptional(modLoc("anthektite_emerald_leggings"))
                .addOptional(modLoc("anthektite_emerald_boots"))

                .addOptional(modLoc("anthektite_diamond_helmet"))
                .addOptional(modLoc("anthektite_diamond_chestplate"))
                .addOptional(modLoc("anthektite_diamond_leggings"))
                .addOptional(modLoc("anthektite_diamond_boots"));


        tag(ItemTags.TRIM_MATERIALS).add(ModItems.STEEL_INGOT.get(), ModItems.DIARKRITE_INGOT.get(), ModItems.ANTHEKTITE_INGOT.get());

        tag(ItemTags.LOGS).addTag(Etags.Items.MOVCADIA_LOGS);

        tag(ItemTags.PLANKS).add(ModItems.MOVCADIA_PLANKS.get());

        tag(ItemTags.STAIRS).add(ModItems.MOVCADIA_STAIRS.get());
        tag(ItemTags.WOODEN_STAIRS).add(ModItems.MOVCADIA_STAIRS.get());

        tag(ItemTags.SLABS).add(ModItems.MOVCADIA_SLAB.get());
        tag(ItemTags.WOODEN_SLABS).add(ModItems.MOVCADIA_SLAB.get());

        tag(ItemTags.FENCES).add(ModItems.MOVCADIA_FENCE.get());
        tag(ItemTags.WOODEN_FENCES).add(ModItems.MOVCADIA_FENCE.get());
        tag(Tags.Items.FENCES).add(ModItems.MOVCADIA_FENCE.get());

        tag(ItemTags.DOORS).add(ModItems.MOVCADIA_DOOR.get());
        tag(ItemTags.WOODEN_DOORS).add(ModItems.MOVCADIA_DOOR.get());

        tag(ItemTags.BUTTONS).add(ModItems.MOVCADIA_BUTTON.get());
        tag(ItemTags.WOODEN_BUTTONS).add(ModItems.MOVCADIA_BUTTON.get());

        tag(ItemTags.WOODEN_PRESSURE_PLATES).add(ModItems.MOVCADIA_PRESSURE_PLATE.get());

        tag(ItemTags.TRAPDOORS).add(ModItems.MOVCADIA_TRAPDOOR.get());
        tag(ItemTags.WOODEN_TRAPDOORS).add(ModItems.MOVCADIA_TRAPDOOR.get());

        tag(ItemTags.NON_FLAMMABLE_WOOD).add(ModItems.MOVCADIA_LOG.get(), ModItems.STRIPPED_MOVCADIA_LOG.get(),
                        ModItems.MOVCADIA_WOOD.get(), ModItems.STRIPPED_MOVCADIA_WOOD.get(),ModItems.MOVCADIA_PLANKS.get(),
                        ModItems.MOVCADIA_SLAB.get(), ModItems.MOVCADIA_STAIRS.get(),ModItems.MOVCADIA_FENCE.get(),
                        ModItems.MOVCADIA_FENCE_GATE.get(),ModItems.MOVCADIA_DOOR.get(), ModItems.MOVCADIA_TRAPDOOR.get(),
                        ModItems.MOVCADIA_PRESSURE_PLATE.get(), ModItems.MOVCADIA_BUTTON.get(),ModItems.MOVCADIA_SIGN.get(),
                        ModItems.STURDY_MOVCADIA_SIGN.get(),ModItems.MOVCADIA_HANGING_SIGN.get())
                .addOptional(modLoc("movcadia_cabinet"))
                .addOptional(modLoc("movcadia_table"));

        tag(ItemTags.LEAVES).add(ModItems.MOVCADIA_LEAVES.get(), ModItems.FLOWERING_MOVCADIA_LEAVES.get());

        tag(ItemTags.SIGNS).add(ModItems.MOVCADIA_SIGN.get(), ModItems.STURDY_MOVCADIA_SIGN.get());

        tag(ItemTags.HANGING_SIGNS).add(ModItems.MOVCADIA_HANGING_SIGN.get());

        tag(ItemTags.SAPLINGS).add(ModItems.MOVCADIA_SAPLING.get());

        tag(ItemTags.FOX_FOOD).add(ModItems.MOVCADIA_BERRIES.get());
    }
    private void forgeTags() {
        tag(Tags.Items.ARMORS_HELMETS).add(ModItems.STEEL_HELMET.get(),ModItems.DIARKRITE_HELMET.get(),ModItems.ANTHEKTITE_HELMET.get())
                .addOptional(modLoc("diarkrite_mage_helmet"))
                .addOptional(modLoc("anthektite_mage_helmet"))

                .addOptional(modLoc("steel_helm"))
                .addOptional(modLoc("diarkrite_helm"))
                .addOptional(modLoc("anthektite_helm"))
                .addOptional(modLoc("steel_horned_helm"))
                .addOptional(modLoc("diarkrite_horned_helm"))
                .addOptional(modLoc("anthektite_horned_helm"))
                .addOptional(modLoc("steel_kabuto"))
                .addOptional(modLoc("diarkrite_kabuto"))
                .addOptional(modLoc("anthektite_kabuto"))

                .addOptional(modLoc("diarkrite_iron_helmet"))
                .addOptional(modLoc("diarkrite_gold_helmet"))
                .addOptional(modLoc("diarkrite_emerald_helmet"))
                .addOptional(modLoc("diarkrite_diamond_helmet"))

                .addOptional(modLoc("anthektite_iron_helmet"))
                .addOptional(modLoc("anthektite_gold_helmet"))
                .addOptional(modLoc("anthektite_emerald_helmet"))
                .addOptional(modLoc("anthektite_diamond_helmet"))

                .addOptional(modLoc("steel_samurai_helmet"))
                .addOptional(modLoc("steel_samurai_helmet_light"))
                .addOptional(modLoc("steel_samurai_helmet_master"))
                .addOptional(modLoc("diarkrite_samurai_helmet"))
                .addOptional(modLoc("diarkrite_samurai_helmet_light"))
                .addOptional(modLoc("diarkrite_samurai_helmet_master"))
                .addOptional(modLoc("anthektite_samurai_helmet"))
                .addOptional(modLoc("anthektite_samurai_helmet_light"))
                .addOptional(modLoc("anthektite_samurai_helmet_master"));

        tag(Tags.Items.ARMORS_CHESTPLATES).add(ModItems.STEEL_CHESTPLATE.get(),ModItems.DIARKRITE_CHESTPLATE.get(),ModItems.ANTHEKTITE_CHESTPLATE.get())
                .addOptional(modLoc("diarkrite_mage_chestplate"))
                .addOptional(modLoc("anthektite_mage_chestplate"))

                .addOptional(modLoc("steel_surcoat"))
                .addOptional(modLoc("diarkrite_surcoat"))
                .addOptional(modLoc("anthektite_surcoat"))
                .addOptional(modLoc("plated_steel_chestplate"))
                .addOptional(modLoc("plated_diarkrite_chestplate"))
                .addOptional(modLoc("plated_anthektite_chestplate"))
                .addOptional(modLoc("steel_do"))
                .addOptional(modLoc("diarkrite_do"))
                .addOptional(modLoc("anthektite_do"))
                .addOptional(modLoc("clothed_steel_cuirass"))
                .addOptional(modLoc("clothed_diarkrite_cuirass"))
                .addOptional(modLoc("clothed_anthektite_cuirass"))

                .addOptional(modLoc("diarkrite_iron_chestplate"))
                .addOptional(modLoc("diarkrite_gold_chestplate"))
                .addOptional(modLoc("diarkrite_emerald_chestplate"))
                .addOptional(modLoc("diarkrite_diamond_chestplate"))

                .addOptional(modLoc("anthektite_iron_chestplate"))
                .addOptional(modLoc("anthektite_gold_chestplate"))
                .addOptional(modLoc("anthektite_emerald_chestplate"))
                .addOptional(modLoc("anthektite_diamond_chestplate"))

                .addOptional(modLoc("steel_samurai_chestplate"))
                .addOptional(modLoc("steel_samurai_chestplate_light"))
                .addOptional(modLoc("steel_samurai_chestplate_master"))
                .addOptional(modLoc("diarkrite_samurai_chestplate"))
                .addOptional(modLoc("diarkrite_samurai_chestplate_light"))
                .addOptional(modLoc("diarkrite_samurai_chestplate_master"))
                .addOptional(modLoc("anthektite_samurai_chestplate"))
                .addOptional(modLoc("anthektite_samurai_chestplate_light"))
                .addOptional(modLoc("anthektite_samurai_chestplate_master"));

        tag(Tags.Items.ARMORS_LEGGINGS).add(ModItems.STEEL_LEGGINGS.get(),ModItems.DIARKRITE_LEGGINGS.get(),ModItems.ANTHEKTITE_LEGGINGS.get())
                .addOptional(modLoc("diarkrite_mage_leggings"))
                .addOptional(modLoc("anthektite_mage_leggings"))

                .addOptional(modLoc("diarkrite_iron_leggings"))
                .addOptional(modLoc("diarkrite_gold_leggings"))
                .addOptional(modLoc("diarkrite_emerald_leggings"))
                .addOptional(modLoc("diarkrite_diamond_leggings"))

                .addOptional(modLoc("anthektite_iron_leggings"))
                .addOptional(modLoc("anthektite_gold_leggings"))
                .addOptional(modLoc("anthektite_emerald_leggings"))
                .addOptional(modLoc("anthektite_diamond_leggings"))

                .addOptional(modLoc("steel_samurai_leggings"))
                .addOptional(modLoc("steel_samurai_leggings_light"))
                .addOptional(modLoc("steel_samurai_leggings_master"))
                .addOptional(modLoc("diarkrite_samurai_leggings"))
                .addOptional(modLoc("diarkrite_samurai_leggings_light"))
                .addOptional(modLoc("diarkrite_samurai_leggings_master"))
                .addOptional(modLoc("anthektite_samurai_leggings"))
                .addOptional(modLoc("anthektite_samurai_leggings_light"))
                .addOptional(modLoc("anthektite_samurai_leggings_master"));

        tag(Tags.Items.ARMORS_BOOTS).add(ModItems.STEEL_BOOTS.get(),ModItems.DIARKRITE_BOOTS.get(),ModItems.ANTHEKTITE_BOOTS.get())
                .addOptional(modLoc("diarkrite_mage_boots"))
                .addOptional(modLoc("anthektite_mage_boots"))

                .addOptional(modLoc("diarkrite_iron_boots"))
                .addOptional(modLoc("diarkrite_gold_boots"))
                .addOptional(modLoc("diarkrite_emerald_boots"))
                .addOptional(modLoc("diarkrite_diamond_boots"))

                .addOptional(modLoc("anthektite_iron_boots"))
                .addOptional(modLoc("anthektite_gold_boots"))
                .addOptional(modLoc("anthektite_emerald_boots"))
                .addOptional(modLoc("anthektite_diamond_boots"))

                .addOptional(modLoc("steel_samurai_boots"))
                .addOptional(modLoc("steel_samurai_boots_light"))
                .addOptional(modLoc("steel_samurai_boots_master"))
                .addOptional(modLoc("diarkrite_samurai_boots"))
                .addOptional(modLoc("diarkrite_samurai_boots_light"))
                .addOptional(modLoc("diarkrite_samurai_boots_master"))
                .addOptional(modLoc("anthektite_samurai_boots"))
                .addOptional(modLoc("anthektite_samurai_boots_light"))
                .addOptional(modLoc("anthektite_samurai_boots_master"));

        tag(Tags.Items.TOOLS_SHIELDS).add(ModItems.STEEL_SHIELD.get(),ModItems.DIARKRITE_SHIELD.get(),ModItems.ANTHEKTITE_SHIELD.get());

        tag(Tags.Items.INGOTS).addTag(Etags.Items.INGOTS_STEEL).addTag(Etags.Items.INGOTS_DIARKRITE).addTag(Etags.Items.INGOTS_ANTHEKTITE);
        tag(Tags.Items.NUGGETS).addTag(Etags.Items.NUGGETS_STEEL);
        tag(Tags.Items.ORES).addTag(Etags.Items.ORES_ATELIS);
        tag(Tags.Items.RAW_MATERIALS).addTag(Etags.Items.RAW_MATERIALS_STEEL);
        tag(Tags.Items.STORAGE_BLOCKS).addTag(Etags.Items.STORAGE_BLOCK_STEEL).addTag(Etags.Items.STORAGE_BLOCK_DIARKRITE).addTag(Etags.Items.STORAGE_BLOCK_ANTHEKTITE);
        tag(Etags.Items.BERRIES).add(ModItems.MOVCADIA_BERRIES.get());

        tag(Tags.Items.CHESTS_WOODEN).add(ModItems.MOVCADIA_CHEST.get());
    }
    private void elementusTags() {
        tag(Etags.Items.STEEL_RECYCLABLE)
                .add(ModItems.STEEL_SWORD.get(), ModItems.STEEL_SHOVEL.get(), ModItems.STEEL_PICKAXE.get(), ModItems.STEEL_AXE.get(), ModItems.STEEL_HOE.get())
                .add(ModItems.STEEL_HELMET.get(), ModItems.STEEL_CHESTPLATE.get(), ModItems.STEEL_LEGGINGS.get(), ModItems.STEEL_BOOTS.get())
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

                .addOptional(modLoc("steel_knife"))

                .addOptional(modLoc("steel_machete"))

                .addOptional(modLoc("steel_paxel"))

                .addOptional(modLoc("steel_chakram"))
                .addOptional(modLoc("steel_claymore"))
                .addOptional(modLoc("steel_cutlass"))
                .addOptional(modLoc("steel_glaive"))
                .addOptional(modLoc("steel_greataxe"))
                .addOptional(modLoc("steel_greatehammer"))
                .addOptional(modLoc("steel_halberd"))
                .addOptional(modLoc("steel_katana"))
                .addOptional(modLoc("steel_longsword"))
                .addOptional(modLoc("steel_rapier"))
                .addOptional(modLoc("steel_sai"))
                .addOptional(modLoc("steel_scythe"))
                .addOptional(modLoc("steel_spear"))
                .addOptional(modLoc("steel_twinblade"))
                .addOptional(modLoc("steel_warglaive"))

                .addOptional(modLoc("steel_gloves"));


        tag(Etags.Items.MOVCADIA_LOGS)
                .add(ModItems.MOVCADIA_LOG.get(), ModItems.STRIPPED_MOVCADIA_LOG.get())
                .add(ModItems.MOVCADIA_WOOD.get(), ModItems.STRIPPED_MOVCADIA_WOOD.get());

        tag(Etags.Items.INGOTS_STEEL).add(ModItems.STEEL_INGOT.get());
        tag(Etags.Items.INGOTS_DIARKRITE).add(ModItems.DIARKRITE_INGOT.get());
        tag(Etags.Items.INGOTS_ANTHEKTITE).add(ModItems.ANTHEKTITE_INGOT.get());
        tag(Etags.Items.NUGGETS_STEEL).add(ModItems.STEEL_NUGGET.get());
        tag(Etags.Items.RAW_MATERIALS_STEEL).add(ModItems.CRUDE_STEEL.get());
        tag(Etags.Items.ORES_ATELIS).add(ModItems.ATELIS_SCRAP.get());

        tag(Etags.Items.STORAGE_BLOCK_STEEL).add(ModItems.STEEL_BLOCK.get());
        tag(Etags.Items.STORAGE_BLOCK_DIARKRITE).add(ModItems.DIARKRITE_BLOCK.get());
        tag(Etags.Items.STORAGE_BLOCK_ANTHEKTITE).add(ModItems.ANTHEKTITE_BLOCK.get());

        tag(Etags.Items.REPAIRS_STEEL_EQUIPMENT).add(ModItems.STEEL_INGOT.get());
        tag(Etags.Items.REPAIRS_DIARKRITE_EQUIPMENT).add(ModItems.DIARKRITE_INGOT.get());
        tag(Etags.Items.REPAIRS_ANTHEKTITE_EQUIPMENT).add(ModItems.ANTHEKTITE_INGOT.get());

        tag(Etags.Items.REPAIRS_DIARKRITE_IRON_ARMOR).addOptional(modLoc("diarkrite_iron_ingot"));
        tag(Etags.Items.REPAIRS_DIARKRITE_GOLD_ARMOR).addOptional(modLoc("diarkrite_gold_ingot"));
        tag(Etags.Items.REPAIRS_DIARKRITE_EMERALD_ARMOR).addOptional(modLoc("diarkrite_emerald_ingot"));
        tag(Etags.Items.REPAIRS_DIARKRITE_DIAMOND_ARMOR).addOptional(modLoc("diarkrite_diamond_ingot"));
        tag(Etags.Items.REPAIRS_ANTHEKTITE_IRON_ARMOR).addOptional(modLoc("anthektite_iron_ingot"));
        tag(Etags.Items.REPAIRS_ANTHEKTITE_GOLD_ARMOR).addOptional(modLoc("anthektite_gold_ingot"));
        tag(Etags.Items.REPAIRS_ANTHEKTITE_EMERALD_ARMOR).addOptional(modLoc("anthektite_emerald_ingot"));
        tag(Etags.Items.REPAIRS_ANTHEKTITE_DIAMOND_ARMOR).addOptional(modLoc("anthektite_diamond_ingot"));

        tag(Etags.Items.REPAIRS_CATALYST_ARMOR).addTag(Etags.Items.ORES_ATELIS);
        tag(Etags.Items.CATALYST_ITEMS)
                .add(net.minecraft.world.item.Items.NETHER_STAR, net.minecraft.world.item.Items.HEART_OF_THE_SEA, net.minecraft.world.item.Items.TOTEM_OF_UNDYING)
                .addOptional(new ResourceLocation("cataclysm", "ignitium_ingot"))
                .addOptional(new ResourceLocation("irons_spellbooks", "arcane_ingot"))
                .addOptional(new ResourceLocation("cataclysm", "cursium_ingot"))
                .addOptional(new ResourceLocation("witherstormmod", "withered_nether_star"));
        tag(Etags.Items.CATALYST_ELYTRA)
                .add(net.minecraft.world.item.Items.ELYTRA);
        tag(Etags.Items.STEEL_GOLEM_HEAL).add(ModItems.STEEL_INGOT.get());
        tag(Etags.Items.STEEL_GOLEM_REPAIR_HALF).add(ModItems.STEEL_SCRAP.get());
        tag(Etags.Items.STEEL_GOLEM_REPAIR_FULL).add(ModItems.STEEL_BLOCK.get());
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
                .add(ModItems.STEEL_SCRAP.get())

                .add(ModItems.STEEL_SWORD.get(), ModItems.STEEL_SHOVEL.get(), ModItems.STEEL_PICKAXE.get(), ModItems.STEEL_AXE.get(), ModItems.STEEL_HOE.get())
                .add(ModItems.DIARKRITE_SWORD.get(), ModItems.DIARKRITE_SHOVEL.get(), ModItems.DIARKRITE_PICKAXE.get(), ModItems.DIARKRITE_AXE.get(), ModItems.DIARKRITE_HOE.get())
                .add(ModItems.ANTHEKTITE_SWORD.get(), ModItems.ANTHEKTITE_SHOVEL.get(), ModItems.ANTHEKTITE_PICKAXE.get(), ModItems.ANTHEKTITE_AXE.get(), ModItems.ANTHEKTITE_HOE.get())

                .add(ModItems.STEEL_HELMET.get(), ModItems.STEEL_CHESTPLATE.get(), ModItems.STEEL_LEGGINGS.get(), ModItems.STEEL_BOOTS.get())
                .add(ModItems.DIARKRITE_HELMET.get(), ModItems.DIARKRITE_CHESTPLATE.get(), ModItems.DIARKRITE_LEGGINGS.get(), ModItems.DIARKRITE_BOOTS.get())
                .add(ModItems.ANTHEKTITE_HELMET.get(), ModItems.ANTHEKTITE_CHESTPLATE.get(), ModItems.ANTHEKTITE_LEGGINGS.get(), ModItems.ANTHEKTITE_BOOTS.get())

                .add(ModItems.STEEL_SHIELD.get(), ModItems.DIARKRITE_SHIELD.get(), ModItems.ANTHEKTITE_SHIELD.get())
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

                .addOptional(modLoc("steel_knife"))
                .addOptional(modLoc("diarkrite_knife"))
                .addOptional(modLoc("anthektite_knife"))

                .addOptional(modLoc("steel_spell_book"))
                .addOptional(modLoc("diarkrite_spell_book"))
                .addOptional(modLoc("anthektite_spell_book"))

                .addOptional(modLoc("diarkrite_mage_helmet"))
                .addOptional(modLoc("diarkrite_mage_chestplate"))
                .addOptional(modLoc("diarkrite_mage_leggings"))
                .addOptional(modLoc("diarkrite_mage_boots"))

                .addOptional(modLoc("anthektite_mage_helmet"))
                .addOptional(modLoc("anthektite_mage_chestplate"))
                .addOptional(modLoc("anthektite_mage_leggings"))
                .addOptional(modLoc("anthektite_mage_boots"))

                .addOptional(modLoc("steel_machete"))
                .addOptional(modLoc("diarkrite_machete"))
                .addOptional(modLoc("anthektite_machete"))

                .addOptional(modLoc("steel_paxel"))
                .addOptional(modLoc("diarkrite_paxel"))
                .addOptional(modLoc("anthektite_paxel"))

                .addOptional(modLoc("diarkrite_paxel_upgrade_kit"))
                .addOptional(modLoc("anthektite_paxel_upgrade_kiy"))

                .addOptional(modLoc("steel_chakram"))
                .addOptional(modLoc("steel_claymore"))
                .addOptional(modLoc("steel_cutlass"))
                .addOptional(modLoc("steel_glaive"))
                .addOptional(modLoc("steel_greataxe"))
                .addOptional(modLoc("steel_greatehammer"))
                .addOptional(modLoc("steel_halberd"))
                .addOptional(modLoc("steel_katana"))
                .addOptional(modLoc("steel_longsword"))
                .addOptional(modLoc("steel_rapier"))
                .addOptional(modLoc("steel_sai"))
                .addOptional(modLoc("steel_scythe"))
                .addOptional(modLoc("steel_spear"))
                .addOptional(modLoc("steel_twinblade"))
                .addOptional(modLoc("steel_warglaive"))

                .addOptional(modLoc("diarkrite_chakram"))
                .addOptional(modLoc("diarkrite_claymore"))
                .addOptional(modLoc("diarkrite_cutlass"))
                .addOptional(modLoc("diarkrite_glaive"))
                .addOptional(modLoc("diarkrite_greataxe"))
                .addOptional(modLoc("diarkrite_greatehammer"))
                .addOptional(modLoc("diarkrite_halberd"))
                .addOptional(modLoc("diarkrite_katana"))
                .addOptional(modLoc("diarkrite_longsword"))
                .addOptional(modLoc("diarkrite_rapier"))
                .addOptional(modLoc("diarkrite_sai"))
                .addOptional(modLoc("diarkrite_scythe"))
                .addOptional(modLoc("diarkrite_spear"))
                .addOptional(modLoc("diarkrite_twinblade"))
                .addOptional(modLoc("diarkrite_warglaive"))

                .addOptional(modLoc("anthektite_chakram"))
                .addOptional(modLoc("anthektite_claymore"))
                .addOptional(modLoc("anthektite_cutlass"))
                .addOptional(modLoc("anthektite_glaive"))
                .addOptional(modLoc("anthektite_greataxe"))
                .addOptional(modLoc("anthektite_greatehammer"))
                .addOptional(modLoc("anthektite_halberd"))
                .addOptional(modLoc("anthektite_katana"))
                .addOptional(modLoc("anthektite_longsword"))
                .addOptional(modLoc("anthektite_rapier"))
                .addOptional(modLoc("anthektite_sai"))
                .addOptional(modLoc("anthektite_scythe"))
                .addOptional(modLoc("anthektite_spear"))
                .addOptional(modLoc("anthektite_twinblade"))
                .addOptional(modLoc("anthektite_warglaive"))

                .addOptional(modLoc("steel_gloves"))
                .addOptional(modLoc("diarkrite_gloves"))
                .addOptional(modLoc("anthektite_gloves"))

                .addOptional(modLoc("steel_great_axe"))
                .addOptional(modLoc("diarkrite_great_axe"))
                .addOptional(modLoc("anthektite_great_axe"))
                .addOptional(modLoc("steel_great_pickaxe"))
                .addOptional(modLoc("diarkrite_great_pickaxe"))
                .addOptional(modLoc("anthektite_great_pickaxe"))
                .addOptional(modLoc("steel_great_sword"))
                .addOptional(modLoc("diarkrite_great_sword"))
                .addOptional(modLoc("anthektite_great_sword"))
                .addOptional(modLoc("steel_naginata"))
                .addOptional(modLoc("diarkrite_naginata"))
                .addOptional(modLoc("anthektite_naginata"))

                .addOptional(modLoc("steel_helm"))
                .addOptional(modLoc("diarkrite_helm"))
                .addOptional(modLoc("anthektite_helm"))
                .addOptional(modLoc("steel_horned_helm"))
                .addOptional(modLoc("diarkrite_horned_helm"))
                .addOptional(modLoc("anthektite_horned_helm"))
                .addOptional(modLoc("steel_kabuto"))
                .addOptional(modLoc("diarkrite_kabuto"))
                .addOptional(modLoc("anthektite_kabuto"))

                .addOptional(modLoc("steel_surcoat"))
                .addOptional(modLoc("diarkrite_surcoat"))
                .addOptional(modLoc("anthektite_surcoat"))
                .addOptional(modLoc("plated_steel_chestplate"))
                .addOptional(modLoc("plated_diarkrite_chestplate"))
                .addOptional(modLoc("plated_anthektite_chestplate"))
                .addOptional(modLoc("steel_do"))
                .addOptional(modLoc("diarkrite_do"))
                .addOptional(modLoc("anthektite_do"))
                .addOptional(modLoc("clothed_steel_cuirass"))
                .addOptional(modLoc("clothed_diarkrite_cuirass"))
                .addOptional(modLoc("clothed_anthektite_cuirass"))

                //Advanced Netherite
                .addOptional(modLoc("diarkrite_iron_ingot"))
                .addOptional(modLoc("diarkrite_gold_ingot"))
                .addOptional(modLoc("diarkrite_emerald_ingot"))
                .addOptional(modLoc("diarkrite_diamond_ingot"))
                .addOptional(modLoc("anthektite_iron_ingot"))
                .addOptional(modLoc("anthektite_gold_ingot"))
                .addOptional(modLoc("anthektite_emerald_ingot"))
                .addOptional(modLoc("anthektite_diamond_ingot"))

                .addOptional(modLoc("diarkrite_iron_block"))
                .addOptional(modLoc("diarkrite_gold_block"))
                .addOptional(modLoc("diarkrite_emerald_block"))
                .addOptional(modLoc("diarkrite_diamond_block"))
                .addOptional(modLoc("anthektite_iron_block"))
                .addOptional(modLoc("anthektite_gold_block"))
                .addOptional(modLoc("anthektite_emerald_block"))
                .addOptional(modLoc("anthektite_diamond_block"))

                .addOptional(modLoc("diarkrite_iron_sword"))
                .addOptional(modLoc("diarkrite_iron_shovel"))
                .addOptional(modLoc("diarkrite_iron_pickaxe"))
                .addOptional(modLoc("diarkrite_iron_axe"))
                .addOptional(modLoc("diarkrite_iron_hoe"))
                .addOptional(modLoc("diarkrite_gold_sword"))
                .addOptional(modLoc("diarkrite_gold_shovel"))
                .addOptional(modLoc("diarkrite_gold_pickaxe"))
                .addOptional(modLoc("diarkrite_gold_axe"))
                .addOptional(modLoc("diarkrite_gold_hoe"))
                .addOptional(modLoc("diarkrite_emerald_sword"))
                .addOptional(modLoc("diarkrite_emerald_shovel"))
                .addOptional(modLoc("diarkrite_emerald_pickaxe"))
                .addOptional(modLoc("diarkrite_emerald_axe"))
                .addOptional(modLoc("diarkrite_emerald_hoe"))
                .addOptional(modLoc("diarkrite_diamond_sword"))
                .addOptional(modLoc("diarkrite_diamond_shovel"))
                .addOptional(modLoc("diarkrite_diamond_pickaxe"))
                .addOptional(modLoc("diarkrite_diamond_axe"))
                .addOptional(modLoc("diarkrite_diamond_hoe"))

                .addOptional(modLoc("anthektite_iron_sword"))
                .addOptional(modLoc("anthektite_iron_shovel"))
                .addOptional(modLoc("anthektite_iron_pickaxe"))
                .addOptional(modLoc("anthektite_iron_axe"))
                .addOptional(modLoc("anthektite_iron_hoe"))
                .addOptional(modLoc("anthektite_gold_sword"))
                .addOptional(modLoc("anthektite_gold_shovel"))
                .addOptional(modLoc("anthektite_gold_pickaxe"))
                .addOptional(modLoc("anthektite_gold_axe"))
                .addOptional(modLoc("anthektite_gold_hoe"))
                .addOptional(modLoc("anthektite_emerald_sword"))
                .addOptional(modLoc("anthektite_emerald_shovel"))
                .addOptional(modLoc("anthektite_emerald_pickaxe"))
                .addOptional(modLoc("anthektite_emerald_axe"))
                .addOptional(modLoc("anthektite_emerald_hoe"))
                .addOptional(modLoc("anthektite_diamond_sword"))
                .addOptional(modLoc("anthektite_diamond_shovel"))
                .addOptional(modLoc("anthektite_diamond_pickaxe"))
                .addOptional(modLoc("anthektite_diamond_axe"))
                .addOptional(modLoc("anthektite_diamond_hoe"))

                .addOptional(modLoc("diarkrite_iron_helmet"))
                .addOptional(modLoc("diarkrite_iron_chestplate"))
                .addOptional(modLoc("diarkrite_iron_leggings"))
                .addOptional(modLoc("diarkrite_iron_boots"))
                .addOptional(modLoc("diarkrite_gold_helmet"))
                .addOptional(modLoc("diarkrite_gold_chestplate"))
                .addOptional(modLoc("diarkrite_gold_leggings"))
                .addOptional(modLoc("diarkrite_gold_boots"))
                .addOptional(modLoc("diarkrite_emerald_helmet"))
                .addOptional(modLoc("diarkrite_emerald_chestplate"))
                .addOptional(modLoc("diarkrite_emerald_leggings"))
                .addOptional(modLoc("diarkrite_emerald_boots"))
                .addOptional(modLoc("diarkrite_diamond_helmet"))
                .addOptional(modLoc("diarkrite_diamond_chestplate"))
                .addOptional(modLoc("diarkrite_diamond_leggings"))
                .addOptional(modLoc("diarkrite_diamond_boots"))

                .addOptional(modLoc("anthektite_iron_helmet"))
                .addOptional(modLoc("anthektite_iron_chestplate"))
                .addOptional(modLoc("anthektite_iron_leggings"))
                .addOptional(modLoc("anthektite_iron_boots"))
                .addOptional(modLoc("anthektite_gold_helmet"))
                .addOptional(modLoc("anthektite_gold_chestplate"))
                .addOptional(modLoc("anthektite_gold_leggings"))
                .addOptional(modLoc("anthektite_gold_boots"))
                .addOptional(modLoc("anthektite_emerald_helmet"))
                .addOptional(modLoc("anthektite_emerald_chestplate"))
                .addOptional(modLoc("anthektite_emerald_leggings"))
                .addOptional(modLoc("anthektite_emerald_boots"))
                .addOptional(modLoc("anthektite_diamond_helmet"))
                .addOptional(modLoc("anthektite_diamond_chestplate"))
                .addOptional(modLoc("anthektite_diamond_leggings"))
                .addOptional(modLoc("anthektite_diamond_boots"))

                //Samurai Dynasty
                .addOptional(modLoc("steel_samurai_helmet"))
                .addOptional(modLoc("steel_samurai_chestplate"))
                .addOptional(modLoc("steel_samurai_leggings"))
                .addOptional(modLoc("steel_samurai_boots"))
                .addOptional(modLoc("steel_samurai_helmet_light"))
                .addOptional(modLoc("steel_samurai_chestplate_light"))
                .addOptional(modLoc("steel_samurai_leggings_light"))
                .addOptional(modLoc("steel_samurai_boots_light"))
                .addOptional(modLoc("steel_samurai_helmet_master"))
                .addOptional(modLoc("steel_samurai_chestplate_master"))
                .addOptional(modLoc("steel_samurai_leggings_master"))
                .addOptional(modLoc("steel_samurai_boots_master"))

                .addOptional(modLoc("diarkrite_samurai_helmet"))
                .addOptional(modLoc("diarkrite_samurai_chestplate"))
                .addOptional(modLoc("diarkrite_samurai_leggings"))
                .addOptional(modLoc("diarkrite_samurai_boots"))
                .addOptional(modLoc("diarkrite_samurai_helmet_light"))
                .addOptional(modLoc("diarkrite_samurai_chestplate_light"))
                .addOptional(modLoc("diarkrite_samurai_leggings_light"))
                .addOptional(modLoc("diarkrite_samurai_boots_light"))
                .addOptional(modLoc("diarkrite_samurai_helmet_master"))
                .addOptional(modLoc("diarkrite_samurai_chestplate_master"))
                .addOptional(modLoc("diarkrite_samurai_leggings_master"))
                .addOptional(modLoc("diarkrite_samurai_boots_master"))

                .addOptional(modLoc("anthektite_samurai_helmet"))
                .addOptional(modLoc("anthektite_samurai_chestplate"))
                .addOptional(modLoc("anthektite_samurai_leggings"))
                .addOptional(modLoc("anthektite_samurai_boots"))
                .addOptional(modLoc("anthektite_samurai_helmet_light"))
                .addOptional(modLoc("anthektite_samurai_chestplate_light"))
                .addOptional(modLoc("anthektite_samurai_leggings_light"))
                .addOptional(modLoc("anthektite_samurai_boots_light"))
                .addOptional(modLoc("anthektite_samurai_helmet_master"))
                .addOptional(modLoc("anthektite_samurai_chestplate_master"))
                .addOptional(modLoc("anthektite_samurai_leggings_master"))
                .addOptional(modLoc("anthektite_samurai_boots_master"))

                //WitherStormmod
                .addOptional(modLoc("steel_command_block_sword"))
                .addOptional(modLoc("steel_command_block_shovel"))
                .addOptional(modLoc("steel_command_block_pickaxe"))
                .addOptional(modLoc("steel_command_block_axe"))
                .addOptional(modLoc("steel_command_block_hoe"))
                .addOptional(modLoc("diarkrite_command_block_sword"))
                .addOptional(modLoc("diarkrite_command_block_shovel"))
                .addOptional(modLoc("diarkrite_command_block_pickaxe"))
                .addOptional(modLoc("diarkrite_command_block_axe"))
                .addOptional(modLoc("diarkrite_command_block_hoe"))
                .addOptional(modLoc("anthektite_command_block_sword"))
                .addOptional(modLoc("anthektite_command_block_shovel"))
                .addOptional(modLoc("anthektite_command_block_pickaxe"))
                .addOptional(modLoc("anthektite_command_block_axe"))
                .addOptional(modLoc("anthektite_command_block_hoe"))

                //BanillaClaws
                .addOptional(modLoc("steel_claws"))
                .addOptional(modLoc("diarkrite_claws"))
                .addOptional(modLoc("anthektite_claws"));

        //Cracker's Witherstorm Mod
        tag(Etags.Items.COMMAND_BLOCK_TOOLS)
                .addOptional(modLoc("steel_command_block_sword"))
                .addOptional(modLoc("steel_command_block_shovel"))
                .addOptional(modLoc("steel_command_block_pickaxe"))
                .addOptional(modLoc("steel_command_block_axe"))
                .addOptional(modLoc("steel_command_block_hoe"))
                .addOptional(modLoc("diarkrite_command_block_sword"))
                .addOptional(modLoc("diarkrite_command_block_shovel"))
                .addOptional(modLoc("diarkrite_command_block_pickaxe"))
                .addOptional(modLoc("diarkrite_command_block_axe"))
                .addOptional(modLoc("diarkrite_command_block_hoe"))
                .addOptional(modLoc("anthektite_command_block_sword"))
                .addOptional(modLoc("anthektite_command_block_shovel"))
                .addOptional(modLoc("anthektite_command_block_pickaxe"))
                .addOptional(modLoc("anthektite_command_block_axe"))
                .addOptional(modLoc("anthektite_command_block_hoe"));

        //Archery Expansion
        tag(Etags.Items.ANTI_POWER_BOW)
                .addOptional(ModItems.STEEL_BOW.getId())
                .addOptional(ModItems.DIARKRITE_BOW.getId())
                .addOptional(ModItems.ANTHEKTITE_BOW.getId());

        //Create
        tag(Etags.Items.CREATE_MOODED_STRIPPED_LOGS)
                .addOptional(ModItems.STRIPPED_MOVCADIA_LOG.getId())
                .addOptional(ModItems.STRIPPED_MOVCADIA_WOOD.getId());
    }
}
