package org.example.game;

import org.example.engine.Engine;
import org.example.engine.IAppLogic;
import org.example.engine.Window;
import org.example.engine.WindowOptions;
import org.example.engine.graph.Mesh;
import org.example.engine.graph.Render;
import org.example.engine.scene.Scene;

public class Main implements IAppLogic {

    public static void main(String[] args) {
        Main app = new Main();
        Engine gameEngine = new Engine("chapter-03",
                new WindowOptions(false, 60, 1280, 720),
                app);
        gameEngine.start();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void init(Window window, Scene scene, Render render) {
        float[] positions = new float[] {
                -0.5f, 0.5f, 0.0f,
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.5f, 0.5f, 0.0f,
        };
        float[] colors = new float[] {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        int[] indices = new int[] {
                0, 1, 3, 3, 1, 2,
        };

        Mesh mesh = Mesh.createMesh(positions,colors, indices);
        scene.addMesh("quad", mesh);
    }

    @Override
    public void input(Window window, Scene scene, long diffTimeMillis) {

    }

    @Override
    public void update(Window window, Scene scene, long diffTimeMillis) {

    }
}