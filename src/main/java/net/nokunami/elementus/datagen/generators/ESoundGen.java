package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.nokunami.elementus.common.registry.ESoundEvents;

import static net.nokunami.elementus.Elementus.MODID;

public class ESoundGen extends SoundDefinitionsProvider {

    public ESoundGen(PackOutput output, String modId, ExistingFileHelper helper) {
        super(output, modId, helper);
    }

    @Override
    public void registerSounds() {
        itemSoundEntries();
        entitySoundEntries();
    }

    void itemSoundEntries() {
        String itemSub = "subtitles." + MODID + ".item.";

        add(ESoundEvents.CATALYST_ARMOR_ACTIVATE, definition()
                .subtitle(itemSub + "catalyst_armor_activation")
                .with(itemSound("catalyst_armor/activate1"),
                        itemSound("catalyst_armor/activate2"),
                        itemSound("catalyst_armor/activate3")));
        add(ESoundEvents.CATALYST_ARMOR_DEACTIVATE, definition()
                .subtitle(itemSub + "catalyst_armor_deactivation")
                .with(itemSound("catalyst_armor/deactivate1"),
                        itemSound("catalyst_armor/deactivate2")));

        add(ESoundEvents.DIARKRITE_SHIELD_BLOCK, definition()
                .subtitle(itemSub + "diarkrite_shield_block")
                .with(itemSound("diarkrite_shield/block1"),
                        itemSound("diarkrite_shield/block2"),
                        itemSound("diarkrite_shield/block3")));
        add(ESoundEvents.ANTHEKTITE_SHIELD_BLOCK, definition()
                .subtitle(itemSub + "anthektite_shield_block")
                .with(itemSound("diarkrite_shield/block1"),
                        itemSound("diarkrite_shield/block2"),
                        itemSound("diarkrite_shield/block3")));

        add(ESoundEvents.CHARGE_BLADE_BLOCK, definition()
                .subtitle(itemSub + "charge_blade.block")
                .with(itemSound("charge_blade/sword_block1"),
                        itemSound("charge_blade/sword_block2"),
                        itemSound("charge_blade/sword_block3")));

        add(ESoundEvents.CHARGE_BLADE_PARRY, definition()
                .subtitle(itemSub + "charge_blade.parry")
                .with(itemSound("charge_blade/sword_parry1"),
                        itemSound("charge_blade/sword_parry2"),
                        itemSound("charge_blade/sword_parry3")));

        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_BLOCK_RESONANCE, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.block")
                .with(itemSound("diarkrite_charge_blade/sword_resonance_block1"),
                        itemSound("diarkrite_charge_blade/sword_resonance_block2"),
                        itemSound("diarkrite_charge_blade/sword_resonance_block3")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_PARRY_RESONANCE, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.parry")
                .with(itemSound("diarkrite_charge_blade/sword_resonance_parry1"),
                        itemSound("diarkrite_charge_blade/sword_resonance_parry2")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_SONIC_RESONANCE, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.resonate")
                .with(itemSound("diarkrite_charge_blade/sonic_resonance1"),
                        itemSound("diarkrite_charge_blade/sonic_resonance2"),
                        itemSound("diarkrite_charge_blade/sonic_resonance3")));

        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_BURST, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.burst")
//                .with(itemSound("diarkrite_charge_blade/burst1"),
//                        itemSound("diarkrite_charge_blade/burst2"),
//                        itemSound("diarkrite_charge_blade/burst3")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom2"),
                        sound("mob/warden/sonic_boom3")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_BURST_CURSED, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.burst_cursed")
                .with(itemSound("diarkrite_charge_blade/burst_cursed1"),
                        itemSound("diarkrite_charge_blade/burst_cursed2"),
                        itemSound("diarkrite_charge_blade/burst_cursed3"),
                        itemSound("diarkrite_charge_blade/burst_cursed4")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_CONDENSED_BURST, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.condensed_burst")
                .with(itemSound("diarkrite_charge_blade/condensed_burst1"),
                        itemSound("diarkrite_charge_blade/condensed_burst2")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_CONDENSED_BURST_CURSED, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.condensed_burst_cursed")
                .with(itemSound("diarkrite_charge_blade/condensed_burst_cursed1"),
                        itemSound("diarkrite_charge_blade/condensed_burst_cursed2")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_PULSE_BURST, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.pulse_burst")
//                .with(itemSound("diarkrite_charge_blade/pulse_burst1"),
//                        itemSound("diarkrite_charge_blade/pulse_burst2")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom1")));
        add(ESoundEvents.DIARKRITE_CHARGE_BLADE_PULSE_BURST_CURSED, definition()
                .subtitle(itemSub + "diarkrite_charge_blade.pulse_burst_cursed")
//                .with(itemSound("diarkrite_charge_blade/pulse_burst_cursed1"),
//                        itemSound("diarkrite_charge_blade/pulse_burst_cursed2")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom1")));

        add(ESoundEvents.ANTHEKTITE_CHARGE_BLADE_WIND_SLASH, definition()
                .subtitle(itemSub + "anthektite_charge_blade.wind_slash")
//                .with(itemSound("anthektite_charge_blade/wind_slash1"),
//                        itemSound("anthektite_charge_blade/wind_slash2")));
                .with(sound("entity/endereye/dead1"),
                        sound("entity/endereye/dead2")));
        add(ESoundEvents.ANTHEKTITE_CHARGE_BLADE_CLEAVE, definition()
                .subtitle(itemSub + "anthektite_charge_blade.cleave")
//                .with(itemSound("anthektite_charge_blade/cleave1"),
//                        itemSound("anthektite_charge_blade/cleave2")));
                .with(sound("entity/endereye/dead1"),
                        sound("entity/endereye/dead2")));
        add(ESoundEvents.ANTHEKTITE_CHARGE_BLADE_RUSH, definition()
                .subtitle(itemSub + "anthektite_charge_blade.rush")
                .with(itemSound("anthektite_charge_blade/rush")));
    }
    void entitySoundEntries() {
        String itemSub = "subtitles." + MODID + ".entity";

        add(ESoundEvents.STEEL_GOLEM_REPAIR, definition()
                .subtitle(itemSub + "steel_golem.repair")
                .with(sound("mob/irongolem/repair")));
        add(ESoundEvents.STEEL_GOLEM_DOWN, definition()
                .subtitle(itemSub + "steel_golem.down")
                .with(mobSound("steel_golem/down")));
        add(ESoundEvents.STEEL_GOLEM_REVIVE, definition()
                .subtitle(itemSub + "steel_golem.revive")
                .with(sound("mob/irongolem/repair")));
        add(ESoundEvents.STEEL_GOLEM_SADDLED, definition()
                .subtitle(itemSub + "steel_golem.saddled")
                .with(sound("mob/horse/leather")));
        add(ESoundEvents.STEEL_GOLEM_CHESTED, definition()
                .subtitle(itemSub + "steel_golem.chested")
                .with(sound("mob/chicken/plop")));
        add(ESoundEvents.STEEL_GOLEM_ARMORED, definition()
                .subtitle(itemSub + "steel_golem.armored")
                .with(sound("mob/horse/armor")));
        add(ESoundEvents.STEEL_GOLEM_LEAVES_SWAG, definition()
                .subtitle(itemSub + "steel_golem.leaves_swag")
                .with(sound("block/azalea/break1"),
                        sound("block/azalea/break2"),
                        sound("block/azalea/break3"),
                        sound("block/azalea/break4"),
                        sound("block/azalea/break5"),
                        sound("block/azalea/break6")));
        add(ESoundEvents.STEEL_GOLEM_CARPET_SWAG, definition()
                .subtitle(itemSub + "steel_golem.carpet_swag")
                .with(sound("mob/llama/swag")));
    }

    private SoundDefinition.Sound itemSound(String path) {
        return sound(MODID + ":item/" + path);
    }
    private SoundDefinition.Sound entitySound(String path) {
        return sound(MODID + ":entity/" + path);
    }
    private SoundDefinition.Sound mobSound(String path) {
        return sound(MODID + ":mob/" + path);
    }
}
