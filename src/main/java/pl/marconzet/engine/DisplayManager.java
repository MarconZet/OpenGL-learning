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

    public void createDisplay() {
        GLFWErrorCallback errorCallback;
        glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        windowHandle = glfwCreateWindow(WIDTH, HEIGHT, "Warp Engine Demo", NULL, NULL);
        if (windowHandle == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        glfwMakeContextCurrent(windowHandle);
        GL.createCapabilities();

        glfwSwapInterval(1);
        glfwShowWindow(windowHandle);

        glClearColor(1, 0, 0, 1);
    }

    public void updateDisplay() {
        glClear(GL_COLOR_BUFFER_BIT);
        glfwSwapBuffers(windowHandle);
        glfwPollEvents();
    }


    public void closeDisplay() {
        try {
            glfwFreeCallbacks(windowHandle);
            glfwDestroyWindow(windowHandle);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            glfwTerminate();
        }
    }

    public long getWindowHandle() {
        return windowHandle;
    }
}
