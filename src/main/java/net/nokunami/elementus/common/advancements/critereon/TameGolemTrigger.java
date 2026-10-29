package net.nokunami.elementus.common.advancements.critereon;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootContext;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import org.jetbrains.annotations.NotNull;

public class TameGolemTrigger extends SimpleCriterionTrigger<TameGolemTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("tame_golem");

    public @NotNull ResourceLocation getId() { return ID; }

    public TameGolemTrigger.@NotNull TriggerInstance createInstance(@NotNull JsonObject pJson, @NotNull ContextAwarePredicate pPredicate, @NotNull DeserializationContext pDeserializationContext) {
        ContextAwarePredicate contextawarepredicate = EntityPredicate.fromJson(pJson, "entity", pDeserializationContext);
        return new TameGolemTrigger.TriggerInstance(pPredicate, contextawarepredicate);
    }

//    public void trigger(ServerPlayer pPlayer, Mob pEntity) {
//        LootContext lootcontext = EntityPredicate.createContext(pPlayer, pEntity);
//        this.trigger(pPlayer, (p_68838_) -> p_68838_.matches(lootcontext));
//    }

    public void trigger(Player player, TamableGolem golem) {
        if (player instanceof ServerPlayer serverPlayer) {
            LootContext lootcontext = EntityPredicate.createContext(serverPlayer, golem);
            trigger(serverPlayer, instance -> instance.matches(lootcontext));
        }
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate entity;

        public TriggerInstance(ContextAwarePredicate pPlayer, ContextAwarePredicate predicate) {
            super(TameGolemTrigger.ID, pPlayer);
            entity = predicate;
        }

        public static TameGolemTrigger.TriggerInstance tamedMob() {
            return new TameGolemTrigger.TriggerInstance(ContextAwarePredicate.ANY, ContextAwarePredicate.ANY);
        }

        public static TameGolemTrigger.TriggerInstance tamedMob(EntityPredicate pEntityPredicate) {
            return new TameGolemTrigger.TriggerInstance(ContextAwarePredicate.ANY, EntityPredicate.wrap(pEntityPredicate));
        }

        public boolean matches(LootContext pLootContext) { return entity.matches(pLootContext); }

        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("entity", entity.toJson(pConditions));
            return jsonobject;
        }
    }
}
