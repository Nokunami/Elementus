package nokunami.elementus.common.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import nokunami.elementus.Elementus;

public class ModBlockSetType {
    public static final BlockSetType MOVCADIA_BLOCK_SET = new BlockSetType(Elementus.MODID + ":movcadia", true, SoundType.CHERRY_WOOD, SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);
    public static final WoodType MOVCADIA_WOOD_TYPE = WoodType.register(new WoodType(Elementus.MODID + ":movcadia", MOVCADIA_BLOCK_SET, SoundType.CHERRY_WOOD, SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE, SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN));
    public static final WoodType STURDY_MOVCADIA_WOOD_TYPE = WoodType.register(new WoodType(Elementus.MODID + ":sturdy_movcadia" ,ModBlockSetType.MOVCADIA_BLOCK_SET));
}
