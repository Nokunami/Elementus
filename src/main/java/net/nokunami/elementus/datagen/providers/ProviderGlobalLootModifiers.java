package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.registry.ModItems;
import net.nokunami.elementus.datagen.loot.AddItemModifier;

public class ProviderGlobalLootModifiers extends GlobalLootModifierProvider {
    public ProviderGlobalLootModifiers(PackOutput output) {
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

        add("ancient_city_addition_atelis_template", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("ancient_city_addition_atelis_scrap", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()}, ModItems.ATELIS_SCRAP.get()));


//        add("anthektite_upgrade_in_shipwreck_treasure", new AddItemModifier(new LootItemCondition[]{
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
//                LootItemRandomChanceCondition.randomChance(1).build()},
//                ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));

//        add("anthektite_upgrade_in_simple_dungeon", new AddItemModifier(new LootItemCondition[]{
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
//                LootItemRandomChanceCondition.randomChance(1).build()},
//                ModItems.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("woodland_mansion_addition_atelis_upgrade", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("bastion_treasure_addition_atelis_upgrade", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        add("end_city_treasure_addition_atelis_upgrade", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        // Pillager Outpost
        add("pillager_outpost_addition_steel_ingot", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_INGOT.get()));
        add("pillager_outpost_addition_steel_scrap", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_SCRAP.get()));

        // Underwater Ruins
        add("underwater_ruin_big_addition_steel_nugget", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                ModItems.STEEL_NUGGET.get()));
        add("underwater_ruin_small_addition_steel_nugget", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                ModItems.STEEL_NUGGET.get()));

        // Shipwreck Treasure
        add("shipwreck_treasure_addition_steel_ingot", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(45).build()},
                ModItems.STEEL_INGOT.get()));
        add("shipwreck_treasure_addition_steel_nugget", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(15).build()},
                ModItems.STEEL_NUGGET.get()));

        // Buried Treasure
        add("buried_treasure_addition_steel_ingot", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                ModItems.STEEL_INGOT.get()));
        add("buried_treasure_addition_steel_sword", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_SWORD.get()));

        // Village Armorer
        add("village_armorer_addition_steel_ingot", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_armorer_addition_steel_boots", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_BOOTS.get()));
        add("village_armorer_addition_steel_chestplate", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.25F).build()},
                ModItems.STEEL_CHESTPLATE.get()));

        // Village Weaponsmith
        add("village_weaponsmith_addition_steel_ingot", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_sword", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_pickaxe", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                ModItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_helmet", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_HELMET.get()));
        add("village_weaponsmith_addition_steel_chestplate", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_CHESTPLATE.get()));
        add("village_weaponsmith_addition_steel_leggings", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_LEGGINGS.get()));
        add("village_weaponsmith_addition_steel_boots", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                ModItems.STEEL_BOOTS.get()));
    }
}

