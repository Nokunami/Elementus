package net.nokunami.elementus.common.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.nokunami.elementus.common.registry.ModItems.*;
import org.infernalstudios.archeryexp.util.BowProperties;
import org.infernalstudios.archeryexp.util.BowUtil;

import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.item.CatalystItemUtil.*;

public class ItemPredicateRegister {

     public static void registerItemPredicate() {
         shieldBlocking(ElementusItems.STEEL_SHIELD.get());
         shieldBlocking(ElementusItems.ANTHEKTITE_SHIELD.get());
         shieldBlocking(ElementusItems.DIARKRITE_SHIELD.get());

         if (sniffsWeapons) {
             shieldBlocking(SniffsWeaponsItems.STEEL_GREAT_PICKAXE.get());
             shieldBlocking(SniffsWeaponsItems.DIARKRITE_GREAT_PICKAXE.get());
             shieldBlocking(SniffsWeaponsItems.ANTHEKTITE_GREAT_PICKAXE.get());
         }
         catalystArmor(ElementusItems.CATALYST_CHESTPLATE.get());

         if (archeryExp) {
             aeComapt(ElementusItems.STEEL_BOW.get());
             aeComapt(ElementusItems.DIARKRITE_BOW.get());
             aeComapt(ElementusItems.ANTHEKTITE_BOW.get());
         } else {
             bowPull(ElementusItems.STEEL_BOW.get());
             bowPull(ElementusItems.DIARKRITE_BOW.get());
             bowPull(ElementusItems.ANTHEKTITE_BOW.get());
         }

         bowPull(ElementusItems.DIARKRITE_CHARGE_BLADE.get());
     }

    private static void shieldBlocking(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, clientLevel, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    private static void catalystArmor(Item item) {
         ItemProperties.register(item, new ResourceLocation("elementus", "catalyst"), (itemStack, clientLevel, livingEntity, i) -> {
             if (CatalystArmorItem.catalystActivator(itemStack).equals(netherStar)) return 0.11F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(ignitium)) return 0.12F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(arcane)) return 0.13F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(heartSea)) return 0.14F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(totem)) return 0.15F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(cursium)) return 0.16F;
             if (CatalystArmorItem.catalystActivator(itemStack).equals(witheredNetherStar)) return 0.17F;
             return 0;
         });
//         ItemProperties.register(item, new ResourceLocation("elementus", ignitium), (itemStack, clientLevel, livingEntity, i) -> {
//             if (CatalystArmorItem.catalystActivator(itemStack).equals(ignitium)) {
//                 return 1;
//             }
//             return 0;
//         });
//         ItemProperties.register(item, new ResourceLocation("elementus", arcane), (itemStack, clientLevel, livingEntity, i) -> {
//             if (CatalystArmorItem.catalystActivator(itemStack).equals(arcane)) {
//                 return 1;
//             }
//             return 0;
//         });
//         ItemProperties.register(item, new ResourceLocation("elementus", heartSea), (itemStack, clientLevel, livingEntity, i) -> {
//             if (CatalystArmorItem.catalystActivator(itemStack).equals(heartSea)) {
//                 return 1;
//             }
//             return 0;
//         });
//         ItemProperties.register(item, new ResourceLocation("elementus", totem), (itemStack, clientLevel, livingEntity, i) -> {
//             if (CatalystArmorItem.catalystActivator(itemStack).equals(totem)) {
//                 return 1;
//             }
//             return 0;
//         });
    }

    private static void bowPull(Item item) {
        ItemProperties.register(item, new ResourceLocation("pulling"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, new ResourceLocation("pull"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null ? livingEntity.getUseItem() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks()) / 20.0F : 0.0F);
    }

    private static void aeComapt(Item item) {
        ItemProperties.register(item, new ResourceLocation("drawing"),
                (itemStack, clientLevel, livingEntity, i) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, new ResourceLocation("draw"),
                (itemStack, clientLevel, livingEntity, i) -> {
                    if (livingEntity != null && livingEntity.getUseItem() == itemStack) {
                        BowProperties properties = (BowProperties)itemStack.getItem();
                        return BowUtil.getPowerForDrawTime(itemStack.getUseDuration() - livingEntity.getUseItemRemainingTicks(), properties);
                    } else {
                        return 0.0F;
                    }
        });
    }
}
