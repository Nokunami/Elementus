package net.nokunami.elementus.client;

import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.nokunami.elementus.Elementus;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static net.minecraft.client.renderer.Sheets.*;

public class ModAtlases {
    public static Material MOVCADIA_CHEST_MATERIAL;
    public static Material MOVCADIA_CHEST_LEFT_MATERIAL;
    public static Material MOVCADIA_CHEST_RIGHT_MATERIAL;
    public static final Map<WoodType, Material> SIGN_MATERIALS = WoodType.values().collect(Collectors.toMap(Function.identity(), ModAtlases::createSignMaterial));
    public static final Map<WoodType, Material> HANGING_SIGN_MATERIALS = WoodType.values().collect(Collectors.toMap(Function.identity(), ModAtlases::createHangingSignMaterial));

    @SubscribeEvent
    public static void registerSheets() {
        MOVCADIA_CHEST_MATERIAL = getChestMaterial("movcadia_chest");
        MOVCADIA_CHEST_LEFT_MATERIAL = getChestMaterial("movcadia_chest_left");
        MOVCADIA_CHEST_RIGHT_MATERIAL = getChestMaterial("movcadia_chest_right");
    }

    private static Material createSignMaterial(WoodType woodType) {
        ResourceLocation location = new ResourceLocation(woodType.name());
        return new Material(SIGN_SHEET, new ResourceLocation(Elementus.MODID, "entity/signs/" + location.getPath()));
    }

    private static Material createHangingSignMaterial(WoodType woodType) {
        ResourceLocation location = new ResourceLocation(woodType.name());
        return new Material(SIGN_SHEET, new ResourceLocation(Elementus.MODID, "entity/signs/hanging/" + location.getPath()));
    }

    public static Material getChestMaterial(String chestName) {
        return new Material(CHEST_SHEET, new ResourceLocation(Elementus.MODID, "entity/tiles/chest/" + chestName));
    }

    public static Material getSignMaterial(WoodType pWoodType) {
        return SIGN_MATERIALS.get(pWoodType);
    }

    public static Material getHangingSignMaterial(WoodType pWoodType) {
        return HANGING_SIGN_MATERIALS.get(pWoodType);
    }
}
