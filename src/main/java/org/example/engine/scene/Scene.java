package org.example.engine.scene;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.example.engine.graph.Model;
import org.example.engine.graph.TextureCache;

public final class Scene {
    private final UnifiedMap<String, Model> modelMap;
    private final Projection projection;
    private final TextureCache textureCache;

    public Scene(int width, int height) {
        modelMap = new UnifiedMap<>();
        projection = new Projection(width, height);
        textureCache = new TextureCache();
    }

    public void addModel(Model model) {
        modelMap.put(model.id(), model);
    }

    public void cleanup() {
        modelMap.forEachValue(Model::cleanup);
    }

    public UnifiedMap<String, Model> getModelMap() {
        return modelMap;
    }

    public Projection getProjection() {
        return projection;
    }

    public TextureCache getTextureCache() {
        return textureCache;
    }

    public void resize(int width, int height) {
        projection.updateProjectionMatrix(width, height);
    }
}
