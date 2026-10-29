package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.api.ConfigApiJava;
import me.fzzyhmstrs.fzzy_config.api.RegisterType;
import net.minecraft.world.item.Tier;

public class EConfig {

    public static void init() { }

    public static final ClientConfig client = ConfigApiJava.registerAndLoadConfig(ClientConfig::new, RegisterType.CLIENT);
    public static ClientConfig.overlayConfig overlayConfig = client.overlayConfig;

    public static final TierConfig tierConfig = ConfigApiJava.registerAndLoadConfig(TierConfig::new);
    public static final ToolConfig toolConfig = ConfigApiJava.registerAndLoadConfig(ToolConfig::new);
    public static final ArmorSetConfig armorConfig = ConfigApiJava.registerAndLoadConfig(ArmorSetConfig::new);
    public static final EnchantmentConfig enchantmentConfig = ConfigApiJava.registerAndLoadConfig(EnchantmentConfig::new);
    public static final UniqueItemsConfig uniqueItems = ConfigApiJava.registerAndLoadConfig(UniqueItemsConfig::new);
    public static final CatalystArmorConfig catalystConfig = ConfigApiJava.registerAndLoadConfig(CatalystArmorConfig::new);

    public static final EntityConfig entityConfig = ConfigApiJava.registerAndLoadConfig(EntityConfig::new);
    public static final MobEffectConfig effectConfig = ConfigApiJava.registerAndLoadConfig(MobEffectConfig::new);

    public static double getMaterialSpeed(Tier tier) { return tierConfig.get(tier).additionalMiningSpeed(); }

    public static UniqueItemsConfig.bladeOfResonance BOR = uniqueItems.bladeOfResonance;
    public static UniqueItemsConfig.bladeOfSurgingWinds BOSW = uniqueItems.bladeOfSurgingWinds;
}