package net.nokunami.elementus.common.capability.abilityData;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.*;
import net.nokunami.elementus.common.catalystCore.ability.*;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.CACooldownSyncS2CPacket;
import net.nokunami.elementus.common.network.client.SyncAbilitySelectionS2CPacket;
import net.nokunami.elementus.common.network.client.SyncCastStateS2CPacket;
import net.nokunami.elementus.common.network.client.ability.AbilityListSyncS2CPacket;

import static net.nokunami.elementus.api.util.AttributeUtil.attributeUtil;
import static net.nokunami.elementus.common.catalystCore.ability.AbilityManager.castMoveSpeedUUID;
import static net.nokunami.elementus.common.catalystCore.ability.AbilityManager.logCastStateSwitch;

public class AbilityData {
    private ServerPlayer serverPlayer;
    private LivingEntity livingEntity;
    private CooldownData cooldownInstance = new CooldownData();
    private boolean castKeyState = false;
    private boolean shouldTickCast = false;
    private AbilityStorage storage = new AbilityStorage();
    private AbilityInstance abilityInstance = new AbilityInstance();

    private int castTick = 0;
    private int castDuration = 0;

    public AbilityData() { }

    public AbilityData(LivingEntity entity) {
        livingEntity = entity;
        if (entity instanceof ServerPlayer player) {
            serverPlayer = player;
        }
    }

    public LivingEntity getEntity() { return livingEntity; }
    public void setPlayer(ServerPlayer player) { serverPlayer = player; }

    public CooldownData getCooldowns() { return cooldownInstance; }
    public boolean isOnCooldown(AbstractAbility ability) { return getCooldowns().isOnCooldown(ability); }
    public void addCooldown(AbstractAbility ability) {
        boolean isCreative = livingEntity instanceof Player player && player.isCreative();
        int cooldown = isCreative ? 10 : ability.getCooldown();
        getCooldowns().addCooldown(ability.getAsString(), cooldown, ability.getCharges());
        if (livingEntity instanceof ServerPlayer player) {
            ENet.sendTo(player, new CACooldownSyncS2CPacket(ability.getAsString(), cooldown, ability.getCharges()));
            AbilityManager.sync(livingEntity, livingEntity.level());
        }
    }

    public boolean isCastKeyDown() { return castKeyState; }
    public void setCastKeyDown(boolean b) { castKeyState = b; }

    public boolean isCastTicking() { return shouldTickCast; }
    public void setCastTicking(boolean b) { shouldTickCast = b; }

    public AbilityInstance getAbilityInstance() { return abilityInstance; }

    public int getPrevSelection() { return getAbilityInstance().getPrevSelection(); }
    public int getSelection() { return getAbilityInstance().getSelection(); }

    public int getPreCastDuration() { return getAbilityInstance().getPreCastDuration(); }
    public void setPreCastDuration(int i) { getAbilityInstance().setPreCastDuration(i); }

    public int getPreCastTick() { return getAbilityInstance().getPreCastTick(); }
    public void setPreCastTick(int i) { getAbilityInstance().setPreCastTick(i); }

    public boolean preCastTickReached() { return getPreCastTick() == getPreCastDuration(); }

    public int getCastDuration() { return getAbilityInstance().getCastDuration(); }
    public void setCastDuration(int i) { getAbilityInstance().setCastDuration(i); }

    public int getCastTick() { return getAbilityInstance().getCastTick(); }
    public void setCastTick(int i) { getAbilityInstance().setCastTick(i); }

    public boolean castTickReached() { return getCastTick() == getCastDuration(); }

    public CastState getCastState() { return getAbilityInstance().getCastState(); }
    public void setCastState(CastState state) {
        getAbilityInstance().setCastState(state);
        logCastStateSwitch(state);
    }
    public boolean isCastState(CastState state) { return getAbilityInstance().getCastState().equals(state); }

    public AbstractAbility getStoredAbility() { return getAbilityInstance().getAbility(); }
    public boolean castAbility() { return getStoredAbility().castAbility(livingEntity, livingEntity.level(), this); }
    public void onAbilityCastStart() { getStoredAbility().onCastStart(livingEntity, livingEntity.level(), this); }
    public void onAbilityCastStop() { getStoredAbility().onCastStop(livingEntity, livingEntity.level(), this); }

    public void scroll(int prev, int next) {
        getAbilityInstance().setPrevSelection(prev);
        getAbilityInstance().setSelection(next);
        storage.select(prev, next);
        if (storage.selected() != null)
            getAbilityInstance().setAbility(storage.selected());
    }

    public AbilityStorage getAbilityStorage() { return storage; }

    public static AbilityData instance(LivingEntity entity) {
        var cap = entity.getCapability(AbilityDataCap.CAP);
        var opt = cap.resolve();
        if (cap.isPresent()) {
            if (opt.isEmpty()) return new AbilityData(entity);
            var data = opt.get();
            if (entity instanceof ServerPlayer player) {
                data.setPlayer(player);
                return data;
            }
            return data;
        }
        return new AbilityData(entity);
    }

    public void sync() {
        if (serverPlayer != null) {
            getCooldowns().sync(serverPlayer);
            ENet.sendTo(serverPlayer, new AbilityListSyncS2CPacket(getAbilityStorage()));
            ENet.sendTo(serverPlayer, new SyncAbilitySelectionS2CPacket(this));
        }
    }

    public void copy(AbilityData abilityData) {
        cooldownInstance = abilityData.cooldownInstance;
        castKeyState = abilityData.castKeyState;
        shouldTickCast = abilityData.shouldTickCast;
        castTick = abilityData.castTick;
        abilityInstance = abilityData.abilityInstance;
        storage = abilityData.storage;
    }

    public void save(CompoundTag tag) {
        if (cooldownInstance.hasCooldownsActive())
            tag.put("AbilityCooldowns", cooldownInstance.save());
        tag.putBoolean("CastKey", isCastKeyDown());
        tag.putBoolean("CastTicking", isCastTicking());
        getAbilityStorage().save(tag);
        getAbilityInstance().save(tag);
    }

    public void load(CompoundTag tag) {
        ListTag listTag = (ListTag) tag.get("AbilityCooldowns");
        if (listTag != null && !listTag.isEmpty()) cooldownInstance.load(listTag);
        setCastKeyDown(tag.getBoolean("CastKey"));
        setCastTicking(tag.getBoolean("CastTicking"));
        getAbilityStorage().load(tag);
        getAbilityInstance().load(tag);
    }

    private void sendCastFailedMsgToPlayer() {
        var ability = getStoredAbility();
        if (serverPlayer != null && ability != null)
            serverPlayer.displayClientMessage(ability.failedCastMessage(serverPlayer, ability), true);
    }

    private void syncCastState() {
        if (serverPlayer != null)
            ENet.sendTo(serverPlayer, new SyncCastStateS2CPacket(this));
    }

    public void onCastStart() {
        var castKey = false;
        var ability = getStoredAbility();
        var castTicking = isCastTicking();

        if (ability == null || !ability.isReadyToCast(livingEntity)) {
            sendCastFailedMsgToPlayer();
            setCastTicking(false);
            return;
        }
//        var instant = ability.isInstant();

//        castKey = !ability.isToggled() || !isCastKeyDown();

//        setCastTicking(castKey);
        getAbilityInstance().setCastTime(ability);

        syncCastState();

//        setCastState(instant ? CastState.CAST_START : CastState.PRE_CASTING);

//        if (!instant) attributeUtil(livingEntity, Attributes.MOVEMENT_SPEED).addTransient(CASTING_MOVEMENT_SPEED);

//        if (getAbilityInstance().hasCastStarted())
//            ability.onCastStart(livingEntity, livingEntity.level(), this);
        // Instant

        setCastTicking(true);
        var readyToCast = ability.isInstant() && !ability.castOnRelease();

        setCastState(CastState.PRE_CASTING);
        if (preCastTickReached()) setCastState(CastState.CAST_START);

        if (getCastState().started() && !getCastState().casting()) {
            setCastState(CastState.CASTING);
            onAbilityCastStart();
        }

        if (readyToCast && getCastState().casting()) {
            if (preCastTickReached() && castTickReached()) setCastState(CastState.CAST_STOP);
            getAbilityInstance().setApplyCooldown(castAbility());
        }

        if (getCastState().stopped()) {
            setCastState(CastState.NOT);
            onAbilityCastStart();
        }

        getAbilityInstance().applyCooldown(this);
    }

    public void onCastStop() {
        var ability = getStoredAbility();
        var nullAbility = ability == null;
        var castTicking = isCastTicking();
//        var addCooldown;

        if (nullAbility || (ability.isToggled() || !ability.isReadyToCast(livingEntity)) || !castTicking) {
            setCastTicking(false);
            return;
        }

        setCastTicking(false);
        var readyToCast = ability.castOnRelease();

        if (getCastState().started() && !getCastState().casting()) {
            setCastState(CastState.CASTING);
            onAbilityCastStart();
        }

        if (readyToCast && getCastState().casting()) {
            if (preCastTickReached() && castTickReached()) setCastState(CastState.CAST_STOP);
            getAbilityInstance().setApplyCooldown(castAbility());
        }

        if (getCastState().stopped()) {
            setCastState(CastState.NOT);
            onAbilityCastStart();
        }

        getAbilityInstance().clearCastTime();
        getAbilityInstance().applyCooldown(this);

//        setCastTicking(false);
        syncCastState();
    }

    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = event.getEntity().level();
        if (!level.isClientSide) {
            var data = instance(entity);
            var cooldowns = data.getCooldowns();
            var abilityInst = data.getAbilityInstance();
            var storage = data.getAbilityStorage();
            cooldowns.tick(entity);
//            if (entity instanceof ServerPlayer player) {
//                if (data.isCastKeyDown()) AbilityManager.onServerCast(player);
//                if (data.isCastTicking()) {
//                    data.setCastTick(data.getCastTick() + 1);
//                    if (EGameRules.isDebugModeOn(level)) player.sendSystemMessage(Component.literal(String.valueOf(data.getCastTick())));
//                    var ability = ERegistries.getAbility(data.getCurrentAbilityId());
//                    if (ability != null) {
//                        if (ability.isType(AbilityType.TOGGLED))
//                            ability.tick(entity, level);
//                        ENet.sendTo(player, new SyncCastTickS2CPacket(data.getCastTick()));
//                        if (ability.getCastDuration() - 1 == data.getCastTick()) {
//                            player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1);
//                        }
//                    }
//                }
//                if (data.getCastTick() >= data.getStoredAbility().getCastDuration() && !data.isCastKeyDown()) {
//                    data.setCastTick(0);
//                    data.setCastTicking(false);
//                }
//            }
            var ability = data.getStoredAbility();
            var keyDown = data.isCastKeyDown();
            var isCastTicking = data.isCastTicking();
            var addCooldown = false;
            var preTick = data.getPreCastTick();
            var preDuration = data.getPreCastDuration();
            var tick = data.getCastTick();
            var duration = data.getCastDuration();

            if (entity.isSpectator())
                if (data.isCastTicking()) data.setCastTicking(false);

            if (ability == null || cooldowns.isOnCooldown(ability) || !isCastTicking) {
                attributeUtil(entity, Attributes.MOVEMENT_SPEED).removeModifier(castMoveSpeedUUID);
            } else {
//                var preCast = ability.getPreCast();
//                var castEnd = ability.getPreCast() + ability.getCastDuration();
//                var shouldCast = !ability.castOnRelease() || !keyDown;
//                data.setCastTick(tick + 1);
//
//                if (tick == preCast && !data.isCastState(CastState.CAST_START) && shouldCast) {
//                    data.setCastState(CastState.CAST_START);
//                    ability.onCastStart(entity, level, data);
//                }
//
//                if (tick >= preCast && tick < castEnd && data.isCastState(CastState.CAST_START))
//                    data.setCastState(CastState.CASTING);
//
//                if (ability.isInstant()) {
//                    if (keyDown && tick == 0)
//                        addCooldown = true;
//                } else {
//                    if (tick >= preCast) data.setCastDuration(duration + 1);
//                    if (tick >= ability.getPreCast()) ability.onCastTick(entity, level);
//                    if (tick == 0) attributeUtil(entity, Attributes.MOVEMENT_SPEED).addTransient(CASTING_MOVEMENT_SPEED);
//                }
//
//                if (tick == castEnd)
//                    data.setCastState(CastState.CAST_STOP);
//
//                if (abilityInst.hasCastStopped() && shouldCast) {
//                    ability.onCastStop(entity, level, data);
//                    data.setCastState(CastState.NOT);
//                }
//
//                if (addCooldown) data.addCooldown(ability);

                if (preDuration > 0 && !data.preCastTickReached()) data.setPreCastTick(preTick + 1);
                if (duration > 0 && !data.castTickReached() && data.preCastTickReached()) data.setCastTick(tick + 1);

                var readyToCast = !ability.castOnRelease() && data.getCastState().casting();

                if (data.preCastTickReached() && data.isCastState(CastState.PRE_CASTING)) data.setCastState(CastState.CAST_START);

                if (data.getCastState().started() && !data.getCastState().casting()) {
                    data.setCastState(CastState.CASTING);
                    data.onAbilityCastStart();
                }

                if (readyToCast) {
                    if (data.preCastTickReached() && data.castTickReached()) data.setCastState(CastState.CAST_STOP);
                    addCooldown = data.castAbility() && duration <= 0 || data.castTickReached();
//                    if (data.castAbility()) data.addCooldown(ability);
                    data.getAbilityInstance().setApplyCooldown(addCooldown);
                }

                if (data.getCastState().stopped()) {
                    data.setCastState(CastState.NOT);
                    data.setCastTicking(false);
                    data.onAbilityCastStop();
                    data.getAbilityInstance().clearCastTime();
                    data.syncCastState();
                }

                if (addCooldown) data.addCooldown(ability);
            }

            data.getAbilityStorage().tickPassives(entity, level);
        }
    }
}