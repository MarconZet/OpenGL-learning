package pl.marconzet.engine;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.opengl.GL11.*;


/**
 * Created by MarconZet on 26.07.2018.
 */
public class DisplayManager {

    private long windowHandle;

    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
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
