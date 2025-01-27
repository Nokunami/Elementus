package net.nokunami.elementus.common.compat.advancednetherite;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.nokunami.elementus.common.compat.advancednetherite.item.*;
import net.nokunami.elementus.common.config.ToolsConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModItems;
import net.nokunami.elementus.common.registry.ModTiers;

import static net.nokunami.elementus.Elementus.MODID;

public class ANModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

     protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
     protected static float anthektiteSpeed = ModItems.anthektiteSpeed;

    public static final RegistryObject<Item> DIARKRITE_IRON_AXE = ITEMS.register("diarkrite_iron_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_IRON,
            ToolsConfig.diarkriteIronAxeDamage, ToolsConfig.diarkriteIronAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_AXE = ITEMS.register("diarkrite_gold_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_GOLD,
            ToolsConfig.diarkriteGoldAxeDamage, ToolsConfig.diarkriteGoldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_AXE = ITEMS.register("diarkrite_emerald_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_EMERALD,
            ToolsConfig.diarkriteEmeraldAxeDamage, ToolsConfig.diarkriteEmeraldAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_AXE = ITEMS.register("diarkrite_diamond_axe", () -> new ANAxeItem(ModTiers.DIARKRITE_DIAMOND,
            ToolsConfig.diarkriteDiamondAxeDamage, ToolsConfig.diarkriteDiamondAxeAttackSpeed + diarkriteSpeed, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_IRON_HOE = ITEMS.register("diarkrite_iron_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_IRON,
            ToolsConfig.diarkriteIronHoeDamage, ToolsConfig.diarkriteIronHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_HOE = ITEMS.register("diarkrite_gold_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_GOLD,
            ToolsConfig.diarkriteGoldHoeDamage, ToolsConfig.diarkriteGoldHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_HOE = ITEMS.register("diarkrite_emerald_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_EMERALD,
            ToolsConfig.diarkriteEmeraldHoeDamage, ToolsConfig.diarkriteEmeraldHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_HOE = ITEMS.register("diarkrite_diamond_hoe", () -> new ANHoeItem(ModTiers.DIARKRITE_DIAMOND,
            ToolsConfig.diarkriteDiamondHoeDamage, ToolsConfig.diarkriteDiamondHoeAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_IRON_PICKAXE = ITEMS.register("diarkrite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_IRON,
            ToolsConfig.diarkriteIronPickaxeDamage, ToolsConfig.diarkriteIronPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_PICKAXE = ITEMS.register("diarkrite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_GOLD,
            ToolsConfig.diarkriteGoldPickaxeDamage, ToolsConfig.diarkriteGoldPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_PICKAXE = ITEMS.register("diarkrite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_EMERALD,
            ToolsConfig.diarkriteEmeraldPickaxeDamage, ToolsConfig.diarkriteEmeraldPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_PICKAXE = ITEMS.register("diarkrite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.DIARKRITE_DIAMOND,
            ToolsConfig.diarkriteDiamondPickaxeDamage, ToolsConfig.diarkriteDiamondPickaxeAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_IRON_SHOVEL = ITEMS.register("diarkrite_iron_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_IRON,
            ToolsConfig.diarkriteIronShovelDamage, ToolsConfig.diarkriteIronShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_SHOVEL = ITEMS.register("diarkrite_gold_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_GOLD,
            ToolsConfig.diarkriteGoldShovelDamage, ToolsConfig.diarkriteGoldShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_SHOVEL = ITEMS.register("diarkrite_emerald_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_EMERALD,
            ToolsConfig.diarkriteEmeraldShovelDamage, ToolsConfig.diarkriteEmeraldShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_SHOVEL = ITEMS.register("diarkrite_diamond_shovel", () -> new ANShovelItem(ModTiers.DIARKRITE_DIAMOND,
            ToolsConfig.diarkriteDiamondShovelDamage, ToolsConfig.diarkriteDiamondShovelAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_IRON_SWORD = ITEMS.register("diarkrite_iron_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_IRON,
            ToolsConfig.diarkriteIronSwordDamage, ToolsConfig.diarkriteIronSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_SWORD = ITEMS.register("diarkrite_gold_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_GOLD,
            ToolsConfig.diarkriteGoldSwordDamage, ToolsConfig.diarkriteGoldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_SWORD = ITEMS.register("diarkrite_emerald_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_EMERALD,
            ToolsConfig.diarkriteEmeraldSwordDamage, ToolsConfig.diarkriteEmeraldSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_SWORD = ITEMS.register("diarkrite_diamond_sword", () -> new ANSwordItem(ModTiers.DIARKRITE_DIAMOND,
            ToolsConfig.diarkriteDiamondSwordDamage, ToolsConfig.diarkriteDiamondSwordAttackSpeed + diarkriteSpeed, new Item.Properties()));


    public static final RegistryObject<Item> ANTHEKTITE_IRON_AXE = ITEMS.register("anthektite_iron_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_IRON,
            ToolsConfig.anthektiteIronAxeDamage, ToolsConfig.anthektiteIronAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_AXE = ITEMS.register("anthektite_gold_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_GOLD,
            ToolsConfig.anthektiteGoldAxeDamage, ToolsConfig.anthektiteGoldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_AXE = ITEMS.register("anthektite_emerald_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_EMERALD,
            ToolsConfig.anthektiteEmeraldAxeDamage, ToolsConfig.anthektiteEmeraldAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_AXE = ITEMS.register("anthektite_diamond_axe", () -> new ANAxeItem(ModTiers.ANTHEKTITE_DIAMOND,
            ToolsConfig.anthektiteDiamondAxeDamage, ToolsConfig.anthektiteDiamondAxeAttackSpeed + anthektiteSpeed, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON_HOE = ITEMS.register("anthektite_iron_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_IRON,
            ToolsConfig.anthektiteIronHoeDamage, ToolsConfig.anthektiteIronHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_HOE = ITEMS.register("anthektite_gold_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_GOLD,
            ToolsConfig.anthektiteGoldHoeDamage, ToolsConfig.anthektiteGoldHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HOE = ITEMS.register("anthektite_emerald_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_EMERALD,
            ToolsConfig.anthektiteEmeraldHoeDamage, ToolsConfig.anthektiteEmeraldHoeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HOE = ITEMS.register("anthektite_diamond_hoe", () -> new ANHoeItem(ModTiers.ANTHEKTITE_DIAMOND,
            ToolsConfig.anthektiteDiamondHoeDamage, ToolsConfig.anthektiteDiamondHoeAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON_PICKAXE = ITEMS.register("anthektite_iron_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_IRON,
            ToolsConfig.anthektiteIronPickaxeDamage, ToolsConfig.anthektiteIronPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_PICKAXE = ITEMS.register("anthektite_gold_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_GOLD,
            ToolsConfig.anthektiteGoldPickaxeDamage, ToolsConfig.anthektiteGoldPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_PICKAXE = ITEMS.register("anthektite_emerald_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_EMERALD,
            ToolsConfig.anthektiteEmeraldPickaxeDamage, ToolsConfig.anthektiteEmeraldPickaxeAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_PICKAXE = ITEMS.register("anthektite_diamond_pickaxe", () -> new ANPickaxeItem(ModTiers.ANTHEKTITE_DIAMOND,
            ToolsConfig.anthektiteDiamondPickaxeDamage, ToolsConfig.anthektiteDiamondPickaxeAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON_SHOVEL = ITEMS.register("anthektite_iron_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_IRON,
            ToolsConfig.anthektiteIronShovelDamage, ToolsConfig.anthektiteIronShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_SHOVEL = ITEMS.register("anthektite_gold_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_GOLD,
            ToolsConfig.anthektiteGoldShovelDamage, ToolsConfig.anthektiteGoldShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SHOVEL = ITEMS.register("anthektite_emerald_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_EMERALD,
            ToolsConfig.anthektiteEmeraldShovelDamage, ToolsConfig.anthektiteEmeraldShovelAttackSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SHOVEL = ITEMS.register("anthektite_diamond_shovel", () -> new ANShovelItem(ModTiers.ANTHEKTITE_DIAMOND,
            ToolsConfig.anthektiteDiamondShovelDamage, ToolsConfig.anthektiteDiamondShovelAttackSpeed, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON_SWORD = ITEMS.register("anthektite_iron_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_IRON,
            ToolsConfig.anthektiteIronSwordDamage, ToolsConfig.anthektiteIronSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_SWORD = ITEMS.register("anthektite_gold_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_GOLD,
            ToolsConfig.anthektiteGoldSwordDamage, ToolsConfig.anthektiteGoldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_SWORD = ITEMS.register("anthektite_emerald_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_EMERALD,
            ToolsConfig.anthektiteEmeraldSwordDamage, ToolsConfig.anthektiteEmeraldSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_SWORD = ITEMS.register("anthektite_diamond_sword", () -> new ANSwordItem(ModTiers.ANTHEKTITE_DIAMOND,
            ToolsConfig.anthektiteDiamondSwordDamage, ToolsConfig.anthektiteDiamondSwordAttackSpeed + anthektiteSpeed, new Item.Properties()));


    public static final RegistryObject<Item> DIARKRITE_IRON_HELMET = ITEMS.register("diarkrite_iron_helmet",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_IRON_CHESTPLATE = ITEMS.register("diarkrite_iron_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_IRON_LEGGINGS = ITEMS.register("diarkrite_iron_leggings",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_IRON_BOOTS = ITEMS.register("diarkrite_iron_boots",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_GOLD_HELMET = ITEMS.register("diarkrite_gold_helmet",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_CHESTPLATE = ITEMS.register("diarkrite_gold_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_LEGGINGS = ITEMS.register("diarkrite_gold_leggings",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_BOOTS = ITEMS.register("diarkrite_gold_boots",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_EMERALD_HELMET = ITEMS.register("diarkrite_emerald_helmet",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_CHESTPLATE = ITEMS.register("diarkrite_emerald_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_LEGGINGS = ITEMS.register("diarkrite_emerald_leggings",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_BOOTS = ITEMS.register("diarkrite_emerald_boots",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> DIARKRITE_DIAMOND_HELMET = ITEMS.register("diarkrite_diamond_helmet",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_CHESTPLATE = ITEMS.register("diarkrite_diamond_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_LEGGINGS = ITEMS.register("diarkrite_diamond_leggings",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_BOOTS = ITEMS.register("diarkrite_diamond_boots",
            () -> new ANArmorItem(ModArmorMaterials.DIARKRITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static final RegistryObject<Item> ANTHEKTITE_IRON_HELMET = ITEMS.register("anthektite_iron_helmet",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_IRON_CHESTPLATE = ITEMS.register("anthektite_iron_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_IRON_LEGGINGS = ITEMS.register("anthektite_iron_leggings",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_IRON_BOOTS = ITEMS.register("anthektite_iron_boots",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_IRON, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_GOLD_HELMET = ITEMS.register("anthektite_gold_helmet",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_CHESTPLATE = ITEMS.register("anthektite_gold_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_LEGGINGS = ITEMS.register("anthektite_gold_leggings",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_BOOTS = ITEMS.register("anthektite_gold_boots",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_GOLD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_HELMET = ITEMS.register("anthektite_emerald_helmet",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_CHESTPLATE = ITEMS.register("anthektite_emerald_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_LEGGINGS = ITEMS.register("anthektite_emerald_leggings",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BOOTS = ITEMS.register("anthektite_emerald_boots",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_EMERALD, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_HELMET = ITEMS.register("anthektite_diamond_helmet",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_CHESTPLATE = ITEMS.register("anthektite_diamond_chestplate",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_LEGGINGS = ITEMS.register("anthektite_diamond_leggings",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BOOTS = ITEMS.register("anthektite_diamond_boots",
            () -> new ANArmorItem(ModArmorMaterials.ANTHEKTITE_DIAMOND, ArmorItem.Type.BOOTS, new Item.Properties()));


    public static final RegistryObject<Item> DIARKRITE_IRON = ITEMS.register("diarkrite_iron_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_GOLD = ITEMS.register("diarkrite_gold_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD = ITEMS.register("diarkrite_emerald_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND = ITEMS.register("diarkrite_diamond_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON = ITEMS.register("anthektite_iron_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD = ITEMS.register("anthektite_gold_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD = ITEMS.register("anthektite_emerald_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND = ITEMS.register("anthektite_diamond_ingot",
            () -> new ANItem(new Item.Properties().fireResistant()));


    public static final RegistryObject<Item> DIARKRITE_IRON_BLOCK = ITEMS.register("diarkrite_iron_block",
            () -> new BlockItem(ANModBlocks.DIARKRITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_GOLD_BLOCK = ITEMS.register("diarkrite_gold_block",
            () -> new BlockItem(ANModBlocks.DIARKRITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_EMERALD_BLOCK = ITEMS.register("diarkrite_emerald_block",
            () -> new BlockItem(ANModBlocks.DIARKRITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DIARKRITE_DIAMOND_BLOCK = ITEMS.register("diarkrite_diamond_block",
            () -> new BlockItem(ANModBlocks.DIARKRITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> ANTHEKTITE_IRON_BLOCK = ITEMS.register("anthektite_iron_block",
            () -> new BlockItem(ANModBlocks.ANTHEKTITE_IRON_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_GOLD_BLOCK = ITEMS.register("anthektite_gold_block",
            () -> new BlockItem(ANModBlocks.ANTHEKTITE_GOLD_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_EMERALD_BLOCK = ITEMS.register("anthektite_emerald_block",
            () -> new BlockItem(ANModBlocks.ANTHEKTITE_EMERALD_BLOCK.get(), new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ANTHEKTITE_DIAMOND_BLOCK = ITEMS.register("anthektite_diamond_block",
            () -> new BlockItem(ANModBlocks.ANTHEKTITE_DIAMOND_BLOCK.get(), new Item.Properties().fireResistant()));


    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
