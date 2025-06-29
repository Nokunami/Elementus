package nokunami.elementus.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class CatalystTotemExhuastEffect extends ModMobEffect {

    public CatalystTotemExhuastEffect() {
        super(MobEffectCategory.HARMFUL, 16750848);
    }

    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}
