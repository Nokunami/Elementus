package net.nokunami.elementus.datagen.generators;

import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;
import net.nokunami.elementus.common.registry.ESounds;

import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;

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
        String itemSub = "subtitles." + EID + ".item.";

        add(ESounds.CATALYST_ARMOR_ACTIVATE, definition()
                .subtitle(getSub(ESounds.CATALYST_ARMOR_ACTIVATE))
                .with(itemSound("catalyst_armor/activate1"),
                        itemSound("catalyst_armor/activate2"),
                        itemSound("catalyst_armor/activate3")));
        add(ESounds.CATALYST_ARMOR_DEACTIVATE, definition()
                .subtitle(getSub(ESounds.CATALYST_ARMOR_DEACTIVATE))
                .with(itemSound("catalyst_armor/deactivate1"),
                        itemSound("catalyst_armor/deactivate2")));

        add(ESounds.DIARKRITE_SHIELD_BLOCK, definition()
                .subtitle(getSub(ESounds.DIARKRITE_SHIELD_BLOCK))
                .with(itemSound("diarkrite_shield/block1"),
                        itemSound("diarkrite_shield/block2"),
                        itemSound("diarkrite_shield/block3")));
        add(ESounds.ANTHEKTITE_SHIELD_BLOCK, definition()
                .subtitle(getSub(ESounds.ANTHEKTITE_SHIELD_BLOCK))
                .with(itemSound("diarkrite_shield/block1"),
                        itemSound("diarkrite_shield/block2"),
                        itemSound("diarkrite_shield/block3")));

        add(ESounds.CHARGE_BLADE_BLOCK, definition()
                .subtitle(getSub(ESounds.CHARGE_BLADE_BLOCK))
                .with(itemSound("charge_blade/sword_block1"),
                        itemSound("charge_blade/sword_block2"),
                        itemSound("charge_blade/sword_block3")));

        add(ESounds.CHARGE_BLADE_PARRY, definition()
                .subtitle(getSub(ESounds.CHARGE_BLADE_PARRY))
                .with(itemSound("charge_blade/sword_parry1"),
                        itemSound("charge_blade/sword_parry2"),
                        itemSound("charge_blade/sword_parry3")));

        add(ESounds.BOR_BLOCK_RESONANCE, definition()
                .subtitle(getSub(ESounds.BOR_BLOCK_RESONANCE))
                .with(itemSound("diarkrite_charge_blade/sword_resonance_block1"),
                        itemSound("diarkrite_charge_blade/sword_resonance_block2"),
                        itemSound("diarkrite_charge_blade/sword_resonance_block3")));
        add(ESounds.BOR_PARRY_RESONANCE, definition()
                .subtitle(getSub(ESounds.BOR_PARRY_RESONANCE))
                .with(itemSound("diarkrite_charge_blade/sword_resonance_parry1"),
                        itemSound("diarkrite_charge_blade/sword_resonance_parry2")));
        add(ESounds.BOR_SONIC_RESONANCE, definition()
                .subtitle(getSub(ESounds.BOR_SONIC_RESONANCE))
                .with(itemSound("diarkrite_charge_blade/sonic_resonance1"),
                        itemSound("diarkrite_charge_blade/sonic_resonance2"),
                        itemSound("diarkrite_charge_blade/sonic_resonance3")));

        add(ESounds.BOR_BURST, definition()
                .subtitle(getSub(ESounds.BOR_BURST))
//                .with(itemSound("diarkrite_charge_blade/burst1"),
//                        itemSound("diarkrite_charge_blade/burst2"),
//                        itemSound("diarkrite_charge_blade/burst3")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom2"),
                        sound("mob/warden/sonic_boom3")));
        add(ESounds.BOR_BURST_CURSED, definition()
                .subtitle(getSub(ESounds.BOR_BURST_CURSED))
                .with(itemSound("diarkrite_charge_blade/burst_cursed1"),
                        itemSound("diarkrite_charge_blade/burst_cursed2"),
                        itemSound("diarkrite_charge_blade/burst_cursed3"),
                        itemSound("diarkrite_charge_blade/burst_cursed4")));
        add(ESounds.BOR_CONDENSED_BURST, definition()
                .subtitle(getSub(ESounds.BOR_CONDENSED_BURST))
                .with(itemSound("diarkrite_charge_blade/condensed_burst1"),
                        itemSound("diarkrite_charge_blade/condensed_burst2")));
        add(ESounds.BOR_CONDENSED_BURST_CURSED, definition()
                .subtitle(getSub(ESounds.BOR_CONDENSED_BURST_CURSED))
                .with(itemSound("diarkrite_charge_blade/condensed_burst_cursed1"),
                        itemSound("diarkrite_charge_blade/condensed_burst_cursed2")));
        add(ESounds.BOR_PULSE_BURST, definition()
                .subtitle(getSub(ESounds.BOR_PULSE_BURST))
//                .with(itemSound("diarkrite_charge_blade/pulse_burst1"),
//                        itemSound("diarkrite_charge_blade/pulse_burst2")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom1")));
        add(ESounds.BOR_PULSE_BURST_CURSED, definition()
                .subtitle(getSub(ESounds.BOR_PULSE_BURST_CURSED))
//                .with(itemSound("diarkrite_charge_blade/pulse_burst_cursed1"),
//                        itemSound("diarkrite_charge_blade/pulse_burst_cursed2")));
                .with(sound("mob/warden/sonic_boom1"),
                        sound("mob/warden/sonic_boom1")));

        add(ESounds.BOSW_WIND_SLASH, definition()
                .subtitle(getSub(ESounds.BOSW_WIND_SLASH))
//                .with(itemSound("anthektite_charge_blade/wind_slash1"),
//                        itemSound("anthektite_charge_blade/wind_slash2")));
                .with(sound("entity/endereye/dead1"),
                        sound("entity/endereye/dead2")));
        add(ESounds.BOSW_CLEAVE, definition()
                .subtitle(getSub(ESounds.BOSW_CLEAVE))
//                .with(itemSound("anthektite_charge_blade/cleave1"),
//                        itemSound("anthektite_charge_blade/cleave2")));
                .with(sound("entity/endereye/dead1"),
                        sound("entity/endereye/dead2")));
        add(ESounds.BOSW_RUSH, definition()
                .subtitle(getSub(ESounds.BOSW_RUSH))
                .with(itemSound("anthektite_charge_blade/rush")));

        add(ESounds.ARMOR_EQUIP_DIARKRITE, definition()
                .subtitle(getSub(ESounds.ARMOR_EQUIP_DIARKRITE))
                .with(itemSound("armor/equip_diarkrite1"))
                .with(itemSound("armor/equip_diarkrite2"))
                .with(itemSound("armor/equip_diarkrite3"))
                .with(itemSound("armor/equip_diarkrite4"))
        );
//        add(ESounds.ARMOR_EQUIP_ANTHEKTITE, definition()
//                .subtitle(getSub(ESounds.ARMOR_EQUIP_ANTHEKTITE))
//                .with(itemSound("armor/equip_anthektite1"))
//                .with(itemSound("armor/equip_anthektite2"))
//                .with(itemSound("armor/equip_anthektite3"))
//                .with(itemSound("armor/equip_anthektite4"))
//        );
    }
    void entitySoundEntries() {
        String sub = "subtitles." + EID + ".entity";

        add(ESounds.STEEL_GOLEM_STEP, definition()
                .subtitle(sub + "steel_golem.step")
//                .with(mobSound("steel_golem/walk1"))
//                .with(mobSound("steel_golem/walk2"))
//                .with(mobSound("steel_golem/walk3"))
//                .with(mobSound("steel_golem/walk4"))
                .with(sound("mob/irongolem/walk1"))
                .with(sound("mob/irongolem/walk2"))
                .with(sound("mob/irongolem/walk3"))
                .with(sound("mob/irongolem/walk4"))
        );
        add(ESounds.STEEL_GOLEM_HURT, definition()
                .subtitle(sub + "steel_golem.hurt")
//                .with(mobSound("steel_golem/hit1"))
//                .with(mobSound("steel_golem/hit2"))
//                .with(mobSound("steel_golem/hit3"))
//                .with(mobSound("steel_golem/hit4"))
                .with(sound("mob/irongolem/hit1"))
                .with(sound("mob/irongolem/hit2"))
                .with(sound("mob/irongolem/hit3"))
                .with(sound("mob/irongolem/hit4"))
        );
//        add(ESoundEvents.STEEL_GOLEM_AMBIENT, definition()
//                .subtitle(sub + "steel_golem.ambient")
//                .with(sound("mob/irongolem/repair")));
        add(ESounds.STEEL_GOLEM_REPAIR, definition()
                .subtitle(sub + "steel_golem.repair")
                .with(sound("mob/irongolem/repair")));
        add(ESounds.STEEL_GOLEM_DOWN, definition()
                .subtitle(sub + "steel_golem.down")
                .with(mobSound("steel_golem/down")));
        add(ESounds.STEEL_GOLEM_DEATH, definition()
                .subtitle(sub + "steel_golem.death")
//                .with(mobSound("steel_golem/death")));
                .with(sound("mob/irongolem/death")));
        add(ESounds.STEEL_GOLEM_REVIVE, definition()
                .subtitle(sub + "steel_golem.revive")
                .with(sound("mob/irongolem/repair")));
        add(ESounds.STEEL_GOLEM_SADDLED, definition()
                .subtitle(sub + "steel_golem.saddled")
                .with(sound("mob/horse/leather")));
        add(ESounds.STEEL_GOLEM_CHESTED, definition()
                .subtitle(sub + "steel_golem.chested")
                .with(sound("mob/chicken/plop")));
        add(ESounds.STEEL_GOLEM_ARMORED, definition()
                .subtitle(sub + "steel_golem.armored")
                .with(sound("mob/horse/armor")));
        add(ESounds.STEEL_GOLEM_LEAVES_SWAG, definition()
                .subtitle(sub + "steel_golem.leaves_swag")
                .with(sound("block/azalea/break1"),
                        sound("block/azalea/break2"),
                        sound("block/azalea/break3"),
                        sound("block/azalea/break4"),
                        sound("block/azalea/break5"),
                        sound("block/azalea/break6")));
        add(ESounds.STEEL_GOLEM_CARPET_SWAG, definition()
                .subtitle(sub + "steel_golem.carpet_swag")
                .with(sound("mob/llama/swag")));
    }

    public static String getSub(Supplier<SoundEvent> soundEvent) { return getSub(soundEvent.get()); }
    public static String getSub(SoundEvent soundEvent) { return "subtitles." + EID + "." + soundEvent.getLocation().getPath(); }
    private SoundDefinition.Sound itemSound(String path) { return sound(EID + ":item/" + path); }
    private SoundDefinition.Sound entitySound(String path) { return sound(EID + ":entity/" + path); }
    private SoundDefinition.Sound mobSound(String path) { return sound(EID + ":mob/" + path); }
}
