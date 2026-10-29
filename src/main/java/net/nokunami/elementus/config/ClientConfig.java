package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.config.ConfigSection;
import net.nokunami.elementus.client.gui.overlay.CatalystAbilityOverlay;

import static net.nokunami.elementus.Elementus.modLoc;

public class ClientConfig extends Config {

    public ClientConfig() { super(modLoc("overlay_config")); }

    public overlayConfig overlayConfig = new overlayConfig();

    public static class overlayConfig extends ConfigSection {
        public boolean lavaRendererType = true;
        public CatalystAbilityOverlay.Layout abilityIconLayout = CatalystAbilityOverlay.Layout.VerticalBottom;
        public int abilityIconPadding = 8;
        public CatalystAbilityOverlay.Anchor abilityIconAnchor = CatalystAbilityOverlay.Anchor.Bottom;
        public int abilityXPadding = 109;
        public int abilityYPadding = -18;
    }
}
