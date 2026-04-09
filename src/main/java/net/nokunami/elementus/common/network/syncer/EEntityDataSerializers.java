package net.nokunami.elementus.common.network.syncer;

import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.nokunami.elementus.common.entity.CombatAction;
import net.nokunami.elementus.common.entity.MovementType;

public class EEntityDataSerializers {
    public static final EntityDataSerializer<MovementType> MOVEMENT_TYPE = EntityDataSerializer.simpleEnum(MovementType.class);
    public static final EntityDataSerializer<CombatAction> COMBAT_ACTION = EntityDataSerializer.simpleEnum(CombatAction.class);

    static {
        EntityDataSerializers.registerSerializer(MOVEMENT_TYPE);
        EntityDataSerializers.registerSerializer(COMBAT_ACTION);
    }
}
