package net.nokunami.elementus.common.item.unique;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.catalystCore.core.CatalystCore;
import net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig;
import net.nokunami.elementus.common.item.EItemUtil;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EItems;
import net.nokunami.elementus.common.registry.EMobEffects;
import net.nokunami.elementus.common.registry.ESounds;
import net.nokunami.elementus.common.tags.EItemTags;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.item.unique.CatalystArmorItem.catalystActivator;
import static net.nokunami.elementus.common.registry.CustomRegistries.CatalystCoreHelper.CatalystCoreUtil;
import static net.nokunami.elementus.common.registry.CustomRegistries.getCatalystCore;

public class CatalystItemUtil {
    public static final String netherStar = "nether_star";
    public static final String ignitium = "ignitium_ingot";
    public static final String arcane = "arcane_ingot";
    public static final String heartSea = "heart_of_the_sea";
    public static final String totem = "totem_of_undying";
    public static final String cursium = "cursium_ingot";
    public static final String witheredNetherStar = "withered_nether_star";

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static void netherStar(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.netherStar)) {
            if (healthActivation && !p.getCooldowns().isOnCooldown(s.getItem())) {
                p.playSound(ESounds.CATALYST_ARMOR_ACTIVATE.get(), 1.25F, 1.5F + p.level().getRandom().nextFloat() * 0.4F);
                p.removeEffect(EMobEffects.BEACON_POWER.get());
                if (!p.hasEffect(EMobEffects.BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(EMobEffects.BEACON_POWER.get(), 100 + CatalystArmorConfig.NSDuration, CatalystArmorConfig.NSBoostedAmp));
                }
                p.getCooldowns().addCooldown(s.getItem(), CatalystArmorConfig.NSCooldown);
            }
            if(!p.getCooldowns().isOnCooldown(s.getItem())) {
                if (!p.hasEffect(EMobEffects.BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(EMobEffects.BEACON_POWER.get(), 100));
                }
            }
        }
    }
    @Deprecated(forRemoval = true, since = "1.20.1")
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
    @Deprecated(forRemoval = true, since = "1.20.1")
    public static void arcane(ItemStack s, Player p) {
        if (catalystActivator(s).equals(CatalystItemUtil.arcane)) {
            p.addEffect(new MobEffectInstance(EMobEffects.ISSEffects.ADD_ISS_MANA.get(), 0, 0, false, false, true));
        }
    }
    @Deprecated(forRemoval = true, since = "1.20.1")
    public static void heartSea(ItemStack s, Player p) {
        if (catalystActivator(s).equals(CatalystItemUtil.heartSea)) {
            if (p.isInWaterOrRain()) {
//                p.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 2, 0, false, false, true));
                mobEffect(p, mobEffectType.SEA, 2, 0, false, true, true);
            }
        }
    }
    @Deprecated(forRemoval = true, since = "1.20.1")
    public static void witheredNetherStar(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.witheredNetherStar)) {
            if (healthActivation) {
                if (!p.hasEffect(EMobEffects.WITHERED_BEACON_POWER.get())) {
                    p.addEffect(new MobEffectInstance(EMobEffects.WITHERED_BEACON_POWER.get(), 100, 1));
                }
            } else if (!p.hasEffect(EMobEffects.WITHERED_BEACON_POWER.get())) {
                p.addEffect(new MobEffectInstance(EMobEffects.WITHERED_BEACON_POWER.get(), 100));
            }
        }
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
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

    @Deprecated(forRemoval = true, since = "1.20.1")
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
                                if ((player.isAlliedTo(p) || player.getTeam().equals(null)) && !player.hasEffect(EMobEffects.BEACON_POWER.get())) {
                                    alliedMobEffects(player, type);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
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
                if ((player.isAlliedTo(p) || player.getTeam().equals(null)) && !player.hasEffect(EMobEffects.BEACON_POWER.get())) {
                    alliedMobEffects(player, type);
                }
            }
        }
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
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

    @Deprecated(forRemoval = true, since = "1.20.1")
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

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static boolean isCatalystChestplate(LivingEntity entity) {
        return EItemUtil.isEquipment(entity, EItems.CATALYST_CHESTPLATE.get());
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static int getContentLWeight(ItemStack pStack) {
        return getContentsL(pStack).mapToInt(ItemStack::getCount).sum();
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static int checkElytraEquiped(ItemStack pStack) {
        return getElytraEquiped(pStack).mapToInt(ItemStack::getCount).sum();
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
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

    @Deprecated(forRemoval = true, since = "1.20.1")
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

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static Stream<ItemStack> getContentsL(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("Items", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static Stream<ItemStack> getElytraEquipped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("ElytraEquipped", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static Stream<ItemStack> getElytraEquiped(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("ElytraEquiped", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    @Deprecated(forRemoval = true, since = "1.20.1")
    public static Stream<ItemStack> getContents(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("Core", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    //----- [ Core Contents ]

    public static Stream<ItemStack> getCatalystContents(ItemStack stack) {
        return stack.getTag() == null ? Stream.empty() : stack.getTag().getList("CatalystItems", 10).stream().map(CompoundTag.class::cast).map(ItemStack::of);
    }

    public static int getContentAmount(ItemStack stack) {
        return getCatalystContents(stack).mapToInt(itemStack -> getWeight(itemStack) * itemStack.getCount()).sum();
    }

    public static int getWeight(ItemStack stack) {
        return 1;
    }

    public static int insertStack(ItemStack chestplate, ItemStack insertStack) {
//        if (!insertStack.isEmpty() && (CatalystCore.filter(insertStack) && getEquippedCore(chestplate).isEmpty() || insertStack.is(EItemTags.CATALYST_ELYTRA) && !hasElytra(chestplate))) {
        if (!insertStack.isEmpty() && (canEquipCore(chestplate, insertStack) || canEquipElytra(chestplate, insertStack))) {
            if (!insertStack.is(EItemTags.CATALYST_ELYTRA)) setCoreInstance(chestplate, insertStack);
            CompoundTag compoundtag = chestplate.getOrCreateTag();
            int maxSize = 2;
            if (!compoundtag.contains("CatalystItems")) compoundtag.put("CatalystItems", new ListTag());

            int contentWeight = getContentAmount(chestplate);
            int weight = getWeight(insertStack);
            int k = Math.min(insertStack.getCount(), 1);
            if (k == 0) {
                return 0;
            } else {
                ListTag catalystItems = compoundtag.getList("CatalystItems", 10);
                ItemStack copied = insertStack.copyWithCount(k);
                CompoundTag newTag = new CompoundTag();
                copied.save(newTag);
                if (catalystItems.isEmpty() || CatalystCore.filter(insertStack))
                    catalystItems.add(0, newTag);
                else if (getEquippedCore(chestplate).isPresent() && insertStack.is(EItemTags.CATALYST_ELYTRA))
                    catalystItems.add(1, newTag);
                return k;
            }
        } else {
            return 0;
        }
    }

    public static Optional<ItemStack> takeStack(ItemStack stack) {
        ListTag catalystItems = stack.getOrCreateTag().getList("CatalystItems", 10);
        if (!stack.getOrCreateTag().contains("CatalystItems")) return Optional.empty();
        else {
            if (catalystItems.isEmpty()) return Optional.empty();
            else {
                int selection = Mth.clamp(Selection.get(stack), 0, catalystItems.size() - 1);
                ItemStack itemstack = ItemStack.of(catalystItems.getCompound(selection));
                catalystItems.remove(selection);
                if (catalystItems.isEmpty()) stack.removeTagKey("CatalystItems");
                removeTextureType(stack);
                CatalystCoreUtil(stack).getCoreFromString(getCoreInstance(stack)).onCoreRemove(stack);
                return Optional.of(itemstack);
            }
        }
    }

    public static Optional<ItemStack> getEquippedCore(ItemStack stack) {
        return getCatalystContents(stack).filter(CatalystCore::filter).findAny();
    }
    public static Optional<ItemStack> getEquippedElytra(ItemStack stack) {
        return getCatalystContents(stack).filter(elytra -> elytra.is(EItemTags.CATALYST_ELYTRA)).findAny();
    }
    public static boolean hasCore(ItemStack stack) { return getEquippedCore(stack).isPresent(); }
    public static boolean hasElytra(ItemStack stack) { return getEquippedElytra(stack).isPresent(); }

    public static boolean canEquipCore(ItemStack chest, ItemStack stack) { return CatalystCore.filter(stack) && !hasCore(chest); }
    public static boolean canEquipElytra(ItemStack chest, ItemStack stack) { return stack.is(EItemTags.CATALYST_ELYTRA) && !hasElytra(chest); }

    public static String getCoreInstance(ItemStack stack) { return stack.getOrCreateTag().getString("CoreInstance"); }
    public static void setCoreInstance(ItemStack stack, ItemStack core) { stack.getOrCreateTag().putString("CoreInstance", getCatalystCore(core).getWithNamespace()); }

    // ----- [ TEXTURES ]

    public static int getTrimVisibility(ItemStack stack) {
        boolean hasTrim = isTrimmed(stack);
        return Mth.clamp(stack.getOrCreateTag().getInt("TrimVisibility"), hasTrim ? 1 : 0, hasTrim ? 2 : 0);
    }
    public static void setTrimVisibility(ItemStack stack, int i) { stack.getOrCreateTag().putInt("TrimVisibility", i); }

    public static boolean isTrimmed(ItemStack stack) { return stack.getTag() != null && stack.getTag().contains("Trim"); }

    public static int getTextureType(ItemStack stack) { return stack.getOrCreateTag().getInt("TextureType"); }
    public static void setTextureType(ItemStack stack, int type) { stack.getOrCreateTag().putInt("TextureType", type); }
    public static void removeTextureType(ItemStack stack) { if (!hasCore(stack)) stack.getOrCreateTag().remove("TextureType"); }

    public static String armorTexture(ItemStack stack, Entity entity) { return baseTextures(stack, entity); }

    public static String baseTextures(ItemStack stack, Entity entity) {
        String base = "%s:textures/models/armor/catalyst/catalyst_armor_layer.png";
        String format = "%s:textures/models/armor/catalyst/catalyst_%s_armor_layer.png";
        String armor = String.format(Locale.ROOT, format, EID, catalystActivator(stack));
        if (getContentLWeight(stack) > 0 ) {
            if (catalystActivator(stack).equals(ignitium)) {
                if (entity instanceof LivingEntity livingEntity && livingEntity.getMaxHealth() / 2.0F >= livingEntity.getHealth()) {
                    return String.format(Locale.ROOT, format, EID, ignitium + "_soul");
                }
                return String.format(Locale.ROOT, format, EID, ignitium);
            }
            return armor;
        }
        return String.format(Locale.ROOT, base, EID);
    }

    public static String getElytraTexture(ItemStack stack) {
        AtomicReference<String> str = new AtomicReference<>("elytra");
        getCatalystContents(stack).filter(elytra -> elytra.is(EItemTags.CATALYST_ELYTRA)).findAny().ifPresent(e -> str.set(String.valueOf(e.getItem())));
        return String.format(Locale.ROOT, "textures/models/armor/catalyst/elytra/catalyst_%s.png", str.get());
        //TODO: make this registerable like catalyst cores.
    }

    @Deprecated
    private static String testElytraTexture(ItemStack stack) {
        if (getElytraEquipped(stack).findAny().isPresent()) {
            getElytraEquipped(stack).findAny().get().getItem();
        }
        return "elytra";
    }

    @Deprecated
    public static String getTestElytraTexture(ItemStack stack) {
        return String.format(Locale.ROOT, "textures/models/armor/catalyst/elytra/catalyst_%s.png", testElytraTexture(stack));
    }

    public static class Selection {
        private static final String SELECTED = "Selected";

        public static int get(ItemStack stack) {
            CompoundTag tag = stack.getOrCreateTag();
            return tag.getInt(SELECTED);
        }

        public static void set(ItemStack stack, int selection) {
            CompoundTag tag = stack.getOrCreateTag();
            tag.putInt(SELECTED, selection);
        }

        public static void add(ItemStack stack, int amount) {
            CompoundTag tag = stack.getTag();
            var count = tag != null ? Math.max(tag.getList("CatalystItems", 10).size(), 1) : 1;
            set(stack, Math.floorMod(get(stack) + amount, count));
        }

        public static void select(ItemStack stack, int amount) {
            CompoundTag tag = stack.getTag();
//            var count = tag != null ? Math.max(tag.getList("CatalystItems", 10).size(), 1) : 1;
            var count = tag != null ? Math.max(2, 1) : 1;
//            set(stack, Math.floorMod(get(stack) + amount, count));
            set(stack, Mth.clamp(get(stack) + amount, 0, isTrimmed(stack) ? 2 : 1));
        }
    }
}
