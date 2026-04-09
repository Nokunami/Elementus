package net.nokunami.elementus.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

import static net.nokunami.elementus.Elementus.EID;

public class EConfig {
    public static final ForgeConfigSpec.Builder CONFIG_CLIENT = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.Builder CONFIG_COMMON = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final ClientConfig CLIENT = new ClientConfig(CONFIG_CLIENT);
    public static final CommonConfig COMMON = new CommonConfig(CONFIG_COMMON);
//    public static final ClientConfig CLIENT;
//    public static final CommonConfig COMMON;

    public static String PREFIX = "config." + EID + ".";

    static {
        CLIENT_SPEC = CONFIG_CLIENT.build();
        COMMON_SPEC = CONFIG_COMMON.build();
//        {
//            final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
//            COMMON = specPair.getLeft();
//            COMMON_SPEC = specPair.getRight();
//
//            final Pair<ClientConfig, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
//            CLIENT = clientSpecPair.getLeft();
//            CLIENT_SPEC = clientSpecPair.getRight();
//        }
    }

    public static void load(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == COMMON_SPEC) CommonConfig.reload(COMMON);
    }
    public static void reload(ModConfigEvent.Reloading event) {
        if (event.getConfig().getSpec() == COMMON_SPEC) CommonConfig.reload(COMMON);
    }
}
