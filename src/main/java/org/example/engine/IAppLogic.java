package org.example.engine;

import org.example.engine.graph.Render;
import org.example.engine.scene.Scene;

public interface IAppLogic {
    void cleanup();
    void init(Window window, Scene scene, Render render);
    void input(Window window, Scene scene, long diffTimeMillis);
    void update(Window window, Scene scene, long diffTimeMillis);
}
