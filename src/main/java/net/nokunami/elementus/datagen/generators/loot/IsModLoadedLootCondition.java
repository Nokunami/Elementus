package net.nokunami.elementus.datagen.generators.loot;

//import com.aetherteam.aether.data.ConfigSerializationUtil;
//import com.aetherteam.aether.loot.conditions.ConfigEnabled;
//import com.google.gson.JsonDeserializationContext;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonSerializationContext;
//import net.minecraft.util.GsonHelper;
//import net.minecraft.world.level.storage.loot.LootContext;
//import net.minecraft.world.level.storage.loot.Serializer;
//import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
//import net.minecraftforge.common.ForgeConfigSpec;
//import net.nokunami.elementus.common.registry.ELootItemConditions;
//import org.jetbrains.annotations.NotNull;

//public class IsModLoadedLootCondition implements LootItemCondition {
////    private final ForgeConfigSpec.ConfigValue<Boolean> config;
//    private final boolean isLoaded;
//
////    public IsModLoadedLootCondition(ForgeConfigSpec.ConfigValue<Boolean> config) {
//    public IsModLoadedLootCondition(boolean isLoaded) {
////        this.config = config;
//        this.isLoaded = isLoaded;
//    }
//
//    public @NotNull LootItemConditionType getType() { return ELootItemConditions.IS_MOD_LOADED.get(); }
//
////    public boolean test(LootContext lootContext) { return this.config.get(); }
//    public boolean test(LootContext lootContext) { return isLoaded; }
//
////    public static LootItemCondition.Builder isEnabled(ForgeConfigSpec.ConfigValue<Boolean> config) {
////        return () -> new ConfigEnabled(config);
////    }
//    public static LootItemCondition.Builder isEnabled(boolean isLoaded) {
//        return () -> new IsModLoadedLootCondition(isLoaded);
//    }
//
//    public static class ESerializer implements Serializer<IsModLoadedLootCondition> {
//        public void serialize(JsonObject json, IsModLoadedLootCondition instance, @NotNull JsonSerializationContext context) {
////            json.addProperty("config", ConfigSerializationUtil.serialize(instance.config));
//            json.addProperty("boolean", ConfigSerializationUtil.serialize(instance.isLoaded));
//        }
//
//        public @NotNull IsModLoadedLootCondition deserialize(@NotNull JsonObject json, @NotNull JsonDeserializationContext context) {
////            return new IsModLoadedLootCondition(ConfigSerializationUtil.deserialize(GsonHelper.getAsString(json, "config")));
//            return new IsModLoadedLootCondition(ConfigSerializationUtil.deserialize(GsonHelper.getAsString(json, "boolean")));
//        }
//    }
//}
