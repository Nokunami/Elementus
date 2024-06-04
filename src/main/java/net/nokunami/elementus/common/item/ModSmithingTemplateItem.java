package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class ModSmithingTemplateItem extends SmithingTemplateItem {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component ANTHEKTITE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("anthektite_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component ANTHEKTITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.anthektite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ANTHEKTITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.anthektite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ANTHEKTITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.anthektite_upgrade.base_slot_description")));
    private static final Component ANTHEKTITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.anthektite_upgrade.additions_slot_description")));
    private static final Component DIARKRITE_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("diarkrite_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component DIARKRITE_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.diarkrite_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DIARKRITE_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.diarkrite_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DIARKRITE_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.diarkrite_upgrade.base_slot_description")));
    private static final Component DIARKRITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.diarkrite_upgrade.additions_slot_description")));
    private static final Component ATELIS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", new ResourceLocation("atelis_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component ATELIS_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.atelis_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ATELIS_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.atelis_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ATELIS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.atelis_upgrade.base_slot_description")));
    private static final Component ATELIS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.atelis_upgrade.additions_slot_description")));
    private static final ResourceLocation EMPTY_SLOT_HELMET = new ResourceLocation("item/empty_armor_slot_helmet");
    private static final ResourceLocation EMPTY_SLOT_CHESTPLATE = new ResourceLocation("item/empty_armor_slot_chestplate");
    private static final ResourceLocation EMPTY_SLOT_LEGGINGS = new ResourceLocation("item/empty_armor_slot_leggings");
    private static final ResourceLocation EMPTY_SLOT_BOOTS = new ResourceLocation("item/empty_armor_slot_boots");
    private static final ResourceLocation EMPTY_SLOT_HOE = new ResourceLocation("item/empty_slot_hoe");
    private static final ResourceLocation EMPTY_SLOT_AXE = new ResourceLocation("item/empty_slot_axe");
    private static final ResourceLocation EMPTY_SLOT_SWORD = new ResourceLocation("item/empty_slot_sword");
    private static final ResourceLocation EMPTY_SLOT_SHOVEL = new ResourceLocation("item/empty_slot_shovel");
    private static final ResourceLocation EMPTY_SLOT_PICKAXE = new ResourceLocation("item/empty_slot_pickaxe");
    private static final ResourceLocation EMPTY_SLOT_INGOT = new ResourceLocation("item/empty_slot_ingot");
    public ModSmithingTemplateItem(Component pAppliesTo, Component pIngredients, Component pUpdradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditonalSlotEmptyIcons) {
        super(pAppliesTo, pIngredients, pUpdradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditonalSlotEmptyIcons);
    }

    public static SmithingTemplateItem createAnthektiteUpgradeTemplate() {
        return new SmithingTemplateItem(ANTHEKTITE_UPGRADE_APPLIES_TO, ANTHEKTITE_UPGRADE_INGREDIENTS, ANTHEKTITE_UPGRADE, ANTHEKTITE_UPGRADE_BASE_SLOT_DESCRIPTION, ANTHEKTITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createAnthektiteUpgradeIconList(), createAnthektiteUpgradeMaterialList());
    }

    private static List<ResourceLocation> createAnthektiteUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createAnthektiteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static SmithingTemplateItem createDiarkriteUpgradeTemplate() {
        return new SmithingTemplateItem(DIARKRITE_UPGRADE_APPLIES_TO, DIARKRITE_UPGRADE_INGREDIENTS, DIARKRITE_UPGRADE, DIARKRITE_UPGRADE_BASE_SLOT_DESCRIPTION, DIARKRITE_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createDiarkriteUpgradeIconList(), createDiarkriteUpgradeMaterialList());
    }

    private static List<ResourceLocation> createDiarkriteUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createDiarkriteUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static SmithingTemplateItem createAtelisUpgradeTemplate() {
        return new SmithingTemplateItem(ATELIS_UPGRADE_APPLIES_TO, ATELIS_UPGRADE_INGREDIENTS, ATELIS_UPGRADE, ATELIS_UPGRADE_BASE_SLOT_DESCRIPTION, ATELIS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createAtelisUpgradeIconList(), createAtelisUpgradeMaterialList());
    }

    private static List<ResourceLocation> createAtelisUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createAtelisUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
