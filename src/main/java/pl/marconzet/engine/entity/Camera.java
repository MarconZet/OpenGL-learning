package pl.marconzet.engine.entity;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import pl.marconzet.engine.properties.CameraProperty;
import pl.marconzet.engine.properties.TransformationProperty;

public class Camera {
    private CameraProperty cameraProperty;
    private TransformationProperty transformationProperty;


    public Camera(CameraProperty cameraProperty, TransformationProperty transformationProperty) {
        this.cameraProperty = cameraProperty;
        this.transformationProperty = transformationProperty;
    }

    public void move(long windowHandle){
        if(isKeyPresed(GLFW.GLFW_KEY_W, windowHandle))
            transformationProperty.increasePosition(new Vector3f(0, 0, -0.02f));
        if(isKeyPresed(GLFW.GLFW_KEY_D, windowHandle))
            transformationProperty.increasePosition(new Vector3f(0.02f, 0, 0));
        if(isKeyPresed(GLFW.GLFW_KEY_A, windowHandle))
            transformationProperty.increasePosition(new Vector3f(-0.02f, 0, 0));
        if(isKeyPresed(GLFW.GLFW_KEY_S, windowHandle))
            transformationProperty.increasePosition(new Vector3f(0, 0, 0.02f));
        if(isKeyPresed(GLFW.GLFW_KEY_E, windowHandle))
            transformationProperty.increaseRotation(new Vector3f(0, (float)Math.PI/128, 0));
        if(isKeyPresed(GLFW.GLFW_KEY_Q, windowHandle))
            transformationProperty.increaseRotation(new Vector3f(0, -(float)Math.PI/128, 0));

    }

    private boolean isKeyPresed(int keyValue, long windowHandle){
        return GLFW.glfwGetKey(windowHandle, keyValue) == GLFW.GLFW_PRESS;
    }

    public CameraProperty getCameraProperty() {
        return cameraProperty;
    }

    public TransformationProperty getTransformationProperty() {
        return transformationProperty;
    }
}
