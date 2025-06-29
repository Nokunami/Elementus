package nokunami.elementus.common.compat.refurbished_furniture;

//public class RFModBlocks {
//    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Elementus.MODID);
//
//    public static final RegistryObject<ChairBlock> MOVCADIA_CHAIR = BLOCKS.register("movcadia_chair",
//            () -> new ChairBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<TableBlock> MOVCADIA_TABLE = BLOCKS.register("movcadia_table",
//            () -> new TableBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<DeskBlock> MOVCADIA_DESK = BLOCKS.register("movcadia_desk",
//            () -> new DeskBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<DrawerBlock> MOVCADIA_DRAWER = BLOCKS.register("movcadia_drawer",
//            () -> new DrawerBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<CeilingFanBlock> MOVCADIA_LIGHT_CEILING_FAN = BLOCKS.register("movcadia_light_ceiling_fan",
//            () -> new CeilingFanBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, MetalType.LIGHT,  BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<CeilingFanBlock> MOVCADIA_DARK_CEILING_FAN = BLOCKS.register("movcadia_dark_ceiling_fan",
//            () -> new CeilingFanBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, MetalType.DARK,  BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenStorageCabinetBlock> MOVCADIA_STORAGE_CABINET = BLOCKS.register("movcadia_storage_cabinet",
//            () -> new WoodenStorageCabinetBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenKitchenCabinetryBlock> MOVCADIA_KITCHEN_CABINETRY = BLOCKS.register("movcadia_kitchen_cabinetry",
//            () -> new WoodenKitchenCabinetryBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenKitchenDrawerBlock> MOVCADIA_KITCHEN_DRAWER = BLOCKS.register("movcadia_kitchen_drawer",
//            () -> new WoodenKitchenDrawerBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenKitchenSinkBlock> MOVCADIA_KITCHEN_SINK = BLOCKS.register("movcadia_kitchen_sink",
//            () -> new WoodenKitchenSinkBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenKitchenStorageCabinetBlock> MOVCADIA_KITCHEN_STORAGE_DRAWER = BLOCKS.register("movcadia_kitchen_storage_drawer",
//            () -> new WoodenKitchenStorageCabinetBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<CuttingBoardBlock> MOVCADIA_CUTTING_BOARD = BLOCKS.register("movcadia_cutting_board",
//            () -> new CuttingBoardBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<CrateBlock> MOVCADIA_CRATE = BLOCKS.register("movcadia_crate",
//            () -> new CrateBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<MailboxBlock> MOVCADIA_MAIL_BOX = BLOCKS.register("movcadia_mail_box",
//            () -> new MailboxBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<HedgeBlock> MOVCADIA_HEDGE = BLOCKS.register("movcadia_hedge",
//            () -> new HedgeBlock(ModBlockSetType.MOVCADIA_LEAF_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<LatticeFenceBlock> MOVCADIA_LATTICE_FENCE = BLOCKS.register("movcadia_lattice_fance",
//            () -> new LatticeFenceBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<LatticeFenceGateBlock> MOVCADIA_LATTICE_FENCE_GATE = BLOCKS.register("movcadia_lattice_fance_gate",
//            () -> new LatticeFenceGateBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenToiletBlock> MOVCADIA_TOILET = BLOCKS.register("movcadia_toilet",
//            () -> new WoodenToiletBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenBasinBlock> MOVCADIA_BASIN = BLOCKS.register("movcadia_basin",
//            () -> new WoodenBasinBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static final RegistryObject<WoodenBathBlock> MOVCADIA_BATH = BLOCKS.register("movcadia_bath",
//            () -> new WoodenBathBlock(ModBlockSetType.MOVCADIA_WOOD_TYPE, BlockBehaviour.Properties.copy(ModBlocks.MOVCADIA_PLANKS.get())));
//
//    public static void register(IEventBus eventBus) {
//        BLOCKS.register(eventBus);
//    }
//}
