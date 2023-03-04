package org.example.engine.graph;

import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.example.engine.scene.Entity;
import org.example.engine.scene.Scene;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL20C.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20C.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public final class SceneRender {
    private final ShaderProgram shaderProgram;
    private final ProgramUniformsCache programUniformsCache;

    public SceneRender() {
        ImmutableList<ShaderModuleData> shaderModuleDataList = Lists.immutable.of(
                new ShaderModuleData("vertex", GL_VERTEX_SHADER),
                new ShaderModuleData("fragment", GL_FRAGMENT_SHADER)
        );

        shaderProgram = new ShaderProgram(shaderModuleDataList);
        programUniformsCache = createUniforms();
    }

    public void render(Scene scene) {
        shaderProgram.bind();

        programUniformsCache.setUniform("projectionMatrix", scene.getProjection().getProjectionMatrix());
        programUniformsCache.setUniform("txtSampler", 0);

        TextureCache textureCache = scene.getTextureCache();

        scene.getModelMap().forEachValue(model -> {
            ImmutableList<Entity> entities = model.entities();

            model.materials().forEach(material -> {
                Texture texture = textureCache.getOrCreateTexture(material.texturePath());
                glActiveTexture(GL_TEXTURE0);
                texture.bind();

                material.meshes().forEach(mesh -> {
                    glBindVertexArray(mesh.vaoId());
                    entities.forEach(entity -> {
                        programUniformsCache.setUniform("modelMatrix", entity.getModelMatrix());
                        glDrawElements(GL_TRIANGLES, mesh.numVertices(), GL_UNSIGNED_INT, 0);
                    });
                });
            });
        });

        glBindVertexArray(0);

        shaderProgram.unbind();
    }

    public void cleanup() {
        shaderProgram.cleanUp();
    }

    private ProgramUniformsCache createUniforms() {
        ProgramUniformsCache programUniformsCacheRef = new ProgramUniformsCache(shaderProgram.getProgramId());
        programUniformsCacheRef.createUniform("projectionMatrix");
        programUniformsCacheRef.createUniform("modelMatrix");
        programUniformsCacheRef.createUniform("txtSampler");

        return programUniformsCacheRef;
    }
}
