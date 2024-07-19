package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.nokunami.elementus.datagen.generators.loot.ANBlockLootTables;
import net.nokunami.elementus.datagen.generators.loot.ModBlockLootTables;

import java.util.List;
import java.util.Set;

public class ModLootTableData {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ANBlockLootTables::new, LootContextParamSets.BLOCK)
        ));
    }
}