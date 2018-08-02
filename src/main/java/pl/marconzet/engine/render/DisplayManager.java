package pl.marconzet.engine.render;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;


/**
 * Created by MarconZet on 26.07.2018.
 */
public class DisplayManager {

    private long windowHandle;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    private GLFWErrorCallback errorCallback;

    public void createDisplay() {
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");


        windowHandle = glfwCreateWindow(WIDTH, HEIGHT, "Warp Engine Demo", NULL, NULL);
        if (windowHandle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        glfwMakeContextCurrent(windowHandle);
        glfwSwapInterval(1);
        glfwShowWindow(windowHandle);

        GL.createCapabilities();
    }

    public void updateDisplay() {
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }


    public void closeDisplay() {
        glfwDestroyWindow(windowHandle);
        glfwTerminate();
    }

    public long getWindowHandle() {
        return windowHandle;
    }

    public boolean isCloseRequested() {
        return glfwWindowShouldClose(windowHandle);
    }
}
