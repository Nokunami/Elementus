package net.nokunami.elementus.common.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.nokunami.elementus.Elementus.MODID;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);

    public static final RegistryObject<SoundEvent> CATALYST_ARMOR_ACTIVATE = SOUNDS.register("catalyst_armor_activate",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "item.catalyst_armor.activate")));

    public static final RegistryObject<SoundEvent> CATALYST_ARMOR_DEACTIVATE = SOUNDS.register("catalyst_armor_deactivate",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "item.catalyst_armor.deactivate")));

    public static final RegistryObject<SoundEvent> DIARKRITE_SHIELD_BLOCK = SOUNDS.register("diarkrite_shield_block",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "item.diarkrite_shield.block")));

    public static final RegistryObject<SoundEvent> STEEL_GOLEM_DOWN = SOUNDS.register("steel_golem_down",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "entity.steel_golem.down")));

    public static final RegistryObject<SoundEvent> DIARKRITE_CHARGE_BLADE_CHARGE_UP = SOUNDS.register("diarkrite_charge_blade_charge_up",
            () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(MODID, "item.diarkrite_charge_blade.charge_up")));

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
