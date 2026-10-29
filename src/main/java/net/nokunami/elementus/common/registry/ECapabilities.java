package net.nokunami.elementus.common.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.nokunami.elementus.common.capability.EParryCap;
import net.nokunami.elementus.common.capability.ITridentOwner;
import net.nokunami.elementus.common.capability.TridentOwnerCapability;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.capability.abilityData.AbilityDataCap;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifier;
import net.nokunami.elementus.common.capability.itemNotifier.ItemNotifierCap;
import net.nokunami.elementus.common.capability.slashStack.SlashStackCap;
import net.nokunami.elementus.common.capability.slashStack.SlashStackData;
import net.nokunami.elementus.common.entity.projectile.WrathTridentEntity;

public class ECapabilities {

    public static final Capability<EParryCap.IParryCap> PARRY_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });
    public static final Capability<ITridentOwner> TRIDENT_OWNER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() { });

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
//        event.register(TestArmorCap.class);
//        event.register(CatalystExhaustion.class);
//        event.register(TridentOwnerCapability.TridentOwnerCap.class);
        event.register(EParryCap.ParryCapImp.class);
//        event.register(CatalystAbilityCap.class);
        event.register(TridentOwnerCapability.class);
        event.register(ItemNotifier.class);
        event.register(AbilityData.class);
        event.register(SlashStackData.class);
    }

    public static void attachEntityCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(EParryCap.ID, new EParryCap.ParryCapImp.ParryProvider());
        }
        ItemNotifierCap.addCap(event);
        AbilityDataCap.addCap(event);
        if (event.getObject() instanceof WrathTridentEntity) {
            event.addCapability(TridentOwnerCapability.ID, new TridentOwnerCapability());
        }
        SlashStackCap.addCap(event);
    }
}
