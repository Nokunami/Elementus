//package net.nokunami.elementus.mixin;
//
//import com.google.common.collect.ImmutableMap;
//import com.mojang.datafixers.DataFixerBuilder;
//import com.mojang.datafixers.schemas.Schema;
//import net.minecraft.util.datafix.DataFixers;
//import net.minecraft.util.datafix.fixes.ItemRenameFix;
//import net.minecraft.util.datafix.schemas.NamespacedSchema;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.Unique;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Map;
//import java.util.function.BiFunction;
//import java.util.function.UnaryOperator;
//
//@Mixin(DataFixers.class)
//public class DataFixersMixin {
//    @Shadow
//    private static final BiFunction<Integer, Schema, Schema> SAME_NAMESPACED = NamespacedSchema::new;
//
//    @Inject(method = "addFixers", at = @At("TAIL"), cancellable = true)
//    private static void FW$addFixers(DataFixerBuilder pBuilder, CallbackInfo ci) {
//        Schema schema54 = pBuilder.addSchema(0, SAME_NAMESPACED);
//        pBuilder.addFixer(ItemRenameFix.create(schema54, "Rename Steel to Astalite Items", E$createRenamer(ImmutableMap.of(
//                "elementus:crude_steel", "elementus:astalite_chunk",
//                "elementus:steel_scrap", "elementus:astalite_scrap",
//                "elementus:steel_ingot", "elementus:astalite_ingot",
//                "elementus:steel_nugget", "elementus:astalite_nugget",
//                "elementus:steel_sword", "elementus:astalite_sword",
//                "elementus:steel_shovel", "elementus:astalite_shovel",
//                "elementus:steel_pickaxe", "elementus:astalite_pickaxe",
//                "elementus:steel_axe", "elementus:astalite_axe",
//                "elementus:steel_hoe", "elementus:astalite_hoe"))));
//
//        pBuilder.addFixer(ItemRenameFix.create(schema54, "Rename Steel to Astalite Items 2", E$createRenamer(ImmutableMap.of(
//                "elementus:steel_shield", "elementus:astalite_shield",
//                "elementus:steel_bow", "elementus:astalite_bow",
//                "elementus:steel_helmet", "elementus:astalite_helmet",
//                "elementus:steel_chestplate", "elementus:astalite_chestplate",
//                "elementus:steel_leggings", "elementus:astalite_leggings",
//                "elementus:steel_boots", "elementus:astalite_boots"))));
//
//        var blockMap = ImmutableMap.of(
//                "elementus:steel_block", "elementus:astalite_block",
//                "elementus:steel_bars", "elementus:astalite_bars",
//                "elementus:steel_tiles", "elementus:astalite_tiles",
//                "elementus:steel_tile_stair", "elementus:astalite_tile_stair",
//                "elementus:steel_tile_slab", "elementus:astalite_tile_slab",
//                "elementus:steel_boots", "elementus:astalite_boots");
//
//        pBuilder.addFixer(ItemRenameFix.create(schema54, "Rename Steel to Astalite Blocks", E$createRenamer(blockMap)));
//    }
//
//    @Unique
//    private static UnaryOperator<String> E$createRenamer(Map<String, String> map) {
//        return (item) -> map.getOrDefault(item, item);
//    }
//
////        private static UnaryOperator<String> createRenamer(String pOldName, String pNewName) {
////        return (p_216523_) -> {
////            return Objects.equals(p_216523_, pOldName) ? pNewName : p_216523_;
////        };
////    }
//}
