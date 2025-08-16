package net.nokunami.elementus.common.registry;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.nokunami.elementus.common.capability.TridentOwnerCapability;

public class ModCapabilities {

    public static final Capability<TridentOwnerCapability.ITridentOwner> TRIDENT_OWNER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    @SubscribeEvent
    public static void registerCapabilitiesEvent(RegisterCapabilitiesEvent event) {
        event.register(TridentOwnerCapability.TridentOwnerCap.class);
    }
}
