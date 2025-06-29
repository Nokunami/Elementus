package nokunami.elementus.common.item;

import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import nokunami.elementus.common.entity.projectile.AnthektiteSlash;
import nokunami.elementus.common.network.AnthektiteChargeBladeSlashPacket;
import nokunami.elementus.common.network.ModNetwork;
import nokunami.elementus.common.registry.ModMobEffects;
import nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Locale;

import static nokunami.elementus.Elementus.MODID;
import static nokunami.elementus.common.config.UniqueItemConfig.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnthektiteChargeBlade extends ChargeSwordItem {
    private final Lazy<Multimap<Attribute, AttributeModifier>> swordDanceAttribute;

    public AnthektiteChargeBlade() {
        super(ModTiers.ANTHEKTITE, anthektiteChargeBladeDamage, (float) anthektiteChargeBladeAttackSpeed, (float) anthektiteChargeBladeAttackReach, new Properties().fireResistant().rarity(Rarity.EPIC));
        swordDanceAttribute = Lazy.of(() -> createDefaultAttributeModifiers(anthektiteChargeBladeAmpDamage, (float) anthektiteChargeBladeAmpAttackSpeed, (float) anthektiteChargeBladeAmpAttackReach).build());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot.equals(EquipmentSlot.MAINHAND) ? getState(stack) ? this.swordDanceAttribute.get() : this.defaultModifiers.get() : super.getAttributeModifiers(slot, stack);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        setState(stack, entity instanceof LivingEntity living && living.hasEffect(MobEffects.DAMAGE_RESISTANCE));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        String friendlyFireStr = String.valueOf(getFriendlyFire(stack));
        super.appendHoverText(stack, level, tooltip, flag);

        tooltip.add(Component.translatable("item.elementus.charge_item.friendly_fire_desc",
                friendlyFireStr.substring(0, 1).toUpperCase(Locale.ROOT) + friendlyFireStr.substring(1)).withStyle(ChatFormatting.GRAY));
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

    public static void parryParticle(Level level, LivingEntity livingEntity, ParticleOptions particleOptions) {
        Vec3 eyePos = livingEntity.getEyePosition();
        Vec3 target = eyePos.add(Vec3.directionFromRotation(livingEntity.getXRot(), livingEntity.yHeadRot).scale(1));
        Vec3 offsetToTarget = target.subtract(eyePos);

        Vec3 particle = eyePos.add(offsetToTarget.normalize().scale(2));
        ((ServerLevel)level).sendParticles(particleOptions, particle.x, particle.y, particle.z, 0, 0.0F, 0.0F, 0.0F, 0.0F);
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
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_EYE_DEATH, SoundSource.NEUTRAL, 1.0F, 1.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                AnthektiteSlash slash = new AnthektiteSlash(level, player);
                slash.setOwnerId(player.getUUID());
                slash.setBlockPos(player.blockPosition());
                slash.setDamage(5);
                slash.setDiscardDistance(16);
                slash.setChargeable(!player.hasEffect(ModMobEffects.ElementusEffects.ANTHEKTITE_SWORD_DANCE.get()));
                slash.setItemStack(player.getItemInHand(hand));
                slash.launchSlash(player, player.getXRot(), player.getYRot(), 0.0F, 0.25F, 1.0F);
                level.addFreshEntity(slash);
            }
        }
    }

    @SubscribeEvent
    public static void EmptyClickEvents(PlayerInteractEvent.LeftClickEmpty event){
        AnthektiteChargeBlade.emptyClick(event.getItemStack(), event.getEntity().getUsedItemHand());
    }
}
