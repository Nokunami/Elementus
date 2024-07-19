package net.nokunami.elementus;

import net.minecraftforge.fml.ModList;

public class ModChecker {
    public static boolean tab = false;

    public static boolean integrationTab() {
        var thisTab = tab;
        if ((thisTab = ModList.get().isLoaded("farmersdelight")) ||
                (thisTab = ModList.get().isLoaded("piercingpaxels")) ||
                (thisTab = ModList.get().isLoaded("nethersdelight")) ||
                (thisTab = ModList.get().isLoaded("irons_spellbooks")) ||
                (thisTab = ModList.get().isLoaded("aether")) ||
                (thisTab = ModList.get().isLoaded("simplyswords")) ||
                (thisTab = ModList.get().isLoaded("sniffsweapons")) ||
                (thisTab = ModList.get().isLoaded("advancednetherite")));
        return thisTab;
    }

    public static boolean farmersdelight() {
        return ModList.get().isLoaded("farmersdelight");
    }

    public static boolean piercingpaxels() {
        return ModList.get().isLoaded("piercingpaxels");
    }

    public static boolean nethersdelight() {
        return ModList.get().isLoaded("nethersdelight");
    }

    public static boolean irons_spellbooks() {
        return ModList.get().isLoaded("irons_spellbooks");
    }

    public static boolean aether() {
        return ModList.get().isLoaded("aether");
    }

    public static boolean simplyswords() {
        return ModList.get().isLoaded("simplyswords");
    }

    public static boolean sniffsweapons() {
        return ModList.get().isLoaded("sniffsweapons");
    }

    public static boolean advancednetherite() {
        return ModList.get().isLoaded("advancednetherite");
    }

    public static boolean epicsamurai() {
        return ModList.get().isLoaded("epicsamurai");
    }

    public static boolean projecte() {
        return ModList.get().isLoaded("projecte");
    }

    public static boolean fireproofboats() {
        return ModList.get().isLoaded("fireproofboats");
    }

    public static boolean twigs() {
        return ModList.get().isLoaded("twigs");
    }

    public static boolean refurbished_furniture() {
        return ModList.get().isLoaded("refurbished_furniture");
    }
}
