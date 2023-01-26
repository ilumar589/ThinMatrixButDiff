package org.example.engine.graph;

import org.eclipse.collections.impl.map.mutable.UnifiedMap;
import org.joml.Matrix4f;

import static org.lwjgl.opengl.GL20.*;

public final class ProgramUniformsCache {
    private final int programId;
    private final UnifiedMap<String, Integer> uniforms;

    public ProgramUniformsCache(int programId) {
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
        glUniformMatrix4fv(getUniformLocation(uniformName), false, value.get(new float[16]));
    }

    public void setUniform(String uniformName, int value) {
        glUniform1i(getUniformLocation(uniformName), value);
    }

    private int getUniformLocation(String uniformName) {
        Integer location = uniforms.get(uniformName);
        if (location == null) {
            throw new RuntimeException("Could not find uniforms [" + uniformName + "]");
        }

        return location;
    }
}
