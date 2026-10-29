package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import net.minecraft.world.item.Tier;
import net.nokunami.elementus.config.preset.EToolSetConfig;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.api.util.EItemUtil.*;

public class ToolConfig extends Config {
    public EToolSetConfig astalite = new EToolSetConfig(3, -2.4, 1.5, -3, 1, -2.8, 5, -3.1, -3, 0, 457, 0, 457);
    public EToolSetConfig diarkrite = new EToolSetConfig(3, -2.4, 1.5, -3, 1, -2.8, 6, -3.1, -6, 0, 843, 3, 843);
    public EToolSetConfig anthektite = new EToolSetConfig(3, -2.4, 1.5, -3, 1, -2.8, 5, -3.1, -3, 0, 598, 0, 598);
    public EToolSetConfig movcadia = new EToolSetConfig(3, -2.4, 1.5, -3, 1, -2.8, 7, -3.1, -1, 0, 457, 0, 457);

    public ToolConfig() { super(modLoc("tools_config")); }

    public EToolSetConfig get(Tier tier) {
        if (isDiarkrite(tier)) return diarkrite;
        if (isAnthektite(tier)) return anthektite;
        if (isMovcadia(tier)) return movcadia;
        return astalite;
    }
    public int getShield(Tier tier) {
        if (isDiarkrite(tier)) return diarkrite.shieldDurability;
        if (isAnthektite(tier)) return anthektite.shieldDurability;
        if (isMovcadia(tier)) return movcadia.shieldDurability;
        return astalite.shieldDurability;
    }
}