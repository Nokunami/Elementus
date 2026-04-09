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
import net.nokunami.elementus.common.catalystCore.ability.CastType;
import net.nokunami.elementus.common.network.ENetwork;
import net.nokunami.elementus.common.network.server.ability.CancelCastC2SPacket;
import net.nokunami.elementus.common.network.server.ability.CastStateC2SPacket;
import org.lwjgl.glfw.GLFW;

import static net.nokunami.elementus.Elementus.EID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = EID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EKeyMap {
    public static final EKeyMap INST = new EKeyMap();

    public static final KeyMapping CATALYST_ABILITY_KEY = new KeyMapping(
            "key.elementus.catalyst_ability",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_X,
            KeyMapping.CATEGORY_GAMEPLAY
    );
    public static final KeyMapping CATALYST_ABILITY_SWITCH_KEY = new KeyMapping(
            "key.elementus.catalyst_ability_selection",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            InputConstants.KEY_LALT,
            KeyMapping.CATEGORY_GAMEPLAY
    );

    @SubscribeEvent
    public static void registerKeybinds(RegisterKeyMappingsEvent event) {
        event.register(CATALYST_ABILITY_KEY);
        event.register(CATALYST_ABILITY_SWITCH_KEY);
    }

    public static void onKeyDown(final InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (mc.isPaused()) ENetwork.sendToServer(new CancelCastC2SPacket());

        if (mc.level == null || mc.screen != null || mc.isPaused() || player == null) return;

        boolean isKey = event.getKey() == EKeyMap.CATALYST_ABILITY_KEY.getKey().getValue();

        if (isKey) {
            if (event.getAction() == InputConstants.PRESS) ENetwork.sendToServer(new CastStateC2SPacket(CastType.ON_CAST_START));
            if (event.getAction() == InputConstants.RELEASE) ENetwork.sendToServer(new CancelCastC2SPacket());
        }
    }
}
