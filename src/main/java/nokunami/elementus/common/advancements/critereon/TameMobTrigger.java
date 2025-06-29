package nokunami.elementus.common.advancements.critereon;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.storage.loot.LootContext;
import org.jetbrains.annotations.NotNull;

public class TameMobTrigger extends SimpleCriterionTrigger<TameMobTrigger.TriggerInstance> {
    static final ResourceLocation ID = new ResourceLocation("tame_mob");

    public @NotNull ResourceLocation getId() {
        return ID;
    }

    public TameMobTrigger.@NotNull TriggerInstance createInstance(@NotNull JsonObject pJson, @NotNull ContextAwarePredicate pPredicate, @NotNull DeserializationContext pDeserializationContext) {
        ContextAwarePredicate contextawarepredicate = EntityPredicate.fromJson(pJson, "entity", pDeserializationContext);
        return new TameMobTrigger.TriggerInstance(pPredicate, contextawarepredicate);
    }

    public void trigger(ServerPlayer pPlayer, Mob pEntity) {
        LootContext lootcontext = EntityPredicate.createContext(pPlayer, pEntity);
        this.trigger(pPlayer, (p_68838_) -> p_68838_.matches(lootcontext));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        private final ContextAwarePredicate entity;

        public TriggerInstance(ContextAwarePredicate pPlayer, ContextAwarePredicate pEntity) {
            super(TameMobTrigger.ID, pPlayer);
            this.entity = pEntity;
        }

        public static TameMobTrigger.TriggerInstance tamedMob() {
            return new TameMobTrigger.TriggerInstance(ContextAwarePredicate.ANY, ContextAwarePredicate.ANY);
        }

        public static TameMobTrigger.TriggerInstance tamedMob(EntityPredicate pEntityPredicate) {
            return new TameMobTrigger.TriggerInstance(ContextAwarePredicate.ANY, EntityPredicate.wrap(pEntityPredicate));
        }

        public boolean matches(LootContext pLootContext) {
            return this.entity.matches(pLootContext);
        }

        public @NotNull JsonObject serializeToJson(@NotNull SerializationContext pConditions) {
            JsonObject jsonobject = super.serializeToJson(pConditions);
            jsonobject.add("entity", this.entity.toJson(pConditions));
            return jsonobject;
        }
    }
}
