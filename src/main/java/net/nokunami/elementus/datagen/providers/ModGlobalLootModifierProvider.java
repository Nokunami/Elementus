package net.nokunami.elementus.datagen.providers;

import com.autovw.advancednetherite.common.loot.CropDropsLootModifier;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.compat.advancednetherite.ANModItems;
import net.nokunami.elementus.datagen.loot.MobDropsLootModifier;
import net.nokunami.elementus.datagen.loot.OreDropsLootModifier;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.datagen.loot.ModLootModifier;

import java.util.List;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    private final List<Item> HOE_ITEMS = List.of(
            ANModItems.DIARKRITE_IRON_HOE.get(),
            ANModItems.DIARKRITE_GOLD_HOE.get(),
            ANModItems.DIARKRITE_EMERALD_HOE.get(),
            ANModItems.DIARKRITE_DIAMOND_HOE.get(),
            ANModItems.ANTHEKTITE_IRON_HOE.get(),
            ANModItems.ANTHEKTITE_GOLD_HOE.get(),
            ANModItems.ANTHEKTITE_EMERALD_HOE.get(),
            ANModItems.ANTHEKTITE_DIAMOND_HOE.get()
            );

    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, Elementus.MODID);
    }

    @Override
    protected void start() {
//        add("ancient_city_addition_steel_hoe", new AddItemModifier(new LootItemCondition[]{
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
//                LootItemRandomChanceCondition.randomChance(3).build()}, ModItems.STEEL_HOE.get()));

//        add("ancient_city_addition_steel_leggings", new AddItemModifier(new LootItemCondition[]{
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
//                LootItemRandomChanceCondition.randomChance(3).build()}, ModItems.STEEL_LEGGINGS.get()));

//        add("ancient_city_addition_anthektite_template", new AddItemModifier(new LootItemCondition[]{
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
//                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("ancient_city_addition_atelis_template", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("ancient_city_addition_atelis_scrap", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.ATELIS_SCRAP.get()));


        add("woodland_mansion_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("bastion_treasure_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("end_city_treasure_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        // Pillager Outpost
        add("pillager_outpost_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_INGOT.get()));
        add("pillager_outpost_addition_steel_scrap", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_SCRAP.get()));

        // Underwater Ruins
        add("underwater_ruin_big_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                ModItems.STEEL_NUGGET.get()));
        add("underwater_ruin_small_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                ModItems.STEEL_NUGGET.get()));

        // Shipwreck Treasure
        add("shipwreck_treasure_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(45).build()},
                ModItems.STEEL_INGOT.get()));
        add("shipwreck_treasure_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(15).build()},
                ModItems.STEEL_NUGGET.get()));

        // Buried Treasure
        add("buried_treasure_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                ModItems.STEEL_INGOT.get()));
        add("buried_treasure_addition_steel_sword", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_SWORD.get()));

        // Village Armorer
        add("village_armorer_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_armorer_addition_steel_boots", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_BOOTS.get()));
        add("village_armorer_addition_steel_chestplate", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.25F).build()},
                ModItems.STEEL_CHESTPLATE.get()));

        // Village Weaponsmith
        add("village_weaponsmith_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_sword", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_pickaxe", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_helmet", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_HELMET.get()));
        add("village_weaponsmith_addition_steel_chestplate", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_CHESTPLATE.get()));
        add("village_weaponsmith_addition_steel_leggings", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_LEGGINGS.get()));
        add("village_weaponsmith_addition_steel_boots", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_BOOTS.get()));

        if (ModList.get().isLoaded("advancednetherite")) {
        // AdvancedNetherite
        // ores
            this.add("ore_drops_loot_modifier", new OreDropsLootModifier(new LootItemCondition[] {}));

            // entities
            add("mob_drops_loot_modifier_e", new MobDropsLootModifier(new LootItemCondition[] {
                    LootItemKilledByPlayerCondition.killedByPlayer().build()
            }));

            // crops
            add("crop_drops_loot_modifier", new CropDropsLootModifier(new LootItemCondition[] {
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(HOE_ITEMS.toArray(Item[]::new))).build()
            }));
        }
    }
}

