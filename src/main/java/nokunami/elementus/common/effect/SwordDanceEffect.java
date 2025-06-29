package nokunami.elementus.common.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class SwordDanceEffect extends ModMobEffect {

    public SwordDanceEffect() {
        super(MobEffectCategory.NEUTRAL, 12054986);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return List.of();
    }
}
