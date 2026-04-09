package net.nokunami.elementus.common.config.configSets.armor;

import net.minecraft.world.item.ArmorItem;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.EnumMap;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ArmorSetConfig {
    public final ForgeConfigSpec.IntValue helmet;
    public final ForgeConfigSpec.IntValue chestplate;
    public final ForgeConfigSpec.IntValue leggings;
    public final ForgeConfigSpec.IntValue boots;

    public ArmorSetConfig(final ForgeConfigSpec.Builder builder, String path, String id, int defaultHelmet, int defaultChsetplate, int defaultLeggings, int defaultBoots) {
        helmet = builder.translation(PREFIX + id + ".armor_material.helmet")
                .defineInRange(path + ".armor.helmet", defaultHelmet, Integer.MIN_VALUE, Integer.MAX_VALUE);
        chestplate = builder.translation(PREFIX + id + ".armor_material.chestplate")
                .defineInRange(path + ".armor.chestplate", defaultChsetplate, Integer.MIN_VALUE, Integer.MAX_VALUE);
        leggings = builder.translation(PREFIX + id + ".armor_material.leggings")
                .defineInRange(path + ".armor.leggings", defaultLeggings, Integer.MIN_VALUE, Integer.MAX_VALUE);
        boots = builder.translation(PREFIX + id + ".armor_material.boots")
                .defineInRange(path + ".armor.boots", defaultBoots, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public void reload(ArmorSetConfig config) {
    }

//    public int getDefenseForType(ArmorItem.@NotNull Type typeDefense) { return getConfigAmor().get(typeDefense); }
//    private EnumMap<ArmorItem.Type, Integer> getConfigAmor() {
//        return Util.make(new EnumMap<>(ArmorItem.Type.class), a -> {
//            a.put(ArmorItem.Type.BOOTS, boots.get());
//            a.put(ArmorItem.Type.LEGGINGS, leggings.get());
//            a.put(ArmorItem.Type.CHESTPLATE, chestplate.get());
//            a.put(ArmorItem.Type.HELMET, helmet.get());
//        });
//    }

//    public EnumMap<ArmorItem.Type, Integer> getArmor();
}
