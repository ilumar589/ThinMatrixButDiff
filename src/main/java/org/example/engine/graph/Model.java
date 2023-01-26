package org.example.engine.graph;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.example.engine.scene.Entity;

public record Model(String id, MutableList<Entity> entities, ImmutableList<Material> materials) {

    public Model(String id, ImmutableList<Material> materials) {
        this(id, Lists.mutable.empty(), materials);
    }

    public void cleanup() {
        materials.forEach(Material::cleanUp);
    }
}
