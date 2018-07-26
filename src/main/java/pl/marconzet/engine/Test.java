package pl.marconzet.engine;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by MarconZet on 26.07.2018.
 */
public class Test {
    private static DisplayManager display = new DisplayManager();

    public static void main(String... args){
        display.createDisplay();
        while(!glfwWindowShouldClose(display.getWindowHandle())){
            display.updateDisplay();
        }
        display.closeDisplay();
    }
}
