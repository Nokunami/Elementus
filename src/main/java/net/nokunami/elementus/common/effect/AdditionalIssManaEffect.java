package net.nokunami.elementus.common.effect;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class AdditionalIssManaEffect extends ModMobEffect {
    public AdditionalIssManaEffect() {
        super(MobEffectCategory.BENEFICIAL, 5592575);
        this.addAttributeModifier(AttributeRegistry.MAX_MANA.get(), "431f1673-4f11-4041-88aa-a99df57dfb4d", 200, AttributeModifier.Operation.ADDITION)
                .addAttributeModifier(AttributeRegistry.MANA_REGEN.get(), "ac27d5d0-4ad5-417c-8225-f3d2f248da2f", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
                .addAttributeModifier(AttributeRegistry.SPELL_POWER.get(), "31791688-5adc-46c8-b2a4-529063cfc51f", 0, AttributeModifier.Operation.MULTIPLY_TOTAL)
                .addAttributeModifier(AttributeRegistry.SPELL_RESIST.get(), "0655c647-3d34-45e9-b6ae-96ef10ec246f", 0, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
