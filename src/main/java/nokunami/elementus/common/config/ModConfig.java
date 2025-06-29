package nokunami.elementus.common.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModConfig {
    public static final ModConfigSpec.Builder CONFIG_CLIENT = new ModConfigSpec.Builder();
    public static final ModConfigSpec.Builder CONFIG_COMMON = new ModConfigSpec.Builder();
    public static final ModConfigSpec CLIENT_SPEC;
    public static final ModConfigSpec COMMON_SPEC;
    public static final Client CLIENT = new Client(CONFIG_CLIENT);
    public static final Common COMMON = new Common(CONFIG_COMMON);

    static {
        CLIENT_SPEC = CONFIG_CLIENT.build();
        COMMON_SPEC = CONFIG_COMMON.build();
    }

    public static class Client {
        public final ModConfigSpec.BooleanValue lavaRendererType;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("Client");
            lavaRendererType = builder
                    .translation("config.elementus.lava_renderer")
                    .define("ReplaceLavaRenderer", true);
            builder.pop();
        }
    }

    public static class Common {
        public final ModConfigSpec.BooleanValue diarkriteEfficiency;
        public final ModConfigSpec.BooleanValue catalystArmorDurability;
        public final ModConfigSpec.BooleanValue catalystTotemAbility;
        public final ModConfigSpec.BooleanValue arcaneSharpnessTreasure;
        public final ModConfigSpec.DoubleValue arcaneSharpnessPercent;
        public final ModConfigSpec.BooleanValue arcaneSharpnessIncompatibility;
        public final ModConfigSpec.DoubleValue test;

        public Common(ModConfigSpec.Builder builder) {
            builder.push("Common");
            diarkriteEfficiency = builder
                    .translation("config.elementus.diarkriteEfficiency.desc")
                            .define("config.elementus.diarkriteEfficiency", true);
            catalystArmorDurability = builder
                    .translation("config.elementus.catalystArmorDurability.desc")
                            .define("config.elementus.catalystArmorDurability", true);
            catalystTotemAbility = builder
                    .translation("config.elementus.catalystTotemAbility.desc")
                            .define("config.elementus.catalystTotemAbility", true);
            arcaneSharpnessTreasure = builder
                    .translation("config.elementus.arcaneSharpnessTreasure.desc")
                            .define("config.elementus.arcaneSharpnessTreasure", true);
            arcaneSharpnessPercent = builder.translation("config.elementus.arcaneSharpnessPercent.desc")
                    .defineInRange("config.elementus.arcaneSharpnessPercent", 0.3, 0, Double.MAX_VALUE);
            arcaneSharpnessIncompatibility = builder
                    .translation("config.elementus.arcaneSharpnessIncompatibility.desc")
                            .define("config.elementus.arcaneSharpnessIncompatibility", true);
            test = builder.translation("config.elementus.test")
                    .defineInRange("test", 0, 0, Double.MAX_VALUE);
            builder.pop();
        }
    }
}
