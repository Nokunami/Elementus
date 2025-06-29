package nokunami.elementus.common.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import nokunami.elementus.common.registry.ModItems.*;
import org.infernalstudios.archeryexp.util.BowProperties;
import org.infernalstudios.archeryexp.util.BowUtil;

import static nokunami.elementus.Elementus.modLoc;
import static nokunami.elementus.ModChecker.*;
import static nokunami.elementus.common.item.CatalystArmorItem.catalystActivator;
import static nokunami.elementus.common.item.CatalystItemUtil.*;
import static nokunami.elementus.common.item.DiarkriteChargeBlade.*;
import static nokunami.elementus.common.registry.ModEnchantments.*;

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

         chargeBlade(ElementusItems.DIARKRITE_CHARGE_BLADE.get());
         chargeBlade(ElementusItems.ANTHEKTITE_CHARGE_BLADE.get());
     }

    private static void shieldBlocking(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, clientLevel, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    private static void catalystArmor(Item item) {
         ItemProperties.register(item, new ResourceLocation("elementus", "catalyst"), (itemStack, clientLevel, livingEntity, i) -> switch (catalystActivator(itemStack)) {
             case netherStar -> 0.11F;
             case ignitium -> 0.12F;
             case arcane -> 0.13F;
             case heartSea -> 0.14F;
             case totem -> 0.15F;
             case cursium -> 0.16F;
             case witheredNetherStar -> 0.17F;
             default -> 0;
         });
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

    private static void chargeBlade(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, clientLevel, livingEntity, i)
                -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, modLoc("enchanted"), (itemStack, clientLevel, livingEntity, i)
                -> isEnchantedWith(itemStack, SACRIFICE_CURSE) ? 0.1F : isEnchantedWith(itemStack, MULTI_CHARGE) ? 0.2F : 0F);

        ItemProperties.register(item, modLoc("charge"), (itemStack, clientLevel, livingEntity, i) -> {
            float i0 = Math.min(getCharge(itemStack), getMaxCharge(itemStack));
            float i1 = getMaxCharge(itemStack);
            return i0 / i1;
        });
    }
}
