package net.nokunami.elementus.common.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;
import net.nokunami.elementus.common.config.ItemConfig;
import net.nokunami.elementus.common.entity.projectile.AnthektiteSlash;
import net.nokunami.elementus.common.network.AnthektiteSlashPacket;
import net.nokunami.elementus.common.network.ModNetwork;
import net.nokunami.elementus.common.registry.ModTiers;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static net.nokunami.elementus.Elementus.MODID;
import static net.nokunami.elementus.ModChecker.betterCombat;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AnthektiteChargeBlade extends SwordItem {
    private static final int BURST_RANGE = 1;
    private static final int BOOM_RANGE = 16;
    public static final String CHARGE_TAG = "Charge";
    private boolean startSoundPlayed = false;
    private boolean midLoadSoundPlayed = false;
    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    protected static final UUID ATTACK_REACH_UUID = UUID.fromString("fe181be2-3fd8-4a90-ba64-a4a06cef6d27");

    public AnthektiteChargeBlade() {
        super(ModTiers.ANTHEKTITE, 0, 0, new Properties().fireResistant().rarity(Rarity.EPIC));
        float attackDamage = (float) ItemConfig.diarkriteChargeBladeDamage/* + this.getTier().getAttackDamageBonus()*/;
        float attackSpeed = (float) ItemConfig.diarkriteChargeBladeAttackSpeed;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", attackDamage, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed, AttributeModifier.Operation.ADDITION));
        if (!betterCombat) builder.put(ForgeMod.ENTITY_REACH.get(), new AttributeModifier(ATTACK_REACH_UUID, "Weapon modifier", ItemConfig.diarkriteChargeBladeAttackReach, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        return slot.equals(EquipmentSlot.MAINHAND) ? this.defaultModifiers : super.getAttributeModifiers(slot, stack);
    }

//    @Override
//    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity livingEntity, int timeCharged) {
//        if (livingEntity instanceof Player) {
//            int i = this.getUseDuration(stack) - timeCharged;
//            if (i < 0) return;
//            float f = getPowerForTime(i);
//            if (!((double)f < 1D)) {
//                if (!level.isClientSide) {
//                }
//                if (enchanted(stack, 0)) livingEntity.hurt(level.damageSources().sonicBoom(livingEntity), livingEntity.getMaxHealth()*0.25F);
//            }
//        }
//    }

    public static float getPowerForTime(int pCharge) {
        float f = (float)pCharge / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    public int getUseDuration(@NotNull ItemStack pStack) {
        return 72000;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack pStack) {
        return UseAnim.BOW;
    }

//    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand hand) {
//        ItemStack itemStack = player.getItemInHand(hand);
//        if (getCharge(itemStack) > 2 || enchanted(itemStack, 0) || player.isCreative()) {
//            player.startUsingItem(hand);
//            return InteractionResultHolder.consume(itemStack);
//        } else return super.use(level, player, hand);
//    }

    @Override
    public void onUseTick(Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (!pLevel.isClientSide) {
            int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, pStack);
            SoundEvent soundevent = SoundEvents.WARDEN_SONIC_CHARGE;
            SoundEvent soundevent1 = i == 0 ? SoundEvents.VEX_CHARGE : null;
            float f = (float)(pStack.getUseDuration() - pRemainingUseDuration) / (float)getChargeDuration(pStack);
            if (f < 0.2F) {
                this.startSoundPlayed = false;
                this.midLoadSoundPlayed = false;
            }
            if (f >= 0.2F && !this.startSoundPlayed) {
                this.startSoundPlayed = true;
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), soundevent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
            if (f >= 0.5F && soundevent1 != null && !this.midLoadSoundPlayed) {
                this.midLoadSoundPlayed = true;
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), soundevent1, SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
    }

    public static int getChargeDuration(ItemStack pCrossbowStack) {
        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.QUICK_CHARGE, pCrossbowStack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

//    @Override
//    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
//        String chargeText = enchanted(stack, 0) ? "item.elementus.diarkrite_charge_blade.cursed_charge_desc" : "item.elementus.diarkrite_charge_blade.charge_desc";
//        ChatFormatting chargeTextColor = enchanted(stack, 0) ? ChatFormatting.DARK_RED : ChatFormatting.GRAY;
//        ChatFormatting underlineText = enchanted(stack, 0) ? ChatFormatting.UNDERLINE : ChatFormatting.GRAY;
//        String damageModifierText = enchanted(stack, 0) && enchanted(stack, 1) ? " (+25%) (-40%)" : enchanted(stack, 0) ? " (+25%)" : enchanted(stack, 1) ? " (-40%)" : "";
//        ChatFormatting damageModifierColor = enchanted(stack, 0) || enchanted(stack, 1) ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA;
//
//        tooltip.add(Component.translatable(chargeText, String.valueOf(getCharge(stack))).withStyle(underlineText).withStyle(chargeTextColor));
//        if (!Screen.hasShiftDown()) {
//            tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.damage_ratio_desc").withStyle(ChatFormatting.GRAY)
//                    .append(Component.translatable(Math.round(getChargeAmount(stack) * 100) + "%").withStyle(ChatFormatting.DARK_AQUA)
//                            .append(Component.translatable(damageModifierText).withStyle((damageModifierColor)))));
//        } else {
//            tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.damage_ratio_shift_desc").withStyle(ChatFormatting.GRAY)
//                    .append(Component.translatable(Math.round(getChargeAmount(stack) * 100) + "%").withStyle(ChatFormatting.DARK_AQUA)));
//            if (enchanted(stack, 0)) tooltip.add(Component.translatable(" +25% ").append(Component.translatable("enchantment.elementus.sacrifice_curse")).withStyle(ChatFormatting.RED));
//            if (enchanted(stack, 1)) tooltip.add(Component.translatable(" -40% ").append(Component.translatable("enchantment.elementus.condensed_burst")).withStyle(ChatFormatting.AQUA));
//        }
//        tooltip.add(Component.translatable("item.elementus.diarkrite_charge_blade.friendly_fire_desc",
//                String.valueOf(getFriendlyFire(stack)).toUpperCase(Locale.ROOT)).withStyle(ChatFormatting.GRAY));
//        if (stack.isEnchanted()) tooltip.add(CommonComponents.EMPTY);
//    }

//    @Override
//    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
//        if (target.attackable() && attacker instanceof Player player && player.getAttackStrengthScale(1.0F) >= 0.5F && !enchanted(stack, 0)) {
//            setCharge(stack, getCharge(stack) < getMaxCharge(stack) ? getCharge(stack) + 1 : getCharge(stack));
//            if (getChargedState(stack)) {
//                player.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
//                attacker.playSound(SoundEvents.EXPERIENCE_ORB_PICKUP);
//            }
//        }
//        return super.hurtEnemy(stack, target, attacker);
//    }
//
//    public static boolean enchanted(ItemStack itemStack, int type) {
//        int curse = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.SACRIFICE_CURSE.get(), itemStack);
//        int resonance = EnchantmentHelper.getTagEnchantmentLevel(ModEnchantments.CONDENSED_BURST.get(), itemStack);
//        return type == 0 ? curse > 0 : type == 1 && resonance > 0;
//    }

//    public static void setCharge(ItemStack stack, int amount) {
//        stack.getOrCreateTag().putInt(CHARGE_TAG, amount);
//    }
//
//    public static int getCharge(ItemStack stack) {
//        return stack.getOrCreateTag().getInt(CHARGE_TAG);
//    }
//
//    public boolean getChargedState(ItemStack stack) {
//        return getCharge(stack) == getMaxCharge(stack);
//    }
//
//    public static int getMaxCharge(ItemStack itemStack) {
//        int i = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.BLOCK_FORTUNE, itemStack);
//        return i > 0 ? 8 : 7;
//    }

//    public static float getChargeAmount(ItemStack stack) {
//        int i0 = getCharge(stack);
//        int i1 = getMaxCharge(stack);
//        float base = (float) i0 / i1;
//        if (enchanted(stack, 0)) base = 1.25F;
//        return base * (enchanted(stack, 1) ? 0.6F : 1);
//    }

//    public static boolean getFriendlyFire(ItemStack stack) {
//        return stack.getOrCreateTag().getBoolean("FriendlyFire");
//    }

//    public void setFriendlyFire(ItemStack stack, boolean b) {
//        stack.getOrCreateTag().putBoolean("FriendlyFire", b);
//    }

//    @Override
//    public boolean overrideOtherStackedOnMe(@NotNull ItemStack stack, ItemStack otherStack, @NotNull Slot slot, @NotNull ClickAction action, @NotNull Player player, @NotNull SlotAccess access) {
//        if (otherStack.isEmpty() && Screen.hasAltDown()) {
//            setFriendlyFire(stack, !getFriendlyFire(stack));
//            return true;
//        }
//        return super.overrideOtherStackedOnMe(stack, otherStack, slot, action, player, access);
//    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return false;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
        return true;
    }

    @Override
    public boolean canBeHurtBy(DamageSource pDamageSource) {
        return pDamageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY);
    }

//    @Override
//    public int getBarWidth(@NotNull ItemStack pStack) {
//        return Math.round(13.0F - (float) (getMaxCharge(pStack) - getCharge(pStack)) * 13.0F / (float) getMaxCharge(pStack));
//    }

    @Override
    public int getBarColor(@NotNull ItemStack pStack) {
        return 7924965;
    }

//    @Override
//    public boolean isBarVisible(@NotNull ItemStack pStack) {
//        return getCharge(pStack) > 0;
//    }

    public static void emptyClick(ItemStack stack) {
        if (!stack.isEmpty() && stack.getItem() instanceof AnthektiteChargeBlade){
            ModNetwork.INSTANCE.send(PacketDistributor.SERVER.noArg(), new AnthektiteSlashPacket());
        }
    }

    /// Credits: Goety Mod Death Scythe
    public static void spawnSlash(Player player) {
        Level level = player.level();
        if (player.getAttackStrengthScale(0.5F) > 0.9F) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_ATTACK_SWEEP, SoundSource.NEUTRAL, 2.0F, 0.4F / (level.random.nextFloat() * 0.4F + 0.8F));
            if (!level.isClientSide) {
                AnthektiteSlash slash = new AnthektiteSlash(level, player);
                slash.setOwnerId(player.getUUID());
                slash.setDamage(8);
                slash.setTotallife(10);
                slash.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.75F, 1.0F);
                level.addFreshEntity(slash);
            }
        }
    }

    @SubscribeEvent
    public static void EmptyClickEvents(PlayerInteractEvent.LeftClickEmpty event){
        AnthektiteChargeBlade.emptyClick(event.getItemStack());
    }
}
