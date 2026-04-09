package net.nokunami.elementus.common.registry;

import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;

public class EGameRules {
    public static GameRules.Key<GameRules.BooleanValue> ELEMENTUS_DEBUG;

    public static void init() {
        EGameRules.ELEMENTUS_DEBUG = GameRules.register("elementusDebugMode", GameRules.Category.MISC, GameRules.BooleanValue.create(false));
    }

    public static boolean isDebugModeOn(Level level) {
        return !level.isClientSide && level.getGameRules().getBoolean(EGameRules.ELEMENTUS_DEBUG);
    }
}
