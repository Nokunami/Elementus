package net.nokunami.elementus.client.model;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public abstract class EHierarchicalModel<T extends Entity> extends HierarchicalModel<T> {
    private final ModelPart root;

    public EHierarchicalModel(ModelPart rootPart) { root = rootPart; }

    @Override public @NotNull ModelPart root() { return root; }

    public abstract void postSetupAnim(T entity, CustomModelProperties modelProperties);
}
