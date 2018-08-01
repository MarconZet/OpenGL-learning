package pl.marconzet.engine.entity;

import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import pl.marconzet.engine.Input;
import pl.marconzet.engine.properties.CameraProperty;
import pl.marconzet.engine.properties.TransformationProperty;

public class Camera {
    private CameraProperty cameraProperty;
    private TransformationProperty transformationProperty;


    public Camera(CameraProperty cameraProperty, TransformationProperty transformationProperty) {
        this.cameraProperty = cameraProperty;
        this.transformationProperty = transformationProperty;
    }

    public void move(){
        Vector3f translatation = new Vector3f();
        Vector3f rotation = new Vector3f();
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_W)) translatation.z += -0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_S)) translatation.z += 0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_D)) translatation.x += 0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_A)) translatation.x += -0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_SPACE)) translatation.y += 0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_LEFT_SHIFT)) translatation.y += -0.02f;
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_E)) rotation.add(0,(float)Math.PI/128,0);
        if(Input.INSTANCE.isKeyPresed(GLFW.GLFW_KEY_Q)) rotation.add(0,-(float)Math.PI/128,0);
        translatation.rotate(transformationProperty.getRotation());
        transformationProperty.increasePosition(translatation);
        transformationProperty.increaseRotation(rotation);
    }

    public CameraProperty getCameraProperty() {
        return cameraProperty;
    }

    public TransformationProperty getTransformationProperty() {
        return transformationProperty;
    }
}
