package net.nokunami.elementus;

import net.minecraftforge.fml.ModList;

public class ModChecker {
    public static boolean tab = false;
    public static String farmersDelightID = "farmersdelight";
    public static String piercingPaxelsID = "piercingpaxels";
    public static String nethersDelightID = "nethersdelight";
    public static String ironsSpellbooksID = "irons_spellbooks";
    public static String aetherID = "aether";
    public static String simplySwordsID = "simplyswords";
    public static String sniffsWeaponsID = "sniffsweapons";
    public static String advancedNetheriteID = "advancednetherite";
    public static String epicSamuraiID = "epicsamurai";
    public static String samuraiDynastyID = "samurai_dynasty";
    public static String projectEID = "projecte";
    public static String twigsID = "twigs";
    //    public static String refurbishedFurnitureID = "refurbished_furniture";
    public static String cataclysmID = "cataclysm";
    public static String witherStormModID = "witherstormmod";
    public static String vanillaClawsID = "vanilla_claws";
    public static String archeryExpID = "archeryexp";
    public static String createID = "create";
    public static String betterCombatID = "bettercombat";

    public static boolean farmersDelight = ModList.get().isLoaded(farmersDelightID);
    public static boolean piercingPaxels = ModList.get().isLoaded(piercingPaxelsID);
    public static boolean nethersDelight = ModList.get().isLoaded(nethersDelightID);
    public static boolean ironsSpellbooks = ModList.get().isLoaded(ironsSpellbooksID);
    public static boolean aether = ModList.get().isLoaded(aetherID);
    public static boolean simplySwords = ModList.get().isLoaded(simplySwordsID);
    public static boolean sniffsWeapons = ModList.get().isLoaded(sniffsWeaponsID);
    public static boolean advancedNetherite = ModList.get().isLoaded(advancedNetheriteID);
    public static boolean samuraiDynasty = ModList.get().isLoaded(epicSamuraiID) | ModList.get().isLoaded(samuraiDynastyID);
    public static boolean projectE = ModList.get().isLoaded(projectEID);
    public static boolean twigs = ModList.get().isLoaded(twigsID);
    //    public static boolean refurbishedFurniture = ModList.get().isLoaded("refurbished_furniture");
    public static boolean cataclysm = ModList.get().isLoaded(cataclysmID);
    public static boolean witherStormMod = ModList.get().isLoaded(witherStormModID);
    public static boolean vanillaClaws = ModList.get().isLoaded(vanillaClawsID);
    public static boolean archeryExp = ModList.get().isLoaded(archeryExpID);
    public static boolean create = ModList.get().isLoaded(createID);
    public static boolean betterCombat = ModList.get().isLoaded(betterCombatID);

    public static boolean integrationTab() {
        var thisTab = tab;
        if ((thisTab = farmersDelight) ||
                (thisTab = piercingPaxels) ||
                (thisTab = nethersDelight) ||
                (thisTab = ironsSpellbooks) ||
                (thisTab = aether) ||
                (thisTab = simplySwords) ||
                (thisTab = sniffsWeapons) ||
                (thisTab = advancedNetherite) ||
                (thisTab = samuraiDynasty) ||
                (thisTab = twigs) ||
                (thisTab = witherStormMod) ||
                (thisTab = vanillaClaws));
        return thisTab;
    }
}
