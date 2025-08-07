package net.nokunami.elementus;

import net.minecraftforge.fml.ModList;

public class ModChecker {
    public static String ironsSpellbooksID = "irons_spellbooks";
    public static String projectEID = "projecte";
    public static String twigsID = "twigs";
    public static String cataclysmID = "cataclysm";
    public static String witherStormModID = "witherstormmod";
    public static String archeryExpID = "archeryexp";
    public static String createID = "create";
    public static String betterCombatID = "bettercombat";

    public static boolean ironsSpellbooks = ModList.get().isLoaded(ironsSpellbooksID);
    public static boolean projectE = ModList.get().isLoaded(projectEID);
    public static boolean twigs = ModList.get().isLoaded(twigsID);
    public static boolean cataclysm = ModList.get().isLoaded(cataclysmID);
    public static boolean witherStormMod = ModList.get().isLoaded(witherStormModID);
    public static boolean archeryExp = ModList.get().isLoaded(archeryExpID);
    public static boolean create = ModList.get().isLoaded(createID);
    public static boolean betterCombat = ModList.get().isLoaded(betterCombatID);
}
