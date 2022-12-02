package org.example.engine.graph;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

public final class UniformsMap {
    private final int programId;
    private final UnifiedMap<String, Integer> uniforms;

    public UniformsMap(int programId) {
        this.programId = programId;
        uniforms = new UnifiedMap<>();
    }

    public void createUniform(String uniformName) {
        int uniformLocation = glGetUniformLocation(programId, uniformName);
        if (uniformLocation < 0) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "] in shader program [" + programId + "]");
        }

        uniforms.put(uniformName, uniformLocation);
    }

    public void setUniform(String uniformName, Matrix4f value) {
        int location = uniforms.getOrDefault(uniformName, -99);
        if (location == -99) {
            throw new RuntimeException("Could not find uniform [" + uniformName + "]");
        }
        glUniformMatrix4fv(location, false, value.get(new float[16]));
    }
}
