package nokunami.elementus.common.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nokunami.elementus.Elementus;
import nokunami.elementus.common.block.entity.ModChestBlockEntity;
import nokunami.elementus.common.block.ModChests;
import nokunami.elementus.common.block.entity.ModHangingSignBlockEntity;
import nokunami.elementus.common.block.entity.ModSignBlockEntity;
import nokunami.elementus.common.registry.ModBlocks.*;

public class ModBlockEntityType {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Elementus.MODID);

    public static final RegistryObject<BlockEntityType<ModChestBlockEntity>> MOD_CHEST = BLOCK_ENTITIES.register("mod_chest",
            () -> BlockEntityType.Builder.of((pos, state)-> new ModChestBlockEntity(pos, state, ModChests.MOVCADIA),
                            ElementusBlocks.MOVCADIA_CHEST.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN = BLOCK_ENTITIES.register("mod_sign",
            () -> BlockEntityType.Builder.of(ModSignBlockEntity::new,
                    ElementusBlocks.MOVCADIA_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_SIGN.get(), ElementusBlocks.STURDY_MOVCADIA_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITIES.register("mod_hanging_sign",
            () -> BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                    ElementusBlocks.MOVCADIA_HANGING_SIGN.get(), ElementusBlocks.MOVCADIA_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
