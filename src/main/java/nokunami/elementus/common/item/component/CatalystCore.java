package nokunami.elementus.common.item.component;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import nokunami.elementus.common.component.ModDataComponents;
import org.apache.commons.lang3.math.Fraction;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CatalystCore {
    public static final CatalystCore EMPTY = new CatalystCore(List.of());
    public static final Codec<CatalystCore> CODEC = ItemStack.CODEC.listOf().xmap(CatalystCore::new, p_331551_ -> p_331551_.items);
    public static final StreamCodec<RegistryFriendlyByteBuf, CatalystCore> STREAM_CODEC = ItemStack.STREAM_CODEC
            .apply(ByteBufCodecs.list())
            .map(CatalystCore::new, p_331649_ -> p_331649_.items);
    private static final Fraction BUNDLE_IN_BUNDLE_WEIGHT = Fraction.getFraction(1, 16);
    private static final int NO_STACK_INDEX = -1;
    final List<ItemStack> items;
    final Fraction weight;

    CatalystCore(List<ItemStack> items, Fraction weight) {
        this.items = items;
        this.weight = weight;
    }

    public CatalystCore(List<ItemStack> items) {
        this(items, computeContentWeight(items));
    }

    private static Fraction computeContentWeight(List<ItemStack> content) {
        Fraction fraction = Fraction.ZERO;

        for (ItemStack itemstack : content) {
            fraction = fraction.add(getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
        }

        return fraction;
    }

    static Fraction getWeight(ItemStack stack) {
        CatalystCore CatalystCore = stack.get(ModDataComponents.CATALYST_CORE);
        if (CatalystCore != null) {
            return BUNDLE_IN_BUNDLE_WEIGHT.add(CatalystCore.weight());
        } else {
            List<BeehiveBlockEntity.Occupant> list = stack.getOrDefault(DataComponents.BEES, List.of());
            return !list.isEmpty() ? Fraction.ONE : Fraction.getFraction(1, stack.getMaxStackSize());
        }
    }

    public ItemStack getItemUnsafe(int index) {
        return this.items.get(index);
    }

    public Stream<ItemStack> itemCopyStream() {
        return this.items.stream().map(ItemStack::copy);
    }

    public Iterable<ItemStack> items() {
        return this.items;
    }

    public Iterable<ItemStack> itemsCopy() {
        return Lists.transform(this.items, ItemStack::copy);
    }

    public int size() {
        return this.items.size();
    }

    public Fraction weight() {
        return this.weight;
    }

    public boolean isEmpty() {
        return this.items.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else {
            return !(other instanceof CatalystCore CatalystCore)
                    ? false
                    : this.weight.equals(CatalystCore.weight) && ItemStack.listMatches(this.items, CatalystCore.items);
        }
    }

    @Override
    public int hashCode() {
        return ItemStack.hashStackList(this.items);
    }

    @Override
    public String toString() {
        return "CatalystCore" + this.items;
    }

    public static class Mutable {
        private final List<ItemStack> items;
        private Fraction weight;

        public Mutable(CatalystCore contents) {
            this.items = new ArrayList<>(contents.items);
            this.weight = contents.weight;
        }

        public CatalystCore.Mutable clearItems() {
            this.items.clear();
            this.weight = Fraction.ZERO;
            return this;
        }

        private int findStackIndex(ItemStack stack) {
            if (!stack.isStackable()) {
                return NO_STACK_INDEX;
            } else {
                for (int i = 0; i < this.items.size(); i++) {
                    if (ItemStack.isSameItemSameComponents(this.items.get(i), stack)) {
                        return i;
                    }
                }

                return NO_STACK_INDEX;
            }
        }

        private int getMaxAmountToAdd(ItemStack stack) {
            Fraction fraction = Fraction.ONE.subtract(this.weight);
            return Math.max(fraction.divideBy(CatalystCore.getWeight(stack)).intValue(), 0);
        }

        public int tryInsert(ItemStack stack) {
            if (!stack.isEmpty() && stack.getItem().canFitInsideContainerItems()) {
                int i = Math.min(stack.getCount(), this.getMaxAmountToAdd(stack));
                if (i == 0) {
                    return 0;
                } else {
                    this.weight = this.weight.add(CatalystCore.getWeight(stack).multiplyBy(Fraction.getFraction(i, 1)));
                    int j = this.findStackIndex(stack);
                    if (j != NO_STACK_INDEX) {
                        ItemStack itemstack = this.items.remove(j);
                        ItemStack itemstack1 = itemstack.copyWithCount(itemstack.getCount() + i);
                        stack.shrink(i);
                        this.items.add(0, itemstack1);
                    } else {
                        this.items.add(0, stack.split(i));
                    }

                    return i;
                }
            } else {
                return 0;
            }
        }

        public int tryTransfer(Slot slot, Player player) {
            ItemStack itemstack = slot.getItem();
            int i = this.getMaxAmountToAdd(itemstack);
            return this.tryInsert(slot.safeTake(itemstack.getCount(), i, player));
        }

        @Nullable
        public ItemStack removeOne() {
            if (this.items.isEmpty()) {
                return null;
            } else {
                ItemStack itemstack = this.items.remove(0).copy();
                this.weight = this.weight.subtract(CatalystCore.getWeight(itemstack).multiplyBy(Fraction.getFraction(itemstack.getCount(), 1)));
                return itemstack;
            }
        }

        public Fraction weight() {
            return this.weight;
        }

        public CatalystCore toImmutable() {
            return new CatalystCore(List.copyOf(this.items), this.weight);
        }
    }
}
