package net.nokunami.elementus.datagen.generators.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.compat.advancednetherite.ANModBlocks;
import net.nokunami.elementus.common.registry.ModBlocks;
import net.nokunami.elementus.common.registry.ModItems;

import java.util.Set;

public class ANBlockLootTables extends BlockLootSubProvider {
    public ANBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        if (ModChecker.advancednetherite()){
            this.dropSelf(ANModBlocks.DIARKRITE_IRON_BLOCK.get());
            this.dropSelf(ANModBlocks.DIARKRITE_GOLD_BLOCK.get());
            this.dropSelf(ANModBlocks.DIARKRITE_EMERALD_BLOCK.get());
            this.dropSelf(ANModBlocks.DIARKRITE_DIAMOND_BLOCK.get());

            this.dropSelf(ANModBlocks.ANTHEKTITE_IRON_BLOCK.get());
            this.dropSelf(ANModBlocks.ANTHEKTITE_GOLD_BLOCK.get());
            this.dropSelf(ANModBlocks.ANTHEKTITE_EMERALD_BLOCK.get());
            this.dropSelf(ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK.get());
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ANModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
