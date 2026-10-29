package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import net.minecraft.world.item.Tier;
import net.nokunami.elementus.config.preset.ETierConfig;
import net.nokunami.elementus.config.preset.IConfigTier;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.api.util.EItemUtil.*;

public class TierConfig extends Config {
    public ETierConfig astalite = new ETierConfig(3, 756, 7, 3, 15, -0.2, 0);
    public ETierConfig diarkrite = new ETierConfig(4, 2546, 7, 6, 15, -0.3, 10);
    public ETierConfig anthektite = new ETierConfig(4, 1946, 12, 3, 15, 0.5, 0);
    public ETierConfig movcadia = new ETierConfig(3, 300, 8, 1, 20, 0, 0);

    public TierConfig() { super(modLoc("tier")); }

    public IConfigTier get(Tier tier) {
        if (isDiarkrite(tier)) return diarkrite;
        if (isAnthektite(tier)) return anthektite;
        if (isMovcadia(tier)) return movcadia;
        return astalite;
    }
}