package net.nokunami.elementus.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.nokunami.elementus.Compat.farmersdelight.FDItemsRegistry;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.registry.BlocksRegistry;
import net.nokunami.elementus.registry.ItemsRegistry;

import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.data.models.ItemModelGenerators.TRIM_TYPE_PREDICATE_ID;

public class ProviderItemModel extends ItemModelProvider {
    public ProviderItemModel(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Elementus.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        normalItem(ItemsRegistry.CRUDE_STEEL);
        normalItem(ItemsRegistry.STEEL_SCRAP);
        normalItem(ItemsRegistry.STEEL_INGOT);
        normalItem(ItemsRegistry.STEEL_NUGGET);
        normalItem(ItemsRegistry.ANTHEKTITE_SCRAP);
        normalItem(ItemsRegistry.ANTHEKTITE_INGOT);
        normalItem(ItemsRegistry.DIARKRITE_INGOT);

        normalItem(ItemsRegistry.ANTHEKTITE_UPGRADE_SMITHING_TEMPLATE);
        normalItem(ItemsRegistry.DIARKRITE_UPGRADE_SMITHING_TEMPLATE);

        toolItem(ItemsRegistry.STEEL_SWORD);
        toolItem(ItemsRegistry.STEEL_SHOVEL);
        toolItem(ItemsRegistry.STEEL_PICKAXE);
        toolItem(ItemsRegistry.STEEL_AXE);
        toolItem(ItemsRegistry.STEEL_HOE);

        toolItem(ItemsRegistry.ANTHEKTITE_SWORD);
        toolItem(ItemsRegistry.ANTHEKTITE_SHOVEL);
        toolItem(ItemsRegistry.ANTHEKTITE_PICKAXE);
        toolItem(ItemsRegistry.ANTHEKTITE_AXE);
        toolItem(ItemsRegistry.ANTHEKTITE_HOE);

        toolItem(ItemsRegistry.DIARKRITE_SWORD);
        toolItem(ItemsRegistry.DIARKRITE_SHOVEL);
        toolItem(ItemsRegistry.DIARKRITE_PICKAXE);
        toolItem(ItemsRegistry.DIARKRITE_AXE);
        toolItem(ItemsRegistry.DIARKRITE_HOE);

        trimmedArmorItem(ItemsRegistry.STEEL_HELEMT);
        trimmedArmorItem(ItemsRegistry.STEEL_CHESTPLATE);
        trimmedArmorItem(ItemsRegistry.STEEL_LEGGINGS);
        trimmedArmorItem(ItemsRegistry.STEEL_BOOTS);

        trimmedArmorItem(ItemsRegistry.ANTHEKTITE_HELEMT);
        trimmedArmorItem(ItemsRegistry.ANTHEKTITE_CHESTPLATE);
        trimmedArmorItem(ItemsRegistry.ANTHEKTITE_LEGGINGS);
        trimmedArmorItem(ItemsRegistry.ANTHEKTITE_BOOTS);

        trimmedArmorItem(ItemsRegistry.DIARKRITE_HELEMT);
        trimmedArmorItem(ItemsRegistry.DIARKRITE_CHESTPLATE);
        trimmedArmorItem(ItemsRegistry.DIARKRITE_LEGGINGS);
        trimmedArmorItem(ItemsRegistry.DIARKRITE_BOOTS);

        block(BlocksRegistry.STEEL_BLOCK);
        block(BlocksRegistry.ANTHEKTITE_BLOCK);
        block(BlocksRegistry.DIARKRITE_BLOCK);
        block(BlocksRegistry.REMNANT);

        if (ModList.get().isLoaded("farmersdelight")) {
            toolItem(FDItemsRegistry.STEEL_KNIFE);
            toolItem(FDItemsRegistry.ANTHEKTITE_KNIFE);
            toolItem(FDItemsRegistry.DIARKRITE_KNIFE);
        }
    }

    protected String blockName(Supplier<? extends Block> block) {
        return ForgeRegistries.BLOCKS.getKey(block.get()).getPath();
    }

    private ResourceLocation texture(String name) {
        return modLoc("block/" + name);
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block) {
        return blockFlat(block, blockName(block));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, Supplier<? extends Block> fullBlock) {
        return blockFlat(block, blockName(fullBlock));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + name));
    }

    public ItemModelBuilder blockFlatWithItemName(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name));
    }

    public ItemModelBuilder normalItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
    }

    public ItemModelBuilder twoLayered(String name, ResourceLocation texture, ResourceLocation overlayTexture) {
        existingFileHelper.trackGenerated(overlayTexture, TEXTURE);
        return withExistingParent(name, "item/generated")
                .texture("layer0", texture)
                .texture("layer1", overlayTexture);
    }

    public void trimmedArmorItem(Supplier<? extends ArmorItem> item) {
        ResourceLocation texture = TextureMapping.getItemTexture(item.get());

        var model = normalItem(item);

        MaterialTrim.TRIM_MATERIALS.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    var material = entry.getKey();
                    var itemModelIndex = entry.getValue();

                    var overlayName = item.get().getType().getName() + "_trim_" + material;

                    ResourceLocation overlayTexture = (new ResourceLocation(overlayName)).withPrefix("trims/items/");
                    var overrideModel = twoLayered(texture.getPath() + "_" + material + "_trim", texture, overlayTexture);

                    model.override()
                            .model(overrideModel)
                            .predicate(TRIM_TYPE_PREDICATE_ID, itemModelIndex);
                });

    }
}
