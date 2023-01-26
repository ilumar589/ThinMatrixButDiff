package org.example.engine.graph;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.MutableList;

public record Material(String texturePath, MutableList<Mesh> meshes) {
    public Material(String texturePath) {
        this(texturePath, Lists.mutable.empty());
    }

    public void cleanUp() {
        meshes.forEach(Mesh::cleanUp);
    }
}
