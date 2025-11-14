package net.nokunami.elementus.common.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.nokunami.elementus.common.registry.EItems;
import org.infernalstudios.archeryexp.util.BowProperties;
import org.infernalstudios.archeryexp.util.BowUtil;

import static net.nokunami.elementus.Elementus.modLoc;
import static net.nokunami.elementus.ModChecker.archeryExp;
import static net.nokunami.elementus.common.item.EItemUtil.getMovcadiaEssence;
import static net.nokunami.elementus.common.item.unique.CatalystArmorItem.catalystActivator;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;
import static net.nokunami.elementus.common.item.unique.DiarkriteChargeBlade.*;
import static net.nokunami.elementus.common.registry.EEnchantments.*;

public class ItemPredicateRegister {

     public static void registerItemPredicate() {
         shieldBlocking(EItems.STEEL_SHIELD.get());
         shieldBlocking(EItems.ANTHEKTITE_SHIELD.get());
         shieldBlocking(EItems.DIARKRITE_SHIELD.get());
         catalystArmor(EItems.CATALYST_CHESTPLATE.get());

         if (archeryExp) {
             aeComapt(EItems.STEEL_BOW.get());
             aeComapt(EItems.DIARKRITE_BOW.get());
             aeComapt(EItems.ANTHEKTITE_BOW.get());
         } else {
             bowPull(EItems.STEEL_BOW.get());
             bowPull(EItems.DIARKRITE_BOW.get());
             bowPull(EItems.ANTHEKTITE_BOW.get());
         }

         chargeBlade(EItems.DIARKRITE_CHARGE_BLADE.get());
         chargeBlade(EItems.ANTHEKTITE_CHARGE_BLADE.get());

         movcadiaTools(EItems.MOVCADIA_SWORD.get());
         movcadiaTools(EItems.MOVCADIA_SHOVEL.get());
         movcadiaTools(EItems.MOVCADIA_PICKAXE.get());
         movcadiaTools(EItems.MOVCADIA_AXE.get());
         movcadiaTools(EItems.MOVCADIA_HOE.get());

         throwing(EItems.WRATH_TRIDENT.get());
     }

    private static void shieldBlocking(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, level, entity, i)
                -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    private static void throwing(Item item) {
        ItemProperties.register(item, new ResourceLocation("throwing"), (itemStack, level, entity, i)
                -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }

    private static void catalystArmor(Item item) {
         ItemProperties.register(item, new ResourceLocation("elementus", "catalyst"), (itemStack, level, entity, i) -> switch (catalystActivator(itemStack)) {
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
        ItemProperties.register(item, new ResourceLocation("pulling"), (itemStack, level, entity, i)
                -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, new ResourceLocation("pull"), (itemStack, level, entity, i)
                -> entity != null ? entity.getUseItem() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() - entity.getUseItemRemainingTicks()) / 20.0F : 0.0F);
    }

    private static void aeComapt(Item item) {
        ItemProperties.register(item, new ResourceLocation("drawing"), (itemStack, level, entity, i)
                -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, new ResourceLocation("draw"), (itemStack, level, entity, i) -> {
                    if (entity != null && entity.getUseItem() == itemStack) {
                        BowProperties properties = (BowProperties)itemStack.getItem();
                        return BowUtil.getPowerForDrawTime(itemStack.getUseDuration() - entity.getUseItemRemainingTicks(), properties);
                    } else {
                        return 0.0F;
                    }
        });
    }

    private static void chargeBlade(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, level, entity, i)
                -> entity != null && entity.isUsingItem() && entity.getUseItem() == itemStack ? 1.0F : 0.0F);

        ItemProperties.register(item, modLoc("sacrifice"), (itemStack, level, entity, i)
                -> EItemUtil.enchantedWith(itemStack, SACRIFICE_CURSE) ? 1 : 0);
        ItemProperties.register(item, modLoc("charge_stacking"), (itemStack, level, entity, i)
                -> EItemUtil.enchantedWith(itemStack, CHARGE_STACKING) ? 1 : 0);
        ItemProperties.register(item, modLoc("charge_stacking"), (itemStack, level, entity, i)
                -> EItemUtil.enchantedWith(itemStack, RUSH) ? 1 : 0);

        ItemProperties.register(item, modLoc("charge"), (itemStack, level, entity, i) -> {
            float i0 = Math.min(getCharge(itemStack), getMaxCharge(itemStack));
            float i1 = getMaxCharge(itemStack);
            return i0 / i1;
        });
    }

    private static void movcadiaTools(Item item) {
        ItemProperties.register(item, modLoc("damage_state"), (itemStack, level, entity, i)
                -> (float) itemStack.getMaxDamage() / itemStack.getMaxDamage());
        ItemProperties.register(item, modLoc("empowered"), (itemStack, level, entity, i)
                -> getMovcadiaEssence(itemStack) > 0 ? 1 : 0);
    }
}
