package net.nokunami.elementus.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static net.nokunami.elementus.Elementus.MODID;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EKeyMap {
    public static final EKeyMap INST = new EKeyMap();

    private EKeyMap() {}

    public static final KeyMapping CATALYST_ABILITY_KEY_1 = new KeyMapping(
            "key.elementus.catalyst_ability_1",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_X,
            KeyMapping.CATEGORY_GAMEPLAY
    );

    public static final KeyMapping CATALYST_ABILITY_KEY_2 = new KeyMapping(
            "key.elementus.catalyst_ability_2",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_C,
            KeyMapping.CATEGORY_GAMEPLAY
    );

    public static final KeyMapping CATALYST_ABILITY_KEY_3 = new KeyMapping(
            "key.elementus.catalyst_ability_3",
            KeyConflictContext.IN_GAME,
            KeyModifier.ALT,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            KeyMapping.CATEGORY_GAMEPLAY
    );
}
