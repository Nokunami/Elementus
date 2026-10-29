package net.nokunami.elementus;

import static net.nokunami.elementus.Elementus.EID;

public class EIdentifier {
//    public static Component
    public static class Description {
        public static String golemUpgradeReinforce = itemDesc("golem_upgrade.reinforced_plating");
        public static String golemUpgradeEquip = itemDesc("golem_upgrade.modifier_equip");
        public static String golemUpgrades = itemDesc("golem_upgrade.upgrades");
        public static String golemUpgradeSolidStance = itemDesc("golem_upgrade.pushable");
        public static String golemUpgradeFastAttack = itemDesc("golem_upgrade.fast_attack");

        public static String diarkritePickaxeBonus = itemDesc("diarkrite_pickaxe.desc");

        public static String catalystCoreDesc = itemDesc("catalyst_core.desc");
        public static String catalystCoreFallbackDesc = itemDesc("catalyst_core.fallback_desc");
        public static String catalystElytraEquip = itemDesc("catalyst_chestplate.elytra_equipped");
        public static String catalystTrimOff = itemDesc("catalyst_chestplate.trim_diabled");
        public static String catalystTrimOn = itemDesc("catalyst_chestplate.trim_enabled");

        public static String catalystCoreShowMore = itemDesc("catalyst_chestplate.show_more");
        public static String catalystLegacyCore = itemDesc("catalyst_chestplate.core_equipped_legacy");
        public static String catalystLegacyElytra = itemDesc("catalyst_chestplate.elytra_equipped_legacy");

        public static String chargeBladeDesc = itemDesc("charge_item.charge_desc");
        public static String chargeBladeFriendlyFire = itemDesc("charge_item.friendly_fire_desc");

        public static String BORDesc = itemDesc("diarkrite_charge_blade.resonance_charge_desc");
        public static String BORCursedDesc = itemDesc("diarkrite_charge_blade.cursed_damage_desc");
        public static String BORDamageDesc = itemDesc("diarkrite_charge_blade.damage_desc");
        public static String BORRatioDesc = itemDesc("diarkrite_charge_blade.damage_ratio_shift_desc");

        public static String movcadiaToolDesc = itemDesc("movcadia_tool.desc");
        public static String repairOnKillDesc = itemDesc("repairs_on_kill.desc");
    }

    public static String itemDesc(String s) { return "item." + EID + "." + s; }
}