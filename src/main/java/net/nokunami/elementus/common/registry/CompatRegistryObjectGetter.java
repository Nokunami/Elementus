package net.nokunami.elementus.common.registry;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.github.L_Ender.cataclysm.init.ModItems.IGNITIUM_INGOT;
import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.cataclysmID;
import static net.nokunami.elementus.ModChecker.ironsSpellbooksID;

public class CompatRegistryObjectGetter {


    public static class CatalysmItems {
        private static RegistryObject<Item> getItem(String id) {
            return RegistryObject.create(modLoc(cataclysmID, id), ForgeRegistries.ITEMS);
        }

        public static final RegistryObject<Item> IGNITIUM_INGOT = getItem("ignitium_ingot");
        public static final RegistryObject<Item> CURSIUM_INGOT = getItem("cursium_ingot");
        public static final RegistryObject<Item> ESSENCE_OF_THE_STORM = getItem("essence_of_the_storm");

    }

    public static class IronsItemRegistry {
        private static RegistryObject<Item> getItem(String id) {
            return RegistryObject.create(modLoc(ironsSpellbooksID, id), ForgeRegistries.ITEMS);
        }
        public static final RegistryObject<Item> ARCANE_INGOT = getItem("arcane_ingot");
        public static final RegistryObject<Item> FIRE_RUNE = getItem("fire_rune");
        public static final RegistryObject<Item> ICE_RUNE = getItem("ice_rune");
        public static final RegistryObject<Item> ENDER_RUNE = getItem("ender_rune");
        public static final RegistryObject<Item> LIGHTNING_RUNE = getItem("lightning_rune");
        public static final RegistryObject<Item> HOLY_RUNE = getItem("holy_rune");
        public static final RegistryObject<Item> BLOOD_RUNE = getItem("blood_rune");
        public static final RegistryObject<Item> EVOCATION_RUNE = getItem("evocation_rune");
        public static final RegistryObject<Item> NATURE_RUNE = getItem("nature_rune");
        public static final RegistryObject<Item> MANA_RUNE = getItem("arcane_rune");
        public static final RegistryObject<Item> COOLDOWN_RUNE = getItem("cooldown_rune");
        public static final RegistryObject<Item> PROTECTION_RUNE = getItem("protection_rune");
    }

    public static class IronsAttributeRegistry {
        private static RegistryObject<Attribute> getAttribute(String id) {
            return RegistryObject.create(modLoc(ironsSpellbooksID, id), ForgeRegistries.ATTRIBUTES);
        }
        private static RegistryObject<Attribute> getSpellResist(String id) {
            return getAttribute(id + "_magic_resist");
        }
        private static RegistryObject<Attribute> getSpellPower(String id) {
            return getAttribute(id + "_spell_power");
        }
        public static final RegistryObject<Attribute> MAX_MANA = getAttribute("max_mana");
        public static final RegistryObject<Attribute> MANA_REGEN = getAttribute("mana_regen");
        public static final RegistryObject<Attribute> COOLDOWN_REDUCTION = getAttribute("cooldown_reduction");
        public static final RegistryObject<Attribute> SPELL_POWER = getAttribute("spell_power");
        public static final RegistryObject<Attribute> SPELL_RESIST = getAttribute("spell_resist");
        public static final RegistryObject<Attribute> CAST_TIME_REDUCTION = getAttribute("cast_time_reduction");
        public static final RegistryObject<Attribute> SUMMON_DAMAGE = getAttribute("summon_damage");

        public static final RegistryObject<Attribute> FIRE_MAFIC_RESIST = getSpellResist("fire");
        public static final RegistryObject<Attribute> ICE_MAFIC_RESIST = getSpellResist("ice");
        public static final RegistryObject<Attribute> LIGHTNING_MAFIC_RESIST = getSpellResist("lightning");
        public static final RegistryObject<Attribute> HOLY_MAFIC_RESIST = getSpellResist("holy");
        public static final RegistryObject<Attribute> ENDER_MAFIC_RESIST = getSpellResist("ender");
        public static final RegistryObject<Attribute> BLOOD_MAFIC_RESIST = getSpellResist("blood");
        public static final RegistryObject<Attribute> EVOCATION_MAFIC_RESIST = getSpellResist("evocation");
        public static final RegistryObject<Attribute> NATURE_MAFIC_RESIST = getSpellResist("nature");
        public static final RegistryObject<Attribute> ELDRITCH_MAFIC_RESIST = getSpellResist("eldritch");

        public static final RegistryObject<Attribute> FIRE_SPELL_POWER = getSpellPower("fire");
        public static final RegistryObject<Attribute> ICE_SPELL_POWER = getSpellPower("ice");
        public static final RegistryObject<Attribute> LIGHTNING_SPELL_POWER = getSpellPower("lightning");
        public static final RegistryObject<Attribute> HOLY_SPELL_POWER = getSpellPower("holy");
        public static final RegistryObject<Attribute> ENDER_SPELL_POWER = getSpellPower("ender");
        public static final RegistryObject<Attribute> BLOOD_SPELL_POWER = getSpellPower("blood");
        public static final RegistryObject<Attribute> EVOCATION_SPELL_POWER = getSpellPower("evocation");
        public static final RegistryObject<Attribute> NATURE_SPELL_POWER = getSpellPower("nature");
        public static final RegistryObject<Attribute> ELDRITCH_SPELL_POWER = getSpellPower("eldritch");
    }

}
