package net.nokunami.elementus.common.compat.farmersdelight;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.registry.ModBlocks;
import vectorwing.farmersdelight.common.block.CabinetBlock;

public class FDModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);

    public static final RegistryObject<Block> MOVCADIA_CABINET = BLOCKS.register("movcadia_cabinet",
            () -> new CabinetBlock(BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
