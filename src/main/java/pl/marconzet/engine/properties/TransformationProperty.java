package pl.marconzet.engine.properties;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

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

    public TransformationProperty() {
        this.translation = new Vector3f(0f);
        this.rotation = new Quaternionf();
        this.scale = new Vector3f(1f);
    }

    public Matrix4f toTransformationMatrix(){
        Matrix4f matrix = new Matrix4f();
        matrix.translate(translation);
        matrix.rotate(rotation);
        matrix.scale(scale);
        return matrix;
    }
    public Matrix4f toViewMatrix(){
        Matrix4f matrix = new Matrix4f();
        Vector3f tmp = new Vector3f();
        matrix.rotate(rotation);
        matrix.translate(translation.negate(tmp));
        return matrix;
    }

    public void increasePosition(Vector3f vector3f){
        translation = translation.add(vector3f);
    }

    public void increaseRotation(Vector3f vector3f){
        rotation = rotation.rotateXYZ(vector3f.x, vector3f.y, vector3f.z);
    }

    public void increseScale(Vector3f vector3f){
        scale = scale.add(vector3f);
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
