package net.nokunami.elementus.common.config.simplyswords;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.nokunami.elementus.Elementus;

@Config(name = Elementus.MODID +"_simplyswords_config")
@Config.Gui.Background("cloth-config2:transparent")
public class GeneralWrapper extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("general")
    @ConfigEntry.Gui.TransitiveObject
    public ElementusSimplySwords weaponAttributes = new ElementusSimplySwords();
}