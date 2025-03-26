package net.nokunami.elementus.datagen.loot;

//public class EnchantedLootModifier extends LootModifier {
//
//    public static final Supplier<Codec<EnchantedLootModifier>> CODEC = Suppliers.memoize(
//            () -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, EnchantedLootModifier::new)));
//
//    public EnchantedLootModifier(LootItemCondition[] conditionsIn) {
//        super(conditionsIn);
//    }
//
//    @Override
//    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
//        for (ItemStack stack : generatedLoot) {
//            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(stack);
//            if(0.15 + 0.1*enchantments.size() > new Random().nextDouble() && (!EnchantmentHelper.getAvailableEnchantmentResults(1, stack, true).isEmpty() || stack.getItem() instanceof EnchantedBookItem)) {
////                ArrayList<RegistryObject<? extends Enchantment>> list = (ArrayList<RegistryObject<? extends Enchantment>>) EnchantmentRegistry.CURSELIST.clone();
////                if (stack.getItem() instanceof EnchantedBookItem) {
////                    EnchantedBookItem.addEnchantment(stack, new EnchantmentInstance(list.get(new Random().nextInt(list.size())).get(), 1));
////                } else {
////                    Collections.shuffle(list);
////                    for (RegistryObject<? extends Enchantment> enchantment: list) {
////                        if (enchantment.get().canEnchant(stack)) {
////                            stack.enchant(enchantment.get(), 1);
////                            break;
////                        }
////                    }
////                }
//                createForEnchantment(stack.getItem(), RandomSource.create().nextIntBetweenInclusive(1, 4), RandomSource.create(), true);
//            }
//        }
//        return generatedLoot;
//    }
//
//    @Override
//    public Codec<? extends IGlobalLootModifier> codec() {
//        return CODEC.get();
//    }
//}