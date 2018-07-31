package pl.marconzet.engine.shader;

import org.joml.Matrix4f;
import pl.marconzet.engine.entity.Camera;

public class StaticShader extends ShaderProgram {

    private static final String frag_reference = "fragmentShader.glsl";
    private static final String ver_reference = "vertexShader.glsl";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;


    public StaticShader() {
        super(ver_reference, frag_reference);
    }

    @Override
    protected void getAllUniformsLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void loadTransMatrix(Matrix4f matrix){
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(location_projectionMatrix, projection);
    }

    public void loadViewMatrix(Camera camera){
        Matrix4f matrix = camera.getTransformationProperty().toViewMatrix();
        super.loadMatrix(location_viewMatrix, matrix);
    }
}


