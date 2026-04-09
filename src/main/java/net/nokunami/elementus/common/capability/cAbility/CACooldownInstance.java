package net.nokunami.elementus.common.capability.cAbility;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.nokunami.elementus.common.catalystCore.ability.activeAbility.AbstractActiveAbility;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.client.CACooldownsSyncS2CPacket;

import java.util.HashMap;
import java.util.Map;

public class CACooldownInstance {
    private final Map<String, CACooldown> cooldownMap;

    public CACooldownInstance() { cooldownMap = new HashMap<>(); }

    public void tick() {
        var cooldowns = cooldownMap.entrySet().stream().filter(c -> decrement(c.getValue(), 1)).toList();
        cooldowns.forEach(cooldown -> cooldownMap.remove(cooldown.getKey()));
    }

    public boolean decrement(CACooldown c, int amount) {
        c.decrement(amount);
        return c.getTicks() <= 0 && c.getCharge() <= 0;
    }

    // ----- [ FUNCTIONS ]

    public void addCooldown(String id, int durationTicks, int chargeAmount) {
        if (cooldownMap.containsKey(id)) {
            var c = cooldownMap.get(id);
            c.setCharge(Mth.clamp(c.getCharge() + 1, 0 , chargeAmount));
        } else cooldownMap.put(id, new CACooldown(durationTicks, durationTicks, chargeAmount));
    }
    public void clearCooldowns() { cooldownMap.clear(); }

    // ----- [ STATUS ]

    public boolean isOnCooldown(AbstractActiveAbility ability) {
//        return cooldownMap.containsKey(ability.getWithNamespace());
//        var cm = cooldownMap.get(ability.getWithNamespace());
//        return cm != null && hasCharge(ability);
        return cooldownMap.containsKey(ability.getWithNamespace()) && hasCharge(ability);
    }
    public boolean hasCharge(AbstractActiveAbility ability) {
//        var cm = cooldownMap.get(ability.getWithNamespace());
//        return cm != null && cm.getCharge() > 0;
        return getCharge(ability) >= getChargeAmount(ability);
    }
    public boolean removeCooldown(String id) { return cooldownMap.remove(id) != null; }
    public boolean hasCooldownsActive() { return !cooldownMap.isEmpty(); }

    public float getCooldownPercent(AbstractActiveAbility ability) {
        return cooldownMap.getOrDefault(ability.getWithNamespace(), new CACooldown()).getCooldownPercent();
    }
    public int getCharge(AbstractActiveAbility ability) {
        return cooldownMap.getOrDefault(ability.getWithNamespace(), new CACooldown()).getCharge();
    }
    public int getChargeAmount(AbstractActiveAbility ability) {
        return cooldownMap.getOrDefault(ability.getWithNamespace(), new CACooldown()).getChargeAmount();
    }

    public void sync(ServerPlayer player) {
        ENetwork.sendTo(player, new CACooldownsSyncS2CPacket(cooldownMap));
    }

    public ListTag save() {
        var listTag = new ListTag();
        cooldownMap.forEach((id, cooldown) -> {
            CompoundTag tag = new CompoundTag();
            if (cooldown.getTicks() > 0) {
                tag.putString("id", id);
                tag.putInt("cooldown", cooldown.getCooldown());
                tag.putInt("cooldownTicks", cooldown.getTicks());
                if (cooldown.getChargeAmount() > 0) {
                    tag.putInt("charge", cooldown.getCharge());
//                    tag.putInt("chargeAmount", cooldown.getChargeAmount());
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
                String spellId = t.getString("id");
                int cooldown = t.getInt("cooldown");
                int ticks = t.getInt("cooldownTicks");
                int charge = t.getInt("charge");
//                int chargeAmount = t.getInt("chargeAmount");
                cooldownMap.put(spellId, new CACooldown(cooldown, ticks, charge));
            });
        }
    }
}
