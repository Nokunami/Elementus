package net.nokunami.elementus.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EchoingMirrorItem extends Item {
    private static final ChatFormatting TITLE_FORMAT;
    private static final ChatFormatting DESC;
    private static final Component APPLIES_TO_TITLE;
    private static final Component MIRROR_SWP;
    private static final Component DESCRIPTION_1;
    private static final Component DESCRIPTION_2;
    private final Component appliesTo;

    public EchoingMirrorItem(Component appliesTo) {
        super(new Properties().fireResistant());
        this.appliesTo = appliesTo;
    }

    public static final EchoingMirrorItem createMirror() {
        return new EchoingMirrorItem(MIRROR_SWP);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(CommonComponents.EMPTY);
        pTooltipComponents.add(APPLIES_TO_TITLE);
        pTooltipComponents.add(CommonComponents.space().append(this.appliesTo));
        pTooltipComponents.add(CommonComponents.EMPTY);
        pTooltipComponents.add(DESCRIPTION_1);
        pTooltipComponents.add(DESCRIPTION_2);
    }

    static {
        TITLE_FORMAT = ChatFormatting.DARK_AQUA;
        DESC = ChatFormatting.YELLOW;
        APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("echoing_mirror.applies_to"))).withStyle(TITLE_FORMAT);
        MIRROR_SWP = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("echoing_mirror.mirror_swap"))).withStyle(TITLE_FORMAT);
        DESCRIPTION_1 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("echoing_mirror.description_1"))).withStyle(DESC);
        DESCRIPTION_2 = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("echoing_mirror.description_2"))).withStyle(DESC);
    }
}
