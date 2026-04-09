package net.nokunami.elementus.common.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.nokunami.elementus.client.gui.overlay.CatalystAbilityOverlay;

import static net.nokunami.elementus.common.config.EConfig.PREFIX;

public class ClientConfig {
    public final ForgeConfigSpec.BooleanValue lavaRendererType;
    public final ForgeConfigSpec.EnumValue<CatalystAbilityOverlay.Layout> abilityIconLayout;
    public final ForgeConfigSpec.IntValue abilityIconPadding;
    public final ForgeConfigSpec.EnumValue<CatalystAbilityOverlay.Anchor> abilityIconAnchor;
    public final ForgeConfigSpec.IntValue abilityXPadding;
    public final ForgeConfigSpec.IntValue abilityYPadding;

    public ClientConfig(ForgeConfigSpec.Builder builder) {
//        builder.push("Client");
//        builder.pop();
        lavaRendererType = builder.translation(PREFIX + "config.elementus.lava_renderer")
                .define("lava_renderer", true);
        abilityIconLayout = builder.translation(PREFIX + "ability_icon_layout")
                .defineEnum("ability_icon_layout", CatalystAbilityOverlay.Layout.VerticalBottom);
        abilityIconPadding = builder.translation(PREFIX + "ability_icon_padding")
                .defineInRange("ability_icon_padding", 0, 0, 60);
        abilityIconAnchor = builder.translation(PREFIX + "ability_icon_anchor")
                .defineEnum("ability_icon_anchor", CatalystAbilityOverlay.Anchor.Bottom);
        abilityXPadding = builder.translation(PREFIX + "ability_x_padding")
                .defineInRange("ability_x_padding", 109, Integer.MIN_VALUE, Integer.MAX_VALUE);
        abilityYPadding = builder.translation(PREFIX + "ability_y_padding")
                .defineInRange("ability_y_padding", -18, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
