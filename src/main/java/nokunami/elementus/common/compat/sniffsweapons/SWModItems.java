package nokunami.elementus.common.compat.sniffsweapons;

@Deprecated
public class SWModItems {
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//    protected static float steelSpeed = ModItems.steelSpeed;
//    protected static float diarkriteSpeed = ModItems.diarkriteSpeed;
//    protected static float anthektiteSpeed = ModItems.anthektiteSpeed;
//
//    public static final RegistryObject<Item> STEEL_GREAT_SWORD = ITEMS.register("steel_great_sword", () -> new GreatSwordItem(ModTiers.STEEL,
//            ItemConfig.steelGreatSwordDamage, ItemConfig.steelGreatSwordAttackSpeed + steelSpeed,new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_GREAT_SWORD = ITEMS.register("diarkrite_great_sword", () -> new GreatSwordItem(ModTiers.DIARKRITE,
//            ItemConfig.diarkriteGreatSwordDamage, ItemConfig.diarkriteGreatSwordAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_GREAT_SWORD = ITEMS.register("anthektite_great_sword", () -> new GreatSwordItem(ModTiers.ANTHEKTITE,
//            ItemConfig.anthektiteGreatSwordDamage, ItemConfig.anthektiteGreatSwordAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_GREAT_AXE = ITEMS.register("steel_great_axe", () -> new GreatAxeItem(ModTiers.STEEL,
//            ItemConfig.steelGreatAxeDamage, ItemConfig.steelGreatAxeAttackSpeed + steelSpeed,new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_GREAT_AXE = ITEMS.register("diarkrite_great_axe", () -> new GreatAxeItem(ModTiers.DIARKRITE,
//            ItemConfig.diarkriteGreatAxeDamage, ItemConfig.diarkriteGreatAxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_GREAT_AXE = ITEMS.register("anthektite_great_axe", () -> new GreatAxeItem(ModTiers.ANTHEKTITE,
//            ItemConfig.anthektiteGreatAxeDamage, ItemConfig.anthektiteGreatAxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_GREAT_PICKAXE = ITEMS.register("steel_great_pickaxe", () -> new GreatPickaxeItem(ModTiers.STEEL,
//            ItemConfig.steelGreatPickaxeDamage, ItemConfig.steelGreatPickaxeAttackSpeed + steelSpeed,new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_GREAT_PICKAXE = ITEMS.register("diarkrite_great_pickaxe", () -> new GreatPickaxeItem(ModTiers.DIARKRITE,
//            ItemConfig.diarkriteGreatPickaxeDamage, ItemConfig.diarkriteGreatPickaxeAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_GREAT_PICKAXE = ITEMS.register("anthektite_great_pickaxe", () -> new GreatPickaxeItem(ModTiers.ANTHEKTITE,
//            ItemConfig.anthektiteGreatPickaxeDamage, ItemConfig.anthektiteGreatPickaxeAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_NAGINATA = ITEMS.register("steel_naginata", () -> new NaginataItem(ModTiers.STEEL,
//            ItemConfig.steelNaginataDamage, ItemConfig.steelNaginataAttackSpeed + steelSpeed,new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_NAGINATA = ITEMS.register("diarkrite_naginata", () -> new NaginataItem(ModTiers.DIARKRITE,
//            ItemConfig.diarkriteNaginataDamage, ItemConfig.diarkriteNaginataAttackSpeed + diarkriteSpeed,new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_NAGINATA = ITEMS.register("anthektite_naginata", () -> new NaginataItem(ModTiers.ANTHEKTITE,
//            ItemConfig.anthektiteNaginataDamage, ItemConfig.anthektiteNaginataAttackSpeed + anthektiteSpeed,new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_SURCOAT = ITEMS.register("steel_surcoat",
//            () -> new StylishArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_SURCOAT = ITEMS.register("diarkrite_surcoat",
//            () -> new StylishArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_SURCOAT = ITEMS.register("anthektite_surcoat",
//            () -> new StylishArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, "stylish", 10511680, new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_HELM = ITEMS.register("steel_helm",
//            () -> new StylishArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_HELM = ITEMS.register("diarkrite_helm",
//            () -> new StylishArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_HELM = ITEMS.register("anthektite_helm",
//            () -> new StylishArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, "stylish", 16777215, new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> PLATED_STEEL_CHESTPLATE = ITEMS.register("plated_steel_chestplate",
//            () -> new HornedArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties()));
//
//    public static final RegistryObject<Item> PLATED_DIARKRITE_CHESTPLATE = ITEMS.register("plated_diarkrite_chestplate",
//            () -> new HornedArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> PLATED_ANTHEKTITE_CHESTPLATE = ITEMS.register("plated_anthektite_chestplate",
//            () -> new HornedArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_HORNED_HELM = ITEMS.register("steel_horned_helm",
//            () -> new HornedArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, 16777215, new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_HORNED_HELM = ITEMS.register("diarkrite_horned_helm",
//            () -> new HornedArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_HORNED_HELM = ITEMS.register("anthektite_horned_helm",
//            () -> new HornedArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_DO = ITEMS.register("steel_do",
//            () -> new SamuraiArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_DO = ITEMS.register("diarkrite_do",
//            () -> new SamuraiArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_DO = ITEMS.register("anthektite_do",
//            () -> new SamuraiArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.CHESTPLATE, 10511680, new Item.Properties().fireResistant()));
//
//
//    public static final RegistryObject<Item> STEEL_KABUTO = ITEMS.register("steel_kabuto",
//            () -> new SamuraiArmorItem(ModArmorMaterials.STEEL, ArmorItem.Type.HELMET, 16777215, new Item.Properties()));
//
//    public static final RegistryObject<Item> DIARKRITE_KABUTO = ITEMS.register("diarkrite_kabuto",
//            () -> new SamuraiArmorItem(ModArmorMaterials.DIARKRITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<Item> ANTHEKTITE_KABUTO = ITEMS.register("anthektite_kabuto",
//            () -> new SamuraiArmorItem(ModArmorMaterials.ANTHEKTITE, ArmorItem.Type.HELMET, 16777215, new Item.Properties().fireResistant()));
//
//    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
