package net.nokunami.elementus.datagen.generators;

import net.minecraft.client.KeyMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.LanguageProvider;
import net.nokunami.elementus.client.EKeyMap;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.registry.*;
import net.nokunami.elementus.common.registry.tempCompat.CompatCoreRegistry.*;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.datagen.generators.ESoundGen.getSub;

public class ELangGen extends LanguageProvider {
    public static String itemPrefix = "item." + EID + ".";
    public static String enchantPrefix = "enchantment." + EID + ".";

    public ELangGen(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        itemTranslations();
        itemDescriptions();
        blockTL();
        guiTL();
        enchantmentTL();
        specialEnchantmentDescTL();
        advancements();
        miscTL();
        effectsTL();
        entityTL();
        subtitlesTL();
        damageTL();
        configTL();
        catalystTL();
        keyMap();
    }

    void itemTranslations() {
        addItem(EItems.CRUDE_STEEL, "Crude Astalite");
        addItem(EItems.ASTALITE_SCRAP, "Astalite Scrap");
        addItem(EItems.ASTALITE_INGOT, "Astalite Ingot");
        addItem(EItems.ASTALITE_NUGGET, "Astalite Nugget");
        addItem(EItems.ATELIS_SCRAP, "Atelis Scrap");
        addItem(EItems.DIARKRITE_INGOT, "Diarkrite Ingot");
        addItem(EItems.ANTHEKTITE_INGOT, "Anthektite Ingot");

        addItem(EItems.MOVCADIA_BERRIES, "Movcadia Berries");
        addItem(EItems.GLISTERING_MOVCADIA_BERRIES, "Glistering Movcadia Berries");

        addItem(EItems.MOVCADIA_ESSENCE, "Movcadia Essence");

        addItem(EItems.ASTALITE_GOLEM_SPAWN_EGG, "Astalite Golem Spawn Egg");
        addItem(EItems.STEEL_GOLEM_SPAWN_EGG, "Steel Golem Spawn Egg");

        addItem(EItems.ASTALITE_SWORD, "Astalite Sword");
        addItem(EItems.ASTALITE_SHOVEL, "Astalite Shovel");
        addItem(EItems.ASTALITE_PICKAXE, "Astalite Pickaxe");
        addItem(EItems.ASTALITE_AXE, "Astalite Axe");
        addItem(EItems.ASTALITE_HOE, "Astalite Hoe");
        addItem(EItems.DIARKRITE_SWORD, "Diarkrite Sword");
        addItem(EItems.DIARKRITE_SHOVEL, "Diarkrite Shovel");
        addItem(EItems.DIARKRITE_PICKAXE, "Diarkrite Pickaxe");
        addItem(EItems.DIARKRITE_AXE, "Diarkrite Axe");
        addItem(EItems.DIARKRITE_HOE, "Diarkrite Hoe");
        addItem(EItems.ANTHEKTITE_SWORD, "Anthektite Sword");
        addItem(EItems.ANTHEKTITE_SHOVEL, "Anthektite Shovel");
        addItem(EItems.ANTHEKTITE_PICKAXE, "Anthektite Pickaxe");
        addItem(EItems.ANTHEKTITE_AXE, "Anthektite Axe");
        addItem(EItems.ANTHEKTITE_HOE, "Anthektite Hoe");
        addItem(EItems.MOVCADIA_SWORD, "Movcadia Sword");
        addItem(EItems.MOVCADIA_SHOVEL, "Movcadia Shovel");
        addItem(EItems.MOVCADIA_PICKAXE, "Movcadia Pickaxe");
        addItem(EItems.MOVCADIA_AXE, "Movcadia Axe");
        addItem(EItems.MOVCADIA_HOE, "Movcadia Hoe");

        addItem(EItems.DIARKRITE_CHARGE_BLADE, "Blade of Resonance");
        addItem(EItems.ANTHEKTITE_CHARGE_BLADE, "Blade of Wind Charging");
        addItem(EItems.WRATH_TRIDENT, "Wrath of The Sea");

        addItem(EItems.ASTALITE_SHIELD, "Astalite Shield");
        addItem(EItems.DIARKRITE_SHIELD, "Diarkrite Shield");
        addItem(EItems.ANTHEKTITE_SHIELD, "Anthektite Shield");

        addItem(EItems.ASTALITE_BOW, "Astalite Bow");
        addItem(EItems.DIARKRITE_BOW, "Diarkrite Bow");
        addItem(EItems.ANTHEKTITE_BOW, "Anthektite Bow");

        addItem(EItems.ASTALITE_HELMET, "Astalite Helmet");
        addItem(EItems.ASTALITE_CHESTPLATE, "Astalite Chestplate");
        addItem(EItems.ASTALITE_LEGGINGS, "Astalite Leggings");
        addItem(EItems.ASTALITE_BOOTS, "Astalite Boots");
        addItem(EItems.DIARKRITE_HELMET, "Diarkrite Helmet");
        addItem(EItems.DIARKRITE_CHESTPLATE, "Diarkrite Chestplate");
        addItem(EItems.DIARKRITE_LEGGINGS, "Diarkrite Leggings");
        addItem(EItems.DIARKRITE_BOOTS, "Diarkrite Boots");
        addItem(EItems.ANTHEKTITE_HELMET, "Anthektite Helmet");
        addItem(EItems.ANTHEKTITE_CHESTPLATE, "Anthektite Chestplate");
        addItem(EItems.ANTHEKTITE_LEGGINGS, "Anthektite Leggings");
        addItem(EItems.ANTHEKTITE_BOOTS, "Anthektite Boots");

        addItem(EItems.CATALYST_CHESTPLATE, "Catalyst Chestplate");
//        addItem(EItems.TEST_CATALYST_CHESTPLATE, "Test Catalyst Chestplate");

        addItem(EItems.REINFORCED_PLATING_GOLEM_UPGRADE, "Golem Upgrade");

        addItem(EItems.ATELIS_UPGRADE_SMITHING_TEMPLATE, "Smithing Template");
        addItem(EItems.WEAPON_FRAGMENT, "Weapon Fragment");
    }
    void itemDescriptions() {
        add(itemPrefix + "golem_upgrade.reinforced_plating", "Reinforced Plating");
//        add(itemPrefix + "golem_upgrade.damage_upgrade", "Test Damage Golem ");
        add(itemPrefix + "golem_upgrade.modifier_equip", "When on Golem: ");
        add(itemPrefix + "golem_upgrade.upgrades", "Upgrades: ");
        add(itemPrefix + "golem_upgrade.pushable", "Solid Feet");
        add(itemPrefix + "golem_upgrade.fast_attack", "Overclocked Arms");

        add(itemPrefix + "diarkrite_pickaxe.desc", "Mines hard blocks faster.");
        add(itemPrefix + "catalyst_core.desc", "Can be used as Catalyst Core.");

        add(itemPrefix + "catalyst_chestplate.elytra_equipped", "Elytra equipped.");
        add(itemPrefix + "catalyst_chestplate.trim_diabled", "Trim disabled.");
        add(itemPrefix + "catalyst_chestplate.trim_enabled", "Trim visible.");
        add(itemPrefix + "catalyst_chestplate.show_more", "Press shift for info.");
        add(itemPrefix + "catalyst_chestplate.core_equipped_legacy", "Catalyst Core NBT tag has change, please remove core!");
        add(itemPrefix + "catalyst_chestplate.elytra_equipped_legacy", "Elytra NBT tag has change, please remove elytra!");

        add(itemPrefix + "charge_item.charge_desc", "Charge: %s/%s");
        add(itemPrefix + "charge_item.friendly_fire_desc", "Friendly Fire: %s");
        add(itemPrefix + "diarkrite_charge_blade.resonance_charge_desc", "Resonance Charge: %s");
        add(itemPrefix + "diarkrite_charge_blade.cursed_damage_desc", "Current Charge: %s + 25% of Max Health");
        add(itemPrefix + "diarkrite_charge_blade.damage_desc", "Damage: ");
        add(itemPrefix + "diarkrite_charge_blade.damage_ratio_shift_desc", "Current Burst Damage: %s");

        add(itemPrefix + "movcadia_tool.desc", "Tool degrades with use.");
    }
    void blockTL() {
        addBlock(EBlocks.ASTALITE_BLOCK, "Block of Astalite");
        addBlock(EBlocks.DIARKRITE_BLOCK, "Block of Diarkrite");
        addBlock(EBlocks.ANTHEKTITE_BLOCK, "Block of Anthektite");
        addBlock(EBlocks.REMNANT, "Remnant");

        addBlock(EBlocks.STEEL_BARS, "Astalite Bars");
        addBlock(EBlocks.STEEL_TILES, "Astalite Tiles");
        addBlock(EBlocks.STEEL_TILE_STAIR, "Astalite Tile Stair");
        addBlock(EBlocks.STEEL_TILE_SLAB, "Astalite Tile Slab");

        addBlock(EBlocks.MOVCADIA_SAPLING, "Movcadia Sapling");

        addBlock(EBlocks.MOVCADIA_LOG, "Movcadia Log");
        addBlock(EBlocks.STRIPPED_MOVCADIA_LOG, "Stripped Movcadia Log");
        addBlock(EBlocks.MOVCADIA_WOOD, "Movcadia Wood");
        addBlock(EBlocks.STRIPPED_MOVCADIA_WOOD, "Stripped Movcadia Wood");

        addBlock(EBlocks.MOVCADIA_LEAVES, "Movcadia Leaves");
        addBlock(EBlocks.FLOWERING_MOVCADIA_LEAVES, "Flowering Movcadia Leaves");

        addBlock(EBlocks.MOVCADIA_ROOTED_DIRT, "Movcadia Rooted Dirt");
        addBlock(EBlocks.MOVCADIA_ROOTED_STONE, "Movcadia Rooted Stone");
        addBlock(EBlocks.MOVCADIA_ROOTED_DEEPSLATE, "Movcadia Rooted Deepslate");

        addBlock(EBlocks.MOVCADIA_PLANKS, "Movcadia Plank");
        addBlock(EBlocks.MOVCADIA_STAIRS, "Movcadia Stairs");
        addBlock(EBlocks.MOVCADIA_SLAB, "Movcadia Slab");

        addBlock(EBlocks.MOVCADIA_DOOR, "Movcadia Door");
        addBlock(EBlocks.MOVCADIA_TRAPDOOR, "Movcadia Trapdoor");

        addBlock(EBlocks.MOVCADIA_FENCE, "Movcadia Fence");
        addBlock(EBlocks.MOVCADIA_FENCE_GATE, "Movcadia Fence Gate");

        addBlock(EBlocks.MOVCADIA_PRESSURE_PLATE, "Movcadia Pressure Plate");
        addBlock(EBlocks.MOVCADIA_BUTTON, "Movcadia Button");

        addBlock(EBlocks.MOVCADIA_SIGN, "Movcadia Sign");
        addBlock(EBlocks.MOVCADIA_HANGING_SIGN, "Movcadia Hanging Sign");
        addBlock(EBlocks.STURDY_MOVCADIA_SIGN, "Sturdy Movcadia Sign");

        addBlock(EBlocks.MOVCADIA_CHEST, "Movcadia Chest");
    }
    void guiTL() {
        add("container.steel_golem.missing_upgrade_tooltip", "Add golem upgrade");
        add("container.steel_golem.missing_leaves_tooltip", "Add camo leaves");
        add("container.steel_golem.missing_carpet_tooltip", "Add carpet swag");

        add("upgrade.elementus.atelis_upgrade", "Item Transmutation");
        add("item.elementus.smithing_template.atelis_upgrade.applies_to", "Reactive Items");
        add("item.elementus.smithing_template.atelis_upgrade.ingredient", "Atelis Alloy");
        add("item.elementus.smithing_template.weapon_fragment.applies_to", "Transmutated Items");
        add("item.elementus.smithing_template.weapon_fragment.ingredient", "\"Catalyst\"");
        add("itemGroup.elementus", "Elementus Items");
    }
    void enchantmentTL() {
        enchantTL(EEnchantments.ARCANE_SHARPNESS, "Arcane Sharpness", "Bonus damage base on item's enchantability");
        enchantTL(EEnchantments.SACRIFICE_CURSE, "Sacrifice", "Adds additional damage to Charge blade but takes health to fire");
        enchantTL(EEnchantments.RESONANCE, "Resonance", "Parrying a attack will send a delayed \"Resonance\" damage to the attack as well as accumulate an additional 150% charge as \"Resonance charge\" that slowly replenishes charge.");
        enchantTL(EEnchantments.CONDENSED_BURST, "Condensed Burst", "Condenses burst into a beam.");
        enchantTL(EEnchantments.RUSH, "Blade Rush", "Rushes forward and slash any entity caught in teh dash.");
        enchantTL(EEnchantments.CHARGE_STACKING, "Charge Stacking", "Adds additional charge stacks.");
        enchantTL(EEnchantments.PULSE_BURST, "Pulse Burst", "Burst from Charge blade turns into a pulsing projectile.");
    }
    void specialEnchantmentDescTL() {
        enchantSpecialDescTL(EEnchantments.SACRIFICE_CURSE, "bonus_desc", "+%s%% Damage from %s");
        enchantSpecialDescTL(EEnchantments.SACRIFICE_CURSE, "penalty_desc", "-%s%% Health from %s");
        enchantSpecialDescTL(EEnchantments.CONDENSED_BURST, "penalty_desc", "+%s%% Charge Penalty from %s");
        enchantSpecialDescTL(EEnchantments.CHARGE_STACKING, "bonus_1_desc", "+%s%% Bonus Charge from %s");
        enchantSpecialDescTL(EEnchantments.CHARGE_STACKING, "bonus_2_desc", "-%s%% Cooldown from %s");
    }
    void advancements() {
        add("advancements.story.smelt_steel.title", "But Steel's heavier than feathers!");
        add("advancements.story.smelt_steel.description", "Obtain Steel Ingot.");
        add("advancements.story.recycle_steel.title", "Reducing my carbon footprint!");
        add("advancements.story.recycle_steel.description", "Obtain steel scrap from smelting steel equipment or from a chest.");
        add("advancements.story.obtain_remnant.title", "I remember throwing 8 of those away");
        add("advancements.story.obtain_remnant.description", "Obtain Remnant, an ancient metallic material embedded in the depths of the Overworld.");
        add("advancements.story.obtain_astalite.title", "It's... warm?");
        add("advancements.story.obtain_astalite.description", "Obtain Astalite from... why is it still warm?");
        add("advancements.story.astalite_armor.title", "Kinda cosy");
        add("advancements.story.astalite_armor.description", "Get a full set of Astalite... it's warm.");
        add("advancements.story.mine_astalite.title", "Fiery origin");
        add("advancements.story.mine_astalite.description", "Discover the origin of astalite, so that's why it's warm.");
    }
    void miscTL() {
        add("trim_material.elementus.steel", "Astalite Material");
        add("trim_material.elementus.diarkrite", "Diarkrite Material");
        add("trim_material.elementus.anthektite", "Anthektite Material");

        add("tag.item.elementus.steel_recyclable", "Astalite Recyclable");
        add("tag.item.elementus.movcadia_logs", "Movcadia Logs");
        add("tag.item.elementus.catalyst.items", "Catalyst Cores");
        add("entity.elementus.steel_golem_down", "Steel Golem powered down, Current Chassis Health: %s");
    }
    void effectsTL() {
        addEffect(EMobEffects.BEACON_POWER, "Beacon Power");
        addEffect(EMobEffects.TOTEM_COOLDOWN, "Catalyst Totem Exhaustion");
        addEffect(EMobEffects.WITHERED_BEACON_POWER, "Withered Beacon Power");
        addEffect(EMobEffects.SACRIFICE_PENALTY, "Sacrifice Penalty");
    }
    void entityTL() {
        addEntityType(EEntityTypes.MOVCADIA_BOAT, "Movcadia Boat");
        addEntityType(EEntityTypes.MOVCADIA_CHEST_BOAT, "Movcadia Boat with Chest");
        addEntityType(EEntityTypes.OLD_STEEL_GOLEM, "Steel Golem");
        addEntityType(EEntityTypes.ASTALITE_GOLEM_CARRIER, "Astalite Golem");
        addEntityType(EEntityTypes.ASTALITE_GOLEM_LONGARM, "Astalite Golem Longarm");
//        addEntityType(ModEntityType.DIARKRITE_GOLEM, "Diarkrite Golem");
//        addEntityType(ModEntityType.ANTHEKTITE_GOLEM, "Anthektite Golem");
        addEntityType(EEntityTypes.ANTHEKTITE_SLASH, "Wind Slash");
        addEntityType(EEntityTypes.PULSE_BURST, "Pulse Burst");
        addEntityType(EEntityTypes.RUSH_PROJECTILE, "Rush");
        addEntityType(EEntityTypes.SWORD_DANCE_SLASH, "Slash");
        addEntityType(EEntityTypes.WRATH_TRIDENT, "Wrath of The Sea");
    }
    void subtitlesTL() {
        add(getSub(ESounds.CATALYST_ARMOR_ACTIVATE), "Catalyst armor activates");
        add(getSub(ESounds.CATALYST_ARMOR_DEACTIVATE), "Catalyst armor deactivates");

        add(getSub(ESounds.DIARKRITE_SHIELD_BLOCK), "Heavy shield block");
        add(getSub(ESounds.ANTHEKTITE_SHIELD_BLOCK), "Shield block");

        add(getSub(ESounds.CHARGE_BLADE_BLOCK), "Sword impact");
        add(getSub(ESounds.CHARGE_BLADE_PARRY), "Sword parries");

        add(getSub(ESounds.BOR_BURST), "Sonic burst");
        add(getSub(ESounds.BOR_BURST_CURSED), "Sonic burst");
        add(getSub(ESounds.BOR_CONDENSED_BURST), "Condensed burst");
        add(getSub(ESounds.BOR_CONDENSED_BURST_CURSED), "Condensed burst");

        add(getSub(ESounds.BOSW_WIND_SLASH), "Wind slash");
        add(getSub(ESounds.BOSW_CLEAVE), "Cleave");
        add(getSub(ESounds.BOSW_RUSH), "Rush");

        add(getSub(ESounds.STEEL_GOLEM_REPAIR), "Golem repaired");
        add(getSub(ESounds.STEEL_GOLEM_DOWN), "Golem breaks down");
        add(getSub(ESounds.STEEL_GOLEM_DEATH), "Golem dies");
        add(getSub(ESounds.STEEL_GOLEM_REVIVE), "Golem rises");

        add(getSub(ESounds.STEEL_GOLEM_SADDLED), "Golem saddled");
        add(getSub(ESounds.STEEL_GOLEM_CHESTED), "Golem chested");
        add(getSub(ESounds.STEEL_GOLEM_ARMORED), "Golem armored");
        add(getSub(ESounds.STEEL_GOLEM_LEAVES_SWAG), "Golem camouflaged");
        add(getSub(ESounds.STEEL_GOLEM_CARPET_SWAG), "Golem dripped out");

        add(getSub(ESounds.ARMOR_EQUIP_DIARKRITE), "Diarkrite armor clangs");
        add(getSub(ESounds.ARMOR_EQUIP_ANTHEKTITE), "Anthektite armor clangs");
    }
    void damageTL() {
        add("death.attack.elementus.sacrificial", "%1$s sacrificed too much");
        add("death.attack.elementus.sacrificial.player", "%1$s was sacrificed while trying to escape %2$s");
    }
    void configTL() {
        addConfig("lava_renderer", "Replace Lava Renderer.");
        addConfig("diarkriteEfficiency.desc", "Diarkrite Pickaxe Efficiency.");
        addConfig("catalystArmorDurability.desc", "Catalyst Armor Durability.");
        addConfig("catalystTotemAbility.desc", "Catalyst Armor Totem Ability.");
        addConfig("arcaneSharpnessTreasure.desc", "Arcane Sharpness Treasure.");
        addConfig("arcaneSharpnessPercent", "Arcane Sharpness Damage Percent.");
        addConfig("arcaneSharpnessPercent.desc", "Item's enchantability * this.");
        addConfig("arcaneSharpnessIncompatibility", "Arcane Sharpness.");
        addConfig("arcaneSharpnessIncompatibility.desc", "Arcane Sharpness Enchantment Compatibility.");
    }
    void catalystTL() {
        add("catalyst_ability.elementus.cooldown", "%s is on cooldown");

        addCore(CatalystCoreRegistry.BEACON_POWER, "Beacon Power");
        addCoreDesc(CatalystCoreRegistry.BEACON_POWER, "Power of a Beacon, but mobile.");
        addCore(CatalystCoreRegistry.HEART_OF_THE_SEA, "Heart of the Sea");
        addCoreDesc(CatalystCoreRegistry.HEART_OF_THE_SEA, "Conduit Power on the go.");
        addCore(CatalystCoreRegistry.TOTEM_OF_UNDYING, "Totem of Undying");
        addCoreDesc(CatalystCoreRegistry.TOTEM_OF_UNDYING, "A second chance after death.");
        addCore(CatalystCoreRegistry.END_SHIFTER, "End Shifter");
        addCoreDesc(CatalystCoreRegistry.END_SHIFTER, "Your location in space shifts constantly.");
        addCore(CatalystCoreRegistry.WAYFINDER, "Wayfinder");
        addCoreDesc(CatalystCoreRegistry.WAYFINDER, "The lodestone.");

        if (cataclysm) {
            addCore(CataclysmCores.IGNITIUM, "Inferno Reflex");
            addCoreDesc(CataclysmCores.IGNITIUM, "A fraction of Ingis' power.");
            addCoreDesc(CataclysmCores.IGNITIUM, ".desc_1", "| Randomly sets attackers on fire and apply Blazing Brand.");
            addCoreDesc(CataclysmCores.IGNITIUM, ".desc_2", "| Immunity to Blazing Brand.");
            addCore(CataclysmCores.CURSIUM_WARRIOR, "Cursed Worrier");
            addCoreDesc(CataclysmCores.CURSIUM_WARRIOR, "Cursed with undeath.");
            addCoreDesc(CataclysmCores.CURSIUM_WARRIOR, ".desc_1", "| Revives upon death.");
            addCoreDesc(CataclysmCores.CURSIUM_WARRIOR, ".desc_2", "| Chance to dodge attacks, chances increase if it's a projectile.");
            addCore(CataclysmCores.ESSENCE_OF_THE_STORM, "Essence of The Storm");
            addCoreDesc(CataclysmCores.ESSENCE_OF_THE_STORM, "Work In Progress.");
        }

        if (ironsSpellbooks) {
            addCore(IronsSpellbooksCores.ISS_FIRE, "Fire Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_FIRE, "Arcane Imbuement of Flames.");
            addCore(IronsSpellbooksCores.ISS_ICE, "Ice Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_ICE, "Arcane Imbuement of Ice.");
            addCore(IronsSpellbooksCores.ISS_ENDER, "Ender Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_ENDER, "Arcane Imbuement of Ender.");
            addCore(IronsSpellbooksCores.ISS_LIGHTNING, "Lightning Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_LIGHTNING, "Arcane Imbuement of Lightning.");
            addCore(IronsSpellbooksCores.ISS_BLOOD, "Blood Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_BLOOD, "Arcane Imbuement of Blood.");
            addCore(IronsSpellbooksCores.ISS_HOLY, "Holy Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_HOLY, "Arcane Imbuement of Holy Light.");
            addCore(IronsSpellbooksCores.ISS_EVOCATION, "Evocation Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_EVOCATION, "Arcane Imbuement of Evocation.");
            addCore(IronsSpellbooksCores.ISS_NATURE, "Nature Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_NATURE, "Arcane Imbuement of Nature.");
            addCore(IronsSpellbooksCores.ISS_ARCANE, "Condensed Arcane Power");
            addCoreDesc(IronsSpellbooksCores.ISS_ARCANE, "Arcane Imbuement of Overflowing Mana.");
            addCore(IronsSpellbooksCores.ISS_COOLDOWN, "Arcane Recovery");
            addCoreDesc(IronsSpellbooksCores.ISS_COOLDOWN, "Arcane Imbuement of Recovery.");
            addCore(IronsSpellbooksCores.ISS_PROTECTION, "Arcane Protection");
            addCoreDesc(IronsSpellbooksCores.ISS_PROTECTION, "Arcane Imbuement of Protection.");
        }

        if (witherStormMod) {
            addCore(WitherstormModCores.WITHERED_BEACON_POWER, "Corrupted Beacon Power");
            addCoreDesc(WitherstormModCores.WITHERED_BEACON_POWER, "The shadow of The World Eater. (WIP)");
        }

        if (friendsandfoes) {
            addCore(FriendsAndFoesCores.FREEZE, "Totem of Freezing");
            addCoreDesc(FriendsAndFoesCores.FREEZE, "Freezing.");
            addCore(FriendsAndFoesCores.ILLUSION, "Totem of Illusion");
            addCoreDesc(FriendsAndFoesCores.ILLUSION, "Illusions.");
        }
    }

    void keyMap() {
        addKey(EKeyMap.CATALYST_ABILITY_KEY, "Catalyst Ability");
        addKey(EKeyMap.CATALYST_ABILITY_SWITCH_KEY, "Ability Selection");
    }

    public void enchantTL(Supplier<? extends Enchantment> key, String name, String desc) {
        addEnchantment(key, name);
        add(key.get().getDescriptionId() + ".desc", desc);
    }

    public void enchantSpecialDescTL(Supplier<? extends Enchantment> key, String id, String desc) {
        add(key.get().getDescriptionId() + "." + id, desc);
    }
    
    public void addSub(String id, String translation) {
        addCustom("subtitles", id, translation);
    }
    
    public void addConfig(String id, String translation) {
        addCustom("config", id, translation);
    }
    
    public void addCoreDesc(String id, String translation) {
        addCustom("catalyst_core", id, translation);
    }
    
    public void addCustom(String prefix, String id, String translation) {
        add(prefix + "." + EID + "." + id, translation);
    }

    public void addKey(KeyMapping key, String translation) {
        add(key.getName(), translation);
    }

    public void addCore(Supplier<CatalystCore> key, String name) {
        add(key.get().getDescriptionId(), name);
    }
    public void addCore(Supplier<CatalystCore> key, String name, boolean condition) {
        if (condition) addCore(key, name);
    }
    public void addCoreDesc(Supplier<CatalystCore> key, String name) {
        add(key.get().getDescriptionId() + ".desc", name);
    }
    public void addCoreDesc(Supplier<CatalystCore> key, String suffix, String name) {
        add(key.get().getDescriptionId() + suffix, name);
    }
}
