package net.nokunami.elementus.common.item.unique;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.item.IShieldedArmor;
import net.nokunami.elementus.common.item.basic.EArmorItem;
import net.nokunami.elementus.common.registry.EArmorMaterials;

import java.util.function.Consumer;

public class LeaviPackItem extends EArmorItem implements IShieldedArmor {

    public LeaviPackItem(Properties properties) { super(EArmorMaterials.SHULKER, ArmorItem.Type.CHESTPLATE, properties); }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        return Mth.clamp(amount, 0, stack.getMaxDamage() - stack.getDamageValue() - 1);
    }

    @Override
    public boolean isShielded(LivingEntity entity, DamageSource source, ItemStack stack, boolean piercedByArrow) {
        if (!source.is(DamageTypeTags.BYPASSES_SHIELD) && !piercedByArrow) {
            Vec3 vec32 = source.getSourcePosition();
            if (vec32 != null) {
                Vec3 blockVector = MobUtil.calculateViewVector(0, entity.getViewYRot(1) + 180);
                Vec3 vec31 = vec32.vectorTo(entity.position()).normalize();
                vec31 = new Vec3(vec31.x, 0, vec31.z);
                return vec31.dot(blockVector) < 0;
            }
        }
        return false;
    }
}