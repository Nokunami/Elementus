package net.nokunami.elementus.common.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import static net.nokunami.elementus.Elementus.EID;

public class EDamageTypeTags {
    public static final TagKey<DamageType> STEEL_GOLEM_IMMUNE = elementusTag("steel_golem_immune");
    public static final TagKey<DamageType> IS_MAGIC = forgeTag("is_magic");

    private static TagKey<DamageType> forgeTag(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("forge", name));
    }

    private static TagKey<DamageType> elementusTag(String name) {
        return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(EID, name));
    }
}
