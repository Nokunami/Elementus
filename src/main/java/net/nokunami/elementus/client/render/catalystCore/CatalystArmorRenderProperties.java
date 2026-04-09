package net.nokunami.elementus.client.render.catalystCore;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nokunami.elementus.client.extensions.IClientCatalystExtension;
import net.nokunami.elementus.client.model.armor.CatalystArmorVoidGuardianModel;
import net.nokunami.elementus.client.model.geom.EModelLayers;
import net.nokunami.elementus.client.model.armor.CatalystArmorModel;
import net.nokunami.elementus.client.model.armor.CatalystBaseModel;
import net.nokunami.elementus.common.registry.tempCompat.CompatCoreRegistry;
import org.jetbrains.annotations.NotNull;

import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;

@OnlyIn(Dist.CLIENT)
public class CatalystArmorRenderProperties implements IClientCatalystExtension {
    private static boolean init;
    public static CatalystArmorModel<LivingEntity> TEST_CATALYST_ARMOR_MODEL;
    public static CatalystBaseModel<LivingEntity> CATALYST_ARMOR_MODEL;

    public static CatalystArmorVoidGuardianModel<LivingEntity> CATALYST_ARMOR_VOID_GUARDIAN_MODEL;

    public static void initializedModels() {
        init = true;
        EntityModelSet bake = Minecraft.getInstance().getEntityModels();
        TEST_CATALYST_ARMOR_MODEL = new CatalystArmorModel<>(bake.bakeLayer(EModelLayers.TEST_CATALYST_ARMOR_MODEL));
        CATALYST_ARMOR_MODEL = new CatalystBaseModel<>(bake.bakeLayer(EModelLayers.CATALYST_ARMOR_MODEL));
        CATALYST_ARMOR_VOID_GUARDIAN_MODEL = new CatalystArmorVoidGuardianModel<>(bake.bakeLayer(EModelLayers.CATALYST_ARMOR_VOID_GUARDIAN_MODEL));
    }

    @Override
    public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
        if (!init) initializedModels();

//        Optional<ItemStack> core = getEquippedCore(stack);
//        if (core.isPresent() && CustomRegistries.getCatalystCore(core.get()) == CompatCoreRegistry.CataclysmCores.IGNITIUM.get())
//            return CATALYST_ARMOR_MODEL;
        var core = CatalystCoreUtil(entity);
        if (core.hasCore()) {
            if (cataclysm) {
                if (core.getCore() == CompatCoreRegistry.CataclysmCores.VOID_GUARDIAN.get()) {
                    return CATALYST_ARMOR_VOID_GUARDIAN_MODEL;
                }
            }
        }

        return TEST_CATALYST_ARMOR_MODEL;
    }
}