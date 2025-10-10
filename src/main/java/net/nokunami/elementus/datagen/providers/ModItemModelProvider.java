package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.datagen.ModTrimMaterials;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.*;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output,String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }

    protected static final List<ResourceKey<TrimMaterial>> VANILLA_TRIM_MATERIALS = List.of(
            TrimMaterials.QUARTZ,
            TrimMaterials.IRON,
            TrimMaterials.NETHERITE,
            TrimMaterials.REDSTONE,
            TrimMaterials.COPPER,
            TrimMaterials.GOLD,
            TrimMaterials.EMERALD,
            TrimMaterials.DIAMOND,
            TrimMaterials.LAPIS,
            TrimMaterials.AMETHYST,
            ModTrimMaterials.STEEL,
            ModTrimMaterials.DIARKRITE,
            ModTrimMaterials.ANTHEKTITE
    );

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
        }
    }

    protected String blockName(Supplier<? extends Block> block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block.get())).getPath();
    }

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    //Item Medthods

    public void generatedItem(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
    }

    public void handheldItem(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
    }

    public void itemCustomParentModel(Supplier<? extends  Item> item, String location, String parentModel) {
        withExistingParent(itemName(item.get()), modLoc(parentModel)).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
    }

    public void itemCustomParentModel(Supplier<? extends  Item> item, String parentModel) {
        withExistingParent(itemName(item.get()), modLoc(parentModel));
    }

    public void spawnEggItem(Supplier<? extends Item> item) {
        this.withExistingParent(this.itemName(item.get()), this.mcLoc("item/template_spawn_egg"));
    }

    public void bowItem(Supplier<? extends Item> item, String location) {
        for (int i = 0; i < 3; ++i)
            withExistingParent(itemName(item.get()) + "_pulling_" + i, mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_" + i)).texture("layer1", modLoc("item/" + location + "/" + "arrow_pulling_" + i));
        for (int i = 0; i < 3; ++i)
            withExistingParent(itemName(item.get()) + "_drawing_" + i, mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_" + i));
        withExistingParent(itemName(item.get()), mcLoc("item/bow"))
                .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())))
                .override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_0"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_1"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_2"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_0"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).predicate(new ResourceLocation("draw"), 0.65F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_1"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).predicate(new ResourceLocation("draw"), 0.9F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_2"))).end();
    }

    public void movcadiaTools(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()), mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())))
                .override().predicate(modLoc("empowered"), 1).model(
                        withExistingParent(itemName(item.get()) + "_empowered", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_empowered"))
                                .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + "_empowered_e"))
                                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1).end())
                .end();
    }

    public void diarkriteChargeBlade(Supplier<? extends Item> item, String location) {
        String sacrifice = "sacrifice";
        chargedWeaponModel(item, "_gui_charge_", mcLoc("item/handheld"), location, "_charge_", 5);
        chargedWeaponModel(item, "_handheld_charge_", modLoc("item/claymore_item"), location, "_handheld_charge_", 5);
        chargedWeaponBlockingModel(item, "_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_handheld_charge_", 5);
        handheldGuiModel(item, "_charge_", mcLoc("item/handheld"), "_handheld_charge_", "_gui_charge_", 5);
        handheldGuiBlockingModel(item, "_charge_", modLoc("item/claymore_item_blocking"), "_handheld_charge_", "_gui_charge_", 5);

        chargedWeaponModel(item, "_" + sacrifice + "_gui_charge_", mcLoc("item/handheld"), location, "_" + sacrifice + "_charge_", 5);
        chargedWeaponModel(item, "_" + sacrifice + "_handheld_charge_", modLoc("item/claymore_item"), location, "_" + sacrifice + "_handheld_charge_", 5);
        chargedWeaponBlockingModel(item, "_" + sacrifice + "_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_" + sacrifice + "_handheld_charge_", 5);
        handheldGuiModel(item, "_" + sacrifice + "_charge_", modLoc("item/claymore_item"), "_" + sacrifice + "_handheld_charge_", "_" + sacrifice + "_gui_charge_", 5);
        handheldGuiBlockingModel(item, "_" + sacrifice + "_charge_", modLoc("item/claymore_item_blocking"), "_" + sacrifice + "_handheld_charge_", "_" + sacrifice + "_gui_charge_", 5);

        baseModels(item, "_gui", "_handheld", modLoc("item/claymore_item"), "_handheld_blocking", modLoc("item/claymore_item_blocking"), location);
        baseModels(item, sacrifice, "_" + sacrifice + "_gui", "_" + sacrifice + "_handheld", modLoc("item/claymore_item"), "_" + sacrifice + "_handheld_blocking", modLoc("item/claymore_item_blocking"), location);

        withExistingParent(itemName(item.get()), modLoc("item/claymore_item")).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc("item/" + itemName(item.get()) + "_base")))).end()

                .override().predicate(mcLoc("blocking"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_blocking"))).end()
                .override().predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_base"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.16F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.16F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.32F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.32F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.48F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.48F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.64F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.64F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_4"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_4_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.2F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.16F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.4F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.32F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.6F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.48F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.64F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_3_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_4"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.8F).predicate(modLoc(sacrifice), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_" + sacrifice + "_charge_4_blocking"))).end()
        ;
    }
    public void anthektiteLongsword(Supplier<? extends Item> item, String location) {
        chargedWeaponModel(item, "_gui_charge_", mcLoc("item/handheld"), location, "_charge_", 4);
        chargedWeaponModel(item, "_handheld_charge_", modLoc("item/claymore_item"), location, "_handheld_charge_", 4);
        chargedWeaponBlockingModel(item, "_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_handheld_charge_", 4);
        handheldGuiModel(item, "_charge_", mcLoc("item/handheld"), "_handheld_charge_", "_gui_charge_", 4);
        handheldGuiBlockingModel(item, "_charge_", modLoc("item/claymore_item_blocking"), "_handheld_charge_", "_gui_charge_", 4);

        baseModels(item, "_gui", "_handheld", modLoc("item/claymore_item"), "_handheld_blocking", modLoc("item/claymore_item_blocking"), location);

        withExistingParent(itemName(item.get()), modLoc("item/claymore_item")).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc("item/" + itemName(item.get()) + "_base")))).end()

                .override().predicate(mcLoc("blocking"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.4F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.4F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.6F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.6F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3_blocking"))).end()
        ;
    }

    private void repeating1LayerModel(Supplier<? extends Item> item, String extension, ResourceLocation itemModel, String location, int loopCount) {
        for (int i = 0; i < loopCount; ++i )
            withExistingParent(itemName(item.get()) + extension + i, itemModel)
                    .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + extension + i));
    }

    private void chargedWeaponModel(Supplier<? extends Item> item, String extension, ResourceLocation itemModel, String location, String texture_extension, int loopCount) {
        for (int i = 0; i < loopCount; ++i )
            withExistingParent(itemName(item.get()) + extension + i, itemModel)
                    .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + texture_extension + i))
                    .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + texture_extension + i + "_e"))
                    .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1);
    }

    private void chargedWeaponBlockingModel(Supplier<? extends Item> item, String extension, ResourceLocation itemModel, String location, String texture_extension, int loopCount) {
        for (int i = 0; i < loopCount; ++i )
            withExistingParent(itemName(item.get()) + extension + i + "_blocking", itemModel)
                    .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + texture_extension + i))
                    .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + texture_extension + i + "_e"))
                    .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1);
    }

    private void handheldGuiModel(Supplier<? extends Item> item, String extension, ResourceLocation itemModel, String baseExtension, String guiExtension, int loopCount) {
        for (int i = 0; i < loopCount; ++i)
            withExistingParent(itemName(item.get()) + extension + i, itemModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + baseExtension + i))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i)))).end();
    }
    private void handheldGuiBlockingModel(Supplier<? extends Item> item, String extension, ResourceLocation itemModel, String baseExtension, String guiExtension, int loopCount) {
        for (int i = 0; i < loopCount; ++i)
            withExistingParent(itemName(item.get()) + extension + i + "_blocking", itemModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + baseExtension + i + "_blocking"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiExtension + i)))).end();
    }

    private void baseModels(Supplier<? extends Item> item, String guiEx, String handheldEx, ResourceLocation handheldModel, String location) {
        withExistingParent(itemName(item.get()) + guiEx, mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
        withExistingParent(itemName(item.get()) + handheldEx, handheldModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld"));

        withExistingParent(itemName(item.get()) + "_base", handheldEx).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + handheldEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
    }
    private void baseModels(Supplier<? extends Item> item, String extension, String guiEx, String handheldEx, ResourceLocation handheldModel, String location) {
        withExistingParent(itemName(item.get()) + guiEx, mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
        withExistingParent(itemName(item.get()) + handheldEx, handheldModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld"));

        withExistingParent(itemName(item.get()) + "_" + extension + "_base", handheldEx).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + handheldEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
    }

    private void baseModels(Supplier<? extends Item> item, String guiEx, String handheldEx, ResourceLocation handheldModel, String blockingEx, ResourceLocation blockingModel, String location) {
        withExistingParent(itemName(item.get()) + guiEx, mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
        withExistingParent(itemName(item.get()) + handheldEx, handheldModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld"));
        withExistingParent(itemName(item.get()) + blockingEx, blockingModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld"));

        withExistingParent(itemName(item.get()) +  "_base", handheldModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + handheldEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
        withExistingParent(itemName(item.get()) + "_blocking", blockingModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + blockingEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
    }
    private void baseModels(Supplier<? extends Item> item, String extension, String guiEx, String handheldEx, ResourceLocation handheldModel, String blockingEx, ResourceLocation blockingModel, String location) {
        withExistingParent(itemName(item.get()) + guiEx, mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) +  "_" + extension));
        withExistingParent(itemName(item.get()) + handheldEx, handheldModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_" + extension +  "_handheld"));
        withExistingParent(itemName(item.get()) + blockingEx, blockingModel).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_" + extension +  "_handheld"));

        withExistingParent(itemName(item.get()) + "_" + extension +  "_base", handheldModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + handheldEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
        withExistingParent(itemName(item.get()) + "_" + extension +  "_blocking", handheldModel).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + blockingEx))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + guiEx))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + guiEx)))).end();
    }

    public void tridentModel(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()) + "_gui", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + item.get()));
        withExistingParent(itemName(item.get()) + "_held", modLoc("item/template_trident")).texture("layer0", modLoc("item/" + location + "/" + item.get() + "_model"));
        withExistingParent(itemName(item.get()) + "_throwing_model", modLoc("item/template_trident_throwing")).texture("layer0", modLoc("item/" + location + "/" + item.get() + "_model"));

        withExistingParent(item.get() + "_throwing", mcLoc("item/handheld")).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + "_throwing_model"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + "_gui"))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + "_gui"))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + "_gui")))).end();
        withExistingParent(itemName(item.get()), mcLoc("item/handheld")).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc(item.get() + "_held"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(getExistingFile(modLoc(item.get() + "_gui"))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(getExistingFile(modLoc(item.get() + "_gui"))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(getExistingFile(modLoc(item.get() + "_gui")))).end()
                .override().predicate(mcLoc("throwing"), 1).model(getExistingFile(modLoc(item.get() + "_throwing")));
    }

    public void shieldItem(Supplier<? extends  Item> item, String location) {
        withExistingParent(itemName(item.get()) + "_blocking", modLoc("item/elementus_model_shield_blocking")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
        withExistingParent(itemName(item.get()), modLoc("item/elementus_model_shield")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())))
                .override().predicate(new ResourceLocation("blocking"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_blocking"))).end();
    }

    public void twoLayeredItem(String itemModel,Supplier<? extends Item> layer0, Supplier<? extends Item> layer1, String location) {
        withExistingParent(itemName(layer0.get()), mcLoc("item/" + itemModel))
                .texture("layer0", modLoc("item/" + "/" + location + "/" + itemName(layer0.get())))
                .texture("layer1", modLoc("item/" + "/" + location + "/" + itemName(layer1.get())));
    }

    public void emmisiveItemModel(String itemModel, Supplier<? extends Item> layer0, Supplier<? extends Item> layer1, String location) {
        withExistingParent(itemName(layer0.get()), mcLoc("item/" + itemModel)).customLoader(ItemLayerModelBuilder::begin)
                .emissive(15, 15, 1).end()
                .texture("layer0", modLoc("item/" + "/" + location + "/" + itemName(layer0.get())))
                .texture("layer1", modLoc("item/" + "/" + location + "/" + itemName(layer1.get())));
    }

    public void armorItem(RegistryObject<Item> item, String loc) {
        if(item.get() instanceof ArmorItem armorItem) {
            double trimValue = 0.1F;
            for (ResourceKey<TrimMaterial> trimMat : VANILLA_TRIM_MATERIALS) {
                String trimPath = "trims/items/" + armorItem.getType().getName() + "_trim_" + trimMat.location().getPath();
                String currentTrimName = "item/" + armorItem + "_" + trimMat.location().getPath() + "_trim";
                ResourceLocation trimResLoc = mcLoc(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = modLoc(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName).parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", modLoc("item/" + loc + "/" + itemName(item.get())))
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemName(item.get()), mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), (float) trimValue).end()
                        .texture("layer0", modLoc("item/" + loc + "/" + itemName(item.get())));
                trimValue += 0.1;
            }
        }
    }

    public void catalystArmor(Supplier<? extends Item> item) {
        String predicate = "catalyst";

        withExistingParent(itemName(item.get()), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                .override().predicate(modLoc(predicate), 0.11F).model(
                        withExistingParent(itemName(item.get()) + "_" + netherStar, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + netherStar))).end()
                .override().predicate(modLoc(predicate), 0.12F).model(
                        withExistingParent(itemName(item.get()) + "_" + ignitium, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + ignitium))).end()
                .override().predicate(modLoc(predicate), 0.13F).model(
                        withExistingParent(itemName(item.get()) + "_" + arcane, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + arcane))).end()
                .override().predicate(modLoc(predicate), 0.14F).model(
                        withExistingParent(itemName(item.get()) + "_" + heartSea, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + heartSea))).end()
                .override().predicate(modLoc(predicate), 0.15F).model(
                        withExistingParent(itemName(item.get()) + "_" + totem, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + totem))).end()
                .override().predicate(modLoc(predicate), 0.16F).model(
                        withExistingParent(itemName(item.get()) + "_" + cursium, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + cursium))).end()
                .override().predicate(modLoc(predicate), 0.17F).model(
                        withExistingParent(itemName(item.get()) + "_" + witheredNetherStar, mcLoc("item/generated"))
                                .texture("layer0", modLoc("item/armor/catalyst_chestplate"))
                                .texture("layer1", modLoc("item/armor/catalyst_" + witheredNetherStar))).end();
    }

    //Block Item methods
    public void blockItem(Supplier<? extends Block> block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }

    public void itemBlock(Supplier<? extends Block> block, String suffix) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block) + suffix));
    }

    public void itemBlockGenerated(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + location + "/" + itemName(item.get())));
    }

    public void fenceItem(Supplier<? extends Block> block, Supplier<? extends Block> baseBlock, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock), location + "/"));
    }

    public void buttonItem(Supplier<? extends Block> block, Supplier<? extends Block> baseBlock, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock), location + "/"));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(wall.get())).getPath(), texture(blockName(fullBlock)));
    }
}