//package net.nokunami.elementus.common.registry;
//
//import net.minecraft.world.entity.ai.attributes.Attribute;
//import net.minecraft.world.entity.ai.attributes.RangedAttribute;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//
//import static net.nokunami.elementus.Elementus.EID;
//
//public class EAttributes {
//
//    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.ATTRIBUTES, EID);
//
//    public static final RegistryObject<Attribute> BURST_DAMAGE = ATTRIBUTES.register("burst_damage",
//            () -> new RangedAttribute(EID + ".burst_damage", 1, 0, 1000000000D));
//
//    public static void register(IEventBus eventBus) { ATTRIBUTES.register(eventBus); }
//}
