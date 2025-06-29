package nokunami.elementus.datagen.generators;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraftforge.common.data.ExistingFileHelper;
import nokunami.elementus.common.Etags;
import nokunami.elementus.common.registry.ModDamageTypes;
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
        tag(Etags.DamageTypes.STEEL_GOLEM_IMMUNE).add(DamageTypes.FALL);
        tag(DamageTypeTags.BYPASSES_ARMOR).addOptional(ModDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_EFFECTS).addOptional(ModDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).addOptional(ModDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_RESISTANCE).addOptional(ModDamageTypes.SACRIFICIAL.location());
        tag(DamageTypeTags.BYPASSES_SHIELD).addOptional(ModDamageTypes.SACRIFICIAL.location());
    }
}
