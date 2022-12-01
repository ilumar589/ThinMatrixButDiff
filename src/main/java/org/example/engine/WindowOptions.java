package org.example.engine;

public record WindowOptions(
        boolean compatibleProfile,
        int fps,
        int width,
        int height,
        int ups) {

    public WindowOptions(boolean compatibleProfile, int fps, int width, int height) {
        this(compatibleProfile, fps, width, height, Engine.TARGET_UPS);
    }
}
