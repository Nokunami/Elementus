package net.nokunami.elementus.common.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.nokunami.elementus.common.advancements.critereon.TameGolemTrigger;

public class ModCriteriaTriggers {
    public static TameGolemTrigger TAME_MOB = new TameGolemTrigger();

    public static void register() { CriteriaTriggers.register(TAME_MOB); }
}
