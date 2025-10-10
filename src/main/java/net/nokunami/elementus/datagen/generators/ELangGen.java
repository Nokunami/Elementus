package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.LanguageProvider;
import net.nokunami.elementus.common.registry.EBlocks;
import net.nokunami.elementus.common.registry.EEnchantments;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.registry.ModEntityType;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.MODID;

public class ELangGen extends LanguageProvider {
    public static String itemPrefix = "item." + MODID + ".";

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
        advancements();
        miscTL();
        effectsTL();
        entityTL();
        subtitlesTL();
        damageTL();
        configTL();
        catalystTL();
    }

    void itemTranslations() {
        addItem(EItems.CRUDE_STEEL, "Crude Steel");
        addItem(EItems.STEEL_SCRAP, "Steel Scrap");
        addItem(EItems.STEEL_INGOT, "Steel Ingot");
        addItem(EItems.STEEL_NUGGET, "Steel Nugget");
        addItem(EItems.ATELIS_SCRAP, "Atelis Scrap");
        addItem(EItems.DIARKRITE_INGOT, "Diarkrite Ingot");
        addItem(EItems.ANTHEKTITE_INGOT, "Anthektite Ingot");

        addItem(EItems.MOVCADIA_BERRIES, "Movcadia Berries");
        addItem(EItems.GLISTERING_MOVCADIA_BERRIES, "Glistering Movcadia Berries");

        addItem(EItems.STEEL_GOLEM_SPAWN_EGG, "Steel Golem Spawn Egg");

        addItem(EItems.STEEL_SWORD, "Steel Sword");
        addItem(EItems.STEEL_SHOVEL, "Steel Shovel");
        addItem(EItems.STEEL_PICKAXE, "Steel Pickaxe");
        addItem(EItems.STEEL_AXE, "Steel Axe");
        addItem(EItems.STEEL_HOE, "Steel Hoe");
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

        addItem(EItems.DIARKRITE_CHARGE_BLADE, "Diarkrite Charge Blade");
        addItem(EItems.ANTHEKTITE_CHARGE_BLADE, "Anthektite Charge Blade");
        addItem(EItems.WRATH_TRIDENT, "Wrath of The Sea");

        addItem(EItems.STEEL_SHIELD, "Steel Shield");
        addItem(EItems.DIARKRITE_SHIELD, "Diarkrite Shield");
        addItem(EItems.ANTHEKTITE_SHIELD, "Anthektite Shield");

        addItem(EItems.STEEL_BOW, "Steel Bow");
        addItem(EItems.DIARKRITE_BOW, "Diarkrite Bow");
        addItem(EItems.ANTHEKTITE_BOW, "Anthektite Bow");

        addItem(EItems.STEEL_HELMET, "Steel Helmet");
        addItem(EItems.STEEL_CHESTPLATE, "Steel Chestplate");
        addItem(EItems.STEEL_LEGGINGS, "Steel Leggings");
        addItem(EItems.STEEL_BOOTS, "Steel Boots");
        addItem(EItems.DIARKRITE_HELMET, "Diarkrite Helmet");
        addItem(EItems.DIARKRITE_CHESTPLATE, "Diarkrite Chestplate");
        addItem(EItems.DIARKRITE_LEGGINGS, "Diarkrite Leggings");
        addItem(EItems.DIARKRITE_BOOTS, "Diarkrite Boots");
        addItem(EItems.ANTHEKTITE_HELMET, "Anthektite Helmet");
        addItem(EItems.ANTHEKTITE_CHESTPLATE, "Anthektite Chestplate");
        addItem(EItems.ANTHEKTITE_LEGGINGS, "Anthektite Leggings");
        addItem(EItems.ANTHEKTITE_BOOTS, "Anthektite Boots");

        addItem(EItems.CATALYST_CHESTPLATE, "Catalyst Chestplate");
        addItem(EItems.TEST_CATALYST_CHESTPLATE, "Test Catalyst Chestplate");

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

        add(itemPrefix + "catalyst_chestplate.elytra_equipped", "Elytra Equipped!");
        add(itemPrefix + "catalyst_chestplate.core_equipped_legacy", "Catalyst Core NBT tag has change, please remove core!");
        add(itemPrefix + "catalyst_chestplate.elytra_equipped_legacy", "Elytra NBT tag has change, please remove elytra!");

        add(itemPrefix + "charge_item.charge_desc", "Charge: %s/%s");
        add(itemPrefix + "charge_item.friendly_fire_desc", "Friendly Fire: %s");
        add(itemPrefix + "diarkrite_charge_blade.resonance_charge_desc", "Resonance Charge: %s");
        add(itemPrefix + "diarkrite_charge_blade.cursed_damage_desc", "Current Charge: %s + 25% of Max Health");
        add(itemPrefix + "diarkrite_charge_blade.damage_desc", "Damage: ");
        add(itemPrefix + "diarkrite_charge_blade.damage_ratio_shift_desc", "Current Burst Damage: %s");
        add(itemPrefix + "diarkrite_charge_blade.damage_bonus_sacrifice", "+%s%% Damage from ");
        add(itemPrefix + "diarkrite_charge_blade.self_sacrifice_damage", "-%s%% Health from ");
        add(itemPrefix + "diarkrite_charge_blade.charge_penalty_condensed_burst", "+%s%% Charge Penalty from ");
        add(itemPrefix + "diarkrite_charge_blade.multi_charge", "+%s%% Bonus Charge from ");
        add(itemPrefix + "movcadia_tool.desc", "Tool degrades with use.");
    }
    void blockTL() {
        addBlock(EBlocks.STEEL_BLOCK, "Block of Steel");
        addBlock(EBlocks.DIARKRITE_BLOCK, "Block of Diarkrite");
        addBlock(EBlocks.ANTHEKTITE_BLOCK, "Block of Anthektite");
        addBlock(EBlocks.REMNANT, "Remnant");

        addBlock(EBlocks.STEEL_BARS, "Steel Bars");
        addBlock(EBlocks.STEEL_TILES, "Steel Tiles");
        addBlock(EBlocks.STEEL_TILE_STAIR, "Steel Tile Stair");
        addBlock(EBlocks.STEEL_TILE_SLAB, "Steel Tile Slab");

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
        add("trim_material.elementus.steel", "Steel Material");
        add("trim_material.elementus.diarkrite", "Diarkrite Material");
        add("trim_material.elementus.anthektite", "Anthektite Material");

        add("tag.item.elementus.steel_recyclable", "Steel Recyclable");
        add("tag.item.elementus.movcadia_logs", "Movcadia Logs");
        add("tag.item.elementus.catalyst.items", "Catalyst Cores");
    }
    void effectsTL() {
        add("effect.elementus.beacon_power", "Beacon Power");
        add("effect.elementus.totem_cooldown", "Catalyst Totem Exhaustion");
        add("effect.elementus.withered_beacon_power", "Withered Beacon Power");
    }
    void entityTL() {
        addEntityType(ModEntityType.MOVCADIA_BOAT, "Movcadia Boat");
        addEntityType(ModEntityType.MOVCADIA_CHEST_BOAT, "Movcadia Boat with Chest");
        addEntityType(ModEntityType.STEEL_GOLEM, "Steel Golem");
//        addEntityType(ModEntityType.DIARKRITE_GOLEM, "Diarkrite Golem");
//        addEntityType(ModEntityType.ANTHEKTITE_GOLEM, "Anthektite Golem");
        addEntityType(ModEntityType.ANTHEKTITE_SLASH, "Wind Slash");
        addEntityType(ModEntityType.PULSE_BURST, "Pulse Burst");
        addEntityType(ModEntityType.RUSH_PROJECTILE, "Rush");
        addEntityType(ModEntityType.SWORD_DANCE_SLASH, "Slash");
        addEntityType(ModEntityType.WRATH_TRIDENT, "Wrath of The Sea");
    }
    void subtitlesTL() {
        add("subtitles.elementus.item.catalyst_armor_activation", "Catalyst armor activates");
        add("subtitles.elementus.item.catalyst_armor_deactivation", "Catalyst armor deactivates");
        add("subtitles.elementus.item.diarkrite_shield_block", "Heavy shield block");
        add("subtitles.elementus.item.anthektite_shield_block", "Heavy shield block");

        add("subtitles.elementus.item.charge_blade.block", "Sword blocks");
        add("subtitles.elementus.item.charge_blade.parry", "Sword parries");
        add("subtitles.elementus.item.diarkrite_charge_blade.burst", "Sonic burst");
        add("subtitles.elementus.item.diarkrite_charge_blade.burst_cursed", "Sonic burst");
        add("subtitles.elementus.item.diarkrite_charge_blade.condensed_burst", "Condensed burst");
        add("subtitles.elementus.item.diarkrite_charge_blade.condensed_burst_cursed", "Condensed burst");
        add("subtitles.elementus.item.anthektite_charge_blade.wind_slash", "Wind slash");
        add("subtitles.elementus.item.anthektite_charge_blade.cleave", "Cleave");
        add("subtitles.elementus.item.anthektite_charge_blade.rush", "Rush");

        add("subtitles.elementus.entity.steel_golem.repair", "Steel Golem repaired");
        add("subtitles.elementus.entity.steel_golem.down", "Steel Golem breaks down");
        add("subtitles.elementus.entity.steel_golem.revive", "Steel Golem rises");
        add("subtitles.elementus.entity.steel_golem.saddled", "Steel Golem saddled");
        add("subtitles.elementus.entity.steel_golem.chested", "Steel Golem chested");
        add("subtitles.elementus.entity.steel_golem.armored", "Steel Golem armored");
        add("subtitles.elementus.entity.steel_golem.leave_swag", "Steel Golem camouflaged");
        add("subtitles.elementus.entity.steel_golem.carpet_swag", "Steel Golem dripped out");
    }
    void damageTL() {
        add("death.attack.elementus.sacrificial", "%1$s sacrificed too much");
        add("death.attack.elementus.sacrificial.player", "%1$s was sacrificed while trying to escape %2$s");
    }
    void configTL() {
        add("config.elementus.lava_renderer", "Replace Lava Renderer.");
        add("config.elementus.diarkriteEfficiency.desc", "Diarkrite Pickaxe Efficiency.");
        add("config.elementus.catalystArmorDurability.desc", "Catalyst Armor Durability.");
        add("config.elementus.catalystTotemAbility.desc", "Catalyst Armor Totem Ability.");
        add("config.elementus.arcaneSharpnessTreasure.desc", "Arcane Sharpness Treasure.");
        add("config.elementus.arcaneSharpnessPercent", "Arcane Sharpness Damage Percent.");
        add("config.elementus.arcaneSharpnessPercent.desc", "Item's enchantability * this.");
        add("config.elementus.arcaneSharpnessIncompatibility", "Arcane Sharpness.");
        add("config.elementus.arcaneSharpnessIncompatibility.desc", "Arcane Sharpness Enchantment Compatibility.");
    }
    void catalystTL() {
        String corePrefix = "catalyst_core." + MODID + ".";
        add(corePrefix + "beacon_power.title", "Beacon Power");
        add(corePrefix + "beacon_power.desc", "Power of a Beacon, but mobile.");
        add(corePrefix + "heart_of_the_sea.title", "Heart of the Sea");
        add(corePrefix + "heart_of_the_sea.desc", "Conduit Power on the go.");
        add(corePrefix + "totem_of_undying.title", "Totem of Undying");
        add(corePrefix + "totem_of_undying.desc", "A second chance after death.");

        add(corePrefix + "ignitium.title", "Inferno Reflex");
        add(corePrefix + "ignitium.desc", "A fraction of Ingis' power.");
        add(corePrefix + "ignitium.desc_1", "Randomly sets attackers on fire and apply Blazing Brand.");
        add(corePrefix + "ignitium.desc_2", "Immunity to Blazing Brand.");
        add(corePrefix + "cursium.title", "Cursed");
        add(corePrefix + "cursium.desc", "Cursed undead worrier.");
        add(corePrefix + "cursium.desc_1", "Revives upon death.");
        add(corePrefix + "cursium.desc_2", "Chance to dodge attacks, chances increase if it's a projectile.");
        add(corePrefix + "essence_of_sea.title", "Essence of Sea");
        add(corePrefix + "essence_of_sea.desc", "Work In Progress.");

        add(corePrefix + "arcane_ingot.title", "Arcane Ingot (deprecated)");
        add(corePrefix + "arcane_ingot.desc", "Arcane Imbuement");
        add(corePrefix + "fire_irons_spellbooks.title", "Fire Arcane Power");
        add(corePrefix + "fire_irons_spellbooks.desc", "Arcane Imbuement of Flames");
        add(corePrefix + "ice_irons_spellbooks.title", "Ice Arcane Power");
        add(corePrefix + "ice_irons_spellbooks.desc", "Arcane Imbuement of Ice");
        add(corePrefix + "ender_irons_spellbooks.title", "Ender Arcane Power");
        add(corePrefix + "ender_irons_spellbooks.desc", "Arcane Imbuement of Ender");
        add(corePrefix + "lightning_irons_spellbooks.title", "Lightning Arcane Power");
        add(corePrefix + "lightning_irons_spellbooks.desc", "Arcane Imbuement of Lightning");
        add(corePrefix + "blood_irons_spellbooks.title", "Blood Arcane Power");
        add(corePrefix + "blood_irons_spellbooks.desc", "Arcane Imbuement of Blood");
        add(corePrefix + "holy_irons_spellbooks.title", "Holy Arcane Power");
        add(corePrefix + "holy_irons_spellbooks.desc", "Arcane Imbuement of Holy Light");
        add(corePrefix + "evocation_irons_spellbooks.title", "Evocation Arcane Power");
        add(corePrefix + "evocation_irons_spellbooks.desc", "Arcane Imbuement of Evocation");
        add(corePrefix + "nature_irons_spellbooks.title", "Nature Arcane Power");
        add(corePrefix + "nature_irons_spellbooks.desc", "Arcane Imbuement of Nature");
        add(corePrefix + "mana_irons_spellbooks.title", "Condensed Arcane Power");
        add(corePrefix + "mana_irons_spellbooks.desc", "Arcane Imbuement of Overflowing Mana");
        add(corePrefix + "cooldown_irons_spellbooks.title", "Swift Arcane Power");
        add(corePrefix + "cooldown_irons_spellbooks.desc", "Arcane Imbuement of Swiftness");
        add(corePrefix + "protection_irons_spellbooks.title", "Protective Arcane Power");
        add(corePrefix + "protection_irons_spellbooks.desc", "Arcane Imbuement of Protection");

        add(corePrefix + "withered_beacon_power.title", "Corrupted Beacon Power");
        add(corePrefix + "withered_beacon_power.desc", "The shadow of The World Eater");
    }

    public void enchantTL(Supplier<? extends Enchantment> key, String name, String desc) {
        addEnchantment(key, name);
        add(key.get() + ".desc", desc);
    }
}
