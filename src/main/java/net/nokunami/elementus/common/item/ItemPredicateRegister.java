package net.nokunami.elementus.common.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.registry.ModItems.*;
import org.infernalstudios.archeryexp.util.BowProperties;
import org.infernalstudios.archeryexp.util.BowUtil;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.item.CatalystArmorItem.catalystActivator;
import static net.nokunami.elementus.common.item.CatalystItemUtil.*;
import static net.nokunami.elementus.common.item.DiarkriteChargeBlade.*;
import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.registry.ModEnchantments.*;

public class ItemPredicateRegister {

     public static void registerItemPredicate() {
         shieldBlocking(ElementusItems.STEEL_SHIELD.get());
         shieldBlocking(ElementusItems.ANTHEKTITE_SHIELD.get());
         shieldBlocking(ElementusItems.DIARKRITE_SHIELD.get());

         if (sniffsWeapons) {
             shieldBlocking(SWModItems.STEEL_GREAT_PICKAXE.get());
             shieldBlocking(SWModItems.DIARKRITE_GREAT_PICKAXE.get());
             shieldBlocking(SWModItems.ANTHEKTITE_GREAT_PICKAXE.get());
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

         movcadiaTools(ElementusItems.MOVCADIA_SWORD.get());
         movcadiaTools(ElementusItems.MOVCADIA_SHOVEL.get());
         movcadiaTools(ElementusItems.MOVCADIA_PICKAXE.get());
         movcadiaTools(ElementusItems.MOVCADIA_AXE.get());
         movcadiaTools(ElementusItems.MOVCADIA_HOE.get());
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

        ItemProperties.register(item, modLoc("sacrifice"), (itemStack, clientLevel, livingEntity, i)
                -> isEnchantedWith(itemStack, SACRIFICE_CURSE) ? 1 : 0);
        ItemProperties.register(item, modLoc("charge_stacking"), (itemStack, clientLevel, livingEntity, i)
                -> isEnchantedWith(itemStack, CHARGE_STACKING) ? 1 : 0);
        ItemProperties.register(item, modLoc("charge_stacking"), (itemStack, clientLevel, livingEntity, i)
                -> isEnchantedWith(itemStack, RUSH) ? 1 : 0);

        ItemProperties.register(item, modLoc("charge"), (itemStack, clientLevel, livingEntity, i) -> {
            float i0 = Math.min(getCharge(itemStack), getMaxCharge(itemStack));
            float i1 = getMaxCharge(itemStack);
            return i0 / i1;
        });
    }

    private static void movcadiaTools(Item item) {
        ItemProperties.register(item, modLoc("damage_state"), (itemStack, clientLevel, livingEntity, i)
                -> (float) itemStack.getMaxDamage() / itemStack.getMaxDamage());
        ItemProperties.register(item, modLoc("empowered"), (itemStack, clientLevel, livingEntity, i)
                -> getMovcadiaEssence(itemStack) > 0 ? 1 : 0);
    }
}
