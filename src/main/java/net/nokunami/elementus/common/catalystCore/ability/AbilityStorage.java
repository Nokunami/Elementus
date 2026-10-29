package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.registry.ERegistries;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AbilityStorage {
    private final List<AbilityStorageInstance> abilities = new ArrayList<>();

    public List<AbilityStorageInstance> getAbilitiesInStorage() { return abilities; }

    public List<AbstractAbility> getAbilities() {
        List<AbstractAbility> list = new ArrayList<>();
        abilities.forEach(inst -> list.add(inst.getAbility()));
        return list;
    }

    public boolean isEmpty() { return getAbilitiesInStorage().isEmpty(); }

    public void addAbilities(List<AbstractAbility> abilities, AbilityData data) {
        var cooldowns = data.getCooldowns();
        for (int i = 0; i < abilities.size(); i++) {
            var ability = abilities.get(i);
            var gui = ability.showOnClient(cooldowns);
            var inst = new AbilityStorageInstance(ability)
                    .setSelected(i == data.getSelection())
                    .shouldAddToGui(gui);
            getAbilitiesInStorage().add(inst);
        }
        cooldowns.putPassivesOnList(this);
    }

    public void addAbilities(List<AbilityStorageInstance> abilities) { getAbilitiesInStorage().addAll(abilities); }

    public void select(int prev, int select) {
        var list = getAbilitiesInStorage();
        list.removeIf(storageInstance -> storageInstance.getAbility() instanceof PassiveAbility);
        list.removeIf(storageInstance -> storageInstance.getAbility() instanceof PassiveAbility);
        int max = Math.max(0, list.size() - 1);
        if (list.isEmpty()) return;
        list.get(Mth.clamp(prev, 0, max)).setSelected(false);
        list.get(Mth.clamp(select, 0, max)).setSelected(true);
    }

    @Nullable
    public AbstractAbility selected() {
        var list = getAbilitiesInStorage().stream().filter(AbilityStorageInstance::isSelected).findFirst();
        return list.map(AbilityStorageInstance::getAbility).orElse(null);
    }

    public List<AbstractAbility> actives() {
        List<AbstractAbility> list = new ArrayList<>();
        getAbilitiesInStorage().forEach(abilityListInstance -> {
            var ability = abilityListInstance.getAbility();
            if (!(ability instanceof PassiveAbility)) list.add(ability);
        });
        return list;
    }
    public int activeCount() { return actives().size() - 1; }

    public List<PassiveAbility> passives() {
        List<PassiveAbility> list = new ArrayList<>();
        getAbilities().forEach(ability -> {
            if (ability instanceof PassiveAbility passive) list.add(passive);
        });
        return list;
    }

    public void onHurt(LivingHurtEvent event) {
        var entity = event.getEntity();
        var data = AbilityData.instance(entity);
        for (PassiveAbility ability : passives()) {
            if (!data.isOnCooldown(ability)) {
                ability.onHurtEvent(event);
                if (ability.applyCooldownOnHurt(entity))
                    data.addCooldown(ability);
            }
        }
    }

    public void onDamage(LivingDamageEvent event) {
        var entity = event.getEntity();
        var data = AbilityData.instance(entity);
        for (PassiveAbility ability : passives()) {
            if (!data.isOnCooldown(ability)) {
                ability.onDamageEvent(event);
                if (ability.applyCooldownOnDamage(entity))
                    data.addCooldown(ability);
            }
        }
    }

    public void onDeath(LivingDeathEvent event) {
        var entity = event.getEntity();
        var data = AbilityData.instance(entity);
        for (PassiveAbility ability : passives()) {
            if (!data.isOnCooldown(ability)) {
                ability.onDeathEvent(event);
                if (ability.applyCooldownOnDeath(entity))
                    data.addCooldown(ability);
            }
        }
    }

    public void onEffect(MobEffectEvent event) {
        var entity = event.getEntity();
        var data = AbilityData.instance(entity);
        for (PassiveAbility ability : passives())
            if (!data.isOnCooldown(ability)) ability.onEffect(event);
    }

    public void tickPassives(LivingEntity entity, Level level) {
        var data = AbilityData.instance(entity);
        for (AbstractAbility ability : passives()) {
            if (!data.isOnCooldown(ability))
                ability.onCastTick(entity, level);
        }
    }

    public void save(CompoundTag tag) {
        var list = new ListTag();
        getAbilitiesInStorage().forEach(ability -> list.add(ability.save()));
        if (!list.isEmpty()) tag.put("Abilities", list);
    }
    public void load(CompoundTag tag) {
        var abilitiesTag = tag.get("Abilities");
        if (abilitiesTag != null) {
            ((ListTag) abilitiesTag).forEach(tagInList -> {
                if (((CompoundTag) tagInList).contains("Id")) {
                    var id = ((CompoundTag) tagInList).getString("Id");
                    var ability = ERegistries.getAbility(id);
                    if (ability != null)
                        getAbilitiesInStorage().add(new AbilityStorageInstance((CompoundTag) tagInList));
                }
            });
        }
    }
}