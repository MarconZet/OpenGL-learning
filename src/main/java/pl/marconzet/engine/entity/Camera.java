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
        Vector3f translatation = new Vector3f();
        Vector3f rotation = new Vector3f();
        if(isKeyPresed(GLFW.GLFW_KEY_W, windowHandle)) translatation.z += -0.02f;
        if(isKeyPresed(GLFW.GLFW_KEY_S, windowHandle)) translatation.z += 0.02f;
        if(isKeyPresed(GLFW.GLFW_KEY_D, windowHandle)) translatation.x += 0.02f;
        if(isKeyPresed(GLFW.GLFW_KEY_A, windowHandle)) translatation.x += -0.02f;
        if(isKeyPresed(GLFW.GLFW_KEY_E, windowHandle)) rotation.add(0,(float)Math.PI/128,0);
        if(isKeyPresed(GLFW.GLFW_KEY_Q, windowHandle)) rotation.add(0,-(float)Math.PI/128,0);
        translatation.rotate(transformationProperty.getRotation());
        transformationProperty.increasePosition(translatation);
        transformationProperty.increaseRotation(rotation);
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
