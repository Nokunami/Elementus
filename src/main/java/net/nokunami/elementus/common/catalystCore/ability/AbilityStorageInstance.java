package net.nokunami.elementus.common.catalystCore.ability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.nokunami.elementus.common.registry.ERegistries;

public class AbilityStorageInstance {
    private final AbstractAbility ability;
    private boolean selected;
    private boolean addToGUI;

    public AbilityStorageInstance(AbstractAbility abstractAbility) { ability = abstractAbility; }

    public AbstractAbility getAbility() { return ability; }
    public boolean isSelected() { return selected; }
    public boolean addToGUI() { return addToGUI; }

    public AbilityStorageInstance setSelected(boolean b) { selected = b; return this; }
    public AbilityStorageInstance shouldAddToGui(boolean b) { addToGUI = b; return this; }

    public CompoundTag save() {
        var tag = new CompoundTag();
        tag.putString("Id", ability.getAsString());
        tag.putBoolean("Selected", isSelected());
        return tag;
    }
    public AbilityStorageInstance(CompoundTag tag) {
        ability = ERegistries.getAbility(tag.getString("Id"));
        selected = tag.getBoolean("Selected");
    }

    public AbilityStorageInstance(FriendlyByteBuf buf) {
        ability = ERegistries.getAbility(buf.readUtf());
        selected = buf.readBoolean();
        addToGUI = buf.readBoolean();
    }
    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeUtf(getAbility().getAsString());
        buf.writeBoolean(selected);
        buf.writeBoolean(addToGUI);
    }
}