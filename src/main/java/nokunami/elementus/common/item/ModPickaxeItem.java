package nokunami.elementus.common.item;

import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import nokunami.elementus.common.config.ModConfig;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static nokunami.elementus.common.item.EItemUtil.cmdTier;

public class ModPickaxeItem extends PickaxeItem {

    public ModPickaxeItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

//    @Override
//    public @NotNull Rarity getRarity(@NotNull ItemStack stack) {
//        if (cmdTier(this.getTier())) {
//            return Rarity.EPIC;
//        }
//        return super.getRarity(stack);
//    }

    @Override
    public boolean canBeHurtBy(@NotNull ItemStack stack, @NotNull DamageSource source) {
        if (cmdTier(this.getTier())) return false;
        return super.canBeHurtBy(stack, source);
    }

    @Override
    public boolean isEnchantable(@NotNull ItemStack stack) {
        if (cmdTier(this.getTier())) {
            return this.getMaxStackSize(stack) == 1;
        }
        return super.isEnchantable(stack);
    }

    public void appendHoverText(@NotNull ItemStack stack, @javax.annotation.Nullable Level worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        EItemUtil.pickaxeTooltip(stack, tooltip, getTier());
    }

    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        if (ModConfig.COMMON.diarkriteEfficiency.get()) {
            float originalSpeed = super.getDestroySpeed(stack, state);
            return EItemUtil.pickaxeMiningSpeed(originalSpeed, stack, state);
        }
        return super.getDestroySpeed(stack, state);
    }
}
