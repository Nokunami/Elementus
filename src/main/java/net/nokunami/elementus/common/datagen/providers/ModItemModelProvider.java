package net.nokunami.elementus.common.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.datagen.ModTirmMaterials;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {
    private static final LinkedHashMap<ResourceKey<TrimMaterial>, Float> TRIM_MATERIALS = new LinkedHashMap<>();
    static
    {
//        TRIM_MATERIALS.put(TrimMaterials.QUARTZ, 0.1F);
//        TRIM_MATERIALS.put(TrimMaterials.IRON, 0.2F);
//        TRIM_MATERIALS.put(TrimMaterials.NETHERITE, 0.3F);
//        TRIM_MATERIALS.put(TrimMaterials.REDSTONE, 0.4F);
//        TRIM_MATERIALS.put(TrimMaterials.COPPER, 0.5F);
//        TRIM_MATERIALS.put(TrimMaterials.GOLD, 0.6F);
//        TRIM_MATERIALS.put(TrimMaterials.EMERALD, 0.7F);
//        TRIM_MATERIALS.put(TrimMaterials.DIAMOND, 0.8F);
//        TRIM_MATERIALS.put(TrimMaterials.LAPIS, 0.9F);
//        TRIM_MATERIALS.put(TrimMaterials.AMETHYST, 1.0F);
//        TRIM_MATERIALS.put(ModTirmMaterials.STEEL, 0.22F);
//        TRIM_MATERIALS.put(ModTirmMaterials.DIARKRITE, 0.24F);
//        TRIM_MATERIALS.put(ModTirmMaterials.ANTHEKTITE, 0.25F);
    }
    public ModItemModelProvider(PackOutput output,String modid, ExistingFileHelper existingFileHelper) {
        super(output, "elementus", existingFileHelper);
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
            ModTirmMaterials.STEEL,
            ModTirmMaterials.DIARKRITE,
            ModTirmMaterials.ANTHEKTITE);


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

    public ItemModelBuilder armorItem(Supplier<? extends Item> item) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/armor/" + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder generatedItem(Supplier<? extends Item> item, String location) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder handheldItem(Supplier<? extends Item> item, String location) {
        return withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
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

    public void helmetItem(Supplier<? extends  Item> item, String location) {
        this.armorItem(item.get(), location, "helmet");
    }

    public void chestplateItem(Supplier<? extends  Item> item, String location) {
        this.armorItem(item.get(), location, "chestplate");
    }

    public void leggingsItem(Supplier<? extends  Item> item, String location) {
        this.armorItem(item.get(), location, "leggings");
    }

    public void bootsItem(Supplier<? extends  Item> item, String location) {
        this.armorItem(item.get(), location, "boots");
    }

    public void armorItem(Item item, String location, String type) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + this.itemName(item)));
        double index = 0.1;
        for (ResourceKey<TrimMaterial> trimMaterial : VANILLA_TRIM_MATERIALS) {
            String material = trimMaterial.location().getPath();
            String name = this.itemName(item) + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated"))
                    .texture("layer0", this.modLoc("item/" + location + this.itemName(item)))
                    .texture("layer1", this.mcLoc("trims/items/" + type + "_trim_" + material));
            builder.override().predicate(new ResourceLocation("trim_type"), (float) index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
            index += 0.1;
        }
    }

    public void glovesItem(Item item, String location) {
        ItemModelBuilder builder = this.withExistingParent(this.itemName(item), this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + this.itemName(item)));
        double index = 0.1;

        for(Iterator var6 = ModItemModelProvider.VANILLA_TRIM_MATERIALS.iterator(); var6.hasNext(); index += 0.1) {
            ResourceKey<TrimMaterial> trimMaterial = (ResourceKey)var6.next();
            String material = trimMaterial.location().getPath();
            String var10000 = this.itemName(item);
            String name = var10000 + "_" + material + "_trim";
            this.withExistingParent(name, this.mcLoc("item/generated")).texture("layer0", this.modLoc("item/" + location + this.itemName(item))).texture("layer1", "aether:" + "trims/items/gloves_trim_" + material);
            builder.override().predicate(new ResourceLocation("trim_type"), (float)index).model(this.getExistingFile(this.modLoc("item/" + name))).end();
        }

    }
}
