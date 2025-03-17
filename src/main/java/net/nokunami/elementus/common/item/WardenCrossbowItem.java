package net.nokunami.elementus.common.item;

import com.google.common.collect.Lists;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class WardenCrossbowItem extends CrossbowItem implements Vanishable {
    private static final String CHARGED_KEY = "Charged";
    private static final String CHARGED_PROJECTILES_KEY = "ChargedProjectiles";
    private static final int DEFAULT_PULL_TIME = 40;
    private static final int BOOM_RANGE = 16;
    private static final float BOOM_DAMAGE = 15.0F;
    private boolean charged = false;
    private boolean loaded = false;
    public static final Predicate<ItemStack> CROSSBOW_PROJECTILES = (stack) -> stack.is(Items.ECHO_SHARD);

    public WardenCrossbowItem(Properties pProperties) {
        super(pProperties);
    }

    public @NotNull Predicate<ItemStack> getSupportedHeldProjectiles() {
        return CROSSBOW_PROJECTILES;
    }

    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return CROSSBOW_PROJECTILES;
    }


    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player user, @NotNull InteractionHand hand) {
        ItemStack itemStack = user.getItemInHand(hand);
        if (isCharged(itemStack)) {
            shoot(world, user, hand, itemStack);
            setCharged(itemStack, false);
            return InteractionResultHolder.consume(itemStack);
        } else if (!user.getProjectile(itemStack).isEmpty()) {
            if (!isCharged(itemStack)) {
                this.charged = false;
                this.loaded = false;
                user.startUsingItem(hand);
            }

            return InteractionResultHolder.consume(itemStack);
        } else {
            return InteractionResultHolder.fail(itemStack);
        }
    }

    public void releaseUsing(@NotNull ItemStack stack, @NotNull Level world, @NotNull LivingEntity user, int remainingUseTicks) {
        int i = this.method_7881(stack) - remainingUseTicks;
        float f = getPullProgress(i, stack);
        if (f >= 1.0F && !isCharged(stack) && loadProjectiles(user, stack)) {
            setCharged(stack, true);
            SoundSource soundCategory = user instanceof Player ? SoundSource.PLAYERS : SoundSource.HOSTILE;
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.CROSSBOW_LOADING_END, soundCategory, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.5F + 1.0F) + 0.2F);
        }

    }

    private static boolean loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, crossbow);
        int j = i == 0 ? 1 : 3;
        boolean bl = shooter instanceof Player && ((Player)shooter).getAbilities().instabuild;
        ItemStack itemStack = shooter.getProjectile(crossbow);
        ItemStack itemStack2 = itemStack.copy();

        for(int k = 0; k < j; ++k) {
            if (k > 0) {
                itemStack = itemStack2.copy();
            }

            if (itemStack.isEmpty() && bl) {
                itemStack = new ItemStack(Items.ARROW);
                itemStack2 = itemStack.copy();
            }

            if (!loadProjectile(shooter, crossbow, itemStack, k > 0, bl)) {
                return false;
            }
        }

        return true;
    }

    private static boolean loadProjectile(LivingEntity shooter, ItemStack crossbow, ItemStack projectile, boolean simulated, boolean creative) {
        if (projectile.isEmpty()) {
            return false;
        } else {
            boolean bl = creative && projectile.getItem() instanceof ArrowItem;
            if (!bl && !creative && !simulated) {
                ItemStack itemStack = projectile.split(1);
                if (projectile.isEmpty() && shooter instanceof Player) {
                    ((Player)shooter).getInventory().removeItem(projectile);
                }
            } else {
                ItemStack itemStack = projectile.copy();
            }

            putProjectile(crossbow, new ItemStack(Items.ECHO_SHARD));
            return true;
        }
    }

    public static boolean isCharged(ItemStack stack) {
        CompoundTag nbtCompound = stack.getTag();
        return nbtCompound != null && nbtCompound.getBoolean(CHARGED_KEY);
    }

    public static void setCharged(ItemStack stack, boolean charged) {
        CompoundTag nbtCompound = stack.getOrCreateTag();
        nbtCompound.putBoolean(CHARGED_KEY, charged);
    }

    private static void putProjectile(ItemStack crossbow, ItemStack projectile) {
        CompoundTag nbtCompound = crossbow.getOrCreateTag();
        ListTag nbtList = nbtCompound.contains(CHARGED_PROJECTILES_KEY, 9) ? nbtCompound.getList(CHARGED_PROJECTILES_KEY, 10) : new ListTag();
        CompoundTag nbtCompound2 = new CompoundTag();
        projectile.save(nbtCompound2);
        nbtList.add(nbtCompound2);
        nbtCompound.put(CHARGED_PROJECTILES_KEY, nbtList);
    }

    private static List<ItemStack> getProjectiles(ItemStack crossbow) {
        ArrayList<ItemStack> list = Lists.newArrayList();
        CompoundTag nbtCompound = crossbow.getTag();
        ListTag nbtList;
        if (nbtCompound != null && nbtCompound.contains(CHARGED_PROJECTILES_KEY, 9) && (nbtList = nbtCompound.getList(CHARGED_PROJECTILES_KEY, 10)) != null) {
            for(int i = 0; i < nbtList.size(); ++i) {
                CompoundTag nbtCompound2 = nbtList.getCompound(i);
                list.add(ItemStack.of(nbtCompound2));
            }
        }

        return list;
    }

    private static void clearProjectiles(ItemStack crossbow) {
        CompoundTag nbtCompound = crossbow.getTag();
        if (nbtCompound != null) {
            ListTag nbtList = nbtCompound.getList(CHARGED_PROJECTILES_KEY, 9);
            nbtList.clear();
            nbtCompound.put(CHARGED_PROJECTILES_KEY, nbtList);
        }

    }

    private static void shoot(Level world, LivingEntity shooter, InteractionHand hand, ItemStack crossbow) {
        if (!world.isClientSide) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, crossbow);
            if (hand != null) {
                crossbow.hurtAndBreak(1, shooter, (e) -> e.broadcastBreakEvent(hand));
            }

            boolean b = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, crossbow) > 0;
            if (b) {
                createBoom(world, shooter, i, crossbow, 20.0F);
                createBoom(world, shooter, i, crossbow, 0.0F);
                createBoom(world, shooter, i, crossbow, -20.0F);
            } else {
                createBoom(world, shooter, i, crossbow, 0.0F);
            }

            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.WARDEN_SONIC_BOOM, SoundSource.PLAYERS, 1.0F, 1.0F);
            world.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
    }

    private static void createBoom(Level world, LivingEntity user, int piercing, ItemStack crossbow, float multiOffSet) {
        Vec3 target = user.getEyePosition().add(Vec3.directionFromRotation(user.getXRot(), user.yHeadRot + multiOffSet).scale(BOOM_RANGE + 4 * piercing));
        Vec3 source = user.getEyePosition();
        Vec3 offsetToTarget = target.subtract(source);
        Vec3 normalized = offsetToTarget.normalize();
        boolean firstTick = true;
        Set<Entity> hitSet = new HashSet<>();

        for(int particleIndex = 1; particleIndex < Mth.floor(offsetToTarget.length()) + 7; ++particleIndex) {
            Vec3 particlePos = source.add(normalized.scale(particleIndex));
            if (firstTick) {
                ((ServerLevel)world).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 5, 0.0F, 0.0F, 0.0F, 0.0F);
                firstTick = false;
            }

            ((ServerLevel)world).sendParticles(ParticleTypes.SONIC_BOOM, particlePos.x, particlePos.y, particlePos.z, 1, 0.0F, 0.0F, 0.0F, 0.0F);
            hitSet.addAll(world.getEntitiesOfClass(LivingEntity.class, (new AABB(new BlockPos((int)particlePos.x(), (int)particlePos.y(), (int)particlePos.z()))).inflate((double)0.5F), (it) -> !(it instanceof Wolf) || !((Wolf)it).isTame() || ((Wolf)it).getOwner() != user));
        }

        hitSet.remove(user);

        for(Entity hitTarget : hitSet) {
            if (hitTarget instanceof LivingEntity living) {
                living.hurt(of(world, DamageTypes.SONIC_BOOM), BOOM_DAMAGE);
            }
        }

        postShoot(world, user, crossbow);
    }

    public static DamageSource of(Level world, ResourceKey<DamageType> key) {
        return new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key));
    }

    private static float[] getSoundPitches(RandomSource random) {
        boolean bl = random.nextBoolean();
        return new float[]{1.0F, getSoundPitch(bl, random), getSoundPitch(!bl, random)};
    }

    private static float getSoundPitch(boolean flag, RandomSource random) {
        float f = flag ? 0.63F : 0.43F;
        return 1.0F / (random.nextFloat() * 0.5F + 1.8F) + f;
    }

    private static void postShoot(Level world, LivingEntity entity, ItemStack stack) {
        if (entity instanceof ServerPlayer serverPlayerEntity) {
            if (!world.isClientSide) {
                CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayerEntity, stack);
            }

            serverPlayerEntity.awardStat(Stats.ITEM_USED.get(stack.getItem()));
        }
        clearProjectiles(stack);
    }

    public void onUseTick(Level world, @NotNull LivingEntity user, @NotNull ItemStack stack, int remainingUseTicks) {
        if (!world.isClientSide) {
            int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
            SoundEvent soundEvent = this.getQuickChargeSound(i);
            SoundEvent soundEvent2 = i == 0 ? SoundEvents.CROSSBOW_LOADING_MIDDLE : null;
            float f = (float)(stack.getUseDuration() - remainingUseTicks) / (float)getPullTime(stack);
            if (f < 0.2F) {
                this.charged = false;
                this.loaded = false;
            }

            if (f >= 0.2F && !this.charged) {
                this.charged = true;
                world.playSound((Player)null, user.getX(), user.getY(), user.getZ(), soundEvent, SoundSource.PLAYERS, 0.5F, 1.0F);
            }

            if (f >= 0.5F && soundEvent2 != null && !this.loaded) {
                this.loaded = true;
                world.playSound((Player)null, user.getX(), user.getY(), user.getZ(), soundEvent2, SoundSource.PLAYERS, 0.5F, 1.0F);

                assert Minecraft.getInstance().gameMode != null;
            }
        }

    }

    public int method_7881(ItemStack stack) {
        return getPullTime(stack) + 3;
    }

    public static int getPullTime(ItemStack stack) {
        int i = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, stack);
        return i == 0 ? DEFAULT_PULL_TIME : DEFAULT_PULL_TIME - 5 * i;
    }

    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.CROSSBOW;
    }

    private SoundEvent getQuickChargeSound(int stage) {
        switch (stage) {
            case 1 -> {
                return SoundEvents.CROSSBOW_QUICK_CHARGE_1;
            }
            case 2 -> {
                return SoundEvents.CROSSBOW_QUICK_CHARGE_2;
            }
            case 3 -> {
                return SoundEvents.CROSSBOW_QUICK_CHARGE_3;
            }
            default -> {
                return SoundEvents.CROSSBOW_LOADING_START;
            }
        }
    }

    private static float getPullProgress(int useTicks, ItemStack stack) {
        float f = (float)useTicks / (float)getPullTime(stack);
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag context) {
        List<ItemStack> list = getProjectiles(stack);
        if (isCharged(stack) && !list.isEmpty()) {
            ItemStack itemStack = (ItemStack)list.get(0);
            tooltip.add(Component.translatable("item.minecraft.crossbow.projectile").append(CommonComponents.SPACE).append(itemStack.getDisplayName()));
        }
    }
}
