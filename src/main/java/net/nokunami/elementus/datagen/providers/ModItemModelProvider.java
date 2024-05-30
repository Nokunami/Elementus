package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.ForgeRegistries;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.datagen.MaterialTrim;
import net.nokunami.elementus.registry.ModBlocks;
import net.nokunami.elementus.registry.ModItems;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static net.minecraft.data.models.ItemModelGenerators.TRIM_TYPE_PREDICATE_ID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,String modid, ExistingFileHelper existingFileHelper) {
        super(output, modid, existingFileHelper);
    }

    @Override
    protected void registerModels() {
    }

    protected static final List<ResourceKey<TrimMaterial>> VANILLA_TRIM_MATERIALS = List.of(TrimMaterials.QUARTZ, TrimMaterials.IRON, TrimMaterials.NETHERITE, TrimMaterials.REDSTONE, TrimMaterials.COPPER, TrimMaterials.GOLD, TrimMaterials.EMERALD, TrimMaterials.DIAMOND, TrimMaterials.LAPIS, TrimMaterials.AMETHYST);


    public String blockName(Block block) {
        ResourceLocation location = ForgeRegistries.BLOCKS.getKey(block);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown block: " + block.toString());
        }
    }

    public String itemName(Item item) {
        ResourceLocation location = ForgeRegistries.ITEMS.getKey(item);
        if (location != null) {
            return location.getPath();
        } else {
            throw new IllegalStateException("Unknown item: " + item.toString());
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

    public ItemModelBuilder normalItemIngredient(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/ingredients/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder toolItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/tools/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder compatNormalItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/compat/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder compatToolItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/compat/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
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

    public void glovesItem(Item item, String location) {
        ItemModelBuilder builder = (ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(this.itemName(item), this.mcLoc("item/generated"))).texture("layer0", this.modLoc("item/" + location + this.itemName(item)));
        double index = 0.1;

        for(Iterator var6 = ModItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = (ResourceKey)var6.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            ((ItemModelBuilder)((ItemModelBuilder)this.withExistingParent(name, this.mcLoc("item/generated"))).texture("layer0", this.modLoc("item/" + location + this.itemName(item)))).texture("layer1", "aether:" + "trims/items/gloves_trim_" + material);
            builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }

    }
}
