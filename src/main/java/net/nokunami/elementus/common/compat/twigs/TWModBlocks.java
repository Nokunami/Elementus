package net.nokunami.elementus.common.compat.twigs;

import com.ninni.twigs.block.TableBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.registry.ModBlocks;

import static net.nokunami.elementus.Elementus.MODID;

public class TWModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> MOVCADIA_TABLE = BLOCKS.register("movcadia_table",
            () -> new TableBlock(BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);}
}
