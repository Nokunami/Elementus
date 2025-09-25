package net.nokunami.elementus.common.item.catalystCore;

import com.google.common.collect.Maps;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.nokunami.elementus.common.registry.CoreAbilityRegistry.CORE_ABILITIES_KEY;
import static net.nokunami.elementus.common.registry.CoreAbilityRegistry.CORE_ABILITIES_RL;

public class CatalystCore {
    public static final List<CatalystCore> CATALYST_CORE_LIST = new ArrayList<>();
    public static final List<Item> CORE_ITEM_LIST = new ArrayList<>();
    public static final Map<String, CatalystCore> CATALYST_CORE_MAP = new HashMap<>();
    public static final Map<CatalystCore, CatalystCoreAbility> CATALYST_CORE_ABILITIES = Maps.newIdentityHashMap();
    public final String id;
    public final CoreAttributes attributes;

    public CatalystCore(String id, Item core, CoreAttributes attributes) {
        this.id = id;
//        this.core = core;
        this.attributes = attributes;
        CATALYST_CORE_LIST.add(this);
    }

    public static void test(ItemStack stack, Player player) {
        if (CORE_ITEM_LIST.contains(stack.getItem())) {
        }
    }

    public static class CoreAttributes {
        Item core;
        CatalystCoreAbility ability;
        int descNum;

        public CoreAttributes(Builder builder) {
            this.ability = builder.ability;
            this.descNum = builder.descNum;
        }

        public CatalystCoreAbility getAbility() {
            return ability;
        }

        public int getDescNum() {
            return descNum;
        }

        public static class Builder {
            Item core;
            CatalystCoreAbility ability;
            int descNum;

            public Builder coreItem(Item item) {
                this.core = item;
                CORE_ITEM_LIST.add(item);
                return this;
            }

            public Builder ability(CatalystCoreAbility ability) {
                this.ability = ability;
                return this;
            }

            public Builder numberOfDescriptions(int descNum) {
                this.descNum = descNum;
                return this;
            }

            public CoreAttributes build() {
                return new CoreAttributes(this);
            }
        }
    }
}
