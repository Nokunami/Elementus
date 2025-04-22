package net.nokunami.elementus.common.entity.living;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.nokunami.elementus.common.registry.ModSoundEvents;
import org.jetbrains.annotations.NotNull;

public abstract class TamableChestedGolem extends TamableGolem {
//    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(TamableChestedGolem.class, EntityDataSerializers.BOOLEAN);
//    public static final int INV_CHEST_COUNT = 20;

    protected TamableChestedGolem(EntityType<? extends TamableChestedGolem> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected int getInventorySize() {
        return this.hasChest() ? 17 : super.getInventorySize();
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasChest()) {
            if (!this.level().isClientSide) {
                this.spawnAtLocation(Blocks.CHEST);
            }

            this.setChest(false);
        }

    }

    public void addAdditionalSaveData(@NotNull CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Chested", this.hasChest());
        if (this.hasChest()) {
            ListTag listtag = new ListTag();
            for(int i = 2; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty()) {
                    CompoundTag compoundtag = new CompoundTag();
                    compoundtag.putByte("Slot", (byte)i);
                    itemstack.save(compoundtag);
                    listtag.add(compoundtag);
                }
            }
            pCompound.put("Items", listtag);
        }

    }

    public void readAdditionalSaveData(@NotNull CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setChest(tag.getBoolean("Chested"));
        if (this.hasChest()) {
            ListTag listtag = tag.getList("Items", 10);
            for(int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundtag = listtag.getCompound(i);
                int j = compoundtag.getByte("Slot") & 255;
                if (j >= 2 && j < this.inventory.getContainerSize()) {
                    this.inventory.setItem(j, ItemStack.of(compoundtag));
                }
            }
        }

        this.updateContainerEquipment();
    }

    public @NotNull SlotAccess getSlot(int pSlot) {
        return pSlot == CHEST_SLOT_OFFSET ? new SlotAccess() {
            public @NotNull ItemStack get() {
                return TamableChestedGolem.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
            }

            public boolean set(@NotNull ItemStack stack) {
                if (stack.isEmpty()) {
                    if (TamableChestedGolem.this.hasChest()) {
                        TamableChestedGolem.this.setChest(false);
                        TamableChestedGolem.this.createInventory();
                    }

                    return true;
                } else if (stack.is(Items.CHEST)) {
                    if (!TamableChestedGolem.this.hasChest()) {
                        TamableChestedGolem.this.setChest(true);
                        TamableChestedGolem.this.createInventory();
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } : super.getSlot(pSlot);
    }

    public @NotNull InteractionResult mobInteract(@NotNull Player pPlayer, @NotNull InteractionHand pHand) {
        boolean flag = !this.isBaby() && this.isTame() && pPlayer.isSecondaryUseActive();
        if (!this.isVehicle() && !flag) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if (!itemstack.isEmpty()) {
                if (!this.isTame()) {
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }

                if (!this.hasChest() && itemstack.is(Items.CHEST)) {
                    this.equipChest(pPlayer, itemstack);
                    return InteractionResult.sidedSuccess(this.level().isClientSide);
                }
            }

            return super.mobInteract(pPlayer, pHand);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    private void equipChest(Player pPlayer, ItemStack pChestStack) {
        this.setChest(true);
        this.playChestEquipsSound();
        if (!pPlayer.getAbilities().instabuild) {
            pChestStack.shrink(1);
        }

        this.createInventory();
    }

    protected void playChestEquipsSound() {
        this.playSound(ModSoundEvents.STEEL_GOLEM_CHESTED.get(), 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    public int getInventoryColumns() {
        return 5;
    }
}
