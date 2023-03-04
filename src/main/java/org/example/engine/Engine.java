package org.example.engine;

import org.example.engine.graph.Render;
import org.example.engine.scene.Scene;

public final class Engine {
    public static final int TARGET_UPS = 30;
    private final IAppLogic appLogic;
    private final Window window;
    private final Render render;
    private final Scene scene;
    private boolean running;
    private final int targetFps;
    private final int targetUps;

    public Engine(String windowTitle, WindowOptions windowOptions, IAppLogic appLogic) {
        window = new Window(windowTitle, windowOptions, () -> {
            resize();
            return null;
        });
        targetFps = windowOptions.fps();
        targetUps = windowOptions.ups();
        this.appLogic = appLogic;
        this.render = new Render();
        this.scene = new Scene(windowOptions.width(), windowOptions.height());

        appLogic.init(window, scene, render);
        running = true;
    }

    public void start() {
        running = true;
        run();
    }

    public void stop() {
        running = false;
    }

    private void run() {
        long initialTime = System.currentTimeMillis();
        float timeU = 1000.0f / targetUps;
        float timeR = 1000.0f / targetFps;
        float deltaUpdate = 0;
        float deltaFps = 0;

        long updateTime = initialTime;
        while (running && !window.windowShouldClose()) {
            window.pollEvents();

            long now = System.currentTimeMillis();
            deltaUpdate += (now - initialTime) / timeU;
            deltaFps += (now - initialTime) / timeR;

            if (targetFps <= 0 || deltaFps >= 1) {
                appLogic.input(window, scene, now - initialTime);
            }

            if (deltaUpdate >= 1) {
                long diffTimeMillis = now - updateTime;
                appLogic.update(window, scene, diffTimeMillis);
                updateTime = now;
                deltaUpdate--;
            }

            if (targetFps <= 0 || deltaFps >= 1) {
                render.render(window, scene);
                deltaFps--;
                window.update();
            }
            initialTime = now;
        }

        cleanup();
    }

    private void cleanup() {
        appLogic.cleanup();
        render.cleanup();
        scene.cleanup();
        window.cleanup();
    }

    private void resize() {

    }
}
