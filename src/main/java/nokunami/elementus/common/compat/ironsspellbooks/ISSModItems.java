package nokunami.elementus.common.compat.ironsspellbooks;

@Deprecated
public class ISSModItems {
//    public static Collection<RegistryObject<Item>> getISSCompatItems() {
//        return ITEMS.getEntries();
//    }
//    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
//
//    public static final RegistryObject<Item> STEEL_SPELL_BOOK = ITEMS.register("steel_spell_book", () -> {
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.steelSpellbookMana, AttributeModifier.Operation.ADDITION));
//        builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.steelSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.steelSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.steelSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//        return new SimpleAttributeSpellBook(ItemConfig.steelSpellbookSlot, SpellRarity.RARE, builder.build());
//    });
//    public static final RegistryObject<Item> DIARKRITE_SPELL_BOOK = ITEMS.register("diarkrite_spell_book", () -> {
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.diarkriteSpellbookMana, AttributeModifier.Operation.ADDITION));
//        builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.diarkriteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.diarkriteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.diarkriteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//        return new SimpleAttributeSpellBook(ItemConfig.diarkriteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
//    });
//    public static final RegistryObject<Item> ANTHEKTITE_SPELL_BOOK = ITEMS.register("anthektite_spell_book", () -> {
//        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
//        builder.put(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Max Mana", ItemConfig.anthektiteSpellbookMana, AttributeModifier.Operation.ADDITION));
//        builder.put(AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cooldown Reduction", ItemConfig.anthektiteSpellbookCooldown, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Cast Time Reduction", ItemConfig.anthektiteSpellbookCastTime, AttributeModifier.Operation.MULTIPLY_BASE));
//        builder.put(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(UUID.fromString("667ad88f-901d-4691-b2a2-3664e42026d3"), "Spell Power", ItemConfig.anthektiteSpellbookSpellPower, AttributeModifier.Operation.MULTIPLY_BASE));
//        return new SimpleAttributeSpellBook(ItemConfig.anthektiteSpellbookSlot, SpellRarity.LEGENDARY, builder.build());
//    });
//
//    public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_HELMET = ITEMS.register("anthektite_mage_helmet",
//            () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_CHESTPLATE = ITEMS.register("anthektite_mage_chestplate",
//            () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_LEGGINGS = ITEMS.register("anthektite_mage_leggings",
//            () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> ANTHEKTITE_MAGE_BOOTS = ITEMS.register("anthektite_mage_boots",
//            () -> new ISSArmorItem(MagicArmorMaterial.ANTHEKTITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//    public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_HELMET = ITEMS.register("diarkrite_mage_helmet",
//            () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_CHESTPLATE = ITEMS.register("diarkrite_mage_chestplate",
//            () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_LEGGINGS = ITEMS.register("diarkrite_mage_leggings",
//            () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
//    public static final RegistryObject<ArmorItem> DIARKRITE_MAGE_BOOTS = ITEMS.register("diarkrite_mage_boots",
//            () -> new ISSArmorItem(MagicArmorMaterial.DIARKRITE_MAGE, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
//
//    public static void register(IEventBus eventBus) {ITEMS.register(eventBus);}
}
