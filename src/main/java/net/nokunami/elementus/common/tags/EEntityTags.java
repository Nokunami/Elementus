package net.nokunami.elementus.common.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

import static net.nokunami.elementus.Elementus.EID;

public class EEntityTags {
    public static final TagKey<EntityType<?>> STEEL_GOLEM_PRIORITY_TARGETS = elementusTag("astalite_golem_priority_targets");
    public static final TagKey<EntityType<?>> STEEL_GOLEM_AVOID = elementusTag("astalite_golem_avoid_targets");
    public static final TagKey<EntityType<?>> DIARKRITE_GOLEM_PRIORITY_TARGETS = elementusTag("diarkrite_golem_priority_targets");
    public static final TagKey<EntityType<?>> DIARKRITE_GOLEM_AVOID = elementusTag("diarkrite_golem_avoid_targets");
    public static final TagKey<EntityType<?>> ANTHEKTITE_GOLEM_PRIORITY_TARGETS = elementusTag("anthektite_golem_priority_targets");
    public static final TagKey<EntityType<?>> ANTHEKTITE_GOLEM_AVOID = elementusTag("anthektite_golem_avoid_targets");

    public static final TagKey<EntityType<?>> FAW_ATTACKABLE_BUT_NOT_ASSIMILABLE = modTag("fromanotherworld", "attackable_but_not_assimilable");
    public static final TagKey<EntityType<?>> FAW_NOT_AFRAID_OF_THINGS = modTag("fromanotherworld", "not_afraid_of_things");

    private static TagKey<EntityType<?>> forgeTag(String name) {
        return modTag("forge", name);
    }

    private static TagKey<EntityType<?>> elementusTag(String name) {
        return modTag(EID, name);
    }

    private static TagKey<EntityType<?>> modTag(String modId, String name) {
        return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(modId, name));
    }
}
