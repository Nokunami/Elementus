package nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static nokunami.elementus.Elementus.modLoc;

public class ModSmithingTemplateItem extends Item {
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component INGREDIENTS_TITLE = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.ingredients"))).withStyle(TITLE_FORMAT);
    private static final Component APPLIES_TO_TITLE = Component.translatable(Util.makeDescriptionId("item", new ResourceLocation("smithing_template.applies_to"))).withStyle(TITLE_FORMAT);
    private static final Component ATELIS_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", modLoc("atelis_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component ATELIS_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.atelis_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ATELIS_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.atelis_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component ATELIS_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.atelis_upgrade.base_slot_description")));
    private static final Component ATELIS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.atelis_upgrade.additions_slot_description")));
    private static final Component WEAPON_FRAGMENT = Component.translatable(Util.makeDescriptionId("upgrade", modLoc("weapon_fragment"))).withStyle(TITLE_FORMAT);
    private static final Component WEAPON_FRAGMENT_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.weapon_fragment.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component WEAPON_FRAGMENT_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.weapon_fragment.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component WEAPON_FRAGMENT_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.weapon_fragment.base_slot_description")));
    private static final Component WEAPON_FRAGMENT_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", modLoc("smithing_template.weapon_fragment.additions_slot_description")));
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
    private final Component appliesTo;
    private final Component ingredients;
    private final Component upgradeDescription;
    private final Component baseSlotDescription;
    private final Component additionsSlotDescription;
    private final List<ResourceLocation> baseSlotEmptyIcons;
    private final List<ResourceLocation> additionalSlotEmptyIcons;

    public ModSmithingTemplateItem(Component pAppliesTo, Component pIngredients, Component pUpdradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditonalSlotEmptyIcons) {
        super(new Item.Properties());
        this.appliesTo = pAppliesTo;
        this.ingredients = pIngredients;
        this.upgradeDescription = pUpdradeDescription;
        this.baseSlotDescription = pBaseSlotDescription;
        this.additionsSlotDescription = pAdditionsSlotDescription;
        this.baseSlotEmptyIcons = pBaseSlotEmptyIcons;
        this.additionalSlotEmptyIcons = pAdditonalSlotEmptyIcons;
    }

    public static ModSmithingTemplateItem createAtelisUpgradeTemplate() {
        return new ModSmithingTemplateItem(ATELIS_UPGRADE_APPLIES_TO, ATELIS_UPGRADE_INGREDIENTS, ATELIS_UPGRADE, ATELIS_UPGRADE_BASE_SLOT_DESCRIPTION, ATELIS_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createAtelisUpgradeIconList(), createAtelisUpgradeMaterialList());
    }

    private static List<ResourceLocation> createAtelisUpgradeIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createAtelisUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public static ModSmithingTemplateItem createWeaponFragment() {
        return new ModSmithingTemplateItem(WEAPON_FRAGMENT_APPLIES_TO, WEAPON_FRAGMENT_INGREDIENTS, WEAPON_FRAGMENT, WEAPON_FRAGMENT_BASE_SLOT_DESCRIPTION, WEAPON_FRAGMENT_ADDITIONS_SLOT_DESCRIPTION, createWeaponFragmentIconList(), createWeaponFragmentMaterialList());
    }

    private static List<ResourceLocation> createWeaponFragmentIconList() {
        return List.of(EMPTY_SLOT_HELMET, EMPTY_SLOT_SWORD, EMPTY_SLOT_CHESTPLATE, EMPTY_SLOT_PICKAXE, EMPTY_SLOT_LEGGINGS, EMPTY_SLOT_AXE, EMPTY_SLOT_BOOTS, EMPTY_SLOT_HOE, EMPTY_SLOT_SHOVEL);
    }

    private static List<ResourceLocation> createWeaponFragmentMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }

    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, @NotNull List<Component> pTooltipComponents, @NotNull TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
        pTooltipComponents.add(this.upgradeDescription);
        pTooltipComponents.add(CommonComponents.EMPTY);
        pTooltipComponents.add(APPLIES_TO_TITLE);
        pTooltipComponents.add(CommonComponents.space().append(this.appliesTo));
        pTooltipComponents.add(INGREDIENTS_TITLE);
        pTooltipComponents.add(CommonComponents.space().append(this.ingredients));
    }

    public Component getBaseSlotDescription() {
        return this.baseSlotDescription;
    }

    public Component getAdditionSlotDescription() {
        return this.additionsSlotDescription;
    }

    public List<ResourceLocation> getBaseSlotEmptyIcons() {
        return this.baseSlotEmptyIcons;
    }

    public List<ResourceLocation> getAdditionalSlotEmptyIcons() {
        return this.additionalSlotEmptyIcons;
    }
}
