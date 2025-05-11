package net.nokunami.elementus.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.Elementus.modLoc;

public class SteelGolemUpgradeItem extends Item {
    private static final String TEX_FOLDER = "textures/entity/golem/steel_golem/";
    private final int protection;
    private final int toughness;
    private final boolean pushable;
    private final ResourceLocation texture;
    public String identifier;

    public void onArmorTick(ItemStack stack, Level level, LivingEntity entity) {
        if (identifier.equals("reinforced_plating")) {
            entity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 0, 1, false, true));
        }
    }


    public SteelGolemUpgradeItem(int protection, int toughness, boolean pushable, String identifier, Item.Properties properties) {
        this(protection, toughness, pushable, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }

    public SteelGolemUpgradeItem(int protection, int toughness, String identifier, Item.Properties properties) {
        this(protection, toughness, false, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }


    public SteelGolemUpgradeItem(int protection, boolean pushable, String identifier, Item.Properties properties) {
        this(protection, 0, pushable, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }

    public SteelGolemUpgradeItem(int protection, String identifier, Item.Properties properties) {
        this(protection, 0, false, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }


    public SteelGolemUpgradeItem(boolean pushable, String identifier, Item.Properties properties) {
        this(0, 0, pushable, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }

    public SteelGolemUpgradeItem(String identifier, Item.Properties properties) {
        this(0, 0, false, modLoc(TEX_FOLDER + "armor/golem_armor_" + identifier + ".png"), properties);
        this.identifier = identifier;
    }

    /**
     *
     * @param protection the given protection level of the {@code HorseArmorItem}
     * @param id the texture path identifier for the {@code DyeableHorseArmorItem}, {@link
     * net.minecraft.world.item.HorseArmorItem}
     * @param properties the item properties
     */
    public SteelGolemUpgradeItem(int protection, int toughness, boolean pushable, ResourceLocation id, Item.Properties properties) {
        super(properties);
        this.protection = protection;
        this.toughness = toughness;
        this.pushable = pushable;
        this.texture = id;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public int getProtection() {
        return this.protection;
    }

    public int getToughness() {
        return this.toughness;
    }

    public boolean getPushable() {
        return this.pushable;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, components, flag);
        components.add(Component.translatable("item.elementus.golem_upgrade." + identifier).withStyle(ChatFormatting.GRAY));
        if (this.protection != 0) components.add(Component.translatable("item.elementus.golem_upgrade_armor", protection).withStyle(ChatFormatting.BLUE));
        if (this.toughness != 0) components.add(Component.translatable("item.elementus.golem_upgrade_toughness").append(CommonComponents.SPACE).append(String.valueOf(this.toughness)).withStyle(ChatFormatting.BLUE));
    }

    @Override
    public @NotNull String getDescriptionId() {
        return "item." + MODID + ".golem_upgrade";
    }
}
