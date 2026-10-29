package net.nokunami.elementus.config;

import me.fzzyhmstrs.fzzy_config.config.Config;
import net.nokunami.elementus.config.preset.EArmorConfig;
import net.nokunami.elementus.config.preset.LeaviPackConfig;

import static net.nokunami.elementus.Elementus.modLoc;

public class ArmorSetConfig extends Config {

    public ArmorSetConfig() { super(modLoc("armor")); }

    public EArmorConfig astalite = new EArmorConfig(3, 8, 6, 3, 10, 24, 0, 0, 0, 0);
    public EArmorConfig diarkrite = new EArmorConfig(3, 8, 6, 3, 18, 38, 4, 0.2, 0, -0.04);
    public EArmorConfig anthektite = new EArmorConfig(3, 8, 6, 3, 15, 35, 2, 0.05, 0.1, 0);
    public LeaviPackConfig leavi_pack = new LeaviPackConfig(8, 15, 30, 2, 0.05, 0.1, 0);
//    public CatalystArmorConfig catalyst = new CatalystArmorConfig(10, 15, 35, 2, 0.05, 0.1, 0);
}