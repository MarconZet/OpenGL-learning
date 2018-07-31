package pl.marconzet.engine.properties;

import org.joml.Matrix4f;
import pl.marconzet.engine.DisplayManager;
import pl.marconzet.engine.shader.StaticShader;

public class CameraProperty {

    private float fov;
    private float near_plane;
    private float far_plane;

    private Matrix4f projectionMatrix;

    public CameraProperty(StaticShader shader) {
        this.fov = 70;
        this.near_plane = 0.1f;
        this.far_plane = 1000f;
        createProjectionMatrix();
        loadProjectionMatrixToShader(shader);
    }

    public CameraProperty(float fov, float near_plane, float far_plane, StaticShader shader) {
        this.fov = fov;
        this.near_plane = near_plane;
        this.far_plane = far_plane;
        createProjectionMatrix();
        loadProjectionMatrixToShader(shader);
    }

    private void loadProjectionMatrixToShader(StaticShader shader){
        shader.start();
        shader.loadProjectionMatrix(this.getProjectionMatrix());
        shader.stop();
    }

    private void createProjectionMatrix(){
        float aspectRatio = (float) DisplayManager.WIDTH / (float) DisplayManager.HEIGHT;
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = far_plane - near_plane;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((far_plane + near_plane) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * near_plane * far_plane) / frustum_length));
        projectionMatrix.m33(0);
    }

    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
}

