package org.example.engine.graph;

import org.eclipse.collections.api.list.ImmutableList;
import org.example.engine.scene.Entity;

public record Model(String id, ImmutableList<Entity> entities, ImmutableList<Material> materials) {
    public void cleanup() {
        materials.forEach(Material::cleanUp);
    }
}
