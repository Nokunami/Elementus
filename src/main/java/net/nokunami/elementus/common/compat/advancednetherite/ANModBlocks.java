package net.nokunami.elementus.common.compat.advancednetherite;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;

public class ANModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);

    public static final RegistryObject<Block> DIARKRITE_IRON_BLOCK = BLOCKS.register("diarkrite_iron_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> DIARKRITE_GOLD_BLOCK = BLOCKS.register("diarkrite_gold_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> DIARKRITE_EMERALD_BLOCK = BLOCKS.register("diarkrite_emerald_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> DIARKRITE_DIAMOND_BLOCK = BLOCKS.register("diarkrite_diamond_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> ANTHEKTITE_IRON_BLOCK = BLOCKS.register("anthektite_iron_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> ANTHEKTITE_GOLD_BLOCK = BLOCKS.register("anthektite_gold_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> ANTHEKTITE_EMERALD_BLOCK = BLOCKS.register("anthektite_emerald_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));
    public static final RegistryObject<Block> ANTHEKTITE_DIAMOND_BLOCK = BLOCKS.register("anthektite_diamond_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)));


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
