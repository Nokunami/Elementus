package nokunami.elementus.datagen.loot;

import com.autovw.advancednetherite.config.ConfigHelper;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import nokunami.elementus.common.registry.ModItems.*;
import org.jetbrains.annotations.NotNull;

/**
 * A loot modifier for adding additional drops to blocks.
 * See {@link com.autovw.advancednetherite.datagen.providers.ModLootModifierProvider} for example implementation.
 * @apiNote This loot modifier can be disabled by {@link com.autovw.advancednetherite.config.Config.AdditionalDropsConfig#enableAdditionalOreDrops}
 * @author Autovw
 */
public class OreDropsLootModifier extends LootModifier
{
    public static final Codec<OreDropsLootModifier> CODEC = RecordCodecBuilder.create(instance -> codecStart(instance).apply(instance, OreDropsLootModifier::new));

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    public OreDropsLootModifier(LootItemCondition[] conditionsIn)
    {
        super(conditionsIn);
    }

    @NotNull
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
    {
        ItemStack tool = context.getParamOrNull(LootContextParams.TOOL);
        BlockState blockState = context.getParamOrNull(LootContextParams.BLOCK_STATE);

        if (tool != null && blockState != null && ConfigHelper.get().getCommon().getAdditionalDrops().enableAdditionalOreDrops())
        {
            Block block = blockState.getBlock();
            Item toolItem = tool.getItem();
            RandomSource random = context.getRandom();

            if (EnchantmentHelper.getEnchantments(tool).containsKey(Enchantments.SILK_TOUCH))
            {
                return generatedLoot; // return early if tool is enchanted with silk touch
            }

            if ((block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE)
                    && (toolItem == AdvancedNetheriteItems.DIARKRITE_IRON_PICKAXE.get() || toolItem == AdvancedNetheriteItems.ANTHEKTITE_IRON_PICKAXE.get())
                    && random.nextFloat() <= ConfigHelper.get().getServer().getAdditionalDropProperties().getAdditionalRawIronDropChance()) {
                generatedLoot.add(new ItemStack(Items.RAW_IRON, random.nextIntBetweenInclusive(1, 2)));
            }

            if ((block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE)
                    && (toolItem == AdvancedNetheriteItems.DIARKRITE_GOLD_PICKAXE.get() || toolItem == AdvancedNetheriteItems.ANTHEKTITE_GOLD_PICKAXE.get())
                    && random.nextFloat() <= ConfigHelper.get().getServer().getAdditionalDropProperties().getAdditionalRawGoldDropChance()) {
                generatedLoot.add(new ItemStack(Items.RAW_GOLD, random.nextIntBetweenInclusive(1, 1)));
            }

            if ((block == Blocks.EMERALD_ORE || block == Blocks.DEEPSLATE_EMERALD_ORE)
                    && (toolItem == AdvancedNetheriteItems.DIARKRITE_EMERALD_PICKAXE.get() || toolItem == AdvancedNetheriteItems.ANTHEKTITE_EMERALD_PICKAXE.get())
                    && random.nextFloat() <= ConfigHelper.get().getServer().getAdditionalDropProperties().getAdditionalEmeraldDropChance()) {
                generatedLoot.add(new ItemStack(Items.EMERALD, random.nextIntBetweenInclusive(1, 1)));
            }

            if ((block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE)
                    && (toolItem == AdvancedNetheriteItems.DIARKRITE_DIAMOND_PICKAXE.get() || toolItem == AdvancedNetheriteItems.ANTHEKTITE_DIAMOND_PICKAXE.get())
                    && random.nextFloat() <= ConfigHelper.get().getServer().getAdditionalDropProperties().getAdditionalDiamondDropChance())
            {
                generatedLoot.add(new ItemStack(Items.DIAMOND, random.nextIntBetweenInclusive(1, 1)));
            }

            if (block == Blocks.NETHER_GOLD_ORE
                    && (toolItem == AdvancedNetheriteItems.DIARKRITE_GOLD_PICKAXE.get() || toolItem == AdvancedNetheriteItems.ANTHEKTITE_GOLD_PICKAXE.get())
                    && random.nextFloat() <= ConfigHelper.get().getServer().getAdditionalDropProperties().getAdditionalGoldNuggetDropChance()) {
                generatedLoot.add(new ItemStack(Items.GOLD_NUGGET, random.nextIntBetweenInclusive(1, 3)));
            }
        }
        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec()
    {
        return CODEC;
    }
}
