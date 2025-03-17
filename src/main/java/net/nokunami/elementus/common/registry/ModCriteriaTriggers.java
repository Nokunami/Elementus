package net.nokunami.elementus.common.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.nokunami.elementus.common.advancements.critereon.TameMobTrigger;

public class ModCriteriaTriggers {
    public static TameMobTrigger TAME_MOB = new TameMobTrigger();

    public static void register() {
        CriteriaTriggers.register(TAME_MOB);
    }
}
