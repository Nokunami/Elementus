package net.nokunami.elementus.common.inventory;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.nokunami.elementus.common.Etags;
import net.nokunami.elementus.common.entity.living.SteelGolem;
import net.nokunami.elementus.common.entity.living.TamableChestedGolem;
import net.nokunami.elementus.common.entity.living.TamableGolem;
import org.jetbrains.annotations.NotNull;

public class SteelGolemInventoryMenu extends AbstractContainerMenu {
    private final Container container;
    private final SteelGolem golem;


    public SteelGolemInventoryMenu(int containerId, Inventory inventory, Container container, final SteelGolem steelGolem) {
        super(null, containerId);
        this.container = container;
        this.golem = steelGolem;
        int i = 3;
        container.startOpen(inventory.player);
        int j = -18;
        this.addSlot(new Slot(container, 0, 8, 18) {
            /// Check if stack is allowed in slot
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(Items.SADDLE) && !this.hasItem() && golem.isSaddleable();
            }

            /// Render slot when needed
            public boolean isActive() {
                return golem.isSaddleable();
            }
        });

        this.addSlot(new Slot(container, 1, 8, 36) {
            /// Check if stack is allowed in slot
            public boolean mayPlace(@NotNull ItemStack stack) {
                return golem.isArmor(stack);
            }

            /// Render slot when needed
            public boolean isActive() {
                return golem.canWearArmor();
            }

            /// Returns the max stack size for slot
            public int getMaxStackSize() {
                return 1;
            }
        });

        this.addSlot(new Slot(container, 2, 8, 54) {
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(Etags.Items.STEEL_GOLEM_LEAVES_DECORATION);
            }

            public int getMaxStackSize() {
                return 1;
            }
        });

        this.addSlot(new Slot(container, 3, 8, 72) {
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.is(Etags.Items.STEEL_GOLEM_CARPET_DECORATION);
            }

            public int getMaxStackSize() {
                return 1;
            }
        });

        //Damn its hard-coded to only have 24 slots
//        this.addSlot(new Slot(container, 4, 62, 72) {
//            public boolean mayPlace(@NotNull ItemStack stack) {
//                return stack.is(Tags.Items.DYES);
//            }
//
//            public boolean isActive() {
//                return golem.hasChest();
//            }
//
//            public int getMaxStackSize() {
//                return 1;
//            }
//        });

        if (this.hasChest(golem)) {
            for(int k = 0; k < 4; ++k) {
                for(int l = 0; l < steelGolem.getInventoryColumns(); ++l) {
                    this.addSlot(new Slot(container, 4 + l + k * steelGolem.getInventoryColumns(), 80 + l * 18, 18 + k * 18));
                }
            }
        }

        for(int i1 = 0; i1 < i; ++i1) {
            for(int k1 = 0; k1 < 9; ++k1) {
                this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 120 + i1 * 18 - 18));
            }
        }

        for(int j1 = 0; j1 < 9; ++j1) {
            this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 160));
        }

    }

    public boolean stillValid(@NotNull Player player) {
        return !this.golem.hasInventoryChanged(this.container) && this.container.stillValid(player) && this.golem.isAlive() && this.golem.distanceTo(player) < 8.0F;
    }

    private boolean hasChest(TamableGolem golem) {
        return golem instanceof TamableChestedGolem && ((TamableChestedGolem)golem).hasChest();
    }

    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }
            int i = this.container.getContainerSize();
            if (index < i) {
                if (!this.moveItemStackTo(itemstack1, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemstack1) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 2 || !this.moveItemStackTo(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k) {
                    if (!this.moveItemStackTo(itemstack1, i, j, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= i && index < j) {
                    if (!this.moveItemStackTo(itemstack1, j, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, j, j, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    public void removed(@NotNull Player player) {
        super.removed(player);
        this.container.stopOpen(player);
        if (hasChest(golem)) this.golem.level().playSound(null, this.golem.blockPosition(), SoundEvents.CHEST_CLOSE, SoundSource.NEUTRAL, 0.5F, 1);
        golem.isChestOpened(false);
    }
}
