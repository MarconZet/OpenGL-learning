package pl.marconzet.engine;

import org.lwjgl.glfw.GLFW;

public class Input {
    private long windowHandle;

    public static Input INSTANCE;

    public Input(long windowHandle) {
        this.windowHandle = windowHandle;
        INSTANCE = this;
    }

    public boolean isKeyPresed(int keyValue){
        return GLFW.glfwGetKey(windowHandle, keyValue) == GLFW.GLFW_PRESS;
    }
}
