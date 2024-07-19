package net.nokunami.elementus.datagen.providers;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.nokunami.elementus.datagen.ModTirmMaterials;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

public class ModItemModelProvider extends ItemModelProvider {

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

    protected ResourceLocation texture(String name) {
        return this.modLoc("block/" + name);
    }

    protected ResourceLocation texture(String name, String location) {
        return this.modLoc("block/" + location + name);
    }

    public ItemModelBuilder block(Supplier<? extends Block> block) {
        return block(block, blockName(block));
    }

    public ItemModelBuilder block(Supplier<? extends Block> block, String name) {
        return withExistingParent(blockName(block), modLoc("block/" + name));
    }

    public void itemBlock(Supplier<? extends Block> block) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block)));
    }

    public void itemBlock(Supplier<? extends Block> block, String suffix) {
        this.withExistingParent(this.blockName(block), this.texture(this.blockName(block) + suffix));
    }

    public ItemModelBuilder blockFlat(Supplier<? extends Block> block, String location) {
        return withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/" + location + blockName(block)));
    }

    public void blockFlatWithItemName(Supplier<? extends Block> block, String name) {
        this.withExistingParent(blockName(block), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + name + blockName(block)));
    }

    public void itemFence(Supplier<? extends Block> block, Supplier<? extends Block> baseBlock, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/fence_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock), location));
    }

    public void itemButton(Supplier<? extends Block> block, Supplier<? extends Block> baseBlock, String location) {
        this.withExistingParent(this.blockName(block), this.mcLoc("block/button_inventory"))
                .texture("texture", this.texture(this.blockName(baseBlock), location));
    }

    public void generatedItem(Supplier<? extends Item> item, String location) {
        withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public void generatedItem(Item item, String location) {
        withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item).getPath()));
    }

    public void handheldItem(Supplier<? extends Item> item, String location) {
        withExistingParent(ForgeRegistries.ITEMS.getKey(item.get()).getPath(), mcLoc("item/handheld"))
                .texture("layer0", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }

    public ItemModelBuilder wall(Supplier<? extends WallBlock> wall, Supplier<? extends Block> fullBlock) {
        return wallInventory(ForgeRegistries.BLOCKS.getKey(wall.get()).getPath(), texture(blockName(fullBlock)));
    }

    public void twoLayeredGeneratedItem(ResourceLocation layer0, Supplier<? extends Item> layer1, String location) {
        withExistingParent(ForgeRegistries.ITEMS.getKey(layer1.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", layer0)
                .texture("layer1", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(layer1.get()).getPath()));
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

    public void customItemModel(Supplier<? extends Item> item, ResourceLocation lookalike, String location) {
        this.withExistingParent( ForgeRegistries.ITEMS.getKey(item.get()).getPath(), lookalike)
                .texture("layer1", modLoc("item/" + location + ForgeRegistries.ITEMS.getKey(item.get()).getPath()));
    }


}
