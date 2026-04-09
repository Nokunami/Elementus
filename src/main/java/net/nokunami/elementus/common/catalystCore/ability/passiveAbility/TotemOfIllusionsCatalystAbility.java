package net.nokunami.elementus.common.catalystCore.ability.passiveAbility;

import com.faboslav.friendsandfoes.common.init.FriendsAndFoesItems;
import com.faboslav.friendsandfoes.common.network.packet.TotemEffectPacket;
import com.faboslav.friendsandfoes.common.util.TotemUtil;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.nokunami.elementus.ModChecker;
import net.nokunami.elementus.common.catalystCore.ability.PassiveCatalystAbility;
import net.nokunami.elementus.common.entity.MobUtil;
import net.nokunami.elementus.common.registry.EMobEffects;

import static net.nokunami.elementus.common.config.catalystConfigs.CatalystArmorConfig.totem_Cooldown;
import static net.nokunami.elementus.common.entity.MobUtil.EffectApplier.EAInst;

public class TotemOfIllusionsCatalystAbility extends PassiveCatalystAbility {

    @Override
    public void postDamageEvent(LivingDamageEvent event) {
        if (ModChecker.friendsandfoes) {
            LivingEntity entity = event.getEntity();
            TotemEffectPacket.sendToClient(((Player) entity), new ItemStack(FriendsAndFoesItems.TOTEM_OF_ILLUSION.get()));
            if (MobUtil.healthPercent(entity, 0.5F) && entity instanceof Player player) TotemUtil.createIllusions(player);
//            MobUtil.applyEffect(entity, EMobEffects.TOTEM_COOLDOWN, totem_Cooldown, 0);
            EAInst(entity, EMobEffects.TOTEM_COOLDOWN).stats(totem_Cooldown).apply();
        }
    }
}
