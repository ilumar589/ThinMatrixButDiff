package org.example.engine.graph;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.example.engine.scene.Entity;

public record Model(String id, MutableList<Entity> entities, ImmutableList<Mesh> meshes) {

    public Model(String id, ImmutableList<Mesh> meshes) {
        this(id, Lists.mutable.empty(), meshes);
    }

    public void cleanup() {
        meshes.forEach(Mesh::cleanup);
    }
}
