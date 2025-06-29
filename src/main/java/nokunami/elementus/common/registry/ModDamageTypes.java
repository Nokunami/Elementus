package nokunami.elementus.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import static nokunami.elementus.Elementus.MODID;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> SACRIFICIAL = register("sacrificial");

    private static ResourceKey<DamageType> register (String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(MODID, name));
    }
}