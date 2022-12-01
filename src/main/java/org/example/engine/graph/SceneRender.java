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

    public SceneRender() {
        ImmutableList<ShaderModuleData> shaderModuleDataList = Lists.immutable.of(
                new ShaderModuleData("vertex", GL_VERTEX_SHADER),
                new ShaderModuleData("fragment", GL_FRAGMENT_SHADER)
        );

        shaderProgram = new ShaderProgram(shaderModuleDataList);
    }

    public void render(Scene scene) {
        shaderProgram.bind();

        scene.getMeshMap().forEachValue(mesh -> {
            glBindVertexArray(mesh.vaoId());
            glDrawElements(GL_TRIANGLES, mesh.numVertices(), GL_UNSIGNED_INT, 0);
        });

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }
}
