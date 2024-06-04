package net.nokunami.elementus.common.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.nokunami.elementus.Elementus;

@Config(name = Elementus.MODID +"_simplyswords_config")
@Config.Gui.Background("cloth-config2:transparent")
public class ConfigWrapper extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("weapon_attributes")
    @ConfigEntry.Gui.TransitiveObject
    public WeaponAttributesConfig weapon_attributes = new WeaponAttributesConfig();
}