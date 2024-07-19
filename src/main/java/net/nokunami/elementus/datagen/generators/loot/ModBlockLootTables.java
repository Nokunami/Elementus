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

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.STEEL_BLOCK.get());
        this.dropSelf(ModBlocks.DIARKRITE_BLOCK.get());
        this.dropSelf(ModBlocks.ANTHEKTITE_BLOCK.get());
        this.dropSelf(ModBlocks.REMNANT.get());

        this.dropSelf(ModBlocks.STEEL_BARS.get());

        this.dropSelf(ModBlocks.MOVCADIA_ROOTS.get());
        this.dropSelf(ModBlocks.MOVCADIA_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MOVCADIA_LOG.get());
        this.dropSelf(ModBlocks.MOVCADIA_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MOVCADIA_WOOD.get());

        this.add(ModBlocks.MOVCADIA_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.MOVCADIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.MOVCADIA_PLANKS.get());
        this.dropSelf(ModBlocks.MOVCADIA_STAIRS.get());
        this.dropSelf(ModBlocks.MOVCADIA_SLAB.get());

        this.dropSelf(ModBlocks.MOVCADIA_FENCE.get());
        this.dropSelf(ModBlocks.MOVCADIA_FENCE_GATE.get());

        this.add(ModBlocks.MOVCADIA_DOOR.get(), createDoorTable(ModBlocks.MOVCADIA_DOOR.get()));
        this.dropSelf(ModBlocks.MOVCADIA_TRAPDOOR.get());

        this.dropSelf(ModBlocks.MOVCADIA_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.MOVCADIA_BUTTON.get());

        this.add(ModBlocks.MOVCADIA_SIGN.get(), block ->
                createSingleItemTable(ModItems.MOVCADIA_SIGN.get()));
        this.add(ModBlocks.MOVCADIA_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.MOVCADIA_SIGN.get()));
        this.add(ModBlocks.MOVCADIA_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.MOVCADIA_HANGING_SIGN.get()));
        this.add(ModBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.MOVCADIA_HANGING_SIGN.get()));
        this.add(ModBlocks.STURDY_MOVCADIA_SIGN.get(), block ->
                createSingleItemTable(ModItems.STURDY_MOVCADIA_SIGN.get()));
        this.add(ModBlocks.STURDY_MOVCADIA_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.STURDY_MOVCADIA_SIGN.get()));

        this.dropSelf(ModBlocks.MOVCADIA_CHEST.get());

        this.dropSelf(ModBlocks.MOVCADIA_SAPLING.get());
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
