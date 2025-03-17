package net.nokunami.elementus.common.compat.refurbished_furniture.data;

//public class RFCommonBlockModelProvider extends CommonBlockModelProvider {
//    private final Consumer<PreparedVariantBlockState> variantStateConsumer;
//    private final Consumer<PreparedMultiPartBlockState> multiPartStateConsumer;
//    private final Consumer<ExtraModel> extraModelConsumer;
//
//    public RFCommonBlockModelProvider(Consumer<PreparedVariantBlockState> variantStateConsumer, Consumer<PreparedMultiPartBlockState> multiPartStateConsumer, Consumer<ExtraModel> extraModelConsumer) {
//        super(variantStateConsumer, multiPartStateConsumer, extraModelConsumer);
//        this.variantStateConsumer = variantStateConsumer;
//        this.multiPartStateConsumer = multiPartStateConsumer;
//        this.extraModelConsumer = extraModelConsumer;
//    }
//
//    public void run() {
//        chair(RFModBlocks.MOVCADIA_CHAIR.get());
//        table(RFModBlocks.MOVCADIA_TABLE.get());
//        desk(RFModBlocks.MOVCADIA_DESK.get());
//        drawer(RFModBlocks.MOVCADIA_DRAWER.get());
//        ceilingFan(RFModBlocks.MOVCADIA_LIGHT_CEILING_FAN.get());
//        ceilingFan(RFModBlocks.MOVCADIA_DARK_CEILING_FAN.get());
//        storageCabinet(RFModBlocks.MOVCADIA_STORAGE_CABINET.get());
//        woodenKitchenCabinetry(RFModBlocks.MOVCADIA_KITCHEN_CABINETRY.get());
//        woodenKitchenDrawer(RFModBlocks.MOVCADIA_KITCHEN_DRAWER.get());
//        woodenKitchenSink(RFModBlocks.MOVCADIA_KITCHEN_SINK.get());
//        woodenKitchenCabinet(RFModBlocks.MOVCADIA_KITCHEN_STORAGE_DRAWER.get());
//        cuttingBoard(RFModBlocks.MOVCADIA_CUTTING_BOARD.get());
//        crate(RFModBlocks.MOVCADIA_CRATE.get());
//        mailbox(RFModBlocks.MOVCADIA_MAIL_BOX.get());
////        hedge(RFModBlocks.MOVCADIA_HEDGE.get());
//        latticeFence(RFModBlocks.MOVCADIA_LATTICE_FENCE.get());
//        latticeFenceGate(RFModBlocks.MOVCADIA_LATTICE_FENCE_GATE.get());
//        woodenToilet(RFModBlocks.MOVCADIA_TOILET.get());
//        woodenBasin(RFModBlocks.MOVCADIA_BASIN.get());
//        woodenBath(RFModBlocks.MOVCADIA_BATH.get());
//    }
//
//    public ResourceLocation blockTexture(Block block) {
//        ResourceLocation name = BuiltInRegistries.BLOCK.getKey(block);
//        return new ResourceLocation(name.getNamespace(), "block/" + name.getPath());
//    }
//
//    public ResourceLocation woodParticle(WoodType type) {
//        return new ResourceLocation("refurbished_furniture", "block/" + type.name() + "_particle");
//    }
//
//    public ResourceLocation metalParticle(MetalType type) {
//        return new ResourceLocation("refurbished_furniture", "block/" + type.getName() + "_particle");
//    }
//
//    public ResourceLocation leafTexture(com.mrcrayfish.furniture.refurbished.block.LeafType type) {
//        return new ResourceLocation("block/" + type.getName() + "_leaves");
//    }
//
//    private void chair(ChairBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.NORTH).prop(ChairBlock.TUCKED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.EAST).prop(ChairBlock.TUCKED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.SOUTH).prop(ChairBlock.TUCKED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.WEST).prop(ChairBlock.TUCKED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.NORTH).prop(ChairBlock.TUCKED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR_TUCKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.EAST).prop(ChairBlock.TUCKED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR_TUCKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.SOUTH).prop(ChairBlock.TUCKED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR_TUCKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(ChairBlock.DIRECTION, Direction.WEST).prop(ChairBlock.TUCKED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CHAIR_TUCKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void table(TableBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model) ModelTemplate.TABLE.stateModel(type).setTextures(textures)).markAsItem();
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_NORTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_NORTH_EAST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_NORTH_EAST_SOUTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_EAST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_EAST_SOUTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_EAST_SOUTH_WEST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_SOUTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_SOUTH_WEST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_SOUTH_WEST_NORTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_WEST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_WEST_NORTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_WEST_NORTH_EAST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, false).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_NORTH_SOUTH.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, false).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, false).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_EAST_WEST.stateModel(type).setTextures(textures));
//        state.createVariant().prop(TableBlock.NORTH, true).prop(TableBlock.EAST, true).prop(TableBlock.SOUTH, true).prop(TableBlock.WEST, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.TABLE_NORTH_EAST_SOUTH_WEST.stateModel(type).setTextures(textures));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void desk(DeskBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.NORTH).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.EAST).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.SOUTH).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.WEST).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.NORTH).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.EAST).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.SOUTH).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.WEST).prop(DeskBlock.LEFT, false).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.NORTH).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.EAST).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.SOUTH).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.WEST).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.NORTH).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_MIDDLE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.EAST).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_MIDDLE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.SOUTH).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_MIDDLE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DeskBlock.DIRECTION, Direction.WEST).prop(DeskBlock.LEFT, true).prop(DeskBlock.RIGHT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DESK_MIDDLE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void drawer(DrawerBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, false).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_RIGHT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, false).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_LEFT_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.LEFT, true).prop(DrawerBlock.RIGHT, true).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.DRAWER_MIDDLE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void ceilingFan(CeilingFanBlock block) {
//        WoodType woodType = block.getWoodType();
//        MetalType metalType = block.getMetalType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.metalParticle(metalType));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.NORTH).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.EAST).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.SOUTH).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.WEST).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.UP).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.DOWN).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.NORTH).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.EAST).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.SOUTH).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.WEST).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.UP).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.DOWN).prop(CeilingFanBlock.POWERED, false).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.NORTH).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.EAST).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.SOUTH).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.WEST).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.UP).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.DOWN).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_OFF.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.NORTH).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.EAST).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R90).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.SOUTH).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.WEST).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R270).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.UP).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures));
//        state.createVariant().prop(CeilingFanBlock.FACING, Direction.DOWN).prop(CeilingFanBlock.POWERED, true).prop(CeilingFanBlock.LIT, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CEILING_FAN_BASE_ON.stateModel(woodType, metalType).setTextures(textures)).setXRotation(VariantProperties.Rotation.R180));
//        this.variantStateConsumer.accept(state);
//        TextureMapping extraTextures = new TextureMapping();
//        extraTextures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        this.extraModelConsumer.accept((ExtraModel)ModelTemplate.CEILING_FAN_BLADE.extraModel(woodType, metalType).setTextures(extraTextures));
//    }
//
//    private void storageCabinet(WoodenStorageCabinetBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenKitchenCabinetry(WoodenKitchenCabinetryBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.NORTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.DEFAULT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_DEFAULT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.NORTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.NORTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.NORTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.NORTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.EAST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.DEFAULT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_DEFAULT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.EAST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.EAST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.EAST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.EAST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.SOUTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.DEFAULT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_DEFAULT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.SOUTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.SOUTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.SOUTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.SOUTH).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.WEST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.DEFAULT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_DEFAULT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.WEST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.WEST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.INSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_INSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.WEST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(WoodenKitchenCabinetryBlock.DIRECTION, Direction.WEST).prop(WoodenKitchenCabinetryBlock.SHAPE, KitchenCabinetryBlock.Shape.OUTSIDE_CORNER_RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_CABINETRY_OUTSIDE_CORNER_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenKitchenDrawer(WoodenKitchenDrawerBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_DRAWER_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenKitchenSink(WoodenKitchenSinkBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.NORTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_SINK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.EAST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_SINK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.SOUTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_SINK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(DrawerBlock.DIRECTION, Direction.WEST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_SINK.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenKitchenCabinet(WoodenKitchenStorageCabinetBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.LEFT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_LEFT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, false).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_CLOSED_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.NORTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.EAST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.SOUTH).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(StorageCabinetBlock.DIRECTION, Direction.WEST).prop(StorageCabinetBlock.OPEN, true).prop(StorageCabinetBlock.HINGE, DoorHingeSide.RIGHT).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.KITCHEN_STORAGE_CABINET_OPEN_HINGE_RIGHT.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void cuttingBoard(CuttingBoardBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(CuttingBoardBlock.DIRECTION, Direction.NORTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CUTTING_BOARD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(CuttingBoardBlock.DIRECTION, Direction.EAST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CUTTING_BOARD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(CuttingBoardBlock.DIRECTION, Direction.SOUTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CUTTING_BOARD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(CuttingBoardBlock.DIRECTION, Direction.WEST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.CUTTING_BOARD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void crate(CrateBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(CrateBlock.OPEN, false).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CRATE_CLOSED.stateModel(type).setTextures(textures)).markAsItem();
//        state.createVariant().prop(CrateBlock.OPEN, true).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.CRATE_OPEN.stateModel(type).setTextures(textures));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void mailbox(MailboxBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.NORTH).prop(MailboxBlock.ENABLED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.ENABLED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.ENABLED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.ENABLED, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.NORTH).prop(DrawerBlock.ENABLED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX_UNCHECKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.EAST).prop(DrawerBlock.ENABLED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX_UNCHECKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.SOUTH).prop(DrawerBlock.ENABLED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX_UNCHECKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(MailboxBlock.DIRECTION, Direction.WEST).prop(DrawerBlock.ENABLED, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.MAIL_BOX_UNCHECKED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void hedge(HedgeBlock block) {
//        LeafType type = block.getLeafType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.leafTexture(type));
//        textures.put(TextureSlot.TEXTURE, this.leafTexture(type));
//        PreparedMultiPartBlockState state = new PreparedMultiPartBlockState(block);
//        state.setItemModel((PreparedVariantBlockState.Model)((PreparedVariantBlockState.Model)ModelTemplate.HEDGE.stateModel(type).setTextures(textures)).markAsChild());
//        state.createPart().addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_STYLE_1.stateModel(type).setTextures(textures)).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_STYLE_2.stateModel(type).setTextures(textures)).addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_STYLE_3.stateModel(type).setTextures(textures));
//        state.createPart().prop(HedgeBlock.NORTH, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_SIDE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createPart().prop(HedgeBlock.EAST, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_SIDE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createPart().prop(HedgeBlock.SOUTH, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_SIDE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createPart().prop(HedgeBlock.WEST, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CENTER_SIDE.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createPart().prop(HedgeBlock.NORTH, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_1.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_2.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_3.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createPart().prop(HedgeBlock.EAST, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_1.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_2.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_3.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createPart().prop(HedgeBlock.SOUTH, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_1.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_2.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_3.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createPart().prop(HedgeBlock.WEST, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_1.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_2.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90)).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.HEDGE_CONNECTION_STYLE_3.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        this.multiPartStateConsumer.accept(state);
//    }
//
//    private void latticeFence(LatticeFenceBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedMultiPartBlockState state = new PreparedMultiPartBlockState(block);
//        state.setItemModel((PreparedVariantBlockState.Model)((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE.stateModel(type).setTextures(textures)).markAsChild());
//        state.createPart().addTexturedModel((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_CENTER.stateModel(type).setTextures(textures));
//        state.createPart().prop(HedgeBlock.NORTH, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_CONNECTION.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createPart().prop(HedgeBlock.EAST, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_CONNECTION.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createPart().prop(HedgeBlock.SOUTH, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_CONNECTION.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createPart().prop(HedgeBlock.WEST, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_CONNECTION.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        this.multiPartStateConsumer.accept(state);
//    }
//
//    private void latticeFenceGate(LatticeFenceGateBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedMultiPartBlockState state = new PreparedMultiPartBlockState(block);
//        state.setItemModel((PreparedVariantBlockState.Model)((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_CLOSED.stateModel(type).setTextures(textures)).markAsChild());
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.NORTH).prop(LatticeFenceGateBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.EAST).prop(LatticeFenceGateBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.SOUTH).prop(LatticeFenceGateBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.WEST).prop(LatticeFenceGateBlock.OPEN, false).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_CLOSED.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.NORTH).prop(LatticeFenceGateBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.EAST).prop(LatticeFenceGateBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.SOUTH).prop(LatticeFenceGateBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createPart().prop(LatticeFenceGateBlock.FACING, Direction.WEST).prop(LatticeFenceGateBlock.OPEN, true).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.LATTICE_FENCE_GATE_OPEN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.multiPartStateConsumer.accept(state);
//    }
//
//    private void woodenToilet(WoodenToiletBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(ToiletBlock.DIRECTION, Direction.NORTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.TOILET.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(ToiletBlock.DIRECTION, Direction.EAST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.TOILET.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(ToiletBlock.DIRECTION, Direction.SOUTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.TOILET.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(ToiletBlock.DIRECTION, Direction.WEST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.TOILET.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenBasin(WoodenBasinBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(BasinBlock.DIRECTION, Direction.NORTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BASIN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0)).markAsItem();
//        state.createVariant().prop(BasinBlock.DIRECTION, Direction.EAST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BASIN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(BasinBlock.DIRECTION, Direction.SOUTH).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BASIN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(BasinBlock.DIRECTION, Direction.WEST).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BASIN.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//
//    private void woodenBath(WoodenBathBlock block) {
//        WoodType type = block.getWoodType();
//        TextureMapping textures = new TextureMapping();
//        textures.put(TextureSlot.PARTICLE, this.woodParticle(type));
//        textures.put(TextureSlot.TEXTURE, this.blockTexture(block));
//        PreparedVariantBlockState state = new PreparedVariantBlockState(block);
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.NORTH).prop(BathBlock.TYPE, BathBlock.Type.HEAD).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_HEAD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.EAST).prop(BathBlock.TYPE, BathBlock.Type.HEAD).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_HEAD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.SOUTH).prop(BathBlock.TYPE, BathBlock.Type.HEAD).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_HEAD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.WEST).prop(BathBlock.TYPE, BathBlock.Type.HEAD).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_HEAD.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.NORTH).prop(BathBlock.TYPE, BathBlock.Type.BOTTOM).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_BOTTOM.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R0));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.EAST).prop(BathBlock.TYPE, BathBlock.Type.BOTTOM).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_BOTTOM.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R90));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.SOUTH).prop(BathBlock.TYPE, BathBlock.Type.BOTTOM).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_BOTTOM.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R180));
//        state.createVariant().prop(BathBlock.DIRECTION, Direction.WEST).prop(BathBlock.TYPE, BathBlock.Type.BOTTOM).addTexturedModel(((PreparedVariantBlockState.Model)ModelTemplate.BATH_BOTTOM.stateModel(type).setTextures(textures)).setYRotation(VariantProperties.Rotation.R270));
//        this.variantStateConsumer.accept(state);
//    }
//}
