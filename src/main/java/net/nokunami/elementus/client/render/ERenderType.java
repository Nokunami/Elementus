package net.nokunami.elementus.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ERenderType extends RenderType {
    public ERenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    public static RenderType GLOW(ResourceLocation locationIn) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(locationIn, false, false);
        return create("glow", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 1536, false, true,
                CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_EMISSIVE_SHADER)
                        .setTextureState(renderstateshard$texturestateshard)
                        .setTransparencyState(ADDITIVE_TRANSPARENCY)
                        .setCullState(NO_CULL)
                        .setWriteMaskState(COLOR_WRITE)
                        .setOverlayState(OVERLAY)
                        .createCompositeState(false));
    }

    public static RenderType eyesNoAlpha(ResourceLocation p_110489_) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(p_110489_, false, false);
        return create("eyes_no_alpha", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
                RenderType.CompositeState.builder().setShaderState(RENDERTYPE_EYES_SHADER)
                        .setTextureState(renderstateshard$texturestateshard)
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setWriteMaskState(COLOR_WRITE)
                        .createCompositeState(false));
    }
    public static RenderType noShading(ResourceLocation location) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(location, false, false);
        return create("no_shading", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENERGY_SWIRL_SHADER)
                        .setTextureState(renderstateshard$texturestateshard)
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setOverlayState(OVERLAY)
                        .setWriteMaskState(COLOR_DEPTH_WRITE)
                        .setLayeringState(NO_LAYERING)
                        .createCompositeState(false));

//        RenderType.CompositeState rendertype$compositestate = CompositeState.builder()
//                .setShaderState(RENDERTYPE_ENERGY_SWIRL_SHADER)
//                .setCullState(NO_CULL)
//                .setTextureState(new RenderStateShard.TextureStateShard(location, false, false))
//                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
//                .setLightmapState(LIGHTMAP)
//                .setOverlayState(OVERLAY)
//                .setWriteMaskState(COLOR_DEPTH_WRITE)
//                .setDepthTestState(LEQUAL_DEPTH_TEST)
//                .setLayeringState(NO_LAYERING)
//                .createCompositeState(false);
//        return create("ghost", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, rendertype$compositestate);
    }
    public static RenderType noShadingArmor(ResourceLocation location) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(location, false, false);
        return create("no_shading", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENERGY_SWIRL_SHADER)
                        .setTextureState(renderstateshard$texturestateshard)
                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                        .setOverlayState(OVERLAY)
                        .setWriteMaskState(COLOR_DEPTH_WRITE)
                        .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                        .createCompositeState(false));
    }
    public static RenderType cmTranslucentGlow(ResourceLocation location) {
        RenderStateShard.TextureStateShard renderstateshard$texturestateshard = new RenderStateShard.TextureStateShard(location, false, false);
//        return create("no_shading", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
//                RenderType.CompositeState.builder()
//                        .setShaderState(RENDERTYPE_ENERGY_SWIRL_SHADER)
//                        .setTextureState(renderstateshard$texturestateshard)
//                        .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
//                        .setOverlayState(OVERLAY)
//                        .setWriteMaskState(COLOR_DEPTH_WRITE)
//                        .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
//                        .createCompositeState(false));
        return create("cataclysm_translucent_glow", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true,
                RenderType.CompositeState.builder()
                        .setShaderState(RENDERTYPE_ENERGY_SWIRL_SHADER)
                        .setTextureState(renderstateshard$texturestateshard)
                        .setTransparencyState(ADDITIVE_TRANSPARENCY)
                        .setWriteMaskState(COLOR_WRITE)
                        .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                        .createCompositeState(false));
    }
}
