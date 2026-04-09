package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;
import net.nokunami.elementus.common.registry.EDamageTypes;

import static net.nokunami.elementus.Elementus.EID;

public class ModDamageTypesTag {

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(EDamageTypes.SACRIFICIAL, new DamageType(EID + ".sacrificial", 0.2F));
    }
}
