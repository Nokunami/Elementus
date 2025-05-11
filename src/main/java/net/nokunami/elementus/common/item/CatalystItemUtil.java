package net.nokunami.elementus.common.item;

import com.github.L_Ender.cataclysm.init.ModEffect;
import com.google.common.collect.ImmutableMultimap;
import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.ModArmorMaterials;
import net.nokunami.elementus.common.registry.ModItems.ElementusItems;
import net.nokunami.elementus.common.registry.ModMobEffects;
import net.nokunami.elementus.common.registry.ModMobEffects.ElementusEffects;
import net.nokunami.elementus.common.registry.ModSoundEvents;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.cataclysm;
import static net.nokunami.elementus.ModChecker.ironsSpellbooks;
import static net.nokunami.elementus.common.config.CatalystArmorConfig.*;
import static net.nokunami.elementus.common.item.CatalystArmorItem.*;

public class CatalystItemUtil {
    public static final String netherStar = "nether_star";
    public static final String ignitium = "ignitium";
    public static final String arcane = "arcane";
    public static final String heartSea = "heart_of_the_sea";
    public static final String totem = "totem";
    public static final String cursium = "cursium";
    public static final String witheredNetherStar = "withered_nether_star";

    public static final UUID maxManaUUID = UUID.fromString("58868843-e045-405d-bd63-05eefabb7383");
    public static final UUID manaRegenUUID = UUID.fromString("6f40fd38-64eb-469f-8fe6-854c9ca5e73a");
    public static final UUID spellPowerUUID = UUID.fromString("80d938ca-e805-4cf5-8239-e019a687acf6");
    public static final UUID spellResistUUID = UUID.fromString("ab2b408a-cc76-447a-829b-1161e4313726");

    public static void netherStar(ItemStack s, Player p) {
        boolean healthActivation = p.getMaxHealth() / 2.0F >= p.getHealth();
        if (catalystActivator(s).equals(CatalystItemUtil.netherStar)) {
            if (healthActivation && !p.getCooldowns().isOnCooldown(s.getItem())) {
                p.playSound(ModSoundEvents.CATALYST_ARMOR_ACTIVATE.get(), 1.25F, 1.5F + p.level().getRandom().nextFloat() * 0.4F);
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
                mobEffect(p, mobEffectType.HASTE, CatalystArmorConfig.ignitium_HasteDuration, CatalystArmorConfig.ignitium_HasteAmp, false, false, true);
                mobEffect(p, mobEffectType.STRENGTH, CatalystArmorConfig.ignitium_StrengthDuration, CatalystArmorConfig.ignitium_StrengthAmp, false, false, true);
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

    public static void addCUstomAttribute(ModArmorMaterials m, Type t, ItemStack s) {
        UUID uuid = ARMOR_MODIFIER_UUID_PER_TYPE.get(Type.CHESTPLATE);
        s.addAttributeModifier(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", m.getDefenseForType(t), AttributeModifier.Operation.ADDITION), t.getSlot());
        s.addAttributeModifier(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", m.getToughness(), AttributeModifier.Operation.ADDITION), t.getSlot());
        if (m.getKnockbackResistance() > 0) {
            s.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Knockback resistance", m.getKnockbackResistance(), Operation.ADDITION), t.getSlot());
        }
        if (ironsSpellbooks && catalystActivator(s).equals(arcane)) {
            if (ISS_MaxMana != 0) {
                s.addAttributeModifier(AttributeRegistry.MAX_MANA.get(), new AttributeModifier(maxManaUUID, "maxMana", ISS_MaxMana, Operation.ADDITION), t.getSlot());
            }
            if (ISS_ManaRegen != 0) {
                s.addAttributeModifier(AttributeRegistry.MANA_REGEN.get(), new AttributeModifier(manaRegenUUID, "manaRegen", ISS_ManaRegen, Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST);
            }
            if (ISS_SpellPower != 0) {
                s.addAttributeModifier(AttributeRegistry.SPELL_POWER.get(), new AttributeModifier(spellPowerUUID, "spellPower", ISS_SpellPower, Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST);
            }
            if (ISS_SpellResist != 0) {
                s.addAttributeModifier(AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier(spellResistUUID, "spellResist", ISS_SpellResist, Operation.MULTIPLY_TOTAL), EquipmentSlot.CHEST);
            }
        } else {
            s.removeTagKey("AttributeModifiers");
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
            if (chestplateItem.is(ElementusItems.CATALYST_CHESTPLATE.get())) {
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
            } else if (entity instanceof OwnableEntity tamableAnimal) {
                if (Objects.requireNonNull(tamableAnimal.getOwner()).is(p) || tamableAnimal.getOwner().isAlliedTo(p)) {
                    alliedMobEffects((Entity) tamableAnimal, type);
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
        if (getContentWeight(stack) > 0) {
            if (catalystActivator(stack).equals(ignitium)) {
//                if (entity instanceof LivingEntity livingEntity) {
//                    boolean half = livingEntity.getMaxHealth() / 2.0F >= livingEntity.getHealth();
//                    if (half) {
//                        return String.format(Locale.ROOT, format, MODID, ignitium + "_soul");
//                    }
//                    return String.format(Locale.ROOT, format, MODID, ignitium);
//                }
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
}
