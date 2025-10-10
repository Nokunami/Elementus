package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.datagen.loot.ModLootModifier;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {

    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, Elementus.MODID);
    }

    @Override
    protected void start() {
        // Ancient City
        add("ancient_city_addition_atelis_template", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.25F).build()}, EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));
        add("ancient_city_addition_atelis_scrap", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.3F).build()}, EItems.ATELIS_SCRAP.get()));
        add("ancient_city_catalyst_armor", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()}, EItems.CATALYST_CHESTPLATE.get()));
        add("ancient_city_addition_weapon_fragment", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(0.15F).build()}, EItems.WEAPON_FRAGMENT.get()));

        // Woodland Mansion
        add("woodland_mansion_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        // Bastion
        add("bastion_treasure_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.1F).build()},
                EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        // End city
        add("end_city_treasure_addition_atelis_upgrade", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE.get()));

        // Pillager Outpost
        add("pillager_outpost_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_INGOT.get()));
        add("pillager_outpost_addition_steel_scrap", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                EItems.STEEL_SCRAP.get()));

        // Underwater Ruins
        add("underwater_ruin_big_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                EItems.STEEL_NUGGET.get()));
        add("underwater_ruin_small_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                EItems.STEEL_NUGGET.get()));

        // Shipwreck Treasure
        add("shipwreck_treasure_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(45).build()},
                EItems.STEEL_INGOT.get()));
        add("shipwreck_treasure_addition_steel_nugget", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(15).build()},
                EItems.STEEL_NUGGET.get()));

        // Buried Treasure
        add("buried_treasure_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(10).build()},
                EItems.STEEL_INGOT.get()));
        add("buried_treasure_addition_steel_sword", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                EItems.STEEL_SWORD.get()));

        // Village Armorer
        add("village_armorer_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                EItems.STEEL_INGOT.get()));
        add("village_armorer_addition_steel_boots", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_BOOTS.get()));
        add("village_armorer_addition_steel_chestplate", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_armorer")).build(),
                LootItemRandomChanceCondition.randomChance(0.25F).build()},
                EItems.STEEL_CHESTPLATE.get()));

        // Village Weaponsmith
        add("village_weaponsmith_addition_steel_ingot", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(5).build()},
                EItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_sword", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                EItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_pickaxe", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(1.25F).build()},
                EItems.STEEL_INGOT.get()));
        add("village_weaponsmith_addition_steel_helmet", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_HELMET.get()));
        add("village_weaponsmith_addition_steel_chestplate", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_CHESTPLATE.get()));
        add("village_weaponsmith_addition_steel_leggings", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_LEGGINGS.get()));
        add("village_weaponsmith_addition_steel_boots", new ModLootModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village/village_weaponsmith")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()},
                EItems.STEEL_BOOTS.get()));
    }
}

