package pl.marconzet.engine;

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
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        display.createDisplay();
        RawModel model = loader.loadToVAO(vertices);

        while(!glfwWindowShouldClose(display.getWindowHandle())){
            //renderer.prepare();
            renderer.render(model);
            display.updateDisplay();
        }

        loader.cleanUp();
        display.closeDisplay();
    }
}
