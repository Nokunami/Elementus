package net.nokunami.elementus.common.item.unique;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlashEntity;
import net.nokunami.elementus.common.entity.projectile.RushProjectileEntity;
import net.nokunami.elementus.common.entity.projectile.SwordDanceSlashEntity;
import net.nokunami.elementus.common.network.AnthektiteChargeBladeSlashPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.EMobEffects;
import net.nokunami.elementus.common.registry.ESoundEvents;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.common.config.UniqueItemConfig.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnthektiteChargeBlade extends ChargeBladeItem {
    private final Multimap<Attribute, AttributeModifier> swordDanceAttribute;

    public AnthektiteChargeBlade() {
        super(ModTiers.ANTHEKTITE, anthektiteChargeBladeDamage, (float) anthektiteChargeBladeAttackSpeed, (float) anthektiteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
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
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return false;
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

    public static boolean getState(ItemStack stack) {
        return stack.getOrCreateTag().getBoolean("SwordDance");
    }

    public static void setState(ItemStack stack, boolean b) {
        stack.getOrCreateTag().putBoolean("SwordDance", b);
    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 12054986;
    }

    public static void emptyClick(ItemStack stack, InteractionHand hand) {
        if (!stack.isEmpty() && stack.getItem() instanceof AnthektiteChargeBlade){
            ModNetwork.INSTANCE.send(PacketDistributor.SERVER.noArg(), new AnthektiteChargeBladeSlashPacket(hand));
        }
    }

    /// Credits: Goety Mod Death Scythe
    public static void spawnSlash(Player player, InteractionHand hand) {
        Level level = player.level();
        if (player.getAttackStrengthScale(1.0F) >= 0.99F) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), ESoundEvents.ANTHEKTITE_CHARGE_BLADE_WIND_SLASH.get(), SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                AnthektiteSlashEntity slash = new AnthektiteSlashEntity(level, player);
                slash.setOwnerId(player.getUUID());
                slash.setBlockPos(player.blockPosition());
                slash.setDamage(5);
                slash.setDiscardDistance(16);
                slash.setChargeable(!player.hasEffect(EMobEffects.ANTHEKTITE_SWORD_DANCE.get()));
                slash.setItemStack(player.getItemInHand(hand));
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
            slash.setDamage(20);
            slash.setOffsetDegree(player.getRandom().nextIntBetweenInclusive(minOffset, maxOffset));
            slash.setItemStack(player.getItemInHand(hand));
            slash.setMirrored(mirrored);
            Vec3 hitLocation = player.position().add(0.0F, player.getBbHeight() * 0.3F, 0.0F).add(player.getForward().multiply(1.65F, 0.35F, 1.65F));

            slash.moveTo(hitLocation);
            slash.setYRot(player.getYRot());
            level.addFreshEntity(slash);
        }
    }


    public static void rush(Player player) {
        Level level = player.level();
//        level.playSound(null, player, SoundEvents.ENDER_EYE_DEATH, SoundSource.PLAYERS, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
//        level.playSound(null, player, SoundEvents.ELYTRA_FLYING, SoundSource.PLAYERS, 1.0F, 4F);
        level.playSound(null, player, ESoundEvents.ANTHEKTITE_CHARGE_BLADE_RUSH.get(), SoundSource.PLAYERS, 1.0F, 1F);
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

    @SubscribeEvent
    public static void EmptyClickEvents(PlayerInteractEvent.LeftClickEmpty event){
        AnthektiteChargeBlade.emptyClick(event.getItemStack(), event.getEntity().getUsedItemHand());
    }
}
