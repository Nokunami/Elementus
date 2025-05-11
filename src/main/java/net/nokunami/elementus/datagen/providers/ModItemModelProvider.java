package net.nokunami.elementus.datagen.providers;

import com.aetherteam.aether.item.accessories.gloves.GlovesItem;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.loaders.ItemLayerModelBuilder;
import net.minecraftforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.nokunami.elementus.datagen.ModTrimMaterials;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static net.nokunami.elementus.ModChecker.aether;
import static net.nokunami.elementus.common.item.CatalystItemUtil.*;

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
//        withExistingParent(itemName(item.get()) + "_pulling_1", mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_1")).texture("layer1", modLoc("item/" + location + "/" + "arrow_pulling_1"));
//        withExistingParent(itemName(item.get()) + "_pulling_2", mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_2")).texture("layer1", modLoc("item/" + location + "/" + "arrow_pulling_2"));
        for (int i = 0; i < 3; ++i)
            withExistingParent(itemName(item.get()) + "_drawing_" + i, mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_" + i));
//        withExistingParent(itemName(item.get()) + "_drawing_1", mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_1"));
//        withExistingParent(itemName(item.get()) + "_drawing_2", mcLoc("item/bow")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_pulling_2"));
        withExistingParent(itemName(item.get()), mcLoc("item/bow"))
                .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())))
                .override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_0"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_1"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_pulling_2"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_0"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).predicate(new ResourceLocation("draw"), 0.65F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_1"))).end()
                .override().predicate(new ResourceLocation("drawing"), 1).predicate(new ResourceLocation("draw"), 0.9F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_drawing_2"))).end();
    }

    public void chargerItem(Supplier<? extends Item> item, String location) {
        withExistingParent(itemName(item.get()) + "_gui", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())));
        withExistingParent(itemName(item.get()) + "_gui_charge_0", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_charge_0"));
        withExistingParent(itemName(item.get()) + "_gui_charge_1", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_charge_1"));
        withExistingParent(itemName(item.get()) + "_gui_charge_2", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_charge_2"));
        withExistingParent(itemName(item.get()) + "_handheld", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld"));
        withExistingParent(itemName(item.get()) + "_handheld_charge_0", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_0"))
                .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_0_emissive"))
                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1).end();
        withExistingParent(itemName(item.get()) + "_handheld_charge_1", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_1"))
                .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_1_emissive"))
                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1).end();
        withExistingParent(itemName(item.get()) + "_handheld_charge_2", mcLoc("item/handheld")).texture("layer0", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_2"))
                .texture("layer1", modLoc("item/" + location + "/" + itemName(item.get()) + "_handheld_charge_2_emissive"))
                .customLoader(ItemLayerModelBuilder::begin).emissive(15, 15, 1).end();

        withExistingParent(itemName(item.get()) + "_charge_0", new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(item.get() + "_handheld_charge_0", modLoc("item/claymore_item"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(itemName(item.get()) + "_gui_charge_0", mcLoc("generated")))).end();
        withExistingParent(itemName(item.get()) + "_charge_1", new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(item.get() + "_handheld_charge_1", modLoc("item/claymore_item"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(itemName(item.get()) + "_gui_charge_1", mcLoc("generated")))).end();
        withExistingParent(itemName(item.get()) + "_charge_2", new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(item.get() + "_handheld_charge_2", modLoc("item/claymore_item"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(itemName(item.get()) + "_gui_charge_2", mcLoc("generated")))).end();

        withExistingParent(itemName(item.get()), new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(item.get() + "_handheld", modLoc("item/claymore_item"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))))
                .perspective(ItemDisplayContext.FIXED, nested().parent(withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))))
                .perspective(ItemDisplayContext.GROUND, nested().parent(withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated")))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.65F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1"))).end()
                .override().predicate(new ResourceLocation("pulling"), 1).predicate(new ResourceLocation("pull"), 0.9F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2"))).end();
    }

    public void diarkriteChargeBlade(Supplier<? extends Item> item, String location) {
        chargedWeaponModel(item, "_gui_charge_", mcLoc("item/handheld"), location, "_charge_", 4);
        chargedWeaponModel(item, "_handheld_charge_", modLoc("item/claymore_item"), location, "_handheld_charge_", 4);
        chargedWeaponBlockingModel(item, "_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_handheld_charge_", 4);
        handheldGuiModel(item, "_charge_", mcLoc("item/handheld"), "_handheld_charge_", "_gui_charge_", 4);
        handheldGuiBlockingModel(item, "_charge_", modLoc("item/claymore_item_blocking"), "_handheld_charge_", "_gui_charge_", 4);

        chargedWeaponModel(item, "_sacrifice_gui_charge_", mcLoc("item/handheld"), location, "_sacrifice_charge_", 4);
        chargedWeaponModel(item, "_sacrifice_handheld_charge_", modLoc("item/claymore_item"), location, "_sacrifice_handheld_charge_", 4);
        chargedWeaponBlockingModel(item, "_sacrifice_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_sacrifice_handheld_charge_", 4);
        handheldGuiModel(item, "_sacrifice_charge_", modLoc("item/claymore_item"), "_sacrifice_handheld_charge_", "_sacrifice_gui_charge_", 4);
        handheldGuiBlockingModel(item, "_sacrifice_charge_", modLoc("item/claymore_item_blocking"), "_sacrifice_handheld_charge_", "_sacrifice_gui_charge_", 4);

        chargedWeaponModel(item, "_multi_gui_charge_", mcLoc("item/handheld"), location, "_multi_charge_", 8);
        chargedWeaponModel(item, "_multi_handheld_charge_", modLoc("item/claymore_item"), location, "_multi_handheld_charge_", 8);
        chargedWeaponBlockingModel(item, "_multi_handheld_charge_", modLoc("item/claymore_item_blocking"), location, "_multi_handheld_charge_", 8);
        handheldGuiModel(item, "_multi_charge_", modLoc("item/claymore_item"), "_multi_handheld_charge_", "_multi_gui_charge_", 8);
        handheldGuiBlockingModel(item, "_multi_charge_", modLoc("item/claymore_item_blocking"), "_multi_handheld_charge_", "_multi_gui_charge_", 8);

        baseModels(item, "_gui", "_handheld", modLoc("item/claymore_item"), "_handheld_blocking", modLoc("item/claymore_item_blocking"), location);
        baseModels(item, "sacrifice", "_sacrifice_gui", "_sacrifice_handheld", modLoc("item/claymore_item"), "_sacrifice_handheld_blocking", modLoc("item/claymore_item_blocking"), location);
        baseModels(item, "multi", "_multi_gui", "_multi_handheld", modLoc("item/claymore_item"), "_multi_handheld_blocking", modLoc("item/claymore_item_blocking"), location);

        withExistingParent(itemName(item.get()), modLoc("item/claymore_item")).customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(getExistingFile(modLoc("item/" + itemName(item.get()) + "_base")))).end()

                .override().predicate(mcLoc("blocking"), 1).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_blocking"))).end()
                .override().predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_base"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_blocking"))).end()
                .override().predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_base"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.4F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.4F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.6F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.6F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.8F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_charge_3_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.2F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.2F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.4F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.4F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.6F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.6F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.8F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.8F).predicate(modLoc("enchanted"), 0.1F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_sacrifice_charge_3_blocking"))).end()

                .override().predicate(modLoc("charge"), 0.11F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_0"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.11F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_0_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.22F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_1"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.22F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_1_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.33F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_2"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.33F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_2_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.44F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_3"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.44F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_3_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.55F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_4"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.55F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_4_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.66F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_5"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.66F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_5_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.77F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_6"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.77F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_6_blocking"))).end()
                .override().predicate(modLoc("charge"), 0.88F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_7"))).end()
                .override().predicate(mcLoc("blocking"), 1).predicate(modLoc("charge"), 0.88F).predicate(modLoc("enchanted"), 0.2F).model(getExistingFile(modLoc("item/" + itemName(item.get()) + "_multi_charge_7_blocking"))).end()
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

    public void armorItem(Supplier<? extends Item> item, String location) {
        if (item.get() instanceof ArmorItem armorItem) {
            ItemModelBuilder builder = withExistingParent(this.itemName(item.get()), this.mcLoc("item/generated"))
                    .texture("layer0", modLoc("item/" + location + "/" + this.itemName(item.get())));
            double index = 0.1;
            for (ResourceKey<TrimMaterial> trimMaterial : VANILLA_TRIM_MATERIALS) {
                String material = trimMaterial.location().getPath();
                String name = itemName(item.get()) + "_" + material + "_trim";
                withExistingParent(name, mcLoc("item/generated"))
                        .texture("layer0", modLoc("item/" + location + "/" + itemName(item.get())))
                        .texture("layer1", this.mcLoc("trims/items/" + armorItem.getType().getName() + "_trim_" + material));
                builder.override().predicate(new ResourceLocation("trim_type"), (float) index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
                index += 0.1;
            }
        }
        if (aether) {
            if (item.get() instanceof GlovesItem) {
                ItemModelBuilder builder = this.withExistingParent(this.itemName(item.get()), this.mcLoc("item/generated"))
                        .texture("layer0", this.modLoc("item/" + location + "/" + this.itemName(item.get())));
                double index = 0.1;
                for(Iterator var6 = ModItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
                    ResourceKey<TrimMaterial> trimMaterial = (ResourceKey)var6.next();
                    String material = trimMaterial.location().getPath();
                    String var10000 = this.itemName(item.get());
                    String name = var10000 + "_" + material + "_trim";
                    this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + "/" + this.itemName(item.get()))).texture("layer1", "aether:" + "trims/items/gloves_trim_" + material);
                    builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
                }
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

    //Aether
    public void glovesItem(Item item, String location) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + this.itemName(item)));
        double index = 0.1;

        for(Iterator<ResourceKey<TrimMaterial>> var6 = ModItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = var6.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + this.itemName(item))).texture("layer1", "aether:" + "trims/items/gloves_trim_" + material);
            builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }
    }

    //Iron's Spells and Spellbooks
    public void spellBookItem(Supplier<? extends Item> item) {
        var modLoc = "irons_spellbooks";
        var modelFile = "spellbook_model";

        withExistingParent(itemName(item.get()), new ResourceLocation("generated"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(itemName(item.get()) + "_model", modLoc(itemName(item.get())))))
                .perspective(ItemDisplayContext.GUI, this.nested().parent(withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))))
                .end();
        withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + itemName(item.get()) + "_gui"));
        withExistingParent(itemName(item.get()) + "_model", modLoc(modelFile))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + itemName(item.get()) + "_model"));
    }

    //SimplySwords
    public void simplySwordsItem(String material) {
        var modLoc = "simplyswords";
        var chakram = "chakram";
        var claymore = "claymore";
        var cutlass = "cutlass";
        var glaive = "glaive";
        var greataxe = "greataxe";
        var greathammer = "greathammer";
        var halberd = "halberd";
        var katana = "katana";
        var longsword = "longsword";
        var rapier = "rapier";
        var sai = "sai";
        var scythe = "scythe";
        var spear = "spear";
        var twinblade = "twinblade";
        var warglaive = "warglaive";

        withExistingParent(material + "_" + chakram, modLoc("item/" + modLoc + "/template_" + chakram))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + chakram));

        withExistingParent(material + "_" + claymore, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + claymore + "_model", modLoc("item/" + modLoc + "/template_" + claymore))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + claymore + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + claymore + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + claymore + "_gui"));
        withExistingParent(material + "_" + claymore + "_model", modLoc("item/" + modLoc + "/template_" + claymore))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + claymore + "_model"));

        withExistingParent(material + "_" + cutlass, mcLoc("handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + cutlass));

        withExistingParent(material + "_" + glaive, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + glaive + "_model", modLoc("item/" + modLoc + "/big_handheld"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + glaive + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + glaive + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + glaive + "_gui"));
        withExistingParent(material + "_" + glaive + "_model", modLoc("item/" + modLoc + "/big_handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + glaive + "_model"));

        withExistingParent(material + "_" + greataxe, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + greataxe + "_model", modLoc("item/" + modLoc + "/big_handheld"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + greataxe + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + greataxe + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + greataxe + "_gui"));
        withExistingParent(material + "_" + greataxe + "_model", modLoc("item/" + modLoc + "/big_handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + greataxe + "_model"));

        withExistingParent(material + "_" + greathammer, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + greathammer + "_model", modLoc("item/" + modLoc + "/template_reworked_greathammer"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + greathammer + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + greathammer + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + greathammer + "_gui"));
        withExistingParent(material + "_" + greathammer + "_model", modLoc("item/" + modLoc + "/template_reworked_greathammer"))
                .texture("texture", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + greathammer + "_model"));

        withExistingParent(material + "_" + halberd, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + halberd + "_model", modLoc("item/" + modLoc + "/long_handheld"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + halberd + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + halberd + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + halberd + "_gui"));
        withExistingParent(material + "_" + halberd + "_model", modLoc("item/" + modLoc + "/long_handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + halberd + "_model"));

        withExistingParent(material + "_" + katana, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + katana + "_model", modLoc("item/" + modLoc + "/template_" + katana))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + katana + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + katana + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + katana + "_gui"));
        withExistingParent(material + "_" + katana + "_model", modLoc("item/" + modLoc + "/template_" + katana))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + katana + "_model"));

        withExistingParent(material + "_" + longsword, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + longsword + "_model", modLoc("item/" + modLoc + "/template_" + longsword))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + longsword + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + longsword + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + longsword + "_gui"));
        withExistingParent(material + "_" + longsword + "_model", modLoc("item/" + modLoc + "/template_" + longsword))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + longsword + "_model"));

        withExistingParent(material + "_" + rapier, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + rapier + "_model", modLoc("item/" + modLoc + "/template_" + longsword))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + rapier + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + rapier + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + rapier + "_gui"));
        withExistingParent(material + "_" + rapier + "_model", modLoc("item/" + modLoc + "/template_" + longsword))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + rapier + "_model"));

        withExistingParent(material + "_" + sai, mcLoc("handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + sai));

        withExistingParent(material + "_" + scythe, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + scythe + "_model", modLoc("item/" + modLoc + "/big_handheld"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + scythe + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + scythe + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + scythe + "_gui"));
        withExistingParent(material + "_" + scythe + "_model", modLoc("item/" + modLoc + "/big_handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + scythe + "_model"));

        withExistingParent(material + "_" + spear, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + spear + "_model", modLoc("item/" + modLoc + "/big_handheld"))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + spear + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + spear + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + spear + "_gui"));
        withExistingParent(material + "_" + spear + "_model", modLoc("item/" + modLoc + "/big_handheld"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + spear + "_model"));

        withExistingParent(material + "_" + twinblade, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + twinblade + "_model", modLoc("item/" + modLoc + "/template_" + twinblade))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + twinblade + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + twinblade + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + twinblade + "_gui"));
        withExistingParent(material + "_" + twinblade + "_model", modLoc("item/" + modLoc + "/template_" + twinblade))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + twinblade + "_model"));

        withExistingParent(material + "_" + warglaive, new ResourceLocation("handheld"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(material + "_" + warglaive + "_model", modLoc("item/" + modLoc + "/template_" + warglaive))))
                .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_" + warglaive + "_gui", mcLoc("generated")))).end();
        withExistingParent(material + "_" + warglaive + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + warglaive + "_gui"));
        withExistingParent(material + "_" + warglaive + "_model", modLoc("item/" + modLoc + "/template_" + warglaive))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + material + "/" + material + "_" + warglaive + "_model"));
    }

    //Sniff's Weapons
    public void sniffsWeaponsItem(String material, String type) {
        var modLoc = "sniffsweapons";
        var greatSword = "great_sword_handheld";
        var greatAxe = "great_axe_handheld";
        var greatPickaxe = "great_pickaxe_blocking_handheld";
        var naginata = "naginata_handheld";

        if (Objects.equals(type, "greatSword")) {
            withExistingParent(material + "_great_sword", new ResourceLocation("handheld"))
                    .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(withExistingParent(material + "_great_sword" + "_model", modLoc("item/" + modLoc + "/" + greatSword))))
                    .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_great_sword" + "_gui", mcLoc("generated")))).end();
            withExistingParent(material + "_great_sword" + "_gui", mcLoc("generated"))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_sword" + "_gui"));
            withExistingParent(material + "_great_sword" + "_model", modLoc("item/" + modLoc + "/" + greatSword))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_sword" + "_model"));
        }
        if (Objects.equals(type, "greatAxe")) {
            withExistingParent(material + "_great_axe", new ResourceLocation("handheld"))
                    .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(withExistingParent(material + "_great_axe" + "_model", modLoc("item/" + modLoc + "/" + greatAxe))))
                    .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_great_axe" + "_gui", mcLoc("generated")))).end();
            withExistingParent(material + "_great_axe" + "_gui", mcLoc("generated"))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_axe" + "_gui"));
            withExistingParent(material + "_great_axe" + "_model", modLoc("item/" + modLoc + "/" + greatAxe))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_axe" + "_model"));
        }
        if (Objects.equals(type, "greatPickaxe")) {
            withExistingParent(material + "_great_pickaxe" + "_gui", mcLoc("generated"))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_pickaxe" + "_gui"));

            withExistingParent(material + "_great_pickaxe" + "_blocking_model", modLoc("item/" + modLoc + "/" + greatPickaxe))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_pickaxe" + "_model"));
            withExistingParent(material + "_great_pickaxe" + "_model", modLoc("item/" + modLoc + "/" + greatAxe))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_great_pickaxe" + "_model"));

            withExistingParent(material + "_great_pickaxe_blocking", new ResourceLocation("handheld"))
                    .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(withExistingParent(material + "_great_pickaxe_blocking" + "_model", modLoc("item/" + modLoc + "/" + greatPickaxe))))
                    .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_great_pickaxe_gui", mcLoc("generated")))).end();

            withExistingParent(material + "_great_pickaxe", new ResourceLocation("handheld"))
                    .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(withExistingParent(material + "_great_pickaxe" + "_model", modLoc("item/" + modLoc + "/" + greatAxe))))
                    .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_great_pickaxe_gui", mcLoc("generated")))).end()
                    .override().predicate(new ResourceLocation("blocking"), 1).model(getExistingFile(modLoc(material + "_great_pickaxe_blocking"))).end();
        }
        if (Objects.equals(type, "naginata")) {
            withExistingParent(material + "_naginata", new ResourceLocation("handheld"))
                    .customLoader(SeparateTransformsModelBuilder::begin)
                    .base(nested().parent(withExistingParent(material + "_naginata" + "_model", modLoc("item/" + modLoc + "/" + naginata))))
                    .perspective(ItemDisplayContext.GUI, nested().parent(withExistingParent(material + "_naginata" + "_gui", mcLoc("generated"))))
                    .end();
            withExistingParent(material + "_naginata" + "_gui", mcLoc("generated"))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_naginata" + "_gui"));
            withExistingParent(material + "_naginata" + "_model", modLoc("item/" + modLoc + "/" + naginata))
                    .texture("layer0", modLoc("item/compat/" + modLoc + "/items/" + material + "_naginata" + "_model"));
        }

        if (Objects.equals(type, "armor")) {
            withExistingParent(material + "_helm", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/feathers")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/" + material + "_helm"));
            withExistingParent(material + "_surcoat", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/surcoat")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/" + material + "_surcoat"));

            withExistingParent(material + "_horned_helm", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/long_feathers")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/" + material + "_horned_helm"));
            withExistingParent("plated_" + material + "_chestplate", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/plated_chestplate_layer")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/plated_" + material + "_chestplate"));

            withExistingParent(material + "_kabuto", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/mask")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/" + material + "_kabuto"));
            withExistingParent(material + "_do", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/do_layer")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/" + material + "_do"));

            withExistingParent("clothed_" + material + "_cuirass", mcLoc("item/generated"))
                    .texture("layer0", modLoc + ":item/clothed_cuirass_layer")
                    .texture("layer1", modLoc("item/compat/sniffsweapons/armor/clothed_" + material + "_cuirass"));
        }
    }

    //Banilla Claws
    public void banillaClawsItem(Supplier<? extends Item> item) {
        var modLoc = "vanilla_claws";
        var modelFile = "claws_hand";

        withExistingParent(itemName(item.get()), new ResourceLocation("generated"))
                .customLoader(SeparateTransformsModelBuilder::begin)
                .base(nested().parent(withExistingParent(itemName(item.get()) + "_model", modLoc(itemName(item.get())))))
                .perspective(ItemDisplayContext.GUI, this.nested().parent(withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))))
                .end();
        withExistingParent(itemName(item.get()) + "_gui", mcLoc("generated"))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + itemName(item.get()) + "_gui"));
        withExistingParent(itemName(item.get()) + "_model", modLoc("item/" + modLoc + "/" + modelFile))
                .texture("layer0", modLoc("item/compat/" + modLoc + "/" + itemName(item.get()) + "_model"));
    }
}