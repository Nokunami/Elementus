package net.nokunami.elementus.common.item;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.ModList;
import net.nokunami.elementus.common.compat.sniffsweapons.SWModItems;
import net.nokunami.elementus.common.registry.ModItems;

public class ElementusShield {
     public ElementusShield() {

     }

     public static void addShieldItemProperties() {
         makeShield((Item) ModItems.STEEL_SHIELD.get());
         makeShield((Item) ModItems.ANTHEKTITE_SHIELD.get());
         makeShield((Item) ModItems.DIARKRITE_SHIELD.get());

         if (ModList.get().isLoaded("sniffsweapons")) {
             makeShield((Item) SWModItems.STEEL_GREAT_PICKAXE.get());
             makeShield((Item) SWModItems.DIARKRITE_GREAT_PICKAXE.get());
             makeShield((Item) SWModItems.ANTHEKTITE_GREAT_PICKAXE.get());
         }
     }


    private static void makeShield(Item item) {
        ItemProperties.register(item, new ResourceLocation("blocking"), (itemStack, clientLevel, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }
}
