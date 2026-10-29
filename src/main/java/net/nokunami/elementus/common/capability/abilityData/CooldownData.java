package net.nokunami.elementus.common.capability.abilityData;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.nokunami.elementus.common.catalystCore.ability.*;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.AbilityCooldownsSyncS2CPacket;
import net.nokunami.elementus.common.registry.ERegistries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CooldownData {
    private final Map<String, AbilityCooldown> cooldownMap;

    public CooldownData() { cooldownMap = new HashMap<>(); }

    public void tick(LivingEntity entity) {
        var cooldowns = cooldownMap.entrySet().stream().filter(c -> decrement(c.getValue())).toList();
        cooldowns.forEach(cooldown -> {
            cooldownMap.remove(cooldown.getKey());
            if (entity instanceof ServerPlayer player) {
                AbilityManager.sync(player, player.level());
            }
        });
    }

    public boolean decrement(AbilityCooldown c) {
        c.decrement();
        return c.getTicks() <= 0 && c.getCharge() <= 0;
    }

    // ----- [ FUNCTIONS ]

    public void addCooldown(String id, int durationTicks, int chargeAmount) {
        if (cooldownMap.containsKey(id)) {
            var c = cooldownMap.get(id);
            c.setCharge(Mth.clamp(c.getCharge() + 1, 0 , chargeAmount));
        } else addFreshCooldown(id, durationTicks, durationTicks, chargeAmount);
    }
    public void addFreshCooldown(String id, int totalTicks, int ticks, int chargeAmount) {
        cooldownMap.put(id, new AbilityCooldown(totalTicks, ticks, chargeAmount));
    }
    public void clearCooldowns() { cooldownMap.clear(); }

    // ----- [ STATUS ]

    public boolean isOnCooldown(AbstractAbility ability) {
        return cooldownMap.containsKey(ability.getAsString()) && hasCharge(ability);
    }
    public boolean hasCharge(AbstractAbility ability) { return getCharge(ability) >= getChargeAmount(ability); }
    public boolean removeCooldown(String id) { return cooldownMap.remove(id) != null; }
    public boolean hasCooldownsActive() { return !cooldownMap.isEmpty(); }

    public float getCooldownPercent(AbstractAbility ability) {
        return cooldownMap.getOrDefault(ability.getAsString(), new AbilityCooldown()).getCooldownPercent();
    }
    public int getCharge(AbstractAbility ability) {
        return cooldownMap.getOrDefault(ability.getAsString(), new AbilityCooldown()).getCharge();
    }
    public int getChargeAmount(AbstractAbility ability) {
        return cooldownMap.getOrDefault(ability.getAsString(), new AbilityCooldown()).getChargeAmount();
    }

    public void sync(ServerPlayer player) { ENet.sendTo(player, new AbilityCooldownsSyncS2CPacket(cooldownMap)); }

//    public void putPassivesOnList(List<AbstractAbility> list) {
//        List<AbstractAbility> temp = new ArrayList<>();
//        cooldownMap.forEach((s, abilityCooldown) -> {
//            var ability = ERegistries.getAbility(s);
//            if (ability instanceof PassiveAbility) {
//                temp.add(ability);
//            }
//        });
//        list.addAll(temp);
//    }
    public void putPassivesOnList(AbilityStorage storage) {
        var storageInstances = storage.getAbilitiesInStorage();
        var abilities = storage.getAbilities();
        List<AbilityStorageInstance> temp = new ArrayList<>();
        cooldownMap.forEach((s, abilityCooldown) -> {
            var ability = ERegistries.getAbility(s);
            if (ability instanceof PassiveAbility && !abilities.contains(ability)) {
                temp.add(new AbilityStorageInstance(ability).shouldAddToGui(true));
            }
        });
        storageInstances.addAll(temp);
    }
    public List<AbstractAbility> allAbilitiesOnCooldown() {
        List<AbstractAbility> temp = new ArrayList<>();
        cooldownMap.forEach((s, abilityCooldown) -> {
            var ability = ERegistries.getAbility(s);
            if (ability != null) temp.add(ability);
        });
        return temp;
    }
    public List<AbstractAbility> passiveAbilitiesOnCooldown() {
        List<AbstractAbility> temp = new ArrayList<>(allAbilitiesOnCooldown());
        temp.removeIf(ability -> !(ability instanceof PassiveAbility));
        return temp;
    }

    public ListTag save() {
        var listTag = new ListTag();
        cooldownMap.forEach((id, cooldown) -> {
            CompoundTag tag = new CompoundTag();
            if (cooldown.getTicks() > 0) {
                tag.putString("id", id);
                tag.putInt("cooldown", cooldown.getTotalTicks());
                tag.putInt("cooldownTicks", cooldown.getTicks());
                if (cooldown.getChargeAmount() > 0) {
                    tag.putInt("charge", cooldown.getCharge());
                }
            }
            listTag.add(tag);
        });
        return listTag;
    }

    public void load(ListTag listTag) {
        if (listTag != null) {
            listTag.forEach(tag -> {
                CompoundTag t = (CompoundTag) tag;
                String id = t.getString("id");
                int cooldown = t.getInt("cooldown");
                int ticks = t.getInt("cooldownTicks");
                int charge = t.getInt("charge");
                cooldownMap.put(id, new AbilityCooldown(cooldown, ticks, charge));
            });
        }
    }
}
