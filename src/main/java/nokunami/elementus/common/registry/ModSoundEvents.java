package nokunami.elementus.common.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static nokunami.elementus.Elementus.MODID;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, MODID);

    private static DeferredHolder<SoundEvent, SoundEvent> register(String id) {
        return SOUNDS.register(id, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MODID, id)));
    }

    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_ARMOR_ACTIVATE = register("catalyst_armor_activate");
    public static final DeferredHolder<SoundEvent, SoundEvent> CATALYST_ARMOR_DEACTIVATE = register("catalyst_armor_deactivate");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_SHIELD_BLOCK = register("diarkrite_shield_block");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANTHEKTITE_SHIELD_BLOCK = register("anthektite_shield_block");

    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_STEP = register("steel_golem_step");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_HURT = register("steel_golem_hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_AMBIENT = register("steel_golem_ambient");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_REPAIR = register("steel_golem_repair");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_DOWN = register("steel_golem_down");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_REVIVE = register("steel_golem_revive");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_SADDLED = register("steel_golem_saddled");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_CHESTED = register("steel_golem_chested");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_ARMORED = register("steel_golem_armored");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_LEAVES_SWAG = register("steel_golem_leaves_swag");
    public static final DeferredHolder<SoundEvent, SoundEvent> STEEL_GOLEM_CARPET_SWAG = register("steel_golem_carpet_swag");

    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_CHARGE_UP = register("diarkrite_charge_blade_charge_up");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_BLOCK = register("diarkrite_charge_blade.block");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_PARRY = register("diarkrite_charge_blade.parry");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_BLOCK_RESONANCE = register("diarkrite_charge_blade.block_resonance");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_PARRY_RESONANCE = register("diarkrite_charge_blade.parry_resonance");
    public static final DeferredHolder<SoundEvent, SoundEvent> DIARKRITE_CHARGE_BLADE_SONIC_RESONANCE = register("diarkrite_charge_blade.sonic_resonance");

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
