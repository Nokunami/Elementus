package net.nokunami.elementus.common.capability.catalystQuickSwap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.nokunami.elementus.common.capability.cAbility.CAbility;
import net.nokunami.elementus.common.capability.cAbility.CAbilityCap;

import java.util.List;

import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEMSTACK_MAP;
import static net.nokunami.elementus.common.catalystCore.core.CatalystCore.CORE_ITEM_MAP;
import static net.nokunami.elementus.common.item.unique.CatalystItemUtil.getEquippedCore;

public class CatalystQuickSwap {
    Player player;
    ItemStack coreStack;
    ItemStack invStack;

    public CatalystQuickSwap(Player player) { this.player = player; }
    public CatalystQuickSwap setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public List<ItemStack> getCoresInInv() {
        return player.getInventory().items.stream().filter(CORE_ITEMSTACK_MAP::containsKey).toList();
    }

    public static CatalystQuickSwap instance(Player player) {
        var cap = player.getCapability(CatalystQuickSwapCap.CAP);
        var opt = cap.resolve();
        if (cap.isPresent()) {
            if (opt.isEmpty()) return new CatalystQuickSwap(player);
            var ca = opt.get();
            ca.setPlayer(player);
            return ca;
        }
        return new CatalystQuickSwap(player);
    }

    public void save(CompoundTag tag) {
    }

    public void load(CompoundTag tag) {
    }
}
