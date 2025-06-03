package net.nokunami.elementus.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.Etags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

import static net.nokunami.elementus.Elementus.MODID;

public class ModEntityTypeTags extends EntityTypeTagsProvider {
    public ModEntityTypeTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> compFeaHoldP, @Nullable ExistingFileHelper existHelper) {
        super(packOutput, compFeaHoldP, MODID, existHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {

        this.tag(Etags.Entity.STEEL_GOLEM_PRIORITY_TARGETS)
                .add(EntityType.EVOKER)
                .addOptional(new ResourceLocation("goety", "obsidian_monolith"))
                .addOptional(new ResourceLocation("goety", "inferno"))
                .addOptional(new ResourceLocation("mowziesmobs", "umvuthana_crane"))
                .addOptional(new ResourceLocation("mowziesmobs", "umvuthana_follower_raptor"))
                .addOptional(new ResourceLocation("mowziesmobs", "umvuthana_raptor"))
                .addOptional(new ResourceLocation("mowziesmobs", "umvuthana"));

        this.tag(Etags.Entity.STEEL_GOLEM_AVOID)
                .addOptional(new ResourceLocation("goety", "hell_cloud"))
                .addOptional(new ResourceLocation("goety", "fire_tornado"));
    }
}
