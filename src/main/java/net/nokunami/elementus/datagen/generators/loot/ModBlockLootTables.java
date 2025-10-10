package net.nokunami.elementus.datagen.generators.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nokunami.elementus.common.registry.EBlocks;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    private static final LootItemCondition.Builder HAS_SHEARS_OR_SILK_TOUCH = HAS_SHEARS.or(HAS_SILK_TOUCH);
    private static final LootItemCondition.Builder HAS_NO_SHEARS_OR_SILK_TOUCH = HAS_SHEARS_OR_SILK_TOUCH.invert();
    protected static final float[] NORMAL_LEAVES_SAPLING_CHANCES = new float[]{0.05F, 0.0625F, 0.083333336F, 0.1F};
    private static final float[] NORMAL_LEAVES_STICK_CHANCES = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void generate() {
        this.dropSelf(EBlocks.STEEL_BLOCK.get());
        this.dropSelf(EBlocks.DIARKRITE_BLOCK.get());
        this.dropSelf(EBlocks.ANTHEKTITE_BLOCK.get());
        this.dropSelf(EBlocks.REMNANT.get());

        this.dropSelf(EBlocks.STEEL_BARS.get());

        this.dropSelf(EBlocks.STEEL_TILES.get());
        this.dropSelf(EBlocks.STEEL_TILE_STAIR.get());
        this.dropSelf(EBlocks.STEEL_TILE_SLAB.get());

        this.dropSelf(EBlocks.MOVCADIA_LOG.get());
        this.dropSelf(EBlocks.STRIPPED_MOVCADIA_LOG.get());
        this.dropSelf(EBlocks.MOVCADIA_WOOD.get());
        this.dropSelf(EBlocks.STRIPPED_MOVCADIA_WOOD.get());

        this.dropSelf(EBlocks.MOVCADIA_ROOTED_DIRT.get());
        this.dropSelf(EBlocks.MOVCADIA_ROOTED_STONE.get());
        this.dropSelf(EBlocks.MOVCADIA_ROOTED_DEEPSLATE.get());

        this.add(EBlocks.MOVCADIA_LEAVES.get(), block ->
                createLeavesDrops(block, EBlocks.MOVCADIA_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(EBlocks.FLOWERING_MOVCADIA_LEAVES.get(), block ->
                movcadiaBerryLeaves(block, EBlocks.MOVCADIA_SAPLING.get(), EItems.MOVCADIA_BERRIES.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(EBlocks.MOVCADIA_PLANKS.get());
        this.dropSelf(EBlocks.MOVCADIA_STAIRS.get());
        this.dropSelf(EBlocks.MOVCADIA_SLAB.get());

        this.dropSelf(EBlocks.MOVCADIA_FENCE.get());
        this.dropSelf(EBlocks.MOVCADIA_FENCE_GATE.get());

        this.add(EBlocks.MOVCADIA_DOOR.get(), createDoorTable(EBlocks.MOVCADIA_DOOR.get()));
        this.dropSelf(EBlocks.MOVCADIA_TRAPDOOR.get());

        this.dropSelf(EBlocks.MOVCADIA_PRESSURE_PLATE.get());
        this.dropSelf(EBlocks.MOVCADIA_BUTTON.get());

        this.add(EBlocks.MOVCADIA_SIGN.get(), block ->
                createSingleItemTable(EItems.MOVCADIA_SIGN.get()));
        this.add(EBlocks.MOVCADIA_WALL_SIGN.get(), block ->
                createSingleItemTable(EItems.MOVCADIA_SIGN.get()));
        this.add(EBlocks.MOVCADIA_HANGING_SIGN.get(), block ->
                createSingleItemTable(EItems.MOVCADIA_HANGING_SIGN.get()));
        this.add(EBlocks.MOVCADIA_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(EItems.MOVCADIA_HANGING_SIGN.get()));
        this.add(EBlocks.STURDY_MOVCADIA_SIGN.get(), block ->
                createSingleItemTable(EItems.STURDY_MOVCADIA_SIGN.get()));
        this.add(EBlocks.STURDY_MOVCADIA_WALL_SIGN.get(), block ->
                createSingleItemTable(EItems.STURDY_MOVCADIA_SIGN.get()));

        this.dropSelf(EBlocks.MOVCADIA_CHEST.get());

        this.dropSelf(EBlocks.MOVCADIA_SAPLING.get());
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    protected LootTable.Builder movcadiaBerryLeaves(Block pLeavesBlock, Block pSaplingBlock, Item item, float... pChances) {
        return createSilkTouchOrShearsDispatchTable(pLeavesBlock,
                this.applyExplosionCondition(pLeavesBlock, LootItem.lootTableItem(pSaplingBlock))
                        .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, pChances)))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).when(HAS_NO_SHEARS_OR_SILK_TOUCH)
                        .add(this.applyExplosionDecay(pLeavesBlock, LootItem.lootTableItem(Items.STICK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE, NORMAL_LEAVES_STICK_CHANCES)))
                        .add(this.applyExplosionDecay(pLeavesBlock,
                                LootItem.lootTableItem(item)
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)))));
    }

    @Override
    protected void add(@NotNull Block pBlock, LootTable.@NotNull Builder pBuilder) {
        super.add(pBlock, pBuilder);
        knownBlocks.add(pBlock);
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
//        return ElementusBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
        return knownBlocks;
//        return List.of(
//                ElementusBlocks.REMNANT.get(),
//                ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK.get());
    }
}
