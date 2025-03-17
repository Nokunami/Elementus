package net.nokunami.elementus.event;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.Etags;

import static net.nokunami.elementus.Elementus.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TooltipEvents {

    @SubscribeEvent
    public void catalystCoreItem(ItemTooltipEvent event) {
        Item item = event.getItemStack().getItem();
        ItemStack itemStack = event.getItemStack();
        if (!itemStack.isEmpty() && itemStack.is(Etags.Items.CATALYST_ITEMS)) {
            event.getToolTip().add(Component.translatable("elementus.catalyst_core_item.desc"));
        }
    }
}
