package org.example.engine.scene;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.example.engine.graph.Model;

public final class Scene {
    private final UnifiedMap<String, Model> modelMap;
    private final Projection projection;

    public Scene(int width, int height) {
        modelMap = new UnifiedMap<>();
        projection = new Projection(width, height);
    }

    public void addEntity(Entity entity) {
        String modelId = entity.getModelId();
        Model model = modelMap.get(modelId);
        if (model == null) {
            throw new RuntimeException("Could not find model [" + modelId + "]");
        }

        model.entities().add(entity);
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

    public void resize(int width, int height) {
        projection.updateProjectionMatrix(width, height);
    }
}
