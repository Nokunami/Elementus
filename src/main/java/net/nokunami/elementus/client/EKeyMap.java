package net.nokunami.elementus.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nokunami.elementus.common.network.ENet;
import net.nokunami.elementus.common.network.server.ability.CancelCastC2SPacket;
import net.nokunami.elementus.common.network.server.ability.OnCastStopC2SPacket;
import net.nokunami.elementus.common.network.server.ability.OnCastStartC2SPacket;

import static net.nokunami.elementus.Elementus.EID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EKeyMap {

    public static final KeyMapping ABILITY_CAST_KEY = new KeyMapping(
            "key.elementus.catalyst_ability",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_X,
            KeyMapping.CATEGORY_GAMEPLAY
    );
    public static final KeyMapping ABILITY_SWITCH_KEY = new KeyMapping(
            "key.elementus.catalyst_ability_selection",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_LALT,
            KeyMapping.CATEGORY_GAMEPLAY
    );

    @SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent event) {
        event.register(ABILITY_CAST_KEY);
        event.register(ABILITY_SWITCH_KEY);
    }

    public static void onKeyDown(final InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

//        if (mc.isPaused()) ENet.sendToServer(new OnCastStopC2SPacket(false));

        if (mc.level == null || mc.screen != null || mc.isPaused() || player == null) {
            ENet.sendToServer(new CancelCastC2SPacket());
            return;
        }

        boolean isKey = event.getKey() == EKeyMap.ABILITY_CAST_KEY.getKey().getValue();

        if (isKey) {
            if (event.getAction() == InputConstants.PRESS) ENet.sendToServer(new OnCastStartC2SPacket());
            if (event.getAction() == InputConstants.RELEASE) ENet.sendToServer(new OnCastStopC2SPacket());
        }
    }
}
