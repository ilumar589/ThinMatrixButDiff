package org.example.engine.scene;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.eclipse.collections.impl.tuple.Tuples;
import org.example.engine.graph.Mesh;

public final class Scene {
    private UnifiedMap<String, Mesh> meshMap;

    public Scene() {
        meshMap = new UnifiedMap<>();
    }

    public void addMesh(String meshId, Mesh mesh) {
        meshMap.add(Tuples.pair(meshId, mesh));
    }

    public void cleanup() {
        meshMap.forEachValue(Mesh::cleanup);
    }

    public UnifiedMap<String, Mesh> getMeshMap() {
        return meshMap;
    }
}
