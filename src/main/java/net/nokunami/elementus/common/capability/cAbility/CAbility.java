package net.nokunami.elementus.common.capability.cAbility;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.nokunami.elementus.common.catalystCore.ability.AbilityCastManager;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.CACooldownSyncS2CPacket;
import net.nokunami.elementus.common.network.client.ability.SyncCastTickS2CPacket;
import net.nokunami.elementus.common.registry.CustomRegistries;
import net.nokunami.elementus.common.registry.EGameRules;

public class CAbility {
    private ServerPlayer serverPlayer;
    private LivingEntity livingEntity;
    private CACooldownInstance cooldownInstance = new CACooldownInstance();
    private boolean castKeyState = false;
    private boolean shouldTickCast = false;
    private int castTick = 0;
    private int abilitySelection = 0;
    private int abilityAmount = 0;
    private int abilityCastDuration = 0;
    private String currentAbilityId = "none";
    private String previousAbilityId = "none";

    public CAbility() { }

    public CAbility(LivingEntity entity) {
        livingEntity = entity;
        if (entity instanceof ServerPlayer player) {
            serverPlayer = player;
        }
    }

    public void setPlayer(ServerPlayer player) { serverPlayer = player; }

    public CACooldownInstance getCooldowns() { return cooldownInstance; }
    public boolean isOnCooldown(AbstractActiveAbility ability) { return getCooldowns().isOnCooldown(ability); }
    public void addCooldown(LivingEntity entity, AbstractActiveAbility ability) {
        boolean isCreative = entity instanceof Player player && player.isCreative();
        int cooldown = isCreative ? 10 : ability.getCooldown();
        int charge = Math.min(ability.getCharges(), 1);
        getCooldowns().addCooldown(ability.getWithNamespace(), cooldown, ability.getCharges());
        if (entity instanceof ServerPlayer player) ENetwork.sendTo(player, new CACooldownSyncS2CPacket(ability.getWithNamespace(), cooldown, ability.getCharges()));
    }

    public boolean isCastKeyDown() { return castKeyState; }
    public void setCastKeyDown(boolean b) { castKeyState = b; }

    public boolean isCastTicking() { return shouldTickCast; }
    public void setCastTicking(boolean b) { shouldTickCast = b; }

    public int getCastTick() { return castTick; }
    public void setCastTick(int i) { castTick = i; }

    public int getSelection() { return abilitySelection; }
    public void setSelection(int i) { abilitySelection = i; }

    public int getAbilityAmount() { return abilityAmount; }
    public void setAbilityAmount(int i) { abilityAmount = i; }

    public int getAbilityCastDuration() { return abilityCastDuration; }
    public void setAbilityCastDuration(int i) { abilityCastDuration = i; }

    public String getCurrentAbilityId() { return currentAbilityId; }
    public void setCurrentAbilityId(String s) { currentAbilityId = s; }
    public void setCurrentAbility(AbstractActiveAbility ability) { setCurrentAbilityId(ability.getWithNamespace()); }

    public String getPreviousAbilityId() { return previousAbilityId; }
    public void setPreviousAbilityId(String s) { previousAbilityId = s; }
    public void setPreviousAbility(AbstractActiveAbility ability) { setCurrentAbilityId(ability.getWithNamespace()); }

    public AbstractActiveAbility getStoredAbility() {
        return CustomRegistries.getAbility(getCurrentAbilityId());
    }

    public static CAbility instance(LivingEntity entity) {
        if (entity instanceof ServerPlayer player) {
            var cap = player.getCapability(CAbilityCap.CAP);
            var opt = cap.resolve();
            if (cap.isPresent()) {
                if (opt.isEmpty()) return new CAbility(player);
                var ca = opt.get();
                ca.setPlayer(player);
                return ca;
            }
        }
        return new CAbility(entity);
    }

    public void load(CAbility cAbility) {
        cooldownInstance = cAbility.cooldownInstance;
        castKeyState = cAbility.castKeyState;
        shouldTickCast = cAbility.shouldTickCast;
        castTick = cAbility.castTick;
        abilitySelection = cAbility.abilitySelection;
        abilityAmount = cAbility.abilityAmount;
        currentAbilityId = cAbility.currentAbilityId;
        previousAbilityId = cAbility.previousAbilityId;
    }

    public void save(CompoundTag tag) {
        if (cooldownInstance.hasCooldownsActive()) tag.put("ability_cooldowns", cooldownInstance.save());
        tag.putBoolean("cast_key", isCastKeyDown());
        tag.putBoolean("cast_ticking", isCastTicking());
        tag.putInt("cast_tick", getCastTick());
        tag.putInt("ability_selection", getSelection());
        tag.putInt("ability_amount", getAbilityAmount());
        tag.putInt("ability_cast_duration", getAbilityCastDuration());
        tag.putString("current_ability", getCurrentAbilityId());
        tag.putString("previous_ability", getPreviousAbilityId());
    }

    public void load(CompoundTag tag) {
        ListTag listTag = (ListTag) tag.get("ability_cooldowns");
        if (listTag != null && !listTag.isEmpty()) cooldownInstance.load(listTag);
        setCastKeyDown(tag.getBoolean("cast_key"));
        setCastTicking(tag.getBoolean("cast_ticking"));
        setCastTick(tag.getInt("cast_tick"));
        setSelection(tag.getInt("ability_selection"));
        setAbilityAmount(tag.getInt("ability_amount"));
        setAbilityCastDuration(tag.getInt("ability_cast_duration"));
        setCurrentAbilityId(tag.getString("current_ability"));
        setPreviousAbilityId(tag.getString("previous_ability"));
    }

    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        Level level = event.getEntity().level();
        if (!level.isClientSide) {
            CAbility ca = instance(entity);
            ca.getCooldowns().tick();
            if (entity instanceof ServerPlayer player) {
                if (ca.isCastKeyDown()) AbilityCastManager.onServerCast(player);
                if (ca.isCastTicking()) {
                    ca.setCastTick(ca.getCastTick() + 1);
                    if (EGameRules.isDebugModeOn(level)) player.sendSystemMessage(Component.literal(String.valueOf(ca.getCastTick())));
                    var ability = CustomRegistries.getAbility(ca.getCurrentAbilityId());
                    if (ability != null) {
                        ENetwork.sendTo(player, new SyncCastTickS2CPacket(ca.getCastTick()));
                        if (ability.getCastDuration() - 1 == ca.getCastTick()) {
                            player.playNotifySound(SoundEvents.EXPERIENCE_ORB_PICKUP, SoundSource.PLAYERS, 1, 1);
                        }
                    }
                }
                if (ca.getCastTick() >= ca.getStoredAbility().getCastDuration() && !ca.isCastKeyDown()) {
                    ca.setCastTick(0);
                    ca.setCastTicking(false);
                }
            }
        }
    }
}