package org.example.engine.scene;

import org.joml.Matrix4f;

public final class Projection {
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_FAR = 1000.f;
    private static final float Z_NEAR = 0.01f;
    private final Matrix4f projectionMatrix;

    public Projection(int width, int height) {
        projectionMatrix = new Matrix4f();
        updateProjectionMatrix(width, height);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }

    public void updateProjectionMatrix(int width, int height) {
        projectionMatrix.setPerspective(FOV, (float) width / height, Z_NEAR, Z_FAR);
    }


}
