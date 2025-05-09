package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.damagesource.DamageType;
import net.nokunami.elementus.common.registry.ModDamageTypes;

import static net.nokunami.elementus.Elementus.MODID;

public class ModDamageTypesTag {

    public static void bootstrap(BootstapContext<DamageType> context) {
        context.register(ModDamageTypes.SACRIFICIAL, new DamageType(MODID + ".sacrificial", 0.2F));
    }
}
