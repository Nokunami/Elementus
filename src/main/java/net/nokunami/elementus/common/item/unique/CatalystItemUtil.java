package net.nokunami.elementus.common.item.unique;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.registry.ModMobEffects;
import net.nokunami.elementus.common.registry.ModMobEffects.ElementusEffects;
import net.nokunami.elementus.common.registry.ESoundEvents;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEM_MAP;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.item.unique.CatalystArmorItem.catalystActivator;

public class CatalystItemUtil {
    public static final String netherStar = "nether_star";
    public static final String ignitium = "ignitium_ingot";
    public static final String arcane = "arcane_ingot";
    public static final String heartSea = "heart_of_the_sea";
    public static final String totem = "totem_of_undying";
    public static final String cursium = "cursium_ingot";
    public static final String witheredNetherStar = "withered_nether_star";

    public static void netherStar(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.netherStar)) {
            if (healthActivation && !p.getCooldowns().isOnCooldown(s.getItem())) {
                p.playSound(ESoundEvents.CATALYST_ARMOR_ACTIVATE.get(), 1.25F, 1.5F + p.level().getRandom().nextFloat() * 0.4F);
                p.removeEffect(ElementusEffects.BEACON_POWER.get());
                if (!p.hasEffect(ElementusEffects.BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(ElementusEffects.BEACON_POWER.get(), 100 + CatalystArmorConfig.NSDuration, CatalystArmorConfig.NSBoostedAmp));
                }
                p.getCooldowns().addCooldown(s.getItem(), CatalystArmorConfig.NSCooldown);
            }
            if(!p.getCooldowns().isOnCooldown(s.getItem())) {
                if (!p.hasEffect(ElementusEffects.BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(ElementusEffects.BEACON_POWER.get(), 100));
                }
            }
        }
    }
    public static void ignitium(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.ignitium)) {
            if (cataclysm) {
                if (p.hasEffect(ModEffect.EFFECTBLAZING_BRAND.get())) {
                    p.playSound(SoundEvents.FIRE_EXTINGUISH);
                    p.removeEffect(ModEffect.EFFECTBLAZING_BRAND.get());
                }
            }
            if (healthActivation) {
                mobEffect(p, mobEffectType.HASTE, ignitium_HasteDuration, ignitium_HasteAmp, false, false, true);
                mobEffect(p, mobEffectType.STRENGTH, ignitium_StrengthDuration, ignitium_StrengthAmp, false, false, true);
                mobEffect(p, mobEffectType.RESIST, ignitium_ResistanceDuration, ignitium_ResistanceAmp, false, false, true);
            }
        }
    }
    public static void arcane(ItemStack s, Player p) {
        if (catalystActivator(s).equals(CatalystItemUtil.arcane)) {
            p.addEffect(new MobEffectInstance(ModMobEffects.ISSEffects.ADD_ISS_MANA.get(), 0, 0, false, false, true));
        }
    }
    public static void heartSea(ItemStack s, Player p) {
        if (catalystActivator(s).equals(CatalystItemUtil.heartSea)) {
            if (p.isInWaterOrRain()) {
//                p.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 2, 0, false, false, true));
                mobEffect(p, mobEffectType.SEA, 2, 0, false, true, true);
            }
        }
    }
    public static void witheredNetherStar(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.witheredNetherStar)) {
            if (healthActivation) {
                if (!p.hasEffect(ElementusEffects.WITHERED_BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(ElementusEffects.WITHERED_BEACON_POWER.get(), 100, 1));
                }
            } else if (!p.hasEffect(ElementusEffects.WITHERED_BEACON_POWER.get())) {
                p.addEffect(new MobEffectInstance(ElementusEffects.WITHERED_BEACON_POWER.get(), 100));
            }
        }
    }

    public static void alliedMobEffects(Entity entity, int type) {
        int amp1 = 1;
        int amp2 = 2;
        int duration = 400;

        if (entity instanceof LivingEntity livingEntity) {
            if (type == 1) {
                mobEffect(livingEntity, mobEffectType.HASTE, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.REGEN, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.SPEED, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.RESIST, duration, amp1, true, true, true);
            }
            if (type == 2) {
                mobEffect(livingEntity, mobEffectType.SEA, duration, amp1, false, true, true);
            }
            if (type == 3) {
                mobEffect(livingEntity, mobEffectType.JUMP, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.HASTE, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.REGEN, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.SPEED, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.RESIST, duration, amp2, true, true, true);
            }
        }
    }

    public static void effectRadius(Player p, ItemStack stack, Level w, int type) {
        Level level = p.level();
        String catalyst = catalystActivator(stack);
        ItemStack chestplateItem = p.getItemBySlot(EquipmentSlot.CHEST);
        int range = 16;
        if (!level.isClientSide) {
            if (chestplateItem.is(EItems.CATALYST_CHESTPLATE.get())) {
                if (catalyst.equals(netherStar) || catalyst.equals(witheredNetherStar)) {
                    areaEffect(p, w, type, range);
                } else if (catalyst.equals(heartSea)) {
                    for (Entity entity : w.getEntities(p, p.getBoundingBox().inflate(range))) {
                        if (entity.isInWaterOrRain()) {
                            if (entity instanceof LivingEntity livingEntity) {
                                if (livingEntity.isAlliedTo(p)) {
                                    alliedMobEffects(livingEntity, type);
                                }
                            } else if (entity instanceof OwnableEntity tamableAnimal) {
                                if (tamableAnimal.getOwner() != null && (tamableAnimal.getOwner().is(p) || tamableAnimal.getOwner().isAlliedTo(p))) {
                                    alliedMobEffects((Entity) tamableAnimal, type);
                                }
                            } else if (entity instanceof Player player) {
                                if ((player.isAlliedTo(p) || player.getTeam().equals(null)) && !player.hasEffect(ElementusEffects.BEACON_POWER.get())) {
                                    alliedMobEffects(player, type);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void areaEffect(Player p, Level w, int type, int range) {
        for (Entity entity : w.getEntities(p, p.getBoundingBox().inflate(range))) {
            if (entity instanceof LivingEntity livingEntity) {
                if (livingEntity.isAlliedTo(p)) {
                    alliedMobEffects(livingEntity, type);
                }
            } else if (entity instanceof OwnableEntity o) {
                if (o.getOwner() != null && o.getOwner().is(p) || Objects.requireNonNull(o.getOwner()).isAlliedTo(p)) {
                    alliedMobEffects((Entity) o, type);
                }
            } else if (entity instanceof Player player) {
                if ((player.isAlliedTo(p) || player.getTeam().equals(null)) && !player.hasEffect(ElementusEffects.BEACON_POWER.get())) {
                    alliedMobEffects(player, type);
                }
            }
        }
    }

    public static void mobEffect(Entity entity, mobEffectType effect, int duration, int amp, boolean ambient, boolean visible, boolean icon) {
        if (duration != 0) {
            if (entity instanceof LivingEntity livingEntity) {
                switch (effect) {
                    case JUMP -> livingEntity.addEffect(new MobEffectInstance(MobEffects.JUMP, duration, amp, ambient, visible, icon));
                    case SPEED -> livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, amp, ambient, visible, icon));
                    case HASTE -> livingEntity.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, duration, amp, ambient, visible, icon));
                    case STRENGTH -> livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, amp, ambient, visible, icon));
                    case REGEN -> {
                        if (!livingEntity.hasEffect(MobEffects.REGENERATION)) livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, amp, ambient, visible, icon));
                    }
                    case RESIST -> livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amp, ambient, visible, icon));
                    case SEA -> livingEntity.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, duration, amp, ambient, visible, icon));
                }
            }
        }
    }

    public static int getTextureType(ItemStack stack) {
        return stack.getOrCreateTag().getInt("TextureType");
    }

    public static void changeTextureType(ItemStack stack, int type) {
        stack.getOrCreateTag().putInt("TextureType", type);
    }

    public static boolean getTextureOverride(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("CoreTextureOverride");
    }

    public static void setTextureOverride(ItemStack stack, boolean type) {
        stack.getOrCreateTag().putBoolean("CoreTextureOverride", type);
    }

    public static int insertCore(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && CORE_ITEM_MAP.containsKey(insertStack.getItem())) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains("Core")) tag.put("Core", new ListTag());
            int min = Math.min(insertStack.getCount(), (1 - getContentWeight(coreStack)));
            if (min == 0) return 0;
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(min).save(newTag);
                tag.getList("Core", 10).add(0, newTag);
                return min;
            }
        } else return 0;
    }

    public static int insertCoreL(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && CatalystCore.getInstance(insertStack).getCoreStack() == insertStack) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains("Items")) tag.put("Items", new ListTag());
            int min = Math.min(insertStack.getCount(), (1 - getContentWeight(coreStack)));
            if (min == 0) return 0;
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(min).save(newTag);
                tag.getList("Items", 10).add(0, newTag);
                return min;
            }
        } else return 0;
    }

    public static int equipElytra(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && insertStack.is(Etags.Items.CATALYST_ELYTRA)) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains("ElytraEquipped")) {
                tag.put("ElytraEquipped", new ListTag());
            }
            int insertAmount = Math.min(insertStack.getCount(), (1 - checkElytraEquipped(coreStack)));
            if (insertAmount == 0) {
                return 0;
            }
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(insertAmount).save(newTag);
                tag.getList("ElytraEquipped", 10).add(0, newTag);
                return insertAmount;
            }
        } else {
            return 0;
        }
    }

    public static int equipElytraL(ItemStack coreStack, ItemStack insertStack) {
        if (!insertStack.isEmpty() && insertStack.is(Etags.Items.CATALYST_ELYTRA)) {
            CompoundTag tag = coreStack.getOrCreateTag();
            if (!tag.contains("ElytraEquiped")) {
                tag.put("ElytraEquiped", new ListTag());
            }
            int k = Math.min(insertStack.getCount(), (1 - checkElytraEquipped(coreStack)));
            if (k == 0) {
                return 0;
            }
            else {
                CompoundTag newTag = new CompoundTag();
                insertStack.copyWithCount(k).save(newTag);
                tag.getList("ElytraEquiped", 10).add(0, newTag);
                return k;
            }
        } else {
            return 0;
        }
    }

    public static int getContentWeight(ItemStack pStack) {
        return getContents(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static int getContentLWeight(ItemStack pStack) {
        return getContentsL(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static int checkElytraEquipped(ItemStack pStack) {
        return getElytraEquipped(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static int checkElytraEquiped(ItemStack pStack) {
        return getElytraEquiped(pStack).mapToInt(ItemStack::getCount).sum();
    }

    public static Optional<ItemStack> removeCore(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList("Core", 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains("Core")) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey("Core");
                }
                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> removeCoreL(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList("Items", 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains("Items")) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey("Items");
                }
                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> removeEquippedElytra(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList("ElytraEquipped", 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains("ElytraEquipped")) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey("ElytraEquipped");
                }
                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> removeEquipedElytra(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList("ElytraEquiped", 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains("ElytraEquiped")) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey("ElytraEquiped");
                }
                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> findCore(ItemStack stack) {
        return getContents(stack).findAny();
    }

    public static Stream<ItemStack> getContentsL(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("Items", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static Stream<ItemStack> getElytraEquipped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("ElytraEquipped", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static Stream<ItemStack> getElytraEquiped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("ElytraEquiped", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static Stream<ItemStack> getContents(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("Core", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static Stream<ItemStack> getCatalystContents(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("CatalystItems", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static int getContentAmount(ItemStack stack) {
//        return getContents1(stack).mapToInt(ItemStack::getCount).sum();
        return getCatalystContents(stack).mapToInt(itemStack -> getWeight(itemStack) * itemStack.getCount()).sum();
    }

    private static Optional<CompoundTag> getMatchingItem(ItemStack stack, ListTag list) {
        return stack.is(Items.BUNDLE) ? Optional.empty() :
                list.stream()
                        .filter(CompoundTag.class::isInstance)
                        .map(CompoundTag.class::cast)
                        .filter((p_186350_) -> ItemStack.isSameItemSameTags(ItemStack.of(p_186350_), stack))
                        .findFirst();
    }

    public static int getWeight(ItemStack stack) {
//        if (stack.is(Items.BUNDLE)) {
//            return 4 + getContentWeight1(stack);
//        } else {
//            if ((stack.is(Items.BEEHIVE) || stack.is(Items.BEE_NEST)) && stack.hasTag()) {
//                CompoundTag compoundtag = BlockItem.getBlockEntityData(stack);
//                if (compoundtag != null && !compoundtag.getList("Bees", 10).isEmpty()) {
//                    return 64;
//                }
//            }
//
//            return 64 / stack.getMaxStackSize();
//        }
        return 1;
    }

    public static int insertStack(ItemStack chestplate, ItemStack insertStack) {
        if (!insertStack.isEmpty() && (CatalystCore.filter(insertStack) && getEquippedCore(chestplate).isEmpty() || insertStack.is(Etags.Items.CATALYST_ELYTRA) && getEquippedElytra(chestplate).isEmpty())) {
            CompoundTag compoundtag = chestplate.getOrCreateTag();
            int maxSize = 2;
            if (!compoundtag.contains("CatalystItems")) {
                compoundtag.put("CatalystItems", new ListTag());
            }

            int contentWeight = getContentAmount(chestplate);
            int weight = getWeight(insertStack);
            int k = Math.min(insertStack.getCount(), (maxSize - contentWeight) / weight);
            if (k == 0) {
                return 0;
            } else {
                ListTag listtag = compoundtag.getList("CatalystItems", 10);
//                Optional<CompoundTag> optional = getMatchingItem(insertStack, listtag);
//                if (optional.isPresent()) {
//                    CompoundTag compoundtag1 = optional.get();
//                    ItemStack itemstack = ItemStack.of(compoundtag1);
//                    itemstack.grow(k);
//                    itemstack.save(compoundtag1);
//                    listtag.remove(compoundtag1);
//                    listtag.add(0, compoundtag1);
//                } else {
//                }
                ItemStack itemstack1 = insertStack.copyWithCount(k);
                CompoundTag compoundtag2 = new CompoundTag();
                itemstack1.save(compoundtag2);
                listtag.add(0, compoundtag2);

                return k;
            }
        } else {
            return 0;
        }
    }

    public static Optional<ItemStack> takeStack(ItemStack stack) {
//        ListTag items = stack.getOrCreateTag().getList("CatalystItems", 10);
//        ItemStack itemstack = ItemStack.of(items.getCompound(0));
//
//        if (!stack.getOrCreateTag().contains("CatalystItems")) {
//            return Optional.empty();
//        } else {
//            if (items.isEmpty()) {
//                return Optional.empty();
//            } else {
//                items.remove(0);
//                if (items.isEmpty()) {
//                    stack.removeTagKey("CatalystItems");
//                }
//                return Optional.of(itemstack);
//            }
//        }
        CompoundTag tag = stack.getOrCreateTag();
        if (!tag.contains("CatalystItems")) {
            return Optional.empty();
        } else {
            ListTag listtag = tag.getList("CatalystItems", 10);
            if (listtag.isEmpty()) {
                return Optional.empty();
            } else {
                CompoundTag list = listtag.getCompound(0);
                ItemStack itemstack = ItemStack.of(list);
                listtag.remove(0);
                if (listtag.isEmpty()) {
                    stack.removeTagKey("CatalystItems");
                }

                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> getEquippedCore(ItemStack stack) {
        return getCatalystContents(stack).filter(CatalystCore::filter).findAny();
    }
    public static Optional<ItemStack> getEquippedElytra(ItemStack stack) {
        return getCatalystContents(stack).filter(elytra -> elytra.is(Etags.Items.CATALYST_ELYTRA)).findAny();
    }

    public static Optional<ItemStack> removeContents(ItemStack stack) {
        ListTag items = stack.getOrCreateTag().getList("CatalystItems", 10);
        ItemStack itemstack = ItemStack.of(items.getCompound(0));

        if (!stack.getOrCreateTag().contains("CatalystItems")) {
            return Optional.empty();
        } else {
            if (items.isEmpty()) {
                return Optional.empty();
            } else {
                items.remove(0);
                if (items.isEmpty()) {
                    stack.removeTagKey("CatalystItems");
                }
                return Optional.of(itemstack);
            }
        }
    }

    public enum mobEffectType {
        JUMP("jump"),
        SPEED("speed"),
        HASTE("haste"),
        STRENGTH("strength"),
        REGEN("regen"),
        RESIST("resist"),
        SEA("sea");

        private final String name;

        mobEffectType(String pName) {
            this.name = pName;
        }

        public String getName() {
            return this.name;
        }
    }


    //Textures
    public static String armorTexture(ItemStack stack, Entity entity) {
        return baseTextures(stack, entity);
    }

    public static String baseTextures(ItemStack stack, Entity entity) {
        String base = "%s:textures/models/armor/catalyst/catalyst_armor_layer.png";
        String format = "%s:textures/models/armor/catalyst/catalyst_%s_armor_layer.png";
        String armor = String.format(Locale.ROOT, format, MODID, catalystActivator(stack));
        if (getContentWeight(stack) > 0 ) {
            if (catalystActivator(stack).equals(ignitium)) {
                if (entity instanceof LivingEntity livingEntity && livingEntity.getMaxHealth() / 2.0F >= livingEntity.getHealth()) {
                    return String.format(Locale.ROOT, format, MODID, ignitium + "_soul");
                }
                return String.format(Locale.ROOT, format, MODID, ignitium);
            }
            return armor;
        }
        return String.format(Locale.ROOT, base, MODID);
    }

    private static String elytraTexture(ItemStack stack) {
        if (getElytraEquipped(stack).findAny().isPresent()) {
            getElytraEquipped(stack).findAny().get().getItem();
        }
        return "elytra";
    }

    public static String getElytraTexture(ItemStack stack) {
        return String.format(Locale.ROOT, "textures/models/armor/catalyst/elytra/catalyst_%s.png", elytraTexture(stack));
    }

    public static String testArmorTexture(ItemStack stack, Entity entity) {
        return testBaseTextures(stack, entity);
    }

    public static String testBaseTextures(ItemStack stack, Entity entity) {
        stack = getEquippedCore(stack).get();
        String base = "%s:textures/models/armor/catalyst/catalyst_chestplate.png";
        String coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s_armor.png";
        if (getEquippedCore(stack).isPresent()) {
            /*if (getTextureType(stack) > 0)*/ coreTexture = "%s:textures/models/armor/catalyst/catalyst_%s_" + CatalystCore.getInstance(getEquippedCore(stack).get()) + "_armor.png";
            return String.format(Locale.ROOT, coreTexture, MODID, getEquippedCore(stack).get().getItem());
//            return String.format(Locale.ROOT, coreTexture, MODID, CatalystCore.getInstance(stack).getTexture());
        }
        return String.format(Locale.ROOT, base, MODID);
    }

    private static String testElytraTexture(ItemStack stack) {
        if (getElytraEquipped(stack).findAny().isPresent()) {
            getElytraEquipped(stack).findAny().get().getItem();
        }
        return "elytra";
    }

    public static String getTestElytraTexture(ItemStack stack) {
        return String.format(Locale.ROOT, "textures/models/armor/catalyst/elytra/catalyst_%s.png", testElytraTexture(stack));
    }
}
