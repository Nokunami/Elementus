package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlashEntity;
import net.nokunami.elementus.common.entity.projectile.RushProjectileEntity;
import net.nokunami.elementus.common.entity.projectile.SwordDanceSlashEntity;
import net.nokunami.elementus.common.item.EItemUtil;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.server.BladeOfSurgingWindSlashC2SPacket;
import net.nokunami.elementus.common.registry.EMobEffects;
import net.nokunami.elementus.common.registry.ESounds;
import net.nokunami.elementus.common.registry.ETier;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

import static net.nokunami.elementus.Elementus.EID;
import static net.nokunami.elementus.common.config.UniqueItemConfig.*;
import static net.nokunami.elementus.common.entity.MobUtil.blockVec;
import static net.nokunami.elementus.common.item.EItemUtil.rush;
import static net.nokunami.elementus.common.registry.EEnchantments.RESONANCE;
import static net.nokunami.elementus.common.registry.EParticles.PARRY;
import static net.nokunami.elementus.common.registry.ESounds.CHARGE_BLADE_BLOCK;
import static net.nokunami.elementus.common.registry.ESounds.BOR_BLOCK_RESONANCE;
import static net.nokunami.elementus.event.ServerEvents.parryWindow;

@Mod.EventBusSubscriber(modid = EID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BladeOfSurgingWinds extends ChargeBladeItem {
    private final Multimap<Attribute, AttributeModifier> swordDanceAttribute;

    public BladeOfSurgingWinds() {
        super(ETier.EnumTiers.ANTHEKTITE, anthektiteChargeBladeDamage, (float) anthektiteChargeBladeAttackSpeed, (float) anthektiteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        this.swordDanceAttribute = createAttributes(builder, anthektiteChargeBladeAmpDamage, (float) anthektiteChargeBladeAmpAttackSpeed, (float) anthektiteChargeBladeAmpAttackReach).build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot.equals(EquipmentSlot.MAINHAND) & getState(stack) ? swordDanceAttribute : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        setState(stack, entity instanceof LivingEntity living && living.hasEffect(EMobEffects.ANTHEKTITE_SWORD_DANCE.get()));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        friendlyFireTooltip(tooltip, stack);
        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
    }

    @Override
    public int getChargeStack(ItemStack stack) {
        return 4;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    @Override
    public void blockDirectEvent(LivingHurtEvent event, ItemStack stack) {
        DamageSource damageSource = event.getSource();
        LivingEntity eventEntity = event.getEntity();
        Vec3 position = eventEntity.position();
        Vec3 viewVec = eventEntity.getViewVector(1);
        Vec3 vec32 = damageSource.getSourcePosition();
        Entity directAttacker = damageSource.getDirectEntity();
        Entity attacker = damageSource.getEntity();
        boolean usingItem = eventEntity.isUsingItem();
        Level level = eventEntity.level();
        float damage = event.getAmount();
        float randomFloat = 0.5F + (eventEntity.getRandom().nextFloat() - eventEntity.getRandom().nextFloat()) * 0.5F;

        boolean flag = directAttacker instanceof AbstractArrow abstractarrow && abstractarrow.getPierceLevel() > 0;
        if (blockVec(vec32, position, viewVec)) {
            if (!stack.isEmpty() && usingItem) {
                float soundVol = 1;
                Supplier<SoundEvent> soundEvent = EItemUtil.enchantedWith(stack, RESONANCE) ? BOR_BLOCK_RESONANCE : CHARGE_BLADE_BLOCK;
                float knockback = 0;
                if (!damageSource.is(DamageTypeTags.BYPASSES_SHIELD) && !flag) {
                    blockAction(event, false, stack, damage - (damage * damageAbsorption(stack)), 0);
                    knockback = 1;
                }
                level.playSound(null, eventEntity, soundEvent.get(), SoundSource.PLAYERS, soundVol, randomFloat);
                setResonanceCharge(stack, 1);
                MobUtil.applyRecoil(directAttacker, eventEntity, knockback, true);
            }
        }
    }

    private float damageAbsorption(ItemStack stack) {
        float i0 = Math.min(getCharge(stack), getMaxCharge(stack));
        float i1 = getMaxCharge(stack);
        return 1 - ((i0 / i1) * 0.5F);
    }

    private void blockAction(LivingHurtEvent event, boolean cancelEvent, ItemStack stack, float damage, double chargeAmount) {
        event.setCanceled(cancelEvent);
        event.setAmount(damage);
        setCharge(stack, (int) chargeAmount);
    }

    public static boolean getState(ItemStack stack) { return stack.getOrCreateTag().getBoolean("SwordDance"); }
    public static void setState(ItemStack stack, boolean b) { stack.getOrCreateTag().putBoolean("SwordDance", b); }

    @Override
    public void blockProjectileEvent(ProjectileImpactEvent event, ItemStack stack, LivingEntity entity, Projectile projectile, Vec3 pDelta) {
        Level level = entity.level();
        float randomFloat = 0.5F + (entity.getRandom().nextFloat() - entity.getRandom().nextFloat()) * 0.5F;
        Vec3 position = entity.position();
        Vec3 viewVec = entity.getViewVector(1);
        Vec3 vec32 = projectile.position();

        Vec3 vec31 = vec32.vectorTo(position).normalize();
        vec31 = new Vec3(vec31.x, 0.0D, vec31.z);

        if (vec31.dot(viewVec) < 0.0D) {
            if (entity.isUsingItem() && entity.getTicksUsingItem() <= parryWindow) {
                Elementus.LOGGER.debug("testHit2 this deflects \"i hope\"");
                event.setImpactResult(ProjectileImpactEvent.ImpactResult.SKIP_ENTITY);
                parryParticleAlt(level, entity, PARRY.get());
                level.playSound(null, entity, ESounds.CHARGE_BLADE_PARRY.get(), SoundSource.PLAYERS, 1, randomFloat);
                projectile.setDeltaMovement(-pDelta.x/2, -pDelta.y/2, -pDelta.z/2);
            }
        }
    }

    public void parryParticleAlt(Level level, LivingEntity livingEntity, ParticleOptions particleOptions) {
        Vec3 eyePos = livingEntity.getEyePosition();
        Vec3 target = eyePos.add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(1));
        Vec3 offsetToTarget = target.subtract(eyePos);

        Vec3 pos = eyePos.add(offsetToTarget.normalize().scale(1));
        level.addParticle(particleOptions, pos.x, pos.y, pos.z, 0, 0, 0);
    }

    @Override
    public ChargeBladeAbility castAbility(Player player, Level level, ItemStack stack, InteractionHand hand) {
        int cooldown = 0;
        boolean shouldStopUsing = false;
//        if (EItemUtil.enchantedWith(stack, RUSH) && (getCharge(stack) >= getChargeStack(stack) || player.isCreative())) {
//            BladeOfSurgingWinds.windRush(player);
//            setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
//            cooldown = 40;
//        } else {
//            if (player.hasEffect(EMobEffects.ANTHEKTITE_SWORD_DANCE.get())) {
//                BladeOfSurgingWinds.swordDanceSlash(player, hand);
//                cooldown = 240;
//                shouldStopUsing = true;
//                player.swing(hand, true);
//            } else if (!player.hasEffect(EMobEffects.ANTHEKTITE_SWORD_DANCE.get()) && (getCharge(stack) >= getChargeStack(stack) || player.isCreative())) {
//                player.addEffect(new MobEffectInstance(EMobEffects.ANTHEKTITE_SWORD_DANCE.get(), 600));
//                setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
//                cooldown = 10;
//            }
//        }
        if (stack.getItem() instanceof BladeOfSurgingWinds && (getChargedState(stack) || player.isCreative())) {
            ability(level, player, stack);
            swordDanceSlash(player, hand);
            cooldown = 10;
        }
        return new ChargeBladeAbility().setCooldown(cooldown).shouldStopUsingItem(shouldStopUsing);
    }

    public void ability(Level level, LivingEntity attacker, ItemStack stack) {
        if (attacker.isFallFlying()) {
            MobUtil.applyRecoil(attacker, attacker, 1.25, true);
        } else if (!attacker.isCrouching()) {
            if (attacker.onGround() && attacker.getViewXRot(attacker.tickCount) > -5) {
                MobUtil.applyRecoil(attacker, attacker, 0.75, 0, 0.75, false);
                attacker.addDeltaMovement(attacker.getDeltaMovement().add(0, 0.5, 0));
            } else MobUtil.applyRecoil(attacker, attacker, 1, false);
        }
        MobUtil.playEntitySound(attacker, SoundEvents.PLAYER_ATTACK_SWEEP, 1, 2);
        setCharge(stack, -Math.min(getCharge(stack), getChargeStack(stack)));
    }

    @Override public int getBarColor(@NotNull ItemStack pStack) { return 12054986; }

    /// Credits: Goety Mod Death Scythe
    public static void spawnSlashProjectile(Player player, InteractionHand hand) {
        Level level = player.level();
        if (player.getAttackStrengthScale(1.0F) >= 0.99F) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ESounds.BOSW_WIND_SLASH.get(), SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                AnthektiteSlashEntity slash = new AnthektiteSlashEntity(level, player);
                slash.setItemStack(player.getItemInHand(hand));
                slash.setOwnerId(player.getUUID());
                slash.setBlockPos(player.blockPosition());
                slash.setDamage(5);
                slash.setDiscardDistance(16);
                slash.setChargeable(!player.hasEffect(EMobEffects.ANTHEKTITE_SWORD_DANCE.get()));
                slash.launchSlash(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
                level.addFreshEntity(slash);
            }
        }
    }

    public static void swordDanceSlash(Player player, InteractionHand hand) {
        Level level = player.level();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
        if (!level.isClientSide) {
            boolean mirrored = false;
            int minOffset = -30;
            int maxOffset = 5;
            if (player.getUsedItemHand() == InteractionHand.MAIN_HAND) {
                if (player.getMainArm() == HumanoidArm.RIGHT) {
                    mirrored = true;
                    minOffset = 5;
                    maxOffset = 30;
                }
            } else {
                if (player.getMainArm() == HumanoidArm.LEFT) {
                    mirrored = true;
                    minOffset = 5;
                    maxOffset = 30;
                }
            }
            SwordDanceSlashEntity slash = new SwordDanceSlashEntity(level, player);
            slash.setOwnerId(player.getUUID());
            slash.setDamage(10);
            slash.setOffsetDegree(player.getRandom().nextIntBetweenInclusive(minOffset, maxOffset));
            slash.setItemStack(player.getItemInHand(hand));
            slash.setMirrored(mirrored);
            Vec3 hitLocation = player.position().add(0.0F, player.getBbHeight() * 0.3F, 0.0F).add(player.getForward().multiply(1.65F, 0.35F, 1.65F));

            slash.moveTo(hitLocation);
            slash.setYRot(player.getYRot());
            level.addFreshEntity(slash);
        }
    }

    public static void windRush(Player player) {
        Level level = player.level();
//        level.playSound(null, player, SoundEvents.ENDER_EYE_DEATH, SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
//        level.playSound(null, player, SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, 1.0F, 4F);
        level.playSound(null, player, ESounds.BOSW_RUSH.get(), SoundSource.PLAYERS, 1.0F, 1F);
        if (!level.isClientSide) {
            if (player.onGround() && player.getViewXRot(player.tickCount) > -5) {
                MobUtil.applyRecoil(player, player, 1.25, 0, 1.25, true);
                player.addDeltaMovement(player.getDeltaMovement().add(0, 0.5, 0));
//                player.sendSystemMessage(Component.literal("Yo"));
            } else MobUtil.applyRecoil(player, player, 2, 2, 2, true);
//            player.sendSystemMessage(Component.literal(String.valueOf(player.getViewXRot(player.tickCount))));
            RushProjectileEntity slash = new RushProjectileEntity(level, player);
            slash.setOwnerId(player.getUUID());
            slash.setDamage(12);
            slash.setTotalLifespan(20);
            slash.setItemStack(player.getItemInHand(player.getUsedItemHand()));
            level.addFreshEntity(slash);
        }
    }

//    @SubscribeEvent
//    public static void EmptyClickEvents(PlayerInteractEvent.LeftClickEmpty event) {
//        ItemStack stack = event.getItemStack();
//        if (!stack.isEmpty() && stack.getItem() instanceof BladeOfSurgingWinds) {
//            ENetwork.sendToServer(new BladeOfSurgingWindSlashC2SPacket(event.getHand()));
//        }
//    }
}
