package net.nokunami.elementus.common.registry;

import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.Elementus.modLoc;

public class ESounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EID);

    public static final RegistryObject<SoundEvent> CATALYST_ARMOR_ACTIVATE = registerItem("catalyst_armor_activate", "catalyst_armor.activate");
    public static final RegistryObject<SoundEvent> CATALYST_ARMOR_DEACTIVATE = registerItem("catalyst_armor_deactivate", "catalyst_armor.deactivate");

    public static final RegistryObject<SoundEvent> DIARKRITE_SHIELD_BLOCK = registerItem("diarkrite_shield_block", "diarkrite_shield.block");
    public static final RegistryObject<SoundEvent> ANTHEKTITE_SHIELD_BLOCK = registerItem("anthektite_shield_block", "anthektite_shield.block");

    public static final RegistryObject<SoundEvent> STEEL_GOLEM_STEP = registerEntity("steel_golem_step", "steel_golem.step");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_HURT = registerEntity("steel_golem_hurt", "steel_golem.hurt");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_AMBIENT = registerEntity("steel_golem_ambient", "steel_golem.ambient");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_REPAIR = registerEntity("steel_golem_repair", "steel_golem.repair");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_DOWN = registerEntity("steel_golem_down", "steel_golem.down");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_DEATH = registerEntity("steel_golem_death", "steel_golem.death");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_REVIVE = registerEntity("steel_golem_revive", "steel_golem.revive");

    public static final RegistryObject<SoundEvent> STEEL_GOLEM_SADDLED = registerEntity("steel_golem_saddled", "steel_golem.saddled");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_CHESTED = registerEntity("steel_golem_chested", "steel_golem.chested");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_ARMORED = registerEntity("steel_golem_armored", "steel_golem.armored");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_LEAVES_SWAG = registerEntity("steel_golem_leaves_swag", "steel_golem.leaves_swag");
    public static final RegistryObject<SoundEvent> STEEL_GOLEM_CARPET_SWAG = registerEntity("steel_golem_carpet_swag", "steel_golem.carpet_swag");

    public static final RegistryObject<SoundEvent> DIARKRITE_CHARGE_BLADE_CHARGE_UP = registerItem("charge_blade_charge_up", "charge_blade.charge_up");
    public static final RegistryObject<SoundEvent> CHARGE_BLADE_BLOCK = registerItem("charge_blade.block", "charge_blade.block");
    public static final RegistryObject<SoundEvent> CHARGE_BLADE_PARRY = registerItem("charge_blade.parry", "charge_blade.parry");

    public static final RegistryObject<SoundEvent> BOR_BLOCK_RESONANCE = registerItem("diarkrite_charge_blade.block_resonance", "diarkrite_charge_blade.block_resonance");
    public static final RegistryObject<SoundEvent> BOR_PARRY_RESONANCE = registerItem("charge_blade.parry_resonance", "diarkrite_charge_blade.parry_resonance");
    public static final RegistryObject<SoundEvent> BOR_SONIC_RESONANCE = registerItem("charge_blade.sonic_resonance", "diarkrite_charge_blade.sonic_resonance");
    public static final RegistryObject<SoundEvent> BOR_BURST = registerItem("diarkrite_charge_blade.burst", "diarkrite_charge_blade.burst");
    public static final RegistryObject<SoundEvent> BOR_BURST_CURSED = registerItem("diarkrite_charge_blade.burst_cursed", "diarkrite_charge_blade.burst_cursed");
    public static final RegistryObject<SoundEvent> BOR_CONDENSED_BURST = registerItem("diarkrite_charge_blade.condensed_burst", "diarkrite_charge_blade.condensed_burst");
    public static final RegistryObject<SoundEvent> BOR_CONDENSED_BURST_CURSED = registerItem("diarkrite_charge_blade.condensed_burst_cursed", "diarkrite_charge_blade.condensed_burst_cursed");
    public static final RegistryObject<SoundEvent> BOR_PULSE_BURST = registerItem("diarkrite_charge_blade.pulse_burst", "diarkrite_charge_blade.pulse_burst");
    public static final RegistryObject<SoundEvent> BOR_PULSE_BURST_CURSED = registerItem("diarkrite_charge_blade.pulse_burst_cursed", "diarkrite_charge_blade.pulse_burst_cursed");

    public static final RegistryObject<SoundEvent> BOSW_WIND_SLASH = registerItem("anthektite_charge_blade.wind_slash", "anthektite_charge_blade.wind_slash");
    public static final RegistryObject<SoundEvent> BOSW_CLEAVE = registerItem("anthektite_charge_blade.cleave", "anthektite_charge_blade.cleave");
    public static final RegistryObject<SoundEvent> BOSW_RUSH = registerItem("anthektite_charge_blade.rush", "anthektite_charge_blade.rush");

    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_DIARKRITE = registerItem("equip_diarkrite", "armor.equip_diarkrite");
    public static final RegistryObject<SoundEvent> ARMOR_EQUIP_ANTHEKTITE = registerItem("equip_anthektite", "armor.equip_anthektite");

    public static RegistryObject<SoundEvent> registerEntity(String id, String path) { return register(id, "entity." + path); }
    public static RegistryObject<SoundEvent> registerItem(String id, String path) { return register(id, "item." + path); }
    public static RegistryObject<SoundEvent> register(String id, String path) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(modLoc(path)));
    }

    public static void register(IEventBus eventBus) { SOUNDS.register(eventBus); }
}
