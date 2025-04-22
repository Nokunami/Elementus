package net.nokunami.elementus.common.entity.ai.navigation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;

public class TestGroundNavigation extends GroundPathNavigation {
    public TestGroundNavigation(Mob pMob, Level pLevel) {
        super(pMob, pLevel);
    }
}
