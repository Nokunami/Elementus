package net.nokunami.elementus.common.catalystCore.ability.activeAbility.cursiumWarrior;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.catalystCore.ability.AbilityType;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;

public class PhantomHalberdsAbility extends AbstractActiveAbility {

    public PhantomHalberdsAbility() {
        super(new AbilityProperties(AbilityType.TRIGGERED, 200).setCastDuration(10));
    }

    @Override
    public boolean castAbility(LivingEntity entity, Level level, CAbility ca) {
        return false;
    }
}
