package net.nokunami.elementus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.item.ItemsRegistry;
import net.nokunami.elementus.loot.AddItemModifier;

public class ProviderGlobalLootModifiers extends GlobalLootModifierProvider {
    public ProviderGlobalLootModifiers(PackOutput output) {
        super(output, Elementus.MODID);
    }

    @Override
    protected void start() {
        add("anthektite_hoe_in_ancient_city", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(2).build()}, ItemsRegistry.ANTHEKTITE_HOE.get()));

        add("anthektite_leggings_in_ancient_city", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(2).build()}, ItemsRegistry.ANTHEKTITE_LEGGINGS.get()));

        add("diarkrite_upgrade_in_ancient_city", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()}, ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("anthektite_upgrade_in_ruined_portal", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));


        add("anthektite_upgrade_in_shipwreck_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("anthektite_upgrade_in_simple_dungeon", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("anthektite_upgrade_in_woodland_mansion", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("diarkrite_upgrade_in_bastion_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get()));

        add("diarkrite_upgrade_in_end_city_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(1).build()},
                ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE.get()));
    }
}

