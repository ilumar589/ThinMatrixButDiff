package org.example.engine.graph;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.example.engine.scene.Scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20C.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20C.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public final class SceneRender {
    private final ShaderProgram shaderProgram;
    private final UniformsMap uniformsMap;

    public SceneRender() {
        ImmutableList<ShaderModuleData> shaderModuleDataList = Lists.immutable.of(
                new ShaderModuleData("vertex", GL_VERTEX_SHADER),
                new ShaderModuleData("fragment", GL_FRAGMENT_SHADER)
        );

        shaderProgram = new ShaderProgram(shaderModuleDataList);
        uniformsMap = new UniformsMap(shaderProgram.getProgramId());
        createUniforms();
    }

    public void render(Scene scene) {
        shaderProgram.bind();

        uniformsMap.setUniform("projectionMatrix", scene.getProjection().getProjectionMatrix());

        scene.getModelMap().forEachValue(model -> model.meshes().forEach(mesh -> {
            glBindVertexArray(mesh.vaoId());
            model.entities().forEach(entity -> {
                uniformsMap.setUniform("modelMatrix", entity.getModelMatrix());
                glDrawElements(GL_TRIANGLES, mesh.numVertices(), GL_UNSIGNED_INT, 0);
            });
        }));

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    private void createUniforms() {
        uniformsMap.createUniform("projectionMatrix");
        uniformsMap.createUniform("modelMatrix");
    }
}
