package pl.marconzet.engine.properties;

import org.joml.Quaternionf;

import javax.vecmath.Matrix4f;
import javax.vecmath.Vector3f;

public class TransformationProperty {
    private Vector3f translation;
    private Quaternionf rotation;
    private Vector3f scale;

    public TransformationProperty(Vector3f translation, Vector3f rotation, float scale) {
        this.translation = translation;
        this.rotation = new Quaternionf();
        this.rotation.rotateXYZ(rotation.x, rotation.y, rotation.z);
        this.scale = new Vector3f();
        this.scale.set(scale, scale, scale);
    }

    public Matrix4f toTransforationMatrix(){
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        matrix.setTranslation(translation);
        throw new UnsupportedOperationException("Szymon help pls");
    }

    public Vector3f getTranslation() {
        return translation;
    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }

    public Quaternionf getRotation() {
        return rotation;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
}
