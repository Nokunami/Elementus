package net.nokunami.elementus.common.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

import static net.nokunami.elementus.Elementus.EID;

public class ModBlockSetType {
    public static final BlockSetType ASTALITE_BLOC_SET = new BlockSetType(EID + ":astalite", false,
            SoundType.METAL,
            SoundEvents.IRON_DOOR_CLOSE, SoundEvents.IRON_DOOR_OPEN,
            SoundEvents.IRON_TRAPDOOR_CLOSE, SoundEvents.IRON_TRAPDOOR_OPEN,
            SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF, SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.STONE_BUTTON_CLICK_OFF, SoundEvents.STONE_BUTTON_CLICK_ON);

    public static final BlockSetType MOVCADIA_BLOCK_SET = new BlockSetType(EID + ":movcadia", true,
            SoundType.CHERRY_WOOD,
            SoundEvents.WOODEN_DOOR_CLOSE, SoundEvents.WOODEN_DOOR_OPEN,
            SoundEvents.BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BAMBOO_WOOD_TRAPDOOR_OPEN,
            SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON,
            SoundEvents.CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.CHERRY_WOOD_BUTTON_CLICK_ON);

    public static final WoodType MOVCADIA_WOOD_TYPE = WoodType.register(new WoodType(EID + ":movcadia",
            MOVCADIA_BLOCK_SET,
            SoundType.CHERRY_WOOD,
            SoundType.CHERRY_WOOD_HANGING_SIGN, SoundEvents.BAMBOO_WOOD_FENCE_GATE_CLOSE, SoundEvents.BAMBOO_WOOD_FENCE_GATE_OPEN));

    public static final WoodType STURDY_MOVCADIA_WOOD_TYPE = WoodType.register(new WoodType(EID + ":sturdy_movcadia" ,ModBlockSetType.MOVCADIA_BLOCK_SET));
}
