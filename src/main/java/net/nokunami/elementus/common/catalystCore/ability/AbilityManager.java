package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.level.Level;
import net.nokunami.elementus.Elementus;
import net.nokunami.elementus.api.IAbilitySupplier;
import net.nokunami.elementus.common.capability.abilityData.AbilityData;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.client.ability.AbilityListSyncS2CPacket;
import net.nokunami.elementus.common.registry.EGameRules;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.nokunami.elementus.api.util.AttributeUtil.attributeUtil;
import static net.nokunami.elementus.common.registry.ERegistries.CatalystCoreHelper.CatalystCoreUtil;

public class AbilityManager {
    public static final UUID castMoveSpeedUUID = UUID.fromString("e644db8d-dedb-45e5-b153-c63dc482dd65");
    public static final AttributeModifier CASTING_MOVEMENT_SPEED = new AttributeModifier(castMoveSpeedUUID, "Casting Speed Movement", -0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL);

    public static void onKeyPress(ServerPlayer player) {
        var data = AbilityData.instance(player);
        var level = player.level();

        data.setCastKeyDown(true);
        if (EGameRules.isDebugModeOn(level))
//            player.sendSystemMessage(Component.literal("Cast Start"));
            logStartCast();
        if (data.getAbilityStorage().isEmpty()) {
            if (data.isCastTicking()) data.setCastTicking(false);
            return;
        }
        data.onCastStart();
    }

    public static void onKeyRelease(ServerPlayer player) {
        var data = AbilityData.instance(player);
        var level = player.level();

        data.setCastKeyDown(false);
        if (EGameRules.isDebugModeOn(level))
//            player.sendSystemMessage(Component.literal("Cast Stop"));
            logStopCast();
        if (data.getAbilityStorage().isEmpty()) {
            if (data.isCastTicking()) data.setCastTicking(false);
            return;
        }
        data.onCastStop();
    }

    public static void onCancel(ServerPlayer player) {
        var data = AbilityData.instance(player);
        var level = player.level();

        if (data.isCastKeyDown()) {
            data.setCastKeyDown(false);
            if (data.isCastTicking()) data.setCastTicking(false);
            if (data.getAbilityStorage().isEmpty()) {
                return;
            }
            data.onCastStop();
            if (EGameRules.isDebugModeOn(level))
//                player.sendSystemMessage(Component.literal("Cast Canceled"));
                logCancelCast();
        }
    }

    public static void onServerCastStart(AbilityData data, Level level) {
        var entity = data.getEntity();
        boolean castState = false;

//        if (!data.getAbilities().isEmpty()) {
//            int selection = Mth.clamp(data.getSelection(), 0, data.getAbilities().size() - 1);
//            data.setCurrentAbility(data.getAbilities().get(selection));
//            var ability = ERegistries.getAbility(data.getCurrentAbilityId());
//
//            if (ability.isReadyToCast(entity)) {
//                ability.onCastStart(entity, level);
//                if (ability.isTriggered()) {
//                    ability.castAbility(entity, level, data);
//                    ability.onCastStop(entity, level, data);
//                    data.addCooldown(ability);
//                } else {
//                    castState = true;
//                    data.setCastKeyDown(castState);
//                    data.setCastTicking(true);
//                }
//            } else {
//                entity.displayClientMessage(ability.failedCastMessage(entity, ability), true);
//            }
//            if (ability.getType().movementSpeedPenalty() || ability.getCastDuration() > 0)
//                attributeUtil(entity, Attributes.MOVEMENT_SPEED).addTransient(CASTING_MOVEMENT_SPEED);
//
//            ENet.sendTo(entity, new SyncCastStateS2CPacket(castState, ability.getCastDuration()));
//        }
        var ability = data.getStoredAbility();

        if (ability != null) {
            if (ability.isReadyToCast(entity) && ability.getPreCast() > 0)
                attributeUtil(entity, Attributes.MOVEMENT_SPEED).addTransient(CASTING_MOVEMENT_SPEED);
        }
    }

    public static void onServerCastStop(ServerPlayer player) {
        var data = AbilityData.instance(player);
        var level = player.level();
        var ability = data.getStoredAbility();
        boolean shouldAddCooldown = false;

//        data.setCastKeyDown(false);
//        if (EGameRules.isDebugModeOn(level)) player.sendSystemMessage(Component.literal("cancel test"));
//        if (ability.isTriggered()) return;
//
//        ENet.sendTo(player, new SyncCastStateS2CPacket(false, 0));
//        if (!data.isOnCooldown(ability) && player.isAlive()) {
//            if (ability.castOnTick(data.getCastTick()) && !data.isCastKeyDown()) {
//                ability.onCastStop(player, level, data);
//                shouldAddCooldown = ability.castAbility(player, level, data);
//            }
//        }
//        data.setCastTicking(false);
//        data.setCastTick(0);
//        attributeUtil(player, Attributes.MOVEMENT_SPEED).removeModifier(castMoveSpeedUUID);
//        if (shouldAddCooldown) data.addCooldown(ability);

//        attributeUtil(player, Attributes.MOVEMENT_SPEED).removeModifier(castMoveSpeedUUID);
//        if (data.getAbilityStorage().isEmpty()) {
//            if (data.isCastKeyDown()) data.setCastKeyDown(false);
//            if (data.isCastTicking()) data.setCastTicking(false);
//            return;
//        }
//        data.setCastKeyDown(false);
//        data.setCastTicking(false);
    }

    public static void sync(LivingEntity entity, Level level) {
        if (level.isClientSide()) return;

        var data = AbilityData.instance(entity);
        data.getAbilityStorage().getAbilitiesInStorage().clear();
        List<AbstractAbility> tempList = new ArrayList<>();
        List<EquipmentSlot> slots = List.of(
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET,
                EquipmentSlot.OFFHAND,
                EquipmentSlot.MAINHAND
        );

        for (EquipmentSlot slot : slots) {
            var itemStack = entity.getItemBySlot(slot);
            var item = itemStack.getItem();
            if (item instanceof Equipable equipable) {
                if (equipable.getEquipmentSlot() == slot && item instanceof IAbilitySupplier supplier)
                    tempList.addAll(supplier.getAbilities(itemStack));
            } else {
                if (item instanceof IAbilitySupplier supplier)
                    tempList.addAll(supplier.getAbilities(itemStack));
            }
        }
//        data.getCooldowns().putPassivesOnList(tempList);
//        data.getAbilityStorage().addAbilitiesToList(tempList);
//        tempList.removeIf(ability -> ability instanceof PassiveAbility && !data.getCooldowns().isOnCooldown(ability));

//        data.getAbilityStorage().addAbilities(tempList, data, ability -> !(ability instanceof PassiveAbility) || data.getCooldowns().isOnCooldown(ability));
        data.getAbilityStorage().addAbilities(tempList, data);
        data.scroll(data.getPrevSelection(), data.getSelection());
        if (entity instanceof ServerPlayer serverPlayer) ENet.sendTo(serverPlayer, new AbilityListSyncS2CPacket(data.getAbilityStorage()));
    }

    public static void syncData(ServerPlayer player) {
        var inst = AbilityData.instance(player);
//        inst.getCooldowns().sync(player);
        inst.sync();
//        ENet.sendTo(player, new SyncAbilitySelectionS2CPacket(inst));
    }

    public static void logStartCast() { Elementus.LOGGER.info("cast start"); }
    public static void logStopCast() { Elementus.LOGGER.info("cast stop"); }
    public static void logCancelCast() { Elementus.LOGGER.info("cast cancelled"); }
    public static void logCastStateSwitch(CastState s) { Elementus.LOGGER.info("cast state -> {}", s); }
}
