package net.nokunami.elementus.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.nokunami.elementus.common.registry.EDamageTypes;
import net.nokunami.elementus.common.tags.EDamageTypeTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModDamageTypeTagsData extends DamageTypeTagsProvider {

    public ModDamageTypeTagsData(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        this.elementusTags();
    }

    private void elementusTags() {
        tag(EDamageTypeTags.STEEL_GOLEM_IMMUNE).add(DamageTypes.FALL);
        tag(EDamageTypeTags.IS_MAGIC).add(DamageTypes.MAGIC, DamageTypes.INDIRECT_MAGIC);
        tag(DamageTypeTags.BYPASSES_ARMOR).addOptional(EDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_EFFECTS).addOptional(EDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).addOptional(EDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_RESISTANCE).addOptional(EDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_SHIELD).addOptional(EDamageTypes.SACRIFICIAL.location());
    }
}
