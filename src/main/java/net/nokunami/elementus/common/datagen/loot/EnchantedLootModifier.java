package net.nokunami.elementus.common.datagen.loot;

//public class EnchantedLootModifier extends LootModifier {
//    public static final Codec<EnchantedLootModifier> CODEC = RecordCodecBuilder.create((instance) -> {
//        return codecStart(instance).and(ItemStack.CODEC.fieldOf("gloves").forGetter((modifier) -> {
//            return modifier.glovesStack;
//        })).apply(instance, EnchantedLootModifier::new);
//    });
//    public final ItemStack glovesStack;
//    public final ArmorMaterials armorMaterial;
//
//    public EnchantedLootModifier(LootItemCondition[] conditionsIn, ItemStack armorItem, ArmorMaterials armorMaterial) {
//        super(conditionsIn);
//        this.glovesStack = armorItem;
//        this.armorMaterial = armorMaterial;
//    }
//
//    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> lootStacks, LootContext context) {
//        RandomSource randomSource = context.getRandom();
//        Vec3 vec3 = (Vec3)context.getParamOrNull(LootContextParams.ORIGIN);
//        if (vec3 != null) {
//            BlockPos pos = BlockPos.containing(vec3);
//            BlockEntity blockEntity = context.getLevel().getBlockEntity(pos);
//            if (blockEntity instanceof BaseContainerBlockEntity) {
//                List<ItemStack> armorItems = lootStacks.stream().filter((itemStack) -> {
//                    Item patt3092$temp = itemStack.getItem();
//                    boolean var10000;
//                    if (patt3092$temp instanceof ArmorItem armorItem) {
//                        if (armorItem.getMaterial().equals(this.armorMaterial)) {
//                            var10000 = true;
//                            return var10000;
//                        }
//                    }
//
//                    var10000 = false;
//                    return var10000;
//                }).toList();
//                Iterator var8 = armorItems.iterator();
//
//                while(true) {
//                    ItemStack armorStack;
//                    ItemStack gloves;
//                    do {
//                        do {
//                            if (!var8.hasNext()) {
//                                return lootStacks;
//                            }
//
//                            armorStack = (ItemStack)var8.next();
//                        } while(randomSource.nextInt(4) >= 1);
//
//                        gloves = this.glovesStack.copy();
//                        int cost = 0;
//                        boolean isTreasure = false;
//                        Iterator var13 = armorStack.getAllEnchantments().entrySet().iterator();
//
//                        while(var13.hasNext()) {
//                            Map.Entry<Enchantment, Integer> enchantmentInfo = (Map.Entry)var13.next();
//                            Enchantment enchantment = (Enchantment)enchantmentInfo.getKey();
//                            int level = (Integer)enchantmentInfo.getValue();
//                            cost = Math.max(cost, enchantment.getMinCost(level));
//                            if (!isTreasure) {
//                                isTreasure = enchantment.isTreasureOnly();
//                            }
//
//                            if (gloves.canApplyAtEnchantingTable(enchantment)) {
//                                gloves.enchant(enchantment, (Integer)enchantmentInfo.getValue());
//                            }
//                        }
//
//                        if (!armorStack.getAllEnchantments().isEmpty() && gloves.getAllEnchantments().isEmpty()) {
//                            EnchantmentHelper.enchantItem(randomSource, gloves, cost, isTreasure);
//                        }
//                    } while(!armorStack.getAllEnchantments().isEmpty() && gloves.getAllEnchantments().isEmpty());
//
//                    lootStacks.replaceAll((stack) -> {
//                        return stack.equals(armorStack) ? gloves : stack;
//                    });
//                }
//            }
//        }
//
//        return lootStacks;
//    }
//
//    public Codec<? extends IGlobalLootModifier> codec() {
//        return CODEC;
//    }
//}
