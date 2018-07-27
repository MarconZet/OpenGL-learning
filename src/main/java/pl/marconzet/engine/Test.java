package pl.marconzet.engine;

import pl.marconzet.engine.shader.StaticShader;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by MarconZet on 26.07.2018.
 */
public class Test {
    private static DisplayManager display = new DisplayManager();

    public static void main(String... args){
        Loader loader = new Loader();
        Renderer renderer = new Renderer();

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f,0
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };
        display.createDisplay();

        StaticShader shader = new StaticShader();

        RawModel model = loader.loadToVAO(vertices, indices);

        while(!glfwWindowShouldClose(display.getWindowHandle())){
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
            display.updateDisplay();
        }

        shader.cleanUp();
        loader.cleanUp();
        display.closeDisplay();
    }
}
