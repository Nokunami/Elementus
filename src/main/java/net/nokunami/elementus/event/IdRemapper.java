package net.nokunami.elementus.event;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

import java.util.HashMap;
import java.util.Map;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;

@Mod.EventBusSubscriber
public class IdRemapper {

    /// Credits: Supplementaries RemapHandler
    private static final Map<String, String> ITEM_REMAP = new HashMap<>();
    private static final Map<String, String> BLOCK_REMAP = new HashMap<>();
    private static final Map<String, String> ENTITY_REMAP = new HashMap<>();
    static {
        ITEM_REMAP.put(modLoc("crude_steel").toString(), modLoc("raw_astalite").toString());
        ITEM_REMAP.put(modLoc("steel_scrap").toString(), modLoc("astalite_scrap").toString());
        ITEM_REMAP.put(modLoc("steel_ingot").toString(), modLoc("astalite_ingot").toString());
        ITEM_REMAP.put(modLoc("steel_nugget").toString(), modLoc("astalite_nugget").toString());

        ITEM_REMAP.put(modLoc("steel_sword").toString(), modLoc("astalite_sword").toString());
        ITEM_REMAP.put(modLoc("steel_shovel").toString(), modLoc("astalite_shovel").toString());
        ITEM_REMAP.put(modLoc("steel_pickaxe").toString(), modLoc("astalite_pickaxe").toString());
        ITEM_REMAP.put(modLoc("steel_axe").toString(), modLoc("astalite_axe").toString());
        ITEM_REMAP.put(modLoc("steel_hoe").toString(), modLoc("astalite_hoe").toString());

        ITEM_REMAP.put(modLoc("steel_bow").toString(), modLoc("astalite_bow").toString());
        ITEM_REMAP.put(modLoc("steel_shield").toString(), modLoc("astalite_shield").toString());

        ITEM_REMAP.put(modLoc("steel_helmet").toString(), modLoc("astalite_helmet").toString());
        ITEM_REMAP.put(modLoc("steel_chestplate").toString(), modLoc("astalite_chestplate").toString());
        ITEM_REMAP.put(modLoc("steel_leggings").toString(), modLoc("astalite_leggings").toString());
        ITEM_REMAP.put(modLoc("steel_boots").toString(), modLoc("astalite_boots").toString());

        ITEM_REMAP.put(modLoc("diarkrite_charge_blade").toString(), modLoc("blade_of_resonance").toString());
        ITEM_REMAP.put(modLoc("anthektite_charge_blade").toString(), modLoc("blade_of_wind_charging").toString());

        ITEM_REMAP.put(modLoc("steel_block").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_bars").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tiles").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tile_stair").toString(), modLoc("astalite_block").toString());
        ITEM_REMAP.put(modLoc("steel_tile_slab").toString(), modLoc("astalite_block").toString());


        BLOCK_REMAP.put(modLoc("steel_block").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_bars").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tiles").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tile_stair").toString(), modLoc("astalite_block").toString());
        BLOCK_REMAP.put(modLoc("steel_tile_slab").toString(), modLoc("astalite_block").toString());

        ENTITY_REMAP.put(modLoc("steel_golem").toString(), modLoc("astalite_golem_longarm").toString());
        ENTITY_REMAP.put(modLoc("astalite_golem").toString(), modLoc("astalite_golem_carrier").toString());
    }

    @SubscribeEvent
    public static void onRemap(MissingMappingsEvent event) {
        for (var v : event.getMappings(ForgeRegistries.BLOCKS.getRegistryKey(), EID)) {
            String rem = BLOCK_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.BLOCK.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
        for (var v : event.getMappings(ForgeRegistries.ITEMS.getRegistryKey(), EID)) {
            String rem = ITEM_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.ITEM.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
        for (var v : event.getMappings(ForgeRegistries.ENTITY_TYPES.getRegistryKey(), EID)) {
            String rem = ENTITY_REMAP.get(v.getKey().toString());
            if (rem != null) {
                var b = BuiltInRegistries.ENTITY_TYPE.getOptional(new ResourceLocation(rem));
                b.ifPresent(v::remap);
            } else v.ignore();
        }
    }
}
