package org.example.engine.graph;

import org.eclipse.collections.api.list.ImmutableList;

public record Material(String texturePath, ImmutableList<Mesh> meshes) {
    public void cleanUp() {
        meshes.forEach(Mesh::cleanUp);
    }
}
