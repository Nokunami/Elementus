package net.nokunami.elementus.client.render.catalystCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.nokunami.elementus.api.IClientCatalystExtension;
import net.nokunami.elementus.client.model.ModModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystArmorModel;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.client.model.armor.ExtendedArmorModel;
import net.nokunami.elementus.common.catalystCore.CompatCoreRegistry;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EItems;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CatalystArmorRenderProperties implements IClientCatalystExtension {
    private static boolean init;
    public static CatalystArmorModel<LivingEntity> TEST_CATALYST_ARMOR_MODEL;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        TEST_CATALYST_ARMOR_MODEL = new CatalystArmorModel<>(bake.bakeLayer(ModModelLayers.TEST_CATALYST_ARMOR_MODEL));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(ModModelLayers.CATALYST_ARMOR_MODEL));
    }

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
        if (!init) initializedModels();
//        ItemStack stack = entity.getItemBySlot(EquipmentSlot.CHEST);
//        if (stack.is(EItems.TEST_CATALYST_CHESTPLATE.get())) {
//            CustomRegistries.getCatalystCore(stack).getCoreStack();
//        }
        Optional<ItemStack> core = getEquippedCore(stack);
        if (core.isPresent() && CustomRegistries.getCatalystCore(core.get()) == CompatCoreRegistry.CataclysmCores.IGNITIUM.get())
            return CATALYST_ARMOR_MODEL;

        return original;
    }
}