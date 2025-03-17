package net.nokunami.elementus.common.item;

import com.github.L_Ender.cataclysm.init.ModEffect;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.common.config.CatalystArmorConfig;
import net.nokunami.elementus.common.registry.ModItems.*;
import net.nokunami.elementus.common.registry.ModMobEffects.*;
import net.nokunami.elementus.common.registry.ModSoundEvents;

import java.util.Locale;
import java.util.Objects;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.*;
import static net.nokunami.elementus.common.item.CatalystArmorItem.*;

public class CatalystItemUtil {
    public static String netherStar = "nether_star";
    public static String ignitium = "ignitium";
    public static String arcane = "arcane";
    public static String heartSea = "heart_of_the_sea";
    public static String totem = "totem";
    public static String cursium = "cursium";
    public static String witheredNetherStar = "withered_nether_star";

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
            p.addEffect(new MobEffectInstance(ISSEffects.ADD_ISS_MANA.get(), 2, 0, false, false, false));
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
            if (type == 0) {
                mobEffect(livingEntity, mobEffectType.HASTE, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.REGEN, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.SPEED, duration, amp1, true, true, true);
                mobEffect(livingEntity, mobEffectType.RESIST, duration, amp1, true, true, true);
            }
            if (type == 1) {
                mobEffect(livingEntity, mobEffectType.JUMP, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.HASTE, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.REGEN, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.SPEED, duration, amp2, true, true, true);
                mobEffect(livingEntity, mobEffectType.RESIST, duration, amp2, true, true, true);
            }
            if (type == 2) {
                mobEffect(livingEntity, mobEffectType.SEA, duration, amp1, false, true, true);
            }
        }
    }

    public static void effectRadius(Player p, ItemStack stack, Level w) {
        Level level = p.level();
        String catalyst = catalystActivator(stack);
        int type = 0;
        if (catalyst.equals(witheredNetherStar)) {
            type = 1;
        } else if (catalyst.equals(heartSea)) {
            type = 2;
        }
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
    public static String armorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return baseTextures(stack, entity);
    }

    public static String baseTextures(ItemStack stack, Entity entity) {
        String base = "%s:textures/models/armor/catalyst/catalyst_armor_layer.png";
        String format = "%s:textures/models/armor/catalyst/catalyst_%s_armor_layer.png";
        String armor = String.format(Locale.ROOT, format, MODID, catalystActivator(stack));
        if (getContentWeight(stack) > 0) {
            if (catalystActivator(stack).equals(ignitium)) {
                if (entity instanceof LivingEntity livingEntity) {
                    boolean half = livingEntity.getMaxHealth() / 2.0F >= livingEntity.getHealth();
                    if (half) {
                        return String.format(Locale.ROOT, format, MODID, ignitium + "_soul");
                    }
                    return String.format(Locale.ROOT, format, MODID, ignitium);
                }
            }
            return armor;
        }
        return String.format(Locale.ROOT, base, MODID);
    }

    private static String elytraTexture(ItemStack stack) {
        if (getElytraEquiped(stack).findAny().isPresent()) {
            getElytraEquiped(stack).findAny().get().getItem();
        }
        return "elytra";
    }

    public static String getElytraTexture(ItemStack stack) {
        return String.format(Locale.ROOT, /*MODID +*/ "textures/models/armor/catalyst/elytra/catalyst_%s.png", elytraTexture(stack));
    }
}
